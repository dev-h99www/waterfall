package com.greedy.waterfall.project.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.common.History.History;
import com.greedy.waterfall.common.paging.Paging;
import com.greedy.waterfall.common.paging.PagingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.common.result.Result;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.menu.model.dto.ProjectInfoDTO;
import com.greedy.waterfall.project.model.dto.BoardCategoryDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.ProjectMainBoardDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;
import com.greedy.waterfall.project.model.mapper.ProjectMapper;

/**
 * <pre>
 * Class : ProjectServiceImpl
 * Comment : 프로젝트에 관련된 조회 생성 수정 삭제기능의 로직을 담당하는 클래스
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 * 
 * Review
 * 2022. 2. 23 (차화응)
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	/* 프로젝트 이력을 저장하는 메소드가 정의돼있는 projectHistory 빈을 의존성주입받는다. */
	@Autowired @Qualifier("projectHistory") private History history;
	private static final String ROLE_ADMIN = "1";
	private static final int BOARD_NOTICE = 1;
	private static final int BOARD_EDU = 2;
	private static final int BOARD_GUIDE = 3;
	private static final int BOARD_MEETING = 4;
	private static final int BOARD_TODO = 5;
	private final ProjectMapper mapper;
	private final Paging paging;
	private PagingDTO pagingSetting;
	

	@Autowired
	public ProjectServiceImpl(ProjectMapper mapper, Paging paging) {
		this.mapper = mapper;
		this.paging = paging;
		this.pagingSetting = PagingDTO.builder().limit(5).buttonAmount(5).build();
	}
	
	/**
	 * findMyProject : 전달받은 회원정보로 권한이 있는 프로젝트 목록을 반환한다.
	 * @param member : 회원정보를 전달받는다.
	 * @return 회원이 조회 권한을 가지고있는 프로젝트의 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public MyProjectDTO findMyProject(Map<String, String> searchMap, MemberDTO member) {
		/*  */
		List<ProjectDTO> manageProject = null;
		List<ProjectDTO> joinProject = null;
		List<ProjectDTO> removedProject = null;
		SelectCriteria selectCriteria = null;
		SelectCriteria subselectCriteria = null;
		if(member == null) {
			return null;
		}
		/* 프로젝트 목록을 조회한 회원이 관리자인 경우 모든 프로젝트 목록과 삭제된 프로젝트 목록을 반환한다. */
		if(ROLE_ADMIN.equals(member.getRole())) {
			/* 관리중인 프로젝트 목록의 페이징처리를 위한  전체 갯수를 조회 후 페이징 정보를 설정한다.*/
			searchMap.put("totalCount", Integer.toString(mapper.findAllManageProjectCount(searchMap)));
			selectCriteria = paging.setPagingCondition(searchMap, pagingSetting, member);
			/* 삭제된 프로젝트 목록의 페이징처리를 위한  전체 갯수를 조회 후 페이징 정보를 설정한다.*/
			searchMap.put("subtotalCount", Integer.toString(mapper.findAllRemovedProjectCount(searchMap)));
			subselectCriteria = paging.setSubPagingCondition(searchMap, pagingSetting, member);
			/* 페이징설정이 저장된 Sriteria변수를 전달해 페이징조건에 맞는 프로젝트들의 목록을 반환받는다. */
			manageProject = mapper.findAllManageProject(selectCriteria);
			removedProject = mapper.findAllRemovedProject(subselectCriteria);
		} else {
			/* 프로젝트 목록을 조회한 회원이 개발자인 경우 pm으로 등록되어 관리중인 프로젝트 목록과 개발자로 참여중인 프로젝트 목록을 반환한다. */
			searchMap.put("memberNo", Integer.toString(member.getNo()));
			/* pm역할을 배정받아 관리중인 프로젝트 목록의 페이징처리를 위한  전체 갯수를 조회 후 페이징 정보를 설정한다.*/
			searchMap.put("totalCount", Integer.toString(mapper.findManageProjectCount(searchMap)));
			selectCriteria = paging.setPagingCondition(searchMap, pagingSetting, member);
			/* 개발자로 참여중인 프로젝트 목록의 페이징처리를 위한  전체 갯수를 조회 후 페이징 정보를 설정한다.*/
			searchMap.put("subtotalCount", Integer.toString(mapper.findManageProjectCount(searchMap)));
			subselectCriteria = paging.setSubPagingCondition(searchMap, pagingSetting, member);
			
			/* 페이징설정이 저장된 Sriteria변수를 전달해 페이징조건에 맞는 프로젝트들의 목록을 반환받는다. */
			manageProject = mapper.findManagaProject(selectCriteria);
			joinProject = mapper.findJoinProject(subselectCriteria);
		}
		/* 조회한 프로젝트 목록을 하나의 클래스에 저장한 후 반환한다. */
		MyProjectDTO projectList = MyProjectDTO.builder().manageProject(manageProject).joinProject(joinProject).removedProject(removedProject)
									.selectCriteria(selectCriteria).subselectCriteria(subselectCriteria).build();
		
		return projectList;
	}

	/**
	 * findRegistForm : 프로젝트 생성에 필요한 프로젝트 상태목록과, pm을 선택하기위한 부서목록을 조회한다.
	 * @return 프로젝트 상태목록과 부서목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public Map<String, Object> findRegistForm() {

		List<ProjectStatusDTO> statusList = mapper.findAllProjectStatus();

		List<DeptDTO> deptList = mapper.findAllDept();
		
		Map<String, Object> projectForm = new HashedMap();
		
		projectForm.put("statusList", statusList);
		projectForm.put("deptList", deptList);
		
		return projectForm;
	}
	
	/**
	 * findTeam : 부서 선택시, 해당 부서의 하위 팀 목록을 조회한다.
	 * @param deptCode : 부서코드를 전달받는다.
	 * @return 해당 부서의 하위 팀 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<TeamDTO> findTeam(String deptCode){
		
		return mapper.findTeam(deptCode);
	}

	/**
	 * findTeamMember : 팀 선택시, 해당 팀에 소속된 개발자를 조회한다.
	 * @param 팀 코드를 전달받는다.
	 * @return 해당 팀에 소속된 개발자의 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<MemberDTO> findTeamMember(String teamCode) {
		
		return mapper.findTeamMember(teamCode);
	}

	/**
	 * registProject : 입력받은 프로젝트정보로 새로운 프로젝트를 생성한다.
	 * @param 프로젝트 생성 정보를 전달받는다.
	 * @return 프로젝트 생성 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean registProject(RegistProjectDTO newProject) {
		/* 프로젝트가 동록이 되어야하고, pm으로 선택한 회원이 역할배정내역에 pm역할로 등록이 되어야한다. */
		boolean registProjectResult = new Result().perform(mapper.registProject(newProject)).and(mapper.registPm(newProject))
		/* 등록된 프로젝트에 pm이 배정되어야하고, 히스토리내역에 등록내역이 저장되어야한다. */
				.and(mapper.registMemberProject(newProject)).and(mapper.registProjectHistory(newProject.getProjectNo())).result();
		
		/* 만약 네가지 모두 등록에 성공한다면  전체 히스토리 내역에 프로젝트 생성 내역을 등록 후 성공여부를 반환한다.*/
		if(registProjectResult) {
				Map<String, Object> info = new HashMap<>();
				/* 프로젝트 전체 이력내역테이블에 저장하기위해, 생성한 관리자의 번호와, 생성한 프로젝트번호를 전달해 이력을 등록한다. */
				info.put("findAdminInfo", mapper.findAdminInfo(newProject.getAdminNo()));
				info.put("newProject", newProject);
				List<ProjectHistoryDTO> projectRegistHistory = history.registHistory(info);
				
				return registHistoryResult(projectRegistHistory);
			}
			
		return false;
	}
	
	/**
	 * registHistoryResult : 프로젝트에 발생한 이력 내력 목록을 모두 등록하고, 등록의 성공여부를 반환한다.
	 * @param 이력내역 목록을 전달받는다.
	 * @return 이력내역 등록 성공여부를 반환한다.
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
	 * findOneProjectInfo : 전달받은 프로젝트 번호로 해당 프로젝트 상세정보를 조회한다.
	 * @param projectNo : 프로젝트의 번호를 전달받는다.
	 * @return : 프로젝트의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public RegistProjectDTO findOneProjectInfo(int projectNo) {
		RegistProjectDTO project = mapper.findOneProjectInfo(projectNo);
		if("N".equals(project.getPmStatus())) {
			project.setPmName("미지정");
		}
		
		return project;
	}

	/**
	 * modifyProject : 전달받은 프로젝트 내용으로 프로젝트를 수정 후, 이력테이블에 수정 내역을 등록한다.
	 * @param 수정하고자하는 프로젝트의 내용을 전달받는다.
	 * @return 수정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean modifyProject(RegistProjectDTO newProject) {
		/* 이력테이블에 수정 내역을 저장하기 위해, 기존 프로젝트 상세내용을 조회하고, 수정하고자 하는 내용을 전달 한 */
		Map<String, Object> info  = new HashMap<>();
		RegistProjectDTO oldProject = mapper.findOneProjectInfo(newProject.getProjectNo());
		info.put("newProject", newProject);
		info.put("oldProject", oldProject);

		/* 새로 입력받은 내용으로 프로젝트를 수정한다. */
		Result modifyResult = new Result(mapper.modifyProject(newProject));
		
		/* 기존의 PM과 수정정보에서 입력된 PM정보를 비교 후, PM이 바뀌었는지 확인한다. */
		if(oldProject.getPmNumber() != newProject.getPmNumber()) {
			
			/* 기존 pm의 역할을 지우고,프로젝트에서 내보낸다. */
			modifyResult.perform(mapper.kickOldPm(oldProject))
						.and(mapper.projectRoleRemove(newProject))
						.and(mapper.projectRoleRemove(oldProject))
						.and(mapper.assignPmRole(newProject)).result();
			
			/* 새로운 pm이 프로젝트에 배정이 안돼있을 시 프로젝트에 배정한다. */
			if(mapper.findMemberInProject(newProject) == null) {
				
				modifyResult.perform(mapper.joinPmInProject(newProject));
			}
		}
		/* 만약 프로젝트가 성공적으로 수정됐다면 변경사항을 이력테이블에 등록한다. */
		if(modifyResult.result()) {
			/* 프로젝트 수정에 발생한 이력목록을 반환받는다. */
			List<ProjectHistoryDTO> modifyProjectHistory = history.modifyHistory(info);
			/* 수정에 발생한목록들이 전부 등록이 되면 성공값을 반환한다. */
			return registHistoryResult(modifyProjectHistory);
		}
		
		return false;
	}

	/**
	 * removeProject : 전달받은 프로젝트 번호로 프로젝트를 삭제한 뒤, 프로젝트 삭제이력을 이력테이블에 등록한다.
	 * @param removeInfo : 프로젝트 삭제시 필요한 프로젝트번호와, 이력을 저장하기위한 삭제한 회원의 번호를 전달받느다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean removeProject(Map<String, Integer> removeInfo) {
		/* 잔달받은 Map변수에서 프로젝트를 삭제하기 위한 projectNo와 삭제이력 등록을 위한  memberNo값을 꺼낸다.*/
		Map<String, Object> historyRegistInfo = new HashMap<String, Object>();
		int projectNo = removeInfo.get("projectNo");
		int memberNo = removeInfo.get("memberNo");

		/* 프로젝트 삭제에 성공하면 삭제 이력을 이력테이블에 등록한다. */
		if(new Result(mapper.removeProject(projectNo)).result()) {
			/* 이력테이블에 등록하기위해, 삭제한 회원의 정보와 프로젝트 정보를 조회한다. */
			MemberDTO memberInfo = mapper.findAdminInfo(memberNo);
			RegistProjectDTO projectInfo = mapper.findOneProjectInfo(projectNo);
			/* 프로젝트의 정보와, 삭제한 회원의 정보를 전달해 발생이력 목록을 전달받는다. */
			historyRegistInfo.put("memberInfo", memberInfo);
			historyRegistInfo.put("projectInfo", projectInfo);
			List<ProjectHistoryDTO> historyInfo = history.removeHistory(historyRegistInfo);
			
			/* 이력등록의 성공여부를 반환한다. */
			return registHistoryResult(historyInfo);
		}

		return false;
	}
	
	/**
	 * restoreProject : 삭제상태의 프로젝트를 복구한다.
	 * @param 복구하려는 프로젝트의 번호를 전달받는다.
	 * @return 복구 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean restoreProject(Map<String, Integer> restoreInfo) {
		/* 잔달받은 Map변수에서 프로젝트를 복구하기 위한 projectNo와 복구이력 등록을 위한  memberNo값을 꺼낸다.*/
		Map<String, Object> historyRegistInfo = new HashMap<String, Object>();
		int projectNo = restoreInfo.get("projectNo");
		int memberNo = restoreInfo.get("memberNo");

		/* 프로젝트 복구에 성공하면 삭제 이력을 이력테이블에 등록한다. */
		if(new Result(mapper.restoreProject(projectNo)).result()) {
			/* 이력테이블에 등록하기위해, 프로젝트를 복구한 회원의 정보와 프로젝트 정보를 조회한다. */
			MemberDTO memberInfo = mapper.findAdminInfo(memberNo);
			RegistProjectDTO projectInfo = mapper.findOneProjectInfo(projectNo);
			/* 프로젝트의 정보와, 프로젝트를 복구한 회원의 정보를 전달해 발생이력 목록을 전달받는다. */
			historyRegistInfo.put("memberInfo", memberInfo);
			historyRegistInfo.put("projectInfo", projectInfo);
			List<ProjectHistoryDTO> historyInfo = history.recoveryHistory(historyRegistInfo);
			
			/* 이력등록의 성공여부를 반환한다. */
			return registHistoryResult(historyInfo);
		}
		return mapper.restoreProject(projectNo);
	}
	
	/**
	 * findProjectMainInfo : 프로젝트 메인페이지에 출력될 정보들을 조회한다.
	 * @param 프로젝트의 번호를 전달받는다.
	 * @return projectAutority : 프로젝트의 번호와 해당 프로젝트의 pm번호를 저장한다.
	 * @return projectBoard : 해당프로젝트 메인페이지에 출력할 각 게시판의 최신 게시글을  5개씩 저장한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public Map<String, Object> findProjectMainInfo(int projectNo) {
		Map<String, Object> projectMainInfo = new HashMap<String, Object>();
		/* 전달받은 프로젝트의 pm번호와 프로젝트번호를 저장한다. */
		ProjectAuthorityDTO projectAutority = mapper.findPmNumber(projectNo);
		BoardCategoryDTO SearchCondition = new BoardCategoryDTO(projectNo);
		
		/* 전달받은 프로젝트에 등록된 각 게시판의 최근등록순 게시글을 5개씩 조회해 저장한다. */
		ProjectMainBoardDTO projectBoard = ProjectMainBoardDTO.builder()
				.guideBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(3)))
				.meetingBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(4)))
				.todoBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(5)))
				.noticeBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(1)))
				.eduBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(2)))
				.build();
		/* 프로젝트 메인에 출력할 프로젝트의 업무 상태별 갯수, 이슈 상태별 갯수, 산출물 갯수를 조회한다. */
		ProjectInfoDTO projectInfo = mapper.findProjectMainInfo(projectNo);
		/* 조회한 정보들을 반환한다. */
		projectMainInfo.put("projectAutority", projectAutority);
		projectMainInfo.put("projectBoard", projectBoard);
		projectMainInfo.put("projectInfo", projectInfo);

		return projectMainInfo;
	}

	/**
	 * findBoardInfo : 게시글의 상세내용을 조회하며, 조회수를 1 증가시킨다.
	 * @param 조회할 게시글의 번호를 전달받는다.
	 * @return 조회한 게시글의 내용과, 카테고리명을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public BoardDTO findBoardInfo(int boardNo) {
		/* 조회하는 게시물의 조회수를 1 증가시킨다. */
		int increseBoardCountResult = mapper.increaseBoardCount(boardNo);
		/* 조회수가 1 증가했다면 게시물의 상세내용을 조회 후 카테고리명을 저장하여 반환한다. */
		if(increseBoardCountResult > 0) {
			BoardDTO boardInfo = mapper.findBoardInfo(boardNo);
			
			return findBoardCategoryName(boardInfo);
		}

		return null;
	}
	
	/**
	 * findBoardCategoryName : 전달받은 게시글의 카테고리번호로, 카테고리명을 저장한 후 반환한다.
	 * @param boardInfo : 게시글을 전달받는다.
	 * @return boardInfo : 게시글의 카테고리명을 저장 후 반환한다.
	 * 
	 * @author 홍성원
	 */
	private BoardDTO findBoardCategoryName(BoardDTO boardInfo) {
		int categoryNo = boardInfo.getBoardCategoryNo();
		String boardCategoryName = "";
		switch(categoryNo) {
			case BOARD_NOTICE: boardCategoryName = "공지사항"; break;
			case BOARD_EDU: boardCategoryName = "교육 게시판"; break;
			case BOARD_GUIDE: boardCategoryName = "프로젝트 가이드"; break;
			case BOARD_MEETING: boardCategoryName = "회의록 게시판"; break;
			case BOARD_TODO: boardCategoryName = "TO DO"; break;
		}
		
		boardInfo.setBoardCategoryName(boardCategoryName);
		
		return boardInfo;
	}
}