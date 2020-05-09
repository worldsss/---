package com.example.bcyimitation.controller.Qu;

import com.example.bcyimitation.pojo.qu.Qu_imgs;
import com.example.bcyimitation.server.qu.QuImgsServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class QuImgsController {

    @Resource
    private QuImgsServer quImgsServer;

    @PostMapping("/insertQuImg")
    @ResponseBody
    public String insertQuImg(@RequestBody Qu_imgs quImgs){

        int flag = quImgsServer.insertQuImg(quImgs.getImg());
        if(flag==0){
            return "失败";
        }else {
            return "成功";
        }

    }

}
