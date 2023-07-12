<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
/* 게시판 */
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
  /* height: 400px; */
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
  height: 720px;
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
/* 다운로드 할 파일 이름이 길 경우 모달 밖으로 삐져나오는것을 방지함 */
#read-originalName {
  width:300px;
}


/* 검색 인풋 버튼 */ 
/* input::-ms-clear,
input::-ms-reveal{
   display:none;width:0;height:0;
} */

/* input::-webkit-search-decoration,
input::-webkit-search-cancel-button,
input::-webkit-search-results-button,
input::-webkit-search-results-decoration{
   display:none;
} */

/* input::-webkit-search-cancel-button {
   display:none;
} */

</style>
<script>
   /* 비즈니스 로직 성공 alert 메시지 처리 */
   const message = '${ requestScope.message }';
   if(message != null && message !== '') {
      alert(message);
   }
</script>
</head>
<body class="sb-nav-fixed">
   <jsp:include page="../../common/inprojectheader.jsp"/>
      
      <!-- 게시글 등록 모달 -->
       <!-- Modal HTML  "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
        <div class="modal fade" id="writeModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                <div class="modal-content" style="top: 172px">
                    <form action="${ pageContext.servletContext.contextPath }/guide/regist" method="POST" encType="multipart/form-data"> 
                        <div class="my-modal-header mb-4">
                            <label class="me-2" for="title-write">제목</label>
                            <input type="text" id="title-write" name="title" placeholder="제목을 입력하세요" required>
                        </div>
                        <div class="my-modal-body">
                            <div class="my-textarea-div mb-3">
                                <textarea name="content" id="my-textarea" cols="30" rows="10" placeholder="내용을 입력하세요" required></textarea>
                                <input type="file"  name="singleFile">
                            </div>
                            <br>
                            <div class="my-modal-footer-read">
                                <button type="submit" class="btn btn-secondary">등록</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            </div>
                        </div>    
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal HTML  -->

        <!-- subModal
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
 -->

        

       <!-- 가이드 게시판 시작 -->
                <div class="todo">
                    <h2><span><i class="far fa-clipboard me-1"></i>가이드 게시판</span></h2>
                    <hr>
                    <div class="tbl-wrapper mx-auto">
                        <div class="write">
                           <c:if var="guide" test="${ sessionScope.loginMember.role eq 1 or ( !empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo))}">
                               <button type="button" class="btn btn-secondary mb-2" data-bs-toggle="modal" data-bs-target="#writeModal"><i class="far fa-edit me-1"></i>등록</button>
                                <!-- 검색 후 전체 게시글을 보려면 뒤로가기나 경로 기술 외에는 돌아갈 방법이 없어서 페이지 갱신 목적으로 버튼 만들어서 해결함-->
                               <button type="button" class="btn btn-secondary mb-2" id="backButon" onclick="backButton_click();"><i class="fas fa-undo"></i></button>
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
                                <c:forEach var="guide" items="${ requestScope.guideList }" varStatus="status">
                                <tr  id="listArea" class="guideSelect">
                                    <td><c:out value="${ guide.no }"/></td>
                                    <td><c:out value="${ guide.title }"/></td>
                                    <td><c:out value="${ guide.count}"/></td>
                                    <td><c:out value="${ guide.updatedDate }"/></td>
                                    <td><c:out value="${ guide.writer.name }"/></td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                                <!-- 게시글 조회 모달 -->
                          <div class="modal fade" id="readModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                              <div class="modal-dialog">
                                  <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                                  <div class="modal-content" style="top: 172px">
                                      <form action="${ pageContext.servletContext.contextPath }/guide/update" method="POST" encType="multipart/form-data">
                                          <div class="my-modal-header mb-4">
                                              <label class="me-2" for="title-write">제목</label>
                                              <input type="text" id="read-title" name="title" required>
                                              <input type="hidden" id="read-no" name="no">
                                              <input type="hidden" id="read-writerNo" name="writerMemberNo">
                                              <input type="hidden" id="read-projectNo" name="projectNo">
                                          </div>
                                          <div class="my-modal-body">
                                              <div class="my-textarea-div mb-3">
                                                  <textarea name="content" id="read-content" cols="30" rows="10" required></textarea>
                                              </div>
                                              <c:if var="guide" test="${ sessionScope.loginMember.role eq 1 or ( !empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo))}">
                                              <div id="uploadZone">
									              <input type="file"  name="singleFile">
									          </div>
									          </c:if>
                                              <div id="downloadZone">
							                 </div>
                                          </div>
                                          <br>
                                          <div class="my-modal-footer-read">
                                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">돌아가기</button>
                                              <c:if var="guide" test="${ sessionScope.loginMember.role eq 1 or ( !empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo))}">
                                                 <input type="button" class="btn btn-secondary" id="delete" value="삭제하기">
                                              </c:if>
                                              <c:if test="${ sessionScope.loginMember.role eq 1 or (!empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo)) }">
                                                 <button type="submit" class="btn btn-secondary">수정하기</button>
                                              </c:if> 
                                          </div>
                                      </form>
                                  </div>
                              </div>
                          </div>
                          <!-- //게시글 조회 모달  -->
                        <div class="paging mt-3">
                            <jsp:include page="../../common/paging.jsp"/>
                        </div>
                        <div class="search-area">
                           <form id="loginForm" action="${ pageContext.servletContext.contextPath }/guide/list" method="get">
                            <div class="search-set mt-2">
                            	<input type="hidden" name="currentPage" value="1">
                                <select name="searchCondition" id="searchCondition">
                                    <option value="title" 
                                       ${ requestScope.selectScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
                                    <option value="content" 
                                       ${ requestScope.selectScope.selectCriteria.searchCondition eq "content"? "selected": "" }>내용</option>
                                </select>
                                <input type="search" id="searchvalue" name="searchValue"
                                   value="<c:out value="${ requestScope.selectCriteria.searchValue }" />">
                                <button type="submit" id="submitButton"><i class="fas fa-search"></i></button>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- 가이드 게시판 끝 -->
          
<script>
$("#read-originalName").mouseenter(
		
);


function manualValidate(ev) {
    ev.target.checkValidity();
    return false;
}
$("#form").bind("submit", manualValidate);

 $(function(){
   $("#submitButton").click(function(){
      console.log("검색 버튼을 눌렀습니다.")
      
      url = $("#searchValue").val();
       if( url == null ) {
         console.log("비어있음");
         location.href="${ pageContext.servletContext.contextPath }/guide/list";
         
      } else {
         console.log("값 있음");
      }
   });
}); 

/* 페이지 갱신 버튼 */
function backButton_click() {
   console.log("gude/list 이동");
   location.href="${ pageContext.servletContext.contextPath }/guide/list";
}

/* 게시글 삭제 이벤트 */
$(function(){
      $("#delete").click(function(){
         const no = $("#read-no").val();
         location.href="${ pageContext.servletContext.contextPath }/guide/delete?no=" + no;
         
      });
});

$(function() {
   const $tds = document.querySelectorAll("#listArea td");   /* 이벤트 클릭 했을 때의 this  */
   console.log($tds);
   for (let i = 0; i < $tds.length; i++) {
      $tds[i].onclick = function() {
         const no = this.parentNode.children[0].innerText;
         const ex = this.parentNode; // this는 td의 부모인 tr
         console.log(no);
         
          $.ajax({
            url :"guideDetail",
            type : "get",
            data : { no : no },
            success : function(data, textStatus, xhr) {
               
                  console.log(data);
                  console.log(Object.entries(data));
                  
                  const guideArray = Object.entries(data);
                  
                  console.log(guideArray[3][1]);
                  const $fileNo = guideArray[14][1];
                  console.log($fileNo);
                  
                  $("#read-no").val(guideArray[0][1]);      
                  $("#read-title").val(guideArray[2][1]);
                  $("#read-content").val(guideArray[3][1]);
                  $("#read-writerNo").val(guideArray[8][1]);
                  $("#read-originalName").val(guideArray[15][1]);
                  $("#read-projectNo").val(guideArray[5][1]);
                  $("#readModal").modal("show");
                  ex.children[2].innerText=guideArray[10][1];      //ex가 tr이고 행 전체의 2번 인덱스에 guideArray 9번째 배열의 1번 인덱스, count
                  
                  $("#downloadZone").empty();
                  console.log(guideArray[13]);
                  if(guideArray[13][1].length != 0) {
                     
                     const $fileNo = guideArray[14][1];
                     
                     $buttonsTag = "<div class='mt-4 row'><div class='col-3 center' style='vertical-align: top;''><label>첨부파일</label></div><div class='col-3'><div class='btn-group' id='attaachmentNameArea'><input type='button' title='" + guideArray[13][1].originalName + "' class='btn btn-outline-dark' id='read-originalName' name='originalName' value='" + guideArray[15][1] + "'><button type='button' class='btn btn-outline-dark dropdown-toggle dropdown-toggle-split' data-toggle='dropdown'><span class='caret'></span></button><div class='dropdown-menu' id='downloadArea'><a class='dropdown-item' href='${pageContext.servletContext.contextPath}/guide/download/" + $fileNo + "'>다운로드</a><a class='dropdown-item' href='${pageContext.servletContext.contextPath}/guide/deleteFile/" + $fileNo + "'>삭제</a></div></div></div></div>";
             		 $("#downloadZone").append($buttonsTag);
                  }
              }, 
              error:function(data) {
                  console.log(data);
               }
          });
         }
   }
});

</script>
   <jsp:include page="../../common/footer.jsp"/>
</body>
</html>