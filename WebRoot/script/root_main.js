/**
 * 
 */


function login(){
	$("#loginBtn").attr("disabled","disabled");
	$.ajax({
		url:'login_rootLogin.action',
		type:'post',
		async:true,
		data:{
			'root.username':$("#root_user").val(),
			'root.password':$("#root_psw").val()
		},
		success:function(res){
			var code = res['code'];
			switch(code){
			case -1:
				alert("密码错误!");
				break;
			case 0:
				alert("账号错误!");
				break;
			default:location.href="root-manager.html";break;
			}
			$("#loginBtn").removeAttr("disabled");
		}
	});
	return false;
	
}

function modifyPsw(){
	$.ajax({
		url:'module/modifyPsw.jsp?'+Math.random(),
		type:'get',
		async: false,
		success:function(res){
			$("#main").html(res);
			$("#modifyPswForm").submit(function(){
				var oldPsw = $("#oldPsw").val();
				var newPsw = $("#newPsw").val();
				var newPswAgain = $("#newPswAgain").val();
				if(newPsw!=newPswAgain){
					alert("两次输入的密码不一致!");
					return false;
				}
				$.ajax({
					url:'root_modifyPsw.action',
					type:'post',
					async:true,
					data:{
						'root.password':oldPsw+","+newPsw,
					},
					success:function(res){
						console.log(res);
						if(res['code']==1){
							alert("修改成功!");
						}else{
							alert("修改失败，原密码错误!");
						}
					}
					
				});
				
				return false;
			});
		},
		error:function(res){
			console.log(res);
		}
	
	});
}

//查看未注册餐厅
function getShopUnRgi(){
	$.ajax({
		url:'shop_queryAllUnRegi.action',
		type:'get',
		async:true,
		success:function(res){
			$("#main").html(res);
		},
		error:function(res){
			console.log(res);
		}
		
	});
}
//获取已注册餐厅
function getShopRig(){
	$.ajax({
		url:'shop_queryAllRegi.action',
		type:'get',
		async:true,
		success:function(res){
			$("#main").html(res);
		},
		error:function(res){
			console.log(res);
		}
		
	});
}

function getCustomers(){
	$.ajax({
		url:'customer_queryAll.action',
		type:'get',
		async:true,
		success:function(res){
			$("#main").html(res);
		}
	});
}

function updateShopStatus(e,shop_id,status){
	if(!confirm("确认该餐厅申请通过?")) return;
	var theEvent = window.event|| e;
	$.ajax({
		url:'shop_updateStatus.action',
		type:'get',
		async:false,
		data:{
			'shop.id':shop_id,
			'status':status,
		},
		success:function(res){
			alert("success");
			$(theEvent.target).attr("disabled","disabled");
			$(theEvent.target).html("已通过申请");
		},
		error:function(res){
			console.log(res);
		}
	});
	
}
