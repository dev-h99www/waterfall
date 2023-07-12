package com.greedy.waterfall.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : GuideMemberDTO
 * Comment : TBL_MEMBER 테이블과 TBL_BOARD 테이블을 조인하기 위해서 작성한 테이블, TBL_MEMBER 테이블과 대응한다.
 * 
 * History
 * 2022. 2. 19.  (박성준)
 * </pre>
 * @version 1
 * @author 박성준
 */
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GuideMemberDTO {

	int no;			//회원번호
	String name;	//회원이름
}
