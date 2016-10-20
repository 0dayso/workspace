<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
 <html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/ticket_cp.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>超级电商集中出票</title>
<%--集中出票页面的js都放在此页面--%>
<%@include file="/WEB-INF/views/jzcp/cpmain/jzcpjs.jsp" %>
</head>
<body style="background:#fff;">
<c:set var="filterinfo" value="" />
<c:if test="${fn:indexOf(cpszOP.opGlCfcs,hdList[0].cfcity)>-1}">
	<c:set var="filterinfo" value="出港城市为[${cpszOP.opGlCfcs}]  " />
</c:if>
<c:if test="${fn:indexOf(cpszOP.opGlHkgs,jpKhdd.hkgs)>-1}">
	<c:set var="filterinfo" value="${filterinfo}航空公司为[${cpszOP.opGlHkgs}]" />
</c:if>
<c:if test="${not empty jpKhdd.dpOffice and fn:indexOf(cpszOp.opGlOffice,jpKhdd.dpOffice)>-1}">
	<c:set var="filterinfo" value="${filterinfo}OFFICE号为[${cpszOP.opGlOffice}]" />
</c:if>
<div class="ticket_cp">
  <!--基础信息-->
  <div class="basic_info">
    <table width="100%" border="1">
      <tr>
        <th width="120px" >PNR/大编码</th>
        <td width="180px">${jpKhdd.cgPnrNo}/${jpKhdd.cgHkgsPnr}</td>
        <th width="170px" >网店</th>
        <td width="220px">${jpKhdd.ex.WD.wdmc}</td>
        <th width="160px">网店政策类型</th>
        <td width="150px">${jpKhdd.wdZclx}</td>
      </tr>
      <tr>
        <th >乘机人</th>
        <td>${vfn:cut(jpKhdd.cjr,10)}</td>
        <th >航程/航班/舱位</th>
        <td><span title="${hcInfo}">${jpKhdd.hc}(${jpKhdd.ex.HCLXMC})/${jpKhdd.cgHbh}/${jpKhdd.cgCw}</span></td>
        <th >起飞时间</th>
        <td>${vfn:format(jpKhdd.cfrq,'yyyy-MM-dd HH:mm')}</td>
      </tr>
      <tr>
        <th >票价/机建/税费</th>
        <td>
        	(${cjrList[0].cgZdj}+${cjrList[0].cgJsf}+${cjrList[0].cgTax})*${fn:length(cjrList)}=
        	<strong style="color:#FF0000;">￥${jpKhdd.cgZdj+jpKhdd.cgJsf+jpKhdd.cgTax}</strong>
        </td>
        <th >销售价/销售金额</th>
        <td><strong style="color:#FF0000;">￥${jpKhdd.xsPj}/￥${jpKhdd.xsJe}</strong></td>
        <th >三方协议号</th>
        <td >
        	<select name="sfxyhsel" id="sfxyhsel" class="input01 width100" onchange="changeXyh(this);" style="height:22px;"></select>
			<input name="sfxyh" id="sfxyh" type="hidden" class="input01" value="" style="width:80px;" />
        </td>
      </tr>
      <tr>
      	<th>订单备注</th>
      	<td colspan="5">
      		<strong style="color:#FF0000;">
      			${jpKhdd.bzbz}
      		</strong>
      	</td>
      </tr>
    </table>
    <div class="pusumInfo" id="platstatus"></div>
  </div>
  <!--基础信息结束-->
  <!--列表表头 Start -->
  <div class="tickets_issuance">
    <div class="t_head">
      <table border="0" width="100%" class="list_table CRZ">
        <tbody>
          <tr>
            <th width="75px" align="left">操作</th>
            <th width="120px" align="left"> 采购渠道 </th>
            <th width="90px" align="left"> 票证类型 </th>
            <th width="60px" align="left"> 票价 </th>
            <th width="80px" align="left">原始/实际返点</th>
            <th width="60px" align="left"> 代理费 </th>
            <th width="70px" align="left"> 采购金额 <i class="i_arr_ud"></i> </th>
            <th width="60px" align="left"> 后返利润 </th>
            <th width="90px" align="left"> 工作时间 </th>
            <th width="284px" align="left"> 
            	 <div> 
	            	<div class="fl" style="margin-left:10px;margin-right:5px;">备注</div>
	                <div class="fr"> 
	                	<span style=" cursor: pointer;" >
	                  		<input  class="a1_button"  value="点击验舱" type="button" onclick="valiHbhCw(this);"/>
	                  	</span>
	                  	<span class="mr10" >
	                  		<a style="cursor: pointer;text-decoration:none;vertical-align: middle;" id="ptshow" onclick="showPt()" show="1" href="###" title="点击展开或收起平台名称列表"> 
	                  			<img src="${ctx}/static/images/jzcp/show_up_blue.png"  id="ptimg"/>
	                  		</a> 
	                  	</span>
	                  	<span class="mr10">
		                  <input  title="勾选后显示PAT价格内容" id="spat"  type="checkbox" onclick="showPat(this)"/>
		                  <label style="cursor: pointer;" title="如果您在打开本页面后编码内容发生了异动，请双击实时获取PAT价格内容，以确保下单到平台的价格是准确的" id="dbpat">
		                  PAT:A
		                  </label>
	                  	<a href="${ctx}/vedsb/cpsz/jpcpymsz/viewjpcpymsz" target="blank" class="mr20"> <img src="${ctx}/static/images/jzcp/set.png" width="19" height="19" align="absmiddle" /></a>&nbsp;&nbsp; </span></div>
	             </div>
             </th>
          </tr>
        </tbody>
      </table>
    </div>
    <c:if test="${cpszOP.sfkq eq '1'}">
	    <div class="m_filter" id="ptfilter_list_div">
	    	<c:if test="${not empty filterinfo}" var="boolfilter">
				<span>管理员设置了<span style="font-weight:bold;color:red">${filterinfo}</span>的订单不能选择异地平台政策出票！
			</c:if>	
			<c:if test="${!boolfilter}">
				<%@include file="/WEB-INF/views/jzcp/cpmain/oplist.jsp"%>
			</c:if>
	    </div>
	    <div class="clear"></div>
	</c:if>
	<%--Pat内容显示区域 --%>
	<div id="patcontext_div"  style="display:none;">
		<textarea id="patcontextval" style="background-color: #000000;color:#00FF00;width: 998px;height: 80px;border: 0px;font-size: 16;" >
			${jpKhdd.patLr}
		</textarea>
	</div>
	<%-- 加载本地政策--%>
	<c:if test="${cpszBSPET.sfkq eq '1' or cpszBOP.sfkq eq '1'}">
	<div id="bsp_policy_div" class="t_body" style="margin-bottom:10px;">
		<div class="t_body">
			<table width="100%" border="0" cellspacing="0">
				<tr>
					<td width="75px" class="compare_color">
						<c:if test="${cpszBSPET.sfkq eq '1'}">
							<div>
								<input class="ext_btn_submit2"  name="button" value="BSPET" type="button" onclick="autoetdz('BSP','${jpKhdd.ddbh}')"/>
							</div>
						</c:if>
						<c:if test="${cpszBOP.sfkq eq '1'}">
							<div style="margin-top:5px">
								<input class="ext_btn_submit2"  name="button" value="  BOP  " type="button" onclick="autoetdz('BOP','${jpKhdd.ddbh}')"/>
							</div>
						</c:if>
					</td>
					<td width="120px" class="compare_color">
						<select id="officeid"  name="officeid" style="width:116px;">
							<option value="">=本地OFFICE=</option>
								<c:forEach items="${officelist}" var="jpoffice">
								  	<option value="${jpoffice.key}"  ${fn:length(officelist) == 1 ? 'selected':''}>${jpoffice.key}</option>
								</c:forEach>
						</select> 
					</td>
					<td width="90px" class="compare_color">BSPET</td>
					<td width="60px" class="compare_color"><div class="font_orange font14">${vfn:format(jpKhdd.cgZdj,'#.##')}</div></td>
					<td width="80px" class="compare_color">-</td>
					<td width="60px" class="compare_color">-</td>
					<td width="70px" class="compare_color"><div class="font_orange font14"><b>${vfn:format(jpKhdd.cgZdj,'#.##')}</b></div></td>
					<td width="60px" class="compare_color"></td>
					<td width="90px" class="compare_color"></td>
					<td  width="284px" style="text-align:left" class="compare_color"></td>
				</tr>
			</table>
		</div>
	</div>
	</c:if>
	<%-- 加载平台政策--%>
	<div id="op_policy_div" class="t_body">
		
	</div>
  </div>
<!--C站代购 Start -->
<div id="b2c_policy_div" class="loginBox" >

</div>
<!--B2B代购 Start -->
<div id="b2b_policy_div" class="loginBox" >
	
</div>
<div id="b2b_info_div" class="loginBox" > </div>
</div>

</body>
</html>
