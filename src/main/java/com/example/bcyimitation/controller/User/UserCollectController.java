package com.example.bcyimitation.controller.User;

import com.example.bcyimitation.pojo.User.UserCollect;
import com.example.bcyimitation.server.User.UserCollectServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserCollectController {

    @Resource
    private UserCollectServer userCollectServer;

    /**
     * 插入一个用户收藏
     * @param userCollect
     * @return
     */
    @PostMapping("/insertUserCollect")
    public int insertUserCollect(@RequestBody UserCollect userCollect){

        return  userCollectServer.insertUserCollect(userCollect.getUid(),userCollect.getPrid());
    }

    /**
     * 判断当前用户收藏了没
     * @param userCollect
     * @return
     */
    @PostMapping("/judgeUserCollect")
    public int judgeUserCollectByUidAndPrid(@RequestBody UserCollect userCollect){
        return userCollectServer.judgeUserCollectByUidAndPrid(userCollect.getUid(),userCollect.getPrid());
    }

    /**
     * 取消当前用户收藏
     * @param userCollect
     * @return
     */
    @PostMapping("/deleteUserCollect")
    public int deleteUserCollectByUidAndPrid(@RequestBody UserCollect userCollect){
        return userCollectServer.deleteUserCollectByUidAndPrid(userCollect.getUid(),userCollect.getPrid());
    }


//    ----------------文字作品收藏----------------

    /**
     * 插入一个文字作品的收藏
     * @param userCollect
     * @return
     */
    @PostMapping("/insertUserCollectByPcid")
    public int insertUserCollectByPcid(@RequestBody UserCollect userCollect){
        return userCollectServer.insertUserCollectByPcid(userCollect.getUid(),userCollect.getPcid());
    }

    /**
     * 判断当前文字作品内容是否被收藏了
     * @param userCollect
     * @return
     */
    @PostMapping("/judegUserCollectByUidAndPcid")
    public int judgeUserCollectByUidAndPcid(@RequestBody UserCollect userCollect){
        return userCollectServer.judgeUserCollectByUidAndPcid(userCollect.getUid(),userCollect.getPcid());
    }

    /**
     * 删除某一个文字作品
     * @param userCollect
     * @return
     */
    @PostMapping("/deleteUserCollectByUidAndPcid")
    public int deleteUserCollectByUidAndPcid(@RequestBody UserCollect userCollect){
        return userCollectServer.deleteUserCollectByUidAndPcid(userCollect.getUid(),userCollect.getPcid());
    }



}
