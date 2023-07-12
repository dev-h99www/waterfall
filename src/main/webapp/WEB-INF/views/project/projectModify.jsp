<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

	<div class="container-fluid px-4" style="margin-left:2%">
		<br>
		<br>
		<h1 class="mt-4" >프로젝트 수정</h1>
		<div class="card mb-4"style="width: 95%;">
		</div>
		<form action="${ pageContext.servletContext.contextPath }/project/modify" method="post">
			<div class="card mb-4" style="width: 95%;">
				<div class="card-header col" style="width: 100%">
					<label style="width: 15%;">프로젝트 수정</label>
					<span style="display:inline-block; width: 80%; margin-left: 2em; text-align: right;">
						<input type="button" id="backtolist" class="btn-dark" style="width: 80px; height: 40px;" value="취소">
						<input type="submit" class="btn-dark" style="width: 80px; height: 40px;" value="수정">
					</span>
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">프로젝트명</label>
					<input type="text" name="projectName" value="${ projectInfo.projectName }"required maxlength="10">
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">시작일</label>
					<input type="date" name="startDate" required value="${ projectInfo.startDate }"><br>
					<label class="mt-3" style="margin-left: 2%; width: 20%" >종료일</label>
					<input type="date" name="deadLine" value="${ projectInfo.deadLine }" required>
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">프로젝트 매니저</label>
					<input type="hidden" id="pmNumber" name="pmNumber" value="${ projectInfo.pmNumber }">
					<input type="hidden" id="adminNo" name="adminNo" value="${ sessionScope.loginMember.no }">
					<input type="hidden" id="adminName" name="adminName" value="${ sessionScope.loginMember.name }">
					<input type="hidden" id="projectNo" name="projectNo" value="${ projectInfo.projectNo }">
					
					<input name="pmName" id="pm-name-area" type="text" class="readonly" value="${ projectInfo.pmName }" required autocomplete="off"> 
				</div>
				<c:if test="${ sessionScope.loginMember.role eq 1 }">
					<div class="mt-4">
						<span  style="margin-left: 2%">
							<select id="dept" name="dept">
								<option value="" selected disabled>부서 선택</option>
								<c:forEach var="dept" items="${ deptList }">
									<option value="${ dept.deptCode }"><c:out value="${ dept.deptName }"/></option>
								</c:forEach>
							</select>
						</span>
						<span>
							<select id="team" name="team">
								<option value="" selected disabled>팀 선택</option>
							</select>
						</span>
						<span>
							<select id="member" name="member">
								<option value="" selected disabled>사원 선택</option>
							</select>
						</span>
						<span>
						    <input id="save-pm" style="width:70px; margin-left:1%; height:40px;"class="btn btn-outline-dark" type="button" value="변경">
						</span>
					</div>
				</c:if>
				<div class="mt-3">
					<label style="margin-left: 2%; width: 20%">진행률</label>
					<input type="number" name="progression" value="${ projectInfo.progression }" min="0" max="100" required maxlength="10">
					
				</div>
				<div class="mt-3">
					<label style="margin-left: 2%; width: 20%">진행상태</label>
					<input type="hidden" id="projectStatusName" name="projectStatusName">
					<select id="projectStatus" name="projectStatusCode" >
					    <option value="" selected disabled>프로젝트 상태</option>
					    <c:forEach var="projectStatus" items="${ statusList }">
					    	<c:if test="${ projectInfo.projectStatusCode eq projectStatus.statusCode}">
						    	<option value="${ projectStatus.statusCode }" selected><c:out value="${ projectStatus.statusName }"/> </option>
					    	</c:if>
					    	<c:if test="${ projectInfo.projectStatusCode ne projectStatus.statusCode}">
						    	<option value="${ projectStatus.statusCode }"><c:out value="${ projectStatus.statusName }"/> </option>
					    	</c:if>
					    	
					    </c:forEach>
					</select>
				</div>
				<div class="col" style="text-align: center; margin-right: 2em; margin-bottom: 2em; margin-top: 4em">
				</div>
			</div>
		</form>
	</div>	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
	
	$("#projectStatus").on("change", function() {
		
		$("#projectStatusName").val($("#projectStatus option:selected").text());
	});
	
	$("#backtolist").on("click", function() {
		location.href = "${ pageContext.servletContext.contextPath }/project/manage";
	});
	
	$("#save-pm").on("click", function() {
		console.log($("#member option:selected").val());
		console.log($("#member option:selected").text());
		if("사원 선택" != $("#member option:selected").text()) {
			$("#pm-name-area").val($("#member option:selected").text());
			$("#pmNumber").val($("#member option:selected").val());
			console.log($("#pmNumber").val());
		}

	});
	$("#dept").on("change", function() {
		const deptCode = $(this).val();
		const url = "${pageContext.servletContext.contextPath}/project/regist/team/" + $(this).val();
		console.log(deptCode);
		console.log(url);
		$("#team option").remove();
		$.ajax({
			url: url,
			type: "get",
			data: {deptCode:deptCode},
			success: function(data) {
				const $teamPreOption = "<option value='' selected disabled>팀 선택</option>";
				$("#team").append($teamPreOption);
				const $memberPreOption = "<option value='' selected disabled>사원 선택</option>";
				$("#member").append($memberPreOption);
				teamList = JSON.parse(data.teamList);
				for(let i = 0; i < teamList.length; i++) {
					const $teamTag = "<option value = '" + teamList[i].teamCode + "'>" + teamList[i].teamName + "</option>" 
					$("#team").append($teamTag);
				}
			},
			error: function(data) {
				console.log("ajax통신 실패 에러");
			}
		});
	});
	
	$("#team").on("change", function() {
		const teamCode = $(this).val();
		console.log(teamCode);
		const url = "${pageContext.servletContext.contextPath}/project/regist/member/" + $(this).val();
		$("#member option").remove();
				const $memberPreOption = "<option value='' selected disabled>사원 선택</option>";
				$("#member").append($memberPreOption);
		$.ajax({
			url: url,
			type: "get",
			data: {teamCode:teamCode},
			success: function(data) {
				
				memberList = JSON.parse(data.memberList);
				for(let i = 0; i < memberList.length; i++) {
					const $memberTag = "<option value = '" + memberList[i].no + "'>" + memberList[i].name + "</option>"
					console.log($memberTag);
						
					$("#member").append($memberTag);
				}
			},
			error: function(data) {
				console.log("ajax통신 실패 에러");
			}
		});
	});
	
	$(".readonly").keydown(function(e){
        e.preventDefault();
    });
	
	</script>
</body>
</html>