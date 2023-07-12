package com.greedy.waterfall.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * interface : MeetingMapper
 * Comment : 회의록게시판의 DAO를 담당하는 인터페이스. MeetingServiceImpl과 MeetingMapper.xml을 연결한다.
 * 
 * History
 * 2022. 2. 23.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Repository
public interface MeetingMapper {
	/**
	 * findMeetingList : 현재페이지의 게시글 목록을 조회한다.
	 * @param 페이징처리와 검색값을 담고있는 SelectCriteria변수를 전달받는다.
	 * @return 검색여부와, 현재페이지수에 맞는 게시글을 조회한 후 List형태로 반환한다.
	 * 
	 * @author 홍성원
	 */
	List<MeetingDTO> findMeetingList(SelectCriteria selectCriteria);

	/**
	 * findMeetingBoardDetail : 하나의 게시글의 상세정보를 조회한다.
	 * @param 게시글 번호를 전달받는다.
	 * @return 게시글의 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	MeetingDTO findMeetingBoardDetail(int meetingNo);

	/**
	 * findMeetingTotalCount : 프로젝트의 전체 회의록 게시글 갯수를 조회한다.
	 * @param 검색조건과, 검색값을 담고있는 Map을 전달받는다.
	 * @return 조건에 맞는 게시글의 전체 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int findMeetingTotalCount(Map<String, String> searchMap);

	/**
	 * registMeetingBoard : 회의록 게시글을 등록한다.
	 * @param 게시글의 정보를 전달받는다.
	 * @return 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int registMeetingBoard(MeetingDTO parameter);

	/**
	 * removeMeetingBoard : 게시글을 삭제한다.
	 * @param 삭제할 게시글 번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean removeMeetingBoard(int meetingNo);
	
	/**
	 * modifyMeetingBoard : 게시글을 수정한다.
	 * @param 수정할 내용을 전달받는다.
	 * @return 수정 여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean modifyMeetingBoard(Map<String, String> meeting);

	/**
	 * findOneMeetingBoard : 하나의 게시글을 조회한다.
	 * @param 게시글의 번호를 전달받는다.
	 * @return 하나의 게시글의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	MeetingDTO findOneMeetingBoard(int meetingNo);

	/**
	 * increseCount : 조회수를 1 증가시킨다.
	 * @param 조회수를 증가시킬 게시글 번호를 전달받는다.
	 * @return 조회수 증가 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	void increseCount(int meetingNo);

	/**
	 * registMeetingFile : 게시글의 첨부파일을 등록한다.
	 * @param 첨부파일의 정보를 전달받는다.
	 * @return 첨부파일의 등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int registMeetingFile(FileDTO fileDTO);

	/**
	 * fineFile : 게시글의 첨부파일을 조회한다.
	 * @param 조회할 첨부파일의 번호를 전달받는다. 
	 * @return 해당 번호의 첨부파일 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	FileDTO fineFile(int no);

	/**
	 * removeMeetingBoardFile : 회의록 게시글의 첨부파일을 삭제한다.
	 * @param 삭제할 첨부파일의 파일번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	boolean removeMeetingBoardFile(int fileNo);
}
