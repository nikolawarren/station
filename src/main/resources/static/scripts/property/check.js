
function refund(id){
    if(!confirm("您确定进行该安排的检查清单的填写吗？")){
        return false;
    }
    $.get("./processingcheck/"+id,
    		{
    	ts:new Date().getTime()
    	
    	},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "填写检查清单",
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
