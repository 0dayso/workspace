<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	$(function(){	
		
	    $("#searchFormButton").click();
	});
	//点到期open票设置方式的单选框
	function switchFs(smlx){
		if(smlx=="0"){//所有平台统一控制
			$("#cglytysz").show();
			$("#bycglysz").hide();
		}else{
			$("#cglytysz").hide();
			$("#bycglysz").show();
		}
	}
	//点按外出票单位控制的复选框
	function startWcpdw(id,cgly,wdid){
	     var c=$("#wcpdw_"+id).attr("checked");
		if(c){
			$("#detr_type_"+id).show();
		} else {
			$("#detr_type_"+id).hide();
		}
	}
	
	//保存
	function toSave(){
		var url = "${ctx}/vedsb/bbzx/jpsmfssz/saveSmfs?";
		//是否开启
		var sfkq=$("input[name='sfkq']:checked").val();
		//pid账号
		var pidyhbh=$("#pidyhbh").val();
		//pid密码
		var pidmm=$("#pidmm").val();
		//提前天数
		var tqts=$("#tqts").val();
		if(tqts>365 || tqts<0 || tqts==''){
			
			$("#errorSpan").show();
			return;
		}
		url+="&sfkq="+sfkq+"&tqts="+tqts+"&pidyhbh="+pidyhbh+"&pidmm="+pidmm;
		var fs=$("input[name='all_pt']:checked").val();
		if(fs=="0"){
			if($("input[name='detr_type']:checked").val()=='0'){
				if(pidyhbh==''){
					alert("pid账号不能为空！");
					return false;
				}
				if(pidmm==''){
					alert("pid密码不能为空！");
					return false;
				}
			}
			url += "&detr_type=" + $("input[name='detr_type']:checked").val();
			url += "&all_pt=" + fs;
		}else{
			if($("input[name='sfkq']:checked").val()=='1'){
				 var length=$("input[name='wdid']:checked").length;
		    
			    if(length==0){
			    	alert("请选择网店！");
			    	return;
			    }
			}
		   
		    var flag=false;
			$("input[name='wdid']:checked").each(function() {
						var detr_lx = $(this).attr("detr_lx");
						url += "&cgly=" + $(this).attr("cgly");
						url += "&wdid=" + $(this).val();
						url += "&detr_lx="+ $("input[name='" + detr_lx + "']:checked").val();
						if($("input[name='" + detr_lx + "']:checked").val()=='0'){
							if(pidyhbh==''){
								alert("pid账号不能为空！");
								flag=true;
								return false;
							}
							if(pidmm==''){
								alert("pid密码不能为空！");
								flag=true;
								return false;
							}
						}
					});
			}
			if(flag){
				return;
			}
			var ii = layer.load('系统正在处理您的操作,请稍候!');
			$.post(url, function(result) {
				alert(result);
				window.location.reload();
			});

	}
	//点击是否显示提前天数
	function showTqts(num){
		if(num==0){
			$("#tqtsSpan").hide();
			$("#detr_table").hide();
		}
		if(num==1){
			$("#tqtsSpan").show();
			$("#detr_table").show();
		}
	}
</script>
<style type="text/css">
	input {
  		vertical-align:middle;
	}
</style>
</head>
<body>
<c:set var="iftykz">${(empty list[0].SZWDID or list[0].SZWDID eq '---' ) ? 0 : 1  }</c:set> 
<div class="container_clear">
<div class="box_border">
	<form action="${ctx}/vedsb/bbzx/jpsmfssz/saveSmfs" method="POST" name="csszForm" id="csszForm">
			<input type="hidden" name="turningUrl" value="${ctx}/vedsb/bbzx/jpsmfssz/viewlist">
			<c:set var="smlxTmp" value="0" />
			<table class="t_tab" align="center" width="700px" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" style="height: 25px;">
						&nbsp;&nbsp;开启扫描：
						<input type="radio" name="sfkq" ${empty jpsmsjsz.sfkq || jpsmsjsz.sfkq eq '0' ? 'checked':''} value="0" onclick="showTqts(0);">否
						<input type="radio" name="sfkq" ${jpsmsjsz.sfkq eq '1' ? 'checked':''} value="1" onclick="showTqts(1);">是
					</td>
				</tr>
				<tr id="tqtsSpan" style="display: ${empty jpsmsjsz.sfkq or jpsmsjsz.sfkq eq '0' ? 'none':''}">
					<td width="220px">
						&nbsp;&nbsp;PID账号：
						<input type="text" size="16" id="pidyhbh" maxlength="20" name="pidyhbh" value="${jpsmsjsz.pidyhbh}"/>
					</td>
					<td width="220px">
						PID密码：
						<input type="password" size="16" id="pidmm" maxlength="20" name="pidmm" value="${jpsmsjsz.pidmm}"/>
					</td>
					<td>
						提前天数：
						<input type="text" id="tqts" name="tqts" value="${empty jpsmsjsz.tqts ?'0':-jpsmsjsz.tqts}" maxlength="3" size="2" />
						<span id="errorSpan" style="color: red;display: none">提前天数范围0~365</span>
					</td>
				</tr>
				</table>
				<table class="t_tab" id="detr_table" style="display: ${empty jpsmsjsz.sfkq || jpsmsjsz.sfkq eq '0' ? 'none':''}" align="center" width="700px" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<strong>&nbsp;&nbsp;OPEN票DETR方式</strong>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;
						<input type="radio" name="all_pt" id="all_pt_0"
						value="0" ${iftykz eq '0' ? 'checked' : '' } onClick="switchFs(this.value);" />
						所有采购来源网店统一控制
					 	&nbsp;&nbsp;&nbsp;
					 	<input type="radio" name="all_pt" id="all_pt_1" value="1" 
				     	${iftykz eq '1'  ? 'checked' : '' } onClick="switchFs(this.value);" />
				     	按采购来源网店控制
				     </td>
				</tr>
				
				<tr id="cglytysz" style="display: ${iftykz eq '1' ? 'none': ''} ">
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="detr_type"
						id="detr_type_1" value="0" ${empty list[0].DETR_LX or list[0].DETR_LX eq '0' ? 'checked' : ''}/>
						本地PID
						<input type="radio" name="detr_type"
						id="detr_type_2" value="1" ${list[0].DETR_LX eq '1' ? 'checked' : ''}/>
						CPS接口
					</td>
				</tr>
				<tr id="bycglysz" style="display: ${iftykz eq '0' ? 'none':''}">
			   		 <!-- 左边的列表 -->
			    	<td>
		    		<table class="list_table" style="width: 350px">
		    			<tr>
		    				<th width="65px">采购来源</th>
		    				<th width="120px;">网店</th>
		    				<th style="width: 160px;">DETR方式</th>
		    			</tr>
						<c:set var="wcpdwAllSize" value="${fn:length(list)}"></c:set>
						<c:set var="wcpdwSize"><fmt:formatNumber type='number' value='${wcpdwAllSize/2}' maxFractionDigits='0'/></c:set>
							<c:forEach items="${list}" var="vc" varStatus="s">
								<c:if test="${s.index lt wcpdwSize}">
									<c:if test="${(s.index)%(len)==0}">
										<tr>
											<td rowspan="${len+1}" align="center" style="border-bottom:2px green solid;">
												<strong>${vc.EX.CGLY.mc}</strong>
											</td>
										</tr>
									</c:if>
									<tr>
										<td style="${ (s.index+1)%(len)==0 ? 'border-bottom:2px green solid;' : ''}">
											<input type="checkbox" value="${vc.EX.WDID.id}" name="wdid" id="wcpdw_${s.index }_${vc.CGLY}" 
											onclick="startWcpdw('${s.index}_${vc.CGLY}');"  cgly="${vc.CGLY}" detr_lx="detr_type_${s.index}_${vc.CGLY}"  ${empty vc.SZWDID ? '':'checked'}/>
											${vc.EX.WDID.wdmc}
										</td>
										<td style="${ (s.index+1)%(len)==0 ? 'border-bottom:2px green solid;' : ''}" align="center">
											<span id="detr_type_${s.index}_${vc.CGLY}" style="display:${empty vc.SZWDID ? 'none':''};">
												<input type="radio" name="detr_type_${s.index}_${vc.CGLY}" id="detr_type_${s.index}_${vc.CGLY}_1" 
												value="0" ${vc.DETR_LX eq '0' or empty vc.DETR_LX  ? 'checked':''} onclick="setDetr_lx($(this),'${s.index}_${vc.CGLY}');"/>本地PID
												<input type="radio" name="detr_type_${s.index}_${vc.CGLY}" id="detr_type_${s.index}_${vc.CGLY}_2" 
												value="1" ${vc.DETR_LX eq '1' ? 'checked':''} onclick="setDetr_lx($(this),'${s.index}_${vc.CGLY}');"/>CPS接口
											</span>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
			    	</td>

			    	<!-- 右边的列表 -->
			    	<td >
			    		<table class="list_table" style="width: 350px;">	
			    			<tr>
			    				<th width="65px">采购来源</th>
		    					<th width="120px;">网店</th>
		    					<th style="width: 160px;">DETR方式</th>
		    				</tr>
							<c:forEach items="${list}" var="vc" varStatus="s">
								<c:if test="${s.index gt wcpdwSize-1}">
									<c:if test="${(s.index)%(len)==0}">
										<tr>
											<td rowspan="${len+1}" align="center" style="border-bottom:2px green solid; ">
												<strong>${vc.EX.CGLY.mc}</strong>
											</td>
										</tr>
									</c:if>
									<tr>
										<td style="${ (s.index+1)%(len)==0 ? 'border-bottom:2px green solid;' : ''} ">
											<input type="checkbox" value="${vc.EX.WDID.id}" name="wdid" id="wcpdw_${s.index }_${vc.CGLY}" 
											onclick="startWcpdw('${s.index}_${vc.CGLY}');"  cgly="${vc.CGLY}" detr_lx="detr_type_${s.index}_${vc.CGLY}"  ${empty vc.SZWDID ? '':'checked'}/>
											${vc.EX.WDID.wdmc}
										</td>
										<td style="${ (s.index+1)%(len)==0 ? 'border-bottom:2px green solid;' : ''}" align="center">
											<span id="detr_type_${s.index}_${vc.CGLY}" style="display:${empty vc.SZWDID ? 'none':''};">
											<input type="radio" name="detr_type_${s.index}_${vc.CGLY}" id="detr_type_${s.index}_${vc.CGLY}_1" 
											value="0" ${vc.DETR_LX eq '0' or empty vc.DETR_LX  ? 'checked':''} onclick="setDetr_lx($(this),'${s.index}_${vc.CGLY}');"/>本地PID
											<input type="radio" name="detr_type_${s.index}_${vc.CGLY}" id="detr_type_${s.index}_${vc.CGLY}_2" 
											value="1" ${vc.DETR_LX eq '1' ? 'checked':''} onclick="setDetr_lx($(this),'${s.index}_${vc.CGLY}');"/>CPS接口
											</span>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
				</td>
				</tr>
		</table>
		<table align="center" width="700px">
			<tr>
				<td align="center"  colspan="2">
					<input type="button"  onclick="toSave()" class="ext_btn ext_btn_success" value="保存" style="margin-top:5px;margin-bottom:5px;margin-left:10px;">
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>
</body>
</html>