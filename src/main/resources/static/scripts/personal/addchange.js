$(function(){
	
	$('#saveForm').validate({
		rules: {
			information  :{required:true},
			ticket_time	:{required:true},
			 ticket_departure :{required:true},
			 ticket_destination:{required:true}
		},messages:{
			information :{required:"必填"},
			ticket_time	:{required:"必填"},
			 ticket_departure :{required:"必填"},
			 ticket_destination:{required:"必填"}
        }
 	});
    $('.saveBtn').click(function(){
         if($('#saveForm').valid()){
             $.ajax({
                 type: "POST",
                 url: "./savechange",
                 data: {information:$("#information").val(),
                	 ticket_time:$("#ticket_time").val(),
                	 ticket_departure:$("#ticket_departure").val(),
                	 ticket_destination:$("#ticket_destination").val(),
             		 userid:$("#userid").val(),
             		 ticket_id:$("#ticket_id").val()},
             		   
                 headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                 success: function (data) {
                     if (data == 1) {
                         alert("提交成功");
                         closeDialog();
                     } else {
                         alert("提交失败");
                     }
                 },
                 error:function(data){
                     var e;
                     $.each(data,function(v){
                         e += v + " ";
                     });
                     alert(e);
                 }
             });
         }else{
             alert('数据验证失败，请检查！');
         }
    });
});
