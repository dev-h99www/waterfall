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
 * Class : TeamDTO
 * Comment : 팀 코드와 팀 이름을 담을 클래스
 * 관련 테이블 : TBL_TEAM
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
public class TeamDTO {

	private String teamCode;		//팀 코드
	private String teamName;		//팀 이름
	
}
