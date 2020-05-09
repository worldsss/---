package com.example.bcyimitation.severimpl.qu;

import com.example.bcyimitation.mapper.qu.QuContentMapper;
import com.example.bcyimitation.mapper.qu.QuImgsMapper;
import com.example.bcyimitation.pojo.qu.Qu_content;
import com.example.bcyimitation.pojo.qu.Qu_imgs;
import com.example.bcyimitation.server.qu.QuContentServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuContentServerImpl implements QuContentServer {

    @Resource
    private QuContentMapper quContentMapper;

    @Resource
    private QuImgsMapper quImgsMapper;

    @Override
    public int insertQuContent(String qu_text, String qu_info) {
/*
        //当前数据库中最后一个id
        int newQuid = quContentMapper.selectNewest();

        newQuid++;

        //根据quid来查询出所有的qu_imgs
        List<Qu_imgs> quImgs = quImgsMapper.selectQuImg(newQuid);*/

        Qu_content quContent = new Qu_content();
        quContent.setQu_info(qu_info);
        quContent.setQu_text(qu_text);


        return quContentMapper.insertQuContent(quContent);
    }

    @Override
    public Qu_content selectQuContent(int quid) {

        Qu_content quContent = new Qu_content();

        quContent.setQuid(quid);

        //关键的关键就是这里
        quContent.setQu_imgs(quImgsMapper.selectQuImg(quid));

        //第二关键，这里不要直接传值，而是把得到的值重新赋值到新的对象中
        Qu_content  quContent1= quContentMapper.selectQuContentByQuid(quContent);

        quContent.setQu_text(quContent1.getQu_text());
        quContent.setQu_info(quContent1.getQu_info());

        return quContent;
    }

    @Override
    public List<Qu_content> selectAllQuContent() {

        List list = new ArrayList();

        List<Qu_content> qu_contents = quContentMapper.selectAllQuContent();

        List<Qu_imgs> qu_imgs = quImgsMapper.selectAllQuImg();

       /* //遍历把每一个都给赋值里面去
        for (Qu_content quContent : qu_contents) {
            quContent.setQu_imgs(qu_imgs);
        }*/

      /* //这一份的问题是，如果有它把全部的img都添加上了
        //遍历把每一个都给赋值里面去!!!!!!!!!!!
        for (Qu_content quContent : qu_contents) {
            for (Qu_imgs  quImgs:qu_imgs ) {
                //这里是关键，把两个里面的quid判断是否相等，相等就把qucontent里面的list的值改成quimgs的list
                if(quImgs.getQuid() == quContent.getQuid()){

                    quContent.setQu_imgs(qu_imgs);
                }
            }

        }*/



        //遍历把每一个都给赋值里面去!!!!!!!!!!!
        for (Qu_content quContent : qu_contents) {
            for (Qu_imgs  quImgs:qu_imgs ) {
                //这里是关键，把两个里面的quid判断是否相等，相等就把qucontent里面的list的值改成quimgs的list
                if(quImgs.getQuid() == quContent.getQuid()){
                    //当两个的quid相同时，把quid作为参数去找当前的对象的img有多少个，再复制给quContent的list里
                    List<Qu_imgs> imgs = quImgsMapper.selectQuImg(quContent.getQuid());
                    quContent.setQu_imgs(imgs);
                }
            }

        }

        return qu_contents;
    }
}
