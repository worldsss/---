package com.example.bcyimitation.severimpl.Pro;

import com.example.bcyimitation.mapper.Pro.ProContentMapper;
import com.example.bcyimitation.mapper.Pro.ProImgMapper;
import com.example.bcyimitation.mapper.Pro.ProIndexMapper;
import com.example.bcyimitation.mapper.Pro.ProTagsMapper;
import com.example.bcyimitation.mapper.TagsMapper;
import com.example.bcyimitation.mapper.User.UserFansMapper;
import com.example.bcyimitation.mapper.User.UserMapper;
import com.example.bcyimitation.pojo.*;
import com.example.bcyimitation.pojo.Pro.ProImgs;
import com.example.bcyimitation.pojo.Pro.ProIndex;
import com.example.bcyimitation.pojo.Pro.ProTags;
import com.example.bcyimitation.pojo.User.UserFans;
import com.example.bcyimitation.server.Pro.ProIndexServer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProIndexServerImpl implements ProIndexServer {

    @Resource
    private ProIndexMapper proIndexMapper;

    @Resource
    private ProImgMapper proImgMapper;

    @Resource
    private TagsMapper tagsMapper;

    @Resource
    private ProTagsMapper proTagsMapper;

    @Resource
    private UserFansMapper userFansMapper;

    @Resource
    private ProContentMapper proContentMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 分页展示所有的图片作品
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public Paging selectIndex(int pageSize, int pageNumber) {
        Paging paging = new Paging();

        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));

        int count = proIndexMapper.CountIndex();
        paging.setTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);


        List<ProIndex> pro_indices = proIndexMapper.selectIndex(paging);

        // 根据paging里的值求出来当前作品有多少图片
        List<ProImgs> pro_imgs = proImgMapper.selectImgsPaging(paging);

        List<ProTags> proTags = proTagsMapper.selectAllProTid(paging);


        for (ProIndex proIndex : pro_indices) {
            for (ProImgs proImgs : pro_imgs) {
                // 当prid相等的时候就把图片赋值到pro_index的list<pr_imgs>中
                if (proIndex.getPrid() == proImgs.getPrid()) {
                    proIndex.setProImgs(proImgMapper.selectImgsByPrid(proIndex.getPrid()));
                }
            }
        }

        for (ProIndex proIndex : pro_indices) {
            for (ProTags pro_tags : proTags) {
//            if(proIndex.getPrid() == pro_tags.getPrid()){
                if (proIndex.getPrid().equals(pro_tags.getPrid())) {
                    proIndex.setPrTags(tagsMapper.selectTagsByPrid(proIndex.getPrid()));
                }
            }
        }

        paging.setList(pro_indices);

        return paging;
    }

    /**
     * 查询某一条值
     *
     * @param prid
     * @return
     */
    @Override
    public ProIndex selectIndexById(int prid) {

        ProIndex proIndex = new ProIndex();

        proIndex.setPrid(prid);
        proIndex.setPrTags(tagsMapper.selectTagsByPrid(prid));

        return proIndexMapper.selectIndexById(proIndex);
    }

    /**
     * 查询某一条作品，包括图片和标签
     *
     * @param prid
     * @return
     */
    @Override
    public ProIndex selectIndexTagsById(int prid) {

        ProIndex proIndex1 = proIndexMapper.selectIndexTagById(prid);

        ProIndex proIndex = new ProIndex();
        // 把根据id获取到的tags赋值给proindex的list里
        proIndex.setPrTags(tagsMapper.selectTagsByPrid(prid));

        // 把根据id获取到的imgs赋值给proindex的list里
        proIndex.setProImgs(proImgMapper.selectImgsByPrid(prid));

        proIndex.setCid(proIndex1.getCid());
        proIndex.setPrClick(proIndex1.getPrClick());
        proIndex.setPrCommentCount(proIndex1.getPrCommentCount());
        proIndex.setPrGivelike(proIndex1.getPrGivelike());
        proIndex.setPrInfo(proIndex1.getPrInfo());
        proIndex.setPrDate(proIndex1.getPrDate());
        proIndex.setUid(proIndex1.getUid());
        proIndex.setUserAvatar(proIndex1.getUserAvatar());
        proIndex.setUserName(proIndex1.getUserName());
        proIndex.setPrImgCount(proIndex1.getPrImgCount());

        return proIndex;
    }

    /**
     * 显示所有的图片作品
     *
     * @return
     */
    @Override
    public List<ProIndex> selectAllIndex() {

        List<ProIndex> proIndices = proIndexMapper.selectAllProIndex();

        List<ProTags> proTags = proTagsMapper.selectAllProTags();

        List<ProImgs> proImgs = proImgMapper.selectAllImgs();

        for (ProIndex proIndex : proIndices) {
            for (ProTags pro_tags : proTags) {
                // 当这两个表的prid相等时，利用prid来查询所有的tags
                if (proIndex.getPrid() == pro_tags.getPrid()) {
                    List<Tags> tagsList = tagsMapper.selectTagsByPrid(proIndex.getPrid());
                    proIndex.setPrTags(tagsList);
                }
            }
        }
        for (ProIndex proIndex : proIndices) {
            for (ProImgs pro_imgs : proImgs) {
                //两个表的prid相等时，把查询到的pro_imgs赋值到pro_index中的list里面
                if (proIndex.getPrid() == pro_imgs.getPrid()) {
                    proIndex.setProImgs(proImgMapper.selectImgsByPrid(proIndex.getPrid()));
                }
            }
        }

        return proIndices;
    }


    @Override
    public List<ProIndex> selectAllIndexByTid(int tid) {
        return proIndexMapper.selectProIndexByProTagsTid(tid);
    }


    /**
     * 根据tid圈子来展示不同的内容
     * @param pageSize
     * @param pageNumber
     * @param tid
     * @return
     */
    @Override
    public Paging selectProIndexByTid(int pageSize, int pageNumber, int tid) {
        Paging paging = new Paging();

        paging.setId(tid);
        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));

        int count = proIndexMapper.countProIndexByTid(tid);
        paging.setTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        //        paging.setTags(tagsMapper.selectTagsByPrid());

        List<ProIndex> pro_indices = proIndexMapper.selectProIndexByTid(paging);

        // 根据paging里的值求出来当前作品有多少图片
        List<ProImgs> pro_imgs = proImgMapper.selectImgsPagingByTid(paging);


        List<ProTags> proTags = proTagsMapper.selectAllProTid(paging);


        for (ProIndex proIndex : pro_indices) {
            for (ProImgs proImgs : pro_imgs) {
                // 当prid相等的时候就把图片赋值到pro_index的list<pr_imgs>中
                if (proIndex.getPrid() == proImgs.getPrid()) {
                    proIndex.setProImgs(proImgMapper.selectImgsByPrid(proIndex.getPrid()));
                }
            }
        }

        for (ProIndex proIndex : pro_indices) {
            for (ProTags pro_tags : proTags) {
                if (proIndex.getPrid().equals(pro_tags.getPrid())) {
                    proIndex.setPrTags(tagsMapper.selectTagsByPrid(proIndex.getPrid()));
                }
            }
        }


        paging.setList(pro_indices);

        return paging;
    }

    /**
     * 根据用户uid来分页展示图片作品的内容
     * @param pageSize
     * @param pageNumber
     * @param uid
     * @return
     */
    @Override
    public Paging selectProIndexByUid(int pageSize, int pageNumber, int uid) {

        Paging paging = new Paging();

        paging.setId(uid);
        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));

        int count = userMapper.ProIndexCountByUid(uid);
        paging.setTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);


        List<ProIndex> pro_indices = proIndexMapper.selectProIndexByUid(paging);

        // 根据paging里的值求出来当前作品有多少图片
        List<ProImgs> pro_imgs = proImgMapper.selectImgsPaginByUid(paging);

        List<ProTags> proTags = proTagsMapper.selectProTagsByUid(paging);

        for (ProIndex proIndex : pro_indices) {
            for (ProImgs proImgs : pro_imgs) {
                // 当prid相等的时候就把图片赋值到pro_index的list<pr_imgs>中
                if (proIndex.getPrid() == proImgs.getPrid()) {
                    proIndex.setProImgs(proImgMapper.selectImgsByPrid(proIndex.getPrid()));
                }
            }
        }

        for (ProIndex proIndex:pro_indices ) {
            for (ProTags pro_tags:proTags ) {
//            if(proIndex.getPrid() == pro_tags.getPrid()){
                if(proIndex.getPrid().equals(pro_tags.getPrid())){
                    proIndex.setPrTags(tagsMapper.selectTagsByPrid(proIndex.getPrid()));
                }
            }
        }

        paging.setList(pro_indices);



        return paging;
    }

    /**
     * 根据自己关注的用户来查看所有的图片内容
     *
     * @param fid
     * @return
     */
    @Override
    public List<ProIndex> selectProIndexByUserUid(Integer fid) {

        //传入自己的uid来寻找自己所有的关注的用户
        List<UserFans> userFans = userFansMapper.selectAllUserFansByFid(fid);


        List<ProIndex> proIndices = new ArrayList<>();

        for (UserFans userFans1:userFans) {
            Integer uid = userFans1.getUid();
            List<ProIndex> proIndexList = proContentMapper.selectProIndexByUserUid(uid);
        /*    for (ProIndex proIndex:proIndexList) {
                proIndices.add(proIndex);
            }*/
            proIndices.addAll(proIndexList);
        }



        return proIndices;
    }

    /**
     * 根据点赞的多少来对所有的图片作品进行排序
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public Paging selectProIndexOrderByProContentPrGivelike(int pageSize, int pageNumber) {

        Paging paging = new Paging();
        paging.setPageNumber(pageNumber);
        paging.setPageSize(pageSize);
        paging.setPageStart(pageSize * (pageNumber - 1));

        int count = proContentMapper.countProIndexByProContentPrGivelike();
        paging.setTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);


        List<ProIndex> proIndexList = proIndexMapper.selectProIndexOrderByProContentPrGivelike(paging);

//        List<ProImgs> proImgs = proImgMapper.selectProImgsByProContentOrderByPrGivelike(paging);


        paging.setList(proIndexList);

        return paging;
    }

    /**
     * cos排行榜前七
     * @return
     */
    @Override
    public List<ProIndex> selectProContentOrderByPrGivelikeLimitSeven() {
        return proIndexMapper.selectProContentOrderByPrGivelikeLimitSeven();
    }
}
