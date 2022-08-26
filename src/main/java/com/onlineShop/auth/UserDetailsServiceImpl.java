package com.onlineShop.auth;

import com.onlineShop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userService.getUser(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        return User.builder()
                .username(appUser.getUserName())
                .password(appUser.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
