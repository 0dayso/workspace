<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>学员管理</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2.js" type="text/javascript"></script>

<script type="text/javascript">
var validator;

function saveStudent(){
	validator.submitForm(false);
}

</script>
<script type="text/javascript">
$(function(){

	validator = $("#student").Validform({
		tiptype:3
	});
	/**
	$.ajax({url:"/manage/student/childTeacherList",success:function(data){
		var html = "<table>";
		html = html +"<tr><td>"+data.id+"</td><td>"+data.sname+"</td><td>"+data.classname+"</td></tr>";
		html =html+ "</table>";
		$("#teacherList").html(html);
	}});
	**/
});

</script>
</head>
<body>
 <div class="container">
 <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">表单</b></div>
            <div class="box_center">
              <form action="/example/student/save" class="jqtransform" id="student" method="POST">
              <input type="hidden" name="turningUrl" value="/example/student/viewlist" />
              <input  type="hidden" name="id" value="${entity.id }">
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
                 <tr>
                  <td class="td_right">姓名：</td>
                  <td class=""> 
                    <input type="text" name="name" value="${entity.name }" class="input-text lh30" size="40"/>
                  </td>
                  
                </tr>
                 <tr>
                 <td class="td_right">年龄：</td>
                  <td>
                   <input type="radio" name="age" value="1" ${entity.age eq '1' || empty entity.age  ? 'checked' : '' }>男
                   <input type="radio" name="age" value="0" ${entity.age eq '0' ? 'checked' : '' }>女
                  </td>
                 </tr>
                <tr >
                  <td class="td_right">出生日期：</td>
                  <td class="">
                  <input type="text" datatype="*" name="csrq" value="${entity.csrqStr }"  class="input-text lh30" size="10" onClick="WdatePicker()"/>
                  </td>
                 </tr>
                 <tr>
                  <td class="td_right">城市：</td>
                  <td class="">
                    <input type="text" datatype="*" name="city" value="${entity.city }"  class="input-text lh30" size="40"/>
                  </td>
                 </tr>
                 <tr>
                   <td class="td_right">&nbsp;</td>
                   <td class="">
                     <input type="button" name="button" onclick="saveStudent()" class="ext_btn ext_btn_submit" value="保存"> 
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