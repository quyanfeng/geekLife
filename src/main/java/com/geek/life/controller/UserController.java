package com.geek.life.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/9/16 14:00
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @RequiresPermissions("user:list")
    @RequestMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }
}
