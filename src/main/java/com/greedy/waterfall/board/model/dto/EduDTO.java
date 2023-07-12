package com.greedy.waterfall.board.model.dto;

import java.sql.Date;

import com.greedy.waterfall.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : EduDTO
 * Comment : 게시판 등록,수정,상태,삭제 정보를 저장한 클래스
 * 관련 테이블 : TBL_BOARD, TBL_FILE, TBL_MEMBER, TBL_PROJECT   
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
public class EduDTO {

	private int no; 					//글번호
	private java.sql.Date registedDate; //동록일
	private String title; 				//제목
	private String content; 			//내용
	private String status; 				//삭제여부 
	private int projectNo; 				//프로젝트 번호
	private int boardCategoryNo;		//카테고리 번호
	private java.sql.Date updatedDate;  //수정일
	private int memberNo;				//회원번호
	private int count;					//조회수
	private MemberDTO writer;			//작성자 조인
	private int rnum;					//게시판 번호
	private EduFileDTO file;  			//file 테이블과 조인
	private int fileNo; 				//첨부파일 번호
	private String originalName;  		//첨부파일 이름
}
