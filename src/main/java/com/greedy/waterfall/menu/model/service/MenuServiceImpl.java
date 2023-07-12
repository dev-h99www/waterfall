package com.greedy.waterfall.menu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.History.ProjectHistory;
import com.greedy.waterfall.common.paging.Paging;
import com.greedy.waterfall.common.paging.PagingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.menu.model.dto.MainInfoDTO;
import com.greedy.waterfall.menu.model.mapper.MenuMapper;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;

/**
 * <pre>
 * Class : MenuServiceImpl
 * Comment : 로그인 후 메인화면에 필요한 정보를 조회한다.
 * 
 * History
 * 2022. 3. 4.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Service
public class MenuServiceImpl implements MenuService{
	private static final String ROLE_ADMIN = "!";
	private final MenuMapper mapper;
	private final Paging paging;
	
	@Autowired
	public MenuServiceImpl(MenuMapper mapper, Paging paging) {
		this.mapper = mapper;
		this.paging = paging;
	}
	
	/**
	 * findMainProjectList : 회원번호를 전달받아 관리중인 프로젝트 목록을 반환한다.
	 * @param 회원정보를 전달받는다.
	 * @return 회원이 관리중인 프로젝트목록을 반환한다.
	 * 
	 * @author 홍성원
	 */
	public Map<String, Object> findMainProjectList(MainInfoDTO mainInfo) {
		
		Map<String, String> searchMap = new HashMap<String, String>();
		MemberDTO loginMember = mainInfo.getLoginMember();
		SelectCriteria subselectCriteria = null;
		searchMap.put("currentPage", mainInfo.getCurrentPage());
		searchMap.put("subcurrentPage", mainInfo.getSubcurrentPage());
		Map<String, Object> findProjectResult = new HashMap<String, Object>();
		/* 한페이지에 버튼의 갯수를 5개로 설정하고, 출력될 프로젝트의 수를 5개로 설정한다. */
		PagingDTO pageSetting = PagingDTO.builder().buttonAmount(5).limit(5).build();
		/* 페이징처리를 위해 전체 프로젝트의 갯수를 조회한 뒤 searchMap에 저장한다. */
		searchMap.put("totalCount", Integer.toString(mapper.findProjectCount(loginMember)));
		/* 페이지에대한 정보가 담긴 searchMap과  출력 설정정보가 담겨져있는 pageSetting, 로그인회원의 정보가 담긴 정보를 전달해, 
		 * 검색조건이 담겨져있는 SelectCriteria변수를 반환받아, 프로젝트목록을 조회한다. */
		SelectCriteria selectCriteria = paging.setPagingCondition(searchMap, pageSetting, loginMember);
		List<ProjectDTO> projectList = mapper.findMainProjectList(selectCriteria);
		
		/* 관리자가 아닌경우 참여중인 프로젝트 목록을 반환한다. */
		if(!"ROLE_ADMIN".equals(loginMember.getRole())){
			searchMap.put("subtotalCount", Integer.toString(mapper.findJoinProjectCount(loginMember)));
			subselectCriteria = paging.setSubPagingCondition(searchMap, pageSetting, loginMember);
			List<ProjectDTO> joinProjectList = mapper.findJoinProjectList(selectCriteria);
			findProjectResult.put("joinProjectList", joinProjectList);
			findProjectResult.put("subselectCriteria", subselectCriteria);
			
		}
		findProjectResult.put("projectList", projectList);
		findProjectResult.put("selectCriteria", selectCriteria);
		
		
		return findProjectResult;
	}

	/**
	 * findAdminPageInfo : 관리자의 메인화면에 출력할 정보들을 조회한다.
	 * @param 관리자가 선택한 프로젝트번호를 전달받는다.
	 * @return 해당 프로젝트로 출력할 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public Map<String, Object> findAdminPageInfo(int projectNo) {
		/* 프로젝트번호에 해당하는 정보를 반환한다. */
		Map<String, Object> pageInfo = new HashMap<>();
		ProjectDTO projectInfo = mapper.findProjectInfo(projectNo);
		List<ProjectHistoryDTO> projectHistory = mapper.findProjectHistory(projectNo);
		pageInfo.put("projectInfo", projectInfo);
		pageInfo.put("projectHistory", projectHistory);
		
		return pageInfo;
	}

	/**
	 * findJoinProjectInfo : 개발자의 메인화면에 참여중인 프로젝트의 상세정보를 조회한다.
	 * @param searchMap: 개발자의 회원번호와 조회하려는 프로젝트의 번호를 담고있는 Map을 전달받는다.
	 * @return 전달받은 정보로 참여중인 프로젝트의 상세정보와 관리중인 업무 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public ProjectDTO findJoinProjectInfo(Map<String, Integer> searchMap) {
		
		return mapper.findJoinProjectDetail(searchMap);
	}

}