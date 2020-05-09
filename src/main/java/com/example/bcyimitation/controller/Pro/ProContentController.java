package com.example.bcyimitation.controller.Pro;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.server.Pro.ProContentServer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class ProContentController {

    @Resource
    private ProContentServer proContentServer;

    /**
     * 查询出所有的图片作品
     * @return
     */
    @RequestMapping("/selectPro")
    public List<ProContent> selectProContent(){
        List<ProContent> list =  proContentServer.selectProContent();
        return list;
    }

    /**
     * 根据prid来查询对应的内容和图片
     * @param prid
     * @return
     */
    @GetMapping("/getProContentByPrid")
    public ProContent selectProContentByPrid(int prid){
        return  proContentServer.selectProContentByPrid(prid);
    }

    /**
     * 查询出所有的内容和图片
     * @return
     */
    @GetMapping("/getAllProContent")
    @ResponseBody
    public List<ProContent> selectAllProContent(){
        return proContentServer.selectAllProContent();
    }


    /**
     * 分页显示所有的内容和图片，包含另一张表的图片
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping("/showPro")
    public Paging selectMainPage(@RequestParam(defaultValue = "10")int pageSize,
                                 @RequestParam(defaultValue = "1" )int pageNumber){

        Paging paging = proContentServer.selectProPage(pageSize,pageNumber);

        return paging;
    }


    /**
     * 修改刚刚插入数据库中的图片个数
     * @return
     */
    @GetMapping("/updateProImgCount")
    public int updateProContentImgCount(){

        return proContentServer.updateProContentImgCount();
    }

    /**
     * 点赞+1
     * @param proContent
     * @return
     */
    @PostMapping("/addPrClickByPrid")
    public int addProContentPrClickByPrid(@RequestBody ProContent proContent){


        return  proContentServer.addProContentPrClickByPrid(proContent.getPrid());
    }

    /**
     * 点赞-1
     * @param proContent
     * @return
     */
    @PostMapping("/lessPrClickByPrid")
    public int lessProContentPrClickByPrid(@RequestBody ProContent proContent){
        return  proContentServer.lessProContentPrGivelikeByPrid(proContent.getPrid());
    }

    /**
     * 更新最新插入的图片作品的第一张内容，pr_img
     * @param proContent
     * @return
     */
    @PostMapping("/updateProImgByPrid")
    public int updateProImgByPrid(@RequestBody ProContent proContent){
        return proContentServer.updateProImgByPrid(proContent.getPrImg(),proContent.getPrid());
    }

    /**
     * 当前用户收藏的作品的显示
     * @param uid
     * @return
     */
    @GetMapping("/getProContentByUserCollectUid")
    public List<ProContent> selectProContentByUserCollectUid(Integer uid){

        return proContentServer.selectProContentByUserCollectUid(uid);
    }

    /**
     * 当前用户点赞的作品的显示
     * @param uid
     * @return
     */
    @GetMapping("/getProContentByUserGivelikeUid")
    public List<ProContent> selectProContentByUserGivelikeUid(Integer uid){
        return proContentServer.selectProContentByUserGivelikeUid(uid);
    }



    /**
     * 当前用户收藏的作品的分页显示
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("/getUserCollectPaging")
    public Paging selectByUserCollectUidPaging(
                                 @RequestParam(defaultValue = "3")int pageSize,
                                 @RequestParam(defaultValue = "1" )int pageNumber,Integer uid){

        Paging paging = proContentServer.selectProContentByUserCollectUidPaging(uid,pageSize,pageNumber);


        return paging;
    }




    @PostMapping("/insertPro")
    public String insertProContent() {
        return "";

    }


    /**
     * 插入一个图片作品内容
     * @param proContent
     * @return
     */
    @PostMapping("/test")
    public String insertProContent(@RequestBody ProContent proContent) {


        int flag = proContentServer.insertProContent(proContent.getUid(),proContent.getPrInfo(),
                proContent.getPrImg(),proContent.getPrDate(),proContent.getPrGivelike());
        if(flag != 0){
            return "发布成功";
        }else {
            return "发布失败";
        }
    }


    /**
     * 当前的访问量+1
     * @param prid
     * @return
     */
    @GetMapping("/updateProContentPrClickByPrid")
    public int updateProContentPrClickByPrid(Integer prid){
        return proContentServer.updateProContentPrClickByPrid(prid);
    }








    /**
     * 上传图片到指定路径
     * @param file
     * @param req
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @PostMapping("/uploads")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {

        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
        }
    String path = "D:\\All_Project\\IDEA_Project\\bcy-imitation\\src\\main\\resources\\static\\img";
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        file.transferTo(filePath);
        return "success";
    }



}
