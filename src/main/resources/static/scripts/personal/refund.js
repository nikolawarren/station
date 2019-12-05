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
        '<td>'+ (v.ticket_id==null?'':v.ticket_id) +'</td>' +
        '<td>'+ (v.ticket_date==null?'':v.ticket_date) +'</td>' +
        '<td>'+ (v.ticket_time==null?'':v.ticket_time) +'</td>' +
        '<td>'+ (v.ticket_departure==null?'':v.ticket_departure) +'</td>' +
        '<td>'+ (v.ticket_destination==null?'':v.ticket_destination) +'</td>' ;
        
        html += '<td><a class="c-50a73f mlr-6" href="javascript:void(0)" onclick="refund(\''+v.ticket_id+'\')">退票</a></td>' ;
        html +='</tr>' ;

        $list.append($(html));
    });
}

function refund(id){
    if(!confirm("您确定退掉此车次的车票吗？")){
        return false;
    }
    $.get("./addrefund/"+id,
    		{
    	ts:new Date().getTime(),
    	},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "填写退票申请单",
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
