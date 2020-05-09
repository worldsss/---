package com.example.bcyimitation.pojo.User;


import lombok.Data;
import org.apache.ibatis.annotations.Insert;

/**
 * 用户的收藏表
 */
@Data
public class UserCollect {

    private Integer ucid;
    //用户id
    private Integer uid;

    /**收藏图片作品的prid**/
    private Integer prid;

    /**收藏文字作品的pcid**/
    private Integer pcid;

}
