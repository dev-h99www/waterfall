<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-area-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-bar-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/js/datatables-simple-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-area-demo.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" data-auto-replace-svg="nest"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<style>
<style>
    td {
            vertical-align: middle !important;  
        }
</style>
</head>
<body>
	<c:choose>
 	<c:when test="${ sessionScope.loginMember.role eq 1 }"><jsp:include page="../common/header.jsp"/></c:when>
	<c:otherwise><jsp:include page="../common/inprojectheader.jsp"/></c:otherwise>
	</c:choose>
	
    <main>
    	<button type="button" class="btn btn-pink mb-2" id="backButon" onclick="backButton_click();"><i class="fas fa-backward"></i></button>
        <div class="container-fluid px-4">
            <br>
            <br>
            <h1 class="mt-4"><i class="fas fa-exclamation-circle"></i>이슈</h1>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table me-1"></i>
                    	프로젝트 별 업무 목록
                </div>
                <div class="card-body">
                    <table id="datatablesSimple">
                       <colgroup>
                            <col style="width:10%"/>
                            <col style="width:20%"/>
                            <col style="width:15%"/>
                            <col style="width:15%"/>
                            <col style="width:15%"/>
                            <col style="width:15%"/>
                            <col style="width:10%"/>
                        </colgroup>
                        <!-- <colgroup>
                            <col style="width:10%"/>
                            <col style="width:50%"/>
                            <col style="width:10%"/>
                            <col style="width:10%"/>
                            <col style="width:20%"/>
                        </colgroup> -->
                        <thead>
                        
                            <tr>
                                <th>No</th>
                                <th>업무 명</th>
                                <th>총 이슈</th>
                                <th>해결 전 이슈</th>
                                <th>해결 중인 이슈</th>
                                <th>해결 완료된 이슈</th>
                                <th>담당자</th>
                            </tr>
                        </thead>
                        <tbody>
                    <c:forEach var="task" items="${ requestScope.taskIssueList }" varStatus="status">
                     <tr>
                         <td><c:out value="${ task.task.no }"/></td>
                         <td><c:out value="${ task.taskCode.taskCategoryName }"/></td>
                         <td><c:out value="${ task.allIssueCount }"/></td>
                         <td><c:out value="${ task.pendingIssueCount }"/></td>
                         <td><c:out value="${ task.processingIssueCount }"/></td>
                         <td><c:out value="${ task.completedIssueCount }"/></td>
                         <td><c:out value="${ task.managerName }"/></td>
                     </tr>
				    </c:forEach>
                            
                        </tbody>
                           
                            <tfoot>
                            </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </main>
                
<script>
/* 이전페이지 돌아가기 버튼 */
function backButton_click() {
   console.log("이전 페이지 이동");
   location.href= location.href = "${ pageContext.servletContext.contextPath }/issue/project";
}

$(function() {
	$("#datatablesSimple td").click(function(){
		 const taskNo = $(this).parent().children(":eq(0)").text();
		 console.log(taskNo)
		 /* 첫번째 td에 저장된 값을 taskNo로 해서 전달하는 방식 */
		 /* location.href = "${ pageContext.servletContext.contextPath }/issue/list?taskNo=" + taskNo; */
		 
		 /*PathVariable로 컨트롤러로 전달받는 방식임 */
		location.href = "${ pageContext.servletContext.contextPath }/issue/admin/list/" + taskNo;
	});
});
</script>
   <jsp:include page="../common/footer.jsp"/>
</body>
</html>