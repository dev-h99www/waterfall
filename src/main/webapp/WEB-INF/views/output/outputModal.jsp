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
<!-- 삭제 확인 Modal "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
              <div class="modal fade modal-dialog-scrollable" id="deleteModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                    <div class="modal-content" style="top: 200px">
                        <div style="background-color: #212529;">
                            <br>
                        </div>
                        <div class="modal-header">
                            <span class="modal-title" id="exampleModalLabel"><strong>산출물 삭제</strong></span>
                        </div> 
                    <!-- 모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남  -->
                        <form>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <!--label for랑 input id랑 일치시키면 라벨에 타이틀을 적을경우 라벨눌르면 인풋박스안 텍스트로 포커스를 맞춘다  -->
                                    <!-- placeholder는 인풋박스안에 적을 내용 기술해두 되고 빼두되고 편한대로 -->
                                  <label for="clieck-point" class="col-form-label" style="margin-left: 35%; margin-top:5%;">삭제하시겠습니까?</label>
                                </div>
                            </div>
                            <!-- 모달의 바디 끝  -->
                            <div class="modal-footer">
                                <button type="button" id="delete" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
                                <button style="margin-right: 36%;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
                
    <!-- 수정 Modal "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
              <div class="modal fade modal-dialog-scrollable" id="modifyModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                    <div class="modal-content" style="top: 200px">
                        <div style="background-color: #212529;">
                            <br>
                        </div>
                        <div class="modal-header">
                            <span class="modal-title" id="exampleModalLabel"><strong>산출물 수정</strong></span>
                        </div> 
                    <!-- 모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남  -->
                        <form action="${ pageContext.servletContext.contextPath }/output/update" method="post" encType="multipart/form-data">
                            <div class="modal-body">
                                <div class="mb-3">
                                    <!--label for랑 input id랑 일치시키면 라벨에 타이틀을 적을경우 라벨눌르면 인풋박스안 텍스트로 포커스를 맞춘다  -->
                                    <!-- placeholder는 인풋박스안에 적을 내용 기술해두 되고 빼두되고 편한대로 -->
                                  <label for="clieck-point" class="col-form-label">내용</label>
                                  <textarea class="form-control" name="content" rows="5" cols="40"></textarea>
                                  <label class="col-form-label">첨부파일</label>
                                  <input type="file" class="form-control" name="outputFile" required>
                                  <input id="outputNo" type="hidden" name="outputNo">
                                  <input name="taskNo" type="hidden" name="taskNo">
                                  <input id="fileNo" type="hidden" name="fileNo">
                                </div>
                            </div>
                            <!-- 모달의 바디 끝  -->
                            <div class="modal-footer">
                                <button type="submit" id="modify" class="btn btn-secondary">확인</button>
                                <button style="margin-right: 36%;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
     <!-- 수정 확인 Modal -->
        <div class="modal fade" id="modifyModal2" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                <div class="modal-content" style="top: 200px; height: 252px; width: 402px; margin-left: 0px; left: 100px;">
                    <div style="background-color: #212529;">
                        <br>
                    </div>
                    <div class="modal-header">
                        <span class="modal-title" id="exampleModalLabel"><strong></strong></span>
                    </div> 
                <!-- 모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남  -->           
                        <div class="modal-body">  
                            <h5 align="center" style="margin-top: 30px;"><strong>수정하시겠습니까?</strong></h5>                          
                        </div>
                        <!-- 모달의 바디 끝  -->
                        <div class="modal-footer">                           
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="margin-right: 44%;">확인</button>
                        </div>                    
                </div>
            </div>
        </div>
    <!-- 수정 확인 Modal 끝 -->         
                
	<!-- 산출물 오류 확인 Modal "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
              <div class="modal fade modal-dialog-scrollable" id="errorModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                    <div class="modal-content" style="top: 200px">
                        <div style="background-color: #212529;">
                            <br>
                        </div>
                        <div class="modal-header">
                            <span class="modal-title" id="exampleModalLabel"><strong>산출물</strong></span>
                        </div> 
                    <!-- 모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남  -->
                        <form>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <!--label for랑 input id랑 일치시키면 라벨에 타이틀을 적을경우 라벨눌르면 인풋박스안 텍스트로 포커스를 맞춘다  -->
                                    <!-- placeholder는 인풋박스안에 적을 내용 기술해두 되고 빼두되고 편한대로 -->
                                  <label for="clieck-point" class="col-form-label" style="margin-left: 27%; margin-top:5%;">산출물이 존재하지 않습니다.</label>
                                </div>
                            </div>
                            <!-- 모달의 바디 끝  -->
                            <div class="modal-footer">
                                <button style="margin-right: 45%;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
                <!--산출물 오류 확인 Modal -->

</body>
</html>