package com.greedy.waterfall.common.History;

import java.util.List;

import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;

/**
 * <pre>
 * Class : History
 * Comment : 프로젝트 내 발생한 이력을 문자열로 저장하는 메소드를 선언한다.
 * 
 * History
 * 2022. 3. 13.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
public interface History {
	
	/* 이력 종류를 상수로 저장한다. */
	final static int PROJECT_HISTORY = 1;
	final static int TASK_HISTORY = 2;
	final static int ISSUE_HISTORY = 3;
	final static int OUTPUT_HISTORY = 4;
	final static int MEMBER_HISTORY = 5;
	final static int BOARD_HISTORY = 6;
	
	/**
	 * registHistory : 등록에 대한 이력을 저장한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectHistoryDTO> registHistory(Object info);

	/**
	 * modifyHistory : 수정에 대한 이력을 저장한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectHistoryDTO> modifyHistory(Object info);
	
	/**
	 * removeHistory : 삭제에대한 이력을 저장한다.
	 * 
	 * @author 홍성원
	 */
	List<ProjectHistoryDTO> removeHistory(Object info);
	
	/**
	 * recoveryHistory : 복구에 대한 이력을 저장한다.
	 * 
	 * @author 홍성원
	 */
	public List<ProjectHistoryDTO> recoveryHistory(Object info);
}
