package com.example.bcyimitation.severimpl.Pc;

import com.example.bcyimitation.mapper.Pc.PcComsMapper;
import com.example.bcyimitation.mapper.Pro.PcCommentMapper;
import com.example.bcyimitation.mapper.User.UserMapper;
import com.example.bcyimitation.mapper.User.UserTagMapper;
import com.example.bcyimitation.mapper.User.UserTagRelationMapper;
import com.example.bcyimitation.pojo.Pc.PcComIndex;
import com.example.bcyimitation.pojo.Pc.PcComment;
import com.example.bcyimitation.pojo.Pc.PcComs;
import com.example.bcyimitation.pojo.Pro.ProComIndex;
import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.pojo.Pro.ProComs;
import com.example.bcyimitation.pojo.User.User;
import com.example.bcyimitation.pojo.User.UserTag;
import com.example.bcyimitation.pojo.User.UserTagRelation;
import com.example.bcyimitation.server.Pc.PcComsServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.UserTransactionAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PcComsServerImpl implements PcComsServer {


    @Resource
    private UserMapper userMapper;

    @Resource
    private UserTagMapper userTagMapper;

    @Resource
    private UserTagRelationMapper userTagRelationMapper;

    @Resource
    private PcCommentMapper pcCommentMapper;

    @Resource
    private PcComsMapper pcComsMapper;


    @Override
    public int insertPcComs(Integer pcid) {

        PcComs pcComs = new PcComs();
        pcComs.setPcid(pcid);
        pcComs.setCcid(pcCommentMapper.selectNewstPcCommentCid());
        return pcComsMapper.insertPcComs(pcComs);
    }

    @Override
    public List<PcComment> selectAllPcCommentByPcid(Integer pcid) {

        List<PcComment> pcComments = pcComsMapper.selectAllCcidByPcid(pcid);

        List<PcComment> pcCommentList = new ArrayList<>();
        for (PcComment pcComment : pcComments) {
            pcCommentList.add(pcCommentMapper.selectPcCommentByCcid(pcComment.getCcid()));
        }


        return pcCommentList;
    }

    @Override
    public List<PcComIndex> selectAllPcComIndexByPcid(Integer pcid) {

        //最终要传的值

        List<PcComIndex> pcComIndexList = new ArrayList<>();

        List<PcComment> pcComments = pcComsMapper.selectPcCommentByPcid(pcid);

        for (PcComment pcComment : pcComments) {


            PcComIndex pcComIndex = new PcComIndex();

            PcComment pcComment2 = pcCommentMapper.selectPcCommentByCcid(pcComment.getCcid());

            //根据pro_comment中的uid来获取到对应的用户对象
            User user = userMapper.selectUserByUid(pcComment2.getUid());

            //分别把用户中的值赋值给proComIndex中
            pcComIndex.setUid(user.getUid());
            pcComIndex.setUserName(user.getUserName());
            pcComIndex.setUserAvatar(user.getUserAvatar());

            //获取当前用户的全部关联信息
            List<UserTagRelation> userTagRelations =
                    userTagRelationMapper.selectUserTagRelationByUid(user.getUid());


            List<UserTag> userTags = new ArrayList<>();
            for (UserTagRelation userTagRelation : userTagRelations) {
                //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                userTags.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
            }

            //把获得到的用户标签赋值到procomindex中
            pcComIndex.setUserTags(userTags);
            PcComment pcComment1 = pcCommentMapper.selectPcCommentByCcid(pcComment.getCcid());
            pcComIndex.setCoContent(pcComment1.getCoContent());
            pcComIndex.setCcid(pcComment1.getCcid());
            pcComIndex.setCoGivelike(pcComment1.getCoGivelike());
            pcComIndex.setCoTime(pcComment1.getCoTime());
            pcComIndex.setCoReply(pcComment1.getCoReply());


            //----------------------------------------------------------------------

            //上面是父类的值，接下来是子类的评论
            List<PcComIndex> pcComIndexList2 = new ArrayList<>();
            int nowCcid = pcComIndex.getCcid();

            List<PcComment> pcCommentList = pcCommentMapper.selectCoParentCxid(nowCcid);

            if(!pcCommentList.isEmpty()){
                for (PcComment pcComment3:pcCommentList) {

                    PcComIndex pcComIndex2 = new PcComIndex();
                    int getCcid = pcComment3.getCcid();

                    PcComment pcComment4 = pcCommentMapper.selectPcCommentByCidContent(getCcid);

                    User user2 = userMapper.selectUserByUid(pcComment4.getUid());
                    pcComIndex2.setUid(user.getUid());
                    pcComIndex2.setUserName(user.getUserName());
                    pcComIndex2.setUserAvatar(user.getUserAvatar());

                    //获取当前用户的全部关联信息
                    List<UserTagRelation> userTagRelations1 =
                            userTagRelationMapper.selectUserTagRelationByUid(user2.getUid());
                    List<UserTag> userTags1 = new ArrayList<>();
                    //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                    for (UserTagRelation userTagRelation:userTagRelations1) {
                        userTags1.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
                    }
                    pcComIndex2.setUserTags(userTags1);
                    //把获得到的用户标签赋值到procomindex中
                    PcComment pcComment5 =
                            pcCommentMapper.selectPcCommentByCidContent(pcComment3.getCcid());
                    pcComIndex2.setCoContent(pcComment5.getCoContent());
                    pcComIndex2.setCcid(pcComment5.getCcid());
                    pcComIndex2.setCoGivelike(pcComment5.getCoGivelike());
                    pcComIndex2.setCoTime(pcComment5.getCoTime());
                    pcComIndex2.setCoReply(pcComment5.getCoReply());

                    pcComIndexList2.add(pcComIndex2);

                }
            }


            //上面的子类全部添加完成后，再把所有子类添加到list
            pcComIndex.setUserParent(pcComIndexList2);


            //最终把所有内容添加进去后，在加入到list中
            pcComIndexList.add(pcComIndex);

        }


        return pcComIndexList;
    }

    @Override
    public List<PcComIndex> selectAllPcComIndexByPcidOrderByCoTime(Integer pcid) {
        //最终要传的值

        List<PcComIndex> pcComIndexList = new ArrayList<>();

        List<PcComment> pcComments = pcComsMapper.selectPcCommentByPcid(pcid);

        for (PcComment pcComment : pcComments) {


            PcComIndex pcComIndex = new PcComIndex();

            PcComment pcComment2 = pcCommentMapper.selectPcCommentByCcid(pcComment.getCcid());

            //根据pro_comment中的uid来获取到对应的用户对象
            User user = userMapper.selectUserByUid(pcComment2.getUid());

            //分别把用户中的值赋值给proComIndex中
            pcComIndex.setUid(user.getUid());
            pcComIndex.setUserName(user.getUserName());
            pcComIndex.setUserAvatar(user.getUserAvatar());

            //获取当前用户的全部关联信息
            List<UserTagRelation> userTagRelations =
                    userTagRelationMapper.selectUserTagRelationByUid(user.getUid());


            List<UserTag> userTags = new ArrayList<>();
            for (UserTagRelation userTagRelation : userTagRelations) {
                //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                userTags.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
            }

            //把获得到的用户标签赋值到procomindex中
            pcComIndex.setUserTags(userTags);
            PcComment pcComment1 = pcCommentMapper.selectPcCommentByCcid(pcComment.getCcid());
            pcComIndex.setCoContent(pcComment1.getCoContent());
            pcComIndex.setCcid(pcComment1.getCcid());
            pcComIndex.setCoGivelike(pcComment1.getCoGivelike());
            pcComIndex.setCoTime(pcComment1.getCoTime());
            pcComIndex.setCoReply(pcComment1.getCoReply());


            //----------------------------------------------------------------------

            //上面是父类的值，接下来是子类的评论
            List<PcComIndex> pcComIndexList2 = new ArrayList<>();
            int nowCcid = pcComIndex.getCcid();

            List<PcComment> pcCommentList = pcCommentMapper.selectCoParentCxid(nowCcid);

            if(!pcCommentList.isEmpty()){
                for (PcComment pcComment3:pcCommentList) {

                    PcComIndex pcComIndex2 = new PcComIndex();
                    int getCcid = pcComment3.getCcid();

                    PcComment pcComment4 = pcCommentMapper.selectPcCommentByCidContent(getCcid);

                    User user2 = userMapper.selectUserByUid(pcComment4.getUid());
                    pcComIndex2.setUid(user.getUid());
                    pcComIndex2.setUserName(user.getUserName());
                    pcComIndex2.setUserAvatar(user.getUserAvatar());

                    //获取当前用户的全部关联信息
                    List<UserTagRelation> userTagRelations1 =
                            userTagRelationMapper.selectUserTagRelationByUid(user2.getUid());
                    List<UserTag> userTags1 = new ArrayList<>();
                    //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                    for (UserTagRelation userTagRelation:userTagRelations1) {
                        userTags1.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
                    }
                    pcComIndex2.setUserTags(userTags1);
                    //把获得到的用户标签赋值到procomindex中
                    PcComment pcComment5 =
                            pcCommentMapper.selectPcCommentByCidContent(pcComment3.getCcid());
                    pcComIndex2.setCoContent(pcComment5.getCoContent());
                    pcComIndex2.setCcid(pcComment5.getCcid());
                    pcComIndex2.setCoGivelike(pcComment5.getCoGivelike());
                    pcComIndex2.setCoTime(pcComment5.getCoTime());
                    pcComIndex2.setCoReply(pcComment5.getCoReply());

                    pcComIndexList2.add(pcComIndex2);

                }
            }


            //上面的子类全部添加完成后，再把所有子类添加到list
            pcComIndex.setUserParent(pcComIndexList2);


            //最终把所有内容添加进去后，在加入到list中
            pcComIndexList.add(pcComIndex);

        }


        Collections.sort(pcComIndexList, new Comparator<PcComIndex>() {
            @Override
            public int compare(PcComIndex o1, PcComIndex o2) {
                return o1.getCoTime().compareTo(o2.getCoTime());
            }
        });
        Collections.reverse(pcComIndexList);
        System.out.println("这里是最终要返回给主页的评论内容"+pcComIndexList);
        for (PcComIndex proComIndex:pcComIndexList) {
            System.out.println(proComIndex);
        }


        return pcComIndexList;

    }

    @Override
    public List<PcComIndex> selectAllPcComIndexByPcidOrderByCoGivelike(Integer pcid) {

        List<PcComIndex> pcComIndexList = new ArrayList<>();

        List<PcComment> pcComments = pcComsMapper.selectPcCommentByPcid(pcid);

        for (PcComment pcComment : pcComments) {


            PcComIndex pcComIndex = new PcComIndex();

            PcComment pcComment2 = pcCommentMapper.selectPcCommentByCcid(pcComment.getCcid());

            //根据pro_comment中的uid来获取到对应的用户对象
            User user = userMapper.selectUserByUid(pcComment2.getUid());

            //分别把用户中的值赋值给proComIndex中
            pcComIndex.setUid(user.getUid());
            pcComIndex.setUserName(user.getUserName());
            pcComIndex.setUserAvatar(user.getUserAvatar());

            //获取当前用户的全部关联信息
            List<UserTagRelation> userTagRelations =
                    userTagRelationMapper.selectUserTagRelationByUid(user.getUid());


            List<UserTag> userTags = new ArrayList<>();
            for (UserTagRelation userTagRelation : userTagRelations) {
                //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                userTags.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
            }

            //把获得到的用户标签赋值到procomindex中
            pcComIndex.setUserTags(userTags);
            PcComment pcComment1 = pcCommentMapper.selectPcCommentByCcid(pcComment.getCcid());
            pcComIndex.setCoContent(pcComment1.getCoContent());
            pcComIndex.setCcid(pcComment1.getCcid());
            pcComIndex.setCoGivelike(pcComment1.getCoGivelike());
            pcComIndex.setCoTime(pcComment1.getCoTime());
            pcComIndex.setCoReply(pcComment1.getCoReply());


            //----------------------------------------------------------------------

            //上面是父类的值，接下来是子类的评论
            List<PcComIndex> pcComIndexList2 = new ArrayList<>();
            int nowCcid = pcComIndex.getCcid();

            List<PcComment> pcCommentList = pcCommentMapper.selectCoParentCxid(nowCcid);

            if(!pcCommentList.isEmpty()){
                for (PcComment pcComment3:pcCommentList) {

                    PcComIndex pcComIndex2 = new PcComIndex();
                    int getCcid = pcComment3.getCcid();

                    PcComment pcComment4 = pcCommentMapper.selectPcCommentByCidContent(getCcid);

                    User user2 = userMapper.selectUserByUid(pcComment4.getUid());
                    pcComIndex2.setUid(user.getUid());
                    pcComIndex2.setUserName(user.getUserName());
                    pcComIndex2.setUserAvatar(user.getUserAvatar());

                    //获取当前用户的全部关联信息
                    List<UserTagRelation> userTagRelations1 =
                            userTagRelationMapper.selectUserTagRelationByUid(user2.getUid());
                    List<UserTag> userTags1 = new ArrayList<>();
                    //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                    for (UserTagRelation userTagRelation:userTagRelations1) {
                        userTags1.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
                    }
                    pcComIndex2.setUserTags(userTags1);
                    //把获得到的用户标签赋值到procomindex中
                    PcComment pcComment5 =
                            pcCommentMapper.selectPcCommentByCidContent(pcComment3.getCcid());
                    pcComIndex2.setCoContent(pcComment5.getCoContent());
                    pcComIndex2.setCcid(pcComment5.getCcid());
                    pcComIndex2.setCoGivelike(pcComment5.getCoGivelike());
                    pcComIndex2.setCoTime(pcComment5.getCoTime());
                    pcComIndex2.setCoReply(pcComment5.getCoReply());

                    pcComIndexList2.add(pcComIndex2);

                }
            }


            //上面的子类全部添加完成后，再把所有子类添加到list
            pcComIndex.setUserParent(pcComIndexList2);


            //最终把所有内容添加进去后，在加入到list中
            pcComIndexList.add(pcComIndex);

        }


        Collections.sort(pcComIndexList, new Comparator<PcComIndex>() {
            @Override
            public int compare(PcComIndex o1, PcComIndex o2) {
                return o1.getCoGivelike().compareTo(o2.getCoGivelike());
            }
        });
        Collections.reverse(pcComIndexList);
        System.out.println("这里是最终要返回给主页的评论内容"+pcComIndexList);
        for (PcComIndex proComIndex:pcComIndexList) {
            System.out.println(proComIndex);
        }


        return pcComIndexList;

    }
}
