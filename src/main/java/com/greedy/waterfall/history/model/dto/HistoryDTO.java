package com.greedy.waterfall.history.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class HistoryDTO {

	private int historyNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatedDate;
	private String content;
	private int categoryTypeNo;
	private String categoryTypeName;
	private int projectNo;
	private int registerNo;
}
