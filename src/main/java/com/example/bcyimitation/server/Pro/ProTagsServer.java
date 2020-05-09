package com.example.bcyimitation.server.Pro;


import com.example.bcyimitation.pojo.Pro.ProTags;

import java.util.List;

public interface ProTagsServer {


    int insertProTags(Integer tid);


//    int insertForProTags(List<Pro_tags> pro_tags);

    /**
     * 插入一个作品与标签的关系
     * @param pro_tags
     * @return
     */
    int insertForProTags(List<ProTags> pro_tags);

    /**
     * 算出圈子的成员数
     * @param tid
     * @return
     */
    int countAllPridByTid(Integer tid);


}
