/**
 * 
 */


function confirmOrderBack(e,customer_id,order_id){
	var theEvent = window.event|| e;
	if(!confirm("确认退单?"))
		return;
	$.ajax({
		type:'get',
		url:'ordeer_delete.action',
		data:{
			'ordeer.id':order_id,
		},
		success:function(res){
			var code = res['code'];
			if(code==1){
				alert("已退单!");
				$(theEvent.target).closest("tr").remove();
			}
			var obj = {
				to:customer_id,
				toType:1,
				message:"商家已确认您的退单!",
			};
			sendMessage(obj);
		}
	});
}



function confirmOrder(e,order_id,status){
    var theEvent = window.event|| e;
	$("#comment").click(function(){
		if(!confirm("确认送达？"))
			return;
		$.ajax({
            type:'get',
            url:'ordeer_updateStatus.action',
            async:false,
            data:{
                'ordeer.id':order_id,
                'ordeer.status':status
            },
            success:function(res){
                var is = res['code'];
                alert(res['msg']);
                if(is==1){
                    $(theEvent.target).closest("tr").remove();
                    $.ajax({
						//添加评论
					});
				}
            }
		});
	});
	$("#commentModal").modal();

}

function updateOrderStatus(e,order_id,status){
	var theEvent = window.event|| e;
	if(!confirm("确认?"))
		return;
	var is = 0;
	$.ajax({
		type:'get',
		url:'ordeer_updateStatus.action',
		async:false,
		data:{
			'ordeer.id':order_id,
			'ordeer.status':status
		},
		success:function(res){
			is = res['code'];
			alert(res['msg']);
		}
	});
	
	if(is==1){
		$(theEvent.target).closest("tr").remove();
	}
}

function getObjectURL(file){
	 var url = null ;   
     if (window.createObjectURL!=undefined) {  
         url = window.createObjectURL(file) ;  
     } else if (window.URL!=undefined) {   
         url = window.URL.createObjectURL(file) ;  
     } else if (window.webkitURL!=undefined) {   
         url = window.webkitURL.createObjectURL(file) ;  
     }  
     return url ;  
}