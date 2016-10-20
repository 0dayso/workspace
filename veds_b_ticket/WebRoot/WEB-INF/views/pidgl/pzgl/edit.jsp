<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>配置管理</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2.js" type="text/javascript"></script>

<script type="text/javascript">

var validator;
$(function(){
	validator = $("#save").Validform({
		tiptype:3
	});
});
function disable(){
 	window.history.back();
}

function checkTime(){
	var ch = document.getElementById("hastransactionwait");
	if(ch.checked==true){
		document.getElementById("checkText").readOnly=false;
	}else{
		document.getElementById("checkText").readOnly=true;
		document.getElementById("checkText").value="1000";
	}
}

function save(){
	validator.submitForm(false);
}
function pidz(o){
	var value = o.value;
	if(value!=""){
		var values = value.split(",");
		$("#pidzbh").val(values[0]);
		$("#pidbh").val(values[1]);
	}
}


</script>
<script type="text/javascript">
<%-- 
$(function(){

	validator = $("#tstudent").Validform({
		tiptype:3
	});
	
	$.ajax({url:"/manage/student/childTeacherList",success:function(data){
		var html = "<table>";
		html = html +"<tr><td>"+data.id+"</td><td>"+data.sname+"</td><td>"+data.classname+"</td></tr>";
		html =html+ "</table>";
		$("#teacherList").html(html);
	}});
	
});
--%>
</script>
</head>
<body>
	<div class="container">
		<div id="forms" class="mt10">
			<div class="box">
				<div class="box_border">
					<div class="box_top">
						<b class="pl15">增加新的PID</b>
					</div>
					<div class="box_center">
						<form action="${ctx}/vedsb/pidgl/pzgl/save" id="save" class="jqtransform" method="post">
							<input type="hidden" name="turningUrl"  value="${ctx}/vedsb/pidgl/pzgl/list?lx=${param.lx}" /> 
							<input type="hidden" name="no" value="${entity.no}"> 
							<input type="hidden" name="stop" value="${entity.stop}">
							<table class="form_table pt15 pb15" width="80%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="td_right">服务器地址：</td>
									<td>
										<input type="text" name="serverip" datatype="*" value="${entity.serverip }" class="input-text lh30" size="20" />
									</td>
									<td class="td_right">服务器端口：</td>
									<td>
										<input type="text" name="serverport" datatype="*" value="${entity.serverport }" class="input-text lh30" size="20" />
									</td>
								</tr>

								<tr>
									<td class="td_right">工作号：</td>
									<td>
										<input type="text" name="si" datatype="*" value="${entity.si }" class="input-text lh30" size="20" /> 
										<span style="color: gray;">(格式:si 工作号/密码)</span>
									</td>
									<td class="td_right">权限组：</td>
									<td>
										<baseTag:groupsList var="list" shbh="${BUSER.shbh}" />
										<select name='sspidz' style='width:120px' datatype="*" onchange="pidz(this)">
											<option value="">==请选择==</option>
											<c:forEach items="${list}" var="g">
												<option value="${g.pidzbh},${g.pidbh}" ${g.pidzbh eq entity.pidzid and g.pidbh eq entity.pidid ? 'selected' : '' }>${g.yhzmc}</option>
											</c:forEach>
										</select> 
										<input type="hidden" id="pidzbh" name="pidzid" value="${entity.pidzid}"> 
										<input type="hidden" id="pidbh" name="pidid" value="${entity.pidid}">
									</td>
								</tr>

								<tr>
									<td class="td_right">配置用户名：</td>
									<td>
										<input type="text" name="username" datatype="*" value="${entity.username }" class="input-text lh30" size="20" />
									</td>
									<td class="td_right">配置密码：</td>
									<td>
										<input type="text" name="password" datatype="*" value="${entity.password }" class="input-text lh30" size="20" />
									</td>
								</tr>
								<tr>
									<td class="td_left"></td>
									<td class="td_left" colspan="3">
										<input type="checkbox" id="autolink" name="autolink" value="" ${entity.autolink eq '1'?'checked':''}/>自动连接
										<input type="checkbox" id="authenticationtype" name="authenticationtype" value="" ${entity.authenticationtype eq '1' ? 'checked':''}/>密码认证
										<input type="checkbox" id="saflytransmited" name="saflytransmited" value="" ${entity.saflytransmited eq '1'?'checked':''}/>安全传输
										<input type="checkbox" id="webpid" name="webpid" value="" ${entity.webpid eq '1' ? 'checked':''}/>信天游
										<select id="webpidtype" name="webpidtype" class="select" style="width:88px;">
											<option value="WEB" <c:if test="${entity.webpidtype eq 'WEB'}">selected</c:if>>WEB</option>
											<option value="WEB2" <c:if test="${entity.webpidtype eq 'WEB2'}">selected</c:if>>WEB2</option>
										</select>
									</td>
								</tr>

								<tr>
									<td class="td_left"></td>
									<td class="td_left" colspan="3">
										<input type="checkbox" id="ipe" name="ipe" value="" ${entity.ipe eq '1' ? 'checked' : ''}/>IPE
										<input type="checkbox" id="bigpid" name="bigpid" value="" ${entity.bigpid eq '1' ? 'checked' : ''}/>大配置
									 	<input type="checkbox" id="cantsl" name="cantsl" value="" ${entity.cantsl eq '1' ? 'checked' : ''}/>是否能导票
									  	<input type="checkbox" id="canprinv" name="canprinv" value="" ${entity.canprinv eq '1' ? 'checked' : ''}/>是否能够打印行程单
									</td>
								</tr>
								<tr>
									<td class="td_right">打票机号：</td>
									<td class="td_left">
										<input name="printno" value="${entity.printno}" class="input-text lh30" size="20" />
									</td>
									<td class="td_right">是否默认配置：</td>
									<td class="td_left">
										<input type="radio" name="sfmr" value="1" ${entity.sfmr eq '1' ? 'checked' : ''} />是 
										<input type="radio" name="sfmr" value="0" ${empty entity.sfmr || entity.sfmr eq '0' ? 'checked' : ''} />否
									</td>
								</tr>
								<tr>
									<td class="td_right"><span>流量上限额度：</span></td>
									<td>
										<input type="text" name="flowlimit" datatype="*" value="${entity.flowlimit }" class="input-text lh30" size="20" />
									</td>
									<td class="td_right"><span>office号：</span></td>
									<td>
										<input type="text" name="office" datatype="*" value="${entity.office }" class="input-text lh30" size="20" />
									</td>
									
								</tr>
                                  
                                <tr>
                                	<td class="td_right"><span>IATA号：</span></td>
									<td>
										<input type="text" name="iata"  value="${jppz.iata}" class="input-text lh30" size="20" />
									</td>
                                </tr>  
                                  
								<tr>
									<td align="center" colspan="4">
										<input type="button" class="ext_btn ext_btn_submit" onclick="save();" value="确认">
										<input type="button" onclick="disable()" class="ext_btn ext_btn_submit" value="取消">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>