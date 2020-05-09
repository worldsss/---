package com.example.bcyimitation.pojo.Pro;


import com.example.bcyimitation.pojo.Tags;
import lombok.Data;

import java.util.List;

/**
 * 不作为业务处理的javaBean ,只用于首页的多表连接显示
 */
@Data
public class ProIndex {

    private Integer uid;
    private Integer prid;
    private Integer cid;

  /*  private String  pr_img; //图片
    private String  pr_info; //介绍
    private String  pr_date; //发布日期
    private Integer pr_givelike; //点赞
    private Integer pr_go; //分享
    private Integer pr_click; //访问
    private Integer pr_comment_count; //收藏个数
    private String  user_name; //用户名
    private String  user_avatar; //用户头像
    private Integer pr_img_count; //当前有多少个图片
    private Integer pr_is_givelike;//点赞状态
    private List<Tags> pr_tags;
    private List<Pro_imgs> pro_imgs;*/


    private String  prImg; //图片
    private String  prInfo; //介绍
    private String  prDate; //发布日期
    private Integer prGivelike; //点赞
    private Integer prGo; //分享
    private Integer prClick; //访问
    private Integer prCommentCount; //收藏个数
    private String  userName; //用户名
    private String  userAvatar; //用户头像
    private Integer prImgCount; //当前有多少个图片

    private Integer prIsGivelike;//点赞状态

    private List<Tags> prTags;
    private List<ProImgs> proImgs;



}
