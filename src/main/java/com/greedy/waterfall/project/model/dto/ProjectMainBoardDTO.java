package com.greedy.waterfall.project.model.dto;

import java.util.List;

import com.greedy.waterfall.board.model.dto.BoardDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : ProjectMainBoardDTO
 * Comment : 프로젝트 메인화면에 출력되는 모든 게시판의 게시글들의 목록을 저장한다.
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
public class ProjectMainBoardDTO {
	
	private List<BoardDTO> meetingBoard;
	private List<BoardDTO> todoBoard;
	private List<BoardDTO> noticeBoard;
	private List<BoardDTO> eduBoard;
	private List<BoardDTO> guideBoard;
	
}
