<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<display:table id="xcbb" name="list" class="list_table" style="width:1280px;" varTotals="ts">
		      			<display:column title="序号" style="text-align:center;">${xcbb_rowNum}</display:column>
		      			<display:column title="领用部门" style="text-align:center;" property="BMBH" group="1" expfield="BMBH"></display:column>
						<display:column title=" 时间"  style="text-align:center;" expfield="CZRQ">${fn:substring(xcbb.CZRQ,0,16)}</display:column>				       <display:column title="领用号段" style="text-align:center;" property="LYHDB" expfield="LYHD" />
				       <display:column title="领用数量"  property="SL"   total="true"   style="text-align:right;"  expfield="SL" href="javascript:detail('')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
				       <c:set var="sl" value="${sl+xcbb.SL}"></c:set>
				       <display:column title="未使用" property="WSY"  total="true" style="text-align:right;" expfield="WSY" href="javascript:detail('0')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
		      		   <display:column title="<span title='出票+未创建已作废+已创建已作废+退废票已作废'>已使用</span>" property="YSY" expfield="YSY" total="true"  style="text-align:right;" href="javascript:detail('10')" paramId="vefun,STARTNO,ENDNO,BMBH" />
		      		   <display:column title="出票" property="CP" style="text-align:right;"  total="true" href="javascript:detail('2')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
		      		   <display:merge title="未创建<br>已作废">
				        <display:column title=" 未回收" property="WCJWHS"  style="text-align:right;"  total="true" href="javascript:detail('3')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
				        <display:column title=" 已回收" property="WCJYHS"  style="text-align:right;"  total="true" href="javascript:detail('4')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
				       </display:merge>
				       <display:merge title="已创建<br>已作废">
				        <display:column title=" 未回收" property="YCJWHS"  style="text-align:right;"  total="true" href="javascript:detail('5')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
				        <display:column title=" 已回收" property="YCJYHS"  style="text-align:right;"  total="true" href="javascript:detail('6')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
				       </display:merge>
				       <display:merge title="退废票<br>已作废">
				        <display:column title=" 未回收" property="TFPWHS"  style="text-align:right;"  total="true" href="javascript:detail('7')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
				        <display:column title=" 已回收" property="TFPYHS"  style="text-align:right;"  total="true"  href="javascript:detail('8')" paramId="vefun,STARTNO,ENDNO,BMBH" ></display:column>
				       </display:merge>
		      			<display:footer>
					  	   <tr>
					  	      <td colspan="4" style="text-align:right;" >合计：</td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column5}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column6}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column7}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column8}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column9}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column10}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column11}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column12}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column13}  </fmt:formatNumber></td>
					  	      <td style="text-align:right;"><fmt:formatNumber pattern="#0">${ts.column14}  </fmt:formatNumber></td>
					  	   </tr>
		  				 </display:footer>
		      		</display:table>