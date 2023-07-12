package com.greedy.waterfall.task.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class TaskDTO {
	
	private int taskNo;					//업무번호
	private int parentTaskNo;			//상위업무번호
	private String parentTaskName;		//상위업무명
	@JsonFormat(pattern = "yyyy-MM-dd")
	private java.sql.Date startDate;	//시작일
	@JsonFormat(pattern = "yyyy-MM-dd")
	private java.sql.Date deadline;		//마감기한
	private int progression;			//진행률
	private String progressStatus;		//진행상태("진행중","진행
	private String typeNo;				//1 : 일반 2: 마일스톤
	private String taskStatus;			//업무 삭제여부
	private int projectNo;				//업무에 해당하는 프로젝트번호
	private String projectName;			//업무에 해당하는 프로젝트명
	private int managerNo;				//업무 담당하는 사람 번호
	private String managerName;
	private String taskCode;			//업무코드
	private String importance;
	
	private int memberNo;				//sessiond에 담긴 memberNo
	
	private List<ChildTaskDTO> childList;
	private TaskCategoryDTO taskCategory;

}
