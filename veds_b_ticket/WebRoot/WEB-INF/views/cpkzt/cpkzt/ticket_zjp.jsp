<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>转机票</title>
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
	var ii;
	$(function(){
		validator = $("#saveZjpForm").Validform({
			tiptype : 4,
			tipSweep : true,
			ajaxPost : true,
			callback:function(transport){
				if (transport.CODE == "1") {
					layer.alert("手工转机票成功！", 1, '提示信息', function index() {
					   	if(window.parent){
							window.parent.$("#searchFormButton").click();
						}
					    closePage();
					});
				} else {
					layer.alert(transport.MSG, 5, '提示信息', function index() {
					});
				}
			},
			beforeSubmit:function(curform){
			    ii = layer.load('系统正在处理您的操作,请稍候!');
		    }
		});
		
		checkCplx();
	})

   function saveZjp(type){
   		$("#cz_type").val(type);
   		validator.submitForm(false);
   }
   
   //关闭页面
   function closePage(){
	   var index=parent.layer.getFrameIndex();
	   parent.layer.close(index);
   }
   
   function showPnrNr(){
   	  $("#pnrTab").show();
   	  $("#showtqpnrbynr").hide();
   	  $("#hidetqpnrbynr").show();
   }
   
   function hidePnrNr(){
   	  $("#pnrTab").hide();
   	  $("#showtqpnrbynr").show();
   	  $("#hidetqpnrbynr").hide();
   }
   
    //根据PNR内容提取信息
	function tqByPnrContent(ddbh){
		var pnrContent =$("textarea[id='pnr_nr']").val();
	 	if(!pnrContent){
		  	alert("PNR内容不能为空！");
		  	return false;
	 	}
	 	document.location.href= "${ctx}/vedsb/cpkzt/cpkzt/tqByPnrContent?ddbh="+ddbh+"&pnrContent="+pnrContent;
	}
	
	function checkTkno(id,tkno){
	  var length=tkno.length;
	  if(length<13){
	     layer.alert("请输入正确的票号");
	     return false;
	  }
	  tkno=tkno.replace("-","");
	  tkno=tkno.substring(0,3)+"-"+tkno.substring(3,13);
	  $("#"+id).val(tkno);
	}
</script>
</head>
<body>
 <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_center" style=" width:100%; background:#fff ;float:left">
              <form action="${ctx}/vedsb/cpkzt/cpkzt/saveZjp" id="saveZjpForm" method="POST">
	              <input type="hidden" name="callback" value="flush()"/>
	              <input type="hidden" name="ddbh" value="${jpkhdd.DDBH }"/>
	              <input type="hidden" name="type" id = "cz_type" value="1"/>
	              <input type="hidden" name="cg_pnr_no" value="${jpkhdd.CG_PNR_NO }"/>
	              <input type="hidden" name="cg_hkgs_pnr" value="${jpkhdd.CG_HKGS_PNR }"/>
	              <input type="hidden" name="cg_ddbh" value="${jpkhdd.WBDH }"/>
	               <div style="background:#efefef;">
	               		<font style="font-size:14px; font-weight:bold;">&nbsp;&nbsp;&nbsp;&nbsp;PNR</font>&nbsp;&nbsp;&nbsp; 
	               		<font style="font-size:14px; font-weight:bold;" color="red">${jpkhdd.CG_PNR_NO}</font>&nbsp;&nbsp;&nbsp;
	               		<span id = "showtqpnrbynr">
	               			<input type="button" id="searchFormButton" name="button" value="PNR内容提取" class="ext_btn ext_btn_submit" onclick="showPnrNr()"/>
	               		</span>
	               		<span id = "hidetqpnrbynr" style="display:none;" >
	               			<input type="button" id="searchFormButton" name="button" value="隐藏PNR内容" class="ext_btn ext_btn_submit" onclick="hidePnrNr()"/>
	               		</span>
	               </div>
	               
	               <!-- 按PNR内容提取 -->
	               <div id="pnrTab" style="display:none; background:none;margin-left: 5px;background:#FFF8ED;padding: 3 0 3 5;">
	               		<table>
		               		<tr>
		               			<td>
									<textarea name="pnr_nr" id="pnr_nr" cols="80" rows="10" style="background-color: black;color:#0BBF1D;"></textarea>
								</td>
								<td>
									<input type="button" name="tq" value="提取信息" class="ext_btn ext_btn_submit" onclick="tqByPnrContent('${jpkhdd.DDBH }');">
								</td>
							</tr>
						</table>
				   </div>
	               <!--乘机人信息 -->
	               <%@include file="zjp_cjrxx.jsp"%>
	               
	               <!--出票信息 -->
	               <%@include file="zjp_cpxx.jsp"%>
	               
	               <!--航程信息 -->
	               <%@include file="zjp_hcxx.jsp"%>
	               
	               <!--备注信息-->
	               <%@include file="zjp_bzxx.jsp"%>
	               
	               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
						 <td></td>
						</tr>
	               </table>
               
	                <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="td_center">
								<input type="button" id="searchFormButton" name="button" value="确认" class="ext_btn ext_btn_submit" onclick="saveZjp('1');"/>
								<!--  <input type="button" id="searchFormButton" name="button" value="确认并交票" class="ext_btn ext_btn_submit" onclick="saveZjp('2');"/> -->
								<input type="button" id="searchFormButton" name="button" value="关闭" class="ext_btn ext_btn_submit" onclick="closePage();"/>
							</td>
						</tr>
	               </table>
               </form>
            </div>
        </div>
 </div>
</body>
</html>