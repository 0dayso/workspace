<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
	var validator;
	$(function(){
		validator = $("#hbydgzForm").Validform({
			tiptype:3,
			beforeSubmit:function(curform){
				layer.load('系统正在处理您的操作,请稍候!');
			}
		});
	});
	//保存
	function saveHshbgz(){
		if(validator.check()){
			var url = "${ctx}/vedsb/jphbyd/bhkgshbgz/saveBean";
			$.ajax({
				type : "post",
				url : url,
				data : $("#hbydgzForm").serialize(),
				success : function(result) {
					var code = result.CODE;
					if(code == '-1'){
						layer.msg(result.MSG, 1, 2);
					}else if(code == '0'){
						window.location.reload();
					}
				}
			});
		}
	}
   
	function addHbydgz(){
		//隐藏所有的编辑
 		$("img[title='编辑']").hide();
 		$("img[title='添加']").hide();
		$("#tbdy").append("<tr><td class='td_center'>"+
				"<a href='###'  onclick='saveHshbgz()'><img src='${ctx}/static/images/save.png' title='保存'/></a>&nbsp;"+
				"<a href='###'  onclick='back()'><img src='${ctx}/static/images/delete.png' title='取消保存'/></a>&nbsp;</td>"+
				"<td class='td_center'><select id='lx' name='lx' >"+
				"<option value='2' ${param.lx eq '2' ? 'selected' : '' }>延误</option>"+
				"<option value='1' ${param.lx eq '1' ? 'selected' : '' }>提前</option>"+
				"<td class='td_center'><input type='text' name='hkgs' placeholder='---表示全部,多个用/分开:CA/CZ' datatype='*,multihs' onkeyup='value =value.toUpperCase();' beanId='' onblur='checkhkgs(this);'/></td>"+
				"<td class='td_center'><input type='text' name='sc' style='width : 80%;' datatype='n'/></td><td class='td_center'><input type='text' name='bzbz' style='width : 100%;'/></td>"+
				"<td class='td_center'></td><td class='td_center'></td></tr>");
	}
	
	function update(ths){
		$tr = $(ths).parent().parent();
		$tr.after($("tr[name='moban']").clone(true));
		
		var vals = new Array();
 		var i = 0;
 		$.each($tr.children("td"), function(i){				//循环编辑tr的td
 			vals[i] = $(this).text().replace(/ /g,"");		//将text()存进数组
 		});
 		
 		var $mbTr = $tr.next();						//复制过来的模板tr
 		//规则类型
 		$mbTr.find("select[name='lx'] option:contains('" + vals[1] + "')").attr("selected","selected");	
 		//航空公司
 		$mbTr.find("input[name='hkgs']").val(vals[2]);		
 		$mbTr.find("input[name='hkgs']").attr("beanId",vals[7]);		
 		//时长
 		$mbTr.find("input[name='sc']").val(vals[3]);		
 		//备注
 		$mbTr.find("input[name='bzbz']").val(vals[4]);
 		//ID
 		$mbTr.find("input[name='id']").val(vals[7]);
 		$tr.hide();
 		//隐藏所有的编辑
 		$("img[title='编辑']").hide();
 		$("img[title='添加']").hide();
    }
	
	function deleteById(id){
		//弹出删除框
		$.layer({
			area: ['400px', '150px'],
			dialog: {
				msg: '确定删除该选项?',
			    btns: 2,
			    type: 4,
			    btn: ['确定','取消'],
			    yes: function(){
			    	layer.msg('删除成功', 1, 2);
			    	location.href="${ctx}/vedsb/jphbyd/bhkgshbgz/delete_"+ id;
			    }, no: function(){
			    	layer.msg('放弃删除', 1, 3);
			    }
			}
		});
	}
	//取消保存
	function back(){
		location.href="${ctx}/vedsb/jphbyd/bhkgshbgz/query";
	}
	//检查航司是否已经设置过
	function checkhkgs(obj){
		var url = "${ctx}/vedsb/jphbyd/bhkgshbgz/checkhkgs";
		var hkgs = $(obj).val();
		if(hkgs.indexOf("-") != -1 && hkgs.length != 3){
			layer.msg("请输入正确的格式", 1, 3);
			return;
		}
		
		var id = $(obj).attr("beanId");
		if(hkgs != '' ){
			$.ajax({
				type : "post",
				url : url,
				data : {hkgs : hkgs,id : id},
				success : function(result) {
					var code = result.CODE;
					if(code == '-1'){
						layer.msg(result.MSG, 1, 3);
						$(obj).focus();
					}
				}
			});
		}
	}
	
</script>
</head>
<body>
<!--网店页签 -->
<%@include file="list_title.jsp"%>
<div class="container_clear">
	<div class="box_border">
	<form action="" class="jqtransform" id="hbydgzForm" method="POST">
	<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<thead>
			<th width="8%"><a href="###"  onclick="addHbydgz();"><img src="${ctx}/static/images/add.png"  title="添加"/></a></th>
			<th width="5%">类型</th>
			<th width="15%">航空公司</th>
			<th width="8%">时长(分)</th>
			<th width="18%">备注</th>
			<th width="10%">修改人</th>
			<th width="18%">修改时间</th>
		</thead>
		<tbody id="tbdy">
		<c:if test="${not empty hbgzList && fn:length(hbgzList)>0 }">
			<c:forEach items="${hbgzList}" var="hb" varStatus="hbl">
			<tr>
				<td class="td_center">
					<a href="###"  onclick="update(this)"><img src="${ctx}/static/images/edit.png" title="编辑"/></a>&nbsp;
					<a href="###"  onclick="deleteById('${hb.id}')"><img src="${ctx}/static/images/delete.png" title="删除"/></a>&nbsp;
				</td>
				<td class="td_center">${hb.lx eq '1' ? '提前' : hb.lx eq '2' ? '延误':'' }</td>
				<td class="td_center">${hb.hkgs }</td>
				<td class="td_center">${hb.sc }</td>
				<td class="td_center">${hb.bzbz }</td>
				<td class="td_center">${hb.xguserid }</td>
				<td class="td_center">${vfn:format(hb.xgDatetime,'yyyy-MM-dd HH:mm:ss') }</td>
				<td style="display:none">${hb.id}</td>
			</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	</form>
	<table style="display:none">
		<tr name="moban">
			<td class='td_center'>
				<a href='###'  onclick="saveHshbgz()"><img src="${ctx}/static/images/save.png" title="保存"/></a>&nbsp;
				<a href='###'  onclick="back()"><img src="${ctx}/static/images/delete.png" title="取消保存 "/></a>&nbsp;
			</td>
			<td class='td_center'>
				<select id='lx' name='lx' >
					<option value='2' ${param.lx eq '2' ? 'selected' : '' }>延误</option>
					<option value='1' ${param.lx eq '1' ? 'selected' : '' }>提前</option>
				</select>
			</td>
			<td class='td_center'>
				<input type='text' name='hkgs' placeholder='---表示全部,多个用/分开:CA/CZ' datatype="*,multihs" beanId="" onkeyup="value =value.toUpperCase();" onblur="checkhkgs(this);"/>
			</td>
			<td class='td_center'>
				<input type='text' name='sc' style='width : 80%;' datatype="n"/>
			</td>
			<td class='td_center'><input type='text' name='bzbz' style='width : 100%;'/></td>
			<td class='td_center'></td>
			<td class='td_center'></td>
			<td class='td_center' style="display: none">
				<input type="hidden" name="id"/>
			</td>
		</tr>
	</table>
	</div>
</div>
</body>
</html>