package com.greedy.waterfall.project.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.menu.model.dto.ProjectInfoDTO;
import com.greedy.waterfall.project.model.dto.BoardCategoryDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;

/**
 * <pre>
 * Class : ProjectMapper
 * Comment : 프로젝트 관리에 필요한 목록조회, 생성, 수정 , 삭제 , 조회기능을 하는 쿼리문을 연결한다.
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Repository
public interface ProjectMapper {

	/**
	 * findManagaProject : 관리중인 프로젝트목록을 조회한다.
	 * @param 회원번호와 페이징설정정보를 전달받는다.
	 * @return 관리중인 프로젝트의 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectDTO> findManagaProject(SelectCriteria selectCriteria);

	/**
	 * findJoinProject : 참여중인 프로젝트목록을 조회한다.
	 * @param 회원번호와 페이징설정정보를 전달받는다.
	 * @return 참여중인 프로젝트의 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectDTO> findJoinProject(SelectCriteria subselectCriteria);
	
	/**
	 * findAllProjectStatus : 삭제되지않은 모든 프로젝트 목록을 조회한다.
	 * @return 모든 프로젝트 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectStatusDTO> findAllProjectStatus();

	/**
	 * findAllDept : 모든 부서를 조회한다.
	 * @return 모든 부서목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<DeptDTO> findAllDept();
	
	/**
	 * findTeam  : 모든 팀을 조회한다.
	 * @return 모든 팀 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<TeamDTO> findTeam(String deptCode);

	/**
	 * findTeamMember : 팀에 소속된 인원을 조회한다.
	 * @param 팀 코드를 전달받는다.
	 * @return 팀에 소속된 인원목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<MemberDTO> findTeamMember(String teamCode);

	/**
	 * registProject : 프로젝트를 생성한다.
	 * @param 프로젝트 정보를 전달받는다.
	 * @return 프로젝트 생성 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int registProject(RegistProjectDTO newProject);

	/**
	 * registPm : 프로젝트의 pm을 등록한다.
	 * @param pm정보와 프로젝트번호를 전달받는다.
	 * @return pm등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int registPm(RegistProjectDTO newProject);

	/**
	 * registMemberProject : 프로젝트에 인원을 배정한다.
	 * @param 인원정보와 프로젝트번호를 전달받는다.
	 * @return 인원등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int registMemberProject(RegistProjectDTO newProject);

	/**
	 * findAllManageProject : 프로젝트 관리목록을 조회한다.
	 * @param 회원정보와 페이징설정정보를 전달받는다.
	 * @return 프로젝트 관리목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectDTO> findAllManageProject(SelectCriteria selectCriteria);

	/**
	 * findAllRemovedProject : 삭제된 프로젝트목록을 조회한다.
	 * @param 페이징 설정정보를 전달받는다.
	 * @return 삭제된 프로젝트 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectDTO> findAllRemovedProject(SelectCriteria subselectCriteria);

	/**
	 * findAllManageProjectCount : 모든 프로젝트를 조회한다.
	 * @param 페이징설정을 전달받는다.
	 * @return 프로젝트 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	int findAllManageProjectCount(Map<String, String> searchMap);
	
	/**
	 * findPmNumber : 프로젝트메인페이지 이동 시 , 해당 프로젝트의 pm 회원번호를 조회한다.
	 * @param 이동하는 프로젝트의 번호를 전달받는다.
	 * @return 프로젝트의 pm번호를 반환한다.
	 * 
	 * @author 홍성원
	 */
	ProjectAuthorityDTO findPmNumber(int projectNo);

	/**
	 * findOneProjectInfo : 프로젝트 수정을 위해 프로젝트 상세정보를 조회한다.
	 * @param 프로젝트의 번호를 전달받는다.
	 * @return 프로젝트의 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	RegistProjectDTO findOneProjectInfo(int projectNo);

	/**
	 * registProjectHistory : 프로젝트 생성시 프로젝트 생성 이력을 기록한다.
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	int registProjectHistory(int projectNo);

	/**
	 * modifyProject : 프로젝트 정보를 수정한다.
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	Integer modifyProject(RegistProjectDTO newProject);

	/**
	 * findMemberInProject : 프로젝트에 참여중인 인원을 조회한다.
	 * @param 프로젝트의 정보를 전달받는다.
	 * @return 인원목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	Integer findMemberInProject(RegistProjectDTO newProject);

	/**
	 * joinPmInProject : pm을 프로젝트에 배정한다.
	 * @param pm과 프로젝트의 정보를 전달받는다.
	 * @return 배정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	Integer joinPmInProject(RegistProjectDTO newProject);

	/**
	 * assignPmRole : 프로젝트에 pm을 등록한다
	 * @param pm과 프로젝트의 정보를 전달받는다.
	 * @return 등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int assignPmRole(RegistProjectDTO newProject);

	/**
	 * kickOldPm : 기존의 pm을 프로젝트에서 내보낸다.
	 * @param 프로젝트 번호와 pm의 정보를 전달받는다.
	 * @return 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int kickOldPm(RegistProjectDTO kickInfo);
	
	/**
	 * removeProject : 프로젝트를 삭제한다.
	 * @param 프로젝트 번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	Integer removeProject(int projectNo);

	/**
	 * restoreProject : 삭제된 프로젝트를 복구한다.
	 * @param 복구할 프로젝트번호를 전달받는다.
	 * @return 복구 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean restoreProject(int projectNo);

	/**
	 * findMainBoardList : 메인화면에 출력될 게시글을 조회한다.
	 * @param 프로젝트의 번호를 전달받는다.
	 * @return 해당 프로젝트의 게시글 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<BoardDTO> findMainBoardList(BoardCategoryDTO searchCondition);

	/**
	 * findBoardInfo : 게시글의 상세정보를 조회한다.
	 * @param 게시글 번호를 전달받는다.
	 * @return 게시글의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	BoardDTO findBoardInfo(int boardNo);

	/**
	 * increaseBoardCount : 게시판의 조회수를 1 증가시킨다.
	 * @param 조회수를 증가시킬 게시판의 번호를 전달받는다.
	 * @return 조회수 증가 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int increaseBoardCount(int boardNo);

	/**
	 * findManageProjectCount : 관리중인 프로젝트의 갯수를 조회한다.
	 * @param 회원번호와 페이징설정정보를 전달받는다.
	 * @return 페이징설정에 맞는 프로젝트 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int findManageProjectCount(Map<String, String> searchMap);

	/**
	 * findAllJoinProjectCount : 참여중인 프로젝트의 갯수를 조회한다.
	 * @param 회원번호와 페이징설정정보를 전달받는다.
	 * @return 페이징설정에 맞는 프로젝트 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int findAllJoinProjectCount(Map<String, String> searchMap);

	/**
	 * findAllRemovedProjectCount : 삭제된 프로젝트의 갯수를 조회한다.
	 * @param 페이징 설정정보를 전달받는다.
	 * @return 페이징설정에 맞는 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int findAllRemovedProjectCount(Map<String, String> searchMap);

	/**
	 * findProjectMainInfo : 프로젝트 메인화면에 출력될 정보를 조회한다.
	 * @param 프로젝트번호를 전달받는다.
	 * @return 프로젝트 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	ProjectInfoDTO findProjectMainInfo(int projectNo);

	/**
	 * projectRoleRemove : pm을 배정내역에서 삭제한다. 
	 * @param pm번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int projectRoleRemove(RegistProjectDTO oldProject);

	/**
	 * findAdminInfo : 관리자의 번호로 관리자의 정보를 조회한다.
	 * @param 관리자의 번호를 전달받는다.
	 * @return 관리자의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	MemberDTO findAdminInfo(int adminNo);

	/**
	 * registEntireHistoryProjectRegist : 프로젝트 관리에서 발생한 이력을 등록한다.
	 * @param 발생이력을 전달받는다.
	 * @return 이력 등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int registEntireHistoryProjectRegist(ProjectHistoryDTO projectHistoryDTO);
}
