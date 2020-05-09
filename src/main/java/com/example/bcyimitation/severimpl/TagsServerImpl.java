package com.example.bcyimitation.severimpl;

import com.example.bcyimitation.mapper.Pro.ProContentMapper;
import com.example.bcyimitation.mapper.TagsMapper;
import com.example.bcyimitation.pojo.Tags;
import com.example.bcyimitation.server.TagsServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TagsServerImpl implements TagsServer {

    @Resource
    private TagsMapper tagsMapper;

    @Resource
    private ProContentMapper proContentMapper;

    /**
     * 插入一个标签(圈子)
     * @param tagsName
     * @return
     */
    @Override
    public int insertTags(String tagsName) {
        Tags tags = new Tags();
        tags.setTagsName(tagsName);
        return tagsMapper.insertTag(tags);
    }

    @Override
    public int selectTagsNewestTid() {
        return tagsMapper.selectNewestTagTid();
    }

    /**
     * 查找推荐的标签
     * @return
     */
    @Override
    public List<Tags> selectTagsByRecom() {
        return tagsMapper.selectTagsByRecom();
    }


    @Override
    public Tags selectTagsByTid(Integer tid) {
        System.out.println(tagsMapper.selectProTagName(tid));
        return tagsMapper.selectProTagName(tid);
    }

    @Override
    public int countAllProContentByTid(Integer tid) {
        return tagsMapper.countAllProContentByTid(tid);
    }

    @Override
    public int updateTagsHotByTid(Integer tid) {

        //计算出当前的圈子中的作品个数
        int nowHot = tagsMapper.countAllProContentByTid(tid);
        System.out.println("这里的作品个数是"+nowHot);
        //作品个数+1
        nowHot++;

        Tags tags = new Tags();
        tags.setTid(tid);
        tags.setTagsHot(nowHot);
        System.out.println("调用了没有");
        return tagsMapper.updateTagsHotByTid(tags);
    }

    @Override
    public List<Tags> selectTagsHotLimitFive() {
        return tagsMapper.selectTagsHotLimitFive();
    }


    @Override
    public int updateTagsLatestTime() {

        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowDate = simpleDateFormat.format(date);

        //根据最新插入数据库总的prid来算出当前作品的所有tid
        List<Tags> tags = tagsMapper.selectAllTagsByProTagsPrid(proContentMapper.selectPirdByNewest());

        Tags tags1 = new Tags();
        tags1.setTagsLatestTime(nowDate);

        int flag = 0;

        for (Tags tags2:tags) {
            //遍历所有的tid再赋值给用来传递参数tags1就可以了
            tags1.setTid(tags2.getTid());

            flag = tagsMapper.updateTagsLatestTime(tags1);

        }

        return flag;
    }
}
