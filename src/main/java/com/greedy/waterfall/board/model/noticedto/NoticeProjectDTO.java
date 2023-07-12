package com.greedy.waterfall.board.model.noticedto;

import java.sql.Date;

public class NoticeProjectDTO {
	
	private int projectNo;					//프로젝트 번호
	private String projectName;				//프로젝트 명
	private java.sql.Date startDate;		//프로젝트 시작일
	private java.sql.Date deadline;			//프로젝트 종료일
	private String status;					//프로젝트 상태
	private String progression;				//프로젝트 진행율
	private java.sql.Date completedDate;	//프로젝트 완료일
	private String statusCode;				//프로젝트 상태코드
	
	public NoticeProjectDTO() {}

	
	public NoticeProjectDTO(int projectNo, String projectName, Date startDate, Date deadline, String status,
			String progression, Date completedDate, String statusCode) {
		super();
		this.projectNo = projectNo;
		this.projectName = projectName;
		this.startDate = startDate;
		this.deadline = deadline;
		this.status = status;
		this.progression = progression;
		this.completedDate = completedDate;
		this.statusCode = statusCode;
	}


	public int getProjectNo() {
		return projectNo;
	}


	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public java.sql.Date getStartDate() {
		return startDate;
	}


	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}


	public java.sql.Date getDeadline() {
		return deadline;
	}


	public void setDeadline(java.sql.Date deadline) {
		this.deadline = deadline;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getProgression() {
		return progression;
	}


	public void setProgression(String progression) {
		this.progression = progression;
	}


	public java.sql.Date getCompletedDate() {
		return completedDate;
	}


	public void setCompletedDate(java.sql.Date completedDate) {
		this.completedDate = completedDate;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	@Override
	public String toString() {
		return "NoticeProjectDTO [projectNo=" + projectNo + ", projectName=" + projectName + ", startDate=" + startDate
				+ ", deadline=" + deadline + ", status=" + status + ", progression=" + progression + ", completedDate="
				+ completedDate + ", statusCode=" + statusCode + "]";
	}


}
