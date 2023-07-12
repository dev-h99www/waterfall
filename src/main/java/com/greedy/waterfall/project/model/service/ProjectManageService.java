package com.greedy.waterfall.project.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

/**
 * <pre>
 * Class : ProjectManageService
 * Comment : 프로젝트 인원관리에 관한 기능의 로직을 수행하기위한 메소드를 선언한다.
 * 
 * History
 * 2022. 3. 13.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
public interface ProjectManageService {
	/**
	 * findProjectMember : 전달받은 페이징 조건으로 프로젝트에 배정된 회원목록을 조회한다.
	 * @param searchMap : 페이징조건과 프로젝트번호가 담겨져있는 Map을 전달받는다.
	 * @return manageProjectMemberInfo : 전달받은 조건에 맞는 회원목록과, 프로젝트 내 모든역할, 등록된 모든 부서목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	Map<String, Object> findProjectMember(Map<String, String> searchMap);
	
	/**
	 * findTeamMember : 팀 코드를 전달받아 해당 팀에 소속되고, 프로젝트에 배정되지않은 인원을 조회한다.
	 * @param parameter : 팀 코드와 프로젝트번호를 전달받는다.
	 * @return 해당 프로젝트와 팀 코드에 맞는 인원목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<MemberDTO> findTeamMember(Map<String, String> parameter);

	/**
	 * findMemberRole : 회원정보를 전달받아 회원이 프로젝트에 배정된 역할목록을 조회한다.
	 * @param 회원정보를 전달받는다.
	 * @return 배정된 역할 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectRoleDTO> findMemberRole(Map<String, Integer> memberInfo);

	/**
	 * registProjectMember : 개발자를 역할을 부여해 프로젝트에 등록한다.
	 * @param 개발자의 정보와 역할 정보를 전달받는다.
	 * @return 등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean registProjectMember(ProjectManageMemberDTO registInfo);
	
	/**
	 * modifyProjectMember : 프로젝트에 배정된 인원의 역할을 수정한다.
	 * @param 배정인원이 새롭게 부여받은 역할 정보를 전달받는다.
	 * @return 수정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean modifyProjectMember(ProjectManageMemberDTO modifyInfo);

	/**
	 * removeMemberInProject : 회원을 프로젝트에서 내보낸다.
	 * @param 프로젝트번호와 회원번호를 전달받는다.
	 * @return 내보내기 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean removeMemberInProject(Map<String, Integer> removeInfo);
}