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
<style>
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
	<jsp:include page="../common/header.jsp"/>
		<main>
		<div class="container-fluid px-4">
                        <br>
                        <br>
                        <h3 class="mt-4"><i class="fa-solid fa-folder-open"></i>삭제 산출물</h3>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                삭제 산출물 목록
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                   <colgroup>
                                        <col style="width:5%"/>
                                        <col style="width:20%"/>
                                        <col style="width:25%"/>
                                        <col style="width:20%"/>
                                        <col style="width:15%"/>
                                        <col style="width:10%"/>
                                   </colgroup>
                                   <thead>
                                    
                                        <tr>
                                        	<th>no</th>
                                            <th>업무 명</th>
                                            <th>산출물 파일명</th>
                                            <th>산출물 내용</th>
                                            <th>등록인</th>
                                            <th>복원</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach var="outputList" items="${ requestScope.outputList }" varStatus="status">
                                         <tr id="listArea">
                                         	<td><c:out value="${ outputList.fileNo }"/></td>
                                            <td><c:out value="${ outputList.taskName }"/></td>
                                            <td><c:out value="${ outputList.originName}"/></td>
                                            <c:forEach var="list" items="${ requestScope.outputList[ status.index ].restoreOutputList }" varStatus="st">
                                            	<td><c:out value="${ list.content }"/></td>
                                            	<td><c:out value="${ list.memberName }"/></td>
                                            </c:forEach>
                                            <td><button id="restoreOutput" class="button float">복원하기</button></td>
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
$(function(){
	/* var arr = new Array();
	<c:forEach var="outputList" items="${ requestScope.outputList }" varStatus="status">
		arr.push({taskNo : "${ outputList.taskNo }"});
	</c:forEach> */
	$("#datatablesSimple td").click(function(){
		const fileNo = $(this).parent().children(":eq(0)").text();
		console.log(fileNo);
		location.href = "${ pageContext.servletContext.contextPath }/output/admin/restoreOutput?fileNo=" + fileNo;
	});
	/* $("#restoreOutput").click(function(){
		location.href = "${ pageContext.servletContext.contextPath }/output/admin/restoreOutput?fileNo=" + fileNo;
	}); */
	
	
});

</script>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>