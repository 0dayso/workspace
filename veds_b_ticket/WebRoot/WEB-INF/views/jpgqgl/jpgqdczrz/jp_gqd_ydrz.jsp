<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>订单异动信息</title>
<script type="text/javascript">
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
  		for(var i=0;i<obj.length;i++){
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
  </script> 
<c:set var="cgqx" value="false"></c:set>
<c:set var="xsqx" value="false"></c:set>

</head>
<body>
<logic:present name="logList">
	<table width="99%" border="0" cellpadding="0" cellspacing="0" class="fb_tab" align="center">
      	<tr>
      		<td class="bt">
      			改签单号：${jpGqd.gqdh}&nbsp;&nbsp;&nbsp;&nbsp;
      			PNR&nbsp;&nbsp;:&nbsp;&nbsp;${jpGqd.xsPnrNo}&nbsp;&nbsp;&nbsp;&nbsp;
		      	改签申请时间&nbsp;&nbsp;:&nbsp;&nbsp;${vfn:format(jpGqd.ddsj,'')}
			</td>
      	</tr>
    </table>
    <hr style="width: 100%;height:30px;color:white"/>
    <table width="99%" border="0" cellpadding="0" cellspacing="0" class="its" id="vc" align="center">
    	<thead>
    		<tr>
    			<th>操作时间</th>
    			<th>操作类型</th>
    			<th>操作人</th>
    		</tr>
    	</thead>
 		<tbody>
    		<c:forEach items="${logList}" var="log" varStatus="vs">
    			<tr class="${vs.count%2==0 ? 'even' : 'odd'}" style="cursor: pointer;" title="点击显示或隐藏详细内容">
    				<td align="center" onclick="showNr('${log.id}')">${vfn:format(log.czsj,'')}</td>
    				<td align="center" onclick="showNr('${log.id}')">${log.czly}</td>
    				<td align="center" onclick="showNr('${log.id}')">${log.czr}</td>
    			</tr>
    			<tr id="${log.id}" style="display:none;">
					<td colspan="7" style="text-align: left;background-color: black;color:#0BBF1D;">
	   					 <PRE>${empty log.cznr ? '&nbsp;&nbsp;无内容' : log.cznr}</PRE>
	   				</td>
	   			</tr>
	   			<c:if test="${vs.count eq fn:length(logList)}">
	   				<script type="text/javascript"> 
	   					showNr('${log.id}');
	   				</script>
	   			</c:if>
    		</c:forEach>
		</tbody>
    </table>
    <bean:size id="ssize" name="logList" />
    <logic:greaterThan name="ssize" value="0">
		<script type="text/javascript">          	
			highlightTableRows("log");
		</script>
	</logic:greaterThan>
</logic:present>
<br>
<div align="center">
	<input type="button" name="button" onclick="closePage()" class="ext_btn ext_btn_submit" value="关闭">
</div>
</body>

</html>

