package com.greedy.waterfall.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;
import com.greedy.waterfall.project.model.service.ProjectManageService;

/**
 * <pre>
 * Class : ProjectManageController
 * Comment : 프로젝트 인원배정, 역할수정, 조회, 삭제기능을 한다.
 * 
 * History
 * 2022. 3. 13.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Controller
@RequestMapping("/manage/*")
public class ProjectManageController {

	private final ProjectManageService pms;
	
	@Autowired
	public ProjectManageController(ProjectManageService pms) {
		this.pms = pms;
	}
	
	/**
	 * findProjectMember : 프로젝트 배정인원을 조회한다.
	 * @param 배정인원의 정보 리스트를 저장할 변수를 전달받는다.
	 * @return 배정인원의 정보와 응답할 주소를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/member/list")
	public ModelAndView findProjectMember(ModelAndView mv, HttpServletRequest request) {
		/* 인원목록 조회에 페이징처리를 하기 위해 전달받은 정보를 설정한다. */
		Map<String, String> searchMap = new HashMap<>();					
		
		String projectNo = Integer.toString(((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		String currentPage = request.getParameter("currentPage");
		String searchCondition = request.getParameter("searchCondition");	
		String searchValue = request.getParameter("searchValue");			
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("projectNo", projectNo);
		searchMap.put("currenPage", currentPage);
		
		/* 페이징 설정정보를 전달하고, 해당 설정정보에 맞는 배정인원목록을 전닯다는다. */
		Map<String, Object> manageProjectMemberInfo = pms.findProjectMember(searchMap);
		/* 배정인원목록과, 프로젝트내 모든 역할, 모든 부서목록을 반환받는다. */
		List<ProjectManageMemberDTO> projectMemberList = (List<ProjectManageMemberDTO>) manageProjectMemberInfo.get("memberList");
		List<ProjectRoleDTO> allRole = (List<ProjectRoleDTO>) manageProjectMemberInfo.get("allRole");
		List<DeptDTO> allDept = (List<DeptDTO>) manageProjectMemberInfo.get("allDept");
		SelectCriteria selectCriteria = (SelectCriteria) manageProjectMemberInfo.get("selectCriteria");
		
		/* 반환받은 목록을 ModelAndView에 담아 요청경로로 전달한다. */
		mv.addObject("projectMemberList", projectMemberList);
		mv.addObject("intent", "/manage/member/list");
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("allRole", allRole);
		mv.addObject("allDept", allDept);
		mv.setViewName("/manage/memberList");
		
		return mv;
	}
	
    /**
     * registProjectMember : 전달받은 회원정보와 역할 정보로 회원의 역할을 부여해 프로젝트에 배정한다.
     * @param projectRole : 회원이 배정받은 역할을 전달받는다.
     * @param registInfo : 회원정보를 전달받는다.
     * @return 요청주소를 설정해 반환한다.
     * 
     * @author 홍성원
     */
    @PostMapping("/member/regist")
	public ModelAndView registProjectMember(ModelAndView mv,@RequestParam("projectRole") List<String> projectRole, @RequestParam Map<String, String> registInfo) {
    	/* 전달받은 회원정보와 배정받은 역할을 전달해 회원을 등록한 후 , 회원목록조회페이지로 redirect한다. */
    	sendToResultView(mv, pms.registProjectMember(parsingMemberInfoForProject(projectRole, registInfo)));

		return mv;
	}
	
	/**
	 * findTeamMember : 전달받은 팀 코드에 소속된 회원의 목록을 조회한다.
	 * @param 팀코드와 프로젝트 번호를 전달받는다.
	 * @return 해당 팀코드에 소속되고, 프로젝트에 배정되지 않은 인원의 목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/member/find")
	public ModelAndView findTeamMember(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 전달받은 팀 코드와 프로젝트 번호로 인원목록을 조회한다. */
		List<MemberDTO> memberList = pms.findTeamMember(parsingMemberInfoByTeam(request));
		ObjectMapper mapper = new ObjectMapper();
		/* 전달받은 회원목록을 반환한다. */
		response.setContentType("application/json; charset=UTF-8");
		mv.addObject("memberList", mapper.writeValueAsString(memberList));
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 * findMemberDetail : 전달받은 회원번호로 해당 회원의 프로젝트 내 상세정보를 조회한다.
	 * @param 회원의 번호와 프로젝트 번호를 전달받는다.
	 * @return 전달받은 정보로 조회된 회원의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/member/detail")
 	public ModelAndView findMemberDetail(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 전달받은 회원번호와 세션에 저장된 프로젝트번호로 회원 프로젝트 배정정보를 조회한다. */
		Map<String, Integer> memberInfo = new HashMap<String, Integer>();
		ObjectMapper mapper = new ObjectMapper();
		memberInfo.put("memberNo", Integer.parseInt(request.getParameter("memberNo")));
		memberInfo.put("projectNo", ((ProjectAuthorityDTO)request.getSession().getAttribute("projectAutority")).getProjectNo());
		/* 조회한 회원의 배정역할을 반환한다. */
		List<ProjectRoleDTO> memberRoleList = pms.findMemberRole(memberInfo);
		response.setContentType("application/json; charset=UTF-8");
		mv.addObject("memberRoleList", mapper.writeValueAsString(memberRoleList));
		mv.setViewName("jsonView");
		
		return mv;
	}

	/**
	 * modifyMemberInProject : 전달받은 회원의 역할을 등록 후 회원목록페이지로 redirect한다.
	 * @param 배정된 역할을 전달받는다.
	 * @return 회원목록페이지 요청을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@PostMapping("/member/modify")
	public ModelAndView modifyMemberInProject(ModelAndView mv, @RequestParam("projectRole") List<String> projectRole
			, @RequestParam Map<String, String> modifyInfo) {
		/* 회원이 배정받은 역할을 전달한 뒤 회원목록조회 url로 리다이렉트한다 */
		sendToResultView(mv, pms.modifyProjectMember(parsingMemberInfoForProject(projectRole, modifyInfo)));
		
		return mv;
	}
	
	/**
	 * removeMemberInProject : 회원번호를 전달받아 해당 회원을 프로젝트에서 내보낸다.
	 * @param 회원번호를 전달받는다.
	 * @return 회원목록페이지 요청을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/member/remove/{memberNo}")
	public ModelAndView removeMemberInProject(ModelAndView mv, @PathVariable("memberNo") int memberNo, HttpSession session) {
		Map<String, Integer> removeInfo = new HashMap<String, Integer>();
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		removeInfo.put("memberNo", memberNo);
		removeInfo.put("projectNo", projectNo);
		sendToResultView(mv, pms.removeMemberInProject(removeInfo));
		
		return mv;
	}
	
	/**
	 * sendToResultView : ModelAndView변수를 반환해, 회원목록 조회화면으로 redirect하도록 설정을 한다.
	 * @param redirect 주소를 설정할 ModelAndView변수를 전달받는다.
	 * 
	 * @author 홍성원
	 */
	private void sendToResultView(ModelAndView mv, boolean result) {
		if(result) {
			mv.setViewName("redirect:/manage/member/list");
		} 
	}
	
	/**
	 * parsingMemberInfoForProject : 전달받은 입력정보로 프로젝트에 인원을 추가 및 역할변경할 수 있는 데이터로 변환한다.
	 * @param parsingInfo : 프로젝트 번호와 회원번호, 관리자 번호를 문자열로 전달받는다
	 * @return memberInfo : 전달받은 정보를 프로젝트 인원배정 및 수정하기위한 데이터로 반환한다.
	 * 
	 * @author 홍성원
	 */
	private ProjectManageMemberDTO parsingMemberInfoForProject(List<String> projectRole,Map<String, String> parsingInfo ) {
		
		ProjectManageMemberDTO memberInfo = ProjectManageMemberDTO.builder().projectNo(parseInt(parsingInfo, "projectNo")).memberNo(parseInt(parsingInfo, "memberNo"))
												.managerNo(parseInt(parsingInfo, "managerNo")).role(parseProjectRoleToList(projectRole)).build();

		return memberInfo;
	}
	
	/**
	 * parseInt : Map<String, String> 변수와 키값을 전달받아 해당 키에 저장된 값을 정수형 값으로 변환해 반환한다.
	 * @param parameter : 정수로 변환할 문자가 들어있는 맵을 전달받는다.
	 * @param key : 정수로 변환할 값의 키값을 전달받는다.
	 * 
	 * @return 전달받은 키값에 저장된 값을 정수로 변환해 반환한다.
	 * 
	 * @author 홍성원
	 */
	private int parseInt(Map<String, String> parameter, String key) {
		
		return Integer.parseInt(parameter.get(key));
	}
	
	private int parseInt(List<String> parameter, int index) {
		
		return Integer.parseInt(parameter.get(index));
	}
	
	/**
	 * parseProjectRoleToList : List<String>형태로 저장된 역할 목록을 List<ProjectRoleDTO>형태로 변환한다.
	 * @param 변환할 리스트를 전달받는다
	 * @return List<ProjectRoleDTO>형태로 변환한 값을 반환한다.
	 * 
	 * @author 홍성원
	 */
	private List<ProjectRoleDTO> parseProjectRoleToList(List<String> projectRole) {
		List<ProjectRoleDTO> memberRoleList = new ArrayList<ProjectRoleDTO>();
		
		for(int i = 0; i < projectRole.size(); i++) {
			System.out.println(projectRole.get(i));
			ProjectRoleDTO role = ProjectRoleDTO.builder().roleNo(parseInt(projectRole, i)).build();
			memberRoleList.add(role);
		}
		
		return memberRoleList;
	}
	
	/**
	 * parsingMemberInfoByTeam : request에 저장된 팀 코드와 프로젝트 번호를 꺼내 Map 변수에 저장 후 반환한다.
	 * @param request : 팀코드와 프로젝트 번호가 담겨있는 request를 전달받는다.
	 * @return memberInfo : 팀코드와 프로젝트가 담긴 맵 변수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	private Map<String, String> parsingMemberInfoByTeam(HttpServletRequest request) {
		Map<String, String> memberInfo = new HashMap<String, String>();
		memberInfo.put("teamCode", request.getParameter("teamCode"));
		memberInfo.put("projectNo", request.getParameter("projectNo"));
		
		return memberInfo;
	}
}