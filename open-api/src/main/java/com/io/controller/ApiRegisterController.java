/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.io.controller;

import com.io.base.vo.ApiResult;
import com.io.service.UserServiceI;
import com.io.vo.RegisterForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册接口
 */
@RestController
@RequestMapping("/api")
@Api(tags = "注册接口")
public class ApiRegisterController {
    @Autowired
    private UserServiceI userService;

    @PostMapping("register")
    @ApiOperation("注册")
    public ApiResult register(@RequestBody RegisterForm form) {
        return userService.save(form);
    }
}
