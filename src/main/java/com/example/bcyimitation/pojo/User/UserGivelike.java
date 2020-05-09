package com.example.bcyimitation.pojo.User;

import lombok.Data;

@Data
public class UserGivelike {

    private Integer ugid; //用户-作品点赞表的主键
    private Integer uid; //用户id
    private Integer prid; //图片作品id
    private Integer pcid; //文字作品id



}
