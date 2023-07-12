package com.greedy.waterfall.issue.model.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.common.History.ProjectHistory;
import com.greedy.waterfall.common.exception.issue.IssueModifyException;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.mapper.IssueMapper;
import com.greedy.waterfall.project.model.service.ProjectServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class IssueServiceImplTest {
	
	private IssueMapper mapper;
	
	private StubIssueServiceImplTest service;
	
	@Before
	public void setUp() {
		service = new StubIssueServiceImplTest(mapper);
	}
	
	@Test
	public void modifyIssue() throws IssueModifyException {
		IssueDTO issue = new IssueDTO();
		
		int loginMemberNo  = 0;
		
		Map<String, Object> condition = new HashMap<>();
		
		condition.put("issue", issue);
		condition.put("loginMemberNo",  loginMemberNo);
		
		service.modifyIssue(condition);
		
		assertThat(service.modifyIssue(condition), is(0));
	}
}
