package com.bkash.savings.migrator_dynamodb_pg.migrators.transaction;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.dto.dps.DpsDateRequest;
import com.bkash.savings.models.dto.dps.DpsDateResponse;
import com.bkash.savings.models.exception.SavingsException;
import com.bkash.savings.models.postgres.product.Term;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DpsDateGenerator {

    private static final int BASE_MONTH_COUNT = 3;
    private static final int BASE_WEEKLY_TENURE = 13;

    /**
     * {@link DpsDateRequest#tenure()} is in months
     */
    public DpsDateResponse getDpsDates(DpsDateRequest request) {
        log.info("DpsDateGenerator|getDpsDates::Generating DPS dates for request: {}", request);

        LocalDate cycleStartDate = request.cycleStartDate() != null ? request.cycleStartDate() :
                determineCycleStartDate(request.startDate(), request.term());

        LocalDate maturityDate = request.startDate().plusMonths(request.tenure());
        LocalDate endDate = calculateEndDate(request.term(), request.tenure(), cycleStartDate);
        log.info("DpsDateGenerator|getDpsDates::Generated DPS dates: cycleStartDate: {}, endDate: {}, maturityDate: {}",
                cycleStartDate, endDate, maturityDate);

        return DpsDateResponse.builder()
                .startDate(request.startDate())
                .cycleStartDate(cycleStartDate)
                .endDate(endDate)
                .maturityDate(maturityDate)
                .build();
    }

    protected LocalDate determineCycleStartDate(LocalDate startDate, Term term) {
        return switch (term) {
            case MONTHLY -> startDate.plusMonths(1);
            case WEEKLY -> startDate.plusWeeks(1);
            default -> throw new IllegalArgumentException("Unsupported term: " + term);
        };
    }

    protected LocalDate calculateEndDate(Term term, int tenure, LocalDate cycleStartDate) {
        String totalPaymentCount = actualPaymentCount(term.name(), String.valueOf(tenure));
        return switch (term) {
            case MONTHLY -> cycleStartDate.plusMonths(Long.parseLong(totalPaymentCount) - 2);
            case WEEKLY -> cycleStartDate.plusWeeks(Long.parseLong(totalPaymentCount) - 2);
            default -> throw new IllegalArgumentException("Unsupported term: " + term);
        };
    }

    public static String actualPaymentCount(String productTerm, String productTenure) {
        if (Term.getByDbValue(productTerm) != Term.WEEKLY) return productTenure;
        int prodTenure = Integer.parseInt(productTenure.trim());
        if (prodTenure % BASE_MONTH_COUNT != 0)
            throw new SavingsException(ApiResponseStatus.REQUEST_PAYLOAD_INVALID.code(), "Tenure count must be one of the multipliers of " + BASE_MONTH_COUNT);
        int tenure = prodTenure / BASE_MONTH_COUNT * BASE_WEEKLY_TENURE;
        return String.valueOf(tenure);
    }
}
