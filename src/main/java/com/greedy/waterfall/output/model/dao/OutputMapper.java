package com.greedy.waterfall.output.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.output.model.dto.OutputAttachmentDTO;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskRestoreOutputDTO;

/**
 * <pre>
 * Class : OutputMapper
 * Comment : 산출물 Mapper 메소드
 * 
 * History
 * 2022. 3. 13.  (김서영)
 * @version 1
 * @author 김서영
 */
public interface OutputMapper {

	/**
	 * selectParentTaskList : 상위업무 리스트 조회
	 * @param projectNo을 받아옴
	 * @return 상위업무 List
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<TaskDTO> selectParentTaskList(TaskDTO taskDTO);

	/**
	 * selectChildTaskList : 하위업무 리스트 조회
	 * @param 하위업무가 속하는 상위업무 번호
	 * @return 하위업무 리스트
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<ChildTaskDTO> selectChildTaskList(int taskNo);

	/**
	 * selectOutputDetail : 산출물 상세조회
	 * @param 산춞물이 속하는 업무번호
	 * @return 산춞물 상세정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	OutputDTO selectOutputDetail(int taskNo);

	/**
	 * selectChildTask : 하위업무 상세조회
	 * @param 하위업무 번호
	 * @return 하위업무 정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	ChildTaskDTO selectChildTask(int taskNo);

	/**
	 * selectParentTaskName : 하위업무의 상위업무명 조회
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	TaskCategoryDTO selectParentTaskName(int parentTaskNo);

	/**
	 * selectParentTask : 상위업무 조회
	 * @param 상위업무 번호
	 * @return 상위업무 정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	TaskDTO selectParentTask(int parentTaskNo);

	/**
	 * deleteOutput : 산출물 삭제
	 * @param 삭제할 산출물 정보
	 * @return 삭제 성공시 int 반환
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int deleteOutput(OutputDTO output);

	/**
	 * selectAdminOutputList : 관리자 삭제 산출물 관리 페이지에 필요한 정보 조회
	 * @param 삭제된 산출물이 속한 프로젝트 번호
	 * @return 삭제된 산출물 정보 list
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<OutputDTO> selectAdminOutputList(int projectNo);

	/**
	 * selectAllProjectList : 모든 프로젝트 List
	 * @param 
	 * @return 프로젝트 정보 List
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<OutputProjectDTO> selectAllProjectList();

	/**
	 * selectAlloutputCount : 프로젝트 내 존재하는 산출물 개수 조회
	 * @param 산출물 개수를 조회할 프로젝트 번호
	 * @return 프로젝트 내 삭제되지 않은 산출물 총 개수
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int selectAlloutputCount(int projectNo);

	/**
	 * insertOutput : 산출물 등록
	 * @param 등록할 산출물 정보
	 * @return 등록 성공시 int return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int insertOutput(OutputDTO output);

	/**
	 * insertOutputAttachment : 산출물 첨부파일 등록
	 * @param 등록할 첨부파일 정보
	 * @return 등록 성공시 int return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int insertOutputAttachment(OutputAttachmentDTO attachmentDTO);

	/**
	 * insertOutputHistory : 산출물 히스토리 등록
	 * @param 히스토리 등록시 필요한 산출물 정보
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	void insertOutputHistory(OutputDTO output);

	/**
	 * selectOutputFile : 신츨믈 첨부파일 조회
	 * @param 파일을 조회할 산출물 번호
	 * @return 산출물 첨부파일 정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	OutputAttachmentDTO selectOutputFile(int outputNo);

	/**
	 * deleteOutputFile : 산출물 첨부파일 삭제
	 * @param 삭제할 산출물 첨부파일 정보
	 * @return 삭제 성공시 int return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int deleteOutputFile(OutputDTO output);

	/**
	 * insertDeleteHistory : 산출물 삭제 히스토리 등록
	 * @param 삭제한 산출물 정보
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	void insertDeleteHistory(OutputDTO output);

	/**
	 * updateOutput : 산출물 수정
	 * @param 산출물 수정 내용
	 * @return 수정 성공시 int return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int updateOutput(OutputDTO output);

	/**
	 * selectOutputVer : 산출물 ver 조회
	 * @param ver 조회할 산출물 정보
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	Integer selectOutputVer(OutputDTO output);

	/**
	 * selectOutputCount : 업무에 존재하는 산출물 개수
	 * @param 업무번호
	 * @return null or 산출물 존재 시 1 반환
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	Integer selectOutputCount(int taskNo);

	/**
	 * insertUpdateHistory : 산출물 수정 히스토리 등록
	 * @param 산출물 수정 정보
	 * @return 수정 성공시 int return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int insertUpdateHistory(OutputDTO output);

	/**
	 * updateFile : 첨부파일 수정
	 * @param 수정 첨부파일 정보
	 * @return 수정 성공시 int return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int updateFile(OutputAttachmentDTO outputFile);

	/**
	 * selectOriginName : 산출물 파일 원본 이름 조회
	 * @param 첨부파일을 조회할 산출물 정보
	 * @return 첨부파일 원본이름
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	String selectOriginName(OutputDTO output);

	/**
	 * selectMemberName : 산출물 등록 멤버이름 조회
	 * @param 산출물 정보
	 * @return 산출물 등록자 이름
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	String selectMemberName(OutputDTO output);

	/**
	 * insertEntireHistory : 수정 성공시 전체 히스토리 등록
	 * @param 히스토리에 등록할 산출물 정보
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	void insertEntireHistory(OutputDTO output);

	/**
	 * selectAllDeletedOutputCount : 삭제된 모든 산출물 개수
	 * @param 삭제된 산출물 개수를 조회할 프로젝트 번호
	 * @return 삭제된 산출물 개수
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int selectAllDeletedOutputCount(int projectNo);

	/**
	 * findDeleteOutputList : 삭제된 산출물 정보 list
	 * @param 삭제된 산출물 정보를 담아갈 DTO
	 * @return 삭제된 산출물 정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<TaskRestoreOutputDTO> findDeleteOutputList(TaskRestoreOutputDTO restoreOutput);

	/**
	 * selectDeletedOutput : 삭제된 산출물 조회
	 * @param 삭제된 산출물이 속한 업무 번호
	 * @return 삭제된 산출물이 없을시 null 반환, 있을시 숫자 반환
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	List<Integer> selectDeletedOutput(int taskNo);

	/**
	 * selectTaskNoOutputNo : 파일번호가 속하는 업무번호, 산출물 번호 조회
	 * @param 파일 번호
	 * @return 업무번호, 산출물 번호
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	TaskRestoreOutputDTO selectTaskNoOutputNo(int fileNo);

	/**
	 * updateRestoreFile : 산출물 복원
	 * @param 복원할 파일 번호
	 * @return 파일 복원 성공시 int return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int updateRestoreFile(int fileNo);

	/**
	 * updateRestoreOutput : 산출물 복원
	 * @param 산출물이 복원된 산출물 번호
	 * @return 산출물 복원 성공시 int return
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int updateRestoreOutput(int outptuNo);

	/**
	 * selectProjectNo : 프로젝트 번호 조회
	 * @param 프로젝트에 속하는 파일번호
	 * @return 파일이 속하는 프로젝트 번호
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int selectProjectNo(int fileNo);

	/**
	 * selectProjectNoByOutputNo : 산출물 번호가 속하는 프로젝트번호 조회
	 * @param 프로젝트 내 속하는 output 번호
	 * @return 프로젝트 번호
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	int selectProjectNoByOutputNo(int outputNo);
	
	
}
