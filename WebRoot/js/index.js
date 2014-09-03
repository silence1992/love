$(function(){
	$("body").layout();
	$("body").layout("panel","east").panel({onBeforeCollapse:function(){
		return false;
	}});
	
	//登录对话框初始化
	$("#login").dialog({
		height : 200,
		width : 300,
		title : '请输入正确的信息来登录',
		closed : true,
		modal : true,
		buttons : [ {
			text : '登录',
			iconCls : 'icon-save',
			handler : function() {
				$("#loginform").form('submit', {
					url : 'user/checkUser.action',
					success : function(res) {
						var data = $.parseJSON(res);
						if (data.status == 'success') {
							$('#login').dialog('close');
							$.messager.show({
								title : '提示信息',
								msg : data.msg
							});
							setTimeout(reLoad, 1500);
						} else {
							$.messager.alert('提示信息', data.msg, 'error');
						}
					}
				});
			}
		}, {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#login').dialog('close');
			}
		} ]
	});
	
	//注册对话框初始化
	$("#regist").dialog({
		height : 400,
		width : 350,
		title : '欢迎注册',
		closed : true,
		modal : true,
		buttons : [{
					text : '提交',
					iconCls : 'icon-ok',
					handler : function() {
						$("#registform").form('submit',{
											url : 'user/regist.action',
											success : function(res) {
												var result = $.parseJSON(res);
												if(result.status == "success"){
													$.messager.show({
														title:"提示信息" ,
														msg:result.msg
													});
													$("#registform").get(0).reset();
													$('#regist').dialog('close');													
													setTimeout(loginAfterRegist,2000);
												}else{
													$("#code_pic").attr("src","getCheckCode?num="+ Math.random());
													$.messager.alert("提示信息",result.msg,"warning");
												}
											}
										});
					}
				}, {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#regist').dialog('close');
					}
				} ]
	});
	
	//注册，登录表单的校验
	$("#niname,#rpassword,#checkcode,#username,#password").validatebox({
		required:true ,
		missingMessage:"该字段不能为空"
	});
	$("#rusername").validatebox({
		required : true,
		missingMessage : '用户名不能为空',
		validType : 'midValid[6,12]'
	});
	$("#confirm").validatebox({
		validType:"pwdValid['#rpassword']" ,
		required:true ,
		missingMessage:"确认密码不能为空！"		
	});
	$("#spinner_age").numberspinner({
		required:true ,
		value:20 ,
		increment:1 ,
		width:50 ,
		missingMessage:"不能为空" ,
		min:1 ,
		max:90 ,
		onSpinUp:function(){
			$("spinner_age").val(parseInt($("spinner_age").val)+1);
		} ,
		onSpinDown:function(){
			if(parseInt($("#spinner_age").val()) > 1){
				$("spinner_age").val(parseInt($("spinner_age").val)-1);
			}			
		}
	});
	
	
});

function login() {
	$("#login").dialog('open');
}
function changeCode() {
	$("#code_pic").attr("src", "getCheckCode?num=" + Math.random());
}
function regist() {
	$("#regist").dialog('open');
}
function loginAfterRegist(){
	$("#login").dialog("open");
}

function reLoad() {
	location.reload();	
}


//注册时用户名是否存在的ajax验证
function hasUsername(){
	$("#rcheckUsername").html("");
	if($("#rusername").validatebox("isValid")){
		$.post("user/hasUsername.action" ,{"user.username":$("#rusername").val()},function(res){
			if(res == "have"){
				$("#rcheckUsername").html("该用户已存在");
			}else{
				$("#rcheckUsername").html("可以使用");
			}
		},'text');
	}	
}

function logout(){
	$.messager.confirm("提示信息","确定要退出么?",function(r){
		if(r){
			$.post("user/logout.action",function(res){
				reLoad();
			},"text");
		}		
	});	
}