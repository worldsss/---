package com.example.bcyimitation.controller.Pro;

import com.example.bcyimitation.pojo.Pro.ProTags;
import com.example.bcyimitation.server.Pro.ProTagsServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProTagsController {

  @Resource private ProTagsServer proTagsServer;

  /**
   * 插入一个作品-标签关系表的内容
   * @param proTags
   * @return
   */
  @PostMapping("/insertProTags")
  public String insertProTags(@RequestBody ProTags proTags) {
    System.out.println(proTags);
    int flag = proTagsServer.insertProTags(proTags.getTid());
    if (flag == 0) {
      return "失败";
    } else {
      return "成功";
    }
  }

  /**
   *
   * @param pro_tags
   * @return
   */
  @PostMapping("/insertForProTags")
  public String insertForProTags(@RequestBody List<ProTags> pro_tags){

    int flag = proTagsServer.insertForProTags(pro_tags);
    if (flag == 0) {
      return "失败";
    } else {
      return "成功";
    }
  }

  /**
   * 返回圈子成员的个数
   * @param tid
   * @return
   */
  @GetMapping("/getCountPridByTid")
  public int countAllPridByTid(Integer tid){

    return proTagsServer.countAllPridByTid(tid);
  }





}
