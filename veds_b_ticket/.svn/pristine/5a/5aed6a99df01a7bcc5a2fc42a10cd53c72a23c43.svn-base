<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
<head>
<title>利润分析明细</title>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
<script type="text/javascript">
	$(function(){	
	   	//航司控件
	    $("#gn_hkgs_m").autocompleteGnHkgs("hkgsMx");
	})
	function clearValue(obj,hkgs_id){
		 if(obj.value==""){
		  $("#"+hkgs_id).val("");
		 }
	}
	
	function toSearch(){
		$("#hkgs").val($("#hkgsMx").val());
		$("#hc").val($("#jp_hcMx").val());
		$('#ksrq').val($('#ksrqMx').val());
		$('#jsrq').val($('#jsrqMx').val());
		layer.load('系统正在处理你的操作,请稍后!');
		$("#listForm").submit();
	}
</script>
</head>
<body>
<div class="container">
  	 <div id="search_bar">
       <div class="box">
		<div class="box_border">
			<div class="box_center pt10 pb10">
				<form name="listForm" action="${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList" method="post" id="listForm">
					<input type="hidden" name="sexport" value="" />
					<input type="hidden" name="wdbh" value="${param.wdbh}" />
					<input type="hidden" name="zclx" value="${param.zclx}" />
					<input type="hidden" name="tfid" value="${param.tfid}" />
					<input type="hidden" name="tjlx" value="${param.tjlx}" />
					<input type="hidden" name="type" value="${param.type}" />
					<input type="hidden" id="hkgs"  name="hkgs" value="${param.hkgs}" />
					<input type="hidden" id="hc"  name="hc" value="${param.hc}" />
					<input type="hidden" name="ywlx" value="${param.ywlx}" />
					<input type="hidden" name="mxlx" value="${param.mxlx}" />
					<input type="hidden" id="ksrq"  name="ksrq" value="${param.ksrq}" />
					<input type="hidden" id="jsrq"  name="jsrq" value="${param.jsrq}" />
              		<table class="table01" border="0" cellpadding="0" cellspacing="0" >
						<tr>
					    	<td class="td_name">开始时间</td>
							<td class="td_val">
							 	<input type="text" id="ksrqMx" maxlength="20" name="ksrq" class="input-text Wdate" value="${empty param.ksrq ? vfn:getPreDay(vfn:dateShort(),-1) : param.ksrq}"  onFocus="WdatePicker()" style="width:100px" />
							</td>
							<td class="td_name">结束时间</td>
							<td class="td_val">
							 	<input type="text" id="jsrqMx" maxlength="20" name="jsrq" class="input-text Wdate" value="${empty param.jsrq ? vfn:getPreDay(vfn:dateShort(),-1) : param.jsrq}"  onFocus="WdatePicker()" style="width:100px" />
							</td>
							 <td class="td_name">航空公司</td>
							<td class="td_val">
								<input type="text" id="gn_hkgs_m" name="gn_hkgs_m" class="input-text lh25 srk" style="width:100px" value="${param.gn_hkgs_m}" onchange="clearValue(this,'hkgsMx');"/>
	   					 	 	<input type="hidden" id="hkgsMx"" name="hkgsMx" value="${param.hkgsMx}">
							</td>
	                        <td class="td_name">航程</td>
							<td class="td_val">
							 	<input type="text" id="jp_hcMx" name="jp_hcMx" value="${param.jp_hcMx}" style="width:100px" class="input-text lh25 srk"/>
							</td>
							<td class="td_val">
								<input type="button"  name="search" value="查询" class="ext_btn ext_btn_submit" onClick="toSearch();">
							 	<input type="button"  name="search" value="导 出" class="ext_btn ext_btn_submit" onClick="toExport2('005844','01');">
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
  
<c:if test="${not empty page.list}">
	<multipage:pone actionFormName="page" page="${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList" var="fyurl" curl="curl" />
	${fyurl}
	<display:table id="vc" name="page.list" class="list_table" style="width:5450px;"  decorator="org.displaytag.decorator.TotalTableDecorator"
  	requestURI="${ctx}/vedsb/jpcwgl/jplrfx/lrfxmxList" >		 
		 <!-- 网店 -->
		<display:merge title="网店">
			<display:column title="序号">${vc_rowNum}</display:column>
			<display:column title="网店名称" sortable="true" sortName="WDMC" property="WDMC" />
			<display:column title="政策代码" sortable="true" sortName="ZCDM" property="ZCDM" />
	  		<display:column title="外部单号" sortable="true" sortName="WBDH" property="WBDH" />			   
			<display:column title="政策类型" sortable="true" sortName="ZCLX" property="ZCLX" />    
			<display:column title="业务类型" sortable="true" sortName="YWLXMC" property="YWLXMC" />
		 </display:merge>     
 		<!-- 订单、正常/退废票明细 -->
	<c:if test="${xslx eq '1' or xslx eq '2'}">	
	    <display:merge title="票面">
			<display:column title=" PNR/单号" sortable="true" property="PNRNO" sortName="PNRNO" />
		    	<c:if test="${xslx eq '1'}">
		    <display:column title=" 订单编号" sortable="true" property="DDBH" sortName="DDBH" />
				</c:if>
			    <c:if test="${xslx eq '2'}">
		    <display:column title=" 大编码" sortable="true" property="HKGS_PNR" sortName="HKGS_PNR" />
		    <display:column title=" 票证状态" sortable="true" property="PZLX" sortName="PZLX" />
		    <display:column title=" 票证类型" sortable="true" property="CPLX" sortName="CPLX" />
			<display:column title=" 是否团队" sortable="true" sortName="TDPBZ" expfield="{TDPBZ,1,是团队,不是团队}">
				<c:if test="${vc.TDPBZ eq '1'}">
					是团队
				</c:if>
				<c:if test="${empty vc.TDPBZ  or vc.TDPBZ ne '1'}">
					不是团队
				</c:if>
			</display:column>				
			<display:column title=" 承运人" sortable="true" property="SZDH" sortName="SZDH" />
			<display:column title=" 票号" sortable="true" property="TKNO"  expfield="TKNO" sortName="TKNO" />
	            </c:if>
			<display:column title=" 乘机人" sortable="true" property="JP_CJR" sortName="JP_CJR" />
			<display:column title=" 航程" sortable="true" sortName="JP_HC" property="JP_HC" style="text-align:center;"/>
			<display:column title="航程名称" sortable="true" sortName="HCMC" property="HCMC" maxLength="15"/>
			<display:column title=" 航班号" sortable="true" sortName="JP_HBH" expfield="JP_HBH" property="JP_HBH" style="text-align:center;"></display:column>
			<display:column title=" 舱位" sortable="true" sortName="JP_CW" expfield="JP_CW" property="JP_CW" style="text-align:center;"></display:column>
			<display:column title=" 起飞时间" sortable="true" sortName="JP_CFDATETIME" expfield="JP_CFDATETIME" property="JP_CFDATETIME" style="text-align:center;" >
				${fn:substring(vc.JP_CFDATETIME,5,16)}
			</display:column>
			<display:column title=" 账单价" sortable="true" sortName="PJ_CGJ" property="PJ_CGJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" <span title='非机票业务显示其它金额'>机建</span>" sortable="true" sortName="PJ_JSF" property="PJ_JSF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" <span title='火车、班车窗口费'>税费</span>" sortable="true" sortName="PJ_TAX" property="PJ_TAX" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" <span title='机建+税费'>税费小计</span>" sortable="true" sortName="SFXJ" property="SFXJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" <span title='账单价+机建+税费'>票面小计</span>" sortable="true" sortName="PJXJ" property="PJXJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
			
		<display:merge title="采购">
			<display:column title=" 代理费率" sortable="true" sortName="PJ_SJJSFL" property="PJ_SJJSFL" format="{0,number,###.##%}" style="text-align:right;"/>
			<display:column title=" 代理费" sortable="true" sortName="DLF" property="DLF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购价" sortable="true" sortName="PJ_HJSJJSJ" property="PJ_HJSJJSJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购服务费" sortable="true" sortName="CJ_FWF" property="CJ_FWF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>	
			<display:column title="<span title='采购价+机建+税费+采购服务费'> 采购金额</span>" sortable="true" sortName="CGJE" property="CGJE" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="保险成本" sortable="true" sortName="CGJE" property="PSCB" total="true" format="{0,number,##0.00}" style="text-align:right;"/>			
			<display:column title="邮寄成本" sortable="true" sortName="CGJE" property="PSCB" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="采购科目" sortable="true" sortName="CG_ZFKMMC" property="CG_ZFKMMC" />
		</display:merge>
			
		<display:merge title="后返">
		    <display:column title=" 后返费率"  sortable="true" sortName="JS_HXFL_EC" property="JS_HXFL_EC" format="{0,number,###.##%}" style="text-align:right;"/>
		    <display:column title=" 后返<br>代理费" total="true"  property="DLF_HF" sortable="true" sortName="DLF_HF" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title=" 特殊后返<br>代理费" total="true"  sortable="true" sortName="DLF_TSHF" property="DLF_TSHF" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title=" <span title='后返代理费+特殊后返代理费'>后返代<br>理费小计"  total="true" sortable="true" sortName="HFXJ" property="HFXJ" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title=" 后返毛利" total="true" sortable="true" sortName="HFML" property="HFML" format="{0,number,##0.00}" style="text-align:right;"/>	     	
		</display:merge>
		
		<display:merge title="销售">
			<display:column title="加价/让利" sortable="true" sortName="JJ" property="JJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="销售价" sortable="true" sortName="PJ_XSJ" property="PJ_XSJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="销售服务费" sortable="true" sortName="TP_FWF" property="TP_FWF" total="true" format="{0,number,##0.00}" style="text-align:right;" />
			<display:column title=" 邮寄费" total="true" sortable="true" sortName="PSF" property="PSF" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 保险<br>份数" sortable="true" sortName="BXFS" property="BXFS" total="true" format="{0,number,##0}" style="text-align:right;"/>
			<display:column title=" 保险<br>销售" sortable="true" sortName="PJ_BX" property="PJ_BX" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 保险<br>下级结算价" sortable="true" sortName="PJ_BXXJJSJ" property="PJ_BXXJJSJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 保险<br>奖励" sortable="true" sortName="BXJL" property="BXJL" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 奖励<br>费率" sortable="true" sortName="PJ_XJJSFL" property="PJ_XJJSFL" total="true" format="{0,number,##0.00}" style="text-align:right;"/>						
			<display:column title=" <span title='销售价-下级结算价'>奖励<br>金额</span>" sortable="true" sortName="JLJE" property="JLJE" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 下级结算价" sortable="true" sortName="PJ_HJXJJSJ" property="PJ_HJXJJSJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="<span title='结算价+机建+税费+销售服务费+保险结算价+接车+其它'> 结算金额</span>" sortable="true" sortName="JSJE" property="JSJE" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="<span title='（结算价-采购价-客户留款）+（保险结算价-保险成本）+（销售服务费-采购服务费）'>销售毛利</span>" sortable="true" sortName="XSML" property="XSML" total="true" format="{0,number,##0.00}" style="text-align:right;"/>	
		</display:merge>
			<display:column title="<span title='销售毛利+退废毛利'>毛利小计</span>" sortable="true" sortName="MLXJ" property="MLXJ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
		    
	<!-- 退废票 -->
		<c:if test="${xslx eq '2'}">
		<display:merge title="退废票毛利">
		    <display:column title="客户办理时间"  sortable="true" sortName="TP_WCSJ" property="TP_WCSJ" />
		    <display:column title="采购办理时间"  sortable="true" sortName="CG_WCSJ" property="CG_WCSJ" />
		    <display:column title="采购改签<br>退款" property="CJ_GQFY" sortable="true" sortName="CJ_GQFY" format="{0,number,#0.00}" style="text-align:right;"/>     		
		    <display:column title="采购应退<br>金额" property="TP_HXJE" sortable="true" sortName="TP_HXJE" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title="采购实退<br>金额" property="TP_CGST" sortable="true" sortName="TP_CGST" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title="客户改签<br>退款" property="TP_GQFY" sortable="true" sortName="TP_GQFY" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title="客户应退<br>金额" property="TP_CUSTJE" sortable="true" sortName="TP_CUSTJE" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title="上级退票费" property="JP_PJHK" sortable="true" sortName="JP_PJHK" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title="下级退票费" property="JP_JPHK" sortable="true" sortName="JP_JPHK" format="{0,number,#0.00}" style="text-align:right;"/>
		    <display:column title="退废毛利" property="TFML" sortable="true" sortName="TFML" format="{0,number,#0.00}" style="text-align:right;"/>   	
		</display:merge>	  
	     	<display:column title="毛利小计" property="MLXJ" sortable="true" sortName="MLXJ" format="{0,number,#0.00}" style="text-align:right;"/>		   
		</c:if>  
		<display:merge title="收款"> 
			<display:column title="收款状态" property="FKZT" sortable="true" sortName="FKZT" expfield="FKZT" style="text-align:center;" />
			<display:column title="收款金额" property="ZF_JE" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="收款科目" property="ZF_FKKMMC" style="text-align:left;" />
			<display:column title="收款时间" property="ZF_ZFSJ" expfield="ZF_ZFSJ" style="text-align:left;" > 
				${fn:substring(vc.ZF_ZFSJ,5,16)}
			</display:column>   	  		  
		</display:merge>	
		<display:merge title="订票">
			<display:column title=" 订票员" sortable="true" sortName="DP_USERMC" property="DP_USERMC"/>
			<display:column title=" 订票时间" sortable="true" sortName="DP_DATETIME" expfield="DP_DATETIME" style="text-align:center;" >
				${fn:substring(vc.DP_DATETIME,5,16)}
			</display:column>
		</display:merge>
		<c:if test="${xslx eq '2'}">
			<display:column title="退票申请人" sortable="true" sortName="TP_USERMC" property="TP_USERMC"/>
		</c:if>
			<display:column title="订单备注" property="DDBZ" maxLength="5"/>
	</c:if>
		
	<!-- 改签票明细 -->
	<c:if test="${xslx eq '3'}">	
		<display:merge title="改签">
			<display:column title="改签单号" sortable="true" sortName="DDBH" property="DDBH" />
			<display:column title="航程" sortable="true" sortName="JP_HC" property="JP_HC" style="text-align:center;" />
			<display:column title="航程名称" sortable="true" sortName="HCMC" property="HCMC" />
			<display:column title="改签状态" sortable="true" sortName="TFZT" property="TFZT" />
			<display:column title="改签时间" sortable="true" sortName="CG_WCSJ" expfield="CG_WCSJ" property="CG_WCSJ">
				${fn:substring(vc.CG_WCSJ,5,16)}
			</display:column>
			<display:column title="改签前PNR" sortable="true" sortName="O_PNR" property="O_PNR" />		
			<display:column title="改签前票号" sortable="true" sortName="O_TKNO" property="O_TKNO" />
			<display:column title="改签前航班号" sortable="true" sortName="O_HBH" property="O_HBH" />
			<display:column title="改签前舱位" sortable="true" sortName="O_CW" property="O_CW" />
			<display:column title="改签前起飞时间" sortable="true" sortName="O_QFSJ" expfield="O_QFSJ" property="O_QFSJ">
				${fn:substring(vc.O_QFSJ,5,16)}
			</display:column>
			<display:column title="改签后PNR" sortable="true" sortName="PNRNO" property="PNRNO" />
			<display:column title="改签后票号" sortable="true" sortName="TKNO" property="TKNO" />
			<display:column title="改签后航班号" sortable="true" sortName="JP_HBH" property="JP_HBH" />
			<display:column title="改签后舱位" sortable="true" sortName="JP_CW" property="JP_CW" />
			<display:column title="改签后起飞时间" sortable="true" sortName="JP_CFDATETIME" expfield="JP_CFDATETIME" property="JP_CFDATETIME">
				${fn:substring(vc.JP_CFDATETIME,5,16)}
			</display:column>
			<display:column title="采购改签费用" sortable="true" sortName="CJ_GQFY" property="CJ_GQFY" format="{0,number,#0.00}" style="text-align:right;" />     
			<display:column title="客户改签费用" sortable="true" sortName="TP_GQFY" property="TP_GQFY" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="服务费" sortable="true" sortName="JP_JPHK" property="JP_JPHK" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="应收金额" sortable="true" sortName="JSJE" property="JSJE" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="毛利" sortable="true" sortName="XSML" property="XSML" format="{0,number,#0.00}" style="text-align:right;" />
		</display:merge>
		<display:merge title="收款"> 
			<display:column title="收款状态"  property="FKZT" sortable="true" sortName="FKZT" expfield="FKZT" style="text-align:center;" />
			<display:column title="收款金额" property="ZF_JE" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="收款科目" property="ZF_FKKMMC" style="text-align:left;" />
			<display:column title="收款时间" property="ZF_ZFSJ" expfield="ZF_ZFSJ" style="text-align:left;" > 
				${fn:substring(vc.ZF_ZFSJ,5,16)}
			</display:column>	 			  		  
		</display:merge>
		<display:merge title="订票">
			<display:column title=" 订票员" sortable="true" sortName="DP_USERMC" property="DP_USERMC"/>
			<display:column title=" 订票时间" sortable="true" sortName="DP_DATETIME" expfield="DP_DATETIME" style="text-align:center;" >
				${fn:substring(vc.DP_DATETIME,5,16)}
			</display:column>
		</display:merge>
			<display:column title="改签申请人" sortable="true" sortName="TP_USERMC" property="TP_USERMC"/>
			<display:column title="订单备注" property="DDBZ" maxLength="5"/>
  	</c:if>	  
  		
  	<!-- 保险明细 -->
  	<c:if test="${xslx eq '4'}">	
 		<display:merge title="保险">
			<display:column title="保险险种" sortable="true" sortName="BXLXMC" property="BXLXMC" />
			<display:column title="投保状态" sortable="true" sortName="PZLX" property="PZLX" style="text-align:center;" />
			<display:column title="PNR" sortable="true" sortName="PNRNO" property="PNRNO" />
			<display:column title="票号" sortable="true" sortName="TKNO" property="TKNO" />
			<display:column title="保单号" sortable="true" sortName="BX_DH" property="BX_DH" />
			<display:column title="航班号" sortable="true" sortName="JP_HBH" property="JP_HBH" />		
			<display:column title="生效日期" sortable="true" sortName="JP_CFDATETIME" expfield="JP_CFDATETIME" property="JP_CFDATETIME">
				${fn:substring(vc.JP_CFDATETIME,5,16)}
			</display:column>
			<display:column title="截止日期" sortable="true" sortName="BXYXQ" expfield="BXYXQ" property="BXYXQ">
				${fn:substring(vc.BXYXQ,5,16)}
			</display:column>
			<display:column title="被保人" sortable="true" sortName="JP_CJR" property="JP_CJR" />
			<display:column title="证件类型" sortable="true" sortName="ZJLX" property="ZJLX" />
			<display:column title="证件号码" sortable="true" sortName="ZJHM" property="ZJHM" />
			<display:column title="采购价" sortable="true" sortName="PJ_BXSJJSJ" property="PJ_BXSJJSJ" format="{0,number,#0.00}" style="text-align:right;" />     
			<display:column title="销售价" sortable="true" sortName="PJ_BX" property="PJ_BX" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="结算价" sortable="true" sortName="PJ_BXXJJSJ" property="PJ_BXXJJSJ" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="毛利" sortable="true" sortName="XSML" property="XSML" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="投/退保时间" sortable="true" sortName="TB_DATETIME" expfield="TB_DATETIME" property="TB_DATETIME">
				${fn:substring(vc.TB_DATETIME,5,16)}
			</display:column>
			<display:column title="收/退款状态" sortable="true" sortName="FKZT" property="FKZT" />								
			<display:column title="收/退款金额" sortable="true" sortName="ZF_JE" property="ZF_JE" format="{0,number,#0.00}" style="text-align:right;" />
		</display:merge> 		
  	</c:if>
  		
  	<!-- 邮寄行程单明细 -->
  	<c:if test="${xslx eq '5'}">	
			<display:column title="PNR" sortable="true" sortName="PNRNO" property="PNRNO" />
			<display:column title="订单编号" sortable="true" sortName="DDBH" property="DDBH" />
			<display:column title="乘机人" sortable="true" sortName="JP_CJR" property="JP_CJR" />
			<display:column title="邮寄费" sortable="true" sortName="PSF" property="PSF" format="{0,number,#0.00}" style="text-align:right;" />     
			<display:column title="邮寄成本" sortable="true" sortName="PSCB" property="PSCB" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="毛利" sortable="true" sortName="XSML" property="XSML" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="邮寄信息" sortable="true" sortName="DDBZ" property="DDBZ" />				
    </c:if>
  		
  	<!-- 邮寄行程单明细 -->
  	<c:if test="${xslx eq '6'}">	
			<display:column title="PNR" sortable="true" sortName="WDMC" property="WDMC" />
			<display:column title="订单编号" sortable="true" sortName="DDBH" property="DDBH" />
			<display:column title="订单类型" sortable="true" sortName="DDLX" property="DDLX" />
			<display:column title="事由" sortable="true" sortName="DDBZ" property="DDBZ" />
			<display:column title="补差金额" sortable="true" sortName="JSJE" property="JSJE" format="{0,number,#0.00}" style="text-align:right;" />
			<display:column title="收款状态" property="FKZT" sortable="true" sortName="FKZT" expfield="FKZT" style="text-align:center;" />
			<display:column title="收款科目" property="ZF_FKKMMC" style="text-align:left;" />
			<display:column title="收款时间" property="ZF_ZFSJ" expfield="ZF_ZFSJ" sortable="true" sortName="ZF_ZFSJ">
				${fn:substring(vc.DP_DATETIME,5,16)}
			</display:column>  
			<display:column title="乘机人" sortable="true" sortName="DP_USERMC" property="DP_USERMC"  />     
			<display:column title="申请时间" property="DP_DATETIME" expfield="DP_DATETIME" sortable="true" sortName="DP_DATETIME">
			    ${fn:substring(vc.DP_DATETIME,5,16)}
			</display:column>
  	</c:if>
  </display:table>
</c:if>
<c:if test="${empty page.list}">
	没有找到相关数据!
</c:if>
</body>
</html>