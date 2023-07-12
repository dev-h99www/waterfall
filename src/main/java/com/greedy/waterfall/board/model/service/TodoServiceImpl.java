package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.board.model.mapper.TodoMapper;
import com.greedy.waterfall.common.exception.TodoModifyException;
import com.greedy.waterfall.common.exception.TodoRegistException;
import com.greedy.waterfall.common.exception.TodoRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;

@Service
public class TodoServiceImpl implements TodoService {
	
	private final TodoMapper mapper;
	
	@Autowired
	public TodoServiceImpl(TodoMapper mapper) {
		this.mapper = mapper;
	}
	
	/* 게시글 전체 목록 조회 메소드 */
	@Override
	public List<TodoDTO> findTodo(SelectCriteria selectCriteria) {
		
		List<TodoDTO> todoList = mapper.findTodo(selectCriteria);
		
		return todoList;
	}
	
	/* 게시글 전체 갯수 조회 메소드 */
	@Override
	public int selectTotalCount(Map<String, String> searchMap) {
		
		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}
	
	/* 게시글 상세 조회 메소드 */
	@Override
	public TodoDTO detailTodo(int no) {

		int result = mapper.incrementTodoCount(no);
	       
        TodoDTO todoDetail = new TodoDTO();
       
       	if(result > 0) {
       		todoDetail = mapper.selectTodoDetail(no);
       	}   
       
        return todoDetail;
	}
	
	/* 게시글 수정 메소드 */
	@Override
	public void modifyTodo(TodoDTO todo) throws TodoModifyException {
		
		int result = mapper.updateTodo(todo);
		
		if(!(result > 0)) {
			throw new TodoModifyException("To Do 수정에 실패하셨습니다.");
		}
		
	}
	
	/* 게시글 등록 메소드 */
	@Override
	public void registTodo(TodoDTO todo) throws TodoRegistException {

		int result = mapper.insertTodo(todo);
		
		if(!(result > 0)) {
			throw new TodoRegistException("To Do 등록에 실패하셨습니다.");
		}
	}
	
	/* 게시글 삭제 메소드 */
	@Override
	public void removeTodo(int no) throws TodoRemoveException {

		int result = mapper.deleteTodo(no);
		
		if(!(result > 0)) {
			throw new TodoRemoveException("To Do 삭제에 실패하셨습니다.");
		}
	}
	
	/* 첨부 파일 */
	@Override
	public FileDTO findFile(int no) {

		return mapper.findFile(no);
	}

}
