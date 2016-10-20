<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function loadTitleContent(title) {
		if (title == "1") {
			window.location.href = "viewpzff?title=1";
		} else {
			window.location.href = "viewpzffcx?title=2";
		}
	}
</script>
<div class="tabContainer">
	<ul class="tabHead">
        <li onclick="loadTitleContent('1');" class="${ empty param.title or param.title eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">票证发放</a></li>
        <li onclick="loadTitleContent('2');" class="${ param.title eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">发放查询</a></li>
	</ul>
</div>

