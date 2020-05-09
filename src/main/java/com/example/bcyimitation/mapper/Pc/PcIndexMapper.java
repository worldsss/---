package com.example.bcyimitation.mapper.Pc;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pc.PcIndex;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PcIndexMapper {

  /**
   * 分页查询所有的pro_index
   * @param paging
   * @return
   */
  @Select(
      " select * from pc_content,user "
          + "where pc_content.uid=user.uid order by pcid desc limit #{pageStart},#{pageSize}")
  List<PcIndex> selectPcIndexPaging(Paging paging);

  /**
   * 计算出来分页后的总数
   * @return
   */
  @Select("select count(*) from pc_content,user where pc_content.uid=user.uid")
  int selectCountPcIndex();

  /**
   * 根据prid来查询的pc_index
   * @param pcid
   * @return
   */
  @Select(
      " select * from pc_content,user "
          + "where pc_content.uid=user.uid and pc_content.pcid=#{pcid}")
  PcIndex selectPcIndexByPcid(int pcid);


  /**
   * 根据tid圈子来分页展示文字作品
   * @param paging
   * @return
   */
  @Select(" select * from user as u,pc_content as pt,pc_tags as ps " +
          "where u.uid=pt.uid and pt.pcid = ps.pcid and ps.tid = #{id} " +
          "order by pt.pcid desc limit #{pageStart},#{pageSize};")
  List<PcIndex> selectPcIndexByTidPaging(Paging paging);


  /**
   * 根据tid圈子来计算出来当前有多少文字作品
   * @param tid
   * @return
   */
  @Select(" select count(*) from user as u,pc_content as pt,pc_tags as ps " +
          "where u.uid = pt.uid and pt.pcid = ps.pcid and ps.tid = #{tid};")
  int countPcIndexByTid(Integer tid);


  /**
   * 根据用户uid来展示当前用户的所有文字作品
   * @param paging
   * @return
   */
  @Select("select * from pc_content as pt,user as u " +
          "where pt.uid = u.uid and u.uid = #{id} " +
          "order by pcid desc limit #{pageStart},#{pageSize};")
  List<PcIndex> selectPcIndexByUidPaging(Paging paging);


  /**
   * 根据用户uid来计算出来当前用户发布了多少文字作品
   * @param uid
   * @return
   */
  @Select("select count(*) from pc_content as pt,user as u " +
          "where pt.uid = u.uid and u.uid = #{uid};")
  int countPcIndexByUid(Integer uid);


}
