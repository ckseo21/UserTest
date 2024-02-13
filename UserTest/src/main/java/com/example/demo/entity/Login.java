package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ログイン情報 Entity
 */
@Entity
@Data
@Table(name = "user_info")
public class Login implements Serializable {
  /**
   * ユーザID
   */
  @Id
  @Column(name = "user_id")
  private String user;
  /**
   * パスワード
   */
  @Column(name = "password")
  private String password;
   
}