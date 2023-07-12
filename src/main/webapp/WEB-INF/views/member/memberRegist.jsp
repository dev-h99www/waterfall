<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>            
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${ pageContext.servletContext.contextPath }/resources/css/buttons.css" rel="stylesheet" type="text/css"> <!-- 버튼-->
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />  -->
<link href="${ pageContext.servletContext.contextPath }/resources/css/login.css" rel="stylesheet" />
<link href="${ pageContext.servletContext.contextPath }/resources/css/buttons.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Insert title here</title>
<style>
.box{
    
    width: 70%;
    height: 80%;
    border-style: solid;
    border-color: white;
}

#outbox{
    position: absolute; left: 21%; top: 10%;
    border-width: 1px;
}
.search-bar{
    width: 500px;
    
}
.select-bar{
    width: 160px;
}
.idtable {
    width: 42%;    
}
td{
    text-align: center;
}

label {
    cursor: pointer;
    margin-left: 5px;
   
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
	
	const message = '${ requestScope.message}';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
</head>
<body>

 	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<div class="box" id="outbox">
                <main>
                    <div>
                        <div>
                            <svg xmlns="http://www.w3.org/2000/svg" top="1000" width="60" height="31" fill="currentColor" class="bi bi-person-plus-fill" viewBox="1 -2 16 16">
                            <path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                            <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                            </svg>
                        </div>
                        <div style="position:absolute; left:50px; top: 10px;">
                            <h6><strong>계정생성</strong></h6>
                        </div>
                        <hr align="left" style="border: solid 2px rgb(0, 0, 0); width: 100%;">
                        <div>
                            <form action="${pageContext.servletContext.contextPath}/member/regist3" method="post">
                                <div class="mx-auto search-bar input-group mt-5">
                                    <input type="text" id="name" name="name" class="form-control" placeholder="이름 입력" aria-label="Recipient's username" aria-describedby="basic-addon2"
                                     style="border-left-width: auto; margin-left: 31%; margin-right: 31%;" required="required">
                                </div>        
                                <div class="mx-auto search-bar mt-5 form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">
                                      Client
                                    </label>
                                </div>
                             
                                <div class="mt-5">
                                    <table align="center" width="510">
                                        <tr>
                                            <td align="center">				
                                                <select class="select-bar form-select" aria-label="Default select example" id="dept" name="dept">
                                                    <option value="" selected>부서</option> 
                                                  	<c:forEach var="dept" items="${ deptList }">
                                                  		<option value="${ dept.deptCode }"><c:out value="${ dept.deptName }"/></option>
                                                  	</c:forEach>                                                    
                                                </select> 
                                            </td>
                                            <td align="center">
                                                <select class="select-bar form-select" aria-label="Default select example" id="team" name="team">
                                                    <option value="">팀 선택</option>
                                                </select>
                                            </td>
                                            <td align="center">
                                                <select class="select-bar form-select" aria-label="Default select example" id="job" name="job">
                                                    <option value="" selected>직급</option>
                                                    <c:forEach var="job" items="${ jobList }">
                                                		<option value="${ job.jobCode }"><c:out value="${ job.jobName }"/></option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>                                                                      
                                </div>
                                <!-- <div class="mt-5 mx-auto" style="width: 8%">
                                    <button class="ui secondary button">
                                    	    추가
                                    </button>
                                </div>
                                <div class="mt-5">
                                    <table class="idtable" align="center" border="1"  height="120" style="border-radius: 8px;" > 
                                        <tr>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                        </tr>
                                        <tr>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                        </tr>
                                        <tr>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                            <td>머머머<label><i class="bi bi-x-circle-fill"></i></label></td>
                                        </tr>    
                                    </table>
                                </div> -->
                                <div class="mt-5 mx-auto" style="width: 16%;" >
                                    <div style="float: left; width: 50%;">
                                        <button class="ui secondary button" type="submit">
                                           	 등록
                                        </button>
                                    </div>    
                                    <div style="float: left; width: 50%;">
                                        <input class="ui secondary button" type="button" value="취소" onclick="location.href='${ pageContext.servletContext.contextPath }/member/list'">                                                                                                                       
                                    </div> 
                                </div>
                            </form>
                        </div>
                    </div>
                </main>
            </div>
                 				 <script>
            					/* 		$("input:checkbox").on('click',function(){

            								$("[id=dept]").val("미지정");
            								$("[id=team]").val("미지정");
            								$("[id=job]").val("미지정");          								
            								}); */
            							
            							$("#dept").on("change", function(){
            								const deptCode = $(this).val();
            								console.log(deptCode);
            								const url = "${pageContext.servletContext.contextPath}/member/regist2/" + $(this).val();
            								$("#team option").remove();
            								$.ajax({
            									url : url,
            									type : "get",
            									data : {deptCode:deptCode},
            									
            									success: function(data,xhr){
            										console.log(data);
            										const teamList = JSON.parse(data.teamList);
        											console.log(teamList);
            										  for(let i = 0; i < teamList.length; i++) {
            											const $teamTag = "<option value = '" + teamList[i].teamCode + "'>" + teamList[i].teamName + "</option>";
            											
            											$("#team").append($teamTag);
            										}
            									}, 
            									error: function(data,xhr){
            										alert("문제생김");
            										console.log(data);
            										console.log(xhr);
            									}
            								});
            							}); 
            							
            							
            							
            							/* $("#job").on('click',function(){
            								const jobCode = $(this).val();
            								const url = "${pageContext.servletContext.contextPath}/member/regist2/" + $(this).val();
            								
            								$.ajax({
            									url: url,
            									type: "get",
            									data: {teamCode: teamCode},
            									success: function(data){
            										jobList = JSON.parse(data.jobList);
            										for(let i = 0; i < jobList.length; i++){
            											const $jobTag = "<option value"
            										}
            									}
            								});
            							}); */
            							
            					  </script>

</body>
</html>