package com.greedy.waterfall.issue.model.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.greedy.waterfall.common.exception.issue.IssueModifyException;
import com.greedy.waterfall.common.exception.issue.IssueRemoveException;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.mapper.IssueMapper;

@Component("issueStub")
@Primary
public class StubIssueServiceImplTest implements IssueService {

	public StubIssueServiceImplTest(IssueMapper mapper) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ProjectIssueCountDTO> selectAllProjectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IssueDTO> selectIssuesOfTask(int projectNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IssueDTO> selectIssueList(int taskNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registIssue(IssueDTO issue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> selectIssueDetail(Map<String, Integer> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IssueFileDTO findFile(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IssueFileDTO removeGuideFile(int fileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyIssue(Map<String, Object> condition) throws IssueModifyException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeIssue(int issueNo, int loginMemberNo) throws IssueRemoveException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> notifyIssueList(Map<String, Integer> identification) {
		// TODO Auto-generated method stub
		return null;
	}

}
