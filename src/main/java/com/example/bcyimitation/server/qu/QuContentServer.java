package com.example.bcyimitation.server.qu;

import com.example.bcyimitation.pojo.qu.Qu_content;

import java.util.List;

public interface QuContentServer {


    /**
     * 插入到问题表中
     * @param qu_text
     * @param qu_info
     * @param qu_imgs
     * @return
     */
    int insertQuContent(String qu_text, String qu_info);

    /**
     * 根据quid来查询出来某个问题的图片和内容
     * @param quid
     * @return
     */
    Qu_content selectQuContent(int quid);

    /**
     * 查询全部的问题，加上图片
     * @return
     */
    List<Qu_content> selectAllQuContent();






}
