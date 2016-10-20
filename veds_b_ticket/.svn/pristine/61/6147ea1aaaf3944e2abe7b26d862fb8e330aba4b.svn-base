<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>

<style>
	.input: {
		width: 85px;
	}
	
	.text_right {
		text-align: right;
	}
	
	.text_left {
		text-align: left;
	}
	a{ 
		text-decoration: none;
	}
</style>
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/contextmenu/jquery.contextMenu.css"></link>
<script type="text/javascript" src="${ctx}/static/js/contextmenu/jquery.contextMenu.js"></script>
<script type="text/javascript" src="${ctx}/static/core/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/static/js/tpd/operate_tpd.js"></script>
<script type="text/javascript" src="${ctx}/static/js/tpd/tpd.js"></script>
<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
<script type="text/javascript">
    XSTFPZT = '${vfn:toJSON(vfn:dictList('XSTFPZT'))}';
    CGTFPZT = '${vfn:toJSON(vfn:dictList('CGTFPZT'))}';
    
	$(function(){
		//联系电话不为空则将开始日期向前调一周
		if($("#nxdh").val()!=""){
			var n=new Date();
			n.setDate(n.getDate()-7);
			$('input[name="ksrq"]').val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
		}
		$("#tpdBatchXsWcButton").hide();
        $("#tpdBatchCgWcButton").hide();
		//$("#searchFormButton").click();
	});

   function loadTitleContent(lx) {
        $("#lx").val(lx);
        $("#tpdBatchXsWcButton").hide();
        $("#tpdBatchCgWcButton").hide();	
        if(lx == "3"){
             $("#tpdBatchXsWcButton").show();
        }else if(lx == "4"){
             $("#tpdBatchCgWcButton").show();
        }
	    $("#titlePage").load("${ctx}/vedsb/jptpgl/jptpd/viewlist_title?ajax=true&lx=" + lx); 
	    $("#searchFormButton").click();
	}
	
   //按不同日期类型查询
   function changeRqtj(rqtj){
        var ksrq_txt="申请日始";
        var jsrq_txt="申请日止";
	    if(rqtj == "2"){
			ksrq_txt="起飞日始";
			jsrq_txt="起飞日止";
		}else if(rqtj == "3"){
			ksrq_txt="采办日始";//最晚采购办理日始
			jsrq_txt="采办日止";//最晚采购办理日止
		}
		$("#td_ksrq").html(ksrq_txt);
		$("#td_jsrq").html(jsrq_txt);
	}

	
	//正常订单详
	function detail(id){
		var url = "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
	
    function tpdApply(){
        var url="${ctx}/vedsb/jptpgl/jptpdapply/viewapply";
	    window.location.href=url;
    }
    

    //航信退票
    function hxtp(tpdh,printno){
        var url="${ctx}/vedsb/jptpgl/jptpdhxtp/getTicket_"+tpdh;
	    window.open(url);
    }

    //查看异动日志
	function enterLogPage(tpdh){
		var url="${ctx}/vedsb/jpddgl/jpkhddczrz/enterLogPage_"+tpdh+"?ywlx=02";
		$.layer({
			type: 2,
			title: ['<b>订单异动日志</b>'],
			area: ['650px', '360px'],
			iframe: {src: url}
	    });
	}
	
    function cancelSeat(tpdh){
       if(window.confirm("确认会将此退票单标识为已取消座位，请认真核对座位已取消，是否继续？")){
           var url="${ctx}/vedsb/jptpgl/jptpdcg/cancelSet";
	       $.ajax({
	       	 		type:"post",
	 				url:url,
	 				data:{tpdh:tpdh},
	 				success:function(data){
	 					 layer.alert(data);	
	 				}
	       	 	});
		   }
    }
    
   function getlx(){
     return $("#lx").val();
   }
</script>
<script id="tpl_list_table" type="text/html">
<div>
	<table width="2000px" border="0" name="jptpd_table" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th rowspan="2" style="width:20px;height:40px">序号</th>
			<th rowspan="2" style="width:30px;height:40px">
 				<input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);">
			</th>
			<th rowspan="2" style="width:80px;height:40px">操作</th>
			<th rowspan="2" style="width:71px;height:40px">申请时间</th>
			<th rowspan="2" style="width:80px;height:40px">网店</th>
			<th rowspan="2" style="width:50px;height:40px">方案</th>
			<th rowspan="2" style="width:80px;height:40px">外部退单号</th>
			<th rowspan="2" style="width:80px;height:40px">外部订单号</th>
			<th rowspan="2" style="width:80px;height:40px">销售/采购<br>是否自愿</th>
            <th rowspan="2" style="width:80px;height:40px">政策代码</th>
            <th rowspan="2" style="width:60px;height:40px">客户<br>状态</th>
			<th rowspan="2" style="width:60px;height:40px">采购<br>状态</th>
			<th rowspan="2" style="width:60px;height:40px">退票凭证<br>提交状态</th>
			<th rowspan="2" style="width:100px;height:40px">PNR</th>
			<th rowspan="2" width="80px">自动退票状态</th>	
			<th rowspan="2" style="width:80px;height:40px">大编码</th>
			<th rowspan="2" style="width:95px;height:40px">票号</th>
			<th rowspan="2" style="width:50px;height:40px">乘机人</th>
			<th rowspan="2" style="width:50px;height:40px">航程</th>
			<th rowspan="2" style="width:100px;height:40px">航班号</th>
			<th rowspan="2" style="width:50px;height:40px">舱位</th>
			<th rowspan="2" style="width:77px;height:40px">起飞时间</th>
			<th rowspan="2" style="width:70px;height:40px">最晚采购<br>办理时间</th>
			<th rowspan="2" style="width:50px">采购</br>来源</th>
			<!--<th rowspan="2" style="width:50px">采购办理方式</th>-->
            <th colspan="7" style="width:480px;height:30px">客户退款</th>
            <th colspan="8" style="width:480px;height:30px">采购退款</th>
            <th rowspan="2" style="width:84px;height:40px">退废单号</th>
            <th rowspan="2" style="width:65px;height:40px">联系人</th>
            <th rowspan="2" style="width:115px;height:40px">联系电话</th>
		</tr>
		<tr>
			<th width="50px">账单价</th>
			<th width="50px">销售价</th>
            <th width="50px">机建</th>
            <th width="50px">税费</th>
            <th width="50px">退票费</th>
            <th width="80px">应退金额</th>
 			<th style="width:55px;height:40px">退款科目</th>
			<th width="50">账单价</th>
			<th width="50">采购价</th>
            <th width="50px">机建</th>
            <th width="50px">税费</th>
            <th width="50">退票费</th>
            <th width="80px">应退金额</th>
            <th width="80px">实退金额</th>
			<th style="width:55px;height:40px">退款科目</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center">{{ i+1 }}</td>
                <td width="25" align="center">	
						<input type="checkbox" name="onechkx"  sortnum="{{i+1}}"  
							   value="{{$.nullToEmpty(d.list[i].TPDH)}}"
				    		   cg_tpzt="{{$.nullToEmpty(d.list[i].CG_TPZT)}}"
							   cgly="{{$.nullToEmpty(d.list[i].CGLY)}}"
							   cg_sfzytp = "{{$.nullToEmpty(d.list[i].CG_SFZYTP)}}";
						>
				</td>
				<td class="td_center">
					<img src="${ctx}/static/images/op/operate.gif" class="opratorBtn_tpd"
 						menuid='{{$.nullToEmpty(d.list[i].TPDH)}}' 
					    menuczlx='{{getlx()}}'
					    menuxszt='{{$.nullToEmpty(d.list[i].XS_TPZT)}}'
					    menucgzt='{{$.nullToEmpty(d.list[i].CG_TPZT)}}'
						menucgly='{{$.nullToEmpty(d.list[i].CGLY)}}'
					/>
				</td>
				<td class="td_center">{{  $.dateF(d.list[i].DDSJ,'MM-dd HH:mm') }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].EX.WDID.wdmc) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].FAID ) }}</td>
				<td class="td_center"><a href="###" style="text-decoration: NONE" onclick="detailTpd('{{d.list[i].TPDH}}')">{{$.cut(d.list[i].WBDH, 10)}}</a></td>
				<td class="td_center"><a href="###" style="text-decoration: NONE" onclick="detail('{{d.list[i].DDBH}}')">{{$.cut(d.list[i].WBDDBH, 10)}}</a></td>
                <td class="td_center">
                 {{#  
					 var xs_sfzytp = $.nullToEmpty(d.list[i].XS_SFZYTP);
                     var cg_sfzytp = $.nullToEmpty(d.list[i].CG_SFZYTP);
                     var zytpmc= (xs_sfzytp == "0") ? "<font color='red'>非自愿</font>":"<font color='green'>自愿</font>";
                       zytpmc += (cg_sfzytp == "0") ? "/<font color='red'>非自愿</font>":"/<font color='green'>自愿</font>";
                    
                 }}
                 {{zytpmc}}
                </td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].WD_ZCDM) }}</td>
                <td class="td_center">{{  $.findJson(XSTFPZT,d.list[i].XS_TPZT).mc}}</td>
                <td class="td_center">{{  $.findJson(CGTFPZT,d.list[i].CG_TPZT).mc}}</td>
                <td class="td_center">
                 {{#  
                     var cg_tppz_tjzt = $.nullToEmpty(d.list[i].CG_TPPZ_TJZT);
                     var sfzytp = $.nullToEmpty(d.list[i].CG_SFZYTP);
                     var pzms="";
                     if(cg_tppz_tjzt == "1"){
                          pzms = "<font color='green'>已提交</font>";
                     }else{
                        if(sfzytp == "0"){
                          pzms = "<font color='red'>未提交</font>";
						}
					 }
                 }}
                 {{pzms}}
                </td>
				<td class="td_center">
					{{#
							var xspnrno=$.nullToEmpty(d.list[i].XS_PNR_NO);
							var cgpnrno=$.nullToEmpty(d.list[i].CG_PNR_NO);
                            var pnrno="<span class='copytext' copytext="+xspnrno+">"+xspnrno+"</span>";
                            if(xspnrno!=cgpnrno && cgpnrno != ''){
                                 pnrno=pnrno+"-><span class='copytext' copytext="+cgpnrno+"><font color=green>"+cgpnrno+"</font></span>";
                            }
					}}
                     <a href="###" onclick="enterLogPage('{{d.list[i].TPDH}}');">{{pnrno}}</a>
				</td>
          		<td class="td_center">
                    <a href="###" style="text-decoration:none;" title="点击查看详细日志" onclick="showZdtpLog('{{d.list[i].ZDTPJK_ID}}','{{d.list[i].CG_PNR_NO}}');">
					{{#
						var tpzt = $.nullToEmpty(d.list[i].TPZT);
                        var tpztsm="";
						if(tpzt == 0){
							tpztsm="初始状态";
                        }else if(tpzt == 1){
							tpztsm = "<font color='green'>自动退票成功</font>";
						}else if(tpzt == 2){
							tpztsm = "<font color='red'>自动退票失败</font>";
						} else if(tpzt == 3){
							tpztsm = "<font color='blue'>退票中</font>";
						}else if(tpzt == 4){
							tpztsm = "<font color='green'>手动退票成功</font>";
						}
					}}
                    {{tpztsm}}
                    </a>
                </td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_HKGS_PNR) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].TKNO).replace(/\,/g,'<br>') }}</td>
				<td class="td_center">{{  $.cut(d.list[i].CJR,2) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HC) }}</td>
				<td class="td_center">
				 {{#  
 						var xs_hbh=$.nullToEmpty(d.list[i].XS_HBH);
						var cg_hbh=$.nullToEmpty(d.list[i].CG_HBH); 
                        if(xs_hbh != cg_hbh){
                            xs_hbh += "-><font color='green'>"+cg_hbh+"</font>";
                        }
				 }}{{xs_hbh}}
                 <span id="hbyd{{d.list[i].TPDH}}">
			     {{#
					getHbyd(d.list[i].TPDH)
			     }}
			     </span></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_CW) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CFRQ,'MM-dd HH:mm') }}</td>
				<td class="td_center"
				{{#  
                    var now = new Date();
                    now = new Date(Date.parse(now.toLocaleDateString(),'yyyy/MM/dd'));
					var cg_zwblsj =new Date(Date.parse($.dateF(d.list[i].CG_ZWBLSJ,'yyyy/MM/dd'))); 
                    var days = cg_zwblsj.getTime() - now.getTime();
					var time = parseInt(days / (1000 * 60 * 60 * 24));
                    var bgcolor="";
                    if(time == 0){
                       bgcolor="background:red;";
					}else if(time == 1){
                       bgcolor="background:yellow;";
					}
                   
				 }} style="{{bgcolor}}">{{$.dateF(d.list[i].CG_ZWBLSJ,'MM-dd HH:mm')}}
				</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CGLY) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_ZDJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_PJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_JSF)}}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_TAX) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_TPSXF) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_TKJE) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].EX.XS_TKKM.kmmc) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CG_ZDJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CG_PJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CG_JSF) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CG_TAX) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CG_TPF) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CG_TKJE) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CG_STJE) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].EX.CG_TKKM.kmmc) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].TPDH) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].NXR) }}</td>
       			<td class="td_center">{{  $.nullToEmpty(d.list[i].NXDH) }}</td>
    		</tr>
		{{# } }}
	</table>
</div>
</script>
</head>
<body>
<c:if test="${empty param.tlx}">
	<!-- title -->
	<div id="titlePage">
		<%@include file="list_title.jsp"%>
	</div>
</c:if>
<c:if test="${!empty param.tlx and param.tlx == '2'}">
	<!--呼叫中心页签 -->
	<%@include file="/WEB-INF/views/callcenter/ddxx/list_title.jsp"%>
</c:if>
<div class="container_clear">
 	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/jptpgl/jptpd/query" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${empty param.pageNum ? 1 : param.pageNum}" id="pageNum"/>
							<input type="hidden"  name="orderBy" value="CG_ZWBLSJ asc" id="orderBy"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
							<input type="hidden"  name="lx"  value="${empty param.lx ? '2': param.lx}" id="lx"/> 
							<input type="hidden"  name="gngj" value="${empty param.gngj ? '1' : param.gngj }" id="gngj" />
							<input type="hidden"  name="nxdh" value="${param.nxdh}" id="nxdh" />
							<table  class="table01" border="0" cellpadding="0" cellspacing="0"> 

              				<!-- 第1行 -->
              					<tr>
              						<td class="text_right" id="td_ksrq">申请日始</td>
              						<td class="text_left">
              						    <%-- maxDate:'%y-%M-#{%d+1} --%>
              							<input type="text" name="ksrq" style="width:90px"  class="input-text Wdate" value="${vfn:dateShort()}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
              						</td>
              						<td class="text_right" id="td_jsrq">申请日止</td>
              						<td class="text_left">
              							<input type="text" name="jsrq" style="width:90px"  class="input-text Wdate" value="${vfn:dateShort()}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
              						</td>
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
              						<td class="text_right">政策代码</td>
              						<td class="text_left">
              							<input type="text" name="wd_zcdm" value="${param.wd_zcdm }" onblur="value=$.trim(this.value)" class="input-text lh25" size="10" />
              						</td>
              						<td class="text_right">外部退单号</td>
              						<td class="text_left">
              							<input type="text" name="wbdh" value="${param.wbdh }"  onblur="value=$.trim(this.value);"  class="input-text lh25" size="10" />
              						</td>
              						<td class="text_right">外部订单号</td>
              						<td class="text_left">
              							<input type="text" name="wbddbh" value="${param.wbddbh }"  onblur="value=$.trim(this.value);"  class="input-text lh25" size="10" />
              						</td>
              					</tr>
              					
              					<!-- 第2行 -->
              					<tr>
              					  <td class="text_right">
              							日期条件
              						</td>
              						<td class="text_left">
              							<select class="input1"  style="width:92px;height: 25px;" id="rqtj" name="rqtj" onchange="changeRqtj(this.value);">
              							  <option value="1">申请时间</option>
              							  <option value="2">起飞时间</option>
              							  <option value="3">最晚采购办理时间</option>
              							</select>
              						</td>
              						<td class="text_right">
              							P&nbsp;N&nbsp;R
              						</td>
              						<td class="text_left">
              							<input type="text" name="xs_pnr_no" value="${param.xs_pnr_no }"  maxlength="6" onblur="value=$.trim(this.value).toUpperCase();" style="width: 90px;"  class="input-text lh25" size="10" />
              						</td>
              						 <td class="text_right">
              							票&nbsp;&nbsp;号
              						</td>
              						<td class="text_left" >
              							<input type="text" class="input-text lh25" style="width: 98px" maxlength="14" onblur="value=$.trim(this.value);" value="${param.tkno}" name="tkno" /></td>
              						</td>
              						<td class="text_right">
              							航&nbsp;&nbsp;程
              						</td>
              						<td class="text_left">
              							<input type="text" name="hc" value="${param.hc }"  onblur="value=$.trim(this.value).toUpperCase();"  class="input-text lh25" size="10" />
              						</td>
              						<td class="text_right">航 班 号</td>
              						<td class="text_left">
              							<input type="text" name="xs_hbh" value="${param.xs_hbh }" title="输入两位查询航空公司，输入六位查询航班号"  onblur="value=$.trim(this.value).toUpperCase();"  class="input-text lh25" size="10" />
              						</td>
              						<td class="text_right">采购来源</td>
									<td class="text_left">
										<select name="cgly" style="width: 100px;height: 24px;" class="select" >
											<option value="" 'selected'}>==请选择==</option>
											<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
												<c:if test="${oneLx.id ne '10014'}">
													<option value="${oneLx.ywmc}" ${param.cgly eq oneLx.ywmc ? 'selected' : ''}>${oneLx.ywmc}</option>
												</c:if>
											</c:forEach>
										<select>
									</td>
              					</tr>
              					
              					
								<!-- 第3行 -->
								<tr>
								   <td class="text_right">乘 机 人</td>
              						<td class="text_left">
              							<input type="text" name="cjr" value="${param.cjr }" onblur="value=$.trim(this.value);"  style="width: 90px;" class="input-text lh25" size="10" />
              						</td>
									<td class="text_right">客户状态</td>
									<td class="text_left">
									<select style="width:92px;height: 25px;"  id="xs_tpzt" name="xs_tpzt"   >
										<option value="" selected>==全部==</option> 
              							<c:forEach items="${vfn:dictList('XSTFPZT')}" var="oneZt">
					                        <option value="${oneZt.value}">${oneZt.mc}</option>
						                </c:forEach>
									</select>
									</td>
									<td class="text_right">采购状态</td>
									<td class="text_left">
										<select  id="cg_tpzt" name="cg_tpzt" style="width: 100px;height: 24px;" class="select" >
											<option value="" selected>==全部==</option> 
	              							<c:forEach items="${vfn:dictList('CGTFPZT')}" var="oneZt">
						                        <option value="${oneZt.value}">${oneZt.mc}</option>
							                </c:forEach>
										</select>
									</td>
									<td class="text_right">是否自愿退票</td>
									<td>
										<select style="width:100px;height: 25px;" id="sfzytp" name="sfzytp" >
											<option value="" selected>==全部==</option> 
											<option value="1" >自愿</option> 
											<option value="0" >非自愿</option>
											<option value="2" >自愿转非自愿</option>  
										</select>
									</td>
									<td class="text_right">退票凭证提交状态</td>
									<td class="text_left">
										<select name="cg_tppz_tjzt" style="width: 100px;height: 24px;" class="select">
											<option value="" 'selected'}>==请选择==</option>
											<option value="0">未提交</option>
											<option value="1">已提交</option>
										<select>
									</td>
								</tr>
								
								<!-- 第4行 -->
								<tr>
									<td class="text_left" colspan="4">
										<input type="button" id="tpdApplyButton"       name="button" value="申请退票"         onclick="tpdApply();" class="ext_btn ext_btn_submit" />
										<input type="button" id="tpdBatchXsWcButton"   name="button" value="批量销售完成"     onclick="batchXsWc();" class="ext_btn ext_btn_submit" />
										<input type="button" id="tpdBatchCgWcButton"   name="button" value="批量采购完成"     onclick="batchCgWc();" class="ext_btn ext_btn_submit" />
										<input type="button" id="tpdBatchTppzTjButton" name="button" value="批量提交退票凭证" onclick="batchTppzTj();" class="ext_btn ext_btn_submit" />
									</td>
									<td class="text_right">退票状态</td>
									<td class="text_left" colspan="4">
										<input type="checkbox" name="tpzt" value="0" ${param.tpzt eq '0' ? 'checked': ''} id="chk_zt_0"> 
										<label for="chk_zt_0">初始状态</label> 
										<input type="checkbox" name="tpzt" value="1" ${param.tpzt eq '1' ? 'checked': ''} id="chk_zt_1">
										<label for="chk_zt_1">自动退票成功</label> 
										<input type="checkbox" name="tpzt" value="2" ${param.tpzt eq '2' ? 'checked': ''} id="chk_zt_2"> 
										<label for="chk_zt_2">退票失败</label> 
										<input type="checkbox" name="tpzt" value="3" ${param.tpzt eq '3' ? 'checked': ''} id="chk_zt_3">
										<label for="chk_zt_3">退票中</label>
										<input type="checkbox" name="tpzt" value="4" ${param.tpzt eq '4' ? 'checked': ''} id="chk_zt_4">
										<label for="chk_zt_4">手动退票成功</label>
									</td>
									<td colspan="5" class="text_right">
										<input type="button" id="searchFormButton" name="button" value=" 查 询  "  class="ext_btn ext_btn_submit" />
									</td>
								</tr>
              				</table>
              			</form>
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
        	</div>
		</div>
</body>
</html>