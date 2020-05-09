package com.example.bcyimitation.mapper.Pro;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProTags;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProTagsMapper {

  @Insert("insert into pro_tags values(#{tid},#{prid});")
  int insertProTags(ProTags proTags);

  /*  @Select("select tid from pro_content,pro_tags " +
  "where pro_content.prid=pro_tags.prid " +
  "order by pro_content.prid desc limit #{pageStart},#{pageSize};")*/

  /**
   * 显示所有的与主关系表的内容
   * @param paging
   * @return
   */
  @Select(
      "select pt.prid,pt.tid from pro_tags as pt,pro_content as pr"
          + " where pt.prid=pr.prid order by pt.prid desc limit  #{pageStart},#{pageSize}")
  List<ProTags> selectAllProTid(Paging paging);

  /**
   * 根据圈子的不同来显示关系表
   * @return
   */
  @Select(" select pt.prid,pt.tid from pro_tags as pt,pro_content as pr " +
          "where pt.prid=pr.prid and pt.tid=#{id} " +
          " order by pt.prid desc limit  #{pageStart},#{pageSize};")
  List<ProTags> selectProTibByTid();

  /**
   * 根据用户id来查询当前的标签
   * @param paging
   * @return
   */
  @Select("select pt.prid,pt.tid from pro_tags as pt,pro_content as pr,user as u " +
          "where pt.prid=pr.prid and pr.uid=u.uid and u.uid=#{id} " +
          "order by pt.prid desc limit  #{pageStart},#{pageSize};")
  List<ProTags> selectProTagsByUid(Paging paging);

  /**
   * 根据当前的Prid来展示所有的pro_tags
   *
   * @param prid
   * @return
   */
  @Select(
      "select pt.prid,pt.tid from pro_tags as pt,pro_content as pr"
          + " where pt.prid=pr.prid and pr.prid = #{prid}")
  List<ProTags> selectAllProTagsByPrid(int prid);

  /**
   * 展示全部的pro_tags
   *
   * @return
   */
  @Select("select * from pro_tags;")
  List<ProTags> selectAllProTags();

  /**
   * 算出圈子的成员数，也就是多少个打tag的作品总数
   * @param tid
   * @return
   */
  @Select("select count(prid) from pro_tags where tid=#{tid};")
  int countAllPridByTid(Integer tid);

  /**
   * 根据用户uid来查找对应的所有pro_tags
   * @param uid
   * @return
   */
  @Select("select * from pro_tags as pt,pro_content as pr" +
          " where pr.prid = pt.prid and pr.uid = #{uid};")
  List<ProTags> selectProTagByProContentUid(Integer uid);

  /**
   * 对点赞进行排序
   * @param paging
   * @return
   */
  @Select("select * from pro_tags as pt,pro_content as pr " +
          "where pt.prid=pr.prid order by pr.pr_givelike desc limit  #{pageStart},#{pageSize};")
  List<ProTags> selectProTagByProContentOrderByPrGivelike(Paging paging);


}
