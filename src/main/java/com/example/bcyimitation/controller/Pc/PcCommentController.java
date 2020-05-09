package com.example.bcyimitation.controller.Pc;

import com.example.bcyimitation.pojo.Pc.PcComIndex;
import com.example.bcyimitation.pojo.Pc.PcComment;
import com.example.bcyimitation.pojo.Pc.PcContent;
import com.example.bcyimitation.pojo.Pro.ProComIndex;
import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.server.Pc.PcCommentServer;
import com.example.bcyimitation.server.Pc.PcComsServer;
import com.example.bcyimitation.server.Pc.PcContentServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PcCommentController {

    @Resource
    private PcCommentServer pcCommentServer;

    @Resource
    private PcContentServer pcContentServer;

    @Resource
    private PcComsServer pcComsServer;


    /**
     * 插入一条评论内容
     * @param pcComment
     * @return
     */
    @PostMapping("/insertPcComment")
    public int insertPcComment(@RequestBody PcComment pcComment){

        return pcCommentServer.insertPcComment(pcComment.getUid(),pcComment.getCoContent());
    }

    /**
     * 获取全部的用户评论信息
     * @param prid
     * @return
     */
  /*  @GetMapping("/getProComIndexByPrid")
    public List<ProComIndex> selectAllProComIndexByPrid(Integer prid){
        return pcCommentServer.
    }*/

    /**
     * 插入一条回复
     * @param pcComment
     * @return
     */
    @PostMapping("/insertPcComParentCid")
    public int insertPcCommentAndParentCid(@RequestBody PcComment pcComment){

        return pcCommentServer.insertPcCommentAndParentCid(pcComment.getUid(),
                pcComment.getCoContent(),pcComment.getCoParentCcid());
    }

    /**
     * 修改作品表中的评论个数
     * @param pcid
     * @return
     */
    @GetMapping("/updatePcContetnCommentCount")
    public int updatePcContentPcCommentCountByPcid(Integer pcid){
        return pcCommentServer.countPcCommentByPrid(pcid);
    }

    /**
     * 给当前评论内容的点赞+1
     * @param ccid
     * @return
     */
    @GetMapping("/updatePcCommentGiveLike")
    public int updatePcCommentGiveLikeByCcid(Integer ccid){
        return pcCommentServer.updatePcComment(ccid);

    }


    /**
     * 获取全部的用户评论信息,根据时间排序
     * @param pcid
     * @return
     */
    @GetMapping("/getPcComIndexByPcidOrderByCoTime")
    public List<PcComIndex> selectAllProComIndexByPridOrderByTime(Integer pcid){
        return pcComsServer.selectAllPcComIndexByPcidOrderByCoTime(pcid);
    }



    /**
     * 获取全部的用户评论信息,根据热度排序
     * @param pcid
     * @return
     */
    @GetMapping("/getPcComIndexByPcidOrderByCoGivelike")
    public List<PcComIndex> selectAllProComIndexByPridOrderByGivelike(Integer pcid){
        return pcComsServer.selectAllPcComIndexByPcidOrderByCoGivelike(pcid);
    }

}
