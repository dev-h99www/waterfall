package com.greedy.waterfall.issue.model.dto;
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
public class IssueHistoryDTO {

	private IssueDTO issue;
	private int issueNo;
	private String issueName;
	private IssueTaskDTO task;
	private int taskNo;
	private IssueTaskCodeManageDTO taskCode;
	private String taskCodeManageName;
	private IssueProjectDTO project;
	private int projectNo;
	private ProjectMemberDTO projectMember;
	private int registerNo;
	private String registerName;
	private int issueReader;
}
