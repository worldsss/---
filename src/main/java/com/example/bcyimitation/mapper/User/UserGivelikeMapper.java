package com.example.bcyimitation.mapper.User;

import com.example.bcyimitation.pojo.User.UserGivelike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserGivelikeMapper {

    /**
     * 插入一个用户点赞的状态
     * @param userGivelike
     * @return
     */
    @Insert("INSERT INTO `bcy`.`user_givelike` (`ugid`, `uid`, `prid`) VALUES (default, #{uid},#{prid});")
    int insertUserGivelikeMapper(UserGivelike userGivelike);

    /**
     * 计算出当前有几个用户点赞
     * @param prid
     * @return
     */
    @Select("select count(*) from user_givelike where prid = #{prid};")
    int countUserGivelikeByPrid(Integer prid);

    /**
     * 查询出所有的用户点赞-作品表的内容，用于重复校验
     * @return
     */
    @Select("select * from user_givelike;")
    List<UserGivelike> selectAllUserGivelike();


    /**
     * 根据用户uid来查询出当前用户点赞过的记录
     * @param uid
     * @return
     */
    @Select("select * from user_givelike where uid = #{uid};")
    List<UserGivelike> selectUserGivelikeByUid(Integer uid);




    /**
     * 删除一个用户点赞关系表
     * @param userGivelike
     * @return
     */
    @Delete(" delete from user_givelike where uid = #{uid} and prid = #{prid};")
    int deleteUserGivelikeByUidAndPrid(UserGivelike userGivelike);


//    -----------------文字作品


    /**
     * 插入一个用户点赞文字的状态
     * @param userGivelike
     * @return
     */
    @Insert("INSERT INTO `bcy`.`user_givelike` (`ugid`, `uid`, `pcid`) " +
            "VALUES (default, #{uid},#{pcid});")
    int insertUserGivelikePcid(UserGivelike userGivelike);


    /**
     * 计算出当前有几个用户点赞
     * @param pcid
     * @return
     */
    @Select("select count(*) from user_givelike where pcid = #{pcid};")
    int countUserGivelikeByPcid(Integer pcid);

    /**
     * 删除一个用户点赞关系表
     * @param userGivelike
     * @return
     */
    @Delete(" delete from user_givelike where uid = #{uid} and pcid = #{pcid};")
    int deleteUserGivelikeByUidAndPcid(UserGivelike userGivelike);

}
