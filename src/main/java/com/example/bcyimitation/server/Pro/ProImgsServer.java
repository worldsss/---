package com.example.bcyimitation.server.Pro;

import com.example.bcyimitation.pojo.Paging;

public interface ProImgsServer {


    /**
     * 插入多个图片
     * @param prid
     * @param img
     * @return
     */
    int insertImgsByPrid(String img);



    Paging selectProImgsPaging(int pageSize, int pageNumber);


}
