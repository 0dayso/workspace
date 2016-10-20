<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<style>
	.ddgl{
		text-align:left; 
	}
	.ddglName{
		text-align:right; 
	}
	a{ 
		text-decoration: none;
	}
</style>
<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
<script type="text/javascript" src="${ctx}/static/js/khdd/khdd.js?timestamp=<%=(new Date()).getTime()%>"></script>
<script type="text/javascript" src="${ctx}/static/js/khdd/operate_khdd.js?timestamp=<%=(new Date()).getTime()%>"></script>
<script type="text/javascript">
	HCLX = '${vfn:toJSON(vfn:dictList('HCLX'))}';
	JPDDZT = '${vfn:toJSON(vfn:dictList('JPDDZT'))}';
	$(function(){
		//联系电话不为空则将开始日期向前调一周
		if($("#nxdh").val()!=""){
			var n=new Date();
			n.setDate(n.getDate()-7);
			$("input[name='sjrs']").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
		}
		if('${param.wdpt}' == '10100050' || $("#wdpt").val() == '10100050' || $("#wdpt").val() == ''){
			$("#jjd_sm").show();
		}else{
			$("#jjd_sm").hide();
		}
		//$("#searchFormButton").click();
	});
	
</script>
		
<script id="tpl_list_table" type="text/html">

<div>
	<table width="2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
		<th width="20">序号</th>
		<th width="30"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
		<th width="150">操作</th>
		<th width="80">网店</th>
		<th width="50">方案</th>
		<th width="90">外部单号</th>
		<th width="100">政策代码</th>
		<th width="50">产品<br>类型</th>
		<th width="50">订单<br>类型</th>
		{{#
             var jjdsm="";
             var jjd=false;
			 if('${param.wdpt}' == '10100050' || $("#wdpt").val() == '10100050' || $("#wdpt").val() == ''){
                jjd=true;
				jjdsm="<th width='50' class='jjcd_show'>紧急度</th>";
			 }
		}}{{jjdsm}}
		<th width="80"><a href="###" onclick="sort('nosj');">NO位时间</a></th>
		<th width="50">政策<br>渠道</th>
		<th width="50">采购<br>来源</th>
		<th width="90">采购订单号</th>
		<th width="40">平台采<br>购状态</th>
		<th width="40">订单<br>状态</th>
		<th width="80">自动出票状态</th>
        <th width="50">回填状态</th>
		<th width="90">PNR</th>
		<th width="30">PNR<br>状态</th>
		<th width="50">大编码</th>
		<th width="50">航程<br>类型</th>
		<th width="50">航程</th>
		<th width="50">航班号</th>
		<th width="20">舱位</th>
		<th width="80"><a href="###" onclick="sort('cfrq');">起飞时间</a></th>
		<th width="60">乘机人</th>
		<th width="20">人数</th>
		<th width="50">账单价</th>
		<th width="50">销售价</th>
		<th width="30">基建</th>
		<th width="30">税费</th>
		<th width="50">航意险<br>份数</th>
		<th width="50">延误险<br>份数</th>
		<th width="50">邮寄费</th>
		<th width="60">收款金额</th>
		<th width="60">收款科目</th>
		<th width="80"><a href="###" onclick="sort('ddsj');">预订时间</a></th>
		<th width="50">联系人</th>
		<th width="90">联系电话</th>
	</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
				<td class="td_center">{{ i+1 }}</td>
				<td width="25" align="center">	
						<input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" sortnum="{{i+1}}"  
						phhtzt="{{$.nullToEmpty(d.list[i].PHHTZT)}}" 
						wbdh="{{$.nullToEmpty(d.list[i].WBDH)}}"
						wdid="{{$.nullToEmpty(d.list[i].WDID)}}"
						value="{{$.nullToEmpty(d.list[i].DDBH)}}">
				</td>
				
                <td>
                	{{#
						var zt = $.nullToEmpty(d.list[i].DDZT);
						var ddbh = $.nullToEmpty(d.list[i].DDBH);
						var sfcp = $.nullToEmpty(d.list[i].SFCP);
						var pnr_zt = $.nullToEmpty(d.list[i].XS_PNR_ZT);
						var cg_pnr_zt = $.nullToEmpty(d.list[i].CG_PNR_ZT);
						var qzcount = $.nullToEmpty(d.list[i].QZCOUNT);
						var zw = $.nullToEmpty(d.list[i].ZW);
						var opreate = "&nbsp;";
					
						if (zt == '1') {
							opreate += "<a href='###' onclick=cancel('"+ddbh+"')>消</a>&nbsp;";
						} else {
							opreate += "<font style='text-decoration: line-through' title='取消订单'>消</font>&nbsp;";
						}
						
						<c:if test="${empty param.tlx}">				
							if ((zt == '1' || zt == '2') && sfcp == '0') {
								opreate += "<a href='###' onclick=enterCpPage('"+ddbh+"','正常单管理出票-锁单')>出</a>&nbsp;";
                                if(cg_pnr_zt != 'RR' || cg_pnr_zt != 'XX'){
                                	opreate += "<a href='###' onclick=sgBw('"+ddbh+"','"+cg_pnr_zt+"') title='PNR编码NO时进行手工补位'>补</a>&nbsp;";
                                }
							} else {
								opreate += "<font style='text-decoration: line-through' title='订单出票'>出</font>&nbsp;";
							}
							if(zw == 0 && zt != '4' && zt != '5'){ 
								opreate += "<a href='###' onclick=toSgZw('"+ddbh+"',this)>追</a>&nbsp;";
							}else if(zt == '4' && zt == '5'){
								opreate += "<font style='text-decoration: line-through' title='订单已取消'>追</font>&nbsp;";
							}else if(zw != 0){ 
								opreate += "<font style='text-decoration: line-through' title='已申请追位'>追</font>&nbsp;";
							} 
							
							if ((zt == '1' || zt == '2') && pnr_zt != 'XX' && sfcp != 1) {
								opreate += "<a href='###' onclick=enterZjpPage('"+ddbh+"')>完</a>&nbsp;";
							} else {
								opreate += "<font style='text-decoration: line-through' title='转机票'>完</font>&nbsp;";
							}
						</c:if>

						opreate = opreate + "<a href='###' onclick=getQzxx('"+ddbh+"') title='点击添加签注信息'>";
						if(qzcount != 0 ){
							opreate += "<img src='${ctx}/static/images/zicon.gif'>";
						}else{
							opreate += "注";
						}
						opreate += "</a>&nbsp;";
					}}{{opreate}}
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].EX.WDID.wdmc)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].FAID)}}</td>
				<td class="td_center">
					<a href="####" onclick="detail('{{d.list[i].DDBH}}');">{{$.cut(d.list[i].WBDH, 15)}}</a>
				</td>
				<td class="td_center" style="width:120px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis">
					<span data-html="true" title="{{$.nullToEmpty(d.list[i].WD_ZCDM)}}">{{$.nullToEmpty(d.list[i].WD_ZCDM)}}</span>
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].WD_ZCLX)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].WD_DDLX)}}</td>
				{{# if('${param.wdpt}' == '10100050' || $("#wdpt").val() == '10100050' || $("#wdpt").val() == ''){ }}
				<td class="td_center jjcd_show">
					<div style="width:100%;height:100%;{{jjdColorRemind(d.list[i].JJD)}}">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				</td>
				{{#}}}
				<td class="td_center"bgcolor={{timeColorRemind('nosj',$.dateF(d.list[i].NOSJ,'yyyy-MM-dd'))}}>
					{{$.dateF(d.list[i].NOSJ,'MM-dd HH:mm')}}
				</td>
				<td class="td_left">{{$.nullToEmpty(d.list[i].ZCQDNAME)}}</td>
				<td class="td_center" bgcolor={{cglyColorRemind($.nullToEmpty(d.list[i].CGLY))}}>
					{{$.nullToEmpty(d.list[i].CGLY)}}
				</td>
				<td class="td_center">
					<span class='copytext' copytext="{{$.nullToEmpty(d.list[i].CG_DDBH)}}">{{$.nullToEmpty(d.list[i].CG_DDBH)}}</span>
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].CGZT)}}</td>
				<td class="td_center">{{ddztColorRemind($.nullToEmpty(d.list[i].DDZT))}}</td>
				<td class="td_center">
					<a href="###" style="text-decoration:none;" title="点击查看详细日志" 
					onclick="showLog('{{d.list[i].ZDCPJK_ID}}','{{d.list[i].CG_PNR_NO}}');">
                	{{cpztColorRemind($.nullToEmpty(d.list[i].CPZT))}}
				</a>
				<td class="td_center">{{phhtzt($.nullToEmpty(d.list[i].PHHTZT))}}</td>
				<td class="td_center">
                     <a href="###" onclick="enterLogPage('{{d.list[i].DDBH}}');">
						{{pnrnoShow($.nullToEmpty(d.list[i].XS_PNR_NO),$.nullToEmpty(d.list[i].CG_PNR_NO))}}
					</a>
               	</td>
				<td class="td_center">{{pnrZtColor(d.list[i].XS_PNR_ZT)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_HKGS_PNR)}}</td>
				<td class="td_center">{{$.findJson(HCLX,d.list[i].HCLX).mc}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].HC)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_HBH)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_CW)}}</td>
				<td class="td_center" bgcolor={{timeColorRemind('cfrq',$.dateF(d.list[i].CFRQ,'yyyy-MM-dd'))}}>
					{{$.dateF(d.list[i].CFRQ,'MM-dd HH:mm')}}
				</td>
				<td class="td_left">{{$.cut(d.list[i].CJR, 4)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].CJRS)}}</td>
				<td class="td_right">{{$.nullToEmpty(d.list[i].XS_ZDJ)}}</td>
				<td class="td_right">{{$.nullToEmpty(d.list[i].XS_PJ)}}</td>
				<td class="td_right">{{$.nullToEmpty(d.list[i].XS_JSF)}}</td>
				<td class="td_right">{{$.nullToEmpty(d.list[i].XS_TAX)}}</td>
				<td class="td_right">{{$.nullToEmpty(d.list[i].XS_HYXFS)}}</td>
				<td class="td_right">{{$.nullToEmpty(d.list[i].XS_YWXFS)}}</td>
				<td class="td_right">{{$.nullToEmpty(d.list[i].XS_YJF)}}</td>
				<td class="td_right">{{$.nullToEmpty(d.list[i].XS_JE)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].EX.SKKM.kmmc)}}</td>
  				<td class="td_center" bgcolor={{timeColorRemind('ddsj',$.dateF(d.list[i].DDSJ,'yyyy-MM-dd'))}}>
					{{$.dateF(d.list[i].DDSJ,'MM-dd HH:mm')}}
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].NXR)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].NXSJ)}}</td>
    		</tr>
		{{# } }}
	</table>
</div>
</script>
</head>
<body>
<c:set var="gngj" value="${empty param.gngj ? '1' : param.gngj }"></c:set>
<c:if test="${empty param.tlx}">
	<!--网店页签 -->
	<%@include file="list_title.jsp"%>
</c:if>
<c:if test="${!empty param.tlx and param.tlx == '1'}">
	<!--呼叫中心页签 -->
	<%@include file="/WEB-INF/views/callcenter/ddxx/list_title.jsp"%>
</c:if>
<!-- 隐藏提示颜色值 -->
<%@include file="../../colorremind/colorremind/hiddenColor.jsp" %>
<div class="container">
  	  	<div id="search_bar">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/jpddgl/jpkhdd/query?gngj=${gngj}" id="searchForm" name="searchForm" method="post" target="_blank">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
							<input type="hidden"  name="orderBy" value="ddsj desc" id="orderBy"/>
							<input type="hidden" value="ddsj" id="order"/>
							<input type="hidden" value="desc" id="by"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
							<input type="hidden"  name="nxdh" value="${param.nxdh}" id="nxdh"/>
							
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              					<tr>
              						<td id = "rs" class="ddglName">预订日始</td>
                  					<td>
                  						<input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" class="input-text Wdate" size="10" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                  					</td> 
                  					<td id = "rz" class="ddglName">预订日止</td>
                 					<td>
                 						<input type="text" name="jsrq" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" class="input-text Wdate" size="10" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                 					</td>
             						
             						<%--具体网店 --%>
	           						<c:if test = "${not empty param.wdid and param.wdid ne '0'}">
	           							<input type="hidden" name="wdid" value="${param.wdid }"/>
           						  		<td class="ddglName">网店</td>
             							<td class="ddgl">
           						  	    	<select name="" id="wdid" class="select" disabled="disabled" style="width: 100px;">
						                  	 	<option value="">全部</option>
						                  	 	<c:forEach items="${wdzlszList}" var="onewd">
								                  	<option value="${onewd.id }" ${onewd.id eq param.wdid ? 'selected': ''} >${onewd.wdmc }</option> 
						                  	 	</c:forEach>
						                    </select>
           						  	    </td>
	           						</c:if>
	           						<%--全部 --%>
	           						<c:if test = "${empty param.wdid or param.wdid eq '0'}">
           						  	  <td class="ddglName">网店平台</td>
             						  <td class="ddgl">
						                  <select name="wdpt" id="wdpt" class="select" style="width: 100px;">
						                  	 	<option value="">全部</option>
						                  	 	<c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
						                  	 		 <c:if test = "${onewdpt.id ne '10100'}">
						                  	 			<option value="${onewdpt.id }" ${param.wdpt eq onewdpt.id ? 'checked' : '' }>${onewdpt.mc }</option> 
						                  	 		 </c:if>
						                  	 	</c:forEach>
						                  </select>
					                  </td>
				                  	</c:if>
				                  	
              						<td class="ddglName">政策代码</td>
              						<td class="ddgl">
              							<input type="text" name="wd_zcdm" class="input-text lh25" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" size="10" />
              						</td>
              						<td class="ddglName">紧急度</td>
              						<td class="ddgl" size="30">
              							<select name="jjd" class="select" style="width: 101px;"> 
	              							<option value="" >==全部==</option>
							                <option value="1">临近最晚出票时间</option>
							                <option value="2">催出票</option>                    
							                <option value="3">AV舱位不足5个</option>
							                <option value="4">临近NO位时间</option>
							                <option value="5">出票超时长规范</option>
							                <option value="6">普通</option>
							             </select> 
              						</td>
              						
              						<td class="ddglName">
              							补位状态
              						</td>
              						<td class="ddgl">
              							<select name="sfbwcg" class="select" style="width: 101px;"> 
	              							<option value="" >==全部==</option>
							                <option value="1">补位成功</option>
							                <option value="2">补位失败</option>                    
							             </select>
              						</td>
              						<td class="ddglName">派单状态</td>
              						<td><!--0非派单  1出票中  2出票成功  3出票失败 -->
              							<select name="sfwbcpz" class="select" style="width: 100px;height: 24px;"> 
              							 	    <option value="" selected>==请选择==</option> 
              							 	    <option value="0">非派单</option>
					                        	<option value="1">派单中</option>
					                        	<option value="2">派单成功</option>
					                        	<option value="3">派单失败</option>
					                     </select>
              						</td>
              						
              					</tr>
              					<tr>
              						<td class="ddglName">日期条件</td>
						          	<td class="ddgl">
				               			<select name="dateType" class="select" style="width: 100px;height: 24px;" onChange="changeDate()">
						                   	<option value="1"  ${empty param.checkdate or param.checkdate=="1"?" selected":""}>预订日期</option>
						                   	<option value="2"  ${param.checkdate=="2"?" selected":""}>起飞日期</option>
				               			</select>
						          	</td>
						          	<td class="ddglName">外部单号</td>
              						<td class="ddgl">
              							<input type="text" name="wbdh" class="input-text lh25" size="10" />
              						</td>
              						<td class="ddglName">
              							订单状态
              						</td>
              						<td class="ddgl">
              							 <select name="ddzt" class="select" style="width: 100px;"> 
              							 	<option value="" selected>==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('JPDDZT')}" var="oneZt">
					                        	<option value="${oneZt.value}">${oneZt.mc}</option>
						                   	</c:forEach>
					                     </select>
              						</td>
              						<td class="ddglName">P N R </td>
              						<td class="ddgl">
              							<input type="text" name="pnr_no" class="input-text lh25" maxlength="6" onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              						<td class="ddglName">大编码</td>
              						<td class="ddgl">
              							<input type="text" name="hkgs_pnr" class="input-text lh25" onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              						<td class="ddglName">采购来源</td>
              						<td>
              							<select name="cgly" class="select" style="width: 100px;"> 
											<option value="" 'selected'}>==请选择==</option>
											<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
												<c:if test="${oneLx.id ne '10014'}">
													<option value="${oneLx.ywmc}" ${param.cgly eq oneLx.ywmc ? 'selected' : ''}>${oneLx.ywmc}</option>
												</c:if>
											</c:forEach>
										<select>
              						</td>
              					</tr>
              					<tr>
              						<td class="ddglName">乘 机 人</td>
              						<td class="ddgl">
              							<input type="text" name="cjr" class="input-text lh25" size="10" onblur="value=$.trim(this.value);"/>
              						</td>
              						<td class="ddglName">航    程</td>
              						<td class="ddgl">
              							<input type="text" name="hc" class="input-text lh25"  onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              						<td class="ddglName">航班号</td>
              						<td class="ddgl">
              							<input type="text" name="hbh" class="input-text lh25" title="输入两位查询航空公司，输入六位查询航班号"  style="width: 98px;" onblur="value=$.trim(this.value).toUpperCase();" size="6" />
              						</td>
              						<td class="ddglName">是否换编码</td>
              						<td><select name="sfhbm" class="select" style="width: 100px;height: 24px;"> 
              							 	    <option value="" selected>全部</option> 
					                        	<option value="1">换编码</option>
					                        	<option value="0">未换编码</option>
					                     </select>
              						</td>
              						<td class="ddglName">回填状态</td>
              						<td>
              							 <select name="phhtzt" class="select" style="width: 100px;height: 24px;"> 
              							 	    <option value="" selected>==全部==</option> 
					                        	<option value="0">未回填</option>
					                        	<option value="1">已回填</option>
					                        	<option value="2">回填失败</option>
					                     </select>
              						</td>
              						<td class="ddglName">采购订单号</td>
              						<td>
              							<input type="text" name="cg_ddbh" class="input-text lh25"  onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              						<td></td>
              						<td style="text-align: right;">
										<input type="button" id="searchFormButton" name="button" onclick="toSearch()" value="查询" class="ext_btn ext_btn_submit" />
									</td>
								</tr>
								<tr id="jjd_sm" style="display: none;">
									<td colspan="10">
										<span class="right" style="padding-right:5px;">
											<b>紧急程度说明：</b>
											<font style="width:100%;height:100%;background:#8e0707;margin-right:2px;margin-top:4px">&nbsp;&nbsp;</font>临近最晚出票时间
											<font style="width:12px;height:4px;background:#fd0106;margin-right:2px;margin-top:4px">&nbsp;&nbsp;</font>催出票
											<font style="width:12px;height:4px;background:#ee7ae9;margin-right:2px;margin-top:4px">&nbsp;&nbsp;</font>出票超时长规范
											<font style="width:12px;height:4px;background:#097efb;margin-right:2px;margin-top:4px">&nbsp;&nbsp;</font>AV舱位不足5个
											<font style="width:12px;height:4px;background:#fcc102;margin-right:2px;margin-top:4px">&nbsp;&nbsp;</font>临近NO位时间
										</span>
									</td>
			                    </tr>
              				</table>
              				
              			<table>
              				<tr>
              					<td>
              						<!-- 设置颜色及默认排序 -->
									<%@include file="../../colorremind/colorremind/szColor.jsp" %>
              					</td>
              					<td>
			              			<table>
										<tr>
											<td class="text_right">&nbsp;&nbsp;自动出票状态&nbsp;</td>
											<td class="text_left" colspan="4">
												<input type="checkbox" name="cpzt" value="-1" ${fn:contains(param.cpzt,',-1,') ? 'checked': ''} id="chk_zt_00">
												<label for="chk_zt_00">出票失败</label> 
												<input type="checkbox" name="cpzt" value="1" ${fn:contains(param.cpzt,',1,') ? 'checked': ''} id="chk_zt_1">
												<label for="chk_zt_1">出票中</label> 
												<input type="checkbox" name="cpzt" value="0" ${fn:contains(param.cpzt,',0,') ? 'checked': ''} id="chk_zt_0">
												<label for="chk_zt_0">自动出票成功</label>
												<input type="checkbox" name="cpzt" value="40" ${fn:contains(param.cpzt,',40,') ? 'checked': ''} id="chk_zt_40">
												<label for="chk_zt_40">手动出票成功</label>
											</td>
										</tr>
									</table>
              					</td>
              				</tr>
              			</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
		<table width="" cellpadding="1" style="margin-top: 3px;">
			<tr>
				<td width="100px">
	 				<input type="button" value="手工订单" class="ext_btn ext_btn_submit" onclick="enterSgdPage('${gngj}');">
			 	</td>
			 	<td width="100px" align = "left">
			 		<input type="button" value="手工导单" class="ext_btn ext_btn_submit" onclick="openDdrk('${param.wdid}','${gngj}');">
			 	</td>
			 	<td width="100px" align = "left">
	 				<input type="button" value="批量完成" class="ext_btn ext_btn_submit" onclick="batchWc();">
			 	</td>
			 	<td width="100px" align = "left">
	 				<input type="button" value="批量票号回填" class="ext_btn ext_btn_submit" onclick="batchPhht();">
			 	</td>
			</tr>
		</table>
      	<div  class="mt10" style="display:table;">
        	<div id="list_table_page1">
       		 <!-- 分页  ID不能修改-->
        </div>
        <div class="box span10 oh" id="list_table">
             <!-- 显示列表 ID不能修改 -->   
        </div>
        <div id="list_table_page">
        <!-- 分页  ID不能修改-->
     	</div>
	</div>
</body>
</html>