package com.example.bcyimitation.mapper.Pro;

import com.example.bcyimitation.pojo.*;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.pojo.Pro.ProIndex;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface ProContentMapper {


    /**
     * 分页查询首页单个图片
     * @param paging
     * @return
     */
   /* @Select("select * from pro_content as pro,user as u where pro.uid=u.uid order by prid desc limit #{pageStart},#{pageSize}")
    List<Pro_content> selectProPage(Paging paging);
*/
    @Select("select * from pro_content as pro,user as u" +
            " where pro.uid=u.uid order by prid desc limit #{pageStart},#{pageSize}")
    List<ProIndex> selectProPage(Paging paging);

    @Select("select count(*) from pro_content;")
    int allCountPro();


    /**
     * 插入一条作品内容
     * @param proContent
     * @return
     */
    @Transactional
    @Insert("INSERT INTO `bcy`.`pro_content` (`uid`, `prid`, `pr_info`,`pr_img`, `pr_date`, `pr_givelike`)" +
            " VALUES (#{uid}, default, #{prInfo},#{prImg},#{prDate}, #{prGivelike});")
    int insertProContent(ProContent proContent);

    /**
     * 根据插入的时间找到当前刚刚插入的prid是多少
     * @param date
     * @return int
     */
    @Select("select prid from pro_content where pr_date=#{date};")
    int searchPridByPrDate(String date);

    /**
     * 查找刚刚插入数据库中的prid
     * @return
     */
    @Select("select prid from pro_content order by prid desc limit 1;")
    int selectPirdByNewest();


    @Select("select * from pro_content where prid=#{prid}")
    ProContent selectProContentByPrid(int prid);

    /**
     * 查询所有的作品内容
     * @return
     */
    @Select("select * from pro_content")
    List<ProContent> selectAllProContentByPrid();

    /**
     * 根据prid来更新pro_content表中图片的个数
     * @param prid
     * @return
     */
    @Update("update pro_content set pr_img_count " +
            "=(select count(*) from pro_imgs where prid = #{prid}) where prid=#{prid};")
    int updateProContentImgCount(int prid);

    /**
     * 根据prid来改变点赞的值
     * @param proContent
     * @return
     */
    @Update("update pro_content set pr_givelike = #{prGivelike} where prid = #{prid};")
    int updateProContentPrClickByPrid(ProContent proContent);

    /**
     * 根据prid来查询当前作品的点赞数
     * @param prid
     * @return
     */
    @Select("select pr_givelike from pro_content where prid = #{prid};")
    int selectProContentPrClickByPrid(Integer prid);

    /**
     * 根据uid来查找自己所有作品的总点赞数
     * @param uid
     * @return
     */
    @Select(" select count(pr.pr_givelike) from pro_content as pr,user as u" +
            " where pr.uid=u.uid and u.uid=#{uid};")
    int countProContentPrGivelike(Integer uid);

    /**
     * 根据prid来改变当前上传的图片作品的首图内容
     * @param proContent
     * @return
     */
    @Update("update pro_content set pr_img=#{prImg} where prid=#{prid};")
    int updatePrImgByPrid(ProContent proContent);


    /**
     * 根据用户当前收藏的里面的作品的id来查
     * 找所有的id
     * @param prid
     * @return
     */
    @Select("select * from user_collect as uc,pro_content as pr " +
            "where uc.prid=pr.prid and uc.prid=#{prid} group by pr.prid;")
    ProContent selectProContentByUserCollectUid(Integer prid);

    /**
     * 根据用户当初点赞里面的作品id来查找
     * @param prid
     * @return
     */
    @Select("select * from user_givelike as ug,pro_content as pr " +
            "where ug.prid = pr.prid and ug.prid = #{prid} group by pr.prid;")
    ProContent selectProContentByUserGivelikePrid(Integer prid);


    /**
     * 改变当前作品的评论数
     * @param proContent
     * @return
     */
    @Update("update `bcy`.`pro_content` SET  `pr_comment_count`=#{prCommentCount} WHERE (`prid`=#{prid});")
    int updateProContentPrCommentCountByPrid(ProContent proContent);

    /**
     * 根据prid来修改一个作品的点赞数目
     * @param proContent
     * @return
     */
    @Update("update pro_content set pr_givelike = #{prGivelike} where prid = #{prid};")
    int updateProContentPrGivelikeByUserGivelike(ProContent proContent);

    /**
     * 当前点赞已经点赞了！
     * @param prid
     * @return
     */
    @Update("update pro_content set pr_is_givelike = 1 where prid = #{prid};")
    int addProContetPrIsGivelikeByPrid(Integer prid);

    /**
     * 当前点赞没有点赞了！
     * @param prid
     * @return
     */
    @Update("update pro_content set pr_is_givelike = 0 where prid = #{prid};")
    int removeProContetPrIsGivelikeByPrid(Integer prid);

    /**
     * 根据prid来修改点赞数量
     * @param proContent
     * @return
     */
    @Update(" update pro_content set pr_click = #{prClick} where prid = #{prid};")
    int addProContentPrClickByPrid(ProContent proContent);


    /**
     * 根据prid来查找当前作品的访问量
     * @param prid
     * @return
     */
    @Select("select pr_click from pro_content where prid = #{prid};")
    int selectProContentPrClicksByPrid(Integer prid);

    /**
     * 根据用户的uid来查找当前用户的所有发布的图片内容
     * @param uid
     * @return
     */
    @Select("select * from pro_content as pr,user as u where pr.uid=u.uid and u.uid = #{uid};")
    List<ProIndex> selectProIndexByUserUid(Integer uid);

    /**
     * 计算出当前有多少图片作品发布
     * @return
     */
    @Select(" select count(*) from pro_content as pr,user as u " +
            "where pr.uid=u.uid order by pr_givelike desc;")
    int countProIndexByProContentPrGivelike();



}
