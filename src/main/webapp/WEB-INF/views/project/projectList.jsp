<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
   /* 비즈니스 로직 성공 alert 메시지 처리 */
   const message = '${ requestScope.message }';
   if(message != null && message !== '') {
      alert(message);
   }
</script>
<style>
	tr {
		height: 24px;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container-fluid px-4">
		<h1 class="mt-4">
			<i class="fas fa-project-diagram"></i> 프로젝트 목록
		</h1>
		<div class="mb-2">
			<c:if test="${ !empty projectList.manageProject or sessionScope.loginMember.role eq 1}">
				<div class="col" style="width: 50%; text-align: left;">
					<button type="button" onclick="location.href='${ pageContext.servletContext.contextPath }/project/manage'" class="btn btn-outline-secondary">프로젝트 관리</button>
				</div>
			</c:if>	
		</div>
		
		<c:if test="${ !empty projectList.manageProject or requestScope.selecCriteria.searchCondition ne '' }">
			<div class="card mb-4 mt-3">
				<div class="card-header" style="width: 100%;">
					<div class="row">
						<div class="col"
							style="width: 50%; text-align: left; font-weight: bold; font-size: 1.3em">
							<label>관리중인 프로젝트</label>
						</div>
						<div class="col" style="margin-left: 50%">
							<form action="${ pageContext.servletContext.contextPath }/project/list" >
								<select id="searchCondition" name="searchCondition">
									<option value="projectName" ${ requestScope.selectCriteria.searchCondition eq "projectName"? "selected": "" }>프로젝트명</option>
									<c:if test="${ sessionScope.loginMember.role eq 1 }">
										<option value="pmName" ${ requestScope.selectCriteria.searchCondition eq "pmName"? "selected": "" }>PM이름</option>
									</c:if>
								</select> 
								<input type="search" id="searchValue" name="searchValue" value="${ requestScope.selectCriteria.searchValue }">
								<button class="btn btn-bs" type="submit"><i class="fas fa-search"></i></button>
							</form>	
						</div>
					</div>
				</div>
				<div class="card-body">
					<table style="width: 100%;font-size:1.1em; text-align: center;">
						<colgroup>
							<col style="width: 20%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 1%" />
		
						</colgroup>
						<thead>
							<tr>
								<th>프로젝트명</th>
								<th>담당자</th>
								<th>진행률</th>
								<th>산출물</th>
								<th>이슈</th>
								<th>시작일</th>
								<th>마감일</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="project" items="${ projectList.manageProject }">
								<tr>
									<td><c:out value="${ project.name}" /></td>
									<td>
										<c:if test="${ project.member.memberStatus eq 'Y' }">
											<c:out value="${ project.member.memberName}" />
										</c:if>
										<c:if test="${ project.member.memberStatus eq 'N' }">
											미지정
										</c:if>
									</td>
									<td><c:out value="${ project.progression }" /></td>
									<td><c:out value="${ project.projectInfo.outputAmount }" /></td>
									<td><c:out value="${ project.projectInfo.totalIssueAmount }" /></td>
									<td><c:out value="${ project.startDate }" /></td>
									<td><c:out value="${ project.deadLine }" /></td>
									<td><input type="hidden" value="${ project.no }" name="projectNo"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<jsp:include page="/WEB-INF/views/project/projectmanagepaging.jsp"/>
				<br>
			</div>
		</c:if>
		<c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.role ne '1'}">
			<div class="card mb-4 mt-3">
				<div class="card-header">
					<div class="row">
						<div class="col">
							<label style="font-size: 1.3em; font-weight: bold">참여중인 프로젝트</label>
						</div>
						<div class="col" style="margin-left: 50%">
							<form action="${ pageContext.servletContext.contextPath }/project/list" >
								<select id="subsearchCondition" name="subsearchCondition">
									<option value="projectName" ${ requestScope.selectCriteria.searchCondition eq "projectName"? "selected": "" }>프로젝트명</option>
									<c:if test="${ sessionScope.loginMember.role eq 1 }">
										<option value="pmName" ${ requestScope.selectCriteria.searchCondition eq "pmName"? "selected": "" }>PM이름</option>
									</c:if>
								</select> 
								<input type="search" id="subsearchValue" name="subsearchValue" value="${ requestScope.subselectCriteria.searchValue }">
								<button class="btn btn-bs" type="submit"><i class="fas fa-search"></i></button>
							</form>	
						</div>
					</div>	
				</div>
				<div class="card-body">
					<table style="width: 100%; text-align: center;">
						<colgroup>
							<col style="width: 20%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 10%" />
							<col style="width: 1%" />
						</colgroup>
		
						<thead>
							<tr>
								<th>프로젝트명</th>
								<th>담당자</th>
								<th>진행률</th>
								<th>산출물</th>
								<th>이슈</th>
								<th>시작일</th>
								<th>마감일</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="project" items="${ projectList.joinProject }">
								<tr>
									<td><c:out value="${ project.name}" />
									<td>
										<c:if test="${ project.member.memberStatus eq 'Y' }">
											<c:out value="${ project.member.memberName}" />
										</c:if>
										<c:if test="${ project.member.memberStatus eq 'N' }">
											미지정
										</c:if>
									</td>
									<td><c:out value="${ project.progression }" /></td>
									<td><c:out value="${ project.projectInfo.outputAmount }" /></td>
									<td><c:out value="${ project.projectInfo.totalIssueAmount }" /></td>
									<td><c:out value="${ project.startDate }" /></td>
									<td><c:out value="${ project.deadLine }" /></td>
									<td><input type="hidden" value="${ project.no }" name="projectNo"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
					<jsp:include page="/WEB-INF/views/project/subpaging.jsp"/>
					<br>
			</div>
		</c:if>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
	    $('#datatablesSimple tbody tr').click(function () {  
	        // get position of the selected row  
	        var position = $('#datatablesSimple').dataTable().fnGetPosition(this)  
	        // value of the first column (can be hidden)  
	        var id = $('#datatablesSimple').dataTable().fnGetData(position).id
	        // redirect
	        document.location.href = "프로젝트수정페이지.html";
	    })
	
	
	    
	    if(document.getElementsByTagName("td")) {
	        
	        const $tds = document.getElementsByTagName("td");
	        for(let i = 0; i < $tds.length; i++) {
	            
	            $tds[i].onmouseenter = function() {
	                this.parentNode.style.backgroundColor = "rgba(29, 22, 22, 0.106)";
	                this.parentNode.style.cursor = "pointer";
	            }
	            
	            $tds[i].onmouseout = function() {
	                this.parentNode.style.backgroundColor = "white";
	                this.parentNode.style.color = "black";
	            }
	            
	            $tds[i].onclick = function() {
	                const projectNo = this.parentNode.children[7].children[0].value;
	                location.href = "${ pageContext.servletContext.contextPath }/project/main/" + projectNo;
	            }
	            
	        }
	        
	    }
	</script>
</body>
</html>