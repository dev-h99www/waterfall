package com.greedy.waterfall.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : ProjectAuthorityDTO
 * Comment : 회원의 프로젝트 권한을 확인하기위에 세션에 담을 정보를 저장한다.
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
@ToString
@Builder
public class ProjectAuthorityDTO {

	private int projectNo;
	private int pmNo;
	private String projectName;
}
