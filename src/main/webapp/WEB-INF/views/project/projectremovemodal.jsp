<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


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
    width: 440px;
    height: 380px;
    padding: 20px;
    margin-left: 2%;
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
</style>
</head>
<body>
	<div class="modal fade" id="projectRemove" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
	    	<div class="modal-content" style="top: 172px">
	        	<form action="${ pageContext.servletContext.contextPath }/meeting/regist" method="post" encType="multipart/form-data">
					<div class="mt-4 mb-4"></div>
                	<div class="my-modal-body">
		            	<div class="my-modal-header mb-4">
		                	<label class="me-4" for="removed" style="width: 30%;margin-left: 4%">프로젝트명 : </label>
	    	                <input id="projectName" style="width:180px; border: none; background: transparent;" readonly>
						</div>
		            	<div class="my-modal-header mb-4">
		                	<label class="me-4" for="removed" style="width: 30%;margin-left: 4%">프로젝트 PM : </label>
	    	                <input id="pmName" style="width:180px; border: none; background: transparent;" readonly>
						</div>
		            	<div class="my-modal-header mb-4">
		                	<label class="me-4" for="removed" style="width: 30%;margin-left: 4%">시작일 : </label>
	    	                <input id="startDate" type="date" style="width:180px; border: none; background: transparent;" readonly>
						</div>
		            	<div class="my-modal-header mb-4">
		                	<label class="me-4" for="removed" style="width: 30%;margin-left: 4%">마감일 : </label>
	    	                <input id="deadLine" type="date" style="width:180px; border: none; background: transparent;" readonly>
						</div>
		            	<div class="my-modal-header mb-4">
	    	                <input type="hidden" id="projectNo" style="width:180px; border: none; background: transparent;" readonly>
						</div>
	            	</div>
					<div class="row mt-4 my-modal-footer-read" style="text-align: left">
                      	<div class="col" style="margin-top: 15%; text-align:center" >
                      		<input type="button" id="restoreProject" class="button float btn btn-outline-success" data-bs-dismiss="modal" value="복원">
                     		<input type="button" id="backToList" class="button float btn btn-outline-secondary" data-bs-dismiss="modal" value="취소">
                     	</div>
                   	</div>
				</form>
			</div>
		</div>
	</div>

<script>

	$("#backToList").click( function() {
		$("#title-write").val("");
		$("#my-textarea").val("");
		$("#meeting-fileupload").val("");
	});
	$("#restoreProject").click( function() {
		location.href= "${ pageContext.servletContext.contextPath }/project/restore/" + $("#projectNo").val();
	});
	$("#realRemove").click( function() {
		location.href= "${ pageContext.servletContext.contextPath }/project/delete/" + $("#projectNo").val();
	});
</script>
</body>
</html>



























