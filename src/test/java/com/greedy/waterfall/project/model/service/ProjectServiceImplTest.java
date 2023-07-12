package com.greedy.waterfall.project.model.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.waterfall.common.History.History;
import com.greedy.waterfall.common.History.ProjectHistory;
import com.greedy.waterfall.project.model.mapper.ProjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class ProjectServiceImplTest {

	private ProjectMapper mapper;
	private History history;
	
	private ProjectService service;
	
	@Before
	public void setUp() {
		service = new ProjectServiceImpl(mapper, null);
		history = new ProjectHistory();
	}
	
	@Test
	public void count_test() {
		Integer i = mapper.findManageProjectCount(null);
		System.out.println(i);
	}
	
	@Test
	@Transactional
	public void removeHistory_test() {
		Map<String, Integer> removeInfo = new HashMap<>();

		int projectNo = 64;
		int memberNo = 1;
		
		removeInfo.put("memberNo", memberNo);
		removeInfo.put("projectNo", projectNo);

		
		boolean result = service.removeProject(removeInfo);
		assertThat(result, is(true));
		
	}
	
	
}
