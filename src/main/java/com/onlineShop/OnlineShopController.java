package com.onlineShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OnlineShopController {
    @Autowired
    private OnlineShopService onlineShopService;

    @GetMapping("/home")
    public String homePage(Model model){
        List<String> categoryList = onlineShopService.getCategories();
        model.addAttribute("categoryList", categoryList);
        return "home.html";
    }

    @GetMapping("/product")
    public String productPage(Model model, @RequestParam int productId){
        System.out.println(productId);
        List<String> categoryList = onlineShopService.getCategories();
        model.addAttribute("categoryList", categoryList);
        Product product = onlineShopService.getProduct(productId);
        model.addAttribute("product", product);
        return "product.html";
    }

    @GetMapping("/category")
    public String categoryPage(Model model, @RequestParam String gender, @RequestParam int categoryId){
        List<String> categoryList = onlineShopService.getCategories();
        model.addAttribute("categoryList", categoryList);
        System.out.println(gender);
        //System.out.println(categoryId);
        List<Product> productsList=onlineShopService.getCategoryOfProducts(gender,categoryId);
        model.addAttribute("products", productsList);
        return "category.html";


    }

}
