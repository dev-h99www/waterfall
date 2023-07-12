package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.dto.GuideFileDTO;
import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRegistException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : GuideService
 * Comment : CRUD 트랜젝션을 제어하는 서비스의 인터페이스
 * 
 * History
 * 2022. 2. 21.  (박성준)
 * </pre>
 * @version 1.1
 * @author 박성준
 */
public interface GuideService {

	List<GuideDTO> selectAllGuideList(SelectCriteria selectCriteria);

	int selectTotalCount(Map<Object, Object> searchMap);

	void registGuide(GuideDTO guide) throws GuideRegistException;

	void removeGuide(int no, int loginMemberNo) throws GuideRemoveException;

	void modifyGuide(GuideDTO guide) throws GuideModifyException;

	GuideDTO selectGuideFileDetail(int no);

	GuideFileDTO findFile(int no);

	GuideFileDTO removeGuideFile(int fileNumber);

	Object searchGuideFile(int guideNo);

}
