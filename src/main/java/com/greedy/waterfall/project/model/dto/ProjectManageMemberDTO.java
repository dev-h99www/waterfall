package com.greedy.waterfall.project.model.dto;

import java.util.List;

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
public class ProjectManageMemberDTO {

	private int projectNo;
	private String projectName;
	private int memberNo;
	private String memberName;
	private int completeTask;
	private int progressingTask;
	private int registOutput;
	private int registIssue;
	private int managerNo;
	private String managerName;
	private java.sql.Date joinDate;
	private java.sql.Date quitDate;
	
	private List<ProjectRoleDTO> role;
	
}
