
function refund(id,type){
    if(!confirm("您确定处理此车票的退票申请吗？")){
        return false;
    }
    $.get("./processingrefund/"+id,
    		{
    	ts:new Date().getTime(),
    	type:type
    	},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "处理退票申请单",
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
