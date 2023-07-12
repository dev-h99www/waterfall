package com.greedy.waterfall.member.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.exception.member.MemberRegistException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.DeptDTO;
import com.greedy.waterfall.member.model.dto.DeptJobDTO;
import com.greedy.waterfall.member.model.dto.JobDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.dto.TeamDTO;

/**
 * <pre>
 * Class : MemberService
 * Comment : MemberServiceImpl에서 사용할 Method들을 선언해놓고, CRUD 트랜젝션을 제어하는 서비스의 인터페이스
 * 총 12개의 메소드 선언
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */

public interface MemberService {
	
	/**
	 * findMember : 로그인 회원정보 조회
	 * @param member : 입력 비밀번호, 아이디 정보 
	 * @return : 요청한 회원정보을 반환 
	 * 
	 * @author 김영광
	 */
	MemberDTO findMember(MemberDTO member) throws LoginFailedException; 
	
	/**
	 * findAdminMember : 회원 계정 목록 조회
	 * @param selectCriteria : 페이징 정보, 검색정보 
	 * @return : 요청한 현재페이지의 정보와 검색정보 반환
	 * 
	 * @author 김영광
	 */
	List<AdminMemberDTO> findAdminMember(SelectCriteria selectCriteria);
	
	/**
	 * selectTotalCount : 검색정보에 따른 회원의 총 수
	 * @param searchMap : 검색 정보 인원수, 없을 시 총 인원 수
	 * @return : 요청 정보 결과 수 반환
	 * 
	 * @author 김영광
	 */
	int selectTotalCount(Map<String, String> searchMap);

	/**
	 * findDeptJobService : 부서와 직급목록 조회
	 * @return : 부서와 직급목록 전체 반환
	 * 
	 * @author 김영광
	 */
	Map<String, Object> findDeptJobService();

	/**
	 * findTeamList : 부서코드에 따른 팀 목록 조회
	 * @param deptCode : 선택한 부서코드 정보  
	 * @return : 부서 코드에 따른 팀 목록 반환
	 * 
	 * @author 김영광
	 */
	List<TeamDTO> findTeamList(String deptCode);

	/**
	 * adminMemberRegist : 입력된 회원정보 등록
	 * @param adminMember : 입력한 회원정보 
	 * @return : 등록된 회원정보 결과 반환
	 * 
	 * @author 김영광
	 */
	void adminMemberRegist(AdminMemberDTO adminMember) throws MemberRegistException;
	
	/**
	 * findMemberModify : 선택한 회원에 정보 조회
	 * @param id : 선택한 회원 아이디 
	 * @return : 선택한 회원 정보 반환
	 * 
	 * @author 김영광
	 */
	AdminMemberDTO findMemberModify(String id);
	
	/**
	 * memberModify : 수정할 회원정보를 등록
	 * @param adminMember : 수정될 회원 정보  
	 * @return : 수정된 회원 결과 반환
	 * 
	 * @author 김영광
	 */
	void memberModify(AdminMemberDTO adminMember);
	
	/**
	 * removeMember : 선택 회원정보를 삭제
	 * @param id : 삭제할 회원 아이디 정보 
	 * @return : 삭제 요청 후 결과 반환
	 * 
	 * @author 김영광
	 */
	void removeMember(String id);
	
	/**
	 * pwCheck : 비밀번호 일치여부 조회
	 * @param id : 선택한 아이디 정보 
	 * @return : 해당 아이디의 비밀번호 반환
	 * 
	 * @author 김영광
	 */
	String pwCheck(String id);

	/**
	 * pwUpdate : 비밀번호 변경 등록  
	 * @param id : 입력된 아이디 정보
	 * @param hashedPw : 변경될 비밀번호 정보
	 * @return : 해당 아이디의 따른 비밀번호 변경 후 결과 반환
	 * 
	 * @author 김영광
	 */
	void pwUpdate(String id, String hashedPw);
	
	/**
	 * memberInfo : 회원정보 이메일, 핸드폰번호, 이름 변경 등록
	 * @param member : 변경내용이 입력된 이메일 ,핸드폰 번호, 이름 정보
	 * @return : 등록 후 결과 반환
	 * 
	 * @author 김영광
	 */
	void memberInfo(MemberDTO member);





}
