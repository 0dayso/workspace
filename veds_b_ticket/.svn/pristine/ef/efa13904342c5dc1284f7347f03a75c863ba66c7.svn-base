<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>白屏改签</title>
		<script type="text/javascript">
			var pnrnr="";
			var selectedPrice = "";	
			var bpgqzt = "0";
		
			function setSj() {
				var sj = "${jpgqd.nxsj}";
				$("#ctct").val(sj);
			}
			
			function setZl(value) {
				if (value == "1") {
					$("#commandType").val("TRDX");
				} else {
					$("#commandType").val("TKNE");
				}
				getGqBz();
			}
			
			function setSfblbm(value) {
				$("#isRetainPnr").val(value);
			}
			
			$(function(){
				var gqdh = "${jpgqd.gqdh}";
				var url = "${ctx}/vedsb/jpgqgl/jpgqd/checkPnr?gqdh=" + gqdh;
				$.ajax({
        	 		type:"post",
  					url:url,
  					success:function(result) {
  						if (result.CODE == "-1") {
  							$("#td_error").html(result.ERROR);
  							$("#tr_error").show();
  							$("#tr_tq").hide();
  						} else {
  							var isAllPassnerChange = result.ISALLPASSNERCHANGE;
  							var isPnrValid = result.ISPNRVALID;
  							$("#isAllPassnerChange").val(isAllPassnerChange);
  							$("#isPnrValid").val(isPnrValid);
  							if (isPnrValid) {
  								$("#isRetainPnr").val("true");
  								//如果只是部分乘机人改签
  								if (!isAllPassnerChange) {
  									if($("#sfblpnr1")){
  										$("#sfblpnr1").attr("disabled","disabled");
  									}
  									if($("#sfblpnr2")){
  										$("#sfblpnr2").attr("disabled","disabled");
  									}
  								}
  							} else {
  								$("#isRetainPnr").val("false");
  								$("#tr_tq").hide();
  								$("#td_bm").html("2、<span class='red'>原编码无效！</span>");
  								$(".gqtr").show();
  							}
  							pnrnr = result.PNRNR;
  							if (pnrnr != null && pnrnr != "") {
  								$("#sp_pnrnr").show();
  							}
  							getGqBz();
  						}
  					}
        	 	});
			});
			
			function getGqBz() {
				var gqdh = "${jpgqd.gqdh}";
				var url = "${ctx}/vedsb/jpgqgl/jpgqd/getGqBz";
				var commandType = $("#commandType").val();
				var isPnrValid = $("#isPnrValid").val();
				var isRetainPnr = $("#isRetainPnr").val();
				var isAllPassnerChange = $("#isAllPassnerChange").val();
				$.ajax({
        	 		type:"post",
  					url:url,
  					data:"gqdh="+gqdh+"&commandType="+commandType+"&isPnrValid="+isPnrValid+"&isRetainPnr="+isRetainPnr+"&isAllPassnerChange="+isAllPassnerChange,
  					success:function(result) {
  						$("#gq_tb").html(result);
  					}
  				});
			}
			
			//改签
			function toGq(){
				var gqdh="${jpgqd.gqdh}";
				var bpzt = "${jpgqd.bpgqzt}";
				var commandType=$("#commandType").val();
				var isPnrValid=$("#isPnrValid").val();
				var isRetainPnr=$("#isRetainPnr").val();
				var isAllPassnerChange=$("#isAllPassnerChange").val();
				
				// 若上一次改签已成功或失败，则不允许再改签
				if ("1" == bpzt || "2" == bpzt) {
					alert("该改签已执行，不能再次改签！");
					return;
				}
				
				var osict="";
				if(!isPnrValid || isRetainPnr == "false"){
					osict=$("#ctct").val();
					
				} else {
					osict="";
				}
				
				// 执行TKNE指令
				if ("TKNE" == commandType) {
					executeCommand(osict, "");
				} 
				// 执行TRDX并用指定价格封口
				else if ("TRDX" == commandType && "3" == bpgqzt) {
					if (isBlank(selectedPrice)) {
						alert("未确认TRDX价格!");
						return;
					}
					
					executeCommand(osict, selectedPrice);
				}
				// 执行TRDX指令获取价格
				else if ("TRDX" == commandType && "0" == bpgqzt) {
					var url = "${ctx}/vedsb/jpgqgl/jpgqd/getGqPrice?gqdh="+gqdh+"&commandType="+commandType+"&isPnrValid="+isPnrValid+"&isRetainPnr="+isRetainPnr
						+"&isAllPassnerChange="+isAllPassnerChange+"&osict="+osict;
				    $.layer({
						type: 2,
						title: ['确认TRDX价格'],
						area: ['830px', '450px'],
						iframe: {src: url},
						scrollbar: true
				    }); 
				} else {
					alert("未知改签类型调用!");
					return;
				}
			}
			
			// 执行TKNE/TRDX指令并封口
			function executeCommand(osict, selectedPrice) {
				var gqdh="${gqData.t_gqsqb.gqdh}";
				var bpzt = "${gqData.t_gqsqb.bpgqzt}";
				var commandType=$("#commandType").value;
				var isPnrValid=$("#isPnrValid").value;
				var isRetainPnr=$("#isRetainPnr").value;
				var isAllPassnerChange=$("#isAllPassnerChange").value;
				var url = "${ctx}/vedsb/jpgqgl/jpgqd/gq";
				$.ajax({
					type:"post",
  					url:url,
  					data:"gqdh="+gqdh+"&commandType="+commandType+"&isPnrValid="
  					+isPnrValid+"&isRetainPnr="+isRetainPnr+"&isAllPassnerChange="
  					+isAllPassnerChange+"&osict="+osict+"&price="+selectedPrice,
  					success:function(result) {
  						try {
							var steps = result.STEPS;
							var stepKey = "";
							var details = "";
							for (var i = 1; i < parseInt(steps) + 1; i++) {
								stepKey = "XQ_" + i;
								details = json[stepKey];
								if (!isBlank(details)) {
									var statusKey = "STATUS_" + i;
									var stepStatus = json[statusKey];
									if (parseInt(stepStatus) > 0) {
										$("TD" + stepKey).style.backgroundColor = "black";
									}
									$("TD" + stepKey).innerHTML = details;
									$("TR" + stepKey).show();
								} 
							}
					
							$("#gq_sp").hide();
							if (!isBlank(result.ERROR)) {
								alert(result.ERROR);
							}
							if (result.STATUS > 0 && isBlank(result.ERROR)) {
								alert("改签成功");
							}
						} catch (e) {
							alert(e.message);
						}
  					}
				});
			}
		</script>
  	</head>
	<body onload="initPage()">
	  <div style="background: #fff; height:650px;width:100%">
		 <div class="container">
		 <!-- 标签 -->
		 <%@include file="transact_title.jsp"%>
		 <div id="forms" class="mt10">
		        <div class="box">
		          <div class="box_border">
		            <div class="box_center" style=" width:100%; background:#fff ;float:left">
		              <form action="${ctx}/vedsb/jpgqgl/jpgqd/saveBl" id="gqBlForm" method="POST">
		              	  <%--指令 TKNE、TRDX--%>
						  <input type="hidden" name="commandType" id="commandType" value="${jpgqd.gqlx  eq '2' ? 'TRDX' : (sfxzTKNE eq '0' ? 'TKNE' : 'TRDX') }">
						  <%--PNR是否有效--%>
						  <input type="hidden" name="isPnrValid" id="isPnrValid" value="">
						  <%--是否保留原PNR--%>
						  <input type="hidden" name="isRetainPnr" id="isRetainPnr" value="">
						  <%--是否所有人改签--%>
						 <input type="hidden" name="isAllPassnerChange" id="isAllPassnerChange" value="">
			              <input type="hidden" name="id" value="${jpgqd.gqdh}">
			              <input type="hidden" name="close" value="true">
			              
			              <!-- 基本信息 -->
			              <%@include file="gqd/gqd_jbxx_bp.jsp"%>
			              
			              <!-- 乘机人信息-->
			    		  <%@include file="gqd/gqd_cjrxx_bp.jsp"%>
			              
			              <!-- 航程信息-->
			              <%@include file="gqd/gqd_hcxx_bp.jsp"%>
						  
						  <div class="box_right" style=" width:30%; background:#fff ;float:left">
		            	<!-- 改签信息 -->
		            	<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 98%;height:120px;margin-top: 0px;" >
							 <tr>
								<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
									<img src="${ctx}/static/images/wdimages/blxx.png" /><br /> 
									<span style="font-size:14px; font-weight:bold; color:#1195db">改签信息</span>
								</td>
								<td>
									<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
										<tr id="tr_error" style="display: none;">
											<td id="td_error"  class="red"></td>
										</tr>
										<tr id="tr_tq">
											<td >
												<img src="${ctx}/static/images/wdimages/loading.gif" /><span id="sp_dd">正在检查编码是否有效，请稍等...</span>
											</td>
										</tr>
										<tr style="display: none;" class="gqtr">
											<td>1、选择改签指令。</td>
										</tr>
										<tr style="display: none;" class="gqtr">
											<td>
												<input type="radio" name="gqzl" value="1" checked="checked" onclick="setZl(this.value);"/>TRD:X
												<input type="radio" name="gqzl" value="2" onclick="setZl(this.value);"/>TKNE
											</td>
										</tr>
										<tr style="display: none;" class="gqtr">
											<td>
												<span id="td_bm">2、
													<label for="sfblpnr1">
														<input type="radio" name="sfblbm" value="1" checked="checked" onclick="setSfblbm('true');"/>保留原PNR
													</label>
													<label for="sfblpnr2">
														<input type="radio" name="sfblbm" value="2" onclick="setSfblbm('false');"/>不保留原PNR
													</label>
												</span>
											</td>
										</tr>
										<tr style="display: none;" class="gqtr">
											<td>
												OSI CTCT <input type="text" name="ctct" value="" id="ctct" style="height: 20px;width:120px" maxlength="11" >
												<input type="button" value="使用订单联系手机" class="ext_btn ext_btn_submit" onclick="setSj()"/> 
											</td>
										</tr>
									</table>
							 	</td>
						  	</tr>
						  </table>
			            </div>
			              <!-- 改签步骤-->
			              <table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 98%;margin-top: 0px;" >
							 <tr>
								<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
									<img src="${ctx}/static/images/wdimages/blxx.png" /><br /> 
									<span style="font-size:14px; font-weight:bold; color:#1195db">改签步骤</span>
								</td>
								<td>
									<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
										
										<tbody id="gq_tb">
										</tbody>
									</table>
							 	</td>
						  	</tr>
						  </table>
			                
			               <div class="box_footer" align="center">
			               		<input type="button" class="ext_btn ext_btn_submit" value="改签" onclick="toGq()"/>
			               		<input type="button" class="ext_btn ext_btn_submit" value="关闭" onclick="javascript:window.close()"/>
			               </div>
		               </form>
		            </div>
		        </div>
		     </div>
		 </div>
		 </div>
	</body>
</html>
