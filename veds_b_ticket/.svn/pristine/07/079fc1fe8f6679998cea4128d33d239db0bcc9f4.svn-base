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

	function addHbydgz(){
		//隐藏所有的编辑
		$("img[title='添加']").hide();
 		$("img[title='编辑']").hide();
		$("#tbdy").append("<tr><td class='td_center'>"+
				"<a href='###'  onclick='saveHbydgz()'><img src='${ctx}/static/images/save.png' title='保存'/></a>&nbsp;"+
				"<a href='###'  onclick='back()'><img src='${ctx}/static/images/delete.png' title='取消保存'/></a>&nbsp;</td>"+
				"<td class='td_center'><select id='lx' name='lx' >"+
				"<option value='2' ${param.rqtj eq '2' ? 'selected' : '' }>延误</option>"+
				"<option value='1' ${param.rqtj eq '1' ? 'selected' : '' }>提前</option>"+
				"<option value='3' ${param.rqtj eq '3' ? 'selected' : '' }>取消</option></select></td>"+
				"<td class='td_center'><input type='text' name='hkgs' placeholder='---表示全部,多个用/分开:CA/CZ' datatype='*,multihs' onkeyup='value =value.toUpperCase();' beanId='' onblur='checkhkgs(this);'/></td>"+
				"<td class='td_center'><input type='text' name='fws' style='width : 30%;' datatype='n'/>-<input type='text' id='fwz' name='fwz' style='width : 30%;' datatype='n'/></td>"+
				"<td class='td_center'><input type='checkbox' id='telphone' name='telphone' checked /></td>"+
				"<td class='td_center'><input type='checkbox' id='infomation' name='infomation' checked /></td>"+
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
 		$mbTr.find("select[name='lx'] option:contains('" + vals[1] + "')").attr("selected","selected");	//规则类型
 		$mbTr.find("input[name='hkgs']").val(vals[2]);		//航空公司
 		$mbTr.find("input[name='hkgs']").attr("beanId",vals[8]);
 		$mbTr.find("input[name='fws']").val(vals[3].split("-")[0]);		//时长范围始
 		$mbTr.find("input[name='fwz']").val(vals[3].split("-")[1]);		//时长范围止
 		if(vals[4] == '否'){
 			$mbTr.find("input[name='telphone']").removeAttr("checked");	//是否电话通知
 		}
 		if(vals[5] == '否'){
 			$mbTr.find("input[name='infomation']").removeAttr("checked");//是否短信通知
 		}
 		$mbTr.find("input[name='id']").val(vals[8]);
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
			    	location.href="${ctx}/vedsb/jphbyd/bqinfohbydgz/deleteBQinfoHbydgz_"+ id;
			    }, no: function(){
			    	layer.msg('放弃删除', 1, 3);
			    }
			}
		});
	}
	function back(){
		location.href="${ctx}/vedsb/jphbyd/bqinfohbydgz/queryList";
	}
	//保存
	function saveHbydgz(){
		if(validator.check()){
			var url = "${ctx}/vedsb/jphbyd/bqinfohbydgz/saveBean";
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
	
	//检查航司是否已经设置过
	function checkhkgs(obj){
		var url = "${ctx}/vedsb/jphbyd/bqinfohbydgz/checkhkgs";
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
<%@include file="/WEB-INF/views/jphbyd/bhkgshbgz/list_title.jsp"%>
<div class="container_clear">
	<div class="box_border">
	<form action="" class="jqtransform" id="hbydgzForm" method="POST">
	<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<thead>
			<th width="8%"><a href="###"  onclick="addHbydgz();"><img src="${ctx}/static/images/add.png" title="添加"/></a></th>
			<th width="5%">类型</th>
			<th width="15%">航空公司</th>
			<th width="8%">时长范围(分)</th>
			<th width="5%">电话通知</th>
			<th width="5%">短信通知</th>
			<th width="10%">修改人</th>
			<th width="18%">修改时间</th>
		</thead>
		<tbody id="tbdy">
		<c:if test="${not empty hbList && fn:length(hbList)>0 }">
			<c:forEach items="${hbList}" var="hb" varStatus="hbl">
			<tr>
				<td class="td_center">
					<a href="###"  onclick="update(this)"><img src="${ctx}/static/images/edit.png" title="编辑"/></a>&nbsp;
					<a href="###"  onclick="deleteById('${hb.id}')"><img src="${ctx}/static/images/delete.png" title="删除"/></a>&nbsp;
				</td>
				<td class="td_center">${hb.lxName }</td>
				<td class="td_center">${hb.hkgs }</td>
				<td class="td_center">${hb.fws } - ${hb.fwz }</td>
				<td class="td_center">${hb.telphoneName }</td>
				<td class="td_center">${hb.infomationName }</td>
				<td class="td_center">${hb.xguserid }</td>
				<td class="td_center">${hb.xgDatetimeStr }</td>
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
				<a href='###'  onclick="saveHbydgz()"><img src="${ctx}/static/images/save.png" title="保存"/></a>&nbsp;
				<a href='###'  onclick="back()"><img src="${ctx}/static/images/delete.png" title="取消保存"/></a>&nbsp;
			</td>
			<td class='td_center'>
				<select id='lx' name='lx' >
					<option value='2' ${param.rqtj eq '2' ? 'selected' : '' }>延误</option>
					<option value='1' ${param.rqtj eq '1' ? 'selected' : '' }>提前</option>
					<option value='3' ${param.rqtj eq '3' ? 'selected' : '' }>取消</option>
				</select>
			</td>
			<td class='td_center'>
				<input type='text' name='hkgs' placeholder='---表示全部,多个用/分开:CA/CZ' datatype="*,multihs" beanId="" onkeyup="value =value.toUpperCase();" onblur="checkhkgs(this);"/>
			</td>
			<td class='td_center'>
				<input type='text' name='fws' style='width : 30%;' datatype="n"/>-<input type='text' id='fwz' name='fwz' style='width : 30%;' datatype="n"/>
			</td>
			<td class='td_center'>
				<input type='checkbox' id='telphone' name='telphone' checked />
			</td>
			<td class='td_center'>
				<input type='checkbox' id='infomation' name='infomation' checked />
			</td>
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