package com.example.bcyimitation.mapper.Pc;

import com.example.bcyimitation.pojo.Pc.PcComment;
import com.example.bcyimitation.pojo.Pc.PcComs;
import com.example.bcyimitation.pojo.Pro.ProComment;
import com.example.bcyimitation.pojo.Pro.ProComs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PcComsMapper {
    /**
     * 插入一条作品-评论关系表
     * @param pcComs
     * @return
     */
    @Insert(" INSERT INTO `bcy`.`pc_coms` (`pccid`, `pcid`, `ccid`)" +
            " VALUES (default, #{pcid},#{ccid});")
    int insertPcComs(PcComs pcComs);

    /**
     * 根据prid来查询出所有的cid
     * @param pcid
     * @return
     */
    @Select(" select ccid from pc_coms where pcid=#{pcid};")
    List<PcComment> selectAllCcidByPcid(Integer pcid);

    /**
     * 根据prid来查询出所有的cid
     * @param pcid
     * @return
     */
    @Select(" select * from pc_coms where pcid=#{pcid};")
    List<PcComment> selectPcCommentByPcid(Integer pcid);

}
