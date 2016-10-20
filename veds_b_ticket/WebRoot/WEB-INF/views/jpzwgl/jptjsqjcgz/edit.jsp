<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>追位基础设置</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<style type="text/css">
	.hxinput {width:50px;border-top: none;border-left: none;border-right: none;border-color:#1C86EE;outline:medium;}
	.td_right{width:230px;}
	.disabled_style{background-color:#D6D6D6;}
</style>
<script type="text/javascript">
var validator;
$(function(){
	validator = $("#wdxsfajcgz").Validform({
		tiptype:3,
		beforeSubmit:function(curform){
			layer.load('系统正在处理您的操作,请稍候!');
		}
	});
	if("${isSuccess}"=="0"){
		layer.msg('保存成功!',3,1);
	}else if("${isSuccess}"=="-1"){
		layer.msg('保存失败!',3,3);
	}
	if(""!="${isSuccess}"){
		loadContent('3');
	}
});
function saveTjsqJcgz(){
	validator.submitForm(false);
}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/jpzwgl/gzlable.jsp" %>
	<div class="container clear">
	 <div id="forms">
	        <div class="box">
	          <div class="box_border">
	            <div class="box_center">
	              <form action="${ctx}/vedsb/jpzwgl/jptjsqjcgz/saveJcgz" class="jqtransform" id="wdxsfajcgz" method="POST">
	              <input type="hidden" name="turningUrl" value="${ctx}/vedsb/jpzwgl/jptjsqjcgz/toedit?lx=3"  id="turningUrl"/>
	               <input type="hidden" name="lx" value="${param.lx}">
	              <input type="hidden" name="id" id="id" value="${param.id }">
	              <c:if test="${not empty jcgzList && fn:length(jcgzList)>0}">
	              	<table class="form_table pt15 pb15" id="form_table" width="100%" border="0" cellpadding="0" cellspacing="0">
	                <c:forEach items="${jcgzList }" var="jcgz" varStatus="vs">
	                  ${jcgz.zdxz}
	                  <tr id="${jcgz.zdywm}">
		                  <td class="td_right" style="width:160px;"><span id="${jcgz.zdywm}_mcspan">${jcgz.zdzwmqz}</span></td>
		                  <td>
		                    <input type="hidden" name="${jcgz.zdywm}szid" value="${jcgz.szid}"/>
		                    <c:set var="sxz" value="${not empty jcgz.zdz ? jcgz.zdz : jcgz.mrz }"/>
		                  	<c:choose>
		                  		<c:when test="${(jcgz.zdlx eq 'radio') or (jcgz.zdlx eq 'checkbox')}">
		                  			<c:set var="mjxz" value="${fn:split(jcgz.mjxz,'/')}"></c:set>
		                  			<c:forEach items="${fn:split(jcgz.mjxmc,'/') }" var="radioitem" varStatus="radiovs">
		                  				<c:choose>
		                  					<c:when test="${jcgz.zdlx eq 'checkbox'}">
		                  						<c:set var="ifcheck" value="${fn:indexOf(sxz,mjxz[radiovs.index])>-1 ? 'checked' : '' }"></c:set>
		                  					</c:when>
		                  					<c:otherwise>
		                  						<c:set var="ifcheck" value="${sxz eq mjxz[radiovs.index] ? 'checked' : '' }"></c:set>
		                  					</c:otherwise>
		                  				</c:choose>
		                  				<input type="${jcgz.zdlx}" id="${jcgz.zdywm}_${radiovs.index}" name="${jcgz.zdywm }"  value="${mjxz[radiovs.index]}"  ${ifcheck} ${jcgz.zdsx}/>${radioitem}
		                  			</c:forEach>
		                  		</c:when>
		                  		<c:otherwise>
		                  			<input type="${jcgz.zdlx}" id="${jcgz.zdywm}text" name="${jcgz.zdywm}" value="${sxz}" ${jcgz.zdsx}/><span id="${jcgz.zdywm}_dwspan">${jcgz.dwmc }</span>&nbsp;
		                  			<span>${jcgz.zdzwmhz}</span>
		                  		</c:otherwise>
		                  	</c:choose>
		                  	<c:if test="${not empty jcgz.zdsm}">
		                  		<span id="${jcgz.zdywm}_zdsm"  style="color:gray;">(${jcgz.zdsm})</span>
		                  	</c:if>
		                  </td>
	                  </tr>
	                </c:forEach>
	                 <tr id="bnttr">
	                   <td class="td_right">&nbsp;</td>
	                   <td class="">
	                     <input type="button" name="button" onclick="saveTjsqJcgz();" class="ext_btn ext_btn_submit" value="保存"/> 
	                     <input type="button" value="返回" onclick="location.href='javascript:history.go(-1)';" class="ext_btn">
	                   </td>
	                 </tr>
	               </table>
	              </c:if>
	              <c:if test="${empty  jcgzList or fn:length(jcgzList)<1}">
	              	<table><tr><td>没有设置相关数据</td></tr></table>
	              </c:if>
	               </form>
	            </div>
	          </div>
	        </div>
	     </div>
    </div>
</body>
</html>
