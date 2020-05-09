package com.example.bcyimitation.controller.Pc;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pc.PcIndex;
import com.example.bcyimitation.server.Pc.PcIndexServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PcIndexController {

    @Resource
    private PcIndexServer pcIndexServer;

    /**
     * 分页展示文字内容
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("/showPcIndex")
    public Paging selectPcIndex(@RequestParam(defaultValue = "10")int pageSize,
                              @RequestParam(defaultValue = "1" )int pageNumber){

        Paging paging = pcIndexServer.selectPcIndexByPaging(pageSize,pageNumber);


        return paging;
    }

    /**
     * 根据pcid来显示一个文字作品
     * @param pcid
     * @return
     */
    @GetMapping("/selectPcIndexByPcid")
    public PcIndex selectPcIndexByPcid(int pcid){

        PcIndex pc_index = pcIndexServer.selectPcIndexByPcid(pcid);

        return pc_index;
    }

    /**
     * 根据圈子uid来展示当前用户的所有文字作品
     * @param pageSize
     * @param pageNumber
     * @param tid
     * @return
     */
    @GetMapping("/selectPcIndexByTidPaging")
    public Paging selectPcIndexByTidPaging(@RequestParam(defaultValue = "10")int pageSize,
                                           @RequestParam(defaultValue = "1" )int pageNumber,int tid){

        return pcIndexServer.selectPcIndexByTidPaging(pageSize,pageNumber,tid);
    }

    /**
     * 根据用户uid来展示当前用户的所有文字作品
     * @param pageSize
     * @param pageNumber
     * @param uid
     * @return
     */
    @GetMapping("/selectPcIndexByUidPaging")
    public Paging selectPcIndexByUidPaging(@RequestParam(defaultValue = "10")int pageSize,
                                           @RequestParam(defaultValue = "1" )int pageNumber,Integer uid){

        return pcIndexServer.selectPcIndexByUidPaging(pageSize,pageNumber,uid);
    }




}
