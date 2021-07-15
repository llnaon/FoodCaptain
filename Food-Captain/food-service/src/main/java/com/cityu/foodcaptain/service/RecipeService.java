package com.cityu.foodcaptain.service;

import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.entity.Recipe;
import com.cityu.foodcaptain.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    public List<Recipe> getRecipe() {
        String data = FileUtils.readAll(Constants.RECIPE);
        String[] rawRecipes = data.split(Constants.RECIPE_SPLIT);
        List<Recipe> res = new ArrayList<>();
        for (String rawRecipe : rawRecipes) {
            Recipe recipe = Recipe.makeRecipe(rawRecipe);
            res.add(recipe);
        }
        return res;
    }

    public List<Recipe> searchRecipe(String str) {
        return null;
    }

    public List<Fresh> getRecipeRelatedFresh(int recipeId) {
        return null;
    }
}
