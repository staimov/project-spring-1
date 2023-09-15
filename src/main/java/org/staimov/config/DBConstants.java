package org.staimov.config;

public class DBConstants {
    public static final String DB_USER = "root";
    public static final String DB_PASS = "root";
    public static final String DB_DRIVER = "com.p6spy.engine.spy.P6SpyDriver";
    public static final String DB_URL = "jdbc:p6spy:mysql://localhost:63306/todo";
    public static final int MAX_POOL_SIZE = 10;
    public static final String DB_DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    public static final String DB_HBM2DDL_AUTO = "validate";
}
