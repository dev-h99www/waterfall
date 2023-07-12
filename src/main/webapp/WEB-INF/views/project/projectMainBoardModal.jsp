<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

/* 모달 */
.modal-content {
   width: 590px;
   height: 650px;
   padding: 30px;
}

#title-write {
   width: 440px;
}
#read-title {
   width: 440px;
}
.my-modal-body {
   margin-left: 0px;
}

.my-textarea-div {
   width: 440px;
   height: 180px;
}

#my-textarea {
   display: block;
   width: 100%;
   height: 100%;
}

#read-content {
    display: block;
    width: 117%;
    height: 169px;
}

.my-modal-footer button {
   color: #000;
   background: none;
   padding: 5px 25px;
}

.my-modal-footer button:first-child {
   margin-right: 306px;
}

/* 서브모달 */
.my-modal-message {
   line-height: 45px;
}

/* 게시글 조회 모달 */
.my-modal-footer-read {
   text-align: center;
}

.my-modal-footer-read button {
   color: #000;
   background: none;
   padding: 5px 25px;
}
#downloadZone {
	width: 100px;
}
#downloadZone #read-originalName {
  width:300px;
  overflow:hidden
  white-space:nowrap
  text-overflow: ellipsis;
}
</style>

</head>
<body>
<!-- 프로젝트 no, memberNo  -->
        <div class="modal fade" id="project-main-board-modal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                   <div class="modal-content" style="top: 172px" >
                        <div class="my-modal-header mb-4">
                            <h2 id="board-category-name"></h2><br>
                            <label class="me-2" for="title-write">제목</label>
                            <input style="width:90%" type="text" id="read-title" name="title">
                        </div>
						<div>
							<label class="mb-4">작성자</label>
							<input type="text" style="width:26%;margin-left:7%" id="read-membername" readonly>
							<label style="margin-left:28%"class="mb-4">조회수</label>
							<input type="text" style="width:15%;margin-left:2%" id="read-boardcount" readonly>
						</div>
                        <div class="my-modal-body" style="width:150px">
                            <div class="my-textarea-div mb-3">
                                <textarea name="content" id="read-content"></textarea>
                            </div>
                        </div>
	               	<div class="my-modal-upload mb-4" id="downloadZone" style="overflow-y:auto;overflow-x:hidden; width:100%; height:150px;">
	            		</div>
                        <div class="my-modal-footer-read">
                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">돌아가기</button>
                        </div>
                    </div>
            </div>
        </div>
        <!-- //게시글 조회 모달  -->


</body>
</html>