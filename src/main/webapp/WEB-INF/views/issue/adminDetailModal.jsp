<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>
/* $(function(){
	$("#read-completedDate").datepicker({
		
	});

}); */
</script>
</head>
<body>

<!-- 조회 모달 -->
<form action="${ pageContext.servletContext.contextPath }/issue/admin/update"  method="post" encType="multipart/form-data">
<div class="modal" id="myModal">
    <div class="modal-dialog modal-xl modal-dialog modal-dialog-scrollable">
    <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
        <h4 class="modal-title">이슈 수정</h4>
        <button type="button" id="xButton" class="close">&times;</button>
        </div>

        <!-- Modal body -->
                   <div class="modal-body" id="modal-body">
                        <div class="mt-4 row">
                        	<input type="hidden" id="read-no" name="no">
                        	<input type="hidden" id="read-projectNo" name="projectNo">
                        	<input type="hidden" id="read-taskNo" name="taskNo">
                        	
                            <div class="col-2 center"><label>이슈명</label></div>
                            <div class="col"><input type="text" id="read-name" name="name" required ></div>
                            <div class="col"></div>
                            <div class="col-2 center"><label>상태</label></div>
                            <div class="col-4">
                                <select class="importance ms-auto" id="read-progressStatus" name="progressStatus"  required>
                                    <option value="대기중">대기중</option>
                                    <option value="처리중">처리중</option>
                                    <option value="완료">완료</option>
                                </select>
                            </div>
                        </div>

                        <div class="mt-4 row">
                       		<div class="col-2 center"><label for="read-createdDate">등록일</label></div>
                            <div class="col"><input type="date" id="read-createdDate" name="createdDate"  required></div>
                       
                            <div class="col-2 center"><label for="read-deadline">마감일</label></div>
                            <div class="col-4"><input type="date" id="read-deadline" name="deadline"  required></div>
                        </div>

                         <div class="mt-4 row">
                            <div class="col-2 center"><label>이슈 제기자</label></div>
                            <div class="col">
                            	<select class="importance ms-auto" id="register" name="registerNo"  required>
								</select>
									
                            </div>
                            
                            <div class="col-2 center"><label>이슈 담당자</label></div>
                            <div class="col">
                            	<select class="importance ms-auto" id="manager" name="managerNo" onfocus="this.value=''">
								</select>
                            </div>
                        </div>

                        <div class="mt-4 row">
                            <div class="col-2 center"><label>중요도</label></div>
                            <div class="col-4">
	                            <select class="importance ms-auto" id="read-importance" name="importance"  required>
	                                <option value="낮음">낮음</option>
	                                <option value="보통">보통</option>
	                                <option value="긴급">긴급</option>
	                            </select>
                            </div>
                            
                       </div>
                       
                       <div class="mt-4 row" id="completedDateZone">
					   </div>
					   
                        <div class="mt-4 row">
                            <div class="col-2 center" style="vertical-align: top;"><label>이슈내용</label></div>
			                <div class="col-10"><textarea id="read-content" cols="80" rows="10" name="content"  required></textarea></div>
			            </div>
			            
			            <div class="mt-4 row">
			                <div class="col-2 center" style="vertical-align: top;"><label>처리내용</label></div>
			                <div class="col-10"><textarea id="read-answer" cols="80" rows="10" name="answer" ></textarea></div>
			            </div>
						
						<div id="uploadZone">
			            	<input type="file"  name="multiFiles" multiple>
			            </div>
						
						<div id="downloadZone">
						</div>
        </div> 	 

        <!-- Modal footer -->
        <div class="modal-footer row">
                <div class="col-2">
                    <button type="submit" id="submit" class="btn btn-outline-dark" data-target="#mySubModal">수정</button>
                </div>
                <div class="col-3">
                    <button id="delete" type="button" class="btn btn-outline-dark">삭제</button>
                </div>
                <div class="col-4">
                    <button type="button" id="cancel" class="btn btn-outline-dark">취소</button>
                </div>
        </div>
    </div>
    </div>
</div>

<!-- 모달 확인 버튼 누를 시 나오는 모달 -->
<div class="modal" id="mySubModal">
    <div class="modal-dialog">
    <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
        <h4 class="modal-title">이슈</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        <!-- Modal body -->
        <div class="modal-body">
        	정보를 저장하시겠습니까?
        </div>

        <!-- Modal footer -->
        <div class="modal-footer row">
            <div class="col-5">
            <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#mySubModal">확인</button>
            </div>
            <div class="col-4">
            <button type="button" class="btn btn-outline-dark" data-dismiss="modal">취소</button>
            </div>
        </div>

    </div>
    </div>
</div>
</form>
<script>
/* 이슈 삭제 이벤트 */
$(function(){
      $("#delete").click(function(){
         const no = $("#read-no").val();
         location.href="${ pageContext.servletContext.contextPath }/issue/admin/delete?no=" + no;
      });
});
/* 모달 취소 버튼이 고장나는 바람에 어쩔 수 없이 만들었음 */ 
$(function(){
	$("#cancel").click(function(){
		$("#myModal").modal("hide");
	});
});
/* x 버튼이 고장나는 바람에 어쩔 수 없이 만들었음 */ 
$(function(){
	$("#xButton").click(function(){
		$("#myModal").modal("hide");
	});xButton
});
</script>
</body>
</html>