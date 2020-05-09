package com.example.bcyimitation.pojo.Pc;

import com.example.bcyimitation.pojo.Tags;
import lombok.Data;

import java.util.List;

@Data
public class PcIndex {

 /*   private Integer uid;
    private Integer pcid;
    private Integer cid;
    private String  pc_content;
    private String  pc_title;
    private String  pc_info;
    private Integer pc_givelike;
    private Integer pc_go;
    private Integer pc_click;
    private Integer pc_collect;
    private String  pc_date;
    private Integer pc_comment;
    private String  pc_firstImg;
    private String  user_name;
    private String  user_avatar;
    private List<Tags> pc_tags;
    private List<String> pc_imgs;
*/

    private Integer uid;
    private Integer pcid;
    private Integer cid;
    private String  pcContent;
    private String  pcTitle;
    private String  pcInfo;
    private Integer pcGivelike;
    private Integer pcIsGivelike;
    private Integer pcGo;
    private Integer pcClick;
    private Integer pcCollect;
    private String  pcDate;
    private Integer pcComment;
    private String  pcFirstImg;
    private String  userName;
    private String  userAvatar;

    private List<Tags> pcTags;
    private List<String> pcImgs;



}
