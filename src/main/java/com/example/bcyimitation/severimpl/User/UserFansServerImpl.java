package com.example.bcyimitation.severimpl.User;

import com.example.bcyimitation.mapper.User.UserFansMapper;
import com.example.bcyimitation.mapper.User.UserMapper;
import com.example.bcyimitation.pojo.User.User;
import com.example.bcyimitation.pojo.User.UserFans;
import com.example.bcyimitation.server.User.UserFansServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserFansServerImpl implements UserFansServer {

  @Resource private UserFansMapper userFansMapper;

  @Resource
  private UserMapper userMapper;

  /**
   * 插入一个用户粉丝
   *
   * @param fid
   * @param uid
   * @return
   */
  @Override
  public int insertUserFans(Integer fid, Integer uid) {

    UserFans userFans = new UserFans();
    /* userFans.setFid(fid);
    userFans.setUid(uid);*/
    userFans.setUid(fid); //被关注的用户
    userFans.setFid(uid); //登录的用户

    //根据uid查询出当前的关注数有多少
    int i = userMapper.selectUserAttentionByUid(uid);
    i++;
    User user = new User();
    user.setUid(uid);
    user.setUserAttention(i);
    //当前的关注数量增1
    userMapper.updateUserAttention(user);

    //根据fid查询出当前的粉丝数有多少
    int i1 = userMapper.selectUserFansByUid(fid);
    i1++;
    User user1 = new User();
    user1.setUid(fid);
    user1.setUserFans(i1);
    //当前的粉丝数量+1
    userMapper.updateUserFans(user1);

    return userFansMapper.insertUserFansByUid(userFans);
  }

  @Override
  public int selectUserFansByUid(Integer uid, Integer fid) {

    //        List<UserFans> userFans = userFansMapper.selectUserFansByUid(uid);

    List<UserFans> userFans = userFansMapper.selectUserFansByFid(uid);
   /* System.out.println("这里是当前登录的用户"+uid);
    System.out.println("这里是当前作品的用户"+fid);*/
    /** 判断当前访问的用户是否是自己关注的了 */
    for (UserFans userFans1 : userFans) {
      if (userFans1.getUid().equals(fid)) {
//        System.out.println(userFans1);
        return 1;
      } else {

      }
//        System.out.println(userFans1);
    }
    return 0;
  }


  @Override
  public List<UserFans> selectUserFansUserByFid(Integer fid) {
    return userFansMapper.selectUserFansByFid(fid);
  }
}
