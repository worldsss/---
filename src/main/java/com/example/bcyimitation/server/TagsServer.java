package com.example.bcyimitation.server;

import com.example.bcyimitation.pojo.Tags;

import java.util.List;

public interface TagsServer {

    /**
     * 插入一个标签
     * @param tagsName
     * @return
     */
    int insertTags(String tagsName);

    /**
     * 获取最新入库的tid
     * @return
     */
    int selectTagsNewestTid();


    /**
     * 查询推荐的标签
     * @return
     */
    List<Tags> selectTagsByRecom();

    /**
     * 根据tid来显示tagss
     * @return
     */
    Tags selectTagsByTid(Integer tid);

    /**
     * 计算出所有圈子中的作品总个数
     * @param tid
     * @return
     */
    int countAllProContentByTid(Integer tid);

    /**
     * 每添加一个圈子中的作品时，圈子中的tags_hot+1
     * @param tid
     * @return
     */
    int updateTagsHotByTid(Integer tid);

    /**
     * 查询出最热门的五条圈子
     * @return
     */
    List<Tags> selectTagsHotLimitFive();

    /**
     * 当上传一个圈子里的作品时，修改圈子的最新时间
     * @param tid
     * @return
     */
    int updateTagsLatestTime();


}
