<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function loadTitleContent(title) {
		window.location.href="${ctx}/vedsb/cgptsz/"+title+"/viewlist?title=" + title;
	}
</script>
<div class="tabContainer">
	<ul class="tabHead">
		<li onclick="loadTitleContent('jpptzcfzsz');" class="${  empty param.title or param.title eq 'jpptzcfzsz' ? 'currentBtn' : ''}"><a style="text-decoration:none;">平台支付方式对应</a></li>
        <li onclick="loadTitleContent('jpptzczh');" class="${  param.title eq 'jpptzczh' ? 'currentBtn' : ''}"><a style="text-decoration:none;">平台账号设置</a></li>
         <li onclick="loadTitleContent('jpcgdd');" class="${  param.title eq 'jpcgdd' ? 'currentBtn' : ''}"><a style="text-decoration:none;">采购订单查询</a></li>
        <li onclick="loadTitleContent('jpptbjhfsz');" class="${ param.title eq 'jpptbjhfsz' ? 'currentBtn' : ''}"><a style="text-decoration:none;">平台后返设置</a></li>
        <li onclick="loadTitleContent('jpptlog');" class="${ param.title eq 'jpptlog' ? 'currentBtn' : ''}"><a style="text-decoration:none;">平台日志</a></li>
	</ul>
</div>