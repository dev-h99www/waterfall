<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>

	<div class="container-fluid px-4" style="margin-left:2%">
		<br>
		<br>
		<h1 class="mt-4" >프로젝트 생성</h1>
		<div class="card mb-4"style="width: 95%;">
		</div>
		<form action="${ pageContext.servletContext.contextPath }/project/regist" method="post">
			<div class="card mb-4" style="width: 95%;">
				<div class="card-header col" style="width: 100%">
					<label style="width: 15%;">프로젝트 생성</label>
					<span style="display:inline-block; width: 80%; margin-left: 2em; text-align: right;">
						<input type="button" class="btn-dark" id="backtolist" style="width: 80px; height: 40px;" value="취소">
						<input type="submit" class="btn-dark" style="width: 80px; height: 40px;" value="저장">
					</span>
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">프로젝트명</label>
					<input type="text" name="projectName" required>
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">시작일</label>
					<input type="date" name="startDate" required><br>
					<label class="mt-3" style="margin-left: 2%; width: 20%">종료일</label>
					<input type="date" name="deadLine" required>
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">프로젝트 매니저</label>
					<input type="hidden" id="pmNumber" name="pmNumber">
					<input type="hidden" id="pmName" name="pmName">
					<input type="hidden" id="adminNo" name="adminNo" value="${ sessionScope.loginMember.no }">
					<input name="pm" id="pm-name-area" type="text" class="readonly" required autocomplete="off"> 
				</div>
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
						<select id="member" name="member" required>
							<option value="" selected disabled>사원 선택</option>
						</select>
					</span>
					<span>
					    <input id="save-pm" type="button" value="등록">
					</span>
				</div>
				<div class="mt-3">
					<label style="margin-left: 2%; width: 20%">진행상태</label>
					<select id="projectStatus" name="projectStatusCode" >
					    <option value="" selected disabled>프로젝트 상태</option>
					    <c:forEach var="stat" items="${ statusList }">
					    	<option value="${ stat.statusCode }"><c:out value="${ stat.statusName }"/> </option>
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
	
	$("#save-pm").on("click", function() {
		console.log($("#member option:selected").val());
		console.log($("#member option:selected").text());
		if("사원 선택" != $("#member option:selected").text()) {
			$("#pm-name-area").val($("#member option:selected").text());
			$("#pmNumber").val($("#member option:selected").val());
			$("#pmName").val($("#member option:selected").text());
			console.log($("#pmNumber").val());
		}

	});
	$("#dept").on("change", function() {
		const deptCode = $(this).val();
		console.log(deptCode);
		const url = "${pageContext.servletContext.contextPath}/project/regist/team/" + $(this).val();
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
	$("#backtolist").on("click", function() {
		location.href = "${ pageContext.servletContext.contextPath }/project/manage";
	});
	
	</script>
</body>
</html>

























