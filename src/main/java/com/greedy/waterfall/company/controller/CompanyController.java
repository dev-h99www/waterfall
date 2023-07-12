package com.greedy.waterfall.company.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;
import com.greedy.waterfall.company.model.dto.TeamDTO;
import com.greedy.waterfall.company.model.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.common.exception.company.DeptModifyException;
import com.greedy.waterfall.common.exception.company.DeptRegistException;
import com.greedy.waterfall.common.exception.company.DeptRemoveException;
import com.greedy.waterfall.common.exception.company.JobModifyException;
import com.greedy.waterfall.common.exception.company.JobRegistException;
import com.greedy.waterfall.common.exception.company.JobRemoveException;
import com.greedy.waterfall.common.exception.company.TeamModifyException;
import com.greedy.waterfall.common.exception.company.TeamRegistException;
import com.greedy.waterfall.common.exception.company.TeamRemoveException;

@Controller
@RequestMapping("/company")
public class CompanyController {

	private final CompanyService companyService;

	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	/* 부서 및 팀 조회 */
	@GetMapping("/dept/list")
	public ModelAndView deptSelectList(HttpServletRequest request, ModelAndView mv) {

		SelectCriteria selectCriteria = null;
		List<DeptDTO> deptList = companyService.findDept(selectCriteria);
		List<TeamDTO> teamList = companyService.findTeam(selectCriteria);
		
		mv.addObject("deptList", deptList);
		mv.addObject("teamList", teamList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/company/dept/list");
		mv.setViewName("/company/dept/deptList");

		return mv;
	}
	
	/* 팀 리스트 조회 */
	@GetMapping("/dept/list/{deptCode}")
	public ModelAndView findTeamList(ModelAndView mv, @PathVariable("deptCode") String deptCode, HttpServletResponse response) throws IOException {
		
		List<TeamDTO> teamDTOList = companyService.findTeamList(deptCode);
		
		System.out.println("deptCode : " + deptCode);
		System.out.println();
		for(int i = 0; i < teamDTOList.size(); i++) {
			System.out.println("     teamDTOList[i] : " + teamDTOList.get(i));
		}
		
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("teamDTOList", mapper.writeValueAsString(teamDTOList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 부서 생성 */
	@PostMapping("/dept/regist")
	public String registDept(@ModelAttribute DeptDTO dept, HttpServletRequest request, RedirectAttributes rttr) 
			throws DeptRegistException {
		
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		
		dept.setCode(code);
		dept.setName(name);
		
		companyService.registDept(dept);
		
		rttr.addFlashAttribute("message", "부서 등록에 성공하셨습니다!");
		
		return "redirect:/company/dept/list";
	}
	
	/* 부서 상세 */
	@GetMapping(value = "deptDetail")
	@ResponseBody
	public ModelAndView detailDept(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		
		String code = request.getParameter("code");
		
		DeptDTO deptDetail = companyService.detailDept(code);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
		        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		        .serializeNulls()
		        .disableHtmlEscaping()
		        .create();
		
		mv.addObject("deptDetail", gson.toJson(deptDetail));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 부서 수정 */
	@PostMapping("/dept/update")
	public String modifyDept(@ModelAttribute DeptDTO dept,
			HttpServletRequest request, RedirectAttributes rttr) throws DeptModifyException {
		
		companyService.modifyDept(dept);
		
		rttr.addFlashAttribute("message", "부서 수정에 성공하셨습니다.");
		
		return "redirect:/company/dept/list";
	}
	
	/* 부서 삭제 */
	@GetMapping("/dept/delete")
	public String removeDept(HttpServletRequest request, RedirectAttributes rttr) throws DeptRemoveException {
		
		String code = request.getParameter("code");
		
		companyService.removeDept(code);
		
		rttr.addFlashAttribute("message", "부서 삭제에 성공하셨습니다!");
		
		return "redirect:/company/dept/list";
	}
	
	/* 팀 생성 */
	@PostMapping("/team/regist")
	public String registTeam(@ModelAttribute TeamDTO team, HttpServletRequest request, RedirectAttributes rttr) 
			throws TeamRegistException {
		System.out.println(team);
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		
		team.setCode(code);
		team.setName(name);
		
		companyService.registTeam(team);
		
		rttr.addFlashAttribute("message", "팀 등록에 성공하셨습니다!");
		
		return "redirect:/company/dept/list";
	}
	
	/* 팀 상세 */
	@GetMapping(value = "teamDetail")
	@ResponseBody
	public ModelAndView detailTeam(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		
		String code = request.getParameter("code");
		
		TeamDTO teamDetail = companyService.detailTeam(code);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
		        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		        .serializeNulls()
		        .disableHtmlEscaping()
		        .create();
		
		mv.addObject("teamDetail", gson.toJson(teamDetail));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 팀 수정 */
	@PostMapping("/team/update")
	public String modifyTeam(@ModelAttribute TeamDTO team,
			HttpServletRequest request, RedirectAttributes rttr) throws TeamModifyException {
		
		companyService.modifyTeam(team);
		
		rttr.addFlashAttribute("message", "팀 수정에 성공하셨습니다.");
		
		return "redirect:/company/dept/list";
	}
	
	/* 팀 삭제 */
	@GetMapping("/team/delete")
	public String removeTeam(HttpServletRequest request, RedirectAttributes rttr) throws TeamRemoveException {
		
		String code = request.getParameter("code");
		
		companyService.removeTeam(code);
		
		rttr.addFlashAttribute("message", "팀 삭제에 성공하셨습니다!");
		
		return "redirect:/company/dept/list";
	}
	
	/* 직급 조회 */
	@GetMapping("/job/list")
	public ModelAndView jobSelectList(HttpServletRequest request, ModelAndView mv) {

		SelectCriteria selectCriteria = null;
		List<JobDTO> jobList = companyService.findJob(selectCriteria);
		mv.addObject("jobList", jobList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/company/job/list");
		mv.setViewName("/company/job/jobList");

		return mv;
	}
	
	/* 직급 생성 */
	@PostMapping("/job/regist")
	public String registJob(@ModelAttribute JobDTO job, HttpServletRequest request, RedirectAttributes rttr)
			throws JobRegistException {

		int rank = Integer.parseInt(request.getParameter("rank"));
		String name = request.getParameter("name");
		String code = request.getParameter("code");

		job.setRank(rank);
		job.setName(name);
		job.setCode(code);

		companyService.registJob(job);

		rttr.addFlashAttribute("message", "직급 등록에 성공하셨습니다!");

		return "redirect:/company/job/list";
	}
	
	/* 직급 상세 */
	@GetMapping(value = "jobDetail")
	@ResponseBody
	public ModelAndView detailJob(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		
		String code = request.getParameter("code");
		
		JobDTO jobDetail = companyService.detailJob(code);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
		        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		        .serializeNulls()
		        .disableHtmlEscaping()
		        .create();
		
		mv.addObject("jobDetail", gson.toJson(jobDetail));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 직급 수정 */
	@PostMapping("/job/update")
	public String modifyJob(@ModelAttribute JobDTO job,
			HttpServletRequest request, RedirectAttributes rttr) throws JobModifyException {
		
		companyService.modifyJob(job);
		
		rttr.addFlashAttribute("message", "직급 수정에 성공하셨습니다.");
		
		return "redirect:/company/job/list";
	}
	
	/* 직급 삭제 */
	@GetMapping("/job/delete")
	public String removeJob(HttpServletRequest request, RedirectAttributes rttr) throws JobRemoveException {
		
		String code = request.getParameter("code");
		
		companyService.removeJob(code);
		
		rttr.addFlashAttribute("message", "직급 삭제에 성공하셨습니다!");
		
		return "redirect:/company/job/list";
	}
	 
}
