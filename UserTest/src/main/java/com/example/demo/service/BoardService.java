package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoardRequest;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
/**
 * 掲示板情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BoardService {
  /**
   * 掲示板情報 Repository
   */
  @Autowired
  private BoardRepository boardRepository;
  /**
   * 掲示板情報 全検索
   * @return 検索結果
   */
  public List<Board> searchAll() {
    return boardRepository.findAll();
  }
  
  /**
   * 掲示板情報 主キー検索
   * @return 検索結果
   */
  public Board findById(Long id) {
    return boardRepository.findById(id).get();
  }
  
  /**
   * 掲示板情報 条件検索
   * @return 検索結果
   */
  public List<Board> findByAuthor(String author) {
    return boardRepository.findByAuthor(author);
  }
    
  /**
   * 掲示板情報 新規登録
   * @param boardRequest 掲示板情報
   */
  public void create(BoardRequest boardRequest) {
    Date now = new Date();
    Board board = new Board();
    board.setTitle(boardRequest.getTitle());
    board.setBody(boardRequest.getBody());
    board.setAuthor(boardRequest.getAuthor());
    board.setCreateDate(now);
    board.setUpdateDate(now);
    boardRepository.save(board);
  }
  
  /**
   * 掲示板情報 更新
   * @param boardRequest 掲示板編集情報
   */
  public void update(BoardRequest boardRequest) {
	Date now = new Date();
	Board board = new Board();
	board.setId(boardRequest.getId());
	board.setTitle(boardRequest.getTitle());
	board.setBody(boardRequest.getBody());
	board.setAuthor(boardRequest.getAuthor());
	board.setCreateDate(now);
	board.setUpdateDate(now);
	boardRepository.save(board);
  }

  /**
   * 掲示板情報論理削除
   * @param id
   */
  public void delete(Long id) {
	  boardRepository.deleteById(id);
	
  }
      
}