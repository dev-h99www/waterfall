package com.greedy.waterfall.project.model.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
		
})
@WebAppConfiguration
public class ProjectMapperTest {
	@Autowired
	private ProjectMapper mapper;
	private SelectCriteria sampleSC;

	@Before
	public void setUp() {
		sampleSC = new SelectCriteria().builder().startRow(1).endRow(100).member(new MemberDTO().builder().no(44).build()).build();
	
	}
	/**
	 * testFindManagaProject : 모든 프로젝트를 조회한다.
	 * @author 홍성원
	 */
	@Test
	@Transactional
	public void testFindManagaProject() {
		SelectCriteria selectCriteria = new SelectCriteria();
		List<ProjectDTO> result = mapper.findAllManageProject(selectCriteria);
		
		
		assertThat(result.size(), is(equalTo(2)));
	}

	@Test
	public void testFindJoinProject() {
	}

	@Test
	public void testFindAllProjectStatus() {
		 
	}

	@Test
	public void testFindAllDept() {
		 
	}

	@Test
	public void testFindTeam() {
		 
	}

	@Test
	public void testFindTeamMember() {
		 
	}

	@Test
	public void testRegistProject() {
		 
	}

	@Test
	public void testRegistPm() {
		 
	}

	@Test
	public void testRegistMemberProject() {
		 
	}

	@Test
	public void testFindAllManageProject() {
		SelectCriteria sc = new SelectCriteria().builder().startRow(1).endRow(100).build();
		List<ProjectDTO> projectResultList = mapper.findAllManageProject(sc);
		
		for(int i = 0; i < projectResultList.size(); i++) {
//			System.out.println(i + " : " + projectResultList.get(i));
			System.out.println(projectResultList.get(i).getProjectInfo());
		}	}

	@Test
	public void testFindAllRemovedProject() {
		 
	}

	@Test
	public void testFindAllManageProjectCount() {
		 
	}

	@Test
	public void testFindPmNumber() {
		 
	}

	@Test
	public void testFindOneProjectInfo() {
		 
	}

	@Test
	public void testRegistProjectHistory() {
		 
	}

	@Test
	public void testModifyProject() {
		 
	}

	@Test
	public void testFindMemberInProject() {
		 
	}

	@Test
	public void testJoinPmInProject() {
		 
	}

	@Test
	public void testAssignPmRole() {
		 
	}

	@Test
	public void testKickOldPm() {
		 
	}

	@Test
	public void testRemoveProject() {
		 
	}

	@Test
	public void testRestoreProject() {
		 
	}

	@Test
	public void testFindMainBoardList() {
		 
	}

	@Test
	public void testFindBoardInfo() {
		 
	}

	@Test
	public void testIncreaseBoardCount() {
		 
	}

	@Test
	public void testFindManageProjectCount() {
		 
	}

	@Test
	public void testFindAllJoinProjectCount() {
		 
	}

	@Test
	public void testFindAllRemovedProjectCount() {
		 
	}

	@Test
	public void testFindProjectMainInfo() {
		 
	}

	@Test
	public void testProjectRoleRemove() {
		 
	}

	@Test
	public void testFindAdminInfo() {
		 
	}

	@Test
	public void testRegistEntireHistoryProjectRegist() {
		 
	}
	
}
