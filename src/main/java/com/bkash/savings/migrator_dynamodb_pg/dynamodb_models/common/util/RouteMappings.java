package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util;

public class RouteMappings {

    // Login & Logout
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    //oauth2 provider Azure AD
    public static final String AZURE_AUTH_URL = "/azure-url";
    public static final String AZURE_CALLBACK_URL = "/azure-callback";
    private RouteMappings() {
    }

}