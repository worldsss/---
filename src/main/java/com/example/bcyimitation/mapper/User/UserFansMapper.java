package com.example.bcyimitation.mapper.User;

import com.example.bcyimitation.pojo.User.UserFans;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserFansMapper {

    /**
     * 给用户插入一个粉丝
     * @param userFans
     * @return
     */
    @Insert("INSERT INTO `bcy`.`user_fans` (`faid`, `fid`, `uid`) VALUES (default,#{fid},#{uid});")
    int insertUserFansByUid(UserFans userFans);

    /**
     * 根据当前登录的用户来查询访问的用户是否是已经关注的了
     * @return
     */
    @Select("select faid,fid from user_fans as uf,user as u where uf.uid=u.uid and u.uid=#{uid};")
    List<UserFans> selectUserFansByUid(int uid);


    /**
     * 根据当前登录的用户来查询访问的用户是否是已经关注的了
     * @return
     */
    @Select("select faid,fid,uf.uid from user_fans as uf,user as u where uf.uid=u.uid and uf.fid=#{fid};")
    List<UserFans> selectUserFansByFid(Integer fid);

    /**
     * 查找出当前用户的关注者的所有uid
     * @param fid
     * @return
     */
    @Select("select * from user as u,user_fans as uf where u.uid=uf.uid and uf.fid=#{fid};")
    List<UserFans> selectAllUserFansByFid(Integer fid);



}
