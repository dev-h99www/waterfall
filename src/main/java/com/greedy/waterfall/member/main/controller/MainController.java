package com.greedy.waterfall.member.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {

	/* 루트주소의 요청시, 세션에 로그인정보가 있다면 메인화면으로 보내고, 아니라면 로그인화면으로 보낸다. */
	@GetMapping(value= {"/"})
	public String main(HttpSession session) {	
		if(session.getAttribute("loginMember") != null){
			return "redirect:/menu/main";
		}
		return "member/memberLogin";
	}
	
	
}
