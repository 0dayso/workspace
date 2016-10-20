<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title></title>
    	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
    	<script type="text/javascript">
    		//关闭当前弹窗
    		function closepj(){
    			 var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				 parent.layer.close(index);
    		}
    		//保存
			function toSave(){
				validator = $("#cancelform").Validform({
		    		tiptype:4,
					tipSweep:true,
					ajaxPost:true,
					callback:function(transport){
						if(transport.responseText == 'ok'){
						   //加载父页面
						   parent.$("#searchFormButton").click();
						   //关闭当前层
						   closepj();
						}else{
						   alert("取消失败");
						}
						layer.close(ii);
					},
					beforeSubmit:function(curform){
					    ii = layer.load('系统正在处理您的操作,请稍候!');
				    }
		    	});
		    	validator.submitForm(false);
			}
			
			function changeQxyy(obj) {
				var $obj = $(obj);
				$("#qxyy").html($obj.find("option:selected").text());
			}
	</script>
  </head>
  <body>
    <div class="container">
 		<div id="forms">
        	<div class="box">
          		<div class="box_border">
            		<div class="box_center">
            		<form action="${ctx}/vedsb/jptpgl/jptpd/tpdCancel" id="cancelform" method="post">
            		 <input type="hidden" id="id" name="tpdh" value="${jptpd.tpdh }"/>
            		 <input type="hidden" id="shbh" name="shbh" value="${jptpd.shbh}"/>
            			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table"  id="jpzzdd">
            			    <tr>
            			      <th colspan="5" align="left">退票单号 <font color="red" style="font-weight: normal;"> ${jptpd.tpdh}</font>&nbsp;&nbsp;PNR  <font color="red" style="font-weight: normal;">${jptpd.xsPnrNo}</font></th>
            			    </tr>
            				<tr>
            					<th width="100px">票号</th>
            					<th width="100px">票证类型</th>
            					<th width="100px">乘机人</th>
            					<th width="200px">航程/起飞时间</th>
            					<th width="100px">航班号/舱位</th>
            				</tr>
            				<c:forEach items="${mxList}" var="map">
            				<tr>
            				    <td align="center">${map.TKNO }</td>
                   				<td align="center">${map.CGLY}</td>
                   				<td align="center">${map.CJR }</td>
                   				<td align="center">${map.HC}/${vfn:format(map.CFRQ,'yyyy-MM-dd HH:mm')}</td>
                   				<td align="center">${map.XS_HBH}/${map.XS_CW}</td>
            				</tr>
            				</c:forEach>
            			</table>
            			
            			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table"  id="jptfdd">
            				<tr>
            					<th align="left">请选择退票原因</th>
            				</tr>
            				<tr>
            					<td>
            					<select name="qxyybh" id="qxyybh" onchange="changeQxyy(this)">
            					 <option value="">==请选择取消原因==</option>
            					 <option value="1">其它原因</option>
            					</select>
            					</td>
            				
            				</tr>
            				
            				<tr>
            				    <td>
            				       <textarea rows="5" cols="50" style="width: 100%" id="qxyy" name="qxyy"></textarea>
            				    </td>
            				</tr>
            				<tr>
								<td align="center">
								   <input type="button" name="button" onclick="toSave()" class="ext_btn ext_btn_submit" value="确认">&nbsp;
								   <input type="button" name="button" onclick="closepj();"class="ext_btn" value="关闭">
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
