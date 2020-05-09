package com.example.bcyimitation.server.Pc;

import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pc.PcIndex;
import org.apache.ibatis.annotations.Insert;

public interface PcIndexServer {

    /**
     * 分页显示首页内容
     * @param pageSize
     * @param pageNumber
     * @return
     */
    Paging selectPcIndexByPaging(int pageSize, int pageNumber);

    /**
     * 根据pcid来展示对应的文字作品内容
     * @param pcid
     * @return
     */
    PcIndex selectPcIndexByPcid(int pcid);

    /**
     * 根据tid（圈子）来分页展示文字作品内容
     * @param pageSize
     * @param pageNumber
     * @param tid
     * @return
     */
    Paging selectPcIndexByTidPaging(int pageSize,int pageNumber,int tid);

    /**
     * 根据用户uid来分页展示文字作品的内容
     * @param pageSize
     * @param pageNumber
     * @param uid
     * @return
     */
    Paging selectPcIndexByUidPaging(int pageSize, int pageNumber, Integer uid);



}
