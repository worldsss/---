package com.example.bcyimitation.severimpl.User;

import com.example.bcyimitation.mapper.*;
import com.example.bcyimitation.mapper.Pro.ProImgMapper;
import com.example.bcyimitation.mapper.Pro.ProIndexMapper;
import com.example.bcyimitation.mapper.Pro.ProTagsMapper;
import com.example.bcyimitation.mapper.User.UserFansMapper;
import com.example.bcyimitation.mapper.User.UserMapper;
import com.example.bcyimitation.pojo.*;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.pojo.Pro.ProImgs;
import com.example.bcyimitation.pojo.Pro.ProIndex;
import com.example.bcyimitation.pojo.Pro.ProTags;
import com.example.bcyimitation.pojo.User.User;
import com.example.bcyimitation.pojo.User.UserTag;
import com.example.bcyimitation.server.User.UserServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author world
 */

@Service
public class UserServerImpl implements UserServer {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProIndexMapper proIndexMapper;

    @Resource
    private ProImgMapper proImgMapper;

    @Resource
    private ProTagsMapper proTagsMapper;

    @Resource
    private TagsMapper tagsMapper;

    @Resource
    private UserFansMapper userFansMapper;


    /**
     * 添加一个用户
     * @param user_name
     * @param user_password
     * @param user_avatar
     * @return
     */
    @Override
    public int insertUser(String user_name, String user_password, String user_avatar) {

        User user = new User();
        user.setUserName(user_name);
        user.setUserPassword(user_password);
        user.setUserAvatar(user_avatar);


      /*  int flag = userMapper.judgeUserNameRepetition(user_name);

        if(flag==1){

        }*/

        return userMapper.insertUser(user);
    }

    /**
     * 判断用户名字是否重复
     * @param userName
     * @return
     */
    @Override
    public int judgeUserName(String userName) {
        int flag = userMapper.judgeUserNameRepetition(userName);
        System.out.println(flag);
        return flag;
    }

    /**
     * 用户登录
     * @param user_name
     * @param user_password
     * @return
     */
    @Override
    public int userLogin(String user_name, String user_password, HttpSession session) {


     /*   if(session.getAttribute("userName")!=null){
            return 1;
        }*/

        List<User> userList = userMapper.selectAllUserForLogin();

        for ( User user:userList ) {
            if(user.getUserName().equals(user_name) && user.getUserPassword().equals(user_password)){

                    //用来存放session的user对象
                    User user1 = new User();

                    session.setAttribute("userName",user_name);
                    session.setAttribute("userPwd",user_password);

                    user1.setUserName(user_name);
                    user1.setUserPassword(user_password);


                    //根据用户的姓名和密码来得到当前的uid
                    int uid = userMapper.searchUidByUserName(user1);
                    //再根据uid来找到当前用户的头像
                    String userAvatar = userMapper.searchUserAvatarByUid(uid);


                    user1.setUid(uid);
                    user1.setUserAvatar(userAvatar);

                    session.setAttribute("uid",uid);
                    session.setAttribute("userAvatar",userAvatar);

                    //用来改变当前用户关注，点赞数的对象
                    User user2 = new User();
                    user2.setUid(uid);
                    user2.setUserAttention(userMapper.countUserAttentionByFid(uid));
                    //更新当前用户的关注数
                    userMapper.updateUserAttention(user2);

                    //用来改变当前用户的粉丝
                    User user3 = new User();
                    user3.setUid(uid);
                    user3.setUserFans(userMapper.countUserFansByUid(uid));
                    //更新当前用户的粉丝数
                    userMapper.updateUserFans(user3);

                return 1;

            }
        }


        return 0;
    }

    /**
     * 根据uid来展示user
     * @param uid
     * @return
     */
    @Override
    public User selectUserByUid(Integer uid) {
        return userMapper.selectUserByUid(uid);
    }

    /**
     * 查看关注的5个用户
     * @param uid
     * @return
     */
    @Override
    public User selectUserByUidLimitFive(Integer uid) {
        return userMapper.selectUserByUidLimitFive(uid);
    }

    /**
     * 根据uid来获取
     * @param uid
     * @return
     */
    @Override
    public List<ProContent> selectProContentByUid(int uid) {


        return  userMapper.selectProContentByUid(uid);
    }

    @Override
    public int searchUidByUserName(String user_name, String user_password) {

        User user = new User();
        user.setUserName(user_name);
        user.setUserPassword(user_password);

        int nowUid = userMapper.searchUidByUserName(user);
        return nowUid;
    }

    /**
     * 从session中取出存放的用户
     * @param session
     * @return
     */
    @Override
    public User getUserBySession(HttpSession session) {

        if(session.getAttribute("userName")==null){
            return null;
        }else {
            User user = new User();

//      System.out.println(session.getAttribute("userName"));
            user.setUserName(session.getAttribute("userName")+"");
            user.setUserPassword(session.getAttribute("userPwd")+"");
            user.setUserAvatar(session.getAttribute("userAvatar")+"");
            user.setUid(Integer.valueOf(session.getAttribute("uid")+""));


            return user;
        }


    }

    @Override
    public String getUserAvatarByUid(int uid) {
        return userMapper.searchUserAvatarByUid(uid);
    }


    @Override
    public User searchUserForPrid(int prid) {
        return userMapper.searchUserForPrid(prid);
    }



    /**
     * 根据用户uid来展示个人标签
     * @param uid
     * @return
     */
    @Override
    public List<Tags> selectTagsNameByUid(Integer uid) {
        System.out.println(userMapper.selectTagsNameByUid(uid));
        return userMapper.selectTagsNameByUid(uid);
    }

    /**
     * 修改用户头像
     * @param userAvatar
     * @param uid
     * @return
     */
    @Override
    public int updateUserAvatarByUid(String userAvatar, Integer uid) {
        User user = new User();
        user.setUid(uid);
        user.setUserAvatar(userAvatar);
        return userMapper.updateUserAvatarByUid(user);
    }

    /**
     * 修改用户昵称，信息，地区，性别
     * @param userName
     * @param userInfo
     * @param userArea
     * @param userSex
     * @param uid
     * @return
     */
    @Override
    public int updateUserInfoByUid(String userName, String userInfo,
                                   String userArea, String userSex, Integer uid) {

        User user =  new User();
        user.setUid(uid);
        user.setUserName(userName);
        user.setUserInfo(userInfo);
        user.setUserSex(userSex);
        user.setUserArea(userArea);
        System.out.println(user);
        return userMapper.updateUserInfoByUid(user);
    }


}
