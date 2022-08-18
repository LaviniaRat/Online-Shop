package com.onlineShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("/admin")
    public String adminPage(){
        return "admin.html";
    }

    @PostMapping("/admin")
    public String addProduct(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam int price,
                             @RequestParam String currency,
                             Model model
    ) {
        System.out.println(title);
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCurrency(currency);

        int productId = productService.addProduct(product);
        System.out.println(productId);
        model.addAttribute("productId", productId);

        return "admin.html";
    }

}
