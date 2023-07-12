package com.greedy.waterfall.project.model.dto;

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
public class ProjectHistoryDTO {

	private int projectNo;
	private String projectName;
	private String memberName;
	private int managerNo;
	private int contentType;
	private String content;
}
