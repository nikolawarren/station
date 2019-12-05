
function refund(id){
    if(!confirm("您确定审核此维修清单吗？")){
        return false;
    }
    $.get("./processingrepair/"+id,
    		{
    	ts:new Date().getTime()
    	
    	},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "审核维修清单",
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
