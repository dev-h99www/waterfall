package com.greedy.waterfall.menu.model.dto;

import com.greedy.waterfall.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : MainInfoDTO
 * Comment : 메인메뉴에 필요한 정보를 조회하기위해 필요한 정보들을 DB로 전달해주는 클래스
 * 
 * History
 * 2022. 3. 7.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class MainInfoDTO {
	private String currentPage;			//현재 페이지의 번호를 저장한다
	private String subcurrentPage;		//서브 페이지의 번호를 저장한다.
	private MemberDTO loginMember;		//로그인한 회원의 정보를 저장한다.
	
}
