package com.greedy.waterfall.member.model.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.member.model.dto.DeptDTO;

@RunWith(SpringJUnit4ClassRunner.class) //메타정도 등록해주기 !
@ContextConfiguration(locations = { //빈이 등록되어있는 경로 이것을 써줘야 오토와이어드를 써서 그 안에 있는 빈을 사용할 수 있다.
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml",
		
})
@WebAppConfiguration
public class MemberMapperTest {
	//오토와이어드에 테스트할 매퍼 컨트롤러 서비스를 기술해주면 된다.
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void 모든부서를_조회했을때_다_조회가_되는지() {
		List<DeptDTO> testList = mapper.findDept();
		//앞에 적는 콤마 기준으로 앞은 실제값 , 뒤에 is는 기댓값
		assertThat(testList.size(), is(6));
	}
}
