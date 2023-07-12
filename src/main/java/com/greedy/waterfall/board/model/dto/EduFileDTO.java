package com.greedy.waterfall.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : EduFileDTO
 * Comment : 게시판 파일정보를  등록,수정,상태,삭제 정보를 저장한 클래스
 * 관련 테이블 : TBL_BOARD, TBL_FILE, TBL_PROJECT   
 * 
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EduFileDTO implements java.io.Serializable {

	private int fileNo;					//파일 번호
	private int refBoardNo;				//게시글 번호
	private String savedPath;			//파일 저장 경로
	private String originalName; 		//원본 파일 이름
	private String randomName;			//랜덤 저장 파일 이름
	private String status;				//삭제여부
	private int fileCategoryNo;			//게시판 카테고리 구분 번호
}
