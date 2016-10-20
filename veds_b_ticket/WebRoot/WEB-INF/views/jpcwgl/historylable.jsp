<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="tffaimg">
	<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1',this);" class="${(empty param.lx and empty lx) or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">机票正常单</a></li>
		<li lx='2' onclick="loadContent('2',this);" class="${param.lx eq '2' or lx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">机票改签单</a></li>
		<li lx='3' onclick="loadContent('3',this);" class="${param.lx eq '3' or lx eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">机票退票单</a></li>
		<li lx='4' onclick="loadContent('4',this);" class="${param.lx eq '4' or lx eq '4'? 'currentBtn' : ''}"><a style="text-decoration:none;">机票补差单</a></li>
	</ul>
	<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
	function loadContent(index,obj){
		var url = "";
		if(index == '1'){
			url = "${ctx}/vedsb/jpcwgl/jphistory/viewlist?lx="+index;
		}else if(index == '2'){
			url = "${ctx}/vedsb/jpcwgl/jpgqdhistory/viewgqlist?lx="+index;
		}else if(index == '3'){
			url = "${ctx}/vedsb/jpcwgl/jptpdhistory/viewtplist?lx="+index;
		}else if(index == '4'){
			url = "${ctx}/vedsb/jpcwgl/jpcydhistory/viewcylist?lx="+index;
		}
		document.location.href = url;
	}
</script>