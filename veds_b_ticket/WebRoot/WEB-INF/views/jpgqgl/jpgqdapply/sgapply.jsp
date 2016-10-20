<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>手工改签申请</title>
		<script type="text/javascript">
			function save() {
				$("#gqForm").submit();
			}
			
			function returnHbcx() {
				$(".hbcxh").css("display","block");
				$(".hbcx").css("display","none");
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
              <form action="${ctx}/vedsb/jpgqgl/jpgqd/saveGqd" id="gqForm" method="POST">
	              <input type="hidden" name="id" value="${jpgqd.id}">
	              <input type="hidden" name="submitForm" value="gqdDetailForm">
	              
	              <table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
						<tr>
							<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
								<img src="${ctx}/static/images/wdimages/blxx.png" /><br /> 
								<span style="font-size:14px; font-weight:bold; color:#1195db">改签信息</span>
							</td>
							<td style="background:#efefef;">
								<table>
									<tr>
										<td colspan="2">
											PNR:<input name="xsPnrNo" value="" onblur="value=$.trim(this.value).toUpperCase();" class="input-text" style="height: 20px;width:60px"/>
											<span style="color: red">*</span>
											<span class="hbcx">
												<input type="button" value="提取" class="ext_btn ext_btn_submit"/>
											</span>
										</td>
										<td>
											&nbsp;&nbsp;&nbsp;
											票号：<input name="tkno" value="" class="input-text" style="height: 20px;width:120px"/>
											<span class="hbcx">
												<input type="button" value="DETR" class="ext_btn ext_btn_submit"/>
											</span>
										</td>
										<td>
											国内国际：
											<input type="radio" name="gngj" value="1" checked="checked"/>国内
											<input type="radio" name="gngj" value="0" />国际
										</td>
									</tr>
									<tr>
										<td>
											<input type="radio" name="gqlx" value="1" checked="checked"}/>改期
											<input type="radio" name="gqlx" value="2" />升舱
											&nbsp;
											改签原因：
										</td>
										<td colspan="3" style="text-align: center;vertical-align: middle;">
											<textarea rows="2" name="gqyy" cols="70"></textarea>
										</td>
									</tr>
								</table>
							</td>
						</tr>
				  </table>
	              
	              <!-- 航程信息-->
	              <%@include file="gqd_hcxx_write.jsp"%>

	              <!-- 乘机人信息-->
	    		  <%@include file="gqd_cjrxx_write.jsp"%>
	    		  
	    		  <div class="hbcxh" style="display: none">
	    		  	   <!-- 采购信息 -->
	              	   <%@include file="gqd_cgxx_write.jsp"%>
	              
			           <!-- 收款信息 -->				               
			           <%@include file="gqd_skxx_write.jsp"%>
	                
		               <div class="box_footer" align="center">
		               		<input type="button" class="ext_btn ext_btn_submit" value="保存" onclick="save()"/>
		               		<input type="button" class="ext_btn ext_btn_submit" value="关闭" onclick="javascript:window.close()"/>
		               </div>
	    		  </div>
				  
				  <div align="center" class="hbcx">
		               	<input type="button" class="ext_btn ext_btn_submit" value="跳过航班查询" onclick="returnHbcx()"/>
		               	<input type="button" class="ext_btn ext_btn_submit" value="查询航班" />
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
