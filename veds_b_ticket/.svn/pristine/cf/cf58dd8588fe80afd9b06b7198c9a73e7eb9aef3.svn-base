<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div id="tffaimg">
	<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1',this);" class="${empty param.lx or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">差异单</a></li>
		<li lx='2' onclick="loadContent('2',this);" class="${param.lx eq '2' ? 'currentBtn' : '' }"><a style="text-decoration:none;">补差单</a></li>
	</ul>
	<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
	function loadContent(index,obj){
		var url = "";
		if(index == '1'){
			url = "${ctx}/vedsb/jpbcd/jpcyd/viewlist?lx="+index;
		}else if(index == '2'){
			url = "${ctx}/vedsb/jpbcd/jpbcd/viewlist?lx="+index;
		}
		document.location.href = url;
	}
</script>