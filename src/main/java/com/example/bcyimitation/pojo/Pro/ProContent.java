package com.example.bcyimitation.pojo.Pro;

import lombok.Data;

import java.util.List;

@Data
public class ProContent {

    /**
     *  作品用户表id
     */
    private Integer uid;

    /**
     * 作品主键id
     */
    private Integer prid;

    /**
     * 评论id
     */
    private Integer cid;

    /**
     * 图片简介
     */
    private String prInfo;
    /**
     * 图片首图
     */
    private String prImg;
    /**
     * 发布日期
     */
    private String prDate;
    /**
     * 作品点赞
     */
    private int prGivelike;
    /**
     * 作品点赞状态(0,1)
     */
    private Integer prIsGivelike;
    /**
     * 作品浏览量
     */
    private Integer prClick;
    /**
     * 作品评论总数
     */
    private Integer prCommentCount;

    /**
     * 作品上传的图片
     */
    private List<ProImgs> proImgs;



}
