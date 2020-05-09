package com.example.bcyimitation.severimpl.Pc;

import com.example.bcyimitation.mapper.Pc.PcIndexMapper;
import com.example.bcyimitation.mapper.Pc.PcTagsMapper;
import com.example.bcyimitation.mapper.TagsMapper;
import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pc.PcIndex;
import com.example.bcyimitation.pojo.Pc.PcTags;
import com.example.bcyimitation.server.Pc.PcIndexServer;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PcIndexServerImpl implements PcIndexServer {

    @Resource
    private PcIndexMapper pcIndexMapper;

    @Resource
    private PcTagsMapper pcTagsMapper;

    @Resource
    private TagsMapper tagsMapper;

    /**
     * 分页展示pc_index里面的内容
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public Paging selectPcIndexByPaging(int pageSize, int pageNumber) {
        Paging paging = new Paging();

        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));

        int count = pcIndexMapper.selectCountPcIndex();
        paging.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);



        List<PcIndex> pc_indices = pcIndexMapper.selectPcIndexPaging(paging);

        List<PcTags> pc_tags = pcTagsMapper.selectTagsPaging(paging);

        for (PcIndex pc_index:pc_indices ) {
            for (PcTags pc_tags1 : pc_tags) {
                if(pc_index.getPcid()==pc_tags1.getPcid()){
                    //根据pcid来获得标签个数
                    pc_index.setPcTags(tagsMapper.selectTagsByPcid(pc_index.getPcid()));
                }
            }

        }

        paging.setList(pc_indices);


        return paging;


    }


    @Override
    public PcIndex selectPcIndexByPcid(int pcid) {
        return pcIndexMapper.selectPcIndexByPcid(pcid);
    }

    /**
     * 根据圈子tid来分页展示文字作品
     * @param pageSize
     * @param pageNumber
     * @param tid
     * @return
     */
    @Override
    public Paging selectPcIndexByTidPaging(int pageSize, int pageNumber, int tid) {

        Paging paging = new Paging();
        paging.setId(tid);
        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));

        int count = pcIndexMapper.countPcIndexByTid(tid);
        paging.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);

        List<PcIndex> pcIndices = pcIndexMapper.selectPcIndexByTidPaging(paging);

        List<PcTags> pcTags = pcTagsMapper.selectPcTagsByTid(paging);

        for (PcIndex pcIndex:pcIndices) {
            for (PcTags pcTags1:pcTags) {
                if (pcIndex.getPcid() == pcTags1.getPcid()){
                    pcIndex.setPcTags(tagsMapper.selectTagsByPcid(pcIndex.getPcid()));
                }
            }


        }

        paging.setList(pcIndices);
        return paging;
    }

    /**
     * 根据用户uid来分页展示文字作品内容
     * @param pageSize
     * @param pageNumber
     * @param uid
     * @return
     */
    @Override
    public Paging selectPcIndexByUidPaging(int pageSize, int pageNumber, Integer uid) {
        Paging paging = new Paging();
        paging.setId(uid);
        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));

        int count = pcIndexMapper.countPcIndexByUid(uid);
        paging.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
        //获取当前用户所有的文字作品
        List<PcIndex> pcIndices = pcIndexMapper.selectPcIndexByUidPaging(paging);
        //当前用户文字作品的标签
        List<PcTags> pcTags = pcTagsMapper.selectPcTagsByUid(paging);

        for (PcIndex pcIndex:pcIndices) {
            for (PcTags pcTags1:pcTags) {
                if (pcIndex.getPcid() == pcTags1.getPcid()){
                    pcIndex.setPcTags(tagsMapper.selectTagsByPcid(pcIndex.getPcid()));
                }
            }
        }
        paging.setList(pcIndices);

        return paging;
    }
}
