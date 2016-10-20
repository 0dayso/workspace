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
<script type="text/javascript" src="${ctx}/static/js/b2bcp/shareto/shareto_float.js"></script>
<script type="text/javascript" src="${ctx}/static/js/b2bcp/b2bcp.js"></script>
<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
<script type="text/javascript" src="${ctx}/static/js/khdd/khdd.js?timestamp=<%=(new Date()).getTime()%>"></script>
<script type="text/javascript" src="${ctx}/static/js/khdd/operate_khdd.js?timestamp=<%=(new Date()).getTime()%>"></script>
<script type="text/javascript">
    HCLX = '${vfn:toJSON(vfn:dictList('HCLX'))}';
    JPDDZT = '${vfn:toJSON(vfn:dictList('JPDDZT'))}';
	$(function(){
		//加载锁单人查询控件
		$("#sdr").autocompleteDynamic('shyhb','cp_sdr');
		//进入页面默认查询
		$("#searchFormButton").click();	
	});
	
</script>
<!-- 1全部出票 2部分出票  0未出票 -1数据错误 -->
<script id="tpl_list_table" type="text/html">
<div>
	<table width="2000px" border="0" cellpadding="0" name="cpkzt_table" cellspacing="0" class="list_table">
	<tr>
		<th width="20"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
		<th width="20">序号</th>
		<th width="140">操作</th>
		<th width="80">网店</th>
		<th width="50">方案</th>
		<th width="90">外部单号</th>
		<th width="120">政策代码</th>
		<th width="50">产品类型</th>
		<th width="50">订单类型</th>
		{{#
			var wdpt=$("#wdid option:selected").attr("wdpt");
			if(wdpt == '' || wdpt == '10100050'){
		}}
		<th width="50">紧急度</th>
		{{#}}}
		<th width="80"><a href="###" onclick="sort('nosj');">NO位时间</a></th>
		<th width="50">政策渠道</th>
		<th width="50">采购来源</th>
		<th width="90">采购订单号</th>
		<th width="50">平台采<br>购状态</th>
		<th width="80">出票<br>锁单人</th>
		<th width="50">订单状态</th>
		<th width="80">自动出票状态</th>
		<th width="80">票号回填状态</th>
		<th width="90">PNR</th>
		<th width="50">PNR状态</th>
		<th width="50">大编码</th>
		<th width="50">航程<br>类型</th>
		<th width="50">航程</th>
		<th width="50">航班号</th>
		<th width="30">舱位</th>
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
		<th width="50">收款金额</th>
		<th width="50">收款科目</th>
		<th width="80"><a href="###" onclick="sort('ddsj');">预订时间</a></th>
		<th width="50">联系人</th>
		<th width="90">联系电话</th>
	</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
				<td width="25" align="center">	
						<input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" sortnum="{{i+1}}"  
						phhtzt="{{$.nullToEmpty(d.list[i].PHHTZT)}}" 
						wbdh="{{$.nullToEmpty(d.list[i].WBDH)}}"
						wdid="{{$.nullToEmpty(d.list[i].WDID)}}"
						cpzt="{{$.nullToEmpty(d.list[i].CPZT)}}"
						ddzt="{{$.nullToEmpty(d.list[i].DDZT)}}"
						zdcpjk_id="{{$.nullToEmpty(d.list[i].ZDCPJK_ID)}}"
						value="{{$.nullToEmpty(d.list[i].DDBH)}}">
				</td>
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
					{{#
                   		var zt = $.nullToEmpty(d.list[i].DDZT);
  						if (zt == '1') {
					}}
					<a href="##" onclick="cancel('{{d.list[i].DDBH}}');">消</a>
					{{#
  						} else {
					}}
					<font style="text-decoration: line-through" title="取消订单">消</font>
					{{#
						}
					}}

					{{#
                   		var zt = $.nullToEmpty(d.list[i].DDZT);
  						var sfcp=$.nullToEmpty(d.list[i].SFCP);
						var cg_pnr_zt = $.nullToEmpty(d.list[i].CG_PNR_ZT);
  						if ((zt == '1' || zt == '2') && sfcp == '0') {
					}}
					<a href="##" onclick="enterCpPage('{{d.list[i].DDBH}}','出票控制台出票-锁单');">出</a>
                     {{#
                          if(cg_pnr_zt != 'RR' || cg_pnr_zt != 'XX'){
                     }}
                        <a href='###' onclick=sgBw('{{d.list[i].DDBH}}','{{d.list[i].CG_PNR_ZT}}') title='PNR编码NO时进行手工补位'>补</a>
                     {{#
						}
					 }}          
					{{#
  						} else {
					}}
					<font style="text-decoration: line-through" title="订单出票">出</font>
					{{#
						}
					}}
					{{#
                   		var cpsdr = $.nullToEmpty(d.list[i].CP_SDR);
                        var sdcz="";
						var zt = $.nullToEmpty(d.list[i].DDZT);
                        var ddbh = $.nullToEmpty(d.list[i].DDBH);
  						if (cpsdr != '' && zt != '3') {
					     sdcz="<a href='###' onclick=jiesuo('"+ddbh+"','出票控制台解锁') >解锁</a>";
						}else{
                          sdcz="解锁";
						}
					}}
					{{sdcz}}
					<%--
					{{#
                   		var zt = $.nullToEmpty(d.list[i].DDZT);
  						if (zt == '1' || zt == '2') {
					}}
					<a href="##" onclick="enterWcPage('{{d.list[i].DDBH}}');">完</a>
					{{#
  						} else {
					}}
					<font style="text-decoration: line-through" title="订单完成">完</font>
					{{#
						}
					}}
					--%>
					{{#
                   		var zt = $.nullToEmpty(d.list[i].DDZT);
						var sfcp=$.nullToEmpty(d.list[i].SFCP);
						var pnr_zt = $.nullToEmpty(d.list[i].XS_PNR_ZT);
  						if ((zt == '1' || zt == '2') && pnr_zt != 'XX' && sfcp != 1) {
					}}
					<a href="##" onclick="enterZjpPage('{{d.list[i].DDBH}}');"><%--转--%>完</a>
					{{#
  						} else {
					}}
					<font style="text-decoration: line-through" title="转机票"><%--转--%>完</font>
					{{#
						}
					}}

					{{#
                   		var zt = $.nullToEmpty(d.list[i].DDZT);
  						if (zt == '1' || zt == '2') {
					}}
					{{#
						var qzcount = $.nullToEmpty(d.list[i].QZCOUNT);
						if(qzcount != 0 ){
					}}
						<a href="###" onclick="getQzxx('{{d.list[i].DDBH}}')"><img src='${ctx}/static/images/zicon.gif' title='点击查看签注信息' ></a>&nbsp;
					{{#
						}else{
					}}
						<a href="###" onclick="getQzxx('{{d.list[i].DDBH}}')" title="点击添加签注信息">注</a>&nbsp;
					{{#
						}
					}}
					{{#
  						} else {
					}}
					<font style="text-decoration: line-through" title="添加签注">注</font>
					{{#
						}
					}}
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].EX.WDID.wdmc)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].FAID)}}</td>
				<td class="td_center">
						<a href="###" onclick="detail('{{d.list[i].DDBH}}');">{{$.nullToEmpty(d.list[i].WBDH)}}</a>
				</td>
				<td class="td_center" style="width:120px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis">
						<span data-html="true" title="{{$.nullToEmpty(d.list[i].WD_ZCDM)}}">{{$.nullToEmpty(d.list[i].WD_ZCDM)}}</span
				</td>				
				<td class="td_center">{{$.nullToEmpty(d.list[i].WD_ZCLX)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].WD_DDLX)}}</td>
				{{#
					var wdpt=$("#wdid option:selected").attr("wdpt");
					if(wdpt == '' || wdpt == '10100050'){
				}}
				<td class="td_center">
                    <div style="width:100%;height:100%;{{jjdColorRemind(d.list[i].JJD)}}">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				</td>
				{{#}}}
				<td class="td_center" bgcolor={{timeColorRemind('nosj',$.dateF(d.list[i].NOSJ,'yyyy-MM-dd'))}}>
					{{$.dateF(d.list[i].NOSJ,'MM-dd HH:mm')}}
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].ZCQDNAME)}}</td>
				<td class="td_center" bgcolor={{cglyColorRemind($.nullToEmpty(d.list[i].CGLY))}}>
					{{$.nullToEmpty(d.list[i].CGLY)}}
				</td>
                <td class="td_center">
					<span class='copytext' copytext="{{$.nullToEmpty(d.list[i].CG_DDBH)}}">{{$.nullToEmpty(d.list[i].CG_DDBH)}}</span>
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].CGZT)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].CP_SDR)}} {{$.nullToEmpty(d.list[i].EX.CP_SDR.xm) }}</td>
				<td class="td_center">{{$.findJson(JPDDZT,d.list[i].DDZT).mc}}</td>
				<td class="td_center">
					<a href="###" style="text-decoration:none;" title="点击查看详细日志" 
					onclick="showLog('{{d.list[i].ZDCPJK_ID}}','{{d.list[i].CG_PNR_NO}}');">
                	{{cpztColorRemind($.nullToEmpty(d.list[i].CPZT))}}
				</a>
				</td>
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
				<td class="td_center">{{$.cut(d.list[i].CJR, 4)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].CJRS)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_ZDJ)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_PJ)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_JSF)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_TAX)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_HYXFS)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_YWXFS)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_YJF)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].XS_JE)}}</td>
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
<!--网店页签 -->
<%@include file="list_title.jsp"%>
<!-- 隐藏提示颜色值 -->
<%@include file="../../colorremind/colorremind/hiddenColor.jsp" %>
<div class="container">
  	  	<div id="search_bar">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/cpkzt/cpkzt/query" id="searchForm" name="searchForm" method="post" target="_blank">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
            				<!-- 在进入页面时在参数表中获取-->
							<input type="hidden"  name="orderBy" value="${orderBy}" id="orderBy"/>
							<input type="hidden" value="cfrq" id="order"/>
							<input type="hidden" value="asc" id="by"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
							<input type="hidden"  name="lx" value="${lx}" id="lx"/>
							<input type="hidden"  name="gngj" value="${empty param.gngj ? '1' : param.gngj}"/>
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              					<tr>
              						<td id = "rs" class="ddglName">预订日始</td>
                  					<td><input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" class="input-text Wdate"style="width:100px;height:22px;" size="10" id="mindate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxdate\')}'})"></td> 
                  					<td id = "rz" class="ddglName">预订日止</td>
                 					<td><input type="text" name="jsrq" style="width:100px;height:22px;" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" class="input-text Wdate"  id="maxdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'mindate\')}'})"></td>
           						  <td class="ddglName">
           								网&nbsp;&nbsp;店
           						  </td>
           						  
           						   <td class="ddgl">
				                  		<select name="wdid" id="wdid" style="width: 100px;height: 24px;" class="select">
					                  	 	<option value="" wdpt="">==全部==</option>
					                  	 	<c:forEach items="${jpfn:wdList(BUSER.shbh,param.gngj)}" var="wdBean">
												<option value="${wdBean.id}" ${param.wdid eq wdBean.id ? 'selected' : ''}>${wdBean.wdmc}</option>
											</c:forEach>
				                  		</select>
			                       </td>
              						<td class="ddglName">
              							政策代码
              						</td>
              						<td class="ddgl">
              							<input type="text" name="wd_zcdm" class="input-text lh25" size="10" />
              						</td>
              						<td class="ddglName">
              							紧急度
              						</td>
              						<td class="ddgl" size="30">
              							<select name="jjd" class="select"> 
	              							<option value="" >==全部==</option>
							                <option value="0">临近最晚出票时间</option>
							                <option value="1">催出票</option>                    
							                <option value="2">AV舱位不足5个</option>
							                <option value="3">临近NO位时间</option>
							                <option value="4">出票超时长规范</option>
							             </select> 
              						</td>
              						<td class="ddglName">
              							是否回填
              						</td>
              						<td>
              							 <select name="phhtzt" class="select" style="width: 100px;height: 24px;"> 
              							 	    <option value="" selected>全部</option> 
					                        	<option value="0">未回填</option>
					                        	<option value="1">已回填</option>
					                        	<option value="2">回填失败</option>
					                     </select>
              						</td>
              						 <c:if test="${empty param.lx or param.lx eq '1'}">
	              						 <td align="right"  style="border-left:1px solid #ccc;width:100px" rowspan="3">
												本页面将在<br/>
												<img src="${ctx}/static/images/blsx.png" />
												<span id="auto_ref">30</span> 秒后<br/>
												<input type="checkbox" name="refresh_status"  checked  id="refresh_status" onclick="autoRefesh();">
												<label for="refresh_status" style="color:blue">自动刷新</label>
										</td>
									</c:if>
              					</tr>
              					<tr>
              						<td class="ddglName">日期条件</td>
						          	<td class="ddgl">
				               			<select name="dateType" class="input1-text" style="width:102px;height:24px;" onChange="changeDate()">
						                   	<option value="1"  ${empty param.checkdate or param.checkdate=="1"?" selected":""}>预订日期</option>
						                   	<option value="2"  ${param.checkdate=="2"?" selected":""}>起飞日期</option>
				               			</select>
						          	</td>
						          	<td class="ddglName">
              							外部单号
              						</td>
              						<td class="ddgl">
              							<input type="text" name="wbdh" class="input-text lh25" style="width:100px;height:22px;" size="10" />
              						</td>
              						<td class="ddglName">
              							订单状态
              						</td>
              						<td class="ddgl">
              							 <select name="ddzt" class="select" style="width: 100px;height: 24px;"> 
              							 	<option value="" selected>全部</option> 
              							  	<c:forEach items="${vfn:dictList('JPDDZT')}" var="oneZt">
					                        	<option value="${oneZt.value}">${oneZt.mc}</option>
						                   	</c:forEach>
					                     </select>
              						</td>
              						<td class="ddglName">
              							PNR
              						</td>
              						<td class="ddgl">
              							<input type="text" name="pnr_no" value="" class="input-text lh25" maxlength="6" onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              						<td class="ddglName">
              							大编码
              						</td>
              						<td class="ddgl">
              							<input type="text" name="hkgs_pnr" value="" class="input-text lh25" maxlength="6" onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              						<td class="ddglName">
              							是否换编码
              						</td>
              						<td><select name="sfhbm" class="select" style="width: 100px;height: 24px;"> 
              							 	    <option value="" selected>全部</option> 
					                        	<option value="1">换编码</option>
					                        	<option value="0">未换编码</option>
					                     </select>
              						</td>
              					</tr>
              					
              					<tr>
              						<td class="ddglName">
              							锁 单 人
              						</td>
              						<td class="ddgl">
              							<input type="text" name="sdr" id="sdr" value="${param.sdr}" onblur="xgCpsdr();" class="input-text lh25" style="width:100px;height:22px;" size="10"/>
            							<input type="hidden" name="cp_sdr" id="cp_sdr" value="${param.cp_sdr}"/>
              						</td>
              						<td class="ddglName">
              							乘 机 人
              						</td>
              						<td class="ddgl">
              							<input type="text" name="cjr" class="input-text lh25" style="width:100px;height:22px;" size="10" />
              						</td>
              						<td class="ddglName">
              							航    程
              						</td>
              						<td class="ddgl">
              							<input type="text" name="hc" class="input-text lh25" style="width:100px;height:22px;" onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              						<td class="ddglName">
              							航 班 号
              						</td>
              						<td class="ddgl">
              							<input type="text" name="hbh" class="input-text lh25" title="输入两位查询航空公司，输入六位查询航班号"  style="width: 98px;height: 22px;" onblur="value=$.trim(this.value).toUpperCase();" size="6" />
              						</td>
              						<td>
              							派单状态
              						</td>
              						<td><!-- 1出票中  2出票成功  0出票失败 -->
              							<select name="sfwbcpz" class="select" style="width: 100px;height: 24px;"> 
              							 	    <option value="" selected>请选择</option> 
              							 	    <option value="0">非派单</option>
					                        	<option value="1">派单中</option>
					                        	<option value="2">派单成功</option>
					                        	<option value="3">派单失败</option>
					                     </select>
              						</td>
              						<c:if test="${param.lx eq '3'}">
	              						<td class="ddglName">采购订单号</td>
	              						<td>
	              							<input type="text" name="cg_ddbh" class="input-text lh25"  onblur="value=$.trim(this.value).toUpperCase();" size="10" />
	              						</td>
              						</c:if>
              						<c:if test="${param.lx ne '3'}">
              							<td></td>
              						</c:if>
              						<td>
										<input type="button" id="searchFormButton" name="button" value="查询" onclick="toSearch()" class="ext_btn ext_btn_submit" />
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
		<table cellpadding="1" style="margin-top: 3px;">
			<tr>
			    <td>
				    <c:if test="${jpkhdd.DDZT eq '2' && jpkhdd.XS_PNR_ZT ne 'XX'}">
			 			<input type="button" value="批量交票" class="ext_btn ext_btn_submit" onclick="batchJp();">
				 	</c:if>
					<input type="button" value="批量完成" class="ext_btn ext_btn_submit" onclick="batchWc();">
				 	<input type="button" value="批量票号回填" class="ext_btn ext_btn_submit" onclick="batchPhht();">
				 	<c:if test="${empty param.lx or param.lx eq '1'}">
	   						<input type="button" value="批量手工出票" title="批量手动触发全自动出票" class="ext_btn ext_btn_submit" onclick="batchSgcp();">
					</c:if>
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