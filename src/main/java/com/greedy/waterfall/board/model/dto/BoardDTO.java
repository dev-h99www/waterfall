package com.greedy.waterfall.board.model.dto;

import java.util.List;

import com.greedy.waterfall.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : MeetingDTO
 * Comment : 회의록 게시판 게시물의 정보를 담기 위한 DTO
 *           resultMap id = meetingResult
 * 
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
@Builder
public class BoardDTO {

	private int no;							//게시물 번호
	private java.sql.Date updatedDate;		//게시물 등록일
	private java.sql.Date registedDate;		//게시물 수정일
	private String title;					//게시물 제목
	private String content;					//게시물 내용
	private String status;					//게시물 삭제여부
	private int boardCategoryNo;			//게시판 카테고리 번호 (4 : 회의록 게시판)
	private int projectNo;					//해당 프로젝트 번호
	private int memberNo;					//작성자 번호
	private int boardCount;					//게시물 조회수
	private MemberDTO member;		//게시자의 이름을 저장하는 DTO
	private List<FileDTO> file;				//해당 게시판의 첨부파일의 정보를 저장하는 DTO
	
	private String boardCategoryName;
}
