package com.cityu.foodcaptain.service;

import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.CartElement;
import com.cityu.foodcaptain.entity.CartModel;
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

    public void addCart(int freshId) {
        List<String> data = FileUtils.readFileByLines(Constants.CART);
        List<CartModel> cartModels = new ArrayList<>();
        boolean f = false;
        for (String singleData : data) {
            if (singleData == null || singleData.length() == 0) continue;
            String[] modelData = singleData.split(Constants.SPLIT);
            int id = Integer.parseInt(modelData[0]);
            int num = Integer.parseInt(modelData[1]);
            if (id == freshId) {
                num++;
                f = true;
            }
            CartModel cartModel = new CartModel(id, num);
            cartModels.add(cartModel);
        }
        if (!f) {
            cartModels.add(new CartModel(freshId, 1));
        }
        List<String> res = new ArrayList<>();
        for (CartModel cartModel : cartModels) {
            res.add(cartModel.getFreshId() + Constants.SPLIT + cartModel.getNum());
        }
//        FileUtils.clearFile(Constants.CART);
//        FileUtils.writeByLine(Constants.CART, res);
    }

    public List<CartElement> showCart() {
        List<CartElement> res = new ArrayList<>();
        List<String> data = FileUtils.readFileByLines(Constants.CART);
        for (String record : data) {
            String[] cartItem = record.split(Constants.SPLIT);
            Fresh fresh = freshService.getDetail(Integer.parseInt(cartItem[0]));
            if (fresh.getName() == null) {
                continue;
            }
            int quantity = Integer.parseInt(cartItem[1]);
            CartElement cartElement = new CartElement();
            cartElement.setFresh(fresh);
            cartElement.setQuantity(quantity);
            res.add(cartElement);
        }
        return res;
    }

    public void pay() {
//        FileUtils.clearFile(Constants.CART);
    }
}
