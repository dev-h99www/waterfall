package com.greedy.waterfall.board.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.EduDTO;
import com.greedy.waterfall.board.model.dto.EduFileDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : EduMapper
 * Comment : 교육게시판 DAO 인터페이스. EduServiceImpl과 EduMapper.xml을 연결한다. 
 * 	 총 11개의 메소드로 구성 되어있다.
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
public interface EduMapper {
	
	/**
	 * selectTotalCount : 프로젝트내 전체 게시글 갯수를 조회한다.
	 * @param 검색조건과 내용을 담고있는 map을 전달받는다
	 * @return 조건에 맞는 게시글 갯수를 반환한다.
	 * 
	 * @author 김영광
	 */
	int selectTotalCount(Map<Object, Object> searchMap);
	
	/**
	 * selectEduList : 현재 페이지의 게시글 목록을 조회한다.
	 * @param 페이징 처리, 검색값을 담고있는 selectCriteria변수를 전달 받는다
	 * @return 검색여부, 현재 페이지수에 맞는 게시글을 List형태로 반환한다. 
	 * 
	 * @author 김영광
	 */
	List<EduDTO> selectEduList(SelectCriteria selectCriteria);
	
	/**
	 * insertEduBoard : 교육 게시글을 등록한다.
	 * @param 게시글의 정보 전달
	 * @return 성공여부를 반환 
	 * 
	 * @author 김영광
	 */
	int insertEduBoard(EduDTO eduBoard);
	
	/**
	 * incrementEduCount : 조회수를 1 증가시킨다.
	 * @param 조회수를 증가시킬 게시글의 번호 정보 전달
	 * @return 조회수 증가 성공여부를 반환
	 * 
	 * @author 김영광
	 */
	int incrementEduCount(int no);
	
	/**
	 * selectEduDetail : 선택한 게시글의 정보 요청
	 * @param 선택한 게시글 번호 정보 전달
	 * @return 게시글 정보 반환
	 * 
	 * @author 김영광
	 */
	EduDTO selectEduDetail(int no);
	
	/**
	 * deleteEduBoard : 선택한 게시글 삭제
	 * @param 선택한 게시글 번호 정보 전달
	 * @return 게시글 삭제 성공 여부 반환
	 * 
	 * @author 김영광
	 */
	int deleteEduBoard(int no);
	
	/**
	 * updateEduBoard : 수정한 게시글 내용 업데이트를 한다.
	 * @param 수정할 게시글 정보 전달
	 * @return 게시글 수정 성공 여부 반환
	 * 
	 * @author 김영광
	 */
	int updateEduBoard(EduDTO edu);
	
	/**
	 * selectEduDetailFile : 선택한 게시글 전체정보와, 파일정보를 불러온다.
	 * @param 해당 게시글의 번호
	 * @return 파일정보를 담아 반환
	 * 
	 * @author 김영광
	 */
	EduDTO selectEduDetailFile(int no);

	/**
	 * findFile : 해당 게시글의 파일을 조회한다.
	 * @param 조회할 파일의 번호 정보 전달
	 * @return 해당 번호의 첨부파일 상세정보를 반환한다.
	 * 
	 * @author 김영광
	 */
	EduFileDTO findFile(int no);
	
	/**
	 * deleteEduFile : 교육 게시글의 첨부파일을 삭제한다.
	 * @param 삭제할 첨부파일의 파일번호를 전달받는다.
	 * @return 삭제 성공여부를 반환한다.
	 * 
	 * @author 김영광
	 */
	int deleteEduFile(int fileNumber);
	
	/**
	 * insertEduFile : 게시글의 첨부파일을 등록한다.
	 * @param 등록할 첨부파일의 정보 전달
	 * @return 첨부파일의 등록 성공여부를 반환한다.
	 * 
	 * @author 김영광
	 */
	void insertEduFile(EduFileDTO eduFileDTO);


	

}
