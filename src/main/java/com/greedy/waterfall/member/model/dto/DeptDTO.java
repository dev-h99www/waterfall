package com.greedy.waterfall.member.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : DeptDTO
 * Comment : 부서코드와 이름의 정보
 * 관련 테이블 : TBL_DEPT
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
public class DeptDTO {

	private String deptCode;	//부서코드
	private String deptName;	//부서이름
	
}
