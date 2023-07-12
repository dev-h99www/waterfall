package com.greedy.waterfall.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * Class : GuideProjectDTO
 * Comment : TBL_PROJECT 테이블과 TBL_BOARD 테이블을 조인하기 위해서 작성한 테이블, TBL_PROJECT 테이블과 대응한다.
 * 
 * History
 * 2022. 2. 19.  (박성준)
 * </pre>
 * @version 1
 * @author 박성준
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GuideProjectDTO {

	int no;		//프로젝트 번호
}
