package com.example.bcyimitation.server.User;

import com.example.bcyimitation.pojo.*;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.pojo.User.User;
import com.example.bcyimitation.pojo.User.UserTag;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserServer {

    /**
     * 插入一个用户
     *
     * @param user_name
     * @param user_password
     * @param user_avatar
     * @return
     */
    int insertUser(String user_name, String user_password, String user_avatar);


    /**
     * 判断一个用户名字是否重复
     *
     * @param userName
     * @return
     */
    int judgeUserName(String userName);


    /**
     * 判断用户登录
     *
     * @param user_name
     * @param user_password
     * @return
     */
    int userLogin(String user_name, String user_password, HttpSession session);


    /**
     * 根据用户uid来查找对应的用户
     *
     * @param uid
     * @return
     */
    User selectUserByUid(Integer uid);


    User selectUserByUidLimitFive(Integer uid);


    /**
     * 根据用户的密码和姓名来找到uid
     *
     * @param user_name
     * @param user_password
     * @return
     */
    int searchUidByUserName(String user_name, String user_password);


    /**
     * 根据session里面存储的用户和密码来返回一个user
     *
     * @param session
     * @return
     */
    User getUserBySession(HttpSession session);

    /**
     * 根据uid来寻找用户头像
     *
     * @param uid
     * @return
     */
    String getUserAvatarByUid(int uid);

    /**
     * 根据prid来找到对应的user对象
     *
     * @param prid
     * @return
     */
    User searchUserForPrid(int prid);

    /**
     * 根据uid来找对应的作品
     *
     * @param uid
     * @return
     */
    List<ProContent> selectProContentByUid(int uid);



    /**
     * 根据uid来展示用户关注的所有圈子
     *
     * @param uid
     * @return
     */
    List<Tags> selectTagsNameByUid(Integer uid);

    /**
     * 修改用户头像
     *
     * @param userAvatar
     * @param uid
     * @return
     */
    int updateUserAvatarByUid(String userAvatar, Integer uid);


    /**
     * 修改用户姓名，信息，地区，性别
     *
     * @param userName
     * @param userInfo
     * @param userArea
     * @param userSex
     * @param uid
     * @return
     */
    int updateUserInfoByUid(String userName, String userInfo, String userArea,
                            String userSex, Integer uid);



}
