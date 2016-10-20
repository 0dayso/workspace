<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title></title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<style>
		
.td_right{
	width:90px;
}
 </style>
<script type="text/javascript">

var validator;
var ii;
$(function(){
	validator = $("#addyeForm").Validform({
		tiptype:4,
		ajaxPost:true,
		callback:function(transport){
			if(transport.CODE=="0"){
			   if(confirm("订单详修改保存成功！")){
			   		window.close();
			   }
			}else{
			   alert(transport.MSG);
			}
			layer.close(ii);
		},
		beforeSubmit:function(curform){
		    ii = layer.load('系统正在处理您的操作,请稍候!');
	    }
	});
})

function save(){
	validator.submitForm(false);
}

//关闭页面
function closePage(){
	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
}
</script>
</head>
<body>
   <div id="forms">
       <div class="box">
            <div class="box_center" style=" width:99%; background:#fff ;float:left">
              <form action="${ctx}/vedsb/jpddgl/jpkhdd/addYe" id="addyeForm" method="POST">
              <input type="hidden" name="ddbh" value="${jpkhdd.DDBH }"/>
              <input type="hidden" name="callback"  value="parent.refresh()" />
              <input  type="hidden" name="id" value="${entity.id }">
              <input  type="hidden" name="pnr_hkgs" value="${jpkhdd.HKGS }">
	          	<!--婴儿基本信息 -->
	          	<%@include file="ye/ye_jbxx.jsp"%>
				<!--航程信息 -->
                <%@include file="ye/ye_hcxx.jsp"%>
                <!--婴儿信息 -->
                <%@include file="ye/ye_cjrxx.jsp"%>
              	<table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0">
                	<tr>
	                   <td class="td_center" colspan="4">
	                     <input type="button" name="button" onclick="save()" class="ext_btn ext_btn_submit" value="确认"/>  &nbsp;
	                     <input type="button" name="button" onclick="closePage()" class="ext_btn" value="关闭"/>
	                   </td>
                	</tr>
                </table>
               </form>
            </div>
       </div>
   </div>
</body>
</html>