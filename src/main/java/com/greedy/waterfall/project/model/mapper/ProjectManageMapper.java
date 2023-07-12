package com.greedy.waterfall.project.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

/**
 * <pre>
 * Class : ProjectManageMapper
 * Comment : 프로젝트 인원관리를 위한 쿼리문을 연결하는인터페이스.
 * 
 * History
 * 2022. 3. 13.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Repository
public interface ProjectManageMapper {

	/**
	 * findProjectMember : 프로젝트에 배정된 인원목록을 조회한다. 
	 * @param 프로젝트번호와 페이징조건을 전달받는다.
	 * @return 인원목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectManageMemberDTO> findProjectMember(SelectCriteria selectCriteria);

	/**
	 * findProjectMemberCount : 검색조건에 맞는 배정인원의 수를 반환한다 
	 * @param 검색조건을 전달받는다.
	 * @return 배정인원 수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int findProjectMemberCount(Map<String, String> searchMap);
	
	/**
	 * findAllRole : 모든 역할목록을 조회한다.
	 * @return 모든 역할목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectRoleDTO> findAllRole();
	
	/**
	 * findAllDept : 모든 부서목록을 조회한다.
	 * @return 모든 부서목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<DeptDTO> findAllDept();

	/**
	 * registMemberToProject : 회원을 프로젝트배정내역에 추가한다
	 * @param 회원정보와 프로젝트 번호를 전달받는다.
	 * @return 회원 등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int registMemberToProject(ProjectManageMemberDTO registInfo);

	/**
	 * registRoleToProject : 회원의 배정역할을 등록한다.
	 * @param 회원번호와 회원의 배정역할을 전달받는다.
	 * @return 역할등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int registRoleToProject(Map<String, Integer> map);

	/**
	 * findNewMember : 전달받은 팀코드에 소속된 회원중 프로젝트에 배정되지않은 회원목록을 조회한다. 
	 * @param 팀코드와 프로젝트 번호를 전달받는다.
	 * @return 회원목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<MemberDTO> findNewMember(Map<String, String> parameter);

	/**
	 * findMemberRole : 회원이 배정받은 역할을 조회한다.
	 * @param 회원번호를 전달받는다.
	 * @return 배정역할목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectRoleDTO> findMemberRole(Map<String, Integer> memberInfo);

	/**
	 * removeOldRole : 회원의 이전 역할들을 삭제한다.
	 * @param 회원번호와 프로젝트번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int removeOldRole(ProjectManageMemberDTO modifyInfo);

	/**
	 * removeMemberRole : 회원의 역할 배정내역을 삭제한다.
	 * @param 회원번호와 프로젝트번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int removeMemberRole(Map<String, Integer> removeInfo);

	/**
	 * removeMemberJoinHistory : 회원의 프로젝트 배정내역을 삭제한다.
	 * @param 회원번호와 프로젝트번호를 전달받는다.
	 * @return 배정내역 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int removeMemberJoinHistory(Map<String, Integer> removeInfo);

	/**
	 * findMemberInfo : 회원번호와 프로젝트번호로 해당 프로젝트의 회원 역할배정정보를 조회한다.
	 * @param 회원번호와 프로젝트번호를 전달받는다.
	 * @return 회원이 배정받은 역할 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	ProjectManageMemberDTO findMemberInfo(ProjectManageMemberDTO memberInfo);

	/**
	 * registEntireHistoryProjectRegist : 인원관리내역을 등록한다.
	 * @param 인원관리내역의 상세내용을 전달받는다.
	 * @return 내역등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	Integer registEntireHistoryProjectRegist(ProjectHistoryDTO projectHistoryDTO); 
}
