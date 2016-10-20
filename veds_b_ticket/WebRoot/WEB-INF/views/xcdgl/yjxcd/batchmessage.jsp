<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title></title>
    	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
    	<script type="text/javascript">
    		var oldlen=${param.size};
			//取消邮寄  关闭当前弹窗
			function hideQuickDiv(){
				var index=parent.layer.getFrameIndex();
				parent.layer.close(index);
			}
			//收件人手机离开焦点时验证手机号码 格式
			function checkPhone(phones){
				if(!phones||phones.trim==''){
					layer.msg("收件人的手机不能为空！",1,0);
						return ;
				}
				var arr=phones.split(",");
				var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
				var reg2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
				if(arr.length>oldlen){
					layer.msg("收件人的手机号个数不能大于原订单数！",1,0);
						return ;
				}
				for(var i=0;i<arr.length;i++){
					if(!reg.test(arr[i])&&!reg2.test(arr[i])){
						layer.msg("收件人的手机格式或固定电话格式不正确！",1,0);
						return ;
					}
				}
					
			}
			//改变短信模板时,修改短信内容
			function showNr(op){
				var sid = op.options[op.selectedIndex].getAttribute("id");
				var nr = op.options[op.selectedIndex].getAttribute("name");
				var ddbhs = document.getElementById("ddbhs").value;
				if(ddbhs.indexOf(",")>0){
					var ddarr=ddbhs.split(",");
					if(ddarr.length<2){
						getRealNr(nr,ddarr[0]);
					}else{
						document.getElementById("fsnr").value=nr;
					}
				}else{
					getRealNr(nr,ddbhs);
				}
				document.getElementById("smslength").innerHTML="目前已有"+nr.length+"个字";
			}
			//每次键盘打个字就会去计算 短信内容里的字数
			function smslen(nr){
				document.getElementById("smslength").innerHTML="目前已有"+nr.length+"个字";
			}
			
			function getRealNr(nr,ddbh){
				var url="${ctx}/vedsb/common/sms/getSmsNr";
				$.ajax({
	        	 		type:"post",
	  					url:url,
	  					data:{"nr":nr,"ddbh":ddbh},
	  					success:function(result){
	  						document.getElementById("fsnr").value=result;
	  					}
	        	 	});
			}
			//保存
			function saveSms(){
				var jshm=document.getElementById("jshm").value;
				var fssj=document.getElementById("fssj").value;
				var dxlx = document.getElementById("dxlx").value;
				var fsnr=document.getElementById("fsnr").value;
				var ids = document.getElementById("ids").value;
				var ddbhs = document.getElementById("ddbhs").value;
				if(!jshm||jshm.trim==''){
					layer.msg("收件人的手机不能为空！",1,0);
						$("#jshm").focus();
						return ;
				}else{
					var arr=jshm.split(",");
					var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
					var reg2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
					if(arr.length>oldlen){
						layer.msg("收件人的手机号个数不能大于原订单数！",1,0);
							$("#jshm").focus();
							return ;
					}
					for(var i=0;i<arr.length;i++){
						if(!reg.test(arr[i])&&!reg2.test(arr[i])){
							layer.msg("收件人的手机格式或固定电话格式不正确！",1,0);
							$("#jshm").focus();
							return ;
						}
					}
				}
				if(!fssj){
					layer.msg("发送短信时间不能为空！",2,0);
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
            			layer.msg("发送时间要比现在的时间大!",2,0);
            			return "发送时间要比现在的时间大!";
            		}
				}
				if(!dxlx||dxlx.trim()==""){
					layer.msg("短信模板必填!",1,0);
            			return ;
				}
				if(!fsnr||fsnr.trim()==""){
					layer.msg("短信内容必填!",1,0);
					$("#fsnr").focus();
            		return ;
				}
				var jshm=document.getElementById("jshm").value;
				var fssj=document.getElementById("fssj").value;
				var dxlx = document.getElementById("dxlx").value;
				var fsnr=document.getElementById("fsnr").value;
				var ids = document.getElementById("ids").value;
				var ddbhs = document.getElementById("ddbhs").value;
				var url="${ctx}/vedsb/common/sms/batchMessage";
				//这里暂时就邮寄单的ids 放在txfsid这个变量中.让它带到controller,再去处理.
				var data={"jshm":jshm,"yqfssj":fssj,"mbbh":dxlx,"fsnr":fsnr,"ddbh":ddbhs,"txfsid":ids};
				$.ajax({
	        	 		type:"post",
	  					url:url,
	  					data:data,
	  					success:function(result){
	  						if("1" == result){
	  							layer.msg("批量短信发送成功！",2,1);
	  						}else{
	  							layer.msg("批量短信发送失败！",2,0);
	  						}
	  						window.parent.toSearch("${param.flag}");
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
							<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
								<tr>
									<td colspan="2" style="padding-left:100px">共选择了${param.size}条记录</td>
							 	</tr>
							 	<tr>
									<td class="td_right">接收手机号码</td>
									<td class="tab_in_td_f">				  
										<input type="text" id="jshm" name="jshm" value="${mobiles}" style="width: 400px" onblur="checkPhone(this.value)" /> &nbsp;<font color="red">*</font>
										&nbsp;多个号码之间用","隔开
									</td> 
								</tr>
								<tr>
							      <td class="td_right">要求发送时间：</td>
							      <td class="tab_in_td_f">
							      	<input type="text" id="fssj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:200px"/>
							        &nbsp;<font color="red">*</font> </td>
							    </tr>
							    <tr>
							      <td class="td_right">短信类型：</td>
							      <td class="tab_in_td_f">
							      	<select name="dxlx" id="dxlx" style="width:200px" onchange="showNr(this);" >
							          <option value="">==选择类型==</option>
							          <c:forEach var="v" items="${mblist}">
							          	<option value="${v.id}" id="${v.id}" name="${v.nrsz}">${v.mbmc}
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
										<input type="button"  value=" 保 存 " class="ext_btn ext_btn_success" onclick="saveSms();">
										<input type="button"  value=" 关 闭 " class="ext_btn ext_btn_success" onClick="hideQuickDiv()">
									</td>               
								</tr>
							</table>
							<input type="hidden" id="ids" name="ids" value="${ids}"> 
							<input type="hidden" id="ddbhs" name="ddbhs" value="${ddbhs}"> 
						</form>
            		</div>
            	</div>
            </div>
        </div>
    </div>
  </body>
</html>
