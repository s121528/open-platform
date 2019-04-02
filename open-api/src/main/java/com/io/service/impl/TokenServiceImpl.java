package com.io.service.impl;

import com.io.base.vo.ApiResult;
import com.io.dao.TokenDaoI;
import com.io.entity.TokenEntity;
import com.io.service.TokenServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:9:08
 * @JDK 1.8
 * @Description 功能模块：
 */
@Service
public class TokenServiceImpl implements TokenServiceI {
    @Autowired
    private TokenDaoI tokenDaoI;
    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;

    @Override
    public TokenEntity queryByToken(String token) {
        return null;
    }

    @Override
    public TokenEntity createToken(Integer id) {
        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        // 生成token
        String token = generateToken();
        // 保存或更新用户token
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(id);
        tokenEntity.setToken(token);
        tokenEntity.setUpdateTime(LocalDateTime.now());
        tokenEntity.setExpireTime(expireTime);
        tokenDaoI.save(tokenEntity);
        return tokenEntity;
    }

    @Override
    public ApiResult expireToken(Integer id) {
        Date now = new Date();
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(id);
        tokenEntity.setUpdateTime(LocalDateTime.now());
        tokenEntity.setExpireTime(now);
        TokenEntity updateUser = tokenDaoI.save(tokenEntity);
        return ApiResult.ok(updateUser);
    }

    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
