package com.onlineShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OnlineShopController {
    @Autowired
    private OnlineShopService OnlineShopService;

    @GetMapping("/home")
    public String homePage(Model model){
        List<String> categoryList = OnlineShopService.getCategories();
        model.addAttribute("categoryList", categoryList);
        return "home.html";
    }
}
