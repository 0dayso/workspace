<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>改签详情</title>
		<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
		<script type="text/javascript">
			var validator;
			var ii;
			function save() {
				if ($("#dh").val() == "" && $("#sj").val() == "") {
					alert("手机和电话必填一个!");
					return;
				}
				validator.submitForm(false);
			}
			
			$(function(){
				validator = $("#gqForm").Validform({
					tiptype:4,
					ajaxPost:true,
					callback:function(transport){
						layer.close(ii);
						if(transport.CODE=="0"){
						   if(confirm("改签详保存成功！")){
						   		window.close();
						   }
						}else{
						   alert(transport.MSG);
						}
					  },
					beforeSubmit:function(curform){
						//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
						//这里明确return false的话表单将不会提交;
						ii = layer.load('系统正在处理您的操作,请稍候!');
					}
				});
			})
			
			function toJc() {
				var hclx = "${jpKhddMap.HCLX}";
				var ddbh = "${jpKhddMap.DDBH}";
				var gqdh = "${jpgqd.gqdh}";
				if (hclx == "1") {
					var url ="${ctx}/vedsb/jpgqgl/jpgqd/downGrading?ddbh=" + ddbh;
				    $.layer({
						type: 2,
						title: ['舱位列表'],
						area: ['830px', '450px'],
						iframe: {src: url},
						scrollbar: true
				    }); 
				} else {
					alert("只有单程才能AV！");
				}
			}
			
			function getGqlx() {
				var gqlx = "";
				$('input[type="radio"][name="gqlx"]').each(function(){
					if ($(this).attr("checked")) {
						gqlx = $(this).val();
					}
				});
				return gqlx;
			}
			
			function changeXsCw() {
				var gqlx = getGqlx();
				$(".oXsCw").each(function(){
					var oxscwid = $(this).attr("id");
					var cw = $(this).val();
					var index = oxscwid.replace("oXsCw","");
					var cwhtml = getCwHtml(index,cw,gqlx);
					$("#nxscw"+index).html(cwhtml);
				});
			}
			
			//得到舱位的HTML
			function getCwHtml(index,cw,gqlx) {
				var cwhtml;
					if(gqlx == '1'){
						cwhtml = cw + "<input type='hidden' id='nXsCw"+index+"' name='nXsCwArr'  value='"+cw+"' style='width:40px;' onblur='value = value.toUpperCase();alltrim(this);' >";
					}else{
						cwhtml = "<input type='text' id='nXsCw"+index+"' name='nXsCwArr' style='width: 20px;' maxlength=1 >";
					}	
				return cwhtml;
			}
			
			function changeYsje(tkno) {
				var $ysje = $("#ysje"+tkno);
				var $tr = $("#ysje"+tkno).parents("tr");
				var $xsfy = $tr.find("input[name='gqXsfyArr']").val();
				var $xssc = $tr.find("input[name='gqXsscfyArr']").val();
				$("#ysje"+tkno).html((parseFloat($xsfy) + parseFloat($xssc)).toFixed(2));
			}
			
			function changeXsfySum() {
				var xsfysum = 0;
				$('input[type="text"][name="gqXsfyArr"]').each(function(){
					var xsfy = $(this).val();
					xsfysum = parseFloat(xsfysum) + parseFloat(xsfy);
				});
				$("#xshj").html(xsfysum.toFixed(2));
				
				var xsscfysum = 0;
				$('input[type="text"][name="gqXsscfyArr"]').each(function(){
					var xsscfy = $(this).val();
					xsscfysum = parseFloat(xsscfysum) + parseFloat(xsscfy);
				});
				$("#xsschj").html(xsscfysum.toFixed(2));
				$("#yshj").html((xsfysum + xsscfysum).toFixed(2));
			}
			
			function changeCgfySum() {
				var cgfysum = 0;
				$('input[type="text"][name="gqCgfyArr"]').each(function(){
					var cgfy = $(this).val();
					cgfysum = parseFloat(cgfysum) + parseFloat(cgfy);
				});
				$("#cghj").html(cgfysum.toFixed(2));
			}
			
			//采购升舱费用计算
			function changeCgscfySum(){
				var cgscfysum = 0;
				$('input[type="text"][name="gqCgscfyArr"]').each(function(){
					var cgscfy = $(this).val();
					cgscfysum = parseFloat(cgscfysum) + parseFloat(cgscfy);
				});
				$("#cgschj").html(cgscfysum.toFixed(2));
			}
			
			function showWcpdw(cgly) {
				if (cgly == "OP") {
					$("#wcpdw").css("display","block");
				} else {
					$("#wcpdw").css("display","none");
				}
			}
			
			function saveXbz() {
				var bzbz = "${jpgqd.bzbz}" + $("#ngqbz").val();
				$("#bzbz").val(bzbz);
				$("#turningUrl").val(window.location.href);
				$("#gqbzForm").submit();
			}
			
			function sqBcd(id){
			   var url ="${ctx}/vedsb/jpbcd/jpbcd/toedit?ddbh="+id+"&ywlxs=gqdd";
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
<div style="background: #fff; height:650px;width:100%">
 <div class="container">
 <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_center" style=" width:100%; background:#fff ;float:left">
              <form action="${ctx}/vedsb/jpgqgl/jpgqd/saveGqdBz" id="gqbzForm" method="POST">
                  <input type="hidden" name="gqdh" value="${jpgqd.gqdh}">
                  <input type="hidden" id="bzbz" name="bzbz" value="">
                  <input type="hidden" id="turningUrl" name="turningUrl" value=""/>
                  
              </form>
              <form action="${ctx}/vedsb/jpgqgl/jpgqd/saveGqd" id="gqForm" method="POST">
	              <input type="hidden" id="gqdh" name="gqdh" value="${jpgqd.gqdh}">
	              
	              <!-- 基本信息 -->
	              <%@include file="gqd/gqd_jbxx_read.jsp"%>
	              
	               <c:if test="${jpgqd.gqzt eq '1' or jpgqd.gqzt eq '3'}">
	     		  	 <!-- 航程信息(已确认和改签中有)-->
	             	 <%@include file="gqd/gqd_gqxx.jsp"%>
	     		  </c:if>
	               <c:if test="${jpgqd.gqzt ne '1' and jpgqd.gqzt ne '3'}">
	     		  	 <!-- 航程信息(除已确认和改签中外不可以修改)-->
	             	 <%@include file="gqd/gqd_gqxx_read.jsp"%>
	     		  </c:if>
	              
	     		  <c:if test="${jpgqd.gqzt eq '1' or jpgqd.gqzt eq '3'}">
	     		  	 <!-- 航程信息(已确认和改签中可以修改)-->
	             	 <%@include file="gqd/gqd_hcxx.jsp"%>
	     		  </c:if>
	              <c:if test="${jpgqd.gqzt ne '1' and jpgqd.gqzt ne '3'}">
	     		  	 <!-- 航程信息(除已确认和改签中外不可以修改)-->
	             	 <%@include file="gqd/gqd_hcxx_read.jsp"%>
	     		  </c:if>
				  
				  <c:if test="${jpgqd.gqzt eq '1' or jpgqd.gqzt eq '3'}">
	             	 <!-- 乘机人信息(已确认和改签中可以修改)-->
	    		  	 <%@include file="gqd/gqd_cjrxx.jsp"%>
	     		  </c:if>
	              <c:if test="${jpgqd.gqzt ne '1' and jpgqd.gqzt ne '3'}">
	     		  	 <!-- 乘机人信息(除已确认和改签中外不可以修改)-->
	             	 <%@include file="gqd/gqd_cjrxx_read.jsp"%>
	     		  </c:if>
				  
				  <c:if test="${jpgqd.gqzt eq '4'}">
				  	 <!-- 采购信息(只有已改签时才会显示，不可修改) -->
	             	 <%@include file="gqd/gqd_cgxx_read.jsp"%> 
				  </c:if>
	              
	              <c:if test="${(jpgqd.gqzt eq '1' or jpgqd.gqzt eq '3') and jpgqd.skzt ne '1'}">
	             	  <!-- 收款信息(已确认和改签中可以修改)-->				               
	              	  <%@include file="gqd/gqd_skxx.jsp"%>
	     		  </c:if>
	              <c:if test="${(jpgqd.gqzt ne '1' and jpgqd.gqzt ne '3') or jpgqd.skzt eq '1'}">
	     		  	 <!-- 收款信息(除已确认和改签中外不可以修改)-->
	             	 <%@include file="gqd/gqd_skxx_read.jsp"%>
	     		  </c:if>
	               <%-- 补差信息 --%>
					<c:if test="${not empty bcdList}">
						<%@include file="jpkhdd_bcdxx.jsp"%>
					</c:if>
					
	              <!-- 备注信息 -->	
	              <%@include file="gqd/gqd_bzxx.jsp"%>
  
	              <div class="box_top"><b class="pl15">签注信息</b></div>
	              
	              <!--签注信息-->
	              <c:set var = "ywdh" value = "${jpgqd.gqdh }"></c:set>
	              <%@include file="/WEB-INF/views/common/jpddqz.jsp"%>
	              
	              <div class="box_footer" align="center">
		                <c:choose>
		               		<c:when test="${ jpgqd.gqzt eq '7' or jpgqd.gqzt eq '8' }">
		               			<span style="color:red">订单已取消，不能修改！</span>
		               			<input type="button" class="ext_btn ext_btn_submit" value="保存新备注" onclick="saveXbz()"/>
		               		</c:when>
		               		<c:when test="${ jpgqd.gqzt eq '4' }">
		               			<span style="color:red">订单已完成，不能修改！</span>
		               			<input type="button" class="ext_btn ext_btn_submit" value="保存新备注" onclick="saveXbz()"/>
		               		</c:when>
		          			<c:otherwise>
		          				<input type="button" class="ext_btn ext_btn_submit" value="保存" onclick="save()"/>
		          			</c:otherwise>
		                </c:choose>
		                <input type="button" class="ext_btn ext_btn_submit" value="申请补差单" onclick="sqBcd('${jpgqd.gqdh}')" />
	               		<input type="button" class="ext_btn ext_btn_submit" value="降舱" onclick="toJc()"/>
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
