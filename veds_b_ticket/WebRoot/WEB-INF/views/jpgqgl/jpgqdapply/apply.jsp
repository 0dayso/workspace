<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>改签申请</title>
		<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
		<script type="text/javascript">
			var validator;
			var ii;
			function save() {
				var cjrchecklen = $('input[type="checkbox"][name="khddCjrIdArr"]').length;
				var cjrinputlen = $('input[type="hidden"][name="khddCjrIdArr"]').length;
				//没有可以改签的乘机人
				if (cjrchecklen == 0 && cjrinputlen == 0) {
					alert("没有可以改签的乘机人！");
					return;
				}
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
						if(transport.CODE=="0"){
						   if(confirm("改签申请保存成功！")){
						   		window.close();
						   }
						}else{
						   alert(transport.MSG);
						}
						layer.close(ii);
					},
					beforeSubmit:function(curform){
						//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
						//这里明确return false的话表单将不会提交;
						ii = layer.load('系统正在处理您的操作,请稍候!');
					}
				});
				var hdids = "";
				$('input[type="hidden"][name="jpHdIdArr"]').each(function () {
					hdids = hdids + $(this).val();
				});
				if (hdids != "") {
					getCjrLsGqjl(hdids);
				}	
			})
			
			function checkAllHc(obj) {
				$('input[type="checkbox"][name="jpHdIdArr"]').each(function() {
					$(this).attr("checked", obj.checked);
					checkHc($(this).val());
				});
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
			
			function getCjrLsGqjl(jp_hdid) {
				var url = "${ctx}/vedsb/jpgqgl/jpgqd/getLsGqjl";
				$.ajax({
					type : "post",
					url : url,
					data : "jp_hdid=" + jp_hdid,
					success: function(result) {
						if (result.STATUS == "0") {
							//获取的历史记录放入对应的订单航段下
							var gqjl = result.GQJL;
							showLsgqjl(gqjl);
						} else {
							alert(result.ERROR);
						}
					}
				});
			}
			
			function showLsgqjl(gqjl) {
				for(var ddhdid in gqjl){
					var lsgqList = gqjl[ddhdid];
					var bl = false;
					var isPass = true;
					$('input[name="jpHdIdArr"]').each(function(){
						if($(this).checked){
							var sddhdid = $(this).val();
							if(sddhdid == ddhdid){
								if(a.attr("checked") == "checked" || !$(this).attr("checked")){
									bl = true;
								}else{
									if(lsgqList[lsgqList.length-1].GQDH != $("gqjl_" + sddhdid).value){
										isPass = false;
										throw $break;
									}
								}
							}
						}
					});
					if(!isPass){
						//判断
						return;
					}
					if(bl){
						var divhtml = "";
						var i = 1;
						$A(lsgqList).each(function(b){
							if(i == lsgqList.length){
								divhtml += "<input type=\"hidden\" id=\"gqjl_"+ddhdid+"\" name=\"gqjl_last\" value=\""+b.GQDH+"\">";
							}
							divhtml += i + "、"+b.GQ_BLSJ + '办理改签，';
							if (b.N_CFSJ != null) {
								var dcfdt = new Date(b.N_CFSJ.time);
								var scfdatetime = dcfdt.getFullYear() + "-" + (dcfdt.getMonth()+1) + "-" + dcfdt.getDate() 
									+ " " + dcfdt.getHours() + ":" + dcfdt.getMinutes();
								divhtml += '起飞时间【' + b.scfdatetime + '】';
							}
							if(!isBlank(b.N_HBH)){
								divhtml += '航班号【'+b.N_XSHBH + '】';
							}
							if(!isBlank(b.N_CW)){
								divhtml += '舱位【'+b.N_XSCW + '】';
							}
							divhtml += "<br>";
							i++;
						});
						if(divhtml != ""){
							$("ddhdtr_"+ddhdid).show();
							$("gqlsjl_"+ddhdid).innerHTML = divhtml;
						}
					}
				}
			}
			
			function changeXsCw() {
				var gqlx = getGqlx();
				var len = $(".oXsCw").length;
				if (len == 1) {
					var oXsCw = $(".oXsCw")[0];
					var oXsCwId = oXsCw.id;
					var index = oXsCwId.replace("oXsCw","");
					var cw = oXsCw.value;
					var cwhtml = getCwHtml(index,cw,gqlx);
					$("#nxscw"+index).html(cwhtml);
				} else {
					$('input[type="checkbox"][name="jpHdIdArr"]').each(function() {
						if ($(this).attr("checked") == "checked") {
							var checkboxid = $(this).attr("id");
							var hdid = checkboxid.replace("hccheck","");
							var ddbh = $(this).attr("ddbh");
							var index = hdid + ddbh;
							var cw = $("#oXsCw"+index).val();
							var cwhtml = getCwHtml(index,cw,gqlx);
							$("#nxscw"+index).html(cwhtml);
						}
					});
				}
			}
			
			//得到舱位的HTML
			function getCwHtml(index,cw,gqlx) {
				var cwhtml;
					if(gqlx == '1'){
						cwhtml = cw + "<input type='hidden' id='nXsCw"+index+"' name='nXsCwArr'  value='"+cw+"' style='width:40px;' onblur='value = value.toUpperCase();alltrim(this);' >";
					}else{
						cwhtml = "<input type='text' id='nXsCw"+index+"' name='nXsCwArr' datatype=\"*\" nullmsg=\"请填写值\" style='width: 20px;' maxlength='1' >";
					}	
				return cwhtml;
			}
			
			function checkHc(hcid) {
				var ddbh = $("#hccheck"+hcid).attr("ddbh");
				if ($("#hccheck"+hcid).attr("checked") == "checked") {
					var ncfsjhtml = "<input  type=\"text\" name=\"nCfsjArr\" datatype=\"*\" nullmsg=\"请填写值\" style=\"width:120px\" class=\"input-text Wdate\" size=\"10\"  id=\"mindate+"+hcid+ddbh+"+\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\\'maxdate+"+hcid+ddbh+"+\\')}'})\">";
					$("#ncfsj"+hcid+ddbh).html(ncfsjhtml);
					var nddsjhtml = "<input  type=\"text\" name=\"nDdsjArr\" datatype=\"*\" nullmsg=\"请填写值\" style=\"width:120px\" class=\"input-text Wdate\" size=\"10\"  id=\"maxdate+"+hcid+ddbh+"+\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\\'mindate+"+hcid+ddbh+"+\\')}'})\">";
					$("#nddsj"+hcid+ddbh).html(nddsjhtml);
					var nhbhhtml = "<input type=\"text\" name=\"nXsHbhArr\" datatype=\"*\" nullmsg=\"请填写值\" style=\"height: 20px;width:60px\"/>";
					$("#nhbh"+hcid+ddbh).html(nhbhhtml);
					changeXsCw();
				} else {
					$("#ncfsj"+hcid+ddbh).html("");
					$("#nddsj"+hcid+ddbh).html("");
					$("#nhbh"+hcid+ddbh).html("");
					$("#nxscw"+hcid+ddbh).html("");
				}
				var checkboxlen = $('input[type="checkbox"][name="jpHdIdArr"]').length;
				var checkedlen = $('input[type="checkbox"][name="jpHdIdArr"]:checked').length;
				if (checkboxlen == checkedlen) {
					$("#hccheckall").attr("checked","checked");
				} else if (checkedlen == 0) {
					$("#hccheckall").removeAttr("checked");
				}
				var cjrcheckedlen = $('input[type="checkbox"][name="khddCjrIdArr"]:checked').length;
				var cjrlen = $('input[type="checkbox"][name="khddCjrIdArr"]').length;
				var hdids = "";
				if (checkedlen > 0 && (cjrcheckedlen > 0 || cjrlen == 0)) {
					$("#saveButton").removeAttr("disabled");
					$('input[type="radio"][name="jpHdIdArr"]').each(function (hdid) {
						hdids = hdids + hdid.val() + ",";
					});
					if (hdids != "") {
						hdids = hdids.substring(0,hdids.length-1);
					}
					getCjrLsGqjl(hdids);
				} else {
					$("#saveButton").attr("disabled");
				}
			}
			
			function checkCjr(cjrid) {
				var tkno = $("#cjrcheck"+cjrid).attr("tkno");
				if ($("#cjrcheck"+cjrid).attr("checked") == "checked") {
					$("#gqXsfy"+tkno+cjrid).removeAttr("disabled");
				} else {
					$("#gqXsfy"+tkno+cjrid).val(0);
					$("#gqXsfy"+tkno+cjrid).attr("disabled","disabled");
				}
				var checkboxlen = $('input[type="checkbox"][name="khddCjrIdArr"]').length;
				var checkedlen = $('input[type="checkbox"][name="khddCjrIdArr"]:checked').length;
				if (checkboxlen == checkedlen) {
					$("#cjrcheckall").attr("checked","checked");
				} else if (checkedlen == 0) {
					$("#cjrcheckall").removeAttr("checked");
				}
				var hccheckedlen = $('input[type="checkbox"][name="jpHdIdArr"]:checked').length;
				var hclen = $('input[type="checkbox"][name="jpHdIdArr"]').length;
				var hdids = "";
				if (checkedlen > 0 && (hccheckedlen > 0 || hclen == 0)) {
					$("#saveButton").removeAttr("disabled");
					$('input[type="radio"][name="jpHdIdArr"]').each(function () {
						hdids = hdids + $(this).val() + ",";
					});
					$('input[type="hidden"][name="jpHdIdArr"]').each(function () {
						hdids = hdids + $(this).val() + ",";
					});
					if (hdids != "") {
						hdids = hdids.substring(0,hdids.length-1);
					}
					getCjrLsGqjl(hdids);
				} else {
					$("#saveButton").attr("disabled");
				}
				
			}
			
			function checkAllCjr(obj) {
				$('input[type="checkbox"][name="khddCjrIdArr"]').each(function(){
					$(this).attr("checked", obj.checked);
					checkCjr($(this).val());
				});
			}
			
			function changeYsje() {
				var ysje = 0;
				$('input[type="text"][name="gqXsfyArr"]').each(function(){
					var gqxsfy = $(this).val();
					ysje = parseFloat(ysje)+parseFloat(gqxsfy);
				});
				$("#ysje").html(ysje);
			}
		</script>
		<style type="text/css">
			.gqd_right {
				text-align: right;
			}
			.gqd_left {
				text-align: left;
			}
		</style>
  	</head>
	<body>
	  <div style="background: #fff; height:650px;width:100%">
 <div class="container">
 <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_center" style=" width:100%; background:#fff ;float:left">
              <form action="${ctx}/vedsb/jpgqgl/jpgqdapply/saveGqdApply" id="gqForm" method="POST">
	              <input type="hidden" name="close" value="true">
	              <c:set var="jpKhdd" value="${gqdData.jpKhdd}"></c:set>
	              <input type="hidden" name="ddbh" value="${jpKhdd.ddbh}"/>
	              <!-- 基本信息 -->
	              <%@include file="gqd_jbxx_apply.jsp"%>
	              
	              <!-- 改签信息 -->
	              <%@include file="gqd_gqxx_apply.jsp"%>

	              <!-- 乘机人信息-->
	    		  <%@include file="gqd_cjrxx_apply.jsp"%>
	    		  
	    		  <!-- 航程信息-->
	              <%@include file="gqd_hcxx_apply.jsp"%>
	    		  
			      <!-- 收款信息 -->				               
			      <%@include file="gqd_skxx_apply.jsp"%>
	                
		          <div class="box_footer" align="center">
		               	<input type="button" id="saveButton" class="ext_btn ext_btn_submit" value="保存" onclick="save()" {jphds eq 1 and cjrs eq 1 ? '' : 'disabled="disabled"'}/>
		     
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
