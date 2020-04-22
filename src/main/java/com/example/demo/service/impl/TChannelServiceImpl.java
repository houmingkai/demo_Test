package com.example.demo.service.impl;

import com.example.demo.dao.TChannelDao;
import com.example.demo.entity.TChannel;
import com.example.demo.service.TChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TChannel)表服务实现类
 *
 * @author makejava
 * @since 2018-10-19 10:30:01
 */
@Service("tChannelService")
public class TChannelServiceImpl implements TChannelService {
    @Resource
    private TChannelDao tChannelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
//    @RedisCache(pre = "pre",key="channelId")
    @Override
    public TChannel queryById(Integer id) {
        return this.tChannelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TChannel> queryAllByLimit(int offset, int limit) {
        return this.tChannelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tChannel 实例对象
     * @return 实例对象
     */
    @Override
    public TChannel insert(TChannel tChannel) {
        this.tChannelDao.insert(tChannel);
        return tChannel;
    }

    /**
     * 修改数据
     *
     * @param tChannel 实例对象
     * @return 实例对象
     */
    @Override
    public TChannel update(TChannel tChannel) {
        this.tChannelDao.update(tChannel);
        return this.queryById(tChannel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tChannelDao.deleteById(id) > 0;
    }

    @Override
    public List<TChannel> findAll() {
        return tChannelDao.findAll();
    }
}