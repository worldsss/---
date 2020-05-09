package com.example.bcyimitation.severimpl.Pro;

import com.example.bcyimitation.mapper.Pro.ProCommentMapper;
import com.example.bcyimitation.mapper.Pro.ProComsMapper;
import com.example.bcyimitation.mapper.Pro.ProContentMapper;
import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.server.Pro.ProCommentServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProCommentServerImpl implements ProCommentServer {

    @Resource
    private ProCommentMapper proCommentMapper;

    @Resource
    private ProComsMapper proComsMapper;

    @Resource
    private ProContentMapper proContentMapper;

    /**
     * 插入一条评论
     * @param uid
     * @param cContent
     * @return
     */
    @Override
    public int insertProComment(Integer uid, String cContent) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String newDataa = simpleDateFormat.format(date);

        ProComment proComment = new ProComment();
        proComment.setContent(cContent);
        proComment.setUid(uid);
        proComment.setTime(newDataa);

        return proCommentMapper.insertProComment(proComment);
    }

    /**
     * 插入一条回复评论
     * @param uid
     * @param cContent
     * @param parentId
     * @return
     */
    @Override
    public int insertProCommentAndParentCid(Integer uid, String cContent, Integer parentId) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String newDataa = simpleDateFormat.format(date);

        ProComment proComment = new ProComment();
        proComment.setContent(cContent);
        proComment.setUid(uid);
        proComment.setTime(newDataa);
        proComment.setParent_cid(parentId);


        return proCommentMapper.insertProCommentAndParentCid(proComment);
    }

    @Override
    public int countProCommentByPrid(Integer prid) {

        //获取所有的当前所有的pro_comment
        List<ProComment> proCommentList = proComsMapper.selectProCommentByPrid(prid);

        int i = 0;
        for (ProComment proComment:proCommentList) {
            //根据当前所有的pro_comment的子评论来计算出当前pro_comment有几个子评论
            i += proCommentMapper.countProCommentByParentCid(proComment.getCid());
            System.out.println("这里的i的值是多少来着？"+i);
        }

        int i1 = proCommentMapper.countProCommentByPrid(prid);

        int allCount = i + i1;

        ProContent proContent = new ProContent();
        proContent.setPrid(prid);
        proContent.setPrCommentCount(allCount);

        //修改当前作品的个数
        return proContentMapper.updateProContentPrCommentCountByPrid(proContent);
    }


    /**
     * 当前评论的点赞+1
     * @param
     * @param cid
     * @return
     */
    @Override
    public int updateProComment(Integer cid) {
        ProComment proComment = new ProComment();
        //获取当前评论的所有点赞
        int i = proCommentMapper.selectProCommentGiveLikeByCid(cid);
        i++;
        proComment.setGivelike(i);
        proComment.setCid(cid);

        return proCommentMapper.updateProCommentGiveLikeByPrid(proComment);
    }
}
