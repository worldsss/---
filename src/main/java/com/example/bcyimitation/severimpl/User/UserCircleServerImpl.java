package com.example.bcyimitation.severimpl.User;

import com.example.bcyimitation.mapper.User.UserCircleMapper;
import com.example.bcyimitation.pojo.User.UserCircle;
import com.example.bcyimitation.server.User.UserCircleServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCircleServerImpl implements UserCircleServer {

    @Resource
    private UserCircleMapper userCircleMapper;

    /**
     * 插入一个用户-圈子关系表
     * @param uid
     * @param tid
     * @return
     */
    @Override
    public int insertUserCircle(Integer uid, Integer tid) {

        UserCircle userCircle = new UserCircle();

        userCircle.setTid(tid);
        userCircle.setUid(uid);



        return userCircleMapper.insertUserCirCle(userCircle);
    }

    /**
     * 判断当前用户是否关注了此圈子
     * @param uid
     * @param tid
     * @return
     */
    @Override
    public int judgeUserCircleByTidAndUid(Integer uid, Integer tid) {
        UserCircle userCircle = new UserCircle();

        userCircle.setTid(tid);
        userCircle.setUid(uid);

        return userCircleMapper.countUserCircleByTidAndUid(userCircle);
    }

    /**
     * 根据uid和tid来删除用户-圈子的关系
     * @param uid
     * @param tid
     * @return
     */
    @Override
    public int deleteUserCircleByTidAndUid(Integer uid, Integer tid) {

        UserCircle userCircle = new UserCircle();

        userCircle.setUid(uid);
        userCircle.setTid(tid);
        return userCircleMapper.deleteUserCircleByTidAndUid(userCircle);
    }


    @Override
    public List<UserCircle> selectMorestUserCircle(Integer tid) {

        List<UserCircle> userCircleList = new ArrayList<>();

        List<UserCircle> userCircles = userCircleMapper.selectAllTidByUserCircle();

        for (UserCircle userCircle:userCircles ) {

            int hot = userCircleMapper.countAllProContentByTid(userCircle.getTid());

//            userCircleList.add(hot);

        }






        return null;
    }
}
