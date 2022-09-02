package com.onlineShop.product;

import com.onlineShop.DBconnectionService;
import com.onlineShop.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.processor.SpringMethodTagProcessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class FeaturedProductService {
    @Autowired
    private DBconnectionService dBconnectionService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    public List<Product> displayFeaturedProducts() {
        List<Product> featuredProductsList = new ArrayList<>();
        try {
            Statement stm = dBconnectionService.getConnection().createStatement();
            String queryFeaturedProduct = "select p.id, p.title, p.description, p.category_id, p.price, p.currency " +
                    "from featured_products fp " +
                    "join products p on fp.product_id = p.id ";
            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(queryFeaturedProduct);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                Product product = new Product();
                int id = res.getInt("id");
                String title = res.getString("title");
                String description = res.getString("description");
                int categoryId = res.getInt("category_id");
                int price = res.getInt("price");
                String currency = res.getString("currency");
                product.setId(id);
                product.setTitle(title);
                product.setDescription(description);
                product.setCategoryId(categoryId);
                product.setPrice(price);
                product.setCurrency(currency);
                product.setImages(productService.getImages(id));
                featuredProductsList.add(product);
            }
            res.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error" + e);
            e.printStackTrace();
        }
        for (Product item : featuredProductsList) {
            System.out.println(item);
        }
        System.out.println(Arrays.toString(featuredProductsList.toArray()));
        return featuredProductsList;

    }


    public List<GenderProduct> getAllGenderProducts() {
        List<GenderProduct> allGenderProductsList = new ArrayList<>();
        try {
            Statement stm = dBconnectionService.getConnection().createStatement();
            String queryAllProductsList = "select p.id, p.title, p.price, p.currency, c.gender " +
                    "from products p " +
                    "join categories c on p.category_id = c.id ";
            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(queryAllProductsList);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                GenderProduct genderProduct = new GenderProduct();
                int id = res.getInt("id");
                String title = res.getString("title");
                int price = res.getInt("price");
                String currency = res.getString("currency");
                String gender = res.getString("gender");
                genderProduct.setId(id);
                genderProduct.setTitle(title);
                genderProduct.setPrice(price);
                genderProduct.setCurrency(currency);
                genderProduct.setGender(gender);
                genderProduct.setImages(productService.getImages(id));
                allGenderProductsList.add(genderProduct);
            }
            res.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error" + e);
            e.printStackTrace();
        }
        return allGenderProductsList;
    }

    public List<Integer> getFeaturedProducts() {
        List<Integer> featuredProductsList = new ArrayList<>();
        try {
            Statement stm = dBconnectionService.getConnection().createStatement();
            String featuredProductsQuery = "select product_id as id " +
                    "from featured_products ";
            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(featuredProductsQuery);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                featuredProductsList.add(id);

            }
            res.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error" + e);
            e.printStackTrace();
        }
        return featuredProductsList;
    }

    public void updateFeaturedProduct(List<Integer> newFeaturedProductList) {
        Connection conn = dBconnectionService.getConnection();
        try {
            String deleteFeaturedProduct = "delete from featured_products ";
            String insertNewFeaturedProduct = "insert into featured_products (id, product_id) " +
                    "values(nextVal('id'),?)";
            conn.setAutoCommit(false);
            PreparedStatement deleteStatement = dBconnectionService.getConnection().prepareStatement(deleteFeaturedProduct);
            PreparedStatement insertStatement = dBconnectionService.getConnection().prepareStatement(insertNewFeaturedProduct);
            deleteStatement.executeUpdate();
            for (Integer integer : newFeaturedProductList) {
                insertStatement.setInt(1, integer);
                insertStatement.executeUpdate();
            }
            conn.commit();

        } catch (SQLException e) {
            System.out.println("Error" + e);
            e.printStackTrace();
            try {
                System.out.println("Transaction is rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                System.out.println("Error" + excep);
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

