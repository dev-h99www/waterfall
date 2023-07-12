package com.greedy.waterfall.menu.model.mapper;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
		
})
@WebAppConfiguration
public class MenuMapperTest {
	@Autowired
	private MenuMapper mapper;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findProjectHistory_test() {
		List<ProjectHistoryDTO> history = mapper.findProjectHistory(2);
		
		for(int i = 0; i < history.size(); i++) {
			System.out.println(history.get(i).getContent());
		}
	}


}
