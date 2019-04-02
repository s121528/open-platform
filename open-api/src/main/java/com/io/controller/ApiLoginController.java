package com.io.controller;

import com.io.annotation.Login;
import com.io.base.vo.ApiResult;
import com.io.service.TokenServiceI;
import com.io.service.UserServiceI;
import com.io.vo.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:10:14
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestController
@RequestMapping("/api")
@Api(tags = "注册接口")
public class ApiLoginController {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private TokenServiceI tokenServiceI;

    @PostMapping("login")
    @ApiOperation("登录")
    public ApiResult login(@RequestBody LoginForm form) {
        //用户登录
        return userServiceI.login(form);
    }

    @Login
    @PostMapping("logout")
    @ApiOperation("退出")
    public ApiResult logout(@ApiIgnore @RequestAttribute("id") Integer id) {
        return tokenServiceI.expireToken(id);
    }
}
