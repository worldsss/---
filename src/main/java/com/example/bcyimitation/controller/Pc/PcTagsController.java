package com.example.bcyimitation.controller.Pc;

import com.example.bcyimitation.pojo.Pc.PcTags;
import com.example.bcyimitation.server.Pc.PcTagsServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PcTagsController {

    @Resource
    private PcTagsServer pcTagsServer;

    /**
     * 插入一个文字-标签关系表
     * @param pcTags
     * @return
     */
    @PostMapping("/insertPcTags")
    public String insertPcTags(@RequestBody PcTags pcTags) {

        int flag = pcTagsServer.insertPcTags(pcTags.getTid());
        if (flag == 0) {
            return "失败";
        } else {
            return "成功";
        }
    }

}
