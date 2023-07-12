<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

#layoutSidenav_content .todo h1 {
  padding-left: 60px;
  padding-top: 60px;
}
#layoutSidenav_content .todo h2 span {
  vertical-align: bottom;
}
#layoutSidenav_content .todo hr {
  width: 95%;
  margin: 10px auto;
}
#layoutSidenav_content .todo .tbl-wrapper {
  width: 95%;
  /* font-size: 1rem; */
}
#layoutSidenav_content .todo .tbl-wrapper .write button {
  /* font-size: 1rem; */
  background: #000;
  padding: 4px 18px;
  border-radius: 8px;
}
#layoutSidenav_content .todo .tbl-wrapper .paging {
  text-align: center;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button {
  /* font-size: 1rem; */
  background: none;
  border: none;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button:hover {
  text-decoration: underline;
}
table, th, td {
  border: 1px solid #e5e5e5;
  /* border-collapse: collapse; */
}
#layoutSidenav_content .todo .todo-tbl {
  width: 100%;
/*   height: 400px; */
  text-align: center;
  border-left: none;
  border-right: none;
}
#layoutSidenav_content .todo .todo-tbl tbody tr td/* :first-child, 
#layoutSidenav_content .todo .todo-tbl tbody tr td:last-child */ {
  border-left: none;
  border-right: none;
}
#layoutSidenav_content .todo .todo-tbl thead tr {
  background: #f0f0f0;
}
#layoutSidenav_content .todo .todo-tbl thead tr th/* :first-child, 
#layoutSidenav_content .todo .todo-tbl thead tr th:last-child */ {
  border-left: none;
  border-right: none;
  border-bottom-color: #999;
}
#layoutSidenav_content .todo .todo-tbl tr {
  height: 30px;
}
#layoutSidenav_content .todo .todo-tbl tbody tr:hover {
  background: paleturquoise;
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

th {
    text-align: inherit;
    text-align: -webkit-match-parent;
    
    height: 50px;
}
td {
    height: 50px;
}

</style>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
	<div class="todo">
		<h1><span><i class="fas fa-users-cog"></i>프로젝트 인원조회</span></h1>
		<hr>
		<div class="tbl-wrapper mx-auto">
		    <div class="write">
		        <button type="button" class="btn btn-secondary mb-2" data-bs-toggle="modal" data-bs-target="#member-regist-modal"><i class="far fa-edit me-1"></i>등록</button>
	    	</div>
		    <table class="todo-tbl" id="meetingTable" >
				<colgroup>
					<col width="10%">
					<col width="30%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="1%">
				</colgroup>
				<thead>
				    <tr>
				        <th scope="colgroup">이름</th>
				        <th scope="colgroup">역할</th>
				        <th scope="colgroup">완료업무</th>
				        <th scope="colgroup">진행중 업무</th>
				        <th scope="colgroup">등록 산출물</th>
				        <th scope="colgroup">등록 이슈</th>
				        <th scope="colgroup">참가일</th>
				        <th scope="colgroup"></th>
				    </tr>
				</thead>
				<tbody id="member-list">
					<c:forEach var="member" items="${ projectMemberList }">
						<tr class="mt-2" data-bs-toggle="modal" data-bs-target="#member-modify-modal">
							<td><c:out value="${ member.memberName }" /></td>
							<td>
								<c:forEach var="role" items="${member.role}">
								<c:out value="${ role.roleName }" />
								</c:forEach>
							</td>
							<td><c:out value="${ member.completeTask }" /></td>
							<td><c:out value="${ member.progressingTask }" /></td>
							<td><c:out value="${ member.registOutput }" /></td>
							<td><c:out value="${ member.registIssue }" /></td>
							<td><c:out value="${ member.joinDate }" /></td>
							<td><input type="hidden" value='${ member.memberNo }'></td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
			<jsp:include page="/WEB-INF/views/common/sungwonpaging.jsp"/>	
			<div class="search-area">
		    	<div class="search-set mt-2">
		     		<form action="${ pageContext.servletContext.contextPath }/manage/member/list" >
						<select id="searchCondition" name="searchCondition">
							<option value="memberName" ${ requestScope.selectCriteria.searchCondition eq "memberName"? "selected": "" }>이름</option>
							<option value="roleName" ${ requestScope.selectCriteria.searchCondition eq "roleName"? "selected": "" }>역할</option>
						</select> <input type="search" id="searchValue" name="searchValue">
						<button class="btn btn-bs" type="submit"><i class="fas fa-search"></i></button>
					</form>
				</div>
			</div>	
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/manage/memberRegist.jsp" />
	<jsp:include page="/WEB-INF/views/manage/memberModify.jsp" />
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
		$(function() {
			$("#member-list td").hover(function() {
				$(this).parent().css({"background":"rgba(29, 22, 22, 0.106)", "cursor":"pointer"});
			}).mouseleave(function() {
				$(this).parent().css({"background":"white", "color":"black"});
				
			}).click(function() {
				const $memberNo = this.parentNode.children[7].children[0].value;
				const $memberName = this.parentNode.children[0].innerText;
				console.log($memberNo);
				console.log($memberName);
				$("#memberNo-modify").val($memberNo);
				$("#memberName-modify").val($memberName);
				
				$.ajax({
					type: 'get',
					url: "${ pageContext.servletContext.contextPath }/manage/member/detail?memberNo=" + $memberNo,
					success: function(data) {
						$roleList = JSON.parse(data.memberRoleList);
						console.log($roleList);
						for(var i = 1; i < 8; i++) {
							$target = "#role" + i;
							$($target).attr("checked", false);
						}
						for(var i = 0; i < $roleList.length; i++) {
							$target = "#role" + $roleList[i].roleNo;
							$($target).attr("checked", true);
						}
					},
					error: function(xhr, status, error) {
						alert(xhr);
					}
				});
			});
		});
	</script>
</body>
</html>












































