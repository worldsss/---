package com.example.bcyimitation.mapper.User;

import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.pojo.Tags;
import com.example.bcyimitation.pojo.User.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

  @Insert(
      "INSERT INTO `bcy`.`user` "
          + "(`uid`, `user_name`, `user_password`, `user_avatar`)"
          + " VALUES (default, #{userName},#{userPassword},#{userAvatar});")
  int insertUser(User user);


  @Select("select * from user")
  List<User> selectAllUserForLogin();

  /**
   * 根据uid来查找用户信息
   * @param uid
   * @return
   */
  @Select("select * from user where uid=#{uid};")
  User selectUserByUid(Integer uid);

  /**
   * 根据uid来查找用户信息
   * @param uid
   * @return
   */
  @Select("select * from user where uid=#{uid} limit 5;")
  User selectUserByUidLimitFive(Integer uid);

  /**
   * 根据用户名和密码来获取用户的Uid
   * @param user
   * @return
   */
  @Select("select uid from user where user_name=#{userName} and user_password=#{userPassword};")
  int searchUidByUserName(User user);

  /**
   * 根据uid来找用户的头像
   * @param uid
   * @return
   */
  @Select("select user_avatar from user where uid=#{uid};")
  String searchUserAvatarByUid(int uid);

  /**
   * 判断用户名称是否重复
   * @param userName
   * @return
   */
  @Select("select count(*) from user where user_name=#{userName};")
  int judgeUserNameRepetition(String userName);

  /**
   * 根据prid来找到对应的用户
   * @param prid
   * @return
   */
  @Select("select u.uid,user_name,user_avatar,user_info,user_attention,user_fans" +
          " from pro_content as pr,user as u where pr.uid=u.uid and pr.prid=#{prid};")
  User searchUserForPrid(int prid);

  /**
   * 根据uid来找对应的作品，图片作品
   * @param uid
   * @return
   */
  @Select(" select * from pro_content as pr,user as u where pr.uid=u.uid and u.uid=#{uid};")
  List<ProContent> selectProContentByUid(int uid);

  /**
   * 根据uid来找对应的作品，图片作品
   * @param uid
   * @return
   */
  @Select(" select count(*) from pro_content as pr,user as u " +
          "where pr.uid=u.uid and u.uid=#{uid};")
  int ProIndexCountByUid(int uid);

  /**
   * 计算出某个用户的粉丝有多少个
   * @param uid
   * @return
   */
  @Select("select count(fid) from user as u,user_fans as uf " +
          "where u.uid=uf.uid and u.uid=#{uid};")
  int countUserFansByUid(int uid);

  /**
   * 计算出当前用户的关注用户个数有多少
   * @param fid
   * @return
   */
  @Select("select count(uf.uid) from user as u,user_fans as uf " +
          "where u.uid=uf.uid and fid=#{fid};")
  int countUserAttentionByFid(int fid);

  /**
   * 根据当前用户的关注来更新user_attention中的值
   * @param user
   * @return
   */
  @Update("update user set user_attention = #{userAttention} where uid = #{uid};")
  int updateUserAttention(User user);

  /**
   * 根据当前用户的粉丝来更新user_fans中的值
   * @param user
   * @return
   */
  @Update("update user set user_fans = #{userFans} where uid = #{uid}")
  int updateUserFans(User user);

  /**
   * 根据uid查询出当前的关注数
   * @param uid
   * @return
   */
  @Select("select user_attention from user where uid=#{uid}")
  int selectUserAttentionByUid(Integer uid);

  /**
   * 根据uid查询出当前的粉丝数
   * @param uid
   * @return
   */
  @Select("select user_fans from user where uid=#{uid}")
  int selectUserFansByUid(Integer uid);

  /**
   * 根据uid来查询出来当前用户关注的圈子
   * @param uid
   * @return
   */
 /* @Select("select t.tid,tags_name,t.tags_recom,t.tags_hot from user_circle as uc,user as u,tags as t " +
          "where uc.uid=u.uid and uc.tid=t.tid and uc.uid=#{uid};")*/
 /* @Select("select t.tid,tags_name,t.tags_recom,t.tags_hot,t.tags_latest_time from user_circle as uc,user as u,tags as t " +
          "where uc.uid=u.uid and uc.tid=t.tid and uc.uid=#{uid};")*/
  @Select("select * from user_circle as uc,user as u,tags as t " +
          "where uc.uid=u.uid and uc.tid=t.tid and uc.uid=#{uid};")
  List<Tags> selectTagsNameByUid(Integer uid);

  /**
   * 根据uid来修改用户的头像
   * @param user
   * @return
   */
  @Update("update user set user_avatar = #{userAvatar} where uid = #{uid};")
  int updateUserAvatarByUid(User user);

  /**
   * 修改用户的名称，个性签名，地区，性别
   * @param user
   * @return
   */
  @Update("UPDATE `bcy`.`user` SET `user_name`=#{userName} ," +
          "`user_info`=#{userInfo}, `user_area`=#{userArea}, " +
          "`user_sex`=#{userSex} WHERE (`uid`=#{uid});")
  int updateUserInfoByUid(User user);




}
