package com.example.bcyimitation.server.Pro;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.pojo.Pro.ProIndex;

import java.util.List;

public interface ProIndexServer {

    /**
     * 分页显示首页内容
     * @param pageSize
     * @param pageNumber
     * @return
     */
    Paging selectIndex(int pageSize, int pageNumber);

    /**
     * 根据uid来对作品分页，个人主页的作品展示
     *
     * @param pageSize
     * @param pageNumber
     * @param uid
     * @return
     */
    Paging selectProIndexByUid(int pageSize, int pageNumber, int uid);


    /**
     * 根据Prid来显示分页
     * @param prid
     * @return
     */
    ProIndex selectIndexById(int prid);

    /**
     * 根据prid来获得
     * @param prid
     * @return
     */
    ProIndex selectIndexTagsById(int prid);

    /**
     * 查询全部的作品内容
     * @return
     */
    List<ProIndex> selectAllIndex();


    /**
     * 根据标签的来展示全部的内容
     * @param tid
     * @return
     */
    List<ProIndex> selectAllIndexByTid(int tid);


    /**
     * 分页显示圈子图片内容
     * @param pageSize
     * @param pageNumber
     * @return
     */
    Paging selectProIndexByTid(int pageSize, int pageNumber,int tid);

    /**
     * 根据用户关注的用户来查询全部的图片内容
     * @param fid
     * @return
     */
    List<ProIndex> selectProIndexByUserUid(Integer fid);

    /**
     * 根据点赞的个数来对所有的图片作品进行排序
     * @param pageSize
     * @param pageNumber
     * @return
     */
    Paging selectProIndexOrderByProContentPrGivelike(int pageSize, int pageNumber);

    /**
     * 当前cos的排行榜前七位
     * @return
     */
    List<ProIndex> selectProContentOrderByPrGivelikeLimitSeven();


}
