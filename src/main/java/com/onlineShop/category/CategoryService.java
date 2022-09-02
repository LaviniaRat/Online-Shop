package com.onlineShop.category;

import com.onlineShop.DBconnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private DBconnectionService dBconnectionService;

    public List<Category> getCategories(String gender) {
        List<Category> categoryList = new ArrayList<>();
        try {
            Statement stm = dBconnectionService.getConnection().createStatement();
            String categoryQuery = "select id, name from categories  " +
                    "where gender=? ";
            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(categoryQuery);
            pstm.setString(1, gender);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                Category category = new Category();
                category.setId(res.getInt("id"));
                category.setName(res.getString("name"));
                categoryList.add(category);
            }
            res.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error" + e);
            e.printStackTrace();
        }
        return categoryList;
    }

    public Category getCategory(int categoryId) {
        Category category = new Category();
        try {
            Statement stm = dBconnectionService.getConnection().createStatement();
            String myQuery = "select id, name, gender from categories " +
                    "where id = ?";
            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(myQuery);
            pstm.setInt(1, categoryId);
            ResultSet res = pstm.executeQuery();
            res.next();
            category.setName(res.getString("name"));
            category.setGender(res.getString("gender"));
            category.setId(res.getInt("id"));

            res.close();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
            e.printStackTrace();
        }
        return category;
    }

    public int addCategory(Category category) {
        int categoryId = 0;
        try {
            String myQuery = "insert into categories(id,name, gender) " +
                    "values(nextVal('category_id_seq'),?,?) RETURNING ID";

            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(myQuery);
            pstm.setString(1, category.getName());
            pstm.setString(2, category.getGender());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt(1);

            }

            pstm.close();
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
            e.printStackTrace();
        }

        return categoryId;
    }

}
