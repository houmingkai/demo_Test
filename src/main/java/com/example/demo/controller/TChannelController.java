package com.example.demo.controller;

import com.example.demo.entity.TChannel;
import com.example.demo.service.TChannelService;
import com.example.demo.util.FreeMarkerUtil;
import com.example.demo.util.RedisUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TChannel)表控制层
 *
 * @author makejava
 * @since 2018-10-19 10:30:02
 */
@RestController
@RequestMapping("/tChannel")
public class TChannelController {
    /**
     * 服务对象
     */
    @Resource
    private TChannelService tChannelService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value="通过主键查询单条数据", notes="根据id查询数据")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Integer")
    @GetMapping("/selectById")
    @ResponseBody
    public TChannel selectOne(Integer id) {
        TChannel tChannel = tChannelService.queryById(id);
        redisUtils.set("channel", tChannel.getChannelname(), RedisUtils.HOUR);
        return tChannel;
    }


    @GetMapping("/getData")
    @ResponseBody
    public List<TChannel> findAll(){
        List<TChannel> channelList =  tChannelService.findAll();


        Map<String, Object> mapData = new HashMap<>();

        mapData.put("channelList",channelList);//System.out.println(mapData.get("channelList"));
        //Freemaker
//        String templateFilePath = Constant.TEMPLATEFILEPATH;
//        System.out.println(templateFilePath);
//        String templateName = Constant.GETCHANNELFTL;
//        System.out.println(templateName);
//        String staticFileOutPath = Constant.ZS_STATICFILEGENERATEDIR;
//        System.out.println(staticFileOutPath);
//        String staticFileName = Constant.GETCHANNELDATAJS;
//        System.out.println(staticFileName);
//
//
        String templateFilePath ="D:/study_project/demo_Test/src/main/resources/templates/";
        String templateName = "channel.ftl";
        String staticFileOutPath = "C:\\Users\\viruser.v-desktop\\Desktop";
        String staticFileName = "channel.html";
       FreeMarkerUtil.geneHtmlFile(templateFilePath, templateName, mapData, staticFileOutPath, staticFileName);

        return  channelList;
    }

    public static void main(String[] args) throws Exception {

    }

}