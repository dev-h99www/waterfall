<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
<script src="https://kit.fontawesome.com/621489de5f.js" crossorigin="anonymous"></script>
<script>
	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<main>
		<div class="container-fluid px-4">
                        <br>
                        <br>
                        <h3 class="mt-4"><i class="fa-solid fa-folder-open"></i>산출물</h3>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                               산출물 프로젝트 목록
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                   <colgroup>
                                        <col style="width:20%"/>
                                        <col style="width:30%"/>
                                        <col style="width:20%"/>
                                        <col style="width:20%"/>
                                   </colgroup>
                                   <thead>
                                    
                                        <tr>
                                            <th>No</th>
                                            <th>프로젝트 명</th>
                                            <th>삭제된 산출물 개수</th>
                                            <th>PM</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach var="project" items="${ requestScope.projectList }" varStatus="status">
                                         <tr>
                                             <td><c:out value="${ project.no }"/></td>
                                             <td><c:out value="${ project.name }"/></td>
                                             <td><c:out value="${ project.totalDeletedOutputCount}"/></td>
                                             <td><c:out value="${ project.manager.name}"/></td>
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
$(function() {
   $("#datatablesSimple td").click(function(){
       const projectNo = $(this).parent().children(":eq(0)").text();
       console.log(projectNo)
       location.href = "${ pageContext.servletContext.contextPath }/output/admin/restoreList?projectNo=" + projectNo;
   });
});
</script>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>