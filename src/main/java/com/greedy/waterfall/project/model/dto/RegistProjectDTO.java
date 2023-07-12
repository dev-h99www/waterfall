package com.greedy.waterfall.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : RegistProjectDTO
 * Comment : 프로젝트 생성하는과정에 필요한 정보를 저장한다.
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
@Getter
@Builder
@ToString
public class RegistProjectDTO {

	private int projectNo;
	private String projectName;
	private java.sql.Date startDate;
	private java.sql.Date deadLine;
	private int pmNumber;
	private String pmName;
	private String pmStatus;
	private String projectStatusCode;
	private String projectStatusName;
	private int progression;
	private String dept;
	private String team;
	private int adminNo;
	private String adminName;
	
}
