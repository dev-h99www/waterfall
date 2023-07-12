package com.greedy.waterfall.member.controller;


import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.greedy.waterfall.member.model.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml",		
})
@WebAppConfiguration
public class MemberControllerTest {

	private MockMvc mvc;
	@Autowired
	private MemberService service;
	
	private MemberController controller;
	private BCryptPasswordEncoder passwordEncoder;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new MemberController(service, passwordEncoder)).build();
	}
	
	@Test
	public void 경로_제대로_찾아가는지() throws Exception {
		mvc.perform(get("/member/list"))
			.andExpect(forwardedUrl("/member/memberList"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void 제대로_전달한_값을_들고가는지_테스트() throws Exception {
		mvc.perform(get("/member/memberList/"))
		.andExpect(forwardedUrl("/member/memberList"))
		.andExpect(model().attributeExists("intent"))
		.andExpect(status().isOk());
	}
}
