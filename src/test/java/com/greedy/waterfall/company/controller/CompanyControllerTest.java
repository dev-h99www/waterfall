package com.greedy.waterfall.company.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.greedy.waterfall.company.model.service.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml",
})
@WebAppConfiguration
public class CompanyControllerTest {
	
	@Autowired
	private CompanyService companyService;
	private CompanyController controller;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new CompanyController(companyService)).build();
	}
	
	@Test
	public void 경로_제대로_찾아가는지() throws Exception {
		mvc.perform(get("/company/dept/list"))
			.andExpect(forwardedUrl("/company/dept/deptList"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void 제대로_전달한_값을_들고가는지_테스트() throws Exception {
		mvc.perform(get("/company/dept/list"))
			.andExpect(forwardedUrl("/company/dept/deptList"))
			.andExpect(model().attributeExists("intent"))
			.andExpect(status().isOk());
	}

}
