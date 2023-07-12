package com.greedy.waterfall.issue.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.exception.issue.IssueModifyException;
import com.greedy.waterfall.common.exception.issue.IssueRegistException;
import com.greedy.waterfall.common.exception.issue.IssueRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueHistoryDTO;
import com.greedy.waterfall.issue.model.dto.IssueNotificationDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.issue.model.mapper.IssueMapper;
import com.greedy.waterfall.member.model.dto.MemberDTO;

@Service
public class IssueServiceImpl implements IssueService {

	private final IssueMapper mapper;
	
	@Autowired
	public IssueServiceImpl(IssueMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<ProjectIssueCountDTO> selectAllProjectList() {
		List<ProjectIssueCountDTO> allProject = mapper.selectAllProjectList();
		return allProject;
	}
	
	@Override
	public List<IssueDTO> selectIssuesOfTask(int projectNo) {
		List<IssueDTO> taskIssueList = mapper.selectIssuesOfTask(projectNo);
		return taskIssueList;
	}
	
	@Override
	public List<IssueDTO> selectIssueList(int taskNo) {
		List<IssueDTO> issueList = mapper.selectIssueList(taskNo);
		return issueList;
	}
	
	@Override
	public boolean registIssue(IssueDTO issue) {
		
		boolean result = false;
		
		
		List<IssueFileDTO> files = issue.getFile();
		System.out.println("IssueServiceImpl의 registIssue의 files 는  " + files);
		if(mapper.registIssue(issue) > 0) {
			result = true;
		
			if(files != null) {
				int count = 0;
				for(int i = 0; i < files.size(); i++) {
					
					files.get(i).setRefIssueNo(issue.getNo());
					count += mapper.registIssueFile(files.get(i));
				}
				if(count != files.size()) {
					result = false;
				} 
			}
			
			/* 이슈 등록 시 히스토리 반영하기 위해서 mapper로 보내줌 */
			
			int registerNo = issue.getRegisterNo();
			
			int managerNo = issue.getManagerNo();
			
			Map<String, Integer> condition = new HashMap<>();
			
			condition.put("registerNo", registerNo);
			condition.put("managerNo", managerNo);
			
			IssueHistoryDTO history = new IssueHistoryDTO();
			
			mapper.registIssueHistory(issue);
			
			history.setIssue(issue);
			
			mapper.writeRegistedIssueHistory(issue);
		}
		return result;
	}

	@Override
	public Map<String, Object> selectIssueDetail(Map<String, Integer> condition) {
		
		int no = condition.get("no");
		
		int projectNo = condition.get("projectNo");
		
		IssueDTO issueDetail = mapper.selectIssueDetail(no);
		
		List<ProjectMemberDTO> projectMemberList= mapper.selectProjectMember(projectNo);
		
		Map<String, Object> issueDetailMap = new HashMap<>();
		
		issueDetailMap.put("projectMemberList", projectMemberList);
		issueDetailMap.put("issueDetail", issueDetail);
		
		return issueDetailMap;
	}

	@Override
	public IssueFileDTO findFile(int no) {
		
		return mapper.findFile(no);
	}

	@Override
	public IssueFileDTO removeGuideFile(int fileNumber) {
		IssueFileDTO issueFileDTO = mapper.findFile(fileNumber);
		
		mapper.deleteIssueFile(fileNumber);
				
		return issueFileDTO;
	}

	/**
	 * modifyIssue : 이슈 수정에 대한 비즈니스 로직 처리를 위한 메소드 
	 * @param condition : 이슈 수정에 필요한 데이터가 담긴 매개변수
	 * 
	 * @author 박성준
	 */
	@Override
	public int modifyIssue(Map<String, Object> condition) throws IssueModifyException {
		
		/* 매개변수에서 값 분리 */
		IssueDTO issue = (IssueDTO) condition.get("issue");
		int loginMemberNo = (int) condition.get("loginMemberNo");
		
		int result = mapper.updateIssue(issue);
		
		/* 이슈에서 따로 파일을 추출해서 파일을 등록 할 수 있게 한다. */
		List<IssueFileDTO> files = issue.getFile();
		System.out.println("IssueServiceImpl의 updateIssue의 files 는  " + files);
		
		if(result > 0) {
			
			/* 파일에 해당 이슈번호를 넣어줌 */
			if(files != null) {
				
				int count = 0;
				
				for(int i = 0; i < files.size(); i++) {
					
					files.get(i).setRefIssueNo(issue.getNo());
					count += mapper.registIssueFile(files.get(i));
				}
				System.out.println("총 등록한 파일의 숫자 : " + count);
			}
			
			/*이슈 수정 히스토리를 알림에 사용할 히스토리와 이슈 히스토리 조회 기능에서 사용할 수 있게 매퍼로 전송함 */
			issue.setLoginMemberNo(loginMemberNo);
			mapper.updateIssueHistory(issue);
			mapper.writeUpdatedIssueHistory(issue);
			
		}
		
		/* 이슈 수정 실패시 발생하는 예외 */
		if(!(result > 0)) {
			throw new IssueModifyException("이슈 수정에 실패하셨습니다.");
		}
		
		return result;
	}

	@Override
	public int removeIssue(int issueNo, int loginMemberNo) throws IssueRemoveException {
		
		System.out.println("int no의 정보는 :" + issueNo);
		
		IssueDTO issue = mapper.selectIssueDetail(issueNo);
		
		int taskNo = issue.getTaskNo();
		
		System.out.println("removeIssue의 DTO 정보는 : " + issue);

		int result = mapper.deleteIssue(issue);
		
		if(!(result > 0)) {
			throw new IssueRemoveException("가이드 게시글 삭제에 실패하셨습니다.");
		}
		
		issue.setLoginMemberNo(loginMemberNo);
		System.out.println("로그인멤버 : " + loginMemberNo);
		
		mapper.deleteIssueHistory(issue);
		
		mapper.writeDeletedIssueHistory(issue);
		
		return taskNo;
	}

	@Override
	public Map<String, Object> notifyIssueList(Map<String, Integer> identification) {
		
		int loginMemberNo = identification.get("loginMember");

		int issueHistoryNo = identification.get("issueHistoryNo");
		
		Map<String, Integer> condition = new HashMap<>();
		
		condition.put("loginMemberNo", loginMemberNo);
		condition.put("issueHistoryNo", issueHistoryNo);
		
		mapper.removeCheck(condition);
		
		List<IssueNotificationDTO> notificationList = mapper.notifyIssueList(loginMemberNo);
		
		int count = mapper.notifyCount(loginMemberNo);
		
		Map<String, Object> notification = new HashMap<>();
		
		notification.put("notificationList", notificationList);
		notification.put("count", count);
		
		return notification;
	}
}

		
		
