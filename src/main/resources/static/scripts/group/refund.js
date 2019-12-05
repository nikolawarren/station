$(function () {
	
	searchTicketEffect();
    

});

function searchTicketEffect(){
	 $.get('./refundshow?t='+new Date().getTime(),{
		 userid:$("#userid").val()
	    },function(data){	        
	        fillData(data)
	    });
}

function fillData(data){
    var $list = $('#tbodyContent').empty();
    $.each(data,function(k,v){
        var html = "" ;
        html += '<tr> ' +
        '<td>'+ (v.group_id==null?'':v.group_id) +'</td>' +
        '<td>'+ (v.group_date==null?'':v.group_date) +'</td>' +
        '<td>'+ (v.group_time==null?'':v.group_time) +'</td>' +
        '<td>'+ (v.group_departure==null?'':v.group_departure) +'</td>' +
        '<td>'+ (v.group_destination==null?'':v.group_destination) +'</td>' ;
        
        html += '<td><a class="c-50a73f mlr-6" href="javascript:void(0)" onclick="refund(\''+v.group_id+'\')">退票</a></td>' ;
        html +='</tr>' ;

        $list.append($(html));
    });
}

function refund(id){
    if(!confirm("您确定退掉此时间的包车订单吗？")){
        return false;
    }
    $.get("./addrefund/"+id,
    		{
    	ts:new Date().getTime(),
    	},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "填写包车退票申请单",
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
