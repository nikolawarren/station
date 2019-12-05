$(function(){
	
	$('#saveForm2').validate({
		rules: {
			template  :{required:true}
		},messages:{
			template :{required:"必填"},
        }
 	});
    $('.saveBtn').click(function(){
         if($('#saveForm2').valid()){
        	 location.href="/report/export?type="+$("#template2").val();  
         }else{
             alert('数据验证失败，请检查！');
         }
    });
});
