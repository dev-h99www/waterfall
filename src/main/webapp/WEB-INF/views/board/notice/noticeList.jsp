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
/* 공지사항 게시판 */
#layoutSidenav_content .notice h2 {
   height: 50px;
   line-height: 1.5;
   padding-left: 60px;
}

#layoutSidenav_content .notice h2 span {
   font-size: 1rem;
   vertical-align: bottom;
}

#layoutSidenav_content .notice hr {
   width: 95%;
   margin: 10px auto;
}

#layoutSidenav_content .notice .tbl-wrapper {
   width: 95%;
   /* font-size: 1rem; */
}

#layoutSidenav_content .notice .tbl-wrapper .write button {
   /* font-size: 1rem; */
   background: #000;
   padding: 4px 18px;
   border-radius: 8px;
}

#layoutSidenav_content .notice .tbl-wrapper .paging {
   text-align: center;
}

#layoutSidenav_content .notice .tbl-wrapper .paging button {
   /* font-size: 1rem; */
   background: none;
   border: none;
}

#layoutSidenav_content .notice .tbl-wrapper .paging button:hover {
   text-decoration: underline;
}

table, th, td {
   border: 1px solid #e5e5e5;
   /* border-collapse: collapse; */
}

#layoutSidenav_content .notice .notice-tbl {
   width: 100%;
   /*height: 400px;*/
   text-align: center;
   border-left: none;
   border-right: none;
}

#layoutSidenav_content .notice .notice-tbl tbody tr td /* :first-child, 
#layoutSidenav_content .notice .notice-tbl tbody tr td:last-child */ {
   border-left: none;
   border-right: none;
}

#layoutSidenav_content .notice .notice-tbl thead tr {
   background: #f0f0f0;
}

#layoutSidenav_content .notice .notice-tbl thead tr th /* :first-child, 
#layoutSidenav_content .notice .notice-tbl thead tr th:last-child */ {
   border-left: none;
   border-right: none;
   border-bottom-color: #999;
}

#layoutSidenav_content .notice .notice-tbl tr {
   height: 30px;
}

#layoutSidenav_content .notice .notice-tbl tbody tr:hover {
   background: paleturquoise;
}

#layoutSidenav_content .notice .tbl-wrapper .search-area .search-set {
   text-align: center;
}

#layoutSidenav_content .notice .tbl-wrapper .search-area .search-set #search-input
   {
   width: 300px;
}

#layoutSidenav_content .notice .tbl-wrapper .search-area .search-set button
   {
   background: none;
   border: none;
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

/* 서브모달 */
.my-modal-message {
   line-height: 45px;
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

td {
   height: 30px !important;
}
.btn-group{
	margin : 10px;
}
</style>
<script>
	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
</head>
<body>

   <jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />

   <!-- To Do 게시판 시작 -->
   <div class="notice">
      <h2>
         <span><i class="far fa-clipboard me-1"></i>공지사항</span>
      </h2>
      <hr>
      <div class="tbl-wrapper mx-auto">
         <div class="write">
            <button type="button" class="btn btn-secondary mb-2"
               data-bs-toggle="modal" data-bs-target="#writeModal">
               <c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
               <i class="far fa-edit me-1"></i>등록
               </c:if>
            </button>
         </div>
         <table class="notice-tbl">
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

            <!-- 게시글 목록 조회 -->

            <c:forEach var="notice" items="${ requestScope.noticeList }">
               <tbody>
                  <tr class="noticeSelect"  id="listArea">
                     <td><c:out value="${ notice.no }" /></td>
                     <td><c:out value="${ notice.title }" /></td>
                     <td id="read-count"><c:out value="${ notice.count }" /></td>
                     <td><c:out value="${ notice.registedDate }" /></td>
                     <td><c:out value="${ notice.memberName.name }" /></td>
                  </tr>
               </tbody>
            </c:forEach>

         </table>
         <div class="paging mt-3">
            <!-- 맨 앞으로 이동 -->
            <button id="startPage">
               <i class="fas fa-angle-double-left"></i>
            </button>
            <!-- 이전 페이지 버튼 -->
            <c:if test="${ requestScope.selectCriteria.pageNo <= 1 }">
               <button disabled>
                  <i class="fas fa-angle-left"></i>
               </button>
            </c:if>
            <c:if test="${ requestScope.selectCriteria.pageNo > 1 }">
               <button id="prevPage">
                  <i class="fas fa-angle-left"></i>
               </button>
            </c:if>

            <!-- 페이징 버튼 -->
            <c:forEach var="p"
               begin="${ requestScope.selectCriteria.startPage }"
               end="${ requestScope.selectCriteria.endPage }" step="1">
               <c:if test="${ requestScope.selectCriteria.pageNo eq p }">
                  <button disabled>
                     <c:out value="${ p }" />
                  </button>
               </c:if>
               <c:if test="${ requestScope.selectCriteria.pageNo ne p }">
                  <button onclick="pageButtonAction(this.innerText);">
                     <c:out value="${ p }" />
                  </button>
               </c:if>
            </c:forEach>

            <!-- 다음 페이지 버튼 -->
            <c:if
               test="${ requestScope.selectCriteria.pageNo >= requestScope.selectCriteria.maxPage }">
               <button disabled>
                  <i class="fas fa-angle-right"></i>
               </button>
            </c:if>
            <c:if
               test="${ requestScope.selectCriteria.pageNo < requestScope.selectCriteria.maxPage }">
               <button id=nextPage>
                  <i class="fas fa-angle-right"></i>
               </button>
            </c:if>

            <!--맨 뒤로 -->
            <button id="maxPage">
               <i class="fas fa-angle-double-right"></i>
            </button>
         </div>
         <form
            action="${ pageContext.servletContext.contextPath }/notice/list"
            method="get">
            <div class="search-area">
               <div class="search-set mt-2">
                  <select name="searchCondition" id="searchCondition">
                     <option value="title"
                        ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
                     <option value="content"
                        ${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>내용</option>
                  </select> <input type="search" id="search-input" name="searchValue"
                     value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
                  <button type="submit">
                     <i class="fas fa-search"></i>
                  </button>
               </div>
            </div>
         </form>
      </div>
   </div>

   <jsp:include page="/WEB-INF/views/common/footer.jsp" />

   <!-- Modal HTML  "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
   <div class="modal fade" id="writeModal" data-bs-backdrop="static"
      tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
         <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
         <div class="modal-content" style="top: 172px">
         
            <form action="${ pageContext.servletContext.contextPath }/notice/regist" method="post" encType="multipart/form-data">
            
               <div class="my-modal-header mb-4">
                  <label class="me-2" for="title-write" >제목</label>
                  <input type="text" id="title-write" name="title">
               </div>
               
               <div class="my-modal-body">
               
                  <div class="my-textarea-div mb-3">
                     <textarea name="content" id="my-textarea" cols="30" rows="10"></textarea>
                  </div>
                  
                  <div class="my-modal-footer">
                     <input type="file" id="noticeFile" name="noticeFile" >
                           <br>
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
   <!-- 프로젝트 no, memberNo  -->
        <div class="modal fade" id="readModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                <form action="${ pageContext.servletContext.contextPath }/notice/update" method="post" encType="multipart/form-data">
                   <div class="modal-content" style="top: 172px" >
                        <div class="my-modal-header mb-4">
                            <label class="me-2" for="title-write">제목</label>
                            <input type="text" id="read-title" name="title">
                        </div>
                        <div class="my-modal-body">
                            <div class="my-textarea-div mb-3">
                                <textarea name="content" id="read-content" cols="30" rows="10"></textarea>
                            </div>
                        </div>
                        <div class="my-modal-footer-read">
                           <input type="file" id="noticeFile" name="noticeFile" ><br>
						<div class="btn-group">
							<input type="button" class="btn btn-outline-dark" name="originalName" id="read-originalName">
							<button type="button" class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<div class="dropdown-menu" id="downloadarea"></div>
						</div>
						<br>
                           <c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
                           <button type="submit" class="btn btn-secondary">수정하기</button>
                           <button id="delete" class="btn btn-secondary">삭제하기</button>
                           </c:if>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">돌아가기</button>
                            <input type="hidden" id="read-no" name="no"> 
                        </div>
                    </div>
                 </form>
            </div>
        </div>
        <!-- //게시글 조회 모달  -->
   
   <script>
      const link = "${ pageContext.servletContext.contextPath }/notice/list";
      let searchText = "";

      /* 검색 조건 유무에 따른 경로 처리 */
      if ("${ !empty requestScope.selectCriteria.searchCondition? true: false }") {
         searchText += "&searchCondition=${ requestScope.selectCriteria.searchCondition }";
      }

      /* 검색 내용 유무에 따른 경로 처리 */
      if ("${ !empty requestScope.selectCriteria.searchValue? true: false }") {
         searchText += "&searchValue=${ requestScope.selectCriteria.searchValue }";
      }

      /* 첫 페이지 버튼 click 이벤트 처리 */
      if (document.getElementById("startPage")) {
         const $startPage = document.getElementById("startPage");
         $startPage.onclick = function() {
            location.href = link + "?currentPage=1" + searchText;
         }
      }

      /* 이전 페이지 버튼 click 이벤트 처리 */
      if (document.getElementById("prevPage")) {
         const $prevPage = document.getElementById("prevPage");
         $prevPage.onclick = function() {
            location.href = link
                  + "?currentPage=${ requestScope.selectCriteria.pageNo - 1 }"
                  + searchText;
         }
      }

      /* 다음 페이지 버튼 click 이벤트 처리 */
      if (document.getElementById("nextPage")) {
         const $nextPage = document.getElementById("nextPage");
         $nextPage.onclick = function() {
            location.href = link
                  + "?currentPage=${ requestScope.selectCriteria.pageNo + 1 }"
                  + searchText;
         }
      }

      /* 마지막 페이지 버튼 click 이벤트 처리 */
      if (document.getElementById("maxPage")) {
         const $maxPage = document.getElementById("maxPage");
         $maxPage.onclick = function() {
            location.href = link
                  + "?currentPage=${ requestScope.selectCriteria.maxPage }"
                  + searchText;
         }
      }

      /* 페이지 번호 버튼 click 이벤트 처리 */
      function pageButtonAction(text) {
         location.href = link + "?currentPage=" + text + searchText;
      }
      
      $(function(){
            $("#delete").click(function(){
               const no = $("#read-no").val();
               location.href="${ pageContext.servletContext.contextPath }/notice/delete?no=" + no;
               
            });
         });

      
      if (document.querySelectorAll("#listArea td")) {
         const $tds = document.querySelectorAll("#listArea td");
         console.log($tds);
         for (let i = 0; i < $tds.length; i++) {
            $tds[i].onclick = function() {
               const no = this.parentNode.children[0].innerText;
               const ex = this.parentNode;
               console.log(no);
               
                $.ajax({
                  url :"noticeDetail",
                  type : "get",
                  data : { no : no },
                  success : function(data, textStatus, xhr) {
                     
                        console.log(data);
                        /* console.log(Object.entries(data)); */
                        console.log(data.title);
                        
                        const noticeArray = Object.entries(data);
                        console.log(noticeArray);
                        
                        $("#read-no").val(data.no);
                        $("#read-title").val(data.title);
                        $("#read-content").val(data.content);
                        $("#readModal").modal("show");
                        $("input[name=originalName]").val(data.fileOriginName);
                        ex.children[2].innerText=data.count;
                        
                        var $downloadTag = "<a href='${pageContext.servletContext.contextPath}/notice/download/" + no 
	                     + "' class='dropdown-item' id='downloadNotice'>다운로드</a>";
	 				    var $deleteTag = "<a href='${pageContext.servletContext.contextPath}/notice/deleteFile/" + no
						 + "' class='dropdown-item' id='deleteNoticeFile'>삭제</a>";
						 
	                     $("#downloadarea").empty();
	                     $("#downloadarea").append($downloadTag);
	                     $("#downloadarea").append($deleteTag);
                        
                     }, error:function(data){
                        console.log(data);
                     }
                  });
                
               

                
                
                //
            }
         }
      }
       
   </script>
</body>
</html>