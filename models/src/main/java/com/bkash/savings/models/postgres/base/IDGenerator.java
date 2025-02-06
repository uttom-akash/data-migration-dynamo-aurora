package com.bkash.savings.models.postgres.base;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(UUIDGenerator.class)
@Retention(RUNTIME) @Target({METHOD,FIELD})
public @interface IDGenerator {
    String name() default "uuid";
}
