<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
	<head>
	    <title>Q信息处理</title>
		<style type="text/css">
			.blackbold{
				font-weight:bold;
				font-color:#000000;
			}
			.smokebold{
				font-weight:nomal;
				color:#808080;
			}
			.inputnum{
				width:60px;
			}
		</style>
		<script type="text/javascript">
			//选择短信类型 自动填充模板
			function changeDdlx(obj){
				$("#fsnr").val("");
				$("#mbbh").val("");
				var id = $(obj).find("option:selected").attr("id");
				var url="${ctx}/vedsb/qxxgl/bqinfo/getSmsmbById?id="+id;
				var ii = layer.load('系统正在处理您的操作,请稍候!');
				$.ajax({
		  				type:"post",
		  				url:url,
		  				success:function(result){
		             	 	layer.close(ii);
		             	 	if(result.CODE == '0'){
		             	 		var sms = result.DATA;
		             	 		$("#fsnr").val(sms.nrsz);
		             	 		$("#mbbh").val(sms.mbbh);
		             	 	}else{
		             	 		layer.msg(result.MSG,2,5);
		             	 	}
		  				}
		  			});
			}
			
			//发送短信 电话通知  控制
			function dtypeCheck(){
				$("#tr1").find("input").attr("disabled",false);
				$("#tr2").find("select").attr("disabled",false);
				$("#tr3").find("textarea").attr("disabled",false);
				var $dtype =  $("input[name=dtype]:checked");
				if($dtype.length == '0' || ($dtype.length == '1' && $dtype.val() == '3')){
					$("#tr1").find("input").attr("disabled",true);
					$("#tr2").find("select").attr("disabled",true);
					$("#tr3").find("textarea").attr("disabled",true);
				}
			}
			
			//保存
			function tosearch(){
				var clQk = $("#clQk").val();
				var dxlx = $("#dxlx").val();
				var fsnr = $("#fsnr").val();
				var clDatetime=$("#clDatetime").val();
				if(clQk == ''){
					alert("处理情况不能为空");
					return;
				}
				if(clDatetime == ""){
					alert("要求发送时间不能为空");
					return;
				}
				if(!$("#dxlx").attr("disabled") && dxlx == ""){
					alert("短信类型不能为空");
					return;
				}
				if(!$("#fsnr").attr("disabled") && fsnr == ""){
					alert("发送内容不能为空");
					return;
				}
				
				var url="${ctx}/vedsb/qxxgl/bqinfo/bqinfoSave";
				var ii = layer.load('系统正在处理您的操作,请稍候!');
				$.ajax({
		  				type:"post",
		  				url:url,
		  				data:$("#searchForm").serialize(),
		  				success:function(result){
		             	 	layer.close(ii);	
		             	 	if(result.CODE == '0'){
		             	 		//刷新父页面
		             	 		window.parent.$("#searchFormButton").click();
		             	 		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        		parent.layer.close(index);
		             	 	}else{
		             	 		layer.msg(result.MSG,2,5);
		             	 	}
		  				}
		  			});
			}
			
		function closeWindow(){
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);
		}
		</script>
	</head>
<body>
	<div class="container">
	<div id="search_bar" class="mt10">
 	<div class="box">
    <div class="box_border">
     <div class="box_center pt10 pb10">
	<form action="" id="searchForm" name="searchForm" method="post">
		<input type="hidden" id="mbbh" name="mbbh" value="">
		<input type="hidden" id="ids" name="ids" value="${param.ids }">
		<table style="width:550px" border="0" cellspacing="0" cellpadding="0" align="center" class="table01">
			<tr>
				<td colspan="2" style="padding-left:100px">共选择了${param.size}条记录</td>
		 	</tr>
			<tr>
				<td style="width:160" align="right">处理情况：</td>
			 	<td style="width:640">
			 		<input type="text" id="clQk" name="clQk" class="inputred required" style="width:400px"/>&nbsp;<span style="color:red">*</span>
			    </td>
		 	</tr>
		    <tr>
		    	<td align="right">Q信息状态：</td>
		    	<td>
		    		<label title="处理被选中的信息，并且标记为已处理状态">
		    			处理完成<input type="radio" name="clZt" value="1" />
		    		</label>&nbsp;&nbsp;
		    		<label title="进行处理操作，但是订单状态为处理中">
		    			处理中
		    			<input type="radio" name="clZt" value="2" checked/>
		    		</label>&nbsp;&nbsp;
		    	</td>
		    </tr>		 	
		 	<tr>
		 		<td colspan="2" style="padding-left:100px">
		 			<input type="checkbox" value="1" name="dtype" id="chk1" checked onclick="dtypeCheck()">发短信给乘机人
		 			<input type="checkbox" value="2" name="dtype" id="chk2" onclick="dtypeCheck()">发短信给联系人
		 			<input type="checkbox" value="3" name="dtype" id="chk3">电话通知
		 		</td>
		 	</tr>
			<tr id="tr1">
		      <td align="right">要求发送时间：</td>
		      <td>
		      	<input type="text" id="clDatetime" name="clDatetime" value="${empty param.ksrq ? vfn:longDate() : param.ksrq}" style="width:173px;height:22px;" class="input-text Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		      </td>
		    </tr>
		    <tr id="tr2">
		      <td align="right">短信类型：</td>
		      <td>
		      	<select id="dxlx" name="dxlx" class="select" style="width:175px;height:24px;" onchange="changeDdlx(this);">
		      		<option value="" selected>==请选择==</option>
		      		<c:forEach var="sms" items="${smsList }">
		      			<option value="${sms.mbfl }" id="${sms.id }" ${param.ddlx eq sms.mbfl ? 'selected':'' }>${sms.mbmc }</option>
		      		</c:forEach>
		      	</select>
		        &nbsp;<font color="red">*</font><span id="tpfth"></span> 
		       </td>
		    </tr>
		    <tr id="tr3">
		      <td align="right">发送内容：<br><span id = "readonly" style="color: red;display: none">（不能修改）</span></td>
		      <td>
		      	<textarea name="fsnr" id="fsnr" cols="55" rows="8" class="inputtextarea required max-length-4000" ></textarea>
		        &nbsp;<font color="red">*</font> <br>
		        <span id="smslength"></span>
		       </td>
		    </tr>
		  </tr>  
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="searchFormButton" name="button" value="保存" class="ext_btn ext_btn_submit" onclick="tosearch();"/>
					<input type="button"  value=" 关 闭 " class="ext_btn ext_btn_submit" onClick="closeWindow()">
				</td>               
			</tr>
		</table>
	</form>
	</div></div></div></div>
</body>
</html>
