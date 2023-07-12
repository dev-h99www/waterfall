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
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">

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
	font-family: 'Noto Sans KR', sans-serif;
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
	width: 850px;
	height: 750px;
	background: #fff;
	border: 1px solid black;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: 2500;
	/* font-size: 1rem; */
	cursor: pointer;
	border-radius:10px;
	overflow : auto;
}
.add{
	display: none;
	width: 770px;
	height: 530px !important;
	background: #fff;
	border: 1px solid black;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: 2500;
	/* font-size: 1rem; */
	cursor: pointer;
	border-radius:10px;
	overflow : auto;
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


.header{
	background-color: #3E3C3C;
	color : white;
	padding:10px;
	border-top-left-radius:10px;
	border-top-right-radius:10px;
}
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
#footerBtn{
	padding-left: 500px;
}
#readModal{

}

#parentTaskDetail{
	width : 40%;
	float : left;
	
}

#childTaskDetail{
	width : 50%;
	float : right;
	
	
}
.task{
	border:solid;
	padding:5px;
	border-color:gray;
	border-radius:5px;
	border-width : 1px;
	
}
#outputModal.modal-overlay {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	top: 0;
	display: none;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
	backdrop-filter: blur(0.5px);
	-webkit-backdrop-filter: blur(0.5px);
	border-radius: 10px;
	border: 1px solid rgba(255, 255, 255, 0.18);
}
#outputModal .modal-window {
	background: rgba( 69, 139, 197, 0.70 );
	box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
	backdrop-filter: blur( 13.5px );
	-webkit-backdrop-filter: blur( 13.5px );
	border-radius: 10px;
	border: 1px solid rgba( 255, 255, 255, 0.18 );
	width: 400px;
	height: 500px;
	position: relative;
	top: -100px;
	padding: 10px;
        }
#outputModal .title {
	padding-left: 10px;
	display: inline;
	text-shadow: 1px 1px 2px gray;
	color: white;
            
}
#outputModal .title h2 {
	display: inline;
}
#outputModal .close-area {
	display: inline;
	float: right;
	padding-right: 10px;
	cursor: pointer;
	text-shadow: 1px 1px 2px gray;
	color: white;
}
        
#outputModal .content-area {
	margin-top: 20px;
	padding: 0px 10px;
	text-shadow: 1px 1px 2px gray;
	color: white;
}
#parentTaskModifyBtn, #childTaskModifyBtn{
	background-color: #9B91BF;
	color: white;
	margin : 0px;
}
#parentTaskDeleteBtn, #childTaskDeleteBtn{
	background-color : #D16B6B;
	color: white;
	margin : 3px;

}


#registModal {
	align-items: center;
	justify-content: center;
}

#addOutput1, #addOutput2{
	background-color: #92B8DE;
	color : white;
}

#addIssue1, #addIssue2{
	background-color: #FBEB83;
	color : gray;
}

#addTaskBtn{
	background-color: #9ED2B2;
}

</style>
<script>
	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />
	<jsp:include page="/WEB-INF/views/task/IssueModal.jsp"/>
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
					<label  type="button" class="folder_toggle"
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
		<jsp:include page="/WEB-INF/views/task/taskModal.jsp"/>	
	</div>
	
	<script>
	<%--Modal drag 이벤트 --%>
	/* $(function(){
		$('#addModal').draggable({ handle: ".header" });

	}); */
	
	$("#parentTaskDeleteBtn").click(function(){
		
		if($("#readModal").css("display")=="none"){
			$("#readModal").fadeIn().css("display", "blcok");
		} else{
			$("#readModal").fadeOut().css("display", "none");
		}
		$("#delete").click(function(){
			const deleteParentTaskNo = $("#outputParentTaskNo").val();
			console.log(deleteParentTaskNo);
			
			/*상위 업무 삭제를 클릭했을 때*/
			location.href="${ pageContext.servletContext.contextPath }/task/delete?taskNo=" + deleteParentTaskNo ;
		});
	});
	$("#childTaskDeleteBtn").click(function(){
		
		if($("#readModal").css("display")=="none"){
			$("#readModal").fadeIn().css("display", "blcok");
		} else{
			$("#readModal").fadeOut().css("display", "none");
		}
		$("#delete").click(function(){
			const deleteChildTaskNo = $("#outputChildTaskNo").val();
			console.log(deleteChildTaskNo);
			
			/*하위 업무 삭제를 클릭했을 때*/
			location.href="${ pageContext.servletContext.contextPath }/task/delete?taskNo=" + deleteChildTaskNo ;
		});
		
	});
	$("#cancelDelete").click(function(){
		if($("#readModal").css("display")=="none"){
			$("#readModal").fadeIn().css("display", "blcok");
		} 
	});

	
	<%-- 상위 업무 수정 이벤트 시작 --%>
	/* 업무조회 모달에서 수정버튼을 클릭했을 경우 */
	$("#parentTaskModifyBtn").click(function(){
		/* 수정모달에 보여질 정보들을 넣어준다. */
		const modifyTaskCode= $("#parentTaskCode").val();
		const modifyMemberName = $("#parentTaskManager").val();
		const modifyMemberNo = $("#parentTaskManagerNo").val();
		const modifyStartDate = $("#parent-start-date").val();
		const modifyDeadline = $("#parent-end-date").val();
		const modifyImportance = $("#parentImportance").val();
		const modifyRatio = $("#parentProgress").val();
		const modifyStatus = $("#parentStatus").val();
		const modifyTaskNo = $("#outputParentTaskNo").val();
		console.log(modifyTaskCode);
		console.log(modifyMemberName);
		console.log(modifyStartDate);
		console.log(modifyDeadline);
		console.log(modifyImportance);
		console.log(modifyRatio);
		console.log(modifyTaskNo);
		$("#modifyCategoryName").val(modifyTaskCode).prop("selected", true);		//value가 선택한 taskCode와 동일한 것을 선택
		$("#selectedNull").val('미지정').prop("selected", true);
		$("#modifyMember").val(modifyMemberNo).prop("selected", true);
		$("#modifyStartDate").val(modifyStartDate).prop("selected", true);
		$("#modifyDeadline").val(modifyDeadline).prop("selected", true);
		$("#modifyImportance").val(modifyImportance).prop("selected", true);
		$("#modifyStatus").val(modifyStatus).prop("selected", true);
		$("#modifyRatio").val(modifyRatio);
		$("#modifyTaskNo").val(modifyTaskNo);
		
		
		if($("#modifyModal").css("display")=="none"){
			$("#modifyModal").fadeIn().css("display", "block");
			$("#readModal").fadeOut().css("display", "none");
		}	
	});
	
	/* 하위업무 수정을 클릭하였을 때 */
	$("#childTaskModifyBtn").click(function(){
		/* 수정모달에 보여질 정보들을 넣어준다. */
		const modifyTaskCode= $("#childTaskCode").val();
		const modifyMemberName = $("#childTaskManager").val();
		const modifyMemberNo = $("#childTaskManagerNo").val();
		const modifyStartDate = $("#child-start-date").val();
		const modifyDeadline = $("#child-end-date").val();
		const modifyImportance = $("#childImportance").val();
		const modifyRatio = $("#childProgress").val();
		const modifyStatus = $("#childStatus").val();
		const modifyParentTaskCode = $("#parentTaskCode").val();
		const modifyTaskNo = $("#outputChildTaskNo").val();
		
		console.log(modifyTaskCode);
		console.log(modifyMemberName);
		console.log(modifyStartDate);
		console.log(modifyDeadline);
		console.log(modifyImportance);
		console.log(modifyRatio);
		console.log(modifyParentTaskCode);
		console.log(modifyTaskNo);
		$("#modifyCategoryName").val(modifyTaskCode).prop("selected", true);		//value가 선택한 taskCode와 동일한 것을 선택
		$("#modifyParentTaskCode").val(modifyParentTaskCode).prop("selected", true);
		$("#modifyMember").val(modifyMemberNo).prop("selected", true);
		$("#modifyStartDate").val(modifyStartDate).prop("selected", true);
		$("#modifyDeadline").val(modifyDeadline).prop("selected", true);
		$("#modifyImportance").val(modifyImportance).prop("selected", true);
		$("#modifyStatus").val(modifyStatus).prop("selected", true);
		$("#modifyRatio").val(modifyRatio);
		$("#modifyTaskNo").val(modifyTaskNo);
		
		if($("#modifyModal").css("display")=="none"){
			$("#modifyModal").fadeIn().css("display", "block");
			$("#readModal").fadeOut().css("display", "none");
		}	
	});
	
	$("#closeModify").click(function(){
		$("#modifyModal").fadeOut().css("display", "none");
		$("#readModal").fadeIn().css("display", "block");
		
	});


	<%-- 상위업무 산출물 --%>
	$("#addOutput1").click(function(){
		if($("#outputModal").css("display")=="none"){
			$("#outputModal").fadeIn().css("display", "flex");
			$("#readModal").fadeOut().css("display", "none");
			
		}
		
		const taskNo = Number($("#outputParentTaskNo").val());
		$("#outputModalTaskNo").val(Number(taskNo));
		console.log(Number(taskNo));
		console.log($("#outputModalTaskNo").val());
	});
	
	<%-- 하위업무 산출물 --%>
	$("#addOutput2").click(function(){
		if($("#outputModal").css("display")=="none"){
			$("#outputModal").fadeIn().css("display", "flex");
			$("#readModal").fadeOut().css("display", "none");
			
		}
		
		const taskNo = $("#outputChildTaskNo").val();
		$("#outputModalTaskNo").val(Number(taskNo));
		console.log(Number(taskNo));
		console.log($("#outputModalTaskNo").val());
	});
	
	$("#closeOutput").click(function(){
		$("#readModal").fadeIn().css("display", "block");
		$("#outputModal").fadeOut().css("display", "none");
	});
	
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
		
		$(".addTask").hover(function(){
			$(this).css({
				"border" : "solid",
				"border-color" : "gray",
			    "border-radius" : "5px",
			    "border-width" : "1px"});
			},function(){
				$(this).css({"border-color" : "white"});
		});
		
		$(".close").hover(function(){
			$(this).css({
				"border" : "solid",
				"border-color" : "gray",
			    "border-radius" : "5px",
			    "border-width" : "1px"});
			}, function(){
				$(this).css({"border-color" : "white"});
		});
	<%-- hover 이벤트 끝 --%>
	

		// 모달 이벤트
		$(".layerPopup").click(function() {
			$("#addModal").fadeIn().css("display", "block");
			$(".layer-bg").css("display", "block");
			
			
		});
		$(".layer #close").click(function() {
			$("#addModal").fadeOut().css("display", "none");
			$(".layer-bg").css("display", "none");
		});
		
		$(".layer #close").click(function() {
			$("#readModal").fadeOut().css("display", "none");
			$(".layer-bg").css("display", "none");
		});
		
		$(".layer #close").click(function() {
			$("#modifyModal").fadeOut().css("display", "none");
			$(".layer-bg").css("display", "none");
		});
		
		
		/* 상세조회 이벤트 */
		const $labels = document.querySelectorAll("#childTask label");
		console.log($labels);
		const $taskNo = document.querySelectorAll("#childTask input");
		console.log($taskNo);
		
		for(let i = 0; i < $labels.length; i++){
			const taskNo = $($taskNo[i]).val();
			console.log(taskNo);
			$($labels[i]).click(function() {
				$.ajax({
					url : "/waterfall/task/detail",
					type : "get",
					data : {"taskNo" : taskNo},
					success : function(data, textStatus, xhr) {
						const taskDetail = JSON.parse(data.taskDetail);
						console.log(data);
						console.log(taskDetail.taskNo);
						console.log(taskDetail.parentTask.taskNo);
						console.log(taskDetail.parentTask.startDate);
						
						/* const parentStart = taskDetail.parentTask.startDate;
						const strArr = parentStart.split('-');
						const day = Number(strArr[2]) + 1;
						const parentStartDate = strArr[0] + "-" + strArr[1] + "-" + day;
						
						const parentEnd = taskDetail.parentTask.deadline;
						const strArr2 = parentEnd.split('-');
						const day2 = Number(strArr2[2]) + 1;
						const parentEndDate = strArr2[0] + "-" + strArr2[1] + "-" + day2;
						console.log(parentEndDate);
						
						const childStart = taskDetail.startDate;
						const strArr3 = childStart.split('-');
						const day3 = Number(strArr3[2]) + 1;
						const childStartDate = strArr3[0] + "-" + strArr3[1] + "-" + day3;
						console.log(childStartDate);
						
						const childEnd = taskDetail.deadline;
						const strArr4 = childEnd.split('-');
						const day4 = Number(strArr4[2]) + 1;
						const childEndDate = strArr4[0] + "-" + strArr4[1] + "-" + day4;
						console.log(childEndDate); */
						
						//상위업무
						$("input[name=parentTaskName]").val(taskDetail.parentTask.taskCategory.categoryName);
						
						$("input[name=parentTaskManager]").val(taskDetail.parentTask.managerName);
						
						$("input[name=parentTaskManagerNo]").val(taskDetail.parentTask.managerNo);
						
						$("input[name=parentStartDate]").val(taskDetail.parentTask.startDate);
						
						$("input[name=parentDeadline]").val(taskDetail.parentTask.deadline);
						
						$("input[name=parentImportance]").val(taskDetail.parentTask.importance);
						
						$("input[name=parentProgress]").val(taskDetail.parentTask.progression);
						
						$("input[name=parentTaskNo]").val(taskDetail.parentTask.taskNo);
						
						$("input[name=parentTaskCode]").val(taskDetail.parentTask.taskCategory.categoryCode);
						
						$("input[name=parentStatus]").val(taskDetail.parentTask.progressStatus);
						
						//하위 업무
						$("input[name=childTaskName]").val(taskDetail.taskCategory.categoryName);
						
						$("input[name=childTaskCode]").val(taskDetail.taskCategory.categoryCode);
						
						$("input[name=childTaskManager]").val(taskDetail.managerName);
						
						$("input[name=childTaskManagerNo]").val(taskDetail.managerNo);
						
						$("input[name=childStartDate]").val(taskDetail.startDate);
						
						$("input[name=childDeadline]").val(taskDetail.deadline);
						
						$("input[name=childImportance]").val(taskDetail.importance);
						
						$("input[name=childProgress]").val(taskDetail.progression);
						
						$("input[name=childTaskNo]").val(taskDetail.taskNo);
						
						$("input[name=childStatus]").val(taskDetail.progressStatus);
						
						
						
						$("#readModal").fadeIn().css("display", "block");
						$(".layer-bg").css("display", "block");
						
					}, error:function(data){
						console.log(data);
					}
					
				});
			});
		}
		/* 상세조회 이벤트 끝 */
		
	<%--Gantt Chart data--%>

		var parentTaskArray = new Array();
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		parentTaskArray[${status.index}] = "${parentTaskList[status.index]}";
		console.log(parentTaskArray[${status.index}]);
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
		
		
		
		var taskArray = new Array();
		console.log(taskArray.length)
		
		for(let i = 0; i < parentTaskArray.length ; i++){
			taskArray.push(
				{ start : startDateArray[i],
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
		
		/* 이슈 등록 기능 모달 이벤트 */
		
		$("#closeIssue").click(function(){
			$("#readModal").css("display", "block");
			$("#registModal").css("display", "none");
		});
		
		$("#addIssue1").click(function(){
			if($("#registModal").css("display")=="none"){
				$("#registModal").css("display", "flex");
				$("#readModal").css("display", "none");
			
			}
			
			var projectNo = ${ sessionScope.projectAutority.projectNo };
		   	$("#read-projectNo").val(projectNo);

		    console.log("프로젝트 번호는 : " + projectNo);
		      
//		    var $taskNo = Number($("input[name=parentTaskNo]").val());
			
			const taskNo = Number($("input[name=parentTaskNo]").val());
			$("#read-taskNo").val(taskNo);
			console.log(Number(taskNo));
			console.log($("#read-taskNo").val());
		});
		
		$("#addIssue2").click(function(){
			if($("#registModal").css("display")=="none"){
				$("#registModal").css("display", "flex");
				$("#readModal").css("display", "none");
			}
			
//			var $taskNo = Number($("input[name=childTaskNo]").val());
			
			var projectNo = ${ sessionScope.projectAutority.projectNo };
		    $("#read-projectNo").val(projectNo);

		    console.log("프로젝트 번호는 : " + projectNo);
		      
		    var $taskNo = Number($("input[name=childTaskNo]").val());
			
			const taskNo = Number($("input[name=childTaskNo]").val());
			$("#read-taskNo").val(taskNo);
			console.log(Number(taskNo));
			console.log($("#read-taskNo").val());
		});
console.log(new Date());
		
	</script>
	
</body>
</html>