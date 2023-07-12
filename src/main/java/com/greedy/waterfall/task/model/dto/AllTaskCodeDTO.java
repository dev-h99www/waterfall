package com.greedy.waterfall.task.model.dto;

import java.util.List;

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
public class AllTaskCodeDTO {
	
	private List<ParentTaskCategoryDTO> parentCategory;
	private List<ChildTaskCategoryDTO> childCategory;
	
	
	
	

}
