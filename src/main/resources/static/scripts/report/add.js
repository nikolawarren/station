$(function () {
	
    $('#reportselect').click(function(){
        refund();   
    });
    
    $('#saveForm').validate({
		rules: {
			
			 file:{required:true}
		},messages:{
			 file:{required:"必填"}
        }
 	});
    $('#reportupload').click(function(){
    	var formData = new FormData($('#saveForm')[0]);
    	
        if($('#saveForm').valid()){
            $.ajax({
                type: "POST",
                url: "./addreport/"+$("#template").val(),
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

var artdialog ;
function refund(){
    if(!confirm("您确定导出报表模板吗？")){
        return false;
    }
    $.get("./selecttemplates",
    		{
    	ts:new Date().getTime(),
    	},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "选择报表模板",
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
}