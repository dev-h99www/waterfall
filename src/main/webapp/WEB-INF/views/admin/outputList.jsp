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
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/621489de5f.js" crossorigin="anonymous"></script>
<style>
<style>
    td {
            vertical-align: middle !important;  
        }
.button {
			@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
			-webkit-appearance: none;
            -moz-appearance: none ;
            appearance: none ;

            margin: 0;
            padding-left: 0.7rem;
            padding-right: 0.7rem;
            padding-top : 0.4rem;
            padding-bottom:0.4rem;

            font-family: "Noto Sans KR ", sans-serif ;
            font-size: 1rem ;
            font-weight: 400;
            text-align: center ;
            text-decoration: none ;

            display: inline-block ;
            width: auto ;

            border: none ;
            border-radius: 4px ;

            cursor: pointer ;
            transition: 0.5s ;

            box-shadow: 3px 3px 3px gray ;

            margin: 10px ;
        
	
}
.float {
	display: inline-block;
	transition-duration: 0.3s;
	transition-property: transform;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	transform: translateZ(0);
	box-shadow: 0 0 1px rgba(0, 0, 0, 0);
}

.float:hover {
	transform: translateY(-7px);
}
#restoreOutput{
	background : #C8B8DD;
	color : white;
}
</style>
</head>
<body>
   <jsp:include page="../common/header.jsp"/>
   
                <main>
                    <div class="container-fluid px-4">
                        <br>
                        <br>
                        <h3 class="mt-4"><i class="fa-solid fa-folder-open"></i>산출물</h3>
                        <button id="restoreOutput" class="button float">산출물 복구</button>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                관리자 프로젝트 목록
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                   <colgroup>
                                        <col style="width:20%"/>
                                        <col style="width:30%"/>
                                        <col style="width:20%"/>
                                        <col style="width:20%"/>
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
                                            <th>프로젝트 명</th>
                                            <th>산출물 총 개수</th>
                                            <th>PM</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach var="project" items="${ requestScope.projectList }" varStatus="status">
                                         <tr>
                                             <td><c:out value="${ project.no }"/></td>
                                             <td><c:out value="${ project.name }"/></td>
                                             <td><c:out value="${ project.totalOutputCount}"/></td>
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
       location.href = "${ pageContext.servletContext.contextPath }/output/admin/detail?projectNo=" + projectNo;
   });
});

$("#restoreOutput").click(function(){
	location.href = "${ pageContext.servletContext.contextPath }/output/admin/projectList";
});
</script>
   <jsp:include page="../common/footer.jsp"/>
</body>
</html>