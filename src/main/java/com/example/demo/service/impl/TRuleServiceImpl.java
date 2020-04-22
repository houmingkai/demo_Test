package com.example.demo.service.impl;

import com.example.demo.entity.TRule;
import com.example.demo.dao.TRuleDao;
import com.example.demo.service.TRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TRule)表服务实现类
 *
 * @author makejava
 * @since 2018-10-22 09:26:01
 */
@Service("tRuleService")
public class TRuleServiceImpl implements TRuleService {
    @Resource
    private TRuleDao tRuleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TRule queryById(Integer id) {
        return this.tRuleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TRule> queryAllByLimit(int offset, int limit) {
        return this.tRuleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRule 实例对象
     * @return 实例对象
     */
    @Override
    public TRule insert(TRule tRule) {
        this.tRuleDao.insert(tRule);
        return tRule;
    }

    /**
     * 修改数据
     *
     * @param tRule 实例对象
     * @return 实例对象
     */
    @Override
    public TRule update(TRule tRule) {
        this.tRuleDao.update(tRule);
        return this.queryById(tRule.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tRuleDao.deleteById(id) > 0;
    }
}