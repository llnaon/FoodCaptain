package com.cityu.foodcaptain.controller;

import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.entity.Recipe;
import com.cityu.foodcaptain.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/recipe")
    public List<Recipe> getRecipe() {
        return RecipeService.RECIPES;
    }

    @GetMapping("/getRecipeDetail")
    public Recipe getRecipeDetail(int id) {
        return recipeService.getRecipeDetail(id);
    }

    @GetMapping("/getRecipeRelatedFresh")
    public List<Fresh> getRecipeRelatedFresh(int recipeId) {
        return recipeService.getRecipeRelatedFresh(recipeId);
    }

    @GetMapping("/searchRecipe")
    public List<Recipe> searchRecipe(String str) {
        return recipeService.searchRecipe(str);
    }


}
