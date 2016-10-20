<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@include file="/WEB-INF/layouts/meta.jsp"%>
<html>
  <head>
     <title>票证入库</title>
     <link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
     <script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js" type="text/javascript"></script>
     <script type="text/javascript">
     	var validator;
		$(function() {
			validator = $("#pzrkForm").Validform({
				tiptype:3
			});
		});
	
		function toSave(){
			if(flag){
				//验证框架
			validator.submitForm(false);
			}
     		
     	}
     	
     	function calc(){
     		var startno = parseInt($("#startno").val());
     		var endno = parseInt($("#endno").val());
     		if(startno==null||startno==''){
     			startno = '0';
     		}
     		if(endno==null||endno==''){
     			endno = '0';
     		}
     		var rksl = $("#rksl");
     		var result = endno - startno;
     		if(result==0){
     			rksl.val(1);
     		}else if(result<0){
     			alert("终止号码不能小于起始号码!");
     			$("#endno").focus();
     		}else{
     			rksl.val(result+1);
     		}
     		
     	}
     	var flag=true;
     	function validateNo(){
     		var startno = $("#startno").val();
     		var endno = $("#endno").val();
			$.ajax({
				url:'${ctx}/vedsb/pzzx/pzrk/validateNo?startno='+startno+'&endno='+endno,
				type:"post",
				success:function(result){
					if(result.msg=="true"){
						flag = true;
						//说明数据库中没有重复的数据,把提示信息取消
						$("#tishi").html("");
					}else{
						flag = false;
						$("#tishi").html("该号段已经存在");
						//说明数据库中有重复的数据
						$("#startno").focus();
					}
				},
				error:function(){
					alert("ajax请求失败!");
				}
			});     		
     	}
	</script>
  </head> 
 <body>
   <!--页签 -->
<div class="box_top"><%@include file="pzrk_title.jsp"%></div>
  	    <div class="container">
        <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_center">
              <form action="${ctx}/vedsb/pzzx/pzrk/saveJpPzIn" class="jqtransform" id="pzrkForm" name="pzrkForm" method="POST"> 
           
           <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
            	<td>
            		票证类型:
            	</td>
            	<td>
	            	<select name="pztype"  class="select" style="width:100px">
		               				<c:forEach items="${vfc:getVeclassLb('7202')}" var="oneLx">
									    <c:if test="${oneLx.id ne '7202'}">
											<option value="${oneLx.id }">${oneLx.mc }</option>
										</c:if>
									</c:forEach>
		            </select>
		            &nbsp;<font color="red">*</font>
	            <td>
            </tr>
             <tr>
			<td id="into_td">起始号码:</td>
			 <td><input  class="input-text lh25" type="text" id="startno" name="startno" value="${param.startno }" onchange="calc()" datatype="n10-10" errormsg="行程单号长度:10位" />&nbsp;<font color="red">*</font><span>(行程单号长度:10位)</span>
			 <div class="Validform_checktip"/>
			 </tr>
			 
			 <tr>
			 <td id="into_td" >终止号码:</td>
			 <td><input type="text" name="endno" id="endno" value="${param.endno }" onblur="validateNo()" onchange="calc()" datatype="n10-10" errormsg="行程单号长度:10位" class="input-text lh25"  />&nbsp;<font color="red">*</font><span id="tishi"  style="color: red;"></span>
			 </tr>
			 
			 <tr>
			  <td id="into_td" >数     量:</td>
			 <td><input type="text" readonly="readonly" name="rksl" id="rksl" value="${param.rksl }" datatype="n0-12" errormsg="请输入数字" class="input-text lh25" size="10" />&nbsp;<font color="red">*</font>
			 </tr>
			  
			  <tr>
			  <td id="into_td" >OFFICE号:</td>
			 <td>
			 	<select id="officeid" name="officeid" datatype="*" class="select" style="width:100px">
			 		<option value="">请选择</option> 
			   		<c:forEach items="${jpfn:officeIdList(BUSER.shbh)}" var="officeid">
			   			<option value="${officeid}" >${officeid}</option>
                   	</c:forEach>
                 </select>
				 &nbsp;<font color="red">*</font>
			 </td>
			 </tr>
			 
			  <tr>
			  <td id="into_td" >备     注:</td>
			  <td>
			  	  <textarea rows="5"  cols="100" name="bzbz" value="${param.bzbz }" ></textarea>
			  </td>
			  </tr>
			  
			  <tr >
			  <td id="into_td"  name="yhbh">操作员:${BUSER.bh }</td>   <div id="userdiv"></div>
			  <td id="into_td"  name="bmbh">所在部门:${BUSER.bmmc }</td> <div id="deptdiv"></div>
			 <input type="hidden" name="yhbh" value="${BUSER.bh }"  class="input-text lh25" size="20" /><!-- 用户编号 -->
			 <input type="hidden" name="bmbh" value="${BUSER.shbmid }"  class="input-text lh25" size="20" /><!-- 部门编号 -->
			 <input type="hidden" name="shbh" id="shbh" value="${BUSER.shbh }"  class="input-text lh25" size="20" /><!-- 商户编号 -->
		     </tr>
		</table>
		  
	         <table width="98%" border="0" cellspacing="2" cellpadding="0" align="center" id="bt_tab">
  		        <tr>
    		      <td align="center">
      				<input type="hidden" name="len" value="10" id="len" />
      				<input type="button" name="" class="ext_btn ext_btn_submit" value=" 保 存 " onClick="toSave();">
      				<input type="button" name="" class="ext_btn ext_btn_submit" value=" 取 消 " onClick="window.history.back();">
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
