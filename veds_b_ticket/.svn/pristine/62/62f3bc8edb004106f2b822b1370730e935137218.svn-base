<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>改签单审核</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
	var validator;
	function save() {
	     var check=true;
	  	 $("input[name='nCfsjArr']").each(function() {
		        var n_cfsj=$(this).val();
		        if(isBlank(n_cfsj)){
		            layer.msg('请输入起飞时间！',2, {shade: false });
					this.focus();
					check=false;
					return check;
		        }
		        
		        var bh=$(this).attr("bh");
		        var n_ddsj=$("#nddsj"+bh).val();
		         if(isBlank(n_ddsj)){
		            layer.msg('请输入到达时间！',2, {shade: false });
					$("#nddsj"+bh).focus();
		            check=false;
					return check;
		        }
		        
		        if(n_cfsj>n_ddsj){
		           layer.msg('起飞时间不能大于到达时间',2, {shade: false });
				   this.focus();
				   check=false;
				   return check;
		        }
		        
		   });
		   if(!check){
		      return false;
		   }

		    var ii;
			validator = $("#gqBlForm").Validform({
				tiptype:4,
				tipSweep:true,
				ajaxPost:true,
			    callback:function(transport){
					layer.close(ii);
					if (transport.CODE=="0") {
					 		layer.alert("改签单审核成功！",1, '提示信息',function index(){
					 		 	window.opener.$("#searchFormButton").click(); 
							  	window.close();
	             		});
					} else {
					   layer.alert(transport.MSG,5, '提示信息',function index(){
								window.close();
	             			 });
					}
			  	},
				beforeSubmit:function(curform){
					//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
					//这里明确return false的话表单将不会提交;
					 ii=layer.load('系统正在处理您的操作,请稍候!');
				}
			});
			validator.submitForm(false);
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
				cwhtml = cw + "<input type='hidden' id='nXsCw"+index+"' name='nXsCwArr'  value='"+cw+"' style='width:40px;' onkeyup='value =value.toUpperCase();' >";
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
</script>
	</head>
<body>
<div style="background: #fff; height:650px;width:100%">
 <div class="container">
 <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_center" style=" width:100%; background:#fff ;float:left">
              <form action="${ctx}/vedsb/jpgqgl/jpgqd/saveReview" id="gqBlForm" method="POST">
	              <input type="hidden" name="gqdh" value="${jpgqd.gqdh}">
	              <input type="hidden" name="close" value="true">
	              
	              <!-- 基本信息 -->
	              <%@include file="gqd/gqd_jbxx.jsp"%>
	              
	              <!-- 改签信息 -->
	              <%@include file="gqd/gqd_gqxx.jsp"%>
	              
	              <!-- 乘机人信息-->
	    		  <%@include file="gqd/gqd_cjrxx.jsp"%>
	              
	              <!-- 航程信息-->
	              <%@include file="gqd/gqd_hcxx.jsp"%>

	              <!-- 采购信息 -->
	              <%@include file="gqd/gqd_cgxx.jsp"%>
	                
	               <div class="box_footer" align="center">
	               		<input type="button" class="ext_btn ext_btn_submit" value="审核" onclick="save()"/>
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
