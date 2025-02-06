package com.bkash.savings.migrator_dynamodb_pg.migrators.product;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.ProductSchemeEntity;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryAvailabilityManagement;
import com.bkash.savings.migrator_dynamodb_pg.mappers.DateConversion;
import com.bkash.savings.models.postgres.account.SavingsType;
import com.bkash.savings.models.postgres.product.Availability;
import com.bkash.savings.models.postgres.product.Term;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Todo: product availablity
// Todo: product types


@Component
public class ProductProcessor implements ItemProcessor<ProductSchemeEntity, com.bkash.savings.models.postgres.product.ProductEntity> {

    private final InMemoryAvailabilityManagement inMemoryAvailabilityManagement;

    public ProductProcessor(InMemoryAvailabilityManagement inMemoryAvailabilityManagement) {
        this.inMemoryAvailabilityManagement = inMemoryAvailabilityManagement;
    }

    @Override
    public com.bkash.savings.models.postgres.product.ProductEntity process(ProductSchemeEntity entity) throws Exception {
        var productAvailbilities = new ArrayList<Availability>();

        for(var option : entity.getAvailabilityOption()){
            var a = inMemoryAvailabilityManagement
                    .getAvailabilities()
                    .stream()
                    .filter(x->x.getOption().equals(option))
                    .findFirst()
                    .orElse(null);

            productAvailbilities.add(a);
        }

        var product = com.bkash.savings.models.postgres.product.ProductEntity.builder()
                .productId(entity.getProductId())
                .productType(entity.getProductType())
                .savingsType(SavingsType.DPS)    // Assuming `SavingsType` is an enum you have defined
                .interest(Double.parseDouble(entity.getInterest()))
                .tenure(Integer.parseInt(entity.getTenure()))
                .term(Term.valueOf(entity.getTerm()))
                .autoRenewal(entity.getAutoRenewal())
//                Todo-akash : check
//                .autoRenewalMode(entity.getAutoRenewal())
                .activeFrom(DateConversion.toLocalDate(entity.getActiveFrom()))
                .deActiveFrom(DateConversion.toLocalDate(entity.getDeActiveFrom()))
                .amount(new BigDecimal(entity.getAmount()))
                .totalPayout(new BigDecimal(entity.getTotalPayout()))
//               Todo-akash : check
                .totalInterestEarned(DateConversion.toBigDecimal("100"))
                .organizationCode(entity.getOrganizationCode())
                .availabilityOptions(productAvailbilities)
                .build();

        return  product;
    }
}