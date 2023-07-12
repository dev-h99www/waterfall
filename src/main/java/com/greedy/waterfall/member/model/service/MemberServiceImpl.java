package com.greedy.waterfall.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.exception.member.MemberRegistException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dao.MemberMapper;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.DeptDTO;
import com.greedy.waterfall.member.model.dto.JobDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.dto.TeamDTO;

/**
 * <pre>
 * Class : MemberServiceImpl
 * Comment : 회원의 로그인을 위한 비밀번호 비교, 회원의 정보 수정(핸드폰, 이메일, 비밀번호변경), 
 * 관리자의 계정 목록 리스트 , 수정(부서,직급,팀), 삭제 
 * 
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * findMember : 로그인 시 데이터테이블에 있는 비밀번호와 입력한 비밀번호를 비교하는 메소드
	 * @param member : 요청한 ip,pwd 정보를 MemberDTO클래스에 필드명 정보가 담긴 변수
	 * @return mapper.selectMember(member) : 요청한 아이디로부터 받아올 회원정보다 담길 member변수
	 * 
	 * @author 김영광
	 */
	@Override
	public MemberDTO findMember(MemberDTO member) throws LoginFailedException {
		if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
			  throw new LoginFailedException("로그인 실패");
		}
		
		return mapper.selectMember(member);
	}


	/**
	 * findAdminMember : 계정 목록 리스트를 매퍼에 요청 후 컨트롤러에 전달해준 메소드  
	 * @param selectCriteria : 페이징과 검색을 위한 매개변수
	 * @return adminMemberList : 요청 받은 정보를 담고 컨트롤러에 반환
	 * 
	 * @author 김영광
	 */
	@Override
	public List<AdminMemberDTO> findAdminMember(SelectCriteria selectCriteria) {
		
		List<AdminMemberDTO> adminMemberList = mapper.findAdminMemberList(selectCriteria);
		
		return adminMemberList;
	}


	/**
	 * selectTotalCount : 회원인원 총 수를 요청 후 반환하기 위한 메소드 
	 * @param searchMap : 검색 내용 정보를 담은 매개변수
	 * @return result : 결과의 대한 회원 수를 컨트롤러에 반환
	 * 
	 * @author 김영광
	 */
	@Override
	public int selectTotalCount(Map<String, String> searchMap) {
		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}

	/**
	 * findDeptJobService : 부서와 직급을 목록을 요청 후 컨트롤러에 반환하는 메소드
	 * @param : 부서 목로과 직급 목록을 요청
	 * @return allList : 부서와 직급의 목록을 담아 컨트롤러에 반환
	 * 
	 * @author 김영광
	 */
	@Override
	public Map<String, Object> findDeptJobService() {

		Map<String, Object> allList = new HashMap<>();
		List<DeptDTO> deptDTO = mapper.findDept(); 
		List<JobDTO> jobDTO = mapper.findJob();
		
		allList.put("deptDTO", deptDTO);
		allList.put("jobDTO", jobDTO);
		
	    return allList;
	}
	
	/**
	 * findTeamList : 부서에 코드의 따른 팀을 목록을 담아 컨트롤러에 반환해줄 메소드 
	 * @param deptCode : 선택한 부서코드 정보가 들어있는 매개변수
	 * @return mapper.findTeamList(deptCode) : 부서코드에 따른 팀 목록을 정보를 담고 반환
	 * 
	 * @author 김영광
	 */
	@Override
	public List<TeamDTO> findTeamList(String deptCode) {

		return mapper.findTeamList(deptCode);
	}

	/**
	 * adminMemberRegist : 입력한 회원 정보를 insert요청하고 결과를 반환받는 메소드    
	 * @param adminMember : 입력한 회원정보가 들어있는 매개변수
	 * @return : member 컨트롤러로 반환
	 * 
	 * @author 김영광
	 */
	@Override
	public void adminMemberRegist(AdminMemberDTO adminMember) throws MemberRegistException {
		
		int adminResult = mapper.adminMemberRegist(adminMember);
		int deptResult = mapper.deptMemberRegist(adminMember);
		int teamResult = mapper.teamMemberRegist(adminMember);
		int jobResult = mapper.jobMemberRegist(adminMember);	
		int memberResult = mapper.memberRegist(adminMember);
		
		if(adminResult < 0 || deptResult < 0 || teamResult < 0 || jobResult < 0 || memberResult < 0) {
			throw new MemberRegistException("회원 등록에 실패하셨습니다.");
		}
	}

	/**
	 * findMemberModify : 선택한 회원에 정보를 출력할 정보를 조회한다.    
	 * @param id : 조회할 아이디 정보가 있는 매개변수
	 * @return findModify : 조회한 아이디 정보를 반환한다. 
	 * 
	 * @author 김영광
	 */
	@Override
	public AdminMemberDTO findMemberModify(String id) {
		
		AdminMemberDTO findModify = new AdminMemberDTO();
		
		findModify = mapper.adminMemberModify(id);
		List<DeptDTO> deptList = mapper.adminDeptMember();
		List<TeamDTO> teamList = mapper.adminTeamMember();
		List<JobDTO> jobList = mapper.adminJobMember();
			
		findModify.setDeptList(deptList);
		findModify.setTeamList(teamList);
		findModify.setJobList(jobList);
		
		return findModify;
	}
	
	/**
	 * memberModify : 변경한 회원정보를 성공여부를 반환하는 메소드이다.   
	 * @param adminMember : 수정할 회원정보가 있는 매개변수
	 * @return : 요청에 따른 결과를 반환한다.
	 * 
	 * @author 김영광
	 */
	@Override
	public void memberModify(AdminMemberDTO adminMember) {
		
		int adminMemberResult = mapper.adminMember(adminMember);
		int deptResult = mapper.adminDept(adminMember);
		int teamResult = mapper.adminTeam(adminMember);
		int jobResult = mapper.adminJob(adminMember);
		int memberResult = mapper.oneMember(adminMember);
		
	}

	/**
	 * removeMember : 관리자가 선택한 아이디 정보를 삭제한다.  
	 * @param id : 삭제할 아이디를 전달받는다.
	 * @return : 삭제 요청 후 결과를 반환
	 * 
	 * @author 김영광
	 */
	@Override
	public void removeMember(String id) {

		int MemberResult = mapper.removeMember(id);
		int MemberInfoResult = mapper.removeMemberInfo(id);
		
	}

	/**
	 * pwCheck : 해당 아이디의 비밀번호를 조회하여 반환하는 메소드
	 * @param id : 요청할 비밀번호를 찾기위한 아이디
	 * @return : 해당 아이디의 비밀번호를 반환한다.
	 * 
	 * @author 김영광
	 */
	@Override
	public String pwCheck(String id) {
		
		return mapper.selectOne(id);
	}

	/**
	 * pwUpdate : 비밀번호 변경을 위한 메소드
	 * @param id : 요청한 아이디의 비밀번호를 바꾸기 위한 변수
	 * @param hashedPw : 암호화된 변경할 비밀번호 업데이트 하기위한 변수
	 * @return : 해당 아이디의 따른 비밀번호 변경 후 반환
	 * 
	 * @author 김영광
	 */
	@Override
	public void pwUpdate(String id, String hashedPw) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pwd", hashedPw);
		
		mapper.pwUpdate(map);
	}

	/**
	 * memberInfo : 회원의 이메일과 핸드폰번호를 insert하기 위한 메소드
	 * @param member : 회원의 이메일과 핸드폰 번호 정보가 저장되어있는 매개변수
	 * @return : 등록 후 결과를 반환
	 * 
	 * @author 김영광
	 */
	@Override
	public void memberInfo(MemberDTO member) {

		mapper.memberInfoUpdate(member);
		
		if(member.getPhone() != null && !"".equals(member.getPhone())) {
			mapper.memberInfoPhone(member);
		}
		
		if(member.getEmail() != null && !"".equals(member.getEmail())) {
			mapper.memberInfoEmail(member);	
		}
		
	}



	

}
