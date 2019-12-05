$(function(){
	
	$('#saveForm').validate({
		rules: {
			result  :{required:true}
		},messages:{
			result :{required:"必填"},
        }
 	});
    $('.saveBtn').click(function(){
         if($('#saveForm').valid()){
             $.ajax({
                 type: "POST",
                 url: "./editrefund",
                 data: {result:$("#result").val(),
                 	   
             		   userid:$("#userid").val(),
             		  refund_id:$("#refund_id").val(),
             		  type:$("#type").val()
             			  },
             		   
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
