package com.onlineShop.product;

import com.onlineShop.DBconnectionService;
import com.onlineShop.category.CategoryService;
import com.onlineShop.product.Product;
import com.onlineShop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class FeaturedProductsService {
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
                    "from featuredProducts fp " +
                    "join product p on fp.product_id = p.id ";
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


        public List<GenderProduct> getAllGenderProducts(){
            List<GenderProduct> allGenderProductsList = new ArrayList<>();
            try {
                Statement stm = dBconnectionService.getConnection().createStatement();
                String queryAllProductsList = "select p.id, p.title, p.price, p.currency, c.gender " +
                        "from product p " +
                        "join category c on p.category_id = c.id ";
                PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(queryAllProductsList);
                ResultSet res = pstm.executeQuery();
                while (res.next()) {
                    GenderProduct genderProduct = new GenderProduct();
                    int id = res.getInt("id");
                    String title = res.getString("title");
                    int price = res.getInt("price");
                    String currency = res.getString("currency");
                    String gender= res.getString("gender");
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
            return  allGenderProductsList;

        }

        public List<Integer> getFeaturedProducts(){
        List<Integer> featuredProductsList = new ArrayList<>();
            try {
                Statement stm = dBconnectionService.getConnection().createStatement();
                String featuredProductsQuery = "select product_id " +
                        "from featuredproducts ";
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
    }

