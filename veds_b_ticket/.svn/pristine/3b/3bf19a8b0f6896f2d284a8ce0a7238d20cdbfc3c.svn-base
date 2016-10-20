<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function loadTitleContent(lx, gngj) {
	    var ksrq = $("input[name='ksrq']").val();
	    var jsrq = $("input[name='jsrq']").val();
	    var url="${ctx}/vedsb/cpkzt/cpkzt/viewlist";
		if (lx == "4") {
		    url = "${ctx}/vedsb/jptpgl/jptpdcpkzt/viewlist";
		} else if (lx == "5") {
			url = "${ctx}/vedsb/cpkzt/cpkzt/viewzdcp_jk_list";
		}else if (lx == "6") {
			url = "${ctx}/vedsb/cpsz/jpzdtfpgzsz/viewzdtp_jk_list";
		}else if (lx == "7") {
			url = "${ctx}/vedsb/xepnrgl/jpxepnr/viewlist";
		}
		url=url+"?lx="+lx+"&ksrq="+ksrq+"&jsrq="+jsrq+"&gngj="+gngj;
		document.location.href = url;
	}
	
</script>
<div class="tabContainer">
	<ul class="tabHead">
		<li id="1" onclick="loadTitleContent('1', '${param.gngj}');" class="${empty param.lx or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">待出票</a></li>
		<li id="2" onclick="loadTitleContent('2', '${param.gngj}');" class="${param.lx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">出票中</a></li>
		<li id="3" onclick="loadTitleContent('3', '${param.gngj}');" class="${param.lx eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">已出票</a></li>
		<li id="4" onclick="loadTitleContent('4', '${param.gngj}');" class="${param.lx eq '4' ? 'currentBtn' : ''}"><a style="text-decoration:none;">退票单</a></li>
		<li id="5" onclick="loadTitleContent('5', '${param.gngj}');" class="${param.lx eq '5' ? 'currentBtn' : ''}"><a style="text-decoration:none;">自动出票监控 </a></li>
		<li id="6" onclick="loadTitleContent('6', '${param.gngj}');" class="${param.lx eq '6' ? 'currentBtn' : ''}"><a style="text-decoration:none;">自动退票监控 </a></li>
		<li id="7" onclick="loadTitleContent('7', '${param.gngj}');" class="${param.lx eq '7' ? 'currentBtn' : ''}"><a style="text-decoration:none;">自动XEPNR监控 </a></li>
	</ul>
</div>