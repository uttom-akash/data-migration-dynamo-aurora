package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product;

public enum SavingsSchemeTag {
    BEST_DEAL("Best Deal"),
    POPULAR("Popular");

    private String title;

    SavingsSchemeTag(String title) {
        this.title = title;
    }

    public String title() {
        return this.title;
    }
}
