package com.example.bcyimitation.controller.Pro;

import com.example.bcyimitation.pojo.Pro.ProComIndex;
import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.server.Pro.ProCommentServer;
import com.example.bcyimitation.server.Pro.ProComsServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProCommentController {

    @Resource
    private ProCommentServer proCommentServer;

    @Resource
    private ProComsServer proComsServer;

    /**
     * 插入一条评论内容
     * @param proComment
     * @return
     */
    @PostMapping("/insertProComment")
    public int insertProComment(@RequestBody ProComment proComment){

        return proCommentServer.insertProComment(proComment.getUid(),proComment.getContent());
    }

    /**
     * 获取全部的用户评论信息
     * @param prid
     * @return
     */
    @GetMapping("/getProComIndexByPrid")
    public List<ProComIndex> selectAllProComIndexByPrid(Integer prid){
        return proComsServer.selectAllProComIndexByPrid(prid);
    }

    /**
     * 插入一条回复
     * @param proComment
     * @return
     */
    @PostMapping("/insertProComParentCid")
    public int insertProCommentAndParentCid(@RequestBody ProComment proComment){
        return proCommentServer.insertProCommentAndParentCid(proComment.getUid(),proComment.getContent(),proComment.getParent_cid());
    }

    /**
     * 修改作品表中的评论个数
     * @param prid
     * @return
     */
    @GetMapping("/updateProContetnCommentCount")
    public int updateProContentPrCommentCountByPrid(Integer prid){
        return proCommentServer.countProCommentByPrid(prid);
    }

    /**
     * 给当前评论内容的点赞+1
     * @param cid
     * @return
     */
    @GetMapping("/updateProCommentGiveLike")
    public int updateProCommentGiveLikeByCid(Integer cid){
        return proCommentServer.updateProComment(cid);
    }


    /**
     * 获取全部的用户评论信息,根据时间排序
     * @param prid
     * @return
     */
    @GetMapping("/getProComIndexByPridOrderByTime")
    public List<ProComIndex> selectAllProComIndexByPridOrderByTime(Integer prid){
        return proComsServer.selectAllProComIndexByPridOrderByTime(prid);
    }



    /**
     * 获取全部的用户评论信息,根据热度排序
     * @param prid
     * @return
     */
    @GetMapping("/getProComIndexByPridOrderByGivelike")
    public List<ProComIndex> selectAllProComIndexByPridOrderByGivelike(Integer prid){
        return proComsServer.selectAllProComIndexByPridOrderByGivelike(prid);
    }

}
