package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザ情報 リクエストデータ
 */
@Data
public class UserInfoRequest implements Serializable {
  /**
    * ユーザID
	*/
	@NotEmpty(message = "ユーザIDを入力してください")
	@Size(max = 20, message = "ユーザIDは20文字以内で入力してください")
	private String user;
	/**
    * パスワード
	*/
	@NotEmpty(message = "パスワードを入力してください")
	@Size(max = 20, message = "パスワードは20文字以内で入力してください")
	private String password;
	/**
    * お名前
	*/
	@NotEmpty(message = "お名前を入力してください")
	@Size(max = 20, message = "お名前は20文字以内で入力してください")
	private String name;

}
