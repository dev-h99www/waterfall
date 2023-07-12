package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.MeetingDTO;

/**
 * <pre>
 * interface : MeetingService
 * Comment : MeetingServiceImpl에서 사용할 Method들을 선언해놓은 인터페이스
 * 
 * History
 * 2022. 2. 23.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
public interface MeetingService {
	/**
	 * findMeetingBoardList : 회의록게시판 검색과, 페이지정보를 담은 변수를 전달해, 해당 게시물목록을 반환받아 전달해주는 서비스 메소드
	 * @param selectCriteria : 검색조건과, 페이지정보를 담고있는 변수
	 * @return List<MeetingDTO> : 검색조건에 해당하는 한 페이지의 게시물의 정보를 저장한 변수를 반환
	 * 
	 * @author 홍성원
	 */
	Map<String, Object> findMeetingBoardList(Map<String, String> searchMap);

	/**
	 * findMeetingBoardDetail : 회의록 게시판 게시글의 번호를 입력받아 상세정보 반환해주는 서비스 메소드
	 * @param meetingNo : 상세정보를 조회할 게시물의 번호를 저장하는 변수
	 * @return MeetingDTO : 게시물 번호로 조회한 해당 게시물의 상세정보를 담은 변수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	MeetingDTO findMeetingBoardDetail(int meetingNo);

	/**
	 * findMeetingTotalCount : 검색조건을 전달받아 맞는 게시물의 갯수를 반환하는 서비스 메소드 
	 * @param searchMap : 검색조건을 Map형태로 저장한 변수
	 * @return: 검색조건에 해당하는 게시물의 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int findMeetingTotalCount(Map<String, String> searchMap);

	/**
	 * registMeetingBoard : 회의록 게시글을 등록한다.
	 * @param parameter : 등록할 게시글의 내용을 전달받는다.
	 * @return boolean : 게시물의 등록 여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean registMeetingBoard(MeetingDTO parameter);
	
	/**
	 * removeMeetingBoard : 게시글을 삭제한다.
	 * @param meetingNo : 삭제할 게시글의 번호를 전달받는다.
	 * @return boolean : 게시글 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean removeMeetingBoard(int meetingNo);
	
	/**
	 * modifyMeetingBoard : 게시글을 수정한다.
	 * @param meeting : 수정할 게시글의 내용을 전달받는다.
	 * @return boolean : 수정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean modifyMeetingBoard(Map<String, String> meeting);
	
	/**
	 * findOneMeetingBoard : 하나의 게시글을 조회한다.
	 * @param meetingNo : 조회할 게시글 번호를 전달받는다.
	 * @return MeetingDTO : 조회한 게시글 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	MeetingDTO findOneMeetingBoard(int meetingNo);

	/**
	 * findFile : 하나의 첨부파일의 상세정보를 조회한다.
	 * @param no : 조회할 첨부파일의 번호를 전달받는다.
	 * @return FileDTO : 첨부파일의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	Map<String, Object> findFile(int no);

	/**
	 * removeMeetingBoardFile : 첨부파일을 삭제한다.
	 * @param 삭제하려는 파일번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean removeMeetingBoardFile(int fileNo);
}