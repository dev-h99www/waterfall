package com.greedy.waterfall.task.model.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.task.model.dao.TaskMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class TaskServiceImplTest {
	
	private TaskMapper mapper;
	private TaskService service;

	@Test
	public void 등록_테스트() {
		
	}
}
