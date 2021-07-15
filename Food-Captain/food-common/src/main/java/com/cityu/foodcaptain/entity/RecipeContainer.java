package com.cityu.foodcaptain.entity;

import java.util.ArrayList;
import java.util.List;

public class RecipeContainer {
    String food;
    String scale;

    public static List<RecipeContainer> makeContainer(String rawData) {
        String[] data = rawData.split("\\|");
        List<RecipeContainer> res = new ArrayList<>();
        for (String str : data) {
            String[] strings = str.split(",");
            if(strings.length != 2) {
                continue;
            }
            RecipeContainer recipeContainer = new RecipeContainer();
            recipeContainer.setFood(strings[0]);
            recipeContainer.setScale(strings[1]);
            res.add(recipeContainer);
        }
        return res;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
