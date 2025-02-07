package org.migration.transformers;

public class DefaultFallback {
    private static final String DEFAULT_STRING = "DEFAULT_STRING";

    public static String getOrDefault(String value) {

        return value == null ? DEFAULT_STRING : value;
    }
}
