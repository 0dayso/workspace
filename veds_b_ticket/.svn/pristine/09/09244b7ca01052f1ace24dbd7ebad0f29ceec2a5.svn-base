<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
	var validator;
	$(function(){
		
		validator = $("#zdcpB2bzhForm").Validform({
			tiptype:3,
			beforeSubmit:function(curform){
				layer.load('系统正在处理您的操作,请稍候!');
			}
		});
		
		//航司控件
	    //$("#gn_hkgs_m").autocompleteGnHkgs("gn_hkgs");
	    
	    var url="${ctx}/vedsb/b2bsz/jpb2bhkgsxx/query";
	    $.ajax({
				type:"post",
				url:url,
				data:{bca:"720102"},
				success:function(result){
	           	 	if(result.CODE == '0'){
	           	 		var list=result.DATA
	           	 		for(var i=0;i<list.length;i++){
	           	 			var $opt="<option value="+list[i].hkgs+">"+list[i].hkgs+" "+list[i].mc+"</option>";
	           	 			$("#hkgs").append($opt);
	           	 		}
	           	 	}
				}
			});
	    
	    
	    $("#searchFormButton").click();
	});
	//保存数据
	function saveJpzdcpb2bzh(){
		var hkgs=$("#hkgs").val();
		if(hkgs==null || hkgs == ''){
			layer.msg("请选择航空公司!", 2, 3);
			return ;
		}
		if('${entity.id}' != ''){
			var zzh=$("#zzh").val();
			var byzh=$("#byzh").val();
			var zflist = "";
			if(zzh == null || zzh == "" || zzh == undefined){
				layer.msg("未查询到支持该航空公司的账号,请先设置!", 2, 3);
				return ;
			} 
			zflist=zzh;
			if(zzh == byzh){
				layer.msg("主账号和备用账号不能相同!", 2, 3);
				return ;
			}
			if(byzh != null && byzh != "" && byzh != undefined){
				zflist=zzh+","+byzh;
			}
      	    $("#zfid").val(zflist);
		}
		
		validator.submitForm(false);
	}
	
	function getZfzhxx(){
		$("#addTr").html("");
		var hkgs=$("#hkgs").val();
		if(hkgs == null || hkgs == ""){
			return null;
		}
		var url="${ctx}/vedsb/b2bsz/jpzdcpb2bzh/getZfzhxx?bca=${param.by1 }&hkgs="+hkgs;
		$.ajax({
			type:"post",
			url:url,
			success:function(data){
				if(data){
					$("#addTr").html(data);
				}
			}
		});
	}
	
	//关闭弹出层
	function closeJpzdcpb2bzh(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
</script>
</head>
<body>
<div class="container_clear">
	<div class="box_border">
		<form action="${ctx}/vedsb/b2bsz/jpzdcpb2bzh/save" class="jqtransform" id="zdcpB2bzhForm" method="POST">
		<input type="hidden"  name="id" value="${entity.id }"/>
		<input type="hidden"  name="by1" value="${param.by1 }"/>
		<input type="hidden"  id="zfid" name="zfid" value="${entity.zfid }"/>
		<input type="hidden" name="callback" value="parent.location.reload();"/>
			<table class="form_table pt15 pb15" border="0" cellpadding="0" cellspacing="0">	
				<tr>
					<td class="td_right" style="width: 20%;">航空公司:</td>
		           	<td class="td_left" style="width: 50%;">
		           		<c:if test="${not empty entity.id }">
							<input type="text" value="${vfc:getHkgs(entity.hkgs).shortname}" disabled="disabled" class="input-text lh25"/>
							<input type="hidden" id="hkgs" name="hkgs" value="${entity.hkgs }" />
							<font color="red">*<span id="error1"></span></font>
						</c:if>
						<c:if test="${empty entity.id }">
							<!-- 
							<input type="text" id="gn_hkgs_m" value="${vfc:getHkgs(entity.hkgs).shortname}" class="input-text lh25 srk" onblur="getZfzhxx();clearValue(this,'gn_hkgs');" style="width: 20%;"/>
							<input type="hidden" id="gn_hkgs" name="hkgs" value="${entity.hkgs }" />
							<font color="red">*</font>
							 -->
							 <select id="hkgs" name="hkgs" onchange="getZfzhxx();" class="select" >
							 	<option value="">=请选择=</option>
							 </select>
						</c:if>
		          	</td>
		        </tr>
		        <tr>
					<td class="td_right">航空公司登录账号:</td>
		           	<td class="td_left">
						<input type="text" name="zh" value="${entity.zh }" datatype="*" class="input-text lh25" />
						<font color="red">*</font>
		          	</td>
		        </tr>
		        <tr>
					<td class="td_right">航空公司登录密码:</td>
		           	<td class="td_left">
						<input type="password" id="mm" name="mm" value="${entity.mm }" datatype="*" class="input-text lh25" />
						<font color="red">*</font>
		          	</td>
		        </tr>
		        <c:if test="${param.by1 eq '720102'}">
			        <tr>
						<td class="td_right">OFFICE号:</td>
			           	<td class="td_left">
							<input type="text" id="office" name="office" value="${entity.office }" datatype="*" class="input-text lh25"/>
							<font color="red">*</font>
			          	</td>
			        </tr>
		        </c:if>
		        <c:if test="${not empty entity.id}">
					<tr>
						<td class="td_right">主账号:</td>
						<td class="td_left">
							<select name="zzh" id="zzh" style="width:170px;height: 24px;">
								<c:forEach var="item" items="${zfzhList}">
									<option value="${item.id}" ${item.id == zzh ? "selected" : ""}>${item.zfzh}[${item.zflxName}]</option>
								</c:forEach>
							</select>
							<font color="red"> *</font>
						</td>
					</tr>
					<tr>
						<td class="td_right">备用账号:</td>
						<td class="td_left">
							<select name="byzh" id="byzh" style="width:170px;height: 24px;">
								<option value="">== 请选择 ==</option>
								<c:forEach var="item" items="${zfzhList}">
									<option value="${item.id}" ${item.id == byzh ? "selected" : ""}>${item.zfzh}[${item.zflxName}]</option>
								</c:forEach>
							</select>
							<span>主账号余额不足,使用备用账号</span>
						</td>
					</tr>
				</c:if>
        		<tr>
        			<td id="addTr" colspan="2"></td>
        		</tr>
		        <tr>
				  	<td class="td_right">简要说明:</td>
				  	<td>
				  		<textarea name="sm" rows="3" cols="30" style="resize: none;" >${entity.sm}</textarea>
				  	</td>
			  	</tr>
		        <tr>
					<td colspan="2" align="center">
						<input type="button" onclick="saveJpzdcpb2bzh()" class="ext_btn ext_btn_submit" value="保 存">&nbsp;
						<input type="button" onclick="closeJpzdcpb2bzh()" class="ext_btn ext_btn_error" value="关闭">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>