<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<div class="tabContainer">
	<ul class="tabHead">
		<li tl='tbb2b' onclick="loadTitleContent('B2B','720102');" class="${empty param.pt or param.pt eq 'B2B' ? 'currentBtn' : ''}"><a style="text-decoration:none;">B2B航空公司设置</a></li>
		<li tl='tbb2c' onclick="loadTitleContent('B2C','720104');" class="${param.pt eq 'B2C' ? 'currentBtn' : ''}"><a style="text-decoration:none;">B2C航空公司设置</a></li>
	</ul>
</div>

<script type="text/javascript">
	function loadTitleContent(cgly,bca){
		var url = "${ctx}/vedsb/b2bsz/jpb2bdlzh/viewmain?pt="+cgly+"&bca=" + bca;
		parent.document.location.href = url;
	}
</script>
