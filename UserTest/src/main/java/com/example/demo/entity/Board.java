package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 掲示板情報 Entity
 */
@Entity
@Data
@Table(name = "board_info")
public class Board implements Serializable {
  /**
   * ID
   */
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  /**
   * タイトル
   */
  @Column(name = "title")
  private String title;
  /**
   * 内容
   */
  @Column(name = "body")
  private String body;
  /**
   * 作成者
   */
  @Column(name = "author")
  private String author;
  /**
   * 更新日時
   */
  @Column(name = "update_date")
  private Date updateDate;
  /**
   * 登録日時
   */
  @Column(name = "create_date")
  private Date createDate;
  /**
   * 削除日時
   */
  @Column(name = "delete_date")
  private Date deleteDate;
}