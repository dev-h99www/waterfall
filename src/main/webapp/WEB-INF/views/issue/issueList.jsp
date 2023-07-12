<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업무 당 이슈목록</title>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-area-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-bar-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/js/datatables-simple-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-area-demo.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" data-auto-replace-svg="nest"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<style>
	td { vertical-align: middle !important;
	     text-align: center;
	   }
	th { text-align: !center; }
</style>
</head>
<body>
<script>
	/* 비즈니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
	   alert(message);
	}
</script>
<jsp:include page="../common/inprojectheader.jsp"/>
	
<jsp:include page="/WEB-INF/views/issue/modal.jsp"/>
<jsp:include page="/WEB-INF/views/issue/detailModal.jsp"/>
     <main>
     <c:if test="${ sessionScope.loginMember.role eq 1 or ( !empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo))}">
     	 <button type="button" class="btn btn-pink mb-2" id="backButon" onclick="backButton_click();"><i class="fas fa-backward"></i></button>
    </c:if>
         <div class="container-fluid px-4">
             <br>
             <br>
             <h1 class="mt-4"><i class="fas fa-exclamation-circle"></i>이슈관리</h1>
             <div class="card mb-4" style="vertical-align: middle;">
                 <div class="card-header">
                     <i class="fas fa-table me-1"></i>
                     	업무 당 이슈 목록
                 </div>
                 <div class="card-body" id="card-body">
                     <table id="datatablesSimple">
                        <colgroup>
                             <col style="width:5%"/>
                             <col style="width:15%"/>
                             <col style="width:15%"/>
                             <col style="width:12.5%"/>
                             <col style="width:12.5%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                         </colgroup>
                         <thead>
                             <tr style="text-align: center;">
                                 <th style="text-align: center;">No</th>
                                 <th style="text-align: center;">이슈 명</th>
                                 <th style="text-align: center;">업무 명</th>
                                 <th style="text-align: center;">발생일</th>
                                 <th style="text-align: center;">마감일</th>
                                 <th style="text-align: center;">상태</th>
                                 <th style="text-align: center;">중요도</th>
                                 <th style="text-align: center;">이슈제기자</th>
                                 <th style="text-align: center;">이슈담당자</th>
                             </tr>
                         </thead>
                         <tbody id="tbody">
                         	<c:forEach var="issue" items="${ requestScope.issueList }" varStatus="status">
                         	<c:if test="${ (sessionScope.loginMember.role eq 1) or ( !empty sessionScope.loginMember.no and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo)) or (sessionScope.loginMember.no eq issue.register.no) or (sessionScope.loginMember.no eq issue.manager.no)}">
                             <tr id="listArea" class="issueSelect">
                                 <td><c:out value="${ issue.no }"/></td>
                                 <td><c:out value="${ issue.name }"/></td>
                                 <td><c:out value="${ issue.taskCode.taskCategoryName }"/></td>
                                 <td><c:out value="${ issue.createdDate }"/></td>
                                 <td><c:out value="${ issue.deadline }"/></td>
                                 <td><c:out value="${ issue.progressStatus }"/></td>
                                 <td><c:out value="${ issue.importance }"/></td>
                                 <td><c:out value="${ issue.register.name }"/></td>
                                 <td><c:out value="${ issue.manager.name }"/></td>
                             </tr>
							</c:if>
							</c:forEach>
                         </tbody>
                         <tfoot></tfoot>
                     </table>
                 </div>
             </div>
         </div>
     </main>
<script>
	function backButton_click() {
		   console.log("이전 페이지 이동");
//		   location.href= document.referrer;
		   location.href = "${ pageContext.servletContext.contextPath }/issue/task";
	}
	
	$(document).ready(function() {
		$('#datatablesSimple tbody').on('click', 'tr', function () {
		      var no = this.children[0].innerText;
		      
 	          $.ajax({
	            url :"issueDetail",
	            type : "get",
	            data : { no : no },
	            success : function(data, textStatus, xhr) {
	               
	                  console.log(data);
	                  console.log(Object.entries(data));
	                  
	                  const guideArray = Object.entries(data);
	                  const issueDetail = JSON.parse(data.issueDetail)
	                  console.log(issueDetail);
	                  const projectMember = JSON.parse(data.projectMember)	                  
	                  console.log(projectMember);
	                  
	                  const registerName = issueDetail.register.name;
	                  console.log("이슈 등록인의 이름은 : " + registerName);
	                  
	                  const memberName = projectMember[0].memberName
	                  console.log("프로젝트 구성인원 목록의 첫 이름은 : " + memberName)
	                  const fileNo = issueDetail.file;
	                  console.log(fileNo);
	                  console.log("파일의 length는 : " +  issueDetail.file.length);
					  console.log("프로젝트 구성인원의 length는 : " + projectMember.length);
					  
					  console.log(projectMember.length);
					  
					  /* 반복문 안이라서 클릭 될때마다 버튼이 생성되는걸 막아줌 */
	                  $("#register").empty();
					  /* register에 select문에 기본값으로 주기 */
	                  const $registerName = "<option value = '" + issueDetail.register.no + "' selected >" + issueDetail.register.name + "</option>";
	                  $("#register").append($registerName);
	                  
	                  //register에 for문으로 전체 출력
	                  for(let i = 0; i < projectMember.length; i++){
	                	  const $memberTag = "<option value = '" + projectMember[i].memberNo + "'>" + projectMember[i].memberName + "</option>";
	                      $("#register").append($memberTag);
	                  }
	                  
	                  /* 반복문 안이라서 클릭 될때마다 버튼이 생성되는걸 막아줌 */
	                  $("#manager").empty();
	                  /* manager에 select문에 기본값으로 주기 */
	                  if(issueDetail.manager == null) {
	                	  const $managerName = "<option value = '" + "0" + "' selected >" + '' + "</option>";
	                	  $("#manager").append($managerName);
	                  } else {
	                	  const $managerName = "<option value = '" + issueDetail.manager.no + "' selected >" + issueDetail.manager.name + "</option>";
		                  $("#manager").append($managerName);
	                  }
	                  
	                  //manager에 for문으로 전체 출력
	                  for(let i = 0; i < projectMember.length; i++){
	                	  const $memberTag = "<option value = '" + projectMember[i].memberNo + "'>" + projectMember[i].memberName + "</option>";
	                      $("#manager").append($memberTag);
	                  }
	                  
	                  $("#read-no").val(issueDetail.no);      
	                  $("#read-name").val(issueDetail.name);
	                  $("#read-createdDate").val(issueDetail.createdDate);
	                  $("#read-deadline").val(issueDetail.deadline);
	                  $("#read-progressStatus").val(issueDetail.progressStatus);
	                  $("#read-importance").val(issueDetail.importance);
	                  $("#read-content").val(issueDetail.content);
	                  $("#read-answer").val(issueDetail.answer);
	                  $("#read-projectNo").val(issueDetail.projectNo);
	                  $("#read-taskNo").val(issueDetail.task.no);
	                  $("#myModal").modal("show");
	                  
	                  $("#downloadZone").empty();
	                  if(issueDetail.file.length != 0) {
	                	  
	                	  const fileName = issueDetail.file[0].originalName;
						  console.log("첫번째 파일의 이름은  : " + fileName);
						  
 	                	  for(let i = 0; i < issueDetail.file.length; i++) {

						  $("#read-fileNo").val(issueDetail.file[i].no);
						  const $fileNo = issueDetail.file[i].no;
						  console.log("파일 번호는 : " + issueDetail.file[i].no);
						  console.log("파일 이름은 : " + issueDetail.file[i].originalName);
						  
	                	  $buttonsTag = "<div class='mt-4 row'><div class='col-2 center' style='vertical-align: top;''><label>첨부파일</label></div><div class='col-3'><div class='btn-group' id='attaachmentNameArea'><input type='button' class='btn btn-outline-dark' id='read-originalName' name='originalName' value='" + issueDetail.file[i].originalName + "'><button type='button' class='btn btn-outline-dark dropdown-toggle dropdown-toggle-split' data-toggle='dropdown'><span class='caret'></span></button><div class='dropdown-menu' id='downloadArea'><a class='dropdown-item' href='${pageContext.servletContext.contextPath}/issue/download/" + $fileNo + "'>다운로드</a><a class='dropdown-item' href='${pageContext.servletContext.contextPath}/issue/deleteFile/" + $fileNo + "'>삭제</a></div></div></div></div>";
	              		  $("#downloadZone").append($buttonsTag);

						  }
	                  }
	                  
	                  /* ------------ 완료 여부에 따라서 이슈 종료일을 결정하기 위한 로직  ------------------ */
	                  $("#completedDateZone").empty();	// 행을 클릭할 때 마다 버튼이 생성되는걸 방지하기 위해서 초기화
	                  if(issueDetail.completedDate == null) {	//종료일이 null 일 때
	              		$("#read-progressStatus").change(function(){
	              			$("#completedDateZone").empty();
	              			if($("#read-progressStatus").val() == "완료") {
	              				 $completedDate = "<div class='col-2 center' id='cZone'><label for='read-completedDate'>종료일</label></div><div class='col-4' id='cZone2'><input type='date' id='read-completedDate' name='completedDate' required></div>";
	              			     $("#completedDateZone").append($completedDate);
	              			     
	              			} else {	//progressStatus의 value가 '완료'가 아닐 때
	              				$("#completedDateZone *").remove();		//div영역에 있는 모든것을 날림
	              			}
	              		});
	              	} else {
	              		$completedDate = "<div class='col-2 center' id='cZone'><label for='read-completedDate'>종료일</label></div><div class='col-4' id='cZone2'><input type='date' id='read-completedDate' name='completedDate' required></div>";
	           	     	$("#completedDateZone").append($completedDate);
	              		$("#read-completedDate").val(issueDetail.completedDate);
	              		
	              		$("#read-progressStatus").change(function(){
	              			if($("#read-progressStatus").val() == "대기중") {
	              				console.log("이슈종료일은 : " + issueDetail.completedDate)
	              				$("#completedDateZone *").remove();
	              			} else if($("#read-progressStatus").val() == "처리중") {
	              				$("#completedDateZone *").remove();
	              			}
	              		});
	              	}
	                /*-----------------------------------------------------------------------------------  */  
	              }, 
	              error:function(data) {
	                  console.log(data);
	              }
	              });
	   		});
		});
</script>
<jsp:include page="../common/footer.jsp"/>

</body>
</html>