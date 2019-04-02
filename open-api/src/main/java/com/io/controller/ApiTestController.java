package com.io.controller;

import com.io.annotation.Login;
import com.io.annotation.LoginUser;
import com.io.base.vo.ApiResult;
import com.io.entity.UserEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:11:37
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestController
@RequestMapping(value = "/api")
public class ApiTestController {
    /**
     * 功能描述：
     */
    @Login
    @GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息", response = UserEntity.class)
    public ApiResult test(@ApiIgnore @LoginUser UserEntity user) {
        return ApiResult.ok(user);
    }

    @Login
    @GetMapping("id")
    @ApiOperation("获取用户ID")
    public ApiResult userInfo(@ApiIgnore @RequestAttribute("ID") Integer id) {
        return ApiResult.ok().put("id", id);
    }

    @Login
    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public ApiResult notToken() {
        return ApiResult.ok().put("msg", "无需token也能访问。。。");
    }
}
