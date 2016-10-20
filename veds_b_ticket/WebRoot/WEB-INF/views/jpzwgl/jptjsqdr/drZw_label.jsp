<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="tffaimg">
	<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContentdr('1',this);" class="${empty param.lx or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">导入订单</a></li>
		<li lx='2' onclick="loadContentdr('2',this);" class="${param.lx eq '2' ? 'currentBtn' : '' }"><a style="text-decoration:none;">追位成功</a></li>
	</ul>
	<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
	function loadContentdr(index,obj){
		var url = "";
		if(index == '1'){
			url = "${ctx}/vedsb/jpzwgl/jptjsqdr/viewlist?lx="+index+"&zwlx=3";
		}else if(index == '2'){
			url = "${ctx}/vedsb/jpzwgl/jptjsqdr/viewsucclist?lx="+index+"&zwlx=3";
		}
		document.location.href = url;
	}
</script>