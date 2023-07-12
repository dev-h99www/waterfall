<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>

.my-modal-footer button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
.my-modal-footer button:first-child {
  margin-right: 306px;
}


.my-modal-message {
  line-height: 45px;
}


.my-modal-footer-read {
  text-align: center;
}
 
.my-modal-footer-read button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
.modal-content {
    width: 633px;
    height: 517px;
    padding: 26px;
}
#title-write {
  width: 390px;
}
.my-modal-body {
  margin-left: 0px;
}
.my-textarea-div {
  width: 440px;
  height: 330px;
}
#my-textarea {
  display: block;
  width: 100%;
  height: 100%;
}
.my-modal-upload {
	align: left;
}


.my-read-textarea-div {
  width: 440px;
  height: 245px;
}

#read-title {
	width: 390px;
}
#read-content {
	width: 436px;
}

.cklabel {
	font-size: 1.2em;
	style: bole;
}
.check {
	zoom: 2;
}
</style>
</head>
<body>
	<div class="modal fade" id="member-regist-modal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
	    	<div class="modal-content" style="top: 140px">
	        	<form action="${ pageContext.servletContext.contextPath }/manage/member/regist" method="post">
					<div class=" mb-4"></div>
	           		<div class="my-modal-body">
		            	<div class="row">
							<div class="col">
							    <label style="margin-left: 1%; font-size: 2em"><i class="fas fa-users-cog"></i>인원배정</label>
							</div>
                        </div>
						<div class="mt-4">
							<span  style="margin-left: 2%;margin-right: 2%">
								<select id="dept" name="dept">
									<option value="" selected disabled>부서 선택</option>
									<c:forEach var="dept" items="${ allDept }">
										<option value="${ dept.deptCode }"><c:out value="${ dept.deptName }"/></option>
									</c:forEach>
								</select>
							</span>
							<span  style="margin-left: 2%;margin-right: 2%">
								<select id="team" name="team">
									<option value="" selected disabled>팀 선택</option>
								</select>
							</span>
							<span  style="margin-left: 2%;margin-right: 2%">
								<select id="member" name="member">
									<option value="" selected disabled>사원 선택</option>
								</select>
							</span>
						</div>
						<div class="mt-4">
							<c:forEach var="projectRole" items="${ allRole }">
								<label class="cklabel" style="margin: 1.5em"><input name="projectRole" type="checkbox" class="check checkbok" value="${ projectRole.roleNo }">${ projectRole.roleName }</label>
							</c:forEach>
                    	</div>
                     	<div class="row mt-3">
							<div style="text-align: left; margin-left: 8%;">
							     <label id="multiPrint"></label>
							</div>
		            	</div>
	            	</div>
	            	<input type="hidden" name="memberNo" id="regist-memberNo">
	            	<input type="hidden" name="managerNo" value="${ sessionScope.loginMember.no }" id="managerNo">
	            	<input type="hidden" name="projectNo" value="${ sessionScope.projectAutority.projectNo }" id="projectNo">
	            	
	            	<div style="margin-ton: 2%;text-align: center">
	            		<button type="submit" class="btn btn-secondary mb-2"><i class="far fa-edit me-1"></i>등록</button>
	            		<button type="button" id="backToList" class="btn btn-secondary mb-2" data-bs-dismiss="modal">취소</button>
	            	</div>
				</form>
			</div>
		</div>
	</div>
	<script>
	
	$("#member").on("change", function() {
		console.log($("#member option:selected").val());
		console.log($("#member option:selected").text());
		if("사원 선택" != $("#member option:selected").text()) {
			const $managerNo = ${ sessionScope.loginMember.no};
			$("#regist-memberNo").val($("#member option:selected").val());
			$("#managerNo").val($managerNo);
			console.log($("#memberNo").val());
			console.log($("#managerNo").val());
		}

	});
	
	$("#dept").on("change", function() {
		const deptCode = $(this).val();
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
		const projectNo = ${ sessionScope.projectAutority.projectNo };
		console.log(teamCode);
		const url = "${pageContext.servletContext.contextPath}/manage/member/find";
		console.log(url);
		$("#member option").remove();
				const $memberPreOption = "<option value='' selected disabled>사원 선택</option>";
				$("#member").append($memberPreOption);
		$.ajax({
			url: url,
			type: "get",
			data: {teamCode:teamCode, projectNo:projectNo},
			success: function(data) {
				
				memberList = JSON.parse(data.memberList);
				for(let i = 0; i < memberList.length; i++) {
					const $memberTag = "<option value = '" + memberList[i].memberNo + "'>" + memberList[i].memberName + "</option>"
					console.log($memberTag);
					
					$("#member").append($memberTag);
				}
				console.log($("dept"));
			},
			error: function(data) {
				console.log("ajax통신 실패 에러");
			}
		});
	});
	
	$(".readonly").keydown(function(e){
        e.preventDefault();
    });
	
	
	$("#backToList").click( function() {
		$("#dept option:eq(0)").prop("selected", true);
		$("#team option:eq(0)").prop("selected", true);
		$("#member option:eq(0)").prop("selected", true);
		$(":checkbox:checked").prop('checked', false);
	});
	</script>
</body>
</html>