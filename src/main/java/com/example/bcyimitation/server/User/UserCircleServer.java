package com.example.bcyimitation.server.User;

import com.example.bcyimitation.pojo.User.UserCircle;

import java.util.List;

public interface UserCircleServer {

    /**
     * 插入一条用户-圈子关注表
     * @param uid
     * @param tid
     * @return
     */
    int insertUserCircle(Integer uid,Integer tid);

    /**
     * 根据uid和tid来判断当前用户是否关注了此圈子
     * @param uid
     * @param tid
     * @return
     */
    int judgeUserCircleByTidAndUid(Integer uid,Integer tid);

    /**
     * 根据uid和tid来取消用户-圈子的联系
     * @param uid
     * @param tid
     * @return
     */
    int deleteUserCircleByTidAndUid(Integer uid,Integer tid);


    /**
     * 根据tid来判断最火的热门圈子
     * @param tid
     * @return
     */
    List<UserCircle> selectMorestUserCircle(Integer tid);



}
