package com.greedy.waterfall.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardCategoryDTO {

	private int projectNo;
	private int categoryNo;
	
	public BoardCategoryDTO() {}
	public BoardCategoryDTO(int projectNo) {
		this.projectNo = projectNo;
	}
	
	public BoardCategoryDTO setBoardCategory(int categoryNo) {
		setCategoryNo(categoryNo);
		
		return this;
	}
}
