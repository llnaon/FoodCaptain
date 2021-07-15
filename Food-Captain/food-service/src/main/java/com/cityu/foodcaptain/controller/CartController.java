package com.cityu.foodcaptain.controller;

import com.cityu.foodcaptain.entity.CartElement;
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

    @GetMapping("/addCart")
    public void addCart(int id){
        cartService.addCart(id);
    }

    @GetMapping("/pay")
    public void pay(){
        cartService.pay();
    }
}
