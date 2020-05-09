package com.example.bcyimitation.server.Pc;

import com.example.bcyimitation.pojo.Pc.PcComIndex;
import com.example.bcyimitation.pojo.Pc.PcComment;
import com.example.bcyimitation.pojo.Pro.ProComIndex;
import com.example.bcyimitation.pojo.Pro.ProComment;

import java.util.List;

public interface PcComsServer {


    /**
     * 插入一条评论-作品关系表
     * @param prid
     * @return
     */
    int insertPcComs(Integer pcid);

    /**
     * 查询出当前作品的所有评论内容
     * @param prid
     * @return
     */
    List<PcComment> selectAllPcCommentByPcid(Integer pcid);

    /**
     * 根据作品id来查询评论内容，联合查询版
     * @param prid
     * @return
     */
    List<PcComIndex> selectAllPcComIndexByPcid(Integer pcid);

    /**
     * 根据作品id来根据时间来进行评论内容的查询
     * @param prid
     * @return
     */
    List<PcComIndex> selectAllPcComIndexByPcidOrderByCoTime(Integer pcid);

    /**
     * 根据作品id来根据点赞来进行评论内容的查询
     * @param prid
     * @return
     */
    List<PcComIndex> selectAllPcComIndexByPcidOrderByCoGivelike(Integer pcid);

}
