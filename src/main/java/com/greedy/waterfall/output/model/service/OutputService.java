package com.greedy.waterfall.output.model.service;

import java.util.List;

import com.greedy.waterfall.output.model.dto.OutputAttachmentDTO;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskRestoreOutputDTO;

/**
 * <pre>
 * Class : OutputService
 * Comment : 산춞물 메소드 Interface
 * 
 * History
 * 2022. 3. 13.  (김서영)
 * @version 1
 * @author 김서영
 */
public interface OutputService {

	List<TaskDTO> findOutputTask(TaskDTO taskDTO);

	Object findOutputDetail(int no);

	void removeOutput(OutputDTO output);

	List<OutputProjectDTO> findOutputList();

	int registOutput(OutputDTO output);

	OutputAttachmentDTO findOutputFile(int outputNo);

	void modifyOutput(OutputDTO output);

	int selectOutputCount(int outputNo);

	List<OutputProjectDTO> findRestoreProjectList();

	List<TaskRestoreOutputDTO> findDeleteOutputList(TaskRestoreOutputDTO restoreOutput);

	boolean restoreOutput(int fileNo) throws Exception;

	int selectProjectNo(int fileNo);

	int selectProjectNoByOutputNo(int outputNo);


}
