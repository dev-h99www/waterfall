package com.greedy.waterfall.history.model.service;

import java.util.List;

import com.greedy.waterfall.history.model.dto.HistoryDTO;

public interface HistoryService {

	List<HistoryDTO> selectIssueHistoryList(int projectNo);

	List<HistoryDTO> selectEntireHistoryList();

	List<HistoryDTO> selectTaskHistoryList(int projectNo);

	List<HistoryDTO> selectOutputHistoryList(int projectNo);

	List<HistoryDTO> selectMemberHistoryList(int projectNo);

	List<HistoryDTO> selectProjectHistoryList(int projectNo);

	List<HistoryDTO> selectAdminProjectHistoryList();

}
