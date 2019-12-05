$(function(){
	
	$('#saveForm').validate({
		rules: {
			vehicle  :{required:true},
			checker  :{required:true}
		},messages:{
			vehicle :{required:"必填"},
			checker :{required:"必填"}
        }
 	});
    $('.saveBtn').click(function(){
         if($('#saveForm').valid()){
             $.ajax({
                 type: "POST",
                 url: "./onarrangement",
                 data: {
                	 vehicle:$("#vehicle").val(),
                	 checker:$("#checker").val(),
             		   userid:$("#userid").val(),
             		  group_id:$("#group_id").val()
             		 
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
