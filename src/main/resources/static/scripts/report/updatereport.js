$(function(){
	$('#saveForm').validate({
		rules: {
            file       :{required:true}
            
		},messages:{
            file :{required:"必填"}
           
        }
 	});
    $('.saveBtn').click(function(){
    	var formData = new FormData($('#saveForm')[0]);
    	var id=$("#report_id").val();
    	 if($('#saveForm').valid()){
             $.ajax({
                 type: "POST",
                 url: "/report/onupdate/4"+id,
                 data: formData,
                 
             	cache: false,
                 processData: false,
                 contentType: false,
                 success: function (data) {
                     if (data == 1) {
                         alert("上传成功");
                         closeDialog();
                     } else {
                         alert("上传失败");
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
