package com.example.bcyimitation.mapper.Pc;

import com.example.bcyimitation.pojo.Pc.PcContent;
import com.example.bcyimitation.pojo.Pro.ProContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PcContentMapper {

    @Insert(" INSERT INTO `bcy`.`pc_content` (`uid`, `pcid`, `pc_content`, `pc_title`, `pc_info`,`pc_firstImg`)" +
            " VALUES (#{uid}, default, #{pcContent},#{pcTitle},#{pcInfo},#{pcFirstImg});")
    int insertPcContent(PcContent pcContent);


    @Select(" select pcid from pc_content order by pcid desc limit 1;")
    int searchNewestPcid();




    /**
     * 根据pcid来查找当前作品的访问量
     * @param pcid
     * @return
     */
    @Select("select pc_click from pc_content where pcid = #{pcid};")
    int selectPcContentPcClicksByPcid(Integer pcid);


    /**
     * 根据prid来修改点赞数量
     * @param pcContent
     * @return
     */
    @Update(" update pc_content set pc_click = #{pcClick} where pcid = #{pcid};")
    int addPcContentPcClickByPcid(PcContent pcContent);


    /**
     * 当前点赞没有点赞了！
     * @param pcid
     * @return
     */
    @Update("update pc_content set pc_is_givelike = 0 where pcid = #{pcid};")
    int removePcContetPcIsGivelikeByPcid(Integer pcid);


    /**
     * 当前点赞已经点赞了！
     * @param pcid
     * @return
     */
    @Update("update pc_content set pc_is_givelike = 1 where pcid = #{pcid};")
    int addPcContetPcIsGivelikeByPcid(Integer pcid);


    /**
     * 根据prid来修改一个作品的点赞数目
     * @param pcContent
     * @return
     */
    @Update("update pc_content set pc_givelike = #{pcGivelike} where pcid = #{pcid};")
    int updatePcContentPcGivelikeByUserGivelike(PcContent pcContent);


}
