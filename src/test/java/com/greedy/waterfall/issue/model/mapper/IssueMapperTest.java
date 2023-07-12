package com.greedy.waterfall.issue.model.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueTaskCodeManageDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class IssueMapperTest {
	
	@Autowired
	private IssueMapper mapper;
	
	@Test
	public void 이슈목록_조회_확인() {
		
		int taskNo = 1; 
		List<IssueDTO> result = (List<IssueDTO>) mapper.selectIssueList(taskNo);
		
		assertThat(result.size(), is(16));
	}
	
	@Test
	public void 프로젝트_목록_조회_확인() {
		
		List<ProjectIssueCountDTO> result = (List<ProjectIssueCountDTO>) mapper.selectAllProjectList();
		
		assertThat(result.size(), is(5));
	}
	
	@Test
	public void 프로젝트_별_업무_목록_및_개수_조회_확인() {
		
		int projectNo = 54;
		
		List<IssueDTO> result =  mapper.selectIssuesOfTask(projectNo);
		
		assertThat(result.size(), is(6));
	}
	
	@Test
	public void 이슈_등록_확인() {
		
	}
	
	@Test
	public void 첨부파일_저장_확인() {
		
	}
	
	@Test
	public void 개별_이슈_조회() {
		
		int no = 150;
		
		IssueDTO result = mapper.selectIssueDetail(no);
		
		assertThat(result.getNo(), is(150));
	}
	
	@Test
	public void 프로젝트_소속_인원_조회() {
		
		int projectNo = 54;
		
		List<ProjectMemberDTO> result = (List<ProjectMemberDTO>) mapper.selectProjectMember(projectNo);
		
		assertThat(result.size(), is(3));
	}
	
	@Test
	public void 다운로드_받을_파일_정보_찾기() {
		
		int no = 144;
		
		IssueFileDTO result = mapper.findFile(no);
		
		assertThat(result.getOriginalName(), is("맛탱이간 페페.jfif"));
	}
	
	@Test
	public void 파일_삭제하기() {
		
		int fileNumber = 145;	//테스트하고 삭제 됐을 수 있으니 삭제할 파일 정보를 찾아야함
		
		int result = mapper.deleteIssueFile(fileNumber);
		
		System.out.println("삭제할파일번호"+result);
	}
	
	@Test
	public void 개별_이슈_수정() {
		
	}
	
	@Test
	public void 개별_이슈_삭제() {
		
		/* 서비스 메소드에서 히스토리에 재활용하려고 넣어준 dto다 */
		IssueDTO issue = new IssueDTO();
		issue.setNo(149);
		
		int result = mapper.deleteIssue(issue);
		
		assertThat(result, is(149));
	}
	
	@Test
	public void 등록한_히스토리_조회() {
		
	}
	
	@Test
	public void 수정한_히스토리_조회() {
		
	}
	
	@Test
	public void 삭제한_히스토리_조회() {
		
	}
}







































