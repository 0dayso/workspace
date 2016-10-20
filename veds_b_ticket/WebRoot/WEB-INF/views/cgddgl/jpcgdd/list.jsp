<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>采购订单管理</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
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
	.cgdd_right{
		text-align: right;
	}
	.cgdd_left{
		text-align: left;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#searchFormButton").click();
	})
	CGDDZT = '${vfn:toJSON(vfn:dictList('CGDDZT'))}';
	CGJYZT = '${vfn:toJSON(vfn:dictList('CGDDJYZT'))}';
	CGLY = '${vfn:toJSON(vfc:getVeclassLb('10014'))}';
	CGLY = '${vfn:toJSON(vfc:getVeclassLb('10014'))}';
</script>
<script id="tpl_list_table" type="text/html">
	<div style="overflow-x:scroll">
	<table style="width:2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th style="width:20px;height:40px">序号</th>
			<th style="width:100px;height:40px">订单编号</th>
			<th style="width:80px;height:40px">订单状态</th>
			<th style="width:40px;height:40px">PNR</th>
			<th style="width:60px;height:40px">大编码</th>
			<th style="width:80px;height:40px">支付方式</th>
			<th style="width:100px;height:40px">支付流水号</th>
			<th style="width:60px;height:40px">支付账号</th>
			<th style="width:60px;height:40px">支付金额</th>
			<th style="width:60px;height:40px">航空公司账号</th>
			<th style="width:50px;height:40px">航班号</th>
			<th width="20px;height:20px">舱位</th>
			<th style="width:60px;height:40px">舱位票价</th>
			<th style="width:60px;height:40px">订单来源</th>
			<th style="width:80px;height:40px">采购支付科目</th>
			<th style="width:80px;height:40px">采购支付方式</th>
			<th style="width:60px;height:40px">采购来源</th>
			<th style="width:80px;height:40px">采购单位</th>
			<th style="width:60px;height:40px">商户编号</th>
			<th style="width:60px;height:40px">交易状态</th>
			<th style="width:100px;height:40px">代购单号</th>
			<th style="width:40px;height:40px">已扫描<br>次数</th>
			<th style="width:100px;height:40px">外采单号</th>
			<th style="width:40px;height:40px">承运人</th>
			<th style="width:100px;height:40px">错误信息</th>
			<th style="width:80px;height:40px">创建人</th>
			<th style="width:80px;height:40px">创建时间</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td	 class="td_center">{{  i+1 }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].ddbh) }}</td>
				<td	 class="td_center">{{  $.findJson(CGDDZT,d.list[i].zt).mc }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].pnrno) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].hkgsPnrno) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].paykind) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].tradeNo) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].zfzh) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].zfje) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].hkgszh) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].hbh) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].cw) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].cwpj) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].ddly) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].cgZfkm) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].cgZffs) }}</td>
				<td	 class="td_center">{{  $.findJson(CGLY,d.list[i].cgly).mc }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].cgdw) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].shbh) }}</td>
				<td  class="td_center">{{  $.findJson(CGJYZT,d.list[i].jyzt).mc }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].dgdh) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].ysmcs) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].hkgsDdbh) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].hkgs) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].error) }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].cjUserid) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].cjdatetime) }}</td>
    		</tr>
		{{# } }}
	</table>
	</div>
</script>
</head>
<body>
<div class="container">
  	  	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/cgddgl/jpcgdd/list" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="e4c64c90db44df4d396d54bdb8ee826a" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              					<tr>
              						<td class="cgdd_right">
              							创建日始
              						</td>
              						<td class="cgdd_left">
              							<input type="text" name="search_GTE_cjdatetime" value="${empty param.search_GTE_cjdatetime ? vfn:dateShort() : param.search_GTE_cjdatetime}" style="width:85px"  class="input-text Wdate" size="10" id="starttime" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}'})"/>
              						</td>
              						<td class="cgdd_right">
              							创建日止
              						</td>
              						<td class="cgdd_left">
              							<input type="text" name="search_LTE_cjdatetime" value="${empty param.search_LTE_cjdatetime ? vfn:dateShort() : param.search_LTE_cjdatetime}" style="width:85px"   class="input-text Wdate" size="10" id="endtime" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}'})"/>
              						</td>
              						<td class="cgdd_right">
              							订单编号
              						</td>
              						<td class="cgdd_left">
              							<input type="text" name="search_EQ_ddbh" value="${param.search_EQ_ddbh }" style="width:85px"  />
              						</td>
              						<td class="cgdd_right">
              							PNR
              						</td>
              						<td class="cgdd_left">
              							<input type="text" name="search_EQ_pnrno" value="${param.search_EQ_pnrno }" onblur="value=$.trim(this.value).toUpperCase();" style="width:85px"  class="input-text" />
              						</td>
              						<td class="cgdd_right">
              							大编码
              						</td>
              						<td class="cgdd_left">
              							<input type="text" name="search_EQ_hkgs_pnrno" value="${param.search_EQ_hkgs_pnrno }" onblur="value=$.trim(this.value).toUpperCase();" style="width:85px"  class="input-text" />
              						</td>	
              					<tr>
              						<td class="cgdd_right">
              							采购来源
              						</td>
              						<td class="cgdd_left">
              							<select name="search_EQ_cgly" class="select" > 
              							 	<option value="" selected>==全部==</option> 
              							 	<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
              							 		<c:if test="${oneLx.id ne '10014' and oneLx.id ne '100141'}">
              							 			<option value="${oneLx.id}" ${oneLx.id eq param.search_EQ_cgly ? 'selected' : ''}>${oneLx.mc}</option>
              							 		</c:if>
											</c:forEach>
					                     </select>
              						</td>
              						<td class="cgdd_right">
              							外采单号
              						</td>
              						<td class="cgdd_left">
              							<input type="text" name="search_EQ_hkgs_ddbh" value="${param.search_EQ_hkgs_ddbh }" style="width:85px"    class="input-text" />
              						</td>
              						<td class="cgdd_right">
              							交易状态
              						</td>
              						<td class="cgdd_left">
              							<select name="search_EQ_jyzt" class="select" > 
              							 	<option value="" selected>==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('CGDDJYZT')}" var="oneZt">
					                        	<option value="${oneZt.value}" ${ontZt.value eq param.search_EQ_jyzt ? 'selected' : '' }>${oneZt.mc}</option>
						                   	</c:forEach>
					                     </select>
              						</td>	
              						<td class="cgdd_right">
              							订单状态
              						</td>
              						<td class="cgdd_left">
              							<select name="search_EQ_zt" class="select" > 
              							 	<option value="" selected>==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('CGDDZT')}" var="oneZt">
					                        	<option value="${oneZt.value}" ${ontZt.value eq param.search_EQ_zt ? 'selected' : ''}>${oneZt.mc}</option>
						                   	</c:forEach>
					                     </select>
              						</td>
              						<td class="cgdd_right">
              							支付流水号
              						</td>
              						<td class="cgdd_left">
              							<input type="text" name="search_EQ_trade_no" value="${param.search_EQ_trade_no }" style="width:85px"   class="input-text" size="10" />
              						</td>
								</tr>
								<tr>
              						<td colspan="9"></td>
									<td align="right">
										<input type="button" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit" />
									</td>
								</tr>
              				</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
      	<div  class="mt10">
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