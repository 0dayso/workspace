<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>订单一键完成</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<style type="text/css">
.passenger td{text-align:center;}
.wb_td01{ 
	background:#d0f2ff;
}
.wb_td02{ 
	font-size:12px; 
	color:#999;
	white-space:nowrap;
	over-flow:hidden;
}
.wb_td03{ 
	white-space:nowrap;
	over-flow:hidden;
}
.wb_tr01{ border-bottom:1px solid #ccc;}
.btn{ font-size:12px; color:#fff; text-align:center; border:none; background:#1195db; height:25px; width:80px;}
.noncewb{ height:35px; width:73px; background:url(/static/images/wdimages/non_bg.png) no-repate;}
.web_button{height:25px;background:#f1f1f1;border:1px solid #ccc;border-radius:3px;cursor:pointer;}
</style>
<script type="text/javascript">
	var validator;
	$(function(){
		validator = $("#ddWcForm").Validform({
			tiptype:4,
			ajaxPost:true,
			callback:function(transport){
				if(transport.CODE=="1"){
				   window.close();
				   window.opener.$("#searchFormButton").click();
				}else{
				   layer.msg(transport.MSG,2,1);
				}
			}
		});
		checkCplx();
	});

	function saveWc(id){
		var flag=checkNxdh();
   		if(flag){
   			validator.submitForm(false);
   		}
	}
	
	//检查电话号码和联系方式是否都为空
	function checkNxdh(){
		var nxdh=$("#nxdh").val();
   		var nxsj=$("#nxsj").val();
   		if(nxdh=="" && nxsj ==""){
   			$("#skxx_nxdh_error").show();
   			return false;
   		}
   		$("#skxx_nxdh_error").hide();
   		return true;
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
              <form action="${ctx}/vedsb/jpddgl/jpkhdd/saveWc" class="ddWcForm" id="ddWcForm" method="POST">
	              <input type="hidden" name="callback" value="flush()"/>
	              <input type="hidden" name="ddbh" value="${jpkhdd.DDBH}">
	              <input type="hidden" name="shbh" value="${entity.shbh}">
	              <input type="hidden" name="ywlx" value="国内机票">
	              
	               <!--基本信息 -->
	               <%@include file="wc/wc_jbxx.jsp"%>
	               
	               <%--订单信息页签
	               <div class="nav_junior" style="width:100%;" align="center">
						<div class="nav_junior_con" align="center">
							<p>
								<a href="javascript:setWebLx('1');" class="noncewb" id="dd_xq1">订单信息</a>
								<a href="javascript:setWebLx('2');" id="dd_xq2" style="display: none">异动日志</a>
								<a href="javascript:setWebLx('3');" id="dd_xq3" style="display: none">平台日志</a>
								<div id="dd_loadDiv" align="left"><img src="/images/load.gif" />正在为您加载其他信息...</div>
							</p>
						</div>
					</div>--%>
	               
	               <!--航程信息 -->
	               <%@include file="wc/wc_hcxx.jsp"%>
	               
	               <!--乘机人信息 -->
	               <%@include file="wc/wc_cjrxx.jsp"%>
	               
	               <!--出票信息-->
	               <%@include file="wc/wc_cpxx.jsp"%>
	               
	               <!--收款信息-->
	               <%@include file="wc/wc_skxx.jsp"%>
	               
	               	<%-- 补差信息 --%>
					<c:if test="${not empty bcdList}">
						<%@include file="jpkhdd/jpkhdd_bcdxx.jsp"%>
					</c:if>
	               
	               <!--邮寄信息-->
	               <%@include file="wc/wc_yjxx.jsp"%>
	               
	               <!--邮寄信息-->
	               <%@include file="wc/wc_bzxx.jsp"%>
               
	               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="td_center">
								<input type="button" id="searchFormButton" name="button" value="保存" class="ext_btn ext_btn_submit" onclick = "saveWc('${jpkhdd.DDBH}')"/>
								<input type="button" id="searchFormButton" name="button" value="关闭" class="ext_btn ext_btn_submit" onclick="javascript:window.close()"/>
							</td>
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