package com.example.bcyimitation.controller.Pro;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProIndex;
import com.example.bcyimitation.server.Pro.ProIndexServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProIndexController {
    @Resource
    private ProIndexServer proIndexServer;

    /**
     * 分页显示图片作品
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("/showIndex")
    public Paging selectIndex(@RequestParam(defaultValue = "10") int pageSize,
                              @RequestParam(defaultValue = "1") int pageNumber) {

        Paging paging = proIndexServer.selectIndex(pageSize, pageNumber);


        return paging;
    }

    /**
     * 根据用户id来查询全部的作品
     * @param uid
     * @return
     */
    @GetMapping("/getProIndexByUid")
    public Paging selectProIndexByUid(@RequestParam(defaultValue = "20")int pageSize,
                                      @RequestParam(defaultValue = "1" )int pageNumber,int uid){

        Paging paging = proIndexServer.selectProIndexByUid(pageSize,pageNumber,uid);
        return paging;
    }


    @PostMapping("/selectIndexById")
    public ProIndex selectIndexById(@RequestParam ProIndex proIndex) {

        ProIndex proIndex1 = proIndexServer.selectIndexById(proIndex.getPrid());

        return proIndex1;
    }

    /**
     * 根据prid来展示一条的信息
     *
     * @param prid
     * @return
     */
    @GetMapping("/selectTagById")
    public ProIndex selectIndexTagById(int prid) {


        return proIndexServer.selectIndexTagsById(prid);
    }

    /**
     * 查询全部的pro_index页面
     *
     * @return
     */
    @GetMapping("/showAllIndex")
    public List<ProIndex> selectAllIndex() {

        return proIndexServer.selectAllIndex();
    }

    /**
     * 根据标签的tid来展示全部的proindex
     *
     * @param tid
     * @return
     */
    @GetMapping("/selectAllIndexByTid")
    public List<ProIndex> selectAllIndexByTid(Integer tid) {

        return proIndexServer.selectAllIndexByTid(tid);

    }


    /**
     * 根据用户id来查询全部的作品
     *
     * @param tid
     * @return
     */
    @GetMapping("/getProIndexByTid")
    public Paging selectProIndexByTid(@RequestParam(defaultValue = "20") int pageSize,
                                      @RequestParam(defaultValue = "1") int pageNumber, int tid) {


        Paging paging = proIndexServer.selectProIndexByTid(pageSize, pageNumber, tid);

        return paging;
    }

    /**
     * 根据当前用户uid来查询所有自己关注的用户的所有图片作品
     *
     * @param fid
     * @return
     */
    @GetMapping("/selectProIndexByUserUid")
    public List<ProIndex> selectProIndexByUserUid(Integer fid) {
        return proIndexServer.selectProIndexByUserUid(fid);
    }

    /**
     * 根据点赞进行分页显示的所有图片内容
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("/selectProIndexOrderByPrGivelike")
    public Paging selectProIndexOrderByPrGivelike(@RequestParam(defaultValue = "20") int pageSize,
                                                  @RequestParam(defaultValue = "1") int pageNumber) {
        Paging paging = proIndexServer.selectProIndexOrderByProContentPrGivelike(pageSize, pageNumber);
        return paging;
    }

    /**
     * cos榜前七
     * @return
     */
    @GetMapping("/selectProContentOrderByPrGivelikeLimitSeven")
    public List<ProIndex> selectProContentOrderByPrGivelikeLimitSeven() {
        return proIndexServer.selectProContentOrderByPrGivelikeLimitSeven();
    }


}
