<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>订单异动信息</title>
<script type="text/javascript">

	$(function(){
		showNr("");
	});

    //关闭页面
	function closePage(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	}

	var pre;
	function highlightTableRows(tableId) { 
    	var previousClass = null;   
    	var table = document.getElementById(tableId);
		if(table==null||table==undefined||table==""){
			return;
		}
    	var tbody = table.getElementsByTagName("tbody")[0];   
    	if (tbody == null) {   
         	rows = table.getElementsByTagName("tr");   
    	} else {   
         	rows = tbody.getElementsByTagName("tr");   
    	}   
    	for (i=0; i < rows.length; i++) {
     		if(rows[i].id == ""){  
	        	rows[i].onmouseover = function() { 
	        		if(this.style.backgroundColor!='#ccff99' && this.style.backgroundColor!='#e7c1ff'){
	        			this.style.backgroundColor="#99ccff";
	        		}  
	        	}  
	        	rows[i].onmouseout = function() { 
					if(this.style.backgroundColor!='#ccff99' && this.style.backgroundColor!='#e7c1ff'){
	         			this.style.backgroundColor="";
	         		}
				}  
	        	rows[i].onclick = function() { 
	        		if(pre!=null){
	        			pre.style.backgroundColor="#E7C1FF"
	        		}
	        		pre=this;  
	         		this.style.backgroundColor="#CCFF99";   
	        	}
	    	}   
    	}   
	}

	function showNr(id){
  		var obj = document.getElementById("vc").getElementsByTagName("tr");
  		var len=obj.length;
  		if(id == ""){
  		   $("#"+obj[len-1].id).show();
  		}else{
	  		for(var i=0;i<len;i++){
	  			if(obj[i].id == id){
	  			    var display = $("#"+obj[i].id).css('display');
	  				if(display == 'none'){
	  					$("#"+obj[i].id).show();
	  				}else{
		  				$("#"+obj[i].id).hide();
	  				}
	  			}
	  		}
  		}
  	}
  	
  </script> 
<c:set var="cgqx" value="false"></c:set>
<c:set var="xsqx" value="false"></c:set>

</head>
<body>
<logic:present name="logList">
	<table width="99%" border="0" cellpadding="0" cellspacing="0" class="list_table" align="center">
      	<tr>
      		<td class="bt">
      			${param.ywlx eq '01' ?  "订单编号：" : ""}
      			${param.ywlx eq '02' ?  "退票单号：" : ""}
      			${param.ywlx eq '03' ?  "改签单号：" : ""}
      			${ywdh}&nbsp;&nbsp;&nbsp;&nbsp;
      			PNR：${bean.xsPnrNo}&nbsp;&nbsp;&nbsp;&nbsp;
      			PNR创建时间：${vfn:format(bean.ddsj,'')}
			</td>
      	</tr>
    </table>
    <table width="99%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="vc" align="center">
    	<thead>
    		<tr>
    			<th width="120px">操作时间</th>
    			<th>操作类型</th>
    			<th>操作人</th>
    			<th>操作商户</th>
    			<th>操作部门</th>
    		</tr>
    	</thead>
 		<tbody>
    		<c:forEach items="${logList}" var="log" varStatus="vs">
    			<tr class="${vs.count%2==0 ? 'even' : 'odd'}" style="cursor: pointer;" title="点击显示或隐藏详细内容">
    				<td align="center" onclick="showNr('${log.ID}')" >${vfn:format(log.CZSJ,'')}</td>
    				<td align="center" onclick="showNr('${log.ID}')">${log.CZLY}</td>
    				<td align="center" onclick="showNr('${log.ID}')">${log.CZR} ${log.EX.CZR.xm}</td>
    				<td align="center" onclick="showNr('${log.ID}')">${log.EX.CZR.shshb.mc}</td>
    				<td align="center" onclick="showNr('${log.ID}')">${log.EX.CZR.bmmc}</td>
    			</tr>
    			<tr id="${log.ID}" style="display:none;">
					<td colspan="7" style="text-align: left;background-color: black;color:#0BBF1D;">
	   					 <PRE>${empty log.CZNR ? '&nbsp;&nbsp;无内容' : log.CZNR}</PRE>
	   				</td>
	   			</tr>
    		</c:forEach>
		</tbody>
    </table>
    <bean:size id="ssize" name="logList" />
    <logic:greaterThan name="ssize" value="0">
		<script type="text/javascript">          	
			highlightTableRows("vc");
		</script>
	</logic:greaterThan>
</logic:present>
<br>
<div align="center">
	<input type="button" name="button" onclick="closePage()" class="ext_btn ext_btn_submit" value="关闭">
</div>
</body>

</html>

