<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
	    <title>自动出票监控</title>
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
		<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
		<script type="text/javascript">
			JPDDZT = '${vfn:toJSON(vfn:dictList('JPDDZT'))}';
			CGLY = '${vfn:toJSON(vfc:getVeclassLb('10014'))}';
			CGZT = '${vfn:toJSON(vfn:dictList('CGZT'))}';
			//订单详
			function detail(id){
				var url = "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
				window.open(url);
			}
			 $(function(){
			 	//航司控件
			    $("#gn_hkgs_m").autocompleteGnHkgs("gn_hkgs");
			    
			 	//出票效率统计报表点击订单数查看详使用
				var cptjbbbs = '${param.cptjbbbs}';
				//标识不为空则为出票效率统计报表查看详
				if(cptjbbbs != '' ){
					//给航空公司input赋值
					$("#gn_hkgs_m").val("${param.hkgs}");
					$("#gn_hkgs").val("${param.hkgs}");
					//cptjbbbs为0表示查看总订单数 1表示查看已匹配规则
					if(cptjbbbs == '0'){
						$("#chk_by3").attr("checked",false);
					}	
				}
			   
			    //进入默认查询
		    	$("#searchFormButton").click();	
			});
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
				var url ="${ctx}/vedsb/jpzdcp/jpzdcpgzsz/cpgzEdit?id="+gzbh+"&index=3";
				window.open(url);
				
			}
			
			function showLog(id,pnr){
				var url="${ctx}/vedsb/cpkzt/jpzdcpjk/getSm";
				$.ajax({
	     	 		type:"post",
					url:url,
					data:{id:id},
					success:function(result){
					     var pageii = $.layer({
									    type: 1,
									    title: ["<b><font color='white'>PNR: "+pnr+" 自动出票日志</font></b>", "background:#3e75d0;text-decoration:none;"],
									    area: ['auto', 'auto'],
									    border: [0], //去掉默认边框
									    shade: [0], //去掉遮罩
									    closeBtn: [0, true], //去掉默认关闭按钮
									    shift: 'top', //从左动画弹出
									    page: {
									        html: "<div style='width:600px; height:450px;overflow:auto;padding:20px; border:1px solid #ccc; background-color:#FFF;'>"+result+"</div>"
									    }
									   });
					}});
		   }
			
			
			//查看异动日志
			function enterLogPage(ddbh){
				var url="${ctx}/vedsb/jpddgl/jpkhddczrz/enterLogPage_"+ddbh+"?ywlx=01";
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
</script>
<script id="tpl_list_table" type="text/html">
<div >
	<table width="2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="30px">序号</th>
			<th width="100px">订单编号</th>
			<th width="60px">网店名称</th>
			<th width="80px">方案</th>
			<th width="120px">网店政策代码</th>
			<th width="80px">产品类型</th>
			<th width="50px">采购来源</th>
			<th width="50px">平台采<br>购状态</th>
			<th width="100px">出票锁单人</th>
			<th width="80px">出票渠道类型</th>
			<th width="60px">订单状态</th>
			<th width="100px">PNR</th>
			<th width="80px">自动出票状态</th>
			<th width="80px">乘机人</th>
			<th width="80px">航程</th>
			<th width="60px">航班号</th>
			<th width="50px">舱位</th>
			<th width="80px">OFFICE号</th>
			<th width="80px"><a href="###" onclick="sort('k.DDSJ');">预定时间</a></th>
			<th width="80px"><a href="###" onclick="sort('a.CJSJ');">出票开始时间</a></th>
			<th width="80px"><a href="###" onclick="sort('a.CPWCSJ');">出票完成时间</a></th>
			<th width="80px">出票员</th>
			<th width="80px">规则 </th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center"><a href="###" onclick="detail('{{d.list[i].DDBH}}');">{{  $.nullToEmpty(d.list[i].DDBH ) }}</a></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].EX.WDID.wdmc ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].FAID ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].WD_ZCDM ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].WD_ZCLX ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CGLY) }}</td>
				<td class="td_center">{{  $.findJson(CGZT,$.nullToEmpty(d.list[i].CGZT)).mc }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CP_SDR) }} {{$.nullToEmpty(d.list[i].EX.CP_SDR.xm) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CP_CPQD ) }}</td>
				<td class="td_center">{{  $.findJson(JPDDZT,d.list[i].DDZT).mc }}</td>
				<td class="td_center">
					{{#
							var ddpnrno=$.nullToEmpty(d.list[i].DD_PNRNO);
							var newpnrno=$.nullToEmpty(d.list[i].NEW_PNR);
                            var pnrno="<span class='copytext' copytext="+ddpnrno+">"+ddpnrno+"</span>";
                            if(ddpnrno!=newpnrno && newpnrno != ''){
                                 pnrno=pnrno+"-><span class='copytext' copytext="+newpnrno+"><font color=green>"+newpnrno+"</font></span>";
                            }
					}}
					<a href="###" onclick="enterLogPage('{{d.list[i].DDBH}}');">{{pnrno}}</a>
				</td>
                <td class="td_center">
                    <a href="###" style="text-decoration:none;" title="点击查看详细日志" onclick="showLog('{{d.list[i].ID}}','{{d.list[i].DD_PNRNO}}');">
					{{#
						var cpzt = $.nullToEmpty(d.list[i].CPZT);
						var cjsj_str=$.dateF(d.list[i].CJSJ,'yyyy/MM/dd HH:mm:ss');
						var now = new Date();
						var cjsj= new Date(cjsj_str);  
					    var diff= parseInt((now.getTime() - cjsj.getTime())/1000);
                        var ztms="";
						if(cpzt == 0){
							ztms="<font color='green'>自动出票成功</font>";
						}else if(cpzt > 0){
                            ztms="<font color='blue'>出票中</font>["+diff+"]";
                            if(cpzt == 40){
                           		 ztms="<font color='green'>手动出票成功</font>";
							}
						} else if(cpzt < 0){
						 	ztms="<font color='red'>出票失败</font>";
						}
					}}{{ztms}}
                    </a>
                </td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CJR ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].DD_HC ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].DD_HBH ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].DD_CW ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].BSP_OFFICE ) }}{{  $.nullToEmpty(d.list[i].BOP_OFFICE) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].DDSJ,'yyyy-MM-dd HH:mm')}}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CJSJ,'yyyy-MM-dd HH:mm')}}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CPWCSJ,'yyyy-MM-dd HH:mm')}}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].EX.ZDCPY.xm) }}</td>
				<td class="td_center"><a href="###" onclick="enterEditPage('{{d.list[i].GZID}}');">{{  $.nullToEmpty(d.list[i].GZID ) }}</a></td>
    		</tr>
		{{# } }}
	</table>
</div>
</script>
</head>
<body onload="doLoad()">
<c:set var="gngj" value="${empty param.gngj ? '1' : param.gngj }"></c:set>
<!--网店页签 -->
<%@include file="list_title.jsp"%>

<!-- 查询条件区 --> 
<div class="container_clear">
 	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
						<form name="listForm" action="${ctx}/vedsb/cpkzt/jpzdcpjk/query" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
							<input type="hidden"  name="orderBy" value=" a.CJSJ desc " id="orderBy"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
							<input type="hidden"  name="lx"  value="${param.lx}" id="lx"/> 
							<input type="hidden"  name="gngj" value="${empty param.gngj ? '1' : param.gngj }" id="gngj" />
							<table  class="table01" border="0" cellpadding="0" cellspacing="0"> 
								<tr>
									<td class="text_right">预订日始</td>
									<td class="text_left">
										<input type="text" name="ksrq" style="width:90px"  class="input-text Wdate" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>
									<td class="text_right">预订日止</td>
									<td class="text_left">
										<input type="text"  name="jsrq" style="width:90px"  class="input-text Wdate" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>
									<td class="text_right">出票日始</td>
									<td class="text_left">
										<input type="text"  name="cpcgsjs" style="width:90px"  class="input-text Wdate" value="${empty param.cpcgsjs ? '' : param.cpcgsjs}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>
									<td class="text_right">出票日止</td>
									<td class="text_left">
										<input type="text"  name="cpcgsjz" style="width:90px"  class="input-text Wdate" value="${empty param.cpcgsjz ? '' : param.cpcgsjz}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td class="text_right">采购来源</td>
									<td class="text_left">
										<select name="cgly" class="inputred" style="width:90px;height: 25px;">
											<option value="" 'selected'}> =请选择= </option>
											<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
												<c:if test="${oneLx.id ne '10014'}">
													<option value="${oneLx.ywmc}" ${param.cgly eq oneLx.ywmc ? 'selected' : ''}>${oneLx.ywmc}</option>
												</c:if>
											</c:forEach>
										<select>
									</td>
									<td class="text_right">订单编号</td>
									<td class="text_left">
										<input type="text" name="ddbh" class="input-text lh25" style="width:90px" value="${param.ddbh}" onkeyup="value =value.toUpperCase();value=$.trim(this.value);"/>
									</td>
									<td class="text_right">PNR </td>
									<td class="text_left">
										<input type="text" name="pnr" class="input-text lh25 UpperCase" value="${param.pnr}" size="8" style="width:90px" onblur="value=$.trim(this.value).toUpperCase();">
									</td>
									<td class="text_right">网店</td>
							        <td class="text_left">
				                  		<select name="wdid" id="wdid" style="width: 92px;height: 24px;" class="select">
											<option value="">==全部==</option>
											<c:forEach items="${jpfn:wdList(BUSER.shbh,param.gngj)}" var="wdBean">
												<option value="${wdBean.id}" ${param.wdid eq wdBean.id ? 'selected' : ''}>${wdBean.wdmc}</option>
											</c:forEach>
										<select>
							        </td>  
								</tr>
								<tr>
									<td class="text_right">航空公司</td>
									<td class="text_left">
										<input type="text" id="gn_hkgs_m" name="gn_hkgs_m" value="" class="input-text lh25" style="width: 88px;" size="10" onchange="clearValue(this,'gn_hkgs');"/>
   					 	  				<input type="hidden" id="gn_hkgs" name='hkgs' value="">
									</td>
									<td class="text_right">航&nbsp;&nbsp;&nbsp;&nbsp;程</td>
									<td class="text_left">
										<input type="text" size="11" name="hc" class="input-text lh25 UpperCase" value="${param.hc}" style="width:90px"
											onkeyup="value =value.toUpperCase();value=$.trim(this.value);">
									</td>
									<td class="text_right">航 班 号</td>
									<td class="text_left">
										<input type="text" name="hbh" class="input-text lh25 UpperCase" style="width:90px" value="${param.hbh}"
											onkeyup="value =value.toUpperCase();value=$.trim(this.value);">
									</td>
									<td class="text_right">乘 机 人</td>
									<td>
										<input type="text" name="cjr" class="input-text lh25 UpperCase" style="width:90px" value="${param.cjr}"
											onkeyup="value=$.trim(this.value);">
									</td>
									<td class="text_right">出票员</td>
									<td class="text_left">
										<input type="text" name="cpy" class="input-text lh25" style="width:90px" value="${param.CPY}"
										  onkeyup="value =value.toUpperCase();value=$.trim(this.value);" />
									</td>
								</tr>
								<tr>
									<td class="text_left" colspan="3">
										<input type="checkbox" name="zdcp_sffh" value="1" ${empty param.zdcp_sffh or param.zdcp_sffh eq '1'? 'checked': ''} id="chk_by3">
										<label for="chk_by3">只显示满足自动出票规则的订单</label>
									</td>
									<td class="text_right">自动出票状态</td>
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
									<td class="text_right" colspan="2">
										<input type="button" id="searchFormButton" name="button"  value=" 查 询 " class="ext_btn ext_btn_submit" >
									</td>
								</tr>
							</table>
						</form>
					</div>
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