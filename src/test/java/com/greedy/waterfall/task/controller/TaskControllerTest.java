package com.greedy.waterfall.task.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.greedy.waterfall.output.controller.OutputController;
import com.greedy.waterfall.output.model.service.OutputService;
import com.greedy.waterfall.task.model.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml",
})
@WebAppConfiguration
public class TaskControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskControllerTest.class);
	
	@Inject
	private WebApplicationContext wac;
	
	@Autowired
	private TaskService tasktService;
	private TaskController controller;
	private MockMvc mvc;
	
	@org.junit.Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.debug("setUp TaskControllerTest mockMvc...");
	}
	
	//주소를 제대로 보내는지, 원하는 값을 제대로 보내는지
	
	@Test
	public void test() throws Exception {
		//this.mockmvc
	}
}
