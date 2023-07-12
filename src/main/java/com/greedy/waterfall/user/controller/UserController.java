package com.greedy.waterfall.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.service.MemberService;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;

/**
 * <pre>
 * Class : UserController
 * Comment : 회원의 계정수정, 비밀번호, 이메일, 핸드폰 등록
 * 
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("loginMember")
public class UserController {
	
	private final MemberService memberService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * info : 개인정보조회 메소드
	 * @param : 개인정보조회 페이지 요청
	 * @return mv("/user/userInfoModify") : 요청주소로 반환  
	 * 
	 * @author 김영광
	 */
	@GetMapping("info")
	public ModelAndView info(ModelAndView mv) {
			
		mv.setViewName("/user/userInfoModify");
		
		return mv;
	}
	
	/**
	 * pwCheck : 현재 비밀번호를 비교 해주는 메소드 
	 * @param parameter : 아이디와 비밀번호가 정보가 담겨있는 변수 
	 * @return : 일치하면 1 일치하지 않으면 0을 반환한다. 
	 * 
	 * @author 김영광
	 */
	@PostMapping(value="/pmCheck", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int pwCheck(@RequestParam Map<String, String> parameter) {

		String id = parameter.get("id");
		String pwd = parameter.get("pwd");
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPwd(pwd);
		
		String memberPwd = memberService.pwCheck(member.getId());
				
		if(member == null || !BCrypt.checkpw(member.getPwd(), memberPwd)) {

			return 0;
		}
		
		return 1;
	}
	
	/**
	 * pwUpdate : 새로운 비밀번호 암호화 등록 메소드
	 * @param id : 입력한 아이디를 담고있는 변수
	 * @param pwd1 : 입력한 새로운 비밀번호를 담고있는 변수
	 * @param rttr : 수정 성공시 담고있는 메세지
	 * @param session : 세션을 만료시키기 위한 변수
	 * @return "redirect:/member/login" : 요청한 주소로 반환한다.
	 * 
	 * @author 김영광
	 */
	@RequestMapping(value="/pwUpdate", method=RequestMethod.POST)
	public String pwUpdate(String id, String pwd1, RedirectAttributes rttr, HttpSession session) {
		
		String hashedPw = BCrypt.hashpw(pwd1, BCrypt.gensalt());
		
		memberService.pwUpdate(id, hashedPw);
		
		session.invalidate();
		rttr.addFlashAttribute("message", "정보 수정이 완료되었습니다. 다시 로그인해주세요.");
		
		return "redirect:/member/login";
	}
	
	/**
	 * memberInfo : 새로운 이메일, 핸드폰 번호를 등록 or 수정 등록 메소드
	 * @param parameter : 이메일과 핸드폰번호를 정보를 담고 있는 변수
	 * @param session : 회원정보가 담겨있는 변수
	 * @param rttr : 성공 시 메세지가 담겨있는 변수
	 * @return mv("redirect:/user/info") : 개인정보로 요청한 주소로 반환한다. 
	 * 
	 * @author 김영광
	 */
	@PostMapping("memberInfo")
	public ModelAndView memberInfo(ModelAndView mv,@RequestParam Map<String, String> parameter , RedirectAttributes rttr,
			HttpSession session) {
		
		MemberDTO member = new MemberDTO();
		
		member.setId(((MemberDTO) session.getAttribute("loginMember")).getId());
		
		String email = parameter.get("email");
		String phone = parameter.get("phone");

		member.setEmail(email);
		member.setPhone(phone);

		memberService.memberInfo(member);
	
		session.invalidate();
		
		rttr.addFlashAttribute("message", "정보 수정이 완료되었습니다.");
		mv.setViewName("redirect:/member/login"); //메인
		
		return mv;
	}
}
