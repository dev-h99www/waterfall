package com.greedy.waterfall.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : FileDTO
 * Comment : 회의록 게시판의 첨부파일 정보를 저장하는 DTO
 *           resultMap id = fileResultMap
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
public class FileDTO {

	private int fileNo;				//파일저장번호
	private String filePath;		//파일경로
	private String fileOriginName;	//파일 원본이름
	private String fileRandomName;	//파일 저장이름
	private String fileStatus;		//파일의 삭제여부
	private int refIssueNo;			//상위 이슈 번호 ( null 허용 )
	private int refTaskNo;			//상위 업무 번호 ( null 허용 )
	private int refBoardNo;			//상위 게시판 번호 ( null 허용 )
	private int fileCategoryNo;		//첨부파일의 상위 게시물의 종류(1: 이슈, 2: 업무, 3: 게시판) 
	

}