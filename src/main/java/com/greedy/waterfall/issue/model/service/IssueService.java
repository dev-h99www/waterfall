package com.greedy.waterfall.issue.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.exception.issue.IssueModifyException;
import com.greedy.waterfall.common.exception.issue.IssueRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueNotificationDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;

public interface IssueService {

	List<ProjectIssueCountDTO> selectAllProjectList();

	List<IssueDTO> selectIssuesOfTask(int projectNo);

	List<IssueDTO> selectIssueList(int taskNo);

	boolean registIssue(IssueDTO issue);

	Map<String, Object> selectIssueDetail(Map<String, Integer> condition);

	IssueFileDTO findFile(int no);

	IssueFileDTO removeGuideFile(int fileNumber);

	int modifyIssue(Map<String, Object> condition) throws IssueModifyException;

	int removeIssue(int issueNo, int loginMemberNo) throws IssueRemoveException;

	Map<String, Object> notifyIssueList(Map<String, Integer> identification);

}
