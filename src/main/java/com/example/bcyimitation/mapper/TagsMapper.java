package com.example.bcyimitation.mapper;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Tags;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TagsMapper {

    /**
     * 插入一个标签
     * @param tags
     * @return
     */
    @Insert(" INSERT INTO `bcy`.`tags` " +
            "(`tid`, `tags_name`, `tags_recom`, `tags_img`, `tags_latest_time`, `tags_hot`) " +
            "VALUES (default, #{tagsName}, 'false', " +
            "'https://p9-bcy.byteimg.com/img/banciyuan/bcy-static/277fcb8b123143b78c5fb8d31eeee8ff.png~tplv-banciyuan-2X2.image'," +
            " '0', '0');")
    int insertTag(Tags tags);

    /**
     * 查找刚刚插入进数据库中的tid
     * @return
     */
    @Select(" select tid from tags order by tid desc limit 1;")
    int selectNewestTagTid();


    /**
     * 根据首页的图片的mid来查询有多少个标签
     * @param mid
     * @return
     */
    @Select("select tags_name from pro_tags as pt,tags as t where pt.tid=t.tid and mid = #{mid};")
    List<Tags> selectTags(int mid);

    /**
     * 根据prid来查询有多少个标签
     * @param prid
     * @return
     */
    @Select("select * from tags,pro_tags where tags.tid=pro_tags.tid and pro_tags.prid=#{prid};")
    List<Tags> selectTagsByPrid(int prid);

    /**
     * 根据pcid来查询有多少个标签
     * @param pcid
     * @return
     */
    @Select("select * from pc_tags as pc,tags as t where pc.tid=t.tid and pc.pcid=#{pcid};")
    List<Tags> selectTagsByPcid(Integer pcid);


    /**
     * 显示推荐的标签
     * @return
     */
    @Select("select * from tags where tags_recom='true';")
    List<Tags> selectTagsByRecom();

    @Select("select * from tags order by tid desc limit #{pageStart},#{pageSize};")
    List<Tags> selectTagsPaging(Paging paging);


//    List<Tags> selectTagsByP

    /**
     * 根据tid来返回当前标签的名字
     * @param tid
     * @return
     */
    @Select("select * from tags where tid=#{tid};")
    Tags selectProTagName(Integer tid);

    /**
     * 根据tid来计算出圈子里所有的作品个数
     * @param tid
     * @return
     */
    @Select(" select count(*) from pro_content as pr,pro_tags as pt,tags as t " +
            "where pr.prid = pt.prid and t.tid = pt.tid and t.tid = #{tid};")
    int countAllProContentByTid(Integer tid);

    /**
     * 每添加一个圈子中的作品，就让圈子中的tags_hot+1
     * @param tags
     * @return
     */
    @Update(" update tags set tags_hot = #{tags_hot} where tid = #{tid};")
    int updateTagsHotByTid(Tags tags);


    /**
     * 根据作品id来看当前的作品在几个圈子里，并求出tid来更新tags里面的最新时间
     * @param prid
     * @return
     */
    @Select(" select * from pro_tags as pt,tags as t where pt.tid=t.tid and pt.prid = #{prid};")
    List<Tags> selectAllTagsByProTagsPrid(Integer prid);


    /**
     * 每天上传一个作品，就用当前的时间来替代tags_latest_time最新的
     * @param tags
     * @return
     */
    @Update(" UPDATE `bcy`.`tags` SET  `tags_latest_time`=#{tags_latest_time} WHERE (`tid`=#{tid});")
    int updateTagsLatestTime(Tags tags);

    /**
     * 查询出最热门的五条圈子，也就是tags_hot最大的五个值
     * @return
     */
    @Select("select * from tags order by tags_hot desc limit 5;")
    List<Tags> selectTagsHotLimitFive();


}
