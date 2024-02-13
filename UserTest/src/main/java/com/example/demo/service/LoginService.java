package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;


/**
 * 掲示板情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {
  /**
   * Login情報 Repository
   */
  @Autowired
  private LoginRepository loginRepository;

  /**
 * ユーザ情報検索
 * @param user ログインID
 * @return ユーザ情報検索結果(1件)
 */
public Optional<Login>user_search(String user) {
	
	return loginRepository.findById(user);
}
    
}