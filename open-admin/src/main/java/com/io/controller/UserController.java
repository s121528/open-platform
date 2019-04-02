package com.io.controller;

import com.io.base.encryp.EncryptMethod;
import com.io.base.log.SysLog;
import com.io.base.vo.ApiResult;
import com.io.service.UserServiceI;
import com.io.vo.UserVoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:15:02
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceI userServiceI;

    /**
     * 功能描述：保存
     */
    @PostMapping(value = "/saveUser")
    @SysLog(name = "测试")
    @EncryptMethod
    public ApiResult saveUser(@RequestBody UserVoReq userVoReq) {
        return userServiceI.saveUser(userVoReq);
    }

    /**
     * 功能描述：
     */
    @PostMapping(value = "/getAll")
    @EncryptMethod
    public ApiResult getAll() {
        return userServiceI.getAll();
    }
}
