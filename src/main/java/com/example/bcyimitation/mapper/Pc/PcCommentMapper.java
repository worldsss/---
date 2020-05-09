package com.example.bcyimitation.mapper.Pro;

import com.example.bcyimitation.pojo.Pc.PcComment;
import com.example.bcyimitation.pojo.Pc.PcContent;
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
public interface PcCommentMapper {

    /**
     * 插入一条评论
     *没有带子评论功能的插入语句
     * @param pcComment
     * @return
     */
    @Insert("INSERT INTO `bcy`.`pc_comment` (`cid`, `uid`, `content`, `givelike`, `reply`, `time`) " +
            "VALUES (default, #{uid}, #{coContent}, '0', '0', #{coTime});")
    int insertPcComment(PcComment pcComment);

    /**
     * 插入一个回复内容
     * @param pcComment
     * @return
     */
    @Insert("INSERT INTO `bcy`.`pc_comment` (`cid`, `uid`, `content`, `givelike`, `reply`, `time`, `parent_cid`) " +
            "VALUES (default, #{uid}, #{coContent}, '0', '0', #{coTime},#{coParentCcid});")
    int insertPcCommentAndParentCid(PcComment pcComment);

    /**
     * 获得最新插入评论表中的cid是多少
     *
     * @return
     */
    @Select("select cid from pc_comment order by cid desc limit 1;")
    int selectNewstPcCommentCid();

    /**
     * 根据cid来查询当前的评论内容
     *
     * @param ccid
     * @return
     */
    @Select(" select * from pro_coms as pc,pc_comment as pt " +
            "where pc.cid = pt.ccid and pc.ccid = #{ccid};")
    PcComment selectPcCommentByCcid(Integer ccid);



    /**
     * 不多表联合查询的评论内容
     * @param ccid
     * @return
     */
    @Select(" select * from pc_comment where cid = #{ccid};")
    PcComment selectPcCommentByCidContent(Integer ccid);


    /**
     * 根据作品的prid来查询当前所有的评论内容
     *
     * @param pcid
     * @return
     */
    @Select("select * from pc_comment as pm,pro_coms as ps " +
            "where pm.ccid=ps.ccid and ps.pcid = #{pcid};")
    List<PcComment> selectPcCommentByPcid(Integer pcid);




    /**根据当前cid来查询有多少个子评论
     * @param ccid
     * @return
     */
    @Select("select * from pc_comment where co_parent_cid = #{ccid};")
    List<PcComment> selectCoParentCxid(Integer ccid);


    /**根据当前cid来查询有多少个子评论
     * @param ccid
     * @return
     */
    @Select("select * from pc_comment where parent_cid = #{ccid};")
    List<ProComIndex> selectPcComIndexParentCid(Integer ccid);

    /**
     * 根据当前作品的prid来算出当前有多少个评论
     * @param pcid
     * @return
     */
    @Select("select count(*) from pc_coms as pc,pc_comment as pt " +
            "where pc.ccid=pt.ccid and pc.pcid = #{pcid};")
    int countPcCommentByPcid(Integer pcid);

    /**
     * 根据自己的父类cid看还有多少个子评论
     * @param coParentCcid
     * @return
     */
    @Select("select count(*) from pc_comment where co_parent_cid = #{coParentCcid};")
    int countPcCommentByParentCid(Integer coParentCcid);

    /**
     * 改变评论区有几个的
     * @param pcContent
     * @return
     */
    @Update(" UPDATE `bcy`.`pc_comment` SET  `pc_givelike`=#{pcGivelike} WHERE (`cid`=#{ccid});")
    int updatePcCommentGiveLikeByPcid(PcContent pcContent);

    /**
     * 根据cid来查询出当前评论的点赞数量
     * @param ccid
     * @return
     */
    @Select("select co_givelike from pc_comment where ccid = #{ccid};")
    int selectPcCommentGiveLikeByCcid(Integer ccid);



    /**
     * 根据时间来排序某个用户的评论
     * @param prid
     * @return
     */
    @Select("select * from pc_comment as pt,pro_content as pr,pro_coms as ps " +
            "where pt.cid=ps.cid and ps.prid=pr.prid and pr.prid = #{pcid} order by time desc;")
    List<ProComIndex> selectPcComIndexOrderByTime(Integer pcid);




}
