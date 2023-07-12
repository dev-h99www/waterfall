package com.greedy.waterfall.board.model.noticedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NoticeAttachmentDTO {
	
	private int fileNo;				//공지사항 파일번호
	private String filePath;		//공지사항 파일경로
	private String originName;		//공지사항 파일 원본명
	private String randomName;		//공지사항 파일 랜덤명
	private String status;			//파일 삭제여부
	private int noticeNo;			//파일이 속한 공지사항 번호
	private int categoryNo;			//게시판 카테고리 번호
	

}
