<%@ page contentType="text/html;charset=UTF-8"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" id="hcInfo">
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/blxx.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">基本信息</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width:100%;height:60px;background:#f1f1f1;">
                <td class="wb_td03" style="border:none;">
               	 <span class="headtitle">网店平台</span>
                	 <c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
	       	 		<c:if test="${onewdpt.parid ne 'none' and onewdpt.id eq jpkhdd.WDPT}">
	       	 			<span class="wb_td02">${onewdpt.mc}</span>
	       	 		</c:if>
	       	 	 </c:forEach>
	       	 	 
			  	 <span class="headtitle">政策代码</span>
			 	 <span class="wb_td02">${jpkhdd.WD_ZCDM}</span>
			 	 
			  	 <span class="headtitle">&nbsp;&nbsp;外部单号</span>
			  	 <span class="wb_td02">${jpkhdd.WBDH }</span>
			  </td>
		  	</tr>
		  	<tr>
				  <td>
				  	 <span class="headtitle">PNR/大编码</span>
					<span class="wb_td02">${jpkhdd.XS_PNR_NO}/${jpkhdd.XS_HKGS_PNR}</span>
					
			 		<span class="headtitle">订单编号</span>	
				 	<span class="wb_td02">${jpkhdd.DDBH}</span>
				 	
				 	<span class="headtitle">PNR/订单状态</span>	
				 	<span class="wb_td02">${jpkhdd.XS_PNR_ZT}/
				 		<c:forEach items="${vfn:dictList('JPDDZT')}" var="oneZt">
				 			<c:if test="${oneZt.value eq jpkhdd.DDZT}">
				 				${oneZt.mc}
				 			</c:if>
			           	</c:forEach>
					</span>
				</td>
          	</tr>
  	   </table>
  	 </td>
  </tr>
</table>