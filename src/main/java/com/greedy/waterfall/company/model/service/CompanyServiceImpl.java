package com.greedy.waterfall.company.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.greedy.waterfall.company.model.dao.CompanyMapper;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;
import com.greedy.waterfall.company.model.dto.TeamDTO;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyMapper mapper;
	
	@Autowired
	public CompanyServiceImpl(CompanyMapper mapper) {
		this.mapper = mapper;
	}
	
	/* 부서 조회 */
	@Override
	public List<DeptDTO> findDept(SelectCriteria selectCriteria) {

		List<DeptDTO> deptList = mapper.findDept(selectCriteria);

		return deptList;
	}

	/* 팀 조회 */
	@Override
	public List<TeamDTO> findTeam(SelectCriteria selectCriteria) {

		List<TeamDTO> teamList = mapper.findTeam(selectCriteria);
		
		return teamList;
	}
	
	/* 팀 리스트 조회 */
	@Override
	public List<TeamDTO> findTeamList(String deptCode) {

		return mapper.findTeamList(deptCode);
	}

	/* 부서 생성 */
	@Override
	public void registDept(DeptDTO dept) throws DeptRegistException {

		int result = mapper.insertDept(dept);
		
		if(!(result > 0)) {
			throw new DeptRegistException("부서 등록에 실패하셨습니다.");
		}
	}
	
	/* 부서 상세 */
	@Override
	public DeptDTO detailDept(String code) {

		DeptDTO deptDetail = null;
		
		deptDetail = mapper.detailDept(code);
		
		return deptDetail;
	}
	
	/* 부서 수정 */
	@Override
	public void modifyDept(DeptDTO dept) throws DeptModifyException {

		int result = mapper.updateDept(dept);
		
		if(!(result > 0)) {
			throw new DeptModifyException("부서 수정에 실패하셨습니다.");
		}
	}
	
	/* 부서 삭제 */
	@Override
	public void removeDept(String code) throws DeptRemoveException {
		
		int result = mapper.deleteDept(code);
		
		if(!(result > 0)) {
			throw new DeptRemoveException("부서 삭제에 실패하셨습니다.");
		}
	}

	/* 팀 생성 */
	@Override
	public void registTeam(TeamDTO team) throws TeamRegistException {

		int result = mapper.insertTeam(team);
		
		if(!(result > 0)) {
			throw new TeamRegistException("팀 등록에 실패하셨습니다.");
		}
	}
	
	/* 팀 상세 */
	@Override
	public TeamDTO detailTeam(String code) {

		TeamDTO teamDetail = null;
		
		teamDetail = mapper.detailTeam(code);
		
		return teamDetail;
	}

	/* 팀 수정 */
	@Override
	public void modifyTeam(TeamDTO team) throws TeamModifyException {
		
		int result = mapper.updateTeam(team);
		
		if(!(result > 0)) {
			throw new TeamModifyException("팀 수정에 실패하셨습니다.");
		}
	}
	
	/* 팀 삭제 */
	@Override
	public void removeTeam(String code) throws TeamRemoveException {

		int result = mapper.deleteTeam(code);
		
		if(!(result > 0)) {
			throw new TeamRemoveException("팀 삭제에 실패하셨습니다.");
		}
	}
	
	/* 직급 조회 */
	@Override
	public List<JobDTO> findJob(SelectCriteria selectCriteria) {

		List<JobDTO> jobList = mapper.findJob(selectCriteria);
		
		return jobList;
	}
	
	/* 직급 생성 */
	@Override
	public void registJob(JobDTO job) throws JobRegistException {

		int result = mapper.insertJob(job);
		
		if(!(result > 0)) {
			throw new JobRegistException("직급 등록에 실패하셨습니다.");
		}
	}
	
	/* 직급 상세 */
	@Override
	public JobDTO detailJob(String code) {

		JobDTO jobDetail = null;
		
		jobDetail = mapper.detailJob(code);
		
		return jobDetail;
	}
	
	/* 직급 수정 */
	@Override
	public void modifyJob(JobDTO job) throws JobModifyException {

		int result = mapper.updateJob(job);
		
		if(!(result > 0)) {
			throw new JobModifyException("직급 수정에 실패하셨습니다.");
		}
	}
	
	/* 직급 삭제 */
	@Override
	public void removeJob(String code) throws JobRemoveException {

		int result = mapper.deleteJob(code);
		
		if(!(result > 0)) {
			throw new JobRemoveException("직급 삭제에 실패하셨습니다.");
		}
	}

}
