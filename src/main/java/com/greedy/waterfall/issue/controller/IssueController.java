package com.greedy.waterfall.issue.controller;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.dto.GuideFileDTO;
import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.exception.issue.IssueModifyException;
import com.greedy.waterfall.common.exception.issue.IssueRemoveException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueNotificationDTO;
import com.greedy.waterfall.issue.model.dto.IssueRegisterDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.issue.model.service.IssueService;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;


/**
 * <pre>
 * Class : IssueController
 * Comment : 이슈 기능의 조회, 추가, 수정, 삭제, 업로드, 다운로드, 알림 기능을 담당하는 클래스
 * 
 * History
 * 2022. 3. 13.  (박성준)
 * </pre>
 * @version 1.1
 * @author 박성준
 */
@RestController
@RequestMapping("/issue")
@SessionAttributes({"adminProjectNo", "taskNo"})		//세션에 키 값 넣어줌
public class IssueController {
	
	private final IssueService issueService;
	
	@Autowired
	public IssueController(IssueService IssueService) {
		this.issueService = IssueService;
	}
	
	/**
	 * projectList : 이슈가 있는 프로젝트의 목록을 반환하며 해당 프로젝트에 이슈 상태에 따른 이슈의 개수를 조회함
	 * @param request : 현재 페이지의 정보를 담고 있는 requestScope의 매개 변수
	 * @param mv : 요청 값을 전송하기 위해 model에 값을 담아주고, 해당 view에 대한 요청 주소를 담아주는 매개변수
	 * @return mv :key&value형태로 요청 값과 요청주소를 반환
	 * 
	 * @author 박성준
	 */
	@GetMapping("/project")
	public ModelAndView selectAllProjectList(HttpServletRequest request, ModelAndView mv) {
		
		List<ProjectIssueCountDTO> allProject = issueService.selectAllProjectList();
		System.out.println("allProject" + allProject);
		
		mv.addObject("allProject", allProject);
		mv.addObject("intent", "/issue/project");
		mv.setViewName("/issue/projectList");
		
		return mv;
	}
	
	/**
	 * adminTaskList : 이슈가 있는 업무의 목록을 반환하며 해당 업무에 이슈 상태에 따른 이슈의 개수를 조회함
	 * @param request :  현재 페이지의 정보를 담고 있는 requestScope의 매개 변수
	 * @param mv : 요청 값을 전송하기 위해 model에 값을 담아주고, 해당 view에 대한 요청 주소를 담아주는 매개변수
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 박성준
	 */
	@GetMapping("/admin/task")
	public ModelAndView adminTaskList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		
		System.out.println("projectNo :" + projectNo);
		
		List<IssueDTO> taskIssueList = issueService.selectIssuesOfTask(projectNo);
		
		mv.addObject("adminProjectNo", projectNo);
		mv.addObject("taskIssueList", taskIssueList);
		mv.addObject("intent", "/issue/task");
		mv.setViewName("/issue/adminTaskList");
		
		return mv;
	}
	
	/**
	 * adminIssueList : 해당 업무의 이슈 목록을 반환하는 메소드
	 * @param taskNo : 해당 업무의 번호
	 * @param request : 현재 페이지의 정보를 담고 있는 requestScope의 매개 변수
	 * @param mv : key&value형태로 요청 값과 요청주소를 반환하기 위한 매개 변수
	 * @return mv : 요청 값을 전송하기 위해 model에 값을 담아주고, 해당 view에 대한 요청 주소를 담아주는 매개변수
	 * 
	 * @author 박성준
	 */
	@GetMapping("/admin/list/{taskNo}")
	public ModelAndView adminIssueList(@PathVariable("taskNo") int taskNo, HttpServletRequest request, ModelAndView mv) {
		
		Integer projectNo = (int) request.getSession().getAttribute("adminProjectNo");
		System.out.println("list에 들어오는 프로젝트 no : " + projectNo);
		
		System.out.println(taskNo);
		
		List<IssueDTO> issueList = issueService.selectIssueList(taskNo);
		
		mv.addObject("projectNo", projectNo);
		mv.addObject("taskNo", taskNo);
		mv.addObject("issueList", issueList);
		mv.addObject("intent", "/issue/list");
		mv.setViewName("/issue/adminIssueList");
		
		return mv;
	}
	
	/**
	 * adminRemoveGuide : 해당 이슈를 삭제 기능 메소드
	 * @param mv : key&value형태로 요청 값과 요청주소를 반환하기 위한 매개 변수
	 * @param request : 현재 페이지의 정보를 담고 있는 requestScope의 매개 변수
	 * @param rttr : 지정된 메시지를 view 페이지에 출력할 매개변수
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 박성준
	 * @throws IssueRemoveException 
	 */
	@GetMapping("/admin/delete")
	public ModelAndView adminRemoveIssue(ModelAndView mv, HttpServletRequest request, 
			RedirectAttributes rttr) throws IssueRemoveException {
		
		int issueNo = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("삭제하기 위해 no 받기 " + issueNo );
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		/* 반환해줄 경로를 찾기 위해서 뽑아낸 업무 번호 */
		int taskNo = issueService.removeIssue(issueNo, loginMemberNo);
		
		rttr.addFlashAttribute("message", "이슈 삭제에 성공하셨습니다.");
		
		mv.addObject("intent", "/issue/admin/delete");
		mv.setViewName("redirect:/issue/admin/list/" + taskNo);
		
		return mv;
		
	}
	
	/**
	 * adminRegistIssue : 관리자 이슈 등록 기능 메소드
	 * @param issue : 등록에 필요한 정보를 담아올 매개변수
	 * @param request : 지정할 페이지의 주소를 담아줄 매개변수
	 * @param mv : key&value 형태의 요청 값과 요청주소를 반환하기 위한 매개 변수
	 * @param taskNo : 이슈 등록에 필요한 해당 업무번호
	 * @param rttr : 지정된 메시지를 view 페이지에 출력할 매개변수
	 * @param multiFiles : 첨부파일 등록에 필요한 파일 매개변수
	 * @return mv : 매개변수 mv 반환
	 * 
	 * @author 박성준
	 */
	@PostMapping("/admin/regist/{taskNo}")
	public ModelAndView adminRegistIssue(@ModelAttribute IssueDTO issue, HttpServletRequest request, ModelAndView mv,
			@PathVariable("taskNo") int taskNo, RedirectAttributes rttr,  @RequestParam List<MultipartFile> multiFiles) {
		System.out.println("업무 넘버는 : " + taskNo);
		issue.setTaskNo(taskNo);
		
		System.out.println("프로젝트 넘버는 :" + issue.getProjectNo());
		
		int writerMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		System.out.println("작성자 넘버는 : " +  writerMemberNo);
		issue.setRegisterNo(writerMemberNo);
		
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 완료 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());

		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
					System.out.println("파일 등록 성공 확인 " + issue);
				}
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
					
					System.out.println("파일 등록 실패 확인 " + issue);
				}
			  }
		
		}
		
		String message = "";
		
		if(issueService.registIssue(issue)) {
			message = "게시글을 등록했습니다.";
			System.out.println(message);
			rttr.addFlashAttribute("message", "이슈 등록에 성공하셨습니다.");
		} else {
			message = "게시글등록에 실패했습니다.";
			System.out.println(message);
			rttr.addFlashAttribute("message", "이슈 등록에 실패하셨습니다.");
		}

		mv.setViewName("redirect:/issue/admin/list/"+taskNo);
		
		return mv;
	}
	
	@GetMapping(value="admin/list/adminIssueDetail", produces="application/json; charset=UTF-8")
	@ResponseBody
	public ModelAndView adminfindIssueDetail(ModelAndView mv, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));		//이슈번호
		System.out.println("detail에 들어오는 이슈 번호 : " + no);
		
		int projectNo = (int) request.getSession().getAttribute("adminProjectNo");
		System.out.println("detail에 들어오는 프로젝트 번호 : " + projectNo);
		
		Map<String, Integer> condition = new HashMap<>();
		
		condition.put("no", no);
		condition.put("projectNo", projectNo);
		
		Map<String, Object> issueDetailMap = issueService.selectIssueDetail(condition);
		
		IssueDTO issueDetail = (IssueDTO) issueDetailMap.get("issueDetail");
		
		List<ProjectMemberDTO> projectMember = (List<ProjectMemberDTO>) issueDetailMap.get("projectMemberList");
		
		System.out.println("상세조회 issueDetail : " + issueDetail);
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		mv.addObject("projectMember", projectMember);
		mv.addObject("issueDetail", gson.toJson(issueDetail));
		mv.addObject("projectMember", gson.toJson(projectMember));
		mv.setViewName("jsonView");
		return mv;
	}
	
	@PostMapping("/admin/update")
	public ModelAndView adminModifyIssue(@ModelAttribute @Nullable IssueDTO issue, HttpServletRequest request,
			RedirectAttributes rttr, ModelAndView mv, @RequestParam List<MultipartFile> multiFiles) throws IssueModifyException {
		
		/* 파일 업로드 */
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 완료 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());
		
		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
					System.out.println("파일 등록 성공 확인 " + issue);
				}
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
					
					System.out.println("파일 등록 실패 확인 " + issue);
				}
			  }
		}
		
		int taskNo = (int) request.getSession().getAttribute("taskNo");
		System.out.println("update에 들어오는 업무번호 : " + taskNo);
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		Map<String, Object> condition = new HashMap<>();
		
		condition.put("issue", issue);
		condition.put("loginMemberNo", loginMemberNo);
		
		issueService.modifyIssue(condition);
		
		System.out.println("modifyIssue : " + issue);
		
		rttr.addFlashAttribute("message", "이슈 수정에 성공하셨습니다");
		
		mv.addObject("intent", "/issue/admin/update");
		mv.setViewName("redirect:/issue/admin/list/" + taskNo);
		
		return mv;
	}
	
	@GetMapping("/list/{taskNo}")
	public ModelAndView selectIssueList(@PathVariable("taskNo") int taskNo, HttpServletRequest request, ModelAndView mv) {
		
		System.out.println(taskNo);
		
		List<IssueDTO> issueList = issueService.selectIssueList(taskNo);
		
		mv.addObject("taskNo", taskNo);
		mv.addObject("issueList", issueList);
		mv.addObject("intent", "/issue/list");
		mv.setViewName("/issue/issueList");
		
		return mv;
	}
	
	@GetMapping("/task")
	public ModelAndView selectTaskList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		System.out.println("projectNo :" + projectNo);

		List<IssueDTO> taskIssueList = issueService.selectIssuesOfTask(projectNo);
		
		mv.addObject("taskIssueList", taskIssueList);
		mv.addObject("intent", "/issue/task");
		mv.setViewName("/issue/taskList");
		
		return mv;
	}
	
	@PostMapping("regist/{taskNo}")
	public ModelAndView registIssue(@ModelAttribute IssueDTO issue, HttpServletRequest request, ModelAndView mv,
			@PathVariable("taskNo") int taskNo, RedirectAttributes rttr,  @RequestParam List<MultipartFile> multiFiles) {
		System.out.println("업무 넘버는 : " + taskNo);
		issue.setTaskNo(taskNo);
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		issue.setProjectNo(projectNo);
		
		int writerMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		System.out.println("작성자 넘버는 : " +  writerMemberNo);
		issue.setRegisterNo(writerMemberNo);
		
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 완료 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());

		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
					System.out.println("이슈 등록 성공 확인 " + issue);
				}
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
					
					System.out.println("이슈 등록 실패 확인 " + issue);
				}
			  }
		}
		
		String message = "";
		if(issueService.registIssue(issue)) {
			message = "게시글을 등록했습니다.";
			System.out.println(message);
			rttr.addFlashAttribute("message", "이슈 등록에 성공하셨습니다");
		} else {
			message = "게시글등록에 실패했습니다.";
			System.out.println(message);
			rttr.addFlashAttribute("message", "이슈 등록에 실패하셨습니다");
		}
		
		mv.setViewName("redirect:/issue/list/"+taskNo);
		return mv;
	}
	
	@GetMapping(value="list/issueDetail", produces="application/json; charset=UTF-8")
	@ResponseBody
	public ModelAndView findIssueDetail(ModelAndView mv, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));		//이슈번호
		System.out.println("detail에 들어오는 이슈 no : " + no);
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		System.out.println("detail에 들어오는 프로젝트 no : " + projectNo);
		
		Map<String, Integer> condition = new HashMap<>();
		
		condition.put("no", no);
		condition.put("projectNo", projectNo);
		
		Map<String, Object> issueDetailMap = issueService.selectIssueDetail(condition);
		
		IssueDTO issueDetail = (IssueDTO) issueDetailMap.get("issueDetail");
		
		List<ProjectMemberDTO> projectMember = (List<ProjectMemberDTO>) issueDetailMap.get("projectMemberList");
		
		System.out.println("상세조회 issueDetail : " + issueDetail);
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		mv.addObject("projectMember", projectMember);
		mv.addObject("issueDetail", gson.toJson(issueDetail));
		mv.addObject("projectMember", gson.toJson(projectMember));
		mv.setViewName("jsonView");
		return mv;
	}
	
	@GetMapping("/download/{fileNo}")
	public ModelAndView downloadFile(@PathVariable("fileNo") String fileNo) throws IOException {
		int no = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		
		IssueFileDTO file = issueService.findFile(no);
		fileInfo.put("filePath", file.getSavedPath());
		System.out.println(file.getSavedPath());
		System.out.println(file.getRandomName());
		fileInfo.put("fileOriginName", file.getOriginalName());
		fileInfo.put("fileRandomName", file.getRandomName());
		return new ModelAndView("fileDownloadView", "downloadFile", fileInfo);
	}
	
	@GetMapping("/deleteFile/{fileNo}")
	public ModelAndView deleteFile(@PathVariable("fileNo") String fileNo, HttpSession session, 
		   ModelAndView mv) throws NumberFormatException, UnsupportedEncodingException {
		
		int taskNo = (int) session.getAttribute("taskNo");
		System.out.println("delete에 들어오는 업무 no : " + taskNo);
		
		int fileNumber = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		IssueFileDTO issueFileDTO = issueService.removeGuideFile(fileNumber); 
		
		String root = session.getServletContext().getRealPath("resources");	
		
		String filePath = root + "/guideUploadFiles";
		
		File file = new File(filePath + "\\" + issueFileDTO.getRandomName());
		
		if(file.exists()) {
			file.delete();
		}
		
		mv.addObject("intent", "/issue/deleteFile");
		mv.setViewName("redirect:/issue/list/" + taskNo);
		
		return mv; 
	}
	

	/**
	 * modifyIssue : 해당 이슈 수정 기능 메소드
	 * @param issue : 수정에 필요한 정보를 담아올 매개변수
	 * @param request : 지정할 페이지의 주소를 담아줄 매개변수
	 * @param rttr : 지정된 메시지를 view 페이지에 출력할 매개변수
	 * @param mv : key&value 형태의 요청 값과 요청주소를 반환하기 위한 매개 변수
	 * @param multiFiles : 
	 * @return mv : 매개변수 mv 반환
	 * 
	 * @author 박성준
	 */
	@PostMapping("/update")
	public ModelAndView modifyIssue(@ModelAttribute @Nullable IssueDTO issue, HttpServletRequest request,
			RedirectAttributes rttr, ModelAndView mv, @RequestParam List<MultipartFile> multiFiles) throws IssueModifyException {
		
		/* 파일 업로드에 필요한 파일 정보 출력 */
		System.out.println("MultiFiles : " + multiFiles);
		/* 파일을 저장할 루트 경로 */
		String root = request.getSession().getServletContext().getRealPath("resources");
		/* 경로 출력 */
		System.out.println("root : " + root);
		/* 이슈에 관련된 파일을 저장할 경로 */
		String filePath = root + "/issueUploadFiles";
		/* 파일 객체 선언 */
		File mkdir = new File(filePath);
		
		/* 폴더가 생성되지 않았으면 폴더 생성 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());
		
		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		
		/* 파일이 전송되었을 경우만 다중 파일에 대한 정보를 IssueDTO 타입 객체에 넣어서 List<IssueFileDTO>에 담음 */
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				/* 파일이 존재하면 해당경로에 파일을 전송 */
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
				}
				System.out.println("이슈 등록 확인 " + issue);
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				/* 파일 전송 실패 시 모든 파일을 삭제 */
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
				}
				System.out.println("이슈 실패 확인 " + issue);
			  }
		}
		
		/* 경로로 반환해 줄 업무 번호  */
		int taskNo = issue.getTaskNo();
		System.out.println("update에 들어오는 업무 no : " + taskNo);
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		/* Service에 Map으로 여러 변수를 넘겨주기 */
		Map<String, Object> condition = new HashMap<>();
		condition.put("issue", issue);
		condition.put("loginMemberNo", loginMemberNo);
		issueService.modifyIssue(condition);
		
		System.out.println("modifyIssue : " + issue);
		
		/* 수정 성공 시 해당 메시지를 view에 전달 */
		rttr.addFlashAttribute("message", "이슈 수정에 성공하셨습니다");
		
		/* 해당 경로 요청 */
		mv.addObject("intent", "/issue/update");
		mv.setViewName("redirect:/issue/list/" + taskNo);
		
		return mv;
	}
	
	@GetMapping("/delete")
	public ModelAndView removeGuide(ModelAndView mv, HttpServletRequest request, 
			RedirectAttributes rttr) throws IssueRemoveException {
		
		int issueNo = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("삭제하기 위해 no 받기 " + issueNo );
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		/* 반환해줄 경로를 찾기 위해서 뽑아낸 업무 번호 */
		int taskNo = issueService.removeIssue(issueNo, loginMemberNo);
		
		rttr.addFlashAttribute("message", "이슈 삭제에 성공하셨습니다");
		
		mv.addObject("intent", "/issue/delete");
		mv.setViewName("redirect:/issue/list/" + taskNo);
		
		return mv;
		
	}
	
	@GetMapping("/notification")
	public ModelAndView notifyIssueList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		int issueHistoryNo = 0;
		
		if(request.getParameter("issueHistoryNo") != null) {
			issueHistoryNo = Integer.parseInt(request.getParameter("issueHistoryNo"));
			System.out.println("issueHistoryNo " + issueHistoryNo);
		}
		
		Map<String, Integer> identification = new HashMap<>();
		
		identification.put("loginMember", loginMemberNo);
		identification.put("issueHistoryNo", issueHistoryNo);
		
		Map<String, Object> notification = issueService.notifyIssueList(identification);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		List<IssueNotificationDTO> notificationList = (List<IssueNotificationDTO>) notification.get("notificationList");
		
		int count = (int) notification.get("count");
		
		mv.addObject("count", gson.toJson(count));
		mv.addObject("notificationList", gson.toJson(notificationList));
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 * taskRegistIssue : 업무에서 이슈 등록 기능 메소드
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 박성준
	 */
	@PostMapping("task/regist/{taskNo}")
	public ModelAndView taskRegistIssue(@ModelAttribute IssueDTO issue, HttpServletRequest request, ModelAndView mv,
			@PathVariable("taskNo") int taskNo, RedirectAttributes rttr,  @RequestParam List<MultipartFile> multiFiles) {
		System.out.println("업무 넘버는 : " + taskNo);
		issue.setTaskNo(taskNo);
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		issue.setProjectNo(projectNo);
		
		int writerMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		System.out.println("작성자 넘버는 : " +  writerMemberNo);
		issue.setRegisterNo(writerMemberNo);
		
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 완료 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());

		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
					System.out.println("이슈 등록 성공 확인 " + issue);
				}
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
					
					System.out.println("이슈 등록 실패 확인 " + issue);
				}
			  }
		}
		
		String message = "";
		if(issueService.registIssue(issue)) {
			message = "게시글을 등록했습니다.";
			System.out.println(message);
			rttr.addFlashAttribute("message", "이슈 등록에 성공하셨습니다");
		} else {
			message = "게시글등록에 실패했습니다.";
			System.out.println(message);
			rttr.addFlashAttribute("message", "이슈 등록에 실패하셨습니다");
		}
		
		mv.setViewName("redirect:/task/timeline");
		return mv;
	}
	
}
