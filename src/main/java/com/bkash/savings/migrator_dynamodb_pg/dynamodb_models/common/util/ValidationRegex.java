package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util;

public class ValidationRegex {

    //    public static final String IMAGE_URL_REGEX = "(^$|\\s*(https?:\\/\\/)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*).(gif|jpe?g|tiff?|png|webp|bmp)\\s*$)";
    public static final String DOMAIN_REGEX = "(^$|\\s*(https?:\\/\\/)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)\\s*$)";
}
