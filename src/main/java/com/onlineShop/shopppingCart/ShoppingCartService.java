package com.onlineShop.shopppingCart;

import com.onlineShop.DBconnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    DBconnectionService dBconnectionService;

    public void placeOrder(Customer customer, List<CartItem> cartItems) {
        try {
            dBconnectionService.getConnection().setAutoCommit(false);
            int customerId = this.addCustomer(customer);
            int orderId = this.addOrder(customerId);

            for (CartItem item : cartItems) {
                item.setOrderId(orderId);
                this.addCartItem(item);
            }
            dBconnectionService.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("Transaction is rolled back");
                dBconnectionService.getConnection().rollback();
            } catch (SQLException excep) {
                System.out.println("Error" + excep);
                e.printStackTrace();
            }
        } finally {
            try {
                dBconnectionService.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Your order was placed");
    }

    private void addCartItem(CartItem item) throws SQLException {

        String queryAddItem = " insert into cart_items(cart_id, product_id, quantity, order_id) " +
                "values(nextVal('cart_id_seq'), ?,?,?)";

        PreparedStatement addCart = dBconnectionService.getConnection().prepareStatement(queryAddItem);
        addCart.setInt(1, item.getProductId());
        addCart.setInt(2, item.getQuantity());
        addCart.setInt(3, item.getOrderId());
        addCart.executeUpdate();
    }

    private int addOrder(int customerId) throws SQLException {
        String queryAddOrder = " insert into orders(order_id, customer_id) " +
                "values(nextVal('order_id_seq'), ?) returning order_id";

        PreparedStatement addOrder = dBconnectionService.getConnection().prepareStatement(queryAddOrder);

        addOrder.setInt(1, customerId);
        ResultSet resOrder = addOrder.executeQuery();
        int orderId = 0;
        if (resOrder.next()) {
            orderId = resOrder.getInt(1);
        }

        return orderId;
    }

    private int addCustomer(Customer customer) throws SQLException {
        String queryAddCustomer = "insert into customers (customer_id, name, email, address, city, country, zip) " +
                "values(nextVal('customer_id_seq'), ?, ?, ?, ?, ?, ?) returning customer_id";

        PreparedStatement addCustomer = dBconnectionService.getConnection().prepareStatement(queryAddCustomer);

        addCustomer.setString(1, customer.getUserName());
        addCustomer.setString(2, customer.getEmail());
        addCustomer.setString(3, customer.getAddress());
        addCustomer.setString(4, customer.getCity());
        addCustomer.setString(5, customer.getCountry());
        addCustomer.setInt(6, customer.getZipCode());
        ResultSet resCustomer = addCustomer.executeQuery();
        int customerId = 0;
        if (resCustomer.next()) {
            customerId = resCustomer.getInt(1);
        }
        return customerId;
    }
}
