package com.greedy.waterfall.issue.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IssueTaskCodeManageDTO {

	private String taskCategoryCode;
	private String taskCategoryName;
}
