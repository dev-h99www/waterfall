package com.greedy.waterfall.project.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;

/**
 * <pre>
 * Class : ProjectService
 * Comment : 프로젝트를 조회, 생성, 수정, 삭제하는 기능을 담당한다.
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
public interface ProjectService {

	/**
	 * findMyProject : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	MyProjectDTO findMyProject(Map<String, String> searchMap, MemberDTO member);

	/**
	 * findRegistForm : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	Map<String, Object> findRegistForm();

	/**
	 * findTeam : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	List<TeamDTO> findTeam(String deptCode);

	/**
	 * findTeamMember : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	List<MemberDTO> findTeamMember(String teamCode);

	/**
	 * registProject : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	boolean registProject(RegistProjectDTO newProject);

	/**
	 * findOneProjectInfo : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	/**
	 * findOneProjectInfo : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	RegistProjectDTO findOneProjectInfo(int projectNo);

	/**
	 * modifyProject : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	boolean modifyProject(RegistProjectDTO project);
	
	/**
	 * removeProject : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	boolean removeProject(Map<String, Integer> removeInfo);

	/**
	 * restoreProject : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	boolean restoreProject(Map<String, Integer> restoreInfo);
	
	/**
	 * findProjectMainInfo : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	Map<String, Object> findProjectMainInfo(int projectNo);

	/**
	 * findBoardInfo : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	BoardDTO findBoardInfo(int boardNo);
}