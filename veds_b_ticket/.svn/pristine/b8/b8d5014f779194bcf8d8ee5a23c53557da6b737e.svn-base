<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<div class="t_body">
  <c:if test="${not empty error}">
  	<font color="#FF0000">${error}</font>
  </c:if>
 <c:if test="${empty error}">
 <table width="100%" class="list_table CRZ">
      <tbody>
      		<c:set var="allSize" value="${fn:length(allPolicy)}"/>
      		<c:forEach items="${allPolicy}" var="onepi" varStatus="vindex">
      			<c:set var="ifCpsTB" value="${onepi.ptzcbs eq '10100011'}" />
      			<c:set var="ifCps" value="${onepi.ptzcbs eq '10100000'}" />
      			<c:set var="tbhide" value="${ifCpsTB and onepi.hide eq '1'}"/>
      			<c:set var="morehide" value="${vindex.count > 8}"/>
		        <tr style="display:${tbhide or morehide ? 'none' : ''}" class="${tbhide ? 'tbdghide' : ''} ${morehide ? 'morehide' : ''}">
		          <td width="75px">
		          	<%--淘宝代购 --%>
		          	<c:if test="${ifCpsTB}">
						<input class="ext_btn_submit2"  name="button" value=" 选 择 " type="button" onclick="orderTb('${onepi.ptzcbs}','${onepi.id}','${onepi.payfee}')"/>
					</c:if>
					<c:if test="${ifCps}">
						<input class="ext_btn_submit2"  name="button" value=" 选 择 " type="button" onclick="orderCps('${onepi.ptzcbs}','${onepi.id}','${onepi.payfee}','${onepi.pj_cgj}','${onepi.zclx}','${onepi.policytype}')"/>
					</c:if>
		          	<c:if test="${!ifCpsTB and !ifCps}">
		          		<input class="ext_btn_submit2"  name="button" value=" 选 择 " type="button" onclick="orderPlat('${onepi.ptzcbs}','${onepi.id}','${onepi.payfee}','${onepi.policytype}')"/>
		          	</c:if>
		          </td>
		          <td width="120px">
		          	OP[${empty onepi.shmc ? onepi.ptmc : onepi.shmc}]
		          </td>
		          <td  width="90px" style="text-align:left;">
		          	<c:if test="${not empty onepi.policytype}">
						<span title="票证类型：${onepi.policytype}"> ${onepi.policytype }${ifCpsTB ? onepi.cabin : ''}</span>
					</c:if>
					<c:if test="${onepi.changerecord eq '1'}">
							<img src="${ctx}/static/images/jzcp/huan.gif"  title="需换编码出票" style="cursor:pointer;" class="fr"/>
					</c:if>
					<c:if test="${not empty onepi.office}">
						<br><label style="cursor:pointer" title="OFFICE:${onepi.office }">[${fn:substring(onepi.office,0,6) }]</label>
					</c:if>
		          </td>
		          <td width="60px"><div class="font_orange font14">${vfn:format(onepi.pj_cgj,'#.##')}</div></td>
		          <td  width="80px">
		          	<c:choose>
			          	<c:when test="${onepi.ptzcbs ne '10100000' and onepi.ptzcbs ne '10100011'}">
			          		<span style="color:#038703;" title="平台原始返点">${vfn:format(onepi.rate,'#.####')}%</span>/<span title="平台实际返点">${vfn:format(onepi.showsjdlfl,'#.####')}%</span>
			          	</c:when>
			          	<c:otherwise>
			          		${vfn:format(onepi.rate,'#.####')}%
			          	</c:otherwise>
			         </c:choose>
		          </td>
		          <td width="60px">${vfn:format(onepi.superProfit,'#.##')}</td>
		          <td width="70px">
			          	<div class="font_orange font16" style="position:relative;">
			          		<b>${vfn:format(onepi.payfee,'#.##')}</b>
			          		<c:if test="${ifCpsTB and onepi.hide ne '1' and tbSize gt 1}">
								<i title="展开或影藏更多价格" class="down_arrow" onclick="showmore(this,'tbdghide')" show="0"></i>
				          	</c:if>
			          	</div>
			          	<c:if test="${onepi.ptzcbs ne '10100000' and onepi.isspecmark eq '1'}">
								<img src="${ctx}/static/images/jzcp/th.gif" align="absmiddle" title="异地平台特惠政策"  class="fr">
						</c:if>
		          		<c:if test="${onepi.ptzcbs eq '10100000'}">
							<c:if test="${onepi.zclx eq '5'}"><img style="VERTICAL-ALIGN: middle"  src="${ctx}/static/images/jzcp/TJ.gif"  class="fr"/></c:if>
							<c:if test="${onepi.zclx eq '4'}"><img style="VERTICAL-ALIGN: middle"  src="${ctx}/static/images/jzcp/TS.gif"  class="fr"/></c:if>
							<c:if test="${onepi.zclx eq '3'}"><img style="VERTICAL-ALIGN: middle"  src="${ctx}/static/images/jzcp/MP.gif"  class="fr"/></c:if>
							<c:if test="${onepi.zclx eq '2'}"><img style="VERTICAL-ALIGN: middle"  src="${ctx}/static/images/jzcp/KW.gif"  class="fr"/></c:if>
							<c:if test="${onepi.zclx eq '6'}"><img style="VERTICAL-ALIGN: middle"  src="${ctx}/static/images/jzcp/YX.gif"  class="fr"/></c:if>
						</c:if>
		          </td>
		          <td width="60px">
		          	 ${vfn:format(onepi.hfje,'#.##')}
		          </td>
		          <td width="90px">
		          		<div>
							${onepi.worktime } 
						</div>
						<div style="color:#038703;position:relative;">
							<c:if test="${not empty onepi.chooseOutWorkTime}">
								${onepi.chooseOutWorkTime}
							</c:if>
							<c:if test="${allSize gt 8 and vindex.count eq 8 }">
								<i title="展开或影藏更多价格" class="down_arrow" onclick="showmore(this,'morehide')" show="0"></i>
							</c:if>
						</div>
		          </td>
		          <td  width="284px" style="text-align:left">
		          	<a title="${onepi.asmsSaleNote }" style="text-decoration:none;">${onepi.asmsSaleNoteTitle }</a>
		          </td>
		        </tr>
		    </c:forEach>
      </tbody>
</table>
</c:if>
</div>
