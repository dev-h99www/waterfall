package com.greedy.waterfall.task.model.dto;

import java.sql.Date;
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
public class TaskRegistDTO {
	
	private String taskCode;
	private String taskName;		//history등록시 taskCategoryName
	private String parentTaskCode;
	
	private String taskMember;		//업무 담당자
	
	private Date startDate;
	private Date deadline;
	private String importance;
	private String progressStatus;
	private int progressRatio;
	private int typeNo;
	private int projectNo;
	
	private int parentTaskNo;
	private int taskNo;
	
	private int memberNo;			// session member 번호
	private String memberName;			// session member 이름
	
	
}
