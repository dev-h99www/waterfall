<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<title>Insert title here</title>

<style>

/* 관리중인 프로젝트 정보 숨기는 css */
.show-manage-project-info{
	display: none;
}
.show-manage-project-detail-info{
	display: none;
}
/* 참여중인 프로젝트 정보 숨기는 css */
.show-join-project-info{
	display: none;
}
.show-join-project-info-cg {
	display: none;
	width: 200px;
}


/* 관리 프로젝트 프로젝트정보 css */
.manage-project-info {
	border: 1px solid black;
	margin-top: 3%;
	border-radius: 5px;
	height: 180px;
}


.manage-project-info-head{
	height: 32px;
	border-radius: 3%;
	border: 1px #343A40 solid;
	background-color: #343A40;
	color: #bebebe;
}
.manage-project-info-head > label {
    margin-left: 2%;
    margin-top: 1px;
    font-size: 1.08em;
    font-weight: 1000;
}

.manage-project-info-body > label {
	width: 30%;
	margin-left: 2%;
	font-size: 0.9em;
	font-weight: 800;
 }
.manage-project-info-body > input {
	margin-left: 2%;
	width:150px; 
	border: none; 
	background: transparent;
 }

/* 테이블리스트 출력 css */
.dev-tbody-css {
	font-size: 0.96em;
	font-weight: 200;
	height: 80%;
}
.dev-tbody-css > tr {
	margin-top: 20%;
}
 /* 관리 프로젝트 원형차트 css부분 */
.manageprojectbox {
	margin-top: 6%;
	width: 90%;
	border: 1px solid black;
	border-radius: 3px;
}


.mpb-chart-head {
	height: 32px;
	border-radius: 3%;
	border: 1px #343A40 solid;
	background-color: #343A40;
	color: #bebebe;
	font-size: 1em;
	font-weight: 1000;
}
.mpb-chart-head > label {
	margin-left: 3%;
	margin-top: 1%;
}
.manage-projectbox-body > label{
	margin: 2%;	
	font-size: 0.9em;
	font-weight: 700;
	width: 100%;
}

.manage-projectbox-body > label > input{
	border: none; 
	background: transparent;
	width:50px;
	margin-left: 5%;
}



/* 참여중인 프로젝트 상세정보 css */
.join-project{
	border:#343A40 1px solid;
	border-radius: 5px;
	height: 265px;
	margin-top: 2%;
}
.join-project-head {
	height: 32px;
	border-radius: 3%;
	border: 1px #343A40 solid;
	background-color: #343A40;
	color: #bebebe;
	font-size: 1.1em;
	font-weight: 1000;
}
.join-project-head > label{
	margin-top: 2px;
	margin-left: 2%;
}

.join-project-body > label{
	margin: 2%;	
	margin-left: 2%;
	font-size: 0.9em;
	font-weight: 700;
	width: 100%;
}

.join-project-body > label > input{
	border: none; 
	background: transparent;
	width: auto;
	margin-left: 5%;
}


.developer-main-table {
	border: 1px solid black;
	height: 90%;
	margin: 2%;
	margin-left: 5%;
	border-radius: 5px;
}
.thead-css {
		border-radius: 3%;
		background-color: #343A40;
		color: #bebebe;
}



.project-category-label{
	margin-top: 2%;
}
.project-category-label > label {
	margin-left: 2%;
	font-size: 1.1em;
	font-weight: 1000;
}

.develop-main-header{
 	height: 100px;
 }
 .develop-main-header > div > label{
	text-align: center;
	font-size: 1.5em;
	font-weight: 1000;
	margin-left: 2%;
	
 }

 .develop-main-body-top{
	 
	 height: 300PX;
 }


</style>

</head>
<body style="overflow-x:hidden; overflow-y:auto;">

	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

	
	<div class=" develop-main-header">
		<div class="mt-5 ">
			<label>${ sessionScope.loginMember.name } 님 환영합니다</label>	
		</div>
	</div>
	
	<div class=" project-category-label">
		<label>관리중인 프로젝트</label>
	</div>
	<!-- 관리중인 프로젝트 폼-->
	<div class="mt-2 row  develop-main-body-top">
		<div class=" col-4">
			<div class="developer-main-table">
				<div style="height: 90%;">
					<table style="width: 100%;font-size:1.1em;">
						<colgroup>
							<col style="width: 15%" />
							<col style="width: 65%" />
							<col style="width: 20%" />
							
						</colgroup>
						<thead class="thead-css">
							<th></th>
							<th>프로젝트명</th>
							<th>PM이름</th>
						</thead>
						<tbody id="dev-manage-project-list" class="dev-tbody-css">
							<c:forEach var="project" items="${ requestScope.projectList }" varStatus="status">
								<tr>
									<td><input readonly type="hidden" value="${ project.no }"></td>
									<td><c:out value="${ project.name }"/></td>
									<td><c:out value="${ project.member.memberName }"/></td>
								</tr>
							</c:forEach>							
						</tbody>
					</table>
				</div>
				<jsp:include page="/WEB-INF/views/main/mainpaging.jsp"/>
			</div>
		</div>



		<div class="col-3 show-manage-project-info">
			<div class="manage-project-info">
				<div class="manage-project-info-head">
					<label id="dev-manage-project-name">프로젝트 정보</label>
				</div>
				<div class="manage-project-info-body mt-2 ">
					<label>시작일 : </label><input id="dev-manage-project-startdate" readonly type="date" value="2022-01-01"><br>
					<label>마감일 : </label><input id="dev-manage-project-deadline"readonly type="date" value="2022-03-14"><br>
					<label for="dev-manage-project-taskcount">&nbsp; 업무 &nbsp;: </label><input id="dev-manage-project-taskcount" readonly type="text" value="1개"><br>
					<label for="dev-manage-project-issuecount">&nbsp; 이슈 &nbsp;: </label><input id="dev-manage-project-issuecount" readonly type="text" value="1개"><br>
					<label for="dev-manage-project-outputcount">산출물 : </label><input id="dev-manage-project-outputcount" readonly type="text" value="1개"><br>
				</div>
			</div>
		</div>
		
		
		
		<div class=" col-5">
		<div class="row" style="height: 100%;">
			<div class="col-8 show-manage-project-detail-info" id="manage-info-chart"></div>
			
			<div class="col-4">
				<div class="manageprojectbox show-manage-project-detail-info">
					<div class="mpb-chart-head" >
						<label class="" id="manage-project-info-category"></label>							
					</div>
					<div class="manage-projectbox-body" id="manage-project-detail-body">
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>

	<div class=" project-category-label">
		<label>참여중인 프로젝트</label>
	</div>

	

	<!-- 참여중인 프로젝트 폼-->
	<div class="mt-2 row  develop-main-body-top">
		<div class=" col-4">
			<div class="developer-main-table">
				<div style="height: 90%;">
					<table style="width: 100%;font-size:1.1em;">
						<colgroup>
							<col style="width: 15%" />
							<col style="width: 65%" />
							<col style="width: 20%" />
							
						</colgroup>
						<thead class="thead-css">
							<th></th>
							<th>프로젝트명</th>
							<th>PM이름</th>
							
						</thead>
						<tbody id="dev-join-project-list" class="dev-tbody-css">
							<c:forEach var="project" items="${ requestScope.joinProjectList }" varStatus="status">
								<tr>
									<td><input readonly type="hidden" value="${ project.no }"></td>
									<td><c:out value="${ project.name }"/></td>
									<td><c:out value="${ project.member.memberName }"/></td>
								</tr>
							</c:forEach>							
						</tbody>
					</table>
				</div>
				<div calss="mt-4">
					<jsp:include page="/WEB-INF/views/main/subpaging.jsp"/>
				</div>
			</div>
		</div>
	
		<div class=" col-3">
			<div class="join-project show-join-project-info">
				<div class="join-project-head">
					<label id="dev-join-project-name">프로젝트 이름</label>
				</div>
				<div class="join-project-body">
					<label>프로젝트 PM : <input id="dev-join-project-pm-name" readonly type="text" value=""></label><br>
					<label>프로젝트 생성일 : <input id="dev-join-project-startdate" readonly type="date" value="2022-01-01"></label><br>
					<label>프로젝트 참여일 : <input id="dev-join-project-deadline" readonly type="date" value="2022-01-01"></label><br>
					<label>프로젝트 역할 : <input id="dev-join-project-role" readonly type="text" value="DBA QA 테스터"></label><br>
				</div>
			</div>
		</div>
	
	
	
		<div class="col-5">
			<div class="show-join-project-info-cg" id="top_x_div" style="width: 100%; height: 100%;"></div>

		</div>
	</div>


	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>


<script>

	/* 관리중인 프로젝트에 대한 상세정보 및 차트 이벤트 부분 */
	$(function() {
		$("#dev-manage-project-list td").hover(function() {
			$(this).parent().css({"background":"rgba(29, 22, 22, 0.106)", "cursor":"pointer"});
		}).mouseleave(function() {
			$(this).parent().css({"background":"white", "color":"#black"});
			
		}).click(function() {
			$(".show-manage-project-detail-info").fadeOut();									
			
			$projectNo = this.parentNode.children[0].children[0].value;
			console.log($projectNo);
			
			$.ajax({
				type: "get",
				url: "${ pageContext.servletContext.contextPath }/menu/main/admin/project/" + $projectNo,
				success: function(data) {
					
					$project = JSON.parse(data.project);
					$projectInfo = $project.projectInfo;
					console.log($projectInfo);
					$("#dev-manage-project-name").val($project.name);
					$("#dev-manage-project-startdate").val($project.startDate);
					$("#dev-manage-project-deadline").val($project.deadLine);
					$("#dev-manage-project-outputcount").val($projectInfo.outputAmount + "개");

					$totalIssu = $projectInfo.watingIssueAmount + $projectInfo.progressingIssueAmount + $projectInfo.solvedIssueAmount;
					$("#dev-manage-project-issuecount").val($totalIssu + "개");

					$totalTaskAmount = $projectInfo.beforeProceedingTaskAmount + $projectInfo.progressingTaskAmount
								+ $projectInfo.testingTaskAmount + $projectInfo.finishedTaskAmount + $projectInfo.pendingTaskAmount;
					$("#dev-manage-project-taskcount").val($totalTaskAmount + "개");
					
					$(".show-manage-project-info").fadeIn();
				},
				error: function(xhr, status, error) {
					alert('admin mainpage/ find project list /network error');
				}
					
			});
		});


		$("#dev-manage-project-taskcount").hover(function() {
			$(this).css({"cursor":"pointer"});
		}).click(function() {
			
			
			$("#manage-info-chart").empty();
			$("#manage-project-detail-body").empty();
			$("#manage-project-info-category").text("업무");
			$(".show-manage-project-detail-info").fadeIn();
			
			$before = "<label>진행전 : <input readonly type='text' value=" + $projectInfo.beforeProceedingTaskAmount + "개" +"></label><br>";
			$progress = "<label>진행중 : <input readonly type='text' value=" + $projectInfo.progressingTaskAmount + "개" +"></label><br>";
			$test = "<label>테스트중 : <input readonly type='text' value=" + $projectInfo.testingTaskAmount + "개" +"></label><br>";
			$done = "<label>완료 : <input readonly type='text' value=" + $projectInfo.finishedTaskAmount + "개" +"></label><br>";
			$pend = "<label>보류 : <input readonly type='text' value=" + $projectInfo.pendingTaskAmount + "개" +"></label><br>";
			
			$("#manage-project-detail-body").append($before);
			$("#manage-project-detail-body").append($progress);
			$("#manage-project-detail-body").append($test);
			$("#manage-project-detail-body").append($done);
			$("#manage-project-detail-body").append($pend);



			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(drawtaskChart);
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

				var chart1 = new google.visualization.PieChart(document.getElementById('manage-info-chart'));

				chart1.draw(data, options);
			}
		});

		
		$("#dev-manage-project-issuecount").hover(function() {
			$(this).css({"cursor":"pointer"});
		}).click(function() {
			
				
			$("#manage-info-chart").empty();
			$("#manage-project-detail-body").empty();
			$("#manage-project-info-category").text("이슈");
			$(".show-manage-project-detail-info").fadeIn();

			
			$watiIssue = "<label>대기중 : <input readonly type='text' value=" + $projectInfo.watingIssueAmount + "개" +"></label><br>";
			$progressIssue = "<label>처리중 : <input readonly type='text' value=" + $projectInfo.progressingIssueAmount + "개" +"></label><br>";
			$solvedIssue = "<label>해결완료 : <input readonly type='text' value=" + $projectInfo.solvedIssueAmount + "개" +"></label><br>";


			$("#manage-project-detail-body").append($watiIssue);
			$("#manage-project-detail-body").append($progressIssue);
			$("#manage-project-detail-body").append($solvedIssue);


			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(drawissueChart);
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

				var chart = new google.visualization.PieChart(document.getElementById('manage-info-chart'));

				chart.draw(data, options);
			}
		});

		
		$("#dev-manage-project-outputcount").hover(function() {
			$(this).css({"cursor":"pointer"});
		}).click(function() {
			google.charts.load('current', {'packages':['corechart']});

		});

	});
		
		// google.charts.load('current', {'packages':['corechart']});
		// 			google.charts.setOnLoadCallback(drawissueChart);
		// 			google.charts.setOnLoadCallback(drawtaskChart);

		// 			function drawissueChart() {

		// 				var data = google.visualization.arrayToDataTable([
		// 					['Status', 'Amount'],
		// 					  ['대기중', $projectInfo.watingIssueAmount],
		// 					  ['처리중', $projectInfo.progressingIssueAmount],
		// 					  ['완료', $projectInfo.solvedIssueAmount]
		// 				]);
						
		// 				var options = {
		// 					  title: '이슈',
		// 					  slices: {
		// 					0: { color: 'yellowgreen' },
		// 					1: { color: 'orangered' },
		// 					2: { color: 'cyan' }
		// 					  },
		// 					  sliceVisibilityThreshold:0
					
						
		// 				};
						
		// 				var chart = new google.visualization.PieChart(document.getElementById('issuechart'));
						
		// 				chart.draw(data, options);
		// 			  }

		$(function() {
		$("#dev-join-project-list td").hover(function() {
			$(this).parent().css({"background":"rgba(29, 22, 22, 0.106)", "cursor":"pointer"});
		}).mouseleave(function() {
			$(this).parent().css({"background":"white", "color":"#black"});
			
		}).click(function() {

			$projectNo = this.parentNode.children[0].children[0].value;
			$pmName = this.parentNode.children[2].innerText;
			
			$.ajax({
				type: "get",
				url: "${ pageContext.servletContext.contextPath }/menu/main/join/project/" + $projectNo,
				success: function(data) {
					$project = JSON.parse(data.joinProjectInfo);
					$projectInfo = $project.projectInfo;
					$projectRole = $projectInfo.role;
					$role = "";
					if($projectRole != null) {
						for(let i = 0; i < $projectRole.length; i++) {
							$role += $projectRole[i].roleName + " ";
						}
					}
					
					
					$("#dev-join-project-name").text($project.memberName);
					$("#dev-join-project-pm-name").val($pmName);
					$("#dev-join-project-startdate").val($project.startDate);
					$("#dev-join-project-deadline").val($project.deadLine);
					$("#dev-join-project-role").val($role);
					
					$totalTaskAmount = $projectInfo.beforeProceedingTaskAmount + $projectInfo.progressingTaskAmount
								+ $projectInfo.testingTaskAmount + $projectInfo.finishedTaskAmount + $projectInfo.pendingTaskAmount;
					
					google.charts.load('current', {'packages':['bar']});
					google.charts.setOnLoadCallback(drawTaskChart);

					function drawTaskChart() {
						var data = new google.visualization.arrayToDataTable([
						['Move', '갯수', { role: 'style' }],
						["전체 업무", $totalTaskAmount,'black'],
						["진행전 업무", $projectInfo.beforeProceedingTaskAmount,'#343A40'],
						["진행중 업무", $projectInfo.progressingTaskAmount, '#343A40'],
						['테스트중 업무', $projectInfo.testingTaskAmount, '#343A40'],
						["완료 업무", $projectInfo.finishedTaskAmount, '#343A40'],
						["보류 업무", $projectInfo.pendingTaskAmount, '#343A40']
						]);

						var options = {
							width: 600,
							legend: { position: 'top' },
							chart: {
								title: '프로젝트 업무 현황',
								subtitle: '배정 업무 진행 현황' },
							axes: {
								x: {
								0: { side: 'top'} // Top x-axis.
								}
							},
							bar: { groupWidth: "40%" },
							sliceVisibilityThreshold: 0
						};

						var chart = new google.charts.Bar(document.getElementById('top_x_div'));
						// Convert the Classic options to Material options.
						chart.draw(data, google.charts.Bar.convertOptions(options));
					};

					$(".show-join-project-info").fadeIn();
					$(".show-join-project-info-cg").fadeIn();
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