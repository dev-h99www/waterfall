package com.greedy.waterfall.member.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : DeptHistoryDTO
 * Comment : 부서 히스토리를 담을 정보
 * 관련 테이블 : TBL_MEM_DEPT_HISTORY
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
public class DeptHistoryDTO {
	
	private String deptCode;		//부서코드
	private int no;					//회원번호
	private String updateDept;		//변경한 부서코드
}
