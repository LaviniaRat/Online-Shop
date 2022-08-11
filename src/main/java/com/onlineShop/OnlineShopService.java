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

    private void connectTodb(String dbname, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
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
            String myQuery = "select id, title, description, price, moneda\n" +
                    "from product\n" +
                    "where id = ?";

            PreparedStatement pstm = connection.prepareStatement(myQuery);
            pstm.setInt(1,id);
            ResultSet res = pstm.executeQuery();
            res.next();
            int productId = res.getInt("id");
            String title = res.getString("title");
            String description = res.getString("description");
            int price = res.getInt("price");
            String moneda = res.getString("price");
            product.setId(productId);
            product.setTitle(title);
            product.setCurrency(moneda);
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
                String queryProduct ="select title, price, moneda from product\n" +
                        "where gender=? AND category_id=?";
                PreparedStatement pstm = connection.prepareStatement(queryProduct);
                pstm.setString(1, gender);
                pstm.setInt(2, categoryId);
                ResultSet res = pstm.executeQuery();
                while (res.next()) {
                    String title = res.getString("title");
                    int price= res.getInt("price");
                    String moneda = res.getString("moneda");
                    Product product = new Product();
                    product.setTitle(title);
                    product.setPrice(price);
                    product.setCurrency(moneda);
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

    public int addProduct(Product product) {
        int productId = 0;
        try {
            String myQuery = "insert into product(id,title, description, price, currency, gender, category_id)" +
                    "values(nextVal('product_id_seq'),?,?,?,?,?,null) RETURNING ID";

            PreparedStatement pstm = connection.prepareStatement(myQuery);
            pstm.setString(1,product.getTitle());
            pstm.setString(2,product.getDescription());
            pstm.setInt(3, product.getPrice());
            pstm.setString(4,product.getCurrency());
            pstm.setString(5,product.getGender());


            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                productId = rs.getInt(1);
            }

            pstm.close();
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
            e.printStackTrace();
        }

        return productId;
    }
}


