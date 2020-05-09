package com.example.bcyimitation.controller.Pro;

import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.pojo.Pro.ProComs;
import com.example.bcyimitation.server.Pro.ProCommentServer;
import com.example.bcyimitation.server.Pro.ProComsServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProComsController {

    @Resource
    public ProComsServer proComsServer;

    /**
     * 插入一条评论-作品关系表
     * @param prid
     * @return
     */
    @GetMapping("/insertProComs")
    public int insertProComs(Integer prid){
       return proComsServer.insertProComs(prid);
    }

    /**
     * 根据作品prid来获取所有的评论内容
     * @param prid
     * @return
     */
    @GetMapping("/getAllProCommentByPrid")
    public List<ProComment> selectProCommentByPrid(Integer prid){
        return proComsServer.selectAllProCommentByPrid(prid);
    }



}
