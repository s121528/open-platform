package com.io.dao;

import com.io.base.log.SysLogInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:15:35
 * @JDK 1.8
 * @Description 功能模块：
 */
@Repository
public interface LogDaoI extends JpaRepository<SysLogInfoEntity, Serializable> {
}