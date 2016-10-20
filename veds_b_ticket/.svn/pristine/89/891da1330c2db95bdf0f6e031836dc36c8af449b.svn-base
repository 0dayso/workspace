<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
  	<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
  	<script type="text/javascript">
  		$(function(){
  			$.ajax({
       	 			type:"post",
 					url:"${ctx}/vedsb/cgdzbb/jpcgdzyc/getzffs",
 					success:function(result){
 						if(result != ''){
 							$("#cgzfkm").append(result);
 							$("#view").val("42A3BF59A4875B7F7F9C91D51737FECC");
 						}
 					}
       	 		});
  			$("#hkgsmc").autocompleteGnHkgs("hkgs");
			$("#searchFormButton").click();
		});
		function flush(){
			$("#searchForm").pageSearch();
		}
  		function changeRqtj(v){
  			if(v == '0'){
  				$("#rqsbt").text("创建日始");
  				$("#rqzbt").text("创建日止");
  			}else if(v == '1'){
  				$("#rqsbt").text("处理日始");
  				$("#rqzbt").text("处理日止");
  			}else if(v == '2'){
  				$("#rqsbt").text("出票日始");
  				$("#rqzbt").text("出票日止");
  			}
  		}
  		
  		function cancel(id){//取消
  			var url = "${ctx}/vedsb/cgdzbb/jpcgdzyc/qxcgdz?id="+id;
	  		$.layer({
	  			area: ['250px', '150px'],
	  			dialog : {
	  				 msg : "确定要取消 吗？",
	  				 btns: 2,                    
		        	 type: 4,
		        	 btn : ['确定','取消'],
		        	 yes : function(){
		        	 	$.ajax({
		        	 		type:"post",
		  					url:url,
		  					success:function(result){
		  						flush();
		  						if("1" == result){
		  							layer.msg("取消成功！",2,1);
		  						}else{
		  							layer.msg("取消失败！",2,1);
		  						}
		  					}
		        	 	});
		        	 },no: function(){
		             	layer.msg("取消操作", 2, 3);
		          	 }
	  			}
	  		});
  		}
		
		function resolve(id){//处理
		   var url ="${ctx}/vedsb/cgdzbb/jpcgdzyc/cgycClEdit?id="+id;
		   $.layer({
				type: 2,
				title: ['采购异常处理'],
				area: ['430px', '190px'],
				iframe: {src: url}
		   }); 
		}
		
		function modify(id){//修改
			window.open("${ctx}/vedsb/cgdzbb/jpcgdzyc/addcgyc?id="+id);
		}
		
		function insert(){//新增
			window.open("${ctx}/vedsb/cgdzbb/jpcgdzyc/addcgyc");
		  // var url ="${ctx}/vedsb/cgdzbb/jpcgdzyc/addcgyc"
		   //$.layer({
				//type: 2,
				//title: ['新增采购异常'],
				//area: ['1200px', '400px'],
				
				
				//iframe: {src: url}
		   //}); 
		}   
		function refresh(){
			$("#searchFormButton").click();
		}		
  	</script>
  	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	  <tr>
        <th width="20">序号</th>
        <th width="70">操作</th>
		<th width="30">票证类型</th>
		<th width="35">PNR</th>
		<th width="35">乘机人</th>
		<th width="40">航程</th>
		<th width="35">采购金额</th>
		<th width="40">采购科目</th>
		<th width="40">采购来源</th>
		<th width="60">采购流水号</th>
		<th width="60">采购订单号</th>
		<th width="45">出票时间</th>
		<th width="35">航空公司编码</th>
		<th width="55">采购异常类型</th>
		<th width="35">处理状态</th>
		<th width="65">处理说明</th>
		<th width="45">处理时间</th>
		<th width="50">处理人</th>
		<th width="45">创建时间</th>
		<th width="50">创建人</th>
		<th width="50">异常说明</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
		<tr class="tr">
			<td class="td_center">{{ i+1 }}</td>
			<td class="td_center">
				{{# if(d.list[i].clZt == '0'){ }}
				<a onclick="cancel('{{d.list[i].id}}');" style="cursor: pointer;">取消</a>
				<a onclick="resolve('{{d.list[i].id}}');" style="cursor: pointer;">处理</a>
				<a onclick="modify('{{d.list[i].id}}');" style="cursor: pointer;">修改</a>
				{{# } }}
				{{# if(d.list[i].clZt == '1'){ }}
				<font>取消</font>
				<font>处理</font>
				<font>修改</font>
				{{# } }}
			</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].cplx)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].pnrno)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].cjr)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].hc)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].zfjeAsms)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].cgZfkm)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].cplx)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].cgZflsh)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].ddbh)}}</td>
			<td class="td_center">{{$.dateF(d.list[i].cpDatetime,'MM-dd HH:mm')}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].hkgsPnr)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].cylx)}}</td>
			<td class="td_center">
				{{# var zt;
					if(d.list[i].clZt == "0"){
						zt = "<font>未处理</font>";
					}else if(d.list[i].clZt == "1"){
						zt = "<font>已处理</font>";
					}
				}}
				{{zt}}
			</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].clSm)}}</td>
			<td class="td_center">{{$.dateF(d.list[i].clDatetime,'MM-dd HH:mm')}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].clUserid)}}</td>
			<td class="td_center">{{$.dateF(d.list[i].cjDatetime,'MM-dd HH:mm')}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].cjUserid)}}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].by3)}}</td>
		</tr>
	   {{# } }}
	 </table>
   </script>
    <title>采购对账报表异常</title>
  </head>
  <body>
  	<%@ include file="/WEB-INF/views/cgdzbb/cgdzlable.jsp"%>
    <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/cgdzbb/jpcgdzyc/getCgdzYcList" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="67D26297AFDAEF572A01C57617C6A7F2" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
				<input type="hidden"  name="orderBy" value="cj_Datetime desc" id="orderBy"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="xsys" style="text-align: right;">
					    	<span id="rqsbt">创建日始</span>
					    </td>
						<td>
						  <input type="text" name="ksrq" id="ksrq" value="${empty param.ksrq ? vfn:dateShort():param.ksrq }" onClick="WdatePicker()" size=10>
						</td>
					    <td class="xsys" style="text-align: right;"><span id="rqzbt">创建日止</span></td>
						<td>
						  <input type="text" name="jsrq" id="jsrq" size=10 value="${empty param.jsrq ? vfn:dateShort():param.jsrq }" onClick="WdatePicker()">
						</td>
					 	<td class="xsys" style="text-align: right;">采购异常类型</td>
						<td>
							<!--  <MD:bjcsjb compid="${VEASMS.zgscompid}" var="yclxList" lb="10118"></MD:bjcsjb>-->
							<select name="search_EQ_cylx" style="width:92px">
								<option value="" ${empty param.cylx ? 'selected' : '' }>==请选择==</option>
								<c:forEach items="${yclxList}" var="yclx">
						     		<option value="${yclx.DH}" ${param.cylx eq yclx.DH ? 'selected' : '' }>${yclx.SVALUE}</option>
						     	</c:forEach>
						    </select>
						</td>
						<td class="xsys" style="text-align: right;">处理状态</td>
						<td>
							<select name="search_EQ_cl_zt" style="width:92px">
						     	<option value="" ${empty param.cl_zt ? 'selected' : '' }>==请选择==</option>
						     	<option value="0" ${param.cl_zt eq '0' ? 'selected' : '' }>未处理</option>
						     	<option value="1" ${param.cl_zt eq '1' ? 'selected' : '' }>已处理</option>
						    </select>
						</td>
						 <td class="xsys" style="text-align: right;">日期条件</td>
						<td>
							<select name="rqtj" style="width:92px" onchange="changeRqtj(this.value)">
						     	<option value="0" ${param.rqtj eq '0' ? 'selected' : '' }>创建日期</option>
						     	<option value="1" ${param.rqtj eq '1' ? 'selected' : '' }>处理日期</option>
						     	<option value="2" ${param.rqtj eq '2' ? 'selected' : '' }>出票日期</option>
						    </select>
						 </td>
					  </tr>
					  <tr>
					    <td class="xsys" style="text-align: right;">票证类型</td>
						<td>
							<!--  <MD:bclass var="cplxList" parid="1004"></MD:bclass>-->
			      			<select name="search_EQ_cplx" class="input1" style="width:90px" onchange="setpzlx(this.value)">
			          			<option value="">==全部==</option>
			          			<c:forEach items="${vfc:getVeclassLb('10014')}" var="cplx">
			          				<option value="${cplx.mc}" ${param.search_EQ_cplx eq cplx.mc ? 'selected' : ''}>${cplx.mc}</option>
			          			</c:forEach>
			        		</select>
						</td>
					 	<td class="xsys" style="text-align: right;">
					 		<span id="hsbt">航空公司</span>
					 	</td>
						<td class="tab_in_td_f">
							<input type="text" id="hkgsmc" name="hkgsmc" class="input1" size=10 value="${param.hkgsmc}"/>
							<input type="hidden" name="search_EQ_hkgs" id="hkgs" value="${param.search_EQ_hkgs}">
						</td>
						<td class="xsys" style="text-align: right;">采购科目</td>
						<td>
							<!-- <MD:zffskm defaultkm="${param.cg_zfkm}" nofs="1006305,1006308,1006301" zkfx="" pt="CG" varoption="cg_option" userid="${VEASMS.bh}"></MD:zffskm> -->
							<select name="search_EQ_cgzfkm" style="width:90px;" id="cgzfkm">
							</select>
						</td>
						<td class="xsys" style="text-align: right;">P N R</td> 
						<td class="tab_in_td_f">
						  <input type="text" id="pnrno" name="search_EQ_pnrno" maxlength="10" size="10" value="${param.search_EQ_pnrno}" onblur="value=$.trim(this.value).toUpperCase();" >
						</td>
						<td>
		                  <input type="button" id="searchFormButton" class="ext_btn ext_btn_submit" value="查询">
		                </td>
		                <td>
		                  <input type="button" class="ext_btn ext_btn_success" value="新增异常" onclick="insert();">
		                  <input type="button" class="ext_btn ext_btn_success" value="导出">
		                </td>
					  </tr>
					</table>
	              </form>
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
   </div>
  </div>
  </body>
</html>
