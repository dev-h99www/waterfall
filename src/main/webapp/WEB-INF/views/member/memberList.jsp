<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">  -->
<link href="${ pageContext.servletContext.contextPath }/resources/css/buttons.css" rel="stylesheet" type="text/css"> <!-- 버튼-->
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />  -->
<link href="${ pageContext.servletContext.contextPath }/resources/css/login.css" rel="stylesheet" />
<link href="${ pageContext.servletContext.contextPath }/resources/css/buttons.css" rel="stylesheet" />

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script> -->
<style>
.box{
    margin-top: 2%;
    width: 80%;
    height: 100%;
    border-style: solid;
    border-color: white;                 
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set {
  text-align: center;
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set #search-input {
  width: 300px;
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set button {
  background: none;
  border: none;
}
 
#layoutSidenav_content .todo .tbl-wrapper .paging {
  text-align: center;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button {
  background: none;
  border: none;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button:hover {
  text-decoration: underline;
}
 
       
</style>
<script>

	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	
	<!-- Modal HTML  "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
    <div class="modal fade modal-dialog-scrollable" id="exampleModal" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
            <div class="modal-content" style="top: 100px">
                <div style="background-color: #212529;">
                    <br>
                </div>
                <div class="modal-header">
                    <span class="modal-title" id="exampleModalLabel"><strong>계정수정</strong></span>
                </div> 
            <!-- 모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남  -->
                <form action="${ pageContext.servletContext.contextPath }/member/memberModify" method="post">
                    <div class="modal-body" style="margin-top: 5%;">
                        <div>
                            <input type="text" class="form-control" placeholder="아이디" id="id" name="id" style="width: 70%; margin-left: 15%;" readonly="readonly">
                        </div>
                        <div class="mb-3" style="margin-top: 3%;">        
                             <input type="text" class="form-control" placeholder="이름" id="name" name="name" style="width: 70%; margin-left: 15%;">
                        </div>
                        <div class="mb-3">                                   
                            <select id="dept" name="dept" class="form-select" style="width: 70%; margin-left: 15%;" required="required">
                                <option value="" selected disabled></option>
                            </select> 
                        </div>
                        <div class="mb-3">                                  
                            <select id="team" name="team" class="form-select" style="width: 70%; margin-left: 15%;" required="required">
                                <option value="" selected disabled></option>
                            </select> 
                        </div>
                        <div class="mb-3">                              
                            <select id="job" name="job" class="form-select" style="width: 70%; margin-left: 15%;" required="required">
                                <option value="" selected disabled></option>
                            </select> 
                        </div>
                    </div>
                    <!-- 모달의 바디 끝  -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-secondary" data-bs-target="#exampleModalToggle2" data-bs-toggle="modal">등록</button>
                        <button style="margin-right: 5px;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        <input type="button" class="btn btn-secondary" id="delete" value="삭제하기" style="margin-right: 25%;">
                    </div>
                </form>
            </div>
        </div>
    </div>
        <!-- Modal HTML 끝  -->

     <!-- Modal HTML 두번째 모달 -->
    <div class="modal fade" id="exampleModalToggle2" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="top: 200px; height: 252px; width: 402px; margin-left: 0px; left: 100px;">
                <div style="background-color: #212529;">
                    <br>
                </div>
                <div class="modal-header">
                    <span class="modal-title" id="exampleModalLabel"><strong></strong></span>
                </div>         
                    <div class="modal-body">  
                        <h5 align="center" style="margin-top: 30px;"><strong>등록되었습니다.</strong></h5>                          
                    </div>
                    <!-- 모달의 바디 끝  -->
                    <div class="modal-footer">                           
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="margin-right: 44%;">확인</button>
                    </div>                    
            </div>
        </div>
    </div>
            <!-- Modal HTML 두번째 모달 -->   
	
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
				 <div>
                    <main>
                        <div class="box container-fluid todo" style="padding-left: 10%;padding-right: 10%;">
                            <div style="height: 0px;">
                                <svg xmlns="http://www.w3.org/2000/svg" top="1000" width="60" height="31" fill="currentColor" class="bi bi-person-plus-fill" viewBox="1 -2 16 16">
                                <path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                                <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                                </svg>
                                <strong>계정관리</strong>
                            </div>
                                              
                                <div style="float: right">
                                    <div style="float: left;">
                                        <button class="ui secondary button" style="padding-left: 20px; margin-bottom: 10px;" onclick="location.href='regist'">
                                        		   계정생성 
                                        </button>  
                                    </div>
                                    <!-- <div style="float: left;">
                                        <button class="ui secondary button" type="button" style="padding-left: 20px;" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        		    계정수정
                                        </button>  
                                    </div> -->
                                </div>   
                                <hr align="left" style="border: solid 2px rgb(0, 0, 0); width: 100%;">
                                
                                <div>
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th scope="col">아이디</th>
                                            <th scope="col">이름</th>
                                            <th scope="col">부서</th>
                                            <th scope="col">팀</th>
                                            <th scope="col">직급</th>
                                            <th scope="col">전화번호</th>
                                            <th scope="col">이메일</th>
                                            <th scope="col">등록일</th>
                                        </tr>
                                        </thead>
                                        <tbody>             
                                        <c:forEach var="adminMember" items="${ requestScope.adminMemberList }">
                                    		<tr id="listArea" data-bs-toggle="modal" data-bs-target="#exampleModal" style="cursor:pointer">
                                        		<th scope="row">"${ adminMember.id }"</th>
                                        		<td>${ adminMember.name }</td>
                                        		<td>${ adminMember.dept.deptName }</td>
                                        		<td>${ adminMember.team.teamName }</td>
                                        		<td>${ adminMember.job.jobName }</td>
                                        		<td>${ adminMember.phone }</td>
                                        		<td>${ adminMember.email }</td>
                                        		<td>${ adminMember.createDate }</td>
                                    		</tr>
                                    	</c:forEach>
                                        </tbody>
                                    </table>
                                   
                                   <div class="paging mt-3">         
					                        <!-- 페이지 처리  -->
					                     <jsp:include page="../common/paging.jsp" />          
					                        <!-- 검색폼  -->   
					                </div>
					                <div class="search-area" align="center">
					                  <form id="loginForm" action="${ pageContext.servletContext.contextPath }/member/list" method="get">
					                     <div class="search-set mt-2">
					                      <input type="hidden" name="currentPage" value="1">
					                        <select name="searchCondition" id="searchCondition">
					                            <option value="name" ${ requestScope.selectCriteria.searchCondition eq "name"? "selected": "" }>이름</option>
					                            <option value="deptName" ${ requestScope.selectCriteria.searchCondition eq "daptName"? "selected": "" }>부서</option>
					                        </select>
					                        <input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
					                        <button type="submit"><i class="fas fa-search"></i></button>
					                     </div>
					                   </form> 
					                </div>              
                                </div>          
                        	</div>
                    	</main>
               	 </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
		<script>
			
		if(document.querySelectorAll("#listArea td")) {
				const $tds = document.querySelectorAll("#listArea td");
				for(let i = 0; i < $tds.length; i++) {
					$tds[i].onclick = function() {
						const id = this.parentNode.children[0].innerText;
						$("#dept option").remove();
						$("#job option").remove();
						$("#team option").remove();
						$.ajax({
							url: "modify",
							type: "get",
							data : { id : id },
							success : function(data, textStatus, xhr) {
								const modify = JSON.parse(data.modify);
								const memberArray = Object.entries(modify);
					
								const deptList = memberArray[9][1];
								const teamList = memberArray[10][1];
							    const jobList = memberArray[11][1];		
								console.log(memberArray);
							    
								$("#dept").append("<option value='" + memberArray[6][0] + "'>" + memberArray[6][1].deptName + "</option>");
								$("#team").append("<option value='" + memberArray[7][0] + "'>" + memberArray[7][1].teamName + "</option>");
								$("#job").append("<option value='" + memberArray[8][0] + "'>" + memberArray[8][1].jobName + "</option>");
								
								for(var i = 0; i < deptList.length; i++){
									$("#dept").append("<option value='" + deptList[i].deptCode + "'>" + 
											deptList[i].deptName + "</option>");
									console.log(deptList);
								}
									
								for(var i = 0; i < teamList.length; i++){
									$("#team").append("<option value='" + teamList[i].teamCode + "'>" + 
											teamList[i].teamName + "</option>");
									console.log(teamList);
								}
									
								for(var i = 0; i < jobList.length; i++){
									$("#job").append("<option value='" + jobList[i].jobCode + "'>" + 
											jobList[i].jobName + "</option>");
									console.log(jobList);
								}
																
								$("#id").val(memberArray[1][1]);
								$("#name").val(memberArray[2][1]);
								$("#exampleModal").modal("show");
																							
							}, error : function(data){
								console.log("문제있네");
							}
						});
					}
				}
			}
		
		$(function() {
		 	$("#delete").click(function() {
		 		const id = $("#id").val();
		 		
		 		location.href="${ pageContext.servletContext.contextPath }/member/delete?id=" + id; 
		 	});
		});
		</script>           
                     
</body>
</html>