package com.greedy.waterfall.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : StubMeetingServiceImpl
 * Comment : MeetingController를 단위테스트하기위한 Stub 클래스
 * 
 * History
 * 2022. 3. 11.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Component("stubMeetingService")
@Primary
public class StubMeetingServiceImpl implements MeetingService{

	@Override
	public Map<String, Object> findMeetingBoardList(Map<String, String> searchMap) {
		Map<String, Object> findResult = new HashMap<String, Object>();
		findResult.put("meetingList", new ArrayList<MeetingDTO>());
		findResult.put("selectCriteria", new SelectCriteria());

		return findResult;
	}

	@Override
	public MeetingDTO findMeetingBoardDetail(int meetingNo) {
		// TODO Auto-generated method stub
		return new MeetingDTO();
	}

	@Override
	public int findMeetingTotalCount(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean registMeetingBoard(MeetingDTO parameter) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean removeMeetingBoard(int meetingNo) {
		
		return meetingNo > 0 ? true: false;
	}

	@Override
	public boolean modifyMeetingBoard(Map<String, String> meeting) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MeetingDTO findOneMeetingBoard(int meetingNo) {
		// TODO Auto-generated method stub
		return new MeetingDTO();
	}

	@Override
	public Map<String, Object> findFile(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeMeetingBoardFile(int fileNo) {
		// TODO Auto-generated method stub 
		return false;
	}

}
