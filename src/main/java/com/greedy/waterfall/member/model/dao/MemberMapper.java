package com.greedy.waterfall.member.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.DeptDTO;
import com.greedy.waterfall.member.model.dto.DeptJobDTO;
import com.greedy.waterfall.member.model.dto.JobDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.dto.TeamDTO;

/**
 * <pre>
 * Class : MemberMapper
 * Comment : 교육 게시판의 DAO를 담당하는 인터페이스, EduServiceImpl과 EduMapper.xml을 연결하며
 * 			 요청 후 결과를 반환한다.
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
public interface MemberMapper {

	/**
	 * selectEncryptedPwd : 아이디에 해당하는 비밀번호 조회 
	 * @param member : 입력한 아이디와 비밀번호 정보 
	 * @return : 비밀번호 반환
	 * 
	 * @author 김영광
	 */
	String selectEncryptedPwd(MemberDTO member);
	
	/**
	 * selectMember : 입력한 아이디에 따른 회원정보 조회 
	 * @param member : 입력한 아이디 정보와 비밀번호 
	 * @return : 아이디와 일치하는 회원정보 반환
	 * 
	 * @author 김영광
	 */
	MemberDTO selectMember(MemberDTO member);
	
	/**
	 * findAdminMemberList : 계정 목록 리스트 조회 
	 * @param selectCriteria : 페이징처리와 검색값을 담고있는 변수 
	 * @return 페이징 처리와 검색에 따른 결과 행들 반환
	 * 
	 * @author 김영광
	 */
	List<AdminMemberDTO> findAdminMemberList(SelectCriteria selectCriteria);
	
	/**
	 * selectTotalCount : 검색정보에 따른 총 검색 정보 반환  
	 * @param searchMap : 검색정보를 담고 있는 변수
	 * @return : 행 전체를 수 반환
	 * 
	 * @author 김영광
	 */
	int selectTotalCount(Map<String, String> searchMap);
	
	/**
	 * findDept : 부서 목록 리스트 요청 후 반환  
	 * @return : 부서 목록 전체 반환
	 * 
	 * @author 김영광
	 */
	List<DeptDTO> findDept();
	
	/**
	 * findDept : 직급 목록 리스트 요청 후 반환 
	 * @return : 직급 목록 전체 반환
	 * 
	 * @author 김영광
	 */
	List<JobDTO> findJob();
	
	/**
	 * findTeamList : 부서코드에 따른 팀 목록 반환 
	 * @param deptCode : 선택한 부서코드 정보
	 * @return : 부서코드에 따른 팀 목록 전체 반환
	 * 
	 * @author 김영광
	 */
	List<TeamDTO> findTeamList(String deptCode);
	
	/**
	 * adminMemberRegist : 입력한 회원정보 계정생성 등록 
	 * @param adminMember : 입력한 회원정보 계정 정보
	 * @return : 회원 등록 결과 반환
	 * 
	 * @author 김영광
	 */
	int adminMemberRegist(AdminMemberDTO adminMember);
	
	/**
	 * deptMemberRegist : 입력한 회원정보 부서 등록
	 * @param adminMember : 입력한 회원정보 계정 부서 정보
	 * @return : 선택한 부서 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	int deptMemberRegist(AdminMemberDTO adminMember);
	
	/**
	 * teamMemberRegist : 입력한 회원정보 팀 등록
	 * @param adminMember : 입력한 회원정보 계정 팀 정보
	 * @return : 선택한 팀 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	int teamMemberRegist(AdminMemberDTO adminMember);
	
	/**
	 * jobMemberRegist : 입력한 회원정보 직급 등록
	 * @param adminMember : 입력한 회원정보 계정 직급 정보
	 * @return : 선택한 직급 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	int jobMemberRegist(AdminMemberDTO adminMember);
	
	/**
	 * memberRegist : 입력한 회원정보 최신 등록
	 * @param adminMember : 입력한 회원정보 부서,팀,직급 정보
	 * @return : 회원 최신 정보 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	int memberRegist(AdminMemberDTO adminMember);
	
	/**
	 * adminMemberModify : 선택한 회원에 정보 출력 목록 요청 
	 * @param id : 선택한 회원의 아이디 정보
	 * @return : 선택한 회원의 정보 반환 (이름, 아이디, 식별번호, 부서, 팀, 직급)
	 * 
	 * @author 김영광
	 */
	AdminMemberDTO adminMemberModify(String id);
	
	/**
	 * adminDeptMember : 부서전체 목록 요청  
	 * @return : 부서 전체목록 반환
	 * 
	 * @author 김영광
	 */
	List<DeptDTO> adminDeptMember();
	
	/**
	 * adminTeamMember : 팀 전체 목록 요청  
	 * @return : 팀 전체목록 반환
	 * 
	 * @author 김영광
	 */
	List<TeamDTO> adminTeamMember();
	
	/**
	 * adminJobMember : 직급 전체 목록 요청
	 * @return : 직급 전체목록 반환 
	 * 
	 * @author 김영광
	 */
	List<JobDTO> adminJobMember();
	
	/**
	 * adminMember : 입력된 이름의 정보 업데이트
	 * @param adminMember : 회원의 아이디 정보
	 * @return : 이름 업데이트 결과 반환
	 * 
	 * @author 김영광
	 */
	int adminMember(AdminMemberDTO adminMember);
	
	/**
	 * adminDept : 선택한 부서 히스토리 정보 등록
	 * @param adminMember : 선택된 부서 정보
	 * @return : 부서 히스토리 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	int adminDept(AdminMemberDTO adminMember);
	
	/**
	 * adminTeam : 선택한 팀 히스토리 정보 등록
	 * @param adminMember : 선택된 팀 정보
	 * @return : 팀 히스토리 등록 후 결과 반환 
	 * 
	 * @author 김영광
	 */
	int adminTeam(AdminMemberDTO adminMember);
	
	/**
	 * adminJob : 선택한 부서 히스토리 정보 등록
	 * @param adminMember : 선택된 직급 정보
	 * @return : 직급 히스토리 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	int adminJob(AdminMemberDTO adminMember);
	
	/**
	 * oneMember : 회원의 최신 부서, 팀, 직급 정보 업데이트
	 * @param adminMember : 선택한 부서,팀, 직급 회원 정보
	 * @return : 최신정보 테이블에 선택한 부서 , 직급, 팀 등록 결과 반환 
	 * 
	 * @author 김영광
	 */
	int oneMember(AdminMemberDTO adminMember);
	
	/**
	 * removeMember : 선택한 회원정보 삭제
	 * @param id : 선택한 회원의 아이디 정보
	 * @return : 삭제 결과 반환
	 * 
	 * @author 김영광
	 */
	int removeMember(String id);
	
	/**
	 * removeMemberInfo : 선택한 회원의 최신정보 삭제
	 * @param id : 선택한 회원의 아이디 정보
	 * @return : 최신정보 테이블의 해당 회원정보 삭제 결과 반환
	 * 
	 * @author 김영광
	 */
	int removeMemberInfo(String id);
	
	/**
	 * selectOne : 현재 비밀번호 조회
	 * @param id : 선택한 회원의 아이디 정보
	 * @return : 해당 아이디의 비밀번호 반환
	 * 
	 * @author 김영광
	 */
	String selectOne(String id);
	
	/**
	 * pwUpdate : 새로운 비밀번호 업데이트
	 * @param map : 회원의 아이디와 새로운 비밀번호 정보
	 * @return : 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	void pwUpdate(Map<String, Object> map);
	
	/**
	 * memberInfoUpdate : 회원의 이메일과 핸드폰번호 등록, 업데이트 
	 * @param member : 회원의 정보(핸드폰 번호, 이메일, 아이디)
	 * @return : 번호, 이메일 업데이트 후 결과 반환
	 * 
	 * @author 김영광
	 */
	void memberInfoUpdate(MemberDTO member);
	
	/**
	 * memberInfoPhone : 회원의 정보 변경이력 핸드폰번호 등록 
	 * @param member : 회원의 정보(핸드폰 번호, 이메일 , 아이디)
	 * @return : 회원 히스토리의 핸드폰 번호 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	void memberInfoPhone(MemberDTO member);
	
	/**
	 * memberInfoEmail : 회원의 정보 변경이력 이메일 등록 
	 * @param member : 회원의 정보(핸드폰 번호, 이메일 , 아이디)
	 * @return : 회원 히스토리의 이메일 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	void memberInfoEmail(MemberDTO member);
}
