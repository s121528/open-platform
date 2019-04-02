package com.io.service.impl;

import com.io.base.vo.ApiResult;
import com.io.base.vo.ApiResultEnum;
import com.io.dao.UserDaoI;
import com.io.entity.TokenEntity;
import com.io.entity.UserEntity;
import com.io.exception.SelfException;
import com.io.service.TokenServiceI;
import com.io.service.UserServiceI;
import com.io.vo.LoginForm;
import com.io.vo.RegisterForm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:9:07
 * @JDK 1.8
 * @Description 功能模块：
 */
@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserDaoI userDaoI;
    @Autowired
    private TokenServiceI tokenServiceI;

    @Override
    public UserEntity queryByMobile(String mobile) {
        return null;
    }

    @Override
    public ApiResult login(LoginForm form) {
        // 用户是否存在
        UserEntity user = userDaoI.findByMobile(form.getMobile());
        if (StringUtils.isEmpty(user)) {
            return ApiResult.error();
        }
        String password = user.getPassword();
        //密码错误
        if (!password.equals(DigestUtils.sha256Hex(form.getPassword()))) {
            throw new SelfException(ApiResultEnum.APPTOKEN_VALUE);
        }
        //获取登录token
        TokenEntity tokenEntity = tokenServiceI.createToken(user.getId());
        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());
        return ApiResult.ok(map);
    }

    @Override
    public UserEntity getById(Integer id) {
        return userDaoI.findById(id);
    }

    @Override
    public ApiResult save(RegisterForm form) {
        //表单校验
        UserEntity user = new UserEntity();
        user.setMobile(form.getMobile());
        user.setUsername(form.getUserName());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        UserEntity saveUser = userDaoI.save(user);
        return ApiResult.ok(saveUser);
    }
}
