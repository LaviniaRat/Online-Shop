package com.onlineShop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class OnlineShopController {
    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("product");
        return "home.html";
    }
}
