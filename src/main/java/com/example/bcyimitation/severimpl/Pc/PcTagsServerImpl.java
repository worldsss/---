package com.example.bcyimitation.severimpl.Pc;

import com.example.bcyimitation.mapper.Pc.PcContentMapper;
import com.example.bcyimitation.mapper.Pc.PcTagsMapper;
import com.example.bcyimitation.pojo.Pc.PcTags;
import com.example.bcyimitation.pojo.Pro.ProTags;
import com.example.bcyimitation.server.Pc.PcTagsServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PcTagsServerImpl implements PcTagsServer {

  @Resource private PcContentMapper pcContentMapper;

  @Resource private PcTagsMapper pcTagsMapper;

  @Override
  public int insertPcTags(Integer tid) {

    PcTags pc_tags = new PcTags();

    int pcid = pcContentMapper.searchNewestPcid();

    pc_tags.setPcid(pcid);
    pc_tags.setTid(tid);

    return pcTagsMapper.insertPcTags(pc_tags);

  }

  @Override
  public int insertForPcTags(List<ProTags> pro_tags) {
    return 0;
  }
}
