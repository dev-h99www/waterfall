package com.greedy.waterfall.project.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.History.History;
import com.greedy.waterfall.common.paging.Paging;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.common.result.Result;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;
import com.greedy.waterfall.project.model.mapper.ProjectManageMapper;

/**
 * <pre>
 * Class : ProjectManageServiceImpl
 * Comment : 프로젝트 인원관리에 관한 기능의 로직을 수행한다.
 * 
 * History
 * 2022. 3. 13.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Service
public class ProjectManageServiceImpl implements ProjectManageService{
	
	@Autowired
	@Qualifier("memberHistory")
	private History history;
	
	private final ProjectManageMapper mapper;
	private final Paging paging;

	@Autowired
	public ProjectManageServiceImpl(ProjectManageMapper mapper, Paging paging) {
		this.mapper = mapper;
		this.paging = paging;

	}
	
	/**
	 * findProjectMember : 전달받은 페이징 조건으로 프로젝트에 배정된 회원목록을 조회한다.
	 * @param searchMap : 페이징조건과 프로젝트번호가 담겨져있는 Map을 전달받는다.
	 * @return manageProjectMemberInfo : 전달받은 조건에 맞는 회원목록과, 프로젝트 내 모든역할, 등록된 모든 부서목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public Map<String, Object> findProjectMember(Map<String, String> searchMap) {

		Map<String, Object> manageProjectMemberInfo = new HashedMap();
		
		searchMap.put("totalCount", Integer.toString(mapper.findProjectMemberCount(searchMap)));
		SelectCriteria selectCriteria = paging.setPagingCondition(searchMap);
		
		List<ProjectManageMemberDTO> memberList = mapper.findProjectMember(selectCriteria); 
		List<ProjectRoleDTO> allRole = mapper.findAllRole();
		List<DeptDTO> allDept = mapper.findAllDept();
		
		manageProjectMemberInfo.put("memberList" , memberList);
		manageProjectMemberInfo.put("allRole", allRole);
		manageProjectMemberInfo.put("allDept", allDept);
		manageProjectMemberInfo.put("selectCriteria", selectCriteria);
		return manageProjectMemberInfo;
	}

	/**
	 * findTeamMember : 팀 코드를 전달받아 해당 팀에 소속되고, 프로젝트에 배정되지않은 인원을 조회한다.
	 * @param parameter : 팀 코드와 프로젝트번호를 전달받는다.
	 * @return 해당 프로젝트와 팀 코드에 맞는 인원목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<MemberDTO> findTeamMember(Map<String, String> parameter) {
		List<MemberDTO> list = mapper.findNewMember(parameter);
		
		return mapper.findNewMember(parameter);
	}

	/**
	 * findMemberRole : 회원정보를 전달받아 회원이 프로젝트에 배정된 역할목록을 조회한다.
	 * @param 회원정보를 전달받는다.
	 * @return 배정된 역할 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<ProjectRoleDTO> findMemberRole(Map<String, Integer> memberInfo) {

		return mapper.findMemberRole(memberInfo);
	}
	
	/**
	 * registProjectMember : 개발자를 역할을 부여해 프로젝트에 등록한다.
	 * @param 개발자의 정보와 역할 정보를 전달받는다.
	 * @return 등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean registProjectMember(ProjectManageMemberDTO registInfo) {
		/* 프로젝트 인원배정내역에 등록한다. */
		/* 프로젝트 역할배정내역에 등록한다. */ 
		/* 인원배정과 역할배정내역 등록에 성공하면 성공값을 반환한다. */
		Result registProjectMember = new Result().perform(mapper.registMemberToProject(registInfo))
									.and(registRoleToProject(registInfo));

		if(registProjectMember.result()) {
			ProjectManageMemberDTO historyInfo = mapper.findMemberInfo(registInfo);
			
			List<ProjectHistoryDTO> memberResigtHistory = history.registHistory(historyInfo);
			if(registHistoryResult(memberResigtHistory)) {
				
				return true;
			}
		}
		
		/* 인원배정과 역할배정내역 등록에 실패하면 실패값을 반환한다. */
		return false;
	}

	/**
	 * modifyProjectMember : 프로젝트에 배정된 인원의 역할을 수정한다.
	 * @param 배정인원이 새롭게 부여받은 역할 정보를 전달받는다.
	 * @return 수정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean modifyProjectMember(ProjectManageMemberDTO modifyInfo) {
		/* 기존의 역할목록을 조회한다. */
		ProjectManageMemberDTO oldInfo = mapper.findMemberInfo(modifyInfo);
		Map<String, Object> historyInfo = new HashMap<>();

		/* 기존 역할을 지우고 새로운 역할을 배정한다. */
		if(removeOldRole(modifyInfo) && registRoleToProject(modifyInfo)) {
			/* 역할 수정이 성공하면 역할 수정내역을 프로젝트 이력테이블에 저장한다. */
			ProjectManageMemberDTO newInfo = mapper.findMemberInfo(modifyInfo);
			historyInfo.put("newInfo", newInfo);
			historyInfo.put("oldInfo", oldInfo);
			/* 수정된 개발자와, 수정한 담당자의 정보를 전달해, 이력내역을 반환받는다. */
			List<ProjectHistoryDTO> modifyHistoryList = history.modifyHistory(historyInfo);
			/* 이력등록에 성공하면 true를 반환한다. */
			if(registHistoryResult(modifyHistoryList)) {
				
				return true;
			}
		}
		
		/* 수정 및 이력등록중 하나라도 실패하면 false를 반환한다. */
		return false;
	}
	
	/**
	 * removeMemberInProject : 회원을 프로젝트에서 내보낸다.
	 * @param 프로젝트번호와 회원번호를 전달받는다.
	 * @return 내보내기 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean removeMemberInProject(Map<String, Integer> removeInfo) {
		ProjectManageMemberDTO projectInfo = ProjectManageMemberDTO.builder().memberNo(removeInfo.get("memberNo"))
													.projectNo(removeInfo.get("projectNo")).build();
		/* 이력을 등록하기위해 회원의 이름을 조회한다. */		
		projectInfo = mapper.findMemberInfo(projectInfo);
		/* 멤버의 역할배정내역과 하차일등록에 성공하면 하차내역을 등록한다. */
		if(isSuccess(mapper.removeMemberRole(removeInfo)) && isSuccess(mapper.removeMemberJoinHistory(removeInfo))) {
			List<ProjectHistoryDTO> removeHistoryList = history.removeHistory(projectInfo);
			if(registHistoryResult(removeHistoryList)) {
				
				return true;
			}
		}
		
		return false;
	}

	/**
	 * removeOldRole : 인원의 기존 배정된 역할을 삭제한다.
	 * @param 회원정보를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	private boolean removeOldRole(ProjectManageMemberDTO modifyInfo) {
		
		return mapper.removeOldRole(modifyInfo) > 0 ? true: false;
	}
	
	/**
	 * registHistoryResult : 프로젝트 인원관리중 발생한 이력내용이 담긴 목록을 전달받은 후 이력테이블에 저장한다. 전달받은 목록이 모두 등록에 성공하면 true를 반환하고, 실패하면 false를 반환한다.
	 * @param projectHistoryList : 발생한 이력정보가 담긴 리스트형태의 변수를 전달받는다.
	 * @return 전달받은 이력정보리스트의 등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	private boolean registHistoryResult(List<ProjectHistoryDTO> projectHistoryList) {
		Result registHistoryResult = new Result();
		for(int i = 0; i < projectHistoryList.size(); i++) {
			
			registHistoryResult.and(mapper.registEntireHistoryProjectRegist(projectHistoryList.get(i)));
		}
			
		return registHistoryResult.result();
	}
	
	/**
	 * isSuccess : 정수를 전달받아 0보다 클 때 true를 반환하고, 0보다 크지않다면 false를 반환한다.
	 * @param 정수를 전달받는다.
	 * @return 정수에따른 true, false값을 반환한다.
	 * 
	 * @author 홍성원
	 */
	private boolean isSuccess(int result) {
		if(result > 0) {
			return true;
		}

		return false;
	}
	
	/**
	 * registRoleToProject : 개발자가 배정받은 프로젝트 내 역할을 프로젝트 역할배정 내역에 등록한다.
	 * @param 개발자의 번호와 역할번호, 프로젝트 번호를 전달받는다.
	 * @return 역할배정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	private boolean registRoleToProject(ProjectManageMemberDTO registInfo) {
		/* 역할 부여받은 갯수만큼 Insert를 반복하고 , Insert가 모두 성공하면 true를 반환한다. */
		Result registProjectRole = new Result();
		for(int i = 0; i < registInfo.getRole().size(); i++) {
			registProjectRole.perform(mapper.registRoleToProject(parsingRegistInfo(registInfo, i)));
		}
		
		/* 한번이라도 실패한다면 false를 반환한다.*/
		return registProjectRole.result();
	}

	/**
	 * parsingRegistInfo : 프로젝트 역할배정시 전달받은 배정정보를 전달받아 등록할 수 있는 형태의 자료형으로 반환한다. 
	 * @param registInfo : 회원의 정보를 전달받는다.
	 * @param index : registInfo에 저장된 회원의 역할번호를 꺼내기위해 전달받는다.
	 * @return 회원의 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	private Map<String, Integer> parsingRegistInfo(ProjectManageMemberDTO registInfo, int index) {

		Map<String, Integer> registRoleInfo  = new HashMap<String, Integer>();
		registRoleInfo.put("memberNo",  registInfo.getMemberNo());
		registRoleInfo.put("roleNo",  registInfo.getRole().get(index).getRoleNo());
		registRoleInfo.put("projectNo",  registInfo.getProjectNo());
		registRoleInfo.put("managerNo",  registInfo.getManagerNo());
		
		
		return registRoleInfo;
	}
}