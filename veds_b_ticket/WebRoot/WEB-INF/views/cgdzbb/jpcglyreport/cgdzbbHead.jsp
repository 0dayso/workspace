<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
		<tr> 
			<td class="xsys" style="text-align: right;">出票日起</td>
			<td>
			    <input type="text" name="ksrq" class="inputDate" value="${empty param.ksrq?vfn:dateShort():param.ksrq }"
			     onclick="WdatePicker()" size="10">
			</td>    
			<td class="xsys" style="text-align: right;">出票日止</td>
			<td>
			    <input type="text" name="jsrq" class="inputDate"  value="${empty param.jsrq?vfn:dateShort():param.jsrq }"
			     	onclick="WdatePicker()" size="10">
			</td>

		    <td class="xsys" style="text-align: right;">PNR</td>
		    <td>
		      <input type="text" name="pnrno" class="input1" size="10" value="${param.pnrno }" onblur="value=$.trim(this.value).toUpperCase();" maxlength="6">
		    </td>  
		    <td class="xsys" style="text-align: right;">采购来源</td>
		    <td>
			    <select name="cplx" style="width: 92px;">
				  <option value="">==请选择==</option>
				  <c:forEach items="${vfc:getVeclassLb('10014')}" var="cplx">
				  		<c:if test="${cplx.id ne 10014}">
	          			<option value="${cplx.ywmc}" ${param.cplx eq cplx.ywmc ? 'selected' : ''}>${cplx.mc}</option>
	          	 		</c:if>
	          	  </c:forEach>
			    </select>
			</td>
		    <td class="xsys" style="text-align: right;">支付科目</td>
		 	<td class="tab_in_td_f">
		 	  <!-- 查询所有支付科目并显示当前支付科目 -->
				  <select name="cg_zfkm" style="width:92px;" >
				  	 <option value="">==所有==</option>
				  	 <c:forEach items="${zfkm}" var="z">
				  	 	<option value="${z.kmbh}" ${param.cg_zfkm eq z.kmbh ? 'selected' : ''}>${z.kmmc}</option>
				  	 </c:forEach>
				  </select>
		    </td>
		</tr>
		<tr>
		    <td class="xsys" style="text-align: right;">国内国际</td> 
		    <td>
		      <select name="hcglgj" class="input1" style="width:92px" >
		        <option value="" ${empty param.hcglgj ?'selected':'' }>==请选择==</option>
			    <option value="1" ${param.hcglgj eq '1'?'selected':'' }>国内</option>
				<option value="0" ${param.hcglgj eq '0'?'selected':'' }>国际</option>
			  </select>
			</td>
		    <td class="xsys" style="text-align: right;">大编码</td>
		  	<td>
		  		<input type="text" class="input1 UpperCase" size=10 value="${param.hkgs_pnr}" name="hkgs_pnr" onblur="value=$.trim(this.value).toUpperCase();" />
		  	</td>
			 <td class="xsys" style="text-align: right;">航程</td>
		     <td>
		     <input type="text" name="hc" class="input1" size="10" value="${param.hc }" onkeyup="this.value=this.value.replace('%','').toUpperCase()">
		     </td>
		     <td class="xsys" style="text-align: right;">航班号</td>
		 	<td>
		 	  <input type="text" name="hbh" class="input1" size="10" value="${param.hbh }" onkeyup="this.value=this.value.replace('%','').toUpperCase()">
		    </td>
		     <td class="xsys" style="text-align: right;">乘机人</td>
		    <td>
		      <input type="text" name="cjr" class="input1" size="10" value="${param.cjr }"  onkeyup="this.value=this.value.replace('%','').toUpperCase()">
		    </td> 
		</tr>
		<tr >
		  	<td class="xsys" style="text-align: right;">付款否</td>
		    <td>
		    	<select name="zf_fkf" style="width:92px">
		    		<option value=""  ${empty param.zf_fkf ? 'selected' : ''}>==全部==</option>
		    		<option value="1" ${param.zf_fkf eq '1' ? 'selected' : ''}>已付</option>
		    		<option value="0" ${param.zf_fkf eq '0' ? 'selected' : ''}>未付</option>
		    	</select>
		    </td>
		    <td class="xsys" style="text-align: right;">票号</td>
		  	<td>
		  		<input type="text" class="input1" size=10 value="${param.tkno}" name="tkno" onblur="value=$.trim(this.value).toUpperCase();" datatype="*14-14"/>
		  	</td>
		  	<c:if test="${param.cplx eq 'BPET' or param.cplx eq 'BP'}">
		  	 <td class="xsys" style="text-align: right;">航空公司</td>
				 <td>
				 	<input type="text" id="hkgsmc" name="hkgsmc" class="input1" size=10 onchange="clearValue(this,'gxhs');" onkeyup="this.value=this.value.toUpperCase()" value="${param.hkgsmc}"/>
					<input type="hidden" name="hkgs" id="hkgs" value="${param.hkgs}"/>
				 </td>
			 </c:if>
			 <c:if test="${param.cplx eq 'BSPET' or param.cplx eq 'BSP'}">
				<td class="xsys" style="text-align: right;">OFFICE号</td>
				<td>
				   <input type="text" name="office" class="input1" size="10" value="${param.office }" onkeyup="this.value=this.value.replace('%','').toUpperCase()">
				</td>
				<td class="xsys" style="text-align: right;">打票机号</td>
				<td>
				  <input type="text" name="printno" class="input1" size="10" value="${param.printno }" >
				</td>
			</c:if>
			<c:if test="${param.cplx eq 'BSPET'}">
				<td class="xsys" style="text-align: right;">工作号</td>
				<td>
				  <input type="text" name="cp_pid" class="input1" size="10" value="${param.cp_pid }">
				</td>
			</c:if>
			<c:if test="${param.cplx eq 'OP' or param.cplx eq 'ODT'}">
				<td class="xsys" style="text-align: right;">外出票单位</td>
				<td>
					<input type="text" id="hzdw" name="hzdw" value="${param.hzdw}" size="10"/>
				</td>
			</c:if>
			<td>
			    <input type="button" class="ext_btn ext_btn_submit" value="查询" onclick="reportmxsearch()">
			</td>
			<td>
				<input type="button" value="导出" class="ext_btn ext_btn_success" onClick="toExcelmx()"}>
			</td>
	</tr>
</table>
