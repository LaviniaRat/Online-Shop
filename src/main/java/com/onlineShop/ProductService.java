package com.onlineShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private DBconnectionService dBconnectionService;

    public Product getProduct(int id) {
       Product product = new Product();
        try {
            Statement stm = dBconnectionService.getConnection().createStatement();
            String myQuery = "select id, title, description, price, currency " +
                    "from product " +
                    "where id = ?";

            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(myQuery);
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                int productId = res.getInt("id");
                String title = res.getString("title");
                String description = res.getString("description");
                int price = res.getInt("price");
                String currency = res.getString("currency");
                product.setId(productId);
                product.setTitle(title);
                product.setCurrency(currency);
                product.setDescription(description);
                product.setPrice(price);
                product.setImages(getImages(productId));
            }
            res.close();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
            e.printStackTrace();
        }
        return product;
    }

     List<String> getImages(int productId) {
        List<String> imagesList = new ArrayList<>();
        try {
            Statement stm = dBconnectionService.getConnection().createStatement();
            String queryImage = "select name from images " +
                    "where product_id = ?";
            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(queryImage);
            pstm.setInt(1, productId);
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

    public List<Product> getProducts(int categoryId){
        List<Product> productsList = new ArrayList<>();
        try {
            Statement stm = dBconnectionService.getConnection().createStatement();
            String queryProduct ="select id, title, price, currency from product\n" +
                    "where category_id=?";
            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(queryProduct);
            pstm.setInt(1, categoryId);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                int id=res.getInt("id");
                String title = res.getString("title");
                int price= res.getInt("price");
                String currency = res.getString("currency");
                Product product = new Product();
                product.setId(id);
                product.setTitle(title);
                product.setPrice(price);
                product.setCurrency(currency);
                product.setImages(getImages(id));
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
            String myQuery = "insert into product(id,title, description, price, currency, category_id)" +
                    "values(nextVal('product_id_seq'),?,?,?,?,?,null) RETURNING ID";

            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(myQuery);
            pstm.setString(1,product.getTitle());
            pstm.setString(2,product.getDescription());
            pstm.setInt(3, product.getPrice());
            pstm.setString(4,product.getCurrency());


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


