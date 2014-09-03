<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>   
  </head>
  
  <body>
    <script type="text/javascript">
    	
    	var taskId;
    	$(function(){
    		$("#friend_self").panel({
    			width:290 ,
    			height:140 ,
    			title:"我的信息" ,
    			iconCls:"icon-tip" ,
    	//		fit:true ,
    			tools:[{
    				iconCls:'icon-edit',
    				handler:function(){
    					$("#friend_changeMsg").dialog('open');
    //					$('#cniname').val($("#friend_nickName").text());
    				}
    			}]
    		});
    		$("#friend_other").accordion({fit:true});
    		$("#friend_title").panel({
    			iconCls:"icon-friend" ,
    			width:290 ,
 //   			fit:true ,
    			height:450 ,
    			title:"我的好友",
    			tools:[{
    				iconCls:"icon-add" ,
    				handler:function(){
    					$("#friend_addFrDialog").dialog("open");
    				}
    			}]
    		});
    		
    		//修改信息表单的验证
    		
    		$("#cniname").validatebox({
    			required:true ,
    			missingMessage:"该项不能为空！"
    		});
    		$("#cpassword").validatebox({
    			required:true ,
    			missingMessage:"修改信息必须验证密码！！"
    		});
    		$("#change_age").numberspinner({
    			required:true ,
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
    		$("#search_minAge,#search_maxAge").numberspinner({
				increment:1 ,
				width:40 ,
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
    		$("#friend_noLoginMsg").panel({
    			width:290 ,
    			height:200 ,
    			iconCls:"icon-tip" ,
    			title:"欢迎光临" 
    		});
  			$("#friend_cal").panel({
    			width:290 ,
    			height:230 ,
    			fit:true ,
    			iconCls:"icon-calendar" ,
    			title:"日历"
    		});
    		$("#friend_calendar").calendar({
    			width:280,
    			height:240 ,  			
    			current:new Date() ,
    			onSelect:function(date){
    				$.messager.alert("提示信息","今天是："+new Date().getFullYear()+"年"+
					(new Date().getMonth()+1)+"月"+new Date().getDate()+"号","info");
    			}
    		});
    		
    		$("#friend_changeMsg").dialog({
    			title:"修改信息" ,
    			iconCls:"icon-edit",
    			closed:true ,
    			width:300 ,
    			modal:true ,
    			buttons:[{
    				text:"确定" ,
    				iconCls:"icon-ok",
    				handler:function(){
    					$("#friend_changeForm").form("submit",{
    						url:"user/changeMsg.action" ,
    						success:function(res){
    							var obj = $.parseJSON(res);
    							if(obj.status == "success"){
    								$.messager.show({
    									title:"提示信息",
    									msg:obj.msg
    								});
    								$("#friend_changeMsg").dialog("close");
    								setTimeout(reLoad,2000);
    							}else{
    								$.messager.show({
    									title:"提示信息",
    									msg:obj.msg
    								});
    							}
    						}
    					});
    				}
    			},{
    				text:"取消" ,
    				iconCls:"icon-cancel",
    				handler:function(){
    					$("#friend_changeMsg").dialog('close');
    				}
    			}]
    		});
    		$("#friend_mymsg table td a").css("background-color","#efe").linkbutton({iconCls:"icon-edit",plain:true}).click(function(){
    			$("#friend_changeMsg").dialog('open');
    		});
    		
    		$("#id_search_result").panel({fit:true,title:"查找结果"});
    		$("#friend_addFrDialog").dialog({
    			width:400 ,
    			height:300 ,
    			iconCls:"icon-add" ,
    			title:"添加新好友" ,
    			closed:true ,
    			modal:true ,
    			buttons:[{
    				text:"关闭该窗口" ,
    				iconCls:'icon-cancel',
    				handler:function(){
    					$("#friend_addFrDialog").dialog("close");
    				}
    			}]
    		});
    		$("#friend_add_tabs").tabs({fit:true});
    		$("#searchByIdInput").combobox({
    			width:140 ,
    			mode:'remote',
    			url:"user/getUserById.action" ,
    			valueField:"username" ,
    			textField:"username" ,
    			delay:500 ,
    			formatter:function(row){
    				if(row != null && row !=undefined)
    				{
    					var str = row.username+":"+(row.sex=="m"? "男 ":"女 ")+row.nickName;
    					return str;
    				}
    			} ,
    			onSelect:function(record){
    				$("#td1").text(record.username);
	    			$("#td2").text(record.nickName);
	    			$("#td4").text(record.age);
	    			$("#td3").text(record.sex=="m"? "男":"女");
	    			$("#td5").text(record.signature);	
    				$("#confirmBtn").linkbutton({
    					iconCls:"icon-add",
    					plain:true ,
    					text:"添加"+record.nickName+"为好友"}).click(function(){
    						$.messager.prompt("请输入分组","[1:朋友 ][2:亲人 ][3:网友 ][4:同学 ]",function(r){
    						if(!r.isNAN && (r==1||r==2||r==3||r==4)){
    							$.post("user/addFriend.action",{"uf.FUsername":$("#td1").text(),
    							"uf.categroy":r},function(res){
    								if(res.status == "success"){
    									$.messager.show({title:"提示信息",msg:res.msg});
    									setTimeout(reLoad,2000);
    								}else{
    									$.messager.alert("提示信息",res.msg,"error");
    								}},"json");}else{
    									$.messager.alert("提示信息","无效的输入!","error");
    								}
    					});
	    			});		
	    		}
    		});
    		
   			$("#search_condition_panel").panel({width:382,height:150});
    		
    		$("#search_result_table").datagrid({
    			title:"查询结果" ,
    			fit:true ,
    			fitColumns:true ,
    			idField:'username' ,
    			rownumbers:true,
    			striped:true ,
    			url:"user/findByCondition.action" ,
    			loadMsg:"-查询信息加载中-",
    			singleSelect:true ,
    			columns:[[{
    					title:"用户名" ,
    					width:60 ,
    					field:'username'
    				} ,{
    					title:"昵称",
    					width:60 ,
    					field:'nickName' ,
    					sortable:true
    				},{
    					title:"年龄" ,
    					width:30 ,
    					field:"age" ,
    					align:'right',
    					sortable:true
    				},{
    					title:"性别" ,
    					width:30 ,
    					align: "right",
    					field:"sex" ,
    					sortable:true ,
    					formatter:function(value,data,index){
    						if(value == 'm'){
    							return "男";
    						}else{
    							return "女";
    						}   					
    					}
    				},{
    					title:'签名' ,
    					field:'signature' ,
    					width:100 ,
    					formatter:function(value,data,index){
    						return "<span title='"+value+"'>"+value+"</span>";
    					}			
    				},{
    				title:"操作" ,
    				field:"if" ,
    				width:60 ,
    				formatter:function(value,data,index){
    					return ("<button onclick='showAddDialog("+index+");'>"+"操作"+"</button>");
    				}  				
    			}]]
    			
    		});
    		$("#search_con_ok").linkbutton({plain:true }).click(function(){
    			$("#search_result_table").datagrid("load",serializeForm($("#search_condition_form")));
    		});
    		$("#search_con_cancel").linkbutton({plain:true }).click(function(){
    			$("#search_result_table").datagrid("load",{});
    		});
    		$("#friend_add_con").dialog({
    			title:"添加好友" ,
    			iconCls:"icon-add" ,
    			width: 200 ,
    			height:200 ,
    			closed: true ,
    			buttons:[{
    				text:"添加" ,
    				iconCls: "icon-ok" ,
    				handler:function(){
    					$.post("user/addFriend.action",{"uf.FUsername":$("#con_td1").text(),
    							"uf.categroy":$("#con_sel").val()},function(res){
	    						if(res.status == "success"){
	    						$("#friend_add_con").dialog("close");
	    						$.messager.show({title:"提示信息",msg:res.msg});
	    						$("#friends,#dears,#netfriends,#students,#search_result_table").datagrid("load");
    						}else{
    							$.messager.alert("提示信息",res.msg,"error");
    						}
    					},'json');
    				}
    			},{
    				text:"关闭" ,
    				iconCls:"icon-cancel" ,
    				handler:function(){
    					$("#friend_add_con").dialog("close");
    				}
    			}]
    		});
    		
    		var nickName1 = "";
    		var signature1 = "";   		
    		$("#friends").datagrid({
    			fitColmns:true ,
    			url:"list/listByCtaegroy.action?categroy=1" ,
    			idField : "id",
    			loadMsg:"数据加载中，请等待" ,
    			singleSelect:true ,
    			rowStyler:function(index,data){
    				if(data.status == 0){
    					return "background-color:#B4CDCD;";
    				}else{
    					return "";
    				}
    			},
    			onDblClickRow:function(index,data){
    				showMsgInChatPanel(data.FUsername);
    				$("#friend_chat").dialog("open");
    				taskId = setInterval(getChatMsg, 2500);
    			} ,
    			columns:[[{
    				title:"头像" ,
    				field:"FUsername" ,
    				width:50 ,
    				formatter:function(value,data,index){
    					$.ajax({
    						type:'post' ,
							url:"list/getUserMsg.action?username="+value ,
							cache:false ,
							async:false ,
							dataType:'json' ,
    						success:function(res){
    							nickName1 = res.nickName;
    							signature1 = res.signature;
    						}
    					});
    					return "<img src='img/"+index%10+".bmp'/>";
    				}
    			},{
    				title:"昵称" ,
    				field:"buildTime" ,
    				width:60,
    				formatter:function(value,data,index){
    					return nickName1;
    				}		
    			},{
    				title:"签名" ,
    				field:"xxx" ,
    				width:150,
    				formatter:function(value,data,index){
    					return signature1;
    				}
    			}]],
    		});
    		
    		var nickName2 = "";
    		var signature2 = "";
    		$("#dears").datagrid({
    			fitColmns:true ,
    			url:"list/listByCtaegroy.action?categroy=2" ,
    			idField : "id",
    			loadMsg:"数据加载中，请等待" ,
    			singleSelect:true ,
    			onDblClickRow:function(index,data){
    				showMsgInChatPanel(data.FUsername);
    				$("#friend_chat").dialog("open");
    				taskId = setInterval(getChatMsg, 2500);
    			} ,
    			rowStyler:function(index,data){
    				if(data.status == 0){
    					return "background-color:#B4CDCD;";
    				}else{
    					return "";
    				}
    			},
    			columns:[[{
    				title:"头像" ,
    				field:"FUsername" ,
    				width:50 ,
    				formatter:function(value,data,index){
    					$.ajax({
    						type:'post' ,
							url:"list/getUserMsg.action?username="+value ,
							cache:false ,
							async:false ,
							dataType:'json' ,
    						success:function(res){
    							nickName2 = res.nickName;
    							signature2 = res.signature;
    						}
    					});
    					return "<img src='img/"+(5+index)%10+".bmp'/>";
    				}
    			},{
    				title:"昵称" ,
    				field:"buildTime" ,
    				width:60,
    				formatter:function(value,data,index){
    					return nickName2;
    				}		
    			},{
    				title:"签名" ,
    				field:"xxx" ,
    				width:150,
    				formatter:function(value,data,index){
    					return signature2;
    				}
    			}]],
    		});
    		
    		
    		var nickName3 = "";
    		var signature3 = "";
    		$("#netfriends").datagrid({
    			fitColmns:true ,
    			url:"list/listByCtaegroy.action?categroy=3" ,
    			idField : "id",
    			loadMsg:"数据加载中，请等待" ,
    			singleSelect:true ,
    			onDblClickRow:function(index,data){
    				showMsgInChatPanel(data.FUsername);
    				$("#friend_chat").dialog("open");
    				taskId = setInterval(getChatMsg, 5000);
    			} ,
    			rowStyler:function(index,data){
    				if(data.status == 0){
    					return "background-color:#B4CDCD;";
    				}else{
    					return "";
    				}
    			},
    			columns:[[{
    				title:"头像" ,
    				field:"FUsername" ,
    				width:50 ,
    				formatter:function(value,data,index){
    					$.ajax({
    						type:'post' ,
							url:"list/getUserMsg.action?username="+value ,
							cache:false ,
							async:false ,
							dataType:'json' ,
    						success:function(res){
    							nickName3 = res.nickName;
    							signature3 = res.signature;
    						}
    					});
    					return "<img src='img/"+(6+index)%10+".bmp'/>";
    				}
    			},{
    				title:"昵称" ,
    				field:"xxx" ,
    				width:60,
    				formatter:function(value,data,index){
    					return nickName3;
    				}		
    			},{
    				title:"签名" ,
    				field:"ghj" ,
    				width:150,
    				formatter:function(value,data,index){
    					return signature3;
    				}
    			}]],
    		});
    		
    		
    		var nickName4 = "";
    		var signature4 = "";
    		$("#students").datagrid({
    			fitColmns:true ,
    			url:"list/listByCtaegroy.action?categroy=4" ,
    			idField : "id",
    			loadMsg:"数据加载中，请等待" ,
    			singleSelect:true ,
    			onDblClickRow:function(index,data){
    				showMsgInChatPanel(data.FUsername);
    				$("#friend_chat").dialog("open");
    				taskId = setInterval(getChatMsg, 2500);
    			} ,
    			rowStyler:function(index,data){
    				if(data.status == 0){
    					return "background-color:#B4CDCD;";
    				}else{
    					return "";
    				}
    			},
    			columns:[[{
    				title:"头像" ,
    				field:"FUsername" ,
    				width:50 ,
    				formatter:function(value,data,index){
    					$.ajax({
    						type:'post' ,
							url:"list/getUserMsg.action?username="+value ,
							cache:false ,
							async:false ,
							dataType:'json' ,
    						success:function(res){
    							nickName4 = res.nickName;
    							signature4 = res.signature;
    						}
    					});
    					return "<img src='img/"+(2+index)%10+".bmp'/>";
    				}
    			},{
    				title:"昵称" ,
    				field:"buildTime" ,
    				width:60,
    				formatter:function(value,data,index){
    					return nickName4;
    				}		
    			},{
    				title:"签名" ,
    				field:"xxx" ,
    				width:150,
    				formatter:function(value,data,index){
    					return signature4;
    				}
    			}]],
    		});
    		
    		$("#friend_chat").dialog({
    			width:500 ,
    			height:350 ,
    			modal:true ,
    			closed:true ,
    			title:"正在聊天" ,
    			collapsible:true ,
    			onClose:function(){
    				$("#chat_input textarea").val("");
    				$("#chat_his_table").html("");
    				clearInterval(taskId);
    			},
    			buttons:[{
    				text:"发送" ,
    				iconCls:"icon-save" ,
    				handler:function(){
    					sendMsg();
    				}
    			},{
    				text:"关闭" ,
    				iconCls:"icon-cancel" ,
    				handler:function(){
    					$("#friend_chat").dialog("close");
    				}
    			}]
    		});
    		
    		$("#chat_his").panel({
    			title:"聊天记录" ,
    			width:300 ,
    			iconCls:"icon-blog" ,
    			height:160 ,
    		//	href: 'chat.jsp'
    		});
    		$("#chat_input").panel({
    			title:"正在输入" ,
    			width:300 ,
    			height:80 ,
    			iconCls:"icon-edit" ,
    		//	href: 'chat.jsp'
    		});
    		$("#chat_frMsg").panel({
    			title:"他的信息" ,
    			width:185,
    			height:265 ,
    			iconCls:"icon-tip" ,
    		});
    		
    		if($("#blog_regist").text() != '注册'){
    			setTimeout(getLatestMsg, 10000);
    		}
    	});
    	
    	function showMsgInChatPanel(re){
    		$.post("list/getUserMsg.action?username="+re,function(data){
    			$("#chat_cap").text(data.nickName+"的个人信息");
    			$("#chat_td1").text(data.username);
    			$("#chat_td2").text(data.nickName);
    			$("#chat_td3").text(data.age);
    			$("#chat_td4").text(data.sex=="m"? "男":"女");
    			$("#chat_td5").text(data.signature);
    		},"json");
    	}
    	function showAddDialog(index){
    		var obj = $("#search_result_table").datagrid("getRows")[index];
    		$("#con_td1").text(obj.username);
    		$("#con_td2").text(obj.nickName);
    		$("#friend_add_con").dialog("open");
    	}
    	function sendMsg(){
    		$.post("chat/saveChatMsg.action",{"ch.toUser":$("#chat_td1").text(),
    			"ch.msg":$("#chat_input textarea").val()},function(res){},"json");
    		$("#chat_his_table").append("<tr><td>我/"+(new Date).format("hh:mm/")
    				+"</td><td>"+$("#chat_input textarea").val()+"</td></tr>");   		
    		$("#chat_input textarea").val("");
    	}
    	function getChatMsg(){
    		$.post("chat/getChatMsg.action?ch.toUser="+$("#chat_td1").text(),function(data){		
    			for(var i=0;i<data.length;i++){
    				$("#chat_his_table").append("<tr><td>他/"+(data[i].time).substring(11,16)+"/"
    				+"</td><td>"+data[i].msg+"</td></tr>");
    			}
    		},"json");
    	}
    	
    	function getLatestMsg(){
    		$.post("chat/getLatestMsg.action",function(data){
    		if( data.length != 0 && data != null){
    			$.messager.show({title:"提示信息" ,msg:data.nickName+"给你发送了消息，双击列表即可看到！"});}
    		},"json");
    	}
    </script>
    <c:choose>
    	<c:when test="${empty sessionScope.user }">
    		<div id="friend_noLoginMsg" style="margin-top:60px;">
    			<span style="font-size:18px;">&nbsp;&nbsp;您还没有登录！赶快
    			<a href="javascript:login();">登录</a>或
    			<a href="javascript:regist();">注册</a>吧！！</span>
    		</div>
    		<div id="friend_cal" style="padding:2px;">
    			<div>
	    			<div id="friend_calendar"></div>
    			</div>
    		</div>
    	</c:when>
    	<c:otherwise>
		    <div id="friend_self">
		    	<div id="friend_mymsg">
		    		<table style="font-size:12px;">
		    			<tr>
		    				<td rowspan="2" align="right" width="40px"><img src="img/0.bmp"/></td>
		    				<td>昵称:${sessionScope.user.nickName }</td>
		    			</tr>
		    			<tr><td id="friend_login_un">号码:${sessionScope.user.username}</td></tr>
		    			<tr>
		    				<td colspan="2" rowspan="2">签名:${sessionScope.user.signature }</td>
		    			</tr>
		    			<tr>
		    				<td colspan="2" align="center"><a href="javascript:;">修改信息</a></td>
		    			</tr>
		    		</table>
		    	</div>
		    </div>
		    <div id="friend_title">
			    <div id="friend_other">
			    	<div title="朋友" selected="true">
				    	<div>
				    		<table id="friends"></table>
				    	</div>
			    	</div>
			    	<div title="亲人">
			    		<table id="dears"></table>
			    	</div>
			    	<div title="网友">
			    		<table id="netfriends"></table>
			    	</div>
			    	<div title="同学">
			    		<table id="students"></table>
			    	</div>
			    </div>
		    </div>
	    </c:otherwise>
    </c:choose>
    
    <div id="friend_changeMsg">
    	<div style="margin-left:20px;margin-top:30px;">
			<form method="post" id="friend_changeForm">
				<table>
					<tr>
						<td>用户名:</td>
						<td>${sessionScope.user.username }</td>
						<td><input type="hidden" name="user.username" 
							value="${sessionScope.user.username }"></td>
					</tr>
					<tr>
						<td>昵&nbsp;&nbsp;称:</td>
						<td><textarea name='user.nickName' id='cniname'
						style="height:50%;width:155px;" rows="1" cols="1">${sessionScope.user.nickName }</textarea>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;码:</td>
						<td><input type="password" name="user.password" id="cpassword">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>性&nbsp;&nbsp;别:</td>
						<td>男<input type="radio" name="user.sex" value="m" ${sessionScope.user.sex=='m'?"checked":"" }/>
							女<input type="radio" name="user.sex" value="f" ${sessionScope.user.sex=='f'?"checked":"" }/>
						</td><td></td>
					</tr>
					<tr>
						<td>年&nbsp;&nbsp;龄:</td>
						<td><input type="text" name="user.age" id="change_age" value="${sessionScope.user.age }"/></td>
						<td></td>
					</tr>
					<tr>
						<td align="right" height=40>签&nbsp;&nbsp;名:</td>
						<td><textarea name="user.signature" cols="2" rows="3"
						  style="height:100%;width:155px;">${sessionScope.user.signature }</textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
    
    <div id="friend_addFrDialog">
    	<div id="friend_add_tabs">
    		<div title="精确查找" style="width:80%;height:80%;" iconCls="icon-search">
    			<div style="margin-left:30px;margin-top:20px;margin-bottom: 20px;">
    				请输入对方用户名:<input type="text" id="searchByIdInput"/>
    			</div>
    			<div id="id_search_result">
    				<table style="margin-left:20px;font-size:12px;">
    				<tr>
    					<td>用户名:</td><td id="td1"></td><td>
    					<a href="javascript:;" id="confirmBtn"></a></td>
    				</tr>
    				<tr>
    					<td>昵称:</td><td id="td2"></td>
    				</tr>
    				<tr>
    					<td>性别:</td><td id="td3"></td>
    				</tr>
    				<tr>
    					<td>年龄:</td><td id="td4"></td>
    				</tr>
    				<tr>
    					<td>签名:</td><td id="td5"></td>
    				</tr>
    			</table>
    			</div>
    		</div>
    		<div title="按条件筛选" style="width:80%;height:80%;" iconCls="icon-search">
    			<div style="margin-left:10px;margin-top:10px;">
    				<form method="post" id="search_condition_form">
    					年龄范围:<input type="text" name="minAge" id="search_minAge"/>
    						到<input type="text" name="maxAge" id="search_maxAge">
    					<span style="margin-left:10px;">性别:<select name="sex">
    						<option value="m">男</option>
    						<option value="f">女</option>
    					</select></span>
    					<a id="search_con_ok" href="javascript:;" style="background-color:#efe;"
    					>确认</a>|<a id="search_con_cancel" href="javascript:;" style="background-color:#efe;">清除</a>
    				</form>
    			</div>
    			<div id="search_condition_panel">
    				<table id="search_result_table">
    				</table>
    			</div>
    		</div>
    	</div>
    </div>
    
    <div id="friend_add_con">
    	<table style="margin-left:5px;font-size:12px;margin-top:20px;" cellpadding="5px;">
    		<tr>
    			<td>用户名:</td><td id="con_td1"></td>
    		</tr>
    		<tr>
    			<td>昵称:</td><td id="con_td2"></td>
    		</tr>
    		<tr><td>分组:</td>
    		<td>
    			<select name="categroy" id="con_sel">
    				<option value="1">朋友</option>
    				<option value="2">亲人</option>
    				<option value="3">网友</option>
    				<option value="4">同学</option>
    			</select>
    	</table>
    </div>
    
    <div id="friend_chat">
    	<div style="float:left;" id="chat_total">
    		<div id="chat_his" style="float:left;">
    			<table id="chat_his_table" style="font-size:12px;">
    				<tr><td style="width:70px;"></td><td></td></tr>
    			</table>
    		</div>
    		<div id="chat_input" style="margin-top:10px;">
    			<textarea style="width:100%;height:97%;"></textarea>
			</div>    		
    	</div>
    	<div style="float:right;" id="chat_frMsg">
    		<table style="margin:20px auto;font-size:15px;">
    			<caption id="chat_cap"></caption>
    			<tr><td style="width:40px;">号码:</td><td id="chat_td1"></td></tr>
    			<tr><td>昵称:</td><td id="chat_td2"></td></tr>
    			<tr><td>年龄:</td><td id="chat_td3"></td></tr>
    			<tr><td>性别:</td><td id="chat_td4"></td></tr>
    			<tr><td rowspan="2">签名:</td>
    				<td id="chat_td5" rowspan="2"></td>
    			</tr>
    		</table>
    	</div>
    </div>
    <div>
    </div>
  </body>
</html>