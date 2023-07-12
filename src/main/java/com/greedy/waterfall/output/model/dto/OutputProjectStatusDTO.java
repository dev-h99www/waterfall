package com.greedy.waterfall.output.model.dto;

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
public class OutputProjectStatusDTO {
	
	private String projectStatusCode;
	private String projectStatusName;

}
