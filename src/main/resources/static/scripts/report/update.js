$(function(){
	$('#searchBtn').click(function(){
		location.href="/report/Query/update?"+$("#queryForm").serialize();
		 /*
		  $.ajax({
               type: "POST",
               url: "./Query/update",
               data: $("#queryForm").serialize(),
              
     		    
        	   report_id:$("#report_id").val(),
        	   type:$("#template").val(),
        	   report_name:$("#report_name").val()
           }  
               headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
               success: function (data) {
            	   location.href="/report/Query/update?"+$("#queryForm").serialize();
               }
           });
           */
          
	}) 
	

});	
function refund(id){
    if(!confirm("您确定更新此财务报表吗？")){
        return false;
    }
    $.get("/report/update/"+id,
    		{
    	ts:new Date().getTime(),
    	},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "选择上传的报表",
            width:'750px',
            height: 'auto',
            left: '50%',
            top: '50%',
            content:data,
            esc: true,
            init: function(){
                artdialog = this;
            },
            close: function(){
                artdialog = null;
            }
        });
    }); 
}

function closeDialog() {
    artdialog.close();
    location.reload();
}
