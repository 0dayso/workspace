<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>采购提交</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/tpd/tpd_je.js"></script>
<script type="text/javascript">
     var  validator;
     //关闭当前弹窗
	function closepj(){
		window.close();
	}
    function toCgTj(){
    	validator = $("#tpdxsshform").Validform({
			tiptype:4,
			tipSweep:true,
			ajaxPost:true,
			callback:function(transport){
				if(transport.status == 'ok'){
				    window.opener.$("#searchFormButton").click(); 
				    layer.alert("采购提交成功",1, '提示信息',function index(){
								window.close();
               			 });
				}else{
				   layer.alert(transport.message,5, '提示信息',function index(){
								window.close();
               			 });
				}
				layer.close(ii);
			},
			beforeSubmit:function(curform){
			    ii = layer.load('系统正在处理您的操作,请稍候!');
		    }
		});
    	validator.submitForm(false);
    }
</script>
</head>
<body>
<div style="background: #fff; height:650px;width:100%">
 <div class="container">
 <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_center" style=" width:100%; background:#fff ;float:left">
              <form action="${ctx}/vedsb/jptpgl/jptpdcg/tpdCgTj_${jptpd.tpdh}" class="tpdxsshform" id="tpdxsshform" method="POST">
              <input type="hidden" name="callback" value="flush()"/>
              <input type="hidden" name="id" value="${entity.id}"><!-- id自动生成 -->
              <input type="hidden" name="shbh" value="${entity.shbh}">
              <input type="hidden" name="ywlx" value="国内机票">
              
              <input type="hidden" name="submitForm" value="searchForm">
	          <input type="hidden" name="close" value="true">
               <!-- 基本信息 -->
               <%@include file="tpd/tpd_jbxx.jsp"%>
               
               <!-- 办理信息 -->
               <%@include file="tpd/tpd_blxx.jsp"%>
               
               <!-- 航程信息 -->
               <%@include file="tpd/tpd_hcxx.jsp"%>
              
               <!-- 机票信息 -->
               <%@include file="tpd/tpd_jpxx.jsp"%>
               
               <c:set var="tfje" value="${mxList[0]}" ></c:set>
               <!-- 采购退款 -->
               <%@include file="tpd/tpd_cgtk.jsp" %>
							               
               <!-- 客户退款 -->
               <%@include file="tpd/tpd_khtk_read.jsp" %>
               
               <!-- 退款信息 -->
               <c:if test="${jptpd.xsTpzt eq '1'}" >
                 <%@include file="tpd/tpd_tkxx.jsp" %>
               </c:if>
               <!-- 备注信息 -->
               <br>
				<table style="width: 100%">
					<tr>
						<td align="center">
						<input type="checkbox" id="ztpt" name="ztpt" value="1"><span title="勾选BOP或BSPET票会执行黑屏退票&#10;BPET票会提交航司退票&#10;OP或TB票会提交到平台退票">提交采购方退票</span>
						<input type="button" name="button"	onclick="toCgTj()" class="ext_btn ext_btn_submit" value="确认">&nbsp;
						<input type="button" name="button" onclick="closepj();" class="ext_btn" value="关闭"></td>
					</tr>
				</table>
			 </form>
            </div>
        </div>
     </div>
 </div>
 </div>
</body>
</html>