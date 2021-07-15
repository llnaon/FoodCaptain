package com.cityu.foodcaptain.service;

import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreshService {

    public List<Fresh> getFresh() {
        List<String> data = FileUtils.readFileByLines(Constants.FRESH);
        List<Fresh> freshList = new ArrayList<>();
        for (String record : data) {
            String[] freshData = record.split(Constants.SPLIT);
            freshList.add(Fresh.makeFresh(freshData));
        }
        return freshList;
    }

    public Fresh getDetail(int id) {
        List<Fresh> freshList = getFresh();
        for (Fresh fresh : freshList) {
            if (fresh.getId() == id) {
                return fresh;
            }
        }
        return null;
    }

    public List<Fresh> searchFresh(String des) {
        List<Fresh> res = new ArrayList<>();
        if (des == null) {
            return res;
        }
        List<Fresh> freshList = getFresh();
        for (Fresh fresh : freshList) {
            if (des.equals(fresh.getType())
                    || des.equals(fresh.getCategory())
                    || match(des, fresh.getName())) {
                res.add(fresh);
            }
        }
        return res;
    }

    private boolean match(String name, String freshName) {
        if (name == null || name.trim().equals("") || freshName == null) {
            return false;
        }
        int len = name.length();
        for (int i = 0; i + len <= freshName.length(); i++) {
            if (freshName.substring(i, i + len).equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Fresh> recommendFresh(int id) {
        List<Fresh> res = new ArrayList<>();
        List<Fresh> freshList = getFresh();
        Fresh target = getDetail(id);
        String name = target.getName();
        String type = target.getType();
        for (Fresh fresh : freshList) {
            if (match(name, fresh.getName())) {
                res.add(fresh);
            }
        }
        for (Fresh fresh : freshList) {
            if (type.equals(fresh.getType())) {
                res.add(fresh);
            }
        }
        return res;
    }
}
