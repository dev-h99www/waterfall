package com.greedy.waterfall.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.exception.member.MemberRegistException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.DeptDTO;
import com.greedy.waterfall.member.model.dto.JobDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.dto.TeamDTO;
import com.greedy.waterfall.member.model.service.MemberService;
/**
 * <pre>
 * Class : MemberController
 * Comment : 1. 사용자 회원의 로그인 
 * 			 2. 계정 생성, 계정 목록조회, 계정 수정, 
 * 			 3. 계정 이메일 발송
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
@Controller
@RequestMapping("/member")
@SessionAttributes("loginMember")
public class MemberController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	public MemberController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * login : 로그인 화면
	 * @param : 로그인 화면 요청
	 * @return "/member/memberLogin" : 요청화면으로 view 반환 
	 * 
	 * @author 김영광
	 */
	@GetMapping("/login")
	public String login() {
		
		return "/member/memberLogin";
	}
	
	/**
	 * login2 : 일반 로그인 메인 멤버회원의 화면
	 * @param  : 로그인 이후 member회원의 화면 요청
	 * @return "/main/mainPage" : 멤버회원의 요청화면으로 view 반환 
	 * 
	 * @author 김영광
	 */
	@GetMapping("/login2") 
	public String login2() {
		
		return "/main/mainPage";
	}
	
	/* 관리자 화면으로 가기 */
	/**
	 *  login3 : 로그인 이후 관리자 메인화면 
	 * @param : 로그인 이후 admin의 화면 요청
	 * @return "/main/adminMain" : 관리자 요청화면으로 view 반환
	 * 
	 * @author 김영광
	 */
	@GetMapping("/login3") 
	public String login3() {
		
		return "/main/adminMain";
	}
	
	/**
	 * login : 로그인요청 ID와 PW
	 * @param member : 요청한 ip,pwd 정보를 MemberDTO클래스에 필드명 정보가 담긴 변수
	 * @param model : loginMember 세션 키값에 회원정보 담은 변수 
	 * @return "redirect:/menu/main" : 요청주소로 반환한다.
	 * 
	 * @author 김영광
	 */
	@PostMapping("/login2")
	public String login(@ModelAttribute MemberDTO member, Model model) throws LoginFailedException {
		
		if("1".equals(member.getRole())) {
			model.addAttribute("loginMember", memberService.findMember(member));
			
			return "redirect:/menu/main"; 
		
		} else {			
			model.addAttribute("loginMember", memberService.findMember(member));
		}
		
		return "redirect:/menu/main";
	}
		
	/**
	 * logout : 세션에서 로그인정보를 없앤다. 로그인 페이지를 요청한다.
	 * @param sessionStatus : 세션만료를 위한 변수   
	 * @return "redirect:/member/login" : 로그인화면으로 view 반환
	 * 
	 * @author 김영광
	 */
	@GetMapping("logout")
	public String logout(SessionStatus sessionStatus) {
	
		sessionStatus.setComplete();
		
		return "redirect:/member/login";
	}
	
	/**
	 * findAdminMemberList : 조회한 계정 목록전체를 view로 전달하는 메소드
	 * @param request : 계정 목록에 현재 페이지, 검색, 요청을 전달 받는다.  
	 * @return mv("member/memberList") : 계정 목록, 검색 결과 요청주소를 담아 반환한다. 
	 * 
	 * @author 김영광
	 */
	@GetMapping("list")
	public ModelAndView findAdminMemberList(HttpServletRequest request ,ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		int totalCount = memberService.selectTotalCount(searchMap);
		
		int limit = 10;
		
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, 0, null);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		List<AdminMemberDTO> adminMemberList = memberService.findAdminMember(selectCriteria); 
		
		mv.addObject("adminMemberList", adminMemberList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/member/list");
		mv.setViewName("/member/memberList");
		
		return mv;
	}
	
	/**
	 * regist : 계정 생성에서 사용되는 selectBox의 부서,직급 리스트 목록을 전달해주는 메소드
	 * @param : 계정 생성에서  부서,직급으로 쓸 리스트 목록을 요청
	 * @return mv("member/memberRegist") : 부서, 직급 목록 리스트를 담아 지정한 view의 주소 반환
	 * 
	 * @author 김영광
	 */
	@GetMapping("regist")
	public ModelAndView regist(ModelAndView mv) {
		
		Map<String, Object> allList = memberService.findDeptJobService(); 

		List<DeptDTO> deptList  = (List<DeptDTO>)allList.get("deptDTO");
		List<JobDTO> jobList = (List<JobDTO>)allList.get("jobDTO");
		
		mv.addObject("deptList", deptList);
		mv.addObject("jobList", jobList);
		mv.setViewName("/member/memberRegist");
		
		return mv;
	}
	
	/**
	 * findTeam : 계정생성에 selectBox에서 1-depth(부서)에 따른 2-depth(팀)을 보여줄 리스트 목록을 view로 전달해주는 메소드
	 * @param Pathvariable(deptCode) : 1-depth(부서)에 따른 2-depth(팀)을 보여줄 리스트 목록을 요청함 
	 * @return mv("jsonView") : 요청한 jsonview로 반환  
	 * 
	 * @author 김영광
	 */
	@GetMapping("regist2/{deptCode}")
	public ModelAndView findTeam(ModelAndView mv, @PathVariable("deptCode") String deptCode ,
			HttpServletResponse response) throws JsonProcessingException {
		
		List<TeamDTO> teamList = memberService.findTeamList(deptCode);
		
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("teamList", mapper.writeValueAsString(teamList));
		mv.setViewName("jsonView"); 
		
		return mv;                                                                                            	
	}                                                                     										
																													
	/**
	 * registMember : 계정 정보를 등록한다.
	 * @param RequestParam : 이름, 부서, 팀, 직급 정보 담고있는 map 
	 * @param rttr : 등록 성공시 출력할 메세지 
	 * @return mv("redirect:/member/regist") : 요청 주소값을 반환
	 * 
	 * @author 김영광
	 */
	@PostMapping("/regist3")                                                                                	
	public ModelAndView registMember(ModelAndView mv, @RequestParam Map<String, String> parameter,
			RedirectAttributes rttr) throws MemberRegistException {       																	
	
		String name = parameter.get("name");
		String dept = parameter.get("dept");
		String team = parameter.get("team");
		String job = parameter.get("job");
	
		AdminMemberDTO adminMember = new AdminMemberDTO();
		DeptDTO deptDTO = new DeptDTO();
		TeamDTO teamDTO = new TeamDTO();
		JobDTO jobDTO = new JobDTO();
		
		deptDTO.setDeptCode(dept);;
		teamDTO.setTeamCode(team);
		jobDTO.setJobCode(job);
		
		adminMember.setName(name);
		adminMember.setDept(deptDTO);
		adminMember.setTeam(teamDTO);
		adminMember.setJob(jobDTO);
				
		memberService.adminMemberRegist(adminMember);
		String message = "등록에 성공하셨습니다.";
		
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/member/regist");
		
		return mv;                                                                                      					
	}
	
	/**
	 * findMemberModify : 계정목록 중 수정할 계정 정보를 조회 후 전달해주는 메소드 
	 * @param request : 선택한 계정 id의 정보를 조회하기 위한  변수 
	 * @return mv("jsonView") : 조회한 해당 계정의 정보와 요청 url 정보를 담고있는 ModelAndView변수 반환 
	 * 
	 * @author 김영광
	 */
	@GetMapping(value="modify")
	@ResponseBody
	public ModelAndView findMemberModify(HttpServletRequest request, HttpServletResponse response,
			ModelAndView mv) {
		
		String id = request.getParameter("id");
		id = id.replace("\"","");
		
		AdminMemberDTO modify = memberService.findMemberModify(id);
			
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		mv.addObject("modify", gson.toJson(modify));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * memberModify : 수정할 아이디, 이름, 부서, 팀, 직급을 등록한다. 
	 * @param parameter : 수정할 아이디, 이름, 부서, 팀 , 직급을 담고있는 변수
	 * @param rttr : 수정의 성공한 메세지를 담고있다.
	 * @return mv("redirect:/member/list") : 요청 주소값을 반환
	 * 
	 * @author 김영광
	 */
	@PostMapping("/memberModify")
	public ModelAndView memberModify(ModelAndView mv, @RequestParam Map<String, String> parameter, RedirectAttributes rttr) {
		
		String id = parameter.get("id");
		String name = parameter.get("name");
		String dept = parameter.get("dept");
		String team = parameter.get("team");
		String job = parameter.get("job");
		
		id = id.replace("\"", "");
		
		AdminMemberDTO adminMember = new AdminMemberDTO();
		DeptDTO deptDTO = new DeptDTO();
		TeamDTO teamDTO = new TeamDTO();
		JobDTO jobDTO = new JobDTO();
		
		deptDTO.setDeptCode(dept);
		teamDTO.setTeamCode(team);
		jobDTO.setJobCode(job);
		
		adminMember.setId(id);
		adminMember.setName(name);
		adminMember.setDept(deptDTO);
		adminMember.setTeam(teamDTO);
		adminMember.setJob(jobDTO);

		memberService.memberModify(adminMember);
		
		String message = "수정에 성공하셨습니다.";
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/member/list");
		
		return mv;
	}
	
	/**
	 * removeMember : 선택된 계정을 삭제한다.
	 * @param request : 선택된 계정에 아이디를 담고있는 변수
	 * @param rttr : 삭제 성공 메세지가 담겨 있는 변수 
	 * @return mv("redirect:/member/list") : 요청 주소를 담은 변수를 반환한다. 
	 * 
	 * @author 김영광
	 */
	@GetMapping("/delete")
	public ModelAndView removeMember(ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr) {
		
		String id = request.getParameter("id");
		id = id.replace("\"", "");

		memberService.removeMember(id);
		
		String message = "삭제에 성공하셨습니다.";
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/member/list");
		
		return mv;
	}
	
	
	/**
	 * mailCheckGET : 이메일 인증번호를 랜덤하게 생성하고 mailSender를 통해 메일을 보내는 메소드  
	 * @param email : 이메일 정보를 담고 있는 변수
	 * @return num : 인증번호를 담아있는 변수를 반환 
	 * 
	 * @author 김영광
	 */
	@RequestMapping(value="/mailCheck", method=RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) {
		
		Random random = new Random();
		
		int checkNum = random.nextInt(888888) + 111111; //111111~999999 범위

		
		String setFrom = "zxcv4097@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다." +
						 "<br><br>" +
						 "인증 번호는" + checkNum + "입니다." +
						 "<br>" +
						 "해당 인증번호를 인증번호 확인란에 가입해 주세요.";
		
		
		  try {
		  
		  MimeMessage message = mailSender.createMimeMessage(); MimeMessageHelper
		  helper = new MimeMessageHelper(message, true, "utf-8");
		  helper.setFrom(setFrom); helper.setTo(toMail); helper.setSubject(title);
		  helper.setText(content,true); mailSender.send(message);
		  
		  }catch(Exception e) { 
			  e.printStackTrace();   
		  }
		 		
		String num = Integer.toString(checkNum); 
		
		return num;
	}
	
	
}
