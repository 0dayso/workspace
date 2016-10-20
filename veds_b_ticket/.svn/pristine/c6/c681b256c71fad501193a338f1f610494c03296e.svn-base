<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>平台日志</title>
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
	.ptrz_right{
		text-align: right;
	}
	.ptrz_left{
		text-align: left;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#cgpt").hide();
		$("#wdpt").hide();
		$("#cgpt").attr("disabled","disabled");
		$("#wdpt").attr("disabled","disabled");
	});

	function subForm(ls) {
		if (ls == "0") {
			$("#searchForm").attr("action","${ctx}/vedsb/cgptsz/jpptlog/list");
			$("#searchFormButton").click();
		} else {
			$("#searchForm").attr("action","${ctx}/vedsb/cgptsz/jpptlogls/list");
			$("#searchFormButton").click();
		}
	}
	CGPT = '${vfn:toJSON(vfc:getVeclassLb('100021'))}';
	WDPT = '${vfn:toJSON(vfc:getVeclassLb('10100'))}';
	PTLOGDDLX = '${vfn:toJSON(vfn:dictList('PTLOGDDLX'))}';
	PTLOGYWLX = '${vfn:toJSON(vfn:dictList('PTLOGYWLX'))}';
	$.findJsonYwmc = function(jsonArray,v){
		jsonArray = eval(jsonArray);
		for(var p in jsonArray){//遍历json数组时，这么写p为索引，0,1
			if(jsonArray[p].ywmc == v){
				return jsonArray[p];
			}
		}
		return {"ywmc":"","label":"","mc":"","pyszm":""};
	}
	
	$.findJsonId = function(jsonArray,v){
		jsonArray = eval(jsonArray);
		for(var p in jsonArray){//遍历json数组时，这么写p为索引，0,1
			if(jsonArray[p].id == v){
				return jsonArray[p];
			}
		}
		return {"id":"","label":"","mc":"","pyszm":""};
	}
	
	function changePt(val) {
		if ("1" == val) {
			$("#cgpt").show();
			$("#wdpt").hide();
			$("#allpt").hide();
			$("#cgpt").removeAttr("disabled");
			$("#allpt").attr("disabled","disabled");
			$("#wdpt").attr("disabled","disabled");
		} else if ("2" == val) {
			$("#wdpt").show();
			$("#cgpt").hide();
			$("#allpt").hide();
			$("#wdpt").removeAttr("disabled");
			$("#allpt").attr("disabled","disabled");
			$("#cgpt").attr("disabled","disabled");
		} else {
			$("#allpt").show();
			$("#cgpt").hide();
			$("#wdpt").hide();
			$("#allpt").removeAttr("disabled");
			$("#wdpt").attr("disabled","disabled");
			$("#cgpt").attr("disabled","disabled");
		}
	}
	function dowloadlog(obj){
		window.open(obj);
	}
</script>
<script id="tpl_list_table" type="text/html">
	<div>
	<table style="width:100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th style="width:10px;height:40px">序号</th>
			<th style="width:25px;height:40px">网店ID</th>
			<th style="width:25px;height:40px">网店MC</th>
			<th style="width:20px;height:40px">PNR</th>
			<th style="width:20px;height:40px">平台</th>
			<th style="width:40px;height:40px">订单类型</th>
			<th style="width:60px;height:40px">业务类型</th>
			<th style="width:30px;height:40px">交票状态</th>
			<th style="width:30px;height:40px">操作用户</th>
			<th style="width:40px;height:40px">外部单号</th>
			<th style="width:40px;height:40px">产品类型</th>
			<th style="width:40px;height:40px">采购供应</th>
			
			<th style="width:100px;height:40px">操作说明</th>
			<th style="width:80px;height:40px">记录时间</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td	 class="td_center">{{  i+1 }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].wdid)  }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].wdmc)  }}</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].pnrNo) }}</td>
				<td	 class="td_center">
					{{#
						var by2 =  $.nullToEmpty(d.list[i].by2);
						if(by2 == "1"){
					}}
							{{  $.findJsonYwmc(CGPT,d.list[i].ptzcbs).mc }}
					{{#
						} else if (by2 == "2") {	
					}}
							{{  $.findJsonId(WDPT,d.list[i].ptzcbs).mc }}
					{{#
						}
					}}
				</td>
				<td	 class="td_center">{{  $.findJson(PTLOGDDLX,d.list[i].ddlx).mc }}</td>
				<td  class="td_center">{{  $.findJson(PTLOGYWLX,d.list[i].ywlx).mc }}</td>
				<td	 class="td_center">
					{{#
						var jpzt =  $.nullToEmpty(d.list[i].jpzt);
						if (jpzt == "2") {
					}}
							交票成功
					{{#
						} else if (jpzt == "3") {
					}}
							交票失败
					{{#
						}
					}}
				</td>
				<td	 class="td_center">{{  $.nullToEmpty(d.list[i].yhbh) }}<a href="###" onclick="dowloadlog('${ctx}{{$.nullToEmpty(d.list[i].rzlj) }}');" title="点击下载平台日志"><img src="${ctx}/static/images/czlog.png"></a></td>
				<td	 class="td_center">{{  $.cut(d.list[i].tfid, 20) }}</td>
				<td	 class="td_center">
					{{#
						var by1 =  $.nullToEmpty(d.list[i].by1);
						if(by1 == "1001901"){
					}}
							国内机票
					{{#
						} else if (by1 == "1001902") {	
					}}
							国际机票
					{{#
						}
					}}
				</td>
				<td	 class="td_center">
					{{#
						var by2 =  $.nullToEmpty(d.list[i].by2);
						if(by2 == "1"){
					}}
							采购
					{{#
						} else if (by2 == "2") {	
					}}
							供应
					{{#
						}
					}}
				</td>
				<td	 class="td_center">{{  $.cut(d.list[i].czsm, 15) }}</td>
				<td	 class="td_center">{{  $.dateF(d.list[i].logDate,'yyyy-MM-dd HH:mm:ss') }}</td>
    		</tr>
		{{# } }}
	</table>
	</div>
</script>
</head>
<body>
<%@include file="/WEB-INF/views/cgptsz/list_title.jsp"%>
<div class="container">
  	  	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/cgptsz/jpptlog/list" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="53835dde977d08c089c10251ce8d0b74" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
            				<input type="hidden"  name="orderBy" value="log_date desc" id="orderBy"/>
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              					<tr>
              						<td class="ptrz_right">
              							创建日始
              						</td>
              						<td class="ptrz_left">
              							<input type="text" name="search_datsGTE_log_date" value="${empty param.search_datsGTE_log_date ? vfn:dateShort() : param.search_datsGTE_log_date}" style="width:85px"  class="input-text Wdate" size="10" id="starttime" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}'})"/>
              						</td>
              						<td class="ptrz_right">
              							创建日止
              						</td>
              						<td class="ptrz_left">
              							<input type="text" name="search_datdLTE_log_date" value="${empty param.search_datdLTE_log_date ? vfn:dateShort() : param.search_datdLTE_log_date}" style="width:85px"   class="input-text Wdate" size="10" id="endtime" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}'})"/>
              						</td>
              						<td class="ptrz_right">
              							PNR
              						</td>
              						<td class="ptrz_left">
              							<input type="text" name="search_EQ_pnr_no" value="" onblur="value=$.trim(this.value).toUpperCase();" style="width:85px"  class="input-text" />
              						</td>	
              						<td class="ptrz_right">
              							订单编号
              						</td>
              						<td class="ptrz_left">
              							<input type="text" name="search_EQ_ddbh" value="" style="width:85px"   class="input-text"  />
              						</td>
              						<td class="ptrz_right" title="如果是退废票则是退废票单号，如果是供应导单则是平台订单号">
              							平台单号
              						</td>
              						<td class="ptrz_left">
              							<input type="text" name="search_EQ_tfid" value="" style="width:85px"  class="input-text" />
              						</td>		
              					<tr>
              						<td class="ptrz_right">
              							平台类型
              						</td>
              						<td class="ptrz_left">
              							<select name="search_EQ_by2" class="select"  style="width:85px" onchange="changePt(this.value)">
              								<option value="" >==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('PTLOGCGGY')}" var="oneZt">
					                        	<option value="${oneZt.value}" >${oneZt.mc}</option>
						                   	</c:forEach> 
					                     </select>
              						</td>
           							<td class="ptrz_right">
              							平台
              						</td>
              						<td class="ptrz_left">
              							<select id="cgpt" name="search_EQ_ptzcbs" class="select"  style="width:85px" > 
              							 	<option value="" selected>==全部==</option>
	              							 	<c:forEach items="${vfc:getVeclassLb('100021')}" var="ptsz" >
	              									<c:if test="${ptsz.id ne '100021'}">
	              										<option value="${ptsz.ywmc}" >${ptsz.mc}</option>
	              									</c:if>
												</c:forEach>
										 </select>
										 <select id="wdpt" name="search_EQ_ptzcbs" class="select"  style="width:85px" > 
              							 	<option value="" selected>==全部==</option>
             									<c:forEach items="${vfc:getVeclassLb('10100')}" var="ptsz" >
	              									<c:if test="${ptsz.id ne '10100'}">
	              										<option value="${ptsz.id}" >${ptsz.mc}</option>
	              									</c:if>
												</c:forEach>
										 </select>
										 <select id="allpt" name="search_EQ_ptzcbs" class="select"  style="width:85px"> 
              							 	<option value="" selected>==全部==</option>
	              							 	<c:forEach items="${vfc:getVeclassLb('100021')}" var="ptsz" >
	              									<c:if test="${ptsz.id ne '100021'}">
	              										<option value="${ptsz.ywmc}" >${ptsz.mc}</option>
	              									</c:if>
												</c:forEach>
             									<c:forEach items="${vfc:getVeclassLb('10100')}" var="ptsz" >
	              									<c:if test="${ptsz.id ne '10100'}">
	              										<option value="${ptsz.id}" >${ptsz.mc}</option>
	              									</c:if>
												</c:forEach>
										 </select>
              						</td>
              						<td class="ptrz_right">
              							产品类型
              						</td>
              						<td class="ptrz_left">
              							<select name="search_EQ_by1" class="select"  style="width:85px" > 
              							 	<option value="" >==全部==</option> 
              							  	<c:forEach items="${vfc:getVeclassLb('10019')}" var="ptsz">
              							  		<c:if test="${ptsz.id ne '10019'}">
              							  			<option value="${ptsz.id}" >${ptsz.mc}</option>
              							  		</c:if>
						                   	</c:forEach>
					                     </select>
              						</td>
              						<td class="ptrz_right">
              							订单类型
              						</td>
              						<td class="ptrz_left">
              							<select name="search_EQ_ddlx" class="select"  style="width:85px" > 
              							 	<option value="" >==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('PTLOGDDLX')}" var="oneZt">
					                        	<option value="${oneZt.value}" >${oneZt.mc}</option>
						                   	</c:forEach>
					                     </select>
              						</td>		
              						<td class="ptrz_right">
              							业务类型
              						</td>
              						<td class="ptrz_left">
              							<select name="search_EQ_ywlx" class="select"  style="width:85px" > 
              							 	<option value="" >==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('PTLOGYWLX')}" var="oneZt">
					                        	<option value="${oneZt.value}" >${oneZt.mc}</option>
						                   	</c:forEach>
					                     </select>
              						</td>
              					</tr>
              					<tr>	
              						<td colspan="7"></td>
              						<td><input type="button" id="searchFormButton" name="button" value="查询" style="display: none" class="ext_btn ext_btn_submit" /></td>
									<td colspan="2" align="left">
										<input type="button"  name="button" value="查近期" class="ext_btn ext_btn_submit" onclick="subForm('0')"/>
										<input type="button"  name="button" value="查历史" class="ext_btn ext_btn_submit" onclick="subForm('1')"/>
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