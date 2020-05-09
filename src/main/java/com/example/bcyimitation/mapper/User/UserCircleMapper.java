package com.example.bcyimitation.mapper.User;

import com.example.bcyimitation.pojo.User.UserCircle;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCircleMapper {

    /**
     * 插入一条用户-圈子关注表
     * @param userCircle
     * @return
     */
    @Insert(" INSERT INTO `bcy`.`user_circle` (`uaid`, `uid`, `tid`) VALUES (default, #{uid},#{tid});")
    int insertUserCirCle(UserCircle userCircle);

//    @Select("")
//    List<UserCircle> judgeUserCircleRepetition();


    /**
     * 判断当前用户是否关注此圈子了
     * @param userCircle
     * @return
     */
    @Select("select count(*) from user_circle where uid=#{uid} and tid=#{tid};")
    int countUserCircleByTidAndUid(UserCircle userCircle);

    /**
     * 取消用户-圈子的关注
     * @param userCircle
     * @return
     */
    @Delete("delete from user_circle where uid = #{uid} and tid = #{tid};")
    int deleteUserCircleByTidAndUid(UserCircle userCircle);

    /**
     * 查询出所有的tid
     * @return
     */
    @Select("select tid from user_circle;")
    List<UserCircle> selectAllTidByUserCircle();

    /**
     * 看有多少人关注圈子
     * @param tid
     * @return
     */
    @Select(" select count(*) from user_circle where tid = #{tid};")
    int countTidByTid(Integer tid);

    /**
     * 当前圈子的所有作品数量有多少，根据这个来判断圈子的火热
     * @return
     */
    @Select(" select count(*) from user_circle as uc,pro_content as pr,pro_tags as pt " +
            "where uc.tid=pt.tid and pr.prid=pt.prid and uc.tid = #{tid};")
    int countAllProContentByTid(Integer tid);


}
