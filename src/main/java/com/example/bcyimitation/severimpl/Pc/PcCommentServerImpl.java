package com.example.bcyimitation.severimpl.Pc;

import com.example.bcyimitation.mapper.Pro.PcCommentMapper;
import com.example.bcyimitation.pojo.Pc.PcComment;
import com.example.bcyimitation.pojo.Pc.PcContent;
import com.example.bcyimitation.server.Pc.PcCommentServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PcCommentServerImpl implements PcCommentServer {

    @Resource
    private PcCommentMapper pcCommentMapper;



    @Override
    public int insertPcComment(Integer uid, String coContent) {
        //获取当前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String newDataa = simpleDateFormat.format(date);

        PcComment proComment = new PcComment();
        proComment.setCoContent(coContent);
        proComment.setUid(uid);
        proComment.setCoTime(newDataa);

        //插入一个文字作品评论
        return pcCommentMapper.insertPcComment(proComment);
    }

    @Override
    public int insertPcCommentAndParentCid(Integer uid, String coContent, Integer coParentCcid) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String newDataa = simpleDateFormat.format(date);

        PcComment pcComment = new PcComment();
        pcComment.setCoTime(newDataa);
        pcComment.setCoContent(coContent);
        pcComment.setUid(uid);
        //插入一个回复作品内容
        return pcCommentMapper.insertPcCommentAndParentCid(pcComment);
    }

    @Override
    public int countPcCommentByPrid(Integer pcid) {

        //获取所有的当前所有的pro_comment

        List<PcComment> pcComments = pcCommentMapper.selectPcCommentByPcid(pcid);

        int i = 0;
        for (PcComment pcComment:pcComments) {
            //根据当前所有的pro_comment的子评论来计算出当前pro_comment有几个子评论
            i += pcCommentMapper.countPcCommentByParentCid(pcComment.getCcid());
            System.out.println("这里的i的值是多少来着？"+i);
        }

        int i1 = pcCommentMapper.countPcCommentByPcid(pcid);

        int allCount = i + i1;

        PcContent pcContent = new PcContent();
        pcContent.setPcid(pcid);
        pcContent.setPcComment(allCount);

        //修改当前作品的个数
        return pcCommentMapper.updatePcCommentGiveLikeByPcid(pcContent);

    }

    @Override
    public int updatePcComment(Integer ccid) {

        PcContent pcContent = new PcContent();
        //计算出当前文字作品的评论有多少点赞
        int i = pcCommentMapper.selectPcCommentGiveLikeByCcid(ccid);
        i++;
        pcContent.setPcGivelike(i);
        pcContent.setCid(ccid);
        //修改评论点赞
        return pcCommentMapper.updatePcCommentGiveLikeByPcid(pcContent);
    }
}
