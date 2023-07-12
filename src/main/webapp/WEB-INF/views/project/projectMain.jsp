<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- google chart cdn-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- Latest compiled JavaScript -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<!-- Google Material Icons-->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
   href="https://fonts.googleapis.com/icon?family=Material+Icons">
   <!-- 게시판 모달 다운로드 토글 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- chart -->
<script src="https://cdn.anychart.com/releases/8.10.0/js/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/releases/8.10.0/js/anychart-pie.min.js"></script>
<style>
#output_header {
   font-size: 1.6rem;
   width: 100%;
   height: 100%;
   margin-left: 5%;
   margin-top: 5%;
}

.main-box {
   border: solid;
   width: 27%;
   height: 100%;
   border-radius: 20px;
   border-color: #C4C4C4;
   float: left;
   margin-right: 30px;
}

.main-box2 {
   border: solid;
   width: 17%;
   height: 100%;
   border-radius: 20px;
   border-color: #C4C4C4;
   float: left;
   margin-right: 100px;
   overflow: auto;
}
.main-box3 {
   border: solid;
   width: 17%;
   height: 100%;
   border-radius: 20px;
   border-color: #C4C4C4;
   float: left;
   margin-left: 8%;
   overflow: auto;
}

.donut-chart {
   width: 100%;
   height: 100%;
   margin: 0;
   padding: 0;
}

#chartContent {
   width: 1700px;
   height: 250px;
}

#chartContent2 {
   width: 1700px;
   height: 250px;
}

.project-name {
   margin-right: 370px;
   display: inline-block;
}
.project-issue{
   margin-right: 370px;
   display: inline-block;
}

.project-name2 {
   display: inline-block;
   margin-top: 10px;
}

button {@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");-webkit-appearance: none;
            -moz-appearance: none ;
            appearance: none ;

            margin: 0; padding: 0.7rem 0.2rem ;

            font-family: "Noto Sans KR ", sans-serif ;
            font-size: 1.3rem ;
            font-weight: 400; text-align: center ;
            text-decoration: none ;

            display: inline-block ;
            width: auto ;

            border: none ;
            border-radius: 4px ;

            cursor: pointer ;
            transition: 0.5s ;

            box-shadow: 3px 3px 3px gray ;
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

#addButton1 {
   background-color: transparent;
   color: black;
   margin:0px;
   
}

.button2 {
   background-color: transparent;
   color: black;
   border: none;
   margin:0px;
   
}

#noticebutton{
   border: none;
   cursor: pointer ;
    transition: 0.5s ;
}

textarea {
   width: 100%;
   height: 500px;
   padding: 10px;
   box-sizing: border-box;
   border: solid 2px #B4ADAD;
   border-radius: 5px;
   font-size: 14px;
   resize: both;
}

#title {
   width: 80%;
   height: 30px;
   border-radius: 5px;
   border: solid 2px #B4ADAD;
}
td {
   font-size: 0.8em;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
	<jsp:include page="/WEB-INF/views/project/projectMainBoardModal.jsp"/>

	<div >
      <main>
         <div id="output_header">
             <%-- 메인화면 헤더 --%>
            <label><i style='font-size: 24px' class='fas'>&#xf4fe;</i>${ sessionScope.projectAutority.projectName }</label>
            <hr style="width:1470px;">
            
            <%-- 프로젝트이름  --%>
            <label class="project-name">프로젝트 진행 현황</label>
            
            <label class="project-issue">이슈 현황</label>
            
            <%-- 프로젝트 가이드 시작 --%>
            <label style="margin-left: 2%">&emsp;&emsp;&emsp;프로젝트 가이드</label>
            <%-- + 버튼 --%>
            <button class="float" id="addButton1" onclick="location.href='${ pageContext.servletContext.contextPath }/guide/list'">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- 프로젝트 가이드 끝 --%>
            
            <div id="chartContent">
            
               <%-- 프로젝트 1 해당 박스 --%>
               <div id="projectbox" class="main-box">
                  
               </div>
               
               <%-- 이슈 해당 박스 --%>
               <div id="issuebox" class="main-box" style="margin-left:3%;">
                  
               </div>
            	   
               <div class="main-box3">
	               <table style="margin-left:3%;margin-top:8%" id="main-board-modal">
		               <c:forEach var="guide" items="${ projectBoard.guideBoard }">
							<tr data-bs-toggle="modal" data-bs-target="#project-main-board-modal">
								<td>${ guide.title }</td>
								<td><input type="hidden" value="${ guide.no }"></td>
							</tr>
		               </c:forEach>
	               </table>
				</div>
               
            </div>
            
            <%-- To Do 시작 --%>
            <label class="project-name2">To Do</label>
            <button class="float button2" style="margin-right:300px;" onclick="location.href='${ pageContext.servletContext.contextPath }/todo/list'">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- To Do 끝 --%>
            
            <%-- 공지사항 시작 --%>
            <label class="project-name2 ">공지사항</label>
            <button class="btn  float " style="margin-right:235px;" id="noticebutton" onclick="location.href='${ pageContext.servletContext.contextPath }/notice/list'">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- 공지사항 끝 --%>
            
            <%-- 회의록 시작 --%>
            <label class="project-name2">회의록</label>
            <button class="float button2" style="margin-right:280px;" onclick="location.href='${ pageContext.servletContext.contextPath }/meeting/list'">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- 회의록 끝 --%>
            
            <%-- 교육 시작 --%>
            <label class="project-name2">교육</label>
            <button class="float button2" onclick="location.href='${ pageContext.servletContext.contextPath }/edu/list'">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- 교육 끝 --%>
            
            <div id="chartContent2">
               <%--To Do 박스--%>
               <div class="main-box2">
	               <table style="margin-left:8%;margin-top:8%" id="main-board-modal">
		               <c:forEach var="todo" items="${ projectBoard.todoBoard }">
							<tr data-bs-toggle="modal" data-bs-target="#project-main-board-modal">
								<td>${ todo.title }</td>
								<td><input type="hidden" value="${ todo.no }"></td>
							</tr>
		               </c:forEach>
	               </table>
               </div>
               <%--공지사항 박스 --%>
               <div class="main-box2">
	               <table style="margin-left:8%;margin-top:8%" id="main-board-modal">
		               <c:forEach var="notice" items="${ projectBoard.noticeBoard }">
							<tr data-bs-toggle="modal" data-bs-target="#project-main-board-modal">
								<td>${ notice.title }</td>
								<td><input type="hidden" value="${ notice.no }"></td>
							</tr>
		               </c:forEach>
	               </table>
               </div>
               <%--회의록 박스 --%>
               <div class="main-box2">
	               <table style="margin-left:8%;margin-top:8%" id="main-board-modal">
		               <c:forEach var="meeting" items="${ projectBoard.meetingBoard }">
							<tr data-bs-toggle="modal" data-bs-target="#project-main-board-modal">
								<td>${ meeting.title }</td>
								<td><input type="hidden" value="${ meeting.no }"></td>
							</tr>
		               </c:forEach>
	               </table>
               </div>
               <%-- 교육 박스 --%>
               <div class="main-box2">
	               <table style="margin-left:8%;margin-top:8%" id="main-board-modal">
		               <c:forEach var="edu" items="${ projectBoard.eduBoard }">
							<tr data-bs-toggle="modal" data-bs-target="#project-main-board-modal">
								<td>${ edu.title }</td>
								<td><input type="hidden" value="${ edu.no }"></td>
							</tr>
		               </c:forEach>
	               </table>
               </div>
            </div>
         </div>
      </main>
      <br clear="all">
   </div>
	
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	
<script>
	
	$("td").click(function() {
		boardNo = this.parentNode.children[1].children[0].value;
		console.log(boardNo);
			
		$url = "${ pageContext.servletContext.contextPath }/project/board/" + boardNo;
		console.log($url);
			
		$.ajax({
			type: "get",
			url: $url,
			success: function(data) {
				const $boardInfo = JSON.parse(data.board);
				$("#upload-file-area").empty();
				$("#board-category-name").empty();

				$("#read-title").val($boardInfo.title);
				$("#read-membername").val($boardInfo.member.name);
				$("#read-boardcount").val($boardInfo.boardCount);
				$("#read-content").val($boardInfo.content);
				console.log($boardInfo.member.name);
				if($boardInfo.file != null) {
					for(let i = 0;i < $boardInfo.file.length; i++) {
						const $fileName = $boardInfo.file[i].fileOriginName;
						const $fileNo = $boardInfo.file[i].fileNo;
						$buttonsTag = "<div class='mt-4 row'><div class='col-2 center' style='vertical-align: top;''><label>첨부파일</label></div><div class='col-3'><div class='btn-group' id='attaachmentNameArea'><input type='button' class='btn btn-outline-dark' id='read-originalName' name='originalName' value='" + $fileName + "'><button type='button' class='btn btn-outline-dark dropdown-toggle dropdown-toggle-split' data-toggle='dropdown'><span class='caret'></span></button><div class='dropdown-menu' id='downloadArea'><a class='dropdown-item' href='${pageContext.servletContext.contextPath}/meeting/download/" + $fileNo + "'>다운로드</a></div></div></div></div>";
						$("#downloadZone").append($buttonsTag);

					}
				}
				$("#board-category-name").append($boardInfo.boardCategoryName);
			},
			error: function() {
				alert('project main board error');
			}
		});
	});

	
	$(function() {
		
	
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawprojectChart);
		google.charts.setOnLoadCallback(drawissueChart);
		const $projectInfo = JSON.parse('${ requestScope.projectInfo }');
      	console.log($projectInfo);
      	console.log($projectInfo.beforeProceedingTaskAmount);
      	console.log($projectInfo.progressingTaskAmount);
      	console.log($projectInfo.watingIssueAmount);

		
		function drawprojectChart() {
	
			var data = google.visualization.arrayToDataTable([
				['Status', 'Amount'],
				['진행전', $projectInfo.beforeProceedingTaskAmount],
				['진행중', $projectInfo.progressingTaskAmount],
				['테스트중', $projectInfo.testingTaskAmount],
				['진행완료', $projectInfo.finishedTaskAmount],
				['보류', $projectInfo.pendingTaskAmount]
			]);
	
			var options = {
				title: '업무상태',
	        	 	slices: {
	            0: { color: 'yellowgreen' },
	            1: { color: 'orangered' },
	            2: { color: 'gold' },
	            3: { color: 'black' },
	            4: { color: 'hotpink' }
	         		},
			  	sliceVisibilityThreshold:0
			};
								
			var chart = new google.visualization.PieChart(document.getElementById('projectbox'));
							
			chart.draw(data, options);
	   	}
	
		function drawissueChart() {
			var data = google.visualization.arrayToDataTable([
				['Status', 'Amount'],
			  	['대기중', $projectInfo.watingIssueAmount],
			  	['처리중', $projectInfo.progressingIssueAmount],
			  	['완료', $projectInfo.solvedIssueAmount],
			]);
								
			var options = {
			  	title: '이슈상태',
			  	slices: {
		    	0: { color: 'yellowgreen' },
			    1: { color: 'orangered' },
			    2: { color: 'cyan' }
			  	},
			  	sliceVisibilityThreshold:0
			};
	
			var chart1 = new google.visualization.PieChart(document.getElementById('issuebox'));
			chart1.draw(data, options);
		}
      
	});
	//Modal
      var myModal = document.getElementById('myModal')
      var myInput = document.getElementById('myInput')

      myModal.addEventListener('shown.bs.modal', function() {
         myInput.focus()
      })
   </script>
</body>
</html>