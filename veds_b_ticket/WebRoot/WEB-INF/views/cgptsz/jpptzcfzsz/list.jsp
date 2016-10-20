<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>支付方式对应</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<style type="text/css">
	.short1{
			width:100%;
		 	white-space:nowrap;
			overflow:hidden;
		 	text-overflow:ellipsis;
		}
	a {
		text-decoration:none;
	}
	.ptzh_right{
		text-align: right;
	}
	.ptzh_left{
		text-align: left;
	}
</style>
<script type="text/javascript">
	var myindex =0;
	/**
	 * 为table指定行添加一行
	 * tab 表id
	 * mb  td模板
	 * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
	 * trHtml 添加行的html代码
	 */
	function addTr(tab, mb, row){
	   var $tr=$("#"+tab+" tr").eq(row);
	   var size = $tr.size();
	   var temp = $("#"+mb).html();
	   $tr.after(temp.replace(new RegExp("___", 'g'), (++myindex)));
	   var i = 1;
	   $(".sxh").each(function(){
		   $(this).html(i);
		   i = i+1;
	   })
	}
	  
	function delTr(tableId, obj, fzszid){
		var lengh =$("#"+tableId+" tr").length;
		if(lengh <= 2){
			alert("至少保留一条记录");
			return;
		}
		if (fzszid != "") {
			var url = "${ctx}/vedsb/cgptsz/jpptzcfzsz/delFzsz_" + fzszid;
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
		  						if("0" == result.status){
		  							layer.msg("删除成功！",2,1);
		  							$(obj).parent().parent().remove();
		  							var i = 1;
		  						    $(".sxh").each(function(){
		  							    $(this).html(i);
		  							    i = i+1;
		  						    })
		  						}else{
		  							layer.msg(result.result,2,1);
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
	
	var validator;
	function toSave(){
		var fzfzfs = $("select[name='fzFzf']");
		var ptzcbses = $("select[name='ptzcbs']");
		for (var i=0;i<fzfzfs.length;i++) {
			var fzfzfval = fzfzfs[i].value;
			var ptzcbsval = ptzcbses[i].value;
			for (var j=0;j<fzfzfs.length;j++) {
				if (i != j && fzfzfval == fzfzfs[j].value && ptzcbsval == ptzcbses[j].value) {
					layer.msg("同一平台的同一支付方式不能重复！",2,1);
					return;
				}
			}
		}
		validator.submitForm(false);
	}
	
	var ii;
	$(function(){
		validator = $("#fzszForm").Validform({
			tiptype:4,
			ajaxPost:true,
			callback:function(transport){
				layer.close(ii);
				if(transport.status=="0"){
			   		location.reload();
				}else{
				   alert(transport.result);
				}
			  },
			beforeSubmit:function(curform){
				//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
				//这里明确return false的话表单将不会提交;
				ii = layer.load('系统正在处理您的操作,请稍候!');
			}
		});
	})
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/cgptsz/list_title.jsp"%>
	<form action="${ctx}/vedsb/cgptsz/jpptzcfzsz/saveFzsz" id="fzszForm" method="post" >
		<input id="del" type="hidden" name="del" value=""/>
		<input  type="hidden" name="ywlx" value="2"/>
		<table cellpadding="0" class="list_table" cellspacing="0" style="width: 800px" id="insertRow1">
			<tr>
				<td align="center">序号</td>
				<td align="center">平台</td>
				<td align="center">平台支付方式</td>
				<td align="center">系统采购科目</td>
				<td align="center"><img src="${ctx}/static/images/wdimages/drop-add.gif" title="点击添加乘机人" onclick="addTr('insertRow1','tpl1', -1);"/></td>
			</tr>
			<script id="tpl1" type="text/html">
			<tr>
				<input type="hidden" name="id" value=""/>
				<td align="center" class="sxh"></td>
				<td align="center">
					<select name="ptzcbs" datatype="*" nullmsg="请选择值">
						<option value="">==请选择==</option>
						<c:forEach items="${vfc:getVeclassLb('100021')}" var="ptsz">
							<c:if test="${ptsz.id ne '100021' }">
								<option value="${ptsz.ywmc}" >${ptsz.mc}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<td align="center">
					<select name="fzFzf" datatype="*" nullmsg="请选择值" >
						<option value="">==请选择==</option>
						<c:forEach items="${vfc:getVeclassLb('100023')}" var="zffs">
							<c:if test="${zffs.id ne '100023' }">
								<option value="${zffs.ywmc}" >${zffs.mc}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<td align="center">
					<select name="xtZfkm" datatype="*" nullmsg="请选择值">
						<option value="">==请选择==</option>
						<c:forEach items="${zfkmList}" var="zfkm">
								<option value="${zfkm.kmbh}" >${zfkm.kmmc}</option>
						</c:forEach>
					</select>
				</td>
				<td align="center"><img src="${ctx}/static/images/wdimages/drop-no.gif" title="点击删除乘机人" onclick="delTr('insertRow1', this, '');"/></td>
			</tr>
			</script>
			<c:if test="${empty fzszList or fn:length(fzszList) == 0}">
				<tr>
					<input type="hidden" name="id" value=""/>
					<td align="center" class="sxh"></td>
					<td align="center">
						<select name="ptzcbs" datatype="*" nullmsg="请选择值">
							<option value="">==请选择==</option>
							<c:forEach items="${vfc:getVeclassLb('100021')}" var="ptsz">
								<c:if test="${ptsz.id ne '100021' }">
									<option value="${ptsz.ywmc}" >${ptsz.mc}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td align="center">
						<select name="fzFzf" datatype="*" nullmsg="请选择值" >
							<option value="">==请选择==</option>
							<c:forEach items="${vfc:getVeclassLb('100023')}" var="zffs">
								<c:if test="${zffs.id ne '100023' }">
									<option value="${zffs.ywmc}" >${zffs.mc}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td align="center">
						<select name="xtZfkm" datatype="*" nullmsg="请选择值">
							<option value="">==请选择==</option>
							<c:forEach items="${zfkmList}" var="zfkm">
									<option value="${zfkm.kmbh}" >${zfkm.kmmc}</option>
							</c:forEach>
						</select>
					</td>
					<td align="center"><img src="${ctx}/static/images/wdimages/drop-no.gif" title="点击删除乘机人" onclick="delTr('insertRow1', this, '');"/></td>
				</tr>
			</c:if>
			<c:forEach items="${fzszList }" var ="fzsz"  varStatus="i">
				<tr>
					<input type="hidden" name="id" value="${fzsz.id}"/>
					<td align="center" class="sxh">${i.count}</td>
					<td align="center">
						<select name="ptzcbs" datatype="*" nullmsg="请选择值">
							<option value="">==请选择==</option>
							<c:forEach items="${vfc:getVeclassLb('100021')}" var="ptsz">
								<c:if test="${ptsz.id ne '100021' }">
									<option value="${ptsz.ywmc}" ${fzsz.ptzcbs eq ptsz.ywmc ? 'selected' : '' }>${ptsz.mc}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td align="center">
						<select name="fzFzf" datatype="*" nullmsg="请选择值">
							<option value="">==请选择==</option>
							<c:forEach items="${vfc:getVeclassLb('100023')}" var="zffs">
								<c:if test="${zffs.id ne '100023' }">
									<option value="${zffs.ywmc}" ${fzsz.fzFzf eq zffs.ywmc ? 'selected' : '' }>${zffs.mc}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td align="center">
						<select name="xtZfkm" datatype="*" nullmsg="请选择值">
							<option value="">==请选择==</option>
							<c:forEach items="${zfkmList}" var="zfkm">
									<option value="${zfkm.kmbh}" ${fzsz.xtZfkm eq zfkm.kmbh ? 'selected' : '' }>${zfkm.kmmc}</option>
							</c:forEach>
						</select>
					</td>
					<td align="center"><img src="${ctx}/static/images/wdimages/drop-no.gif" title="点击删除乘机人" onclick="delTr('insertRow1', this, '${fzsz.id}');"/></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<div style="width: 800px;text-align: center;"><input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();"></div>
</body>
</html>