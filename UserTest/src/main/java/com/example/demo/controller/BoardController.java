package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.BoardRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.UserInfoRequest;
import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.service.BoardService;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;

/**
 * 掲示板情報 Controller
 */
@Controller
public class BoardController {
  /**
   * 掲示板情報 Service
   */
  @Autowired
  private BoardService boardService;
  @Autowired
  private UserService userService;
  @Autowired
  private LoginService loginService;
  /**
   * 掲示板情報一覧画面を表示
   * @param model モデル
   * @return 掲示板情報一覧画面
   */
  @GetMapping(value = "/board/list")
  public String displayList(Model model) {
    List<Board> boardList = boardService.searchAll();
    model.addAttribute("boardlist", boardList);
    return "board/list";
  }
  /**
   * 掲示板初期画面を表示
   * @param model モデル
   * @return 掲示板情報検索画面
   */
  @GetMapping(value = "/board")
  public String displayStart(Model model) {
    List<Board> userList = null;
    model.addAttribute("userlist", userList);
    return "board/search";
  }
  /**
   * 検索画面を表示
   * @param userRequest 作成者情報
   * @param author 
   * @return 掲示板情報検索画面
   */
  @RequestMapping(value = "/board/search", method = RequestMethod.POST)
  public String searchResult(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
	  if (result.hasErrors()) {
	      // 入力チェックエラーの場合
	      List<String> errorList = new ArrayList<String>();
	      for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	      }
	      model.addAttribute("validationError", errorList);
	      return "board/search";
	   }
	  String author = userRequest.getAuthor();
	  List<Board> userList = boardService.findByAuthor(author);
	  if (userList.isEmpty()) {
		  model.addAttribute("userlist", null);
		  model.addAttribute("validationError", "該当の作成者は存在しません。");
	  } else {
		  model.addAttribute("userlist", userList);
	  }
      return "board/search";
  }
  /**
   * 掲示板新規登録画面を表示
   * @param model Model
   * @return 掲示板情報登録画面
   */
  @GetMapping(value = "/board/add")
  public String displayAdd(Model model) {
    model.addAttribute("boardRequest", new BoardRequest());
    return "board/add";
  }
  /**
   * 掲示板新規登録
   * @param boardRequest リクエストデータ
   * @param model Model
   * @return 掲示板初期画面
   */
  @RequestMapping(value = "/board/create", method = RequestMethod.POST)
  public String create(@Validated @ModelAttribute BoardRequest boardRequest, BindingResult result, Model model) {
    if (result.hasErrors()) {
      // 入力チェックエラーの場合
      List<String> errorList = new ArrayList<String>();
      for (ObjectError error : result.getAllErrors()) {
        errorList.add(error.getDefaultMessage());
      }
      model.addAttribute("validationError", errorList);
      return "board/add";
    }
    // 掲示板情報の登録
    boardService.create(boardRequest);
    if(boardRequest.getAuthor() == null) {
    	model.addAttribute("validationError", "登録作業が失敗しました。");
    }
    else {
    	String author = boardRequest.getAuthor();
		List<Board> userList = boardService.findByAuthor(author);
		model.addAttribute("userlist", userList);
    }
    //return "redirect:/board";
    return "board/search";
  }
  
  /**
   * 掲示板更新
   * @param boardRequest リクエストデータ
   * @param model Model
   * @return 掲示板初期画面
   */
  @RequestMapping(value = "/board/update", method = RequestMethod.POST)
  public String update(@Validated @ModelAttribute BoardRequest boardRequest, BindingResult result, Model model) {
      if (result.hasErrors()) {
          List<String> errorList = new ArrayList<String>();
          for (ObjectError error : result.getAllErrors()) {
              errorList.add(error.getDefaultMessage());
          }
          model.addAttribute("validationError", errorList);
          return "board/edit";
      }
      // 掲示板情報の更新
      boardService.update(boardRequest);
      return "redirect:/board";
  }
  
  /**
   * 掲示板情報詳細画面を表示
   * @param id 表示する掲示板ID
   * @param model Model
   * @return 掲示板情報詳細画面
   */
  @GetMapping("/board/{id}")
  public String displayView(@PathVariable Long id, Model model) {
    Board board = boardService.findById(id);
    model.addAttribute("boardData", board);
    return "board/view";
  }
  
  /**
   * 掲示板編集画面を表示
   * @param id ID
   * @param model Model
   * @return 掲示板編集画面
   */
  @GetMapping("/board/{id}/edit")
  public String displayEdit(@PathVariable Long id, Model model) {
	  Board board = boardService.findById(id);
	  model.addAttribute("boardData", board);
      return "board/edit";
  }
  
  /**
   * 掲示板情報削除（論理削除）
   * @param id 掲示板ID
   * @param model Model
   * @return 掲示板初期画面
   */
  @GetMapping("/board/{id}/delete")
  public String delete(@PathVariable Long id, Model model) {
      // 掲示板情報の削除
	  boardService.delete(id);
      return "redirect:/board";
  } 
  
  //Added by SEO(2024/02/09)
  /**
   * ユーザ登録画面を表示
   * @param model Model
   * @return ユーザ登録画面
   */
  @GetMapping("/board/user")
  public String displayUser(Model model) {
	  model.addAttribute("userInfoRequest", new UserInfoRequest());
      return "board/user";
  }

  /**
   * ユーザID新規登録
   * @param userInfoRequest リクエストデータ
   * @param model Model
   * @return 掲示板初期画面
   */
  @RequestMapping(value = "/user/create", method = RequestMethod.POST)
  public String user_create(@Validated @ModelAttribute UserInfoRequest userInfoRequest, BindingResult result, Model model) {
    if (result.hasErrors()) {
      // 入力チェックエラーの場合
      List<String> errorList = new ArrayList<String>();
      for (ObjectError error : result.getAllErrors()) {
        errorList.add(error.getDefaultMessage());
      }
      model.addAttribute("validationError", errorList);
      return "board/user";
    }
    // 掲示板情報の登録
    //userService.user_create(userInfoRequest);
    User user = userService.user_create(userInfoRequest);
    if(user == null) {
    	model.addAttribute("validationError", "ユーザIDは既に存在します。");
    	return "board/user";
    } 
   	return "redirect:/board/userlist";

  }
  
  /**
   * ユーザ情報画面を表示
   * @param model モデル
   * @return ユーザ情覧画面
   */
  @GetMapping(value = "/board/userlist")
  public String searchUser(Model model) {
    List<User> userList = userService.searchUser();
    model.addAttribute("userlist", userList);
    return "board/userlist";
  }
  
  //Added by SEO(2024/02/12)
	/**
	 * 初期画面
	 * 
	 * @param model モデル
	 * @return 初期画面
	 */	
	@GetMapping("/login")
	public String userInfo(Model model, LoginRequest loginRequest) {
		
		return "board/login";
		
	}
	
	/**
	 * ログイン
	 * 
	 * @param model モデル
	 * @param bdResult エラーチェック結果
	 * @param form ログイン画面
	 * @return 結果画面
	 */
	@RequestMapping(value = "/login/search", method = RequestMethod.POST)
	  public String user_search(@Validated @ModelAttribute LoginRequest loginRequest, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	      // 入力チェックエラーの場合
	      List<String> errorList = new ArrayList<String>();
	      for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	      }
	      model.addAttribute("validationError", errorList);
	      return "board/login";
	    }
		var user = loginService.user_search(loginRequest.getUser());
		var isCorrectUserAuth = user.isPresent()
				&& loginRequest.getPassword().equals(user.get().getPassword());
		if (isCorrectUserAuth) {
			  String author = loginRequest.getUser();
			  List<Board> userList = boardService.findByAuthor(author);
			  if (userList.isEmpty()) {
				  model.addAttribute("userlist", null);
				  model.addAttribute("validationError", "該当の作成者は存在しません。");
			  } else {
				  model.addAttribute("userlist", userList);
			  }
		      return "board/search";
			//model.addAttribute("info", loginRequest);
			//return "board/result";
		} else {
			model.addAttribute("validationError", "IDまたはパスワードが間違っています。");
			return "board/login";
		}
	}
   
}