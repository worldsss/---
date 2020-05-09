package com.example.bcyimitation.severimpl.Pro;

import com.example.bcyimitation.mapper.Pro.ProContentMapper;
import com.example.bcyimitation.mapper.Pro.ProImgMapper;
import com.example.bcyimitation.pojo.Paging;
import com.example.bcyimitation.pojo.Pro.ProImgs;
import com.example.bcyimitation.server.Pro.ProImgsServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProImgsServerImpl implements ProImgsServer {

    @Resource
    private ProImgMapper imgsMapper;

    @Resource
    private ProContentMapper proContentMapper;

    @Override
    public int insertImgsByPrid(String img) {

        ProImgs proImgs = new ProImgs();
        proImgs.setImg(img);

        //获取最新插入的值
        int prid = proContentMapper.selectPirdByNewest();

        proImgs.setPrid(prid);

        return imgsMapper.insertImgsByPrid(proImgs);
    }


    @Override
    public Paging selectProImgsPaging(int pageSize, int pageNumber) {


        Paging paging = new Paging();

        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));


        int count = imgsMapper.selectCountAllProImgs();
        paging.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);


        return paging;
    }
}
