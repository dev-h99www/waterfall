package com.greedy.waterfall.output.model.dto;

import java.util.List;

import com.greedy.waterfall.member.model.dto.MemberDTO;

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
public class OutputProjectDTO {

	private int no; 							// 프로젝트번호
	private String name; 						// 프로젝트명
	private java.sql.Date startDate; 			// 시작일
	private java.sql.Date deadLine; 			// 마감일
	private int progression; 					// 진행율
	private java.sql.Date completed; 			// 종료일
	private String statusCode; 					// 프로젝트 상태코드(진행전, 진행 중 등)

	private MemberDTO manager; 					//프로젝트 담당자
	private OutputProjectStatusDTO status;		//프로젝트 상태정보
	private List<OutputDTO> output;				//프로젝트 내 산출물 정보 리스트
	private int totalOutputCount;				//프로젝트 내 총 산출물 개수
	private int totalDeletedOutputCount;		//프로젝트 내 총 삭제된 산출물 개수

}
