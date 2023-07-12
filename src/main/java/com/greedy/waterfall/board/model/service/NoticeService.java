/**
 * <pre>
 * Interface :NoticeService
 * Comment : 공지사항 목록조회, 상세조회, 수정, 삭제 기능이 존재합니다.
 * History
 * 2022/02/17 (김서영) 처음 작성
 * </pre>
 * @version 1
 * @author 김서영
 * @see 
 * */
package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;


import com.greedy.waterfall.board.model.dto.NoticeDTO;
import com.greedy.waterfall.board.model.noticedto.NoticeAttachmentDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface NoticeService {


	List<NoticeDTO> findNoticeList(SelectCriteria selectCriteria);

	NoticeDTO findNoticeDetail(int no);

	void registNotice(NoticeDTO notice);

	void modifyNotice(NoticeDTO notice);

	void removeNotice(int no);

	int findTotalCount(Map<String, String> searchMap);

	NoticeAttachmentDTO findNoticeFile(int noticeNo);

	void removeNoticeFile(int noticeNo);

	
	

}
