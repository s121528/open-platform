package com.io.service.impl;

import com.io.annotation.DataSource;
import com.io.base.vo.ApiResult;
import com.io.dao.UserDaoI;
import com.io.entity.UserEntity;
import com.io.service.UserServiceI;
import com.io.vo.UserVoReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:14:53
 * @JDK 1.8
 * @Description 功能模块：
 */
@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserDaoI userDaoI;

    @Override
    @DataSource(value = "slave2")
    public ApiResult saveUser(UserVoReq userVoReq) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userVoReq, userEntity);
        return ApiResult.ok(userDaoI.save(userEntity));
    }

    @Override
    public ApiResult getAll() {
        return ApiResult.ok(userDaoI.findAll());
    }
}
