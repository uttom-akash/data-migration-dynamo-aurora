package com.bkash.savings.migrator_dynamodb_pg.migrators.organization;

import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.account.SavingsAccountEntity;
import com.bkash.savings.models.postgres.organization.OrganizationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class OrganizationWriter extends PostgresWriter implements ItemWriter<OrganizationEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends OrganizationEntity> items) throws Exception {

        for (OrganizationEntity organization : items.getItems()) {

            OrganizationEntity existingEntity = (OrganizationEntity) entityManager.createQuery(
                            "SELECT e FROM OrganizationEntity e" +
                                    " WHERE (e.orgDisbursementId = :orgDisbursementId or e.organizationCode = :organizationCode)")
                    .setParameter("orgDisbursementId", organization.getOrgDisbursementId())
                    .setParameter("organizationCode", organization.getOrganizationCode())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            merge(organization, existingEntity);
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}