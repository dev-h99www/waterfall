<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
        integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">      
<title>직급 관리</title>
<script>

	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
<style>

.main-content {
	position: absolute;
	left: 230px;
	top: 10px;
	width: 1700px;
	height: 900px;
}

.jobadminheader {
	position: absolute;
	left: 100px;
	top: 100px;
	width: 1500px;
	height: 700px;
}

.table-area {
	border: solid;
	border-style: 1px;
	width: 90%;
	height: 60%;
	border-radius: 1%;
	
}

#table-header {
	font-size: 1.6rem;
}

#table-content {
	font-size: 1.4rem;
}

.button {@import
	url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");-webkit-appearance: none
	;
            -moz-appearance: none ;
            appearance: none ;

            margin: 0; padding: 0.5rem 1rem ;

            font-family: "Noto Sans KR ", sans-serif ;
            font-size: 1rem ;
            font-weight: 400; text-align: center ;
            text-decoration: none ;

            display: inline-block ;
            width: auto ;

            border: none ;
            border-radius: 4px ;

            cursor: pointer ;
            transition: 0.5s ;

            box-shadow: 3px 3px 3px gray ;

            margin: 3px ;
        
	
}

#modifyButton {
	background-color: rgb(197, 197, 197);
}

#deleteButton {
	background-color: rgb(177, 209, 221);
}
#addButton{
	background-color: rgb(173, 206, 173);
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
	transform: translateY(-5px);
}
td{
	vertical-align: middle !important;
}

/* 모달 */
.modal-content {
  width: 635px;
  height: 272px;
  padding: 30px;
}
.my-modal-input label {
  width: 60px;
}
.my-modal-input input {
  width: 365px;
}
.my-modal-body {
  margin-left: 0px;
}
.my-modal-footer {
  text-align: right;
}
.my-modal-footer button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
.my-modal-footer button:first-child {
  margin-right: 5px;
}
/* 수정 모달 */
.my-modal-footer-read {
  text-align: right;
}
.my-modal-footer-read button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
.my-modal-footer-read button:first-child {
  margin-right: 5px;
}

input[type="text"]:focus {
	outline-color: lightseagreen;
	background: paleturquoise;
}

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<!-- 직급 추가 모달 -->
		<div class="modal fade" id="writeModal" data-bs-backdrop="static"
			tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
				<div class="modal-content" style="top: 172px">
					<form action="${ pageContext.servletContext.contextPath }/company/job/regist" method="POST">
						<div class="my-modal-header mb-3">
							<h3>직급 생성</h3>
						</div>
						<div class="my-modal-body">
							<div class="my-modal-input mb-3">
								<label class="me-2" for="rank-write">RANK</label>
								<input type="text" id="rank-write" name="rank">
							</div>
							<div class="my-modal-input mb-3">
								<label class="me-2" for="name-write">직급명</label>
								<input type="text" id="name-write" name="name">
							</div>
							<div class="my-modal-input mb-4">
								<label class="me-2" for="code-write">직급코드</label>
								<input type="text" id="code-write" name="code">
							</div>
						</div>
						<div class="my-modal-footer">
							<button type="submit" class="btn btn-secondary"
								data-bs-toggle="modal">등록</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<!-- 직급 수정 모달 -->
		<div class="modal fade" id="readModal" data-bs-backdrop="static"
			tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
				<div class="modal-content" style="top: 172px">
					<form action="${ pageContext.servletContext.contextPath }/company/job/update" method="POST">
						<div class="my-modal-header mb-3">
							<h3>직급 수정</h3>
						</div>
						<div class="my-modal-body">
							<div class="my-modal-input mb-3">
								<label class="me-2" for="rank-read">RANK</label>
								<input type="text" id="rank-read" name="rank">
							</div>
							<div class="my-modal-input mb-3">
								<label class="me-2" for="name-read">직급명</label>
								<input type="text" id="name-read" name="name">
							</div>
							<!-- 직급코드 읽기 전용 -->
							<div class="my-modal-input mb-4">
								<label class="me-2" for="code-read">직급코드</label>
								<input id="code-read" name="code" style="border: none; outline: none" readonly>
							</div>
						</div>
						<div class="my-modal-footer-read">
							<button type="submit" class="btn btn-secondary">수정</button>
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<main>
			<!-- 메뉴바 내 콘텐트 영역 -->
			<div class="main-content">
				<!-- 직급관리 헤더 영역 -->
				<div class="jobadminheader">
					<label style="font-size: 1.4rem; margin-right: 1200px;">
						<i style='font-size: 24px' class='far'>&#xf2bb;</i>직급관리
					</label>
					<button class="float button" id="addButton" data-bs-toggle="modal" data-bs-target="#writeModal">추가</button>
					<hr>
					<!-- 직급검색창-->
					<br> 
					<!-- 직급명 목록 조회 -->
					<table  class="table table-bordered">
						<thead>
							<tr>
								<th>RANK</th>
								<th>직급명</th>
								<th>직급코드</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="job" items="${ requestScope.jobList }">
								<tr id="listArea">
									<td> <c:out value="${ job.rank }" /></td>
									<td> <c:out value="${ job.name }" /></td>
									<td> <c:out value="${ job.code }" /></td>
									<td>
										<button class="float button" id="modifyButton" data-bs-toggle="modal" data-bs-target="#readModal">수정</button>
										<!-- <button class="float button" id="deleteButton">삭제</button> -->
										<form action="${ pageContext.servletContext.contextPath }/company/job/update" method="POST" style="display: inline-block">
											<input type="button" class="float button delBtn" id="deleteButton" value="삭제">
										</form>
									</td>
								</tr>
							</c:forEach>
							<%-- <tr>
								<td>2 <c:out value="${ job.rank }" />
								</td>
								<td>이사 <c:out value="${ job.name }" />
								</td>
								<td><button class="float button" id="modifyButton">수정</button>
									<button class="float button" id="deleteButton">삭제</button></td>
							</tr> --%>
						</tbody>

					</table>


				</div>
				
			</div>
		</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
	/* 직급 수정 */
	if(document.querySelectorAll("#listArea td button")) {
		const $btns = document.querySelectorAll("#listArea td button");
		console.log($btns);
		for(let i = 0; i < $btns.length; i++) {
			$btns[i].onclick = function() {
				const code = this.parentNode.parentNode.children[2].innerText;
				console.log(code);
				
				$.ajax({
					url: "${ pageContext.servletContext.contextPath }/company/jobDetail?code=" + code,
//					url: "jobDetail",
					type: "get",
					data: { code : code },
					success: function(data, status, xhr) {
						console.log(data);
						jobDetail = JSON.parse(data.jobDetail);
						
						$("#rank-read").val(jobDetail.rank);
						$("#name-read").val(jobDetail.name);
						$("#code-read").val(jobDetail.code);
						$("#readModal").modal("show");
					}, error: function(xhr, status, error) {
						console.log(xhr);
					}
				});
				
			}
		}
	}
	
	/* 직급 삭제 */
	$(".delBtn").click(function() {
		const code = this.parentNode.parentNode.parentNode.children[2].innerText;
		console.log(code);
		location.href="${ pageContext.servletContext.contextPath }/company/job/delete?code=" + code;
	});
	</script>
</body>
</html>