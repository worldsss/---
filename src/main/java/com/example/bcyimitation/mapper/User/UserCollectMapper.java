package com.example.bcyimitation.mapper.User;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.User.UserCollect;
import com.example.bcyimitation.pojo.User.UserGivelike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCollectMapper {

    /**
     * 插入一个收藏
     * @param userCollect
     * @return
     */
    @Insert("INSERT INTO `bcy`.`user_collect` (`ucid`, `uid`, `prid`)" +
            " VALUES (default, #{uid}, #{prid});")
    int insertUserCollect(UserCollect userCollect);


    /**
     * 根据uid来找所有的用户收藏
     * @param uid
     * @return
     */
    @Select("select * from user_collect where uid=#{uid};")
    List<UserCollect> selectUserCollectByUid(Integer uid);




    /**
     * 分页展示根据uid来找所有的用户收藏
     * @param paging
     * @return
     */
    @Select("select * from user_collect where uid=#{id} limit #{pageStart},#{pageSize};")
    List<UserCollect> selectUserCollectByUidPaging(Paging paging);

    /**
     * 分页展示根据uid来找所有的用户收藏的个数
     * @param uid
     * @return
     */
    @Select("select count(*) from user_collect where uid=#{uid};")
    int countUserCollectByUid(Integer uid);


    /**
     * 查找所有的prid
     * @return
     */
    @Select("select prid from user_collect;")
    List<UserCollect> selectAllUserCollectPrid();

    /**
     * 看当前的prid和uid是否一致，一致则收藏
     * @param userCollect
     * @return
     */
    @Select(" select count(*) from user_collect where uid=#{uid} and prid=#{prid};")
    int judgeUserCollectByUidAndPrid(UserCollect userCollect);


    /**
     * 取消用户对某一个作品的收藏
     * @param userCollect
     * @return
     */
    @Delete("delete from user_collect where prid = #{prid} and uid = #{uid};")
    int deleteUserCollectByUidAndPrid(UserCollect userCollect);


//    ----------------------文字作品的mapper--------------------



    /**
     * 插入一个文字收藏
     * @param userCollect
     * @return
     */
    @Insert("INSERT INTO `bcy`.`user_collect` (`ucid`, `uid`, `pcid`)" +
            " VALUES (default, #{uid},#{pcid});")
    int insertUserCollectPcid(UserCollect userCollect);


    /**
     * 看当前的prid和uid是否一致的，是1则是收藏
     * @param userCollect
     * @return
     */
    @Select(" select count(*) from user_collect where uid=#{uid} and pcid=#{pcid};")
    int judgeUserCollectByUidAndPcid(UserCollect userCollect);


    /**
     * 取消用户对某一个文字作品的收藏
     * @param userCollect
     * @return
     */
    @Delete("delete from user_collect where pcid = #{pcid} and uid = #{uid};")
    int deleteUserCollectByUidAndPcid(UserCollect userCollect);



}
