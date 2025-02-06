package com.bkash.savings.models.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
@Slf4j
public class DataSourceAspect {
    @Around("@annotation(com.bkash.savings.models.config.ReadOnly) || @within(com.bkash.savings.models.config" +
            ".ReadOnly)")
    public Object switchDataSource(ProceedingJoinPoint point) throws Throwable {
        DatabaseContextHolder.setReplicaType(DataSourceType.SLAVE);
        try {
            return point.proceed();
        } finally {
            DatabaseContextHolder.clear();
        }
    }
}
