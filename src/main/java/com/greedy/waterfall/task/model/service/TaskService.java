package com.greedy.waterfall.task.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.task.model.dto.AllTaskCodeDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskHistoryDTO;
import com.greedy.waterfall.task.model.dto.TaskRegistDTO;

/**
 * <pre>
 * Class : TaskService
 * Comment : 업무 서비스 interface
 * 
 * History
 * 2022. 3. 13.  (김서영)
 * @version 1
 * @author 김서영
 */
public interface TaskService {

	List<TaskDTO> findTaskTimeline(TaskDTO taskDTO);

	AllTaskCodeDTO findAllTaskCode();

	List<ProjectMemberDTO> findProjectMember(int projectNo);

	boolean registTask(TaskRegistDTO taskRegistDTO);

	List<TaskCategoryDTO> findAllCategoryCode();

	List<ChildTaskDTO> findChildTaskList(int projectNo);

	ChildTaskDTO findTaskDetail(int taskNo);

	void updateTask(TaskRegistDTO taskUpdateDTO, TaskHistoryDTO history);

	void removeTask(TaskDTO task, TaskHistoryDTO history);


}
