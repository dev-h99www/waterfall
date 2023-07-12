package com.greedy.waterfall.company.model.dto;

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
public class CompanyDTO {

	private DeptDTO dept;
	private TeamDTO team;
	private JobDTO job;
}
