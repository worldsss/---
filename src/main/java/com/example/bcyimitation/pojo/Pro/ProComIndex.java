package com.example.bcyimitation.pojo.Pro;

import com.example.bcyimitation.pojo.User.UserTag;
import lombok.Data;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Data
public class ProComIndex {

    private Integer uid; //用户id
    private String  user_name; //用户姓名
    private String  user_avatar; //用户头像
    private Integer cid; //评论id
    private String  content; //评论内容
    private Integer givelike; //评论的点赞
    private String  reply; //评论回复
    private String  time; //评论时间

    List<UserTag> userTags; //用户的标签
//    List<ProComment> userParent; //用户的回复，内容是它自己
    List<ProComIndex> userParent; //用户的回复，内容是它自己


}
