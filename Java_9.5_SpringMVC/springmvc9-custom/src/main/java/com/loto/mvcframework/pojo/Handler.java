package com.loto.mvcframework.pojo;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 封装 handler 方法相关的信息
 */
@Data
public class Handler {
    private Object controller; // method.invoke(obj,)
    private Method method;
    private Pattern pattern;   // spring 中 url 是支持正则的
    private Map<String,Integer> paramIndexMapping; // 参数顺序，是为了进行参数绑定，key是参数名，value代表是第几个参数 <name,2>

    public Handler(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
        this.paramIndexMapping = new HashMap<>();
    }
}
