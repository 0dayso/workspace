<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title></title>
    	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    	<script type="text/javascript">
			//取消邮寄  关闭当前弹窗
			function hideQuickDiv(){
				var index=parent.layer.getFrameIndex();
				parent.layer.close(index);
			}
			//跳过邮寄
			function saveTgYj(){
				var ddbh=document.getElementById("ddbh").value;
				var id=document.getElementById("yjid").value;
				var yjdh ="";
				var url="${ctx}/vedsb/xcdgl/yjxcd/saveYj";
				var data={"ddbh":ddbh,"id":id,"yjdh":yjdh};
				$.ajax({
	        	 		type:"post",
	  					url:url,
	  					data:data,
	  					success:function(result){
	  						if("1" == result){
	  							layer.msg("跳过邮寄行程单成功！",2,1);
	  						}else{
	  							layer.msg("跳过邮寄行程单失败！",2,0);
	  						}
	  						window.parent.toSearch("${param.flag}");
	  					}
	        	 	});
			}
			//保存邮寄
			function saveYj(obj){
				var ddbh=document.getElementById("ddbh").value;
				var id=document.getElementById("yjid").value;
				var yjdh = document.getElementById("yjdh").value;
				var cgyjf=document.getElementById("cgyjf").value;
				var url="${ctx}/vedsb/xcdgl/yjxcd/saveYj";
				if(!yjdh){
					layer.msg("邮寄单号不能为空！",2,0);
					return ;
				}
				if(!cgyjf){
					layer.msg("采购邮寄费不能为空！",2,0);
					return ;
				}else{
					var reg=/^[-\+]?\d+(\.\d+)?$/;
					if(!reg.test(cgyjf)){
						layer.msg("采购邮寄费必须为数字！",2,0);
						return ;
					}
				}
				var sjr=document.getElementById("sjr").value;
				var nxdh=document.getElementById("nxdh").value;
				var yzbm = document.getElementById("yzbm").value;
				var xjdz=document.getElementById("xjdz").value;
				if(!sjr){
					layer.msg("收件人不能为空！",1,0);
					$("#sjr").focus();
					return ;
				}else if(sjr.trim().length>30){
					layer.msg("收件人的长度不能超过30个字符！",1,0);
					$("#sjr").focus();
					return ;
				}
				if(!nxdh){
					layer.msg("收件人手机不能为空！",1,0);
					$("#nxdh").focus();
					return ;
				}
				var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
				if(!reg.test(nxdh)){
					layer.msg("收件人的手机格式不正确！",1,0);
					$("#nxdh").focus();
					return ;
				}
				if(!yzbm){
					layer.msg("邮政编码不能为空！",1,0);
					$("#yzbm").focus();
					return ;
				}
				var reg2=/^\d{6}$/;
				if(!reg2.test(yzbm)){
					layer.msg("邮政编码必须是6位数字！",1,0);
					$("#yzbm").focus();
					return ;
				}
				if(!xjdz){
					layer.msg("邮寄地址为能为空！",1,0);
					$("#xjdz").focus();
					return ;
				}else if(xjdz.length>60){
					layer.msg("邮寄地址不能超过60个字符！",1,0);
					$("#xjdz").focus();
					return ;
				}
				data={"ddbh":ddbh,"id":id,"yjdh":yjdh,"cg_yjf":cgyjf,"sjr":sjr,"nxdh":nxdh,"yzbm":yzbm,"xjdz":xjdz};
				$.ajax({
	        	 		type:"post",
	  					url:url,
	  					data:data,
	  					success:function(result){
	  						if("1" == result){
	  							layer.msg("邮寄行程单成功！",2,1);
	  						}else{
	  							layer.msg("邮寄行程单失败！",1,0);
	  						}
	  						window.parent.toSearch("${param.flag}");
	  					}
	        	 	}); 
			}
			
			
    	</script>
  </head>
  <body>
    <div class="container">
 		<div id="forms">
        	<div class="box">
          		<div class="box_border">
            		<div class="box_center">
            			<form action="${ctx}/vedsb/xcdgl/yjxcd/saveYj" id="yjForm" method="POST">
			              <input type="hidden" name="ddbh" id="ddbh" value="${param.ddbh }" /><!-- 订单编号 -->
			              <input type="hidden" name="id" id="yjid" value="${param.id }" /><!-- 邮寄单id -->
           					<table width="100%" height="20%" border="0" cellpadding="0" cellspacing="0" class="list_table">
           						<tr>
           							<td class="td_right" style="width:14%;">收件人：</td>
           							<td class="tab_in_td_f"><input type="text" name="sjr" id="sjr" value="${jpkhdd.SJR }" style="width:80%;">&nbsp;<font color="red">*</font></td>
           							<td class="td_right" style="width:16%;">收件人手机：</td>
           							<td class="tab_in_td_f"><input type="text" name="nxdh" id="nxdh" value="${jpkhdd.NXDH }" style="width:80%;">&nbsp;<font color="red">*</font></td>
           							<td class="td_right" style="width:15%;">邮政编码：</td>
           							<td class="tab_in_td_f"><input type="text" name="yzbm" id="yzbm" value="${jpkhdd.YZBM }" style="width:50px;">&nbsp;<font color="red">*</font></td>
           						</tr>
           						<tr>
           							<td class="td_right" style="width:14%;">邮寄地址：</td>
           							<td class="tab_in_td_f" colspan="5"><input type="text" name="xjdz" id="xjdz" value="${jpkhdd.XJDZ }" style="width:90%;"/>&nbsp;<font color="red">*</font></td>
           						</tr>
           						<tr>
	           						<td class="td_right" style="width:14%;">邮寄单号：</td>
									<td class="tab_in_td_f" style="width:20%;"><input type="text" name="yjdh" class="required " id="yjdh" value="${jpkhdd.YJDH }" style="width:80%;">
									<font color="red">*</font></td>
									<td class="td_right" style="width:16%;">采购邮寄费：</td>
									<td class="tab_in_td_f" style="width:20%;"><input type="text" name="cgYjf" class="required " id="cgyjf" value="${jpkhdd.YJCGYJF }" style="width:70px;">
									<font color="red">*</font>元</td>
									<td colspan="2"></td>
	           					</tr>
	           					<tr>
	           						<td colspan="6" align="center">
	           						<input type="button" value="保 存" class="ext_btn ext_btn_success" onclick="saveYj()" >
									<input type="button" value="取 消" class="ext_btn ext_btn_success" onclick="hideQuickDiv();" >
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
