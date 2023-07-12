package com.greedy.waterfall.project.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;

@Component("stubProjectService")
@Primary
public class StubProjectServiceImplTest implements ProjectService {

	@Override
	public MyProjectDTO findMyProject(Map<String, String> searchMap, MemberDTO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findRegistForm() {
		Map<String, Object> projectForm = new HashMap<>();
		List<ProjectStatusDTO> statusList = new ArrayList<>(); 
		List<DeptDTO> deptList = new ArrayList<>();
		projectForm.put("statusList", statusList);
		projectForm.put("deptList", deptList);
	
		return projectForm;
	}

	@Override
	public List<TeamDTO> findTeam(String deptCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDTO> findTeamMember(String teamCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registProject(RegistProjectDTO newProject) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public RegistProjectDTO findOneProjectInfo(int projectNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyProject(RegistProjectDTO project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProject(Map<String, Integer> removeInfo) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Map<String, Object> findProjectMainInfo(int projectNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO findBoardInfo(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean restoreProject(Map<String, Integer> restoreInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
