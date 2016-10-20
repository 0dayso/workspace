<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function changeRowSum(changeName, sumName, precision) {
		var sum = 0;
		$('input[type="text"][name=' + changeName + ']').each(function() {
			var currentVal = $(this).val();
			sum = parseFloat(sum) + parseFloat(currentVal);
		});
		if (!precision && precision != 0) {
			precision = 2;
		}
		$("#" + sumName).html(sum.toFixed(precision));
	}

	function changeColSum(id) {
		var xsjId = "cjr_xsj_" + id;
		var jsfId = "cjr_jsf_" + id;
		var taxId = "cjr_tax_" + id;
		var yjId = "cjr_yj_" + id;
		var zsjeId = "cjr_xsje_" + id;
		var xsJeId = "xsJe_" + id;
		var xsjVal = parseFloat($("#" + xsjId).attr("value"));
		var jsfVal = parseFloat($("#" + jsfId).attr("value"));
		var taxVal = parseFloat($("#" + taxId).attr("value"));
		var yjVal = parseFloat($("#" + yjId).attr("value"));
		var sum = parseFloat(xsjVal + jsfVal + taxVal - yjVal);
		$("#" + xsJeId).html(sum.toFixed(2));
		$("#" + zsjeId).html(sum.toFixed(2));

		var allSum = 0;
		$('span[name="cjr_xsje"]').each(function() {
			var currentVal = $(this).html();
			allSum = parseFloat(allSum) + parseFloat(currentVal);
		});
		$("#cjr_sumYsje").html(allSum.toFixed(2));
	}
</script>
<c:set var="sumZdj" value="0"></c:set>
<c:set var="sumCgj" value="0"></c:set>
<c:set var="sumXsj" value="0"></c:set>
<c:set var="sumJsf" value="0"></c:set>
<c:set var="sumTax" value="0"></c:set>
<c:set var="sumYj" value="0"></c:set>
<c:set var="sumHyxFs" value="0"></c:set>
<c:set var="sumYwxFs" value="0"></c:set>
<c:set var="sumYsje" value="0"></c:set>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center"
	style="width: 100%;margin-top: 1px;" id="khInfo">
	<tr>
		<td
			style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/kh.png" /><br /> <span
			style="font-size:14px; font-weight:bold; color:#1195db">客户信息</span></td>
		<td style="position: relative; top:0px;">
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center"
				style="float:left;width:100%;height:90px;">
				<tr style="background:#f1f1f1;">
					<th>乘机人</th>
					<th>证件号码</th>
					<th>账单价</th>
					<th>采购价</th>
					<th>销售价</th>
					<th>销售佣金</th>
					<th>机建</th>
					<th>税费</th>
					<th>航意险份数</th>
					<th>延误险份数</th>
					<th>应收金额</th>
					<th>票号</th>
				</tr>
				<c:set var="tmp" value="" />
				<c:forEach items="${jpkhddCjrList}" var="vc">
					<input type="hidden" id="cjrId" name="cjrId" value="${vc.id}" />
					<input type="hidden" id="xsJe_${vc.id}" name="xsJe"
						value="${vc.xsJe}" />
					<tr id="jptr${vc.tkno}">
						<td align="center" class="wb_td02">${vc.cjr}<script
								type="text/javascript">
							getCjrlxImg('${vc.cjrlx}')
						</script>
						</td>
						<td align="center" class="wb_td02">${vc.zjhm}</td>
						<td align="center" class="wb_td02"><input type="text"
							size="2" id="cjr_zdj_${vc.id}" name="cjr_zdj"
							onblur="changeRowSum('cjr_zdj', 'cjr_sumZdj', 0);"
							value="${vc.xsZdj}" style="text-align:right"
							datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"
							nullmsg="请输入值" />
						</td>
						<td align="center" class="wb_td02"><input type="text"
							size="2" id="cjr_cgj_${vc.id}" name="cjr_cgj"
							onblur="changeRowSum('cjr_cgj', 'cjr_sumCgj', 1);"
							value="${vc.cgPj}" style="text-align:right"
							datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"
							nullmsg="请输入值" />
						</td>
						<td align="center" class="wb_td02"><input type="text"
							size="2" id="cjr_xsj_${vc.id}" name="cjr_xsj"
							onblur="changeRowSum('cjr_xsj', 'cjr_sumXsj', 0);changeColSum('${vc.id}');"
							value="${vc.xsPj}" style="text-align:right"
							datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"
							nullmsg="请输入值" errormsg="请输入正确格式的值" />
						</td>
						<td align="center" class="wb_td02"><input type="text"
							size="2" id="cjr_yj_${vc.id}" name="cjr_yj"
							onblur="changeRowSum('cjr_yj', 'cjr_sumYj', 0);changeColSum('${vc.id}');"
							value="${vc.xsYj}" style="text-align:right"
							datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"
							nullmsg="请输入值" errormsg="请输入正确格式的值" />
						</td>
						<td align="center" class="wb_td02"><input type="text"
							size="1" id="cjr_jsf_${vc.id}" name="cjr_jsf"
							onblur="changeRowSum('cjr_jsf', 'cjr_sumJsf', 0);changeColSum('${vc.id}');"
							value="${vc.xsJsf}" style="text-align:right"
							datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"
							nullmsg="请输入值" errormsg="请输入正确格式的值" />
						</td>
						<td align="center" class="wb_td02"><input type="text"
							size="1" id="cjr_tax_${vc.id}" name="cjr_tax"
							onblur="changeRowSum('cjr_tax', 'cjr_sumTax', 0);changeColSum('${vc.id}');"
							value="${vc.xsTax}" style="text-align:right"
							datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"
							nullmsg="请输入值" errormsg="请输入正确格式的值" />
						</td>
						<td align="center" class="wb_td02"><input type="text"
							size="1" id="cjr_hyxfs_${vc.id}" name="cjr_hyxfs"
							onblur="changeRowSum('cjr_hyxfs', 'cjr_sumHyxFs', 0);"
							value="${vc.xsHyxfs}" style="text-align:right"
							datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99"
							nullmsg="请输入值" errormsg="请输入正确格式的值" />
						</td>
						<td align="center" class="wb_td02"><input type="text"
							size="1" id="cjr_ywxfs_${vc.id}" name="cjr_ywxfs"
							onblur="changeRowSum('cjr_ywxfs', 'cjr_sumYwxFs', 0);"
							value="${vc.xsYwxfs}" style="text-align:right"
							datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99"
							nullmsg="请输入值" errormsg="请输入正确格式的值" />
						</td>
						<td align="center" class="wb_td02" style="color: red"><span
							id="cjr_xsje_${vc.id}" name="cjr_xsje">${vc.xsJe}</span>
						</td>
						<td align="center" class="wb_td02">${empty vc.tkno ? '未出票' :
							vc.tkno}</td>
						<c:set var="sumZdj" value="${sumZdj+vc.xsZdj}"></c:set>
						<c:set var="sumCgj" value="${sumCgj+vc.cgPj}"></c:set>
						<c:set var="sumXsj" value="${sumXsj+vc.xsPj}"></c:set>
						<c:set var="sumYj" value="${sumYj+vc.xsYj}"></c:set>
						<c:set var="sumJsf" value="${sumJsf+vc.xsJsf}"></c:set>
						<c:set var="sumTax" value="${sumTax+vc.xsTax}"></c:set>
						<c:set var="sumHyxFs" value="${sumHyxFs+vc.xsHyxfs}"></c:set>
						<c:set var="sumYwxFs" value="${sumYwxFs+vc.xsYwxfs}"></c:set>
						<c:set var="sumYsje" value="${sumYsje+vc.xsJe}"></c:set>
					</tr>
				</c:forEach>
				<!-- 合计列 -->
				<tr>
					<td></td>
					<td></td>
					<td style="color: red" align="center"><span id="cjr_sumZdj"
						name="cjr_sumZdj">${sumZdj}</span>
					</td>
					<td style="color: red" align="center"><span id="cjr_sumCgj"
						name="cjr_sumCgj">${sumCgj}</span>
					</td>
					<td style="color: red" align="center"><span id="cjr_sumXsj"
						name="cjr_sumXsj">${sumXsj}</span>
					</td>
					<td style="color: red" align="center"><span id="cjr_sumYj"
						name="cjr_sumYj">${sumYj}</span>
					</td>
					<td style="color: red" align="center"><span id="cjr_sumJsf"
						name="cjr_sumJsf">${sumJsf}</span>
					</td>
					<td style="color: red" align="center"><span id="cjr_sumTax"
						name="cjr_sumTax">${sumTax}</span>
					</td>
					<td style="color: red" align="center"><span id="cjr_sumHyxFs"
						name="cjr_sumHyxFs">${sumHyxFs}</span>
					</td>
					<td style="color: red" align="center"><span id="cjr_sumYwxFs"
						name="cjr_sumYwxFs">${sumYwxFs}</span>
					</td>
					<td style="color: red" align="center"><span id="cjr_sumYsje"
						name="cjr_sumYsje">${sumYsje}</span>
					</td>
					<td></td>
				</tr>
			</table></td>
	</tr>
</table>
