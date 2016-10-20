<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function loadTitleContent(cl_zt) {
	    var ksrq = $("input[name='ksrq']").val();
	    var jsrq = $("input[name='jsrq']").val();
	    var url="${ctx}/vedsb/qxxgl/bqinfo/viewbqinfo";
		url=url+"?cl_zt="+cl_zt+"&ksrq="+ksrq+"&jsrq="+jsrq;
		document.location.href = url;
	}
</script>
<div class="tabContainer">
	<ul class="tabHead">
		<li id="1" onclick="loadTitleContent('0');" class="${empty param.cl_zt or param.cl_zt eq '0' ? 'currentBtn' : ''}"><a style="text-decoration:none;">待处理</a></li>
		<li id="2" onclick="loadTitleContent('1');" class="${param.cl_zt eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">全部</a></li>
		<li id="3" onclick="loadTitleContent('2');" class="${param.cl_zt eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">处理中</a></li>
	</ul>
</div>