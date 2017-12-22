/**
 * 
 */
function addShopcart(food_id,shop_id){
	$.ajax({
		type:'post',
		url:'shoppingCart_add.action',
		data:{
			'shoppingCart.shop.id':shop_id,
			'shoppingCart.food.id':food_id,
		},
		success:function(res){
			alert("已加入购物车");
		},
		error:function(res){
			console.log(res);
		}
	});
}

function changeComment(){
	var s = $("#selectComment option:selected").val();
	var $content = $("#content");
	if(s=="好评"){
		$content.text("好吃好吃~");
	}else if(s=="满意"){
		$content.text("还行还行~");
	}else{
		$content.text("负分滚粗!");
	}
}

function getComment(){
	$.ajax({
		type:'get',
		url:'comment_queryForCustomer.action',
		success:function(res){
			$("#main").html(res);
		},
		error:function(res){
			console.log(res);
		}
	});
}

function comment(e,order_id,food_id){
	var theEvent = Window.Event||e;
	$("#commentBtn").one('click',function(){
        $.ajax({
            type:'post',
            url:'comment_add.action',
            data:{
                'comment.food.id':food_id,
                'comment.comment':$("#selectComment option:selected").val()+":"+$("#content").val(),
            },
            success:function(res){

                alert("感谢您的评价~");
                $(theEvent.target).attr("disabled","disabled");
                $(theEvent.target).html("已评价")
                $("#closeCommentBtn").click();
                $.ajax({
                    type:'get',
                    url:'ordeer_updateStatus.action',
                    async:false,
                    data:{
                        'ordeer.id':order_id,
                        'ordeer.status':100
                    },
                    success:function(res){
                        console.log(res);
                    }
                });
            },
            error:function(res){
                console.log(res);
            }
        });
	});
	$("#commentModal").modal();
}


function removeShoppingCart(e,shoppingCart_id){
	var theEvent = Window.Event||e;
	$.ajax({
		type:'get',
		url:'shoppingCart_remove.action',
		data:{
			'shoppingCart.id':shoppingCart_id,
		},
		success:function(res){
			$(theEvent.target).attr("disabled","disabled");
			$(theEvent.target).html("已移除");
		},
		error:function(res){
			console.log(res);
		}
	});
}

function shoppingCart(){
	$.ajax({
		type:'get',
		url:'shoppingCart_query.action',
		success:function(res){
			$("#main").html(res);
		},
		error:function(res){
			console.log(res);
		}
	});
}

function searchFood(){
    var key=$("#search_food").val();
    $.ajax({
        type:'post',
        url:'food_queryByKey.action',
        data:{
            'msg':key,
            'shop_id':-1,
        },
        success:function(res){
            $("#main").html(res);
        },
        error:function(res){
            console.log(res);
        }
    });
}

function searchShop(){
    var key = $("#search_shop").val();
    $.ajax(
        {
            type:'post',
            url:'shop_queryByKey.action',
            data:{
                'msg':key,
            },
            success:function(res){
                $("#main").html(res);
            },
            error:function(res){
                console.log(res);
            }
        }
    );
}


function login() {
	//alert($("#signin-phone").val() + "," + $("#signin-password").val());
	$.ajax(
		{
			type : "post",
			url : "login_customerLogin.action",
			data : {
				'customer.phoneNum' : $("#signin-phone").val(),
				'customer.password' : $("#signin-password").val()
			},
			success : function(res) {
				console.log(res);
				var status = res['status'];
				switch (status) {
				case 0:
					alert("账号不存在,请先申请账号!");
					break;
				case -1:
					alert("密码错误!");
					break;
				default:
					location.href = "customer.html";
				}
			}
		});
	return false;
}

function logout(){
	$.ajax({
		url:'login_customerLogout.action',
		type:'get',
		async:true,
		success:function(){
			location.href = "customer-login.html";
		}
	});
}

function getShops() {
	$.ajax({
		type : 'get',
		url : 'shop_queryAll.action',
		data:{
			status:1,
		},
		success : function(res) {
			$("#main").html(res);
		},
		error : function(res) {
			$("#main").html(res);
		}
	});
}


function register() {
	$.ajax({
		type : 'post',
		url : 'customer_register.action',
		async : true,
		data : 
		{
			'customer.name':$("#customer_name").val(),
			'customer.phoneNum':$("#customer_phoneNum").val(),
			'customer.password':$("#customer_password").val(),
			'customer.address':$("#customer_address").val(),
		},
		success : function(res) {
			console.log(res);
			var code = res['code'];
			if (code == 0) {
				alert("注册失败!请与系统管理员联系!");
			}
			else {
				alert("注册成功!");
				$("#closeRegisterBtn").click();
			}

		},
		error : function(res) {
			$("#error").html(res);
		}
	});
	return false;
}

function get(){
	$.ajax({
		type:'get',
		url:'customer_query.action',
		async:true,
		success:function(res){
			console.log(res);
			$("#name").html(res['customer_name']);
            if('WebSocket' in window){
                websocket = new WebSocket("ws://localhost:8080/informSocket/"+res['customer_id']+"/1");
            }else{
                alert("您的浏览器不支持websocket,请使用支持HTML5的浏览器,如谷歌浏览器");
            }
            websocket.onopen = function(){
                console.log("连接成功!");
            }

            websocket.onmessage = function(message){
                $("#msgInform").attr("data-content",message.data);
                $("#msgInform").popover('show');
            }

            websocket.onclose = function(){}

            websocket.onerror = function(){
                console.log("连接错误!");
            }
            window.onbeforeunload = function(){
                websocket.close();
            }

        },
	});
}

function customerUpdate(){
	$.ajax({
		url:'customer_updateInput.action',
		type:'get',
		async:true,
		success:function(res){
			$("#customerUpdateModalDiv").html(res);
			$("#customerUpdateModal").modal();
			$("#customerUpdateForm").submit(function(){
				var $name = $("#customer_name").val();
				var $address = $("customer_address").val();
				if($name.toString().trim()==""){
					alert("请输入昵称!");
					return false;
				}
				$.ajax({
					url:'customer_update.action',
					type:'get',
					async:true,
					data:{
						'customer.name':$name,
						'customer.address':$address,
					},
					success:function(res){
						if(res['code']==1){
							alert("修改成功!");
							$("#name").html($name);
						}else{
							alert("修改失败,请重新登录后再尝试修改!");
						}
					},
				});
				return false;
			});
		}
	});
}

function modifyPsw(){
	$.ajax({
		url:'module/modifyPsw.jsp?t='+Math.random(),
		type:'get',
		async:true,
		success:function(res){
			$("#main").html(res);
			$("#modifyPswForm").submit(function(){
				
				var $oldPsw = $("#oldPsw").val();
				var $newPsw = $("#newPsw").val();
				var $newPswAgain = $("#newPswAgain").val();
				
				if($newPsw!=$newPswAgain){
					alert("两次输入的密码不一致!");
					return false;
				}
				
				$.ajax({
					url:'customer_modifyPsw.action',
					type:'get',
					data:{
						'customer.password':$oldPsw+","+$newPsw,
					},
					async:true,
					success:function(res){
						if(res['code']==1){
							alert("修改密码成功");
						}else{
							alert("修改失败,原密码错误!");
						}
					}
				});
				
				return false;
			});
		},
	});
}

function openShop(shop_id) {
	$.ajax({
		type : 'get',
		url : 'shop_queryForCustomer.action',
		data : {
			'shop.id' : shop_id
		},
		async : true,
		success : function(res) {
			$("#main").html(res);
		}
	});

}

function backOrder(e,shop_id,order_id,type) {
    var theEvent = window.event || e;
    if (!confirm("确认退单?"))
        return;
    var is = 0;
    $.ajax({
        type: 'get',
        url: 'ordeer_updateStatus.action',
        async: false,
        data: {
            'ordeer.id': order_id,
            'ordeer.status': -1
        },
        success: function (res) {
            is = res['code'];
            alert(res['msg']);
            var obj = {
                to: shop_id,
                toType: 2,
                message: "有新的退单,请及时处理!",
            }
            sendMessage(obj);
            if(is==1){
                $(theEvent.target).closest("tr").remove();
            }
        }
    });
}
/**
 * ??????
 */
function placeOrder(food_id,shop_id) {
	var remarks = $("#orderRemarks").val();
	var type = $("input[name='orderType']:checked").val();
	if(type==1){
		remarks = $("#insideOrderType").val()+": "+remarks;
	}
	$.ajax({
		type : 'post',
		url : 'ordeer_placeOrder.action',
		data : {
			'ordeer.food.id' : food_id,
			'ordeer.shop.id' : shop_id,
			'ordeer.type':type,
			'ordeer.remarks':remarks,
		},
		async : true,
		success : function(res) {
			if (res['status'] == 1) {
				alert("下单成功,请至未接单页面查看");
				$("#closeFoodToOrderBtn").click();
				var message;
				if(type==0){
					message = "有新的外卖订单,请及时查看!";
				}else{
					message = "有新的店内订单,请及时查看!";
				}
				var obj = {
					to:shop_id,
					toType:2,
					message:"有新订单,请及时查看!",
				}
				sendMessage(obj);
			}
		}
	});
	return false;
}

function foodToOrder(food_id,shop_id){
	$.ajax({
		type:'get',
		url:'food_toOrder.action',
		data:{
			'food.id':food_id,
			'msg':shop_id,
		},
		async:true,
		success:function(res){
			$("#foodToOrderModalDiv").html(res);
			$("#foodToOrderModal").modal();
		}
	});
}


function getOrdersAccess(type){
	$.ajax({
		type:'get',
		url:'customer_ordersAccess.action',
		data:{
			order_type:type,
		},
		success:function(res){
			$("#main").html(res);
		},
		error:function(res){
			$("#main").html(res);
		}
	});
}

function getOrdersBack(type){
	$.ajax({
		type:'get',
		url:'customer_ordersBack.action',
		data:{
			order_type:type,
		},
		success:function(res){
			$("#main").html(res);
		},
		error:function(res){
			$("#main").html(res);
		}
	});
}

function getOrdersComplete(type){
	$.ajax({
		type:'get',
		url:'customer_ordersComplete.action',
		data:{
			order_type:type,
		},
		success:function(res){
			$("#main").html(res);
		},
		error:function(res){
			$("#main").html(res);
		}
	});
}

function getOrdersNot(type){
	$.ajax(
		{
			type:'get',
			url:'customer_ordersNot.action',
			data:{
				order_type:type,
			},
			success:function(res){
				$("#main").html(res);
			},
			error:function(res){
				$("#main").html(res);
			}
		}
	);
}
