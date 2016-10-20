<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>改签管理</title>
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
<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
<script type="text/javascript">
    JPGQZT = '${vfn:toJSON(vfn:dictList('JPGQZT'))}';
	$(function(){
		//联系电话不为空则将开始日期向前调一周
		if($("#nxdh").val()!=""){
			var n=new Date();
			n.setDate(n.getDate()-7);
			$('input[name="ksrq"]').val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
			//$("#searchFormButton").click();
		}
	});

	function detail(gqdh) {
		window.open("${ctx}/vedsb/jpgqgl/jpgqd/detail?gqdh=" + gqdh);
	}
	
	//审核
	function review(gqdh) {
		window.open("${ctx}/vedsb/jpgqgl/jpgqd/review?forward=review&gqdh=" + gqdh);
	}
	
	
	//退票单详
	function detailGqd(gqdh){
	//弹出取消页面 
	    var url="${ctx}/vedsb/jpgqgl/jpgqd/detail_"+gqdh;
	    window.open(url);
	}
	
	//正常订单详
	function detail(id){
		var url = "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
	
	//改签
	function transact(gqdh) {
		window.open("${ctx}/vedsb/jpgqgl/jpgqd/transact?forward=transact&gqdh=" + gqdh);
	}
	
	function apply() {
		window.open("${ctx}/vedsb/jpgqgl/jpgqdapply/viewapplylist");
	}
	
	function getQzxx(gqdh) {
		var url ="${ctx}/vedsb/jpgqgl/jpgqd/viewgqd_qzxx_" + gqdh;
	    $.layer({
			type: 2,
			title: ['<b>签注</b>'],
			area: ['800px', '480px'],
			iframe: {src: url}
	    });
	}
	
	function enterLogPage(gqdh) {
		var url ="${ctx}/vedsb/jpgqgl/jpgqdczrz/enterLogPage_" + gqdh;
	    $.layer({
			type: 2,
			title: ['<b>改签单异动日志</b>'],
			area: ['800px', '480px'],
			iframe: {src: url}
	    });
	}
	
	function cancel(gqdh) {
  		var url = "${ctx}/vedsb/jpgqgl/jpgqd/cancelGqd?xgly=fromGqgl&gqdh="+gqdh;
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
	function  gqztColor(gqzt,gqztmc){
        //改签状态0待确认,1已确认,3改签中,4已改签,7客户消,8已取消
        if(gqzt == '4'){
            return "<font color='green'>"+gqztmc+"</font>";
        }else if(gqzt == '7' || gqzt =='8'){
            return "<font color='red'>"+gqztmc+"</font>";
        }else{
            return "<font color='blue'>"+gqztmc+"</font>";
        }	   
	}
</script>
<script id="tpl_list_table" type="text/html">
	<div>
	<table style="width:2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th rowspan="2" style="width:20px;height:40px">序号</th>
			<th rowspan="2" style="width:80px;height:40px">操作</th>
			<th rowspan="2" style="width:40px;height:40px">网店</th>
			<th rowspan="2" style="width:40px;height:40px">方案</th>
			<th rowspan="2" style="width:60px;height:40px">政策代码</th>
			<th rowspan="2" style="width:70px;height:40px">外部改单号</th>
			<th rowspan="2" style="width:70px;height:40px">外部订单号</th>
			<th rowspan="2" style="width:50px;height:40px">改签类型</th>
			<th rowspan="2" style="width:50px;height:40px">改签状态</th>
			<th rowspan="2" style="width:60px;height:40px">乘机人</th>
			<th rowspan="2" style="width:160px;height:40px">票号</th>
			<th rowspan="2" style="width:60px;height:40px">航程</th>
			<th colspan="4" width="200px;height:20px">改签前航段信息</th>
			<th colspan="4" width="200px;height:20px">改签后航段信息</th>
			<th rowspan="2" style="width:60px;height:40px">销售升舱费</th>
			<th rowspan="2" style="width:60px;height:40px">销售改签费</th>
			<th rowspan="2" style="width:60px;height:40px">收款状态</th>
			<th rowspan="2" style="width:60px;height:40px">收款科目</th>
			<th rowspan="2" style="width:60px;height:40px">采购升舱费</th>
			<th rowspan="2" style="width:60px;height:40px">采购改签费</th>
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
						var gqdh=$.nullToEmpty(d.list[i].GQDH);
                        var ztsm="";
						<c:if test="${!empty param.tlx and param.tlx=='3'}">
							ztsm+="<a href='###' onclick=cancel('"+gqdh+"')>消</a>&nbsp;";
						</c:if>
						<c:if test="${empty param.tlx}">
                   			var zt = $.nullToEmpty(d.list[i].GQZT);
							if (zt == '0') {
								ztsm+="<a href='###' onclick=review('"+gqdh+"')>审</a>&nbsp;";							
							}else if (zt == '3' || zt == '1' ) {
								ztsm+="<a href='###' onclick=transact('"+gqdh+"')>改</a>&nbsp;";
  							} else {
								ztsm+="改&nbsp;";
							}
  							if(zt == '1') {
								ztsm+="<a href='###' onclick=cancel('"+gqdh+"')>消</a>&nbsp;";
  							} else {
								ztsm+="消&nbsp;";
							}
							ztsm+="<a href='###' onclick=detailGqd('"+gqdh+"')>详</a>&nbsp;";
						</c:if>
						var qzcount = $.nullToEmpty(d.list[i].QZCOUNT);
						if(qzcount != 0 ){
							ztsm+="<a href='###' onclick=getQzxx('"+gqdh+"')><img src='/static/images/zicon.gif' title='点击查看签注信息'></a>&nbsp;";
						}else{
							ztsm+="<a href='###' onclick=getQzxx('"+gqdh+"')>注</a>&nbsp;";
						}
					}}
					{{ztsm}}
				</td>
				<td  class="td_center">{{$.cut(d.list[i].EX.WDID.wdmc, 2)}}</td>
				<td  class="td_left">{{  $.nullToEmpty(d.list[i].FAID) }}</td>
				<td  class="td_left">{{  $.cut(d.list[i].WD_ZCDM, 8) }}</td>
				<td  class="td_left"><a href="###" style="text-decoration: NONE" onclick="detailGqd('{{d.list[i].GQDH}}')">{{  $.cut(d.list[i].WBDH, 12) }}</a></td>
				<td  class="td_left"><a href="###" style="text-decoration: NONE" onclick="detail('{{d.list[i].DDBH}}')">{{  $.cut(d.list[i].WBDDBH, 12) }}</a></td>
				<td  class="td_center">
					{{#  
						var gqlx = $.nullToEmpty(d.list[i].GQLX);
                        var gqsm = gqlx == '1' ? "改期":"升舱";
					}}{{gqsm}}
				</td>
				<td  class="td_center">
					{{gqztColor($.nullToEmpty(d.list[i].GQZT),$.findJson(JPGQZT,d.list[i].GQZT).mc)}}
				</td>
				<td  class="td_left">{{  $.cut(d.list[i].CJR, 4) }}</td>
				<td  class="td_center">
					{{# for(var j = 0, cjrlen = d.list[i].CJRLIST.length; j < cjrlen; j++){ }}
						{{# 
                            var tkno = $.nullToEmpty(d.list[i].CJRLIST[j].tkno);
							var gqTkno = $.nullToEmpty(d.list[i].CJRLIST[j].gqTkno);
							if(tkno != gqTkno && gqTkno != "") {
								tkno+="-><font color='green'>"+gqTkno+"</font>"; 
							}
						}}
						{{tkno}}<br/>
					{{# } }}
				</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].HC) }}</td>
				<td  class="td_center"><a href="###" class="copytext" copytext="{{  $.nullToEmpty(d.list[i].XS_PNR_NO) }}"  onclick="enterLogPage('{{ $.nullToEmpty(d.list[i].GQDH) }}')">{{  $.nullToEmpty(d.list[i].XS_PNR_NO) }}</a></td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].XS_HBH) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].XS_CW) }}</td>
				<td  class="td_center">{{  $.dateF(d.list[i].CFRQ,'MM-dd') }}<br/>{{ $.nullToEmpty(d.list[i].CFSJ) }}</td>
				<td  class="td_center"><a href="###" class="copytext" copytext="{{  $.nullToEmpty(d.list[i].GQ_XS_PNR_NO) }}">{{  $.nullToEmpty(d.list[i].GQ_XS_PNR_NO) }}</a></td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].GQ_XS_HBH) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].GQ_XS_CW) }}</td>
				<td  class="td_center">{{  $.dateF(d.list[i].GQ_CFRQ,'MM-dd') }}<br/>{{ $.nullToEmpty(d.list[i].GQ_CFSJ) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].GQ_XSSCFY) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].GQ_XSFY) }}</td>
				<td  class="td_center">
					{{#  
						var skzt = $.nullToEmpty(d.list[i].SKZT);
                        var skztsm= skzt == '0'? "未收款" : "已收款";
					}}{{skztsm}}
				</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].EX.SKKM.kmmc) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].GQ_CGSCFY) }}</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].GQ_CGFY) }}</td>
				<td  class="td_left">{{  $.nullToEmpty(d.list[i].NXR) }}</td>
				<td  class="td_left">
					{{#
						var nxdh = $.nullToEmpty(d.list[i].NXDH);
						if (nxdh == "") {
					}}
							{{  $.nullToEmpty(d.list[i].NXSJ) }}
					{{#
						} else {
					}}
							{{  $.nullToEmpty(d.list[i].NXDH) }}
					{{#
						}
					}}
				</td>
				<td  class="td_center">{{  $.nullToEmpty(d.list[i].DDYH) }}<br/>{{  $.dateF(d.list[i].DDSJ,'MM-dd HH:mm') }}</td>
    		</tr>
		{{# } }}
	</table>
	</div>
</script>
</head>
<body>
	<c:if test="${!empty param.tlx and param.tlx == '3'}">
		<!--呼叫中心页签 -->
		<%@include file="/WEB-INF/views/callcenter/ddxx/list_title.jsp"%>
	</c:if>
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
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
            				<input type="hidden"  name="gngj" value="${not empty param.gngj ? param.gngj : '1' }" />
            				<input type="hidden"  name="nxdh" value="${param.nxdh}" id="nxdh" />
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
              							<input type="text" name="xsPnrNo" value="${param.xsPnrNo }" style="width:85px" onblur="value=$.trim(this.value).toUpperCase();" class="input-text" size="6" />
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
              							网&nbsp;&nbsp;店
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
              							政策代码
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="wdZcdm" value="${param.wdZcdm }" style="width:85px"   class="input-text"  />
              						</td>
              						<td class="gqd_right">
              							外部改单号
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
              						
								</tr>
								<tr>
									<td class="gqd_right">
              							改签状态
              						</td>
              						<td class="gqd_left">
              							<select name="gqzt" class="select" style="width: 87px;"> 
              							 	<option value="" selected>==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('JPGQZT')}" var="oneZt">
					                        	<option value="${oneZt.value}" ${oneZt.value eq param.gqzt ? 'selected' : '' }>${oneZt.mc}</option>
						                   	</c:forEach>
					                     </select>
              						</td>
              						<td class="gqd_right">
              							改签类型
              						</td>
              						<td class="gqd_left">
              							<select name="gqlx" class="select" style="width:87px">
              								<option value="">==全部==</option>
              								<option value="1" ${"1" eq param.gqlx ? 'selected' : '' }>改期</option>
              								<option value="2" ${"2" eq param.gqlx ? 'selected' : '' }>升舱</option>
              							</select>
              						</td>
              						<td class="gqd_right">
              							外部订单号
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="wbddbh" value="${param.wbddbh}"  style="width:85px"  class="input-text" size="10" />
              						</td>	
              						<td></td>
									<td colspan="2" align="right">
										<input type="button" name="button" onclick="apply()" value="改签申请" class="ext_btn ext_btn_submit" />
									</td>
									<td align="left">
										&nbsp;&nbsp;&nbsp;
										<input type="button" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit" />
									</td>
								</tr>
              				</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
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