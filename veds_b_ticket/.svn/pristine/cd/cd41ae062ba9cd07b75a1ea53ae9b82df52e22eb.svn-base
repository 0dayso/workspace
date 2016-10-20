<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<title>Q信息管理</title>
	<style type="text/css">
		.inputDate{width:100px;}
		.input1{width:100px;}
		a{
			text-decoration: none;
		}
	</style>
	<script type="text/javascript">
	//系统处理
	function doDispose(zt){
		var checkboxes = $("input[name=id]:checked");
		if(checkboxes.length < 1){
			layer.msg('请选择一条记录',2,5);
			return;
		}
		var cl_zt = "${param.cl_zt }";
		var checkeds = new Array();
		$(checkboxes).each(function(i){
			if($(this).attr("checked")) {
		      checkeds.push($(this).val());
		   	}
		});
		var ids = checkeds.join(",");
		var url = "${ctx}/vedsb/qxxgl/bqinfo/toEdit?size=" + checkeds.length + "&zt=" + zt + "&cl_zt=" +cl_zt+"&ids="+ids;
		$.layer({
			type: 2,
			title: ['<b>处理Q信息</b>'],
			area: ['600px', '450px'],
			iframe: {src: url}
	    });
	}
	//全选  取消全选
	function selectAll(obj){
		var checkboxes = $('input[name="id"]');
		if($(obj).attr("checked")){
			$(checkboxes).each(function(i){
				$(this).attr("checked",true);
			});
		}else{
			$(checkboxes).each(function(i){
				$(this).attr("checked",false);
			});
		}
	}
	//单个checkbox点击
	function clickSelect(obj){
		if($(obj).attr("checked")){
			if($("input[name=id]").length == $("input[name=id]:checked").length){
				$("#allSelect").attr("checked",true);
			}
		}else{
			$("#allSelect").attr("checked",false);
		}
	}
	
	//详
	function detail(id){
		var url="${ctx}/vedsb/qxxgl/bqinfo/detail?id="+id;
		$.layer({
			type: 2,
			title: ['<b>详细信息</b>'],
			area: ['600px', '480px'],
			iframe: {src: url}
	    });
	}
	
	//查看日志
	function showLog(id){
		var url="${ctx}/vedsb/qxxgl/bqinfo/showLog?id="+id;
		$.layer({
			type: 2,
			title: ['<b>Q信息日志</b>'],
			area: ['600px', '480px'],
			iframe: {src: url}
	    });
	}
	
	function setOffice(){
		var selval = $("offtype").value;
		if( "1" == selval ){
			$("officeid").setAttribute("name","officeid");
			$("officeid").setAttribute("value","${param.officeid}");
		}else{
			$("officeid").setAttribute("name","by2");
			$("officeid").setAttribute("value","${param.by2}");
		};
	}
	
	//订单详
	function detailKhdd(id){
		var url = "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
	
	
	//退票单详
	function detailTpd(tpdh){
	//弹出取消页面
	    var url="${ctx}/vedsb/jptpgl/jptpd/tpdDetail_"+tpdh;
	    window.open(url);
	}
	//手动批量处理
	function sdplcl(){
		var checkboxes = $("input[name=id]:checked");
		if(checkboxes.length < 1){
			layer.msg('请选择一条记录',2,5);
			return;
		}
		var cl_zt = "${param.cl_zt }";
		var checkeds = new Array();
		$(checkboxes).each(function(i){
			if($(this).attr("checked")) {
		      checkeds.push($(this).val());
		   	}
		});
		var ids = checkeds.join(",");
		var url = "${ctx}/vedsb/qxxgl/bqinfo/sdplcl?ids="+ids;
		$.ajax({
   	 		type:"post",
			url:url,
			async:false,
			success:function(result){
				if(result.CODE == '0'){
					layer.msg(result.MSG,2,1,function(){
						$("#searchFormButton").click();
					});
				}else{
					layer.msg(result.MSG,2,3);
				}
			}
     	 });
			
	}
	
	function init(){
		setOffice();
	};
	CGTFPZT = '${vfn:toJSON(vfn:dictList('CGTFPZT'))}';
	</script>
	<script id="tpl_list_table" type="text/html">
	<div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="20">序号</th>
			<th width="20" style="display:${param.cl_zt eq '1' ? 'none' : ''}"><input id="allSelect" type="checkbox" name="ids" onclick="selectAll(this);"/></th>
			<th width="30">订单编号</th>
			<th width="30">外部订单编号</th>
			<th width="30">外部退单编号</th>
			<th width="30">退改状态</th>
			<th width="10">是否<br>匹配</th>
			<th width="10">类型</th>
			<th width="25">处理状态</th>
			<th width="25">PNR</th>
			<th width="15">国内<br>国际</th>
			<th width="30">清QOFFICE号</th>
			<th width="30">预订OFFICE号</th>
			<th width="100">航程-航班号-起飞到达时间-航段状态</th>
			<th width="30">乘机人</th>
			<th width="30">联系人</th>
			<th width="30">联系电话</th>
			<th width="50">清Q时间</th>
			<th width="30">最后处理人</th>
			<th width="50">最后处理时间</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center"  style="display:${param.cl_zt eq '1' ? 'none' : ''}"><input type="checkbox" name="id" value="{{$.nullToEmpty(d.list[i].ID)}}" onclick="clickSelect(this);"/></td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].DDBH)}}</td>
				<td class="td_center"><a href="###" onclick="detailKhdd('{{$.nullToEmpty(d.list[i].DDBH)}}');">{{$.nullToEmpty(d.list[i].WBDH)}}</a></td>
				<td class="td_center">
				{{#
					var wbddbh = $.nullToEmpty(d.list[i].WBTDBH);
					var wbddbhs= new Array();
					wbddbhs=wbddbh.split(",");
					var tpdh = $.nullToEmpty(d.list[i].TPDH);
					var tpdhs= new Array();
					tpdhs=tpdh.split(",");
					var show="";
					for(var j=0;j<tpdhs.length;j++){
						show=show + "<a href='###' onclick=detailTpd('"+tpdhs[j]+"');>"+wbddbhs[j]+"</a><br>";
					}
				}}
				{{show}}
				</td>
				<td class="td_center">
					{{# var CG_TPZT = $.nullToEmpty(d.list[i].CG_TPZT);
						var tpzts= new Array();
						tpzts=CG_TPZT.split(",");
						for(var j=0;j<tpzts.length;j++){
					}}
					{{$.findJson(CGTFPZT,$.nullToEmpty(tpzts[j])).mc}}<br>
					{{#}}}
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].DDBH) != '' ? '是' : '否'}}</td>
				<td class="td_center"><a href="###" onclick="detail('{{$.nullToEmpty(d.list[i].ID)}}')">{{$.nullToEmpty(d.list[i].QTYPE)}}</a></td>
				<td class="td_center">
					{{#
						var clZt=$.nullToEmpty(d.list[i].CL_ZT);
						var ztValue="";
						if(clZt == '0'){ ztValue="未处理" }
						else if(clZt == '1'){ ztValue="已处理" }
						else if(clZt == '2'){ ztValue="处理中" }
					}}
					{{ztValue}}
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].PNRNO)}}</td>
				<td class="td_center">
					{{#
						var glgj=$.nullToEmpty(d.list[i].PNR_HCGLGJ);
						var glgjValue="";
						if(glgj == '0'){ glgjValue = "国际"}
						else if(glgj == '1'){ glgjValue = "国内"}
					}}
					{{glgjValue}}
				</td>
				<td class="td_center"><a href="###" onclick="showLog('{{$.nullToEmpty(d.list[i].ID)}}')">{{$.nullToEmpty(d.list[i].OFFICEID)}}</a></td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].BY2)}}</td>
				<td class="td_center">
					{{#
						var pnrHc=$.nullToEmpty(d.list[i].PNR_HC).split(",");
						var pnrHbh=$.nullToEmpty(d.list[i].PNR_HBH).split(",");
						var pnrCfdatetime1=$.nullToEmpty(d.list[i].PNR_CFDATETIME1).split(",");
						var pnrHdzt=$.nullToEmpty(d.list[i].PNR_HDZT).split(",");
						var str="";
						for(var j=0;j <pnrHc.length;j++){
						  str =str+pnrHc[j]+"-"+pnrHbh[j]+"-"+pnrCfdatetime1[j]+"-"+pnrHdzt[j]+"<br/>";
						}
					}}
					{{str}}
				</td>    
				<td class="td_center">{{$.nullToEmpty(d.list[i].PNR_CJR)}}</td>		
				<td class="td_center">{{$.nullToEmpty(d.list[i].LXR)}}</td>   
				<td class="td_center">{{$.nullToEmpty(d.list[i].LXRDH) == '' ? '' : d.list[i].LXRDH+'<br>'}}{{$.nullToEmpty(d.list[i].MSMOBILNO)}} </td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].CREATE_DATETIME)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].CL_USERID)}}</td>
				<td class="td_center">{{$.dateF(d.list[i].CL_DATETIME,'yyyy-MM-dd HH:mm:ss')}}</td>
    		</tr>
		{{# } }}
		</table>
	</div>
	</script>
</head>
<body onload="init();">
	<%@include file="list_title.jsp"%>
	<div class="container">
  	  	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/qxxgl/bqinfo/query" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="cl_zt" value="${empty param.cl_zt ? "0" : param.cl_zt }" />
            				<input type="hidden"  name="pageNum" value="${empty param.pageNum ? 1 : param.pageNum}" id="pageNum"/>
            				<input type="hidden" name="pageSize" value="${empty param.pageSize ? 10 : param.pageSize }" id="pageSize"/>
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
								<tr>
									<td>
										<select name="datetype" class="select">
											<option ${empty param.datetype or '1' eq param.datetype ? 'selected' : ''} value="1">清Q日期</option>
											<option ${'2' eq param.datetype ? 'selected' : ''} value="2" >起飞日期</option>
											<option ${'3' eq param.datetype ? 'selected' : ''} value="3" >处理日期</option>
											
										</select>
									</td>
									<td colspan="2">
										<input type="text" name="ksrq" style="width: 100px;height: 24px;" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" class="input-text Wdate"style="width:100px;height:24px;" size="10" id="mindate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxdate\')}'})">-
										<input type="text" name="jsrq" style="width:100px;height:24px;" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" class="input-text Wdate"  id="maxdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'mindate\')}'})">
									</td>
									
									<td align="right" colspan="3">类型
										<input type="checkbox" value="GQ" id="gq" name="qtypes" ${fn:contains(qtypes,'GQ') ? 'checked': ''}><label for="gq">GQ</label> 
										<input type="checkbox" value="KK" id="kk" name="qtypes" ${fn:contains(qtypes,'KK') ? 'checked': ''}><label for="kk">KK</label>
										<input type="checkbox" value="SR" id="sr" name="qtypes" ${fn:contains(qtypes,'SR') ? 'checked': ''}><label for="sr">SR</label>
										<input type="checkbox" value="SC" id="sc" name="qtypes" ${fn:contains(qtypes,'SC') ? 'checked': ''}><label for="sc">SC</label> 
										
										<input type="checkbox" value="TL" id="tl" name="qtypes" ${fn:contains(qtypes,'TL') ? 'checked': ''}><label for="tl">TL</label>
										<input type="checkbox" value="RP" id="rp" name="qtypes" ${fn:contains(qtypes,'RP') ? 'checked': ''}><label for="rp">RP</label>
										<input type="checkbox" value="TC" id="tc" name="qtypes" ${fn:contains(qtypes,'TC') ? 'checked': ''}><label for="tc">TC</label> 
										<input type="checkbox" value="RE" id="re" name="qtypes" ${fn:contains(qtypes,'RE') ? 'checked': ''}><label for="re">RE</label>
									</td>
									
									<td align="right">国内国际</td>
									<td>
										<select name="pnr_hcglgj" class="select" style="width: 100px" >
											<option value="">==请选择==</option>
											<option value="1" ${'1' eq param.pnr_hcglgj ? 'selected' : '' }>国内</option>
											<option value="0" ${'0' eq param.pnr_hcglgj ? 'selected' :''  }>国际</option>
										</select>
									</td>
								</tr>
									
								<tr>
									<td align="right">P N R</td>
									<td>
										<input type="text" class="input1" name="pnrno" id="pnrno" size="10" style="width: 100px;height: 20px;" value="${param.pnrno}" onblur="value=$.trim(this.value).toUpperCase();" />
									</td>
									<td align="right">航班号</td>
									<td>
										<input type="text" class="input1" name="pnr_hbh" id="pnr_hbh" size="10" style="width: 100px;height: 20px;" value="${param.pnr_hbh}" onblur="value=$.trim(this.value).toUpperCase();" />
									</td>
									<td align="right">航&nbsp;&nbsp;&nbsp;&nbsp;程</td>
									<td>
										<input type="text" name="pnr_hc" id="pnr_hc" size="10" style="width: 100px;height: 20px;" onblur="value=$.trim(this.value).toUpperCase();" />
									</td>
									<td>
										<select id = "offtype" name ="offtype" onchange="setOffice();" class="select">
											<option ${empty param.offtype or '1' eq param.offtype ? 'selected' : ''} value="1">清QOFFICE</option>
											<option ${'2' eq param.offtype ? 'selected' : ''} value="2" >预订OFFICE</option>
										</select>
									</td>
									<td>
										<input type="text" name="officeid" id="officeid" size="10" style="width: 100px;height: 20px;" value="${param.officeid}" onblur="value=$.trim(this.value).toUpperCase();" />
									</td>
								</tr>
								<tr>
									<td align="right">外部订单编号</td>
							        <td>
							        	<input type="text" id="wbddbh" name="wbddbh" style="width: 100px;height: 20px;" value="${param.wbddbh }" onblur="value=$.trim(this.value).toUpperCase();"/>
							        </td>
							        <td align="right">外部退单编号</td>
							        <td>
							        	<input type="text" id="wbdh" name="wbdh" style="width: 100px;height: 20px;" value="${param.wbdh }" onblur="value=$.trim(this.value).toUpperCase();"/>
							        </td>
									<td align="right">乘机人</td>
									<td>
										<input type="text" class="input1" name="pnr_cjr" id="pnr_cjr" style="width: 100px;height: 20px;" value="${param.pnr_cjr}" />
									</td>
									<td align="right">联系电话</td>
									<td>
										<input type="text" class="input1" name="lxrdh" id="lxrdh" style="width: 100px;height: 20px;" value="${param.lxrdh}" />
									</td>
									<td align="center">
										<input type="button" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit" />
									</td>
									<!-- 
									<c:if test="${empty param.cl_zt or param.cl_zt eq '0' or param.cl_zt eq '2' }">
										<td align="center">
									         <input type="button" onclick="doDispose(2);" class="ext_btn ext_btn_submit" value=" 系统处理 ">
									 	</td>
								 	</c:if>
								 	 -->
								 	<c:if test="${empty param.cl_zt or param.cl_zt eq '0'}"  >
								 		<td>
								 			<input type="button" name="button" onclick="sdplcl();" value="手动处理" class="ext_btn ext_btn_submit" />
								 		</td>
								 	</c:if>
								</tr>
								<tr>
									<td align="right">是否匹配上订单</td>
									<td style="text-align:left;">
										<select name="sfpp" style="height: 24px;">
											<option value="">请选择</option>
											<option value="1">已匹配</option>
											<option value="2">未匹配</option>
										</select>
									</td>
									<c:if test="${param.cl_zt eq '1'}"  >
										<td align="right">处理状态</td>
										<td style="text-align:left;">
											<select name="qzt" style="height: 24px;">
												<option value="">请选择</option>
												<option value="0">未处理</option>
												<option value="1">已处理</option>
												<option value="2">处理中</option>
											</select>
										</td>
									</c:if>
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
