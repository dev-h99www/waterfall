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

  background: none;
  border: none;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button:hover {
  text-decoration: underline;
}
table, th, td {
  border: 1px solid #e5e5e5;

}
#layoutSidenav_content .todo .todo-tbl {
  width: 100%;
  /* height: 400px; */
  text-align: center;
  border-left: none;
  border-right: none;
}
#layoutSidenav_content .todo .todo-tbl tbody tr td {
  border-left: none;
  border-right: none;
}
#layoutSidenav_content .todo .todo-tbl thead tr {
  background: #f0f0f0;
}
#layoutSidenav_content .todo .todo-tbl thead tr th {
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
/* 게시글 조회 모달 */
.my-modal-footer-read {
  text-align: center;
}
.my-modal-footer-read button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
/* 서브모달 */
.my-modal-message {
  line-height: 45px;
}
/* 모달 */
.modal-content {
  width: 635px;
  height: 700px;
  padding: 30px;
}
#title-write {
  width: 440px;
}

#read-title {
 width: 440px;
}

.my-modal-body {
  margin-left: 0px;
}
.my-textarea-div {
  width: 440px;
  height: 430px;
}
#my-textarea {
  display: block;
  width: 100%;
  height: 100%;
}
#read-content {
   display: block;
   width: 100%;
   height: 100%;
}
.my-modal-footer button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
.my-modal-footer button:first-child {
  margin-right: 306px;
}

</style>
<script>
   /* 비즈니스 로직 성공 alert 메시지 처리 */
   const message = '${ requestScope.message }';
   if(message != null && message !== '') {
      alert(message);
   }
</script>
</head>
<body>
		<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
		
		<div class="modal fade" id="writeModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                <div class="modal-content" style="top: 172px">
                    <form action="${ pageContext.servletContext.contextPath }/edu/regist" method="post" encType="multipart/form-data">
                        <div class="my-modal-header mb-4">
                            <label class="me-2" for="title-write">제목</label>
                            <input type="text" id="title-write" name="title">
                        </div>
                        <div class="my-modal-body">
                            <div class="my-textarea-div mb-3">
                                <textarea name="content" id="my-textarea" cols="30" rows="10"></textarea>
                                <input type="file"  name="singleFile" style="margin-top: 20px;">
                            </div>
                            <div class="my-modal-footer" align="right">
                                <button type="submit" class="btn btn-secondary" style="margin-right: 0px;" data-bs-toggle="modal" data-bs-target="#subModal">등록</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            </div>
                        </div>    
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal HTML  -->

        <!-- subModal -->
        <div class="modal fade" id="subModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="left: 150px; top: 300px; width: 300px; height: 150px; margin: 0; padding: 0;">
                    <div class="modal-body align-middle my-modal-message">
                        등록되었습니다.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- //subModal -->

        <!-- 게시글 조회 모달 -->
        <div class="modal fade" id="readModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                <div class="modal-content" style="top: 172px">
                    <form action="${ pageContext.servletContext.contextPath }/edu/update" method="POST">
                        <div class="my-modal-header mb-4">
                            <label class="me-2" for="title-write">제목</label>
                            <input type="text" id="read-title" name="title">
                            <input type ="hidden" id="read-no" name="no">
                            <input type ="hidden" id="read-writerNo" name="writerMemberNo">
                        </div>
                        <div class="my-modal-body">
                            <div class="my-textarea-div mb-3">
                                <textarea name="content" id="read-content" cols="30" rows="10"></textarea>
                            </div>                         
                        </div>
                        <div class="my-modal-footer-read">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">돌아가기</button>
                            <c:if test="${ sessionScope.loginMember.role eq 1 or (!empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo))}">
                            	<input type="button" class="btn btn-secondary" id="delete" value="삭제하기">
                            </c:if>
                            <c:if test="${ sessionScope.loginMember.role eq 1 or (!empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo))}">	
                            	<button type="submit" class="btn btn-secondary">수정하기</button>
                            </c:if>      
						    <div calss="btn-group" style="margin-top: 5%; float: right;">
                            	<input type="button" class="btn btn-outline-dark" name="originalName" id="read-originalName"
  	                        		style="text-overflow:ellipsis; overflow:hidden; width: 326px;">
                            	<button type="button" class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                            		<span class="caret"></span>
                            	</button>
                            	<div class="dropdown-menu" id="downloadarea">
                            	</div>
                            </div>       
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- //게시글 조회 모달  -->
   
   		<div class="todo">
                    <h2><span><i class="far fa-clipboard me-1"></i>교육 게시판</span></h2>
                    <hr>
                    <div class="tbl-wrapper mx-auto">
                        <div class="write">
                    		<c:if test="${ sessionScope.loginMember.role eq 1 or (!empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo))}">
                            	<button type="button" class="btn btn-secondary mb-2" data-bs-toggle="modal" data-bs-target="#writeModal"><i class="far fa-edit me-1"></i>등록</button>
                        	</c:if>
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
                              <c:forEach var="edu" items="${ requestScope.eduList }">
	                                <tr id="listArea" data-bs-toggle="modal" data-bs-target="#readModal">
	                                    <td><c:out value="${ edu.no }"/></td>
	                                    <td><c:out value="${ edu.title }"/></td>
	                                    <td><c:out value="${ edu.count }"/></td>
	                                    <td><c:out value="${ edu.updatedDate }"/></td>
	                                    <td><c:out value="${ edu.writer.name }"/></td>
	                                </tr>
                               </c:forEach> 
                            </tbody>
                        </table>
                       
                        <div class="paging mt-3">                           
                        <!-- 페이지 처리  -->
                       		 <jsp:include page="../../common/paging.jsp" />                       
                        </div>
                       
                        <!-- 검색폼  -->                      
                        <div class="search-area">
                          <form id="loginForm" action="${ pageContext.servletContext.contextPath }/edu/list" method="get">
                             <div class="search-set mt-2">
                              <input type="hidden" name="currentPage" value="1">
                                <select name="searchCondition" id="searchCondition">
                                    <option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
                                    <option value="content" ${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>내용</option>
                                </select>
                                <input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
                                <button type="submit"><i class="fas fa-search"></i></button>
                             </div>
                           </form> 
                        </div>
                    </div>
                </div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		
		<script>
			
		if (document.querySelectorAll("#listArea td")) {
			const $tds = document.querySelectorAll("#listArea td");
			console.log($tds);
			for (let i = 0; i < $tds.length; i++) {
				$tds[i].onclick = function() {
					const no = this.parentNode.children[0].innerText;
					const ex = this.parentNode;
					console.log(no);
					
					 $.ajax({
						url :"eduDetail",
						type : "get",
						data : { no : no },
						success : function(data, textStatus, xhr) {
							const eduDetail = JSON.parse(data.eduDetail);
							
								console.log(eduDetail);
								console.log(Object.entries(eduDetail));
								
								const eduArray = Object.entries(eduDetail);
								
								const $fileNo = eduArray[13][1];
								
								console.log(eduArray);
								$("#read-no").val(eduArray[0][1]);
								$("#read-title").val(eduArray[2][1]);
								$("#read-content").val(eduArray[3][1]);
								$("#read-writerNo").val(eduArray[8][1]);
								$("#read-originalName").val(eduArray[14][1]);
								$("#readModal").modal("show");
								ex.children[2].innerText=eduArray[9][1];
								
								if($fileNo != null) {
									const $downloadTag = "<a href='${pageContext.servletContext.contextPath}/edu/download/" + $fileNo
													      + "' class='dropdown-item' id='downloadedu'>다운로드</a>";
													        									
									$("#downloadarea").empty();
									$("#downloadarea").append($downloadTag);
								
								}
								
								
							}, error:function(data){
								console.log(data);
							}
						});					
				}
			}
		}
		
		$(function() {
			$("#delete").click(function() {
				const no = $("#read-no").val();
				console.log(no);
				location.href="${pageContext.servletContext.contextPath}/edu/delete?no=" + no;
			});
		});
		</script>
</body>
</html>