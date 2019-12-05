$(function(){
	
	$('#saveForm').validate({
		rules: {
			passengerchecker  :{required:true},
			entrance	  :{required:true}
			
			
		},messages:{
			
			passengerchecker  :{required:"必填"},
			entrance	  :{required:"必填"}
			
			
        }
 	});
    $('.saveBtn').click(function(){
         if($('#saveForm').valid()){
             $.ajax({
                 type: "POST",
                 url: "./savepassengerarrangement",
                 data: {
                	 checkusers_id:$("#passengerchecker").val(),
                	 entrance:$("#entrance").val(),
                	 
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
