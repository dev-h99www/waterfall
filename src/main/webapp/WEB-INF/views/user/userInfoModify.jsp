<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>            
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
.select-phone{
    width: 500px;
}

/* 메일 영역 */
.mail_wrap{
	width: 100%;
    margin-top: 20px;
}
.mail_name{
	font-size: 25px;
    font-weight: bold;
}
.mail_input_box{
	border: 1px solid black;
	height:31px;
	padding: 10px 14px;	
	
}
.mail_input{
	width:100%;
	height:100%;
	border:none;
	font-size:28px;
}
.mail_check_wrap{
	margin-top: 20px;	
}
.mail_check_input_box{
    height: 31px;
    width: 33%;
    float: left;
}
#mail_check_input_box_false{
	background-color:#ebebe4;
}
#mail_check_input_box_true{
	background-color:white;
}
.mail_check_input{
	width:100%;
	height:100%;
	border:none;
	font-size:28px;
}
.mail_check_button{
    
}
.mail_check_button :active{
	box-shadow: 1px 1px 0 rgb(0,0,0,0.5); 
	position: relative; 
	top:2px;
}
.correct{
    color : green;
}
.incorrect{
    color : red;
}
.clearfix{
	clear: both;
}

	
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link href="${ pageContext.servletContext.contextPath }/resources/css/buttons.css" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="${ pageContext.servletContext.contextPath }/resources/css/styles.css" rel="stylesheet" />
        
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
</head>
<body>
	<div class="modal fade modal-dialog-scrollable" id="exampleModal" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
         <div class="modal-dialog">
             <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
             <div class="modal-content" style="top: 100px">
                 <div style="background-color: #212529;">
                     <br>
                 </div>
                 <div class="modal-header">
                     <span class="modal-title" id="exampleModalLabel"><strong>비밀번호 변경</strong></span>
                 </div> 
             <!-- 모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남  -->
                 <form action="${ pageContext.servletContext.contextPath }/user/pwUpdate" method="post" id="pwUpdateForm" name="pwUpdateForm">
                     <input type="hidden" id="id" name="id" value="${ sessionScope.loginMember.id }">
                     <div class="modal-body">
                         <div class="mb-3">
                             <!--label for랑 input id랑 일치시키면 라벨에 타이틀을 적을경우 라벨눌르면 인풋박스안 텍스트로 포커스를 맞춘다  -->
                             <!-- placeholder는 인풋박스안에 적을 내용 기술해두 되고 빼두되고 편한대로 -->
                           <label for="clieck-point" class="col-form-label" style="margin-left: 15%;">현재 비밀번호</label>
                           <input type="password" name="pwd" id="pwd" class="form-control" placeholder="현재 비밀번호" id="clieck-point" style="width: 70%; margin-left: 15%;">
                         </div>
                         <div class="mb-3" style="height: 70px;">
                             <label for="clieck-point1" class="col-form-label" style="margin-left: 15%;">새 비밀번호</label>
                             <input type="password" name="pwd1" id="pwd1" class="form-control" placeholder="새 비밀번호" id="clieck-point1" style="width: 70%; margin-left: 15%;">
                         </div>
                         <div class="mb-3">
                             <label for="clieck-point2" class="col-form-label" style="margin-left: 15%;">새 비밀번호 확인</label>
                             <input type="password" name="pwd2" id="pwd2" class="form-control" placeholder="새 비밀번호 확인" id="clieck-point2" style="width: 70%; margin-left: 15%;">
                         </div>
                         <!-- 아래 텍스트박스는 글쓴이가 쓰는 만큼 늘어날 수 있다. 초기 크기 설정은 위에 일반 텍스트 박스 참조 -->
                         <!-- <div class="mb-3">
                             <label for="message-text" class="col-form-label">Message:</label>
                             <textarea class="form-control" id="message-text"></textarea>
                         </div> -->
                         <!-- 셀렉트 박스 크기 조절 위에 참고 하면 충분히 합니다. -->
                         <!-- <div>
                             <select class="form-select">
                                 <option selected>부서</option>
                                 <option value="1">One</option>
                                 <option value="2">Two</option>
                                 <option value="3">Three</option>
                             </select> 
                         </div> -->
                     </div>
                     <!-- 모달의 바디 끝  -->
                     <div class="modal-footer">
                         <button type="button" name="pwdUpdate" id="pwdUpdate" class="btn btn-secondary">확인</button>
                         <button style="margin-right: 36%;" id="cansle" type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                     </div>
                 </form>
             </div>
         </div>
     </div>
            <!-- Modal HTML  -->
	        
        <!-- Modal HTML 2 -->
        <!-- <div class="modal fade" id="exampleModalToggle2" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                 style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요
                <div class="modal-content" style="top: 200px; height: 252px; width: 402px; margin-left: 0px; left: 100px;">
                    <div style="background-color: #212529;">
                        <br>
                    </div>
                    <div class="modal-header">
                        <span class="modal-title" id="exampleModalLabel"><strong></strong></span>
                    </div> 
                모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남            
                        <div class="modal-body">  
                            <h5 align="center" style="margin-top: 30px;"><strong>등록되었습니다.</strong></h5>                          
                        </div>
                        모달의 바디 끝 
                        <div class="modal-footer">                           
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="margin-right: 44%;">확인</button>
                        </div>                    
                </div>
            </div>
        </div> -->
	
	<jsp:include page="../common/inprojectheader.jsp"/>
	<jsp:include page="../common/footer.jsp"/>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="assets/demo/chart-pie-demo.js"></script>
	
	
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
                            <h6><strong>개인정보관리</strong></h6>
                        </div>
                        <hr align="left" style="border: solid 2px rgb(0, 0, 0); width: 100%;">
                        <div>
                            <form action="${ pageContext.servletContext.contextPath }/user/memberInfo" method="post">
                                <div>
                                    <button type="button" class="ui secondary button" style="float: right;" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        	비밀번호 변경
                                    </button>
                                </div>
                               
                                <div class="mx-auto search-bar input-group mt-5">
                                    <input type="text" class="form-control" placeholder="아이디" aria-label="Recipient's username" aria-describedby="basic-addon2" style="margin-top: 50px; left: 0px;margin-left: 30%;margin-right: 30%;"
                                    	value="${ sessionScope.loginMember.id }" readonly>                              
                                </div> 
                                <div class="mx-auto search-bar input-group mt-4">
                                    <input type="text" class="form-control" placeholder="이름 입력" aria-label="Recipient's username" aria-describedby="basic-addon2" style="margin-left: 30%;margin-right: 30%;"
                                    	value="${ sessionScope.loginMember.name }" readonly>
                                </div>

                                <div class="mx-auto select-phone input-group mt-4">
                                    <input type="text" class="form-control" name="phone" placeholder="휴대전화" aria-label="Recipient's username" aria-describedby="basic-addon2" style="margin-left: 30%;margin-right: 30%;"
                                    	value="${ sessionScope.loginMember.phone }">                                                        
                                </div>                            
                                
                                <div class="mx-auto select-phone input-group mt-4">
                                    <input class="form-control mail_input" name="email" placeholder="이메일" aria-label="Recipient's username" aria-describedby="basic-addon2" style="margin-left: 30%;margin-right: 30%;"
                                    	value="${ sessionScope.loginMember.email }">                                                             
                                </div>
                                
                                <div class="mx-auto select-phone input-group mt-4 mail_check_wrap">
                                	<div class="mail_check_input_box" id="mail_check_input_box_false" style="margin-left: 30%;width: 530px;">
                                   	 	<input class="form-control mail_check_input" placeholder="이메일 인증번호" aria-label="Recipient's username" aria-describedby="basic-addon2" disabled="disabled">
                                    </div>
                                    <div class="mail_check_button">
                                    	<span class="ui secondary button" style="height: 35.1428px; margin-left: 12px;">인증번호 전송</span>
                                    </div>
                                    <div class="clearfix"></div>
                                    <span id="mail_check_input_box_warn"></span>                                    
                                </div> 

                                <div class="mt-5 mx-auto" style="width: 16%;" >
                                    <div style="float: left; width: 50%;">
                                        <button class="ui secondary button" type="submit">
                                            	등록
                                        </button>
                                    </div>    
                            
                                    <div style="float: left; width: 50%;">
                                        <input type="button" class="ui secondary button" value="취소" onclick="location.href='${pageContext.servletContext.contextPath }/project/list/'">                                                                                                                       
                                    </div> 
                                </div>
                            </form>    
                        </div>
                    </div>
                </main>
            </div>
            
            <script>
				var code = "";
            	$(document).ready(function(){
            		
            		$("#pwdUpdate").on("click", function(){
    			
            			if($("#pwd").val()=="") {
            				console.log("첫번 째 비밀번호");
            				alert("현재 비밀번호를 입력해주세요");
            				$("#pwd").focus();
            				return false
            			}
            			
            			if($("#pwd1").val()=="") {
            				console.log("두번 째 비밀번호");
            				alert("변경비밀번호를 입력해주세요");
            				$("pwd1").focus();
            				return false
            			}
            			
            			if($("#pwd2").val()=="") {
            				alert("변경 비밀번호 확인을 입력해주세요");
            				$("pwd2").focus();
            				return false
            			}
            			if($("#pwd1").val() != $("#pwd2").val()){
            				alert("새 비밀번호확인이 일치하지 않습니다.");
            				$("#pwd2").focus();
            				return false
            			}
            			
            			if($("#pwd1").val() == $("#pwd2").val()){
            				
            				$.ajax({
            					url: "pmCheck",
            					type : "post",
            					data : $("#pwUpdateForm").serializeArray(),
            					success: function(data,status,xhr){
            						
            						if(data == 0){
            							alert("패스워드가 틀렸습니다.");
            							return;
            						}else{
            							if(confirm("변경하시겠습니까?")){
            								$("#pwUpdateForm").submit();
            							}
            						}
            					},
            					error : function(data,xhr){
            						console.log(data);
            					}
            				})
            			}
            		});	
            	});
            	
            	$(document).ready(function(){
            		$("#cansle").on("click", function(){
            			$("#pwd").val("");
            			$("#pwd1").val("");
            			$("#pwd2").val("");            			
            		});
            	});
            	
            	/* 인증번호 이메일 전송 */
            	$(".mail_check_button").click(function(){
            		var email = $(".mail_input").val(); //입력한 이메일
            		var checkBox = $(".mail_check_input");		//인증번호 입력란
            		var boxWrap = $(".mail_check_input_box");	//인증번호 입력란 박스
            		
            		$.ajax({
            			type : "GET",
            			url : "${pageContext.servletContext.contextPath}/member/mailCheck?email=" + email,
            			success:function(data){
            				console.log("data" + data);
            				
            				checkBox.attr("disabled", false);
            				boxWrap.attr("id", "mail_check_input_box_true");
            				code = data;
            			}
            		});
				});
            	
            	/* 인증번호 비교 */
            	$(".mail_check_input").blur(function(){
            		
            		var inputCode = $(".mail_check_input").val();  		
            		var checkResult = $("#mail_check_input_box_warn");	
            		
            		if(inputCode == code){								
            			checkResult.html("인증번호가 일치합니다.");
            	        checkResult.attr("class", "correct");        
            	    } else {                                            
            	        checkResult.html("인증번호를 다시 확인해주세요.");
            	        checkResult.attr("class", "incorrect");
            	    }
            	});
            </script>
	
</body>
</html>