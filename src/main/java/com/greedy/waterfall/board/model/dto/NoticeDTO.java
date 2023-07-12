package com.greedy.waterfall.board.model.dto;

import java.sql.Date;

import com.greedy.waterfall.board.model.noticedto.NoticeAttachmentDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;

/**
 * <pre>
 * Class : NoticeDTO
 * Comment : 공지사항 DTO
 * 
 * History
 * 2022. 2. 25.  (김서영)
 * @version 1
 * @author 김서영
 */

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
public class NoticeDTO {
	
	private int no; 						//공지사항 번호
	private java.sql.Date updatedDate;		//업데이트 날짜
	private java.sql.Date registedDate;		//등록한 날짜
	private String title;					//공지사항 제목
	private String content;					//공지사항 내용
	private String status;					//공지사항 삭제여부
	private int projectNo;					//공지사항 게시판이 존재하는 프로젝트 번호
	private int boardCategoryNo;			//게시판 카테고리 번호(공지사항 = 1)
	private int memberNo;
	private MemberDTO memberName;
	private int count;
	private NoticeAttachmentDTO attachmentDTO;
	
	private String fileOriginName;
	
	
	

	
	
	
}
