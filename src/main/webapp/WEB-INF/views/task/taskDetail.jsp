<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">		
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" data-auto-replace-svg="nest"></script>

<!-- 간트차트 -->	
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.3.0/frappe-gantt.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.3.0/frappe-gantt.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.3.0/frappe-gantt.min.css">
<script type="text/javascript"
	src="https://unpkg.com/frappe-gantt@0.6.0/src/index.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.3.0/frappe-gantt.min.js"></script>



<style>
#box1header {
	margin: 30px;
	font-size: 18px;
	font-weight: 600;
}

.box {
	width: 1600px !important;
	height: 800px;
	display: flex;
	margin: 30px;
}

.box2 {
	width: 1400px;
	height: 800px;
	margin-left: 50px;
	font-size: 18px;
}

#box1_body {
	width: 180px;
}

.upDown {
	border: 0.2px solid gray;
	overflow: visible;
	width: 0.3px;
	height: 800px;
	display: inline-box;
}

body {
	font-family: sans-serif;
	background: #ccc;
}

.container {
	width: 80%;
	margin: 0 auto;
}
/* custom class */
.gantt .bar-milestone .bar {
	fill: tomato;
}
/* 업무 생성 */
a {
	color: black;
	text-decoration: none;
}

#layoutSidenav_content .task-regist {
	position: relative;
}

#layoutSidenav_content .task-regist h2 {
	height: 50px;
	line-height: 1.5;
	padding-left: 60px;
}

#layoutSidenav_content .task-regist h2 span {
	font-size: 1rem;
	vertical-align: bottom;
}

#layoutSidenav_content .task-regist h2 span img {
	width: 20px;
	vertical-align: bottom;
}

#layoutSidenav_content .task-regist hr {
	width: 95%;
	margin: 10px auto;
}

#layoutSidenav_content .task-regist .sect1 {
	position: fixed;
	width: 305px;
	height: 75%;
	border-right: 1px solid black;
}

#layoutSidenav_content .task-regist .sect1 p {
	padding-left: 42px;
	line-height: 1;
	/* font-size: 1rem; */
}
/* 레이어 팝업 버튼 */
#layoutSidenav_content .task-regist .sect1 p .layerPopup {
	vertical-align: middle;
	cursor: pointer;
}

#layoutSidenav_content .task-regist .sect2 {
	width: 75%;
	margin-left: 305px;
}
/* 레이어 창 */
#layoutSidenav_content .task-regist .layer {
	display: none;
	width: 777px;
	height: 580px;
	background: #fff;
	border: 1px solid black;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: 2500;
}

.layer {
	display: none;
	width: 777px;
	height: 580px;
	background: #fff;
	border: 1px solid black;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: 2500;
	/* font-size: 1rem; */
	padding-left: 30px;
	padding-right: 30px;
	cursor: pointer;
}

#layoutSidenav_content .task-regist .layer p {
	/* font-size: 1rem; */
	padding-left: 30px;
	padding-right: 30px;
}

#layoutSidenav_content .task-regist .layer p .trash {
	cursor: pointer;
}

#layoutSidenav_content .task-regist .layer p button {
	border: 0;
	padding: 3px 8px;
	border-radius: 5px;
	background-color: #f8f8f8;
	box-shadow: 1px 4px 3px #bbb;
}

#layoutSidenav_content .task-regist .layer p label {
	padding: 3px 8px;
	border-radius: 5px;
	background-color: #f8f8f8;
	box-shadow: 1px 4px 3px #bbb;
}

#layoutSidenav_content .task-regist .layer p select {
	vertical-align: middle;
	width: 150px;
	background-color: #fff;
	box-shadow: 1px 4px 3px #ddd inset;
}

#layoutSidenav_content .task-regist .layer p #start-date,
	#layoutSidenav_content .task-regist .layer p #end-date {
	vertical-align: middle;
}

#layoutSidenav_content .task-regist .layer p .milestone {
	vertical-align: middle;
}

.layer-bg {
	display: none;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.6);
	position: absolute;
	left: 0;
	top: 0;
	z-index: 2000;
}

/* 업무 수정 */
ul {
	list-style: none;
}

#layoutSidenav_content .task-regist .sect1 .task-list {
	/* font-size: 1rem; */
	margin-top: 50px;
	padding-left: 42px;
}

#layoutSidenav_content .task-regist .sect1 .task-list li {
	height: 40px;
	vertical-align: middle;
}

#layoutSidenav_content .task-regist .sect1 .task-list li span {
	line-height: 41px;
}

#layoutSidenav_content .task-regist .sect1 .task-list li.layerPopup {
	cursor: pointer;
}

.layerPopup {
	cursor: pointer;
}

#layoutSidenav_content .task-regist .sect1 .task-list li.subtask {
	text-indent: 15px;
}

.close {
	font-size: 1rem;
}

.addTask {
	padding: 0;
	background-color: transparent;
	border: 0;
    font-weight: 700;
    line-height: 1;
    color: #000;
    text-shadow: 0 1px 0 #fff;
    opacity: .5;
    
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />
	<div class="taskList">
		<div id="box1header">
			<i style='font-size:24px' class='far'>&#xf328;</i>&nbsp;&nbsp;업무 관리 <br>
		</div>
		<hr>
		<div class="box">
			<div id="box1_body">
				<div class="sect1 mt-3">
					<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
						<p>
							<span class="layerPopup"><img src="${ pageContext.servletContext.contextPath }/resources/assets/img/generate.png">업무생성</span>
						</p>
					</c:if>
				</div>
				<%-- 상위업무 forEach --%>

				<c:forEach var="parentTask" items="${ requestScope.parentTaskList }"
					varStatus="status">
					<label id="parentTaskName" type="button" class="folder_toggle"
						data-toggle="collapse" data-target="#demo${ status.index }"><i
						style='font-size: 16px;' class='fas'>&#xf07b;</i>&nbsp;<c:out
							value="${ parentTask.taskCategory.categoryName }" /></label>
					<br>
					<%--하위 업무 forEach --%>
					<c:forEach var="childList"
						items="${ requestScope.parentTaskList[ status.index ].childList }"
						varStatus="st">

						<div id="demo${ status.index }" class="collapse">
							<div id="childTasks${ st.index }">
								<div id="childTask">
									<label class=childName type="button"><i
										style='font-size: 24px' class='fas'>&#xf0da;</i>
									<c:out value="${ childList.taskCategory.categoryName }" /></label> <input
										id="childDTO${ st.index }" type="hidden"
										value="${ childList.taskNo }">
								</div>
							</div>
						</div>
					</c:forEach>
					<%-- <c:out value="${ parentTask.taskCategory.categoryName }"/><br> --%>
				</c:forEach>
			</div>
			<div class="upDown"></div>
			<div class="box2">
				<div class="container">
					<h2>Gantt Chart</h2>
					<div class="gantt-target"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- 업무 생성 레이어 -->
	<form action="${ pageContext.servletContext.contextPath }/task/regist" method="post">
	<div class="layer">
		<p class="text-end mt-3">
		</p>
		<p>
			<select class="task-register-code" name="taskCode" required>
			<option value="" selected disabled >선택</option>
			<c:forEach var="taskList" items="${requestScope.taskCategoryList }" varStatus="status" >
				<option value="${taskList.categoryCode }" ><c:out value="${ taskList.categoryName }"/></option>
			</c:forEach>	
			</select> <span class="trash"><i class="far fa-trash-alt ms-2"></i></span>
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
				<option value="보퉁">보퉁</option>
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
			
			<button class="addTask" type="submit">업무 생성</button>
			<button class="close">업무 나가기</button>
	</div>
	</form>
	<!-- 업무 생성 끝 -->
	<script>
		
	<%-- hover event 추가 --%>
		$(".folder_toggle").hover(function() {
			$(this).css({
				"border" : "solid",
				"border-color" : "gray",
				"border-radius" : "5px",
				"border-width" : "1px"
			});

		}, function() {
			$(this).css({
				"border-color" : "white"
			});
		});

		$(".childName").hover(function() {
			$(this).css({
				"border" : "solid",
				"border-color" : "gray",
				"border-radius" : "5px",
				"border-width" : "1px"
			});

		}, function() {
			$(this).css({
				"border-color" : "white"
			});
		});
	<%-- hover 이벤트 끝 --%>
	
	
		const startDate2 = "${parentTaskList[0].startDate}";
		console.log(startDate2);

		var parentTaskList = "${parentTaskList}";
		console.log(parentTaskList.length);

		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		var taskArray = new Array();
		taskArray[${status.index}] = "${parentTaskList[status.index]}";
		console.log(taskArray[${status.index}]);
		</c:forEach>
		
		var startDateArray = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		startDateArray[${status.index}] = "${parentTaskList[status.index].startDate}";
		console.log(startDateArray[${status.index}]);
		</c:forEach>
		
		console.log(startDateArray);
		
		var deadlineArray = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		deadlineArray[${status.index}] = "${parentTaskList[status.index].deadline}";
		</c:forEach>
		console.log(deadlineArray)
		
		var parentTaskName = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		parentTaskName[${status.index}]= "${parentTaskList[status.index].taskCategory.categoryName}";
		</c:forEach>
		console.log(parentTaskName);
		
		var parentTaskNo = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		parentTaskNo[${status.index}] = "${parentTaskList[status.index].taskNo}";
		</c:forEach>
		console.log(parentTaskNo);
		
		var parentProgress = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		parentProgress[${status.index}] = "${parentTaskList[status.index].progression}";
		</c:forEach>
		console.log(parentProgress);
		// 상위업무 정보 끝
		
		//하위업무 정보 시작
		console.log("========================하위업무=================================")
		var childTaskList = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childTaskList[${status.index}] = "${childTaskList[status.index]}";
		</c:forEach>
		console.log(childTaskList.length);
		
		var childStartDateArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childStartDateArr[${status.index}] = "${childTaskList[status.index].startDate}";
		</c:forEach>
		console.log(childStartDateArr);
		
		var childDeadlineArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childDeadlineArr[${status.index}] = "${childTaskList[status.index].deadline}";
		</c:forEach>
		console.log(childDeadlineArr);
		
		var childTaskName = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childTaskName[${status.index}] = "${childTaskList[status.index].taskCategory.categoryName}";
		</c:forEach>
		console.log(childTaskName);
		
		var childTaskNoArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childTaskNoArr[${status.index}] = "${childTaskList[status.index].taskNo}";
		</c:forEach>
		console.log(childTaskNoArr);
		
		var childTaskProgressionArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childTaskProgressionArr[${status.index}] = "${childTaskList[status.index].progression}";
		</c:forEach>
		console.log(childTaskProgressionArr);
		
		var childDependenciesArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childDependenciesArr[${status.index}] = "${childTaskList[status.index].parentTaskNo}";
		</c:forEach>
		console.log(childDependenciesArr);
		
		
		console.log(taskArray.length);
		
		var taskArray = new Array();
		console.log(taskArray)
		
		for(let i = 0; i < 5 ; i++){
			taskArray.push({ start : startDateArray[i],
                end : deadlineArray[i],
                name : parentTaskName[i],
                id : parentTaskNo[i],
                progress : Number(parentProgress[i])
                })
		}
		
		for(let i = 0; i < childTaskList.length; i++){
			taskArray.push({ start : childStartDateArr[i],
                end : childDeadlineArr[i],
                name : childTaskName[i],
                id : childTaskNoArr[i],
                progress : Number(childTaskProgressionArr[i]),
                dependencies : childDependenciesArr[i]
                })
		}
		
		
		
		
		
		var gantt_chart = new Gantt(".gantt-target", taskArray, {
			on_click : function(task) {
				console.log(task);
			},
			on_date_change : function(task, start, end) {
				console.log(task, start, end);
			},
			on_progress_change : function(task, progress) {
				console.log(task, progress);
			},
			on_view_change : function(mode) {
				console.log(mode);
			},
			view_mode : 'Day',
			language : 'en'
		});
		console.log(gantt_chart);

		// 모달 이벤트
		$(".layerPopup").click(function() {
			$(".layer").css("display", "block");
			$(".layer-bg").css("display", "block");
			
			$.ajax({
				url : "/waterfall/task/findModalInfo",
				type : "get",
				data : {"taskNo" : no},
				success : function(data, textStatus, xhr){
					console.log(data);
					
					$("")
				}
			})
		});
		$(".layer .close").click(function() {
			$(".layer").css("display", "none");
			$(".layer-bg").css("display", "none");
		});
		
	</script>
	
</body>
</html>