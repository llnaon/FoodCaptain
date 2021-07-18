package com.cityu.foodcaptain.service;

import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.entity.Recipe;
import com.cityu.foodcaptain.entity.RecipeContainer;
import com.cityu.foodcaptain.entity.RecipeRecommendation;
import com.cityu.foodcaptain.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    public static final List<Recipe> RECIPES = getRecipe();

    public static List<Recipe> getRecipe() {
        List<Recipe> res = new ArrayList<>();
//        String data = FileUtils.readAll(Constants.RECIPE);
        String data = Constants.RECIPE_DATA;
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
        for (RecipeContainer recipeContainer : list) {
            if (recipeContainer.getFood() == null) continue;
            for (Fresh fresh : freshList) {
                if (recipeContainer.getFood().equals(fresh.getType())) {
                    res.add(fresh);
                    break;
                }
            }
        }
        return res;
    }

    public List<Recipe> recommendRecipe(int recipeId) {
        Recipe cur = getRecipeDetail(recipeId);
        List<Recipe> list = RECIPES;
        Collections.shuffle(list);
        List<RecipeRecommendation> rrList = getSimiList(cur, list);
        rrList.sort((rr1, rr2) -> rr2.getScore() - rr1.getScore());
        List<Recipe> res = rrList.stream().map(RecipeRecommendation::getRecipe).collect(Collectors.toList());
        res.remove(cur);
        return res;
    }

    private List<RecipeRecommendation> getSimiList(Recipe cur, List<Recipe> list) {
        return list.stream().map(recipe -> calSimilarity(cur, recipe)).collect(Collectors.toList());
    }

    public RecipeRecommendation calSimilarity(Recipe cur, Recipe r2) {
        if (cur == null || r2 == null) {
            return null;
        }
        int res = 0;
        if (cur.getCuisineSystem().equals(r2.getCuisineSystem())) {
            res += 10;
        }
        int score = 10;
        List<RecipeContainer> l1 = cur.getRecipeContainers();
        List<RecipeContainer> l2 = r2.getRecipeContainers();
        Set<String> match = new HashSet<>(l1).stream()
                .map(RecipeContainer::getFood).collect(Collectors.toSet());
        for (RecipeContainer rc : l2) {
            if (rc != null && match.contains(rc.getFood())) {
                res += score;
            }
            score = score / 3 + 1;
        }
        return new RecipeRecommendation(r2, res);
    }
}
