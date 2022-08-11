package com.onlineShop;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class OnlineShopService {
    Connection connection = null;

    public OnlineShopService() {
        connectTodb("onlineShop", "postgres", "java");

    }

    private Connection connectTodb(String dbname, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return connection;
    }

    public List<String> getCategories() {
        List<String> categoryList = new ArrayList<>();
        try {
            Statement stm = connection.createStatement();
            ResultSet res = stm.executeQuery("select name from category");
            while (res.next()) {
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


    public Product getProduct(int id) {
       Product product = new Product();
        try {
            Statement stm = connection.createStatement();
            String myQuery = "select id, title, description, price\n" +
                    "from product\n" +
                    "where id = ?";

            PreparedStatement pstm = connection.prepareStatement(myQuery);
            pstm.setInt(1,id);
            ResultSet res = pstm.executeQuery();
            res.next();
            int productId = res.getInt("id");
            String title = res.getString("title");
            String description = res.getString("description");
            String price = res.getString("price");
            product.setId(productId);
            product.setTitle(title);
            product.setDescription(description);
            product.setPrice(price);
            product.setImages(getImages(productId));

            res.close();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
            e.printStackTrace();
        }
        return product;
    }

    private List<String> getImages(int productId) {
            List<String> imagesList = new ArrayList<>();
            try {
                Statement stm = connection.createStatement();
                String queryImage = "select name from images\n" +
                        "where product_id=?";
                PreparedStatement pstm = connection.prepareStatement(queryImage);
                pstm.setInt(1,productId);
                ResultSet res = pstm.executeQuery();
                while (res.next()) {
                    String image = res.getString("name");
                    imagesList.add(image);
                }
                res.close();
                stm.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
                e.printStackTrace();
            }
            return imagesList;
        }

        public List<Product> getCategoryOfProducts(String gender, int categoryId){
        List<Product> productsList = new ArrayList<>();
            try {
                Statement stm = connection.createStatement();
                String queryProduct ="select title, price from product\n" +
                        "where gender=? AND category_id=?";
                PreparedStatement pstm = connection.prepareStatement(queryProduct);
                pstm.setString(1, gender);
                pstm.setInt(2, categoryId);
                ResultSet res = pstm.executeQuery();
                while (res.next()) {
                    String title = res.getString("title");
                    String price= res.getString("price");
                    Product product = new Product();
                    product.setTitle(title);
                    product.setPrice(price);
                    productsList.add(product);
                }
                res.close();
                stm.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
                e.printStackTrace();
            }
            return productsList;


        }
}


