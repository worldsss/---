package com.example.bcyimitation.controller;

import com.example.bcyimitation.pojo.Pro.ProTags;
import com.example.bcyimitation.pojo.Tags;
import com.example.bcyimitation.server.TagsServer;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TagsController {

    @Resource
    private TagsServer tagsServer;

    /**
     * 插入一个标签
     * @param tagsName
     * @return
     */
    @GetMapping("/insertTags")
    public int insertTags(String tagsName){
        return tagsServer.insertTags(tagsName);
    }

    @GetMapping("/selectTagsNewestTid")
    public int selectTagsNewestTid(){
        return tagsServer.selectTagsNewestTid();
    }


    /**
     * 显示所有的推荐的标签(圈子)
     * @return
     */
    @GetMapping("/showTags")
    public List<Tags> selectTagsByRecom(){

        List<Tags> tagsList = tagsServer.selectTagsByRecom();

        return tagsList;
    }

    /**
     * 添加一个标签
     * @param proTags
     * @return
     */
    @PostMapping("/insertTags")
    public String insertTags(ProTags proTags){

//        tagsServer.

        return "哈哈";
    }

    /**
     * 根据tid来获取某个标签
     * @param tid
     * @return
     */
    @GetMapping("/getTagsByTid")
    public Tags selectTagsNameByTid(Integer tid){

        return tagsServer.selectTagsByTid(tid);
    }


    /**
     * 根据tid来添加一个tags_hot
     * @param tid
     * @return
     */
    @GetMapping("/addTagsHotByTid")
    public int addTagsHotByTid(Integer tid){

        return tagsServer.updateTagsHotByTid(tid);
    }

    /**
     * 获取最热门的五条圈子
     * @return
     */
    @GetMapping("/getTagsHotLimitFive")
    public List<Tags> selectTagsHotLimitFive(){
        return tagsServer.selectTagsHotLimitFive();
    }

    /**
     * 更新当前圈子内作品的发布时间
     * @return
     */
    @GetMapping("/updateTagsLatestTime")
    public int updateTagsLatestTime(){
        return tagsServer.updateTagsLatestTime();
    }


}
