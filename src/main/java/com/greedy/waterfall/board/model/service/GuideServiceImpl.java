package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.dto.GuideFileDTO;
import com.greedy.waterfall.board.model.mapper.GuideMapper;
import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRegistException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : GuideServiceImpl
 * Comment : 가이드 게시판의 게시물 전체목록 조회, 게시글 상세조회, 수정, 삭제, 등록 기능 서비스
 * 
 * History
 * 2022. 2. 21.  (박성준)
 * </pre>
 * @version 1.1
 * @author 박성준
 */
@Service
public class GuideServiceImpl implements GuideService {

	private final GuideMapper mapper;
	
	@Autowired
	public GuideServiceImpl(GuideMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * selectAllGuideList : 가이드 게시판의 게시물 전체목록 조회 결과를 반환하는 메소드
	 * @param selectCriteria : 검색 조건을 조회할 DTO 매개변수
	 * @return guideList : 검색조건에 해당하는 게시글 또는 전체 게시글 조회한 결과 반환
	 * 
	 * @author 박성준
	 */
	@Override
	public List<GuideDTO> selectAllGuideList(SelectCriteria selectCriteria) {
		List<GuideDTO> guideList = mapper.selectAllGuideList(selectCriteria);
		
		return guideList;
	}

	/**
	 * selectTotalCount : 해당 게시판의 전체 게시글 또는 검색 조건에 해당하는 게시글의 수를 조회한 결과를 반환하는 서비스 메소드
	 * @param searchMap : 검색 조건을 저장한 변수
	 * @return result : 게시글의 수
	 * 
	 * @author 박성준
	 */
	@Override
	public int selectTotalCount(Map<Object, Object> searchMap) {
		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}

	/**
	 * registGuide : 게시판에 게시글을 등록한 서비스 메소드
	 * @param guide : 작성한 게시글을 DTO 타입으로 저장한 매개변수
	 * 
	 * @author 박성준
	 */
	@Override
	public void registGuide(GuideDTO guide) throws GuideRegistException {
		
		int result = mapper.insertGuide(guide);
		
		GuideFileDTO guideFileDTO = guide.getFile();
		
		if(guideFileDTO != null) {
			guideFileDTO.setRefBoardNo(guide.getNo());
			mapper.insertGuideFile(guideFileDTO);
		}
		
		if(!(result > 0)) {
			throw new GuideRegistException("가이드 게시글 등록에 실패하셨습니다.");
		}
		
	}

	/**
	 * removeGuide : 게시판에 게시글을 삭제한 서비스 메소드
	 * @param no : 삭제할 게시판 번호
	 * 
	 * @author 박성준
	 */
	@Override
	public void removeGuide(int no, int loginMemberNo) throws GuideRemoveException {

		int result = mapper.deleteGuide(no);
		
		if(!(result > 0)) {
			throw new GuideRemoveException("가이드 게시글 삭제에 실패하셨습니다.");
		}
	}

	/**
	 * modifyGuide : 메소드 설명 작성 부분(수정해야함)
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 박성준
	 */
	@Override
	public void modifyGuide(GuideDTO guide) throws GuideModifyException {
		
		int result = mapper.updateGuide(guide);
		
		GuideFileDTO file = guide.getFile();
		System.out.println("GuideServiceImpl의 updateGuideFile의 file은 " + file);
		
		if(mapper.updateGuide(guide) > 0) {
			
			if(file != null) {
				file.setRefBoardNo(guide.getNo());
				mapper.insertGuideFile(file);
			}
		}
		
		if(!(result > 0)) {
			throw new GuideModifyException("가이드 게시글 수정에 실패하셨습니다.");
		}
		
	}

	/**
	 * selectGuideFileDetail : 첨부파일이 등록된 게시글 상세 조회 결과를 반환하는 서비스 메소드
	 * @param no : 상세 조회할 게시글 번호
	 * @return guideDetail : 상세 조회한 게시글의 정보
	 * 
	 * @author 박성준
	 */
	@Override
	public GuideDTO selectGuideFileDetail(int no) {
		/* 게시글 조회 수 */
		int result = mapper.incrementGuideCount(no);
		
		GuideDTO guideFileDetail = new GuideDTO();
		
		/* 조회수가 0보다 크면 게시글 상세정보를 불러옴 */
		if(result > 0) {
	         
	        guideFileDetail = mapper.selectGuideDetailPlusFile(no);

	      }
		
		return guideFileDetail;
	}

	/**
	 * findFile : 파일 다운로드 하기 위해서 해당 파일을 찾는 메소드
	 * @param no : 파일번호
	 * @return mapper.findFile(no) : 해당 파일에 대한 정보를 반환함
	 * 
	 * @author 박성준
	 */
	@Override
	public GuideFileDTO findFile(int no) {
		
		return mapper.findFile(no);
	}

	/**
	 * removeGuideFile : 게시글 첨부파일  삭제 메소드
	 * @param fileNumber : 파일번호
 	 * @return guideFileDTO : 조회한 파일을 삭제하기 위해서 파일정보를 반환
	 * 
	 * @author 박성준
	 */
	@Override
	public GuideFileDTO removeGuideFile(int fileNumber) {
		
		GuideFileDTO guideFileDTO = mapper.findFile(fileNumber);
		
		mapper.deleteGuideFile(fileNumber);
		
		return guideFileDTO;
	}

	/**
	 * searchGuideFile : 첨부파일을 조회하는 메소드
	 * @param guideNo : 해당 게시글 번호
	 * @return result : 첨부파일 번호, 첨부파일이 없을 경우 null값 반환
	 * 
	 * @author 박성준
	 */
	@Override
	public Object searchGuideFile(int guideNo) {
		
		/* 게시물 당 1개의 첨부파일만 지원하기 때문에 기존에 등록된 첨부파일이
		 * 있는지 확인이 필요하다 */
		Object result = mapper.searchGuideFile(guideNo);
		
		return result;
	}

}
