<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>按单申请退废票</title>
<style>
	.input: {
		width: 85px;
	}
	
	.text_right {
		text-align: right;
	}
	
	.text_left {
		text-align: left;
	}
</style>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script>
	var validator;
	
	$(document).ready(function() {
	    validator = $("#searchForm").Validform({tiptype : 3});
		var errormsg = "${errormsg}";
		if (errormsg) {
			layer.alert(errormsg);
		}
	 });

    function toSearch(){
        var ddbh=$("#ddbh").val();
        var tkno=$("#tkno").val();
        var pnrno=$("#xs_pnr_no").val();
		
	    if (ddbh == "" && tkno == "" && pnrno == "") {
			var ksrq = $("#ksrq").val();
			var jsrq = $("#jsrq").val();
			if (ksrq == "") {
				layer.alert("请输入开始日期查询！");
				return false;
			}
			if (jsrq == "") {
				layer.alert("请输入结束日期查询！");
				return false;
			}
			if (DateDiff(jsrq, ksrq) > 30) {
				layer.alert("日期范围不能大于30天");
				return false;
			}
		}
		validator.submitForm(false);
	}

	function apply(ddbh) {
		$("#ddbh").val(ddbh);
		validator.submitForm(false);
	}
</script>
</head>
<body>
<div class="container_clear">
 	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/jptpgl/jptpdapply/getJptpdForApply" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
							<input type="hidden"  name="orderBy" value="" id="orderBy"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
							<input type="hidden"  name="lx" value="${param.lx}" id="lx"/>
                            <table class="table01" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="11" class="red">温馨提示：<br>
										1、可单独输入PNR、或10位票号、或订单编号查询，优先级别为票号、订单编号、PNR；<br>
										2、也可输入乘机人、航程、大编码等其他条件，和起飞、或预订、或出票日期一起查询，日期最大查询范围为30天；<br>
									</td>
								</tr>
								<tr>
									<td class="text_right">P N R</td>
									<td class="text_left">
										<input type="text" id="xs_pnr_no" name="xs_pnr_no"  class="input-text lh25" size="10"  value="${param.xs_pnr_no}" onblur="value=$.trim(this.value).toUpperCase();">
									</td>
									<td class="text_right">票 &nbsp;号</td>
									<td class="text_left">
										<input type="text" id="tkno" name="tkno"  value="${param.tkno}" class="input-text lh25" style="width: 150px;" >
									</td>
									
									<td class="text_right">乘机人</td>
									<td class="text_left">
										<input type="text" id="cjr" name="cjr" value="${param.cjr}" class="input-text lh25" onblur="value=replaceAll(value,'%','').toUpperCase()" size="12">
									</td>
									<td class="text_right">航 程</td>
									<td class="text_left">
										<input type="text" id="hc" name="hc" value="${param.hc}" class="input-text lh25" onblur="value=value.toUpperCase()" style="width: 120px;">
									</td>
									<td class="text_left"  align="center">
										<input type="button" id="searchButton" class="ext_btn ext_btn_submit lh25" value=" 查 询 " onclick="toSearch();"> 
									</td>
								
								</tr>
								<tr>
									<td class="text_right">日期条件</td>
									<td class="text_left" colspan="3">
										<select name="rqtj" style="width: 100px;" class="select">
											<option value="1" ${empty param.rqtj or param.rqtj eq '1' ? 'selected' : ''}>起飞日期</option>
											<option value="2" ${param.rqtj eq '2' ? 'selected' : ''}>预订日期</option>
											<option value="3" ${param.rqtj eq '3' ? 'selected' : ''}>出票日期</option>
										</select> 
										<input type="text" style="width: 90px;" readonly name="ksrq" class="input-text Wdate"  size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="ksrq"> -- 
										<input type="text" style="width: 90px;" readonly name="jsrq" class="input-text Wdate"  size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="jsrq">
									</td>
									<td class="text_right">大编码</td>
									<td class="text_left">
										<input type="text" id="xs_hkgs_pnr" name="xs_hkgs_pnr" value="${param.hkgs_pnr}" class="input-text lh25"  onblur="value=$.trim(this.value).toUpperCase();" size="12">
									</td>
									<td class="text_right">订单编号</td>
									<td class="text_left">
									   <input type="text" id="ddbh" name="ddbh" class="input-text lh25" style="width: 120px;" value="${param.ddbh}">
									</td>
									<td>
										<input type="button" id="searchBack"   class="ext_btn ext_btn_submit lh25" value=" 返 回 " onclick="window.history.go(-1)" >
									</td>
								</tr>
							</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>

		<c:if test="${fn:length(jpList)>1}">
			<table width="800px" border="0" cellpadding="0" cellspacing="0" class="list_table">
				<tr>
					<th>序号</th>
					<th>PNR</th>
					<th>票号</th>
					<th>票证类型</th>
					<th>乘机人</th>
					<th>乘机人<br>类型</th>
					<th>出票时间</th>
					<th>起飞时间</th>
					<th>航程</th>
					<th>航班号</th>
					<th>舱<br>位</th>
				</tr>

                <c:set var="xh" value="1"></c:set>
				<c:forEach items="${jpList}" var="vc" varStatus="s">
					<tr>
						<td align="center">
							<c:if test="${ddbh ne vc.DDBH}">
								${xh}<c:set var="xh" value="${xh+1}"></c:set>
							</c:if>
						</td>
						<td align="center">
							<c:if test="${ddbh ne vc.DDBH}">
								<a href="###" onclick="apply('${vc.DDBH}')">${vc.XS_PNR_NO}</a>
							</c:if>
						</td>
						<td align="center">${vc.TKNO}</td>
						<td align="center">${vc.CGLY}</td>
						<td align="center">${vc.CJR}</td>
						<td align="center">${vc.CJRLX}</td>
						<td align="center">${vfn:format(vc.CP_DATETIME,'yy-MM-dd HH:mm')}</td>
						<td align="center">${vfn:format(vc.CFRQ,'yy-MM-dd HH:mm')}</td>
						<td align="center">${vc.HC}</td>
						<td align="center">${vc.XS_HBH}</td>
						<td align="center">${vc.XS_CW}</td>
						<c:set var="ddbh"  value="${vc.DDBH}" ></c:set>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>
