package com.greedy.waterfall.board.model.noticemapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.NoticeDTO;
import com.greedy.waterfall.board.model.noticedto.NoticeAttachmentDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : NoticeMapper
 * Comment : 공지사항 mapper에서 사용되는 메소드
 * 
 * History
 * 2022. 3. 2.  (김서영)
 * @version 1
 * @author 김서영
 */
public interface NoticeMapper {

	/**
	 * selectTotalCount : 공지사항 게시물 개수
	 * @param searchCondition, searchValue, projectNo이 담긴 Map
	 * @return 조건에 해당하는 공지사항 게시물 개수
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int selectTotalCount(Map<String, String> searchMap);		

	/**
	 * selectNoticeList : 페이징 처리를 적용한 공지사항 리스트 조회
	 * @param 페이징 처리를 위한 정보 DTO
	 * @return 페이징 후 공지사항 리스트
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria);		

	/**
	 * selectNoticeDetail : 공지사항 상세 정보 조회
	 * @param 공지사항 번호
	 * @return 공지사항 상세 정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	NoticeDTO selectNoticeDetail(int no);									

	/**
	 * incrementNoticeCount : 게시물 클릭시 조회수 증가
	 * @param 조회수 증가시킬 공지사항 번호
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int incrementNoticeCount(int no);										

	/**
	 * insertNotice : 공지사항 등록
	 * @param 등록할 공지사항 정보
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int insertNotice(NoticeDTO notice);										

	/**
	 * updateNotice : 공지사항 수정
	 * @param 수정할 공지사항 정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	void updateNotice(NoticeDTO notice);									

	/**
	 * deleteNotice : 공지사항 삭제
	 * @param 삭제할 공지사항 번호
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int deleteNotice(int no);												

	/**
	 * insertAttachment : 첨부파일 등록
	 * @param 공지사항에 등록할 첨부파일 정보
	 * @return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int insertAttachment(NoticeAttachmentDTO attachmentDTO);			

	/**
	 * selectNoticeFile : 공지사항 첨부파일 정보 조회
	 * @param 첨부파일이 속한 공지사항 번호
	 * @return 공지사항 첨부파일 정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	NoticeAttachmentDTO selectNoticeFile(int no);


}
