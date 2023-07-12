package com.greedy.waterfall.board.model.dto;

import java.sql.Date;

import com.greedy.waterfall.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor	//기본 생성자
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TodoDTO {

	private int no;						//글번호
	private java.sql.Date postingDate;	//등록일
	private String title;				//제목
	private String content;				//본문내용
	private String status;				//게시물삭제여부
	private int projectNo;				//프로젝트번호
	private int categoryNo;				//게시판카테고리번호
	private java.sql.Date updatedDate;	//수정일
	private int memberNo;				//회원번호
	private int count;					//조회수
	
	private MemberDTO writer;			//MemberTable과 join하는 경우 1:1 조인이 될 것이기 때문에 MemberDTO 타입으로 생성
	
//	public TodoDTO() {}	//기본 생성자
	
}
