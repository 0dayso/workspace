<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript">
	function checkAdd(vsindex){
       if($("#checkb"+vsindex).attr('checked')){
       		$("#erxm"+vsindex).attr("datatype","s");
            $("#erxm"+vsindex).attr("disabled",false);
            $("#erywxm"+vsindex).attr("disabled",false);
            $("#csrq"+vsindex).attr("disabled",false);
       }else{
            $("#erxm"+vsindex).attr("disabled",true);
            $("#erywxm"+vsindex).attr("disabled",true);
            $("#csrq"+vsindex).attr("disabled",true);
       }
	}
</script>

<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width:100%;height:100%;margin-top: 0px;" id="hcInfo">
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/cjr_passenger.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">乘机人信息</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="float:left;width:100%;height:70px;">
				<tr style="background:#f1f1f1;">
				    <th>选择</th>
					<th>乘机人</th>
					<th>证件号码</th>
					<th>婴儿姓名</th>
					<th>婴儿英文名称</th>
					<th>出生日期</th>
				</tr>
				<c:set var="tmp" value="" />
				<c:forEach items="${jpkhddCjrList}" var="vc" varStatus="vsindex">
					<input value="${vc.cjr}" type="hidden" name="cjr"  id="cjr${vsindex.count}">
					<input value="${vc.cjrlx}" type="hidden" name="cjrlx"  id="cjrlx${vsindex.count}">
					<input value="${vc.zjhm}" type="hidden" name="zjhm"  id="zjhm${vsindex.count}">
					<tr id="jptr${vc.tkno}">
					    <td align = "center"><input type="checkbox" value="${vc.sxh}" vsindex="${vsindex.count}" name="checkb"  id="checkb${vsindex.count}" onclick="checkAdd('${vsindex.count}');"></td>
						<c:set var="cjrlx" value=""></c:set>
						<c:choose>
							<c:when test="${vc.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
							</c:when>
							<c:when test="${vc.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_et.gif"></c:set>
							</c:when>
							<c:when test="${vc.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_ye.gif"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
							</c:otherwise>
						</c:choose>
						
						<td align="center" class="wb_td02">${vc.cjr}<span><img src= "${cjrlx}" style='vertical-align:middle'></span></td>
						<td align="center" class="wb_td02">${vc.zjhm}</td>
						<td align="center" class="wb_td02"><input type="text" name="erxm" id="erxm${vsindex.count}" size="3" disabled value=""/><font style="color: red">*</font></td>
						<td align="center" class="wb_td02"><input type="text" name="erywxm" id="erywxm${vsindex.count}" size="7" disabled onkeyup="value =value.toUpperCase();"/></td>
						<td align="center" class="wb_td02"><input type="text" name="csrq" id="csrq${vsindex.count}" disabled value="" class="input-text Wdate" size="8" id="mindate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><font style="color: red">*</font></td>
					</tr>
				</c:forEach>
			</table>
		</td>
  </tr>
</table>