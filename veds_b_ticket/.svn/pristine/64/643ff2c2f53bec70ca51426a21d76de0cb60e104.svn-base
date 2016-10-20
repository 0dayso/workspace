<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="tffaimg">
	<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1',this);" class="${empty param.lx  or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">采购对账</a></li>
		<li lx='2' onclick="loadContent('2',this);" class="${param.lx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">退票待回款</a></li>
		<li lx='3' onclick="loadContent('3',this);" class="${param.lx eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">采购异常</a></li>
	</ul>
	<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
	function loadContent(index,obj){
		var url = "";
		if(index == '3'){
			url = "${ctx}/vedsb/cgdzbb/jpcgdzyc/viewlist?lx="+index;
		}else if(index == '2'){
			url = "${ctx}/vedsb/cgdzbb/jpcgdztpdhk/viewlist?lx="+index;
		}else if(index == '1'){
			url = "${ctx}/vedsb/cgdzbb/jpcglyreport/viewlist?lx="+index
		}
		document.location.href = url;
	}
</script>