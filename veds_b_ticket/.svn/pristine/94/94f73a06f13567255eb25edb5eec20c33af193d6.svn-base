<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
<head>
<title>填写订单信息</title>
<script src="${ctx}/static/core/js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/core/data/gn_city.js?v=${VERSION}"></script>
<script type="text/javascript" src="${ctx}/static/core/data/gj_city.js?v=${VERSION}"></script>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>

<script type="text/javascript">
var validator;
$(function(){
	validator = $("#saveSgdForm").Validform({
		tiptype:4,
		ajaxPost:true,
		callback:function(transport){
			if(transport.CODE=="0"){
			   if(confirm("手工单保存成功！")){
			   		window.close();
			   }
			}else{
			   alert(transport.MSG);
			}
		}
	});
})

    function saveSgd(){
     	validator.submitForm(false);
    }
   
   
	function showCity(nameId, valueId){
	    $("#"+nameId).autocompleteGnCity(valueId);
	}
	
	var myindex =0;
	/**
	 * 为table指定行添加一行
	 * tab 表id
	 * mb  td模板
	 * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
	 * trHtml 添加行的html代码
	 */
	function addTr(tab, mb, row){
	   var $tr=$("#"+tab+" tr").eq(row);
	   var size = $tr.size();
	   var temp = $("#"+mb).html();
	   $tr.after(temp.replace(new RegExp("___", 'g'), (++myindex)));
	}
	  
	function delTr(tableId, obj){
		var lengh =$("#"+tableId+" tr").length;
		if(lengh <= 2){
			alert("至少保留一条记录");
			return;
		}
		$(obj).parent().parent().remove();
	}
	//计算金额
	function jsje(index){
		var xsj=$("#cjr_xsj"+index).val();
		var jsf=$("#cjr_jsf"+index).val();
		var tax=$("#cjr_tax"+index).val();
		$("#fpj_hj"+index).html(Number(xsj)+Number(jsf)+Number(tax));
	}
</script>
</head>
<body>

<!-- 防止重复提交 -->
<c:set value="new" var="new" scope="session"></c:set>
<c:set value="${lx }" var="lx"></c:set>
<c:set var="cjrList" value="${pnrObject.cjrlist }"></c:set>
<c:set var="hdList" value="${pnrObject.hdblist }"></c:set>
<form action="${ctx}/vedsb/jpddgl/jpkhdd/saveSgd" name="saveSgdForm" method="post" id="saveSgdForm">
	<input type="hidden" name="iftb" id="iftb"><%-- 是否团编 --%>
	<input type="hidden" name="pnr_no" value="${kh_khdd_pn.pnr_no }" id="pnr_no">
	<input type="hidden" name="hkgs_pnr" value="${kh_khdd_pn.hkgs_pnr }">
	<input type="hidden" name="ddlx" value="1"/>
	<input type="hidden" name="pnr_zt" value="HK"/>
	<input type="hidden" name="ddly" value="1"/>
	<input type="hidden" name="tjlx" value="0"/>
	<input type="hidden" name="ddzt" value="1"/>
	<input type="hidden" name="zcid" id="zcid" value=""/>
	<input type="hidden" name="dp_compid" value="${VEASMS.compid}"/>
	<input type="hidden" name="dp_deptid" value="${VEASMS.deptid}"/>
	<input type="hidden" name="dp_dpyid" id="dp_dpyid" value="${VEASMS.bh}"/>
	<input type="hidden" name="qxjb" id="qxjb" value="${VEASMS.qxjb}"/>
	<input type="hidden" name="pnr_lr" value="手工订单暂无内容"/>
	<input type="hidden" name="platid" value="${kh_khdd_pn.platid }"/>
	<input type="hidden" name="sfbm" value="${kh_khdd_pn.sfbm }"/>
	<input type="hidden" name="khlx" id="khlx" value="${param.khlx }"/>
	<input type="hidden" name="pnr_hcglgj" id="pnr_hcglgj" value="${empty kh_khdd_pn.pnr_hcglgj ? '1' : kh_khdd_pn.pnr_hcglgj }"/>
	<input type="hidden" name="zkfx" id="zkfx" value="${empty kh_khdd_pn.zkfx ? '0' : kh_khdd_pn.zkfx }"/>
	<input name="ct_hyid" id="ct_hyid" type="hidden" value="${kh_khdd_pn.ct_hyid}"/>
	<input type="hidden" name="wjlpjsfjs"  id="wjlpjsfjs" value="${empty kh_khdd_pn_pn.wjlpjsfjs ? '1' : kh_khdd_pn_pn.wjlpjsfjs}"/>
	<input type="hidden" name="ps_pszt"  value="${kh_khdd_pn_pn.ps_pszt}"/>
	<input type="hidden" name="ddfrom"  value="1001104"/>
	<input type="hidden" name="in_zk_fklx"   value="${kh_khdd_pn.in_zk_fklx }"/>
	<input type="hidden" name="ps_pszt"   value="${kh_khdd_pn.ps_pszt }"/>
	<input type="hidden" name="czfrom"   value="ASMS5K后台国内手工单"/>
	<input type="hidden" name="ps_yqrqsj" value="${kh_khdd_pn.ps_yqrqsj}" />
	<input type="hidden" name="version" value="0" />
	<input type="hidden" name="sfsgd" value="1" />
	<input type="hidden" name="xjjlfs" value="${empty kh_khdd_pn.xjjlfs ? '1' : kh_khdd_pn.xjjlfs}" />
	<input type="hidden" name="sjjlfs" value="${empty kh_khdd_pn.sjjlfs ? '1' : kh_khdd_pn.sjjlfs }" />
	<input type="hidden" name="cp_hkgs" id="cp_hkgs" value="${cp_hkgsList[0].EZDH }"/>
	<%--页面传入会将点除以100--%>
	<input type="hidden" name="ddxgtype" value="ddxq" />
	<input type="hidden" name="close" value="" />
	<input type="hidden" name="mkbh" value="${param.mkbh }" />
	<input type="hidden" name="cgmk_gn" value="${param.cgmk_gn }" />
	<input type="hidden" name="cgmk_gj" value="${param.cgmk_gj }" />
	<input type="hidden" name="xsmk" value="${ param.xsmk}" />
	
	<%--调度段--%>
	<input type="hidden" name="ps_compid_dd" value=""/>
	<input type="hidden" name="ps_deptid_dd" value=""/>
	<input type="hidden" name="ddtzbz" value=""/>
	<input type="hidden" name="ds_compid_dd" value=""/>
	<input type="hidden" name="ds_deptid_dd" id="ds_deptid_dd" value=""/>
	<input type="hidden" name="dp_compmc" value=""/>
	<input type="hidden" name="dp_deptmc" value=""/>
	<input type="hidden" name="dp_dpyid" value=""/>
	<input type="hidden" name="jsfl" value="0">
	<input type="hidden" name="bxjsj" value="0">
	<input type="hidden" name="ddlx_dd" value="0">
	<input type="hidden" name="ddtzfs" value="0">
	<input type="hidden" name="ifxf_dd" value="0">
	<input type="hidden" name="openlx" value="1"><!-- 1 保存 2 本部门出票  3或4保存并调度  -->
	
	<input type="hidden" name="by02" id="by02" value="${kh_khdd_pn.by02 }">
	<input type="hidden" name="gjsdn" id="gjsdn" value="${gjFee}"/>
	<input type="hidden" name="gjsdw" id="gjsdw" value="${gjsdwFee}"/>
	<input type="hidden" name="gnfwf1" id="gnfwf1" value="${gnFee }"/>
	<input type="hidden" name="zgs" id="zgs" value="${VEASMS.zgscompid }"/>
	<%--其它--%>
	
	<%-- 错误信息(用于提取出错时提示) --%> 
	<logic:present name="error">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="t_tab">
		 <tr>
		    <td>
		    <FONT color="red">${error}</FONT>
		    </td>
		  <tr>
	</table>
	</logic:present>
	<%--pnr信息--%>
	<%@include file="sgd/sgd_pnrxx.jsp" %>
	
	<%--客户信息--%>
	<%@include file="sgd/sgd_wdxx.jsp" %>
	
	<%--航程信息--%>
	<%@include file="sgd/sgd_hcxx.jsp" %>
	
	<%--乘机人信息--%>
	<%@include file="sgd/sgd_cjrxx.jsp" %>
	
	<%--出票信息--%>
	<%@include file="sgd/sgd_cpxx.jsp" %>
	
	 <!--邮寄信息-->
     <%@include file="sgd/sgd_yjxx.jsp"%>
     
     <!--备注信息-->
     <%@include file="sgd/sgd_bzxx.jsp"%>

</form>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="4">
  <tr>
    <td><div align="center">
      <input type="button" class="ext_btn ext_btn_submit" value="保 存" onclick="saveSgd()"> 
       <input type="button" class="ext_btn ext_btn_submit" value="关 闭" onclick="javascript:window.close()">
    </div></td>
  </tr>
</table>
</body>
</html>
