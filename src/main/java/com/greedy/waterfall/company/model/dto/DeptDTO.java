package com.greedy.waterfall.company.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeptDTO {

	private String code;
	private String name;
	
	private List<TeamDTO> subTeamList;
	
}
