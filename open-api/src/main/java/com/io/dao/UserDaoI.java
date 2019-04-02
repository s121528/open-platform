package com.io.dao;

import com.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:9:06
 * @JDK 1.8
 * @Description 功能模块：
 */
@Repository
public interface UserDaoI extends JpaRepository<UserEntity, Serializable> {
    UserEntity findById(Integer id);

    UserEntity findByMobile(String form);
}
