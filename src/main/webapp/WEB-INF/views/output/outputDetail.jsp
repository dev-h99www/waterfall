<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
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
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />	
<style>
/* #output_header {
	position: absolute;
	left: 300px;
	top: 50px;
	font-size: 1.4rem;
	width: 90%;
	height: 800px;
} */
.test{
	display: none;
}
.box {
	width: 600px;
	height: 580px;
	border-style: solid;
	border-color: #888686;
	
}
#header{
	margin-left:150px;
	margin-top: 50px;

}

#outputbox1 {
	top: 150px;
	border-width: 1px;
	float : left;
	overflow : auto;
	/* margin-left:200px; */
}
#outputbox2 {
	top: 150px;
	border-width: 1px;
	margin-left:660px;

}

#box2_border_top {
	width: 600px;
	height: 50px;
	background-color: #343A40;
}

#box1header {
	padding: 40px;
	font-size: 1.4rem;
}

#headerunderline {
	position: absolute;
	left: 40px;
	top: 45px;
}

#box1_body {
	font-size: 1.2rem;
}

#box2_header {
	font-size: 1.4rem;
	color: white;
	padding: 5px;
}

.box2_sql_body {
	background-color: #F6F6F6;
	font-size: 1rem;
	padding: 3px;
	margin:10px;
	box-shadow: 2px 2px 2px 2px gray;
}

textarea {
	border-radius: 5px;
	font-size: 1.2rem;
}
label {
	padding:3px;
}
.content{
	margin-left: 200px;
	margin-top:130px;
}
#deleteBtn{
	margin-left:500px;
	margin-top : 15px;
	margin-bottom : 0px;
}
input{
	border : none;
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
#deleteBtn{
	background-color: #D16B6B;
	color: white;
}
.box2Title{
	background-color : transparent;
	color : white;
	width : 30px;
}
#box2Name{
	width : 150px;
}
#downloadArea{
	border : solid;
	background-color : orangered;
	display : block;
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
		<main>
		<div id="header">
			<i style='font-size: 24px' class='fas'>&#xf07c;</i>산출물
			<hr>
		
				<div class="box" id="outputbox1" >
					<div id="box1header"> 산출물 관리 <br>
						<hr>
						
						<div id="box1_body">
							<%-- 상위업무 forEach --%>
							
							<c:forEach var="parentTask" items="${ requestScope.parentTaskList }" varStatus="status" >
								<label id="parentTaskName" type="button" class="folder_toggle" data-toggle="collapse" data-target="#demo${ status.index }"><i style='font-size: 16px; ' class='fas'>&#xf07b;</i>&nbsp;<c:out value="${ parentTask.taskCategory.categoryName }"/></label>
								<br>
								<%--하위 업무 forEach --%>
								<c:forEach var="childList" items="${ requestScope.parentTaskList[ status.index ].childList }" varStatus="st">
								
								<div id="demo${ status.index }" class="collapse" >
										<div id="childTasks${ st.index }">
											<div id="childTask">
												<label class= childName type="button" ><i style='font-size:24px' class='fas'>&#xf0da;</i><c:out value="${ childList.taskCategory.categoryName }"/></label>
												<input id="childDTO${ st.index }" type="hidden" value="${ childList.taskNo }">
											</div>
										</div>
								</div>	
								</c:forEach>
								<%-- <c:out value="${ parentTask.taskCategory.categoryName }"/><br> --%>
							</c:forEach>
						</div>
					</div>
				</div>
			
				<div class="box" id="outputbox2">
					<div id="box2_border_top">
						<label id="box2_header">&nbsp;&nbsp;ver&nbsp;</label>
						<input class="box2Title" type="text" name="box2Ver">
						<input class="box2Title" type="text" id="box2Name" name="box2Name">
						</div>
						<div class="box2_body" id="box2_sq1">
							<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
							<button id="deleteBtn" rel="float" class="button float" data-bs-toggle="modal" data-bs-target="#deleteModal"><i style='font-size: 24px' class='far'>&#xf2ed;</i>삭제</button>
							</c:if>
							<div id="detail">
								<label class="box2_sql_body">프로젝트이름</label>
								<input class="test"  id="box1_body" type="text" name="projectName"  /><br>
								
								<label class="box2_sql_body" >상위업무</label>
								<input class="box1_body test" type="text" name="parentTaskName" /><br>
								
								<label class="box2_sql_body" >등록인</label>
								<input class="box1_body test" type="text" name="registedMember" /><br>
								
								<label class="box2_sql_body">등록일</label>
								<input class="box1_body test" type="text" name="registedDate" /><br>
								
								<label class="box2_sql_body" >첨부파일</label>
								<div class="btn-group" >
									<input type="button" class="btn btn-outline-dark" name="originalName" id="read-originalName">
									<button type="button" class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
	                                <span class="caret"></span>
	                                </button>
	                                <div class="dropdown-menu" id="downloadarea">
	                                </div>
								</div><br>
								
								<label class="box2_sql_body" id="content">내용</label><br>
								<textarea class="test" cols="75" rows="5"  style="font-size: 14px;margin-left:20px" name="content" ></textarea><br>
								<button class="box2_sql_body button float" id="modifyBtn" style="margin-left : 500px;" >수정하기</button>
								<input id="outputNo" type="hidden" name="outputNo"/>
								<input id="taskNo" type="hidden" name="taskNo">
								<!-- <label class="box2_sql_body" >취소</label> -->
							</div>
						</div>
				</div>
			</div>
		</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<jsp:include page="/WEB-INF/views/output/outputModal.jsp"/>
	                
	
	
	
	
	<script>
	/*id="childTask 하위 label 태그들을 전부 index에 담는다 "*/
	 if (document.querySelectorAll("#childTask label")) {
		const $parentLabels = document.querySelectorAll("#parentTaskName");
		console.log($parentLabels);
		const $labels = document.querySelectorAll("#childTask label");
		 console.log($labels);
		 const $no = document.querySelectorAll("#childTask input");
		 console.log($no);
		 /* for(let i = 0; i < $no.length; i++){
			 const taskNo = $("#childDTO" + i).val();
			 console.log(taskNo);
		 }  */
		/*  const $no = $("#childTask input");
		 console.log($no); */
		 
		 /* labels의 크기만큼 index 증가, 현재상황 : 하위업무 9개, length = 9 */
		<c:set var= "TaskName" value="${ childList.taskCategory.categoryName }"/>

		for(let i = 0; i < $labels.length; i++){
			 console.log($labels[i])
			 $($labels[i]).click (function() {
				 const parentTaskName = $($parentLabels[i]).text();
				 const no = $($no[i]).val();
				 console.log(no);
				 console.log(parentTaskName);
				 
				 $.ajax({
					 url : "/waterfall/output/detail",
					 type : "get",
					 data : {"taskNo" : no},
					 success : function(data, textStatus, xhr){
						 console.log(data);
						 
						const parentTask = JSON.parse(data.parentTask);
						const childTask = JSON.parse(data.childTask);
						const outputDetail = JSON.parse(data.outputDetail);
						const outputFile = JSON.parse(data.outputFile);
						const refOutputNo = outputFile.outputNo;
						const fileNo = outputFile.fileNo;
						console.log(parentTask);
						console.log(childTask);
						console.log(outputDetail);
						console.log(outputFile);
						console.log(fileNo);
						
						 
						
						 if(outputDetail == null){
							 
							 $("input[name=box2Ver]").val("");
							 
							 $("input[name=box2Name]").val("");
							 
							 $("input[name=projectName]").val(parentTask.projectName);
							 
							 $("input[name=registedMember]").val("");
							 
							 $("input[name=registedDate]").val("");
							 
							 $("#downloadarea").empty()
							 
							 $("input[name=parentTaskName]").val("");
						     
						     $("input[name=originalName]").val("");
							 
							 $("textarea[name=content]").val("산출물이 존재하지 않습니다.");
							 
							 $("input[name=taskNo]").val(no);
							 $(".test").fadeIn();
						 }
						     $("input[name=parentTaskName]").val(childTask.parentTaskName);
						 
						 if(outputDetail != null && outputFile != null){
							 
							 
							 $("input[name=box2Ver]").val(outputDetail.outputVer);
							 
							 $("input[name=box2Name]").val(childTask.taskCategory.categoryName);
							 
							 $("input[name=projectName]").val(outputDetail.project.name);
							 
							 $("input[name=registedMember]").val(outputDetail.memberName);	 
							 
							 $("input[name=registedDate]").val(outputDetail.registedDate);
							 
							 $("textarea[name=content]").val(outputDetail.content);
							 
							 $("input[name=outputNo]").val(outputDetail.outputNo);
							 
						     $("input[name=parentTaskName]").val(childTask.parentTask.taskCategory.categoryName);
						     
						     $("input[name=originalName]").val(outputFile.originName);
						     
						     $("input[name=taskNo]").val(no);
						     
						     $("input[name=projectNo]").val(parentTask.projectNo);
						     
						     $("input[name=childTaskName]").val(childTask.taskCategory.categoryName);
							 
			                 
						     var $downloadTag = "<a href='${pageContext.servletContext.contextPath}/output/download/" + refOutputNo 
		                     + "' class='dropdown-item' id='downloadOutput'>다운로드</a>";
		 				     /*var $deleteTag = "<a href='${pageContext.servletContext.contextPath}/output/deleteFile/" + fileNo
							 + "' class='dropdown-item' id='deleteOutputFile'>삭제</a>"; */
							 
		                     $("#downloadarea").empty();
		                     $("#downloadarea").append($downloadTag);
		                     /* $("#downloadarea").append($deleteTag); */
							 $(".test").fadeIn();
							 
						 }
						 $(".test").fadeIn();
						 
						 
						 
					 }, error:function(data){
						 
						 $('#exceptionModal').on('shown.bs.modal', function () {
							    $("#myModal").attr('aria-hidden', false);
							});
					 }
				 })
			 }); 
		 }
	 }
	
	<%-- hover event 추가 --%>
	$(".folder_toggle").hover(function(){
		$(this).css({"border" : "solid",
				    "border-color" : "gray",
				    "border-radius" : "5px",
				    "border-width" : "1px"});
		
	}, function(){
		$(this).css({"border-color" : "white"});
	});
	
	$(".childName").hover(function(){
		$(this).css({"border" : "solid",
		    "border-color" : "gray",
		    "border-radius" : "5px",
		    "border-width" : "1px"});

	}, function(){
		$(this).css({"border-color" : "white"});
	});
	
	//삭제 이벤트
	$(function(){
            $("#delete").click(function(){
            	
            	const taskNo = $("#taskNo").val();
    			console.log(taskNo);
    			const outputNo = $("#outputNo").val();
    			
    			$.ajax({
    				url : "/waterfall/output/count",
    				type : "get",
    				data : {"taskNo" : taskNo},
    				success : function(data, textStatus, xhr){
    					const result = JSON.parse(data.result);
    					
    					if(result == 1){
    						$("#errorModal").modal("show");
    					} else{
    						location.href="${ pageContext.servletContext.contextPath }/output/delete?outputNo=" + outputNo;
    					}
    				}
    			});
            	
              
               
            });
         });
	
	//수정 이벤트
	$(function(){
		$("#modifyBtn").click(function(){
			const taskNo = $("#taskNo").val();
			console.log(taskNo);
			/* location.href="${ pageContext.servletContext.contextPath }/output/count?outputNo=" + outputNo; */
			$.ajax({
				url : "/waterfall/output/count",
				type : "get",
				data : {"taskNo" : taskNo},
				success : function(data, textStatus, xhr){
					console.log(data);
					
					const result = JSON.parse(data.result);
					
					if(result == 1){
						$("#errorModal").modal("show");
					} else{
						$("#modifyModal").modal("show");
					}
				}
				
			})
		})
	})
	</script>
	
</body>
</html>