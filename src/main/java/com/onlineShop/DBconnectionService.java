package com.onlineShop;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class DBconnectionService {
    private Connection connection = null;

    public DBconnectionService(){
        connectTodb("onlineShop", "postgres", "java");
    }

    public Connection getConnection() {
        return connection;
    }

    private void connectTodb(String dbname, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
