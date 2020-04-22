package com.example.demo.controller;

import com.example.demo.entity.TRule;
import com.example.demo.service.TRuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TRule)表控制层
 *
 * @author makejava
 * @since 2018-10-22 09:26:01
 */
@RestController
@RequestMapping("tRule")
public class TRuleController {
    /**
     * 服务对象
     */
    @Resource
    private TRuleService tRuleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TRule selectOne(Integer id) {
        return this.tRuleService.queryById(id);
    }

}