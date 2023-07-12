package com.greedy.waterfall.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class MemberDTO {

	private int no; 					//회원번호
	private String id; 					//아이디
	private String pwd;					//비밀번호
	private String phone;   			//핸드폰번호
	private String email;   			//이메일
	private java.sql.Date createDate; 	//등록일
	private java.sql.Date retiredDate;  //탈퇴일
	private String status; 				//탈퇴여부
	private String name; 				//이름
	private String initStatus; 			//비밀번호 초기화
	private String role; 				//권한

	
}
