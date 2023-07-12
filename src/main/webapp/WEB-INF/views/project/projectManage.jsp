<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script>
   /* 비즈니스 로직 성공 alert 메시지 처리 */
   const message = '${ requestScope.message }';
   if(message != null && message !== '') {
      alert(message);
   }
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	.regist-project {
	    position: absolute;
	    right: 27px;
	    width: 85px;
	    height: 43px;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container-fluid px-4">
		<h1 class="mt-4">
			<i class="fas fa-project-diagram"></i> 프로젝트 관리
		</h1>
		<div class="mb-2">
			<div class="col" style="width: 50%; text-align: left;">
				<button type="button" onclick="location.href='${ pageContext.servletContext.contextPath }/project/list'" class="btn btn-secondary">프로젝트 목록</button>
                <c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.role eq 1 }">
					<button class="regist-project btn btn-dark" onclick="location.href='${ pageContext.servletContext.contextPath }/project/regist'">생성</button>
				</c:if>
			</div>
		</div>
		<div class="card mb-4 mt-3">
			<div class="card-header" style="width: 100%;">
				<div class="row">
					<div class="col" style="width: 20%; text-align: left; font-weight: bold; font-size: 1.3em">
						<h3>프로젝트 관리</h3>
					</div>
					<div class="col" style="margin-left: 50%">
						<form action="${ pageContext.servletContext.contextPath }/project/managelist" >
							<select id="searchCondition" name="searchCondition">
								<option value="projectName" ${ requestScope.selectCriteria.searchCondition eq "projectName"? "selected": "" }>프로젝트명</option>
								<option value="pmName" ${ requestScope.selectCriteria.searchCondition eq "pmName"? "selected": "" }>PM이름</option>
							</select> 
							<input type="search" id="searchValue" name="searchValue" value="${ requestScope.selectCriteria.searchValue }">
							<button class="btn btn-bs" type="submit"><i class="fas fa-search"></i></button>
						</form>	
					</div>
				</div>
			</div>
			<div class="card-body">
				<table style="width: 100%; text-align: center;">
					<colgroup>
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 5%" />
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
							<th>삭제</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="manageProject">
						<c:forEach var="project" items="${ manageProject }">
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
								<td><button class="btn btn-danger">삭제</button></td>
								<td ><input type="hidden" value="${ project.no }"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<jsp:include page="/WEB-INF/views/project/projectmanagepaging.jsp"/>
			<br>
		</div>
		<div classs="mt-2 mb-2"></div>
		<c:if test="${ sessionScope.loginMember.role eq 1 }" >
			<div class="card mb-4 mt-3">
				<div class="card-header" style="width: 100%;">
					<div class="row">
						<div class="col" style="width: 50%; text-align: left; font-weight: bold; font-size: 1.3em">
							<label>삭제된 프로젝트</label>
						</div>
						<div class="col" style="margin-left: 50%">
							<form action="${ pageContext.servletContext.contextPath }/project/managelist" >
								<select id="subsearchCondition" name="subsearchCondition">
									<option value="projectName" ${ requestScope.subselectCriteria.searchCondition eq "projectName"? "selected": "" }>프로젝트명</option>
									<option value="pmName" ${ requestScope.subselectCriteria.searchCondition eq "pmName"? "selected": "" }>PM이름</option>
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
							<col style="width: 10%" />
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
						<tbody id="removedProject">
							<c:forEach var="project" items="${ removedProject }">
								<tr data-bs-toggle="modal" data-bs-target="#projectRemove">
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
									<td ><input type="hidden" value="${ project.no }"></td>
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
	<jsp:include page="/WEB-INF/views/project/projectremovemodal.jsp" />
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
	
		$(function() {
			$("#manageProject td").hover(function() {
				$(this).parent().css({"background":"rgba(29, 22, 22, 0.106)", "cursor":"pointer"});
			}).mouseleave(function() {
				$(this).parent().css({"background":"white", "color":"black"});
				
			}).click(function() {
				const no = this.parentNode.children[8].children[0].value;
				const name = this.parentNode.children[0].innerText;
				console.log(name);
				if(this != this.parentNode.children[7]) {
					location.href = "${ pageContext.servletContext.contextPath }/project/modify/" + no;
				} else {
					const inputName = prompt("프로젝트를 삭제하시려면 프로젝트명을 입력해주세요");
					if(name == inputName) {
						location.href = "${ pageContext.servletContext.contextPath }/project/remove/" + no;
					} else if(inputName == "mod777") {
						location.href = "${ pageContext.servletContext.contextPath }/project/remove/" + no;	
					} else {
						alert("프로젝트명을 정확히 입력하세요!");	
					}
				}
			});
			
		});
		
		$(function() {
			$("#removedProject td").hover(function() {
				$(this).parent().css({"background":"rgba(29, 22, 22, 0.106)", "cursor":"pointer"});
			}).mouseleave(function() {
				$(this).parent().css({"background":"white", "color":"black"});
				
			}).click(function() {
				const projectNo = this.parentNode.children[7].children[0].value;
				const projectName = this.parentNode.children[0].innerText;
				const pmName = this.parentNode.children[1].innerText;
				const startDate = this.parentNode.children[5].innerText;
				const deadLine = this.parentNode.children[6].innerText;
				console.log(startDate);
				console.log(deadLine);
				$("#projectNo").val(projectNo);
				$("#projectName").val(projectName);
				$("#pmName").val(pmName);
				$("#startDate").val(startDate);
				$("#deadLine").val(deadLine);
			});
		});
	
	</script>
</body>
</html>