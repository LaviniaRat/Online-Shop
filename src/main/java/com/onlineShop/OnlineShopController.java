package com.onlineShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/admin")
    public String adminPage(){
        return "admin.html";
    }

    @PostMapping("/admin")
    public String addProduct(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam int price,
                             @RequestParam String gender,
                             @RequestParam String currency,
                             Model model
                            ) {
        System.out.println(title);
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCurrency(currency);
        product.setGender(gender);

        int productId = onlineShopService.addProduct(product);
        System.out.println(productId);
        model.addAttribute("productId", productId);

        return "admin.html";
    }

}
