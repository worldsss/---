package com.example.bcyimitation.severimpl.Pro;

import com.example.bcyimitation.mapper.Pro.ProContentMapper;
import com.example.bcyimitation.mapper.Pro.ProImgMapper;
import com.example.bcyimitation.mapper.User.UserCollectMapper;
import com.example.bcyimitation.mapper.User.UserGivelikeMapper;
import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.pojo.Pro.ProImgs;
import com.example.bcyimitation.pojo.User.UserCollect;
import com.example.bcyimitation.pojo.User.UserGivelike;
import com.example.bcyimitation.server.Pro.ProContentServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProContentServerImpl implements ProContentServer {

    @Resource
    private ProContentMapper proContentMapper;

    @Resource
    private ProImgMapper proImgMapper;

    @Resource
    private UserCollectMapper userCollectMapper;

    @Resource
    private UserGivelikeMapper userGivelikeMapper;

    @Override
    public List<ProContent> selectProContent() {
        return null;
    }

    /**
     * 在首页展示单图片作品
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public Paging selectProPage(int pageSize, int pageNumber) {

        Paging paging = new Paging();

        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));


        int count = proContentMapper.allCountPro();
        paging.setTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        paging.setList(proContentMapper.selectProPage(paging));

        return paging;
    }


    /**
     * 插入一条单个图片内容
     *
     * @param pr_info
     * @param pr_img
     * @param pr_date
     * @param pr_givelike
     * @return
     */
//    @Transactional
    @Override
    public int insertProContent(Integer uid, String pr_info, String pr_img, String pr_date, int pr_givelike) {

        ProContent proContent = new ProContent();

      /*  Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        String newDataa = simpleDateFormat.format(date);*/
        proContent.setUid(uid);
        proContent.setPrDate(pr_date);
        proContent.setPrImg(pr_img);
        proContent.setPrInfo(pr_info);
        proContent.setPrGivelike(pr_givelike);


        return proContentMapper.insertProContent(proContent);
    }

    /**
     * 根据时间来寻找刚刚插入的prid,(已废弃)
     *
     * @param date
     * @return
     */
    @Override
    public int searchPridByPrDate(String date) {
        return proContentMapper.searchPridByPrDate(date);
    }

    /**
     * 根据sql语句的limit来茶轴最新的prid
     *
     * @return
     */
    @Override
    public int searchPridByNewest() {
        return proContentMapper.selectPirdByNewest();
    }

    /**
     * 根据prid来查找pro_content
     *
     * @param prid
     * @return
     */
    @Override
    public ProContent selectProContentByPrid(int prid) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        String newDataa = simpleDateFormat.format(date);

        ProContent proContent = new ProContent();
        //获取到pro_imgs之后赋值到pro_content中
        List<ProImgs> pro_imgs = proImgMapper.selectImgsByPrid(prid);

        proContent.setProImgs(pro_imgs);
        //把根据prid获得到的值先存放到proContent1中，再由proContent1里放入新的对象中
        ProContent proContent1 = proContentMapper.selectProContentByPrid(prid);
        proContent.setPrGivelike(proContent1.getPrGivelike());
        proContent.setPrInfo(proContent1.getPrInfo());
        proContent.setCid(proContent1.getCid());
        proContent.setUid(proContent1.getUid());
        proContent.setPrid(prid);
        proContent.setPrDate(newDataa);


        return proContent;
    }


    @Override
    public List<ProContent> selectAllProContent() {


        List<ProContent> pro_contents = proContentMapper.selectAllProContentByPrid();


        return pro_contents;
    }


    @Override
    public int updateProContentImgCount() {

        //获取刚刚插入数据库中的值，开始改变它的图片个数
        int prid = proContentMapper.selectPirdByNewest();

        return proContentMapper.updateProContentImgCount(prid);
    }

    /**
     * 点赞数++
     *
     * @param prid
     * @return
     */
    @Override
    public int addProContentPrClickByPrid(Integer prid) {

        //当前的点赞数
        int nowPrClick = proContentMapper.selectProContentPrClickByPrid(prid);
        //每次点击点赞数++
        nowPrClick++;
        ProContent proContent = new ProContent();
        proContent.setPrid(prid);
        proContent.setPrGivelike(nowPrClick);

        return proContentMapper.updateProContentPrClickByPrid(proContent);
    }

    /**
     * 点赞数--
     *
     * @param prid
     * @return
     */
    @Override
    public int lessProContentPrGivelikeByPrid(Integer prid) {

        //当前的点赞数
        int nowPrClick = proContentMapper.selectProContentPrClickByPrid(prid);
        //每次点击点赞数--
        nowPrClick--;
        ProContent proContent = new ProContent();
        proContent.setPrid(prid);
        proContent.setPrGivelike(nowPrClick);

        return proContentMapper.updateProContentPrClickByPrid(proContent);
    }


    /**
     * 修改图片作品的首个图片信息
     *
     * @param pr_img
     * @param prid
     * @return
     */
    @Override
    public int updateProImgByPrid(String pr_img, Integer prid) {

        ProContent proContent = new ProContent();
        proContent.setPrid(prid);
        proContent.setPrImg(pr_img);
        //修改图片作品首图的信息
        return proContentMapper.updatePrImgByPrid(proContent);
    }

    /**
     * 根据当前用户收藏-作品表的来查找所有的收藏作品
     * @param uid
     * @return
     */
    @Override
    public List<ProContent> selectProContentByUserCollectUid(Integer uid) {

        //获取当前用户收藏的全部表，包括了prid
        List<UserCollect> userCollectList = userCollectMapper.selectUserCollectByUid(uid);

        List<ProContent> proContentList = new ArrayList<>();

        for (UserCollect userCollect : userCollectList) {

            ProContent proContent = proContentMapper.selectProContentByUserCollectUid(userCollect.getPrid());
            //用用户收藏表中的prid来求出分别的内容再赋值到pro_content的list中
            if (proContent != null) {
                proContentList.add(proContent);
            }
            System.out.println("用户收藏的所有内容" + proContentList);
        }


        return proContentList;
    }

    @Override
    public List<ProContent> selectProContentByUserGivelikeUid(Integer uid) {
        //获取当前用户点赞的全部表，包含了prid
        List<UserGivelike> userGivelikeList = userGivelikeMapper.selectUserGivelikeByUid(uid);

        List<ProContent> proContentList = new ArrayList<>();
        for (UserGivelike userGivelike:userGivelikeList) {
            ProContent proContent = proContentMapper.selectProContentByUserGivelikePrid(userGivelike.getPrid());
            if (proContent!=null){
                proContentList.add(proContent);
            }
        }

        return proContentList;
    }

    @Override
    public Paging selectProContentByUserCollectUidPaging(Integer uid, int pageSize, int pageNumber) {
        Paging paging = new Paging();

        paging.setId(uid);
        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));


        int count = userCollectMapper.countUserCollectByUid(uid);
        paging.setTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        //获取当前用户收藏的全部表，包括了prid
        List<UserCollect> userCollectList = userCollectMapper.selectUserCollectByUidPaging(paging);

        List<ProContent> proContentList = new ArrayList<>();

        for (UserCollect userCollect : userCollectList) {

            //用用户收藏表中的prid来求出分别的内容再赋值到pro_content的list中
            proContentList.add(proContentMapper.selectProContentByUserCollectUid(userCollect.getPrid()));

        }


        paging.setList(proContentList);

        return paging;
    }


    /**
     * 修改用户的点赞数
     *
     * @param prGivelike
     * @param prid
     * @return
     */
    @Override
    public int updateProContentPrGivelikeByUserGivelike(Integer prGivelike, Integer prid) {

        ProContent proContent = new ProContent();
        proContent.setPrGivelike(prGivelike);
        proContent.setPrid(prid);

        return proContentMapper.updateProContentPrGivelikeByUserGivelike(proContent);
    }

    /**
     * 当前作品的访问量+1
     *
     * @param prid
     * @return
     */
    @Override
    public int updateProContentPrClickByPrid(Integer prid) {

        ProContent proContent = new ProContent();
        int i = proContentMapper.selectProContentPrClicksByPrid(prid);
        i++;
        proContent.setPrClick(i);
        proContent.setPrid(prid);
        return proContentMapper.addProContentPrClickByPrid(proContent);
    }


}
