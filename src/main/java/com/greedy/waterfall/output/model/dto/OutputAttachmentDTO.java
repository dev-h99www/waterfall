package com.greedy.waterfall.output.model.dto;

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
public class OutputAttachmentDTO {
	
	private int fileNo;						//파읾번호
	private String filePath;				//파일경로
	private String originName;				//파일 원본이름
	private String randomName;				//파일 랜덤이름
	private String status;					//파일 삭제상태
	private java.sql.Date registedDate;		//파일 등록일
	private int outputNo;					//파일이 속한 산출물 번호
	private int taskNo;						//파일이 속한 업무 번호

}
