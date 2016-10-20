<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
var validator;
$(function(){
	validator = $("#ticket").Validform({
		tiptype:3
	});
});
function save(){
	validator.submitForm(false);
}
function closetpsz(){
	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
}
</script>
<style>
		
.td_right{
	width:90px;
}
 </style>
</head>
<body>
	 <div class="container">      
          <div class="box_border" >
          
              <form action="${ctx}/vedsb/jptpsz/jptpsz/save" class="jqtransform" id="ticket" method="POST">
               <input type="hidden" name="closeDiv" value="true"/>
              <input type="hidden" name="callback"  value="parent.refresh()" />
              <input type="hidden" name="turningUrl" value="${ctx}/vedsb/jptpsz/jptpsz/viewlist" />
              <input  type="hidden" name="id" value="${entity.id }">
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0" >
               	<tr>
                  <td class="td_right">是否自愿退票：</td>      
                  <td class=""> 									
					<input type="radio" name="sfzytp" value="1" ${empty entity.sfzytp or entity.sfzytp eq '1' ? 'checked':''}/>是 
					<input type="radio" name="sfzytp" value="0" ${entity.sfzytp eq '0' ? 'checked':''}/>否 
                  </td>
                </tr>
                 <tr>
                  <td class="td_right">航空公司：</td>      
                  <td class=""> 
                    <input type="text" datatype="*,multihs" name="hkgs" id="hkgs" value= "${entity.hkgs }" class="input-text lh30" size="18" onkeyup="this.value=this.value.toUpperCase()"/>
                    <font color="red"> *</font><font color="gray">(支持多个，用 "/"分割。例如:CZ/3U)</font>              
                  </td>
                </tr>
                 <tr>
                 <td class="td_right">票证类型：</td>
                  <td>
                    <select name="pzlx" class="select srk" datatype="*" style="width: 158px"> 
					   <option value="">==请选择==</option>
					   <c:forEach items="${vfc:getVeclassLb('10014')}" var="onecgly">
					   		<c:if test="${onecgly.parid ne 'none'}">
		                       <option value="${onecgly.mc}" ${entity.pzlx eq onecgly.mc ? 'selected':'' }>${onecgly.mc}</option>
		                    </c:if>
	                    </c:forEach>
                     </select>
                     <font color="red">*</font>
                   </td>
                 </tr> 
                <tr >
                  <td class="td_right">预计回款时长：</td>
                  <td class=""> 
                    <input type="text" datatype="*" name="yjhksc" id="yjhksc" value="${entity.yjhksc}" class="input-text lh30" size="18"/>
                    <font color="red"> *</font><font color="gray">(单位：天)</font>
                  </td>
                 </tr>
                 <tr>
                    <td class="td_center" colspan="7">
                    <input type="button" name="button" onclick="save()" class="ext_btn ext_btn_submit" value="保存">  &nbsp;
                     <input type="button" name="button" onclick="closetpsz()" class="ext_btn" value="关闭">                    
                   </td>
                 </tr>
               </table>               
               </form>  
          </div>
        </div>
     </div>
</body>
</html>
