package com.greedy.waterfall.project.model.dto;

import java.util.List;

import com.greedy.waterfall.common.paging.SelectCriteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : MyProjectDTO
 * Comment : 회원의 권한별 조회할 수 있는 프로젝트의 목록들을 저장한다.
 * 
 * History
 * 2022. 2. 25.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
@Builder
public class MyProjectDTO {

	private int no;
	private List<ProjectDTO> manageProject;
	private List<ProjectDTO> joinProject;
	private List<ProjectDTO> removedProject;
	
	private SelectCriteria selectCriteria;
	private SelectCriteria subselectCriteria;
	
	
	
}
