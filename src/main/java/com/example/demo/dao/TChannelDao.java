package com.example.demo.dao;

import com.example.demo.entity.TChannel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * (TChannel)表数据库访问层
 *
 * @author makejava
 * @since 2018-10-19 10:29:58
 */
@Mapper
@CacheConfig(cacheNames = "users")
public interface TChannelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Cacheable
    TChannel queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TChannel> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tChannel 实例对象
     * @return 对象列表
     */
    List<TChannel> queryAll(TChannel tChannel);

    /**
     * 新增数据
     *
     * @param tChannel 实例对象
     * @return 影响行数
     */
    @CachePut
    int insert(TChannel tChannel);

    /**
     * 修改数据
     *
     * @param tChannel 实例对象
     * @return 影响行数
     */
    @CachePut
    int update(TChannel tChannel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @CacheEvict
    int deleteById(Integer id);

    List<TChannel> findAll();
}