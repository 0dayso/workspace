<%@ page language="java" pageEncoding="UTF-8"%>
<script>
/*
 * 新增
 */
$(function(){
	$("#insertRow").dynTable({tplid:'tpl'});
});
</script>

<script id="tpl" type="text/html">
    <%@include file="sgd_hcxx_tbody.jsp" %>
</script>
<div class="box_top"><b class="pl15">航程信息</b>
	<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="float:left;width:100%;height:90px;background:#efefef;" id="insertRow">
		<tr style="background:#f1f1f1;">
			<th>航班号</th>
			<th>出发城市</th>
			<th>到达城市</th>
			<th>起飞时间</th>
			<th>到达时间</th>
			<th>舱位</th>
			<th>机型</th>
			<th>出发<br>航站楼</th>
			<th>到达<br>航站楼</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty hdList}">
			<c:forEach items="${hdList}" var="hd" varStatus="vs">
				<c:set var="hd_index" value="${vs.count}"></c:set>
				<%@include file="sgd_hcxx_tbody.jsp" %>
			</c:forEach>
		</c:if>
		<c:if test="${empty hdList}">
			<%@include file="sgd_hcxx_tbody.jsp" %>
		</c:if>	
	</table>
	
</div>
<div class="clear"></div>