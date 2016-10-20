<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
	<head>
		<title>票证追踪</title>
		<script type="text/javascript">
			//判断是否输入票号或行程单
			function search(){
				if($("#xcdNo").val() == "" && $("#tkNo").val() == ""){
					alert("行程单号和票号必须输入一项！");
				}else{
					var ii = layer.load('系统正在处理您的操作,请稍候!');
			   		$("#searchForm").submit();
				}
			}
			//点击显示或隐藏tr下的内容
			function showNr(id){
		  		var obj = $("#tr_"+id);
		  		if(obj.css("display")=='none'){
		  			obj.show();
		  		}else{
		  			obj.hide();
		  		}
		  	}
		</script>
		<style type="text/css">
			#show_table table tr:hover{
				background-color: #FFFFE0;
			}
		</style>
	</head>
	<body>
		<div class="container_clear">
			<div id="search_bar" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_center pt10 pb10">
							<form id="searchForm" action="${ctx}/vedsb/pzzx/pzzz/pzzzlist" name="searchForm"  method="post">
								<input type="hidden" name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" /> 
								<input type="hidden" name="pageNum" value="${param.pageNum }" id="pageNum" /> 
								<input type="hidden" name="pageSize" value="10" id="pageSize" />
								<table class="table01">
									<tr>
										<td>票证类型：</td>
										<td>
											 <select name="pztype" class="select srk" datatype="*" style="width: 95px;heigth:24px;"> 
											   <c:forEach items="${vfc:getVeclassLb('7202')}" var="onecgly">
											   		<c:if test="${onecgly.parid ne 'none'}">
								                       <option value="${onecgly.id}" ${param.pztype eq onecgly.mc ? 'selected':'' }>${onecgly.mc}</option>
								                    </c:if>
							                    </c:forEach>
						                     </select>
										</td>
										<td>行程单号：</td>
										<td>
											<input type="text" name="xcdNo" value="${param.xcdNo }" id="xcdNo" class="input-text lh25"/>
										</td>
										<td>票号：</td>
										<td>
											<input type="text" name="tkno" value="${param.tkno }" id="tkNo" class="input-text lh25"/>
										</td>
										<td align="right">
											<input type="button" value=" 查 询 "class="ext_btn ext_btn_submit" onclick="search();">
										 </td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="show_table" style="width:1024px;">
				<table border="0" style="table-layout:fixed ;" class="list_table" cellpadding="0" cellspacing="0">
					<tr>
						<th align="center" width="20">序号</th>
						<th align="center" width="30">操作类型</th>
						<th align="center" width="40">单号</th>
						<th align="center" width="30">票证类型 </th>
						<th align="center" width="40">操作时间</th>
						<th align="center" width="50">操作部门</th>
						<th align="center" width="40">操作人</th>
						<th align="center" width="70">操作说明</th>
					</tr>
					<c:forEach var="vc" items="${list}" varStatus="status">
						<tr style="cursor: pointer;" title="点击显示或隐藏详细内容">
							<td align="center" onclick="showNr('${status.index }')">${status.index }</td>
							<td align="left" onclick="showNr('${status.index }')">
							 <c:choose>
							 	<c:when test="${vc.czlx eq '1'}">入库</c:when>
							 	<c:when test="${vc.czlx eq '2'}">发放</c:when>
							 	<c:when test="${vc.czlx eq '3'}">退回</c:when>
							 	<c:when test="${vc.czlx eq '4'}">报废</c:when>
							 	<c:when test="${vc.czlx eq '5'}">打印</c:when>
							 	<c:when test="${vc.czlx eq '6'}">作废</c:when>
							 	<c:when test="${vc.czlx eq '7'}">回收</c:when>
							 	<c:when test="${vc.czlx eq '8'}">取消回收</c:when>
							 	<c:when test="${vc.czlx eq '9'}">取消入库</c:when>
							 	<c:when test="${vc.czlx eq '10'}">已使用</c:when>
							 </c:choose>
							</td>
							<td align="center" onclick="showNr('${status.index }')">${vc.pzbh }</td>
							<td align="center" onclick="showNr('${status.index }')">${vc.ex.PZTYPE.mc }</td>
							<td align="center" onclick="showNr('${status.index }')"><fmt:formatDate value="${vc.czDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td align="center" onclick="showNr('${status.index }')">${vc.ex.CZYHBH.bmmc }</td>
							<td align="center" onclick="showNr('${status.index }')">${vc.ex.CZYHBH.xm }</td>
							<td align="left" onclick="showNr('${status.index }')">${vc.czSm }</td>
						</tr>
						<tr id="tr_${status.index }" style="display:none;">
							<td colspan="8" style="text-align: left;background-color: black;color:#0BBF1D;">
	    						${fn:replace(vc.czSm, vEnter, "<br>")}
	    						<c:if test="${vc.czlx eq '5' and fn:indexOf(vc.czSm,'票号') eq  -1}">
									<br>票号：${jpXcd.tkno}
									<br>行程单号：${jpXcd.xcdNo}
								</c:if>
		   					</td>
	   					</tr>
					</c:forEach>
				</table>
			</div>
	</body>
</html>