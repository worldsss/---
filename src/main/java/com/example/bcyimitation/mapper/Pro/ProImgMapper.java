package com.example.bcyimitation.mapper.Pro;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProImgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProImgMapper {

    /**
     * 根据prid来插入多个图片
     * @param proImgs
     * @return
     */
    @Insert("insert into pro_imgs values(default,#{prid},#{img});")
    int insertImgsByPrid(ProImgs proImgs);


    @Select("select * from pro_imgs where prid=#{prid}")
    List<ProImgs> selectImgsByPrid(int prid);

    /**
     * 查询全部的pro_imgs
     * @return
     */
    @Select("select * from pro_imgs")
    List<ProImgs> selectAllImgs();

    /**
     * 分页查询出Pro_imgs
     * @return
     */
    @Select("select * from pro_imgs order by piid desc limit #{pageStart},#{pageSize};")
    List<ProImgs> selectImgsPaging(Paging paging);


    /**
     * 根据圈子来显示作品内容
     * @param paging
     * @return
     */
    @Select(" select piid,pi.prid,pi.img from pro_imgs as pi,pro_content as pr,pro_tags as pt " +
            "where pi.prid=pr.prid and pt.prid=pr.prid and pt.tid=#{id} " +
            "order by pr.prid desc limit #{pageStart},#{pageSize};")
    List<ProImgs> selectImgsPagingByTid(Paging paging);

    /**
     * 计算出所有的图片总数
     * @return
     */
    @Select("select count(*) from pro_imgs;")
    int selectCountAllProImgs();

    /**
     * 根据uid来分页展示作品的图片
     * @param paging
     * @return
     */
    @Select("select pi.piid,pi.prid,pi.img from pro_content as pr,pro_imgs as pi,user as u " +
            "where pr.prid=pi.prid and pr.uid=u.uid and u.uid=#{id} " +
            "order by piid desc limit #{pageStart},#{pageSize};")
    List<ProImgs> selectImgsPaginByUid(Paging paging);


    /**
     * 根据作品的用户uid来查询出来所有的图片信息
     * @param uid
     * @return
     */
    @Select(" select * from pro_imgs as pi,pro_content as pr " +
            "where pr.prid = pi.prid and pr.uid = #{uid};")
    List<ProImgs> selectProImgsByProContentUid(Integer uid);

    /**
     * 根据点赞的多少来对图片作品进行排序
     * @return
     */
    @Select(" select * from pro_imgs as pi,pro_content as pr " +
            "where pr.prid=pi.prid order by pr.pr_givelike desc limit #{pageStart},#{pageSize};")
    List<ProImgs> selectProImgsByProContentOrderByPrGivelike(Paging paging);


}
