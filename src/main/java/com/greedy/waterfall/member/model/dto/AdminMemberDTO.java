package com.greedy.waterfall.member.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : AdminMemberDTO
 * Comment : 관리자가 회원 등록,삭제,수정,변경을 할때 사용할 정보
 * 관련 테이블 : TBL_MEMBER, TBL_DEPT, TBL_TEAM, TBL_JOB_TITLE 
 * 			 ,TBL_MEMBER_COM_INFO 
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AdminMemberDTO {

	private int no;							//회원 식별번호
	private String id;						//회원 아이디
	private String name;					//회원 이름
	private String phone;					//회원 핸드폰
	private String email;					//회원 이메일
	private java.sql.Date createDate;		//회원 생성날짜
	private DeptDTO dept; 					//회원 부서정보 
	private TeamDTO team;					//회원 팀정보
	private JobDTO job;						//회원 직급정보
	private List<DeptDTO> DeptList;			//부서 목록
	private List<TeamDTO> teamList;			//팀 목록
	private List<JobDTO> jobList;		 	//직급 목록
	private String postDept;				//회원 변경전 부서
	private String postTeam;				//회원 변경전 팀
	private String postJob;					//회원 변경전 직급
	
}
