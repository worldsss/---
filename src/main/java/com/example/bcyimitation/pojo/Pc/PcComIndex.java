package com.example.bcyimitation.pojo.Pc;

import com.example.bcyimitation.pojo.Pro.ProComIndex;
import com.example.bcyimitation.pojo.User.UserTag;
import lombok.Data;

import java.util.List;
@Data
public class PcComIndex {

    private Integer uid; //用户id
    private String  userName; //用户姓名
    private String  userAvatar; //用户头像
    private Integer ccid; //评论id
    private String  coContent; //评论内容
    private Integer coGivelike; //评论的点赞
    private Integer  coReply; //评论回复
    private String  coTime; //评论时间
    List<UserTag> userTags; //用户的标签
    List<PcComIndex> userParent; //用户的回复，内容是它自己



}
