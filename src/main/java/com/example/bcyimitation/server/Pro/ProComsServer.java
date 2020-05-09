package com.example.bcyimitation.server.Pro;

import com.example.bcyimitation.pojo.Pro.ProComIndex;
import com.example.bcyimitation.pojo.Pro.ProComment;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface ProComsServer {

    /**
     * 插入一条评论-作品关系表
     * @param prid
     * @return
     */
    int insertProComs(Integer prid);

    /**
     * 查询出当前作品的所有评论内容
     * @param prid
     * @return
     */
    List<ProComment> selectAllProCommentByPrid(Integer prid);

    /**
     * 根据作品id来查询评论内容，联合查询版
     * @param prid
     * @return
     */
    List<ProComIndex> selectAllProComIndexByPrid(Integer prid);

    /**
     * 根据作品id来根据时间来进行评论内容的查询
     * @param prid
     * @return
     */
    List<ProComIndex> selectAllProComIndexByPridOrderByTime(Integer prid);

    /**
     * 根据作品id来根据点赞来进行评论内容的查询
     * @param prid
     * @return
     */
    List<ProComIndex> selectAllProComIndexByPridOrderByGivelike(Integer prid);

}
