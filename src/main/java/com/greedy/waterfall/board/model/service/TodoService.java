package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.common.exception.TodoModifyException;
import com.greedy.waterfall.common.exception.TodoRegistException;
import com.greedy.waterfall.common.exception.TodoRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface TodoService {

	/* 게시글 전체 목록 */
	List<TodoDTO> findTodo(SelectCriteria selectCriteria);
	
	/* 게시글 전체 갯수 */
	int selectTotalCount(Map<String, String> searchMap);
	
	/* 게시글 상세 조회 */
	TodoDTO detailTodo(int no);
	
	/* 게시글 수정 */
	void modifyTodo(TodoDTO todo) throws TodoModifyException;
	
	/* 게시글 등록 */
	void registTodo(TodoDTO todo) throws TodoRegistException;
	
	/* 게시글 삭제 */
	void removeTodo(int no) throws TodoRemoveException;
	
	/* 파일 다운로드 */
	FileDTO findFile(int no);

}
