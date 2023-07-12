package com.greedy.waterfall.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : MeetingMemberDTO
 * Comment : 회의록 게시판 게시글 조회시 게시글 등록자의 번호와 이름을 저장하는 DTO
 *           resultMap id = memberResultMap
 * History
 * 2022. 2. 23.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MeetingMemberDTO {

	private int memberNo;			//게시물 등록자의 번호를 저장
	private String memberName;		//게시물 등록자의 이름을 저장
	private String memberStatus;		//게시물 등록자의 이름을 저장
}
