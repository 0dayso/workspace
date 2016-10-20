<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>追位基础设置</title>
<script type="text/javascript" src="${ctx}/static/core/data/gn_city.js?v=${VERSION}"></script>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
var validator;
$(function(){
	validator = $("#hbydForm").Validform({
		tiptype:3,
		beforeSubmit:function(curform){
			layer.load('系统正在处理您的操作,请稍候!');
		}
	});
	$("#gn_orgCity_m").autocompleteGnCity("gn_orgCity");
	$("#gn_orgCity_m2").autocompleteGnCity("gn_orgCity2");
	if('${entity.hc }'){
		$("#gn_orgCity_m").val('${entity.cfcitymc}');
		$("#gn_orgCity_m2").val('${entity.ddcitymc}');
		$("#gn_orgCity").val('${entity.cfcity}');
		$("#gn_orgCity2").val('${entity.ddcity}');
		
	}
});
function saveHbyd(){
	if($("#error").val()){
		alert($("#error").val());
	}else{
		validator.submitForm(false);
	}
}

//根据类型隐藏显示输入框
function displayByLx(val){
	if(val == '3'){
		$("#bhhb").css("display","none");
		$("#yjqf").css("display","none");
		$("#yjdd").css("display","none");
	}else{
		$("#bhhb").css("display","");
		$("#yjqf").css("display","");
		$("#yjdd").css("display","");
	}
}

//根据原航班号和起飞时间获取订单信息
function gethcxx(){
	var hbh=$("#hbh").val();
	var cfrq=$("#cfrq").val();
	if(hbh == null || hbh == "" || hbh == undefined){
		return null;
	}
/*	var url="${ctx}/vedsb/jphbyd/jphbyd/getHbxx?hbh="+hbh+"&cfrq="+cfrq;
	$.ajax({
		type:"post",
		url:url,
		success:function(data){
			if(data){
				$("#by2").val(data.ddsj);
				$("#gn_orgCity_m").val(data.cfcitymc);
				$("#gn_orgCity_m2").val(data.ddcitymc);
				$("#gn_orgCity").val(data.cfcity);
				$("#gn_orgCity2").val(data.ddcity);
				$("#error").val(data.error);
			}
		}
	}); */
}

//关闭弹出层
function closeHbyd(){
	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
}
</script>
</head>
<body>
	<div class="container clear">
		<div class="box_border">
			<form action="${ctx}/vedsb/jphbyd/jphbyd/save" class="jqtransform" id="hbydForm" method="POST">
			<input type="hidden" name="id" id="id" value="${entity.id }">
			<input type="hidden" name="hc" id="hc" value="${entity.hc }">
			<input type="hidden" name="callback" value="parent.location.reload();"/>
			<input type="hidden" name="closeDiv" value="true"/>
			<input type="hidden" name="error" id="error">
			<table class="form_table pt15 pb15" border="0" cellpadding="0" cellspacing="0">	
				<tr>     
					<td class="td_right" style="width:25%">原航班号:</td>
					<td >
						<input type="text" id="hbh" name="hbh" value="${entity.hbh}" maxlength="10"  datatype="*" nullmsg="请输入..." style="width: 80px" onkeyup="value =value.toUpperCase();"  ${not empty entity.id ? "disabled" : '' }/><label style="color: red"> * </label>
					</td>               
				</tr>
				<tr>     
					<td class="td_right">原航班起飞时间:</td>
					<td >
						<input type="text" id="cfrq" name="cfrq" value="${entity.cfrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate" maxlength="20"  datatype="*" nullmsg="出发日期不能为空" style="width: 30%;" onblur="gethcxx();" ${not empty entity.id ? "disabled" : '' }/><font color="red">*</font>
					</td>               
				</tr>
				<tr>
					<td class="td_right">原航班到达时间:</td>
					<td >
						<input type="text" id="by2" name="by2" value="${entity.by2 }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate" maxlength="20" style="width: 30%;" ${not empty entity.id ? "disabled" : '' }/>
					</td>
				</tr>
				<tr>
					<td class="td_right">出发城市:</td>
					<td >
						<input type="text" id="gn_orgCity_m" size="20" datatype="*" style="width: 20%;" ${not empty entity.id ? "disabled" : '' }>
                   	  		<input type="hidden" id="gn_orgCity" name="cfcity" value="${param.cfcity }" >
                   	  		<font color="red">*</font> &nbsp;到达城市:
						<input type="text" id="gn_orgCity_m2" size="20" datatype="*" style="width: 20%;" ${not empty entity.id ? "disabled" : '' }>
                   	 		<input type="hidden" id="gn_orgCity2" name="ddcity" value="${param.ddcity }" >
                   	 		<font color="red">*</font>
					</td>
				</tr>
				<tr>
			  		<td class="td_right">类型:</td>
			  		<td>
			  			<input type="radio" name="hblx" value="2" onclick="displayByLx(2)" ${entity.hblx eq '2' || empty entity.hblx ? 'checked' : ''}/>延误
			  			<input type="radio" name="hblx" value="1" onclick="displayByLx(1)" ${entity.hblx eq '1' ? 'checked' : ''}/>提前
			  			<input type="radio" name="hblx" value="3" onclick="displayByLx(3)" ${entity.hblx eq '3' ? 'checked' : ''}/>取消
			  		</td>
			  	</tr>
				<tr id="bhhb" style="${entity.zt eq '3' ? 'display:none' : '' }">
					<td class="td_right">保护航班号:</td>
					<td >
						<input type="text" id="by1" name="by1" value="${entity.by1}" style="width:90px;" onkeyup="value =value.toUpperCase();" />
					</td>
				</tr>
				<tr id="yjqf" style="${entity.zt eq '3' ? 'display:none' : '' }">     
					<td class="td_right">预计起飞时间:</td>
					<td >
						<input type="text" name="yjCfsj" value="${entity.yjCfsj}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate" maxlength="20"  style="width: 30%;"/>
					</td>               
				</tr>
				<tr id="yjdd" style="${entity.zt eq '3' ? 'display:none' : '' }">
					<td class="td_right">预计到达时间:</td>
					<td >
						<input type="text" name="yjDdsj" value="${entity.yjDdsj}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate" maxlength="20"  style="width: 30%;"/>
					</td>
				</tr>
				<tr>
				  	<td class="td_right">航变原因:</td>
				  	<td>
				  		<textarea name="ydyy" rows="3" cols="40" style="resize: none;" >${entity.ydyy}</textarea>
				  	</td>
			  	</tr>
			  	<tr>
							<td colspan="2" align="center">
								<input type="button" onclick="saveHbyd()" class="ext_btn ext_btn_submit" value="保 存">&nbsp;
						<input type="button" onclick="closeHbyd()" class="ext_btn ext_btn_error" value="关闭">
							</td>
						</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>
