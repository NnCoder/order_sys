/**
 * 
 */
function searchFood(){
    var key=$("#search_food").val();
    $.ajax({
        type:'get',
        url:'food_queryByKey.action',
        data:{
            'msg':key,
            'shop_id':-1,
        },
        success:function(res){
            $("##main").html(res);
        },
        error:function(res){
            console.log(res);
        }
    });
}

function initTrunover(recentDay){
	var day = recentDay;
    $.ajax({
        type:'get',
        url:'jsp/shop/shop_queryTurnover.html?t='+Math.random(),
        success:function(res){
            $("#main").html(res);
            $.ajax({
                type:'post',
                url:'shop_queryTurnover.action',
                data:{
                    'recentDay':day
                },
                success:function(res){

                    $("#turnoverTable").html(res);
                },
                error:function(res){
                    console.log(res);
                }
            });
        },
        error:function(res){
            console.log(res);
        }
    });
}

function turnover(type){
	var day = $("#daySelect option:selected").val();
	console.log(day);
	$.ajax({
		type:'post',
		url:'shop_queryTurnover.action',
		data:{
			'recentDay':day
		},
		success:function(res){
			$("#turnoverTable").html(res);
		},
		error:function(res){
			console.log(res);
		}
	});
}

function response(e,comment_id){
	var theEvent = Window.Event||e;
	$("#responseBtn").one("click",function(){
		$.ajax({
			type:'post',
			url:'comment_response.action',
			data:{
				'comment.id':comment_id,
				'comment.response':$("#content").val(),
			},
			success:function(res){
				console.log(res);
				$(theEvent.target).attr("disabled","disabled");
				$(theEvent.target).html("已回复");
				$("#closeResponseBtn").click();
			}

		});
	});
	$("#responseModal").modal();
}

function getComment(){
	$.ajax({
		type:'get',
		url:'comment_queryForShop.action',
		success:function(res){
			$("#main").html(res);
		},
		error:function(res){
			console.log(res);
		}
	});
}



function login() {
	$.ajax(
		{
			type : "post",
			url : "login_shopLogin.action",
			data : {
				'shop.phoneNum' : $("#signin-phone").val(),
				'shop.password' : $("#signin-password").val()
			},
			success : function(res) {
				console.log(res);
				var status = res['status'];
				switch (status) {
				case 0:
					alert("账号不存在，请先申请账号！");
					break;
				case -1:
					alert("密码错误!");
					break;
				case -2:
					alert("您的账号尚未通过审核，请耐心等待管理员审核通过!");
					break;
				default:
					location.href = "shopManager.html";
				}
			}
		});
	return false;
}

function logout(){
	$.ajax(
		{
			type:'get',
			url:'login_shopLogout.action',
			success:function(){
				location.href="shop-login.html";
			}
		}
			
	);
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
					url:'shop_modifyPsw.action',
					type:'get',
					data:{
						'shop.password':$oldPsw+","+$newPsw,
					},
					async:true,
					success:function(res){
						if(res['code']==1){
							alert("修改成功,请牢记您的新密码!");
							$("#oldPsw").val("");
							$("#newPsw").val("");
							$("#newPswAgain").val("");
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

function checkPhoneNum() {
	$.ajax(
		{
			type : 'get',
			url : 'shop_checkPhoneNum.action',
			data : {
				'shop.phoneNum' : $("#register-phoneNum").val()
			},
			success : function(res) {
				var code = res['code'];
				if (code == 0) {
					$("#phoneCheck").html("<span style='color:red;'><i class='fa fa-times-circle'></i>号码已被申请</span>");
				}
				else {
					$("#phoneCheck").html("<span style='color:green;'><i class='fa fa-check-circle'></i>可用</span>");
				}
			},
			error : function(res) {
				$("#error").html(res);
			}
		}
	);
}

function get() {
	$.ajax({
		type : 'get',
		url : 'shop_query.action',
		success : function(res) {
			$("#shopImg").attr("src", res['shop_imgSrc']);
			$("#shopName").html(res['shop_name']);
			openTheGay(1);
            if('WebSocket' in window){
                websocket = new WebSocket("ws://localhost:8080/informSocket/"+res['shop_id']+"/2");
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
		error : function(res) {
			$("#main").html(res);

		}
	});
}
function openTheGay(status){
	$.ajax({
		type:'post',
		url:'shop_updateOpen.action',
		data:{
			'shop.open':status,
		},
		success:function(res){
			switch(status){
				case 0:$("#openMsg").html("已关店");break;
				case 1:$("#openMsg").html("营业中");break;
			}
		}
	});
}

function informCustomer(customer_id,type){
	var message;
	if(type==0){
		message = "您的外卖订单已完成,外卖小哥配送中,请留意您的手机短信或电话!"
	}else{
		message = "您的店内订单已完成,请及时到店内领取!"
	}
	var obj = {
		to:customer_id,
		toType:1,
		message:message
	}
	sendMessage(obj);
}

function register() {
	var data = new FormData($("#registerForm")[0]);
	$.ajax({
		type : 'post',
		url : 'shop_register.action',
		async : true,
		cache : false,
		processData : false, // 
		contentType : false, //?????л??? 
		data : data,
		success : function(res) {
			var code = res['code'];
			if (code == 0) {
				alert("注册失败,请与系统管理员联系!");
			}
			else {
				alert("注册成功,请耐心等待管理员审核!");
				$("#closeRegisterBtn").click();
			}

		},
		error : function(res) {
			$("#error").html(res);
		}
	});
	return false;
}

function updateShop(){
	var data = new FormData($("#shopUpdateForm")[0]);
	$.ajax(
		{
			type:'post',
			url:'shop_update.action',
			async : false,
			cache : false,
			processData : false, // 
			contentType : false, //?????л??? 
			data : data,
			success : function(res) {
				console.log(res);
				$("#closeShopUpdateBtn").click();
				get();
			},
			error : function(res) {
				
			}
		}
	)
	return false;
}

function shopUpdateInput(){
	$.ajax({
		type : 'get',
		url : 'shop_updateInput.action',
		success : function(res) {
			$("#shopUpdateModalDiv").html(res);
			$("#shopUpdateModal").modal();
		},
		error : function() {
			$("#container").html(res);
		}
	}
	);
}

function getFoods() {
	$.ajax({
		type : 'get',
		url : 'shop_foods.action',
		success : function(res) {
			$("#main").html(res);
		},
		error : function() {
			$("#container").html(res);
		}
	});
}

function updateFood(){
	var data = new FormData($("#foodUpdateForm")[0]);
	$.ajax(
		{
			type:'post',
			url:'food_update.action',
			cache : false,
			processData : false, // 
			contentType : false, //?????л??? 
			data : data,
			success : function() {
				alert("更新成功!");
				getFoods();
			},
			error : function(res) {
				alert(res);
			}
		}
	)
	return false;
}

function  foodUpdateInput(food_id){
	$.ajax(
		{
			type:'get',
			url:'food_updateInput.action',
			data:{
				'food.id':food_id,
			},
			success:function(res){
				$("#foodUpdateModalDiv").html(res);
				$("#foodUpdateModal").modal();
			},
			error:function(res){
				$("#main").html(res);
			}
		}
			
	);
}


function deleteFood(e,food_id){
	var theEvent = window.event||e;
	if(!confirm("确认删除该食物?"))
		return;
	$.ajax(
		{
			type:'get',
			url:'food_delete.action',
			data:{
				'food.id':food_id,
			},
			async:true,
			success:function(res){
				var code = res['code'];
				if(code==1){
					alert("删除成功");
					$(theEvent.target).attr("disabled","disabled");
					$(theEvent.target).html("已删除");
				}else{
					alert("删除失败,请与系统管理员联系!");
				}
			}
		}
	);
}
function accessOrder(e,customer_id,order_id){
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
            'ordeer.status':1
        },
        success:function(res){
            is = res['code'];
            alert(res['msg']);
            var obj = {
            	to:customer_id,
				toType:1,
				message:"商家已接单!",
			}
            sendMessage(obj);
        }
    });

    if(is==1){
        $(theEvent.target).closest("tr").remove();
    }
}

function getOrdersAccess(type){
	$.ajax({
		type:'get',
		url:'shop_ordersAccess.action',
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
		url:'shop_ordersBack.action',
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
		url:'shop_ordersComplete.action',
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
			url:'shop_ordersNot.action',
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

function addFood() {
	var data = new FormData($("#foodForm")[0]);
	$.ajax(
		{
			type : 'post',
			url : 'food_add.action',
			async : true,
			cache : false,
			processData : false, // 
			contentType : false, //?????л??? 
			data : data,
			success : function(res) {
				$("#foodTable").append(res);
				$("#closeFoodBtn").click();
			},
			error : function(res) {}
		});
	return false;
}