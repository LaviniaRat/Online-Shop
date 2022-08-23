package com.onlineShop.auth;

import com.onlineShop.DBconnectionService;
import com.onlineShop.auth.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@SessionScope
public class UserService {

    @Autowired
    private DBconnectionService dBconnectionService;


    public AppUser getUser(String username) {
        AppUser appUser = null;
        try {
            String myQuery = "select id, user_name, password from users " +
                    "where user_name = ? ";

            PreparedStatement pstm = dBconnectionService.getConnection().prepareStatement(myQuery);
            pstm.setString(1, username);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                int userId = res.getInt("id");
                String userName = res.getString("user_name");
                String password = res.getString("password");
                appUser = new AppUser((long) userId, userName, password);
            }

            res.close();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
            e.printStackTrace();
        }

        return appUser;
    }
}
