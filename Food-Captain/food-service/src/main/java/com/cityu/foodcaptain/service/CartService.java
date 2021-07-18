package com.cityu.foodcaptain.service;

import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.CartElement;
import com.cityu.foodcaptain.entity.CartModel;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    @Autowired
    FreshService freshService;

    private final Map<Integer, Integer> cartModels = new LinkedHashMap<>();

    public void addCart(int freshId) {
        cartModels.put(freshId, cartModels.getOrDefault(freshId, 0) + 1);
    }

    public List<CartElement> showCart() {
        List<CartElement> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> cartModel : cartModels.entrySet()) {
            Fresh fresh = freshService.getDetail(cartModel.getKey());
            if (fresh.getName() == null) {
                continue;
            }
            int quantity = cartModel.getValue();
            CartElement cartElement = new CartElement();
            cartElement.setFresh(fresh);
            cartElement.setQuantity(quantity);
            res.add(cartElement);
        }
        return res;
    }

    public void pay() {
        cartModels.clear();
    }
}
