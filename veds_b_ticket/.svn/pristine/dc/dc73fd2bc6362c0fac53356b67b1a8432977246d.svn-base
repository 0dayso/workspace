<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1');" class="${empty param.lx or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">PID组</a></li>
		<li lx='2' onclick="loadContent('2');" class="${param.lx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">配置管理</a></li>
	</ul>
	<div class="clear"></div>
</div>
<script type="text/javascript">
function loadContent(lx){
	if(lx=='1'){
		window.location.href='${ctx}/vedsb/pidgl/zgl/viewlist?lx=1';
	}else if(lx=='2'){
		window.location.href='${ctx}/vedsb/pidgl/pzgl/viewlist?lx=2';
	}
}
</script>