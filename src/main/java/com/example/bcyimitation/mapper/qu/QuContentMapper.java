package com.example.bcyimitation.mapper.qu;

import com.example.bcyimitation.pojo.qu.Qu_content;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuContentMapper {

    @Insert(" INSERT INTO `bcy`.`qu_content` (`quid`, `uid`, `qu_text`, `qu_info`)" +
            " VALUES (default, '1', #{qu_text}, #{qu_info});")
    int insertQuContent(Qu_content qu_content);

    /**
     * 获取当前数据库中最新的prid
     * @return
     */
    @Select("select quid from qu_content order by quid desc limit 0,1;")
    int selectNewest();

    @Select("select * from qu_content where quid=#{quid};")
    Qu_content selectQuContentByQuid(Qu_content qu_content);

    @Select("select * from qu_content;")
    List<Qu_content> selectAllQuContent();


}
