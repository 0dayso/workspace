<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>网店设置 -网店资料</title>
<!-- 需要弹出层时用到 -->
<script type="text/javascript" src="${ctx}/static/core/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/static/core/layer/extend/layer.ext.js"></script>
<script type="text/javascript"> 
    //编辑网店
	function _modifyWeb(wdid){
		showIframe("${ctx}/vedsb/jpddsz/jpddsz/pageJpDdsz?&wdid="+wdid+"&timer="+new Date().toString(), "设置机票订单配置信息", 760, 600);
	}
	function showIframe(url,title,w,h){
		$.layer({type: 2,title: title,area: [w, h],iframe: {src: url}});
	}
   function zcdSm(obj,type){
   		var _wdid = $(obj).attr("_wdid");
   		$.ajax({
			type:"post",
			url:"${ctx}/vedsb/jpddsz/jpddsz/smrk?&wdid="+_wdid+"&type="+type+"&timer="+new Date().toString(),
			success:function(result){
				alert(result);
			}	
	  	});
   }
   function cl(obj,type){
   		var _wdid = $(obj).attr("_wdid");
   		var _index = $(obj).attr("_index");
   		var $dhObj = $("#dh"+_index);
   		if($dhObj.is(":hidden")){
   			if(type=='0'||type=='1'||type=='2'||type=='6'){
   				$dhObj.val('${vfn:longDate()}');
   			}
        	$dhObj.show();    //如果元素为隐藏,则将它显现
 		}else{
 			var dh = $dhObj.val();
 			if(dh==""){
 				return;
 			}
	     	$.ajax({
				type:"post",
				url:"${ctx}/vedsb/jpddsz/jpddsz/smrk?&wdid="+_wdid+"&type="+type+"&dh="+dh+"&timer="+new Date().toString(),
				success:function(result){
					alert(result);
				}	
	  		});
 		}
   }   
   
   function refresh(){
	   location.reload();
   }
</script>
</head>
<body >
<form action="${ctx}/vedsb/jpdd/jpddsz/saveJpDdsz" name="ListForm" id="ListForm" method="post"></form>
<form onsubmit="_setAuthId();" action="${ctx}/vedsb/jpdd/jpddsz/saveJpDdsz" method="post">
<input type="hidden" value="" id="edit" name="isEdit"/>
<input type="hidden" value="" id="webId" name="webId"/>
<input type="hidden" value="" id="webName" name="webName"/>
<input type="hidden" value="" id="pt" name="pt"/><!-- 支付平台 -->
<table class="table01">
  <tr>
    <td valign="middle">
     	<!-- <input type="button" onclick="_modifyWeb('add');" class="addButton" value=" 新 增 " /> -->
     	<!-- <input type="button" onclick="_delWeb();" class="delButton" value=" 删 除 " /> -->
	    <!--<input type="button" onclick="_modifyWeb('edit');" class="addButton" value=" 编 辑 "  /> -->
    </td>
  </tr>
</table>
<table id="vc" class="list_table" cellpadding="0" cellspacing="0"  style="width: 80%">
	<thead>
		<tr>
			<th  >网店名称</th>
			<th  >网店平台</th>
			<th  >导单接口账号</th>
			<th  >正常单</br>开启状态</th>
			<th  >退票单</br>开启状态</th>
			<th  >改签单</br>开启状态</th>
			<th  >自动出票</br>开启状态</th>
			<th  >操作</th>
	    </tr>
    </thead>
	<tbody>
	<c:forEach items="${wdzlszList}" var="zl" varStatus="v">
		<tr >
			<td align="center" style="width: 15%">
				<input type="hidden" value="${zl.id }" name="wdid"/>
		    	<input type="hidden" value="${zl.shbh }" name="shbh"/>
		    	<c:set var="jpddsz" value="${jpDdszMap[zl.id]}"></c:set>
		    	<c:set var="hasJpddsz" value="${not empty jpddsz}"></c:set>
				${zl.wdmc }
			</td>
			<td style="width: 10%" align="center" >${zl.wdptmc }</td>
			<td style="width: 15%" align="center" >${hasJpddsz ? jpddsz.ddJkzh : ''}</td>
			<td style="width: 8%" align="center">
			<c:if var="bl1" test="${hasJpddsz and '1' eq jpddsz.ddKqzcdd}">
				<font color="green">开启</font>
			</c:if>
			<c:if test="${!bl1}">
				<font color="red">关闭</font>
			</c:if>
			</td>
			<td style="width: 8%" align="center">
			<c:if var="bl1" test="${hasJpddsz and '1' eq jpddsz.ddKqtpdd}">
				<font color="green">开启</font>
			</c:if>
			<c:if test="${!bl1}">
				<font color="red">关闭</font>
			</c:if>
			</td>
			<td style="width: 8%" align="center">
			<c:if var="bl1" test="${hasJpddsz and '1' eq jpddsz.ddKqgqdd}">
				<font color="green">开启</font>
			</c:if>
			<c:if test="${!bl1}">
				<font color="red">关闭</font>
			</c:if>
			</td>
			<td style="width: 8%" align="center">
			<c:if var="bl1" test="${hasJpddsz and '1' eq jpddsz.ddautocp}">
				<font color="green">开启</font>
			</c:if>
			<c:if test="${!bl1}">
				<font color="red">关闭</font>
			</c:if>
			</td>
		    <td style="" align="center">  
		    	<input type="button" onclick="_modifyWeb('${zl.id }');" class="ext_btn" value=" 编 辑 "  />
		    	<input type="button" class="ext_btn"  _wdid="${zl.id}" _index="${v.index}" value="扫描正常单" onclick="cl(this,'0')" />
    		    <input type="button" class="ext_btn"  _wdid="${zl.id}" _index="${v.index}" value="扫描改签单" onclick="cl(this,'2')" />
    			<input type="button" class="ext_btn"  _wdid="${zl.id}" _index="${v.index}" value="扫描退票单" onclick="cl(this,'1')" />
    			<input type="button" class="ext_btn"  _wdid="${zl.id}" _index="${v.index}" value="注册派单JOB" onclick="cl(this,'6')" />
    			<input type="text" style="display: none" size="16" class="input1" id="dh${v.index}" value=""/>
		    	<!-- 
			    	<input type="button" class="ext_btn"  _wdid="${zl.id}" value="扫描改签单" onclick="cl(this,'2')" />
		    		<input type="button" class="ext_btn"  _wdid="${zl.id}" value="扫描退票单" onclick="zcdSm(this,'1')" />
			    	<input type="button" class="ext_btn"  _wdid="${zl.id}" value="扫描正常单" onclick="zcdSm(this,'0')" />
			    	<input type="button" class="ext_btn"  _wdid="${zl.id}" value="扫描票号回填" onclick="zcdSm(this,'3')" />
			    	<input type="button" class="ext_btn"  _wdid="${zl.id}" _index="${v.index}" value="退废单复核" onclick="cl(this,'4')" />
			    	<input type="button" class="ext_btn"  _wdid="${zl.id}" _index="${v.index}" value="改签单处理" onclick="cl(this,'5')" />
			    	<input type="text" style="display: none" size="8" class="input1" id="dh${v.index}" value=""/>
		    	 -->
		    </td>
		</tr>
		</c:forEach>
    </tbody>
</table>
</form>
 

</body>
</html>
