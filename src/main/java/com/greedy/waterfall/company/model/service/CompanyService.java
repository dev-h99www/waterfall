package com.greedy.waterfall.company.model.service;

import java.util.List;

import com.greedy.waterfall.common.exception.company.DeptModifyException;
import com.greedy.waterfall.common.exception.company.DeptRegistException;
import com.greedy.waterfall.common.exception.company.DeptRemoveException;
import com.greedy.waterfall.common.exception.company.JobModifyException;
import com.greedy.waterfall.common.exception.company.JobRegistException;
import com.greedy.waterfall.common.exception.company.JobRemoveException;
import com.greedy.waterfall.common.exception.company.TeamModifyException;
import com.greedy.waterfall.common.exception.company.TeamRegistException;
import com.greedy.waterfall.common.exception.company.TeamRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;
import com.greedy.waterfall.company.model.dto.TeamDTO;

public interface CompanyService {

	List<DeptDTO> findDept(SelectCriteria selectCriteria);
	
	List<TeamDTO> findTeam(SelectCriteria selectCriteria);

	List<TeamDTO> findTeamList(String deptCode);

	void registDept(DeptDTO dept) throws DeptRegistException;

	DeptDTO detailDept(String code);

	void modifyDept(DeptDTO dept) throws DeptModifyException;

	void removeDept(String code) throws DeptRemoveException;

	void registTeam(TeamDTO team) throws TeamRegistException;

	TeamDTO detailTeam(String code);

	void modifyTeam(TeamDTO team) throws TeamModifyException;

	void removeTeam(String code) throws TeamRemoveException;

	List<JobDTO> findJob(SelectCriteria selectCriteria);

	void registJob(JobDTO job) throws JobRegistException;

	JobDTO detailJob(String code);

	void modifyJob(JobDTO job) throws JobModifyException;

	void removeJob(String code) throws JobRemoveException;
	
}
