package com.cityu.foodcaptain.entity;

public class Fresh {
    private int id;
    private String name;
    private String img;
    private double price;
    private String description;
    private String category;
    private String type;
    private int stock;
    private int sell;

    public static Fresh makeFresh(String[] freshData) {
        if(freshData.length != 9) {
            return null;
        }
        Fresh fresh = new Fresh();
        fresh.id = Integer.parseInt(freshData[0]);
        fresh.name = freshData[1];
        fresh.img = freshData[2];
        fresh.price = Double.parseDouble(freshData[3]);
        fresh.description = freshData[4];
        fresh.category = freshData[5];
        fresh.type = freshData[6];
        fresh.stock = Integer.parseInt(freshData[7]);
        fresh.sell = Integer.parseInt(freshData[8]);
        return fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }


}
