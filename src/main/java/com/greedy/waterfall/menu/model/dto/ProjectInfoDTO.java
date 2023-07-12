package com.greedy.waterfall.menu.model.dto;

import java.util.List;

import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : ProjectInfoDTO
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 3. 4.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ProjectInfoDTO {
	private int outputAmount;				//산출물 갯수
	
	private int totalIssueAmount;			//이슈 총 갯수
	private int watingIssueAmount;			//대기중 이슈 갯수
	private int progressingIssueAmount;		//처리중 이슈 갯수
	private int solvedIssueAmount;			//완료 이슈 갯수
	
	private int totalTaskAmount;			//업무 총 갯수
	private int beforeProceedingTaskAmount;	//진행전 업무 갯수 
	private int progressingTaskAmount;		//진행중 업무 갯수
	private int testingTaskAmount;			//테스트중 업무 갯수
	private int finishedTaskAmount;			//진행완료 업무 갯수
	private int pendingTaskAmount;			//보류 업무 갯수
	
	private List<ProjectRoleDTO> role;
}
