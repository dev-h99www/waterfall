package com.greedy.waterfall.task.model.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.jta.SpringJtaSynchronizationAdapter;

import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml",
})
@WebAppConfiguration
public class TaskMapperTest {
	
	@Autowired
	private TaskMapper mapper;
	
	@Test
	public void 모든_상위업무를_조사해오는가() {
		int projectNo = 55;
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setProjectNo(projectNo);
		
		List<TaskDTO> parentTaskList = mapper.selectParentTaskList(taskDTO);
		
		assertThat(parentTaskList.size(), is(2));
	}
	
	@Test
	public void childTask_상세정보_조사해오는가() {
		
		int taskNo = 72;
		int memberNo = 1;
		int projectNo = 54;
		
		ChildTaskDTO childTask = new ChildTaskDTO();
		TaskDTO parentTask = childTask.getParentTask();
		
		childTask = mapper.selectChildTask(taskNo);
		
		
	}
	

}
