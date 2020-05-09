package com.example.bcyimitation.controller.User;

import com.example.bcyimitation.pojo.User.UserTag;
import com.example.bcyimitation.pojo.User.UserTagRelation;
import com.example.bcyimitation.server.User.UserTagServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserTagController {

    @Resource
    private UserTagServer userTagServer;

    /**
     * 根据uid来展示当前用户的所有标签
     * @param uid
     * @return
     */
    @GetMapping("/getUserTagByUid")
    public List<UserTag> selectUserTagByUid(Integer uid){
        return userTagServer.selectUserTabByUid(uid);
    }

    /**
     * 插入一个用户标签
     * @param userTagRelation
     * @return
     */
    @PostMapping("/insertUserTagRelationByUidAndUtid")
    public int insertUserTagRelationByUidAndUtid(@RequestBody UserTagRelation userTagRelation){

        return userTagServer.insertUserTagRelationByUidAndUtid(userTagRelation.getUid(),userTagRelation.getUtid());
    }

    /**
     * 显示所有的用户标签
     * @return
     */
    @GetMapping("/selectAllUserTag")
    public List<UserTag> selectAllUserTag(){
        return userTagServer.selectAllUserTag();
    }

    /**
     * 删除某一条用户标签
     * @param userTagRelation
     * @return
     */
    @PostMapping("/deleteUserTagRelationByUidAndUid")
    public int deleteUserTagRelationByUidAndUtid(@RequestBody UserTagRelation userTagRelation){
        return userTagServer.deleteUserTagRelationByUidAndUtid(userTagRelation.getUid(),userTagRelation.getUtid());
    }


}
