package com.example.bcyimitation.server.User;

import com.example.bcyimitation.pojo.User.UserTag;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface UserTagServer {

    /**
     * 根据uid来查找对应的
     * @param uid
     * @return
     */
    List<UserTag> selectUserTabByUid(Integer uid);

    /**
     * 插入一个用户的标签
     * @param uid
     * @param utid
     * @return
     */
    int insertUserTagRelationByUidAndUtid(Integer uid,Integer utid);


    /**
     * 显示所有的用户标签
     * @return
     */
    List<UserTag> selectAllUserTag();


//    List<UserTag> selectUserTabByUid(Integer uid);

    /**
     * 删除用户的一个用户标签
     * @param uid
     * @param utid
     * @return
     */
    int deleteUserTagRelationByUidAndUtid(Integer uid,Integer utid);



}
