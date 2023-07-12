<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/inprojectheader.jsp"/>
	
 <div id="layoutSidenav_content">
                <div class="container-fluid px-4">
                        <div class="row" style="margin-left: 0px; width: 91%" >
                            <div class="mt-4 col-10">
                                <h1><i class="fas fa-exclamation-circle"></i>이슈</h1>
                            </div>
                            <div class="mt-4 col-2">
                             <label type="button" data-toggle="modal" data-target="#myModal">
                                 <h1><i class="fas fa-trash-alt"></i>삭제</h1>
                            </label>
                            </div>
                        </div>
                    <div class="container" id="left-container" style="overflow:scroll">
                            <br>
                           	미니프로젝트
                            <br>
                            <br>
                            <div id="box1_body">
                                <label type="button" class="folder_toggle" data-toggle="collapse" data-target="#demo">
                                    <i style='font-size:24px' class='fas'>&#xf07b;</i>
                                   	 업무분석
                                </label>
                                <div id="demo" class="collapse">
                                    <label type="button" class="folder_toggle nav-link" data-toggle="collapse" data-target="#subdemo1" style="color: black;">
                                        <i class="fas fa-folder"></i>
                                       	 업무분석1
                                        <div id="subdemo1" class="collapse">
                                            <label type="button"  class="nav-link" style="color: black;">#1 이슈발생</label>
                                            <label type="button"  class="nav-link" style="color: black;">#2 이슈발생</label>
                                            <label type="button"  class="nav-link" style="color: black;">#3 이슈발생</label>
                                            <label type="button"  class="nav-link" style="color: black;">#4 이슈발생</label>
                                        </div>
                                    </label>
                                    </div>
                                    <br>
                                    <label type="button" class="folder_toggle" data-toggle="collapse" data-target="#demo1">
                                        <i style='font-size:24px' class='fas'>&#xf07b;</i>
                                       	 기획설계
                                    </label>
                                    <div id="demo1" class="collapse">
                                        <label type="button" class="folder_toggle nav-link" data-toggle="collapse" data-target="#subdemo2" style="color: black;">
                                            <i class="fas fa-folder"></i>
                                            	기획1
                                            <div id="subdemo2" class="collapse">
                                                <label type="button"  class="nav-link" style="color: black;"><a href="detailedissue2.html">#1 이슈발생</a></label>
                                                <label type="button"  class="nav-link" style="color: black;">#2 이슈발생</label>
                                                <label type="button"  class="nav-link" style="color: black;">#3 이슈발생</label>
                                                <label type="button"  class="nav-link" style="color: black;">#4 이슈발생</label>
                                            </div>
                                        </label>
    
                                        <label class="nav-link" style="color: black;">
                                            <i class="fas fa-folder"></i>
                                            	기획2
                                        </label>

                                        <label class="nav-link" style="color: black;">
                                            <i class="fas fa-folder"></i>
                                           	 기획3
                                        </label>
                                    </div>
                            </div>
                    </div>
                    <div class="container" id="right-container">
                    		<form>
                                 <div class="mt-4 row">
                                    <div class="col-2 center"><label>업무명</label></div>
                                    <div class="col-1"><input type="text" readonly></div>
                                    <div class="col-3"></div>
                                    <div class="col-2 center"><label>처리일</label></div>
                                    <div class="col-1"><input type="text" readonly></div>
                                </div>

                                <div class="mt-4 row">
                                    <div class="col-2 center"><label>이슈명</label></div>
                                    <div class="col-1"><input type="text" readonly></div>
                                    <div class="col-3"></div>
                                    <div class="col-2 center"><label>상태</label></div>
                                    <div class="col-1"><input type="text" readonly></div>
                                </div>

                                <div class="mt-4 row">
                                    <div class="col-2 center"><label>등록일</label></div>
                                    <div class="col-1"><input type="text" readonly></div>
                                </div>

                                <div class="mt-4 row">
                                    <div class="col-2 center"><label>제기자</label></div>
                                    <div class="col-1"><input type="text" readonly></div>
                                </div>

                                <div class="mt-4 row">
                                    <div class="col-2 center" style="vertical-align: top;"><label>이슈내용</label></div>
                                    <div class="col-1"><textarea name="" id="" cols="22" rows="auto" readonly></textarea></div>
                                    <div class="col-3"></div>
                                    <div class="col-2 center" style="vertical-align: top;"><label>처리내용</label></div>
                                    <div class="col-1"><textarea name="" id="" cols="22" rows="auto" readonly></textarea></div>
                                </div>

                                <div class="mt-4 row">
                                    <div class="col-2 center" style="vertical-align: top;"><label>첨부파일</label></div>
                                    <div class="col-3" style="text-align: left;">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-dark">첨부파일.word</button>
                                            <button type="button" class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                                              <span class="caret"></span>
                                            </button>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="#">다운로드</a>
                                              <a class="dropdown-item" href="#">삭제</a>
                                            </div>
                                          </div>
                                    </div>
                                    <div class="col-1"></div>
                                    <div class="col-2 center" style="vertical-align: top;"><label>첨부파일</label></div>
                                    <div class="col-3" style="text-align: left;">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-dark">첨부파일.word</button>
                                            <button type="button" class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                                              <span class="caret"></span>
                                            </button>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="#">다운로드</a>
                                              <a class="dropdown-item" href="#">삭제</a>
                                            </div>
                                          </div>
                                    </div>
                                <div class="row" style="text-align: center; margin-top: 25%;">
                                    <div class="col" style="text-align: right;">
                                        <button type="button" class="btn btn-outline-dark">확인</button>
                                    </div>
                                    <div class="col" style="text-align: left;">
                                        <button type="button" class="btn btn-outline-dark">취소</button>
                                    </div>
                                </div>
                                
                                <div class="modal" id="myModal">
                                    <div class="modal-dialog">
                                    <div class="modal-content">
                                
                                        <div class="modal-header" style="text-align: center; border: 1px solid black;">
                                        <h4 class="modal-title" style="width: 100%;">이슈</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                

                                        <div class="modal-body" style="text-align: center;">
                                        #1 0001이슈발생을 정말로 삭제하시겠습니까?<br>
                                        	관리자 삭제 시 영구삭제됩니다.
                                        </div>
                                

                                        <div class="row">
                                            <div class="modal-footer col-5">
                                            <button type="button" class="btn btn-outline-dark" data-dismiss="modal">확인</button>
                                            </div>
                                            <div class="modal-footer col-4">
                                            <button type="button" class="btn btn-outline-dark" data-dismiss="modal" style="text-align: left;">취소</button>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                </div>

                                <div class="modal fade"></div>


                                <div class="modal"></div>
                            </div>
                       </form> 
                    </div>
                </div>
	
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>