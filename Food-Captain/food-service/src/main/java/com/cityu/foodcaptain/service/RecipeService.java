package com.cityu.foodcaptain.service;

import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.entity.Recipe;
import com.cityu.foodcaptain.entity.RecipeContainer;
import com.cityu.foodcaptain.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    public static final int NUM_MAX_REC = 10;
    public static final int NUM_SYS_REC = 3;
    public static final int SIM_THRESHOLD = 4;

    public static final List<Recipe> RECIPES = getRecipe();

    public static List<Recipe> getRecipe() {
        List<Recipe> res = new ArrayList<>();
        String data = FileUtils.readAll(Constants.RECIPE);
        if (data == null) {
            return res;
        }
        String[] rawRecipes = data.split(Constants.RECIPE_SPLIT);
        for (String rawRecipe : rawRecipes) {
            Recipe recipe = Recipe.makeRecipe(rawRecipe);
            res.add(recipe);
        }
        return res.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Recipe getRecipeDetail(int recipeId) {
        for (Recipe recipe : RECIPES) {
            if (recipe.getRecipeId() == recipeId) {
                return recipe;
            }
        }
        return new Recipe();
    }

    public List<Recipe> searchRecipe(String str) {
        List<Recipe> res = new ArrayList<>();
        for (Recipe recipe : RECIPES) {
            if (recipe.getCuisineSystem().contains(str)
                    || recipe.getName().contains(str)
                    || recipe.getDescription().contains(str)
                    || recipe.getMakeMethod().contains(str)) {
                res.add(recipe);
            }
        }
        return res;
    }

    public List<Fresh> getRecipeRelatedFresh(int recipeId) {
        List<Fresh> res = new ArrayList<>();
        List<Fresh> freshList = FreshService.FRESH;
        Collections.shuffle(freshList);
        Recipe recipe = getRecipeDetail(recipeId);
        List<RecipeContainer> list = recipe.getRecipeContainers();
        if (list == null) {
            return res;
        }
        Set<String> matchSet = new HashSet<>();
        for (RecipeContainer recipeContainer : list) {
            matchSet.add(recipeContainer.getFood());
        }
        for (Fresh fresh : freshList) {
            if (matchSet.isEmpty()) {
                break;
            }
            if (fresh == null) {
                continue;
            }
            if (matchSet.remove(fresh.getType())) {
                res.add(fresh);
            }
        }
        return res;
    }

    public List<Recipe> recommendRecipe(int recipeId) {
        Recipe cur = getRecipeDetail(recipeId);
        int num = 0;
        List<Recipe> res = new ArrayList<>(NUM_MAX_REC);
        List<Recipe> list = RECIPES;
        Collections.shuffle(list);
        if (cur.getName() == null) {
            for (int i = 0; i < NUM_MAX_REC && i < list.size(); i++) {
                res.add(list.get(i));
            }
            return res;
        }
        for (Recipe recipe : list) {
            if (recipe == cur) {
                continue;
            }
            if (cur.getCuisineSystem().equals(recipe.getCuisineSystem())
                    && num < NUM_SYS_REC && res.size() < NUM_MAX_REC) {
                res.add(recipe);
                num++;
                continue;
            }
            if (Recipe.calSimilarity(cur, recipe) >= SIM_THRESHOLD
                    && res.size() < NUM_MAX_REC) {
                res.add(recipe);
            }
        }
        return res;
    }
}
