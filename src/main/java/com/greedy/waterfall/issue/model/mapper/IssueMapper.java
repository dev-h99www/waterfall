package com.greedy.waterfall.issue.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueNotificationDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;

public interface IssueMapper {

	List<ProjectIssueCountDTO> selectAllProjectList();

	List<IssueDTO> selectIssuesOfTask(int projectNo);

	List<IssueDTO> selectIssueList(int taskNo);

	int registIssue(IssueDTO issue);

	int registIssueFile(IssueFileDTO issueFileDTO);

	IssueDTO selectIssueDetail(int no);

	List<ProjectMemberDTO> selectProjectMember(int projectNo);

	IssueFileDTO findFile(int no);

	int deleteIssueFile(int fileNumber);

	/**
	 * updateIssue : 이슈 수정에 필요한 데이터를 xml파일로 전달하기 위한 메소드
	 * @param issue : 이슈 수정에 필요한 데이터
	 * 
	 * @author 박성준
	 */
	int updateIssue(IssueDTO issue);

	int deleteIssue(IssueDTO issue);

	void registIssueHistory(IssueDTO issue);

	/**
	 * updateIssueHistory : 이슈 알림에 필요한 데이터를 xml 파일로 전달하기 위한 메소드
	 * @param issue : 이슈 알림에 필요한 데이터
	 * 
	 * @author 박성준
	 */
	void updateIssueHistory(IssueDTO issue);

	void deleteIssueHistory(IssueDTO issue);

	void writeRegistedIssueHistory(IssueDTO issue);

	/**
	 * writeUpdatedIssueHistory : 이슈 히스토리 조회에 필요한 데이터를 xml 파일로 전달하기 위한 메소드
	 * @param issue : 이슈 히스토리 조회에 필요한 데이터
	 * 
	 * @author 박성준
	 */
	void writeUpdatedIssueHistory(IssueDTO issue);

	void writeDeletedIssueHistory(IssueDTO issue);

	List<IssueNotificationDTO> notifyIssueList(int loginMemberNo);

	int notifyCount(int loginMemberNo);

	void removeCheck(Map<String, Integer> condition);

}
