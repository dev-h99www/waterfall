<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${ pageContext.servletContext.contextPath }/resources/css/login.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Insert title here</title>
<script>

	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
</head>
<body class="bg-primary">

<!-- 로그인 머리 부분 시작 -->
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
              <a class="navbar-brand ps-3" href="${ pageContext.servletContext.contextPath }">Project Waterfall Service</a>
        </nav>
        <!-- 로그인 머리 부분 끝  -->

        <!-- 바디 영역 부분시작 -->
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5" style="margin-top: 12%">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                        <h3 class="text-center my-4" style="color: white;">Login</h3>
                                    </div>
                                    <div class="card-body">
										<form id="loginForm" action="${ pageContext.servletContext.contextPath }/member/login2" method="post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" type="text" name="id" placeholder="id"/>
                                                <label for="inputEmail">ID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control"type="password" name="pwd" placeholder="Password" />
                                                <label for="inputPassword">Password</label>
                                            </div>
                                            <div class="form-check mb-3">
                                                <input class="form-check-input" id="idSaveCheck" type="checkbox" value="" />
                                                <label class="form-check-label" for="inputRememberPassword">Remember ID</label>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <p class="small">비밀번호 관련 문의 : 010-xxxx-xxxx</p>
                                                <input class="btn btn-primary" type="submit" value="login" id="login2"/>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small">
                                            <strong style="color: white;"> Wellcome To PMS !!</strong>
                                        </div>     
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>            
            </div>
            <!-- 바디 영역 부분 끝 -->


            <!-- 여기서부터 하단 FOOTER 시작 -->
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; 민트초코 4ever 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <!-- 하단 끝 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${ pageContext.servletContext.contextPath }resource/js/scripts.js"></script>
        
       <!-- 	<script>
       		$(document).ready(function(){
       			var userInputId = getCookie("userInputId"); //저장된 쿠기 가져온다
       			$("input[name='id']").val(userInputId);
       			
       			if($("input[name='id']").val() != ""){  //그 전에 id를 저장해서 처음 페이지 로딩
       													//아이디 저장하기 체크되어 있을 시
       				$("#idSaveCheck").attr("checked", true); //id 저장하기를 체크 상태로 두기.
       			}
       			
       			$("#idSaveCheck").change(function(){             //체크박스에 변화 발생시
       				if($("#idSaveCheck").is(":checked")){ 		 //id 저장하기 체크했을 때,
       					var userInputId = $("input[name='id']").val(); 
       					setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키보관
       				} else {
       					deleteCookie("userInputId");
       				}
       			});
       		  	
       			/* id 저장하기를 체크한 상태에서 id를 입력하는 경우, 이럴 때도 쿠키 저장*/
       			$("input[name='id']").keyup(function(){			  // id 입력 칸에 id를 입력할 때 	
       				if($("#idSaveCheck").is(":checked")){		  // id 저장하기를 체크한 상태라면
       					var userInputId = $("input[name='id']").val(); 
       					setCookie("userInputId", userInputId, 7); // 7일동안 보관
       				}
       			});
       		});
       		
       		function setCookie(cookieName, value, exdays){
       			var exdate = new Date();
       			exdate.setDate(exdate.getDate() + exdays);
       			var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
				document.cookie = cookieName + "=" + cookieValue;       		
       		}
       		
       		function deleteCookie(cookieName){
       			var expireDate = new Date();
       			expireDate.setDate(expireDate.getDate() - 1);
       			document.cookie = cookieName + "=" + "; expires=" + expireDate.toGMTString();
       		}
       		
       		function getCookie(cookieName) {
       			cookieName = cookieName + "=";
       			var cookieDate = document.cookie;
       			var start = cookieData.indexOf(cookieName);
       			var cookieValue = '';
       			if(start != -1){
       				start += cookieName.length;
       				var end = cookieData.indexOf(';', start);
       				if(end == -1)end = cookieData.length;
       				cookieValue = cookieData.substring(start, end);
       			}
       			return unescape(cookieValue);
       		}
       	</script> -->
</body>
</html>