package com.example.bcyimitation.server.User;

import org.apache.ibatis.annotations.Select;

public interface UserCollectServer {

    /**
     * 插入一个用户的收藏
     * @param uid
     * @param prid
     * @return
     */
    int insertUserCollect(Integer uid,Integer prid);


    /**
     * 根据uid来查询收藏了多少个作品
     * @param uid
     * @return
     */
    int selectUserCollectByUid(Integer uid);

    /**
     * 所有被收藏的作品的prid
     * @return
     *//*
    List<UserCollect> selectAllUserCollectPrid();*/

    /**
     * 判断是uid和prid一致则收藏
     * @param uid
     * @param prid
     * @return
     */
    int judgeUserCollectByUidAndPrid(Integer uid,Integer prid);

    /**
     * 取消用户对某一作品的收藏
     * @param uid
     * @param prid
     * @return
     */
    int deleteUserCollectByUidAndPrid(Integer uid,Integer prid);




//    ------------------------------------

    /**
     * 根据pcid来收藏一个文字作品
     * @param uid
     * @param pcid
     * @return
     */
    int insertUserCollectByPcid(Integer uid,Integer pcid);



    /**
     * 判断是uid和prid一致则收藏
     * @param uid
     * @param pcid
     * @return
     */
    int judgeUserCollectByUidAndPcid(Integer uid,Integer pcid);


    /**
     * 取消用户对某一作品的收藏
     * @param uid
     * @param pcid
     * @return
     */
    int deleteUserCollectByUidAndPcid(Integer uid,Integer pcid);



}
