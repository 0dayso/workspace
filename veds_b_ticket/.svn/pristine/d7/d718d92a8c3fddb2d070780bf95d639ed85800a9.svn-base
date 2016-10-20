<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>退票详</title>
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
//关闭当前弹窗
	function closepj(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
	
	function sqBcd(tpdh){
	   var url ="${ctx}/vedsb/jpbcd/jpbcd/toedit?ddbh="+tpdh+"&ywlxs=tpdd";
	   $.layer({
			type: 2,
			title: ['<b>新增补差单</b>'],
			area: ['370px', '190px'],
			iframe: {src: url}
	   });
	}
</script>
</head>
<body>
<c:set var = "ywdh" value = "${jptpd.tpdh }"></c:set>
<div style="background: #fff; height:650px;width:100%">
 <div class="container">
 <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_center" style=" width:100%; background:#fff ;float:left">
              <form action="" class="jqtransform" id="wdzlsz" method="POST">
              <input type="hidden" name="callback" value="flush()"/>
              <input type="hidden" name="id" value="${entity.id}"><!-- id自动生成 -->
              <input type="hidden" name="shbh" value="${entity.shbh}">
              <input type="hidden" name="ywlx" value="国内机票">
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
               <%@include file="tpd/tpd_khtk_read.jsp" %>
               
               <!-- 退款信息 -->
               <%@include file="tpd/tpd_tkxx.jsp" %>
                <%-- 补差信息 --%>
				<c:if test="${not empty bcdList}">
					<%@include file="jpkhdd_bcdxx.jsp"%>
				</c:if>
               <!-- 备注信息 -->
               
                <!--签注信息-->
               <div class="box_top"><b class="pl15">签注信息</b></div>
               <%@include file="/WEB-INF/views/common/jpddqz.jsp"%>
               <br>
				<table style="width: 100%">
					<tr>
						<td align="center">
						<input type="button" name="button"	onclick="sqBcd('${jptpd.tpdh}')" class="ext_btn ext_btn_submit" value="申请补差单">&nbsp;
						<input type="button" name="button" onclick="javascript:window.close()" class="ext_btn" value="关闭"></td>
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