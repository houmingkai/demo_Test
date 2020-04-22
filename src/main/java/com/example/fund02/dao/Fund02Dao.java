package com.example.fund02.dao;

import com.example.fund02.entity.Fund02;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Fund02)表数据库访问层
 *
 * @author makejava
 * @since 2019-01-17 17:35:14
 */
@Mapper
public interface Fund02Dao {


    int insert(Fund02 fund02);

}