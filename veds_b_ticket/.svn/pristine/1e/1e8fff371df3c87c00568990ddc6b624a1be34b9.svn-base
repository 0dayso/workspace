<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function loadTitleContent(titlelx) {
	    var url="${ctx}/vedsb/jphbyd/bqinfohbydgz/queryList?titlelx="+titlelx;
		if (titlelx == "2") {
		    url = "${ctx}/vedsb/jphbyd/bhkgshbgz/query?titlelx="+titlelx;
		} 
		document.location.href = url;
	}
	
</script>
<div class="tabContainer">
	<ul class="tabHead">
		<li id="1" onclick="loadTitleContent('1');" class="${(empty param.titlelx and empty titlelx)  or param.titlelx eq '1' or titlelx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">航司航变规则设置</a></li>
		<li id="2" onclick="loadTitleContent('2');" class="${param.titlelx eq '2' or titlelx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">航变时长设置</a></li>
	</ul>
</div>