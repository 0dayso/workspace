<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>自动退票监控</title>
<style>
a {
	text-decoration: none;
}

.input: {
	width: 85px;
}

.text_right {
	text-align: right;
}

.text_left {
	text-align: left;
}

hr {
	height: 0px;
	border-top: 1px solid #EBEBEB;
	border-right: 0px;
	border-bottom: 0px;
	border-left: 0px;
}
</style>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js"></script>
<script type="text/javascript" src="${ctx}/static/js/tpd/tpd.js"></script>
<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
<script type="text/javascript">
	CGTFPZT = '${vfn:toJSON(vfn:dictList('CGTFPZT'))}';
	$(function(){
		//出票效率统计报表点击订单数查看详使用
		var cptjbbbs = '${param.cptjbbbs}';
		if(cptjbbbs != '' ){
			//清空预定开始日期、结束日期
			$("#gn_hkgs_m").val("${param.hkgs}");
			$("#gn_hkgs").val("${param.hkgs}");
			//cptjbbbs为0表示查看总订单数 1表示查看已匹配规则
			if(cptjbbbs == '0'){
				$("#chk_by3").attr("checked",false);
			}	
		}
	     //航司控件
    	 $("#gn_hkgs_m").autocompleteGnHkgs("gn_hkgs");	
    	 $("#searchFormButton").click();
	});
	
	//正常订单详
	function detailKhdd(id){
		var url = "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
			
	//退票订单详
	function detailTpd(tpdh){
		var url = "${ctx}/vedsb/jptpgl/jptpd/tpdDetail_"+tpdh;
		window.open(url);
	}
	
	//航司隐藏框写值
	function clearValue(obj,hkgs_id){
	   if(obj.value==""){
		   $("#"+hkgs_id).val("");
	   }
	}
	/* 
	*进入规则详
	*/
	function enterEditPage(gzbh){
		var url ="${ctx}/vedsb/cpsz/jpzdtfpgzsz/enterEditPage?id="+gzbh+"&flag=3";
		$.layer({
			type: 2,
			title: ['<b>自动废票规则</b>'],
			area: ['700px', '400px'],
			iframe: {src: url}
	    });
		
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
	
	
			
	//点击表头排序
	function sort(obj){
		var order=$("#orderBy");
		if(order.val().indexOf(obj)=='-1'){
			order.val(obj+" desc");
		}else {
			if(order.val().indexOf("desc")=='-1'){
				order.val(obj+" desc");
			}else{
				order.val(obj+" asc");
			}
		}
		$("#searchFormButton").click();
	}
	
	function  tpztColor(tpzt,tpztmc){
        //采购退票状态  0待提交，1提交中，2已提交，3已办理，4已拒单，9已取消
        if(tpzt == '3'){
            return "<font color='green'>"+tpztmc+"</font>";
        }else if(tpzt == '9' || tpzt == '4'){
            return "<font color='red'>"+tpztmc+"</font>";
        }else{
            return "<font color='blue'>"+tpztmc+"</font>";
        }	   
	}
</script>
<script id="tpl_list_table" type="text/html">
<div>
	<table width="2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="30px">序号</th>
			<th width="30px"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
			<th width="100px">退票单号</th>
            <th width="60px">外部单号</th>
			<th width="60px">网店名称</th>
			<th width="80px">方案</th>
			<th width="60px">政策代码</th>
			<th width="80px">产品类型</th>
			<th width="80px">采购来源</th>
			<th width="50px">采购退</br>票状态</th>
			<th width="80px">退票类型</th>
            <th width="100px">PNR</th>
			<th width="80px">自动退票状态</th>	
			<th width="80px">乘机人</th>
            <th width="120px">票号</th>
			<th width="80px">航程</th>
			<th width="100px">航班号</th>
			<th width="50px">舱位</th>
			<th width="80px"><a href="###" onclick="sort('a.CJSJ');">退票</br>开始时间</a></th>
			<th width="80px"><a href="###" onclick="sort('a.TPWCSJ');">退票</br>完成时间</a></th>
			<th width="80px">退票规则 </th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center">{{ i+1 }}</td>
				<td width="25" align="center">	
						<input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" sortnum="{{i+1}}"  
						tpzt="{{$.nullToEmpty(d.list[i].TPZT)}}" 
						wbdh="{{$.nullToEmpty(d.list[i].WBDH)}}"
						wdid="{{$.nullToEmpty(d.list[i].WDID)}}"
						value="{{$.nullToEmpty(d.list[i].TPDH)}}">
				</td>
				<td class="td_center"><a href="###" onclick="detailTpd('{{d.list[i].TPDH}}');">{{  $.nullToEmpty(d.list[i].TPDH ) }}</td>
                <td class="td_center" ><a href="###" onclick="detailKhdd('{{d.list[i].DDBH}}');">{{  $.nullToEmpty(d.list[i].WBDH ) }}</a></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].EX.WDID.wdmc ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].FAID ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].WDZCDM ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].WDZCLX ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CGLY) }}</td>
				<td class="td_center">
					{{tpztColor($.nullToEmpty(d.list[i].CG_TPZT),$.findJson(CGTFPZT,d.list[i].CG_TPZT).mc)}}
				</td>
				<td class="td_center">
					{{# 
						 var tplx =  $.nullToEmpty(d.list[i].TPLX );
                         var cg_bllx=$.nullToEmpty(d.list[i].CG_BLLX);
						 var tplxName = cg_bllx == '2' ? "<font color='red'>废票</font>":"";
					     if(tplx == '0'){ 
                              tplxName="<font color='red'>非自愿</font>";
                         }else if(tplx == '1'){ 
                              tplxName="<font color='green'>自愿</font>";
						 }
                          
					 }}
					{{tplxName}}
				</td>
                <td class="td_center">
					{{#
						var cgpnrno=$.nullToEmpty(d.list[i].CG_PNR_NO);
						var xspnrno=$.nullToEmpty(d.list[i].XS_PNR_NO);
                        var pnrsm = "<span class='copytext' copytext="+xspnrno+">"+xspnrno+"</span>";
						if(cgpnrno != xspnrno && cgpnrno != ''){
							pnrsm = pnrsm +"-><span class='copytext' copytext="+cgpnrno+"><font color=green>"+cgpnrno+"</font></span>";
						}
					}}
					<a href="###" onclick="enterLogPage('{{d.list[i].TPDH}}');">{{pnrsm}}</a>
				</td>
                <td class="td_center">
                    <a href="###" style="text-decoration:none;" title="点击查看详细日志" onclick="showZdtpLog('{{d.list[i].ID}}','{{d.list[i].CG_PNR_NO}}');">
					{{#
						var tpzt = $.nullToEmpty(d.list[i].TPZT);
						var now = new Date();
						var cjsj = new Date(Date.parse($.dateF(d.list[i].CJSJ,'yyyy/MM/dd HH:mm:ss')));  
                        var tpwcsj = new Date(Date.parse($.dateF(d.list[i].TPWCSJ,'yyyy/MM/dd HH:mm:ss')));  
					    var diff=0;
                        var tpztsm="";
						if(tpzt == 0){
							tpztsm="初始状态";
                        }else if(tpzt == 1){
                          	diff = parseInt((tpwcsj.getTime() - cjsj.getTime())/1000);
							tpztsm = "<font color='green'>自动退票成功</font>["+diff+"]";
						}else if(tpzt == 2){
                        	diff = parseInt((tpwcsj.getTime() - cjsj.getTime())/1000);
							tpztsm = "<font color='red'>自动退票失败</font>["+diff+"]";
						} else if(tpzt == 3){
                        	diff = parseInt((now.getTime() - cjsj.getTime())/1000);
							tpztsm = "<font color='blue'>退票中</font>["+diff+"]";
						}else if(tpzt == 4){
                        	diff = parseInt((tpwcsj.getTime() - cjsj.getTime())/1000);
							tpztsm = "<font color='green'>手动退票成功</font>["+diff+"]";
						}
					}}
                    {{tpztsm}}
                    </a>
                </td>
				<td class="td_center" id="td_{{i+1}}">{{  ($.nullToEmpty(d.list[i].CJR )).replace(/\,/g,"<hr>") }}</td>
                <td class="td_center">{{  ($.nullToEmpty(d.list[i].TKNO )).replace(/\,/g,"<hr>") }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HC ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HBH ) }} <span id="hbyd{{d.list[i].TPDH}}">
			     {{#
					getHbyd(d.list[i].TPDH)
			     }}
			     </span></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CW ) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CJSJ,'yyyy-MM-dd HH:mm')}}</td>
				<td class="td_center">{{  $.dateF(d.list[i].TPWCSJ,'yyyy-MM-dd HH:mm')}}</td>
				<td class="td_center"><a href="###" onclick="enterEditPage('{{d.list[i].GZID}}');">{{  $.nullToEmpty(d.list[i].GZID) }}</a></td>
    		</tr>
		{{# } }}
	</table>
</div>
</script>
</head>
<body onload="doLoad()">
	<c:set var="gngj" value="${empty param.gngj ? '1' : param.gngj }"></c:set>
	<!--网店页签 -->
	<%@include file="/WEB-INF/views/cpkzt/cpkzt/list_title.jsp"%>
	<!-- 查询条件区 -->
<div class="container">
  	  	<div id="search_bar">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
						<form name="listForm" action="${ctx}/vedsb/jptpgl/jpzdtpjk/query" id="searchForm" name="searchForm" method="post">
							<input type="hidden" name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" /> 
							<input type="hidden" name="pageNum" value="${param.pageNum }" id="pageNum" /> 
							<input type="hidden" name="tpfp" value="${param.tpfp }" id="tpfp" /> 
							<input type="hidden" name="orderBy" value="cjsj desc" id="orderBy" /> 
							<input type="hidden" name="pageSize" value="10" id="pageSize" />
							<input type="hidden" name="lx" value="${param.lx}" id="lx" /> 
							<input type="hidden" name="gngj" value="${empty param.gngj ? '1' : param.gngj }" id="gngj" />
							<table class="table01" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="text_right">退票日始</td>
									<td class="text_left"><input type="text" name="ksrq"
										style="width:110px" class="input-text Wdate"
										value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}"
										size="10" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>
									<td class="text_right">退票日止</td>
									<td class="text_left"><input type="text" name="jsrq"
										style="width:110px" class="input-text Wdate"
										value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}"
										size="10" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>

									<td class="text_right">采购来源</td>
									<td class="text_left">
										<select name="cgly" class="inputred" style="width:112px;height: 24px;">
											<option value=""'selected'}>==请选择==</option>
											<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
												<c:if test="${oneLx.id ne '10014'}">
													<option value="${oneLx.ywmc}"${param.cgly eq oneLx.ywmc ? 'selected' : ''}>${oneLx.ywmc}</option>
												</c:if>
											</c:forEach>
											<select>
									</td>
									<td class="text_right">退票单号</td>
									<td class="text_left">
										<input type="text" name="tpdh" class="input-text lh25" style="width:110px" value="${param.tpdh}"
										 onkeyup="value =value.toUpperCase();value=$.trim(this.value);" />
									</td>
									<td class="text_right">是否自愿退票</td>
									<td class="text_left">
										<select name="sfzytp" class="select" style="width:112px;height: 24px;">
											<option value="" 'selected'}>==请选择==</option>
											        <option value="1" >自愿</option>
													<option value="0" >非自愿</option>
													<option value="2" >自愿转非自愿</option>
										<select>
									</td>
								</tr>
								<tr>
									<td class="text_right">P N R</td>
									<td class="text_left">
										<input type="text" name="pnr" class="input-text lh25 UpperCase" value="${param.pnr}" size="8" style="width:110px"
										onblur="value=$.trim(this.value).toUpperCase();">
									</td>
									<td class="text_right">网&nbsp;&nbsp;店</td>
									<td class="text_left">
										<select name="wdid" id="wdid" style="width: 112px;height: 24px;" class="select">
											<option value="">==全部==</option>
											<c:forEach items="${jpfn:wdList(BUSER.shbh,param.gngj)}" var="wdBean">
												<option value="${wdBean.id}" ${param.wdid eq wdBean.id ? 'selected' : ''}>${wdBean.wdmc}</option>
											</c:forEach>
										</select>
									</td>

									<td class="text_right">航空公司</td>
									<td class="text_left">
										<input type="text" id="gn_hkgs_m" name="gn_hkgs_m" value="" class="input-text lh25" style="width:110px" size="10"
											   onchange="clearValue(this,'gn_hkgs');" /> 
										<input type="hidden" id="gn_hkgs" name='hkgs' value="">
									</td>
									<td class="text_right">航&nbsp;&nbsp;程</td>
									<td class="text_left">
									<input type="text" size="11" name="hc" class="input-text lh25 UpperCase" value="${param.hc}" style="width:110px"
										onkeyup="value =value.toUpperCase();value=$.trim(this.value);">
									</td>
								</tr>
								<tr>
									<td class="text_right">航 班 号</td>
									<td class="text_left">
										<input type="text" name="hbh" class="input-text lh25 UpperCase" style="width:110px" value="${param.hbh}"
										onkeyup="value =value.toUpperCase();value=$.trim(this.value);">
									</td>
									<td class="text_right">乘 机 人</td>
									<td>
										<input type="text" name="cjr" class="input-text lh25 UpperCase" style="width:110px" value="${param.cjr}" 
										onkeyup="value=$.trim(this.value);">
									</td>
									<td class="text_right">外部单号</td>
									<td class="text_left">
										<input type="text" name="wbdh" class="input-text lh25" style="width:110px" value="${param.wbdh}" />
									</td>
									<td class="text_right">采购状态</td>
									<td>
										 <select name="cgtfpzt" class="select" style="width: 112px;height: 24px;"> 
              							 	<option value="" selected>==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('CGTFPZT')}" var="oneZt">
					                        	<option value="${oneZt.value}">${oneZt.mc}</option>
						                   	</c:forEach>
					                     </select>
									</td>
									<td></td>
									<td class="text_right">
										<input type="button" id="searchFormButton" name="button" value=" 查 询 " class="ext_btn ext_btn_submit">
									</td>
								</tr>
								<tr>
									<td class="text_left" colspan="3">
										<input type="checkbox" name="zdtp_sffh" value="1" ${empty param.zdcp_sffh or param.zdcp_sffh eq '1'? 'checked': ''} id="chk_by3"> 
										<label for="chk_by3">只显示满足自动退票规则的订单</label>
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
								</tr>
								<tr>
									<td width="100px" align="left" colspan="3">
										<input type="button"value="批量手动退票" class="ext_btn ext_btn_submit" onclick="batchCgTp();">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="mt10" style="display:table;">
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