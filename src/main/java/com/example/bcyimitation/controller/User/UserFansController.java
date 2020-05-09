package com.example.bcyimitation.controller.User;

import com.example.bcyimitation.pojo.User.User;
import com.example.bcyimitation.pojo.User.UserFans;
import com.example.bcyimitation.server.User.UserFansServer;
import com.example.bcyimitation.server.User.UserServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserFansController {

    @Resource
    private UserFansServer userFansServer;

    @Resource
    private UserServer userServer;

    /**
     * 插入一个用户的粉丝
     * @param userFans
     * @return
     */
    @PostMapping("/insertUserFans")
    public int insertUserFans(@RequestBody UserFans userFans){

        return  userFansServer.insertUserFans(userFans.getFid(),userFans.getUid());
    }

    /**
     * 判断我访问的用户我关注了没有
     * @param userFans
     * @return
     */
    @PostMapping("/selectUserFansByFid")
    public int selectUserFansByFid(@RequestBody UserFans userFans){

        return userFansServer.selectUserFansByUid(userFans.getUid(),userFans.getFid());
    }

    /**
     * 根据登录用户来查看自己关注的用户
     * @param fid
     * @return
     */
    @GetMapping("/getUserFansUserByFid")
    public List<User> selectUserFansUserByFid(Integer fid){

        List<UserFans> userFansList = userFansServer.selectUserFansUserByFid(fid);

        List<User> userList = new ArrayList<>();

        int i = 0;
        for (UserFans userFans:userFansList ) {
            i++;
            //根据userfans里面的所有的uid来获得所有的用户信息，在放入list中
//            userList.add(userServer.selectUserByUidLimitFive(userFans.getUid()));

            //只添加五个用户信息
            if(i<=5){
                userList.add(userServer.selectUserByUidLimitFive(userFans.getUid()));
            }
        }
        System.out.println(userList);

        return userList;
    }



}
