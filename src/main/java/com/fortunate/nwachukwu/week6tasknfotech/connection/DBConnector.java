package com.fortunate.nwachukwu.week6tasknfotech.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/myStores?useSSL=false";
                String uname = "root";
                String pass = "12345678";
                connection = DriverManager.getConnection(url, uname, pass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
