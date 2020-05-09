package com.example.bcyimitation.controller.User;


import com.example.bcyimitation.pojo.User.UserCircle;
import com.example.bcyimitation.server.User.UserCircleServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserCircleController {

    @Resource
    private UserCircleServer userCircleServer;

    /**
     * 插入一个用户-圈子关注表
     * @param userCircle
     * @return
     */
    @PostMapping("/insertUserCircle")
    public int insertUserCircle(@RequestBody UserCircle userCircle){

        return userCircleServer.insertUserCircle(userCircle.getUid(),userCircle.getTid());
    }

    /**
     * 判断当前用户是否关注了此圈子
     * @param userCircle
     * @return
     */
    @PostMapping("/judgeUserCircleByTidAndUid")
    public int judgeUserCircleByTidAndUid(@RequestBody UserCircle userCircle){

        return userCircleServer.judgeUserCircleByTidAndUid(userCircle.getUid(),userCircle.getTid());
    }

    /**
     * 删除用户-圈子表的关联关系
     * @param userCircle
     * @return
     */
    @PostMapping("/deleteUserCircle")
    public int deleteUserCircleByTidAndUid(@RequestBody UserCircle userCircle){
        return userCircleServer.deleteUserCircleByTidAndUid(userCircle.getUid(),userCircle.getTid());
    }


}
