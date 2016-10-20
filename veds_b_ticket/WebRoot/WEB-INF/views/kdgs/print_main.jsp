<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>打印</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<c:set var="khdd" value="${khddList[0]}" scope="request" />
		<style type="text/css">
			.hide_for_jatools_print{}
			.i1 { font-family:楷体;
			    font-size:12px;
			    font-weight:bold;
			    border: 0}
			.i2 {font-size:12px; font-family:楷体;font-weight:bold;border: 0}
			.i3 {font-size:16px; font-family:楷体;
			    font-weight:bold;
			    border: 0}
			.i4 {font-size:16px; font-family:楷体;font-weight:bold;border: 0}
			.jc{
				position:absolute; 
				color:black;
				text-align:left; 
				left:5px;
				top: 35px;
				z-index:100;
			}
		</style>
		<%@include file="/static/core/print_lodop/lodop.jsp" %>
		
		<script type="text/javascript" src="${ctx}/static/js/prints.js"></script>
		<script type="text/javascript">
			//切换模板
			function setopen(mbid){ 
				var type="${param.dytype}";
				if(type&&type>0){
					window.location = "${ctx}/vedsb/xcdgl/printxcd/viewprint?mbid="+mbid+"&ddbhs=${param.ddbhs}&dytype="+type+"&flag=${param.flag}";
				}else{
					window.location = "${ctx}/vedsb/xcdgl/printxcd/viewprint?mbid="+mbid+"&ddbh=${khdd.ddbh}&flag=${param.flag}";
				}
			}
			
			//异步设置默认模板
			function setDefault() {
				var mbid = document.getElementById("kdd").value;
				var url="${ctx}/vedsb/common/ajax/setDefaultMb";
				$.layer({
		    	 area: ['250px', '150px'],
		         dialog: {
		             msg: '确定将其设为公司的默认模板吗?',
		             btns: 2,                    
		             type: 4,
		             btn: ['确定','取消'],
		             yes: function(){
		            	 $.ajax({
		        	 		type:"post",
		  					url:url,
		  					data:{"mbid":mbid},
		  					success:function(result){
		  						if("1" == result){
		  							layer.msg("设置默认模板成功！",2,1);
		  							opener.window.document.getElementById("mbid").value=mbid;
		  						}else{
		  							layer.msg("设置默认模板失败！",2,0);
		  						}
		  					}
		        	 	});
		             }, no: function(){
		                 layer.msg('放弃设置', 2, 3);
		             }
		         }
		     });
				
			}
			//格式化日期格式
			Date.prototype.Format = function (fmt) { //author: heqiwen 
			    var o = {
			        "M+": this.getMonth() + 1, //月份 
			        "d+": this.getDate(), //日 
			        "h+": this.getHours(), //小时 
			        "m+": this.getMinutes(), //分 
			        "s+": this.getSeconds(), //秒 
			        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
			        "S": this.getMilliseconds() //毫秒 
			    };
			    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			    for (var k in o)
			    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			    return fmt;
			}
			
			//保存打印信息(单条/批量)进入待邮寄--邮寄
			function save_yj(ddbh, mbid) {
			    var mid="${mbid}";
			    var flagtype="${size}";
			    if(flagtype>1){//批量打印
			    	var k=0;
			    	<c:forEach items="${khddList}" var="khddone">
				    	if(mid!="1001705"&&mid!="1001706"){
				    		document.getElementById("yjyzbm").value="${khddone.yzbm}";
				    	}
				    	if(mid=="1001700"){
				    		document.getElementById("snxdh").value="${khddone.nxdh}";
				    		document.getElementById("sjr").value="${khddone.sjr}";
				    		document.getElementById("sxjdz").value="${khddone.xjdz}";
				    	}
				    	document.getElementById("sddbh").value="${khddone.ddbh}";
			    		document.getElementById("pnrNo").value="${khddone.xsPnrNo}";
			    		document.getElementById("yjzt").value="${khddone.yjzt}";
			    		document.getElementById("yjsj").value="${empty khdd.yjsj ? today : khdd.yjsj}";
			    		document.getElementById("yjdh").value="${empty khdd.yjdh ? khdd.ddbh : khdd.yjdh}";
			    		var yjsj=document.getElementById("yjsj").value;
			    		var c =new Date(yjsj);
						document.getElementById("yjsj").value=c.Format("yyyy-MM-dd hh:mm:ss");
			    		var formdata= $("#kdformId").serializeArray();
					    formdata.mbid=mbid;
						excuteAddPrint(formdata);
			    	</c:forEach>
			    }else{//打印快递单或合并打印
			    	var yjsj=document.getElementById("yjsj").value;
			    	var c =new Date(yjsj);
		    		var yj=c.Format("yyyy-MM-dd hh:mm:ss");
					document.getElementById("yjsj").value=yj;
			    	var formdata= $("#kdformId").serializeArray();
				    formdata.mbid=mbid;
				    if(ddbh.indexOf(",")>0){//如果是合并打印
				    	formdata.ddbhs=ddbh;
				    }
					excuteAddPrint(formdata);//0表示可能是单个打印或合并打印
			    }
			    
			}
			//执行保存打印信息(单条/批量)进入待邮寄
			function excuteAddPrint(formdata){
				var sizes="${empty param.ddbhs?"1":param.ddbhs}";
				var size=1;
				var flag="${param.flag}";
				if(sizes!=1){
					size=sizes.split(",").length;
				}
				var url="${ctx}/vedsb/xcdgl/printxcd/addPrintRecord?mbid="+formdata.mbid+"&flag=${param.flag}";
				if(formdata.ddbhs){////合并打印
					url="${ctx}/vedsb/xcdgl/printxcd/addPrintRecord?ddbhs="+formdata.ddbhs+"&mbid="+formdata.mbid;
				}
	   	  	  	$.ajax({
	        	 		type:"post",
	  					url:url,
	  					data:formdata,
	  					success:function(result){
	  						if("1" == result){
	  							layer.msg("打印订单记录成功！",2,1);
	  							opener.window.document.getElementById("mbid").value=formdata.mbid;
	  							if(flag!=1){
	  								opener.window.document.getElementById("daidy").innerHTML="待打印("+(parseInt(opener.window.document.getElementById("daidy1").value)-size)+")";
	  								opener.window.document.getElementById("daiyj").innerHTML="待邮寄("+(parseInt(opener.window.document.getElementById("daiyj1").value)+parseInt(size))+")";
	  							}
	  							opener.window.flush();
	  						}else{
	  							layer.msg("打印订单记录失败！",2,0);
	  						}
	  						//opener.window.location.reload();
	  						window.close();
	  					}
	        	 	});
			}
			
		</script>
	</head>
	<body>
		<c:set var="today" value="${vfn:dateShort()}" />
		<div id="pri">
			<input class="ext_btn_submit" type="button" value="直接打印(${dycs})" onClick="TFD_LodopPrint('P')"> 
			<input class="ext_btn_success" type="button" value="打印调整" onClick="TFD_LodopPrint('S')">  
			<input class="asms_button" type="button" value="跳过打印" onClick="save_yj('${empty param.ddbhs ? khdd.ddbh : param.ddbhs}','${mbid}')"> 
		  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>选取打印类型：</b>
		  	<select name="mbid" style="width:120px;" onchange="setopen(this.value)" id="kdd">
				<option value="">==请选择打印类型==</option>
				<c:forEach items="${vfc:getVeclassLb('10017')}" var="klx">
						<c:if test="${klx.id!='10017'}">
							<option value="${klx.id}"  ${mbid eq klx.id ? 'selected' : '' } > ${klx.mc} </option>
						</c:if>
				</c:forEach>
			</select>
			<input class="ext_btn_submit" type="button" value="设置为默认" onclick="setDefault()"/>
		</div>
		<form name="kuaidiForm" id="kdformId">
			    <input type="hidden" name="shbh" value="${BUSER.shbh}">
			    <c:if test="${mbid ne '1001705' and mbid ne '1001706'}">
			     	<input type="hidden" name="yzbm" id="yjyzbm" value="${khdd.yzbm}">
			     </c:if>
			     <input type="hidden" name="ddbh" id="sddbh" value="${khdd.ddbh}">
			     <input type="hidden" name="pnr_no" id="pnrNo" value="${khdd.xsPnrNo}">
			     <input type="hidden" name="dycs" id="dycs" value="${empty dycs ? '0' : dycs}">
			     <input type="hidden" name="dysj" value="${today}">
			     <input type="hidden" name="dyr" id="yuser" value="${BUSER.bh}">
			     <input type="hidden" name="yjzt" id="yjzt" value="${khdd.yjzt}">
			     <input type="hidden" name="yjr" id="ybh" value="${BUSER.bh}">
			     <input type="hidden" name="yjsj" id="yjsj" value="${empty khdd.yjsj ? today : khdd.yjsj}">
			     <input type="hidden" name="yjdh" id="yjdh" value="${empty khdd.yjdh ? khdd.ddbh : khdd.yjdh}">
			     <c:if test="${empty mbid or mbid eq '1001700'}">
			     	<input type="hidden" name="nxdh" id="snxdh" value="${khdd.nxdh}">
				     <input type="hidden" name="sjr" id="sjr" value="${khdd.sjr}">
				     <input type="hidden" name="xjdz" id="sxjdz" value="${khdd.xjdz}">
				 </c:if>
				<jsp:include page="kdlx/print_kdd_${mbid}.jsp" />
		</form>
		
	</body>
</html>
