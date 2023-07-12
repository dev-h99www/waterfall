package com.greedy.waterfall.member.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : MemberInfoDTO
 * Comment : 회원의 정보가 최신 현황를 담을 클래스
 * 관련 테이블 : TBL_MEM_COM_INFO 
 * 			
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MemberInfoDTO {

	private int historyNo;				//히스토리 고유번호
	private int no;						//회원번호
	private String code;				//코드(이메일, 핸드폰번호)
	private String content;				//변경 내용
	private java.sql.Date date;			//등록 날짜
	
}
