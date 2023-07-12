<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 등록 모달 -->
<div class="modal" id="registModal">
    <form id="registForm" onsubmit="test()"  encType="multipart/form-data" target="_parent">
    <div class="modal-dialog modal-xl modal-dialog modal-dialog-scrollable">
    <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
        <h4 class="modal-title">이슈 등록</h4>
        </div>

        <!-- Modal body -->
                  <div class="modal-body">
					<input type="hidden" id="read-taskNo" name="taskNo">
					<input type="hidden" id="read-projectNo" name="projectNo">
                        <div class="mt-4 row">
                           <div class="col-2 center"><label>이슈명</label></div>
                           <div class="col"><input type="text" name="name" required></div>
                           <div class="col"></div>
                           <div class="col-2 center"><label>상태</label></div>
                           <div class="col-4">
                               <select class="importance ms-auto" name="progressStatus">
                                   <option value="대기중">대기중</option>
                                   <option value="처리중">확인중</option>
                                   <option value="완료">완료</option>
                               </select>
                           </div>
                       </div>

                       <div class="mt-4 row">
                       	   <div class="col-2 center"><label for="start-date">시작일</label></div>
                           <div class="col-4"><input type="date" id="start-date" name="createdDate" required></div>
                       
                           <div class="col-2 center"><label for="start-date">마감일</label></div>
                           <div class="col"><input type="date" id="start-date" name="deadline" required></div>
                       </div>

                       <div class="mt-4 row">
                           <div class="col-2 center"><label>중요도</label></div>
                           <div class="col-4">
                           <select class="importance ms-auto" name="importance">
                               <option value="낮음">낮음</option>
                               <option value="보통">보통</option>
                               <option value="긴급">긴급</option>
                           </select>
                           </div>
                       </div>

                       <div class="mt-4 row">
                           <div class="col-2 center" style="vertical-align: top;"><label>이슈내용</label></div>
                		   <div class="col"><textarea name="content" id="" cols="100" rows="auto" name="content" required></textarea></div>
            		  </div>

            <div class="mt-4 row">
                <div class="col-2 center" style="vertical-align: top;"><label>첨부파일</label></div>
                <div class="col-10"><input type="file"  name="multiFiles" multiple></div>
            </div>
        </div>

        <!-- Modal footer -->
        <div class="modal-footer row">
                <div class="col-5">
                    <button type="submit" onclick="submit" id="registSubmit" class="btn btn-outline-dark button float">확인</button>
                </div>
                <div class="col-4">
                    <button type="button" class="btn btn-outline-dark button float" id="closeIssue">취소</button>
                </div>
        </div>
    </div>
    </div>
	</form>
</div>

<script>

	function manualValidate(ev) {
	    ev.target.checkValidity();
	    return false;
	}
	$("#form").bind("submit", manualValidate);

  function test(e) { 
	
	const $taskNo = $("#read-taskNo").val();
	console.log($taskNo);

	 console.log("호출됨"); 
	 var form = document.forms["registForm"];
	 console.log(form);
	 form.action = "${ pageContext.servletContext.contextPath }/issue/task/regist/" + $taskNo;
	 form.method="POST";	 
 }
 
</script>               

</body>
</html>