package com.greedy.waterfall.task.model.dto;

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
public class TaskHistoryDTO {
	
	private int taskNo;
	private int memberNo;
	private String memberName;
	private String taskName;
	private String taskCode;
	private int projectNo;
	

}
