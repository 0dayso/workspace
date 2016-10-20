<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="tffaimg">
	<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1',this);" class="${empty param.lx or param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">完全正确(${dbtslzqSize})</a></li>
		<li lx='2' onclick="loadContent('2',this);" class="${param.lx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">票价对比不正确(${dbtslcwSize})</a></li>
		<li lx='3' onclick="loadContent('3',this);" class="${param.lx eq '3'  ? 'currentBtn' : ''}"><a style="text-decoration:none;">只存在于系统(${dbxtSize})</a></li>
		<li lx='4' onclick="loadContent('4',this);" class="${param.lx eq '4' ? 'currentBtn' : ''}"><a style="text-decoration:none;">只存在于TSL账单(${dbtslSize})</a></li>
	</ul>
	<div class="clear"></div>
	</div>
	<form action="${ctx}/vedsb/cgdzbb/jpcgdz/cgdzDbResult" method="post" id="cgdzForm" name="cgdzForm">
		<input type="hidden" name="office" value="${param.office }" id="compareoffice"/>
		<input type="hidden" name="printno" value="${param.printno }" id="compareprint"/>
		<input type="hidden" name="tsldate" value="${param.tsldate}" id="comparetsldate"/>
		<input type="hidden" name="dbjg" value="${param.dbjg}" id="comparedbjg"/>
		<input type="hidden" name="lx" value="${param.lx}" id="lx"/>
	</form>
</div>
<script type="text/javascript">
	function loadContent(index,obj){
		if(index == '1'){
			$("#comparedbjg").val("0");
			$("#lx").val("1");
		}else if(index == '2'){
			$("#comparedbjg").val("1");
			$("#lx").val("2");
		}else if(index == '3'){
			$("#comparedbjg").val("2");
			$("#lx").val("3");
		}else if(index == '4'){
			$("#comparedbjg").val("3");
			$("#lx").val("4");
		}
		$("#cgdzForm").submit();
	}
</script>