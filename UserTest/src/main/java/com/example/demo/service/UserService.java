package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserInfoRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
/**
 * 掲示板情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
  /**
   * ユーザ情報 Repository
   */
  @Autowired
  private UserRepository userRepository;
  //Added by SEO(2024/02/09)
  /**
   * 掲示板情報 新規登録
   * @param userInfoRequest 掲示板情報
   */
  public User user_create(UserInfoRequest userInfoRequest) {
    Date now = new Date();
    User user = new User();
    String userid = userInfoRequest.getUser();
    user.setUser(userid);
    user.setPassword(userInfoRequest.getPassword());
    user.setName(userInfoRequest.getName());
    user.setCreateDate(now);
    boolean isuser = userRepository.existsById(userid);
    if(isuser == false) {
    	userRepository.save(user);
    }
    else {
    	user = null;
    }
    //userRepository.save(user);
    return user;
  }
  
  /**
   * ユーザ情報 全検索
   * @return 検索結果
   */
  public List<User> searchUser() {
    return userRepository.findAll();
  }
      
}
