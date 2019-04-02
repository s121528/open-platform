package com.io.dao;

import com.io.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:9:05
 * @JDK 1.8
 * @Description 功能模块：
 */
@Repository
public interface TokenDaoI extends JpaRepository<TokenEntity, Serializable> {
    TokenEntity findByToken(String token);
}
