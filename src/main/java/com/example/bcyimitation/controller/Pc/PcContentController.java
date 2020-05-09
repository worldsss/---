package com.example.bcyimitation.controller.Pc;

import com.example.bcyimitation.pojo.Pc.PcContent;
import com.example.bcyimitation.server.Pc.PcContentServer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author world
 */
@RestController
public class PcContentController {

    @Resource
    private PcContentServer pcContentServer;

    /**
     * 插入一个图片的作品
     * @param pcContent
     * @return
     */
    @PostMapping("/insertPcContent")
    public String insertPcContent(@RequestBody PcContent pcContent){

        String imgFromHtml = getImgFromHtml(pcContent.getPcContent());
        System.out.println(imgFromHtml);


//        String imgBigList = getImgStr(pcContent.getPc_content());


        int flag = pcContentServer.insertPcContent(pcContent.getUid(),pcContent.getPcContent(),
                pcContent.getPcTitle(),
                pcContent.getPcInfo(),imgFromHtml);
        if(flag==0){
            return "失败";
        }else {
            return "成功";
        }

    }


    /**
     * 上传图片的作品
     * @param file
     * @param req
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @PostMapping("/uploadsTextImg")
    @ResponseBody
    public String uploadTextImg(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {

        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
        }
        // 获取文件存储路径（绝对路径）
        //        String path = req.getServletContext().getRealPath("/img/file");
        //        String path =
        // "G:\\IDEA_Projects\\bcy-imitation\\src\\main\\java\\com\\example\\bcyimitation\\img";
        //        String path = "G:\\IDEA_Projects\\bcy-imitation\\src\\main\\resources\\static\\img";
        String path = "D:\\All_Project\\IDEA_Project\\bcy-imitation\\src\\main\\resources\\static\\img";
        // 获取原文件名
//        String fileName = file.getOriginalFilename();

        String fileNames=file.getOriginalFilename();

        /*String[] token = fileNames.split(".");

        String pf = token[1];*/

        String prefix=fileNames.substring(fileNames.lastIndexOf(".")+1);
        System.out.println(prefix);


        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        //获取格式化后的时间
        String newDataa = simpleDateFormat.format(date);
        Random random = new Random();
        int randoms = random.nextInt(100000);


        String fileName = newDataa+"-"+randoms+"."+prefix;

        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        file.transferTo(filePath);
//        return file+"";
        return fileName;
    }


    /**
     * 获取html字符串中第一张图片的路径
     * @param htmlcontent
     * @return
     */
    public static String getImgFromHtml(String htmlcontent){
        if(htmlcontent!=null){
            String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
            Pattern p_image = Pattern.compile(regEx_img,Pattern.CASE_INSENSITIVE);
            Matcher m_image = p_image.matcher(htmlcontent);
            if(m_image.find()){
                String img = m_image.group(0);
                Matcher m  = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
                if(m.find()){
                    if(m.group(0)!=null){
                        return m.group(0).substring(5, m.group(0).length()-1);
                    }
                }
            }
        }
        return "";
    }

    /**
     * 获取html字符串中所有图片
     * @param htmlStr
     * @return
     */
   /* public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }


        return pics;
    }
*/

    /**
     * 获取html字符串中所有图片
     * @param htmlStr
     * @return
     */
    public static String getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }

     /*   String firstImg = "";
        //第一种方法
        if(!pics.isEmpty()){
            System.out.println(pics.iterator().next());// 1.2
            firstImg = pics.iterator().next();
        }*/
        //第二种方法:将set集合转换成list集合 取第一个
        List list = new ArrayList(pics);
//        System.out.println(list.get(0));// 1.2


        return list+"";
    }


   /* Set<String> aa = StringTools.getImgStr(baike1.getContent());
        if (!aa.isEmpty()) {
        System.out.println(aa.iterator().next());//获取第一张
    }*/


    /**
     * 当前文字作品的访问量+1
     * @param pcid
     * @return
     */
    @GetMapping("/updatePcContentPcClickByPcid")
    public int updatePcContentPcClickByPcid(Integer pcid){
        return pcContentServer.updatePcContentPcClickByPcid(pcid);
    }





}
