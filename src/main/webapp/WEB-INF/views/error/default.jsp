<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>404 Error - SB Admin</title>
<link href="css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/js/script.js"></script>
<link href="${ pageContext.servletContext.contextPath }/resources/css/styles.css" rel="stylesheet" />
<style>
.error-body{
	width:70%;
	height:100%;
	text-align: center;
	margin-top: 5%;
}
.error-area {
	height: 800px;
}
</style>
    </head>
    <body>	
    	<jsp:include page="/WEB-INF/views/common/errorheader.jsp"/>
			<div class="error-area">
				<h1>알수없는 에러가 발생했습니다.</h1>
				<div class="error-body">
					<a href="${ pageContext.servletContext.contextPath}/">
						<i class="fas fa-arrow-left me-1"></i>
						<label>메인으로 돌아가기</label>
					</a>
				</div>
	     	</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>            
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>

