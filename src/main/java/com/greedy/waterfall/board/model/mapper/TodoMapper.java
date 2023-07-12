package com.greedy.waterfall.board.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface TodoMapper {
	
	/* 게시글 전체 목록 조회 */
	List<TodoDTO> findTodo(SelectCriteria selectCriteria);
	
	/* 게시글 전체 갯수 조회 */
	int selectTotalCount(Map<String, String> searchMap);
	
	/* 조회수 */
	int incrementTodoCount(int no);
	
	/* 게시글 상세 조회 */
	TodoDTO selectTodoDetail(int no);
	
	/* 게시글 수정 */
	int updateTodo(TodoDTO todo);
	
	/* 게시글 등록 */
	int insertTodo(TodoDTO todo);
	
	/* 게시글 삭제 */
	int deleteTodo(int no);
	
	/* 첨부 파일 */
	FileDTO findFile(int no);

}
