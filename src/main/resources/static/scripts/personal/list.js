$(function () {
	
    $('#searchTicket').click(function(){
        searchTicket();   
    });
    

});


function searchTicket(){
	 $.get('./listshow?t='+new Date().getTime(),{
		 ticket_date:$("#ticket_date").val(),
		 ticket_departure:$("#ticket_departure").val(),
		 ticket_destination:$("#ticket_destination").val()
		 
	    },function(data){	        
	        fillData(data)
	    });
}

function fillData(data){
    var $list = $('#tbodyContent').empty();
    $.each(data,function(k,v){
        var html = "" ;
        html += '<tr> ' +
        '<td>'+ (v.ticket_time==null?'':v.ticket_time) +'</td>' +
        '<td>'+ (v.trip==null?'':v.trip) +'</td>' ;
        
        html += '<td><a class="c-50a73f mlr-6" href="javascript:void(0)" onclick="buy(\''+v.ticket_time+'\')">购买</a></td>' ;
        html +='</tr>' ;

        $list.append($(html));
    });
}

function buy(ticket_time){
    if(!confirm("您确定购买此车次的车票吗？")){
        return false;
    }
    $.get("./create/",{
    	ts:new Date().getTime(),
    	ticket_date:$("#ticket_date").val(),
		ticket_departure:$("#ticket_departure").val(),
		ticket_destination:$("#ticket_destination").val(),
		ticket_time:ticket_time,
		userid:$("#userid").val()
    	},function(data){
        if(data==1){
            alert("购票成功");
            
        }else{
            alert("购票失败");
        }
    });
}