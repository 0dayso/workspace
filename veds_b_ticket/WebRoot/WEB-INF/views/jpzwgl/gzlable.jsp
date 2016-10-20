<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1');" class="${empty param.lx or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">追位规则设置</a></li>
		<li lx='3' onclick="loadContent('3');" class="${param.lx eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">追位基础设置</a></li>
	</ul>
	<div class="clear"></div>
</div>
<script type="text/javascript">
function loadContent(lx){
	if(lx=='1'){
		window.location.href='${ctx}/vedsb/jpzwgl/jptjsqzwsz/viewlist?lx=1';
	}else if(lx=='3'){
		window.location.href='${ctx}/vedsb/jpzwgl/jptjsqjcgz/toedit?lx=3';
	}
}
</script>