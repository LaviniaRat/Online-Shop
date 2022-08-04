package com.onlineShop;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class OnlineShopService {
    Connection connection=null;

    public OnlineShopService(){
        connectTodb("onlineShop", "postgres", "java");

    }

    private Connection connectTodb(String dbname, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": " +e.getMessage());
        }
    return connection;
    }

    public List<String> getCategories(){
        List<String> categoryList = new ArrayList<>();
        try {
            Statement stm = connection.createStatement();
            ResultSet res = stm.executeQuery("select name from category");
            while(res.next()){
                String category = res.getString("name");
                categoryList.add(category);
            }
            res.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error" + e);
            e.printStackTrace();
        }
        System.out.println(categoryList);
        return categoryList;
    }
}
