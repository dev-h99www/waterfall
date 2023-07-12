
package com.greedy.waterfall.board.model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.noticemapper.NoticeMapper;
import com.greedy.waterfall.board.model.dto.NoticeDTO;
import com.greedy.waterfall.board.model.noticedto.NoticeAttachmentDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : NoticeServiceImpl
 * Comment : NoticeService의 구현부 입니다. 공지사항 목록조회, 상세조회, 수정, 삭제 기능이 존재합니다.
 * 
 * History
 * 2022. 2. 17.  (김서영)
 * @version 1
 * @author 김서영
 */
@Service
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper mapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * selectTotalCount : 공지사항 총 게시물 개수
	 * @param : 검색할 시 select 된 카테고리와 검색할 내용을 담은 Map
	 * @return result : dao 실행 결과값 반환
	 * */
	@Override
	public int findTotalCount(Map<String, String> searchMap) {
		
		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}
	
	/**
	 * selectNoticeList : 공지사항 목록조회
	 * @param : 검색할 시 select 된 카테고리와 검색할 내용을 담은 Map
	 * @return result : dao 실행 결과값 반환
	 * */
	@Override
	public List<NoticeDTO> findNoticeList(SelectCriteria selectCriteria) {
		
		List<NoticeDTO> noticeList = mapper.selectNoticeList(selectCriteria);
		
		return noticeList;
	}
	/**
	 * findNoticeDetail : 공지사항 상세조회
	 * @param : 공지사항 고유번호
	 * @return noticeDetail : 공지사항 고유번호에 따른 data DTO에 담아서 반환
	 * */
	@Override
	public NoticeDTO findNoticeDetail(int no) {
		
		/*incrementNoticeCount : 조회수 증가*/
		int result = mapper.incrementNoticeCount(no);
		
		NoticeDTO noticeDetail = new NoticeDTO();
		
		if(result > 0) {
			noticeDetail = mapper.selectNoticeDetail(no);
		}
		
		return noticeDetail;
	}
	/**
	 * registNotice : 공지사항 등록
	 * @param : 등록할 공지사항 data DTO
	 * @return noticeDetail : 공지사항 고유번호에 따른 data DTO에 담아서 반환
	 * */
	@Override
	public void registNotice(NoticeDTO notice) {
		
		int result = mapper.insertNotice(notice);
		
		NoticeAttachmentDTO attachmentDTO = notice.getAttachmentDTO();
	
		if(attachmentDTO != null) {
			attachmentDTO.setNoticeNo(notice.getNo());
			int attachmentResult = mapper.insertAttachment(attachmentDTO);
		}
		
	}
	/**
	 * modifyNotice : 공지사항 수정
	 * @param : 수정한 공지사항 data DTO
	 * */
	@Override
	public void modifyNotice(NoticeDTO notice) {
		
		mapper.updateNotice(notice);
		
		
	}
	/**
	 * removeNotice : 공지사항 삭제
	 * @param : 공지사항 no
	 * */
	@Override
	public void removeNotice(int no) {
		
		NoticeAttachmentDTO file = new NoticeAttachmentDTO();
		
		file = mapper.selectNoticeFile(no);
		String fileUploadDirectory = file.getFilePath();
		String savedName = file.getRandomName();
		
		int result = mapper.deleteNotice(no);
		
		if(result > 0) {
			new File(fileUploadDirectory + "\\" + savedName).delete();
			
		}
		
	}

	/**
	 * findNoticeFile : 공지사항 파일정보 return
	 * @param int no : 공지사항 번호
	 * @return NoticeAttachmentDTO : no의 첨부파일 정보DTO
	 * 
	 * @author 김서영
	 */
	@Override
	public NoticeAttachmentDTO findNoticeFile(int no) {
		
		
		return mapper.selectNoticeFile(no);
	}

	/**
	 * removeNoticeFile : 공지사항 첨부파일 삭제
	 * @param int no 파일을 삭제할 공지사항 번호
	 * @return 
	 * 
	 * @author 김서영
	 */
	@Override
	public void removeNoticeFile(int no) {
		
		NoticeAttachmentDTO file = new NoticeAttachmentDTO();
		
		file = mapper.selectNoticeFile(no);
		String fileUploadDirectory = file.getFilePath();
		String savedName = file.getRandomName();
		
		new File(fileUploadDirectory + "\\" + savedName).delete();
		
	}

	

	

}
