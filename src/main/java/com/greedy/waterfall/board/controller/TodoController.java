package com.greedy.waterfall.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.board.model.service.TodoService;
import com.greedy.waterfall.common.exception.TodoModifyException;
import com.greedy.waterfall.common.exception.TodoRegistException;
import com.greedy.waterfall.common.exception.TodoRemoveException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;

/**
 * <pre>
 * Class : TodoController
 * Comment : 클라이언트의 요청을 처리한 뒤, 결과를 view페이지 todoList.jsp에게 리턴
 * 
 * History
 * 2022. 2. 19.  (차화응) 처음 작성
 * </pre>
 * @version 1
 * @author 차화응
 */
@Controller
@RequestMapping("/todo")
public class TodoController {

	private final TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	/* 게시글 등록 */
	@PostMapping("/regist")
	public String registTodo(@ModelAttribute TodoDTO todo, HttpServletRequest request,
			RedirectAttributes rttr) throws TodoRegistException {
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		todo.setTitle(title);
		todo.setContent(body);
		todo.setProjectNo(((ProjectAuthorityDTO)(request.getSession().getAttribute("projectAutority"))).getProjectNo());
		todo.setMemberNo(((MemberDTO)(request.getSession().getAttribute("loginMember"))).getNo());
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		System.out.println(todo);
		todoService.registTodo(todo);
		
//		rttr.addFlashAttribute("message", "To Do 등록에 성공하셨습니다!");
		
		return "redirect:/todo/list";
	}
	
	/* 게시글 등록 + 다중 파일 업로드 */
//	@PostMapping("/regist")
//	public String registTodo(@ModelAttribute TodoDTO todo, 
//			@RequestParam("todoUpload") List<MultipartFile> todoUpload, 
//			HttpServletRequest request, RedirectAttributes rttr) throws TodoRegistException {
//		
//		/* 게시글 등록 */
//		String title = request.getParameter("title");
//		String body = request.getParameter("body");
//		
//		todo.setTitle(title);
//		todo.setContent(body);
//		
//		/* 다중 파일 업로드 */
//		String root = request.getSession().getServletContext().getRealPath("resources");	//webapp폴더 하위 resources폴더
//		
//		String filePath = root + "\\uploadFiles";	//resources폴더 하위 uploadFiles폴더 생성
//		
//		File mkdir = new File(filePath);
//		if(!mkdir.exists()) {
//			mkdir.mkdirs();
//		}
//		
//		List<Map<String, String>> files = new ArrayList<>();
//		for(int i = 0; i < todoUpload.size(); i++) {
//			/*파일명 변경 처리*/
//			String originFileName = todoUpload.get(i).getOriginalFilename();
//			String ext = originFileName.substring(originFileName.lastIndexOf("."));
//			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
//			
//			Map<String, String> file = new HashMap<>();
//			file.put("originFileName", originFileName);
//			file.put("savedName", savedName);
//			file.put("filePath", filePath);
//			
//			files.add(file);
//		}
//		
//		/*파일 저장*/
//		try {
//			for(int i = 0; i < todoUpload.size(); i++) {
//				Map<String, String> file = files.get(i);
//				todoUpload.get(i).transferTo(new File(filePath + "\\" + file.get("savedName")));
//			}
//			
////			rttr.addFlashAttribute("message", "To Do 다중 파일 업로드에 성공하셨습니다!");
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//			/*실패시 파일 삭제*/
//			for(int i = 0; i < todoUpload.size(); i++) {
//				Map<String, String> file = files.get(i);
//				
//				new File(filePath + "\\" + file.get("savedName")).delete();
//			}
//			
//			rttr.addFlashAttribute("message", "To Do 다중 파일 업로드에 실패하셨습니다!");
//		}
//		
//		todoService.registTodo(todo);
//		
//		return "redirect:/todo/list";
//	}
	
	/**
	 * todoSelectList : 메소드 설명 작성 부분
	 * @param first  : request 클라이언트로부터 서버로 들어온 요청정보를 전달인자로 받음
	 * @param second : mv 컨트롤러가 처리한 결과 정보 및 뷰 선택에 필요한 정보를 담은 매개변수를 전달인자로 받음
	 * @return : mv ModelAndView 타입으로 반환
	 * 
	 * @author 차화응
	 */
	/* 게시글 전체 목록 조회 */
	@GetMapping("/list")
	public ModelAndView todoSelectList(HttpServletRequest request, ModelAndView mv) {
		
		/* 
		 * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
		 * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
		 */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		int projectNo = ((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo();
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		System.out.println("컨트롤러에서 검색조건 확인하기 : " + searchMap);
		
		/* 
		 * 전체 게시물 수가 필요하다.
		 * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
		 * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
		 */
		int totalCount = todoService.selectTotalCount(searchMap);
		
		System.out.println("totalTodoCount : " + totalCount);
		
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 20;		//얘도 파라미터로 전달받아도 된다.
		
		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;
		
		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		selectCriteria.setProjectNo(projectNo);
		
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		System.out.println(selectCriteria);
		
		/* 조회해 온다 */
		List<TodoDTO> todoList = todoService.findTodo(selectCriteria);
		
		System.out.println("todoList : " + todoList);
		
		mv.addObject("todoList", todoList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("/board/todo/todoList");
		
		return mv;
	}
	
	/* 게시글 상세 조회 */
	@GetMapping(value = "todoDetail"/* , produces = "application/json; charset=UTF-8" */)
	@ResponseBody
	public ModelAndView detailTodo(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		TodoDTO todoDetail = todoService.detailTodo(no);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
		        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		        .serializeNulls()
		        .disableHtmlEscaping()
		        .create();
		
		mv.addObject("todoDetail", gson.toJson(todoDetail));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 게시글 수정 */
	@PostMapping("/update")
	public String modifyTodo(@ModelAttribute TodoDTO todo,  
			HttpServletRequest request, RedirectAttributes rttr) throws TodoModifyException {

		todoService.modifyTodo(todo);
		
		rttr.addFlashAttribute("message", "To Do 수정에 성공하셨습니다.");

		return "redirect:/todo/list";
	}
	
	/* 게시글 삭제 */
	@GetMapping("/delete")
	public String removeTodo(HttpServletRequest request, RedirectAttributes rttr) throws TodoRemoveException {

		int no = Integer.parseInt(request.getParameter("no"));
		
		todoService.removeTodo(no);
		
		rttr.addFlashAttribute("message", "To Do 삭제에 성공하셨습니다!");
		
		return "redirect:/todo/list";
	}
}
