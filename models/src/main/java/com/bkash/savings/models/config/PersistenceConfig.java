package com.bkash.savings.models.config;

import com.bkash.savings.common.utils.DateTimeUtils;
import com.bkash.savings.models.properties.PlatformProperties;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import software.amazon.awssdk.services.ssm.SsmClient;

import javax.sql.DataSource;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@EntityScan(basePackages = "com.bkash.savings.models")
@RequiredArgsConstructor
@Slf4j
@DependsOn("ssmClient")
public class PersistenceConfig {

    private final PlatformProperties platformProperties;

    @Bean // Makes ZonedDateTime compatible with auditing fields
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now(DateTimeUtils.ZONE_ID_DHAKA));
    }

    @Bean
    public DataSource writeReplicaDataSource(SsmClient ssmClient) {
        log.info("PersistenceConfig|writeReplicaDataSource:: creating write replica datasource");

        var response =
                ssmClient.getParameter(
                        builder -> builder.name(platformProperties.getDatasource()
                                        .getWriteReplica().getPasswordSsmKey())
                                .withDecryption(true));

        var password = response.parameter().value();

        var datasource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .username(platformProperties.getDatasource().getWriteReplica().getUsername())
                .password(password)
                .url(platformProperties.getDatasource().getWriteReplica().getUrl())
                .build();
        datasource.setMaximumPoolSize(platformProperties.getDatasource().getWriteReplica().getMaximumPoolSize());
        datasource.setMinimumIdle(platformProperties.getDatasource().getWriteReplica().getMinimumIdle());
        datasource.setConnectionTimeout(platformProperties.getDatasource().getWriteReplica().getConnectionTimeout());
        datasource.setIdleTimeout(platformProperties.getDatasource().getWriteReplica().getIdleTimeout());
        datasource.setMaxLifetime(platformProperties.getDatasource().getWriteReplica().getMaximumLifetime());
        datasource.setAutoCommit(platformProperties.getDatasource().getWriteReplica().getAutoCommit());
        datasource.setPoolName(platformProperties.getDatasource().getWriteReplica().getPoolName());
        datasource.setInitializationFailTimeout(platformProperties.getDatasource().getWriteReplica().getInitializationFailTimeout());
        datasource.setLeakDetectionThreshold(platformProperties.getDatasource().getWriteReplica().getLeakDetectionThreshold());
        datasource.setConnectionTestQuery(platformProperties.getDatasource().getWriteReplica().getConnectionTestQuery());
        return datasource;
    }

    @Bean
    public DataSource readReplicaDataSource(SsmClient ssmClient) {
        log.info("PersistenceConfig|readReplicaDataSource:: creating read replica datasource");
        var response =
                ssmClient.getParameter(
                        builder -> builder.name(platformProperties.getDatasource()
                                        .getReadReplica().getPasswordSsmKey())
                                .withDecryption(true));

        var password = response.parameter().value();

        var datasource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .username(platformProperties.getDatasource().getReadReplica().getUsername())
                .password(password)
                .url(platformProperties.getDatasource().getReadReplica().getUrl())
                .build();
        datasource.setMaximumPoolSize(platformProperties.getDatasource().getReadReplica().getMaximumPoolSize());
        datasource.setMinimumIdle(platformProperties.getDatasource().getReadReplica().getMinimumIdle());
        datasource.setConnectionTimeout(platformProperties.getDatasource().getReadReplica().getConnectionTimeout());
        datasource.setIdleTimeout(platformProperties.getDatasource().getReadReplica().getIdleTimeout());
        datasource.setMaxLifetime(platformProperties.getDatasource().getReadReplica().getMaximumLifetime());
        datasource.setAutoCommit(platformProperties.getDatasource().getReadReplica().getAutoCommit());
        datasource.setPoolName(platformProperties.getDatasource().getReadReplica().getPoolName());
        datasource.setInitializationFailTimeout(platformProperties.getDatasource().getReadReplica().getInitializationFailTimeout());
        datasource.setLeakDetectionThreshold(platformProperties.getDatasource().getReadReplica().getLeakDetectionThreshold());
        datasource.setConnectionTestQuery(platformProperties.getDatasource().getReadReplica().getConnectionTestQuery());
        return datasource;
    }

    @Primary
    @Bean
    public DataSource routingDataSource(@Qualifier("writeReplicaDataSource") DataSource writeReplicaDataSource,
                                        @Qualifier("readReplicaDataSource") DataSource readReplicaDataSource) {
        log.info("PersistenceConfig|routingDataSource:: creating routing datasource");
        ReplicaRoutingDataSource routingDataSource = new ReplicaRoutingDataSource();

        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DataSourceType.MASTER, writeReplicaDataSource);
        dataSources.put(DataSourceType.SLAVE, readReplicaDataSource);

        routingDataSource.setTargetDataSources(dataSources);
        routingDataSource.setDefaultTargetDataSource(writeReplicaDataSource);

        return routingDataSource;
    }

    @Bean("entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("routingDataSource") DataSource dataSource) {
        log.info("PersistenceConfig|entityManagerFactory:: creating entity manager factory");
        return builder.dataSource(dataSource)
                .packages("com.bkash.savings.models")
                .persistenceUnit("primary")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        log.info("PersistenceConfig|transactionManager:: creating transaction manager");
        return new JpaTransactionManager(entityManagerFactory);
    }
}
