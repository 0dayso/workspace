<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:if test="${not empty list}">
<multipage:pone page="${ctx}/vedsb/bbzx/cpbb/viewlist" actionFormName="page" var="surl"></multipage:pone>
${surl}
<c:if test="${empty param.tjfs or param.tjfs eq 1}">
	<display:table id="vc" name="page.list" class="list_table" style="width:3500px;" 
	requestURI="${ctx}/vedsb/bbzx/cpbb/viewlist?dtableid=vc" 
	decorator="org.displaytag.decorator.TotalTableDecorator"
	sort="external" excludedParams="dtableid"   reportid="${reportid}">
	       <display:column title="序号" style="text-align:center;"  expfield="RO">${vc_rowNum }</display:column>
	       <display:column title="操作" style="width:110px;text-align:center;">
	       <a href='javascript:void(0);' onclick = 'updateJp("${vc.ID}","${vc.DDBH}")' title='点击将修改该机票信息'>修改</a>
			&nbsp;<a href='javascript:void(0);' onclick = 'deleteJp("${vc.ID}","${vc.SKZT}")'>删除</a></display:column>
		   <display:column title="票证<br>状态"  style="text-align:center;width:70px;"  expfield="JPZT">
			        ${vc.JPZT eq '1' ? '正常票' : '' }
			        ${vc.JPZT eq '2' ? '退票' : '' }
			        ${vc.JPZT eq '3' ? '废票' : '' }
		    </display:column>
		    <display:column  title=" 采购来源"  style="text-align:center;width:60px;" property="CGLY"></display:column>
		    <display:column title="国内<br>国际" style="text-align:center;width:50px;" expfield="GNGJ">
		    	${vc.GNGJ eq '1' ? '国内' : '国际' }
		    </display:column>
		    <display:column style="text-align:center;width:50px;" title="PNR" expfield="XS_PNR_NO">
		    	<a href="###" class="copytext" copytext='${vc.XS_PNR_NO}' onclick="enterLogPage('${vc.DDBH}');">
	 		         <font title="点击查看最新异动信息">${vc.XS_PNR_NO}</font>
	 	         </a>
		    </display:column>
		    <display:column  style="text-align:center;width:75px;" title="大编码" property="XS_HKGS_PNR" expfield="XS_HKGS_PNR"></display:column>
		    <display:column  style="text-align:center;width:64px;" title="航空公司" property="HKGS" ></display:column>
		    <display:column style="text-align:center;width:115px;" title="票号"  expfield="TKNO">
		    	<a href="javascript:void(0);" onclick="showTicketDetail('${vc.ID }','${vc.TKNO }')">${vc.TKNO }</a>
		    </display:column>
		    
		    <display:column property="XCDH" title="行程单号" style="text-align:center;width:100px;"/>
		    <display:column property="WBDH" title="外部单号" style="text-align:center;width:100px;"/>
			<display:column title="网店平台" expfield="WDPT"  style="text-align:center;width:80px;">
				<c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
              	 		${vc.WDPT eq  onewdpt.id ? onewdpt.mc : ''}
              	 </c:forEach>
			</display:column>
			<display:column property="EX.WDID.wdmc" title="网店名称"  expfield="WDID" style="text-align:center;width:86px;"/>
			<display:column property="WD_ZCDM" title="网店<br>政策代码" style="text-align:center;width:75px;"/>
		    <display:column property="WD_ZCLX" title="网店<br>政策类型" style="text-align:center;width:75px;"/>
		    <display:column property="WD_DDLX" title="网店<br>订单类型" style="text-align:center;width:75px;"/>
			<display:column property="CJR" title="乘机人" style="text-align:left;width:60px;"/>
			<display:column title="乘机人<br>类型" style="text-align:center;width:50px;" expfield="CJRLX"  >
				${vc.CJRLX eq 1 ? "成人" : (vc.CJRLX eq 2 ? "儿童" : "婴儿") }
			</display:column>
			<display:column title="乘机人<br>证件类型" style="text-align:center;width:65px;" expfield="ZJLX" >
				<c:if test="${vc.ZJLX eq 'NI' }">
					身份证
				</c:if>
				<c:if test="${vc.ZJLX eq 'ID' }">
					护照
				</c:if>
			</display:column>
		    <display:column property="ZJHM" title="乘机人<br>证件号码" style="text-align:center;width:100px;"/>
		    
		    <%-- <display:column property="SJHM" title="乘机人<br>手机号码" style="text-align:left;width:80px;"/> --%>
			<display:column property="HC" title="航程" style="text-align:center;width:50px;"/>
			<%-- <display:column property="HCS" title="航程数" style="text-align:left;width:45px;"/> --%>
			<%-- <display:column property="HCMC" title="航程名称" style="text-align:left;width:100px;"/> --%>
		    <display:column property="XS_HBH" title="航班号" style="text-align:center;width:50px;"/>
		    <display:column property="XS_CW" title="舱位" style="text-align:center;width:40px;"/>
			<display:column property="CFRQ" title="起飞时间" style="text-align:center;width:120px;"/>
			<display:column property="XS_ZDJ" title="销售<br>账单价" style="text-align:right;width:70px;" format="{0,number,#0.##}" total="true"/>
			<display:column property="XS_JSF" title="销售<br>机建" style="text-align:right;width:60px;" format="{0,number,#0.##}" total="true"/>
		    <display:column property="XS_TAX" title="销售<br>税费" style="text-align:right;width:60px;" format="{0,number,#0.##}" total="true"/>
		    <display:column property="XS_PJ" title="销售价" style="text-align:right;width:70px;" format="{0,number,#0.##}" total="true"/>
			<display:column property="XS_XJ" title="销售<br>小计" style="text-align:right;width:80px;" format="{0,number,#0.##}" total="true"/>
			<display:column title="航意险<br>份数/利润" style="text-align:right;width:80px;">
			${vc.XS_HYXFS }/${vc.XS_HYXLR }
			</display:column>
			<display:column title="延误险<br>份数/利润" style="text-align:right;width:80px;">
			${vc.XS_YWXFS }/${vc.XS_YWXLR }
			</display:column>
		    <display:column property="XS_YJF" title="邮寄费" style="text-align:right;width:70px;" format="{0,number,#0.##}" total="true"/>
		    <display:column property="CG_HBH" title="采购<br>航班号" style="text-align:center;width:70px;"/>
		    <display:column property="CG_CW" title="采购<br>舱位" style="text-align:center;width:50px;"/>
		    <display:column property="CG_ZDJ" title="采购<br>账单价" style="text-align:right;width:70px;" format="{0,number,#0.##}" total="true"/>
			<display:column property="CG_PJ" title="采购价" style="text-align:right;width:70px;" format="{0,number,#0.##}" total="true"/>
			<display:column property="CG_JSF" title="采购<br>机建" style="text-align:right;width:60px;" format="{0,number,#0.##}" total="true"/>
			<display:column property="CG_TAX" title="采购<br>税费" style="text-align:right;width:60px;" format="{0,number,#0.##}" total="true"/>
		    <display:column property="JPML" title="机票毛利" style="text-align:right;width:80px;" format="{0,number,#0.##}" total="true"/>
		    <display:column property="XSML" title="销售毛利" style="text-align:right;width:80px;" format="{0,number,#0.##}" total="true"/>
			<display:column title="支付<br>状态"   expfield="SKZT"  style="text-align:center;width:60px;">
				${vc.SKZT==1?"已支付":"未支付" }
			</display:column>
			<display:column property="EX.SKKM.kmmc" title="支付科目" expfield="SKKM" style="text-align:center;width:80px;"/>
			<display:column property="XS_XJ" title="支付金额"  expfield="ZFJE" style="text-align:right;width:80px;" format="{0,number,#0.##}" total="true"/>
		    <display:column  title="出票部门" style="text-align:left;width:70px;">${vc.CP_BMBH}</display:column>
		    <display:column property="CP_YHBH" title="出票员" style="text-align:left;width:70px;"/>
			<display:column property="CP_DATETIME" title="出票时间" style="text-align:center;width:80px;"/>
			<display:column property="CP_OFFICEID" title="OFFICE号" style="text-align:center;width:70px;"/>
			<display:column property="WORKNO" title="工作号" style="text-align:center;width:70px;"/>
			<display:column property="PRINTNO" title="打票机" style="text-align:center;width:50px;"/>
			<display:column property="DDBH" title="订单编号" style="text-align:center;width:80px;"/>
			<display:column property="CG_DDBH" title="航空公司<br>订单号" style="text-align:left;width:100px;"/>
			<display:footer>
			<tr>
				<td colspan="24" style="text-align: right;">合    计：</td>
				<td style="text-align: right;">${cbsumList.XS_ZDJ}</td>
				<td style="text-align: right;">${cbsumList.XS_JSF}</td>
				<td style="text-align: right;">${cbsumList.XS_TAX}</td>
				<td style="text-align: right;">${cbsumList.XS_PJ}</td>
				<td style="text-align: right;">${cbsumList.XS_XJ}</td>
				<td style="text-align: right;"></td>
				<td></td>
				<td style="text-align: right;">${cbsumList.XS_YJF}</td>
				<td></td>
				<td></td>
				<td style="text-align: right;">${cbsumList.CG_ZDJ}</td>
				<td style="text-align: right;">${cbsumList.CG_PJ}</td>
				<td style="text-align: right;">${cbsumList.CG_JSF}</td>
				<td style="text-align: right;">${cbsumList.CG_TAX}</td>
				<td style="text-align: right;">${cbsumList.JPML}</td>
				<td style="text-align: right;">${cbsumList.XSML}</td>
				<td style="text-align: right;"></td>
				<td></td>
				<td style="text-align: right;">${cbsumList.XS_XJ}</td>
				<td colspan="8"></td>
				</tr>
			</display:footer>
	    </display:table>
	</c:if>
	<c:if test="${not empty param.tjfs and param.tjfs ne 1}">
		<display:table id="vc" name="page.list" class="list_table" decorator="org.displaytag.decorator.TotalTableDecorator">
			<c:if test="${param.tjfs eq 2}">
				 <display:column  title="网店平台"  style="text-align:center;width:60px;" expfield="WDPT">
				 	<c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
              	 		 ${vc.WDPT eq  onewdpt.id ? onewdpt.mc : ''}
              	 	</c:forEach>
				 </display:column>
			</c:if>
			<c:if test="${param.tjfs eq 3}">
				<display:column  title="网店平台"  style="text-align:center;width:80px;" expfield="WDPT" group="1">
					<c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
              	 		 ${vc.WDPT eq  onewdpt.id ? onewdpt.mc : ''}
              	 	</c:forEach>
				</display:column>
				 <display:column  title="网店"  style="text-align:center;width:80px;" expfield="WDID" group="2">
				 	${vc.EX.WDID.wdmc }
				 </display:column>
			</c:if>
			<c:if test="${param.tjfs eq 4}">
				 <display:column  title=" 采购来源"  style="text-align:center;width:60px;" property="CGLY"></display:column>
			</c:if>
			<c:if test="${param.tjfs eq 5}">
				 <display:column  title="航程"  style="text-align:center;width:60px;" property="HC"></display:column>
			</c:if>
			<c:if test="${param.tjfs eq 6}">
				 <display:column  title="航空公司"  style="text-align:center;width:60px;" expfield="HKGS">
				 	${vc.EX.HKGS.shortname }
				 </display:column>
			</c:if>
			<c:if test="${param.tjfs eq 7}">
				 <display:column  title="出票员"  style="text-align:left;width:60px;" expfield="CP_YHBH">
				 	${vc.CP_YHBH }  ${vc.EX.CP_YHBH.xm }
				 </display:column>
			</c:if>
			 <display:column  title="总票数" style="text-align:right;width:50px;" href="javascript:showDetailPs()" property="CPS" format="{0,number,##0}" total="true" 
			 paramId="vefun,WDPT,WDID,CGLY,HC,HKGS,CP_YHBH">
			 </display:column>
		    <display:column title="平均<br>代理费率" style="text-align:right;width:50px;" format="{0,number,##0.##}" property="PJDLFL" total="true"/>
		    <display:column property="PMXJ" style="text-align:right;width:90px;" title="票面小计"  format="{0,number,##0.##}"  total="true"/>
		    <display:column  style="text-align:right;width:60px;" title="采购<br>代理费" property="CG_DLF"  format="{0,number,##0.##}"  total="true"></display:column>
		    <display:column property="CG_ZDJ" title="采购<br>帐单价" style="text-align:right;width:90px;" format="{0,number,##0.##}"  total="true"/>
		    <display:column property="CG_JSF" title="采购<br>机建" style="text-align:right;width:50px;" format="{0,number,##0.##}"  total="true"/>
		    <display:column property="CG_TAX" title="采购<br>税费" style="text-align:right;width:50px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="CG_PJ" title="采购价" style="text-align:right;width:90px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="CGJE" title="采购<br>金额" style="text-align:right;width:90px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="XS_PJ" title="销售价" style="text-align:right;width:90px;" format="{0,number,##0.##}"  total="true"/>
		    <display:column property="XS_ZDJ" title="销售<br>账单价" style="text-align:right;width:90px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="XS_JSF" title="销售<br>机建" style="text-align:right;width:50px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="XS_TAX" title="销售<br>税费" style="text-align:right;width:50px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="XS_YJF" title="销售<br>邮寄费" style="text-align:right;width:40px;" format="{0,number,##0.##}"  total="true"/>
		    <display:column property="SFXJ" title="税费小计" style="text-align:right;width:60px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="XSXJ" title="销售小计" style="text-align:right;width:90px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="BXFS" title="保险份数" style="text-align:right;width:40px;" format="{0,number,##0}" total="true"/>
			<display:column property="BXLR" title="保险利润" style="text-align:right;width:60px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="JSJE" title="结算金额" style="text-align:right;width:100px;" format="{0,number,##0.##}"  total="true"/>
			<display:column property="JPML" title="机票毛利" style="text-align:right;width:70px;" format="{0,number,##0.##}"  total="true"/>
		    <display:column property="XSML" title="销售毛利" style="text-align:right;width:60px;" format="{0,number,##0.##}"  total="true"/>
		    <%-- <display:footer>
			<tr>
				<td style="text-align: right;">合    计：</td>
				<td>${cbsumList.CPS}</td>
				<td>${cbsumList.PJDLFL}</td>
				<td>${cbsumList.PMXJ}</td>
				<td>${cbsumList.CG_DLF}</td>
				<td>${cbsumList.CG_ZDJ}</td>
				<td>${cbsumList.CG_JSF}</td>
				<td>${cbsumList.CG_TAX}</td>
				<td>${cbsumList.CG_PJ}</td>
				<td>${cbsumList.CGJE}</td>
				<td>${cbsumList.XS_PJ}</td>
				<td>${cbsumList.XS_ZDJ}</td>
				<td>${cbsumList.XS_JSF}</td>
				<td>${cbsumList.XS_TAX}</td>
				<td>${cbsumList.XS_YJF}</td>
				<td>${cbsumList.SFXJ}</td>
				<td>${cbsumList.XSXJ}</td>
				<td>${cbsumList.BXFS}</td>
				<td>${cbsumList.BXLR}</td>
				<td>${cbsumList.JSJE}</td>
				<td>${cbsumList.JPML}</td>
				<td>${cbsumList.XSML}</td>
				</tr>
			</display:footer> --%>
		</display:table>
	</c:if>
	${surl}
</c:if>
<input type="hidden" id="vcexpfield" value="${vfn:urid(vcexpfield)}">
<c:if test="${empty list}">
	<span>没有找到相关数据</span>
</c:if>
<script type="text/javascript">
	highlightTableRows("vc");
</script>
