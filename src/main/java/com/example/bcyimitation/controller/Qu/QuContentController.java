package com.example.bcyimitation.controller.Qu;

import com.example.bcyimitation.pojo.qu.Qu_content;
import com.example.bcyimitation.server.qu.QuContentServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class QuContentController {

    @Resource
    private QuContentServer quContentServer;


    @PostMapping("/insertQuContent")
    @ResponseBody
//    public String insertQuContent(Integer quid,String qu_text, String qu_info){
    public String insertQuContent(@RequestBody Qu_content quContent){

        int flag = quContentServer.insertQuContent(quContent.getQu_text(), quContent.getQu_info());

        if(flag==0){
            return "失败";
        }else {
            return "成功";
        }

    }

    /**
     * 根据quid来获得某一个问题的回答
     * @param quid
     * @return
     */
    @GetMapping("/getQuContentByQuid")
    @ResponseBody
    public Qu_content getQuContentByQuid(int quid){


        return  quContentServer.selectQuContent(quid);
    }

    /**
     * 获取全部的问题的值，有内容有图片哈哈哈哈哈哈哈哈哈哈！！！
     * @return
     */
    @GetMapping("/getAllQuContent")
    @ResponseBody
    public List<Qu_content> selectAllQuContent(){



        return  quContentServer.selectAllQuContent();
    }





    /**
     * 上传问题的图片
     * @param file
     * @param req
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @PostMapping("/uploadQuImgs")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {

        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
        }
        // 获取文件存储路径（绝对路径）
        //        String path = req.getServletContext().getRealPath("/img/file");
        //        String path =
        // "G:\\IDEA_Projects\\bcy-imitation\\src\\main\\java\\com\\example\\bcyimitation\\img";
        //        String path = "G:\\IDEA_Projects\\bcy-imitation\\src\\main\\resources\\static\\img";
        String path = "D:\\All_Project\\IDEA_Project\\bcy-imitation\\src\\main\\resources\\static\\quimg";
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
