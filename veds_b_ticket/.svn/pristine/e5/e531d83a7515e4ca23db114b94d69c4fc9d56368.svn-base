<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<style>
  
	.input: {
		width:85px;
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
<script type="text/javascript" src="${ctx}/static/core/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
<script type="text/javascript">
    XSTFPZT = '${vfn:toJSON(vfn:dictList('XSTFPZT'))}';
    CGTFPZT = '${vfn:toJSON(vfn:dictList('CGTFPZT'))}';
    
     $(function(){
     	loadTitle();
     	//进入页面默认查询
		$("#searchFormButton").click();		
     });
     
      //采购提交
	function cgTjTpd(tpdh){
	       var url="${ctx}/vedsb/jptpgl/jptpd/tpdDetail_"+tpdh+"?forward=cgtj";
	       window.open(url);
    }
    
    //采购完成
	function cgWcTpdd(tpdh){
	       var url="${ctx}/vedsb/jptpgl/jptpd/tpdDetail_"+tpdh+"?forward=cgwc";
	       window.open(url);
    }
     
     function loadTitle(){
           $("#titlePage").load("${ctx}/vedsb/jptpgl/jptpdcpkzt/titlePage?ajax=true&"+$("#searchForm").serialize()); 
     }
     
     function setCgdw(yw_type,cgly,cgdw){
    	document.searchForm.yw_type.value = yw_type;
    	document.searchForm.cgly.value = cgly;
    	document.searchForm.cgdw.value = cgdw;
    	loadTitle();
    	$("#searchForm").pageSearch();
    	document.searchForm.yw_type.value = "";
    	document.searchForm.cgly.value = "";
    	document.searchForm.cgdw.value = "";
    	
    }
    
    
    //退票单详
	function detailTpd(tpdh){
	    var url="${ctx}/vedsb/jptpgl/jptpd/tpdDetail_"+tpdh;
	    window.open(url);
	}
	
	//签注
  	function getQzxx(ddbh){
  		var url ="${ctx}/vedsb/common/jpcommon/qzlist?ywdh="+ddbh+"&ywlx=01&from=list";
		   $.layer({
				type: 2,
				title: ['签注'],
				area: ['745px', '400px'],
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
    
</script>
<script id="tpl_list_table" type="text/html">
<div >
	<table width="2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th rowspan="2" width="20">序号</th>
			<th rowspan="2" width="100">操作</th>
			<th rowspan="2" width="110">申请<br>时间</th>
			<th rowspan="2" width="80">网店</th>
			<th rowspan="2" width="80">方案</th>
			<th rowspan="2" width="80">外部单号</th>
			<th rowspan="2" width="80">自愿退票</th>
            <th rowspan="2" width="80">政策代码</th>
            <th rowspan="2" width="80">客户<br>状态</th>
			<th rowspan="2" width="80">采购<br>状态</th>
			<th rowspan="2" width="80">PNR</th>
			<th rowspan="2" width="80">大编码</th>
			<th rowspan="2" width="150">票号</th>
			<th rowspan="2" width="80">乘机人</th>
			<th rowspan="2" width="80">航程</th>
			<th rowspan="2" width="80">航班号</th>
			<th rowspan="2" width="80">舱位</th>
			<th rowspan="2" width="110">起飞时间</th>
			<th rowspan="2" width="110">最晚采购<br>办理时间</th>
			<th rowspan="2" width="80">采购来源</th>
			<th rowspan="2" width="80">采购办理方式</th>
            <th colspan="6" width="480">客户退款</th>
            <th colspan="7" width="560">采购退款</th>
            <th rowspan="2" width="80">退款科目</th>
            <th rowspan="2" width="80">退废单号</th>
            <th rowspan="2" width="80">联系人</th>
            <th rowspan="2" width="80">联系电话</th>
		</tr>
		<tr>
			<th width="80">账单价</th>
			<th width="80">销售价</th>
            <th width="80">机建</th>
            <th width="80">税费</th>
            <th width="80">退票费</th>
            <th width="80">应退金额</th>
			<th width="80">账单价</th>
			<th width="80">采购价</th>
            <th width="80">机建</th>
            <th width="80">税费</th>
            <th width="80">退票费</th>
            <th width="80">应退金额</th>
            <th width="80">实退金额</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
                    {{#
                   		var zt = $.nullToEmpty(d.list[i].CG_TPZT);
  						if (zt == '0' || zt== "1" || zt=="4") {
					}}
						<a href="###"  onclick="cgTjTpd('{{d.list[i].TPDH}}')">提</a>&nbsp;
					{{#
  						} else {
					}}
						<a href="###"  onclick="cgWcTpdd('{{d.list[i].TPDH}}')">办</a>&nbsp;
					{{#
						}
					}}
                    {{#
						var qzcount = $.nullToEmpty(d.list[i].QZCOUNT);
						if(qzcount != 0 ){
					}}
						<a href="####" onclick="getQzxx('{{d.list[i].DDBH}}')"><img src='${ctx}/static/images/zicon.gif' title='点击查看签注信息' ></a>&nbsp;
					{{#
						}else{
					}}
					<a href="####" onclick="getQzxx('{{d.list[i].DDBH}}')">注</a>&nbsp;
					{{#
						}
					}}
				</td>
				<td class="td_center">{{  $.dateF(d.list[i].DDSJ,'MM-dd HH:mm') }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].EX.WDID.wdmc ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].FAID ) }}</td>
				<td class="td_center"><a href="###"  onclick="detailTpd('{{d.list[i].TPDH}}')">{{$.nullToEmpty(d.list[i].WBDH) }}</a></td>
                <td class="td_center">
                 {{#  
                     var sfzytp = $.nullToEmpty(d.list[i].CG_SFZYTP);
                     var zytpmc="";
                     if(sfzytp == "0"){
                          zytpmc = "<font color='red'>非自愿</font>";
                     }else if(sfzytp == "1"){
                          zytpmc = "<font color='green'>自愿</font>";
                     } 
                 }}
                 {{zytpmc}}
				<td class="td_center">{{  $.nullToEmpty(d.list[i].WD_ZCDM) }}</td>
                <td class="td_center">{{  $.findJson(XSTFPZT,d.list[i].XS_TPZT).mc}}</td>
                <td class="td_center">{{  $.findJson(CGTFPZT,d.list[i].CG_TPZT).mc}}</td>
				<td class="td_center">
                 <a href="###" onclick="enterLogPage('{{d.list[i].TPDH}}');">
				 {{#
							var xspnrno=$.nullToEmpty(d.list[i].XS_PNR_NO);
							var cgpnrno=$.nullToEmpty(d.list[i].CG_PNR_NO);
                            var pnrno="<span class='copytext' copytext="+xspnrno+">"+xspnrno+"</span>";
                            if(xspnrno != cgpnrno){
                                 pnrno=pnrno+"-><span class='copytext' copytext="+cgpnrno+"><font color=green>"+cgpnrno+"</font></span>";
                            }
				 }}
                 {{pnrno}}</a>
                </td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_HKGS_PNR) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].TKNO) }}</td>
				<td class="td_center">{{  $.cut(d.list[i].CJR,2) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HC) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_HBH) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_CW) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CFRQ,'MM-dd HH:mm') }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CG_ZWBLSJ,'MM-dd HH:mm') }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CGLY) }}</td>
				<td class="td_center"></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_ZDJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_PJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_JSF)}}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_TAX) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_TPSXF) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_TKJE) }}</td>
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
<!--出票控制台页签 -->
<%@include file="/WEB-INF/views/cpkzt/cpkzt/list_title.jsp" %>
<!-- title -->
<div class="container_clear">
 	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/jptpgl/jptpdcpkzt/query" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
							<input type="hidden"  name="orderBy" value="" id="orderBy"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
							<input type="hidden"  name="lx" value="${param.lx}" id="lx"/>
							<input type="hidden"  name="yw_type" value="${empty param.yw_type ? 'WBL': param.yw_type}" id="yw_type"/>
							<input type="hidden"  name="cgdw" value="${param.cgdw}" id="cgdw"/>
							<input type="hidden"  name="gngj" value="${empty param.gngj ? '1' : param.gngj }" id="gngj" />
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              				<!-- 第1行 -->
              					<tr>
              						<td class="text_right">
              							申请日始
              						</td>
              						<td class="text_left">
              							<input type="text" style="width:100px"  name="ksrq"  class="input-text Wdate"  value="${empty param.ksrq ? vfn:dateShort(): param.ksrq }"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
              						</td>
              						<td class="text_right">
              							申请日止
              						</td>
              						<td class="text_left">
              							<input type="text" style="width:100px"  name="jsrq"  class="input-text Wdate"  value="${vfn:dateShort()}"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
              						</td>
              						<td class="text_right">网&nbsp;&nbsp;店</td>
							        <td class="text_left">
							        	<select name="wdid" id="wdid"  style="width:100px;height: 25px;">
					                  	 	<option value="">==全部==</option>
					                  	 	<c:forEach items="${jpfn:wdList(BUSER.shbh,param.gngj)}" var="wdBean">
												<option value="${wdBean.id}" ${param.wdid eq wdBean.id ? 'selected' : ''}>${wdBean.wdmc}</option>
											</c:forEach>
				                  		</select>
							        </td>
              						<td class="text_right">
              							政策代码
              						</td>
              						<td class="text_left">
              							<input type="text" name="wd_zcdm" value="${param.wd_zcdm }"  class="input-text lh25"  size="10"/>
              						</td>
              						<td class="text_right">
              							外部单号
              						</td>
              						<td class="text_left">
              							<input type="text" name="wbdh" value="${param.wbdh }"  style="width:100px"  class="input-text lh25" size="10"/>
              						</td>
              						<td>采购来源</td>
              						<td>
              							<select name="cgly" class="inputred" style="width:90px;height: 25px;">
											<option value="" 'selected'}> =请选择= </option>
											<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
												<c:if test="${oneLx.id ne '10014'}">
													<option value="${oneLx.ywmc}" ${param.cgly eq oneLx.ywmc ? 'selected' : ''}>${oneLx.ywmc}</option>
												</c:if>
											</c:forEach>
										</select>
              						</td>
              					</tr>
              					
              					<!-- 第2行 -->
              					<tr>
              					  <td class="text_right">
              							日期条件
              						</td>
              						<td class="text_left">
              							<select  id="rqtj" name="rqtj" style="width:100px;height: 25px;">
              							  <option value="1">申请时间</option>
              							  <option value="2">起飞时间</option>
              							  <option value="3">最晚采购办理时间</option>
              							</select>
              						</td>
              						<td class="text_right">
              							P&nbsp;N&nbsp;R
              						</td>
              						<td class="text_left">
              							<input type="text" name="xs_pnr_no" value="${param.xs_pnr_no }"  onblur="value=$.trim(this.value).toUpperCase();"  style="width:100px"  class="input-text lh25" size="10"/>
              						</td>
              						 <td class="text_right">
              							票&nbsp;&nbsp;号
              						</td>
              						<td class="text_left">
              							<input type="text" name="tkno" value="${param.tkno}"  style="width:100px" class="input-text lh25" size="10" onblur="value=$.trim(this.value);"/>
              						</td>
              						<td class="text_right">
              							航&nbsp;&nbsp;程
              						</td>
              						<td class="text_left">
              							<input type="text" name="hc" value="${param.hc}"   style="width:100px"   class="input-text lh25" size="10" onblur="value=$.trim(this.value).toUpperCase();" />
              						</td>
              						<td class="text_right">
              							航班号
              						</td>
              						<td class="text_left">
              							<input type="text" name="xs_hbh" value="${param.xs_hbh }"   style="width:100px"  class="input-text lh25" size="10" onblur="value=$.trim(this.value).toUpperCase();" />
              						</td>
              					</tr>
              					
              					
								<!-- 第3行 -->
								<tr>
								   <td class="text_right">
              							乘机人
              						</td>
              						<td class="text_left">
              							<input type="text" name="cjr" value="${param.cjr }"  style="width:100px" class="input-text lh25" size="10" />
              						</td>
									<td class="text_right">客户状态</td>
									<td class="text_left">
									<select  id="xs_tpzt" name="xs_tpzt" style="width:100px;height: 25px;"  >
										<option value="" selected>全部</option> 
              							<c:forEach items="${vfn:dictList('XSTFPZT')}" var="oneZt">
					                        <option value="${oneZt.value}">${oneZt.mc}</option>
						                </c:forEach>
									</select>
									</td>
										
									<td class="text_right">采购状态</td>
									<td class="text_left">
										<select  id="cg_tpzt" name="cg_tpzt" style="width:100px;height: 25px;" >
											<option value="" selected>全部</option> 
	              							<c:forEach items="${vfn:dictList('CGTFPZT')}" var="oneZt">
						                        <option value="${oneZt.value}">${oneZt.mc}</option>
							                </c:forEach>
										</select>
									</td>
									<td class="text_right">审核状态</td>
									<td class="text_left">
										<input type="text" name="shzt" value="${param.shzt }"  class="input-text lh25" size="10" />
									</td>
									<td class="text_right">自愿退票</td>
									<td>
										<select style="width:100px;height: 25px;" id="cg_sfzytp" name="cg_sfzytp" >
											<option value="" selected>==全部==</option> 
											<option value="1" >自愿</option> 
											<option value="0" >非自愿</option> 
										</select>
								   </td>
									<td class="text_right">
										 <input type="button" name="button" id="searchFormButton" class="ext_btn ext_btn_submit" onclick="loadTitle();" value="查询">  
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