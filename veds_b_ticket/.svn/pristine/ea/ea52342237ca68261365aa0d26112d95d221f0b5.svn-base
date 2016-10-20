<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style>
.title {
  background: #f6f5f5 url('/WebRoot/static/images/wdimages/tab_on.gif') 5px 5px no-repeat;
  font-size: 14px;
  font-weight: bold;
  color: #0858c7;
  padding-left: 18px;
}
.images {
	cursor: pointer;
}
</style>
<title>出票页面参数设置</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
	function checkZffs(obj,zffs,num) {
		if (obj.checked) {
			$("#"+zffs+num).val(num);
		} else {
			$("#"+zffs+num).val("");
		}
	}
	
	$(function(){
		userKj();
	});
	function userKj(){
		var i = 0;
		$('input[name="yhbhs"]').each(function(){
			$(this).autocompleteDynamic('shyhb',"yhbh"+i);
			i++;
		});
	}
	function closeLayer(){
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index);
	}
	
	var validator;
	var ii;
	
	function  toSave(){
		validator.submitForm(false);
	}
	
	$(function(){
		validator = $("#listForm").Validform({
			tiptype:4,
			ajaxPost:true,
			callback:function(result){
				layer.close(ii);
				if("1" == result){
					layer.msg("保存成功！",2,1);
				}else{
					layer.msg("保存失败！",2,1);
				}
			  },
			beforeSubmit:function(curform){
				//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
				//这里明确return false的话表单将不会提交;
				ii = layer.load('系统正在处理您的操作,请稍候!');
			}
		});
	})
	
	
	//CPS淘宝 联系人信息拼接
	function linkNxr(){
	    var  nxr=$("#lxr").val()+"||"+$("#lxrsj").val()+"||"+$("#lxremail").val();
		$("#tbNxr").val(nxr);
	}
	
	function checkFs (obj,id) {
		if (obj.checked) {
			$("#"+id).val("1");
		} else {
			$("#"+id).val("0");
		}
	}
	
	/**
	 * 为table指定行添加一行
	 * tab 表id
	 * mb  td模板
	 * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
	 * trHtml 添加行的html代码
	 */
	 
	function addTr(tab, mb, row){
		var size = $("#"+tab+" tr").length;
		var $tr=$("#"+tab+" tr").eq(row);
		var temp = $("#"+mb).html();
		var num = size - 1;
		temp=temp.replace('syhbh', 'yhbh' +num );
		temp=temp.replace('yhbhss', 'yhbhs' +num );
		temp=temp.replace('saveB2bZh()', 'saveB2bZh('+num+')');
		temp=temp.replace('saveB2cZh()', 'saveB2cZh('+num+')');
		temp=temp.replace('soffice', 'office'+num);
		temp=temp.replace('szh', 'zh'+num);
		temp=temp.replace('smm', 'mm'+num);
		temp=temp.replace('sid', 'id'+num);
		temp=temp.replace('sdlfs', 'dlfs'+num);
		$tr.after(temp);
		var i = 1;
		$(".sxh").each(function(){
		 $(this).html(i);
		 i = i+1;
		})
		userKj();
	}
	  
	function delTr(tableId, obj, id, bca){
		if (id != "") {
			var url = "${ctx}/vedsb/b2bsz/jpb2bdlzh/delDlzh?id=" + id + "&bca=" + bca;
			$.layer({
	  			area: ['250px', '150px'],
	  			dialog : {
	  				 msg : "您确定要删除该记录吗？",
	  				 btns: 2,
		        	 type: 4,
		        	 btn : ['确定','取消'],
		        	 yes : function(){
		        	 	$.ajax({
		        	 		type:"post",
		  					url : url,
		  					success:function(result){
		  						if("1" == result){
		  							layer.msg("删除成功！",2,1);
		  							$(obj).parent().parent().remove();
		  							var i = 1;
		  						    $(".sxh").each(function(){
		  							    $(this).html(i);
		  							    i = i+1;
		  						    })
		  						}else{
		  							layer.msg("删除失败！",2,1);
		  						}
		  					}
		        	 	});
		        	 },no: function(){
		             	layer.msg("取消订单操作", 2, 3);
		          	 }
	  			}
	  		});
		} else {
			$.layer({
	  			area: ['250px', '150px'],
	  			dialog : {
	  				 msg : "您确定要删除该记录吗？",
	  				 btns: 2,
		        	 type: 4,
		        	 btn : ['确定','取消'],
		        	 yes : function(){
		        		 $(obj).parent().parent().remove();
		        		 layer.msg("删除成功！",2,1);
		        		 var i = 1;
						 $(".sxh").each(function(){
							   $(this).html(i);
							   i = i+1;
						 })
		        	 },no: function(){
		             	layer.msg("取消订单操作", 2, 3);
		          	 }
	  			}
	  		});
		}
	}
	
	function showPt(obj, id) {
		if (obj.value == "0") {
			$("#"+id).hide();
		} else {
			$("#"+id).show();
		}
	}
	
	function saveB2cZh(num) {
     	var zh = $("#zh"+num).val();
     	if (zh == "") {
     		alert("账号不能为空！");
     		return;
     	}
     	var id = $("#id"+num).val();
     	var mm = $("#mm"+num).val();
     	if (mm == "") {
     		alert("密码不能为空！");
     		return;
     	}
     	var dlfs = $("#dlfs"+num).val();
     	var bca="${param.bca}";
     	if (bca == "") {
     		bca = "720104";
     	}
     	var hkgs = $("#hkgs").val();
     	var url = "${ctx}/vedsb/b2bsz/jpb2bdlzh/checkB2bDlzh" ;
     	$.ajax({
     		type : "post",
     		url : url,
     		data : "zh=" + zh + "&bca=" + bca + "&hkgs=" + hkgs ,
     		success:function(result){
     			if ("1" == result) {
     				url = "${ctx}/vedsb/b2bsz/jpb2bdlzh/saveB2bDlzh";
     				$.ajax({
     		     		type : "post",
     		     		url : url,
     		     		data : "id=" +id + "&zh=" + zh + "&mm=" + mm + "&bca=" + bca + "&hkgs=" + hkgs + "&dlfs=" + dlfs,
     		     		success:function(result){
     		     			if ("1" == result) {
     		     				 layer.msg("保存成功！",2,1);
     		     				 location.reload();
     		     			}
     		     		}
     				})
     			} else {
     				layer.msg(result,2,1);
     			}
     		}
     	})
	}
	
	function saveB2bZh(num) {
     	var zh = $("#zh"+num).val();
     	if (zh == "") {
     		alert("账号不能为空！");
     		return;
     	}
     	var mm = $("#mm"+num).val();
     	if (mm == "") {
     		alert("密码不能为空！");
     		return;
     	}
     	var id = $("#id"+num).val();
     	var office = $("#office"+num).val();
     	var yhbhs = $("#yhbhs"+num).val();
     	var yhbh = $("#yhbh"+num).val();
     	if (yhbh == "" || yhbhs == "") {
     		alert("使用用户不能为空！");
     		return;
     	}
     	var bca="${param.bca}";
     	if (bca == "") {
     		bca = "720102";
     	}
     	var hkgs = $("#hkgs").val();
     	var url = "${ctx}/vedsb/b2bsz/jpb2bdlzh/checkB2bDlzh" ;
     	$.ajax({
     		type : "post",
     		url : url,
     		data : "zh=" + zh + "&bca=" + bca + "&hkgs=" + hkgs + "&yhbh="+ yhbh,
     		success:function(result){
     			if ("1" == result || id != "") {
     				url = "${ctx}/vedsb/b2bsz/jpb2bdlzh/saveB2bDlzh";
     				$.ajax({
     		     		type : "post",
     		     		url : url,
     		     		data : "id=" +id + "&zh=" + zh + "&mm=" + mm + "&office=" + office + "&yhbh=" + yhbh + "&bca=" + bca + "&hkgs=" + hkgs ,
     		     		success:function(result){
     		     			if ("1" == result) {
     		     				 layer.msg("保存成功！",2,1);
     		     				 location.reload();
     		     			} else {
     		     				 layer.msg(result,2,1);
     		     			}
     		     		}
     				})
     			} else {
     				layer.msg(result,2,1);
     			}
     		}
     	})
	}
	
	function updZhZt(id, bca, sfsy) {
		url = "${ctx}/vedsb/b2bsz/jpb2bdlzh/saveB2bDlzh";
		var hkgs = $("#hkgs").val();
		$.ajax({
     		type : "post",
     		url : url,
     		data : "id=" + id + "&bca=" + bca + "&hkgs=" + hkgs + "&sfsy=" + sfsy,
     		success:function(result){
     			if ("1" == result) {
     				 layer.msg("修改成功！",2,1);
     				 location.reload();
     			} else {
     				 layer.msg("修改失败！",2,1);
     			}
     		}
		})
	}
</script>	
</head>
<body  onLoad="initPage();" bgcolor="#f2f2f2">
	<form action="${ctx}/vedsb/b2bsz/jpb2bdlzh/saveHkgs" method="post" id="listForm" name="listForm">
		<input type="hidden" id="cgly" name="cgly" value="${empty param.pt ? 'B2B' : param.pt}">
		<input type="hidden" name = "flag" id = "flag" value="${flag}">
		<c:choose>
			<c:when test="${pt eq 'B2B'}"><%@include file="pt/b2b.jsp"%> </c:when>
			<c:when test="${pt eq 'B2C'}"><%@include file="pt/b2c.jsp"%> </c:when>
		</c:choose>
</body>
</html>