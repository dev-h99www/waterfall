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
public class ProjectIssueCountDTO {

	private int projectNo;
	private IssueProjectDTO project;
	private String projectName;
	private int allIssueCount;
	private int pendingIssueCount;
	private int processingIssueCount;
	private int completedIssueCount;
	private int managerNo;
	private String pmName;
}
