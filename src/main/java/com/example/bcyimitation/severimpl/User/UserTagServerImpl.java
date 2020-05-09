package com.example.bcyimitation.severimpl.User;

import com.example.bcyimitation.mapper.User.UserTagMapper;
import com.example.bcyimitation.mapper.User.UserTagRelationMapper;
import com.example.bcyimitation.pojo.User.UserTag;
import com.example.bcyimitation.pojo.User.UserTagRelation;
import com.example.bcyimitation.server.User.UserTagServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.UserTransactionAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserTagServerImpl implements UserTagServer {

    @Resource
    private UserTagMapper userTagMapper;

    @Resource
    private UserTagRelationMapper userTagRelationMapper;

    @Override
    public List<UserTag> selectUserTabByUid(Integer uid) {

        //获取当前用户的全部关联信息
        List<UserTagRelation> userTagRelations =
                userTagRelationMapper.selectUserTagRelationByUid(uid);


        List<UserTag> userTags = new ArrayList<>();

        for (UserTagRelation userTagRelation :userTagRelations) {
            //根据关联信息中的utid来查找出来对应的标签内容，再放入到list中
            userTags.add(userTagMapper.selectUserTagByUtid(userTagRelation.getUtid()));
        }

        return userTags;
    }


    /**
     * 插入一个用户的身份标签
     * @param uid
     * @param utid
     * @return
     */
    @Override
    public int insertUserTagRelationByUidAndUtid(Integer uid, Integer utid) {

        UserTagRelation userTagRelation = new UserTagRelation();
        userTagRelation.setUid(uid);
        userTagRelation.setUtid(utid);

        return userTagRelationMapper.insertUserTagRelationByUidAndUtid(userTagRelation);
    }

    /**
     * 显示所有的用户标签
     * @return
     */
    @Override
    public List<UserTag> selectAllUserTag() {
        return userTagMapper.selectAllUserTag();
    }


    @Override
    public int deleteUserTagRelationByUidAndUtid(Integer uid, Integer utid) {

        UserTagRelation userTagRelation = new UserTagRelation();
        userTagRelation.setUtid(utid);
        userTagRelation.setUid(uid);
        return userTagMapper.deleteUserTagRelationByUidAndUtid(userTagRelation);
    }
}
