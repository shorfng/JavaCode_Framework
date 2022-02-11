package com.loto.example.controller;

import com.loto.example.service.IQueryService;
import com.loto.mvcframework.annotations.LotoAutowired;
import com.loto.mvcframework.annotations.LotoController;
import com.loto.mvcframework.annotations.LotoRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-06 16:46</p>
 * <p>PageName：QueryController.java</p>
 * <p>Function：</p>
 */

@LotoController
@LotoRequestMapping("/loto")
public class QueryController {
    @LotoAutowired
    private IQueryService queryService;

    /**
     * http://localhost:8080/loto/query?name=td
     */
    @LotoRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String name) {
        return queryService.queryName(name);
    }
}
