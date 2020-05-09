package com.example.bcyimitation.pojo.qu;

import lombok.Data;

@Data
public class Qu_index {

    private Integer uid; //用户id
    private Integer quid; //问题id
    private Integer cid; //评论id
    private String  qu_text; //问题
    private String  qu_info; //问题简介
    private String  qu_date; //提问题时间
    private Integer qu_givelike; //问题点赞
    private Integer qu_go; //问题分析
    private Integer qu_click; //问题访问
    private Integer qu_collect; //问题收藏
    private String  qu_firstImg; //问题首图
    private String  user_name; //用户名称
    private String  user_avatar; //用户头像
    private String  user_link; //用户主页链接


}
