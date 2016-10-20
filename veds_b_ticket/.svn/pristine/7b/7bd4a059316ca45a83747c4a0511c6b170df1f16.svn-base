<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<div class="tabContainer">
	<ul class="tabHead">
		<li tl='tbspet' onclick="loadTitleContent('BSPET');" class="${empty param.cgly or param.cgly eq 'BSPET' ? 'currentBtn' : ''}"><a style="text-decoration:none;">BSPET出票设置</a></li>
		<li tl='tbop' onclick="loadTitleContent('BOP');" class="${param.cgly eq 'BOP' ? 'currentBtn' : ''}"><a style="text-decoration:none;">BOP出票设置</a></li>
		<li tl='top' onclick="loadTitleContent('OP');" class="${param.cgly eq 'OP' ? 'currentBtn' : ''}"><a style="text-decoration:none;">OP出票设置</a></li>
		<li tl='tbpet' onclick="loadTitleContent('BPET');" class="${param.cgly eq 'BPET' ? 'currentBtn' : ''}"><a style="text-decoration:none;">BPET出票设置</a></li>
		<li tl='tcz' onclick="loadTitleContent('CZ');" class="${param.cgly eq 'CZ' ? 'currentBtn' : ''}"><a style="text-decoration:none;">CPS-官网设置</a></li>
		<li tl='ttb' onclick="loadTitleContent('TB');" class="${param.cgly eq 'TB' ? 'currentBtn' : ''}"><a style="text-decoration:none;">CPS-TB设置</a></li>
	</ul>
</div>

<script type="text/javascript">
	function loadTitleContent(cgly){
		var url = "${ctx}/vedsb/cpsz/jpcpymsz/getCpymSz?cgly="+cgly;
		document.location.href = url;
	}
</script>
<!--  
<div class="nav_junior">
	<div class="nav_junior_con" id="pttitle" name="pttitle">
		<p>
			<a href="javascript:setLx('BSPET');" id="tbspet" class="${pt eq 'BSPET' ? 'nonce' : ''}">BSPET出票设置</a>
			<a href="javascript:setLx('BOP');" id="tbop" class="${pt eq 'BOP' ? 'nonce' : ''}">BOP出票设置</a>
			<a href="javascript:setLx('OP');" id="top" class="${pt eq 'OP' ? 'nonce' : ''}">OP出票设置</a>
			<a href="javascript:setLx('BPET');" id="tbpet" class="${pt eq 'BPET' ? 'nonce' : ''}">BPET出票设置</a>
			<a href="javascript:setLx('CZ');" id="tcz" class="${pt eq 'CZ' ? 'nonce' : ''}">CPS-官网设置</a>
			<a href="javascript:setLx('TB');" id="ttb" class="${pt eq 'TB' ? 'nonce' : ''}">CPS-TB设置</a>
		</p>
	</div>
</div>
-->