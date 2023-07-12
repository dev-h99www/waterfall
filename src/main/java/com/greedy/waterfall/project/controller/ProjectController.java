package com.greedy.waterfall.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.menu.model.dto.ProjectInfoDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectMainBoardDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;
import com.greedy.waterfall.project.model.service.ProjectService;

/**
 * <pre>
 * Class : ProjectController
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
/**
 * <pre>
 * Class : ProjectController
 * Comment : 프로젝트의 생성, 조회, 수정, 삭제
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Controller
@RequestMapping("/project/*")
@SessionAttributes("projectAutority")
public class ProjectController implements ProjectControllerView {
	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * sendRegistPage : 프로젝트 생성에 필요한 프로젝트상태명과 부서명을 조회한 후 프로젝트 생성 페이지로 반환한다. 
	 * @return 프로젝트 상태명 목록과 pm을 선택하기위한 부서 목록을 조회한 후 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/regist")
	public ModelAndView sendRegistPage(ModelAndView mv) {
		
		/* 프로젝트 생성에 필요한 목록을 조회한다. */
		Map<String, Object> projectForm = projectService.findRegistForm();
		List<ProjectStatusDTO> statusList = (List<ProjectStatusDTO>) projectForm.get("statusList");
		List<DeptDTO> deptList = (List<DeptDTO>) projectForm.get("deptList");
		
		/* 조회한 목록들을 ModelAndView에 저장하고, 프로젝트 생성 페이지로 forwarding한다. */
		mv.addObject("statusList", statusList);
		mv.addObject("deptList", deptList);
		mv.setViewName(PROJECT_REGIST_VIEW);
		
		return mv;
	}
	
	/**
	 * registProject : 입력받은 정보로 프로젝트를 생성한다.
	 * @return 프로젝트 생성 성공여부를 저장한 메세지를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@PostMapping("/regist")
	public ModelAndView registProject(ModelAndView mv, @ModelAttribute RegistProjectDTO newProject, RedirectAttributes rttr) {
		/* 전달받은 값으로 프로젝트를 생성한다. */
		/* 프로젝트 생성 성공여부에 맞는 메세지를 반환한다. */
		String message = "프로젝트 생성 실패!";
		if(projectService.registProject(newProject)) {
			message = "프로젝트 생성 성공!";
		} 
		rttr.addFlashAttribute("message", message);
		mv.setViewName(REDIRECT_MANAGE_VIEW);
		
		return mv;
	}
	
	/**
	 * sendManageProjectList : 프로젝트 목록페이지에서 프로젝트 관리페이지로 리다이렉트한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/manage")
	public ModelAndView sendManageProjectList() {
		
		return new ModelAndView(REDIRECT_MANAGE_VIEW);
	}
	
	/**
	 * findManageProjectList : 회원이 조회권한을 가지고있는 프로젝트의 목록을 조회 후 반환한다.
	 * @param requset: 페이징처리를 위한 값을 전달받는다. 
	 * @return manageProject: 관리 권한이 있는 프로젝트의 목록을 반환한다.
	 * @return removedProject: 삭제된 프로젝트의 목록을 반환한다.
	 * @return selectCriteria: 페이징처리를 위한 검색조건을 반환한다.
	 * @return subselectCriteria: 삭제된 프로젝트 목록의 페이징처리를 위한 검색조건을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@RequestMapping("/managelist")
	public ModelAndView findManageProjectList(ModelAndView mv, HttpServletRequest request) {
		/* 권한이 있는 프로젝트를 조회하기 위해 세션에 저장된 회원정보를 꺼낸다.*/
		MemberDTO member = (MemberDTO) request.getSession().getAttribute("loginMember");
		Map<String, String> searchMap = new HashMap<>();

		/* 페이징처리를 위해 전달받은 값을 Map에 저장한다. */
		String currentPage = request.getParameter("currentPage");
		String searchCondition = request.getParameter("searchCondition");	
		String searchValue = request.getParameter("searchValue");			
		String subcurrentPage = request.getParameter("subcurrentPage");
		String subsearchCondition = request.getParameter("subsearchCondition");	
		String subsearchValue = request.getParameter("subsearchValue");			
		
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("currentPage", currentPage);
		searchMap.put("subsearchCondition", subsearchCondition);
		searchMap.put("subsearchValue", subsearchValue);
		searchMap.put("subcurrentPage", subcurrentPage);

		/* Map에 저장한 검색조건으로 권한이 있는 프로젝트와 삭제된 프로젝트 목록을 조회 후 반환받는다. */
		MyProjectDTO project = projectService.findMyProject(searchMap, member);
		List<ProjectDTO> manageProject = project.getManageProject();
		List<ProjectDTO> removedProject = project.getRemovedProject();

		/* 조회된 프로젝트목록과, 페이징처리를 위한 SelectCriteria자료형을 ModelAndView에 저장 후 반환한다. */
		mv.addObject("subselectCriteria", project.getSubselectCriteria());
		mv.addObject("selectCriteria", project.getSelectCriteria());
		mv.addObject("manageProject", manageProject);
		mv.addObject("removedProject", removedProject);
		mv.addObject("intent", "/project/managelist");
		mv.setViewName(PROJECT_MANAGE_VIEW);
		return mv;
	}
	
	/**
	 * findProjectList : 프로젝트 목록 페이지에 출력될 참여중인 프로젝트와 관리중인 프로젝트 목록을 조회한다.
	 * @param request: 페이징처리를 위한 정보를 담은 request를 전달받는다.
	 * @return manageProject: 관리중인 프로젝트의 목록을 반환한다.
	 * @return projectList: 참여중인 프로젝트의 목록을 반환한다.
	 * @return selectCriteria: 관리중인 프로젝트의 목록을 페이징처리하기위한 정보를 반환한다.
	 * @return subselectCriteria: 참여중인 프로젝트의 목록을 페이징처리하기위한 정보를 반환한다. 
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/list")
	public ModelAndView findProjectList(HttpServletRequest request, ModelAndView mv) {
		/* 권한이 있는 프로젝트를 조회하기 위해 세션에서 로그인 한 회원의 정보를 전달받는다. */
		MemberDTO member = (MemberDTO) request.getSession().getAttribute("loginMember");
		Map<String, String> searchMap = new HashMap<>();

		/* request로 전달받은 페이징처리에 필요한 정보들을 꺼낸 후 Map에 담아준다. */
		String currentPage = request.getParameter("currentPage");
		String searchCondition = request.getParameter("searchCondition");	
		String searchValue = request.getParameter("searchValue");			
		String subcurrentPage = request.getParameter("subcurrentPage");
		String subsearchCondition = request.getParameter("subsearchCondition");	
		String subsearchValue = request.getParameter("subsearchValue");			
		
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("currentPage", currentPage);
		searchMap.put("subsearchCondition", subsearchCondition);
		searchMap.put("subsearchValue", subsearchValue);
		searchMap.put("subcurrentPage", subcurrentPage);

		/*Map에 저장된 페이징정보와, 회원정보를 전달 해주고, 회원정보에 맞는 프로젝트 목록을 반환받는다.*/
		MyProjectDTO project = projectService.findMyProject(searchMap, member);
		List<ProjectDTO> manageProject = project.getManageProject();
		/* 관리중인 프로젝트 목록, 참여중인 프로젝트목록, 페이징정보를 저장 후 view페이지로 전달한다. */
		mv.addObject("manageProject", manageProject);
		mv.addObject("selectCriteria", project.getSelectCriteria());
		mv.addObject("subselectCriteria", project.getSubselectCriteria());
		mv.addObject("projectList", project);
		mv.addObject("intent", "/project/list");
		mv.setViewName(PROJECT_LIST_VIEW);

		return mv;
	}
	
	/**
	 * findTeam : 프로젝트 등록 중 pm선택에서 부서를 선택하면 해당 부서의 팀을 조회한다.
	 * @param 선택한 부서코드를 전달받는다.
	 * @return 해당 부서의 하위 팀을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/regist/team/{deptCode}")
	public ModelAndView findTeam(ModelAndView mv, @PathVariable("deptCode") String deptCode, HttpServletResponse response) throws IOException {
		/* 선택 부서에서 조회된 하위 팀 목록을  jsonViewResolver로 보낸다. */
		List<TeamDTO> teamList = projectService.findTeam(deptCode);
		ObjectMapper mapper = new ObjectMapper();

		response.setContentType("application/json; charset=UTF-8");
		mv.addObject("teamList", mapper.writeValueAsString(teamList));
		mv.setViewName(JSON_VIEW);
		
		return mv;
	}
	
	/**
	 * findProjectMainBoard : 프로젝트 메인화면에 출력된 각 게시판의 제목을 클릭시, 해당 게시글의 상세 내용을 조회한다.
	 * @param boardNo : 상세정보를 조회 할 게시글의 번호를 전달받는다.
	 * @return 해당 게시글의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/board/{boardNo}")
	public ModelAndView findProjectMainBoard(ModelAndView mv, @PathVariable("boardNo") int boardNo, HttpServletResponse response) throws IOException {
		/* 전달받은 게시글 번호로 게시글 상세내용을 전달받는다. */
		BoardDTO boardInfo = projectService.findBoardInfo(boardNo);
		ObjectMapper mapper = new ObjectMapper();

		/* 조회한 게시글의 상세정보를 반환한다. */
		response.setContentType("application/json; charset=UTF-8");
		mv.addObject("board", mapper.writeValueAsString(boardInfo));
		mv.setViewName(JSON_VIEW);
		
		return mv;
	}
	
	/**
	 * findTeamMember : 프로젝트 생성에 pm을 선택할 때 , 팀을 선택하면 해당 팀에 소속된 개발자의 목록을 조회한다.
	 * @param teamCode : 선택한 팀의 팀 코드를 전달받는다.
	 * @return 해당 팀의 회원 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/regist/member/{teamCode}")
	public ModelAndView findTeamMember(ModelAndView mv, @PathVariable("teamCode") String teamCode, HttpServletResponse response) throws IOException {
		/* 전달받은 팀 코드로 해당 팀에 소속된 개발자의 목록을 전달받는다. */
		List<MemberDTO> memberList = projectService.findTeamMember(teamCode);
		ObjectMapper mapper = new ObjectMapper();
		
		/* 조회한 개발자 목록을 반환한다. */
		response.setContentType("application/json; charset=UTF-8");
		mv.addObject("memberList", mapper.writeValueAsString(memberList));
		mv.setViewName(JSON_VIEW);
		return mv;
	}
	
	/**
	 * findProjectDetail : 수정하려는 프로젝트의 상세내용을 조회한다.   
	 * @param projectNo : 수정하려는 프로젝트의 번호를 전달받는다.
	 * @return statusList : 프로젝트의 상태를 변경하기위한 상태목록을 반환한다..
	 * @return deptList : pm을 선택하기 위해 필요한 부서목록을 반환한다.
	 * @return projectInfo : 수정하려는 프로젝트의 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/modify/{projectNo}")
	public ModelAndView findProjectDetail(ModelAndView mv, @PathVariable("projectNo") int projectNo) {
		
		RegistProjectDTO projectInfo = projectService.findOneProjectInfo(projectNo);
		
		Map<String, Object> projectForm = projectService.findRegistForm();

		List<ProjectStatusDTO> statusList = (List<ProjectStatusDTO>) projectForm.get("statusList");
		List<DeptDTO> deptList = (List<DeptDTO>) projectForm.get("deptList");

		mv.addObject("statusList", statusList);
		mv.addObject("deptList", deptList);
		mv.addObject("projectInfo", projectInfo);
		mv.setViewName(PROJECT_MODIFY_VIEW);
		return mv;
	}
	
	/**
	 * modifyProject : 전달받은 수정 내용으로 프로젝트를 수정한다. 
	 * @param project : 프로젝트의 수정내용을 전달받는다.
	 * @return message : 수정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@PostMapping("/modify")
	public ModelAndView modifyProject(ModelAndView mv, @ModelAttribute RegistProjectDTO project, RedirectAttributes rttr) {
		/* 프로젝트의 수정 성공여부 메세지를 담은 문자열을 반환한다. */
		String message = "프로젝트 수정에 실패했습니다."; 
		if(projectService.modifyProject(project)) {
			message = "프로젝트를 수정했습니다.";
		} 
		rttr.addFlashAttribute("message", message);
		mv.setViewName(REDIRECT_MANAGE_VIEW);
		
		return mv;
	}
	
	/**
	 * sendProjectDetail : 프로젝트 번호와 해당 프로젝트의 PM번호를 세션에 저장하고, 프로젝트의 메인페이지에 필요한 정보를 조회한다.
	 * @param 프로젝트 번호와, 정보와 요청주소를 저장할 ModelAndView변수를 전달받는다.
	 * @return 프로젝트 메인페이지에 필요한 정보와, 요청주소를 담은 ModelAndView 변수를 반환한다.
	 * 
	 * @author 홍성원
	 * @throws IOException 
	 */
	@GetMapping("main/{projectNo}")
	public ModelAndView sendProjectDetail(@PathVariable("projectNo") int projectNo, ModelAndView mv) throws IOException {
		/* 선택한 프로젝트 번호로, 메인화면에 출력할 정보를 조회한다. */
		Map<String, Object> projectMainInfo = projectService.findProjectMainInfo(projectNo);
		ObjectMapper mapper = new ObjectMapper();
		
		/* 해당 프로젝트의 pm번호와 프로젝트 번호를 세션에 넣어준다. */
		ProjectAuthorityDTO projectAutority = (ProjectAuthorityDTO) projectMainInfo.get("projectAutority");
		/* 메인화면에 출력할 프로젝트에 작성된 게시판의 게시글과, 해당프로젝트의 업무, 이슈 진행사항을 조회한 후 반환한다. */
		ProjectMainBoardDTO projectBoard = (ProjectMainBoardDTO) projectMainInfo.get("projectBoard");
		ProjectInfoDTO projectInfo = (ProjectInfoDTO) projectMainInfo.get("projectInfo");
		
		mv.addObject("projectInfo", mapper.writeValueAsString(projectInfo));
		mv.addObject("projectBoard", projectBoard);
		mv.addObject("projectAutority", projectAutority);
		mv.setViewName(PROJECT_MAIN_VIEW);
		
		return mv;
	}
									
	/**
	 * removeProject : 프로젝트를 삭제한 후, 삭제 이력을 히스토리 이력 테이블에 저장한다.
	 * @param projectNo : 삭제할 프로젝트의 번호를 전달받는다.
	 * @return 프로젝트 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/remove/{projectNo}")
	public ModelAndView removeProject(ModelAndView mv, @PathVariable int projectNo, HttpSession session, RedirectAttributes rttr) {
		/* 삭제할 프로젝트 번호와, 삭제 이력을 저장하기 위한 삭제한 회원의 번호를 세션에서 꺼낸 후 전달한다. */
		Map<String, Integer> removeInfo = new HashMap<>();
		String message = "삭제에 실패했습니다.";
		int memberNo = ((MemberDTO) session.getAttribute("loginMember")).getNo();
		removeInfo.put("projectNo", projectNo);
		removeInfo.put("memberNo", memberNo);
		
		/* 삭제 성공여부를 전달한다. */
		if(projectService.removeProject(removeInfo)) {
			message = "삭제에 성공했습니다.";
		}
		rttr.addFlashAttribute("message", message);
		mv.setViewName(REDIRECT_MANAGE_VIEW);

		return mv;
	}
	
	/**
	 * restoreProject : 삭제된 프로젝트를 복구한다.
	 * @param 복구할 프로젝트의 번호를 전달받는다.
	 * @return 복구 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/restore/{projectNo}")
	public ModelAndView restoreProject(ModelAndView mv, @PathVariable int projectNo , HttpSession session, RedirectAttributes rttr) {
		/* 복구할 프로젝트번호와 삭제이력을 저장하기위한 복구한 회원의 번호를 저장하고, 프로젝트 복구 성공여부를 저장한 메세지를 전달한다. */
		Map<String, Integer> restoreInfo = new HashMap<>();
		String message = "프로젝트 복구 실패.";
		int memberNo = ((MemberDTO) session.getAttribute("loginMember")).getNo();
		restoreInfo.put("projectNo", projectNo);
		restoreInfo.put("memberNo", memberNo);
		
		if(projectService.restoreProject(restoreInfo)) {
			message = "프로젝트 복구 성공.";
		}
		rttr.addFlashAttribute("message", message);
		mv.setViewName(REDIRECT_MANAGE_VIEW);

		return mv;
	}
}