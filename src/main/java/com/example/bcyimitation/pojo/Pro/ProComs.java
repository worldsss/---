package com.example.bcyimitation.pojo.Pro;

import lombok.Data;

/**
 * 作品-评论的关系表
 */

@Data
public class ProComs {

    private Integer prcoid; //作品-评论表的主键
    private Integer prid; //作品表的主键
    private Integer cid; //评论表的主键


}
