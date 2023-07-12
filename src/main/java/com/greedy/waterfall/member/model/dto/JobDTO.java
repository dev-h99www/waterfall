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
 * Class : JobDTO
 * Comment : 직급을 담을 정보
 * 관련 테이블 : TBL_JOB_TITLE
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
public class JobDTO {

	private String jobCode;			//직급코드
	private String jobName;			//직급이름
	
}
