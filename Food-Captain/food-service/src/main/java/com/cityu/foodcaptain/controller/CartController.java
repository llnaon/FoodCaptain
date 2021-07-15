package com.cityu.foodcaptain.controller;

import com.cityu.foodcaptain.entity.CartElement;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/showCart")
    public List<CartElement> showCart(){
        return cartService.showCart();
    }
}
