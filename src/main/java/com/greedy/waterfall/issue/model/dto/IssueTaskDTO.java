package com.greedy.waterfall.issue.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IssueTaskDTO {

	private int no;
	private String taskCategoryCode;
	private int taskRefNo;
}
