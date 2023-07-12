package com.greedy.waterfall.common.History;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;

/**
 * <pre>
 * Class : ProjectHistory
 * Comment : 프로젝트관리에서 발생한 이력을 문자열로 저장 후 반환한다.
 * 
 * History
 * 2022. 3. 13.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Component("projectHistory")
public class ProjectHistory implements History{

	/**
	 * registHistory : 프로젝트 생성 이력을 저장 후 반환한다.
	 * @param 프로젝트 생성 정보를 전달받는다.
	 * @return 생성 이력을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<ProjectHistoryDTO> registHistory(Object info) {
		/* 프로젝트 생성한 관리자의 정보와 프로젝트의 정보를 전달받는다. */
		MemberDTO findAdminInfo = (MemberDTO) ((Map<String, Object>) info).get("findAdminInfo");
		RegistProjectDTO newProject = (RegistProjectDTO) ((Map<String, Object>) info).get("newProject");
		List<ProjectHistoryDTO> projectRegistHistory = new ArrayList<ProjectHistoryDTO>();
		/* 프로젝트 생성 이력내용을 저장한다. */
		projectRegistHistory.add(ProjectHistoryDTO.builder()
				.projectNo(newProject.getProjectNo())
				.managerNo(newProject.getAdminNo())
				.contentType(PROJECT_HISTORY)
				.content("[" + findAdminInfo.getName() + "]님이 [" + newProject.getProjectName() + "]프로젝트를 [생성]했습니다.")
				.build());
		/* 프로젝트 pm등록 내역을 저장한다. */
		projectRegistHistory.add(ProjectHistoryDTO.builder()
				.projectNo(newProject.getProjectNo())
				.managerNo(newProject.getAdminNo())
				.contentType(PROJECT_HISTORY)
				.content("[" + findAdminInfo.getName() + "]님이 [" + newProject.getProjectName() + "]에 [" + newProject.getPmName() + "]님을 PM으로 등록했습니다.")
						.build());
		return projectRegistHistory;
	}

	/**
	 * modifyHistory : 프로젝트 수정 이력을 저장 후 반환한다.
	 * @param 프로젝트 수정 정보를 전달받는다.
	 * @return 수정 이력을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<ProjectHistoryDTO> modifyHistory(Object info) {
		/* 변경내용을 저장하기 위해 기존 정보와 수정 정보를 전달받는다. */
		RegistProjectDTO oldProject = (RegistProjectDTO) ((Map<String, Object>) info).get("oldProject"); 
		RegistProjectDTO newProject = (RegistProjectDTO) ((Map<String, Object>) info).get("newProject");
		List<ProjectHistoryDTO> projectHistoryList = new ArrayList<>();
		
		/* 기존정보와 다른 값이 있다면 변경이력에 추가한다. */
		if(!oldProject.getProjectName().equals(newProject.getProjectName())) {
			projectHistoryList.add(ProjectHistoryDTO.builder().content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 이름을 [" + newProject.getProjectName() + "](으)로 수정했습니다.")
										.projectNo(newProject.getProjectNo()).managerNo(newProject.getAdminNo()).contentType(PROJECT_HISTORY).build());
		}
		if(oldProject.getPmNumber() != newProject.getPmNumber()) {
			projectHistoryList.add(ProjectHistoryDTO.builder().content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 PM을 [" + oldProject.getPmName() + "]님 에서 [" + newProject.getPmName() + "]님으로 변경했습니다.")
					.projectNo(newProject.getProjectNo()).managerNo(newProject.getAdminNo()).contentType(PROJECT_HISTORY).build());
		}
		if(!oldProject.getStartDate().equals(newProject.getStartDate())) {
			projectHistoryList.add(ProjectHistoryDTO.builder().content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 시작일을 [" + oldProject.getStartDate() + "]에서 [" + newProject.getStartDate() +"]로 변경했습니다.")
					.projectNo(newProject.getProjectNo()).managerNo(newProject.getAdminNo()).contentType(PROJECT_HISTORY).build());
		}
		if(!oldProject.getDeadLine().equals(newProject.getDeadLine())) {
			projectHistoryList.add(ProjectHistoryDTO.builder().content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 마감일을 [" + oldProject.getDeadLine() + "]에서 [" + newProject.getDeadLine() +"]로 변경했습니다.")
					.projectNo(newProject.getProjectNo()).managerNo(newProject.getAdminNo()).contentType(PROJECT_HISTORY).build());
		}
		if(!oldProject.getProjectStatusCode().equals(newProject.getProjectStatusCode())) {
			projectHistoryList.add(ProjectHistoryDTO.builder().content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 상태를 [" + oldProject.getProjectStatusName() + "]에서 [" + newProject.getProjectStatusName() +"]로 변경했습니다.")
					.projectNo(newProject.getProjectNo()).managerNo(newProject.getAdminNo()).contentType(PROJECT_HISTORY).build());
		}
		if(oldProject.getProgression() != newProject.getProgression()) {
			projectHistoryList.add(ProjectHistoryDTO.builder().content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 진행률을 [" + oldProject.getProgression() + "%]에서 [" + newProject.getProgression() +"%]로 변경했습니다.")
					.projectNo(newProject.getProjectNo()).managerNo(newProject.getAdminNo()).contentType(PROJECT_HISTORY).build());
		}

		/* 저장한 이력 목록을 반환한다. */
		return projectHistoryList;
	}

	/**
	 * removeHistory : 프로젝트 삭제 이력을 저장 후 반환한다.
	 * @param 프로젝트 삭제 정보를 전달받는다.
	 * @return 삭제 이력을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<ProjectHistoryDTO> removeHistory(Object info) {
		/* 프로젝트를 삭제한 프로젝트관리인의 정보를 전달받는다. */
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		RegistProjectDTO projectInfo = (RegistProjectDTO) ((Map<String, Object>) info).get("projectInfo");
		MemberDTO memberInfo = (MemberDTO) ((Map<String, Object>) info).get("memberInfo");
		
		/* 프로젝트 삭제이력을 저장 후 반환한다. */
		ProjectHistoryDTO history = ProjectHistoryDTO.builder().projectNo(projectInfo.getProjectNo()).managerNo(memberInfo.getNo()).contentType(PROJECT_HISTORY)
										.content("[" + memberInfo.getName() + "]님이 [" + projectInfo.getProjectName() + "] 프로젝트를 삭제했습니다.").build();
		historyList.add(history);		

		return historyList;
	}

	/**
	 * recoveryHistory : 프로젝트 복구 이력을 저장 후 반환한다.
	 * @param 프로젝트 정보와 복구한 관리자의 정보를 전달받는다.
	 * @return 복구 이력을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<ProjectHistoryDTO> recoveryHistory(Object info) {
		/* 프로젝트를 복구한 관리자의 정보를 전달받는다. */
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		RegistProjectDTO projectInfo = (RegistProjectDTO) ((Map<String, Object>) info).get("projectInfo");
		MemberDTO memberInfo = (MemberDTO) ((Map<String, Object>) info).get("memberInfo");

		/* 프로젝트 복구이력을 저장 후 반환한다. */
		ProjectHistoryDTO history = ProjectHistoryDTO.builder().projectNo(projectInfo.getProjectNo()).managerNo(memberInfo.getNo())
				.content("[" + memberInfo.getName() + "]님이 [" + projectInfo.getProjectName() + "] 프로젝트를 복구했습니다.").build();

		historyList.add(history);		
		
		return historyList;
	}
}
 