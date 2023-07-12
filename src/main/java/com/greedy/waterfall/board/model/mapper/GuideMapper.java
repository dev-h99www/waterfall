package com.greedy.waterfall.board.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.dto.GuideFileDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;


/**
 * <pre>
 * Class : GuideMapper
 * Comment : MAPPER.xml의 쿼리의 key와 bind하기 위한 Mapper 인터페이스
 * 
 * History
 * 2022. 2. 19.  (박성준)
 * @version 1
 * @author 박성준
 */
public interface GuideMapper {

	List<GuideDTO> selectAllGuideList(SelectCriteria selectCriteria);

	int selectTotalCount(Map<Object, Object> searchMap);

	int insertGuide(GuideDTO guide);

	int deleteGuide(int no);

	int updateGuide(GuideDTO guide);

	int incrementGuideCount(int no);

	int insertGuideFile(GuideFileDTO guideFileDTO);

	GuideDTO selectGuideDetailPlusFile(int no);

	GuideFileDTO findFile(int no);

	int deleteGuideFile(int fileNumber);

	Object searchGuideFile(int guideNo);

}
