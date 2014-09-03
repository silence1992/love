<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
</head>

<body>
	<script type="text/javascript">
		$(function() {
			$("#mylog_my").panel(
					{
						width : 1000,
						height : 550,
						title : "我的动态",
						tools : [ {
							iconCls : "icon-reload",
							handler : function() {
								$("body").layout("panel", "center").panel(
										"refresh", "blog/getBlog.action");
							}
						},{
							iconCls:"icon-edit" ,
							handler:function(){
								$("#editNewBlog").dialog("open");
							}
						} ]
					});
			$("#blog_response").dialog({
				title:"回复对话框" ,
				closed:true ,
				width:250 ,
				height:200 ,
				modal:true ,
				buttons:[{
					text:"确认" ,
					iconCls:"icon-ok" ,
					handler:function(){
						$.post("blog/responseBlog.action?",{"id":$("#sendMsgSpan").text(),"text":$("#resText").val()},function(res){
							if(res.status == "success"){
								$("#blog_response").dialog("close");
								$.messager.show({title:"提示信息",msg:res.msg});
								$("body").layout("panel", "center").panel("refresh", "blog/getBlog.action");							
							}
						},"json");
					}
				},{
					text:"取消" ,
					iconCls:"icon-cancel" ,
					handler:function(){
						$("#blog_response").dialog("close");
					}
				},]
			});
			
			$("#editNewBlog").dialog({
				title:"发表新说说" ,
				iconCls:"icon-edit" ,
				closed:true ,
				width:300 ,
				height:250 ,
				modal:true ,
				buttons:[{
					text:"确认" ,
					iconCls:"icon-ok" ,
					handler:function(){
						$.post("blog/addNewBlog.action",serializeForm($("#editNewBlogForm")),function(res){
							if(res.status == "success"){
								$.messager.show({title:"提示信息",msg:res.msg});
								$("#editNewBlog").dialog("close");
								$("body").layout("panel", "center").
									panel("refresh", "blog/getBlog.action");								
							}						
						},"json");
					}
				},{
					text:"取消" ,
					iconCls:"icon-no" ,
					handler:function(){
						$("#editNewBlog").dialog("close");
					}
				}]
			});
			
			$("#blog_topic").validatebox({
				required:true ,
				missingMessage:"该项不能为空" ,
				width:300
			});
			$("#blog_content").validatebox({
				required:true ,
				missingMessage:"该项不能为空" ,
				width:200
			});
			
			$("#helpDialog").dialog({
				width:300 ,
    			height:200 ,
    			title:"帮助信息" ,
    			closed:true ,
    			modal:true ,
    			iconCls:"icon-help" ,
    			buttons:[{
    				text:"关闭" ,
    				iconCls:"icon-cancel" ,
    				handler:function(){
    					$("#helpDialog").dialog('close');
    				}
    			}]
			});
			$("#messagePanel").dialog({
				modal:true ,
    			width:400 ,
    			height:300 ,
    			title:"我的离线消息" ,
    			closed:true ,
    			buttons:[{
    				text:"关闭" ,
    				iconCls:"icon-cancel" ,
    				handler:function(){
    					$("#messagePanel").dialog("close");
    				}
    			}]
    		});
		});
		
		function sendBlogMsg(id){
			var blogId = parseInt(id);
			$("#sendMsgSpan").text(blogId);
			$("#blog_response").dialog("open");
		}
		function priseBlog(id){
			var blogId = parseInt(id);
			$.post("blog/priseBlog.action?id="+blogId,function(res){
				if(res.status == "success"){
					$.messager.show({title:"提示信息",msg:res.msg});
					$("body").layout("panel", "center").panel("refresh", "blog/getBlog.action");
				}
			},"json");
		}
		function getAllMessage(){
			if($("#msgChat").text()=="" || $("#msgChat").text()==null){
				$.post("chat/getAllMessage.action",function(res){
				for(var i=0;i<res.length;i++){
					$("#msgChat").append("<li>"+
						res[i].nickName+"("+res[i].username+")给你发了"+res[i].count+"条消息！</li>");
				}
				},"json");
			}
    		$("#messagePanel").dialog("open");
    	}
    	
    	function showHelp(){
    		$("#helpDialog").dialog("open");
    	}
	</script>
	<div id="north_center_head">
		<div>
			<ul id="mylog_ul">
				<c:choose>
					<c:when test="${empty sessionScope.user }">
						<li style="float:left;">您好,<a href="javascript:;"
							onclick="login();">请登录</a></li>
						<li style="float:left;">|<a href="javascript:;"
							onclick="regist();" id="blog_regist">注册</a></li>
					</c:when>
					<c:otherwise>
						<li style="float:left;">您好,${sessionScope.user.nickName }</li>
						<li style="float:left;">|<a href="javascript:;"
							onclick="logout();">退出</a></li>
						<span style="margin-left:400px;"><a href="javascript:getAllMessage();">消息</a></span>
						<span style="margin-left:20px;"><a href="javascript:showHelp();">帮助</a></span>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<div id="mylog_total">
		<div id="mylog_my">
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<div style="color:red;font-size:25px;margin-top:50px;margin-left:250px;">
						您还没有登录，请<a href="javascript:login();">登录</a>或<a
							href="javascript:regist();">注册</a>吧!
					</div>
					<div
						style="color:red;font-size:25px;margin-top:0px;;margin-left:150px;">
						<table>
							<tr>
								<td>
									<div align="center">
										<table style="BORDER-COLLAPSE: collapse" border="0"
											cellpadding="0" height="240" width="600">
											<tbody>
												<tr>
													<td background="images/green.jpg"><object id="obj3"
															codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0"
															classid="clsid:D27CDB6E-AE6D-11CF-96B8-444553540000"
															border="0" height="240" width="600">
															<param name="movie" value="images/green.swf">
															<param name="quality" value="High">
															<param name="wmode" value="transparent">
															<embed src="images/green.swf"
																pluginspage="http://www.macromedia.com/go/getflashplayer"
																type="application/x-shockwave-flash" name="obj3"
																quality="High" wmode="transparent" height="240"
																width="600">
														</object>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<s:iterator value="blist" var="blog" status="status">
					<div style="margin-left:120px;margin-top:10px;border:1px solid black;width:700px;">
						<table style="font-size:15px;" cellpadding="0px" cellspacing="0px">
							<tr>
								<td style="width:50px;" rowspan="2"><img src="img/<s:property value="#status.count"/>.bmp"/></td>
								<td style="width:500px;"><s:property value="#blog.user.nickName"/>发表了新说说</td>
								<td><span style="font-size:12px;color:#f00;"><s:property value="#blog.createTime"/></span></td>
							</tr>
							<tr>
								<td><span style="color:#8B1A1A;font-size:15px;">&lt;&lt;
									<span><s:property value="#blog.topic"/></span>&gt;&gt;</span></td>
								<td style="font-size:12px;">
								<s:if test="#blog.user.username.equals(#session.user.username)">
								
								</s:if>
								<s:else>
								<span><img src="img/good.png"/>
									<a href="javascript:priseBlog('<s:property value="#blog.id"/>');">赞(<span><s:property value="#blog.zan"/></span>)</a>
									</span><span style="margin-left:30px;"><a href="javascript:sendBlogMsg('<s:property value="#blog.id"/>');"
										>回复(<span><s:property value="#blog.comments"/></span>)</a></span>
								</s:else>		
								</td>
							</tr>
						</table>
							<div style="margin-top:0px;margin-left:30px;font-size:20px;" >
								&nbsp;&nbsp;&nbsp;&nbsp;内容:<span><s:property value="#blog.content"/></span></div>
							<div style="margin-top:0px;margin-left:100px;border:1px solid red;width:500px;">
								<table style="font-size:12px;">
									<s:iterator value="#blog.set" var="ub">
										<tr>
											<td align="right"><s:property value="#ub.user.nickName"/>:</td>
											<td><s:property value="#ub.talks"/></td>
											<td style="color:red;font-size:10px;"><s:property value="#ub.talkTime"/></td>
										</tr>
									</s:iterator>
								</table>
							</div>
						</div>
					</s:iterator>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<div id="blog_response">
		<div style="margin-left:20px;margin-top:10px;">
			<h2>请输入回复内容</h2>
			<textarea cols="25" rows="3" id="resText" style="font-size:12px;">
				
			</textarea>
		</div>
	</div>
	
	<div id="editNewBlog">
		<div style="margin-top:10px;margin-left:20px;">
			<form id="editNewBlogForm">
				<table style="font-size:12px;">
					<tr><td>主题:</td><td style="width:220px;"><input type="text" name="topic" id="blog_topic"></td></tr>
					<tr><td>内容:</td><td><textarea rows="3" cols="15" id="blog_content" name="text"></textarea></td></tr>
				</table>
			</form>
		</div>
	</div>
	<div id="messagePanel" style="font-size:12px;">
    	<div style="margin-top:10px;margin-left:20px;">
    		<ul id="msgChat"></ul>
    	</div>
    </div>
    
    <div id="helpDialog">
    	<div style="margin-top:10px;margin-left:20px;">
    		<ol>
    			<li>双击好友列表即可与选中好友聊天</li>
    			<li>点击我的动态面板右上角的小图标可以刷新界面或者发表新说说</li>
    			<li>点击我的好友右侧的<img src="img/add.png"/>图标可以添加好友</li>
    			<li>不能删除好友或者删除动态</li>
    			<li>BUG较多，请无视。。。</li>
    		</ol>
    	</div>
    </div>
	<div style="display:none">
		<span id="sendMsgSpan"></span>
	</div>
</body>
</html>