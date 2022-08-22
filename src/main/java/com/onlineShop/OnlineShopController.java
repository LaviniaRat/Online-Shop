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
    private ProductService productService;

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

    @GetMapping("/product")
    public String productPage(Model model, @RequestParam int productId){

        List<Category> womenCategoryList = categoryService.getCategories("FEMALE");
        List<Category> menCategoryList = categoryService.getCategories("MALE");
        model.addAttribute("womenCategoryList", womenCategoryList);
        model.addAttribute("menCategoryList", menCategoryList);
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "product.html";
    }

    @GetMapping("/category")
    public String categoryPage(Model model, @RequestParam int categoryId){
        List<Category> womenCategoryList = categoryService.getCategories("FEMALE");
        List<Category> menCategoryList = categoryService.getCategories("MALE");
        model.addAttribute("womenCategoryList", womenCategoryList);
        model.addAttribute("menCategoryList", menCategoryList);

        Category category = categoryService.getCategory(categoryId);
        List<Category> sidebarCategories;
        if (category.getGender().equals("FEMALE")) {
            sidebarCategories = womenCategoryList;
        } else {
            sidebarCategories = menCategoryList;
        }
        model.addAttribute("sidebarCategories", sidebarCategories);
        List<Product> productsList= productService.getProducts(categoryId);
        model.addAttribute("productsList", productsList);
        return "category.html";
    }


    @GetMapping("/search")
    public String searchProduct(@RequestParam String word, Model model){
        List<Product> searchProductList= productService.searchProduct(word);
        model.addAttribute("searchProductList", searchProductList);
        return"search.html";
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
