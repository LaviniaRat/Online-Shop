package com.onlineShop;

import com.onlineShop.category.Category;
import com.onlineShop.category.CategoryService;
import com.onlineShop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OnlineShopController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FeaturedProductsService featuredProductsService;

    @GetMapping("/home")
    public String homePage(Model model){
        List<Category> womenCategoryList = categoryService.getCategories("FEMALE");
        List<Category> menCategoryList = categoryService.getCategories("MALE");
        model.addAttribute("womenCategoryList", womenCategoryList);
        model.addAttribute("menCategoryList", menCategoryList);
        List<Product> featuredProductsList=featuredProductsService.getFeaturedProducts();
        model.addAttribute("featuredProductsList", featuredProductsList);
        return "home.html";
    }


    @GetMapping("/about")
    public String aboutPage(Model model){
        List<Category> womenCategoryList = categoryService.getCategories("FEMALE");
        List<Category> menCategoryList = categoryService.getCategories("MALE");
        model.addAttribute("womenCategoryList", womenCategoryList);
        model.addAttribute("menCategoryList", menCategoryList);
        return "about.html";
    }

}
