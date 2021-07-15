package com.cityu.foodcaptain.entity;

public class CartElement {
    private Fresh fresh;
    private int quantity;

    public Fresh getFresh() {
        return fresh;
    }

    public void setFresh(Fresh fresh) {
        this.fresh = fresh;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
