
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="pagingArea" align="center">
	
		<div class="paging mt-3">
			<!-- 맨 앞으로 이동 버튼 -->
		    <button id="substartPage"><i class="fas fa-angle-double-left"></i></button>
		    
			
			<!-- 이전 페이지 버튼 -->
			<c:if test="${ requestScope.subselectCriteria.pageNo <= 1 }">
				<button disabled><i class="fas fa-angle-left"></i></button>
			</c:if>
			<c:if test="${ requestScope.subselectCriteria.pageNo > 1 }">
				<button id="subprevPage"><i class="fas fa-angle-left"></i></button>
			</c:if>
			
			<!-- 숫자 버튼 -->
      
			<script>
			function subpageButtonAction(text) {
				alert("123");
				location.href = link + "?subcurrentPage=" + text + searchText;
			}
			</script>
			<c:forEach var="p" begin="${ requestScope.subselectCriteria.startPage }" end="${ requestScope.subselectCriteria.endPage }" step="1">
				<c:if test="${ requestScope.subselectCriteria.pageNo eq p }">
					<button disabled><c:out value="${ p }"/></button>
				</c:if>
				<c:if test="${ requestScope.subselectCriteria.pageNo ne p }">
					<button onclick="subpageButtonAction(this.innerText)"><c:out value="${ p }"/></button>
				</c:if>
			</c:forEach>
			<!-- 다음 페이지 버튼 -->
			<c:if test="${ requestScope.subselectCriteria.pageNo >= requestScope.subselectCriteria.maxPage }">
				<button disabled><i class="fas fa-angle-right"></i></button>
			</c:if>
			<c:if test="${ requestScope.subselectCriteria.pageNo < requestScope.subselectCriteria.maxPage }">
				<button id="subnextPage"><i class="fas fa-angle-right"></i></button>
			</c:if>
			
			<!-- 마지막 페이지로 이동 버튼 -->
			<button id="submaxPage"><i class="fas fa-angle-double-right"></i></button>
		</div>
	</div>
</body>
</html>



