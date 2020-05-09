package com.example.bcyimitation.mapper.User;

import com.example.bcyimitation.pojo.User.UserTagRelation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserTagRelationMapper {

    /**
     * 根据uid来查找当前用户的用户标签
     * @param uid
     * @return
     */
    @Select("select * from user_tag_relation where uid = #{uid};")
    List<UserTagRelation> selectUserTagRelationByUid(Integer uid);

    /**
     * 插入一个用户的身份标签
     * @param userTagRelation
     * @return
     */
    @Insert("INSERT INTO `bcy`.`user_tag_relation` (`urtid`, `uid`, `utid`) " +
            "VALUES (default, #{uid}, #{utid});")
    int insertUserTagRelationByUidAndUtid(UserTagRelation userTagRelation);



}
