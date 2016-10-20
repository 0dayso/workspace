<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1');" class="${empty param.lx or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">未到账</a></li>
		<li lx='2' onclick="loadContent('2');" class="${param.lx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">漏单</a></li>
	</ul>
	<div class="clear"></div>
</div>
<script type="text/javascript">
function loadContent(lx){
	if(lx=='1'){
		window.location.href='${ctx}/vedsb/jpcwgl/jpysdz/viewwdzList?lx=1';
	}else if(lx=='2'){
		window.location.href='${ctx}/vedsb/jpcwgl/jpysdz/viewldList?lx=2';
	}
}
</script>