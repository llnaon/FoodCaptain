package com.cityu.foodcaptain.entity;

public class CartModel {
    private int freshId;
    private int num;

    public CartModel(int freshId, int num) {
        this.freshId = freshId;
        this.num = num;
    }

    public int getFreshId() {
        return freshId;
    }

    public void setFreshId(int freshId) {
        this.freshId = freshId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
