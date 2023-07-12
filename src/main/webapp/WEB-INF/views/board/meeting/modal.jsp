<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>

#downloadZone {
	width: 100px;
}
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
  width: 510px;
  height: 600px;
  padding: 30px;
}

#title-write {
  width: 377px;
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
</style>
</head>
<body>
	<!-- 게시판 등록용 모달 -->
	<div class="modal fade" id="writeModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
	    	<div class="modal-content" style="top: 172px">
	        	<form action="${ pageContext.servletContext.contextPath }/meeting/regist" method="post" encType="multipart/form-data">
	            	<div class="my-modal-header mb-4">
	                	<label class="me-4" for="title-write">제목</label>
	                    <input type="text" id="title-write" name="title">
					</div>
                	<div class="my-modal-body">
	                	<div class="my-textarea-div mb-3">
	                    	<textarea id="my-textarea" cols="30" rows="10" name="content"></textarea>
	           			</div>
	            	</div>
	            	<div class="my-modal-upload mb-4">
	            		<input id="meeting-fileupload"  type="file" name="meetingfile"  multiple>
	            	</div>
					<div class="mt-4 my-modal-footer-read" style="text-align: left">
	                	<input type="hidden" name="memberNo" value="${ sessionScope.loginMember.no }">
	                	<input type="hidden" name="projectNo" value="${ sessionScope.projectAutority.projectNo }">
	                	<button type="submit" class="btn btn-secondary" >등록</button>
                      	<input type="button" id="meetingRegistBackToList" class="btn btn-secondary" data-bs-dismiss="modal" value="돌아가기">
                      	
                  	</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 게시판 상세조회 모달 -->
	<div class="modal fade" id="readModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
			<div class="modal-content" style="top: 172px">
				<form action="${ pageContext.servletContext.contextPath }/meeting/modify" method="post">
                	<div class="my-modal-header mb-4">
                    	<label class="me-2" for="title-write">제목 : </label>
	                    <input type="text" id="read-title" name="title" style=" border: none; background: transparent;">
	                    <input type ="hidden" id="read-no" name="no">
                	</div>	
	                <div class="my-modal-body">
	                    <div class="my-read-textarea-div mb-3">
	                        <textarea name="content" id="read-content" cols="30" rows="10"></textarea>
	                    </div>
	                </div>
	               	<div class="my-modal-upload mb-4" id="downloadZone" style="overflow-y:auto;overflow-x:hidden; width:100%; height:150px;">
	            	</div>
	                <div class="my-modal-footer-read">
	                    <c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.role eq 1 or sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo}">
	                    	<button type="submit" class="btn btn-secondary" id="delete">수정하기</button>
	                    	<button type="button" class="btn btn-secondary" id="delete">삭제하기</button>
                    	</c:if>
	                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">돌아가기</button>
	                </div>
                </form>
	        </div>
	    </div>
	</div>
<script>

	$("#meetingRegistBackToList").click( function() {
		$("#title-write").val("");
		$("#my-textarea").val("");
		$("#meeting-fileupload").val("");
	});
	$("#delete").click( function() {
		location.href= "${ pageContext.servletContext.contextPath }/meeting/remove/" + $("#read-no").val();
	});
</script>
</body>
</html>



























