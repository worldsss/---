package com.example.bcyimitation.controller.User;

import com.example.bcyimitation.pojo.*;
import com.example.bcyimitation.pojo.Pro.ProContent;
import com.example.bcyimitation.pojo.User.User;
import com.example.bcyimitation.server.User.UserServer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@SessionAttributes("Nowuser")
public class UserController {

  @Resource private UserServer userServer;

  /**
   * 添加一个用户
   *
   * @param user
   * @return
   */
  @PostMapping("/insertUser")
  public String insertUser(@RequestBody User user) {

    int flag = userServer.insertUser(user.getUserName(),
                user.getUserPassword(), user.getUserAvatar());


    if (flag == 0) {
      return "失败";
    } else {
      return "成功";
    }
  }

  /**
   * 判断注册时用户名是否重复
   * @param user
   * @return
   */
  @PostMapping("/judgeUserName")
  public String judgeUserName(@RequestBody User user){
    System.out.println(user);
    int flag = userServer.judgeUserName(user.getUserName());

    if (flag == 0) {
      return "成功";
    } else {
      return "用户名重复";
    }
  }




  /**
   * 用户登录
   *
   * @param user
   * @param
   * @return
   */
  @PostMapping("/userLogin")
  public User userLogin(@RequestBody User user, HttpServletRequest request, Model model) {

    HttpSession session = request.getSession();
    User user1 = new User();

    int flag = userServer.userLogin(user.getUserName(), user.getUserPassword(), session);

    if (flag == 1) {
      // 返回当前用户的uid
      int integer = userServer.searchUidByUserName(user.getUserName(), user.getUserPassword());

      String userAvatar = userServer.getUserAvatarByUid(integer);

      user1.setUid(integer);
      user1.setUserAvatar(userAvatar);
      user1.setUserName(user.getUserName());
      user1.setUserPassword(user.getUserPassword());

      session.setAttribute("nowuser", user1);
      model.addAttribute("Nowuser", user1);
      //            System.out.println(session.getAttribute("Nowuser"));
      //            System.out.println(session.getAttribute("nowuser"));

//      System.out.println(session.getId());

//      return integer;
      return user1;

    } else {
      return null;
    }
  }

  /**
   * 根据uid来显示对应的用户信息
   * @param uid
   * @return
   */
  @GetMapping("/selectUserByUid")
  public User selectUserByUid(Integer uid){

    return userServer.selectUserByUid(uid);
  }





  /**
   * 从当前controller中获取还没有离开网站的上的用户对象
   *
   * @param
   * @return
   */
  @GetMapping("/getSessionUserInfo")
  public User getSessionUserInfo(HttpServletRequest request, @ModelAttribute User users) {

    HttpSession session = request.getSession();

//    System.out.println(session.getId());

      User nowuser = (User)session.getAttribute("nowuser");
      User userBySession = userServer.getUserBySession(session);

      if(nowuser == null){
          return null;
      }else {
          return nowuser;
      }

  }


  /**
   * 清除用户状态
   * @param request
   * @return
   */
  @GetMapping("/outUserSession")
  public String outSessionUser(HttpServletRequest request){

    HttpSession session = request.getSession();

    session.removeAttribute("nowuser");
    if(session.getAttribute("nowuser")==null){
      return "成功";
    }else {
      return "失败";
    }


  }

  /**
   * 根据作品id来查询出来作品的用户
   * @param prid
   * @return
   */
  @GetMapping("/getUserByPrid")
  public User searchUserByPrid(int prid){


    return userServer.searchUserForPrid(prid);
  }

  /**
   * 根据用户id来查询全部的作品
   * @param uid
   * @return
   */
  @GetMapping("/getProContentByUid")
  public List<ProContent> selectProContentByUid(int uid){

    return userServer.selectProContentByUid(uid);

  }


  /**
   * 根据uid来查找用户关注的圈子
   * @param uid
   * @return
   */
  @GetMapping("/getTagsNameByUid")
  public List<Tags> selectTagsNameByUid(Integer uid){

    return userServer.selectTagsNameByUid(uid);
  }

  /**
   * 根据提交的用户头像和uid来修改用户的头像
   * @param user
   * @return
   */
  @PostMapping("/updateUserAvatarByUid")
  public int updateUserAvatarByUid(@RequestBody User user){
    return userServer.updateUserAvatarByUid(user.getUserAvatar(),user.getUid());
  }

  /**
   * 修改用户的昵称，信息，地区，性别
   * @param userInfo
   * @return
   */
  @PostMapping("/updateUserInfoByUid")
  public int updateUserInfoByUid(@RequestBody User userInfo){
    System.out.println("请求过来了没有啊");
    return userServer.updateUserInfoByUid(userInfo.getUserName(),userInfo.getUserInfo(),
            userInfo.getUserArea(),userInfo.getUserSex(),userInfo.getUid());
  }




}
