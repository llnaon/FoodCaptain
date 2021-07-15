package com.cityu.foodcaptain.controller;

import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.service.FreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendController {
    @Autowired
    FreshService freshService;

    @GetMapping("/recommendFresh")
    public List<Fresh> recommendFresh(int id){
        return freshService.recommendFresh(id);
    }
}
