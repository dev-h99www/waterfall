<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To Do 게시판</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
<style>

/* 투두 게시판 */
#layoutSidenav_content .todo h2 {
  height: 50px;
  line-height: 1.5;
  padding-left: 60px;
}
#layoutSidenav_content .todo h2 span {
  font-size: 1rem;
  vertical-align: bottom;
}
#layoutSidenav_content .todo hr {
  width: 95%;
  margin: 10px auto;
}
#layoutSidenav_content .todo .tbl-wrapper {
  width: 95%;
  /* font-size: 1rem; */
}
#layoutSidenav_content .todo .tbl-wrapper .write button {
  /* font-size: 1rem; */
  background: #000;
  padding: 4px 18px;
  border-radius: 8px;
}
#layoutSidenav_content .todo .tbl-wrapper .paging {
  text-align: center;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button {
  /* font-size: 1rem; */
  background: none;
  border: none;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button:hover {
  text-decoration: underline;
}
table, th, td {
  border: 1px solid #e5e5e5;
  /* border-collapse: collapse; */
}
#layoutSidenav_content .todo .todo-tbl {
  width: 100%;
  /* height: 400px; */ /* 페이지 처리 시 이슈 발생 */
  text-align: center;
  border-left: none;
  border-right: none;
}
#layoutSidenav_content .todo .todo-tbl tbody tr td/* :first-child, 
#layoutSidenav_content .todo .todo-tbl tbody tr td:last-child */ {
  border-left: none;
  border-right: none;
}
#layoutSidenav_content .todo .todo-tbl thead tr {
  background: #f0f0f0;
}
#layoutSidenav_content .todo .todo-tbl thead tr th/* :first-child, 
#layoutSidenav_content .todo .todo-tbl thead tr th:last-child */ {
  border-left: none;
  border-right: none;
  border-bottom-color: #999;
}
#layoutSidenav_content .todo .todo-tbl tr {
  height: 30px;
}
#layoutSidenav_content .todo .todo-tbl tbody tr:hover {
  background: paleturquoise;
  cursor: pointer;
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set {
  text-align: center;
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set #search-input {
  width: 300px;
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set button {
  background: none;
  border: none;
}

/* 모달 */
.modal-content {
  width: 635px;
  height: 600px;
  padding: 30px;
}
#title-write {
  width: 392px;
}
.my-modal-body {
  margin-left: 0px;
}
.my-textarea-div {
  width: 440px;
  height: 400px;
}
#my-textarea {
  display: block;
  width: 100%;
  height: 100%;
}
.my-modal-footer {
  text-align: right;
}
.my-modal-footer button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
.my-modal-footer button:first-child {
  margin-right: 5px;
}

/* 서브모달 */
.my-modal-message {
  line-height: 45px;
}

/* 게시글 조회 모달 */
#read-title {
  width: 392px;
}
#read-content {
  display: block;
  width: 100%;
  height: 100%;
}
.my-modal-footer-read {
  text-align: right;
}
.my-modal-footer-read input {
  width: 84px;
  height: 36px;
  line-height: 1rem;
}
.my-modal-footer-read button {
  color: #000;
  background: none;
  padding: 5px 25px;
  margin-left: 5px;
}

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
	
	<!-- Modal HTML  "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
	<div class="modal fade" id="writeModal" data-bs-backdrop="static"
		tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
			<div class="modal-content" style="top: 172px">
				<form action="${ pageContext.servletContext.contextPath }/todo/regist" method="POST" encType="multipart/form-data">
					<div class="my-modal-header mb-3">
						<label class="me-2" for="title-write">제목</label>
						<input type="text" id="title-write" name="title">
					</div>
					<div class="my-modal-body">
						<div class="my-textarea-div mb-2">
							<textarea name="body" id="my-textarea" cols="30" rows="10"></textarea>
						</div>
						<!-- <div class="my-modal-upload mb-3">
	            			<input type="file" id="todoUploadInput" name="todoUpload" multiple>
	            		</div> -->
						<div class="my-modal-footer">
							<button type="submit" class="btn btn-secondary"
								data-bs-toggle="modal" data-bs-target="#subModal">등록</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal HTML  -->

	<!-- subModal -->
	<div class="modal fade" id="subModal" data-bs-backdrop="static"
		tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"
				style="left: 150px; top: 300px; width: 300px; height: 150px; margin: 0; padding: 0;">
				<div class="modal-body align-middle my-modal-message">
					등록되었습니다.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	<!-- //subModal -->

	<!-- 게시글 조회 모달 -->
	<div class="modal fade" id="readModal" data-bs-backdrop="static"
		tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
			<div class="modal-content" style="top: 172px">
				<form action="${ pageContext.servletContext.contextPath }/todo/update" method="POST">
					<div class="my-modal-header mb-3">
						<label class="me-2" for="read-title">제목</label>
                        <input type="text" id="read-title" name="title">
                        <input type="hidden" id="read-no" name="no">
					</div>
					<div class="my-modal-body">
						<div class="my-textarea-div mb-2">
							<textarea name="content" id="read-content" cols="30" rows="10"></textarea>
						</div>
						<!-- <div class="my-modal-upload mb-3" id="upload-file-area">
	            		</div> -->
					</div>
					<div class="my-modal-footer-read">
						<input type="button" class="btn btn-secondary" id="delete" value="삭제">
                        <button type="submit" class="btn btn-secondary">수정</button>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- //게시글 조회 모달  -->
	
	<!-- To Do 게시판 시작 -->
	<div class="todo">
		<h2>
			<span><i class="far fa-clipboard me-1"></i>To Do 게시판</span>
		</h2>
		<hr>
		<div class="tbl-wrapper mx-auto">
			<div class="write">
				<button type="button" class="btn btn-secondary mb-2"
					data-bs-toggle="modal" data-bs-target="#writeModal">
					<i class="far fa-edit me-1"></i>등록
				</button>
			</div>
			<table class="todo-tbl">
				<colgroup>
					<col width="10%">
					<col width="55%">
					<col width="10%">
					<col width="15%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th scope="colgroup">글번호</th>
						<th scope="colgroup">제목</th>
						<th scope="colgroup">조회수</th>
						<th scope="colgroup">등록일</th>
						<th scope="colgroup">등록자</th>
					</tr>
				</thead>
				<tbody>
					<!-- <tr data-bs-toggle="modal" data-bs-target="#readModal">
						<td>1</td>
						<td>1번 게시글</td>
						<td>0</td>
						<td>2022-01-01</td>
						<td>박성준</td>
					</tr> -->
					<c:forEach var="todo" items="${ requestScope.todoList }">
						<tr id="listArea" data-bs-toggle="modal" data-bs-target="#readModal">
							<td><c:out value="${ todo.no }" /></td>
							<td><c:out value="${ todo.title }" /></td>
							<td><c:out value="${ todo.count }" /></td>
							<td><c:out value="${ todo.postingDate }" /></td>
							<td><c:out value="${ todo.writer.name }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- <div class="paging mt-3">
				<button>
					<i class="fas fa-angle-double-left"></i>
				</button>
				<button>
					<i class="fas fa-angle-left"></i>
				</button>
				<button>1</button>
				<button>2</button>
				<button>3</button>
				<button>4</button>
				<button>5</button>
				<button>
					<i class="fas fa-angle-right"></i>
				</button>
				<button>
					<i class="fas fa-angle-double-right"></i>
				</button>
			</div> -->
			
			<!-- 페이지 처리 -->
			<jsp:include page="../../common/todoPaging.jsp"/>

			<!-- 검색 폼 -->
			<div class="search-area">
				<div class="search-set mt-2">
					<!-- <select name="searchcat" id="searchcat">
						<option value="제목">제목</option>
						<option value="내용">내용</option>
					</select> <input type="search" id="search-input"> -->
					<form id="loginForm"
						action="${ pageContext.servletContext.contextPath }/todo/list"
						method="GET" style="display: inline-block">
						<input type="hidden" name="currentPage" value="1">
						<!-- 검색 드롭다운 목록 -->
						<select id="searchCondition" name="searchCondition">
							<option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
							<option value="content" ${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>내용</option>
							<%-- <option value="writer" ${ requestScope.selectCriteria.searchCondition eq "writer"? "selected": "" }>등록자</option> --%>
						</select>
						<!-- 검색 입력 필드 -->
						<input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
						<!-- 찾기 버튼 -->
						<button type="submit">
							<i class="fas fa-search"></i>
						</button>
					</form>
				</div>
			</div>
			<!-- 검색 폼 끝 -->
		</div>
	</div>
	<!-- To Do 게시판 끝 -->

	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
	
		/* 상세 조회 모달 */
		if(document.querySelectorAll("#listArea td")) {
		      const $tds = document.querySelectorAll("#listArea td");
		      console.log($tds);
		      for(let i = 0; i < $tds.length; i++) {
		         $tds[i].onclick = function() {
		            const no = this.parentNode.children[0].innerText;
//		            const ex = this.parentNode;
		            const count = this.parentNode.children[2];
		            console.log(no);
		            
		            $.ajax({
//		               url : "todoDetail",
		               url: "${ pageContext.servletContext.contextPath }/todo/todoDetail?no="+ no,
		               type : "get",
		               data : { no : no },
//		               success : function(data, textStatus, xhr) {
		               success: function(data, status, xhr) {
		             	  console.log(data);
//		                  const todoDetail = JSON.parse(data.todoDetail);
		             	  todoDetail = JSON.parse(data.todoDetail);
		                  
//		                  for(let index in todoDetail) {
						     $("#upload-file-area").empty();
		                     $("#read-no").val(todoDetail.no);
		                     console.log( $("#read-no").val());
		                     $("#read-title").val(todoDetail.title);
		                     $("#read-content").val(todoDetail.content);
		                     $("#readModal").modal("show");
//		                     ex.children[2].innerText = todoDetail.count;
							 count.innerText = todoDetail.count;
//		                  }

							 if(todoDetail.file != null) {
								for(let i = 0; i < todoDetail.file.length; i++) {
									const $fileName = todoDetail.file[i].fileOriginName;
									const $fileNo = todoDetail.file[i].fileNo;
									console.log($fileNo);
									console.log($fileNo);
									console.log($fileNo);
									console.log($fileNo);
									console.log($fileNo);
									console.log($fileNo);
									console.log($fileNo);
									console.log($fileNo);
									const $fileTag = "<a href='"+'${ pageContext.servletContext.contextPath }/todo/download/' + $fileNo + "'>"+$fileName+"</a>";
									$("#upload-file-area").append($fileTag);
									$("#upload-file-area").append("<br>");
								}
							 }
							 
//		               }, error : function(data) {
//		                     console.log(data);
//		                  }
					   }, error: function(xhr, status, error) {
						   	 console.log(xhr);
					      }
		            });
		            
		         }
		      }
		} 
		   
		   
		/* 삭제 버튼 클릭 게시글 삭제 */
		$("#delete").click(function() {
			const no = $("#read-no").val();
			console.log(no);
			location.href="${ pageContext.servletContext.contextPath }/todo/delete?no=" + no;
		});
	   
	</script>
</body>
</html>