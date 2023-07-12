<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<link href="${ pageContext.servletContext.contextPath }/resources/css/styles.css" rel="stylesheet" />
<script src="${ pageContext.servletContext.contextPath }/resources/js/script.js"></script>
<style>
.notification {
  color: white;
  text-decoration: none;
  padding: 15px 26px;
  position: relative;
  display: inline-block;
  border-radius: 2px;
}

.notification .badge {
  position: absolute;
  right: -10px;
  padding: 5px 10px;
  border-radius: 50%;
  background-color: red;
  color: white;
}

.closebtn {
  margin-left: 15px;
  color: white;
  font-weight: bold;
  float: right;
  font-size: 22px;
  line-height: 20px;
  cursor: pointer;
  transition: 0.3s;
}

.closebtn:hover {
  color: black;
}

.note-content-container {
  display: table;
  width: 100%;
}
.note-content {
  display: table-cell;
  padding: 5px;
}

</style>	
<title>Insert title here</title>

<!-- 추가됐던거 -->
<!-- <link href="css/button.css" rel="stylesheet" type="text/css"> -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css"> -->
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> -->
</head>
<body class="sb-nav-fixed">
		<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
			<!-- Navbar Brand-->
			<a class="navbar-brand ps-3" href="${ pageContext.servletContext.contextPath }">Project Waterfall Service</a>
			<!-- Sidebar Toggle-->
			<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
			<!-- Navbar Search-->
			<form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
	               
	        </form>
	        <!-- Navbar-->
	        
	        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4" id="aaa">
	            <li class="nav-item dropdown">
	                <a class="nav-link dropdown-toggle notification" id="navbarDropdownNotification" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	                	<i class="fas fa-bell"></i><span class="badge" id="badge"></span>
	                </a>
	                <ul id="liZone" class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownNotification" style="overflow: auto">                       
	                </ul>
	            </li>
	        </ul>
	        
	        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
	            <li class="nav-item dropdown">
	                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
	                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">                       
	                    <li><a class="dropdown-item" href="${ pageContext.servletContext.contextPath }/user/info">계정정보 관리</a></li>
	                    <li><hr class="dropdown-divider" /></li>
	                    <li><a class="dropdown-item" href="${ pageContext.servletContext.contextPath }/member/logout">로그아웃</a></li>
	                </ul>
	            </li>
	        </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            
                            <div class="sb-sidenav-menu-heading"></div>
                           
                           
                           <c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.role eq 1 }">
                           
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="menu-font">관리자메뉴</div>
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
		                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
		                                <div class="menu-font2">
		                                     <nav class="sb-sidenav-menu-nested nav">
		                                        <a class="nav-link" href="${ pageContext.servletContext.contextPath }/member/list">계정관리</a>
		                                        <a class="nav-link" href="${ pageContext.servletContext.contextPath }/company/job/list">직급관리</a>
		                                        <a class="nav-link" href="${ pageContext.servletContext.contextPath }/company/dept/list">부서관리</a>
		                                        <a class="nav-link" href="${ pageContext.servletContext.contextPath }/output/admin/list">산출물</a>
		                                        <a class="nav-link" href="${ pageContext.servletContext.contextPath }/issue/project">이슈</a>
		                                        <a class="nav-link" href="${ pageContext.servletContext.contextPath }/history/project">히스토리</a>
		                                    </nav>
		                                </div>
		                            </div>
                           </c:if>
		                            
		                            
		                            
		                            
		                            
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <!-- <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div> -->
                                <div class="menu-font">프로젝트</div>
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                	<div class="menu-font2">
										<a class="nav-link" href="${ pageContext.servletContext.contextPath }/project/list">프로젝트</a>
                                	</div>                               
                                </nav>
                            </div>                          
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
						<div class="small">Logged in as:</div>
						<c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.role eq 1 }">
							Administrator
						</c:if>
						<c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.role eq 2 }">
							Developer
						</c:if>
						<c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.role eq 3 }">
							Client
						</c:if>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">

<script src="${ pageContext.servletContext.contextPath }/resources/js/notification.js"></script>         
</body>
</html>