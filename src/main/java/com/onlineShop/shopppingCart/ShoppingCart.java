package com.onlineShop.shopppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class ShoppingCart {
    List<Integer> shoppingCartList = new ArrayList<>();

    public List<Integer> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<Integer> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    public List<Integer> addtoShoppingCart(Integer item) {
        shoppingCartList.add(item);
        return shoppingCartList;
    }


}


