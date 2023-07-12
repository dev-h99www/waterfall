package com.greedy.waterfall.company.model.dao;

import java.util.List;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;
import com.greedy.waterfall.company.model.dto.TeamDTO;

public interface CompanyMapper {

	List<DeptDTO> findDept(SelectCriteria selectCriteria);

	List<TeamDTO> findTeam(SelectCriteria selectCriteria);

	List<TeamDTO> findTeamList(String deptCode);

	int insertDept(DeptDTO dept);

	DeptDTO detailDept(String code);

	int updateDept(DeptDTO dept);

	int deleteDept(String code);

	int insertTeam(TeamDTO team);

	TeamDTO detailTeam(String code);

	int updateTeam(TeamDTO team);

	int deleteTeam(String code);
	
	List<JobDTO> findJob(SelectCriteria selectCriteria);

	int insertJob(JobDTO job);

	JobDTO detailJob(String code);

	int updateJob(JobDTO job);

	int deleteJob(String code);

}
