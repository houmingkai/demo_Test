package com.example.demo.service;

import com.example.demo.entity.TRule;
import java.util.List;

/**
 * (TRule)表服务接口
 *
 * @author makejava
 * @since 2018-10-22 09:26:01
 */
public interface TRuleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TRule queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TRule> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tRule 实例对象
     * @return 实例对象
     */
    TRule insert(TRule tRule);

    /**
     * 修改数据
     *
     * @param tRule 实例对象
     * @return 实例对象
     */
    TRule update(TRule tRule);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}