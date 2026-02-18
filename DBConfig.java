package com.promanage.dao;

import java.io.IOException;
import java.util.Properties;

public class DBConfig {
    private static final Properties props = new Properties();

    static {
        try {
            props.load(DBConfig.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Failed to load application.properties from resources", e);
        }
    }

    public static String url() { return props.getProperty("db.url"); }
    public static String user() { return props.getProperty("db.user"); }
    public static String password() { return props.getProperty("db.password"); }
}
