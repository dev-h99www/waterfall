<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />
		<div class="taskList">
			<div id="box1header"> 업무 관리 <br>
						<hr>
						
						<div id="box1_body">
							<%-- 상위업무 forEach --%>
							
							<c:forEach var="parentTask" items="${ requestScope.parentTaskList }" varStatus="status" >
								<label id="parentTaskName" type="button" class="folder_toggle" data-toggle="collapse" data-target="#demo${ status.index }"><i style='font-size: 16px; ' class='fas'>&#xf07b;</i>&nbsp;<c:out value="${ parentTask.taskCategory.categoryName }"/></label>
								<br>
								<%--하위 업무 forEach --%>
								<c:forEach var="childList" items="${ requestScope.parentTaskList[ status.index ].childList }" varStatus="st">
								
								<div id="demo${ status.index }" class="collapse" >
										<div id="childTasks${ st.index }">
											<div id="childTask">
												<label class= childName type="button" ><i style='font-size:24px' class='fas'>&#xf0da;</i><c:out value="${ childList.taskCategory.categoryName }"/></label>
												<input id="childDTO${ st.index }" type="hidden" value="${ childList.taskNo }">
											</div>
										</div>
								</div>	
								</c:forEach>
								<%-- <c:out value="${ parentTask.taskCategory.categoryName }"/><br> --%>
							</c:forEach>
						</div>
					</div>
		</div>
</body>
</html>