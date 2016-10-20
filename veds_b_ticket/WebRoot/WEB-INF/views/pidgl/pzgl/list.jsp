-<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
	<head>
		<script type="text/javascript">
		function deleteById(no,pidconnectionstate){
			if(confirm('确认删除吗?')){
				document.location.href="${ctx}/vedsb/pidgl/pzgl/delete_"+no+"?turningUrl=${ctx}/vedsb/pidgl/pzgl/viewlist&pidconnectionstate="+pidconnectionstate;
			}
		}
		function insert(){
			document.location.href="${ctx}/vedsb/pidgl/pzgl/viewedit?lx=${param.lx}";
		}
		function off(no){
			document.location.href="${ctx}/vedsb/pidgl/pzgl/disconnect_"+no+"?turningUrl=${ctx}/vedsb/pidgl/pzgl/list?lx=${param.lx}";
		}
		function on(no){
			document.location.href="${ctx}/vedsb/pidgl/pzgl/connect_"+no+"?turningUrl=${ctx}/vedsb/pidgl/pzgl/list?lx=${param.lx}";
		}
		function searchPid(){
			$("#searchPidForm").submit();
		}
		function load(){
			document.getElementById("search").click();
		}
		function start(no){
			document.location.href="${ctx}/vedsb/pidgl/pzgl/start_"+no+"?turningUrl=${ctx}/vedsb/pidgl/pzgl/list?lx=${param.lx}";
		}
		function stop(no){
			document.location.href="${ctx}/vedsb/pidgl/pzgl/stop_"+no+"?turningUrl=${ctx}/vedsb/pidgl/pzgl/list?lx=${param.lx}";
		}
		</script>
	</head>
	<body onload="load();">
		<%@include file="/WEB-INF/views/pidgl/pzgl/title.jsp"%>
		<div class="container">
			<div id="search_bar" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">PID服务</b>
						</div>
						<div class="box_center pt10 pb10">
							<form action="${ctx}/vedsb/pidgl/pzgl/list?no=-1" id="searchPidForm" 
								name="searchForm" method="post">
								<input type="hidden" name="lx"
									value="${param.lx}" />
								<input type="hidden" name="VIEW"
									value="692a3b3046e69162f490ff0c1e16bcf1" />
								<input type="hidden" name="pageNum" value="${param.pageNum }"
									id="pageNum" />
								<table class="form_table" width="100%" border="0"
									cellpadding="0" cellspacing="0">
									<tr>
										<td class="td_right" width="100%">
											<input type="button" name="button"
												class="ext_btn ext_btn_submit" value="新增配置"
												onclick="insert()">
												
											<input type="button" id="search" onclick="searchPid()"
												class="ext_btn ext_btn_submit" value="查询所有配置">
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
					 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
				       <tr>
						<th width="200">操作</th>
						<th width="20">配置状态</th>
						<th width="20">连接状态</th>
				      <!--  <th width="40">本地地址</th> -->
				        <th width="40">远程地址</th> 
						<th width="40">配置用户名</th>
						<th width="40">office</th>
						<th width="40">PID分组</th>
						<th width="40">SI信息</th>
						<th width="20">商户编号</th>
						<th width="20">是否默认配置</th>
				       </tr>
			    	<c:forEach items="${list}" var="o">
				       	<tr class="tr">
							<td class="td_center"><a href="${ctx}/vedsb/pidgl/pzgl/edit_${o.no}?lx=${param.lx}">编辑</a>
							<c:if test="${o.stop eq '1' }">		
								<a href="###" onclick="stop('${o.no}')">停用</a>
							</c:if>
							<c:if test="${o.stop eq '0' }">		
								<a href="###" onclick="start('${o.no}')">启用</a>
							</c:if>
							<c:choose>
								<c:when test="${o.pidconnectionstate=='已连接'}">
									<a href="###" title="点击连接后请多查询几次查看结果" onclick="off('${o.no}')">断开</a>
								</c:when>
								<c:when test="${o.pidconnectionstate=='断开'}">
									<a href="###" title="点击连接后请多查询几次查看结果" onclick="on('${o.no}')">连接</a>
								</c:when>
								<c:otherwise>
									连接中...
								</c:otherwise>
							</c:choose></td>
							<td class="td_center">${o.stop eq '0' ? '停用' : '启用' }</td>
							<td class="td_center">${o.pidconnectionstate }</td>
					        <!-- <td class="td_center">${o.localip }</td> -->
					        <td class="td_center">${o.serverip }</td>
					        <td class="td_center">${o.username }</td>
							<td class="td_center">${o.office }</td>
							<td class="td_center">${o.pidzid },${o.pidid }</td>
							<td class="td_center">${o.si }</td>
							<td class="td_center">${o.remark }</td>
							<td class="td_center">${o.sfmr eq '1' ? '是' : '否' }</td>
				       	</tr>
						</c:forEach>
					 </table>
				</div>

			</div>
		</div>
	</body>
</html>
