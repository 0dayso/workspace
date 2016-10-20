<%@ page language="java" pageEncoding="UTF-8"%>
<multipage:pone page="${ctx}/vedsb/jpcwgl/jpxsmlfx/xsmlfx" actionFormName="page" var="surl"></multipage:pone>
${surl}
<display:table id="vc" name="page.list" class="list_table" style="width:2800px"  reportid="${reportid}"
       requestURI="${requestURI}?dtableid=vc" sort="external" decorator="org.displaytag.decorator.TotalTableDecorator"
		 excludedParams="dtableid">
       	<display:column title="序号" >
			${vc_rowNum}
		</display:column>
		<display:column title="对账状态">
			<c:if test="${vc.XSDZZT eq '1'}">
				已对账
			</c:if>
			<c:if test="${vc.XSDZZT ne '1'}">
				未对账
			</c:if>
		</display:column>
		<display:merge title="票面">
		    <display:column title=" 业务类型" sortable="true"  sortName="YWLX" >
		    	<c:if test="${vc.YWLX eq '1'}">
					 机票
				</c:if>
				<c:if test="${vc.YWLX eq '2'}">
					 补差单
				</c:if>
			</display:column>	
			<display:column title=" 采购来源" sortable="true" property="CGLY" sortName="CGLY" />
			<display:column title=" 票证状态" sortable="true" sortName="PZZT" expfield="PZZT">
				<c:if test="${vc.PZZT eq '1'}">
					 正常票
				</c:if>
				<c:if test="${vc.PZZT eq '2'}">
					 退票
				</c:if>
				<c:if test="${vc.PZZT eq '3'}">
					废票
				</c:if>
				<c:if test="${vc.PZZT eq '4'}">
					 改签
				</c:if>
			</display:column>
		    <display:column title="PNR" sortable="true"  sortName="PNR" >
		    	<c:if test="${vc.CG_PNR_NO eq vc.XS_PNR_NO}">
		    		${vc.CG_PNR_NO }
		    	</c:if>
		    	<c:if test="${vc.CG_PNR_NO ne vc.XS_PNR_NO}">
		    		${vc.CG_PNR_NO }/${vc.XS_PNR_NO}
		    	</c:if>
		    </display:column>
		    <display:column title="大编码" sortable="true"  sortName="HKGS_PNR" >
		    	<c:if test="${vc.CG_HKGS_PNR eq vc.XS_HKGS_PNR}">
		     		${vc.CG_HKGS_PNR}
		    	</c:if>
		    	<c:if test="${not empty vc.CG_HKGS_PNR and not empty vc.XS_HKGS_PNR}">
		    	<c:if test="${ vc.CG_HKGS_PNR ne vc.XS_HKGS_PNR}">
		    		${vc.CG_HKGS_PNR }/${vc.XS_HKGS_PNR}
		    	</c:if>
		    	</c:if>
		    </display:column>
			<%--<display:column title=" 承运人-票号" sortable="true" sortName="SZDH" expfield="HXTKNO" media="excel" style="text-align:center;"/> --%>
			<display:column title=" 票号" property="TKNO" sortable="true" sortName="SZDH" expfield="TKNO" style="text-align:center;"/>
			<display:column title=" 乘机人" sortable="true" property="CJR" sortName="CJR" expfield="CJR"/>
			<display:column title=" 类型" sortable="true" sortName="CJRLX" expfield="CJRLX"  style="text-align:center;">
				<c:if test="${vc.CJRLX eq '1'}">
					 成人
				</c:if>
				<c:if test="${vc.CJRLX eq '2'}">
					儿童
				</c:if>
				<c:if test="${vc.CJRLX eq '3'}">
					婴儿
				</c:if>
			</display:column>
			<display:column title=" 航程类型" sortable="true" sortName="HCLX" expfield="HCLX"  style="text-align:center;">
				<c:if test="${vc.HCLX eq '1'}">
					 单程
				</c:if>
				<c:if test="${vc.HCLX eq '2'}">
					往返程
				</c:if>
				<c:if test="${vc.HCLX eq '3'}">
					联程
				</c:if>
				<c:if test="${vc.HCLX eq '4'}">
					缺口程
				</c:if>
			</display:column>
			<display:column title=" 航程" sortable="true" sortName="HC" property="HC" style="text-align:center;"/>
			<display:column title="航程名称" sortable="true" sortName="HCMC"  >
				${vfc:getBcity(fn:substring(vc.HC,0,3)).mc}-${vfc:getBcity(fn:substring(vc.HC,3,6)).mc}
				<c:if test="${not empty fn:substring(vc.HC,6,9)}">
				-${vfc:getBcity(fn:substring(vc.HC,6,9)).mc}
				</c:if>
			</display:column>
			<display:column title=" 航班" sortable="true" sortName="XS_HBH" expfield="XS_HBH" property="XS_HBH" style="text-align:center;"></display:column>
			<display:column title=" 舱位" sortable="true" sortName="XS_CW" expfield="XS_CW" property="XS_CW" style="text-align:center;"></display:column>
			<display:column title=" 起飞时间" sortable="true" sortName="CFRQ" property="CFRQ" expfield="CFRQ"  style="text-align:center;" >
				${fn:substring(vc.CFRQ,5,16)}
			</display:column>
			<display:column title=" 账单价" sortable="true" sortName="CG_ZDJ" property="CG_ZDJ"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
			<display:column title=" <span title='非机票业务显示其它金额'>机建</span>" sortable="true" sortName="CG_JSF" property="CG_JSF"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
			<display:column title=" 税费" sortable="true" sortName="CG_TAX" property="CG_TAX"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
			<display:column title=" <span title='机建+税费'>税费小计</span>" sortable="true" sortName="SFXJ"  format="{0,number,##0.00}" style="text-align:right;">
				${vc.CG_TAX+vc.CG_JSF}
			</display:column>
			<display:column title="<span title='账单价+机建+税费'>票面小计</span>" sortable="true" sortName="PJXJ"   format="{0,number,##0.00}" style="text-align:right;">
				${vc.CG_TAX+vc.CG_JSF+vc.CG_ZDJ}
			</display:column>
		</display:merge>
		
		<display:merge title="采购">
			<display:column title="采购价" sortable="true" sortName="CG_PJ" property="CG_PJ"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
			<display:column title="采购金额" sortable="true" sortName="CGJE" property="CGJE"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
		</display:merge>
		
		<display:merge title="销售">
			<display:column title=" 销售价" sortable="true" sortName="XS_PJ" property="XS_PJ"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
			<%--<display:column title=" 销售结算价" sortable="true" sortName="XS_JE" property="XS_JE"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>--%>
			<%--<display:column title="<span title='结算价+机建+税费+销售服务费+保险结算价+接车+其它'> 结算金额</span>" sortable="true" sortName="XS_JE" property="XS_JE"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>--%>
			<display:column title=" 应收<br>金额" sortable="true" sortName="XS_JE" property="XS_JE"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
		</display:merge>
			
		<display:merge title="退废票">
			<display:column title=" 退废状态"  sortable="true" sortName="TPZT">
				<c:if test="${vc.TPZT eq '0'}">
					待提交
				</c:if>
				<c:if test="${vc.TPZT eq '1'}">
					 提交中
				</c:if>
				<c:if test="${vc.TPZT eq '2'}">
					已提交
				</c:if>
				<c:if test="${vc.TPZT eq '3'}">
					已办理
				</c:if>
				<c:if test="${vc.TPZT eq '4'}">
					已拒单
				</c:if>
				<c:if test="${vc.TPZT eq '5'}">
					部分办理
				</c:if>
				<c:if test="${vc.TPZT eq '9'}">
					已取消
				</c:if>
			</display:column>
			<display:column title=" 采购<br>办理时间" sortable="true" sortName="CG_BLSJ" expfield="CG_BLSJ"  style="text-align:center;">
				${fn:substring(vc.CG_BLSJ,5,16)}
			</display:column>
			<display:column title=" 采购<br>改签退款" sortable="true" sortName="CG_GQFY" property="CG_GQFY"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
			<display:column title=" 采购<br>应退金额" sortable="true" sortName="CG_TPF" property="CG_TPF"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
			<display:column title=" 采购<br>实退" sortable="true" sortName="CG_STJE" property="CG_STJE"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
			<display:column title=" 采购应退<br>实退差额" sortable="true" sortName="cgtkce"   format="{0,number,##0.00}" style="text-align:right;">
				${vc.CG_TPF-vc.CG_STJE}
			</display:column>
			<display:column title="<span title='（下级手续费工本费-上级手续费工本费）+（客户改签退款-采购改签退款）+ 加收服务费'> 退废毛利</span>" sortable="true" sortName="TPML" property="TPML"  format="{0,number,##0.00}" style="text-align:right;" total="true"/>
		</display:merge>
			<display:column title="<span title='销售毛利+退废毛利'>毛利小计</span>" sortable="true" sortName="MLXJ"   format="{0,number,##0.00}" style="text-align:right;">
				${vc.XSML+vc.TPML}
			</display:column >
		<display:merge title="出票">
			<display:column title=" 出票员" sortable="true" sortName="CP_YHBH" expfield="CP_YHBH">
				${vc.CP_YHBH}&nbsp;${vc.EX.CP_YHBH.xm}
			</display:column>
			<display:column title=" 出票时间" sortable="true" sortName="CP_DATETIME" expfield="CP_DATETIME" style="text-align:center;" >
		 		 ${fn:substring(vc.CP_DATETIME,5,16)}
			</display:column>	
		</display:merge>
		<display:footer>
		   <tr>
		      <td colspan="16">合计：</td>
		      <td align="right">${allsum.CG_ZDJ}</td>
		      <td align="right">${allsum.CG_JSF}</td>
		      <td align="right">${allsum.CG_TAX}</td>
		      <td align="right">${allsum.CG_TAX+allsum.CG_JSF}</td>
		      <td align="right">${allsum.CG_TAX+allsum.CG_JSF+allsum.CG_ZDJ}</td>
		      <td align="right">${allsum.CG_PJ}</td>
		      <td align="right">${allsum.CGJE}</td>
		      <td align="right">${allsum.XS_PJ}</td>
			  <td align="right">${allsum.XS_JE}</td>
		      <!--<td align="right">${allsum.XS_JE}</td>
		      <td align="right">${allsum.XS_JE}</td>  -->
		      <td align="right"></td>
		      <td align="right"></td>
		      <td align="right">${allsum.CG_GQFY}</td>
		      <td align="right">${allsum.CG_TPF}</td>
		      <td align="right">${allsum.CG_STJE}</td>
		      <td align="right">${allsum.CG_TPF-allsum.CG_STJE}</td>
		      <td align="right">${allsum.TPML}</td>
		      <td align="right">${allsum.XSML+allsum.TPML}</td>
		      <td align="right"></td>
		    </tr>
		</display:footer>
    </display:table>
   	${surl}
