package com.example.bcyimitation.severimpl.User;

import com.example.bcyimitation.mapper.User.UserCollectMapper;
import com.example.bcyimitation.pojo.User.UserCollect;
import com.example.bcyimitation.server.User.UserCollectServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserCollectServerImpl implements UserCollectServer {

  @Resource private UserCollectMapper userCollectMapper;

  /**
   * 插入一个用户收藏
   *
   * @param uid
   * @param prid
   * @return
   */
  @Override
  public int insertUserCollect(Integer uid, Integer prid) {

    UserCollect userCollect = new UserCollect();
    userCollect.setUid(uid);
    userCollect.setPrid(prid);

    return userCollectMapper.insertUserCollect(userCollect);
  }

  @Override
  public int selectUserCollectByUid(Integer uid) {

    List<UserCollect> userCollectList = userCollectMapper.selectUserCollectByUid(uid);

    List<UserCollect> userCollectList1 = userCollectMapper.selectAllUserCollectPrid();

    // 判断当前的用户的作品id中有
    for (UserCollect userCollect : userCollectList) {
      for (UserCollect userCollect1 : userCollectList1) {

        if (userCollect.getPrid().equals(userCollect1.getPrid())) {
          return 1;
        }
      }
    }

    return 0;
  }

  @Override
  public int judgeUserCollectByUidAndPrid(Integer uid, Integer prid) {

    UserCollect userCollect = new UserCollect();

    userCollect.setPrid(prid);
    userCollect.setUid(uid);
    int prid1 = userCollectMapper.judgeUserCollectByUidAndPrid(userCollect);
    return prid1;
  }

  @Override
  public int deleteUserCollectByUidAndPrid(Integer uid, Integer prid) {

    UserCollect userCollect = new UserCollect();

    userCollect.setPrid(prid);
    userCollect.setUid(uid);
    int prid1 = userCollectMapper.deleteUserCollectByUidAndPrid(userCollect);
    return prid1;

  }


  /**
   * 根据pcid来收藏一个文字作品
   * @param uid
   * @param pcid
   * @return
   */
  @Override
  public int insertUserCollectByPcid(Integer uid, Integer pcid) {
    UserCollect userCollect = new UserCollect();
    userCollect.setUid(uid);
    userCollect.setPcid(pcid);

    return userCollectMapper.insertUserCollectPcid(userCollect);
  }

  /**
   * 判断当前的文字作品中是否被收藏了
   * @param uid
   * @param pcid
   * @return
   */
  @Override
  public int judgeUserCollectByUidAndPcid(Integer uid, Integer pcid) {

    UserCollect userCollect = new UserCollect();

    userCollect.setPcid(pcid);
    userCollect.setUid(uid);
    int prid1 = userCollectMapper.judgeUserCollectByUidAndPcid(userCollect);
    return prid1;
  }

  /**
   * 删除某一个文字作品的收藏
   * @param uid
   * @param pcid
   * @return
   */
  @Override
  public int deleteUserCollectByUidAndPcid(Integer uid, Integer pcid) {
    UserCollect userCollect = new UserCollect();
    userCollect.setUid(uid);
    userCollect.setPcid(pcid);

    return userCollectMapper.deleteUserCollectByUidAndPcid(userCollect);
  }
}
