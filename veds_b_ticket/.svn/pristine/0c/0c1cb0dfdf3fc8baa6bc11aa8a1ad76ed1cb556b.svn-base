<%@page language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>导单入库</title>
  </head>
  
  <body>
  <link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
  <script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
  <script type="text/javascript">
	var validator;
	$(function(){
		validator = $("#ddrkForm").Validform({
			tiptype:3,
		});
	})
	function toSave(){
		validator.submitForm(false);
		var ii = layer.load('入库中……');
	}
  </script>
  	<form action="${ctx}/vedsb/jpddsz/jpddsz/ddrk" id="ddrkForm" method="post" name="ddrkForm">
  	<input type="hidden" name="closeDiv" value="true"/>
    <input type="hidden" name="callback"  value="parent.refresh()" />
    <table width="300px" border="0" cellpadding="0" cellspacing="0" class="list_table">
    	<tr>
    		<td class="">
        		网店<select name="wdid" id="wdid" class="select" datatype="*" request="true">
              	 	<option value="">==请选择导单网店==</option>
              	 	<c:forEach items="${wdzlszList}" var="wdzlsz">
                	<option value="${wdzlsz.id }">${wdzlsz.wdmc }</option> 
              	 	</c:forEach>
                </select>
    		</td>
    		<td class="">
    			外部单号<input type="text"  id="wbdh" name="wbdh" size="15" class="input1" datatype="*" value="${param.wbdh}"  />
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2" align="center">
    			<input type="button" value="手工导单" class="ext_btn ext_btn_submit" onclick="toSave();">
    			<c:if test="${not empty errMsg}"><font color="red">${errMsg}</font></c:if>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2" >
				<font color="gray">携程网店要传入的是出票单号</font>
    		</td>
    	</tr>
    </table>
    </form>
  </body>
</html>
