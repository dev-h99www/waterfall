package com.greedy.waterfall.output.model.dto;

import org.springframework.web.multipart.MultipartFile;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

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
@ToString
@Builder
public class OutputDTO {
	
	private int outputNo;					//산출물번호
	private int outputVer;					//산출물 버젼 번호
	private int taskNo;						//산출물이 속한 업무 번호
	private int fileNo;						//산출물 내 파일 번호
	private int memberNo;					//산출물을 올린 member번호
	private int projectNo;					//산춞물이 속한 프로젝트 번호
	private String content;					//산출물 게시글 내용
	private java.sql.Date registedDate;		//산출물 등록일
	private String status;					//산출물 삭제여부
	private String memberName;				//산출물 등록인 이름
	private TaskDTO task;					//산출물이 속한 업무 정보
	private ChildTaskDTO childTask;			//산춞물이 속한 하위업무 정보
	private OutputProjectDTO project;		//산출물이 속한 프로젝트 정보
	private OutputAttachmentDTO outputFile;	//산출물 내 파일 정보
	private String taskName;				//산춞물이 속한 업무명
	private String beforeOriginName;		//산출물 변경 전 파일명
	private String afterOriginName;			//산출물 변경 후 파일명
	
	


}
