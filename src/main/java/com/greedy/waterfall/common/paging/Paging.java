package com.greedy.waterfall.common.paging;

import java.util.Map;

import com.greedy.waterfall.member.model.dto.MemberDTO;

public interface Paging {

	SelectCriteria setPagingCondition(Map<String, String> searchMap);
	
	SelectCriteria setPagingCondition(Map<String, String> searchMap, PagingDTO pageSetting);

	SelectCriteria setSubPagingCondition(Map<String, String> searchMap, PagingDTO pagingSetting);

	SelectCriteria setPagingCondition(Map<String, String> searchMap, PagingDTO pageSetting, MemberDTO loginMember);
	
	SelectCriteria setSubPagingCondition(Map<String, String> searchMap, PagingDTO pageSetting, MemberDTO loginMember);

}
