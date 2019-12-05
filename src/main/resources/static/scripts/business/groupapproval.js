
function refund(id){
    if(!confirm("您确定审批该包车申请吗？")){
        return false;
    }
    $.get("./approval/"+id,
    		{
    	ts:new Date().getTime()
    	
    	},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "审批包车申请单",
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
