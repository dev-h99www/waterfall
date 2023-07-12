package com.greedy.waterfall.history.model.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.history.model.dto.HistoryDTO;
import com.greedy.waterfall.issue.model.dto.IssueDTO;

@Repository
public interface HistoryMapper {

	List<HistoryDTO> selectIssueHistoryList(int projectNo);

	List<HistoryDTO> selectTaskHistoryList(int projectNo);

	List<HistoryDTO> selectOutputHistoryList(int projectNo);

	List<HistoryDTO> selectMemberHistoryList(int projectNo);

	List<HistoryDTO> selectProjectHistoryList(int projectNo);

	List<HistoryDTO> selectAdminProjectHistoryList();

}
