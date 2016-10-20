<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>销售办理完成</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/tpd/tpd_je.js"></script>
<script type="text/javascript">
     var  validator;
	//关闭当前弹窗
	function closepj(){
		window.close();
	}
    function toXsWc(){
    	validator = $("#tpdxsshform").Validform({
    		tiptype:4,
			tipSweep:true,
			ajaxPost:true,
			callback:function(transport){
				if(transport.xswc == "true"){
				   window.opener.$("#searchFormButton").click(); 
				   layer.alert("销售办理成功",1, '提示信息',function index(){
								window.close();
               			 });
				}else{
				   layer.alert("销售办理失败<br>"+transport.message,5, '提示信息',function index(){
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
              <form action="${ctx}/vedsb/jptpgl/jptpd/tpdXsWc_${jptpd.tpdh}" class="tpdxsshform" id="tpdxsshform" method="POST">
              <input type="hidden" name="callback" value="flush()"/>
              <input type="hidden" name="id" value="${entity.id}"><!-- id自动生成 -->
              <input type="hidden" name="shbh" value="${entity.shbh}">
              <input type="hidden" name="ywlx" value="国内机票">
              <input type="hidden" name="submitForm" value="SearchForm">
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
               <%@include file="tpd/tpd_cgtk_read.jsp" %>
							               
               <!-- 客户退款 -->
               <%@include file="tpd/tpd_khtk.jsp" %>
               
               <!-- 退款信息 -->
               <%@include file="tpd/tpd_tkxx.jsp" %>
               
               <!-- 备注信息 -->
               <br>
				<table style="width: 100%">
					<tr>
						<td align="center">
						<input type="button" name="button"	onclick="toXsWc()" class="ext_btn ext_btn_submit" value="确认">&nbsp;
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