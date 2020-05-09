package com.example.bcyimitation.severimpl.qu;

import com.example.bcyimitation.mapper.qu.QuContentMapper;
import com.example.bcyimitation.mapper.qu.QuImgsMapper;
import com.example.bcyimitation.pojo.qu.Qu_imgs;
import com.example.bcyimitation.server.qu.QuImgsServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuImgsServerImpl implements QuImgsServer {

    @Resource
    private QuImgsMapper quImgsMapper;

    @Resource
    private QuContentMapper quContentMapper;

    @Override
    public int insertQuImg( String img) {

        Qu_imgs quImgs = new Qu_imgs();
        quImgs.setImg(img);

        //获得到刚刚插入的quid是多少然后再插入到数据库中
        quImgs.setQuid(quContentMapper.selectNewest());

        return quImgsMapper.insertQuImg(quImgs);
    }
}
