<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title></title>
    	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
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
				var url="${ctx}/vedsb/xcdgl/yjxcd/saveYj";
				var data={"ddbh":ddbh,"id":id,"yjdh":yjdh,"cg_yjf":cgyjf};
				$.ajax({
	        	 		type:"post",
	  					url:url,
	  					data:data,
	  					success:function(result){
	  						if("1" == result){
	  							layer.msg("邮寄行程单成功！",2,1);
	  						}else{
	  							layer.msg("邮寄行程单失败！",2,0);
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
           					<table width="100%" height="100%" class="list_table">
	           					<tr>
           							<td class="td_right" style="width:15%;">收件人：</td>
           							<td class="tab_in_td_f"><span>${jpkhdd.SJR }</span></td>
           							<td class="td_right" style="width:15%;">收件人手机：</td>
           							<td class="tab_in_td_f"><span>${jpkhdd.NXDH }</span></td>
           							<td class="td_right" style="width:15%;">邮政编码：</td>
           							<td class="tab_in_td_f"><span>${jpkhdd.YZBM }</span></td>
           						</tr>
           						<tr>
           							<td class="td_right" style="width:15%;">邮寄地址：</td>
           							<td colspan="5" width="100%"><span>${jpkhdd.XJDZ }</span></td>
           						</tr>
	           					<tr>
	           						<td class="td_right" style="width:15%;">邮寄单号：</td>
									<td class="tab_in_td_f" style="width:20%;"><input type="text" name="yjdh" class="required " id="yjdh" style="width:80%;">
									<font color="red">*</font></td>
									<td class="td_right" style="width:15%;">采购邮寄费：</td>
									<td class="tab_in_td_f" style="width:20%;"><input type="text" name="cgYjf" class="required " id="cgyjf" style="width:70%;">
									<font color="red">*</font>元</td>
									<td colspan="2"></td>
	           					</tr>
	           					<tr><td colspan="6" align="center">
	           						<input type="button" value="保 存" class="ext_btn ext_btn_success" onclick="saveYj()" >
									<input type="button" value="跳 过" class="ext_btn ext_btn_success" onclick="saveTgYj()" >
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
