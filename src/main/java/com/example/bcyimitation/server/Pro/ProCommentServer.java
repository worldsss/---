package com.example.bcyimitation.server.Pro;

import com.example.bcyimitation.pojo.Pro.ProComment;
import org.apache.ibatis.annotations.Insert;

public interface ProCommentServer {

    /**
     * 插入一条评论
     * @param uid
     * @param cContent
     * @return
     */
    int insertProComment(Integer uid,String cContent);

    /**
     * 回复一条评论的评论
     * @param uid
     * @param cContent
     * @param parentId
     * @return
     */
    int insertProCommentAndParentCid(Integer uid,String cContent,Integer parentId);

    /**
     * 根据作品prid来算出来当前作品有多少个评论
     * @param prid
     * @return
     */
    int countProCommentByPrid(Integer prid);

    /**
     * 对当前评论点赞+1
     * @param
     * @return
     */
    int updateProComment(Integer cid);



}
