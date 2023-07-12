package com.greedy.waterfall.member.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : DeptJobDTO
 * Comment : 부서와 직급을 담을 정보
 * 관련 테이블 : TBL_DEPT, TBL_TEAM
 * 
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DeptJobDTO {

	private String deptCode;		//부서코드
	private String deptName;		//부서이름
	private String jobCode;			//직급코드
	private String jobName;			//직급이름
			
}
