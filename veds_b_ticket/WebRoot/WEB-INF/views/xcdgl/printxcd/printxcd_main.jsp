<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
  <head>
    <title>电子客票报销凭证</title>
	<%@include file="/static/core/print_lodop/lodop.jsp" %>
	<script type="text/javascript" src="${ctx}/static/js/xcdprint/xcdprint.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/xcdprint/xcdprintinfo.js"></script>
	<script type="text/javascript">
		function enterFontTestPage(){
		    var url = "${ctx}/vedsb/xcdgl/printxcd/enterFontTestPage";
  	 		$.layer({
				type: 2,
				title: ['<b>字体加载测试</b>'],
				area: ['650px', '500px'],
				iframe: {src: url}
		   });
		}
		
		function enterPrintSmPage(){
		    var url = "${ctx}/vedsb/xcdgl/printxcd/enterPrintSmPage";
  	 		$.layer({
				type: 2,
				title: ['<b>字体加载测试</b>'],
				area: ['650px', '530px'],
				iframe: {src: url}
		   });
		}
		
		//打印测试
		function printXcdTest(obj,gngj,gp_no){
			formPageCnt=0;
			var url = "${ctx}/vedsb/xcdgl/printxcd/printXcdTest";
		    $.ajax({  
                    url:url,  
                    success:function(data){  
                        alert(data);  
                    }  
                });
		}
		

		//显示行程单号 
		//1显示可修改  可修改所有内容
		//0显示不可修改   可选择行程单号
		//2不显示不可修改  不显示
		//4显示所有内容都不能修改  
		//5只能订单管理打印
		/*
		function openxcdh(){	
			var url = "/vedsb/xcdgl/printxcd/openxcdh";
			var data={"asmsAndagent":asmsAndagent,"xcdlyfs":xcdlyfs};
			$.layer({
				type: 2,
				title: ['<b>选择行程单号</b>'],
				area: ['650px', '530px'],
				iframe: {src: url}
		   });
		}

		//更改行程单号
		function setxcdno(xcdh){
			$("xcdhTemp").value=xcdh;
			$("xcdh"+currenttkno).value=xcdh;
			setYzm();
		}*/
		
		
		/************创建行程单 START**********/
		var createTknos=new Array();
		var createTknosIndex=0;
		var createSuccessFlag=true;//全部创建成功
		var createFailureTknoArr=new Array();//创建失败的票号
		var createFailureXcdhArr=new Array();//创建失败的行程单
		var createFailureTipArr=new Array();//创建失败的信息
		
		function createXcd2(){
			var tkno = $("#electronicTicket").val();
			if(tkno == ""){
					alert("请选择票号后，再创建行程单！");
				return ;
			}
			//初始化创建失败信息
			createSuccessFlag=true;
			createFailureTknoArr=new Array();//创建失败的票号
			createFailureXcdhArr=new Array();//创建失败的行程单
			createFailureTipArr=new Array();//创建失败的信息
			//disabledButton(0);
			reqCreateXcd2(tkno);
		}
		
		//创建行程单请求
		function reqCreateXcd2(tknoTemp){
			var xcdh = $("#xcdhTemp").val();
			var url="/vedsb/xcdgl/printxcd/printXcdTest";
			var url="/vedsb/xcdgl/yjxcd/cancelYj";
			var ii = layer.load('系统正在处理您的操作,请稍候!');
  			$.ajax({
        	 		type:"post",
  					url:url,
  					data:{"tkno":tkno,"xcdh":xcdh},
  					success:function(transport){
  						layer.close(ii);
  						var info=transport.responseText;
  						if(info!="OK"){
  							layer.msg(infos,2,0);
  						}else{
  							layer.msg("创建行程单成功！",2,1);
  						}
  					}
        	 	});
			return;
		}
		
		/************打印行程单 START**********/
		var printTknos=new Array();
		var printTknosIndex=0;
		function printXcd(){
			var tknoTemp = $("#electronicTicket").val();
			if(tknoTemp == ""){
				alert("请选择票号后，再创建行程单！");
				return ;
			}
			var alreadyload=$("xcdprintinfoshowDIV"+tknoTemp).alreadyload;
			//调用JAVA组织数据格式
			if(alreadyload=="true"){
				var formid="xcdform"+tknoTemp;
				if($(formid)){
					printTknos.push(tknoTemp);
					setXcdztSpanInnerHTML("等待打印","",tknoTemp);
				}else{
					setXcdztSpanInnerHTML("打印出错","票号"+tknoTemp+"无打印数据,请核实",tknoTemp);
				}
			}
			disabledButton(0);
			printAjax(tknoTemp,getprintAction());
		}
		
		
		//调用打印
		function printAjax(tkno,action){
			var formid="xcdform"+tkno;
			var xcdh=$("xcdh"+tkno).value
			var formObj= $(formid);
			if(formObj){
				formObj.action='/eterm/cpzx/printxcd_main.shtml?p=printXcd&asmsAndagent='+asmsAndagent+"&xcdh="+xcdh+"&realtkno="+tkno+"&sfcp=${sfcp}";
			    $(formid).request({
				    asynchronous:true,
					onComplete: function(transport){
					var text = transport.responseText;	
						if(text.indexOf("操作失败,原因")==0){
							setXcdztSpanInnerHTML("打印出错",text,tkno);
						}else{
						   var json = eval("("+text+")"); 
						   var jphcglgj=$("jphcglgj"+tkno).value;
						   var rtn =  xcdPrint_lodop(json,action,jphcglgj,"");
					       if(rtn>0){//打印成功
						   		setXcdztSpanInnerHTML("打印完成","",tkno);
						   }else{
						   		setXcdztSpanInnerHTML("打印出错","系统打印驱动运行异常",tkno);
						   }
						}
						printTknosIndex++;
						if(printTknosIndex<printTknos.length){
							printAjax(printTknos[printTknosIndex],action);
						}else{
							disabledButton(1);
							printTknos=new Array();
							printTknosIndex=0;
						}
					}
				}
			}
		}
		
		/*
		function updZhZt(id, bca, sfsy) {
		url = "/vedsb/b2bsz/jpb2bdlzh/saveB2bDlzh";
		var hkgs = $("#hkgs").val();
			$.ajax({
	     		type : "post",
	     		url : url,
	     		data : "id=" + id + "&bca=" + bca + "&hkgs=" + hkgs + "&sfsy=" + sfsy,
	     		success:function(result){
	     			if ("1" == result) {
	     				 layer.msg("修改成功！",2,1);
	     				 location.reload();
	     			} else {
	     				 layer.msg("修改失败！",2,1);
	     			}
	     		}
			})
		}
		*/
	</script>
  </head>
  <body>
  	
    <c:set var="tkno_or_cjr" value="${sfcp eq 'N' ? '2' : '1'}"></c:set><!-- 票号列表或者乘机人列表 1票号 2乘机人 -->
	 	<div style="height:99%">
	 		 <table class="jc2" border="0" align="center" cellpadding="0" cellspacing="1" style="width:70%;margin-left:30%;border:0px solid #5A7D4A; top:40px;height:20px">
				<tr style="text-align:right">
					<td>
						<span style="font-weight:bold;font-size:14px;color:black">航空运输电子客票行程单</span>
						<c:if test="${tkno_or_cjr eq '1'}">
							<c:if test="${not empty MK418746.ve_mkgnMap['04'] || param.asmsAndagent eq 'agent'}">
								<input type="button" id="btnRePrint" value="重新打印" class="asms_button" style="width:60px;display:none;" onclick="reprint();">
							</c:if>
							<c:if test="${not empty MK418746.ve_mkgnMap['05'] || param.asmsAndagent eq 'agent'}">
								<input type="button" id="btnDetr" value="DETR校正" title="通过DETR，DETR,S，DETR,F指令从航信获取数据,更新行程单打印内容;<br>建议经本系统创建行程单,可以节省流量" class="asms_button" style="width:60px;" onclick="detrUpdateData();">
							</c:if>
						</c:if>
						<span  style="margin:0px 10px;">
							<!-- onchange="setxcdno(this.value);"  ondblclick="openxcdh();"-->
							行程单号：<input type="text" title="双击选择行程单号"  id="xcdhTemp"   class="input1" style="width:90px;color:#00f;" value="${param.xcdh}"  maxlength="10"/>
							<c:if test="${not empty MK418746.ve_mkgnMap['06'] || param.asmsAndagent eq 'agent'}">
								 <input type="button" id="btnZf" value="作废" class="asms_button" style="width:40px;" onclick="zfxcd();">
							</c:if>
						</span>
					</td>
				</tr>
			</table>	
	 		
	 		
	 		<div id="allDetailDiv" style="height:320px">
	 			  <span id="emptyXcdPage" disabled="disabled">
		 			 	<%@include file="printxcd_detail.jsp" %>
		 		  </span>
		 		  <c:forEach items="${tknoList}" var="tkno" varStatus="v">
		 		    <c:if test="${not empty tkno.TKNO and fn:length(tkno.TKNO) eq 10}">
		 		  	     <c:set var="xcdDIVid">${tkno.SZDH }${tkno.TKNO }</c:set>
		 		    </c:if>
		 		    <c:if test="${empty tkno.TKNO or fn:length(tkno.TKNO) ne 10}">
		 		   		 <c:set var="xcdDIVid">${tkno.ID }</c:set>
		 		    </c:if>
		 			<div id="xcdprintinfoshowDIV${xcdDIVid }" style="display:none" alreadyload="false"></div>
		 		 </c:forEach>
	 		 </div>
	 		 
	 		 <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" style="border:1px dotted gray;">
				<tr><td align="right">
					<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
				      <tbody>
				        <tr>
				          <td align="left">
				         	&nbsp;&nbsp;&nbsp;温馨提示：
				          </td>
				          <td align="right">
				          	<span title="勾选表示每次打印前都进行预览"><input type="checkbox" id="asms_xcd_preprint_box" title="勾选表示每次打印前都进行预览" onclick="setPreprint();" >预览打印</span>
				          	&nbsp;
				            <span title="勾选表示固定使用210*102大小的行程单纸张，注意有个别打印机不支持，请不要勾选。"><input type="checkbox" id="asms_xcd_pagesize_fixed_box" title="勾选表示固定使用210*102大小的行程单纸张，注意有个别打印机不支持，请不要勾选。" onclick="setPageFontSize();" >行程单纸张</span>
					       &nbsp;  字体
					       <select onchange="setPageFontSize();" id="asms_xcd_pagesize_font_select">
					           <option value="TEC">航信行程单字体</option>
					           <option value="2">方正姚体简体</option>
					           <option value="3">长城小姚体</option>
					           <option value="4">宋体</option>
				           </select>
					        &nbsp;  大小
					        <select onchange="setPageFontSize();" id="asms_xcd_pagesize_fontsize_select">
					           <option value="14">14</option>
					           <option value="15">15</option>
					           <option value="16">16</option>
					           <option value="17">17</option>
					           <option value="18">18</option>
				           </select>
				           &nbsp;
				             <c:if test="${param.asmsAndagent eq 'asms'}">
					    		 <c:if test="${false and not empty MK418746.ve_mkgnMap['07'] and param.asmsAndagent eq 'asms'}">
					     		 	<input onclick="printSet();" value="打印参数设置" type="button" class="asms_button" style="width:80px;" id="printSetBtn">
					    		 </c:if>
				    		 </c:if>
				          </td>
				        </tr>
				      </tbody>
				    </table>
				</td></tr>
				<tr>
				  <td colspan="2">
					 &nbsp;&nbsp;&nbsp;如果没有安装打印控件或没有安装字体点击此处<a href="javascript:void(0);" onclick="enterFontTestPage();"><font color="red">[下载安装]</font></a>
					      ，第一次使用请务必阅读<a class="red" href="javascript:void(0)" onclick="enterPrintSmPage();">[行程单打印使用说明]</font></a>
				  </td>
				</tr>
				
				<tr>
					<td align="center">
						<input onclick="printXcdTest(this,'1','');" value="国内票模板" title="点击调整国内机票的打印模板，点击应用即可保存" type="button" class="ext_btn ext_btn_submit" id="printTestBtn1">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input onclick="printTestAjax(this,'0','');" value="国际票模板" title="点击调整国际机票的打印模板，点击应用即可保存" type="button" class="ext_btn ext_btn_submit" id="printTestBtn3">
					</td>
				</tr>
			</table>
	 	</div>
 	</div>
<input id="tkno_or_cjr" type="hidden" value="${tkno_or_cjr }">	
<input type="hidden" name="asmsAndagent" value='${param.asmsAndagent}'>  
<input type="hidden" id="xcdlyfs" value="${xcdlyfs }" />
<input type="hidden" id="cs_czb2b_a" value="${cs_czb2b_a }" />
<input type="hidden" id="cs_czb2b_b" value="${cs_czb2b_b }" />
<input type="hidden" id="cs7734" value="${cs_dyfxtjp }" />
<script type="text/javascript">
</script>	
</body>
</html>
