package com.bkash.savings.migrator_dynamodb_pg.migrators.transaction;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.dto.dps.DpsPlanNumberRequest;
import com.bkash.savings.models.dto.dps.DpsPlanNumberResponse;
import com.bkash.savings.models.dto.dps.InstallmentPlanRequest;
import com.bkash.savings.models.exception.SavingsException;
import com.bkash.savings.models.postgres.product.Term;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.bkash.savings.models.postgres.product.Term.MONTHLY;

@Slf4j
@Service
@RequiredArgsConstructor
public class DpsPlanGenerator {

    private final DpsDateGenerator dpsDateGenerator;

    private static final int THRESHOLD_DAY_ON_RIGHT_FOR_MONTHLY = 7;
    private static final int THRESHOLD_DAY_ON_LEFT_FOR_MONTHLY = 5;

    private static final int THRESHOLD_DAY_ON_RIGHT_FOR_WEEKLY = 4;
    private static final int THRESHOLD_DAY_ON_LEFT_FOR_WEEKLY = 0;

    public List<LocalDate> getInstallmentPlanDates(InstallmentPlanRequest request) {
        log.info("DpsPlanGenerator|getInstallmentPlanDates::Generating payment dates for request: {}", request);
        List<LocalDate> paymentDates = generatePaymentDates(request);
        log.info("DpsPlanGenerator|getInstallmentPlanDates::Generated payment dates: {}", paymentDates);
        return paymentDates;
    }

    public DpsPlanNumberResponse getPlanNumber(DpsPlanNumberRequest request) {
        log.info("DpsPlanGenerator|getPlanNumber::request: {}", request);

        List<LocalDate> paymentDates = getPaymentDates(request);
        log.info("DpsPlanGenerator|getPlanNumber::DpsPlanNoService|getPlanNumber|paymentDates: {}", paymentDates);

        // If due date is not provided, use transaction date as due date
        LocalDate dueDate = request.dueDate() != null ? request.dueDate() : request.trxDate();
        request = request.toBuilder().dueDate(dueDate).build();

        log.info("DpsPlanGenerator|getPlanNumber::dueDate: {}", dueDate);
        int nearestPaymentIndex = findNearestPaymentIndex(paymentDates, request);
        if (nearestPaymentIndex == -1) {
            log.info("DpsPlanGenerator|getPlanNumber::No payment date found for due date: {}", dueDate);
            throw new SavingsException(ApiResponseStatus.REQUEST_PAYLOAD_INVALID.code(), "No payment date found for due date: " + dueDate);
        }
        log.info("DpsPlanGenerator|getPlanNumber::nearestPaymentIndex: {}", nearestPaymentIndex);
        log.info("DpsPlanGenerator|getPlanNumber::Mapping dueDate: = {} with date: {}", request.dueDate(), paymentDates.get(nearestPaymentIndex));

        return DpsPlanNumberResponse.builder()
                .planNo(nearestPaymentIndex + 1)
                .dueDate(paymentDates.get(nearestPaymentIndex))
                .build();
    }

    private List<LocalDate> getPaymentDates(DpsPlanNumberRequest request) {
        InstallmentPlanRequest installmentPlanRequest = InstallmentPlanRequest.builder()
                .startDate(request.startDate())
                .cycleStartDate(request.cycleStartDate())
                .term(request.term())
                .tenure(request.tenure())
                .build();

        return generatePaymentDates(installmentPlanRequest);
    }

    private int findNearestPaymentIndex(List<LocalDate> paymentDates, DpsPlanNumberRequest request) {
        LocalDate dueDate = request.dueDate();
        log.info("DpsPlanGenerator|findNearestPaymentIndex::given dueDate: {}", dueDate);
        log.info("DpsPlanGenerator|findNearestPaymentIndex::paymentDates: {}", paymentDates);

        var pos = IntStream.range(0, paymentDates.size())
                .filter(i -> paymentDates.get(i).isEqual(dueDate))
                .findFirst()
                .orElse(-1);

        log.info("DpsPlanGenerator|findNearestPaymentIndex::pos: {}", pos);

        if (pos != -1) return pos;

        return IntStream.range(0, paymentDates.size())
                .filter(i -> {
                    LocalDate paymentDate = paymentDates.get(i);

                    int leftSubtract = request.term().equals(MONTHLY) ? THRESHOLD_DAY_ON_LEFT_FOR_MONTHLY : THRESHOLD_DAY_ON_LEFT_FOR_WEEKLY;
                    int rightAdd = request.term().equals(MONTHLY) ? THRESHOLD_DAY_ON_RIGHT_FOR_MONTHLY : THRESHOLD_DAY_ON_RIGHT_FOR_WEEKLY;

                    LocalDate leftRange = paymentDate.minusDays(leftSubtract);
                    LocalDate rightRange = paymentDate.plusDays(rightAdd);
                    log.info("DpsPlanGenerator|findNearestPaymentIndex::leftRange: {}, rightRange: {}", leftRange, rightRange);

                    return (dueDate.isAfter(leftRange) || dueDate.isEqual(leftRange)) &&
                            (dueDate.isBefore(rightRange) || dueDate.isEqual(rightRange));
                })
                .findFirst()
                .orElse(-1);
    }

    /**
     * <p>
     * The first payment date is the start date from the request. Subsequent payment dates are calculated
     * starting from the cycle start date and are added to the list until the end date is reached.
     * The term of the payments (monthly or weekly) determines the interval between payment dates.
     * </p>
     */
    public List<LocalDate> generatePaymentDates(InstallmentPlanRequest request) {
        log.info("DpsPlanNoService|generatePaymentDates::Generating payment dates for request: {}", request);
        List<LocalDate> paymentDates = new ArrayList<>();
        paymentDates.add(request.startDate());

        LocalDate cycleStartDate = determineCycleStartDate(request);
        request = request.toBuilder().cycleStartDate(cycleStartDate).build();
        log.info("DpsPlanNoService|generatePaymentDates::paymentDate: {}", cycleStartDate);

        LocalDate endDate = calculateEndDate(request);
        return generateDates(paymentDates, cycleStartDate, endDate, request.term());
    }

    /**
     * If the cycle start date is not provided, it is calculated from the start date and term.
     */
    private LocalDate determineCycleStartDate(InstallmentPlanRequest request) {
        LocalDate cycleStartDate = request.cycleStartDate();
        if (cycleStartDate == null) {
            log.info("DpsPlanNoService|generatePaymentDates::cycleStartDate is null, calculating from start date");
            cycleStartDate = dpsDateGenerator.determineCycleStartDate(request.startDate(), request.term());
        }
        return cycleStartDate;
    }

    private LocalDate calculateEndDate(InstallmentPlanRequest request) {
        return dpsDateGenerator.calculateEndDate(request.term(), request.tenure(), request.cycleStartDate());
    }


    /**
     * Generates a list of payment dates based on the given cycle start date, end date, and term.
     * The first payment date is the cycle start date, and subsequent payment dates are calculated
     * based on the term (monthly or weekly) until the end date is reached.
     */
    private List<LocalDate> generateDates(List<LocalDate> paymentDates, LocalDate cycleStartDate, LocalDate endDate, Term term) {
        LocalDate date = cycleStartDate;
        int paymentNo = 2;
        while (!date.isAfter(endDate)) {
            paymentDates.add(date);
            date = switch (term) {
                case MONTHLY -> cycleStartDate.plusMonths(paymentNo - 1);
                case WEEKLY -> cycleStartDate.plusWeeks(paymentNo - 1);
                default -> throw new IllegalArgumentException("Unsupported term: " + term);
            };
            paymentNo++;
        }
        return paymentDates;
    }
}
