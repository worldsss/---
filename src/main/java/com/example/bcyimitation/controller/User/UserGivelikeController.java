package com.example.bcyimitation.controller.User;

import com.example.bcyimitation.pojo.User.UserGivelike;
import com.example.bcyimitation.server.Pc.PcContentServer;
import com.example.bcyimitation.server.Pro.ProContentServer;
import com.example.bcyimitation.server.User.UserGivelikeServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserGivelikeController {

    @Resource
    private UserGivelikeServer userGivelikeServer;

    @Resource
    private ProContentServer proContentServer;

    @Resource
    private PcContentServer pcContentServer;


    /**
     * 插入一条用户点赞关系表
     *
     * @param userGivelike
     * @return
     */
    @PostMapping("/insertGivelike")
    public int insertGivelike(@RequestBody UserGivelike userGivelike) {
        //插入一条用户点赞关系表
        int flag = userGivelikeServer.insertGivelike(userGivelike.getUid(), userGivelike.getPrid());
        if (flag != 1) {
            //表示当前用户已经点过赞了
            return 0;
        } else {
            return 1;
        }

    }


    /**
     * 用户-作品的点赞数量-1
     *
     * @param userGivelike
     * @return
     */
    @PostMapping("/deleteUserGivelikeByUidAndPrid")
    public int deleteUserGivelikeByUidAndPrid(@RequestBody UserGivelike userGivelike) {

        //如果这个用户已经点过赞了，那么偶就删除它
        int i2 = userGivelikeServer.removeGivelikeByPrid(userGivelike.getUid(), userGivelike.getPrid());

        //获得有多少用户个这个作品点赞
        int i = userGivelikeServer.countGivelikeByPrid(userGivelike.getPrid());


        //修改当前作品的点赞值
        int i1 = proContentServer.updateProContentPrGivelikeByUserGivelike(i, userGivelike.getPrid());

        if (i1 == 1) {
            return i;
        } else {
            return 0;

        }

    }

    /**
     * 当前的作品的givelike+1
     *
     * @param userGivelike
     * @return
     */
    @PostMapping("/addUserGivelikeByUidAndPrid")
    public int addUserGivelikeByUidAndPrid(@RequestBody UserGivelike userGivelike) {
        //获得有多少用户个这个作品点赞
        int i = userGivelikeServer.countGivelikeByPrid(userGivelike.getPrid());

        //修改当前作品的点赞值
        int i1 = proContentServer.updateProContentPrGivelikeByUserGivelike(i, userGivelike.getPrid());

        if (i1 == 1) {
            //返回当前作品有多少用户点赞
            return i;
        } else {
            return 0;
        }

    }

//    ---------------------文字作品

    /**
     * 插入一条文字作品的用户点赞表
     *
     * @param userGivelike
     * @return
     */
    @PostMapping("/insertGivelikePcid")
    public int insertGivelikePcid(@RequestBody UserGivelike userGivelike) {
        //插入一条用户点赞关系表
        return userGivelikeServer.insertGivelikePcid(userGivelike.getUid(),userGivelike.getPcid());
    }


    /**
     * 文字作品的用户-作品的点赞数量-1
     *
     * @param userGivelike
     * @return
     */
    @PostMapping("/deleteUserGivelikeByUidAndPcid")
    public int deleteUserGivelikeByUidAndPcid(@RequestBody UserGivelike userGivelike) {

        //如果这个用户已经点过赞了，那么偶就删除它
        int i2 = userGivelikeServer.removeGivelikeByPcrd(
                userGivelike.getUid(), userGivelike.getPcid());

        //获得有多少用户个这个作品点赞
        int i = userGivelikeServer.countGivelikeByPcid(userGivelike.getPcid());

        //修改当前作品的点赞值
        int i1 = pcContentServer.updatePcContentPcGivelikeByUserGivelike(
                i, userGivelike.getPcid());

        if (i1 == 1) {
            return i;
        } else {
            return 0;

        }

    }

    /**
     * 当前的作品的givelike+1
     *
     * @param userGivelike
     * @return
     */
    @PostMapping("/addUserGivelikeByUidAndPcid")
    public int addUserGivelikeByUidAndPcid(@RequestBody UserGivelike userGivelike) {
        //获得有多少用户个这个作品点赞

        int i = userGivelikeServer.countGivelikeByPcid(userGivelike.getPcid());

        //修改当前作品的点赞值
        int i1 = pcContentServer.updatePcContentPcGivelikeByUserGivelike(
                i, userGivelike.getPcid());
        if (i1 == 1) {
            //返回当前作品有多少用户点赞
            return i;
        } else {
            return 0;
        }

    }


}
