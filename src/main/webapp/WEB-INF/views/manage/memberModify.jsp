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
	<div class="modal fade" id="member-modify-modal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
	    	<div class="modal-content" style="top: 140px">
	        	<form action="${ pageContext.servletContext.contextPath }/manage/member/modify" method="post">
					<div class=" mb-4"></div>
	           		<div class="my-modal-body">
		            	<div class="row">
							<div class="col">
							    <label style="margin-left: 1%; font-size: 2em"><i class="fas fa-users-cog"></i>인원 정보 수정</label>
							</div>
                        </div>
                        <div>
                        	<span>
                        		<label>이름 : </label>
                        		<input type="text" id="memberName-modify" name="memberName" readonly>
                        	</span>
                        		<input type="button" id="remove-project-member" value="내보내기" class="btn btn-secondary" style="margin-left:40%"><br>
                        </div>
						<div class="mt-4 member-modify-checkbox">
							<c:forEach var="projectRole" items="${ allRole }">
								<label class="cklabel" style="margin: 1.5em"><input id="role${ projectRole.roleNo }" name="projectRole" type="checkbox" class="check checkbok" value="${ projectRole.roleNo }">${ projectRole.roleName }</label>
							</c:forEach>
                    	</div>
                     	<div class="row mt-3">
							<div style="text-align: left; margin-left: 8%;">
							     <label id="multiPrint"></label>
							</div>
		            	</div>
	            	</div>
	            	<input type="hidden" name="memberNo" id="memberNo-modify">
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
	$("#remove-project-member").click(function() {
		const $memberNo = $("#memberNo-modify").val();
		console.log($memberNo);
		$url = "${ pageContext.servletContext.contextPath }/manage/member/remove/" +$memberNo;
		console.log($url);
		
		location.href=$url;
		 
		
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