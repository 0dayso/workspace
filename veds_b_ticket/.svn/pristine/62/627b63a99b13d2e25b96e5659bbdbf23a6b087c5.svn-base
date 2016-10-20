<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<style>
	.input: {
		width: 30px;
	}
	
	.td_right {
		text-align: right;
	}
	
	.td_left {
		text-align: left;
	}
	a:link{
		text-decoration:none;
	}
</style>
<script type="text/javascript">
	$(function(){	
	    $("#searchFormButton").click();
	})
	
	function autoSetDate(ths){
		if(ths.value!="" && ths!="1" ){
			$("#kssj").attr("value",ths.value);
		    $("#jssj").attr("value",ths.value);
		    sumTime(ths.value);
		}
		if(ths=="1"){
			var time = $("#kssj").val();
			if(time == "" || time == null){
				var y = new Date().getFullYear();
				var m = new Date().getMonth()+1;
				var d = new Date().getDate();
				if(m <= 9) m = "0"+m;
				if(d <= 9) d = "0"+d; 
				time = y+"-"+m+"-"+d;
			}
			sumTime(time);
		}
		$("#currentDay").css({"background-color":"#3e75d0","border-color":"blue","color":"red","border":""});
		$("#today").css({"background-color":"white","color":"","border":"none"});
	}
    
	function sumTime(val){
		$("#currentDay").attr("value",val);
		var oneday = addDays(val,1);
		$("#oneDaysAgo").attr("value",oneday);
		var twoday = addDays(val,2);
		$("#twoDaysAgo").attr("value",twoday);
		var threeday = addDays(val,3);
		$("#threeDaysAgo").attr("value",threeday);
		var fourday = addDays(val,4);
		$("#fourDaysAgo").attr("value",fourday);
	}
	
	function addDays(date,n){
		var nd = new Date(date);
			nd = nd.valueOf();
		  	nd = nd - n * 24 * 60 * 60 * 1000;
		  	nd = new Date(nd);
		var y = nd.getFullYear();
		var m = nd.getMonth()+1;
		var d = nd.getDate();
		if(m <= 9)
			m = "0"+m;
		if(d <= 9)
			d = "0"+d; 
		var cdate = y+"-"+m+"-"+d;
		return cdate;
	}
	
	function nowDay(now){
		$("#kssj").val(now);
	    $("#jssj").val(now);
	    $("#currentDay").attr("value",now);
	    sumTime(now);
	    $("#today").css({"background-color":"#3e75d0","border-color":"blue","color":"red","border":""});
	    $("#currentDay").css({"background-color":"#3e75d0","border-color":"blue","color":"red","border":""});
	}
	
	function selectall(obj){
		$("input[name='btkno']").attr("checked", obj.checked);
	}
	
	function batchPro(){
		var btkno = document.getElementsByName("btkno");
		var kssj=$("#kssj").val();
		var jssj=$("#jssj").val();
		var tknos = [];
			for(var i=0;i<btkno.length;i++){
				if(btkno[i].checked == true){
					tknos.push(btkno[i].value);
				}
			}
		var count = $("input[name='btkno']:checked").length;
		if(count == '0'){
			layer.msg("请选择一条记录!",2,2);
		}else{
			$.layer({
				type: 2,
				title: ['批量处理'],
				area: ['440px', '160px'],
				iframe: {src: "${ctx}/vedsb/bbzx/jpztjk/viewjpztjkedit?tknos="+tknos+"&kssj="+kssj+"&jssj="+jssj}
		    });
		}
	}
	
	function batchDel(){
		var btkno = document.getElementsByName("btkno");
		var kssj=$("#kssj").val();
		var jssj=$("#jssj").val();
		var tknos = [];
			for(var i=0;i<btkno.length;i++){
				if(btkno[i].checked == true){
					tknos.push(btkno[i].value);
				}
			}
		var count = $("input[name='btkno']:checked").length;
		if(count == '0'){
			layer.msg("请选择一条记录!",2,1);
		}else{
			$.layer({
			   	 area: ['400px', '150px'],
			        dialog: {
			            msg: '确定删除选中行？',
			            btns: 2,
			            type: 4,
			            btn: ['确定','取消'],
			            yes: function(){
			            	layer.msg('删除成功', 1, 2);
			            	location.href="${ctx}/vedsb/bbzx/jpztjk/jpztjkDel?tknos="+tknos+"&turningUrl=/vedsb/bbzx/jpztjk/viewlist?kssj="+kssj+"&jssj="+jssj;
			            }, no: function(){
			                layer.msg('放弃删除', 1, 3);
			            }
			     }
		    });
		}
	}
	
	function showBdwt(){
		var status = $("#status").val();
		if(status == "OPEN FOR USE"){
			$("#c_bdwt").css("display","");
		}else{
			$("#c_bdwt").css("display","none");
		}
	}
	
	//订单详
	function detail(id){
		var url = "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
	
	function openSmSz(){
		var url = "${ctx}/vedsb/bbzx/jpsmfssz/viewlist";	
	    var w = 720;
		var h = 600;
 		$.layer({type: 2,title: "<Strong>OPEN票扫描设置</Strong>",area: [w, h],iframe: {src: url}});
	}
</script>
<script id="tpl_list_table" type="text/html">
	<table width="2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="40px">序号</th>
			<th width="40px"><input type="checkbox" name="checkboxall" value="" onclick="selectall(this);"/></th>
			<th width="90px">出票时间</th>
			<th width="120px">提取时间</th>
			<th width="100px">原订单号</th>
			<th width="60px">PNR</th>
            <th width="120px">票号</th>
            <th width="120px">客票<br>状态</th>
			<th width="120px">当前客票状态</th>
			<th width="50px">办理<br>状态</th>
			<th width="120px">办理备注</th>
			<th width="80px">乘机人</th>
			<th width="60px">航程</th>
			<th width="60px">航班号</th>
			<th width="40px">航空<br>公司</th>
			<th width="30px">舱位</th>
			<th width="120px">起飞时间</th>
			<th width="40px">账单价</th>
			<th width="40px">机建</th>
			<th width="40px">税费</th>
			<th width="40px">小计</th>
			<th width="40px">采购<br>金额</th>
			<th width="100px">采购来源</th>
			<th width="90px">出票OFFICE号<br>/外出票单位</th>
			<th width="80px">工作号</th>
			<th width="60px">打票机</th>
			<th width="80px">订票员</th>
			<th width="100px">订票部门</th>
			<th width="120px">订票时间</th>
			<th width="100px">处理人</th>
			<th width="100px">处理部门</th>
			<th width="120px">处理时间</th>
			<th width="80px">本地退票状态</th>
		</tr>
		{{# for(var i = 0, len = d.list.length-1; i < len; i++){ }}
			<tr class="tr">
				<td class="td_center">{{ i+1 }}</td>
				<td class="td_center"><input type="checkbox" id="btkno" name="btkno" value="{{  $.nullToEmpty(d.list[i].TKNO ) }}"/></td>
				<td class="td_center">{{  $.dateF(d.list[i].CP_DATETIME,'yyyy-MM-dd') }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].DQ_DATETIME ) }}</td>
				<td class="td_center"><a href="###" onclick="detail('{{d.list[i].DDBH}}');" title="查看历史订单号详细">{{  $.nullToEmpty(d.list[i].DDBH ) }}</a></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].PNR_NO ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].TKNO ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].STATUS ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].ZH_STATUS ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CL_ZTMC ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CL_BZ ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CJR ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HC ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_HBH ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HKGS ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_CW ) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CFRQ,'yyyy-MM-dd HH:mm') }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_ZDJ ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_JSF ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_TAX ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XJ ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CGJE ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CGLYNAME ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CP_OFFICEID ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].WORKNO ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].PRINTNO ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CP_YHBH ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CP_BMBH ) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CP_DATETIME,'yyyy-MM-dd HH:mm') }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CL_USERID ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CL_DEPTID ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CL_DATETIME ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].BDWTZT ) }}</td>
    		</tr>
		{{# } }}
		{{# if(d.list.length-1 > 0){ }}
			<tr id="total">
				<td colspan="17" class="td_right">
					合计:
				</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[d.list.length-1].JPZDJS ) }}</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[d.list.length-1].JPJSFS ) }}</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[d.list.length-1].JPTAXS ) }}</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[d.list.length-1].XJS ) }}</td>
				<td colspan="33"></td>
			</tr>
		{{# } }}
	</table>
</script>
</head>
<body>
<div class="container_clear">
<div class="box_border">
<form action="${ctx}/vedsb/bbzx/jpztjk/queryPage" id="searchForm" name="searchForm" method="POST">
	<input type="hidden"  name="VIEW" value="692A3B3046E69162F490FF0C1E16BCF1" />
	<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
	<input type="hidden"  name="sortType" value="cp_date desc" id="sortType"/>
	<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
	<table class="table01" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td class="td_right">提取日始</td>
			<td class="td_left">
				<input type="text" id="kssj" name="kssj"  class="input-text Wdate" value="${empty param.kssj and empty param.cprs ? vfn:dateShort() : param.kssj}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="autoSetDate('1')" style="width: 80%;" />
			</td>
			<td class="td_right">提取日止</td>
			<td class="td_left">
				<input type="text" id="jssj" name="jssj"  class="input-text Wdate" value="${empty param.jssj and empty param.cprz ? vfn:dateShort() : param.jssj}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 80%;" />
			</td>
			<td class="td_right">机票状态</td>
			<td class="td_left">
				<select name="status" id="status" style="width: 80%;height: 24px;" onchange="showBdwt();">
					<option value=""  ${empty param.status ? 'selected' : '' }>==请选择==</option>
					<option value="0" ${param.status eq '0' ? 'selected' : '' } >未乘机</option>
					<option value="RETRANSMIT" ${(param.status eq 'RETRANSMIT')? 'selected' : '' } >RETRANSMIT</option>
				<c:forEach var="ve" items="${vfc:getVeclassLb('10084')}">
					<c:if test="${ve.id ne '10084'}">
						<option value="${ve.ywmc}" ${param.status eq ve.ywmc ? 'selected' : '' }>${ve.ywmc} 
					</c:if>
				</c:forEach>
				</select>
				<span id="c_bdwt" style="display: none"><input type="checkbox" id="bdwt" name="bdwt" value="1" ${param.bdwt eq '1' ? 'checked' : ''} title="勾选表示包含状态为REFUNDED但是未在系统中退票" ></span>
			</td>
			<td class="td_right">办理状态</td>
			<td class="td_left">
				<select id="clZt" name="clZt" style="width: 80%;height: 24px;">
					<option value=""  ${empty param.clZt  ? 'selected' : '' }>==请选择==</option>
					<option value="0" ${param.clZt eq '0' ? 'selected' : '' }>未办理退票</option>
					<option value="1" ${param.clZt eq '1' ? 'selected' : '' }>已办理退票</option>
				</select>
			</td>
			<td class="td_right">采购来源</td>
			<td class="td_left">
				<select id="cgly" name="cgly" style="width: 80%;height: 24px;">
					<option value=""  ${empty param.cgly  ? 'selected' : '' }>==请选择==</option>
					<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
						<c:if test="${oneLx.id ne '10014'}">
							<option value="${oneLx.id }">${oneLx.mc }</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_right">出票日始</td>
			<td class="td_left">
				<input type="text" name="cprs"  class="input-text Wdate" value="${param.tqrs }" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 80%;" />
			</td>
			<td class="td_right">出票日止</td>
			<td class="td_left">
				<input type="text" name="cprz"  class="input-text Wdate" value="${param.tqrz }" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 80%;" />
			</td>
			<td class="td_right">票号</td>
			<td class="td_left">
				<input type="text" name="tkno" value="${param.tkno }" class="input-text lh25" onblur="value =value.toUpperCase();value=strTrim(this.value);" size="10" style="width: 80%;"/>
			</td>
			<td class="td_right">P N R</td>
			<td class="td_left">
				<input type="text" name="pnrNo" value="${param.pnrNo }" class="input-text lh25" onblur="value=$.trim(this.value).toUpperCase();" size="10" style="width: 80%;" />
			</td>
			<td></td>
			<td>
				<input type="button" id="searchFormButton" class="ext_btn ext_btn_submit" value="查询">
			</td>
		</tr>
	</table>
	<c:set var="curDay" value="${empty param.kssj ? vfn:dateShort() : param.kssj }" />
	<table class="table01" border="0" width="90%" cellpadding="0" cellspacing="0">
		<tr align="center">
			<td>
				<input type="button" id="fourDaysAgo" name="fourDaysAgo" value="${vfn:getPreDay(curDay,-4) }" onclick="autoSetDate(this);" style="background-color:white;border:none;width: 50%">
			</td>
			<td>
				<input type="button" id="threeDaysAgo" name="threeDaysAgo" value="${vfn:getPreDay(curDay,-3)}" onclick="autoSetDate(this);" style="background-color:white;border:none;width: 50%">
			</td>
			<td>
				<input type="button" id="twoDaysAgo" name="twoDaysAgo" value="${vfn:getPreDay(curDay,-2)}" onclick="autoSetDate(this);" style="background-color:white;border:none;width: 50%">
			</td>
			<td>
				<input type="button" id="oneDaysAgo" name="oneDaysAgo" value="${vfn:getPreDay(curDay,-1)}" onclick="autoSetDate(this);" style="background-color:white;border:none;width: 50%">
			</td>
			<td>
				<input type="button" id="currentDay" name="currentDay" value="${curDay }" onclick="autoSetDate(this);" style="background-color:white;border:none;width: 50%; ">
			</td>
			<td>
			<input type="button" id="today"  value="今日提取" onclick="nowDay('${vfn:dateShort() }');" style="background-color:white;border:none;width: 50%; ">
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<input type="button"  onclick="batchPro();"  class="ext_btn ext_btn_submit" value="批量办理">
				<input type="button"  onclick="batchDel();"  class="ext_btn ext_btn_submit" value="批量删除">
				<input type="button"  onclick="openSmSz();"  class="ext_btn ext_btn_submit" value="OPEN票扫描设置">
			</td>
		</tr>
	</table>
	</form>
	</div>
	<div  class="mt10" style="display: table;">
		<div id="list_table_page1">
		<!-- 分页  ID不能修改-->
		</div>
		<div class="box span10 oh" id="list_table">
		<!-- 显示列表 ID不能修改 -->
		</div>
		<br>
		<div id="list_table_page">
		<!-- 分页  ID不能修改-->
		</div>
	</div>
</div>
</body>
</html>