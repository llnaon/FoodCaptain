package com.cityu.foodcaptain.service;

import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FreshService {

    public static final List<Fresh> FRESH = getFresh();

    public static List<Fresh> getFresh() {
        String rawData = Constants.FRESH_DATA;
//        List<String> data = FileUtils.readFileByLines(Constants.FRESH);
        List<String> data = Arrays.asList(rawData.split("\n"));
        List<Fresh> freshList = new ArrayList<>();
        for (String record : data) {
            Fresh fresh = parseFresh(record);
            if (fresh != null) {
                freshList.add(fresh);
            }
        }
        return freshList;
    }

    public static Fresh parseFresh(String record) {
        String[] freshData = record.split(Constants.SPLIT);
        return Fresh.makeFresh(freshData);
    }

    public Fresh getDetail(int id) {
        for (Fresh fresh : FRESH) {
            if (fresh.getId() == id) {
                return fresh;
            }
        }
        return new Fresh();
    }

    public List<Fresh> searchFresh(String des) {
        List<Fresh> res = new ArrayList<>();
        if (des == null) {
            return res;
        }
        for (Fresh fresh : FRESH) {
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
        Set<Fresh> res = new HashSet<>();
        List<Fresh> freshList = FRESH;
        Fresh target = getDetail(id);
        if (target.getName() == null) {
            return new ArrayList<>();
        }
        String name = target.getName();
        String type = target.getType();
        String category = target.getCategory();
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
        for (Fresh fresh : freshList) {
            if (category.equals(fresh.getCategory())) {
                res.add(fresh);
            }
        }
        res.remove(target);
        return new ArrayList<>(res);
    }
}
