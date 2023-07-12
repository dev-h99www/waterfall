package com.greedy.waterfall.issue.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskIssueCountDTO {
	
	private int taskNo;						//업무 번호 (어떤 업무에서 발생한 이슈인지 확인)
	private int projectNo;
	private IssueProjectDTO project;
	private int allIssueCount;
	private int pendingIssueCount;
	private String managerName;
	private int processingIssueCount;
	private int completedIssueCount;
	private int managerNo;
	private IssueManagerDTO manager;
	private IssueTaskDTO task;				//TBL_TASK 테이블과 JOIN하는 경우 1:1 조인이 될 것이기  때문에 DTO타입으로 생성
	private IssueTaskCodeManageDTO taskCode;		//업무이름을 저장하기 위한 DTO
	private String taskCodeManageName;
	
}
