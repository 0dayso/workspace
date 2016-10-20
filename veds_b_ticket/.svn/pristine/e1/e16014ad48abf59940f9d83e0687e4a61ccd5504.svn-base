<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>退票申请</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">

	var validator;
	$(function(){
	   validator = $("#tfdSaveForm").Validform({
	   	tiptype:3
	   });
	});
	
	function tpxx() {
		var url = "viewtpd_add_tpxx?ajax=true";
		$("#tpxx_div").load(url, function() {
			loadApplyData();
		});
	}
	
	function selectHcOrCjr() {
	    var hds = $("input[name='hcCheckBox']:checked").length;
		var cjrs = $("input[name='cjrCheckBox']:checked").length;
		
		if (( hds > 0 || $("input[name='hcCheckBox']").length == 0) && cjrs > 0 ) {
			$("#tpxx_div").show();
			$("#savebutton").show();
			tpxx();
		} else {
			$("#tpxx_div").hide();
			$("#savebutton").hide();
		}
	}

	//全选
	function selectAll(obj, type) {
		$('input[type="checkbox"][name="' + type + 'CheckBox"]').each(
		function() {
			$(this).attr("checked", obj.checked);
		});
		selectHcOrCjr();
	}
	
	function setDisable() {
		$('input[type="checkbox"][name="cjrCheckBox"]').each(function() {
			var tkno = $(this).attr("tkno");
			var isCheck = false;
			isCheck = !$(this).prop("checked");
			$("#tkno" + tkno).attr("disabled", isCheck);
			$("#cjrlx" + tkno).attr("disabled", isCheck);
			$("#cjr" + tkno).attr("disabled", isCheck);
			$("#zjlx" + tkno).attr("disabled", isCheck);
			$("#zjhm" + tkno).attr("disabled", isCheck);
			$("#sjhm" + tkno).attr("disabled", isCheck);
			$("#xb" + tkno).attr("disabled", isCheck);
			$("#gj" + tkno).attr("disabled", isCheck);
			$("#csrq" + tkno).attr("disabled", isCheck);
			$("#zjyxq" + tkno).attr("disabled", isCheck);
			$("#zjqfg" + tkno).attr("disabled", isCheck);
			$("#wbcjrid" + tkno).attr("disabled", isCheck);
			$("#xs_cw" + tkno).attr("disabled", isCheck);
			$("#cg_cw" + tkno).attr("disabled", isCheck);
		});

	}

	function modifyAttribute(name, isReadonly) {
		if (isReadonly) {
			$(name).attr("readonly", "readonly");
			$(name).addClass("red");
		} else {
			$(name).removeAttr("readonly");
			$(name).removeClass("red");
		}
	}

	function loadApplyData() {
		//判断是否按航段退票
		var ishdtp = true;//true按航段退票，false非航段退票
		if($("input[name='hcCheckBox']").length == 0){
			ishdtp=false;
		}else{
			 //获取选中的票号对应的机票航段
			$('input[type="checkbox"][name="cjrCheckBox"]:checked').each(function() {
				var tkno = $(this).attr("tkno");
				var ktphd = $("input[name='tkhdid_" + tkno + "']").length;//可退票航段数
				var selecttphd = $("input[name='hcCheckBox']:checked").length;//选择的退票航段数
				if (ktphd == selecttphd) {
					ishdtp = false;
				}
			}); 
		}


		if (ishdtp) {
		    modifyAttribute("#xsZdj",false);
		    modifyAttribute("#xsJsf",false);
		    modifyAttribute("#xsTax",false);
		
			$("#cgZdj").val("0");
			$("#cgPj").val("0");
			$("#cgJsf").val("0");
			$("#cgTax").val("0");
			$("#cgTpfl").val("0");
			$("#cgTpf").val("0");

			$("#xsZdj").val("0");
			$("#xsPj").val("0");
			$("#xsJsf").val("0");
			$("#xsTax").val("0");
			$("#xsTpfl").val("0");
			$("#xsTpsxf").val("0");

		} else {

            modifyAttribute("#xsZdj",true);
		    modifyAttribute("#xsJsf",true);
		    modifyAttribute("#xsTax",true);
		
			//加载采购信息和客户信息
			<c:forEach  begin="0" end="1" items="${jpList}" var="vc" >
			$("#cgZdj").val(getXfs("${vc.CG_ZDJ}"));
			$("#cgPj").val(getXfs("${vc.CG_PJ}"));
			$("#cgJsf").val(getXfs("${vc.CG_JSF}"));
			$("#cgTax").val(getXfs("${vc.CG_TAX}"));

			var cgTpfl = "${vc.CG_TPFL}";
			if ($.isEmptyObject(cgTpfl)) {
				cgTpfl = "0";
				$("#cgTpfl").val(cgTpfl);
			}

			var cgTpf = "${vc.CG_TPF}";
			if ($.isEmptyObject(cgTpf)) {
				cgTpf = "0";
				$("#cgTpf").val(cgTpf);
			}

			$("#xsZdj").val(getXfs("${vc.XS_ZDJ}"));
			$("#xsPj").val(getXfs("${vc.XS_PJ}"));
			$("#xsJsf").val(getXfs("${vc.XS_JSF}"));
			$("#xsTax").val(getXfs("${vc.XS_TAX}"));
			var xsTpfl = "${vc.XS_TPFL}";
			if ($.isEmptyObject(xsTpfl)) {
				xsTpfl = "0";
				$("#xsTpfl").val(xsTpfl);
			}

			var xsTpsxf = "${vc.XS_TPSXF}";
			if ($.isEmptyObject(xsTpsxf)) {
				xsTpsxf = "0";
				$("#xsTpsxf").val(xsTpsxf);
			}
			</c:forEach>
		}

		ytje();

	}

	//计算应退金额
	function ytje() {
		var cgZdj = parseFloat($("#cgZdj").val());
		var cgPj = parseFloat($("#cgPj").val());
		var cgJsf = parseFloat($("#cgJsf").val());
		var cgTax = parseFloat($("#cgTax").val());
		var cgTpf = parseFloat($("#cgTpf").val());

		var cgTkje = (cgPj + cgJsf + cgTax + cgTpf);
		$("#cgTkje").html(cgTkje);

		var xsZdj = parseFloat($("#xsZdj").val());
		var xsPj = parseFloat($("#xsPj").val());
		var xsJsf = parseFloat($("#xsJsf").val());
		var xsTax = parseFloat($("#xsTax").val());
		var xsTpsxf = parseFloat($("#xsTpsxf").val());

		var xsTkje = (xsPj + xsJsf + xsTax + xsTpsxf);
		$("#xsTkje").html(xsTkje);

		//合计列
		var ps = $("input[name='cjrCheckBox']:checked").length;
		$("#hj_cgZdj").html(cgZdj * ps);
		$("#hj_cgPj").html(cgPj * ps);
		$("#hj_cgJsf").html(cgJsf * ps);
		$("#hj_cgTax").html(cgTax * ps);
		$("#hj_cgTpf").html(cgTpf * ps);
		$("#hj_cgTkje").html(cgTkje * ps);

		$("#hj_xsZdj").html(xsZdj * ps);
		$("#hj_xsPj").html(xsPj * ps);
		$("#hj_xsJsf").html(xsJsf * ps);
		$("#hj_xsTax").html(xsTax * ps);
		$("#hj_xsTpsxf").html(xsTpsxf * ps);
		$("#hj_xsTkje").html(xsTkje * ps);

	}

	function toTpfl(name) {
		var Zdj = parseFloat($("#" + name + "Zdj").val());
		var Tpfl = parseFloat($("#" + name + "Tpfl").val());
		if (name == "cg") {
			$("#cgTpf").val(getZs(Zdj * Tpfl / 100));
		}
		if (name == "xs") {
			$("#xsTpsxf").val(getZs(Zdj * Tpfl / 100));
		}
		ytje();
	}

	
	function modifyXsJg(name){
	
	     if($(name).prop("readonly")){
	         return;
	     }
       
	    var result=$(name).val();
		switch (name) {
			case "#xsZdj": {
				$("#xsPj").val(result);
				$("#cgZdj").val(result);
				$("#cgPj").val(result);
				break;
			}
			case "#xsJsf": {
			    $("#cgJsf").val(result);
			  break;
			}
			case "#xsTax": {
			    $("#cgTax").val(result);
			  break;
			}
		}
	    ytje();
	}
	
	//求相反数
	function getXfs(num){
		return 0-num;
	}
	
	//转换成负数
	function getFs(num){
		if(parseFloat(num) > 0){
			return 0-num;
		}
		return parseFloat(num);
	}
	
	//转换成正数
	function getZs(num){
		if(parseFloat(num) < 0){
			return Math.abs(num);
		}
		return parseFloat(num);
	}
	//点确认
	function toSearch(){
		if(!$("#tkkmid").val()){
			layer.msg("没有选择退款科目,请先选择!",2,0);
			return;
		}
		setDisable();
		var ii = layer.load('系统正在处理您的操作,请稍候!');
			var url="${ctx}/vedsb/jptpgl/jptpdapply/createTpd";
			$.ajax({
		            type: "POST",
		            url: url,
		            data:$('#tfdSaveForm').serialize(),
		            success: function(data){
		            			layer.close(ii);
		            			if(data=='ok'){
		            				layer.msg("退票申请成功！",2,1);
		            				window.location.href="${ctx}/vedsb/jptpgl/jptpd/viewlist?gngj=1";
		            			}else{
		            				layer.msg("退票申请失败!",2,0);
		            			}
		                     }
		        });

	}
</script>
</head>
<body>
<%@include file="add/tpd_add_jbxx.jsp"%>
<form id="tfdSaveForm" name="tfdSaveForm" action="" method="post" >
	<input type="hidden" name="p" value="save">
	<c:if test="${param.openlx eq '1'}">
		<!-- 弹出窗口 -->
		<input type="hidden" name="submitForm" value="listForm">
		<input type="hidden" name="close" value="true">
	</c:if>
	<c:if test="${param.ddfrom eq 'jpztjk'}">
		<!-- 机票状态监控 弹出窗口 -->
		<input type="hidden" name="submitForm" value="listForm">
		<input type="hidden" name="close" value="true">
	</c:if>
	<input type="hidden" name="czfrom" value="ASMS退废票按单申请">
	<input type="hidden" name="ddbh" value="${jpkhdd.ddbh}">
	<input type="hidden" name="tbAll" value="1">
	<input type="hidden" name="sfdd" value="0">
	<input type="hidden" name="sffwf" value="0">
	<input type="hidden" name="lxkp" value="0">
	<input type="hidden" name="sfzdd" value="${param.isFromWebZd}">
	<input type="hidden" name="zddbh" value="${param.zddbh}">
	<input type="hidden" name="zddlx" value="${param.zddlx}">
	<input type="hidden" name="ywlx" value="${param.ywlx}">
	<input type="hidden" name="ifzdqxzw" value="1">
	
	<!-- 退票信息 -->
	<%@include file="add/tpd_add_tfxx.jsp"%>
	
	<!-- 航程信息 -->
	<%@include file="add/tpd_add_hcxx.jsp"%>
	
	<!-- 机票信息 -->
	<%@include file="add/tpd_add_jpxx.jsp"%>
	
    <div id="tpxx_div"></div>
	
	<br>
	
	<!-- 退票备注 -->
	<%@include file="add/tpd_add_bzbz.jsp"%>
	
	<table style="width: 100%">
		<tr>
			<td align="center">
			<input type="button" id="savebutton" class="ext_btn ext_btn_submit" value="确认" onclick="toSearch();">&nbsp;
			<input type="button" id="closebutton" onclick="history.go(-1);" class="ext_btn" value="返回"></td>
		</tr>
	</table>
	
</form>
</body>

</html>