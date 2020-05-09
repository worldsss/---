package com.example.bcyimitation.server.User;


import org.apache.ibatis.annotations.Insert;

public interface UserGivelikeServer {

    /**
     * 插入一个用户点赞的状态关系表
     * @param uid
     * @param prid
     * @return
     */
    int insertGivelike(Integer uid,Integer prid);

    /**
     * 计算出当前有几个用户给这个作品点赞
     * @param prid
     * @return
     */
    int countGivelikeByPrid(Integer prid);

    /**
     * 删除一个用户点赞状态关系表
     * @param uid
     * @param prid
     * @return
     */
    int removeGivelikeByPrid(Integer uid,Integer prid);

//---------------------文字作品

    /**
     * 插入一个用户点赞的状态关系表
     * @param uid
     * @param pcid
     * @return
     */
    int insertGivelikePcid(Integer uid,Integer pcid);


    /**
     * 计算出当前有几个用户给这个作品点赞
     * @param pcid
     * @return
     */
    int countGivelikeByPcid(Integer pcid);


    /**
     * 删除一个用户点赞状态关系表
     * @param uid
     * @param pcid
     * @return
     */
    int removeGivelikeByPcrd(Integer uid,Integer pcid);


}
