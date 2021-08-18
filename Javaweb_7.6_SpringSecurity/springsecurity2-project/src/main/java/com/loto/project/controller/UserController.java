package com.loto.project.controller;

import com.loto.project.domain.User;
import com.loto.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户处理类
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<User> userList = userService.list();
        model.addAttribute("userList", userList);
        return "user_list";
    }

    /**
     * 查询所有用户-返回json数据
     *
     * @return
     */
    @RequestMapping("/findAllTOJson")
    @ResponseBody
    public List<User> findAllTOJson() {
        List<User> userList = userService.list();
        return userList;
    }

    /**
     * 用户修改页面跳转
     *
     * @return
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user_update";
    }

    /**
     * 用户添加或修改
     *
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(User user) {
        userService.saveOrUpdate(user);
        return "redirect:/user/findAll";
    }

    /**
     * 用户添加页面跳转
     *
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return "user_add";
    }

    /**
     * 用户删除
     *
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.removeById(id);
        return "redirect:/user/findAll";
    }

    /**
     * 用户删除-多选删除
     *
     * @return
     */
    @GetMapping("/delByIds")
    public String delByIds(@RequestParam(value = "id") List<Integer> ids) {
        for (Integer id : ids) {
            System.out.println(id);
        }
        return "redirect:/user/findAll";
    }


    /**
     * 根据用户ID查询用户
     *
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public User getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return user;
    }

}
