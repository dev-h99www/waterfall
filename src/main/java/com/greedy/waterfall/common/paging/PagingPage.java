package com.greedy.waterfall.common.paging;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.greedy.waterfall.member.model.dto.MemberDTO;

@Component
public class PagingPage implements Paging {

	@Override
	public SelectCriteria setPagingCondition(Map<String, String> searchMap) {
		
		int pageNo = 1;														//입력받은 현재페이지가 없으면 1페이지를 보여준다.
		int limit = 10;														//한 페이지에 출력될 게시물의 수로 초기화한다.
		int buttonAmount = 5;												//한 페이지에 출력될 버튼의 갯수로 초기화한다.
		SelectCriteria selectCriteria = null;								//검색 조건과, 페이징처리를 할 클래스변수를 선언한다.
		/* request에서 전달받은 현재 페이지를 currentPage에 저장한다. */
		String currentPage = searchMap.get("currentPage");
		String searchCondition = searchMap.get("searchCondition");	
		String searchValue = searchMap.get("searchValue");			
		int projectNo = Integer.parseInt(searchMap.get("projectNo"));			
		int totalCount = Integer.parseInt(searchMap.get("totalCount"));			
		
		/* 현재페이지를 전달받으면 전달받은 값을 대입한다. */
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		/* 검색여부에 따른 페이징처리를 한다.*/
		if(searchCondition != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, projectNo, null);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, projectNo);
		}
		
		return selectCriteria;
	}

	@Override
	public SelectCriteria setPagingCondition(Map<String, String> searchMap, PagingDTO pageSetting) {

		SelectCriteria selectCriteria = null;								//검색 조건과, 페이징처리를 할 클래스변수를 선언한다.
		int pageNo = 1;														//입력받은 현재페이지가 없으면 1페이지를 보여준다.
		int limit =  pageSetting.getLimit();								//한 페이지에 출력될 게시물의 수로 초기화한다.
		int buttonAmount = pageSetting.getButtonAmount();					//한 페이지에 출력될 버튼의 갯수로 초기화한다.
		MemberDTO member = MemberDTO.builder().no(pageSetting.getMemberNo()).build();
		int projectNo = 0;
		int totalCount = Integer.parseInt(searchMap.get("totalCount"));			
		String currentPage = searchMap.get("currentPage");
		String searchCondition = searchMap.get("searchCondition");	
		String searchValue = searchMap.get("searchValue");			
		
		if(searchMap.get("projectNo") != null) {
			projectNo = Integer.parseInt(searchMap.get("projectNo"));
		}
		
		/* 현재페이지를 전달받으면 전달받은 값을 대입한다. */
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		return Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, projectNo, member);
	}

	@Override
	public SelectCriteria setSubPagingCondition(Map<String, String> searchMap, PagingDTO pagingSetting) {
		
		SelectCriteria selectCriteria = null;								//검색 조건과, 페이징처리를 할 클래스변수를 선언한다.
		int pageNo = 1;														//입력받은 현재페이지가 없으면 1페이지를 보여준다.
		int limit =  pagingSetting.getLimit();								//한 페이지에 출력될 게시물의 수로 초기화한다.
		int buttonAmount = pagingSetting.getButtonAmount();					//한 페이지에 출력될 버튼의 갯수로 초기화한다.
		MemberDTO member = MemberDTO.builder().no(pagingSetting.getMemberNo()).build();
		int projectNo = 0;
		
		int totalCount = Integer.parseInt(searchMap.get("subtotalCount"));			
		String currentPage = searchMap.get("subcurrentPage");
		String searchCondition = searchMap.get("subsearchCondition");	
		String searchValue = searchMap.get("subsearchValue");			
		
		if(searchMap.get("projectNo") != null) {
			projectNo = Integer.parseInt(searchMap.get("projectNo"));
		}
		
		/* 현재페이지를 전달받으면 전달받은 값을 대입한다. */
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		/* 검색여부에 따른 페이징처리를 한다.*/
		return Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, projectNo, member);
	}

	@Override
	public SelectCriteria setPagingCondition(Map<String, String> searchMap, PagingDTO pageSetting,
			MemberDTO loginMember) {

		SelectCriteria selectCriteria = null;								//검색 조건과, 페이징처리를 할 클래스변수를 선언한다.
		int pageNo = 1;														//입력받은 현재페이지가 없으면 1페이지를 보여준다.
		int limit =  pageSetting.getLimit();								//한 페이지에 출력될 게시물의 수로 초기화한다.
		int buttonAmount = pageSetting.getButtonAmount();					//한 페이지에 출력될 버튼의 갯수로 초기화한다.
		int projectNo = pageSetting.getMemberNo();
		
		int totalCount = Integer.parseInt(searchMap.get("totalCount"));			
		String currentPage = searchMap.get("currentPage");
		String searchCondition = searchMap.get("searchCondition");	
		String searchValue = searchMap.get("searchValue");			
		
		if(searchMap.get("projectNo") != null) {
			projectNo = Integer.parseInt(searchMap.get("projectNo"));
		}
		
		/* 현재페이지를 전달받으면 전달받은 값을 대입한다. */
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		/* 검색여부에 따른 페이징처리를 한다.*/
		if(searchCondition != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, projectNo, null);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, projectNo);
		}
		
		selectCriteria.setMember(loginMember);
		
		return selectCriteria;
	}

	@Override
	public SelectCriteria setSubPagingCondition(Map<String, String> searchMap, PagingDTO pageSetting,
			MemberDTO loginMember) {
		SelectCriteria selectCriteria = null;								//검색 조건과, 페이징처리를 할 클래스변수를 선언한다.
		int pageNo = 1;														//입력받은 현재페이지가 없으면 1페이지를 보여준다.
		int limit =  pageSetting.getLimit();								//한 페이지에 출력될 게시물의 수로 초기화한다.
		int buttonAmount = pageSetting.getButtonAmount();					//한 페이지에 출력될 버튼의 갯수로 초기화한다.
		int projectNo = pageSetting.getMemberNo();
		
		int totalCount = Integer.parseInt(searchMap.get("subtotalCount"));			
		String currentPage = searchMap.get("subcurrentPage");
		String searchCondition = searchMap.get("subsearchCondition");	
		String searchValue = searchMap.get("subsearchValue");			
		
		if(searchMap.get("projectNo") != null) {
			projectNo = Integer.parseInt(searchMap.get("projectNo"));
		}
		
		/* 현재페이지를 전달받으면 전달받은 값을 대입한다. */
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		/* 검색여부에 따른 페이징처리를 한다.*/
		if(searchCondition != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, projectNo, null);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, projectNo);
		}
		
		selectCriteria.setMember(loginMember);
		return selectCriteria;
	}

}
