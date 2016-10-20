<%@ page language="java" pageEncoding="UTF-8"%>
<multipage:pone page="${ctx}/vedsb/jpcwgl/jpxsmlfx/xsmlfx" actionFormName="page" var="surl"></multipage:pone>
	${surl}
	<display:table id="vc" name="page.list" class="list_table" style="width:2800px">
       	   <display:merge title="统计方式">
			    <c:if test="${param.tjtype eq '1'}">
				     	<display:column title=" 网店平台"  expfield="WDPT" sortable="true" sortName="wdpt"  total="true"  style="text-align:center;">
				     	 ${vfc:getVeclass(vc.WDPT).mc}
				     	</display:column>
				     	<display:column title=" 网店"  expfield="WDID" sortable="true" sortName="WDID"  total="true"  style="text-align:center;">
				     		${vc.EX.WDID.wdmc }
				     	</display:column>
			     </c:if>
			     <c:if test="${param.tjtype eq '2'}">
				     	<display:column title=" 投放方案"  sortable="true" sortName="faid" property="FAID" total="true"  style="text-align:right;">
				     	</display:column>
				 </c:if>
				<c:if test="${param.tjtype eq '3'}">
						 <display:column title="序号" >
							${vc_rowNum}
						</display:column>
				        <display:column title=" 航程"  expfield="HC" style="text-align:center;">
				     	${vfc:getBcity(fn:substring(vc.HC,0,3)).mc}-${vfc:getBcity(fn:substring(vc.HC,3,6)).mc}
							<c:if test="${not empty fn:substring(vc.HC,6,9)}">
							-${vfc:getBcity(fn:substring(vc.HC,6,9)).mc}
							</c:if>
						</display:column>
				</c:if>
		   </display:merge>
		
		
		<display:merge title="正常票" >
			<display:column title=" 张数" style="text-align:right;"  total="true" format="{0,number,##0}" expfield="CPS">
			   	 <a href="###" onclick="detail('1','${vc.HC}','${vc.WDPT}','0','1','${param.kssj}','${param.jssj}','${param.rqtj}',
			   	 '${vc.FAID}','${param.pnrno}','${param.tkno}','${param.cjrlx}','${param.cjr}','${param.hkgs}','${param.shclx}','${param.hb}','${param.tfbz}'),'';">${vc.CPS}</a>
			</display:column>	 
			<display:column title=" 账单价" sortable="true" sortName="CG_ZDJ_ZCP" property="CG_ZDJ_ZCP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 机建" sortable="true" sortName="CG_JSF_ZCP" property="CG_JSF_ZCP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 税费" sortable="true" sortName="CG_TAX_ZCP" property="CG_TAX_ZCP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="<span title='账单价+机建+税费'> 票面<br>小计</span>" sortable="true" sortName="PJXJ_ZCP"  total="true" format="{0,number,##0.00}" style="text-align:right;">
				${vc.CG_ZDJ_ZCP+vc.CG_JSF_ZCP+vc.CG_TAX_ZCP}
			</display:column>
			<display:column title=" 无奖励<br>票价" sortable="true" sortName="CG_PJ_ZCP" property="CG_PJ_ZCP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
				<%--<display:column title=" 返现" sortable="true" sortName="KHFX" property="KHFX" total="true" format="{0,number,##0.00}" style="text-align:right;"/>--%>
			<display:column title="<span title='采购价+机建+税费+采购服务费'> 采购<br>金额</span>" sortable="true" sortName="CGJE"  total="true" format="{0,number,##0.00}" style="text-align:right;">
				${vc.CG_PJ_ZCP+vc.CG_JSF_ZCP+vc.CG_TAX_ZCP+vc.CG_FWF_ZCP}
			</display:column>
			<%--<display:column title=" 代理费" sortable="true" sortName="DLF" property="DLF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 奖励<br>金额" sortable="true" sortName="JLJE" property="JLJE" total="true" format="{0,number,##0.00}" style="text-align:right;"/>--%>
			<%--<display:column title=" 销售<br>结算价" sortable="true" sortName="XS_JE_ZCP" property="XS_JE_ZCP" total="true" format="{0,number,##0.00}" style="text-align:right;"/> --%>
			<display:column title="<span title='结算价+机建+税费+销售服务费+保险结算价+接车+其它'> 结算金额</span>" sortable="true" sortName="XS_JE_ZCP" property="XS_JE_ZCP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="<span title='（结算价-采购价-客户留款）+（保险结算价-保险成本）+（销售服务费-采购服务费）'> 销售毛利</span>" sortable="true" sortName="XSML_ZCP" property="XSML_ZCP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
	  </display:merge>
		
		<display:merge title="退票">
			<display:column title=" 张数" style="text-align:right;"  total="true" format="{0,number,##0}" expfield="TPS">
			   	 <a href="###" onclick="detail('1','${vc.HC}','${vc.WDPT}','0','2','${param.kssj}','${param.jssj}','${param.rqtj}',
			   	 '${vc.FAID}','${param.pnrno}','${param.tkno}','${param.cjrlx}','${param.cjr}','${param.hkgs}','${param.shclx}','${param.hb}','${param.tfbz}','');">${vc.TPS}</a>
			</display:column>
			<display:column title="<span title='（退票结算价-退票采购价-退票客户留款）-退票保险奖励-（退票销售服务费-退票采购服务费）'> 销售毛利</span>" sortable="true" sortName="XSML_TP" property="XSML_TP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购<br>手续费" sortable="true" sortName="CG_TPF_TP" property="CG_TPF_TP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 销售<br>手续费" sortable="true" sortName="XS_TPSXF_TP" property="XS_TPSXF_TP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<%--<display:column title=" 退票<br>服务费" sortable="true" sortName="TP_FWF" property="TP_FWF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 加收<br>服务费" sortable="true" sortName="JSFWF" property="JSFWF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
	     	<display:column title=" 上级<br>改签退款" sortable="true" sortName="CJ_GQFY" property="CJ_GQFY" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
	     	<display:column title=" 下级<br>改签退款" sortable="true" sortName="TP_GQFY" property="TP_GQFY" total="true" format="{0,number,##0.00}" style="text-align:right;"/>--%>	     
			<display:column title=" 采购<br>应退金额" property="CG_PJ_TP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="<span title='(下级手续费工本费-上级手续费工本费)+(下级改签退款-上级改签退款)+加收服务费'>退票毛利</span>" sortable="true" sortName="TPML_TP" property="TPML_TP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
		</display:merge>
		
		<display:merge title="废票">
			<display:column title=" 张数" style="text-align:right;"  total="true" format="{0,number,##0}" expfield="FPS">
			   	 <a href="###" onclick="detail('1','${vc.HC}','${vc.WDPT}','0','3','${param.kssj}','${param.jssj}','${param.rqtj}',
			   	 '${vc.FAID}','${param.pnrno}','${param.tkno}','${param.cjrlx}','${param.cjr}','${param.hkgs}','${param.shclx}','${param.hb}','${param.tfbz}','');">${vc.FPS}</a>
			</display:column>
			<display:column title=" 采购<br>工本费" sortable="true" sortName="CG_TPF_FP" property="CG_TPF_FP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 销售<br>工本费" sortable="true" sortName="XS_TPSXF_FP" property="XS_TPSXF_FP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<%--<display:column title=" 废票<br>服务费" sortable="true" sortName="FP_FWF" property="FP_FWF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 加收<br>服务费" sortable="true" sortName="F_JSFWF" property="F_JSFWF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 采购<br>应退金额" sortName="CG_TPF_FP" property="CG_TPF_FP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>--%>
			<display:column title="<span title='(销售手续费工本费-采购手续费工本费)+(销售改签退款-采购改签退款)+加收服务费'> 废票毛利</span>" sortable="true" sortName="FPML_FP" property="FPML_FP" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
	    </display:merge>
	    
	    <display:merge title="改签">
			<display:column title=" 张数" style="text-align:right;"  total="true" format="{0,number,##0}" expfield="GQPS">
			   	 <a href="###" onclick="detail('1','${vc.HC}','${vc.WDPT}','0','4','${param.kssj}','${param.jssj}','${param.rqtj}',
			   	 '${vc.FAID}','${param.pnrno}','${param.tkno}','${param.cjrlx}','${param.cjr}','${param.hkgs}','${param.shclx}','${param.hb}','${param.tfbz}','');">${vc.GQPS}</a>
			</display:column>
			<display:column title="采购<br>改签费" sortable="true" sortName="CG_PJ_GQ" property="CG_PJ_GQ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="改签费" sortable="true" sortName="XS_JE_GQ" property="XS_JE_GQ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title=" 改签<br>服务费" sortable="true" sortName="GQ_FWF" property="GQ_FWF" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
			<display:column title="<span title='(改签费-采购改签费)'> 改签毛利</span>" sortable="true" sortName="XSML_GQ" property="XSML_GQ" total="true" format="{0,number,##0.00}" style="text-align:right;"/>
	    </display:merge>
		<display:column style="text-align:left;" title="毛利小计" format="{0,number,##0.00}" property="MLXJ"/>
		</display:table>
	${surl}
