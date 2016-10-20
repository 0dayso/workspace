<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
	<head>
		<script type="text/javascript">
			function refresh(){
				$("#searchYhzForm").submit();
			}
			function insert(){
			    var url ="${ctx}/vedsb/pidgl/zgl/viewedit?method=ADDED&lx=${param.lx}";
			   	$.layer({
					type: 2,
					title: ['添加'],
					area: ['500px', '300px'],
					iframe: {src: url}
			  	});
		   	}
		   	function modify(yhzbh){
			    var url ="${ctx}/vedsb/pidgl/zgl/edit_"+yhzbh+"?method=MODIFIED&lx=${param.lx}";
			   	$.layer({
					type: 2,
					title: ['修改'],
					area: ['500px', '300px'],
					iframe: {src: url}
			  		});
		   	}
		   	function search(){
		   		$("#searchYhzForm").submit();
		   	}
		</script>
	</head>
	<body>
		<%@include file="/WEB-INF/views/pidgl/pzgl/title.jsp"%>
		<div class="container">
			<div id="search_bar" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">用户组列表</b>
						</div>
						<div class="box_center pt10 pb10">
							<form action="${ctx}/vedsb/pidgl/zgl/list" id="searchYhzForm" name="searchYhzForm" method="post">
								<table class="form_table" width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr><td class="td_right">
									<input type="hidden" name="lx"
														value="${param.lx}" />
									<input type="button" name="button" class="ext_btn ext_btn_success" value="新增" onclick="insert()">
									<input type="button" class="ext_btn ext_btn_success" value="查询" onclick="search()">
								</td>
								</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="mt10">
				<div class="box span10 oh" id="list_table">
					<c:if test="${not empty list}">
						<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
							<tr>
								<th style="text-align:center">操作</th>
								<th style="text-align:center">序号</th>
								<th style="text-align:center">组名称</th>
								<th style="text-align:center">组编号</th>
						        <th style="text-align:center">PID组编号</th>
								<th style="text-align:center">PID编号</th>
					       	</tr>
							<c:forEach items="${list}" var="o" varStatus="vs">
								 <tr class="tr">
									<td style="text-align:center;height:25px"><a href="###" onclick="modify('${o.yhzbh}')">编辑</a></td>
									<td style="text-align:center;height:25px">${vs.count}</td>
									<td style="text-align:center;height:25px">${o.yhzmc}</td>
									<td style="text-align:center;height:25px">${o.yhzbh}</td>
									<td style="text-align:center;height:25px">${o.pidzbh}</td>
									<td style="text-align:center;height:25px">${o.pidbh}</td>
							      </tr>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</body>
</html>
