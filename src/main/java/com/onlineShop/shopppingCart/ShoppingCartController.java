package com.onlineShop.shopppingCart;

import com.onlineShop.category.Category;
import com.onlineShop.category.CategoryService;
import com.onlineShop.product.Product;
import com.onlineShop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @PostMapping("/addProductToShoppingCart")
    public ModelAndView addProductToShoppingCart(HttpSession session, @RequestParam int productId) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("ShoppingCart", cart);
        }
        cart.addtoShoppingCart(productId);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/shoppingCart")
    public String getShoppingCart(HttpSession session, Model model) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
        List<Product> shoppingCartProductsList = new ArrayList<>();
        for (Integer id : cart.getShoppingCartList()) {
            shoppingCartProductsList.add(productService.getProduct(id));
        }
        int totalPrice = 0;
        for (Product product : shoppingCartProductsList) {
            totalPrice += product.getPrice();
        }
        model.addAttribute("shoppingCartProductsList", shoppingCartProductsList);
        model.addAttribute("totalPrice", totalPrice);
        List<Category> womenCategoryList = categoryService.getCategories("FEMALE");
        List<Category> menCategoryList = categoryService.getCategories("MALE");
        model.addAttribute("womenCategoryList", womenCategoryList);
        model.addAttribute("menCategoryList", menCategoryList);

        return "shoppingCart.html";
    }

    @PostMapping("/orders")
    public String orderPage(Model model,
                            @RequestParam String userName,
                            @RequestParam String email,
                            @RequestParam String address,
                            @RequestParam String city,
                            @RequestParam String country,
                            @RequestParam int zipCode,
                            @RequestParam List<Integer> productIds,
                            @RequestParam List<Integer> quantities
    ) {
        Customer customer = new Customer();
        customer.setUserName(userName);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setCity(city);
        customer.setCountry(country);
        customer.setZipCode(zipCode);

        List<CartItem> cartItems = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++) {
            CartItem item = new CartItem();
            item.setProductId(productIds.get(i));
            item.setQuantity(quantities.get(i));
            item.setCartId(item.getCartId());
            cartItems.add(item);
        }
        shoppingCartService.placeOrder(customer, cartItems);
        return "order.html";
    }
}
