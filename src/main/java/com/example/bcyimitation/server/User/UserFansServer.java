package com.example.bcyimitation.server.User;

import com.example.bcyimitation.pojo.User.UserFans;

import java.util.List;

public interface UserFansServer {

    /**
     * 插入一个粉丝
     * @param fid
     * @param uid
     * @return
     */
    int insertUserFans(Integer fid, Integer uid);

    /**
     * 根据uid来查询出来fans
     * @param uid
     * @param fid
     * @return
     */
    int selectUserFansByUid(Integer uid,Integer fid);

    /**
     * 根据当前登录用户来显示自己关注的所有用户 登录用户是fid
     * @return
     */
    List<UserFans> selectUserFansUserByFid(Integer fid);
//    List<User> selectUserFansUserByFid(Integer fid);


}
