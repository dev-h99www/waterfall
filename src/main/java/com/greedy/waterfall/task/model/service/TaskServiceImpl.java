package com.greedy.waterfall.task.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.greedy.waterfall.task.model.dao.TaskMapper;
import com.greedy.waterfall.task.model.dto.AllTaskCodeDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.ParentTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskHistoryDTO;
import com.greedy.waterfall.task.model.dto.TaskRegistDTO;

/**
 * <pre>
 * Class : TaskServiceImpl
 * Comment : task 로직 구현 부분
 * 
 * History
 * 2022. 2. 26.  (김서영)
 * @version 1
 * @author 김서영
 */
@Service
public class TaskServiceImpl implements TaskService{
	
	private final TaskMapper mapper;
	
	public TaskServiceImpl(TaskMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * findTaskTimeline : GanttChart및 초기 페이지 조회시 필요한 정보들 조회
	 * @param TaskDTO taskDTO : projectNo이 담긴 TaskDTO
	 * @return parentTaskList 상위업무 내 하위업무를 포함한 data
	 * 
	 * @author 김서영
	 */
	@Override
	public List<TaskDTO> findTaskTimeline(TaskDTO taskDTO) {
		
		/* 상위업무 List */
		List<TaskDTO> parentTaskList = mapper.selectParentTaskList(taskDTO);
		List<ChildTaskDTO> childTaskList = new ArrayList<ChildTaskDTO>();
		/* parentTaskList안의 parentDTO내의 childTaskList 불러오기 */
		for(int i = 0; i < parentTaskList.size(); i++) {
			childTaskList = parentTaskList.get(i).getChildList();
			
			//parentTaskList DTO의 parentTaskNo만 저장하는 과정, i=0일 때 parentTaskNo = 5...
			int parentTaskNo = parentTaskList.get(i).getTaskNo();
			
			/* parentTaskList안의 childTaskList안의 childDTO안의 parentTaskNo을 set해주는 과정 */
			for(int j = 0; j < childTaskList.size(); j++) {
				childTaskList.get(j).setParentTaskNo(parentTaskNo);
				childTaskList.get(j).setProjectNo(taskDTO.getProjectNo());
			}
			
			/*위에서 조회한 상위업무의 번호를 통해 하위업무 list 조회*/
			childTaskList = mapper.selectChildTaskList(parentTaskNo);
			parentTaskList.get(i).setChildList(childTaskList);
		}
		
			
		System.out.println("parentTaskList : " +parentTaskList);
		
		
		return parentTaskList;
	}

	/**
	 * findAllTaskCode : 모든 업무 코드, 업무명 조회 메소드
	 * @param 
	 * @return allTaskCodeDTO : 모든 업무코드와 코드명이 담긴 DTO
	 * 
	 * @author 김서영
	 */
	@Override
	public AllTaskCodeDTO findAllTaskCode() {
		
		/*현재 존재하는 모든 업무 카테고리 코드 저장*/
		
		List<ParentTaskCategoryDTO> parentTaskCategory = mapper.selectParentCategory();
		List<ChildTaskCategoryDTO> childTaskCategory = mapper.selectChildCategory();
		
		AllTaskCodeDTO allTaskCodeDTO = new AllTaskCodeDTO();
		allTaskCodeDTO.setParentCategory(parentTaskCategory);
		allTaskCodeDTO.setChildCategory(childTaskCategory);
		
		return allTaskCodeDTO;
		
	}

	/**
	 * findProjectMember : 프로젝트에 참여중인 멤버 정보 조회
	 * @param int projectNo : 프로젝트 번호
	 * @return memberList : projectNo에 해당하는 member정보 List
	 * 
	 * @author 김서영
	 */
	@Override
	public List<ProjectMemberDTO> findProjectMember(int projectNo) {
		
		List<ProjectMemberDTO> memberList = mapper.selectAllProjectMember(projectNo);
		
		return memberList;
	}

	/**
	 * registTask : 업무 등록 메소드
	 * @param TaskRegistDTO taskRegistDTO : 등록할 업무 정보
	 * @return true : 업무 등록 성공/ false : 업무 등록 실패
	 * 
	 * @author 김서영
	 */
	@Override
	public boolean registTask(TaskRegistDTO taskRegistDTO) {
		
		int memberNo = taskRegistDTO.getMemberNo();
		TaskHistoryDTO history = new TaskHistoryDTO();
		
			/*종속관계가 Null이면서 상위업무 코드일때만 등록가능*/
			if(isParentTaskCodeNull(taskRegistDTO)) {
				if(isParentTaskCode(taskRegistDTO)) {
					int result = mapper.registParentTask(taskRegistDTO);
					
					if(isSuccess(result)) {
						mapper.registHistory(taskRegistDTO);
						
						String memberName = mapper.selectMemberName(memberNo);
						taskRegistDTO.setMemberName(memberName);
						
						mapper.insertEntireRegistHistroy(taskRegistDTO);
						return true;
					}
				/*상위업무가 등록안된 상태로 하위업무를 등록하려고 할 때*/	
				} else {
					return false;
				}
				
				
				
			/* 하위 업무 등록 시 */
			} else {
				/*상위업무, 하위업무 동시에 들어올때 상위업무 먼저 등록*/
				Integer parentTaskNo = mapper.selectParentTaskNo(taskRegistDTO);
				
				/*parentTaskNo이 Null이면 0으로 반환 받음*/
				parentTaskNo = isParentTaskNoNull(parentTaskNo);
				
				System.out.println("상위업무 없을 때 등록 테스트 : "+ parentTaskNo);
				
				/*등록된 상위업무가 있을 때 하위업무로 바로 등록*/
				if(isParentTaskExist(parentTaskNo)) {
					taskRegistDTO.setParentTaskNo(parentTaskNo);
					int result = mapper.registTask(taskRegistDTO);
					
					if(isSuccess(result)) {
						mapper.registHistory(taskRegistDTO);
						
						String memberName = mapper.selectMemberName(memberNo);
						taskRegistDTO.setMemberName(memberName);
						
						mapper.insertEntireRegistHistroy(taskRegistDTO);
						
						return true;
					}
				/*등록된 상위업무가 없을 때 */	
				} else {
					
					/*상위업무와 종속관계가 둘다 상위업무 코드로 들어올 때*/
					if(isParentTaskCode(taskRegistDTO) && isParentTaskCodeEqualsTaskCode(taskRegistDTO)) {
						return false;
					}
					/*상위업무 먼저 등록*/
					int parentTaskResult = mapper.registTask(taskRegistDTO);
					
					if(isSuccess(parentTaskResult)) {
						/* 상위업무 등록한 후 히스토리 등록*/
						String memberName = mapper.selectMemberName(memberNo);
						taskRegistDTO.setMemberName(memberName);
						history.setTaskCode(taskRegistDTO.getParentTaskCode());
						String taskName = mapper.selectTaskName(history);
						taskRegistDTO.setTaskName(taskName);
						System.out.println("야야야ㅑ야야야야야야taskRegistDTO : " + taskRegistDTO);
						mapper.insertEntireRegistHistroy(taskRegistDTO);
						
						/*하위업무 등록*/
						parentTaskNo = mapper.selectParentTaskNoCurrval(taskRegistDTO);
						taskRegistDTO.setParentTaskNo(parentTaskNo);
						int result = mapper.registTask(taskRegistDTO);
						
						if(isSuccess(result)) {
							mapper.registHistory(taskRegistDTO);
							history.setTaskCode(taskRegistDTO.getTaskCode());
							taskName = mapper.selectTaskName(history);
							taskRegistDTO.setTaskName(taskName);
							System.out.println("야야야ㅑ야야야야야야taskRegistDTO22222222 : " + taskRegistDTO);
							mapper.insertEntireRegistHistroy(taskRegistDTO);
							return true;
						}
					/*상위업무 등록 실패시*/	
					} else {
						return false;
					}
				}
			}
			return false;
	}


	/**
	 * findAllCategoryCode : 모든 업무 카테고리 코드 조회 메소드
	 * @param 
	 * @return taskCategoryList : 모든 카테고리 코드와 코드명
	 * 
	 * @author 김서영
	 */
	@Override
	public List<TaskCategoryDTO> findAllCategoryCode() {
		
		List<TaskCategoryDTO> taskCategoryList = mapper.selectAllCategoryCode();
		
		return taskCategoryList;
	}

	/**
	 * findChildTaskList : 프로젝트 내 하위업무 조회하는 메소드
	 * @param int projectNo 프로젝트 번호
	 * @return childTaskList : proejctNo에 해당하는 하위업무 List
	 * 
	 * @author 김서영
	 */
	@Override
	public List<ChildTaskDTO> findChildTaskList(int projectNo) {
		
		List<ChildTaskDTO> childTaskList = mapper.selectAllchildTask(projectNo);
		
		return childTaskList;
	}

	/**
	 * findTaskDetail : 업무 상세정보 조회
	 * @param int taskNo : 상세조회할 taskNo
	 * @return childTask : taskNo에 해당하는 업무 상세정보
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public ChildTaskDTO findTaskDetail(int taskNo) {
		
		ChildTaskDTO childTask = new ChildTaskDTO();
		TaskDTO parentTask = childTask.getParentTask();
		
		childTask = mapper.selectChildTask(taskNo);
		int parentNo = childTask.getParentTaskNo();
		parentTask = mapper.selectParentTask(parentNo);
		
		childTask.setParentTask(parentTask);
		
		return childTask;
	}

	/**
	 * updateTask : 업무 수정
	 * @param first : TaskRegistDTO taskUpdateDTO : 업무 수정정보가 담긴 DTO
	 * @param second : TaskHistoryDTO history : history 등록을 위한 DTO
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public void updateTask(TaskRegistDTO taskUpdateDTO,  TaskHistoryDTO history) {
		
		/* 수정할 내용 업데이트 */
		int result = mapper.updateTask(taskUpdateDTO);
		int memberNo = history.getMemberNo();
		
		/* 업데이트 성공시 히스토리 등록 */
		int historyResult = 0;
		
		if(result > 0) {
			historyResult = mapper.insertUpdateHistory(taskUpdateDTO);
		}
		
		/* Entire History 등록 */
		if(historyResult > 0) {
			String memberName = mapper.selectMemberName(memberNo);
			String taskName = mapper.selectTaskName(history);
			
			taskUpdateDTO.setMemberName(memberName);
			taskUpdateDTO.setTaskName(taskName);
			
			
			mapper.insertEntireUpdateHistory(taskUpdateDTO);
		}
		
		
		
		
	}

	/**
	 * removeTask : 상위업무 번호로 삭제 요청이 들어올 시 로직을 통해 하위업무까지 삭제하는 메소드
	 * @param first TaskDTO task : 삭제할 task 정보
	 * @param second TaskHistoryDTO history :히스토리 등록을 위한 DTO
	 * @return 
	 * 
	 * @author 김서영
	 * @date 2022. 3. 13.
	 */
	@Override
	public void removeTask(TaskDTO task, TaskHistoryDTO history) {
		
		/* 지우기 전에 히스토리에 등록할 정보 조회*/
		TaskHistoryDTO historyInfo = mapper.selectHistoryInfo(history);
		
		history.setMemberName(historyInfo.getMemberName());
		history.setTaskName(historyInfo.getTaskName());
		
		Integer taskCodeResult = mapper.selectRefTaskCode(task);
		
		System.out.println("taskCodeResult : " + taskCodeResult);
		
		int deleteResult = 0;
		/* 매개변수로 넘어온 taskNo가 상위업무 일 때 */
		if(taskCodeResult == null) {
			deleteResult = mapper.deleteTask(task);
		/* 하위업무일 때*/	
		} else {
			deleteResult = mapper.deleteChildTask(task);
		}
		
		int historyResult = 0;
		if(deleteResult > 0) {
			historyResult = mapper.insertDeleteHistory(task);
		}
		
		int entireHistoryResult = 0;
		if(historyResult > 0) {
			
			System.out.println("deleteHistoryInfo : " + history);
			
			entireHistoryResult = mapper.insertEntireDeleteHistory(history);
		}
		
		
	}
	
	
	/**
	 * isParentTaskCodeNull : view에서 받아온 종속관계가 null 인지 확인하는 메소드
	 * @param TaskRegistDTO taskRegistDTO : view에서 받아온 정보
	 * @return 종속관계 select value가 null인경우 true, null이 아닌경우 false
	 * 
	 * @author 김서영
	 */
	private boolean isParentTaskCodeNull(TaskRegistDTO taskRegistDTO) {
		
		if(taskRegistDTO.getParentTaskCode().equals("NULL")) {
			return true;
		}
		return false;
	}
	
	/**
	 * isParentTaskCode : view에서 선택한 업무가 상위업무 목록과 같은지 확인하는 메소드
	 * @param TaskRegistDTO taskRegistDTO : view에서 받아온 정보
	 * @return 받아온 업무가 상위업무(요구사항명세, 테스트, 설계 등)과 같을 때 true, 그 외 false
	 * 
	 * @author 김서영
	 */
	private boolean isParentTaskCode(TaskRegistDTO taskRegistDTO) {
		
		String taskCode = taskRegistDTO.getTaskCode();
		List<String> parentTaskCodes = getParentTaskCodes();
		
		for(int i = 0; i < parentTaskCodes.size(); i++) {
			if(isCompareParentTaskCode(taskCode, parentTaskCodes.get(i))) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * isParentTaskCodeEqualsTaskCode : 종속관계에서 선택한 업무가 상위업무 목록에 속하는지 확인하는 메소드
	 * @param TaskRegistDTO taskRegistDTO : view에서 받아온 정보
	 * @return 받아온 종속관계 업무가 상위업무(요구사항명세, 테스트, 설계 등)과 같을 때 true, 그 외 false
	 * 
	 * @author 김서영
	 */
	private boolean isParentTaskCodeEqualsTaskCode(TaskRegistDTO taskRegistDTO) {
		String parentTaskCode = taskRegistDTO.getParentTaskCode();
		List<String> parentTaskCodes = getParentTaskCodes();
		
		for(int i = 0; i < parentTaskCodes.size(); i++) {
			
			if(isCompareParentTaskCode(parentTaskCode, parentTaskCodes.get(i))) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * isCompareParentTaskCode : taskCode와 parentTaskCode가 같은지 확인하는 메소드
	 * @param first : String taskCode : 선택한 업무
	 * @param second : String parentTaskCode : 상위업무 목록 중 한개
	 * @return 같으면 true, 다를 시 false
	 * 
	 * @author 김서영
	 */
	private boolean isCompareParentTaskCode(String taskCode, String parentTaskCode) {
		
		return taskCode.equals(parentTaskCode);
	}
	
	/**
	 * getParentTaskCodes : 상위업무 List 반환 메소드
	 * @param 
	 * @return 상위업무 List
	 * 
	 * @author 김서영
	 */
	private List<String> getParentTaskCodes() {
		
		List<String> parentTaskCodes = new ArrayList<String>();
		
		String parentTaskCode1 = "REQ";
		String parentTaskCode2 = "MAINTENANCE";
		String parentTaskCode3 = "DEVELOPMENT";
		String parentTaskCode4 = "TEST";
		String parentTaskCode5 = "DESIGN";
		
		parentTaskCodes.add(parentTaskCode1);
		parentTaskCodes.add(parentTaskCode2);
		parentTaskCodes.add(parentTaskCode3);
		parentTaskCodes.add(parentTaskCode4);
		parentTaskCodes.add(parentTaskCode5);
		
		return parentTaskCodes;
	}

	/**
	 * isSuccess : insert, update, delete 수행결과가 성공인지 확인하는 메소드
	 * @param int result : dao 수행결과
	 * @return result > 0 일시 true 그 외 false
	 * 
	 * @author 김서영
	 */
	private boolean isSuccess(int result) {
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * isParentTaskNoNull : 상위업무가 존재하는지 확인하는 메소드
	 * @param Integer parentTaskNo : dao에서 받아온  참조하고있는 상위업무번호(ref_task_no)
	 * @return parentTaskNo이 null 일 시(상위업무가 존재하지 않음) true
	 * 
	 * @author 김서영
	 */
	private int isParentTaskNoNull(Integer parentTaskNo) {
		
		if(parentTaskNo == null) {
			parentTaskNo = 0;
		}
		
		return parentTaskNo;
	}
	
	/**
	 * isParentTaskExist : 상위업무번호(ref_task_no)가 존재하는지 확인하는 메소드
	 * @param Integer parentTaskNo
	 * @return parentTaskNo이 0보다 크면 return true;
	 * 
	 * @author 김서영
	 */
	private boolean isParentTaskExist(Integer parentTaskNo) {
		if(parentTaskNo > 0) {
			return true;
		}
		return false;
	}
	
	
}
