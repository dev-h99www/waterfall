package com.greedy.waterfall.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.greedy.waterfall.board.model.service.MeetingService;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class MeetingControllerTest {

	@Autowired
	@Qualifier("stubMeetingService")
	private MeetingService service;
	private MockMvc mvc;

    
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new MeetingController(service)).build();
        
	}


	@Test
	public void testFindMeetingBoardList() throws Exception {
		ProjectAuthorityDTO auth = new ProjectAuthorityDTO();
		
		mvc.perform(
				get("/meeting/list")
					.sessionAttr("projectAutority", auth))
			.andExpect(status().isOk())
			.andExpect(model().size(4))
			.andExpect(model().attributeExists("intent"))
			.andExpect(model().attributeExists("meetingList"))
			.andExpect(model().attributeExists("selectCriteria"))
			.andExpect(forwardedUrl("/board/meeting/meetingList"))
			.andDo(print());
	}

	@Test
	public void testDownloadFile() throws Exception {
	
		mvc.perform(get("/meeting/download/1")).andExpect(status().isOk());
	}

	@Test
	public void testFindMeetingBoardDetail() throws Exception {
		mvc.perform(get("/meeting/detail/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json; charset=UTF-8"))
			.andExpect(model().attributeExists("meeting"))
			.andDo(print());
	}

	@Test
	public void testRemoveMeetingBoard() throws Exception {
		mvc.perform(get("/meeting/remove/123211"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/meeting/list"))
			.andExpect(flash().attributeExists("message"))
			.andDo(print());
	}

	@Test
	public void testRegistMeetingBoard() throws Exception {
		mvc.perform(
				post("/meeting/regist")
				)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/meeting/list"))
			.andExpect(flash().attributeExists("message"))
			.andDo(print());
	}

	@Test
	public void testModifyBoard() throws Exception {
		mvc.perform(get("/meeting/modify/22"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("meeting"))
			.andExpect(forwardedUrl("/board/meeting/meetingModify"))
			.andDo(print());
	}

	@Test
	public void testRemoveMeetingBoardFile() throws Exception {
		mvc.perform(get("/meeting/deleteFile/22"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"))
		.andExpect(redirectedUrl("/meeting/list"))
		.andDo(print());
	
	}

}
