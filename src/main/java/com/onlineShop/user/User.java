package com.onlineShop.user;

public class User {

    private int userId;
    private String name;
    private String email;
    private String password;
    private int addressId;
    private int phone;

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getPhone() {
        return phone;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
