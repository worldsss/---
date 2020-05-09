package com.example.bcyimitation.severimpl.Pro;

import com.example.bcyimitation.mapper.Pro.ProCommentMapper;
import com.example.bcyimitation.mapper.Pro.ProComsMapper;
import com.example.bcyimitation.mapper.User.UserMapper;
import com.example.bcyimitation.mapper.User.UserTagMapper;
import com.example.bcyimitation.mapper.User.UserTagRelationMapper;
import com.example.bcyimitation.pojo.Pro.ProComIndex;
import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.pojo.Pro.ProComs;
import com.example.bcyimitation.pojo.User.User;
import com.example.bcyimitation.pojo.User.UserTag;
import com.example.bcyimitation.pojo.User.UserTagRelation;
import com.example.bcyimitation.server.Pro.ProCommentServer;
import com.example.bcyimitation.server.Pro.ProComsServer;
import com.example.bcyimitation.server.User.UserTagServer;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ProComsServerImpl implements ProComsServer {

    @Resource
    private ProComsMapper proComsMapper;

    @Resource
    private ProCommentMapper proCommentMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserTagMapper userTagMapper;

    @Resource
    private UserTagRelationMapper userTagRelationMapper;


    /**
     * 插入一个作品-评论关系表的内容
     *
     * @param prid
     * @return
     */
    @Override
    public int insertProComs(Integer prid) {

        ProComs proComs = new ProComs();
        proComs.setPrid(prid);
        proComs.setCid(proCommentMapper.selectNewstProCommentCid());
        return proComsMapper.insertProComs(proComs);
    }

    @Override
    public List<ProComment> selectAllProCommentByPrid(Integer prid) {
        //根据prid来获取到所有的pro_coms的cid
        List<ProComment> proComments = proComsMapper.selectAllCidByPrid(prid);
        //获取所有的根据cid来得到的评论内容
        List<ProComment> proCommentList = new ArrayList<>();
        for (ProComment proComment : proComments) {
            proCommentList.add(proCommentMapper.selectProCommentByCid(proComment.getCid()));
        }

        return proCommentList;
    }


    @Override
    public List<ProComIndex> selectAllProComIndexByPrid(Integer prid) {

        //最终要传的值
        List<ProComIndex> proComIndexList = new ArrayList<>();

        //根据prid来获取到所有的pro_coms的cid
        List<ProComment> proComments = proComsMapper.selectProCommentByPrid(prid);
        for (ProComment proComment : proComments) {

            ProComIndex proComIndex = new ProComIndex();

            //获取当前全部的pro_comment
            ProComment proComment2 = proCommentMapper.selectProCommentByCid(proComment.getCid());

            //根据pro_comment中的uid来获取到对应的用户对象
            User user = userMapper.selectUserByUid(proComment2.getUid());

            //分别把用户中的值赋值给proComIndex中
            proComIndex.setUid(user.getUid());
            proComIndex.setUser_name(user.getUserName());
            proComIndex.setUser_avatar(user.getUserAvatar());


            //获取当前用户的全部关联信息
            List<UserTagRelation> userTagRelations =
                    userTagRelationMapper.selectUserTagRelationByUid(user.getUid());
            List<UserTag> userTags = new ArrayList<>();
            for (UserTagRelation userTagRelation : userTagRelations) {
                //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                userTags.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
            }
            //把获得到的用户标签赋值到procomindex中
            proComIndex.setUserTags(userTags);
            ProComment proComment1 = proCommentMapper.selectProCommentByCid(proComment.getCid());
            proComIndex.setContent(proComment1.getContent());
            proComIndex.setCid(proComment1.getCid());
            proComIndex.setGivelike(proComment1.getGivelike());
            proComIndex.setTime(proComment1.getTime());
            proComIndex.setReply(proComment1.getReply());


            //----------------------------------------------------------------------

            //上面是父类的值，接下来是子类的评论
            List<ProComIndex> proComIndexList2 = new ArrayList<>();
            int nowCid = proComment.getCid();
//            int nowCid = proComment1.getCid();
            System.out.println("这里是用当前cid来作为子类查询条件的cid" + nowCid);
            List<ProComment> proCommentList = proCommentMapper.selectParentCid(nowCid);


            System.out.println("这里是所有的评论内容" + proCommentList);
//            if (proComIndexList != null && !proComIndexList.isEmpty()) {
//            if (!proComIndexList.isEmpty()) {
            if (!proCommentList.isEmpty()) {
                System.out.println("这里还是执行了？？");
                for (ProComment proComment3 : proCommentList) {

                    ProComIndex proComIndex2 = new ProComIndex();
                    System.out.println(proComment3.getCid());
                    int getCid = proComment3.getCid();
                    //获取当前全部的pro_comment
//                    ProComment proComment4 = proCommentMapper.selectProCommentByCid(proComment3.getCid());
//                    ProComment proComment4 = proCommentMapper.selectProCommentByCid(proComment3.getCid());
                    ProComment proComment4 = proCommentMapper.selectProCommentByCidContent(getCid);
                    System.out.println("这里是proComment4" + proComment4);
                    System.out.println(proComment4.getCid());
                    //根据pro_comment中的uid来获取到对应的用户对象
                    User user2 = userMapper.selectUserByUid(proComment4.getUid());
                    //分别把用户中的值赋值给proComIndex中
                    proComIndex2.setUid(user.getUid());
                    proComIndex2.setUser_name(user.getUserName());
                    proComIndex2.setUser_avatar(user.getUserAvatar());


                    //获取当前用户的全部关联信息
                    List<UserTagRelation> userTagRelations1 =
                            userTagRelationMapper.selectUserTagRelationByUid(user2.getUid());
                    List<UserTag> userTags1 = new ArrayList<>();
                    for (UserTagRelation userTagRelation : userTagRelations1) {
                        //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                        userTags1.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
                    }
                    //把获得到的用户标签赋值到procomindex中
                    proComIndex2.setUserTags(userTags1);
                    ProComment proComment5 = proCommentMapper.selectProCommentByCidContent(proComment3.getCid());
                    proComIndex2.setContent(proComment5.getContent());
                    proComIndex2.setCid(proComment5.getCid());
                    proComIndex2.setGivelike(proComment5.getGivelike());
                    proComIndex2.setTime(proComment5.getTime());
                    proComIndex2.setReply(proComment5.getReply());
                    System.out.println("这里是每一个子类的值" + proComIndex2);
                    proComIndexList2.add(proComIndex2);

                }
            }


            //上面的子类全部添加完成后，再把所有子类添加到list
            proComIndex.setUserParent(proComIndexList2);


            //最终把所有内容添加进去后，在加入到list中
            proComIndexList.add(proComIndex);


        }


        return proComIndexList;
    }

    /**
     * 根据时间顺序来展示procomindex
     * @param prid
     * @return
     */
    @Override
    public List<ProComIndex> selectAllProComIndexByPridOrderByTime(Integer prid) {


        //最终要传的值
        List<ProComIndex> proComIndexList = new ArrayList<>();

        //根据prid来获取到所有的pro_coms的cid
        List<ProComment> proComments = proComsMapper.selectProCommentByPrid(prid);
        for (ProComment proComment : proComments) {

            ProComIndex proComIndex = new ProComIndex();

            //获取当前全部的pro_comment
            ProComment proComment2 = proCommentMapper.selectProCommentByCid(proComment.getCid());

            //根据pro_comment中的uid来获取到对应的用户对象
            User user = userMapper.selectUserByUid(proComment2.getUid());

            //分别把用户中的值赋值给proComIndex中
            proComIndex.setUid(user.getUid());
            proComIndex.setUser_name(user.getUserName());
            proComIndex.setUser_avatar(user.getUserAvatar());


            //获取当前用户的全部关联信息
            List<UserTagRelation> userTagRelations =
                    userTagRelationMapper.selectUserTagRelationByUid(user.getUid());
            List<UserTag> userTags = new ArrayList<>();
            for (UserTagRelation userTagRelation : userTagRelations) {
                //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                userTags.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
            }
            //把获得到的用户标签赋值到procomindex中
            proComIndex.setUserTags(userTags);
            ProComment proComment1 = proCommentMapper.selectProCommentByCid(proComment.getCid());
            proComIndex.setContent(proComment1.getContent());
            proComIndex.setCid(proComment1.getCid());
            proComIndex.setGivelike(proComment1.getGivelike());
            proComIndex.setTime(proComment1.getTime());
            proComIndex.setReply(proComment1.getReply());


            //----------------------------------------------------------------------

            //上面是父类的值，接下来是子类的评论
            List<ProComIndex> proComIndexList2 = new ArrayList<>();
            int nowCid = proComment.getCid();
//            int nowCid = proComment1.getCid();
            List<ProComment> proCommentList = proCommentMapper.selectParentCid(nowCid);


            if (!proCommentList.isEmpty()) {
                for (ProComment proComment3 : proCommentList) {

                    ProComIndex proComIndex2 = new ProComIndex();
                    int getCid = proComment3.getCid();
                    //获取当前全部的pro_comment
//                    ProComment proComment4 = proCommentMapper.selectProCommentByCid(proComment3.getCid());
//                    ProComment proComment4 = proCommentMapper.selectProCommentByCid(proComment3.getCid());
                    ProComment proComment4 = proCommentMapper.selectProCommentByCidContent(getCid);
                    //根据pro_comment中的uid来获取到对应的用户对象
                    User user2 = userMapper.selectUserByUid(proComment4.getUid());
                    //分别把用户中的值赋值给proComIndex中
                    proComIndex2.setUid(user.getUid());
                    proComIndex2.setUser_name(user.getUserName());
                    proComIndex2.setUser_avatar(user.getUserAvatar());


                    //获取当前用户的全部关联信息
                    List<UserTagRelation> userTagRelations1 =
                            userTagRelationMapper.selectUserTagRelationByUid(user2.getUid());
                    List<UserTag> userTags1 = new ArrayList<>();
                    for (UserTagRelation userTagRelation : userTagRelations1) {
                        //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                        userTags1.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
                    }
                    //把获得到的用户标签赋值到procomindex中
                    proComIndex2.setUserTags(userTags1);
                    ProComment proComment5 = proCommentMapper.selectProCommentByCidContent(proComment3.getCid());
                    proComIndex2.setContent(proComment5.getContent());
                    proComIndex2.setCid(proComment5.getCid());
                    proComIndex2.setGivelike(proComment5.getGivelike());
                    proComIndex2.setTime(proComment5.getTime());
                    proComIndex2.setReply(proComment5.getReply());
                    proComIndexList2.add(proComIndex2);

                }
            }


            //上面的子类全部添加完成后，再把所有子类添加到list
            proComIndex.setUserParent(proComIndexList2);


            //最终把所有内容添加进去后，在加入到list中
            proComIndexList.add(proComIndex);


        }


        Collections.sort(proComIndexList, new Comparator<ProComIndex>() {
            @Override
            public int compare(ProComIndex o1, ProComIndex o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        Collections.reverse(proComIndexList);
        System.out.println("这里是最终要返回给主页的评论内容"+proComIndexList);
        for (ProComIndex proComIndex:proComIndexList) {
            System.out.println(proComIndex);
        }


        return proComIndexList;


    }


    @Override
    public List<ProComIndex> selectAllProComIndexByPridOrderByGivelike(Integer prid) {

        //最终要传的值
        List<ProComIndex> proComIndexList = new ArrayList<>();

        //根据prid来获取到所有的pro_coms的cid
        List<ProComment> proComments = proComsMapper.selectProCommentByPrid(prid);
        for (ProComment proComment : proComments) {

            ProComIndex proComIndex = new ProComIndex();

            //获取当前全部的pro_comment
            ProComment proComment2 = proCommentMapper.selectProCommentByCid(proComment.getCid());

            //根据pro_comment中的uid来获取到对应的用户对象
            User user = userMapper.selectUserByUid(proComment2.getUid());

            //分别把用户中的值赋值给proComIndex中
            proComIndex.setUid(user.getUid());
            proComIndex.setUser_name(user.getUserName());
            proComIndex.setUser_avatar(user.getUserAvatar());


            //获取当前用户的全部关联信息
            List<UserTagRelation> userTagRelations =
                    userTagRelationMapper.selectUserTagRelationByUid(user.getUid());
            List<UserTag> userTags = new ArrayList<>();
            for (UserTagRelation userTagRelation : userTagRelations) {
                //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                userTags.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
            }
            //把获得到的用户标签赋值到procomindex中
            proComIndex.setUserTags(userTags);
            ProComment proComment1 = proCommentMapper.selectProCommentByCid(proComment.getCid());
            proComIndex.setContent(proComment1.getContent());
            proComIndex.setCid(proComment1.getCid());
            proComIndex.setGivelike(proComment1.getGivelike());
            proComIndex.setTime(proComment1.getTime());
            proComIndex.setReply(proComment1.getReply());


            //----------------------------------------------------------------------

            //上面是父类的值，接下来是子类的评论
            List<ProComIndex> proComIndexList2 = new ArrayList<>();
            int nowCid = proComment.getCid();
//            int nowCid = proComment1.getCid();
            List<ProComment> proCommentList = proCommentMapper.selectParentCid(nowCid);


            if (!proCommentList.isEmpty()) {
                for (ProComment proComment3 : proCommentList) {

                    ProComIndex proComIndex2 = new ProComIndex();
                    int getCid = proComment3.getCid();
                    //获取当前全部的pro_comment
//                    ProComment proComment4 = proCommentMapper.selectProCommentByCid(proComment3.getCid());
//                    ProComment proComment4 = proCommentMapper.selectProCommentByCid(proComment3.getCid());
                    ProComment proComment4 = proCommentMapper.selectProCommentByCidContent(getCid);
                    //根据pro_comment中的uid来获取到对应的用户对象
                    User user2 = userMapper.selectUserByUid(proComment4.getUid());
                    //分别把用户中的值赋值给proComIndex中
                    proComIndex2.setUid(user.getUid());
                    proComIndex2.setUser_name(user.getUserName());
                    proComIndex2.setUser_avatar(user.getUserAvatar());


                    //获取当前用户的全部关联信息
                    List<UserTagRelation> userTagRelations1 =
                            userTagRelationMapper.selectUserTagRelationByUid(user2.getUid());
                    List<UserTag> userTags1 = new ArrayList<>();
                    for (UserTagRelation userTagRelation : userTagRelations1) {
                        //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                        userTags1.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
                    }
                    //把获得到的用户标签赋值到procomindex中
                    proComIndex2.setUserTags(userTags1);
                    ProComment proComment5 = proCommentMapper.selectProCommentByCidContent(proComment3.getCid());
                    proComIndex2.setContent(proComment5.getContent());
                    proComIndex2.setCid(proComment5.getCid());
                    proComIndex2.setGivelike(proComment5.getGivelike());
                    proComIndex2.setTime(proComment5.getTime());
                    proComIndex2.setReply(proComment5.getReply());
                    proComIndexList2.add(proComIndex2);

                }
            }


            //上面的子类全部添加完成后，再把所有子类添加到list
            proComIndex.setUserParent(proComIndexList2);


            //最终把所有内容添加进去后，在加入到list中
            proComIndexList.add(proComIndex);


        }


        Collections.sort(proComIndexList, new Comparator<ProComIndex>() {
            @Override
            public int compare(ProComIndex o1, ProComIndex o2) {
                return o1.getGivelike().compareTo(o2.getGivelike());
            }
        });
        Collections.reverse(proComIndexList);
        System.out.println("这里是最终要返回给主页的评论内容"+proComIndexList);
        for (ProComIndex proComIndex:proComIndexList) {
            System.out.println(proComIndex);
        }


        return proComIndexList;


    }
}



/*

    @Override
    public List<ProComIndex> selectAllProComIndexByPrid(Integer prid) {

        //最终要传的值
        List<ProComIndex> proComIndexList = new ArrayList<>();


        //根据prid来获取到所有的pro_coms的cid
        List<ProComment> proComments = proComsMapper.selectProCommentByPrid(prid);
//        System.out.println(proComments);
        for (ProComment proComment : proComments) {

            ProComIndex proComIndex = new ProComIndex();

            //获取当前全部的pro_comment
            ProComment proComment2 = proCommentMapper.selectProCommentByCid(proComment.getCid());
//                System.out.println(proComment2);

            //根据pro_comment中的uid来获取到对应的用户对象
            User user = userMapper.selectUserByUid(proComment2.getUid());

            //分别把用户中的值赋值给proComIndex中
            proComIndex.setUid(user.getUid());
            proComIndex.setUser_name(user.getUser_name());
            proComIndex.setUser_avatar(user.getUser_avatar());


            //获取当前用户的全部关联信息
            List<UserTagRelation> userTagRelations =
                    userTagRelationMapper.selectUserTagRelationByUid(user.getUid());
            List<UserTag> userTags = new ArrayList<>();
            for (UserTagRelation userTagRelation : userTagRelations) {
                //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
                userTags.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
            }
            //把获得到的用户标签赋值到procomindex中
            proComIndex.setUserTags(userTags);
            ProComment proComment1 = proCommentMapper.selectProCommentByCid(proComment.getCid());
            proComIndex.setContent(proComment1.getContent());
            proComIndex.setCid(proComment1.getCid());
            proComIndex.setGivelike(proComment1.getGivelike());
            proComIndex.setTime(proComment1.getTime());
            proComIndex.setReply(proComment1.getReply());

            //上面是父类的值，接下来是子类的评论


            //最终要传的值
            List<ProComIndex> proComIndexList = new ArrayList<>();

            int parentCid = proComment.getCid();
            //获取当前是否有用户评论内容
//                List<ProComment> proCommentList = proCommentMapper.selectParentCid(proComment1.getCid());

            System.out.println(parentCid);
            List<ProComment> proCommentList = proCommentMapper.selectParentCid(parentCid);



            System.out.println(proCommentList);
            proComIndex.setUserParent(proCommentList);


            proComIndexList.add(proComIndex);


        }


        return proComIndexList;
    }*/
