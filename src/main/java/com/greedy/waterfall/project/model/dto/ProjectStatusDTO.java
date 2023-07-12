package com.greedy.waterfall.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : ProjectStatusDTO
 * Comment : 프로젝트 생성시 DB에서 조회해온 프로젝트 상태코드와 이름을 저장한다.
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
public class ProjectStatusDTO {

	private String statusCode;
	private String statusName;
	
}
