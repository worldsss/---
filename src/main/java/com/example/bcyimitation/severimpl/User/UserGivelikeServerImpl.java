package com.example.bcyimitation.severimpl.User;

import com.example.bcyimitation.mapper.Pc.PcContentMapper;
import com.example.bcyimitation.mapper.Pro.ProContentMapper;
import com.example.bcyimitation.mapper.User.UserGivelikeMapper;
import com.example.bcyimitation.pojo.User.User;
import com.example.bcyimitation.pojo.User.UserGivelike;
import com.example.bcyimitation.server.Pro.ProContentServer;
import com.example.bcyimitation.server.User.UserGivelikeServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserGivelikeServerImpl implements UserGivelikeServer {

    @Resource
    private UserGivelikeMapper userGivelikeMapper;

    @Resource
    private ProContentMapper proContentMapper;

    @Resource
    private PcContentMapper pcContentMapper;

    /**
     * 插入一个用户点赞-作品表
     *
     * @param uid
     * @param prid
     * @return
     */
    @Override
    public int insertGivelike(Integer uid, Integer prid) {


        List<UserGivelike> userGivelikes = userGivelikeMapper.selectAllUserGivelike();

        boolean flag = false;

        //用于判断是否插入点赞成功
        int i = 0;
        for (UserGivelike userGivelike : userGivelikes) {
            //当这个用户点过赞后，不再让其继续点赞的判断
            if(userGivelike.getPrid()!=null){
                if (userGivelike.getUid().equals(uid) && userGivelike.getPrid().equals(prid)) {
                    return 0;
                }
            }

        }

        UserGivelike userGivelike1 = new UserGivelike();
        userGivelike1.setPrid(prid);
        userGivelike1.setUid(uid);

        proContentMapper.addProContetPrIsGivelikeByPrid(prid);
        System.out.println("怎么图片作品的添加还有问题了"+userGivelike1);
        i = userGivelikeMapper.insertUserGivelikeMapper(userGivelike1);
        System.out.println(i);


        return i;
    }


    /**
     * 计算出当前作品有几个用户点赞
     *
     * @param prid
     * @return
     */
    @Override
    public int countGivelikeByPrid(Integer prid) {
        return userGivelikeMapper.countUserGivelikeByPrid(prid);
    }


    /**
     * 删除一个图片作品用户点赞关系表
     *
     * @param uid
     * @param prid
     * @return
     */
    @Override
    public int removeGivelikeByPrid(Integer uid, Integer prid) {

        UserGivelike userGivelike = new UserGivelike();
        userGivelike.setUid(uid);
        userGivelike.setPrid(prid);

        //更新表中的点赞状态
        proContentMapper.removeProContetPrIsGivelikeByPrid(prid);

        return userGivelikeMapper.deleteUserGivelikeByUidAndPrid(userGivelike);
    }


//    -------------文字作品

    /**
     * 计算出当前有几个用户给这个作品点赞
     * @param pcid
     * @return
     */
    @Override
    public int countGivelikeByPcid(Integer pcid) {
        return userGivelikeMapper.countUserGivelikeByPcid(pcid);
    }

    /**
     * 插入一个文字作品用户点赞
     *
     * @param uid
     * @param pcid
     * @return
     */
    @Override
    public int insertGivelikePcid(Integer uid, Integer pcid) {

        List<UserGivelike> userGivelikes = userGivelikeMapper.selectAllUserGivelike();

        boolean flag = false;
        //用于判断是否插入点赞成功
        int i = 0;
        for (UserGivelike userGivelike : userGivelikes) {
            //当这个用户点过赞后，不再让其继续点赞的判断
            if(userGivelike.getPcid()!=null){
                if (userGivelike.getUid().equals(uid) && userGivelike.getPcid().equals(pcid)) {
                    return 0;
                }
            }

        }

        UserGivelike userGivelike1 = new UserGivelike();
        userGivelike1.setPcid(pcid);
        userGivelike1.setUid(uid);
        //修改点赞状态
        pcContentMapper.addPcContetPcIsGivelikeByPcid(pcid);

       return userGivelikeMapper.insertUserGivelikePcid(userGivelike1);

    }



    /**
     * 删除一个文字作品的点赞
     *
     * @param uid
     * @param pcid
     * @return
     */
    @Override
    public int removeGivelikeByPcrd(Integer uid, Integer pcid) {

        UserGivelike userGivelike = new UserGivelike();
        userGivelike.setUid(uid);
        userGivelike.setPcid(pcid);

        //更新表中的点赞状态
        pcContentMapper.removePcContetPcIsGivelikeByPcid(pcid);

        return userGivelikeMapper.deleteUserGivelikeByUidAndPcid(userGivelike);
    }
}
