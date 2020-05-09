package com.example.bcyimitation.pojo.Pc;

import lombok.Data;

@Data
public class PcContent {

    private Integer uid;
    private Integer pcid;
    private Integer cid;
   /* private String  pc_content;
    private String  pc_title;
    private String  pc_info;
    private String  pc_firstImg;*/
    private String  pcContent;
    private String  pcTitle;
    private String  pcInfo;
    private String  pcFirstImg;
    private Integer pcGivelike;
    private Integer pcIsGivelike;
    private Integer pcGo;
    private Integer pcClick;
    private Integer pcCollect;
    private Integer pcComment;

}
