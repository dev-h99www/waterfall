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
public class ParentTaskCategoryDTO {
	
	private int typeNo;
	private String parentCategoryCode;
	private String parentCategoryName;

}
