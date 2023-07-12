package com.greedy.waterfall.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : GuideFileDTO
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 21.  (박성준)
 * </pre>
 * @version 1
 * @author 박성준
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuideFileDTO implements java.io.Serializable {

	private int fileNo;				// 파일 번호(식별자)
	private int refBoardNo;			// 게시글 번호
	private String savedPath;		// 파일이 저장된 경로
	private String originalName;	// 원래 파일 이름
	private String randomName;		// 랜덤으로 저장될 파일 이름
	private String status;			// 삭제여부
	private int fileCategoryNo;		// 어떤 게시판에 속하는지에 대한 번호
	
}
