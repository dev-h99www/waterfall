package com.greedy.waterfall.board.model.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.isNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : MeetingMapperTest
 * Comment : MeetingMapper의 단위테스트를 작성한 클래스
 * 
 * History
 * 2022. 3. 10.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class MeetingMapperTest {

	@Autowired MeetingMapper mapper;
	private FileDTO file;
	Map<String, String> searchMap;
	@Before
	public void setUp() {
		this.file = new FileDTO().builder().fileOriginName("test").fileRandomName("randontestname").filePath("testPath").refBoardNo(1003).build();
		searchMap = new HashMap<>();
		searchMap.put("projectNo", "54");

		
	}
	
	/**
	 * increseCount : 게시글을 조회할 때 조회수가 제대로 증가하는지 테스트한다.
	 * 
	 * @author 홍성원
	 */
	@Test
	@Transactional
	public void increseCount_test() {
		MeetingDTO firstResult = mapper.findOneMeetingBoard(989);
		
		mapper.increseCount(firstResult.getNo());
		System.out.println(firstResult);
		MeetingDTO secondResult = mapper.findOneMeetingBoard(firstResult.getNo());
		System.out.println(secondResult);
		assertThat(secondResult.getBoardCount(), is(equalTo(firstResult.getBoardCount() + 1)));
		
	}
	
	/**
	 * registMeetingBoard : 게시글이 정상적으로 등록되는지 테스트한다.
	 * 프로젝트의 게시글 갯수가, 게시글을 등록했을때 1 증가하는지 확인한다.
	 * 게시글 등록 정보를 전달해 등록했을때, 조회된 값이 등록정보와 같은지 확인한다.
	 * 
	 * @author 홍성원
	 */
	@Test
	@Transactional
	public void registMeetingBoard_test() {
		MeetingDTO registInfo = new MeetingDTO().builder().title("title").content("content").projectNo(54).memberNo(1).build();
		int boardCountFirst = mapper.findMeetingTotalCount(searchMap);
		
		mapper.registMeetingBoard(registInfo);				
		
		MeetingDTO registResult = mapper.findOneMeetingBoard(registInfo.getNo());
		
		int boardCountSecond = mapper.findMeetingTotalCount(searchMap);
		
		assertThat(boardCountSecond, is(equalTo(boardCountFirst + 1)));
		
		assertThat(registInfo.getTitle(), is(equalTo(registResult.getTitle())));
		assertThat(registInfo.getContent(), is(equalTo(registResult.getContent())));
				
		
	}
	
	@Test
	@Transactional
	public void registMeetingFile() {
		int firstCount = (mapper.findMeetingBoardDetail(file.getRefBoardNo())).getFile().size();
		mapper.registMeetingFile(file);
		
		MeetingDTO result = mapper.findMeetingBoardDetail(file.getRefBoardNo());
		int secondCount = result.getFile().size();
		
		assertThat(secondCount, is(equalTo(firstCount + 1)));
	}
	
	@Test
	@Transactional
	public void findMeetingList() {
		SelectCriteria selectCriteria = new SelectCriteria().builder().startRow(1).endRow(100).projectNo(54).build();
		List<MeetingDTO> boardList = mapper.findMeetingList(selectCriteria);
		
		assertThat(boardList.size(), is(4));
	}
	
	/**
	 * findMeetingBoardDetail : 하나의 게시글 번호로 게시글의 정보를 조회한다.
	 * DB에 저장된 회의록 게시글번호를 전달해 조회된 게시글 정보가 실제 DB에 저장된 내용과 같은지 검증한다.
	 * 
	 * @author 홍성원
	 */
	@Test
	public void findMeetingBoardDetail() {
		MeetingDTO result = mapper.findOneMeetingBoard(1003);
		assertThat(result.getTitle(), is(equalTo("주간회의")));
		assertThat(result.getContent(), is(equalTo("주간회의내용")));
		
	}
	
	/**
	 * findMeetingTotalCount_test : 검색조건에 맞는 게시글의 갯수를 정상적으로 반환하는지 테스트한다.
	 * 하나의 프로젝트에 작성된 회의록 게시글 갯수가 잘 조회되는지 확인한다.
	 * 검색조건을 입력했을 때 게시글 갯수가 잘 조회되는지 확인한다.
	 * 
	 * @author 홍성원
	 */
	@Test
	public void findMeetingTotalCount_test() {
		int firstResult = mapper.findMeetingTotalCount(searchMap);
		
		assertThat(firstResult, is(equalTo(4)));
		
		searchMap.put("searchCondition", "title");
		searchMap.put("searchValue", "tes");
		
		int secondResult = mapper.findMeetingTotalCount(searchMap);
		
		assertThat(secondResult, is(equalTo(1)));
		
		
	}
	
	/**
	 * removeMeetingBoard_test : 전달받은 게시글 번호를 삭제하는지 테스트한다.
	 * 전달받은 게시글번호가 조회가 되는지 확인한다.
	 * 조회된 게시글 번호를 삭제한 후 다시 조회를 하면, null을 반환받는지 확인한다.
	 * 
	 * @author 홍성원
	 */
	@Test
	@Transactional
	public void removeMeetingBoard_test() {
		MeetingDTO result = mapper.findOneMeetingBoard(1003);
		assertNotNull(result);
		
		mapper.removeMeetingBoard(1003);
		MeetingDTO secondResult = mapper.findOneMeetingBoard(1003);
		assertNull(secondResult);
		
		
	}
	
	/**
	 * modifyMeetingBoard_test : 입력한 정보로 회의록 게시글을 수정한다.
	 * DB에 존재하는 게시글 번호의 제목과 글내용을 수정한다.
	 * 수정한 글 번호로 게시글을 상세조회해, modify메소드로 전달한 수정내용이 잘 반영됐는지 확인한다.
	 * 수정내용을 전달해여 같은 글번호를 수정 후 , 수정내용이 잘 반영됐는지 확인한다.
	 * 
	 * @author 홍성원
	 */
	@Test
	@Transactional
	public void modifyMeetingBoard_test() {
		Map<String,String> modifyInfo = new HashMap<>();
		modifyInfo.put("no", "1003");
		modifyInfo.put("title", "주간회의");
		modifyInfo.put("content", "주간회의내용");
		mapper.modifyMeetingBoard(modifyInfo);
		MeetingDTO firstResult = mapper.findOneMeetingBoard(1003);
		
		assertThat(firstResult.getTitle(), is(equalTo("주간회의")));
		assertThat(firstResult.getContent(), is(equalTo("주간회의내용")));
		
		modifyInfo.put("no", "1003");
		modifyInfo.put("title", "주간회의2");
		modifyInfo.put("content", "주간회의내용2");
		mapper.modifyMeetingBoard(modifyInfo);
		MeetingDTO secondResult = mapper.findOneMeetingBoard(1003);
		assertThat(secondResult.getTitle(), is(equalTo("주간회의2")));
		assertThat(secondResult.getContent(), is(equalTo("주간회의내용2")));
		
		
	}
	
	/**
	 * findOneMeetingBoard_test : 하나의 게시글 번호로 게시글의 정보를 조회한다.
	 * DB에 저장된 회의록 게시글번호를 전달해 조회된 게시글 정보가 실제 DB에 저장된 내용과 같은지 검증한다.
	 * 
	 * @author 홍성원
	 */
	@Test
	public void findOneMeetingBoard_test() {
		MeetingDTO result = mapper.findOneMeetingBoard(1003);
		assertThat(result.getTitle(), is(equalTo("주간회의")));
		assertThat(result.getContent(), is(equalTo("주간회의내용")));
		
	}
	
	/**
	 * fineFile_test : fineFile 테스트 코드
	 * DB에 저장된 파일번호를 입력해, 조회된 파일의 정보가 실제 값과 일치하는지 확인한다.
	 *  
	 * @author 홍성원
	 */
	@Test
	public void fineFile_test() {
		FileDTO file = mapper.fineFile(131);
		
		assertThat(file.getFileOriginName(), is("빨간맛 페페.jpg"));
	}
	
	/**
	 * findMainList : 첨부파일이_정상적으로_삭제가_되는지_테스트 
	 * 
	 * DB에 존재하는 하나의 파일을 삭제했을때, 결과값이 true인지 확인한다.
	 * 다시 파일을 삭제하려고했을 때, false를 반환하는지 확인한다.
	 *   
	 * @author 홍성원
	 */
	@Test
	@Transactional
	public void 첨부파일이_정상적으로_삭제가_되는지_테스트() {
		
		boolean firstResult = mapper.removeMeetingBoardFile(154);
		
		assertThat(firstResult, is(true));
		
		boolean secondResult = mapper.removeMeetingBoardFile(154);
		
		assertThat(secondResult, is(false));
	}
}
