package com.example.bcyimitation.server.Pc;

public interface PcContentServer {

    /**
     * 插入一个文字作品内容
     * @param uid
     * @param pc_content
     * @param pc_title
     * @param pc_info
     * @param pc_firstImg
     * @return
     */
    int insertPcContent(Integer uid,String pc_content, String pc_title,
                        String pc_info,String pc_firstImg);


    /**
     * 修改作品的点赞数
     * @param pcGivelike
     * @param pcid
     * @return
     */
    int updatePcContentPcGivelikeByUserGivelike(Integer pcGivelike,Integer pcid);



    /**
     * 修改文字作品的访问数量
     * @param pcid
     * @return
     */
    int updatePcContentPcClickByPcid(Integer pcid);






}
