<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>航协对账</title>
	<script type="text/javascript">
		$(function(){
			$("#searchFormButton").click();
		})
		/**标签切换*/
		function loadContent(lx){
			$('#jglx').val(lx);
			$(".tabHead li").each(function(){
				$(this).removeClass("currentBtn");
				if(lx == $(this).attr("lx")){
					$(this).addClass("currentBtn");
				}
			}
			);
			$("#searchFormButton").click();
		}
		var ii;
		function iframOnloadEven(){
			if(ii){
				layer.close(ii);
			}
			$("#searchFormButton").click();
		}
		function upfile(){
			 var has=false;
			  $("input[name='files']").each(function(){
				   var fileName=$(this).val();
				   if(fileName!=""){
					   var hz=fileName.substr(fileName.lastIndexOf(".")+1).toUpperCase();
						//验证文件格式
						if(hz!="XLS" && hz!="XLSX" && hz !="CSV" && hz !="TXT"){
							alert("请上传xls或xlsx或CSV或TXT格式文件！");
							return false;
						}
						has=true;
					}
			 });
 			if(!has){
 				alert("请选择一个文件上传");
 				return ;
 			}
			ii = layer.load('系统正在处理您的操作,请稍候!');
			$("#upForm").submit();
		}
		function delDbresult(){
			layer.confirm('你确定删除数据重新对账吗？',function(index){
					$.ajax({
		   	 			type:"post",
						url:"${ctx}/vedsb/cgdzbb/jpcgalldz/delDbresult?id=${param.id }",
						success:function(result){
							if(result.result == 0){
								layer.msg("删除数据重新对账失败："+result.error,2,1);
							}else{
								layer.msg("删除数据重新对账成功！"+result.error,2,1);
								window.location.href="${ctx}/vedsb/cgdzbb/jpcgalldz/daycgdz?id=${param.id }"
							}
						}
			 		});
			});
		}
		function dbresult(){
			layer.confirm('你确定需要开始对比数据吗？',function(index){
				ii = layer.load('系统正在处理您的操作,请稍候!');
				$.ajax({
	   	 			type:"post",
					url:"${ctx}/vedsb/cgdzbb/jpcgalldz/dbdata?id=${param.id }",
					success:function(result){
						layer.close(ii);
						if(result.result == 0){
							layer.msg("对比数据失败："+result.error,2,1);
						}else{
							layer.msg("对比数据成功！"+result.error,2,1);
							window.location.href="${ctx}/vedsb/cgdzbb/jpcgalldz/daycgdz?id=${param.id }"
						}
					}
		 		});
		});			
		}
	</script>
	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
<div>
	   
<table width="1551"  border="0" cellpadding="0" cellspacing="0" class="list_table">
	  <tr>
	    <th width="31" rowspan="2">序号</th>
	    <th width="38" rowspan="2">结果</th>
	    <th width="53" rowspan="2">订单类型</th>
	    <th width="60" rowspan="2">导入来源</th>
	    <th width="38" rowspan="2">编码</th>
	    <th width="63" rowspan="2">票号</th>
	    <th colspan="10" align="center">导入数据</th>
	    <th colspan="7" align="center">系统数据</th>
        <th colspan="11" align="center">其他</th>

  </tr>
	  <tr>
        <th width="30">采购<br />
        票面</th>
		<th width="30" title="机建税费综合">税总和</th>
		<th width="30" bgcolor="#FFFFCC" title="正常：=采购票面 + 税总和，退废票=采购票面+税总和-退票费">采购/应退<br />
	    小计</th>

		<th width="30">采购<br />
	    代理费</th>
		<th width="30" title="采购票面	- 采购代理费">采购价</th>

<th width="30">采购<br />
  手续费</th>
<th width="30">退票费</th>
<th width="30">退票<br />
  费率</th>
<th width="30">实付<br />
  金额</th>
<th width="60">发生日期</th>


<th width="43" bgcolor="#FFFF99">采购<br />
  金额</th>
<th width="43">采购<br />
  来源</th>
<th width="60">订单编号</th>
<th width="59">出票日期 </th>
<th width="46">外部<br />
  订单号</th>

<th width="26">金额<br />
  正确</th>
<th width="59">出票<br />
  对账日期</th>
<th width="59">出票<br />
  对账人</th>
<th width="59">是否<br />
  已补单人</th>
<th width="59">补单<br />
  对账日期</th>
<th width="79">补单<br />
  对账人</th>
<th width="46">支付<br />
  流水号</th>
<th width="90">商品名称</th>
<th width="60">支付时间</th>
<th width="30">收入<br />
  金额</th>
<th width="30">支出<br />
  金额</th>
<th width="60">业务类型</th>
<th width="60">采购备注</th>
  </tr>
  
   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
		<tr class="tr">
			<td class="td_center">{{ i+1 }}</td>
			<td class="td_center">
				{{# var zt="";
					if(d.list[i].jglx == "0"){
						zt = "<font>待比对</font>";
					}else if(d.list[i].jglx == "1"){
						zt = "<font color='green'>金额正确</font>";
					}else if(d.list[i].jglx == "2"){
						zt = "<font color='red'>金额不正确</font>";
					}else if(d.list[i].jglx == "3"){
						zt = "<font color='blue'>当日漏单</font>";
					}else if(d.list[i].jglx == "4"){
						zt = "<font color='blue'>当日多单</font>";
					}else if(d.list[i].jglx == "9"){
						zt = "<font color='#FF0099'>异常单</font>";
					}
				}}
				{{zt}}            </td>
			<td class="td_center">
				{{# var zt="";
					if(d.list[i].ddlx == "1"){
						zt = "<font >正常单</font>";
					}else if(d.list[i].ddlx == "2"){
						zt = "<font color='green'>退票单</font>";
					}else if(d.list[i].ddlx == "3"){
						zt = "<font color='blue'>改签单</font>";
					}else if(d.list[i].ddlx == "4"){
						zt = "<font color='blue'>补差单</font>";
					}else if(d.list[i].ddlx == "0"){
						zt = "<font color='#FF0099'>异常单</font>";
					}
				}}
				{{zt}}            </td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].drly)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].pnrno)}}/{{$.nullToEmpty(d.list[i].bigpnrno)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].tkno)}}</td>

			<td class="td_center">{{$.nullToEmpty(d.list[i].cgPmj)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].cgJsf  + d.list[i].cgTax)}}</td>
			<td bgcolor="#FFFFCC" class="td_center">{{$.nullToEmpty(d.list[i].cgXj)}}</td>
			 
<td class="td_center">{{$.nullToEmpty(d.list[i].cgDlf)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgCgj)}}</td>

<td class="td_center">{{$.nullToEmpty(d.list[i].cgSxf)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgTpf)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgTpfl*100)}}%</td>

<td class="td_center">{{$.nullToEmpty(d.list[i].cgSfje)}}</td>

<td class="td_center">{{$.dateF(d.list[i].ywrq,'yyyy-MM-dd') }}</td>


<td bgcolor="#FFFF99" class="td_center">{{$.nullToEmpty(d.list[i].cgje)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgly)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].ddbh)}}</td>
<td class="td_center">{{$.dateF(d.list[i].cprq,'yyyy-MM-dd HH:mm') }}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].wbdh)}}</td>

<td class="td_center">
				{{# var sfzq="";
					if(d.list[i].jesfzq == "1"){
						sfzq = "<font >正确</font>";
					}else if(d.list[i].jesfzq == "2"){
						sfzq = "<font color='red'>不正确</font>";
					}
				}}
				{{sfzq}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cpDzrq)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cpDzr)}}</td>
<td class="td_center">
			{{# var Sfybd="";
					if(d.list[i].bdSfybd == "0"){
						Sfybd = "<font >未补</font>";
					}else if(d.list[i].bdSfybd == "1"){
						Sfybd = "<font color='green'>已补</font>";
					}
				}}
				{{Sfybd}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].bdBdrq)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].bdBdr)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgZflsh)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgZfspmc)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgZfsj)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgSrje)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgZcje)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgYwlx)}}</td>
<td class="td_center">{{$.nullToEmpty(d.list[i].cgBz)}}</td>
		</tr>
	   {{# } }}
  
  
</table>

<div>
   </script>
</head>
<body>
    <div class="container clear">
           <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
    <table  style="width:100%;">
			<tr>
				<td>
					<span >对账人： ${jpcgdz.dzUserid } </span>
					<span style="margin-left:20px;">对账日期：${jpcgdz.dzrq }</span>
					<span style="margin-left:20px;">
						对账状态： 
						 ${jpcgdz.dzZt eq '0' ? '未对账' : ''}
						 ${jpcgdz.dzZt eq '1' ? '核对无误已到账'  : ''}
						 ${jpcgdz.dzZt eq '2' ? '对账有异常'  : ''}
						 ${jpcgdz.dzZt eq '3' ? '核对无误未到账'  : ''}
					</span>
					<span style="margin-left:50px;">
						<input type="button" value="重新对账" class="ext_btn ext_btn_submit" onClick="delDbresult()"/>
					</span>
					
					<span style="margin-left:50px;">
						<input type="button" value="对比数据" class="ext_btn ext_btn_submit" onClick="dbresult()"/>
					</span>
					
				</td>
			</tr>
		</table>
		<c:if test="${jpcgdz.dzZt ne '1'}">
		<iframe  id="hiddeniframe" name="hiddeniframe" height="0" onload="iframOnloadEven()"></iframe>
 <form id="upForm" name="upForm" action="${ctx}/vedsb/cgdzbb/jpcgalldz/bankup" method="post" target="hiddeniframe" enctype="multipart/form-data">
 	<input type="hidden" name="id" value="${param.id}">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		 <tr>
			 <td>
			 <label for="gsbh10100073">
			 	<input name="gsbhs"  type="hidden"  value="10100073" id="gsbh10100073" >直通车数据</label>
				<input name="files"  type="file"                       id="file10100073" class="input-text lh30" style="width:300px;">
				<a href="${ctx }/updownFiles/mb/ZTC.xls" title="您导入的数据格式必须符合 示例格式">格式示例下载</a>
				<input type="button"  value="执行导入" class="ext_btn ext_btn_submit" onClick="upfile();" style="margin-left:13px;">
			 </td>
		</tr>
	</table>
</form>	
</c:if>
</div></div></div>

  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
		 <form action="${ctx}/vedsb/cgdzbb/jpcgalldz/getCgdzMxList" id="searchForm" name="searchForm" method="post">
		<input type="hidden"  name="VIEW" value="032593831a843f1b255e93f93c79e87f" id="view"/>
		<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
		<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
		<input type="hidden"  name="orderBy" value="drsj,id" id="orderBy"/>
		<input type="hidden"  name="search_EQ_zbid" value="${param.id }"/>
		<input type="hidden" name="search_EQ_jglx" id="jglx">
		<table class="table01" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		   <td style="text-align: right;">订单类型</td>
			<td>
				<select name="search_EQ_ddlx">
					<option value="">===请选择===</option>
					<option value="1">正常单</option>
					<option value="2">退票单</option>
					<option value="3">改签单</option>
					<option value="4">补差单</option>
					<option value="0">异常单</option>
				</select>
			</td>
		    <td   style="text-align: right;">导入来源</td>
			<td>
			    <select name="search_EQ_drly">
					<option value="">===请选择===</option>
					<option value="直通车">直通车</option>
				</select>
			</td>
			 <td  style="text-align: right;">票号</td>
			<td>
			  <input type="text" name="search_EQ_tkno" size=10 >
			</td>
			<td  style="text-align: right;">PNR</td>
			<td>
			  <input type="text" name="search_EQ_pnrno"  size=10 >
			</td>
		     <td>
		       <input type="button" class="ext_btn ext_btn_success" value="查询" id="searchFormButton">
		     </td>
		  </tr>
		</table>
		<div class="tabContainer" style="width:99.6%;">
				<ul class="tabHead">
					<li lx='' onclick="loadContent('');" class="currentBtn" ><a style="text-decoration:none;">全部(${mxgroup['all'] })</a></li>
					<li lx='0' onclick="loadContent('0');" ><a style="text-decoration:none;">待比对(${mxgroup['0'] })</a></li>
					<li lx='1' onclick="loadContent('1');" ><a style="text-decoration:none;">金额正确(${mxgroup['1'] })</a></li>
					<li lx='2' onclick="loadContent('2');" ><a style="text-decoration:none;">金额不正确(${mxgroup['2'] })</a></li>
					<li lx='3' onclick="loadContent('3');" ><a style="text-decoration:none;">当日漏单(${mxgroup['3'] })</a></li>
					<li lx='4' onclick="loadContent('4');" ><a style="text-decoration:none;">当日多单(${mxgroup['4'] })</a></li>
					<li lx='9' onclick="loadContent('9');" ><a style="text-decoration:none;">异常订单(${mxgroup['9'] })</a></li>
				</ul>
			<div class="clear"></div>
			</div> 
		
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
        <div class="box span10 oh" id="list_table" style="width:2500px">
             <!-- 显示列表 ID不能修改 -->   
        </div>
        <div id="list_table_page">
        <!-- 分页  ID不能修改-->
     	</div>
	</div>
<div id="footer"></div>
    
</body>
</html>