package com.example.bcyimitation.mapper.User;

import com.example.bcyimitation.pojo.User.UserTag;
import com.example.bcyimitation.pojo.User.UserTagRelation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserTagMapper {

    /**
     * 根据utid来查找对应的用户标签
     * @param utid
     * @return
     */
    @Select(" select * from user_tag_relation as ur,user_tag as ut " +
            "where ut.utid=ur.utid and ur.utid = #{utid} group by ut.utid;")
    UserTag selectUserTagByUtid(Integer utid);

    /**
     * 显示所有的用户标签
     * @return
     */
    @Select(" select * from user_tag;")
    List<UserTag> selectAllUserTag();

  /*  *//**
     * 根据用户的uid来查找当前用户的所有用户标签
     * @param uid
     * @return
     *//*
    @Select(" select * from user_tag_relation as utr,user_tag as ut " +
            "where utr.utid = ut.utid and utr.uid = #{uid};")
    List<UserTag> selectUserTagByUid(Integer uid);*/

    /**
     * 删除某一个用户标签
     * @param userTagRelation
     * @return
     */
    @Delete(" delete from user_tag_relation where uid = #{uid} and utid = #{utid};")
    int deleteUserTagRelationByUidAndUtid(UserTagRelation userTagRelation);



}
