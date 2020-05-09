package com.example.bcyimitation.severimpl.Pro;

import com.example.bcyimitation.mapper.Pro.ProContentMapper;
import com.example.bcyimitation.mapper.Pro.ProTagsMapper;
import com.example.bcyimitation.pojo.Pro.ProTags;
import com.example.bcyimitation.server.Pro.ProTagsServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProTagsServerImpl implements ProTagsServer {

    @Resource
    private ProTagsMapper proTagsMapper;

    @Resource
    private ProContentMapper proContentMapper;


    @Override
    public int insertProTags(Integer tid) {

        ProTags proTags = new ProTags();

        int prid = proContentMapper.selectPirdByNewest();

        proTags.setPrid(prid);
        proTags.setTid(tid);

        return proTagsMapper.insertProTags(proTags);
    }


    @Override
    public int insertForProTags(List<ProTags> pro_tags) {



        List<ProTags> proTagsList = new ArrayList<>();
        int flag = 0;
        int prid = proContentMapper.selectPirdByNewest();

        for (ProTags proTags: pro_tags) {
            for (ProTags proTags1: proTagsList) {
                proTags1.setPrid(prid);
                proTags1.setTid(proTags.getTid());

                flag = proTagsMapper.insertProTags(proTags1);
            }
        }

        return flag;
    }

    /**
     * 计算出圈子的成员数
     * @param tid
     * @return
     */
    @Override
    public int countAllPridByTid(Integer tid) {
        return proTagsMapper.countAllPridByTid(tid);
    }
}
