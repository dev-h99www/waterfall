var close = document.getElementsByClassName("closebtn");
var i;

/* 알림에서 x 버튼 누를 시 작동하는 버튼 */
for (i = 0; i < close.length; i++) {
  close[i].onclick = function(){
    var div = this.parentElement;
    div.style.opacity = "0";
    setTimeout(function(){ div.style.display = "none"; }, 600);
  }
}  

  $(document).ready(function(){
	  
	  $.ajax({
		  url : "/waterfall/issue/notification",
		  type : "get",
	      success : function(data, textStatus, xhr) {
			  
			  console.log(data);
			  console.log(Object.entries(data));
			  
			  const notificationList = JSON.parse(data.notificationList);
			  console.log(notificationList);
			  const count = JSON.parse(data.count);
			  console.log(count);
			  
			  $("#badge").text(count);
			  
			  if(notificationList.length > 0) {
				  
				  $("#liZone").empty();
				  for(let i = 0; i < notificationList.length; i++) {
					  
					  console.log(notificationList[i].issueUpdatedContent);
					  content = notificationList[i].issueUpdatedContent;
					  $liTag = "<li><div id='note' class='dropdown-item note-content'>" + content  + "<span class='closebtn' value='" + notificationList[i].issueHistoryNo + "'>&times;</span></div></li>"
							  $("#liZone").append($liTag);
				  }
				  
			  }
			  
	      },
	      error:function(data) {
			  console.log(data);
		  }
	  });
  });
  
  $(document).on("click", ".closebtn", function(){
	  issueHistoryNo = $(this).attr("value");
	  console.log(issueHistoryNo)
	  
	  $.ajax({
		  url : "/waterfall/issue/notification",
		  type : "get",
		  data : { issueHistoryNo : issueHistoryNo },
	      success : function(data, textStatus, xhr) {
			  
			  const notificationList = JSON.parse(data.notificationList);
			  console.log(notificationList);
			  const count = JSON.parse(data.count);
			  console.log(count);
			  
			  $("#badge").text(count);
			  
			  $("#liZone").empty();
			  for(let i = 0; i < notificationList.length; i++) {
				  
				  console.log(notificationList[i].issueUpdatedContent);
				  content = notificationList[i].issueUpdatedContent;
				  $liTag = "<li><div id='note' class='dropdown-item note-content'>" + content  + "<span class='closebtn' value='" + notificationList[i].issueHistoryNo + "'>&times;</span></div></li>"
				  $("#liZone").append($liTag);
			  }
			  
		  },
	      error:function(data) {
			  console.log(data);
		  }
	  });
  });
  
 

  
  


