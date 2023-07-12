package com.greedy.waterfall.history.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.history.model.dto.HistoryDTO;
import com.greedy.waterfall.history.model.mapper.HistoryMapper;
import com.greedy.waterfall.issue.model.dto.IssueDTO;


@Service
public class HistoryServiceImpl implements HistoryService{

private final HistoryMapper mapper;
	
	@Autowired
	public HistoryServiceImpl(HistoryMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<HistoryDTO> selectEntireHistoryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoryDTO> selectIssueHistoryList(int projectNo) {
		
		List<HistoryDTO> issueHistoryList = mapper.selectIssueHistoryList(projectNo);
		
		return issueHistoryList;
	}

	@Override
	public List<HistoryDTO> selectTaskHistoryList(int projectNo) {
		
		List<HistoryDTO> taskHistoryList = mapper.selectTaskHistoryList(projectNo);
		
		return taskHistoryList;
	}

	@Override
	public List<HistoryDTO> selectOutputHistoryList(int projectNo) {
		
		List<HistoryDTO> outputHistoryList = mapper.selectOutputHistoryList(projectNo);
		
		return outputHistoryList;
	}

	@Override
	public List<HistoryDTO> selectMemberHistoryList(int projectNo) {
		
		List<HistoryDTO> memberHistoryList = mapper.selectMemberHistoryList(projectNo);
		
		return memberHistoryList;
	}

	@Override
	public List<HistoryDTO> selectProjectHistoryList(int projectNo) {
		
		List<HistoryDTO> projectHistoryList = mapper.selectProjectHistoryList(projectNo);
		
		return projectHistoryList;
	}

	@Override
	public List<HistoryDTO> selectAdminProjectHistoryList() {
		
		List<HistoryDTO> adminProjectHistoryList = mapper.selectAdminProjectHistoryList();
		
		return adminProjectHistoryList;
	}

}
