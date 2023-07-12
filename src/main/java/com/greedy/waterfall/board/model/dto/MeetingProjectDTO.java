package com.greedy.waterfall.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : MeetingProjectDTO
 * Comment : 게시글의 소속 프로젝트의 번호를 저장하는 DTO 
 * 			 resultMap id = projectResultMap
 * History
 * 2022. 2. 19.  (홍성원)
 * @version 1
 * @author 홍성원
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MeetingProjectDTO {

	private int no;				//프로젝트의 번호를 저장
}
