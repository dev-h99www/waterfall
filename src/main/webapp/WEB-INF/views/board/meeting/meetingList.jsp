<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<style>
#downloadZone #read-originalName {
  width:250px;
  overflow:hidden
  white-space:nowrap
  text-overflow: ellipsis;
}
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
/*   height: 400px; */
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

th {
    text-align: inherit;
    text-align: -webkit-match-parent;
    
    height: 50px;
}
td {
    height: 50px;
}
</style>





</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
	<jsp:include page="/WEB-INF/views/board/meeting/modal.jsp"/>
	<div class="todo">
                    <h2><span><i class="far fa-clipboard me-1"></i>회의록 게시판</span></h2>
                    <hr>
                    <div class="tbl-wrapper mx-auto">
                        <div class="write">
                            <button type="button" class="btn btn-secondary mb-2" data-bs-toggle="modal" data-bs-target="#writeModal"><i class="far fa-edit me-1"></i>등록</button>
                        </div>
                        <table class="todo-tbl" id="meetingTable">
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
                            <c:forEach var="meeting" items="${ meetingList }">
                            	<tr class="mt-2" data-bs-toggle="modal" data-bs-target="#readModal">
                            		<td><c:out value="${ meeting.no }" /></td>
                            		<td><c:out value="${ meeting.title }" /></td>
                            		<td><c:out value="${ meeting.boardCount }" /></td>
                            		<td><c:out value="${ meeting.updatedDate }" /></td>
                            		<td><c:out value="${ meeting.member.memberName }" /></td>
                            	</tr>
                            </c:forEach>
                            </tbody>
                        </table>
						<jsp:include page="/WEB-INF/views/common/paging.jsp"/>	
                        <div class="search-area">
                            <div class="search-set mt-2">
	                            <form action="${ pageContext.servletContext.contextPath }/meeting/list" >
									<select id="searchCondition" name="searchCondition">
										<option value="writer" ${ requestScope.selectCriteria.searchCondition eq 'writer'? 'selected': '' }>작성자</option>
										<option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
										<option value="content" ${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>내용</option>
									</select> <input type="search" id="searchValue" name="searchValue">
									<button class="btn btn-bs" type="submit"><i class="fas fa-search"></i></button>
								</form>
                            </div>
                        </div>
                    </div>
                </div>
						                	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
	
	
	<script>
		if(document.querySelectorAll("#meetingTable")){
			const $tds = document.querySelectorAll("#meetingTable td");
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onmouseenter = function() {
					this.parentNode.style.cursor = "pointer";
				}
				
				$tds[i].onclick = function() {
					const no = this.parentNode.children[0].innerText;
					const count = this.parentNode.children[2];
					$.ajax({
						type: 'get',
						url: "${pageContext.servletContext.contextPath}/meeting/detail/"+ no,
						success: function(data, status, xhr) {
							meeting = JSON.parse(data.meeting);
							$("#downloadZone").empty();
							$("#read-no").val(meeting.no);
							$("#read-title").val(meeting.title);
							$("#read-content").val(meeting.content);
							$("readModal").modal("show");
							count.innerText = meeting.boardCount;
							if(meeting.file != null) {
								for(let i = 0;i < meeting.file.length; i++) {
									const $fileName = meeting.file[i].fileOriginName;
									const $fileNo = meeting.file[i].fileNo;
				                     $buttonsTag = "<div class='mt-4 row'><div class='col-3 center' style='vertical-align: top;''><label>첨부파일</label></div><div class='col-3'><div class='btn-group' id='attaachmentNameArea'><input type='button' class='btn btn-outline-dark' id='read-originalName' name='originalName' value='" + $fileName + "'><button type='button' class='btn btn-outline-dark dropdown-toggle dropdown-toggle-split' data-toggle='dropdown'><span class='caret'></span></button><div class='dropdown-menu' id='downloadArea'><a class='dropdown-item' href='${pageContext.servletContext.contextPath}/meeting/download/" + $fileNo + "'>다운로드</a><a class='dropdown-item' href='${pageContext.servletContext.contextPath}/meeting/deleteFile/" + $fileNo + "'>삭제</a></div></div></div></div>";

									$("#downloadZone").append($buttonsTag);
								}
							}
						},
						error: function(xhr, status, error) {
							console.log(xhr);
							
						}
					});
				}
			}
		}
	</script>
</body>
</html>
 


























