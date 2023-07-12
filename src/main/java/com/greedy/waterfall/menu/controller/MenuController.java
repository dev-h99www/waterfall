package com.greedy.waterfall.menu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.menu.model.dto.MainInfoDTO;
import com.greedy.waterfall.menu.model.service.MenuService;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;

/**
 * <pre>
 * Class : MenuController
 * Comment : 로그인 후 계정의 종류별로 출력되는 메인화면에 필요한 정보들을 조회 한다. 
 * 
 * History
 * 2022. 3. 3.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Controller
@RequestMapping("/menu/*")
public class MenuController {
	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	/**
	 * sendToMainpage : 메인페이지에 필요한 관리프로젝트 목록, 참여프로젝트목록을 조회한다.
	 * @param 프로젝트목록의 페이징처리를 위한 정보와 회원정보다 저장된 request를 전달받는다.
	 * @return projectList : 관리중인 프로젝트의 목록을 반환한다.
	 * @return joinProjectList : 참여중인 프로젝트의 목록을 반환한다.
	 * @return selectCriteria : 관리중인 프로젝트의 페이징정보를 반환한다.
	 * @return subselectCriteria : 참여중인 프로젝트의 페이징정보를 반환한다.
	 * @return intent : 페이징처리를 위해, 요청주소값을 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/main")
	public ModelAndView sendToMainpage(ModelAndView mv, HttpServletRequest request) {
		String currentPage = request.getParameter("currentPage");
		String subcurrentPage = request.getParameter("subcurrentPage");
		MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
		MainInfoDTO mainInfo = MainInfoDTO.builder()
								.currentPage(currentPage)
								.subcurrentPage(subcurrentPage)
								.loginMember(loginMember).build();
		
		Map<String, Object> findProjectResult = menuService.findMainProjectList(mainInfo);
		
		List<ProjectDTO> projectList = (List<ProjectDTO>) findProjectResult.get("projectList");
		List<ProjectDTO> joinProjectList = (List<ProjectDTO>) findProjectResult.get("joinProjectList");
		SelectCriteria selectCriteria = (SelectCriteria) findProjectResult.get("selectCriteria");
		SelectCriteria subselectCriteria = (SelectCriteria) findProjectResult.get("subselectCriteria");
		
		mv.addObject("projectList", projectList);
		mv.addObject("joinProjectList", joinProjectList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("subselectCriteria", subselectCriteria);
		mv.addObject("intent", "/menu/main");
		mv.setViewName("/main/mainPage");
		
		return mv;
	}

	/**
	 * findAdminMainProjectList : 메인화면에서 관리자가 선택한 프로젝트의 상세정보를 반환한다.
	 * @param 프로젝트번호를 전달받는다.
	 * @return 해당 프로젝트의 업무, 이슈, 산출물갯수를 반환한다.
	 * 
	 * @author 홍성원
	 * @throws IOException 
	 */
	@GetMapping("/main/admin/project/{projectNo}")
	public ModelAndView findAdminMainProjectList(ModelAndView mv, HttpServletResponse response, @PathVariable("projectNo") int projectNo) throws IOException {
		Map<String, Object> adminPageInfo = menuService.findAdminPageInfo(projectNo);
		
		ProjectDTO project = (ProjectDTO) adminPageInfo.get("projectInfo");
		List<ProjectHistoryDTO> projectHistory = (List<ProjectHistoryDTO>) adminPageInfo.get("projectHistory");
		
		response.setContentType("application/json; charset=UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(dateFormat);
		
		mv.addObject("project", mapper.writeValueAsString(project));
		mv.addObject("projectHistory", mapper.writeValueAsString(projectHistory));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * findJoinProjectInfo : 참여중인 프로젝트에대한 정보를 조회한다.
	 * @param 프로젝트번호와 회원정보를 전달받는다.
	 * @return 프로젝트정보와 해당 프로젝트에서 맡은 업무갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("main/join/project/{projectNo}")
	public ModelAndView findJoinProjectInfo(ModelAndView mv, HttpServletRequest request, HttpServletResponse response
															, @PathVariable("projectNo") int projectNo) throws IOException {
		
		Map<String, Integer> searchMap = new HashMap<String, Integer>();
		Integer memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		searchMap.put("memberNo", memberNo);
		searchMap.put("projectNo", projectNo);
		
		ProjectDTO joinProjectInfo = menuService.findJoinProjectInfo(searchMap);
		
		
		response.setContentType("application/json; charset=UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(dateFormat);
		
		mv.addObject("joinProjectInfo", mapper.writeValueAsString(joinProjectInfo));
		mv.setViewName("jsonView");
		
		return mv;
	}
}