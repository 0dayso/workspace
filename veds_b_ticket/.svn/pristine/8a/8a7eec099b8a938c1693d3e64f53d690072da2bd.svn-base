<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>国际DOCA设置</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
	var validator;
	$(function (){
		validator = $("#searchForm").Validform({
				tiptype:3
			});
	});
	
	
	//关闭页面
	function closePage(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
	//保存
	function save(){
		if(validator.check()){
			var url = "${ctx}/vedsb/jpzdcp/jpgjdocasz/savesz";
			var ii = layer.load('系统正在处理你的操作,请稍后!');
			$.ajax({
				url : url,
				data : $("#searchForm").serialize(),
				success: function(result){
					layer.close(ii);
	     			if(result == "0"){
	     				layer.msg('保存成功!',2,1);
	     				window.parent.document.getElementById("searchForm").submit();
	     				closePage();
	     			}else{
	     				layer.msg('保存失败!',2,3);
	     			}
	    		}
			});
		}else{
				validator.submitForm(false);
			}
	}
	
	//验证城市匹配键格式及唯一性
	function yzCsszm(obj){
		var id = $("input[name=id]").val();
		var csszms = $(obj).val();
		var reg = /^[A-Z]{3}$/;
		if(csszms.indexOf("/") == "-1"){
			if(!reg.test(csszms)){
				layer.msg('格式不正确，请重新输入!',2,3);
				return;
			}
		}
		var arr = csszms.split("\/");
		for(var i=0;i<arr.length;i++){
			if(arr[i]!='' && !reg.test(arr[i])){
				layer.msg('格式不正确，请重新输入!',2,3);
				return;
			}
		}
		var url = "${ctx}/vedsb/jpzdcp/jpgjdocasz/yzCsszm";
		$.ajax({
				url : url,
				data:{csszms:csszms,id:id},
				success: function(result){
	     			if(result == '-1'){
	     				layer.msg('已设置过，请重新输入!',2,3);
	     				$(obj).focus();
	     			}
	    		}
			});
	}
</script>
</head>
<body>
	<form action="" id="searchForm" name="searchForm" method="post">
		<input type="hidden" name="id" value="${entity.id }">
		<table class="list_table" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="right">城市匹配键</td>
				<td colspan="2">
					<input type="text" name="csszm" size=18 value="${entity.csszm}" datatype="*" onblur="value=$.trim(this.value).toUpperCase();yzCsszm(this);"/>
	            	<font color="red">*</font>
	            	<font color="gray">（城市三字码，多个以/分隔）</font>
				</td>
			</tr>
			<tr>
				<td align="right"></td>
				<td align="center">
					居住地（R）
				</td>
				<td align="center">
					目的地（D）
				</td>
			</tr>
			<tr>
				<td align="right" width="140px">
					国籍<font color="gray">（国家二字码）</font>
				</td>
				<td>
					<input type="text" name="rGj" size=18 value="${entity.rGj}" datatype="*,/^[A-Za-z]{2}$/" onblur="value=$.trim(this.value).toUpperCase();"/>
	            	<font color="red">*</font>
	            </td>
	            <td>
					<input type="text" name="dGj" size=18 value="${entity.dGj}" datatype="*,/^[A-Za-z]{2}$/" onblur="value=$.trim(this.value).toUpperCase();"/>
	            	<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td align="right">详细地址</td>
				<td>
					<input type="text" name="rXxdz" size=18 value="${entity.rXxdz}" datatype="*" onblur="value=$.trim(this.value);"/>
	            	<font color="red">*</font>
	            </td>
	            <td>
					<input type="text" name="dXxdz" size=18 value="${entity.dXxdz}" datatype="*" onblur="value=$.trim(this.value);"/>
	            	<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td align="right">城市名称</td>
				<td>
					<input type="text" name="rCsmc" size=18 value="${entity.rCsmc}" datatype="*" onblur="value=$.trim(this.value).toUpperCase();"/>
	            	<font color="red">*</font>
	            </td>
	            <td>
					<input type="text" name="dCsmc" size=18 value="${entity.dCsmc}" datatype="*" onblur="value=$.trim(this.value).toUpperCase();"/>
	            	<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td align="right">所在省市州信息</td>
				<td>
					<input type="text" name="rCzxx" size=18 value="${entity.rCzxx}" datatype="*" onblur="value=$.trim(this.value).toUpperCase();;"/>
	            	<font color="red">*</font>
	            </td>
	            <td>
					<input type="text" name="dCzxx" size=18 value="${entity.dCzxx}" datatype="*" onblur="value=$.trim(this.value).toUpperCase();;"/>
	            	<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td align="right">邮政编码</td>
				<td>
					<input type="text" name="rYzbm" size=18 value="${entity.rYzbm}" datatype="*" onblur="value=$.trim(this.value);"/>
	            	<font color="red">*</font>
	            </td>
	            <td>
					<input type="text" name="dYzbm" size=18 value="${entity.dYzbm}" datatype="*" onblur="value=$.trim(this.value);"/>
	            	<font color="red">*</font>
				</td>
			</tr>
		</table>
	</form>		
	<br>
	<div align="center">
		<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="save();"/>
		<input type="button" name="button" onclick="closePage()" class="ext_btn ext_btn_submit" value="关闭">
	</div>
</body>

</html>

