package com.cityu.foodcaptain.controller;

import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.entity.Recipe;
import com.cityu.foodcaptain.service.FreshService;
import com.cityu.foodcaptain.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendController {
    @Autowired
    FreshService freshService;
    @Autowired
    RecipeService recipeService;

    @GetMapping("/recommendFresh")
    public List<Fresh> recommendFresh(int id){
        return freshService.recommendFresh(id);
    }

    @GetMapping("/recommendRecipe")
    public List<Recipe> recommendRecipe(int id){
        return recipeService.recommendRecipe(id);
    }
}
