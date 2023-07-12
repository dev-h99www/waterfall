package com.greedy.waterfall.task.model.dao;

import java.util.List;

import com.greedy.waterfall.task.model.dto.ChildTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.ParentTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskHistoryDTO;
import com.greedy.waterfall.task.model.dto.TaskRegistDTO;

public interface TaskMapper {

	/**
	 * selectParentTaskList : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<TaskDTO> selectParentTaskList(TaskDTO taskDTO);

	/**
	 * selectChildTaskList : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<ChildTaskDTO> selectChildTaskList(int taskNo);

	/**
	 * selectAllProjectMember : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<ProjectMemberDTO> selectAllProjectMember(int projectNo);

	/**
	 * registTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int registTask(TaskRegistDTO taskRegistDTO);

	/**
	 * selectParentCategory : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<ParentTaskCategoryDTO> selectParentCategory();

	/**
	 * selectChildCategory : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<ChildTaskCategoryDTO> selectChildCategory();

	/**
	 * selectParentTaskNo : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	Integer selectParentTaskNo(TaskRegistDTO taskRegistDTO);

	/**
	 * registHistory : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	void registHistory(TaskRegistDTO taskRegistDTO);

	/**
	 * selectAllCategoryCode : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<TaskCategoryDTO> selectAllCategoryCode();

	/**
	 * selectAllchildTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<ChildTaskDTO> selectAllchildTask(int projectNo);

	/**
	 * selectChildTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	ChildTaskDTO selectChildTask(int taskNo);

	/**
	 * selectParentTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	TaskDTO selectParentTask(int parentNo);

	/**
	 * updateTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int updateTask(TaskRegistDTO taskUpdateDTO);

	/**
	 * insertUpdateHistory : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int insertUpdateHistory(TaskRegistDTO taskUpdateDTO);

	/**
	 * selectMemberName : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	String selectMemberName(int memberNo);

	/**
	 * selectTaskName : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	String selectTaskName(TaskHistoryDTO history);

	/**
	 * insertEntireUpdateHistory : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	void insertEntireUpdateHistory(TaskRegistDTO taskUpdateDTO);

	/**
	 * selectRefTaskCode : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	Integer selectRefTaskCode(TaskDTO task);

	/**
	 * deleteTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int deleteTask(TaskDTO task);

	/**
	 * insertDeleteHistory : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int insertDeleteHistory(TaskDTO task);

	/**
	 * selectHistoryInfo : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	TaskHistoryDTO selectHistoryInfo(TaskHistoryDTO history);

	/**
	 * insertEntireDeleteHistory : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int insertEntireDeleteHistory(TaskHistoryDTO history);

	/**
	 * deleteChildTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int deleteChildTask(TaskDTO task);

	/**
	 * insertEntireRegistHistroy : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	void insertEntireRegistHistroy(TaskRegistDTO taskRegistDTO);

	/**
	 * selectParentTaskNoCurrval : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	Integer selectParentTaskNoCurrval(TaskRegistDTO taskRegistDTO);

	/**
	 * registParentTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int registParentTask(TaskRegistDTO taskRegistDTO);

}
