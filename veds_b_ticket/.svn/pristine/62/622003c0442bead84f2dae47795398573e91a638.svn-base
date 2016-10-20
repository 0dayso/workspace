<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function loadTitleContent(id,wdpt,gngj) {
		if(!gngj){//默认为国内
			gngj = "1";
		}
		var ksrq = $("input[name='ksrq']").val();
	    var jsrq = $("input[name='jsrq']").val();
		document.location.href = '${ctx}/vedsb/jpddgl/jpkhdd/viewlist?wdid='+id+'&wdpt='+wdpt+'&ksrq='+ksrq+'&jsrq='+jsrq+'&gngj='+gngj;
	}
</script>
<div class="tabContainer" id="div_ul">
	<ul class="tabHead" >
	 <li wdid='0' onclick="loadTitleContent('0','','${gngj}');" class="${empty param.wdid or param.wdid eq '0' ? 'currentBtn' : ''}"><a style="text-decoration:none;">全部订单</a></li>
	  <c:forEach items="${wdzlszList}" var="wd" varStatus="wdptStatus">
 	 	<li wdid='${wd.id }' onclick="loadTitleContent('${wd.id}','${wd.wdpt }', '${gngj}');" class="${param.wdid eq wd.id ? 'currentBtn' : ''}">
 	 		<a style="text-decoration:none;">${wd.wdptmc}【${wd.wdmc}】</a>
 	 	</li>
 	 </c:forEach>
  </ul>
</div>   
	    
