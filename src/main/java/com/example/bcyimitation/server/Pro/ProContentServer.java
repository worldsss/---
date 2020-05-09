package com.example.bcyimitation.server.Pro;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProContent;

import java.util.List;

public interface ProContentServer {


    /**
     * 查询出所有的作品内容
     * @return
     */
    List<ProContent> selectProContent();



    /**
     * 分页显示首页内容
     * @param pageSize
     * @param pageNumber
     * @return
     */
    Paging selectProPage(int pageSize, int pageNumber);


    /**
     * 插入一条内容
     * @param uid
     * @param prInfo
     * @param prImg
     * @param prDate
     * @param prGivelike
     * @return
     */
    int insertProContent(Integer uid,String prInfo,String prImg, String prDate, int prGivelike);


    /**
     * 根据日期找prid
     * @param date
     * @return
     */
    int searchPridByPrDate(String date);


    /**
     * 获取刚刚插入数据库中的值
     * @return
     */
    int searchPridByNewest();



    ProContent selectProContentByPrid(int prid);


    /**
     * 查询全部的pro_content包括图片
     * @return
     */
    List<ProContent> selectAllProContent();


    /**
     * 修改刚刚创建好的数据库中的图片个数
     * @param prid
     * @return
     */
    int updateProContentImgCount();

    /**
     * 点赞数+1
     * @param prid
     * @param pr_click
     * @return
     */
    int addProContentPrClickByPrid(Integer prid);

    /**
     * 点赞数-1
     * @param prid
     * @return
     */
    int lessProContentPrGivelikeByPrid(Integer prid);

    /**
     * 改变当前作品的首图，用于我的收藏中显示
     * @param prImg
     * @param prid
     * @return
     */
    int updateProImgByPrid(String prImg,Integer prid);

    /**
     * 根据当前用户收藏的prid来查找所有的图片作品
     * @param uid
     * @return
     */
    List<ProContent> selectProContentByUserCollectUid(Integer uid);

    /**
     * 根据当前用户点过赞的uid来查询所有的用户作品
     * @param uid
     * @return
     */
    List<ProContent> selectProContentByUserGivelikeUid(Integer uid);

    /**
     * 分页展示根据当前用户收藏的prid来查找所有的图片作品
     * @param uid
     * @param pageSize
     * @param pageNumber
     * @return
     */
    Paging selectProContentByUserCollectUidPaging(Integer uid,int pageSize, int pageNumber);

    /**
     * 修改作品的点赞数
     * @param prGivelike
     * @param prid
     * @return
     */
    int updateProContentPrGivelikeByUserGivelike(Integer prGivelike,Integer prid);


    /**
     * 修改作品的访问数量
     * @param prid
     * @return
     */
    int updateProContentPrClickByPrid(Integer prid);



}
