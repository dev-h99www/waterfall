package com.greedy.waterfall.company.model.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
		
})
@WebAppConfiguration
public class CompanyMapperTest {
	
	@Autowired
	private CompanyMapper mapper;
	
	@Test
	public void 모든부서를_조회했을때_그_갯수가_맞게_조회가되는지() {
		
		SelectCriteria searchCondition = new SelectCriteria().builder().build();
		List<DeptDTO> testList = mapper.findDept(searchCondition);
		
		assertThat(testList.size(), is(6));
	}
	
}
