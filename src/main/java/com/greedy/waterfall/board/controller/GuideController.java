package com.greedy.waterfall.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.dto.GuideFileDTO;
import com.greedy.waterfall.board.model.service.GuideService;
import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRegistException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;

/**
 * <pre>
 * Class : GuideController
 * Comment : 페이징 처리를 위한 값과 가이드 게시판 게시글의 조회, 추가, 수정, 삭제 기능을 담당하는 클래스
 * 
 * History
 * 2022. 2. 21.  (박성준)
 * @version 1.1
 * @author 박성준
 */
@Controller
@RequestMapping("/guide")
public class GuideController {
	
	private final GuideService guideService;

	@Autowired
	private GuideController(GuideService guideService) {
		this.guideService = guideService;
	}
	
	/**
	 * guideList : 조회한 가이드 게시판의 게시글 전체 목록을 view로 전달하는  메소드
	 * @param request : 현재 페이지 정보를 담고 있는 매개 변수
	 * @param mv : 현재 페이지 정보를 담고 있는 매개변수
	 * @return mv : 전달 받은 현재 페이지로 조회한 게시글 전체 목록과, 지정한 view의 주소 반환
	 * 
	 * @author 박성준
	 */
	@GetMapping("/list")
	public ModelAndView guideList( HttpServletRequest request, ModelAndView mv) {
		
		/* 해당 게시판의 프로젝트 번호 */
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		System.out.println("projectNo 있냐? " + projectNo);
		
		/* 현재 페이지, 페이징 처리를 위해 변수 초기화 */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		/* int, Stirng 형, String, String 등 다양한 형태의 맵을 사용해야해서 Object타입의 맵을 사용하였음 */
		Map<Object, Object> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("projectNo", projectNo);
		
		System.out.println("컨트롤러에서 검색조건 확인하기 : " + searchValue);
		
		int totalCount = guideService.selectTotalCount(searchMap);
		
		System.out.println("totalGuideCount : " + totalCount);
		
		/* 페이징 처리 시 화면에 보여질 게시글의 수 */
		int limit = 10;
		
		/* 페이지 당 페이징 버튼 */
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		selectCriteria.setProjectNo(projectNo);
		System.out.println(selectCriteria);
		
		List<GuideDTO> guideList = guideService.selectAllGuideList(selectCriteria);
		
		System.out.println(guideList);
		
		mv.addObject("guideList", guideList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/guide/list");
		mv.setViewName("/board/guide/guideList");
		
		return mv;
	}
	
	/**
	 * registGuide : 가이드 게시글 등록 기능을 담당하는 메소드
	 * @param guide : 등록에 필요한 정보를 담아올 매개변수
	 * @param request : 지정할 페이지의 주소를 담아줄 매개변수
	 * @param rttr : 지정된 메시지를 view 페이지에 출력할 매개변수
	 * @param : singleFile : view 페이지에 등록한 파일을 담아올 매개변수
	 * @return "redirect:/guide/list" : 전달 받은 현재 페이지의 주소가 담긴 문자열을 viewResolver에 반환한다
	 * 
	 * @author 박성준
	 */
	@PostMapping("/regist")
	public String registGuide(@ModelAttribute GuideDTO guide, HttpServletRequest request,
		RedirectAttributes rttr, @RequestParam MultipartFile singleFile) throws GuideRegistException {
		/* 나중에 로그인 기능이 구현되면 HttpServletRequest를 webRequst로 바꿔서 해보자 */
		/* @ModelAttribute는 view에서 넘어온 데이터를 GuideDTO와 바인딩 해주는 역할을 함 */
		/* 첨부파일은 일반적인 request객체에서 값을 꺼내는것이 아닌 multi-part 객체에서 꺼내야된다 */
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		guide.setProjectNo(projectNo);
		
		System.out.println("프로젝트넘버는 : " + projectNo);
		
		int writerMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		System.out.println("작성자 넘버는 : " +  writerMemberNo);
		guide.setWriterMemberNo(writerMemberNo);
		
		
		System.out.println("singleFile : " + singleFile);
		
		/* 절대경로 지정 */
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);		
		
		String filePath = root + "/guideUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		if(singleFile.getSize() > 0) {
			
			/* 파일 명 변경처리 */
			String originFileName = singleFile.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));		//확장자
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			System.out.println("originFileName : " + originFileName);
			System.out.println("savedName : " + savedName);
			
			/* file 정보 저장해서 DTO에 insert */
			GuideFileDTO guideFileDTO = new GuideFileDTO();
			guideFileDTO.setSavedPath(filePath);
			guideFileDTO.setOriginalName(originFileName);
			guideFileDTO.setRandomName(savedName);
			guide.setFile(guideFileDTO);
			
			/* 파일 저장 */
			try {
				singleFile.transferTo(new File(filePath + "\\" + savedName));
				
				System.out.println("등록 용 가이드 확인 " + guide);
				
				guideService.registGuide(guide);
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				/* 실패 시 파일 삭제*/
				new File(filePath + "\\" + savedName).delete();
			}
		
		} else {
			guideService.registGuide(guide);
			
			rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다.");
		}
		
		return "redirect:/guide/list";
	}
	
	/**
	 * removeGuide : 해당 게시글을 삭제하는 메소드
	 * @param request : 현재 페이지의 정보를 담고 있는 requestScope의 매개 변수
	 * @param rttr : 삭제 성공 시 출력할 메시지
	 * @return "redirect:/guide/list" : 전달 받은 현재 페이지의 주소가 담긴 문자열을 viewResolver에 반환한다
	 * 
	 * @author 박성준
	 */
	@GetMapping("/delete")
	public String removeGuide(HttpServletRequest request, 
			RedirectAttributes rttr) throws GuideRemoveException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("삭제하기 위해 no 받기 " + no );
		
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		guideService.removeGuide(no, loginMemberNo);
		
		rttr.addFlashAttribute("message", "가이드 게시판 삭제에 성공하셨습니다.");
		
		return "redirect:/guide/list";
		
	}
	
	/**
	 * modifyGuide : 해당 게시글을 수정하고 파일 업로드 기능을 하는 메소드
	 * @param guide : 수정할 게시글의 정보가 담긴 변수
	 * @param request : 현재 페이지의 정보를 담고 있는 requestScope의 매개 변수
	 * @param rttr : 수정 성공 시 출력할 메시지
	 * @param singleFile : 단일 파일에 대한 정보
	 * @return  "redirect:/guide/list" : 반환할 주소
	 * 
	 * @author 박성준
	 */
	@PostMapping("/update")
	public String modifyGuide(@ModelAttribute GuideDTO guide, HttpServletRequest request,
			RedirectAttributes rttr, @RequestParam MultipartFile singleFile) throws GuideModifyException {
		
		System.out.println("update에 들어오는 가이드 게시글 : " + guide);
		
		int guideNo = guide.getNo();		//게시글 번호
		
		/* 파일업로드는 기존에 파일이 저장 되어 있지 않아야 추가가 가능하다 */
		if(guideService.searchGuideFile(guideNo) == null) {
			
			System.out.println("singleFile : " + singleFile);
			
			/* 절대 경로 */
			String root = request.getSession().getServletContext().getRealPath("resources");		
			
			System.out.println("root : " + root);		
			
			String filePath = root + "/guideUploadFiles";
			
			File mkdir = new File(filePath);
			
			/* 폴더 생성 */
			if(!mkdir.exists()) {
				mkdir.mkdirs();
			}
			
			if(singleFile.getSize() > 0) {
				
				/* 파일 명 변경처리 */
				String originFileName = singleFile.getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));		//확장자
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				
				System.out.println("originFileName : " + originFileName);
				System.out.println("savedName : " + savedName);
				
				/* file 정보 저장해서 DTO에 insert */
				GuideFileDTO guideFileDTO = new GuideFileDTO();
				guideFileDTO.setSavedPath(filePath);
				guideFileDTO.setOriginalName(originFileName);
				guideFileDTO.setRandomName(savedName);
				guide.setFile(guideFileDTO);
				
				/* 파일 저장 */
				try {
					singleFile.transferTo(new File(filePath + "\\" + savedName));
					
					System.out.println("등록 용 가이드 확인 " + guide);
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					
					rttr.addFlashAttribute("message", "파일을 먼저 삭제하세요");
					
					/* 실패 시 파일 삭제*/
					new File(filePath + "\\" + savedName).delete();
				}
			}
		}
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		guide.setLoginMemberNo(loginMemberNo);
		
		guideService.modifyGuide(guide);
		
		System.out.println("modifyGuide : " + guide);
		
		rttr.addFlashAttribute("message", "가이드 게시판 수정에 성공하셨습니다");
		
		return "redirect:/guide/list";
	}
	
	/**
	 * findguideDetail : 해당 게시글을 수정하는 메소드
	 * @param request : 해당 게시글의 번호를 담아올 매개변수
	 * @return gson.toJson(guideDetail) : 첨부파일이 없는 게시글의 정보를 Json으로 반환
	 * @return gson.toJson(guideFileDetail) : 첨부파일이 있는 게시글의 정보를 Json으로 반환
	 * 
	 * @author 박성준
	 */
	@GetMapping(value="guideDetail", produces ="application/json; charset= UTF-8")
    @ResponseBody
    public String findguideDetail(HttpServletRequest request) {
		
		/* 게시글 번호 */
	    int no = Integer.parseInt(request.getParameter("no"));
	    System.out.println("guideDetail에 들어오는 게시글 번호 " + no);
	    
	    GuideDTO guideFileDetail = guideService.selectGuideFileDetail(no);
	    System.out.println("상세조회 guideDetail : " + guideFileDetail);
	    
	    Gson gson = new GsonBuilder()
	          .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
	          .setPrettyPrinting()
	          .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
	          .serializeNulls()
	          .disableHtmlEscaping()
	          .create();
	   
    	return gson.toJson(guideFileDetail); 
   }
	
	/**
	 * downloadFile : 게시글 상세 조회 시 첨부파일 다운로드 기능 메소드
	 * @param fileNo : 해당 첨부파일번호를 가져옴 
	 * @return new ModelAndView("fileDownloadView", "downloadFile", fileInfo) : fileDownloadView 에 downloadFile 라는 이름의 모델을 전송하고 file의 정보를 담은 fileInfo를 전송  
	 * 
	 * @author 박성준
	 */
	@GetMapping("/download/{fileNo}")
	public ModelAndView downloadFile(@PathVariable("fileNo") String fileNo) throws IOException {
		int no = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		
		GuideFileDTO file = guideService.findFile(no);
		fileInfo.put("filePath", file.getSavedPath());
		System.out.println("파일이 저장된 경로를 출력 : " + file.getSavedPath());
		System.out.println("변경 이후 파일이름 출력 : " + file.getRandomName());
		fileInfo.put("fileOriginName", file.getOriginalName());
		fileInfo.put("fileRandomName", file.getRandomName());
		return new ModelAndView("fileDownloadView", "downloadFile", fileInfo);
	}
	
	
	/**
	 * deleteFile : 파일 삭제 기능 메소드
	 * @param fileNo : 삭제할 파일 번호
	 * @param session : 절대경로를 넣기 위해서 세션에서 서블릿 컨텍스트에 대한 정보를 받기 위한 객체
	 * @param rttr : 파일 삭제 시 첨부파일 삭제 메시지를 출력할 세션 객체
	 * @param mv : 요청 주소를 반환하는 매개변수
	 * @return mv : 요청 주소를 반환
	 * 
	 * @author 박성준
	 */
	@GetMapping("/deleteFile/{fileNo}")
	public ModelAndView deleteFile(@PathVariable("fileNo") String fileNo, HttpSession session, 
			RedirectAttributes rttr, ModelAndView mv) throws NumberFormatException, UnsupportedEncodingException {
		int fileNumber = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		GuideFileDTO guideFileDTO = guideService.removeGuideFile(fileNumber); 
		
		String root = session.getServletContext().getRealPath("resources");	
		
		String filePath = root + "/guideUploadFiles";
		
		File file = new File(filePath + "\\" + guideFileDTO.getRandomName());
		
		if(file.exists()) {
			file.delete();
		}
		
		mv.addObject("intent", "/guide/deleteFile");
		mv.setViewName("redirect:/guide/list");
		
		rttr.addFlashAttribute("message", "가이드 게시판 첨부파일 삭제에 성공하셨습니다.");
		
		return mv; 
	}
}