<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>销售审核</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/tpd/tpd_je.js"></script>
<script type="text/javascript">
     var  validator;
	//关闭当前弹窗
	function closepj(){
		window.close();
	}
    function toXsSh(xsqrtp){
        var cgtj=$("input[name='cgtj']:checked").length>0;
        var cgly ="${jptpd.cgly}";
		if (cgtj && (cgly == "BOP" || cgly == "BSP")) {
			$.layer({
				area : [ '250px', '150px' ],
				dialog : {
					msg : '已勾选与采购办理将取消乘机人座位,是否继续审核?',
					btns : 2,
					type : 4,
					btn : [ '审核', '取消' ],
					yes : function() {
						toSubmit(xsqrtp);
					},
					no : function() {
						layer.msg('放弃审核！', 1, 3);
					}
				}
			});
		}else{
		    toSubmit(xsqrtp);
		}
	}

	function toSubmit(xsqrtp) {
		$("input[name='xsqrtp']").val(xsqrtp);
		validator = $("#tpdxsshform").Validform(
				{
					tiptype : 4,
					tipSweep : true,
					ajaxPost : true,
					callback : function(transport) {
						if (transport.xssh == "true") {
							var tipType = 1;
							var message="销售审核成功";
							if (transport.status == "false") {
								tipType = 5;
								message=message+"<br>"+ transport.message;
							}
							layer.alert(message,tipType, '提示信息', function index() {
							            if(window.opener){
							            	window.opener.$("#searchFormButton").click();
							            }
										window.close();
									});

						} else {
							layer.alert("销售审核失败<br>" + transport.message, 5, '提示信息', function index() {
								window.close();
							});
						}
					},
					beforeSubmit : function(curform) {
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
              <form action="${ctx}/vedsb/jptpgl/jptpd/tpdXsSh_${jptpd.tpdh}" class="tpdxsshform" id="tpdxsshform" method="POST">
              <input type="hidden" name="callback" value="flush()"/>
              <input type="hidden" name="id" value="${entity.id}"><!-- id自动生成 -->
              <input type="hidden" name="shbh" value="${entity.shbh}">
              <input type="hidden" name="ywlx" value="国内机票">
              <input type="hidden" name="xsqrtp" value="">
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
               <%@include file="tpd/tpd_khtk.jsp" %>
               
               <!-- 退款信息 -->
               <c:if test="${jptpd.xsTpzt eq '1'}" >
                 <%@include file="tpd/tpd_tkxx.jsp" %>
               </c:if>
               <!-- 备注信息 -->
               <br>
				<table style="width: 100%">
					<tr>
						<td align="center">
						   	<input type="checkbox" id="cgtj" name="cgtj" value="1" checked>同时采购提交
							<c:if test="${jptpd.cgly eq 'OP'}">
								<input type="checkbox" id="ztpt" name="ztpt" value="1" checked>同时采购提交并提交平台
							</c:if>
							<input type="button" name="button"	onclick="toXsSh('0')" class="ext_btn ext_btn_submit" value="审核">&nbsp;
							<input type="button" name="button"	onclick="toXsSh('1')" class="ext_btn ext_btn_submit" value="审核并与销售确认退票">&nbsp;
							<input type="button" name="button" onclick="closepj();" class="ext_btn" value="关闭">
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