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
public class IssueFileDTO {

	private int no;					// 파일 번호(식별자)
	private int refIssueNo;			// 이슈 번호
	private String savedPath;		// 파일이 저장된 경로
	private String originalName;	// 원래 파일 이름
	private String randomName;		// 랜덤으로 저장될 파일 이름
	private String status;			// 삭제여부
	private int fileCategoryNo;		// 어떤 게시판에 속하는지에 대한 번호
}
