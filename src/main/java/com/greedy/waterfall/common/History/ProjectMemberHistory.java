package com.greedy.waterfall.common.History;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

/**
 * <pre>
 * Class : ProjectMemberHistory
 * Comment : 프로젝트 인원관리에서 발생한 이력을 문자열로 저장 후 반환한다.
 * 
 * History
 * 2022. 3. 13.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Component("memberHistory")
public class ProjectMemberHistory implements History{

	/**
	 * registHistory : 프로젝트 인원배정 내역을 저장 후 반환한다.
	 * @param 개발자의 정보와 배정 역할목록을 전달받는다.
	 * @return 생성이력을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<ProjectHistoryDTO> registHistory(Object info) {
		/* 인원정보와 인원이 배정받은 역할목록을 전달받는다. */
		ProjectManageMemberDTO historyInfo = (ProjectManageMemberDTO) info;
		List<ProjectRoleDTO> roleList = historyInfo.getRole();
		String roleName = roleString(roleList);
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		
		/* 전달받은 인원 이름과 역할이름으로 배정내역을 저장 후 반환한다. */
		historyList.add(ProjectHistoryDTO.builder().content("[" + historyInfo.getManagerName() + "]님이 [" + historyInfo.getProjectName() + "]프로젝트에 [" + historyInfo.getMemberName() +"]님을 " + roleName + "역할로 배정했습니다.")
				.projectNo(historyInfo.getProjectNo()).managerNo(historyInfo.getManagerNo()).contentType(MEMBER_HISTORY).build());
		
		return historyList;
	}

	/**
	 * modifyHistory : 프로젝트 배정인원의 역할 변경사항을 저장 후 반환한다.
	 * @param 프로젝트 배정인원의 정보화 변경 역할을 전달받는다.
	 * @return 변경내역을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<ProjectHistoryDTO> modifyHistory(Object info) {
		/* 인원의 기존 역할과 새로 부여받은 역할을 전달받는다. */
		Map<String, Object> historyInfo = (Map<String, Object>) info;
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		ProjectManageMemberDTO oldInfo = (ProjectManageMemberDTO) historyInfo.get("oldInfo"); 
		ProjectManageMemberDTO newInfo = (ProjectManageMemberDTO) historyInfo.get("newInfo"); 
		String oldRole = roleString(oldInfo.getRole());
		String newRole = roleString(newInfo.getRole());
		/* 기존역할과 변경역할을 이력에 반영하여 저장 후 반환한다. */
		historyList.add(ProjectHistoryDTO.builder().content("[" + oldInfo.getManagerName() + "]님이 [" + oldInfo.getMemberName() + "]님의 역할을 " + oldRole +" 에서  " + newRole + "로 수정했습니다.")
				.projectNo(oldInfo.getProjectNo()).managerNo(oldInfo.getManagerNo()).contentType(MEMBER_HISTORY).build());
		
		return historyList;
	}

	/**
	 * removeHistory : 프로젝트에서 내보내기한 이력을 반환한다.
	 * @param 회원정보와 프로젝트정보를 전달받는다.
	 * @return 내보내기 한 이력을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<ProjectHistoryDTO> removeHistory(Object info) {
		/* 회원정보와 프로젝트를 전달받는다. */
		ProjectManageMemberDTO historyInfo = (ProjectManageMemberDTO) info;
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		/* 내보내기 이력 저장 후 반환한다. */
		historyList.add(ProjectHistoryDTO.builder().content("[" + historyInfo.getManagerName() + "]님이 [" + historyInfo.getProjectName() + "]프로젝트에서 [" + historyInfo.getMemberName() +"]님을 내보냈습니다.")
				.projectNo(historyInfo.getProjectNo()).managerNo(historyInfo.getManagerNo()).contentType(MEMBER_HISTORY).build());
		
		return historyList;
	}

	@Override
	public List<ProjectHistoryDTO> recoveryHistory(Object info) {

		return null;
	}
	
	/**
	 * roleString : 전달받은 역할목록을 합친 후 반환한다.
	 * @param 역할목록을 전달받는다.
	 * @return 합친 문자열을 반환한다.
	 * 
	 * @author 홍성원
	 */
	private String roleString(List<ProjectRoleDTO> roleList) {
		String roleName = "[ ";
		if(roleList != null) {
			for(int i = 0; i < roleList.size(); i++) {
				roleName += roleList.get(i).getRoleName() + " ";
			}
		}
		roleName += "]";
		
		return roleName;
	}
}