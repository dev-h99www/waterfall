<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<style>
	.show-project-info{
		display:none;
	}
	.project-name-area{
		height: 2.6em;
		
		background-color: #343A40;
		color: #bebebe;
	}
	.project-name-area > input{
		margin: 1%;
		font-size: 1.2em;
		font-weight: 1000;
		color: #bebebe;
	}
	#thead-css {
		
		border-radius: 3%;
		background-color: #343A40;
		color: #bebebe;
	}
	
	.admin-main-projectlist{

		color: #343A40;
		background-color: #ffffff;
	}
	.history-area{
		height: 85%;
		overflow: auto;
	}
	.history-area > h5{
		margin-top: 10px;
	}
	th {
		height: 2.4em;
	}
	td {
		font-size: 1em;
	}
	tr {
		height: 2em;
	}
	hr {
		height: 2px;
		background-color: goldenrod;
	}
	.print-area {
		height: 300px;
	}
	.inbox {
		margin-left: 1%;
		width: 99%;
		height: 100%;
		border: 2px solid #343A40;
		border-radius: 5px;
	}
	.issuebox {
		margin-top: 15%;
		margin-left: 0%;
		width: 77%;
		height: 43%;
		border: 1px solid #343A40;
		border-radius: 5px;
	}
	.issue-name-area{
		height: 2.6em;
		background-color: #343A40;
		color: #bebebe;
	}
	.issue-name-area > label{
		margin: 2%;
		font-size: 1.2em;
		font-weight: 1000;
		color: #bebebe;
	}
	.taskbox {
		margin-top: 15%;
		margin-left: 0%;
		width: 70%;
		height: 57%;
		border: 1px solid #343A40;
		border-radius: 5px;
	}
	.task-name-area{
		height: 2.6em;
		background-color: #343A40;
		color: #bebebe;
	}
	.task-name-area > label{
		margin: 2%;
		font-size: 1.2em;
		font-weight: 1000;
		color: #bebebe;
	}
	.task-amout-area > label{
		margin-top: 1%;
		margin-left: 3%;
	}
	.ml-3 {
		margin-left: 3%;
	}
	.projectdetail {
		border: 1px solid;
	}
	
</style>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
	<div style="margin:1%">
		<div class="row" style="border: 1px; height:20px">
			<h3>관리자님 환영합니다</h3>
			<hr style="border: 1px;">
		</div>
		<div class="row" style="height: 40px;"></div>
		<div class="row ">
			<div id="test" class="col-3 print-area">
				<div class="inbox admin-main-projectlist">
					<div style="height: 80%;">
						<table style="width: 100%;font-size:1.1em;">
							<colgroup>
								<col style="width: 15%" />
								<col style="width: 65%" />
								<col style="width: 20%" />
								
							</colgroup>
							<thead id="thead-css">
								<th></th>
								<th>프로젝트명</th>
								<th>PM이름</th>
								
							</thead>
							<tbody id="project-list">
								<c:forEach var="project" items="${ requestScope.projectList }" varStatus="status">
									<tr>
										<td><input type="hidden" value="${ project.no }"></td>
										<td><c:out value="${ project.name }"/></td>
										<td><c:out value="${ project.member.memberName }"/></td>
									</tr>
								</c:forEach>							
							</tbody>
						</table>
					</div>
					<div class="mt-4" style="text-align: center;">
						<jsp:include page="/WEB-INF/views/main/mainadminpaging.jsp"/>
					</div>
				</div>
			</div>
			<div class="col-3 print-area show-project-info">
				<div class="projectdetail inbox ml-3" >
					
					<div class="project-name-area">
						<input id="admin-main-project-name"  type="text" style="border: none; background: transparent;"><br>
					</div>
					<div style="margin:1%; margin-left:5%;font-size: 1em"> 
						<label class="mt-2">PM : <input id="admin-main-project-pm" type="text" style="width:100px; border: none; background: transparent;"></label><br>
						<label class="mt-2">시작일 : <input id="admin-main-project-startdate" type="text" style="width:180px; border: none; background: transparent;"></label>
						<label class="mt-2">마감일 : <input id="admin-main-project-deadline" type="text" style="width:180px; border: none; background: transparent;"></label>
						<label class="mt-2">상태 : <input id="admin-main-project-statuscode" type="text" style="width:180px; border: none; background: transparent;"></label>
						<label class="mt-2">진행률 : <input id="admin-main-project-progression" type="text" style="width:180px; border: none; background: transparent;"></label>
						<label class="mt-2">산출물 : <input id="admin-main-project-output" type="text" style="width:180px; border: none; background: transparent;"></label>
					</div>
				</div>
			</div>
			<div class="col-5 print-area show-project-info">
				<div class="inbox ml-3">
					<div class="project-name-area" >
						<input id="admin-main-project-name"  value="히스토리" type="text" style="border: none; background: transparent;">
					</div>
					<div class="history-area" id="history-area">
					</div>
				</div>
			</div>
		
		</div>
	</div>
	<div class="row print-area show-project-info" style="width: 98%; height: 100%;margin-left: 1%">
		<div class="col-6">
			<div class="row" style="height:100%">  
				<div class="col-8 show-project-info" id="issuechart" style="width: 70%; height: 80%;"></div>
				<div class="col">
					<br>
					<div class="issuebox show-project-info">
						<div class="issue-name-area" >
							<label class="mt-2 ml-3">이슈</label>							
						</div>
						<div>
							<label class="show-project-info  mt-2 ml-3">대기중 이슈<input type="text" id="issue-category-wait"   style="width:70px; border: none; background: transparent;"></label><br>
							<label class="show-project-info  mt-2 ml-3">처리중 이슈<input type="text" id="issue-category-proc"  style="width:70px; border: none; background: transparent;"></label><br>
							<label class="show-project-info  mt-2 ml-3">해결된 이슈<input type="text" id="issue-category-solv"  style="width:70px; border: none; background: transparent;"></label>	<br>	
						</div>
					</div>
				</div>
			</div>
			
		</div> 
		<div class="col-6">
			<div class="row" style="height:100%">  
				<div class="col-8 show-project-info" id="taskchart" style="width: 65%; height: 80%;"></div>
				<div class="col mt-4 show-project-info">
					<div class="taskbox">
						<div class="task-name-area">
							<label>업무</label>
						</div>
						<div class="task-amout-area">
							<label class="show-project-info " >총 업무 <input type="text" id="task-category-totl" style="width:50px; border: none; background: transparent;"></label>
							<label class="show-project-info ">진행전 업무 <input type="text"id="task-category-befo" style="width:50px; border: none; background: transparent;"></label>
							<label class="show-project-info ">진행중 업무 <input type="text" id="task-category-proc" style="width:50px; border: none; background: transparent;"></label>
							<label class="show-project-info ">테스트중 업무 <input type="text" id="task-category-test" style="width:50px; border: none; background: transparent;"></label>
							<label class="show-project-info ">완료 업무<input type="text" id="task-category-done" style="width:50px; border: none; background: transparent;"></label>
							<label class="show-project-info ">보류 업무 <input type="text" id="task-category-pend" style="width:50px; border: none; background: transparent;"></label>
						</div>
					</div>
				</div>
			</div>
		</div> 
	</div>

	

	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
	<script>
		$(function() {
			$("#project-list td").hover(function() {
				$(this).parent().css({"background":"rgba(29, 22, 22, 0.106)", "cursor":"pointer"});
			}).mouseleave(function() {
				$(this).parent().css({"background":"white", "color":"#black"});
				
			}).click(function() {
				$projectNo = this.parentNode.children[0].children[0].value;
				
				$.ajax({
					type: "get",
					url: "${ pageContext.servletContext.contextPath }/menu/main/admin/project/" + $projectNo,
					success: function(data) {
						
						$project = JSON.parse(data.project);
						$projectHistory = JSON.parse(data.projectHistory);
						$projectInfo = $project.projectInfo;
						$("#admin-main-project-name").val($project.name);
						$("#admin-main-project-pm").val($project.member.memberName);
						$("#admin-main-project-startdate").val($project.startDate);
						$("#admin-main-project-deadline").val($project.deadLine);
						$("#admin-main-project-statuscode").val($project.statusCode);
						$("#admin-main-project-progression").val($project.progression);
						$("#admin-main-project-output").val($projectInfo.outputAmount);

						$("#issue-category-wait").val($projectInfo.watingIssueAmount + "개");
						$("#issue-category-proc").val($projectInfo.progressingIssueAmount + "개");
						$("#issue-category-solv").val($projectInfo.solvedIssueAmount + "개");


						$totalTaskAmount = $projectInfo.beforeProceedingTaskAmount + $projectInfo.progressingTaskAmount
									+ $projectInfo.testingTaskAmount + $projectInfo.finishedTaskAmount + $projectInfo.pendingTaskAmount;

						$("#task-category-totl").val($totalTaskAmount + "개");
						$("#task-category-befo").val($projectInfo.beforeProceedingTaskAmount + "개");
						$("#task-category-proc").val($projectInfo.progressingTaskAmount + "개");
						$("#task-category-test").val($projectInfo.testingTaskAmount + "개");
						$("#task-category-done").val($projectInfo.finishedTaskAmount + "개");
						$("#task-category-pend").val($projectInfo.pendingTaskAmount + "개");
						
						for(var i = 0; i < $projectHistory.length; i++){
							$historyLog = '<label class="mt-2">' + $projectHistory[i].content + '</label>';
							$("#history-area").append($historyLog);
						}

						google.charts.load('current', {'packages':['corechart']});
						google.charts.setOnLoadCallback(drawissueChart);
						google.charts.setOnLoadCallback(drawtaskChart);

						function drawissueChart() {

							var data = google.visualization.arrayToDataTable([
								['Status', 'Amount'],
							  	['대기중', $projectInfo.watingIssueAmount],
							  	['처리중', $projectInfo.progressingIssueAmount],
							  	['완료', $projectInfo.solvedIssueAmount]
							]);
							
							var options = {
							  	title: '이슈',
							  	slices: {
						    	0: { color: 'yellowgreen' },
							    1: { color: 'orangered' },
							    2: { color: 'cyan' }
							  	},
							  	sliceVisibilityThreshold:0
						
							
							};
							
							var chart = new google.visualization.PieChart(document.getElementById('issuechart'));
							
							chart.draw(data, options);
				      	}

						function drawtaskChart() {

							var data = google.visualization.arrayToDataTable([
								['Status', 'Amount'],
								['진행전', $projectInfo.beforeProceedingTaskAmount],
								['진행중', $projectInfo.progressingTaskAmount],
								['테스트중', $projectInfo.testingTaskAmount],
								['진행완료', $projectInfo.finishedTaskAmount],
								['보류', $projectInfo.pendingTaskAmount]
								]);

							var options = {
								title: '업무',
				         	 	slices: {
					            0: { color: 'yellowgreen' },
					            1: { color: 'orangered' },
					            2: { color: 'gold' },
					            3: { color: 'black' },
					            4: { color: 'hotpink' }
				          		},
							  	sliceVisibilityThreshold:0
							};

							var chart1 = new google.visualization.PieChart(document.getElementById('taskchart'));

							chart1.draw(data, options);
						}
						
    					$(".show-project-info").fadeIn();	
					},
					error: function(xhr, status, error) {
						alert('admin mainpage/ find project list /network error');
					}
						
				});
			});
		});
	</script>
	
	
	
	
	
</body>
</html>









































