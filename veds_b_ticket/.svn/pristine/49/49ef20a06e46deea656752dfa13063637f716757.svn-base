<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	//页面加载时默认触发
	$(function (){
		cglyChange('${ticket.cgly}');
	});

  //采购来源变动时
   function cglyChange(cgly){
   		var url="${ctx}/vedsb/bbzx/cpbb/loadcgly";
   		$("#cglydiv").load(
	   		url, 
	   		{cgly:cgly,
		   		skzt:"${ticket.skzt}",
		   		cgkm:"${ticket.cgkm}",
		   		workno:"${ticket.workno}",
		   		cpOfficeid:"${ticket.cpOfficeid}",
		   		cgdw:"${ticket.cgdw}",
		   		printno:"${ticket.printno}",
		   		cgDdbh:"${ticket.cgDdbh}",
		   		gngj:"${ticket.gngj}",
		   		wbdh:"${ticket.wbdh}"
	   		},function(){}
   		);
		
  }
 
   //确认保存修改的出票信息
   function toSave(){
	   	var cgly=$("#cgly").val();
	   	var cgkm=$("#cgkm").val();
	   	var office=$("#office").val();
	   	var cgDdbh=$("#cgDdbh").val();
	   	var workno=$("#workno").val();
	   	var printno=$("#printno").val();
	   	if(cgly == 'B2B'){
	   		if(cgkm == ''){
	   			layer.msg("采购科目不能为空",2,0);
	   			return;
	   		}
	   		if(office == ''){
	   			layer.msg("OFFICE号 不能为空",2,0);
	   			return;
	   		}
	   		if(cgDdbh == ''){
	   			layer.msg("采购订单编号不能为空",2,0);
	   			return;
	   		}
	   	}else if(cgly == 'B2C'){
	   		if(cgkm == ''){
	   			layer.msg("采购科目不能为空",2,0);
	   			return;
	   		}
	   		if(cgDdbh == ''){
	   			layer.msg("采购订单编号不能为空",2,0);
	   			return;
	   		}
	   	}else if(cgly == 'BOP'){
	   		if(cgkm == ''){
	   			layer.msg("采购科目不能为空",2,0);
	   			return;
	   		}
	   		if(office == ''){
	   			layer.msg("OFFICE号 不能为空",2,0);
	   			return;
	   		}
	   		if(workno == ''){
	   			layer.msg("工作号 不能为空",2,0);
	   			return;
	   		}
	   	}else if(cgly == 'OP' || cgly == 'TB'){
	   		if(cgkm == ''){
	   			layer.msg("采购科目不能为空",2,0);
	   			return;
	   		}
	   		if(cgDdbh == ''){
	   			layer.msg("采购订单编号不能为空",2,0);
	   			return;
	   		}
	   	}else if(cgly == 'BSP'){
	   		if(workno == ''){
	   			layer.msg("工作号 不能为空",2,0);
	   			return;
	   		}
	   		if(office == ''){
	   			layer.msg("OFFICE号 不能为空",2,0);
	   			return;
	   		}
	   		if(printno == ''){
	   			layer.msg("打票机号不能为空",2,0);
	   			return;
	   		}
	   	}
   		//将没勾选的tr中name属性清空
   		$("input[name=chooseJp]").not("input:checked").each(function(){
   			var $tr=$(this).parents("tr");
   			$tr.find("input").attr("name","");
   		});
   		var url="${ctx}/vedsb/bbzx/cpbb/updateTicket";
   		$.ajax({
   	 		type:"post",
			url:url,
			data:$("#saveForm").serialize(),
			success:function(result){
				if(result.CODE == '0'){
					layer.msg(result.MSG,2,1);
					parent.$("#tosearch").click();
					closeJp();
				}else{
					layer.msg(result.MSG,2,0);
				}
			}
   	 	});
   	 	
  }
  //关闭当前弹出窗
  function closeJp(){
  	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
  }
  
  function checkOne(obj){
  	var $tr=$(obj).parents("tr");
  	if($(obj).is(":checked")){
  		$tr.find("input[name=ei]").removeAttr("disabled");
  	}else{
  		$tr.find("input[name=ei]").attr("disabled","disabled");
  	}
  }
  
  function checkTkno(tkno){
	  var length=tkno.length;
	  if(length<13){
	     layer.alert("请输入正确的票号");
	     return false;
	  }
	  tkno=tkno.replace("-","");
	  tkno=tkno.substring(0,3)+"-"+tkno.substring(3,13);
	  $("#tknoflag").val(tkno);
 }
 
 function getcglmByoffice(obj){
	var $obj = $(obj);
	var objval = $obj.val();
	var objs = $("#"+objval+"_index").val();
	$("select option[value='"+objs+"']").attr("selected","selected");
	$("#cgkmhidden").val(objs); 
}
</script>

</head>
<body>
 <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
            <form id="saveForm" action="${ctx}/vedsb/bbzx/cpbb/updateTicket" name="saveForm" method="post">
	           	<input type="hidden"  name="VIEW" value="827931810cad8945ef6eb71a850bb128" />
				<input type="hidden"  name="ddbh" value="${ticket.ddbh }" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
					<tr>
						<th width="6%">选择</th>
						<th width="15%">票号</th>
						<th width="10%">pnr</th>
						<th width="10%">采购来源</th>
						<th width="61%">签转信息</th>
					</tr>
					<c:forEach items="${tklist}" var="tk">
						<tr>
							<!--<input type="hidden"  name="tkno" value="${tk.tkno }" />  -->
							<input type="hidden"  name="id" value="${tk.id }"/>
                  			<td class="td_center">
                  				<input type="checkbox" name="chooseJp" value="${tk.id }" onclick="checkOne(this);" ${tk.id eq ticket.id ? 'checked':'' } id="${tk.tkno }">
                  			 </td>
                  			<td class="td_center"><input type="text" id="tknoflag" name="tkno" value="${tk.tkno}" datatype="*14-14" maxlength = "14" size="12" onblur="checkTkno($.trim(this.value).toUpperCase());"/></td>
                  			<td class="td_center">${tk.xsPnrNo }</td>
                  			<td class="td_center">${tk.cgly }</td>
                  			<td class="td_center"><input type="text" name="ei" value="${tk.ei }" id="getEi${tk.id }" style="width:100%;" class="input-text lh25" ${tk.id eq ticket.id ? '':'disabled' }></td>
                  		</tr>
                	</c:forEach>
				</table>
               
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>采购来源
							<select name="cgly" id="cgly" class="select" style="width:100px;" onchange="cglyChange(this.value)">
								<c:forEach items="${vfc:getVeclassLb('10014')}" var="one" varStatus="wdptStatus">
									<c:if test="${one.id ne '10014' }">
		                  	 			<option value="${one.ywmc }" ${ticket.cgly eq one.ywmc ? 'selected' : '' }>${one.ywmc }</option>
									</c:if>
		                  	 	</c:forEach>
	                  	 	</select>
						</td>
					</tr>
					<tr id="cglydiv">
						
					</tr>
					  <tr style="margin-left:-10px;">
					  	<td colspan="2">
					  		<input type="button" class="ext_btn ext_btn_success" value="确定" onclick="toSave()" style="margin-left:230px;">
	          				<input type="button" class="ext_btn ext_btn_success" value="关闭" onclick="closeJp()">
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