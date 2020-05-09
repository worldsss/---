package com.example.bcyimitation.mapper.Pro;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.pojo.Pro.ProIndex;
import com.example.bcyimitation.pojo.Pro.ProTags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProIndexMapper {

  /**
   * 分页查询
   * @param paging
   * @return
   */
  @Select(
      "select * from pro_content as pr,user as u "
          + "where pr.uid=u.uid order by prid desc limit #{pageStart},#{pageSize}")
  List<ProIndex> selectIndex(Paging paging);

  @Select("select count(*) from pro_content as pr,user as u where pr.uid=u.uid")
  int CountIndex();

  /* @Select("" +
          "")
  List<Pro_index> selectIndex();*/

  @Select("select * from pro_content where prid = #{prid};")
  ProIndex selectIndexById(ProIndex proIndex);
/*

  @Select("select * from pro_content")
  List<Pro_index> selectIndex();

*/
//  @Select("select * from pro_content where prid = #{prid};")
  @Select("select * from pro_content as pr,user as u where pr.uid=u.uid and pr.prid = #{prid};")
  ProIndex selectIndexTagById(int prid);


  //    @Select("select tid from pro_content,pro_tags where pro_content.prid=pro_tags.prid and pro_content.prid=1;")
  @Select("select tid from pro_content,pro_tags where pro_content.prid=pro_tags.prid")
  List<ProTags> selectAllProTid();

  //在尝试了分页中添加时不行，准备先查询全部的情况下来显示
  @Select("select * from pro_content as pr,user as u where pr.uid=u.uid order by prid desc")
  List<ProIndex> selectAllProIndex();

  /**
   * 根据uid来查询有多少个作品，图片内容
   * @param paging
   * @return
   */
  @Select("select * from pro_content,user " +
          "where pro_content.uid=user.uid and user.uid=#{id} " +
          "order by prid desc limit  #{pageStart},#{pageSize};")
  List<ProIndex> selectProIndexByUid(Paging paging);


  /**
   * 根据tid根据不同的标签来显示全部的内容，圈子的展示
   * @return
   */
  @Select(" select * from user as u,pro_content as pr,pro_tags as pt " +
          "where u.uid=pr.uid and pr.prid=pt.prid and pt.tid=#{tid};")
  List<ProIndex> selectProIndexByProTagsTid(Integer tid);


  /**
   * 根据tid根据不同的标签来分页显示的内容，圈子的展示
   * @return
   */
  @Select(" select * from user as u,pro_content as pr,pro_tags as pt " +
          "where u.uid=pr.uid and pr.prid=pt.prid and pt.tid=#{id} " +
          "order by pr.prid desc limit  #{pageStart},#{pageSize};")
  List<ProIndex> selectProIndexByTid(Paging paging);

  /**
   * 根据tid根据不同的标签来分页的总个数
   * @return
   */
  @Select(" select count(*) from user as u,pro_content as pr,pro_tags as pt" +
          " where u.uid=pr.uid and pr.prid=pt.prid and pt.tid=#{tid};")
  int countProIndexByTid(Integer tid);


  /**
   * 根据uid来插询对应的图片作品内容，分页
   * @param paging
   * @return
   */
  @Select(" select * from pro_content as pr,user as u " +
          "where pr.uid=u.uid and  pr.uid= 8 order by prid desc limit #{pageStart},#{pageSize};")
  List<ProIndex> selectProIndexByUserUid(Paging paging);


  /**
   * 根据点赞的多少来对图片作品进行排序
   * @param paging
   * @return
   */
  @Select("select * from pro_content as pr,user as u " +
          "where pr.uid=u.uid order by pr_givelike desc limit #{pageStart},#{pageSize};")
  List<ProIndex> selectProIndexOrderByProContentPrGivelike(Paging paging);


  /**
   * 根据作品的点赞数量进行排序
   * @return
   */
  @Select("select * from pro_content as pr,user as u " +
          "where pr.uid=u.uid order by pr_givelike desc#{pageStart},#{pageSize};")
  List<ProIndex> selectProIndexByProContentPrGivelike();


  /**
   * 当前cos的排行榜前七位
   * @return
   */
  @Select(" select * from user as u,pro_content as pr,pro_tags as pt " +
          "where u.uid=pr.uid and pr.prid = pt.prid and pt.tid = 11 " +
          "order by pr.pr_givelike desc limit 7;")
  List<ProIndex> selectProContentOrderByPrGivelikeLimitSeven();

}
