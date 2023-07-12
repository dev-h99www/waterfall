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
<!-- 업무 생성 레이어 -->
	<form action="${ pageContext.servletContext.contextPath }/task/regist" method="post">
	<div>
		<div class="layer add" id="addModal">
		<div class="header">업무 생성</div>
			<div style="margin-left:30px;">
				<p class="text-end mt-3">
				</p>
				<p>
					<label>업무선택</label>
					<select class="task-register-code" name="taskCode" required>
					<option value="" selected disabled >선택</option>
					<c:forEach var="taskList" items="${requestScope.taskCategoryList }" varStatus="status" >
						<option value="${taskList.categoryCode }" ><c:out value="${ taskList.categoryName }"/></option>
					</c:forEach>	
					</select>
				</p>
				<p>
					<label>종속관계</label>
					<select class="relation ms-2 me-5" name="parentTaskCode" required>
					<option value="" selected disabled >선택</option>
					<c:forEach var="taskCode" items="${requestScope.allTaskCode.parentCategory }" varStatus="status">
						<option value="${taskCode.parentCategoryCode }" ><c:out value="${taskCode.parentCategoryName }"/></option>
					</c:forEach>
						<option value= "NULL">미지정</option>
					</select>
					<label>담당자</label><i class="far fa-user ms-2"></i>
					<select class="in-charge ms-0" name="taskMember" required>
						<option value="" selected disabled>선택</option>
						<c:forEach var="projectMember" items="${requestScope.projectMemberList }" varStatus="status">
						<option value="${projectMember.memberNo }" ><c:out value="${projectMember.memberName }"/></option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label for="start-date">시작일</label><i
						class="far fa-calendar-alt ms-2"></i>
						<input type="date" id="start-date" name="startDate" required>
				</p>
				<p>
					<label for="end-date">종료일</label><i class="far fa-calendar-alt ms-2"></i>
					<input type="date" id="end-date" name="deadline" required>
				</p>
				<p>
					<label>중요도</label>
					<select class="importance ms-2" name="importance" required>
						<option value="" selected disabled>선택</option>
						<option value="낮음">낮음</option>
						<option value="보통">보통</option>
						<option value="긴급">긴급</option>
					</select>
				</p>
				<p>
					<label>진행상태</label> <select class="status ms-2" name="progressStatus" required>
						<option value="" selected disabled>선택</option>
						<option value="진행전">진행전</option>
						<option value="진행중">진행중</option>
						<option value="테스트중">테스트중</option>
						<option value="진행완료">진행완료</option>
						<option value="보류">보류</option>
					</select>
				</p>
				<p>
					<label>진행률</label>
					<input class="rate ms-2" type="number" name="progressRatio" value="0" min="0" max="100" >%
				</p>
				
				<p>
					<label>마일스톤</label>
					<input class="milestone ms-2" type="checkbox" name="typeNo" value="2">
					<input type="hidden" name="typeNo" value="1">
				</p>
				</div>
					<div id="footerBtn">
						<button id="addTaskBtn" class="addTask button float" type="submit" style="padding:4px; " rel="float">업무 생성</button>
						<input type="button" id="close" class="button float" style="padding:4px; margin-right:30px;" rel="float" value="업무 나가기">
					</div>
			
		</div>
	</div>
	</form>
	<!-- 업무 생성 끝 -->
	
	<!-- 업무 수정 모달 -->
	<form action="${ pageContext.servletContext.contextPath }/task/update" method="post">
	<div>
		<div class="layer add" id="modifyModal">
		<div class="header">업무 수정</div>
			<div style="margin-left:30px;">
				<p class="text-end mt-3">
				</p>
				<p>
					<label>업무선택</label>
					<select id="modifyCategoryName" class="task-register-code" name="taskCode" required>
					<option value="" disabled >선택</option>
					<c:forEach var="taskList" items="${requestScope.taskCategoryList }" varStatus="status" >
						<option  value="${taskList.categoryCode }" ><c:out value="${ taskList.categoryName }"/></option>
					</c:forEach>	
					</select>
				</p>
				<p>
					<label>종속관계</label>
					<select id="modifyParentTaskCode" class="relation ms-2 me-5" name="parentTaskCode" required>
					<option value="" disabled >선택</option>
					<c:forEach var="taskCode" items="${requestScope.allTaskCode.parentCategory }" varStatus="status">
						<option value="${taskCode.parentCategoryCode }" ><c:out value="${taskCode.parentCategoryName }"/></option>
					</c:forEach>
						<option id="selectedNull" value= "NULL">미지정</option>
					</select>
					<label>담당자</label><i class="far fa-user ms-2"></i>
					<select id="modifyMember" class="in-charge ms-0" name="taskMember" required>
						<option value="" disabled>선택</option>
						<c:forEach var="projectMember" items="${requestScope.projectMemberList }" varStatus="status">
						<option id="modifyMember" value="${projectMember.memberNo }" ><c:out value="${projectMember.memberName }"/></option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label for="start-date">시작일</label><i
						class="far fa-calendar-alt ms-2"></i>
						<input id="modifyStartDate" type="date" id="start-date" name="startDate" required>
				</p>
				<p>
					<label for="end-date">종료일</label><i class="far fa-calendar-alt ms-2"></i>
					<input id="modifyDeadline" type="date" id="end-date" name="deadline" required>
				</p>
				<p>
					<label>중요도</label>
					<select id="modifyImportance" class="importance ms-2" name="importance" required>
						<option value="" disabled>선택</option>
						<option value="낮음">낮음</option>
						<option value="보통">보통</option>
						<option value="긴급">긴급</option>
					</select>
				</p>
				<p>
					<label>진행상태</label>
					<select id="modifyStatus" class="status ms-2" name="progressStatus" required>
						<option value="" selected disabled>선택</option>
						<option value="진행전">진행전</option>
						<option value="진행중">진행중</option>
						<option value="테스트중">테스트중</option>
						<option value="진행완료">진행완료</option>
						<option value="보류">보류</option>
					</select>
				</p>
				<p>
					<label>진행률</label>
					<input id="modifyRatio" class="rate ms-2" type="number" name="progressRatio" value="0" min="0" max="100" >%
				</p>
				
				<p>
					<label>마일스톤</label>
					<input class="milestone ms-2" type="checkbox" name="typeNo" value="2">
					<input type="hidden" name="typeNo" value="1">
				</p>
				</div>
					<div id="footerBtn">
						<button class="addTask button float" type="submit" style="padding:4px; " rel="float">업무 수정</button>
						<input type="hidden" id="modifyTaskNo" name="taskNo">
						<input type="button" id="closeModify" class="button float" style="padding:4px; margin-right:30px;" rel="float" value="업무 나가기">
					</div>
			
		</div>
	</div>
	</form>
	<!-- 업무 수정 모달 끝 -->
		
	 <%-- 업무 조회 모달 --%>
	<div>
		<div class="layer" id="readModal">
		<div class="header">업무 조회</div>
			<div style="margin-left:30px;">
				<p class="text-end mt-3">
				</p>
				<div class="detail" id="parentTaskDetail">
				<label class="task">상위업무</label>
				<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
				<button class="button float" id="parentTaskModifyBtn">수정</button>
				<button class="button float" id="parentTaskDeleteBtn" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
				</c:if>
					<p>
						<label>업무명</label>
						<input id="parentTaskName" type="text" name="parentTaskName" readonly>
						<input type="hidden" name="parentTaskCode" id="parentTaskCode" />
					</p>
					<p>
						<label>담당자</label>
						<input id="parentTaskManager" type="text" name="parentTaskManager" readonly>
						<input id="parentTaskManagerNo" type="hidden" name="parentTaskManagerNo">
					</p>	
					<p>
						<label>시작일</label>
						<input type="date" id="parent-start-date" name="parentStartDate" readonly>
					</p>	
					<p>
						<label>마감일</label>
						<input type="date" id="parent-end-date" name="parentDeadline" readonly>
					</p>
					<p>
						<label>중요도</label>
						<input type="text" id="parentImportance" name="parentImportance" readonly>
					</p>
					<p>
						<label>진행률</label>
						<input id="parentProgress" class="rate ms-2" type="number" name="parentProgress" value="0" min="0" max="100" readonly>%
					</p>
					<p>
						<label>진행상태</label>
						<input id="parentStatus" type="text" name="parentStatus" readonly>
					</p>
					<p>
						<label>마일스톤</label>
						<input class="milestone ms-2" type="checkbox" name="typeNo" value="2">
						<input type="hidden" name="typeNo" value="1">
					</p>
					<p>
						<label>이슈 등록</label>
                     <label id="addIssue">
                     <button id="addIssue1" class="button float">등록</button>
                     <input type="hidden" name="parentTaskNo">
                     </label>
							
					</p>
					
					<p>
						<label>산출물 등록</label>
								<button id="addOutput1" class="button float">등록</button>
								<input id="outputParentTaskNo" type="hidden" name="parentTaskNo">
					</p>
				</div>
				<div class="detail"id="childTaskDetail">
				<label class="task">하위업무</label>
				<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
				<button class="button float" id="childTaskModifyBtn">수정</button>
				<button class="button float" id="childTaskDeleteBtn" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
				</c:if>
					<p>
						<label>업무명</label>
						<input id="childTaskName" type="text" name="childTaskName" readonly>
						<input id="childTaskCode" type="hidden" name="childTaskCode"/>
					</p>
					
					<p>
						<label>담당자</label>
						<input id="childTaskManager" type="text" name="childTaskManager" readonly>
						<input id="childTaskManagerNo" type="hidden" name="childTaskManagerNo">
					</p>
					
					<p>
						<label>시작일</label>
						<input type="date" id="child-start-date" name="childStartDate" readonly>
					</p>
					
					<p>
						<label>마감일</label>
						<input type="date" id="child-end-date" name="childDeadline" readonly>
					</p>
					
					<p>
						<label>중요도</label>
						<input type="text" id="childImportance" name="childImportance" readonly>
					</p>
					
					<p>
						<label>진행률</label>
						<input id="childProgress" class="rate ms-2" type="number" name="childProgress" value="0" min="0" max="100" readonly>%
					</p>
					
					<p>
						<label>진행상태</label>
						<input id="childStatus" type="text" name="childStatus" readonly>
					</p>
					
					<p>
						<label>마일스톤</label>
						<input class="milestone ms-2" type="checkbox" name="typeNo" value="2">
						<input type="hidden" name="typeNo" value="1">
					</p>
					
					<p>
						<label>이슈 등록</label>
                     <label id="addIssue">
                     <button id="addIssue2" class="button float">등록</button>
                     <input type="hidden" name="childTaskNo">
                     </label>
					</p>
					
					<p>
						<label>산출물 등록</label>
								<button id="addOutput2" class="button float">등록</button>
								<input id="outputChildTaskNo" type="hidden" name="childTaskNo">
					</p>
					</div>
						<button id="close" class="button float" style="float: none; padding:4px; margin-right:30px; margin-left: 600px;"  rel="float">업무 나가기</button>
				
			</div>
		</div>
	</div>
	<%--업무 조회 모달 끝 --%>
	
	<!-- 삭제 확인 Modal "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
              <div class="modal fade modal-dialog-scrollable" id="deleteModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                    <div class="modal-content" style="top: 200px">
                        <div style="background-color: #212529;">
                            <br>
                        </div>
                        <div class="modal-header">
                            <span class="modal-title" id="exampleModalLabel"><strong>업무 삭제</strong></span>
                        </div> 
                    <!-- 모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남  -->
                            <div class="modal-body">
                                <div class="mb-3">
                                    <!--label for랑 input id랑 일치시키면 라벨에 타이틀을 적을경우 라벨눌르면 인풋박스안 텍스트로 포커스를 맞춘다  -->
                                    <!-- placeholder는 인풋박스안에 적을 내용 기술해두 되고 빼두되고 편한대로 -->
                                  <label for="clieck-point" class="col-form-label" style="margin-left: 35%; margin-top:5%;">삭제하시겠습니까?</label>
                                  <input type="hidden" id="deleteParentTaskNo">
                                  <input type="hidden" id="deleteChildTaskNo">
                                </div>
                            </div>
                            <!-- 모달의 바디 끝  -->
                            <div class="modal-footer">
                                <button type="button" id="delete" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
                                <button id="cancelDelete" style="margin-right: 36%;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            </div>
                    </div>
                </div>
            </div>
	
	
	
	<%-- 산출물 등록 모달 --%>
	<form action="${ pageContext.servletContext.contextPath }/output/regist" method="post" encType="multipart/form-data">
	<div id="outputModal" class="modal-overlay">
		<div class="modal-window">
			<div class="title">
				<h4>산출물 등록</h4>
			</div>
			<div class="content-area">
				<textarea name="content" rows="8" cols="45"></textarea>
			</div>
			<p>파일 업로드</p>
			<input id="addOutput" type="file" name="outputFile" required>
			<div class="close-area">
				<button type="submit" id="submitOutput" class="button float">등록</button>
				<input type="button" id="closeOutput" class="button float" value="돌아가기">
				<input type="hidden" id="outputModalTaskNo" name="taskNo">
			</div>
		</div>
	</div>
	</form>
</body>
</html>