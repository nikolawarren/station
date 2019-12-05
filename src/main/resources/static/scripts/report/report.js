$(function(){
	$('#searchBtn').click(function(){
		location.href="/report/Query/report?"+$("#queryForm").serialize();
		 /*
		  $.ajax({
               type: "POST",
               url: "./Query/report",
               data: $("#queryForm").serialize(),
               /*
     		    
        	   report_id:$("#report_id").val(),
        	   type:$("#template").val(),
        	   report_name:$("#report_name").val()
           }  
               headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
               success: function (data) {
            	   location.href="/report/Query/report?"+$("#queryForm").serialize();
               }
           });
           */
          
	}) 
	

});	
function refund(id){
    if(!confirm("您确定下载此财务报表吗？")){
        return false;
    }
    location.href="/report/download?report_id="+id; 
}

function ondelete(id){
    if(!confirm("您确定删除此财务报表吗？")){
        return false;
    }
    $.get('./ondelete?t='+new Date().getTime(),{
		 
		 report_id:id
		 
	    },function(data){	        
	    	if (data == 1) {
                alert("提交成功");
                location.reload();
               
            } else {
                alert("提交失败");
            }
	    });
}