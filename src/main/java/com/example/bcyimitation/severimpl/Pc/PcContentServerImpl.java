package com.example.bcyimitation.severimpl.Pc;

import com.example.bcyimitation.mapper.Pc.PcContentMapper;
import com.example.bcyimitation.pojo.Pc.PcContent;
import com.example.bcyimitation.server.Pc.PcContentServer;
import org.apache.ibatis.javassist.bytecode.LineNumberAttribute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PcContentServerImpl implements PcContentServer {

    @Resource
    private PcContentMapper pcContentMapper;

    /**
     * 插入一个文字作品内容
     * @param uid
     * @param pc_content
     * @param pc_title
     * @param pc_info
     * @param pc_firstImg
     * @return
     */
    @Override
    public int insertPcContent(Integer uid,String pc_content, String pc_title, String pc_info,String pc_firstImg) {

        PcContent pcContent = new PcContent();

        pcContent.setUid(uid);
        pcContent.setPcContent(pc_content);
        pcContent.setPcTitle(pc_title);
        pcContent.setPcInfo(pc_info);
        pcContent.setPcFirstImg(pc_firstImg);


        return pcContentMapper.insertPcContent(pcContent);
    }

    @Override
    public int updatePcContentPcGivelikeByUserGivelike(Integer pcGivelike, Integer pcid) {

        PcContent pcContent = new PcContent();
        pcContent.setPcid(pcid);
        pcContent.setPcGivelike(pcGivelike);
        return pcContentMapper.updatePcContentPcGivelikeByUserGivelike(pcContent);
    }

    @Override
    public int updatePcContentPcClickByPcid(Integer pcid) {
        PcContent pcContent = new PcContent();
        int i = pcContentMapper.selectPcContentPcClicksByPcid(pcid);
        i++;
        pcContent.setPcClick(i);
        pcContent.setPcid(pcid);
        return pcContentMapper.addPcContentPcClickByPcid(pcContent);
    }
}
