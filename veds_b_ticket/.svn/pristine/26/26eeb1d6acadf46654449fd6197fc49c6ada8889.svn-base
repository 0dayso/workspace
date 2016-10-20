<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function loadTitleContent(gqzt) {
		window.location.href="${ctx}/vedsb/jpgqgl/jpgqd/viewcpkztlist?title=gqd&gqzt=" + gqzt;
	}
</script>
<div class="tabContainer">
	<ul class="tabHead">
        <li onclick="loadTitleContent('1');" class="${ empty param.gqzt or param.gqzt eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">已确认</a></li>
        <li onclick="loadTitleContent('3');" class="${ param.gqzt eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">改签中</a></li>
        <li onclick="loadTitleContent('4');" class="${ param.gqzt eq '4' ? 'currentBtn' : ''}"><a style="text-decoration:none;">已改签</a></li>
	</ul>
</div>