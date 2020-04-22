package com.example.demo.service;

import com.example.demo.entity.TChannel;
import java.util.List;

/**
 * (TChannel)表服务接口
 *
 * @author makejava
 * @since 2018-10-19 10:30:01
 */
public interface TChannelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TChannel queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TChannel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tChannel 实例对象
     * @return 实例对象
     */
    TChannel insert(TChannel tChannel);

    /**
     * 修改数据
     *
     * @param tChannel 实例对象
     * @return 实例对象
     */
    TChannel update(TChannel tChannel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<TChannel> findAll();
}