<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/static/core/js/common.js?_=<%=(new Date()).getTime()%>"></script>

<script type="text/javascript">
	//关闭编辑窗口
	function closeEdit(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	} 
</script>
</head>
<body>
 <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
          	<form id="tfpEditForm" name="tfpEditForm">
          			<input type="hidden" name="tpfp" id="tpfp" value="${param.tpfp }">
          			<input type="hidden" name="id" id="tfpid" value="${tfp.id }">
          			<input type="hidden" name="zt" id="tfpzt" value="${empty tfp.zt ?0:tfp.zt }">
	           		<fieldset style="border:1px solid black; width: 690px; margin-top:10px;">
						<legend>${param.tpfp eq 1?'自动退':'自动废' }票规则<font color="red">基础信息设置</font>：</legend>
						<table class="ddxtab" align="center" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-bottom:10px;">
							<c:if test="${param.tpfp eq 1 }">
								<tr style="margin-top:10px;">
									<td class="title" align="right">&nbsp;是否自愿退票：</td>
									<td colspan="3">
										  <c:if test="${tfp.tplx eq 0 }">
										  		自愿退票
										  </c:if>
										  <c:if test="${tfp.tplx eq 1 }">
										  		非自愿退票
										  </c:if>
									</td>
								</tr>
							</c:if>
							<tr style="margin-top:10px;">
								<td class="title" align="right">&nbsp;规则名称：</td>
								<td colspan="3">
									<span>${tfp.gzmc}</span> 
								</td>
								<td class="title" align="right">&nbsp;航空公司：</td>
								<td colspan="3">
									<span>${tfp.hkgs}</span> 
								</td>
							</tr>
							<tr style="margin-top:10px;">
								<td class="title" align="right">&nbsp;舱位：</td>
							  	<td colspan="3">
							  		<span>${tfp.cw}</span>
							  	</td>
							  	<td class="title" align="right">票证类型:</td>
								<td colspan="3">
									<c:forEach items="${vfc:getVeclassLb('10014')}" var="onepzlx" varStatus="pzlxStatus">
			                  	 		 <c:if test = "${fn:contains(tfp.pzlx,onepzlx.id)}">
			                  	 			<span>${onepzlx.mc }&nbsp;</span>
			                  	 		 </c:if>
			                  	 	</c:forEach>	
								</td>						 
							</tr>
							<tr style="margin-top:10px;">
								<td class="title" align="right">适用乘机人类型：</td>
								<td colspan="3">
									<c:if test="${fn:contains(tfp.cjrlx,'1')}">
											<span>成人&nbsp;</span>
									</c:if>
									<c:if test="${fn:contains(tfp.cjrlx,'2')}">
											<span>儿童&nbsp;</span>
									</c:if>
									<c:if test="${fn:contains(tfp.cjrlx,'3')}">
											<span>婴儿&nbsp;</span>
									</c:if>								
								</td>
								<td class="title" align="right">乘机日起止：</td>
								<td>
									<span>${tfp.cfrqs}-${tfp.cfrqz}</span>
								</td>
							</tr>
						   	<tr style="margin-top:10px;">
							  <td class="title" align="right" title="可以通过<font color='red'>多个/分隔，全部可输入---.可以通过多选框中出发城市选择</font>辅助添加">&nbsp;出发城市：</td>
							  <td colspan="3">
							  	  <span>${tfp.cfcity}&nbsp;</span>
							  </td>	 
							  <td class="title" align="right" title="可以通过<font color='red'>多个/分隔，全部可输入---.可以通过多选框中到达城市选择</font>辅助添加">&nbsp;到达城市：</td>
							  <td colspan="3">
							      <span>${tfp.ddcity }&nbsp;</span>
							  </td>	
							</tr>
						</table>
						<table style="margin-top:10px;margin-bottom:10px;">
							<tr style="margin-top:10px;margin-bottom:10px;">
								<td style="width:120px;margin-left:5px;">
									<span style="margin-left:60px;">提前天数:</span>
								</td>
								<td style="width:100px;">
									<span>${tfp.tqtss}-${tfp.tqtsz}天</span>
								</td>
								<td style="width:70px;">
									有效时间起:
								</td>
								<td style="width:110px;">
									<span>${tfp.yssjq}-${tfp.yssjz}</span>
								</td>
								<td style="width:110px;">起飞时间提交限制:</td>
								<td style="width:60px;">
									<span>${tfp.qfsjxz }小时&nbsp;</span>
								</td>
							</tr>
						</table>
	              	</fieldset>
	              	<!-- 自愿转非自愿设置 -->
			    <fieldset style="border:1px solid black; width: 690px; margin-top:10px;">
					<legend>自动退票规则<font color="red">自愿转非自愿设置</font>：</legend>
					<table class="ddxtab"  width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-bottom:10px;">
						  <tr>
						  	  <td colspan="2">
						  	 	&nbsp; 是否赌航变：&nbsp;
						  	    <c:if test="${empty tfp.ishb or tfp.ishb eq '0'}">
						  	    	赌航变
						  	    </c:if>
						  	    <c:if test="${tfp.ishb eq '1'}">
						  	    	不赌航变
						  	    </c:if>
						  	  </td>
				  	  	</tr>
				  	  	<c:if test="${tfp.ishb ne '1'}">
						  	<tr>
						  		<td colspan="3">
							  		&nbsp;起飞前${empty tfp.hbTqsj ? 0 : tfp.hbTqsj}小时提交退票<font color='gray'>（在设定时间内有航变提交非自愿退票，否则提交自愿退票）</font>
							  	</td>
					  	    </tr>
						  	<tr>
						  		<td colspan="3">
						  	  		&nbsp;设置最大风险值为${empty tfp.hbZxtpf ? 0 : tfp.hbZxtpf}<font color='gray'>（退票费大于等于设置值时，赌航变） </font>
								</td>
					  	   	</tr>
						   	<tr>
						  	  <td colspan="3">
						  	     	&nbsp;设置利润率最小为${empty tfp.hbZxlrl ? 0 : tfp.hbZxlrl}%
						  	     	<font color='gray'>（利润率高于或等于设定值时，赌航变） </font></td>
					  	   	</tr>
						  	<tr>
								<td colspan="3">
							     	&nbsp;设置利润值最小为${empty tfp.hbZxlr ? 0 : tfp.hbZxlr}<font color='gray'>（利润值高于或等于设定值时，赌航变）</font></td>	
							</tr>
						</c:if>
					</table>
             	</fieldset>
             	
             	
             	<!-- BSP退票设置 -->
			    <fieldset style="border:1px solid black; width: 690px; margin-top:10px;">
					<legend>自动退票规则<font color="red">BSP退票取消座位设置</font>：</legend>
					<input type="hidden" name="jpBspTpszId" id="id" value="${bspsz.id}">
					<table class="ddxtab"  width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-bottom:10px;">
						  <tr>
						  	  <td colspan="4">
						  	   	&nbsp;是否验证乘机人证件号码
						  	    <c:if test="${empty bspsz.yzzjhm or bspsz.yzzjhm eq '0'}">
						  	    	验证
						  	    </c:if>
						  	    <c:if test="${bspsz.yzzjhm eq '1'}">
						  	    	不验证
						  	    </c:if>
						  	  </td>
				  	  	</tr>
				  	  	<tr>
				  	  		<td colspan="3">
				  	  			&nbsp;自动取消座位失败是否提醒
						  	    <c:if test="${empty bspsz.sfsbtx or bspsz.sfsbtx eq '0'}">
						  	    	提醒
						  	    </c:if>
						  	    <c:if test="${bspsz.sfsbtx eq '1'}">
						  	    	不提醒
						  	    </c:if>
						  	  </td>
						 </tr>
						<c:if test="${bspsz.sfsbtx  eq '0'}">
					  	  	<tr><td colspan="3" width="600px">&nbsp;
					  	  	<table id="issbtxshow" class="ddxtab" align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
							  	<tr>
			                    	<td>&nbsp;</td>
							  		<td colspan="3">
								  		短信提醒${bspsz.dxtx}
								  	</td>
						  	    </tr>
							  	<tr>
							  		<td>&nbsp;</td>
							  		<td colspan="3">
							  	  		邮箱提醒${bspsz.yjtx}
									</td>
						  	   	</tr>
							</table>
							</td>
							</tr>
						</c:if>
					</table>
             	</fieldset>
           		<table style="margin-top:20px;">
           			<tr style="margin-top:20px;">
           				<td>
							<input type="button"  value="关 闭 " class="ext_btn ext_btn_success" onClick="closeEdit()" style="margin-left:250px;">
						</td> 
           			</tr>
           		</table>
            </form>
          </div>
        </div>
   </div>
  </div>
</body>

</html>