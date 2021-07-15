package com.cityu.foodcaptain.utils;


/**
 * 通用响应请求格式
 *
 * @author Langning LIU
 * @date 2021/4/10
 */
public class Resp<T> {
    private int code;
    private String message;
    private T data;
}
