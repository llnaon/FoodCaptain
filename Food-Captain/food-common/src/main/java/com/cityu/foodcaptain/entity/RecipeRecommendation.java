package com.cityu.foodcaptain.entity;

public class RecipeRecommendation {

    private Recipe recipe;
    private int score;

    public RecipeRecommendation(Recipe recipe, int score) {
        this.recipe = recipe;
        this.score = score;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
