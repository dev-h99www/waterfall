package com.greedy.waterfall.task.model.dto;


/**
 * <pre>
 * Class : ProjectMemberDTO
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 28.  (김서영)
 * @version 1
 * @author 김서영
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ProjectMemberDTO {
	
	private int memberNo;
	private String memberName;
	private int roleNumber;
	
	
	
	
	
	
}
