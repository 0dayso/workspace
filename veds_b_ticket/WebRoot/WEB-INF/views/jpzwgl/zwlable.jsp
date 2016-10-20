<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1');" class="${empty param.zwlx or param.zwlx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">系统订单追位管理</a></li>
		<li lx='3' onclick="loadContent('3');" class="${param.zwlx eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">外部订单追位管理</a></li>
	</ul>
	<div class="clear"></div>
</div>
<script type="text/javascript">
function loadContent(lx){
	if(lx=='1'){
		window.location.href='${ctx}/vedsb/jpzwgl/jptjsq/viewzwdllist?zwlx=1';
	}else if(lx=='3'){
		window.location.href='${ctx}/vedsb/jpzwgl/jptjsqdr/viewlist?zwlx=3';
	}
}
</script>