package com.greedy.waterfall.output.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.output.model.dao.OutputMapper;
import com.greedy.waterfall.output.model.dto.OutputAttachmentDTO;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskRestoreOutputDTO;

/**
 * <pre>
 * Class : OutputServiceImpl Comment : 산출물 로직 처리 부분
 * 
 * History 2022. 2. 26. (김서영)
 * 
 * @version 1
 * @author 김서영
 */
@Service
public class OutputServiceImpl implements OutputService {

	private final OutputMapper mapper;

	@Autowired
	public OutputServiceImpl(OutputMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * findOutputTask : 해당 프로젝트 내 업무 조회
	 * 
	 * @param taskDTO : projectNo, memberNo
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@Override
	public List<TaskDTO> findOutputTask(TaskDTO taskDTO) {
		/* 상위업무 List */
		List<TaskDTO> parentTaskList = mapper.selectParentTaskList(taskDTO);

		List<ChildTaskDTO> childTaskList = new ArrayList<ChildTaskDTO>();
		/* parentTaskList안의 parentDTO내의 childTaskList 불러오기 */
		for (int i = 0; i < parentTaskList.size(); i++) {
			childTaskList = parentTaskList.get(i).getChildList();

			// parentTaskList DTO의 parentTaskNo만 저장하는 과정, i=0일 때 parentTaskNo = 5...
			int parentTaskNo = parentTaskList.get(i).getTaskNo();

			/* parentTaskList안의 childTaskList안의 childDTO안의 parentTaskNo을 set해주는 과정 */
			for (int j = 0; j < childTaskList.size(); j++) {
				childTaskList.get(j).setParentTaskNo(parentTaskNo);
				childTaskList.get(j).setProjectNo(taskDTO.getProjectNo());
			}
		}
		for (int i = 0; i < parentTaskList.size(); i++) {
			childTaskList = mapper.selectChildTaskList(parentTaskList.get(i).getTaskNo());
			parentTaskList.get(i).setChildList(childTaskList);

		}

		return parentTaskList;
	}

	/**
	 * findOutputDetail : 업무 별 세부 산출물 내용 조회
	 * 
	 * @param taskNo : 클릭한 업무의 번호
	 * @return 클릭한 업무번호의 산출물 정보가 담긴 DTO 반환
	 * 
	 * @author 김서영
	 */
	@Override
	public Object findOutputDetail(int taskNo) {

		Map<String, Object> map = new HashMap<String, Object>();
		OutputDTO outputDetail = new OutputDTO();
		ChildTaskDTO childTask = new ChildTaskDTO();
		OutputAttachmentDTO outputFile = new OutputAttachmentDTO();
		TaskDTO parentTask = childTask.getParentTask();

		/* 화면에서 클릭한 하위업무의 번호를 통해 해당하는 하위업무의 정보를 저장한다. */
		childTask = mapper.selectChildTask(taskNo);

		/* 선택한 하위업무의 상위업무 업무 번호를 저장한다. */
		int parentTaskNo = childTask.getParentTaskNo();

		/* 상위업무 번호를 넘겨 상위업무 정보를 조회해온다 */
		parentTask = mapper.selectParentTask(parentTaskNo);

		/* 하위업무 내 상위업무DTO에 조회해온 상위업무 정보를 저장 */
		childTask.setParentTask(parentTask);

		/* 화면에서 클릭한 하위업무 번호로 번호에 해당하는 산출물 정보 저장 */
		outputDetail = mapper.selectOutputDetail(taskNo);

		if (outputDetail != null) {
			int outputNo = outputDetail.getOutputNo();
			/* 산출물 파일 정보 */
			outputFile = mapper.selectOutputFile(outputNo);

		}
		map.put("outputFile", outputFile);
		map.put("childTask", childTask);
		map.put("parentTask", parentTask);
		map.put("outputDetail", outputDetail);

		/* 산출물 DTO내 하위업무DTO에 상위업무 정보를 담은 하위업무를 저장 */
//		outputDetail.setChildTask(childTask);

		System.out.println("outputFile : " + outputFile);
		System.out.println("childTask : " + childTask);
		System.out.println("parentTask : " + parentTask);
		System.out.println("outputDetail : " + outputDetail);

		return map;
	}

	/**
	 * removeOutput : 산출물 삭제
	 * 
	 * @param OutputDTO output : 받아온 output 정보
	 * @return 
	 * 
	 * @author 김서영
	 */
	@Override
	public void removeOutput(OutputDTO output) {

		int outputNo = output.getOutputNo();
		int result = mapper.deleteOutput(output);

		if (result > 0) {

			OutputAttachmentDTO file = new OutputAttachmentDTO();

			file = mapper.selectOutputFile(outputNo);
			String fileUploadDirectory = file.getFilePath();
			String savedName = file.getRandomName();

			result = mapper.deleteOutputFile(output);
			
			if(result>0) {
				mapper.insertDeleteHistory(output);
			}

		}

	}

	/**
	 * findOutputList : Admin이 모든 프로젝트별 산출물 개수 조회를 위한 로직
	 * 
	 * @param 
	 * @return 전체 프로젝트 내 각각 산출물List를 담은 List
	 * 
	 * @author 김서영
	 */
	@Override
	public List<OutputProjectDTO> findOutputList() {

		List<OutputProjectDTO> projectList = new ArrayList<OutputProjectDTO>();
		List<OutputDTO> outputList = new ArrayList<OutputDTO>();

		/* 각 각 프로젝트에 해당하는 산출물 개수 */
		projectList = mapper.selectAllProjectList();
		System.out.println("output Admin projectList : " + projectList);

		for (int i = 0; i < projectList.size(); i++) {

			int projectNo = projectList.get(i).getNo();

			int outputCount = mapper.selectAlloutputCount(projectNo);

			outputList = mapper.selectAdminOutputList(projectNo);

			projectList.get(i).setTotalOutputCount(outputCount);
			projectList.get(i).setOutput(outputList);
		}

		System.out.println(projectList);

		return projectList;
	}
	
	/**
	 * findRestoreProjectList : 삭제한 산출물 복원 페이지 프로젝트 리스트받아오는 메소드
	 * @param 
	 * @return projectList : 각 프로젝트에 outputList를 담은 List 반환
	 * 
	 * @author 김서영
	 */
	@Override
	public List<OutputProjectDTO> findRestoreProjectList() {
		
		List<OutputProjectDTO> projectList = new ArrayList<OutputProjectDTO>();
		List<OutputDTO> outputList = new ArrayList<OutputDTO>();
		
		projectList = mapper.selectAllProjectList();
		
		for(int i = 0; i < projectList.size(); i++) {
			int projectNo = projectList.get(i).getNo();
			
			int deletedOutputCount = mapper.selectAllDeletedOutputCount(projectNo);
			
			outputList = mapper.selectAdminOutputList(projectNo);
			
			projectList.get(i).setTotalDeletedOutputCount(deletedOutputCount);
			projectList.get(i).setOutput(outputList);
			
		}
		
		return projectList;
	}

	/**
	 * registOutput : 산출물 등록 메소드
	 * @param OutputDTO output : 등록할 output 정보
	 * @return 1 : 산출물 등록 성공, 2: 산출물이 이미 존재하여 등록 실패
	 * 
	 * @author 김서영
	 */
	@Override
	public int registOutput(OutputDTO output) {

		int result = mapper.selectOutputVer(output);

		/* 산춞물이 등록되어 있지 않은 경우에만 등록 가능 그외에는 산출물 관리에서만 수정가능 */
		if (result == 0) {

			result = mapper.insertOutput(output);
			System.out.println("output insert 확인 : " + result);

			OutputAttachmentDTO attachmentDTO = output.getOutputFile();

			attachmentDTO.setTaskNo(output.getTaskNo());
			int outputResult = 0;
			if (result > 0) {
				outputResult = mapper.insertOutputAttachment(attachmentDTO);

			}

			if (outputResult > 0) {
				mapper.insertOutputHistory(output);
			}
			return 1;
		} else {
			return 0;
		}

	}

	
	/**
	 * findOutputFile : 산출물 내 파일 정보 조회 메소드
	 * @param int outputNo : 파일정보가 속한 산출물 번호
	 * @return OutputAttachmentDTO : 산출물 정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public OutputAttachmentDTO findOutputFile(int outputNo) {

		return mapper.selectOutputFile(outputNo);
	}

	@Override
	public void modifyOutput(OutputDTO output) {

		/* 1. TBL_OUTPUT 테이블의 내용을 업데이트한다. */
		int result = mapper.updateOutput(output);

		OutputAttachmentDTO outputFile = output.getOutputFile();
		outputFile.setOutputNo(output.getOutputNo());

		/* 히스토리 등록을 위해 필요한 originName을 미리 조회해둔다. */
		String beforeOriginName = mapper.selectOriginName(output);
		System.out.println("조회한 beforeOriginName : " + beforeOriginName);
		output.setBeforeOriginName(beforeOriginName);

		String afterOriginName = outputFile.getOriginName();
		System.out.println("조회한 beforeOriginName : " + afterOriginName);
		output.setAfterOriginName(afterOriginName);

		/* outputDTO 안에 있는 attachmentDTO에 값이 있는 경우(file이 들어온경우) */
		if (outputFile.getOriginName() != null) {

			/* 전에 존재하던 파일 정보를 수정한다. */
			int fileUpdateResult = mapper.updateFile(outputFile);
		}

		/* 2. 업데이트 성공 시 산출물 히스토리를 등록한다. */
		int historyResult = 0;
		if (result > 0) {
			historyResult = mapper.insertUpdateHistory(output);
		}
		/* 3. 산출물 히스토리 등록 성공 시 전체 히스토리에 등록한다. */
		
		  if(historyResult > 0) {
			  /*memberName 조회*/
			  String memberName = mapper.selectMemberName(output);
			  
			  output.setMemberName(memberName);
			  
			  mapper.insertEntireHistory(output); 
		  }
	}

	/**
	 * selectOutputCount : 산출물의 유무를 판단하는 메소드
	 * @param int taskNo : 산출물이 존재하는지 확인할 업무 번호
	 * @return 0: taskNo에 산출물이 있음, 1 : taskNo에 해당하는 산출물이 없음
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public int selectOutputCount(int taskNo) {

		int result = mapper.selectOutputCount(taskNo);

		/* 산출물이 없는 경우 1 return 산출물이 있는 경우 0 return */
		if (result == 0) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * findDeleteOutputList : 삭제된 산출물 정보 List
	 * @param TaskRestoreOutputDTO restoreOutput : projectNo을 담고 return값을 담을 DTO
	 * @return outputList : 업무와 삭제된 산출물 정보 List
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public List<TaskRestoreOutputDTO> findDeleteOutputList(TaskRestoreOutputDTO restoreOutput) {
		
		List<TaskRestoreOutputDTO> outputList = mapper.findDeleteOutputList(restoreOutput);
		
		return outputList;
	}

	/**
	 * restoreOutput : 산출물 복원 메소드
	 * @param int fileNo : 복원할 파일 번호
	 * @return true : 로직 성공시 / false : 로직 실패시
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public boolean restoreOutput(int fileNo) throws Exception {
		
		/* 선택한 파일에 해당하는 outputNo, taskNo 조회 */
		TaskRestoreOutputDTO output = mapper.selectTaskNoOutputNo(fileNo);
		System.out.println("selectTaskNoOutputNo : " + output);
		
		 int taskNo = output.getTaskNo();
		 int outptuNo = output.getOutputNo();
		 
		/*taskNo에 해당하는 산출물이 존재할경우 복원 불가*/
		int result = mapper.selectOutputCount(taskNo);
		if(result > 0) {
			return false;
		}else {
			int fileResult = mapper.updateRestoreFile(fileNo);
			if(!(fileResult > 0)) {
				throw new Exception();
			}
			int restoreResult = mapper.updateRestoreOutput(outptuNo);
			if(!(restoreResult > 0)) {
				throw new Exception();
			}
			return true;
		}
		
		
		
		
	}

	/**
	 * selectProjectNo : projectNo 조회 메소드
	 * @param int fileNo : 파일번호
	 * @return projectNo 파일이 속한 프로젝트 번호
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public int selectProjectNo(int fileNo) {
		
		int projectNo = mapper.selectProjectNo(fileNo);
		
		return projectNo;
	}

	/**
	 * selectProjectNoByOutputNo : 메소드 설명 작성 부분
	 * @param int outputNo : 산출물 번호
	 * @return projectNo : outputNo이 속한 projectNo
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public int selectProjectNoByOutputNo(int outputNo) {
		
		int projectNo = mapper.selectProjectNoByOutputNo(outputNo);
		
		return projectNo;
	}

	

}
