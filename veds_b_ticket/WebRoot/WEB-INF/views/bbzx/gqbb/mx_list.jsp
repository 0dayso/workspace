<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>改签报表明细</title>
<style type="text/css">
	.short1{
			width:100%;
		 	white-space:nowrap;
			overflow:hidden;
		 	text-overflow:ellipsis;
		}
	a {
		text-decoration:none;
	}
	.gqd_right {
		text-align: right;
	}
	.gqd_center {
		text-align: center;
	}
	.gqd_left {
		text-align: left;
	}
</style>
<script type="text/javascript">

	var tjfs = "${param.tjfs}";
	
	$(function(){
		changeDateType();
		if (tjfs != "A") {
			$("#submitBtn").click();
		}
	});
	
	function changeDateType() {
        var rqtj = $("#rqtj").val();
		if (rqtj == "1") {
			$("#rs").html("申请日始");
			$("#rz").html("申请日止");
		} else if (rqtj == "2") {
			$("#rs").html("办理日始");
			$("#rz").html("办理日止");
		}
	}
	
	JPGQZT = '${vfn:toJSON(vfn:dictList('JPGQZT'))}';
</script>
</head>
<body>
<div class="container">
  	  	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/bbzx/gqbb/queryMx" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${empty param.pageNum ? 1 : param.pageNum}" id="pageNum"/>
            				<input type="hidden"  name="tjfs" value="A" />
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              					<tr>
              						<td class="gqd_right" id="rs">
              							
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" style="width:85px"  class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxdate\')}'})"/>
              						</td>
              						<td class="gqd_right" id="rz">
              							
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="jsrq" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" style="width:85px"   class="input-text Wdate" size="10" id="maxdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'mindate\')}'})"/>
              						</td>
              						<td class="gqd_right">
              							乘机人
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="cjr" value="${param.cjr }" style="width:85px"  class="input-text" />
              						</td>		
              						<td class="gqd_right">
              							PNR
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="xsPnrNo" value="${param.xsPnrNo }" onblur="value=$.trim(this.value).toUpperCase();" style="width:85px"  class="input-text" size="6" />
              						</td>
              						<td class="gqd_right">
              							航程
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="hc" value="${param.hc }" style="width:85px"  class="input-text" size="6" />
              						</td>
              					<tr>
              						<td class="gqd_right">
              							日期条件
              						</td>
              						<td class="gqd_left">
              							<select name="rqtj" class="select" id="rqtj" style="width:87px" onChange="changeDateType();">
              								<option value="1" ${param.rqtj eq '1' ? 'selected' : ''}>申请日期</option>
              								<option value="2" ${param.rqtj eq '2' ? 'selected' : ''}>办理日期</option>
              							</select>
              						</td>
              						<td class="gqd_right">
              							改签状态
              						</td>
              						<td class="gqd_left">
              							<select name="gqzt" class="select" style="width:87px"> 
              							 	<option value="" selected>==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('JPGQZT')}" var="oneZt">
              							  		<c:if test="${oneZt.value ne '7' and oneZt.value ne '8'}">
              							  			<option value="${oneZt.value}" ${param.gqzt eq oneZt.value ? 'selected' : ''}>${oneZt.mc}</option>
              							  		</c:if>
						                   	</c:forEach>
					                     </select>
              						</td>
              						<td class="gqd_right">
              							改签类型
              						</td>
              						<td class="gqd_left">
              							<select name="gqlx" class="select" style="width:87px">
              								<option value="">==全部==</option>
              								<option value="1" ${param.gqlx eq '1' ? 'selected' : ''}>改期</option>
              								<option value="2" ${param.gqlx eq '2' ? 'selected' : ''}>升舱</option>
              							</select>
              						</td>
              						<td class="gqd_right">
              							航班号
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="xsHbh" value="${param.xsHbh}" style="width:85px"   class="input-text" size="10" />
              						</td>
              						<td class="gqd_right">
              							政策代码
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="wdZcdm" value="${param.wdZcdm }" style="width:85px"   class="input-text"  />
              						</td>
								</tr>
								<tr>
									<td class="gqd_right">
              							网店
              						</td>
              						<td class="gqd_left">
              							<select name="wdid" class="select" style="width:87px">
              								<option value="">==全部==</option>
              								<c:forEach items="${wdzlszList}" var="wdid">	
					                  	 		<option value="${wdid.id }" ${param.wdid eq wdid.id ? 'selected' : '' }>${wdid.wdmc }</option> 
					                  		</c:forEach>
              							</select>
              						</td>
              						<td class="gqd_right">
             								网店平台
            						  </td>
            						<td class="gqd_left">
					                  <select name="wdpt" id="wdpt" class="select" style="width:87px">
					                  	 	<option value="">==全部==</option>
					                  	 	<c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
					                  	 		 <c:if test = "${onewdpt.id ne '10100'}">
					                  	 			<option value="${onewdpt.id }" ${param.wdpt eq onewdpt.id ? 'selected' : '' }>${onewdpt.mc }</option> 
					                  	 		 </c:if>
					                  	 	</c:forEach>
					                  </select>
				                  	</td>
              						<td></td>
              						<td><input type="hidden" name="hkgs" value="${param.hkgs}"/></td>
              						<td></td>
              						<td><input type="hidden" name="gqCgly" value="${param.gqCgly}"/></td>
									<td align="right">
										<input type="submit" id="submitBtn" value="查询" class="ext_btn ext_btn_submit" />
									</td>
									<td align="left">
										&nbsp;&nbsp;&nbsp;
										<input type="button" name="button" value="导出" class="ext_btn ext_btn_submit" />
									</td>
								</tr>
              				</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
      	<div  class="mt10">
        	<c:if test="${not empty page}">
	      		<multipage:pone page="${ctx}/vedsb/bbzx/gqbb/query" actionFormName="page" var="surl"></multipage:pone>
	      		${surl}
	      		<display:table id="vc" name="page.list" class="list_table" style="width:2000px" requestURI="" decorator="org.displaytag.decorator.TotalTableDecorator" >
					  <display:column title="序号" style="text-align:center;width:40px">${vc_rowNum}</display:column>
					  <display:column title="网店" maxLength="3" property="EX.WDID.wdmc" style="text-align:left;width:60px" />
					  <display:column title="方案" property="FAID"  style="text-align:left;width:40px"  />
					  <display:column title="政策代码" property="WD_ZCDM" maxLength="8" style="text-align:left;width:60px"  />
					  <display:column title="外部单号" property="WBDH"  style="text-align:left;width:100px" maxLength="12" />
					  <display:column title="航空<br/>公司" property="HKGS" style="text-align:center;width:40px"/>
					  <display:column title="改签类型" style="text-align:center;width:50px">
					  	${vc.GQLX eq '1' ? '改期' : '升舱'}
					  </display:column>  
					  <display:column title="改签状态" style="text-align:center;width:50px" >
					  	<c:forEach items="${vfn:dictList('JPGQZT')}" var="oneZt">
				 			<c:if test="${oneZt.value eq vc.GQZT}">
				 				${oneZt.mc}
				 			</c:if>
		          		</c:forEach>
					  </display:column>
					  <display:column title="乘机人" property="CJR" maxLength="4" style="text-align:left;width:40px"/>
					  <display:column title="票号" property="TKNO" style="text-align:left;width:200px"  /> 
		  			  <display:column title="采购来源" style="text-align:center;width:60px">
		  			  	<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
							<c:if test="${oneLx.id eq vc.GQ_CGLY}">
								${oneLx.mc }
							</c:if>
						</c:forEach>
		  			  </display:column>
		  			  <display:column title="采购科目" property="EX.GQ_CGKM.kmmc" style="text-align:left;width:60px" />
		  			  <display:column title="出票单位" property="GQ_CGDW" style="text-align:left;width:60px"/> 
		  			  <display:column title="航程" property="HC" maxLength="6" style="text-align:center;width:60px" > </display:column>
		  			  <display:merge title="改签前航段信息"> 
		  			  	  <display:column title="PNR" property="XS_PNR_NO" style="text-align:center;width:40px"/> 
			  			  <display:column title="航班号" property="XS_HBH" style="text-align:center;width:40px" />
			  			  <display:column title="舱位" property="XS_CW"  style="text-align:center;width:20px"/> 
			  			  <display:column title="起飞时间" style="text-align:center;width:60px" >
			  			  	${vfn:format(vc.CFRQ,'MM-dd')}</br>
			  			  	${vc.CFSJ}
			  			  </display:column>
		  			  </display:merge>
		  			  <display:merge title="改签后航段信息"> 
		  			  	  <display:column title="PNR" property="GQ_XS_PNR_NO" style="text-align:center;width:40px"/>
			  			  <display:column title="航班号" property="GQ_XS_HBH" style="text-align:center;width:40px" />
			  			  <display:column title="舱位" property="GQ_XS_CW"  style="text-align:center;width:20px"/> 
			  			  <display:column title="起飞时间" style="text-align:center;width:60px">
			  			  	${vfn:format(vc.GQ_CFRQ,'MM-dd')}</br>
			  			  	${vc.GQ_CFSJ}
			  			  </display:column>
		  			  </display:merge>
		  			  <display:column title="销售改签<br/>费用" property="GQ_XSFY" style="text-align:right;width:60px" format="{0,number,#0.00}" total="true"/> 
		  			  <display:column title="收款状态" style="text-align:center;width:60px" >
		  			  	${vc.SKZT eq '0' ? '未收款' : '已收款'}
		  			  </display:column> 
		  			  <display:column title="收款科目" property="EX.SKKM.kmmc" style="text-align:left;width:60px"/> 
		  			  <display:column title="采购改签<br/>费用" property="GQ_CGFY" style="text-align:right;width:60px" format="{0,number,#0.00}" total="true"/> 
		  			  <display:column title="联系人" property="NXR"  style="text-align:left;width:40px"/> 
		  			  <display:column title="联系电话" style="text-align:center;width:60px" >
		  			  	${empty vc.NXDH ? vc.NXSJ : vc.NXDH}
		  			  </display:column>
		  			  <display:column title="申请人/时间" style="text-align:center;width:100px">
		  			  	${vc.DDYH}<br/>
		  			  	${vfn:format(vc.DDSJ,'MM-dd HH:mm')}
		  			  </display:column> 
		  			  <display:column title="办理人/时间" style="text-align:center;width:100px" >
		  			  	${vc.GQ_BLR}<br/>
		  			  	${vfn:format(vc.GQ_BLSJ,'MM-dd HH:mm')}
		  			  </display:column> 
					</display:table>
				${surl}
			</c:if>
	   </div>
	</body>
</html>