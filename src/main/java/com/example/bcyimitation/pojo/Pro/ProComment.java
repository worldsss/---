package com.example.bcyimitation.pojo.Pro;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

/**
 * 用户评论表
 */
@Data
public class ProComment {

    private Integer cid; //评论id
    private Integer uid; //用户id
    private String  content; //评论内容
    private Integer givelike; //评论的点赞
    private String  reply; //评论回复
    private String  time; //评论时间

    private Integer parent_cid;//回复哪个评论

}
