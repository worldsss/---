package com.example.bcyimitation.mapper.Pro;

import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.pojo.Pro.ProComs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProComsMapper {

    /**
     * 插入一条作品-评论关系表
     * @param proComs
     * @return
     */
    @Insert(" INSERT INTO `bcy`.`pro_coms` (`prcoid`, `prid`, `cid`)" +
            " VALUES (default, #{prid},#{cid});")
    int insertProComs(ProComs proComs);

    /**
     * 根据prid来查询出所有的cid
     * @param prid
     * @return
     */
    @Select(" select cid from pro_coms where prid=#{prid};")
    List<ProComment> selectAllCidByPrid(Integer prid);

    /**
     * 根据prid来查询出所有的cid
     * @param prid
     * @return
     */
    @Select(" select * from pro_coms where prid=#{prid};")
    List<ProComment> selectProCommentByPrid(Integer prid);


}
