package com.cityu.foodcaptain.service;

import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.CartElement;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    FreshService freshService;

    public void addCart(int id) {

    }

    public List<CartElement> showCart() {
        List<CartElement> res = new ArrayList<>();
        List<String> data = FileUtils.readFileByLines(Constants.CART);
        for (String record : data) {
            String[] cartItem = record.split(Constants.SPLIT);
            Fresh fresh = freshService.getDetail(Integer.parseInt(cartItem[0]));
            int quantity = Integer.parseInt(cartItem[1]);
            CartElement cartElement = new CartElement();
            cartElement.setFresh(fresh);
            cartElement.setQuantity(quantity);
            res.add(cartElement);
        }
        return res;
    }
}
