package com.example.bcyimitation.pojo.User;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private Integer uid;
   /* private String  user_name; //用户名称
    private String  user_password; //用户密码
    private String  user_avatar; //用户头像
    private String  user_info; //用户信息
    private String  user_sex; //用户性别
    private String  user_area; //用户地址
    private Integer user_attention; //用户关注个数
    private Integer user_fans; //用户粉丝个数
    private Integer user_givelike;//用户所有作品总获赞个数*/

    private String  userName; //用户名称
    private String  userPassword; //用户密码
    private String  userAvatar; //用户头像
    private String  userInfo; //用户信息
    private String  userSex; //用户性别
    private String  userArea; //用户地址
    private Integer userAttention; //用户关注个数
    private Integer userFans; //用户粉丝个数
    private Integer userGivelike;//用户所有作品总获赞个数



}
