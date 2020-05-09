package com.example.bcyimitation.pojo.Pc;

import lombok.Data;

/**
 * 文字评论表
 */
@Data
public class PcComment {

    private Integer ccid;
    private Integer uid;
    private String  coContent;
    private Integer coGivelike;
    private Integer coReply;
    private String  coTime;
    private Integer coParentCcid;

}
