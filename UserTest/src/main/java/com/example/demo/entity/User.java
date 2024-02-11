package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 掲示板情報 Entity
 */
@Entity
@Data
@Table(name = "user_info")
public class User implements Serializable {
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
  /**
   * 登録日時
   */
  @Column(name = "create_date")
  private Date createDate;
  /**
   * お名前
   */
  @Column(name = "name")
  private String name;
  
}
