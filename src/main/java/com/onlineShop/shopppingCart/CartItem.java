package com.onlineShop.shopppingCart;

public class CartItem {
    private int cartId;
    private int orderId;
    private int productId;
    private int quantity;

    public int getCartId() {
        return cartId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
