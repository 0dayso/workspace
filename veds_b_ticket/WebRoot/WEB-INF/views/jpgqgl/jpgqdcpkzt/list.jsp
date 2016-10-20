<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>改签单出票控制台</title>
<style type="text/css">
	.short1{
			width:100%;
		 	white-space:nowrap;
			overflow:hidden;
		 	text-overflow:ellipsis;
		}
	.gqd_right {
		text-align: right;
	}
	.gqd_left {
		text-align: left;
	}
	.gqd_center {
		text-align: center;
	}
	a {
		text-decoration:none;
	}
</style>
<script type="text/javascript">
	function initPage() {
		var title = "${param.title}";
		if (title != null) {
			$("#searchFormButton").click();
		}
	}

	function detail(gqdh) {
		window.open("${ctx}/vedsb/jpgqgl/jpgqd/detail?gqdh=" + gqdh);
	}
	
	function transact(gqdh) {
		window.open("${ctx}/vedsb/jpgqgl/jpgqd/transact?forward=transact&gqdh=" + gqdh);
	}
	
	function apply() {
		window.open("${ctx}/vedsb/jpgqgl/jpgqd/viewapplylist");
	}
	
	function cancel(id) {
  		var url = "${ctx}/vedsb/jpgqgl/jpgqd/cancelGqd?xgly=fromCpkzt&gqdh="+gqdh;
  		$.layer({
  			area: ['250px', '150px'],
  			dialog : {
  				 msg : "您确定要取消该订单吗？",
  				 btns: 2,
	        	 type: 4,
	        	 btn : ['确定','取消'],
	        	 yes : function(){
	        	 	$.ajax({
	        	 		type:"post",
	  					url:url,
	  					success:function(result){
	  						if("0" == result){
	  							layer.msg("取消失败！",2,1);
	  						}else{
	  							layer.msg("取消成功！",2,1);
	  							$("#searchFormButton").click();
	  						}
	  					}
	        	 	});
	        	 },no: function(){
	             	layer.msg("取消订单操作", 2, 3);
	          	 }
  			}
  		});
	}
	
	JPGQZT = '${vfn:toJSON(vfn:dictList('JPGQZT'))}';
</script>
<script id="tpl_list_table" type="text/html">
	<div style="overflow-x:scroll">
	<table style="width:2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th rowspan="2" style="width:20px;height:40px">序号</th>
			<th rowspan="2" style="width:40px;height:40px">操作</th>
			<th rowspan="2" style="width:40px;height:40px">网店</th>
			<th rowspan="2" style="width:40px;height:40px">方案</th>
			<th rowspan="2" style="width:60px;height:40px">政策代码</th>
			<th rowspan="2" style="width:100px;height:40px">外部单号</th>
			<th rowspan="2" style="width:60px;height:40px">改签类型</th>
			<th rowspan="2" style="width:60px;height:40px">改签状态</th>
			<th rowspan="2" style="width:80px;height:40px">乘机人</th>
			<th rowspan="2" style="width:200px;height:40px">票号</th>
			<th rowspan="2" style="width:60px;height:40px">航程</th>
			<th colspan="4" width="200px">改签前航段信息</th>
			<th colspan="4" width="200px;height:20px">改签后航段信息</th>
			<th rowspan="2" style="width:60px;height:40px">销售改签<br/>费用</th>
			<th rowspan="2" style="width:60px;height:40px">收款状态</th>
			<th rowspan="2" style="width:60px;height:40px">收款科目</th>
			<th rowspan="2" style="width:60px;height:40px">采购改签<br/>费用</th>
			<th rowspan="2" style="width:60px;height:40px">联系人</th>
			<th rowspan="2" style="width:80px;height:40px">联系电话</th>
			<th rowspan="2" style="width:80px;height:40px">申请人/时间</th>
		</tr>
		<tr>
			<th style="width:60px;height:20px">PNR</th>
			<th style="width:60px;height:20px">航班号</th>
			<th style="width:20px;height:20px">舱位</th>
			<th style="width:60px;height:20px">起飞时间</th>
			<th style="width:60px;height:20px">PNR</th>
			<th style="width:60px;height:20px">航班号</th>
			<th style="width:20px;height:20px">舱位</th>
			<th style="width:60px;height:20px">起飞时间</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td	 class="td_center">{{ i+1 }}</td>
				<td  class="td_center">
                	{{#
                   		var zt = $.nullToEmpty(d.list[i].gqzt);
  						if (zt == '3' || zt == '1' ) {
					}}
					<a href="##" onclick="transact('{{ $.nullToEmpty(d.list[i].gqdh) }}')">改</a>
					{{#
  						} else {
					}}
					改
					{{#
						}
					}}

					{{# 
  						if(zt == '1') {
					}}
					<a href="##" onclick="transact('{{ $.nullToEmpty(d.list[i].gqdh) }}')">消</a>
					{{#
  						} else {
					}}
					消
					{{#
						}
					}}
				</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].ex.WDID.wdmc) }}</td>
				<td  class="td_left">{{  $.nullToEmpty(d.list[i].faid) }}</td>
				<td  class="td_left">{{  $.cut(d.list[i].wdZcdm, 6) }}</td>
				<td  class="td_left">{{ $.cut(d.list[i].wbdh, 12) }}</td>
				<td  class="td_center">
					{{#  
						var gqlx = $.nullToEmpty(d.list[i].gqlx);
						if (gqlx == '1') {
					}}
						改期
					{{#
						} else {
					}}
						升舱
					{{#
						}
					}}
				</td>
				<td  class="td_center">
					{{  $.findJson(JPGQZT,d.list[i].gqzt).mc }}
				</td>
				<td  class="td_left">{{  $.nullToEmpty(d.list[i].cjr) }}</td>
				<td  class="td_left">
					{{# for(var j = 0, cjrlen = d.list[i].cjrList.length; j < cjrlen; j++){ }}
						{{  $.nullToEmpty(d.list[i].cjrList[j].tkno) }}
						{{# 
							var gqTkno = $.nullToEmpty(d.list[i].cjrList[j].gqTkno);
							if(gqTkno != "") {
						}}
								-->
							{{  $.nullToEmpty(d.list[i].cjrList[j].gqTkno) }}
						{{#
							}
						}}
						<br/>
					{{# } }}
				</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].hc) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].xsPnrNo) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].xsHbh) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].xsCw) }}</td>
				<td  class="td_center">{{  $.dateF(d.list[i].cfrq,'MM-dd HH:mm') }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].gqXsPnrNo) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].gqXsHbh) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].gqXsCw) }}</td>
				<td  class="td_center">{{  $.dateF(d.list[i].gqCfrq,'MM-dd HH:mm') }}</td>
				<td  class="td_right">{{  $.nullToEmpty(d.list[i].gqXsfy) }}</td>
				<td  class="td_center">
					{{#  
						var skzt = $.nullToEmpty(d.list[i].skzt);
						if (skzt == '0') {
					}}
						未收款
					{{#
						} else {
					}}
						已收款
					{{#
						}
					}}
				</td>
				<td  class="td_left">{{  $.nullToEmpty(d.list[i].ex.SKKM.kmmc) }}</td>
				<td  class="td_right">{{  $.nullToEmpty(d.list[i].gqCgfy) }}</td>
				<td  class="td_left">{{  $.nullToEmpty(d.list[i].nxr) }}</td>
				<td  class="td_left">{{  $.nullToEmpty(d.list[i].nxdh) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].ddyh) }}<br/>{{  $.dateF(d.list[i].ddsj,'MM-dd HH:mm') }}</td>
    		</tr>
		{{# } }}
	</table>
	</div>
</script>
</head>
<body onload="initPage()">
<!--出票控制台改签状态页签 -->
<%@include file="list_title.jsp"%>
<div class="container">
  	  	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/jpgqgl/jpgqd/cancelGqd" id="gqdCancelForm" name="gqdCancelForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1"/>
            				<input type="hidden" id="id" name="id" value="" />
            			</form>
            			<form action="${ctx}/vedsb/jpgqgl/jpgqd/query" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${ param.pageNum }" id="pageNum"/>
            				<input type="hidden"  name="gqzt" value="${ empty param.gqzt ? 1 : param.gqzt }" id="gqzt"/>
            				<input type="hidden"  name="gngj" value="${not empty param.gngj ? param.gngj : '1' }" />
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              					<tr>
              						<td class="gqd_right">
              							申请日始
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" style="width:85px"  class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxdate\')}'})"/>
              						</td>
              						<td class="gqd_right">
              							申请日止
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="jsrq" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" style="width:85px"   class="input-text Wdate" size="10" id="maxdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'mindate\')}'})"/>
              						</td>
              						<td class="gqd_right">
              							PNR
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="xsPnrNo" value="${param.xsPnrNo }" onblur="value=$.trim(this.value).toUpperCase();" style="width:85px"  class="input-text" maxlength="6" />
              						</td>
              						<td class="gqd_right">
              							票号
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="tkno" value="${param.tkno }" style="width:85px"    class="input-text" />
              						</td>
              						<td class="gqd_right">
              							乘机人
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="cjr" value="${param.cjr }" style="width:85px"  class="input-text" />
              						</td>		
              					<tr>
              						<td class="gqd_right">
              							网店
              						</td>
              						<td class="gqd_left">
              							<select name="wdid" class="select" style="width:85px">
              								<option value="">==全部==</option>
              								<c:forEach items="${wdzlszList}" var="wdid">	
					                  	 		<option value="${wdid.id }" ${param.wdid eq wdid.id ? 'selected' : '' }>${wdid.wdmc }</option> 
					                  		</c:forEach>
              							</select>
              						</td>
              						<td class="gqd_right">
              							外部单号
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="wbdh" value="${param.wbdh }"  style="width:85px"  class="input-text" size="10" />
              						</td>	
									<td class="gqd_right">
              							联系人
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="nxr" value="${param.nxr }"  style="width:85px"  class="input-text" size="10" />
              						</td>
              						<td class="gqd_right">
              							联系电话
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="nxsj" value="${param.nxsj }" style="width:85px"   class="input-text" size="10" />
              						</td>
              						<td></td>
									<td class="gqd_left">
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