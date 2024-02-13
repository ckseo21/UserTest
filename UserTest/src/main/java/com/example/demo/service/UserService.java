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
  public void user_create(UserInfoRequest userInfoRequest) {
    Date now = new Date();
    User user = new User();
    user.setUser(userInfoRequest.getUser());
    user.setPassword(userInfoRequest.getPassword());
    user.setName(userInfoRequest.getName());
    user.setCreateDate(now);
    userRepository.save(user);
  }
  
  /**
   * ユーザ情報 全検索
   * @return 検索結果
   */
  public List<User> searchUser() {
    return userRepository.findAll();
  }
      
}
