package com.example.bcyimitation.mapper.qu;

import com.example.bcyimitation.pojo.qu.Qu_imgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuImgsMapper {

      @Select("select * from qu_imgs where quid = #{quid}")
      List<Qu_imgs> selectQuImg(int quid);

      @Insert("INSERT INTO `bcy`.`qu_imgs` (`imgid`, `img`, `quid`) VALUES (default,#{img},#{quid});")
      int insertQuImg(Qu_imgs quImgs);

      @Select("select * from qu_imgs")
      List<Qu_imgs> selectAllQuImg();

}
