package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Lop DAO cha: giu ket noi CSDL dung chung cho toan he thong (Hinh 15, Hinh 19).
 * Sua dbUrl, dbUser, dbPassword cho dung voi MySQL tren may minh
 * (hoac dat bien moi truong RESTMAN_DB_URL, RESTMAN_DB_USER, RESTMAN_DB_PASSWORD).
 */
public class DAO2 {
    protected static Connection con;

    private static final String DB_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/restman2"
            + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false"
            + "&allowPublicKeyRetrieval=true&autoReconnect=true";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "";

    public DAO2() {
        try {
            if (con == null || !con.isValid(2)) {
                String dbUrl = env("RESTMAN_DB_URL", DEFAULT_URL);
                String dbUser = env("RESTMAN_DB_USER", DEFAULT_USER);
                String dbPassword = env("RESTMAN_DB_PASSWORD", DEFAULT_PASSWORD);
                Class.forName(DB_CLASS);
                con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String env(String name, String defaultValue) {
        String value = System.getenv(name);
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }
}
