package com.greedy.waterfall.issue.model.dto;

import java.util.List;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greedy.waterfall.board.model.dto.FileDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IssueDTO {
	
	private int rnum;
	private int no;							//이슈번호
	private String name;					//이슈명
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd HH:mm:ss")
	private java.sql.Date createdDate;		//생성일
	private String progressStatus;			//진행상태
	private String importance;				//중요도
	private String content;					//이슈내용
	@Nullable private String answer;					//이슈답변
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd HH:mm:ss")
	@Nullable private java.sql.Date deadline;		//이슈마감일
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd HH:mm:ss")
	@Nullable private java.sql.Date completedDate;	//이슈종료일
	private String status;					//이슈 삭제여부
	private IssueRegisterDTO register;			//TBL_MEMBER 테이블과 JOIN하는 경우 1:1 조인이 될 것이기  때문에 DTO타입으로 생성
	private IssueManagerDTO manager;			//TBL_MEMBER 테이블과 JOIN하는 경우 1:1 조인이 될 것이기  때문에 DTO타입으로 생성
	private int registerNo;					//이슈를 등록한 사람
	private int managerNo;					//해당 이슈 처리를 도와줄 사람
	private IssueProjectDTO project;		//TBL_PROJECT 테이블과 JOIN하는 경우 1:1 조인이 될 것이기  때문에 DTO타입으로 생성
	private int projectNo;					//프로젝트 번호 (어느 프로젝트에서 발생했는지 확인하는 목적)
	private IssueTaskDTO task;				//TBL_TASK 테이블과 JOIN하는 경우 1:1 조인이 될 것이기  때문에 DTO타입으로 생성
	private int taskNo;						//업무 번호 (어떤 업무에서 발생한 이슈인지 확인)
	private IssueTaskCodeManageDTO taskCode;		//업무이름을 저장하기 위한 DTO
	private String taskCodeManageCode;				//업무 코드
	private String taskCodeManageName;				//업무 이름
	private List<IssueFileDTO> file;				//File 테이블과 join하는 경우 1:1 조인이 될 것이기 때문에 GuideFileDTO로 생성
	private int fileNo;						//첨부파일 번호
	private String originalName;			//첨부파일 이름
	private int loginMemberNo;				//로그인 한 사람 번호
	private String loginMemberName;			//로그인 한 사람 이름
}
