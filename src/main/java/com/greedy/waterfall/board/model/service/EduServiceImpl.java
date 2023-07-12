package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.EduDTO;
import com.greedy.waterfall.board.model.dto.EduFileDTO;
import com.greedy.waterfall.board.model.mapper.EduMapper;
import com.greedy.waterfall.common.exception.board.BoardModifyException;
import com.greedy.waterfall.common.exception.board.BoardRegistException;
import com.greedy.waterfall.common.exception.board.BoardRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : EduServiceImpl
 * Comment : 교육 게시판의 게시물 전체조회, 상세조회, 수정, 등록, 삭제 기능
 * 
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
@Service
public class EduServiceImpl implements EduService {
	
	private final EduMapper mapper;
	  
	@Autowired 
	public EduServiceImpl(EduMapper mapper) {
	     this.mapper = mapper;
	}
	  
   /**
  	* selectTotalCount : 전체 게시글의 검색조건에 해당하는 게시글 수를 조회해서 반환해주는 메소드 
  	* @param searchMap : 검색 조건을 정보 저장 전달
  	* @return result : 검색조건에 해당하는 게시글 수 반환
  	* 
  	* @author 김영광
  	*/
	  @Override 
  	public int selectTotalCount(Map<Object, Object> searchMap) { 
	 
		  int result = mapper.selectTotalCount(searchMap);
    
		  return result; 
  	}
 	 
   /**
    * selectEduList : 전체 게시글 목록 조회 결과를 반환하는 메소드 
    * @param selectCriteria : 검색 조건을 조회 정보
    * @return eduList : 해당 결과를 담아 리스트로 반환
    * 
    * @author 김영광
    */  
	@Override
	public List<EduDTO> selectEduList(SelectCriteria selectCriteria) {
		List<EduDTO> eduList = mapper.selectEduList(selectCriteria);
		
		return eduList;
	}
	
   /**
    * registEdu : 게시글 등록을 하는 메소드 
    * @param eduBoard : 첨부파일 및 게시글 내용 정보
    * @return result : 결과 성공여부 반환
    * 
    * @author 김영광
    */
	@Override
	public void registEdu(EduDTO eduBoard) throws BoardRegistException {
		int result = mapper.insertEduBoard(eduBoard);
		
		EduFileDTO eduFileDTO = eduBoard.getFile();
		
		if(eduFileDTO != null) {
			eduFileDTO.setRefBoardNo(eduBoard.getNo());
			mapper.insertEduFile(eduFileDTO);
		}
		
		if(!(result > 0 )) {
			throw new BoardRegistException("게시글 등록이 실패");
		}
		
	}
	
   /**
    * findEduDetail : 게시글 상세조회 메소드
    * @param no : 조회할 게시글 번호
    * @return eduDetail : 조회한 게시글 정보 반환
    * 
    * @author 김영광
    */
    @Override
    public EduDTO findEduDetail(int no) {
    	
        int result = mapper.incrementEduCount(no);
       
        EduDTO eduDetail = new EduDTO();
       
       	if(result > 0) {
       		eduDetail = mapper.selectEduDetail(no);
       	}   
       
        return eduDetail;
   }

   /**
 	* removeEduBoard : 게시글 삭제 메소드
 	* @param no : 삭제할 게시글 번호
 	* @return result : 성공 여부 반환
 	* 
 	* @author 김영광
 	*/
	@Override
	public void removeEduBoard(int no) throws BoardRemoveException {
		
		int result = mapper.deleteEduBoard(no);
		
		if(!(result > 0)) {
			throw new BoardRemoveException("게시판 삭제에 실패하셨습니다.");
		}
		
	}

   /**
 	* removeEduBoard : 게시글 수정 메소드
 	* @param no : 수정할 게시글 정보 (번호,내용,작성자)
 	* @return result : 성공 여부 반환
 	* 
 	* @author 김영광
 	*/
	@Override
	public void modifyEduBoard(EduDTO edu) throws BoardModifyException {
		int result = mapper.updateEduBoard(edu);
		
		if(!(result > 0)) {
			throw new BoardModifyException("공지사항 수정에 실패하셨습니다.");
		}
		
	}

   /**
 	* eduFileDetail : 해당 게시글 파일 조회 메소드
 	* @param no : 조회할 게시글 번호
 	* @return eduFileDetail : 해당 게시글 첨부파일 조회
 	* 
 	* @author 김영광
 	*/
	@Override
	public EduDTO findEduFileDetail(int no) {

		EduDTO eduFileDetail = new EduDTO();

		eduFileDetail = mapper.selectEduDetailFile(no);

		return eduFileDetail;
	}
	
   /**
 	* eduFileDetail : 해당 게시글 파일정보 반환 메소드
 	* @param no : 조회할 게시글 정보
 	* @return mapper.findFile(no) : 해당 파일의 정보 갖고오기
 	* 
 	* @author 김영광
 	*/
	@Override
	public EduFileDTO findFile(int no) {

		return mapper.findFile(no);
	}
	
   /**
 	* eduFileDetail : 해당 게시글 파일정보 삭제 메소드
 	* @param fileNumber : 파일정보가 전달 변수
 	* @return 삭제된 널값 반환
 	* 
 	* @author 김영광
 	*/
	@Override
	public EduFileDTO removeEduFile(int fileNumber) {
		
		EduFileDTO eduFileDTO = mapper.findFile(fileNumber);
		
		int result = mapper.deleteEduFile(fileNumber);
		
		return eduFileDTO;
	}

	
	
	

}
