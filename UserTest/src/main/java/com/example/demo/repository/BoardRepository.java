package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

/**
 * 掲示板情報 Repository
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	//掲示板情報削除
	int deleteById(int id);
	//掲示板情報条件検索
	List<Board> findByAuthor(String author);

}

