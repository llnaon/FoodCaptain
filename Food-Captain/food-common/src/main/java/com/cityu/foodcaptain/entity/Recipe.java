package com.cityu.foodcaptain.entity;

import com.cityu.foodcaptain.constants.Constants;

import java.util.List;

public class Recipe {
    private int recipeId;
    private String name;
    private String img;
    // 菜系
    private String cuisineSystem;
    // 收藏数
    private int collection;
    // 介绍
    private String description;
    // 做法
    private String makeMethod;
    // 用料
    private List<RecipeContainer> recipeContainers;

    public static Recipe makeRecipe(String record) {
        String[] data = record.split(Constants.SPLIT);
        if(data.length != 8) {
            return new Recipe();
        }
        Recipe recipe = new Recipe();
        recipe.setMakeMethod(data[0]);
        recipe.setRecipeId(Integer.parseInt(data[1]));
        recipe.setName(data[2]);
        recipe.setImg(data[3]);
        recipe.setCuisineSystem(data[4]);
        recipe.setCollection(Integer.parseInt(data[5]));
        recipe.setDescription(data[6]);
        recipe.setRecipeContainers(RecipeContainer.makeContainer(data[7]));
        return recipe;
    }


    public String getCuisineSystem() {
        return cuisineSystem;
    }

    public void setCuisineSystem(String cuisineSystem) {
        this.cuisineSystem = cuisineSystem;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
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

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMakeMethod() {
        return makeMethod;
    }

    public void setMakeMethod(String makeMethod) {
        this.makeMethod = makeMethod;
    }

    public List<RecipeContainer> getRecipeContainers() {
        return recipeContainers;
    }

    public void setRecipeContainers(List<RecipeContainer> recipeContainers) {
        this.recipeContainers = recipeContainers;
    }
}
