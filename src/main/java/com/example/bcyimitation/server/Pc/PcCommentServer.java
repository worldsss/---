package com.example.bcyimitation.server.Pc;

import com.example.bcyimitation.pojo.Pro.ProComment;
import org.apache.ibatis.annotations.Insert;

public interface PcCommentServer {

    /**
     * 插入一条评论
     * @param uid
     * @param coContent
     * @return
     */
    int insertPcComment(Integer uid,String coContent);

    /**
     * 回复一条评论的评论
     * @param uid
     * @param coContent
     * @param coParentCcid
     * @return
     */
    int insertPcCommentAndParentCid(Integer uid,String coContent,Integer coParentCcid);

    /**
     * 根据作品prid来算出来当前作品有多少个评论
     * @param pcid
     * @return
     */
    int countPcCommentByPrid(Integer pcid);

    /**
     * 对当前评论点赞+1
     * @param
     * @return
     */
    int updatePcComment(Integer ccid);



}
