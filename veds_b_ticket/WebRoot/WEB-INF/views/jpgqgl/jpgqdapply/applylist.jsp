<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<html>
	<head>
		<title>改签申请列表</title>
		<style type="text/css">
			.short1{
					width:100%;
				 	white-space:nowrap;
					overflow:hidden;
				 	text-overflow:ellipsis;
				}
			a {
				text-decoration:none;
			}
			.gqd_right {
				text-align: right;
			}
			.gqd_left {
				text-align: left;
			}
			.gqd_center {
				text-align: center;
			}
		</style>
		<script type="text/javascript">
			function toApply(ddbh) {
				window.location.href="${ctx}/vedsb/jpgqgl/jpgqdapply/findJp?ddbh="+ddbh;
			}
			
			function check() {
				var ksrq = $("#ksrq").val();
				var jsrq = $("#jsrq").val();
				if($("#pnr").val() == "" && $("#tkno").val() == "" && $("#cjr").val() == ""  && ( ksrq == "" || jsrq == "")) {
					layer.msg("请按提示输入正确的查询条件！");
					return false;
				}
				if(ksrq != "" && jsrq != ""){
					if(parseInt(DateDiff(ksrq, jsrq)) > 31){
						layer.msg("起飞日期范围不能超过31天！");
						return false;
					}
				}
			}
		</script>
  	</head>
	<body>
	 <div class="container">
	 	<div id="search_bar" class="mt10">
	       		<div class="box">
	          		<div class="box_border">
	            		<div class="box_center pt10 pb10">
		            		<form action="${ctx}/vedsb/jpgqgl/jpgqdapply/findJp" id="searchForm" name="searchForm" method="post" onsubmit="return check();">
			            		<input type="hidden"  name="VIEW" value="4222d92b07bddad129c2a64df3a25c1e" id="view"/>
			            		<input type="hidden" name="gngj" value="${not empty param.gngj ? param.gngj : '1'}">
			            		<table>
			            			<tr>
			            				<td colspan="5">
			            					<span style="color:blue">温馨提示：输入PNR、或票号、或乘机人与时间范围(</span>
			            					<span style="color:red">范围不能超过31天,申请退废的不可以做改签</span>
			            					<span style="color:blue">)，回车提取需要改签的机票 </span>
			            				</td>
			            			</tr>
			            			<tr>
			            				<td>
			            					PNR&nbsp;<input type="text" id="pnr" value="${param.xsPnrNo}" name="xsPnrNo" onblur="this.value=this.value.replace('%','').toUpperCase()" style="height: 20px;width:120px"/>
			            				</td>
			            				<td>
			            					&nbsp;&nbsp;票号&nbsp;<input type="text" id="tkno" value="${param.tkno}" name="tkno" style="height: 20px;width:120px"/>
			            				</td>
			            				<td>
			            					&nbsp;&nbsp;起飞日期&nbsp;
			            					<input name="ksrq" id="ksrq" class="input-text Wdate" size="10" value="${empty param.ksrq ? vfn:dateShort()  : param.ksrq}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'ksrq\')}'})">
			            					--
			            					<input name="jsrq" id="jsrq" class="input-text Wdate" size="10" value="${empty param.jsrq ? vfn:dateShort()  : param.jsrq}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'jsrq\')}'})">
			            				</td>
			            				<td>
			            					&nbsp;&nbsp;乘机人&nbsp;<input type="text" id="cjr" value="${param.cjr}" name="cjr" style="height: 20px;width:120px"/>
			            				</td>
			            				<td>
			            					&nbsp;&nbsp;<input type="submit" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit" />
			            				</td>
			            			</tr>
			            		</table>
		            		</form>
	            		</div>
	            		<c:if test="${not empty ddList}">
	            			<table border="0" cellpadding="0" cellspacing="0" class="list_table">
	            				<tr>
	            					<th>序号</th>
	            					<th>PNR</th>
	            					<th>预定时间</th>
	            					<th>网店</th>
	            					<th>政策代码</th>
	            					<th>方案</th>
	            					<th>票证类型</th>
	            					<th>出票时间</th>
	            					<th>乘机人</th>
	            					<th>起飞时间</th>
	            					<th>航程</th>
	            					<th>航班号</th>
	            					<th>舱位</th>
	            					
	            				</tr>
	 							<c:forEach items="${ddList}" var="khdd" varStatus="i">
	 								<tr>
		            					<td class="gqd_center">${i.count}</td>
		            					<td class="gqd_center"><a href="javascript:toApply('${khdd.ddbh}')" title="点击申请改签">${khdd.xsPnrNo}</a></td>
		            					<td class="gqd_center">${vfn:format(khdd.ddsj,'yyyy-MM-dd HH:mm:ss')}</td>
		            					<td class="gqd_center">${khdd.ex.WDID.wdmc}</td>
		            					<td class="gqd_center">${khdd.wdZcdm}</td>
		            					<td class="gqd_left">${khdd.faid}</td>
		            					<td class="gqd_center">${khdd.cgly}</td>
		            					<td class="gqd_center">${vfn:format(khdd.cpSdsj,'yyyy-MM-dd HH:mm:ss')}</td>
		            					<td class="gqd_left">${khdd.cjr}</td>
		            					<td class="gqd_center">${vfn:format(khdd.cfrq,'yyyy-MM-dd HH:mm:ss')}</td>
		            					<td class="gqd_center">${khdd.hc}</td>
		            					<td class="gqd_center">${khdd.xsHbh}</td>
		            					<td class="gqd_center">${khdd.xsCw}</td>
	            					</tr>
	 							</c:forEach>
	            			</table>
            			</c:if>
	          		</div>
	          		${empty error ? '':error }
	        	</div>	
			</div>
		</div>            	
	</body>
</html>
