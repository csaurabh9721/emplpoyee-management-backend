package com.devix.employemanagement.utils;

public class Constants {
    public static final String user = "appdevix";
    public static final String password = "123appdevix456";
    public static final String SECRET_KEY = user + "_super_secret_key_" + password + "_its_for_web_app_security";
    public static final long accessTokenValidity = 5 * 24 * 20 * 60 * 1000;
    public static final long refreshTokenValidity = 10 * 24 * 60 * 60 * 1000;
    public static final Double workingHour = 8.0;


}
//"appdevix_super_secret_key_123appdevix456_its_for_web_app_security"