package com.greedy.waterfall.menu.model.service;

import java.util.Map;

import com.greedy.waterfall.menu.model.dto.MainInfoDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;

/**
 * <pre>
 * Class : MenuService
 * Comment : 로그인 후 메인화면에 필요한 정보를 조회하는 메소드를 선언한다.
 * 
 * History
 * 2022. 3. 13.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
public interface MenuService {
	
	/**
	 * findMainProjectList : 회원번호를 전달받아 관리중인 프로젝트 목록을 반환한다.
	 * @param 회원정보를 전달받는다.
	 * @return 회원이 관리중인 프로젝트목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	public Map<String, Object> findMainProjectList(MainInfoDTO mainInfo);

	/**
	 * findAdminPageInfo : 관리자의 메인화면에 출력할 정보들을 조회한다.
	 * @param 관리자가 선택한 프로젝트번호를 전달받는다.
	 * @return 해당 프로젝트로 출력할 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	public Map<String, Object> findAdminPageInfo(int projectNo);

	/**
	 * findJoinProjectInfo : 개발자의 메인화면에 참여중인 프로젝트의 상세정보를 조회한다.
	 * @param searchMap: 개발자의 회원번호와 조회하려는 프로젝트의 번호를 담고있는 Map을 전달받는다.
	 * @return 전달받은 정보로 참여중인 프로젝트의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	public ProjectDTO findJoinProjectInfo(Map<String, Integer> searchMap);
}
