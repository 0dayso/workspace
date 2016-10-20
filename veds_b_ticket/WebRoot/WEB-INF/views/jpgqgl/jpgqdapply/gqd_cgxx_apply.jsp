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
	               		&nbsp;票证类型：
	               		<span>
	               			<select name="gqCgly" class="select" onchange="showWcpdw(this.value)">
	               				<option value="BPET">BPET</option>
	               				<option value="BSPET">BSPET</option>
	               				<option value="OP">OP</option>
	               				<option value="GP">GP</option>
	               			</select>
				 		</span>
	               </th>
	           	   <th id="wcpdw" style="display: none">
	               		&nbsp;外出票单位：
	               		<span>
				 			<input type="text" name="gqCgdw" value="" style="height: 20px;width:100px">
				 		</span>
	               </th>
	               <th>
	               		&nbsp;采购科目：
	               		<span>
				 			<select name="gqCgkm" class="select">
					   		 	<option>==请选择==</option>
					   		 	<option value="支付宝" ${jpgqd.gqCgkm eq '支付宝' ? 'selected' : ''}>支付宝</option>
					   		 	<option value="财付通" ${jpgqd.gqCgkm eq '财付通' ? 'selected' : ''}>财付通</option>
					   		 </select>
				 		</span>
	               </th>
	           </tr>
	        </table>
		</td>
	</tr>
</table>
