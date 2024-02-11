package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 作成者情報 リクエストデータ
 */
@Data
public class UserRequest implements Serializable {
  /**
   * 作成者
   */
  @NotEmpty(message = "お名前を入力してください")
  @Size(max = 20, message = "作成者は20文字以内で入力してください")
  private String author;

}