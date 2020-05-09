package com.example.bcyimitation.server.Pc;

import com.example.bcyimitation.pojo.Pro.ProTags;

import java.util.List;

public interface PcTagsServer {


    /**
     * 插入一个pc_tags里的值
     * @param tid
     * @return
     */
    int insertPcTags(Integer tid);


    //    int insertForProTags(List<Pro_tags> pro_tags);
    int insertForPcTags(List<ProTags> pro_tags);







}
