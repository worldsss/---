package com.example.bcyimitation.mapper.Pc;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pc.PcTags;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PcTagsMapper {

    /**
     * 分页查询关系表
     * @param paging
     * @return
     */
    @Select("select pt.pcid,pt.tid from pc_content as pc,pc_tags as pt" +
            " where pc.pcid=pt.pcid order by pt.pcid desc limit  #{pageStart},#{pageSize};")
    List<PcTags> selectTagsPaging(Paging paging);


    /**
     * 插入一个文字作品的标签
     * @param pcTags
     * @return
     */
    @Insert("insert into pc_tags values(#{pcid},#{tid});")
    int insertPcTags(PcTags pcTags);

    /**
     * 根据tid(圈子)的来展示作品对应的标签
     * @param paging
     * @return
     */
    @Select("select * from user as u,pc_content as pt,pc_tags as ps " +
            "where u.uid=pt.uid and pt.pcid = ps.pcid and ps.tid = #{id} " +
            "order by pt.pcid desc limit #{pageStart},#{pageSize};")
    List<PcTags> selectPcTagsByTid(Paging paging);

    /**
     * 根据用户uid来展示当前用户所有文字作品的标签
     * @param paging
     * @return
     */
    @Select(" select * from pc_tags as ps,pc_content as pt,user as u " +
            "where pt.pcid=ps.pcid and pt.uid = u.uid and u.uid = #{id} " +
            "order by pt.pcid desc limit #{pageStart},#{pageSize};")
    List<PcTags> selectPcTagsByUid(Paging paging);


}
