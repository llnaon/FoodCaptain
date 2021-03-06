package com.cityu.foodcaptain.controller;

import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.service.FreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FreshController {

    @Autowired
    private FreshService freshService;

    @GetMapping("/searchFresh")
    public List<Fresh> searchFresh(String str){
        return freshService.searchFresh(str);
    }

    @GetMapping("/getFreshDetail")
    public Fresh getFreshDetail(int id){
        return freshService.getDetail(id);
    }

    @GetMapping("/fresh")
    public List<Fresh> getFresh(){
        return FreshService.FRESH;
    }

}
