package com.onlineShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScan
@SpringBootApplication
public class OnlineShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApplication.class,args);}
    }


