package com.greedy.waterfall.task.model.dto;

import java.util.List;

import com.greedy.waterfall.output.model.dto.OutputDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class TaskRestoreOutputDTO {
	
	private int taskNo;
	private int projectNo;
	private int memberNo;
	private int fileNo;
	private int outputNo;
	private String taskName;
	private String originName;
	
	private List<OutputDTO> restoreOutputList;

}
