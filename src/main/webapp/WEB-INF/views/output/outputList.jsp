<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#layoutSidenav_content .output h2 {
   height: 50px;
   line-height: 1.5;
   padding-left: 60px;
}

#layoutSidenav_content .output h2 span {
   font-size: 1rem;
   vertical-align: bottom;
}

#layoutSidenav_content .output hr {
   width: 95%;
   margin: 10px auto;
}

#layoutSidenav_content .output .tbl-wrapper {
   width: 95%;
   /* font-size: 1rem; */
}

#layoutSidenav_content .output .tbl-wrapper .write button {
   /* font-size: 1rem; */
   background: #000;
   padding: 4px 18px;
   border-radius: 8px;
}

#layoutSidenav_content .output .tbl-wrapper .paging {
   text-align: center;
}

#layoutSidenav_content .output .tbl-wrapper .paging button {
   /* font-size: 1rem; */
   background: none;
   border: none;
}

#layoutSidenav_content .output .tbl-wrapper .paging button:hover {
   text-decoration: underline;
}

table, th, td {
   border: 1px solid #e5e5e5;
   /* border-collapse: collapse; */
}

#layoutSidenav_content .output .output-tbl {
   width: 100%;
   /*height: 400px;*/
   text-align: center;
   border-left: none;
   border-right: none;
}

#layoutSidenav_content .output .output-tbl tbody tr td /* :first-child, 
#layoutSidenav_content .output .output-tbl tbody tr td:last-child */ {
   border-left: none;
   border-right: none;
}

#layoutSidenav_content .output .output-tbl thead tr {
   background: #f0f0f0;
}

#layoutSidenav_content .output .output-tbl thead tr th /* :first-child, 
#layoutSidenav_content .output .output-tbl thead tr th:last-child */ {
   border-left: none;
   border-right: none;
   border-bottom-color: #999;
}

#layoutSidenav_content .output .output-tbl tr {
   height: 30px;
}

#layoutSidenav_content .output .output-tbl tbody tr:hover {
   background: paleturquoise;
}

#layoutSidenav_content .output .tbl-wrapper .search-area .search-set {
   text-align: center;
}

#layoutSidenav_content .output .tbl-wrapper .search-area .search-set #search-input
   {
   width: 300px;
}

#layoutSidenav_content .output .tbl-wrapper .search-area .search-set button
   {
   background: none;
   border: none;
}
/* 모달 */
.modal-content {
   width: 635px;
   height: 650px;
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
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />

   <!-- 산출물  목록 -->
   <div class="output">
      <h2>
         <span><i class="far fa-clipboard me-1"></i>공지사항</span>
      </h2>
      <hr>
      <div class="tbl-wrapper mx-auto">
         <div class="write">
            <button type="button" class="btn btn-secondary mb-2"
               data-bs-toggle="modal" data-bs-target="#writeModal">
               <i class="far fa-edit me-1"></i>등록
            </button>
         </div>
         <table class="output-tbl">
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

            <c:forEach var="output" items="${ requestScope.outputList }">
               <tbody>
                  <tr class="outputSelect"  id="listArea">
                     <td><c:out value="${ output.no }" /></td>
                     <td><c:out value="${ output.title }" /></td>
                     <td id="read-count"><c:out value="${ output.count }" /></td>
                     <td><c:out value="${ output.registedDate }" /></td>
                     <td><c:out value="${ output.memberName.name }" /></td>
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
            action="${ pageContext.servletContext.contextPath }/output/list"
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
          
</body>
</html>