<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    <script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
	<script type="text/javascript">
		//关闭当前弹窗
    		function closepj(){
    			var index=parent.layer.getFrameIndex();
				parent.layer.close(index);
    		}
    		var validator;
    		$(function(){
    			validator = $("#editform").Validform({
						tiptype:3
					});
    		});
    		
    		function toSave(){
    			validator.submitForm(false);
    		}
	</script>
</head>
<title>调整时间</title>
<body>
<form name="saveForm" action="${ctx}/vedsb/jpcwgl/jphistory/jpCpsjSave" method="post" id="editform">
 <input type="hidden" name="callback"  value="parent.refresh()" />
 <input type="hidden" name="closeDiv" value="true"/>
 <input type="hidden" name="ref" value="true"/>
<input type="hidden" name="pnr" value="${param.pnr}" />
<input type="hidden" name="ddbh" value="${param.ddbh}" />
<input type="hidden" name="pzzt" value="${param.pzzt}" />
<input type="hidden" name="submitForm" value="listForm" />
<table border="0" cellpadding="0" cellspacing="0" class="list_table">
  <tr>
    <th>PNR</th>
 	<td colspan="2" >${param.pnr}</td>
  </tr>
  
  <tr>
 	<th>票号</th>
 	<th>原出票时间</th>
 	<th><span title="修改的出票时间要小于当前日期">修改出票时间</span></th>
  </tr> 
  <c:set var="tklength" value="${fn:length(list)}" ></c:set>
  <input type="hidden" name="ddbh" value="${param.ddbh}">
  <c:forEach var="vc" items="${list}" varStatus="status" >
  <tr>
 	<td style="text-align:center">${vc.TKNO}</td> 
 	<td style="text-align:center">${vc.CP_DATETIME}</td>
    <td style="text-align:left" >
        <input id="itkno" name="itkno" type="hidden" value="${vc.TKNO}"  />
        <input name="cp_datetime" type="hidden" value="${vc.CP_DATETIME}" />
		<input id="kssj" name="kssj_${vc.TKNO}" type="text" onblur="value = replaceAll(value,'%','')" onFocus="WdatePicker()">
		<c:if test="${tklength gt 1 and status.count eq 1}"><a href="###" onClick="toTbTknoList('${vc.TKNO}')" >同步</a></c:if>
    </td>
  </tr> 
 </c:forEach>
  <tr>
 	<th>修改原因</th>
 	<td colspan="2" >
		<textarea class="required max-length-200" title="" name="xgyy" cols="42" rows="3" datatype="s1-500"></textarea>
		<font color="red">*</font>
	</td>
  </tr>
 
  <tr>
 	<td colspan="3" align="center">
 		<input type="button" class="ext_btn ext_btn_submit" value="保  存" onclick="toSave();" />
   		<input type="button" name="button" onclick="closepj()" class="ext_btn" value="关闭">
 	</td>
  </tr>
</table>
</form>

<table class="list_table" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="sugtitle">
          温馨提示：
    </td>
  </tr>
  <tr>
  	<td class="sugcontent" style="color: red">
  	  1、调整范围不能大于7天<br>
  	</td>
  </tr>
</table>
</body>
</html>