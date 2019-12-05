$(function(){
	
	$('#saveForm').validate({
		rules: {
			person_number  :{required:true},
			group_date	  :{required:true},
			group_time :{required:true},
			group_departure:{required:true},
			group_destination:{required:true}
		},messages:{
			
			person_number  :{required:"必填"},
			group_date	  :{required:"必填"},
			group_time :{required:"必填"},
			group_departure:{required:"必填"},
			group_destination:{required:"必填"}
        }
 	});
    $('.saveBtn').click(function(){
         if($('#saveForm').valid()){
             $.ajax({
                 type: "POST",
                 url: "./savegroup",
                 data: {
                	   person_number:$("#person_number").val(),
                	   group_date:$("#group_date").val(),
                	   group_time:$("#group_time").val(),
                	   group_departure:$("#group_departure").val(),
                	   group_destination:$("#group_destination").val(),
             		   userid:$("#userid").val()
                 },
                 headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                 success: function (data) {
                     if (data == 1) {
                         alert("提交成功");
                         
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
