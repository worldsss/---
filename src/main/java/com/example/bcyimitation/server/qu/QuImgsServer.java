package com.example.bcyimitation.server.qu;

import org.apache.ibatis.annotations.Insert;

public interface QuImgsServer {

    /**
     * 插入一条
     * @param quid
     * @param img
     * @return
     */
    int insertQuImg(String img);

}
