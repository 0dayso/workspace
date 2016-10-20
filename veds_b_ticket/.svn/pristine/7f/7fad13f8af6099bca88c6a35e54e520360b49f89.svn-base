<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	function toFfqy(obj){
		var id = ${zhBean.id };
		var zfzh= ${zhBean.zfzh };
		var zfzhmm = $("#zfzhmm").val();
		var czykz = ${zhBean.czykz };
		var czykzmm = $("#czykzmm").val();
	    $(obj).attr("disabled","disabled");
	    var data={"id":id,"zfzh":zfzh,"zfzhmm":zfzhmm,"czykz":czykz,"czykzmm":czykzmm};
	    $.post("${ctx}/vedsb/b2bsz/jpb2bzfzh/etripAuth", data, function(result){
	    	$(obj).removeAttr("disabled");
		 	if(result.indexOf("失败")>=0){
		   		alert(result);
		   		return ;
		   	}else{
				$("#hkgsmainIframe",parent.document).attr("src","${ctx}/vedsb/b2bsz/jpb2bzfzh/viewlist?zflx=7");
			}
	    });
  	}
	
</script>
</head>
<body>
<div class="container clear">
	<div class="box_border">
	<input name="id" type="hidden" value="${zhBean.id }" >
	<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<td class="td_right">支付帐号</td>
			<td class="td_left">${zhBean.zfzh }
				<input id="zfzh" value="${zhBean.zfzh }" type="hidden">
			</td>
			<td class="td_right">支付帐号密码</td>
			<td class="td_left">
				<input id="zfzhmm" type="text">
			</td>
		</tr>
		<c:if test="${not empty zhBean.czykz}">
			<td class="td_right">操作员子账号</td>
			<td class="td_left">${zhBean.czykz }
				<input id="czykz" value="${zhBean.czykz }" type="hidden">
			</td>
			<td class="td_right">操作员密码</td>
			<td class="td_left">
				<input type="text" id="czykzmm">
			</td>
		</c:if>
		<tr>
			<td colspan="4" align="center"><br>
				<input type="button" class="ext_btn ext_btn_submit" value="获得身份认证密钥 " onclick="toFfqy(this);">
			</td>
		</tr>
	</table>
	</div>
</div>
</body>
</html>