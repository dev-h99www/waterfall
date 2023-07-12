package com.greedy.waterfall.issue.model.dto;

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
public class IssueNotificationDTO {
	
	private int issueHistoryNo;					//이슈 알림 번호
	private String issueProgressStatus;			//이슈 진행 상태
	private java.sql.Date issueUpdatedDate;		//이슈 업데이트 날짜
	private int issueNo;						//이슈 번호
	private int issueRegisterNo;				//이슈 등록자 번호
	private int issueManagerNo;					//이슈 담당자 번호
	private String issueUpdatedContent;			//업데이트 된 이슈 내용
	private int issueReadCheckNo;				//이슈 읽었는지 여부
	private int issueProjectNo;				//이슈가 소속된 프로젝트 번호
	private int loginMemberNo;
	private int issueReader;
}
