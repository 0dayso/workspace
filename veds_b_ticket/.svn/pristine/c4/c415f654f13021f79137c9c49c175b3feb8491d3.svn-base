<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <title>机票订单-按订单显示内容设置提醒颜色</title> 
  <script type="text/javascript">
  
  $(function(){
  	var url="${ctx}/vedsb/colorremind/colorremind/query";
  	var bh=$("input[name='bh']").val();
		$.ajax({
			url:url,
			type:"post",
			data:{"bh":bh},
			dataType:"json",
			success:function(result){
				if(result.CODE=="0"){
					var list=result.DATA;
					for(var i=0;i<list.length;i++){
						var cz=list[i].csz2.split("|");
						var bg1=cz[0];
						var bg2=cz[1];
						var bg3=cz[2];
						var name=list[i].csz1;
						$("#"+name+"_dt").css("background-color",bg1);
						$("#"+name+"_ltn").css("background-color",bg2);
						$("#"+name+"_lth").css("background-color",bg3);
						$("#sz_"+name+"_dt").val(bg1);
						$("#sz_"+name+"_ltn").val(bg2);
						$("#sz_"+name+"_lth").val(bg3);
						<c:forEach var="vc" items="${vfc:getVeclassLb('10014')}" varStatus="vs">
							$("#"+name+"_${vc.mc}").val(cz[${vs.index}]);
							$("#"+name+"_${vc.ywmc}").css("background-color",cz[${vs.index}]);
 						</c:forEach>
					}
				}else{
					layer.msg(result.MSG,2,5);
				}
			},
			error:function(){
				layer.msg("获取失败!",2,5);
			}
		});
  });
  
  
  
  function getColor(inp,sp){
  		var url="${ctx}/vedsb/colorremind/colorremind/viewcolor?inp="+inp+"&sp="+sp;
  		$.layer({
				type: 2,
				title: ['颜色选择'],
				area: ['400px', '300px'],
				iframe: {src: url}
		   }); 
  }
  
  function update(){
  	var url="${ctx}/vedsb/colorremind/colorremind/update";
  	$.ajax({
			url:url,
			type:"post",
			data:$("#colorSetForm").serialize(),
			dataType:"json",
			success:function(result){
				if(result.CODE == '0'){
					layer.msg("保存成功",2,1);
				}else{
					layer.alert(result.MSG,2,5);
				}
			},error:function(){
				layer.alert("保存失败",2,5);
			}
		});
  }
  
  function toClose(){
  	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
  }
  </script>
   </head> 
  <body>
  	<form action="" method="post" id="colorSetForm" name="colorSetForm">
  		<input type="hidden" name="bh" value="2024"/>
  		<table class="list_table" align="center" width="100%" style="margin-top:20px;" border="1">
		  	<tr>
		  		<th>设置规则</th>
		  		<th>当天</th>
	            <th>两天内</th>
				<th>两天后</th>
			</tr>
			<tr>
		  		<th class="tab_in_td">起飞时间</th>
		  		<td align="center">
		  			<input type="hidden" name="cfrq" id="sz_cfrq_dt" value=""/>
		  			<span id="cfrq_dt" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
		  			<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色" onclick="getColor('sz_cfrq_dt','cfrq_dt')" src="${ctx}/static/images/edit.png"/>
		  		</td>
		  		<td align="center">
		  			<input type="hidden" name="cfrq" id="sz_cfrq_ltn" value=""/>
		  			<span id="cfrq_ltn" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
		  			<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色" onclick="getColor('sz_cfrq_ltn','cfrq_ltn')" src="${ctx}/static/images/edit.png"/>
		  		</td>
	            <td align="center">
	            	<input type="hidden" name="cfrq" id="sz_cfrq_lth" value=""/>
	            	<span id="cfrq_lth" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
	            	<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色" onclick="getColor('sz_cfrq_lth','cfrq_lth')" src="${ctx}/static/images/edit.png"/>
	            </td>
			</tr>
			<tr>
		  		<th class="tab_in_td">预订时间</th>
		  		<td align="center">
		  			<input type="hidden" name="ydsj" id="sz_ydsj_dt" value=""/>
		  			<span id="ydsj_dt" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
		  			<img style="margin-left:5px; cursor: pointer;" title="点击修改颜色" onclick="getColor('sz_ydsj_dt','ydsj_dt')" src="${ctx}/static/images/edit.png"/>
		  		</td>
		  		<td align="center">
		  			<input type="hidden" name="ydsj" id="sz_ydsj_ltn" value=""/>
		  			<span id="ydsj_ltn" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
		  			<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色" onclick="getColor('sz_ydsj_ltn','ydsj_ltn')" src="${ctx}/static/images/edit.png"/>
		  		</td>
	            <td align="center">
	            	<input type="hidden" name="ydsj" id="sz_ydsj_lth" value=""/>
	            	<span id="ydsj_lth" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
	            	<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色"  onclick="getColor('sz_ydsj_lth','ydsj_lth')" src="${ctx}/static/images/edit.png"/>
	            </td>
			</tr>
			<tr>
		  		<th class="tab_in_td">NO位时间</th>
		  		<td align="center">
		  			<input type="hidden" name="nosj" id="sz_nosj_dt" value=""/>
		  			<span id="nosj_dt" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
		  			<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色" onclick="getColor('sz_nosj_dt','nosj_dt')" src="${ctx}/static/images/edit.png"/>
		  		</td>
		  		<td align="center">
		  			<input type="hidden" name="nosj" id="sz_nosj_ltn" value=""/>
		  			<span id="nosj_ltn" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
		  			<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色" onclick="getColor('sz_nosj_ltn','nosj_ltn')" src="${ctx}/static/images/edit.png"/>
		  		</td>
	            <td align="center">
	            	<input type="hidden" name="nosj" id="sz_nosj_lth" value=""/>
	            	<span id="nosj_lth" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
	            	<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色"  onclick="getColor('sz_nosj_lth','nosj_lth')" src="${ctx}/static/images/edit.png"/>
	            </td>
			</tr>
		</table>
			
		<table class="list_table" align="center" width="100%" style="margin-top:20px" border="1">
			<caption style="font-weight:bold">采购来源设置</caption>
			
			
		  	<tr>
		  		<th></th>
		  		<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
			   		<c:if test="${oneLx.id ne '10014'}">
						<th>${oneLx.ywmc}</th>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<th class="tab_in_td">票证类型</th>
				<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
					<c:if test="${oneLx.id ne '10014'}">
						<td align="center">
		  					<input type="hidden" name="pzlx" id="pzlx_${oneLx.mc}" value=""/>
		  					<span id="pzlx_${oneLx.ywmc}" style="height:16px;width:16px;">&nbsp;&nbsp;</span>
		  					<img style="margin-left:5px;cursor: pointer;" title="点击修改颜色"  onclick="getColor('pzlx_${oneLx.mc}','pzlx_${oneLx.ywmc}')" src="${ctx}/static/images/edit.png"/>
		  				</td>
		  			</c:if>
				</c:forEach>
			</tr>
	  	</table>
	  	<div align="center" style="margin-top:60px;vertical-align: bottom">
		  	<input type="button" id="searchFormButton" name="button" value="保存" onclick="update()"  class="ext_btn ext_btn_submit" />
		  	<input type="button" name="cancel" value=" 关 闭 " class="ext_btn" onClick="toClose();">
		</div>
  	</form>
  </body>
</html>