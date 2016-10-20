<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/cg.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">采购信息</span>
		</td>
		<td style="background:#efefef;">
			<table class="table01" border="0" cellpadding="0" cellspacing="0" >
	           <tr>
	           	  <th>
	               		&nbsp;票证类型:
	               		<span>
	               			<select name="gq_cgly" class="select" datatype="*" nullmsg="请选择值" onchange="showWcpdw(this.options[this.selectedIndex].text)">
	               				<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
								    <c:if test="${oneLx.id ne '10014'}">
										<option value="${oneLx.ywmc}" ${oneLx.ywmc eq (empty jpgqd.gqCgly ? 'BOP':jpgqd.gqCgly ) ? 'selected' : ''} >${oneLx.ywmc}</option>
									</c:if>
								</c:forEach>
	               			</select>
				 		</span>
				 		
	               </th>
	           	   <th id="wcpdw" style="display: none">
	               		&nbsp;外出票单位：
	               		<span>
				 			<input type="text" name="gq_cgdw" value="" style="height: 20px;width:100px">
	               </th>
	               <th>
	               		&nbsp;采购科目:
	               		<span>
				 			<select name="gq_cgkm" class="select" datatype="*" nullmsg="请选择值">
					   		 	<option value="">==请选择==</option>
					   		 	<c:forEach items="${zfkmList}" var="zfkm"> 
					   		 		<option value="${zfkm.kmbh}" ${jpgqd.gqCgkm eq zfkm.kmbh ? 'selected' : ''}>${zfkm.kmmc}</option>
					   		 	</c:forEach>
					   		 </select>
				 		</span>
	               </th>
	           </tr>
	        </table>
		</td>
	</tr>
</table>

