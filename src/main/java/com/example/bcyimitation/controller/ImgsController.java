package com.example.bcyimitation.controller;

import com.example.bcyimitation.pojo.Pro.ProImgs;
import com.example.bcyimitation.server.Pro.ProContentServer;
import com.example.bcyimitation.server.Pro.ProImgsServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ImgsController {

    @Resource
    private ProImgsServer proImgsServer;

    @Resource
    private ProContentServer proContentServer;

//    @RequestMapping("/insertImgs")

    /**
     * 插入一个图片表
     * @param proImgs
     * @return
     */
    @PostMapping("/insertImgs")
    private int insertImgs(@RequestBody ProImgs proImgs){

        //ctrl+alt+v 快速生成返回值
        int flag = proImgsServer.insertImgsByPrid(proImgs.getImg());
        if(flag != 0){
//            return proImgs.getImg()+"上传成功";
            return proContentServer.searchPridByNewest();
        }else {
            return 0;
        }

    }



}
