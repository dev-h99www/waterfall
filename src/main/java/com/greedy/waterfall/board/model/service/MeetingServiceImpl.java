package com.greedy.waterfall.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.board.model.mapper.MeetingMapper;
import com.greedy.waterfall.common.paging.Paging;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : MeetingServiceImpl
 * Comment : 회의록 게시판의 게시물 전체목록 조회, 게시글 상세조회, 수정, 삭제, 등록을 담당하는 서비스
 * 
 * History
 * 2022. 2. 18.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Service
public class MeetingServiceImpl implements MeetingService {
	private final MeetingMapper mapper;
	private final Paging paging;

	@Autowired
	public MeetingServiceImpl(MeetingMapper mapper, Paging paging) {
		this.mapper = mapper;
		this.paging = paging;
	}
	
	/**
	 * findMeetingBoardList : 회의록게시판 검색과, 페이지정보를 담은 변수를 전달해, 해당 게시물목록을 반환받아 전달해주는 서비스 메소드
	 * @param selectCriteria : 검색조건과, 페이지정보를 담고있는 변수
	 * @return List<MeetingDTO> : 검색조건에 해당하는 한 페이지의 게시물의 정보를 저장한 변수를 반환
	 * 
	 * @author 홍성원
	 */
	@Override
	public Map<String, Object> findMeetingBoardList(Map<String, String> searchMap) { 
		Map<String, Object> findResult = new HashMap<String, Object>();
		searchMap.put("totalCount", Integer.toString(mapper.findMeetingTotalCount(searchMap)));
		SelectCriteria selectCriteria = paging.setPagingCondition(searchMap);
		
		List<MeetingDTO> meetingList = mapper.findMeetingList(selectCriteria);
		findResult.put("selectCriteria", selectCriteria);
		findResult.put("meetingList", meetingList);
		
		/* 검색조건과 현재 페이지에 대한 정보를 담은 변수를 전달한 후 돌려받은 값을 반환한다. */
		return findResult;
	}

	/**
	 * findMeetingBoardDetail : 회의록 게시판 게시글의 번호를 입력받아 상세정보 반환해주는 서비스 메소드
	 * @param meetingNo : 상세정보를 조회할 게시물의 번호를 저장하는 변수
	 * @return MeetingDTO : 게시물 번호로 조회한 해당 게시물의 상세정보를 담은 변수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public MeetingDTO findMeetingBoardDetail(int meetingNo) {
		
		mapper.increseCount(meetingNo);
		/* 게시물 번호를 전달해 반환받은  게시물의 상세정보를 반환한다.*/
		return mapper.findMeetingBoardDetail(meetingNo);
	}

	/**
	 * findMeetingTotalCount : 검색조건을 전달받아 맞는 게시물의 갯수를 반환하는 서비스 메소드 
	 * @param searchMap : 검색조건을 Map형태로 저장한 변수
	 * @return: 검색조건에 해당하는 게시물의 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public int findMeetingTotalCount(Map<String, String> searchMap) {
		/* Map형태로 전달받은 검색 조건을 전달 후 조회된 게시물의 갯수를 반환한다. */
		return mapper.findMeetingTotalCount(searchMap);
	}

	/**
	 * registMeetingBoard : 회의록 게시글을 등록한다.
	 * @param parameter : 등록할 게시글의 내용을 전달받는다.
	 * @return boolean : 게시물의 등록 여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean registMeetingBoard(MeetingDTO parameter) {
		/* Map형태로 저장된 게시판의 내용을  전달한 후 등록의 성공여부를 boolean형태로 반환한다.*/
		/* 성공여부를 저장할 result와, parameter에 저장된 첨부파일 정보를 저장할 files를 선언 후 초기화한다.*/
		boolean result = false;
		List<FileDTO> files = parameter.getFile();

		/* 게시글 등록을 성공하면 첨부파일을 등록한다. */
		if(mapper.registMeetingBoard(parameter) > 0) {
			result = true;
		}
		/* 첨부파일을 담는 files가 null이 아니라면 첨부파일을 등록한다.*/
		if(result && files != null) {
			/* 첨부파일 등록 성공 갯수를 저장할 count 변수를 선언 후 0으로 초기화한다. */
			int count = 0;
			for(int i = 0; i < files.size(); i++) {
				/* 각각의 첨부파일에 상위 게시글번호를 전달한 후 첨부파일을 등록한다. */
				files.get(i).setRefBoardNo(parameter.getNo());
				count += mapper.registMeetingFile(files.get(i));
			}
			/* 첨부파일의 갯수와, 첨부파일 등록 성공 갯수가 다를 시 등록에 실패한다. */
			if(count != files.size()) {
				result = false;
			} 
		}
		
		return result;
	}

	/**
	 * removeMeetingBoard : 게시글을 삭제한다.
	 * @param meetingNo : 삭제할 게시글의 번호를 전달받는다.
	 * @return boolean : 게시글 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean removeMeetingBoard(int meetingNo) {

		return mapper.removeMeetingBoard(meetingNo);
	}

	/**
	 * modifyMeetingBoard : 게시글을 수정한다.
	 * @param meeting : 수정할 게시글의 내용을 전달받는다.
	 * @return boolean : 수정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean modifyMeetingBoard(Map<String, String> meeting) {
		
		return mapper.modifyMeetingBoard(meeting);
	}

	/**
	 * findOneMeetingBoard : 하나의 게시글을 조회한다.
	 * @param meetingNo : 조회할 게시글 번호를 전달받는다.
	 * @return MeetingDTO : 조회한 게시글 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public MeetingDTO findOneMeetingBoard(int meetingNo) {

		return mapper.findOneMeetingBoard(meetingNo);
	}

	/**
	 * findFile : 하나의 첨부파일의 상세정보를 조회한다.
	 * @param no : 조회할 첨부파일의 번호를 전달받는다.
	 * @return FileDTO : 첨부파일의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public Map<String, Object> findFile(int no) {
		Map<String, Object> fileInfo = new HashMap<String, Object>();

		FileDTO file = mapper.fineFile(no);
		
		fileInfo.put("filePath", file.getFilePath());
		fileInfo.put("fileOriginName", file.getFileOriginName());
		fileInfo.put("fileRandomName", file.getFileRandomName());
		
		return fileInfo;
	}

	/**
	 * removeMeetingBoardFile : 첨부파일을 삭제한다.
	 * @param 삭제하려는 파일번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean removeMeetingBoardFile(int fileNo) {
		
		return mapper.removeMeetingBoardFile(fileNo);
	}

}