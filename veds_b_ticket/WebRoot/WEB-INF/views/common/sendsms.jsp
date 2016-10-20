<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title></title>
    	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
    	<script type="text/javascript">
			//取消邮寄  关闭当前弹窗
			function hideQuickDiv(){
				var index=parent.layer.getFrameIndex();
				parent.layer.close(index);
			}
			//收件人手机离开焦点时验证手机号码 格式
			function checkPhone(phones){
				if(!phones||phones.trim==''){
					alert("收件人的手机不能为空！");
					return ;
				}
				var arr=phones.split(",");
				var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
				var reg2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
				for(var i=0;i<arr.length;i++){
					if(!reg.test(arr[i])&&!reg2.test(arr[i])){
						alert("收件人的手机号码格式不正确！");
						return ;
					}
				}
			}
			//改变短信模板时,修改短信内容
			function showNr(op){
				var sid = op.options[op.selectedIndex].getAttribute("id");
				var nr = op.options[op.selectedIndex].getAttribute("name");
				var a=document.getElementById("fsnr");
				document.getElementById("fsnr").value=nr;
				document.getElementById("smslength").innerHTML="目前已有"+nr.length+"个字";
			}
			//每次键盘打个字就会去计算 短信内容里的字数
			function smslen(nr){
				document.getElementById("smslength").innerHTML="目前已有"+nr.length+"个字";
			}
			//保存
			function saveSms(){
				var jshm=document.getElementById("jshm").value;
				var fssj=document.getElementById("fssj").value;
				var dxlx = document.getElementById("dxlx").value;
				var fsnr=document.getElementById("fsnr").value;
				var ddbh = document.getElementById("ddbh").value;
				if(!jshm||jshm.trim==''){
					alert("收件人的手机不能为空！");
					$("#jshm").focus();
					return ;
				}else{
					var arr=jshm.split(",");
					var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
					var reg2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
					for(var i=0;i<arr.length;i++){
						if(!reg.test(arr[i])&&!reg2.test(arr[i])){
							alert("收件人的手机号码格式不正确！");
							$("#jshm").focus();
							return ;
						}
					}
				}
				if(!fssj){
					alert("发送短信时间不能为空！");
					return ;
				}else{
					var date = new Date();
					var year = date.getFullYear();
				    var month = date.getMonth() + 1;
				    var strDate = date.getDate();
				    var hours=date.getHours();
				    var minutes=date.getMinutes();
				    var seconds=date.getSeconds();
				    if (month >= 1 && month <= 9) {
				        month = "0" + month;
				    }
				    if (strDate >= 0 && strDate <= 9) {
				        strDate = "0" + strDate;
				    }
				    if (hours >= 0 && hours <= 9) {
				        hours = "0" + hours;
				    }
				    if (minutes >= 0 && minutes <= 9) {
				        minutes = "0" + minutes;
				    }
				    if (seconds >= 0 && seconds <= 9) {
				        seconds = "0" + seconds;
				    }
            		var now=year+month+strDate+hours+minutes+seconds;
            		var fstime=fssj.substring(0,4)+fssj.substring(5,7)+fssj.substring(8,10)+fssj.substring(11,13)
            		+fssj.substring(14,16)+fssj.substring(17,fssj.length);
            		if(now>=fstime){
            			alert("发送时间要比现在的时间大!");
            			return;
            		}
				}
				if(!dxlx||dxlx.trim()==""){
					alert("短信模板必填!");
            		return ;
				}
				if(!fsnr||fsnr.trim()==""){
					alert("短信内容必填!");
					$("#fsnr").focus();
            		return ;
				}
				var url="${ctx}/vedsb/common/sms/saveMessage";
				//这里暂时就邮寄单的ids 放在txfsid这个变量中.让它带到controller,再去处理.
				var data={"jshm":jshm,"yqfssj":fssj,"mbbh":dxlx,"fsnr":fsnr,"ddbh":ddbh};
				$.ajax({
	        	 		type:"post",
	  					url:url,
	  					data:data,
	  					success:function(result){
	  						if("1" == result){
	  							layer.msg("短信发送成功！",2,1);
	  						}else{
	  							layer.msg("短信发送失败！",2,0);
	  						}
	  						window.parent.location.reload();
	  					}
	        	 	});
			}
    	</script>
  </head>
  <body>
    <div class="container">
 		<div id="forms">
        	<div class="box">
          		<div class="box_border">
            		<div class="box_center">
            			<form name="messageForm" method="post">
							<input type="hidden"  name="close"  value="true" />
							<input type="hidden" name="submitForm" value="listForm">
							<table width="98%" height="98%" border="0" cellpadding="0" cellspacing="0" class="list_table">
							 	<tr>
									<td class="td_right">接收手机号码：</td>
									<td class="tab_in_td_f">
										<input type="text" id="jshm" name="jshm" value="${mobiles}" style="width:141px;height: 20px;" onblur="checkPhone(this.value)" /> &nbsp;<font color="red">*</font>
										&nbsp;多个号码之间用","隔开
									</td>
								</tr>
								<tr>
							      <td class="td_right">要求发送时间：</td>
							      <td class="tab_in_td_f">
							      	<input type="text" id="fssj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:143px;height: 22px;" class="Wdate" />
							        &nbsp;<font color="red">*</font></td>
							    </tr>
							    <tr>
							      <td class="td_right">短信类型：</td>
							      <td class="tab_in_td_f">
							      	<select name="dxlx" id="dxlx" style="width:145px;height: 24px;" onchange="showNr(this);" >
							          <option value="">==选择类型==</option>
							          <c:forEach var="v" items="${mblist}">
							          	<option value="${v.mbbh}" id="${v.mbbh}" name="${v.nrsz}">${v.mbmc}
							          </c:forEach>
							        </select>
							        &nbsp;<font color="red">*</font><span id="tpfth"></span> </td>
							    </tr>
							    <tr>
							      <td class="td_right">发送内容：<br><span id = "readonly" style="color: red;display: none">（不能修改）</span></td>
							      <td>
							      	<textarea name="fsnr" id="fsnr" cols="60" rows="10" onkeyup="smslen(this.value)"></textarea>
							        &nbsp;<font color="red">*</font> <br>
							       <span id="smslength" name="smsnrlen"></span>
							       </td>
							    </tr>
							  </tr>  
								<tr>
									<td colspan="2" align="center">
										<input type="button"  id="searchFormButton" name="button" value=" 保 存 " class="ext_btn ext_btn_success" onclick="saveSms();">
										<input type="button"  id="searchFormButton" name="button" value=" 关 闭 " class="ext_btn ext_btn_success" onclick="hideQuickDiv()">
									</td>               
								</tr>
							</table>
							<input type="hidden" id="ddbh" name="ddbh" value="${ddbh}"> 
						</form>
            		</div>
            	</div>
            </div>
        </div>
    </div>
  </body>
</html>
