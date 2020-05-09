package com.example.bcyimitation.mapper.Pro;

import com.example.bcyimitation.pojo.Pro.ProComIndex;
import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.pojo.Pro.ProComs;
import com.example.bcyimitation.severimpl.Pro.ProComsServerImpl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ProCommentMapper {

    /**
     * 插入一条评论
     *
     * @param proComment
     * @return
     */

   /* @Insert("INSERT INTO `bcy`.`pro_comment` (`cid`, `uid`, `content`, `givelike`, `reply`, `time`)" +
            " VALUES (default,#{uid},#{content}, '0', '0',#{time});")*/
    //没有带子评论功能的插入语句
    @Insert("INSERT INTO `bcy`.`pro_comment` (`cid`, `uid`, `content`, `givelike`, `reply`, `time`) " +
            "VALUES (default, #{uid}, #{content}, '0', '0', #{time});")
    int insertProComment(ProComment proComment);

    /**
     * 插入一个回复内容
     * @param proComment
     * @return
     */
    @Insert("INSERT INTO `bcy`.`pro_comment` (`cid`, `uid`, `content`, `givelike`, `reply`, `time`, `parent_cid`) " +
            "VALUES (default, #{uid}, #{content}, '0', '0', #{time},#{parent_cid});")
    int insertProCommentAndParentCid(ProComment proComment);

    /**
     * 获得最新插入评论表中的cid是多少
     *
     * @return
     */
    @Select("select cid from pro_comment order by cid desc limit 1;")
    int selectNewstProCommentCid();

    /**
     * 根据cid来查询当前的评论内容
     *
     * @param cid
     * @return
     */
    @Select(" select * from pro_coms as pc,pro_comment as pt " +
            "where pc.cid = pt.cid and pc.cid = #{cid};")
    ProComment selectProCommentByCid(Integer cid);

    /*@Select("")
    ProComment selectProCommentByCidCon(Integer cid);*/

    /**
     * 不多表联合查询的评论内容
     * @param cid
     * @return
     */
    @Select(" select * from pro_comment where cid = #{cid};")
    ProComment selectProCommentByCidContent(Integer cid);


    /**
     * 根据作品的prid来查询当前所有的评论内容
     *
     * @param prid
     * @return
     */
    @Select("select * from pro_comment as pm,pro_coms as ps " +
            "where pm.cid=ps.cid and ps.prid = #{prid};")
    List<ProComment> selectProCommentByPrid(Integer prid);




    /**根据当前cid来查询有多少个子评论
     * @param cid
     * @return
     */
    @Select("select * from pro_comment where parent_cid = #{cid};")
    List<ProComment> selectParentCid(Integer cid);


    /**根据当前cid来查询有多少个子评论
     * @param cid
     * @return
     */
    @Select("select * from pro_comment where parent_cid = #{cid};")
    List<ProComIndex> selectProComIndexParentCid(Integer cid);

    /**
     * 根据当前作品的prid来算出当前有多少个评论
     * @param prid
     * @return
     */
    @Select("select count(*) from pro_coms as pc,pro_comment as pt " +
            "where pc.cid=pt.cid and pc.prid = #{prid};")
    int countProCommentByPrid(Integer prid);

    /**
     * 根据自己的父类cid看还有多少个子评论
     * @param parentCid
     * @return
     */
    @Select("select count(*) from pro_comment where parent_cid = #{parentCid};")
    int countProCommentByParentCid(Integer parentCid);

    /**
     * 改变评论区有几个回复的
     * @param proComment
     * @return
     */
    @Update(" UPDATE `bcy`.`pro_comment` SET  `givelike`=#{givelike} WHERE (`cid`=#{cid});")
    int updateProCommentGiveLikeByPrid(ProComment proComment);

    /**
     * 根据cid来查询出当前评论的点赞数量
     * @param cid
     * @return
     */
    @Select("select givelike from pro_comment where cid = #{cid};")
    int selectProCommentGiveLikeByCid(Integer cid);



    /**
     * 根据时间来排序某个用户的评论
     * @param prid
     * @return
     */
    @Select("select * from pro_comment as pt,pro_content as pr,pro_coms as ps " +
            "where pt.cid=ps.cid and ps.prid=pr.prid and pr.prid = #{prid} order by time desc;")
    List<ProComIndex> selectProComIndexOrderByTime(Integer prid);




}
