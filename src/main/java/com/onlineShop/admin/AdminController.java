package com.onlineShop.admin;

import com.onlineShop.category.Category;
import com.onlineShop.category.CategoryService;
import com.onlineShop.product.FeaturedProductsService;
import com.onlineShop.product.GenderProduct;
import com.onlineShop.product.Product;
import com.onlineShop.product.ProductService;
import com.onlineShop.user.User;
import com.onlineShop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private FeaturedProductsService featuredProductsService;



    @GetMapping("/admin/addProduct")
    public String adminPage() {
        return "/admin/addProduct.html";
    }

    @PostMapping("/admin/addProduct")
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
        return "/admin/addProduct.html";
    }

    @GetMapping("/admin/addCat")
    public String addCatPage() {
        return "/admin/addCat.html";
    }


    @PostMapping("/admin/addCat")
    public String addCategory(@RequestParam String name,
                              @RequestParam String gender,
                              Model model
    ) {
        System.out.println(name);
        Category category = new Category();
        category.setName(name);
        category.setGender(gender);

        int categoryId = categoryService.addCategory(category);
        System.out.println(categoryId);
        model.addAttribute("categoryId", categoryId);
        return "/admin/addCat.html";
    }

    @GetMapping("/admin/addUser")
    public String addUserPage() {
        return "/admin/addUser.html";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam int phone,
                             @RequestParam String userName,
                             Model model
    ) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        password=encoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setName(userName);

        int userId = userService.addUser(user);
        model.addAttribute("userId", userId);
        return "/admin/addUser.html";
    }


    @GetMapping("/admin/addFP")
    public String addFPPage(Model model){
        List<GenderProduct> allGenderProductsList=featuredProductsService.getAllGenderProducts();
        List<Integer> featuredProductsList=featuredProductsService.getFeaturedProducts();
        model.addAttribute("allGenderProductsList", allGenderProductsList);
        model.addAttribute("featuredProductsList", featuredProductsList);

        return "/admin/addFP.html";
    }




}
