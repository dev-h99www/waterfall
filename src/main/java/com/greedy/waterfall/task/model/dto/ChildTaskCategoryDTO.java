package com.greedy.waterfall.task.model.dto;
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
public class ChildTaskCategoryDTO {
	
	private int typeNo;
	private String childCategoryCode;
	private String childCategoryName;

}
