<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
<head>
<title>利润分析</title>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
<script type="text/javascript">
	$(function(){	
	    $("#gn_hkgs_m").autocompleteGnHkgs("hkgs");
	})
	function clearValue(obj,hkgs_id){
		 if(obj.value==""){
		  $("#"+hkgs_id).val("");
		 }
	}
	
	function toSearch(){
		var tjlx = $("#tjlx").val();
		if(tjlx=='0' || tjlx == ''||tjlx == undefined){
			$("#searchForm").attr("action","${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList");
		}else{
			$("#searchForm").attr("action","${ctx}/vedsb/jpcwgl/jplrfx/lrfxList");
		}
		layer.load('系统正在处理你的操作,请稍后!');
		$("#searchForm").submit();
	}
	
	function showXq(mxlx,ywlx,obj){
	    var param ="?mxlx="+mxlx+"&ywlx="+ywlx+"&wdbh="+obj.wdbh+"&zclx="+encodeURIComponent(obj.zclx)
	                    +"&tfid="+encodeURIComponent(obj.tfid)+"&hkgs="+obj.hkgs+"&type="+obj.type+"&tjlx="+obj.tjlx
	                    +"&hc="+obj.hc+"&ksrq="+"${param.ksrq}"+"&jsrq="+"${param.jsrq}";
		window.open("${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList"+param,"",980,580);
	}
</script>
</head>
<body>
<div class="container">
  	 <div id="search_bar">
       <div class="box">
		<div class="box_border">
			<div class="box_center pt10 pb10">
				<form action="${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList" id="searchForm" name="searchForm" method="post" >
              		<table class="table01" border="0" cellpadding="0" cellspacing="0" >
						<tr>
							<td class="xsys" style="text-align: right;">开始时间</td>
							<td>
							 	<input type="text" maxlength="20" name="ksrq" class="input-text Wdate" value="${empty param.ksrq ? vfn:getPreDay(vfn:dateShort(),-1) : param.ksrq}"  onFocus="WdatePicker()" style="width:100px" />
							</td>
							<td class="xsys" style="text-align: right;">结束时间</td>
							<td>
							 	<input type="text" maxlength="20" name="jsrq" class="input-text Wdate" value="${empty param.jsrq ? vfn:getPreDay(vfn:dateShort(),-1) : param.jsrq}"  onFocus="WdatePicker()" style="width:100px" />
							</td>
                            <td class="xsys" style="text-align: right;">网店</td>
							<td>
							 	<input type="text"  id="wdmc" name="wdmc" value="${param.wdmc}" style="width:100px" class="input-text lh25 srk"
							 		onclick="getDeptList(this,{qx:'1',isopenwd:'1'})" onkeyup="getDeptList(this,{qx:'1',isopenwd:'1'})" value_to_input="wd" />
							 	<input type="hidden" class="input-text lh25 srk" id="wd" name="wd" value="${param.wd}" />
							</td>
                            <td class="xsys" style="text-align: right;">政策类型</td>
							<td>
							 	<input type="text"  id="cplx" name="cplx" value="${param.cplx}" style="width:100px" class="input-text lh25 srk"/>
							</td>
						</tr>  
                        <tr>
                        	<td class="xsys" style="text-align: right;">投放方案</td>
							<td class="td_val">
							 	<input type="text" name="tffa" value="${param.tffa}" maxlength="20" style="width:100px" class="input-text lh25 srk"/>
							</td>
                            <td class="xsys" style="text-align: right;">航空公司</td>
							<td class="td_val">
							 	 <input type="text" id="gn_hkgs_m" name="gn_hkgs_m" class="input-text lh25 srk" style="width:100px" value="${param.gn_hkgs_m}" onchange="clearValue(this,'hkgs');"/>
	   					 	 	 <input type="hidden" id="hkgs" name="hkgs" value="${param.hkgs}">
							</td>
	                        <td class="xsys" style="text-align: right;">航程</td>
							<td class="td_val">
							 	<input type="text" id="jp_hc" name="jp_hc" value="${param.jp_hc}" style="width:100px" class="input-text lh25 srk"/>
							</td>
							<td class="xsys" style="text-align: right;">统计方式</td>
							<td class="td_val">
								<select name="tjlx" id="tjlx" style="width:180px" class="input-text lh25 srk">
									<option value="0" ${param.tjlx eq '0' ? "selected" : ""}>按明细</option>
	        						<option value="1" ${param.tjlx eq '1' ? "selected" : ""}>按网店统计</option>
	        						<option value="2" ${param.tjlx eq '2' ? "selected" : ""} >按网店、政策类型统计</option>
	       							<option value="3" ${param.tjlx eq '3' ? "selected" : ""} >按投放方案统计</option>
	        						<option value="4" ${param.tjlx eq '4' ? "selected" : ""} >按航程统计</option>
	        						<option value="5" ${param.tjlx eq '5' ? "selected" : ""} >按网店、政策类型、航程统计</option>
	        						<option value="6" ${param.tjlx eq '6' ? "selected" : ""} >按投放方案、航程统计</option>
	        						<option value="7" ${param.tjlx eq '7' ? "selected" : ""} >按网店、航司来统计</option>
	        						<option value="8" ${param.tjlx eq '8' ? "selected" : ""} >按政策渠道来统计</option>
								</select>
							</td>	
						</tr>
						<tr>
							<td class="xsys" style="text-align: right;">政策渠道</td>
							<td class="td_val">
								<select name="zcqd" class="input-text lh25 srk" style="width: 100px">
									<option value="">全部</option> 
			                        <option value="1000201">自有</option> 
				                  	<option value="1000203">平台</option> 
				                  	<option value="1000202">官网</option> 
				                  	<option value="1000204">NFD</option> 
				                  	<option value="1000205">官网旗舰（淘宝）</option> 
				                  	<option value="1000206">B2T</option> 
								</select>
							</td>
							<td class="xsys" style="text-align: right;">网店平台</td>
							<td class="td_val">
								<select name="wdpt" class="input-text lh25 srk" style="width: 100px">
									<option value="">全部</option> 
			                  	 	<option value="10100010" ${param.wdpt eq '10100010' ? 'selected' : ''}>去哪儿</option> 
			                  	 	<option value="10100011" ${param.wdpt eq '10100011' ? 'selected' : ''}>淘宝</option> 
			                  	 	<option value="10100012" ${param.wdpt eq '10100012' ? 'selected' : ''}>酷讯</option> 
			                  	 	<option value="10100050" ${param.wdpt eq '10100050' ? 'selected' : ''}>携程</option> 
			                  	 	<option value="10100024" ${param.wdpt eq '10100024' ? 'selected' : ''}>同程</option> 
								</select>
							</td>
							<td></td>
                            <td>
								<input type="button"  name="search" value="查询" class="ext_btn ext_btn_submit" onClick="toSearch();">
							 	<!--<input type="button"  name="search" value="导 出" class="ext_btn ext_btn_submit" onClick="toExport2('005844','01');">  -->
							</td>
						</tr>  
					</table>
				</form>
            </div>
          </div>
        </div>
      <div  class="mt10"> 
    </div>
   </div>
</div>
<c:set var="clv" value="#FFFFFF" ></c:set>
<c:set var="wdv1" value="0" ></c:set>
<c:set var="wdv2" value="0" ></c:set>
<c:if test="${not empty page.list and param.tjlx ne '0'}">
		<display:table id="vc" name="page.list" class="list_table" style="width:99%;" requestURI="${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList">
        	 	<c:set var="wdv1" value="${vc.WDBH}" ></c:set>
			 	<c:if test="${wdv1 eq wdv2 or wdv2 eq '0'}">
			   	<c:if test="${clv eq '#FFFFFF'}">
			     	<c:set var="clv" value="#FFFFFF" ></c:set>
			   	</c:if>
			   	<c:if test="${clv eq '#B4C7E3'}">
			     	<c:set var="clv" value="#B4C7E3" ></c:set>
			   	</c:if>
			 	</c:if>
			 
			 	<c:if test="${wdv1 ne wdv2 and wdv2 ne '0'}">
			   	<c:if test="${clv eq '#B4C7E3'}">
			     	<c:set var="clv" value="#FFFFFF" ></c:set>
			     	<c:set var="wdv2" value="0" ></c:set>
			  	</c:if>
			   	<c:if test="${clv eq '#FFFFFF' and wdv2 ne '0'}">
			     	<c:set var="clv" value="#B4C7E3" ></c:set>
			   	</c:if>
			 	</c:if>
			<display:column title="序号" style="background-color:${clv}" >${vc_rowNum}</display:column>
				<c:if test="${param.tjlx eq 1 or param.tjlx eq 2 or param.tjlx eq 5 or param.tjlx eq 7 or param.tjlx eq 8}">
					<display:column title="网店名称" property="WDMC" style="text-align:left;background-color:${clv}" group="1"  />
				</c:if>
				<c:if test="${param.tjlx eq 2 or param.tjlx eq 5 or param.tjlx eq 8}">
			    	<display:column title="政策类型" property="ZCLX" style="text-align:left;background-color:${clv}" group="1"  />
				</c:if>
				<c:if test="${param.tjlx eq 3 or param.tjlx eq 6}">
			    	<display:column title="投放方案" property="TFID" style="text-align:left;background-color:${clv}" group="1"   />
				</c:if>
				<c:if test="${param.tjlx eq 7}">
			    	<display:column title="航空公司" property="HKGS" style="text-align:left;background-color:${clv}" group="1"   />
				</c:if>
				<c:if test="${param.tjlx eq 4 or param.tjlx eq 5 or param.tjlx eq 6}">
			    	<display:column title="机票航程" property="JP_HC" style="text-align:left;background-color:${clv}" group="1"   />
				</c:if>
			<display:column title="订单数" expfield="DDS" format="{0,number,##.##}" style="text-align:right;background-color:${clv}" group="2" >
				<a href="javascript:showXq('1','',{wdbh:'${vc.WDBH}',zclx:'${vc.ZCLX}',tfid:'${vc.TFID}',hkgs:'${vc.HKGS}',hc:'${vc.JP_HC}',type:'${param.tjms}',tjlx:'${param.tjlx}'});" 
			   	 title="点击查看该订单明细">${vc.DDS}</a>
        	</display:column> 
			<display:column title="票数" expfield="CPS" format="{0,number,##.##}" style="text-align:right;background-color:${clv}" group="2">
        		<a href="javascript:showXq('2','${vc.YWLX}',{wdbh:'${vc.WDBH}',zclx:'${vc.ZCLX}',tfid:'${vc.TFID}',hkgs:'${vc.HKGS}',hc:'${vc.JP_HC}',type:'${param.tjms}',tjlx:'${param.tjlx}'});" 
        	   	title="点击查看机票明细">${vc.CPS}</a>
        	</display:column>
	    	<display:column title="平均票价" property="PJ_AVG" format="{0,number,##0.00}" style="text-align:right;background-color:${clv}" group="2"   />
	    	<display:column title="平均折扣" property="ZKL_AVG" format="{0,number,##0.00}" style="text-align:right;background-color:${clv}" group="2"  />  
	    	<display:column title="票价" property="PJ" format="{0,number,##0.00}" style="text-align:right;background-color:${clv}" group="2"   />
			<display:column title="机建税费" property="TAXHJ" format="{0,number,##0.00}" style="text-align:right;background-color:${clv}" group="2"   />
			<display:column title="销售价" property="XSHJ" format="{0,number,##0.00}" style="text-align:right;background-color:${clv}" group="2"   />
			<display:merge title="各利润来源">
				<display:column title="利润来源" property="LRQX_YWLXMC" style="text-align:left;background-color:${clv}"   />
				<display:column title="张数" expfield="LRQX_CPS" format="{0,number,##.##}" style="text-align:right;background-color:${clv}">
					<a href="javascript:showXq('4','${vc.LRQX_YWLX}',{wdbh:'${vc.WDBH}',zclx:'${vc.ZCLX}',tfid:'${vc.TFID}',hkgs:'${vc.HKGS}',hc:'${vc.JP_HC}',type:'${param.tjms}',tjlx:'${param.tjlx}'});" 
				   	title="点击查看机票明细">${vc.LRQX_CPS}</a>
				</display:column> 						     
				<display:column title="每张利润" property="LRQX_LR_AVG" format="{0,number,##0.00}" style="text-align:right;background-color:${clv}" total="true" />
				<display:column title="小计" property="LRQX_LRHJ" format="{0,number,##0.00}" style="text-align:right;background-color:${clv}" total="true" />
			</display:merge> 
				<c:set var="wdv2" value="${vc.WDBH}" ></c:set> 
				<display:footer>
				  	<tr>
				  	    <c:if test="${param.tjlx eq 1 or param.tjlx eq 3 or param.tjlx eq 4}">
				  	       <c:set var="colValue"  value="2" />
				  	    </c:if>
				  	    <c:if test="${param.tjlx eq 2 or param.tjlx eq 6 or param.tjlx eq 7}">
				  	       <c:set var="colValue"  value="3" />
				  	    </c:if>
				  	    <c:if test="${param.tjlx eq 5}">
				  	       <c:set var="colValue"  value="4" />
				  	    </c:if>
				  		<td style="text-align:right;background-color:#B4C7E3" colspan="${colValue}">合计：</td>
				  		<td style="text-align:right;background-color:#B4C7E3">${sumMap.DDS}</td>
				  		<td style="text-align:right;;background-color:#B4C7E3">${sumMap.CPS}</td>
				  		<td style="text-align:right;;background-color:#B4C7E3" colspan="2"></td>
				  		<td style="text-align:right;;background-color:#B4C7E3">${sumMap.PJ}</td>
				  		<td style="text-align:right;;background-color:#B4C7E3">${sumMap.TAXHJ}</td>
				  		<td style="text-align:right;;background-color:#B4C7E3">${sumMap.XSHJ}</td>
				  		<td style="text-align:right;;background-color:#B4C7E3" colspan="2"></td>
				  		<td style="text-align:right;;background-color:#B4C7E3">${sumMap.LR_AVG}</td>
				  		<td style="text-align:right;;background-color:#B4C7E3">${sumMap.LRHJ}</td>
				  	</tr>
  				</display:footer>
		</display:table>
</c:if>
		
<c:if test="${not empty page.list and param.tjlx eq '0'}">
	<multipage:pone actionFormName="page" page="${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList" var="fyurl" curl="curl"></multipage:pone>
	${fyurl}
	<display:table id="vc" name="page.list" class="list_table" style="width:5450px;" requestURI="${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList" >	 
		 <!-- 网店 -->
		<display:merge title="网店">
		 	  <display:column title="序号">${vc_rowNum}</display:column>
			  <display:column title="网店名称" property="WDBHMC" group="1" />
	  		  <display:column title="外部单号"  property="WBDH" group="2"/>
	  		  <display:column title="投放方案"  property="TFID" group="2"/>
	  		  <display:column title="政策代码"  property="WDZC_CODE" group="2"/>			   
			  <display:column title="政策类型"  property="WDZC_LX" group="2"/>    
		</display:merge>
		<display:merge title="票面">
		    <display:column title=" 利润来源"  property="YWLX" />
		    <display:column title=" 订单类型"  property="YWDDLX" />    
		    <display:column title=" 订单编号"  property="DDBH" />  
		    <display:column title=" PNR"  property="PNR_NO" /> 
		    <display:column title=" 大编码" property="HKGS_PNR" />
		    <display:column title=" 票号"  property="TKNO" />      
		    <display:column title=" 出票类型" property="CPLX" />
		    
			<display:column title=" 乘机人"  property="PNR_CJR" />
			<display:column title=" 航程" property="PNR_HC" style="text-align:center;"/>
			<display:column title="航程名称" property="HCMC" maxLength="15"/>
			<display:column title=" 航班号" expfield="PNR_HB" property="PNR_HB" style="text-align:center;"></display:column>
			<display:column title=" 舱位" expfield="PNR_CW" property="PNR_CW" style="text-align:center;"></display:column>
			<display:column title=" 起飞时间" expfield="PNR_CFRQSJ" property="PNR_CFRQSJ" style="text-align:center;" >
				${fn:substring(vc.PNR_CFRQSJ,5,16)}
			</display:column>
			<display:column title=" 账单价" property="PJ_CGJ" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" <span title='非机票业务显示其它金额'>机建</span>" property="PJ_JSF"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" <span title='火车、班车窗口费'>税费</span>" property="PJ_TAX"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" <span title='机建+税费'>税费小计</span>"  property="SFXJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" <span title='账单价+机建+税费'>票面小计</span>" property="PJXJ"  format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
			
		<display:merge title="采购">
			<display:column title=" 代理费率" property="PJ_SJJSFL"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 代理费" property="DLF"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购价" property="PJ_HJSJJSJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购金额" property="CGJE"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购服务费" property="HIDEDISCOUNT"  format="{0,number,##0.00}" style="text-align:right;"/>
			
			<display:column title=" 采购科目" property="CG_ZFKMMC" />
			<display:column title=" 外出票单位" property="CU_JSDW" />
			<display:column title=" 保险成本"  property="PJ_BXSJJSJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 邮寄成本"  property="PSCB"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购小计"  property="CGXJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			</display:merge>
			
			<display:merge title="采购后返">
			<display:column title=" 后返费率" property="JS_HXFL_EC" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 后返代理费" property="DLF_HF"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 特殊后返代理费" property="DLF_TSHF"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 后返毛利" property="HFML" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
			
		<display:merge title="销售">
			<display:column title=" 销售价" property="PJ_XSJ" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 加价/让利" property="JJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" SPA"  property="SPA"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 无奖励票价" property="RETURNTOINVALIDATE"  format="{0,number,##0.00}" style="text-align:right;"/>
			
			<display:column title=" 销售代理费率"  property="PJ_SJJSFL_XS"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 奖励费率" property="PJ_XJJSFL"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 奖励金额" property="JLJE"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 客户返现" property="KHFX"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 下级结算价" property="PJ_HJXJJSJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 其它" property="QT1"  format="{0,number,##0.00}" style="text-align:right;"/>
			
			<display:column title=" 保险销售价" property="PJ_BX"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 保险奖励" property="BXJL"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 保险结算价" property="PJ_BXXJJSJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 销售服务费" property="FWF"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 邮寄费" property="LONGRANGEGAVEIICK"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 结算金额" property="JSJE"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 支付金额" property="ZF_JE"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 支付人"  property="ZF_USERID" />
			<display:column title=" 支付状态" property="FKZT" />
			<display:column title=" 支付科目" property="ZF_FKKMMC" />
			<display:column title=" 支付时间" property="ZF_ZFSJ" />
			<display:column title=" 销售毛利" property="XSML" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
			
		<display:merge title="退票与采购办理">
			<display:column title=" 采购应退"  property="CGYT"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购实退"  property="TP_CGST"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购改签费用" property="CJ_GQFY"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购退票费"  property="CGTPF"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 退票采购服务费" property="CJ_FWF"  format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
			
		<display:merge title="退票与客户办理">
			<display:column title=" 客户办理时间" property="TP_WCSJ" />
			<display:column title=" 客户应退" property="KHYT"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 退票改签费用" property="TP_GQFY"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 客户退票费" property="KHTPF"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 退票客户服务费" property="TP_FWF"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 退废毛利"  property="TFML" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
			
		<display:merge title="改签">
			<display:column title=" 新编码" property="N_PNR" />
			<display:column title=" 新票号"  property="N_TKNO" />
			<display:column title=" 新航班号" property="N_HBH" />
			<display:column title=" 新舱位" property="N_CW" />
			<display:column title=" 新起飞时间" property="N_QFSJ" />
			<display:column title=" 改签费用"  property="CG_GQFY"  format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 改签毛利" property="GQML" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
			
		<display:merge title="二次营销">
			  <display:column title=" 保险票价"  property="ECYX_BX"  format="{0,number,##0.00}" style="text-align:right;"/>
			  <display:column title=" 保险采购价"  property="ECYX_BXSJJSJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			  <display:column title=" 保险结算价"  property="ECYX_BXXJJSJ"  format="{0,number,##0.00}" style="text-align:right;"/>
			  <display:column title=" 保险毛利"  property="ECYX_XSML"  format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
		<display:merge title="补差">
			  <display:column title=" 成本"  property="BC_CB"  format="{0,number,##0.00}" style="text-align:right;"/>
			  <display:column title=" 应收金额"  property="BC_JE"  format="{0,number,##0.00}" style="text-align:right;"/>
			  <display:column title=" 毛利"  property="BC_ML"  format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
			<display:column title=" 毛利小计" property="MLXJ" total="true"  format="{0,number,##0.00}" style="text-align:right;"/>
			
			<display:column title=" 备注"  property="BZBZ" maxLength="20" />
			<display:column title=" 订票员" property="DP_USERMC" />
			<display:column title=" 订票时间" property="DP_DATETIME" />
			<display:column title=" 出票员" property="CP_USERMC" />
			<display:column title=" 出票时间" property="CP_DATETIME" />
			<display:column title=" 改签员" property="GQ_USERMC" />
			<display:column title=" 改签时间" property="GQ_DATETIME" />
			<display:column title=" 退票员" property="TP_USERMC" />
			<display:column title=" 采购办理时间" property="CG_WCSJ" />
			<c:set var="XPJ_CGJ" value="${XPJ_CGJ + vc.PJ_CGJ}" />
			<c:set var="XPJ_JSF" value="${XPJ_JSF + vc.PJ_JSF}" />
			
			<c:set var="XPJ_TAX" value="${XPJ_TAX + vc.PJ_TAX}" />
			<c:set var="XSFXJ" value="${XSFXJ + vc.SFXJ}" />
			<c:set var="XPJXJ" value="${XPJXJ + vc.PJXJ}" />
			<c:set var="XDLF" value="${XDLF + vc.DLF}" />
			<c:set var="XPJ_HJSJJSJ" value="${XPJ_HJSJJSJ + vc.PJ_HJSJJSJ}" />
			
			<c:set var="XCGJE" value="${XCGJE + vc.CGJE}" />
			<c:set var="XHIDEDISCOUNT" value="${XHIDEDISCOUNT + vc.HIDEDISCOUNT}" />
			
			<c:set var="XPJ_BXSJJSJ" value="${XPJ_BXSJJSJ + vc.PJ_BXSJJSJ}" />
			<c:set var="XPSCB" value="${XPSCB + vc.PSCB}" />
			<c:set var="XCGXJ" value="${XCGXJ + vc.CGXJ}" />
			
			<c:set var="XDLF_HF" value="${XDLF_HF + vc.DLF_HF}" />
			<c:set var="XDLF_TSHF" value="${XDLF_TSHF + vc.DLF_TSHF}" />
			<c:set var="XHFML" value="${XHFML + vc.HFML}" />
			
			<c:set var="XPJ_XSJ" value="${XPJ_XSJ + vc.PJ_XSJ}" />
			<c:set var="XJJ" value="${XJJ + vc.JJ}" />
			<c:set var="XSPA" value="${XSPA + vc.SPA}" />
			<c:set var="XRETURNTOINVALIDATE" value="${XRETURNTOINVALIDATE + vc.RETURNTOINVALIDATE}" />
			
			<c:set var="XJLJE" value="${XJLJE + vc.JLJE}" />
			<c:set var="XKHFX" value="${XKHFX + vc.KHFX}" />
			<c:set var="XPJ_HJXJJSJ" value="${XPJ_HJXJJSJ + vc.PJ_HJXJJSJ}" />
			
			<c:set var="XQT1" value="${XQT1 + vc.QT1}" />
			<c:set var="XPJ_BX" value="${XPJ_BX + vc.PJ_BX}" />
			<c:set var="XBXJL" value="${XBXJL + vc.BXJL}" />
			<c:set var="XPJ_BXXJJSJ" value="${XPJ_BXXJJSJ + vc.PJ_BXXJJSJ}" />
			
			<c:set var="XFWF" value="${XFWF + vc.FWF}" />
			<c:set var="XLONGRANGEGAVEIICK" value="${XLONGRANGEGAVEIICK + vc.LONGRANGEGAVEIICK}" />
			<c:set var="XJSJE" value="${XJSJE + vc.JSJE}" />
			<c:set var="XZF_JE" value="${XZF_JE + vc.ZF_JE}" />
			<c:set var="XXSML" value="${XXSML + vc.XSML}" />
			<c:set var="XCGYT" value="${XCGYT + vc.CGYT}" />
			<c:set var="XTP_CGST" value="${XTP_CGST + vc.TP_CGST}" />
			
			<c:set var="XCJ_GQFY" value="${XCJ_GQFY + vc.CJ_GQFY}" />
			<c:set var="XCGTPF" value="${XCGTPF + vc.CGTPF}" />
			<c:set var="XCJ_FWF" value="${XCJ_FWF + vc.CJ_FWF}" />
			
			<c:set var="XKHYT" value="${XKHYT + vc.KHYT}" />
			<c:set var="XTP_GQFY" value="${XTP_GQFY + vc.TP_GQFY}" />
			<c:set var="XKHTPF" value="${XKHTPF + vc.KHTPF}" />
			
			<c:set var="XTP_FWF" value="${XTP_FWF + vc.TP_FWF}" />
			<c:set var="XTFML" value="${XTFML + vc.TFML}" />
			
			<c:set var="XCG_GQFY" value="${XCG_GQFY + vc.CG_GQFY}" />
			<c:set var="XGQML" value="${XGQML + vc.GQML}" />
			
			<c:set var="XECYX_BX" value="${XECYX_BX + vc.ECYX_BX}" />
			<c:set var="XECYX_BXSJJSJ" value="${XECYX_BXSJJSJ + vc.ECYX_BXSJJSJ}" />
			<c:set var="XECYX_BXXJJSJ" value="${XECYX_BXXJJSJ + vc.ECYX_BXXJJSJ}" />
			<c:set var="XECYX_XSML" value="${XECYX_XSML + vc.ECYX_XSML}" />
			
			<c:set var="XBC_CB" value="${XBC_CB + vc.BC_CB}" />
			<c:set var="XBC_JE" value="${XBC_JE + vc.BC_JE}" />
			<c:set var="XBC_ML" value="${XBC_ML + vc.BC_ML}" />
			<c:set var="XMLXJ" value="${XMLXJ + vc.MLXJ}" />
			
		<display:footer>
		<tr>
			<td colspan="19">本页小计：</td>
			<td style="text-align:right;">${XPJ_CGJ}</td>
			<td style="text-align:right;">${XPJ_JSF}</td>
			
			<td style="text-align:right;">${XPJ_TAX}</td>
			<td style="text-align:right;">${XSFXJ}</td>
			<td style="text-align:right;">${XPJXJ}</td>
			<td>&nbsp;</td>
			<td style="text-align:right;">${XDLF}</td>
			<td style="text-align:right;">${XPJ_HJSJJSJ}</td>
			
			<td style="text-align:right;">${XCGJE}</td>
			<td style="text-align:right;">${XHIDEDISCOUNT}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			
			<td style="text-align:right;">${XPJ_BXSJJSJ}</td>
			<td style="text-align:right;">${XPSCB}</td>
			<td style="text-align:right;">${XCGXJ}</td>
			<td>&nbsp;</td>
			
			<td style="text-align:right;">${XDLF_HF}</td>
			<td style="text-align:right;">${XDLF_TSHF}</td>
			<td style="text-align:right;">${XHFML}</td>
			
			<td style="text-align:right;">${XPJ_XSJ}</td>
			<td style="text-align:right;">${XJJ}</td>
			<td style="text-align:right;">${XSPA}</td>
			<td style="text-align:right;">${XRETURNTOINVALIDATE}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			
			<td style="text-align:right;">${XJLJE}</td>
			<td style="text-align:right;">${XKHFX}</td>
			<td style="text-align:right;">${XPJ_HJXJJSJ}</td>
			
			<td style="text-align:right;">${XQT1}</td>
			<td style="text-align:right;">${XPJ_BX}</td>
			<td style="text-align:right;">${XBXJL}</td>
			<td style="text-align:right;">${XPJ_BXXJJSJ}</td>
			
			<td style="text-align:right;">${XFWF}</td>
			<td style="text-align:right;">${XLONGRANGEGAVEIICK}</td>
			<td style="text-align:right;">${XJSJE}</td>
			<td style="text-align:right;">${XZF_JE}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="text-align:right;">${XXSML}</td>
			<td style="text-align:right;">${XCGYT}</td>
			<td style="text-align:right;">${XTP_CGST}</td>
			
			<td style="text-align:right;">${XCJ_GQFY}</td>
			<td style="text-align:right;">${XCGTPF}</td>
			<td style="text-align:right;">${XCJ_FWF}</td>
			<td>&nbsp;</td>
			<td style="text-align:right;">${XKHYT}</td>
			<td style="text-align:right;">${XTP_GQFY}</td>
			<td style="text-align:right;">${XKHTPF}</td>
			
			<td style="text-align:right;">${XTP_FWF}</td>
			<td style="text-align:right;">${XTFML}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="text-align:right;">${XCG_GQFY}</td>
			<td style="text-align:right;">${XGQML}</td>
			
			<td style="text-align:right;">${XECYX_BX}</td>
			<td style="text-align:right;">${XECYX_BXSJJSJ}</td>
			<td style="text-align:right;">${XECYX_BXXJJSJ}</td>
			<td style="text-align:right;">${XECYX_XSML}</td>
			
			<td style="text-align:right;">${XBC_CB}</td>
			<td style="text-align:right;">${XBC_JE}</td>
			<td style="text-align:right;">${XBC_ML}</td>
			<td style="text-align:right;">${XMLXJ}</td>
			<td colspan="10">&nbsp;</td>
		</tr>	
		<tr>
			<td colspan="19">合计：</td>
			<td style="text-align:right;">${sumMap.ZPJ_CGJ}</td>
			<td style="text-align:right;">${sumMap.ZPJ_JSF}</td>
			
			<td style="text-align:right;">${sumMap.ZPJ_TAX}</td>
			<td style="text-align:right;">${sumMap.ZSFXJ}</td>
			<td style="text-align:right;">${sumMap.ZPJXJ}</td>
			<td>&nbsp;</td>
			<td style="text-align:right;">${sumMap.ZDLF}</td>
			<td style="text-align:right;">${sumMap.ZPJ_HJSJJSJ}</td>
			
			<td style="text-align:right;">${sumMap.ZCGJE}</td>
			<td style="text-align:right;">${sumMap.ZHIDEDISCOUNT}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			
			<td style="text-align:right;">${sumMap.ZPJ_BXSJJSJ}</td>
			<td style="text-align:right;">${sumMap.ZPSCB}</td>
			<td style="text-align:right;">${sumMap.ZCGXJ}</td>
			<td>&nbsp;</td>
			
			<td style="text-align:right;">${sumMap.ZDLF_HF}</td>
			<td style="text-align:right;">${sumMap.ZDLF_TSHF}</td>
			<td style="text-align:right;">${sumMap.ZHFML}</td>
			
			<td style="text-align:right;">${sumMap.ZPJ_XSJ}</td>
			<td style="text-align:right;">${sumMap.ZJJ}</td>
			<td style="text-align:right;">${sumMap.ZSPA}</td>
			<td style="text-align:right;">${sumMap.ZRETURNTOINVALIDATE}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			
			<td style="text-align:right;">${sumMap.ZJLJE}</td>
			<td style="text-align:right;">${sumMap.ZKHFX}</td>
			<td style="text-align:right;">${sumMap.ZPJ_HJXJJSJ}</td>
			
			<td style="text-align:right;">${sumMap.ZQT1}</td>
			<td style="text-align:right;">${sumMap.ZPJ_BX}</td>
			<td style="text-align:right;">${sumMap.ZBXJL}</td>
			<td style="text-align:right;">${sumMap.ZPJ_BXXJJSJ}</td>
			
			<td style="text-align:right;">${sumMap.ZFWF}</td>
			<td style="text-align:right;">${sumMap.ZLONGRANGEGAVEIICK}</td>
			<td style="text-align:right;">${sumMap.ZJSJE}</td>
			<td style="text-align:right;">${sumMap.ZZF_JE}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="text-align:right;">${sumMap.ZXSML}</td>
			<td style="text-align:right;">${sumMap.ZCGYT}</td>
			<td style="text-align:right;">${sumMap.ZTP_CGST}</td>
			
			<td style="text-align:right;">${sumMap.ZCJ_GQFY}</td>
			<td style="text-align:right;">${sumMap.ZCGTPF}</td>
			<td style="text-align:right;">${sumMap.ZCJ_FWF}</td>
			<td>&nbsp;</td>
			<td style="text-align:right;">${sumMap.ZKHYT}</td>
			<td style="text-align:right;">${sumMap.ZTP_GQFY}</td>
			<td style="text-align:right;">${sumMap.ZKHTPF}</td>
			
			<td style="text-align:right;">${sumMap.ZTP_FWF}</td>
			<td style="text-align:right;">${sumMap.ZTFML}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="text-align:right;">${sumMap.ZCG_GQFY}</td>
			<td style="text-align:right;">${sumMap.ZGQML}</td>
			
			<td style="text-align:right;">${XECYX_BX}</td>
			<td style="text-align:right;">${XECYX_BXSJJSJ}</td>
			<td style="text-align:right;">${XECYX_BXXJJSJ}</td>
			<td style="text-align:right;">${XECYX_XSML}</td>
			
			<td style="text-align:right;">${XBC_CB}</td>
			<td style="text-align:right;">${XBC_JE}</td>
			<td style="text-align:right;">${XBC_ML}</td>
			<td style="text-align:right;">${XMLXJ}</td>
			<td colspan="10">&nbsp;</td>
		</tr>
		</display:footer>
	</display:table>
	${fyurl}
</c:if>
<c:if test="${empty page.list}">
	没有找到相关数据!
</c:if>
</body>	


