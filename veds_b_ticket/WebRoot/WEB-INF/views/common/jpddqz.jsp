<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>

<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	//关闭当前弹窗
	function closeSelf(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
	
	//保存签注
	function addQz(from){
		var ddbh=document.getElementById("qz_ywdh").value;
		var content=document.getElementById("newQz").value;
		var ywlx=document.getElementById("ywlx").value;
		if(!content||content.trim()==""){
			layer.msg("签注内容不能为空！",2,0);
			return ;
		}
		else if(content.length>3500){
			layer.msg("签注内容的长度不能超过3500个字符！",2,0);
			return ;
		}
		var url="${ctx}/vedsb/common/jpcommon/saveQz";
		var data={"ywdh":ddbh,"qzNr":content,"ywlx":ywlx};
		$.ajax({
    	 	type:"post",
			url:url,
			data:data,
			success:function(result){
				if("1" == result){
					if(from == 'list'){//从列表弹窗添加签注信息
						layer.msg("保存签注成功！",2,1);
						window.parent.$("#searchForm").pageSearch();	
					} else {//详页面添加签注
						window.parent.location.reload();
					}
				}else{
					layer.msg("保存签注失败！",2,0);
				}
				setTimeout("closeSelf()",1000);
			}
     	});
	}
</script>

<div>
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="3%">序号</th>
			<th width="10%">签注时间</th>
			<th width="10%">签注人</th>
			<th width="65%">签注内容</th>
		</tr>
		<c:forEach items="${list}" var="vc" varStatus="varone">
			<tr>
				<td align="center" style="width:10px;">${varone.count}</td>
				<td align="center">${vc.qzSjstr }</td>
				<td align="center">${vc.ex.QZYHBH.xm}</td>
				<td align="left">${vc.qzNr }</td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" name="ywdh"  id="qz_ywdh" value="${empty param.ywdh ? ywdh : param.ywdh}">
	<input type="hidden" name="ywlx"  id="ywlx" value="${ywlx}">
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<td align="right" style="width:50px;border:none;">
			 	新签注
			</td>
			<td align="left" style="width:510px;border:none;">
				<textarea name="qzNr" id="newQz" style="width:500px;" rows="3"></textarea>
				<c:if test="${from eq 'list' }">
					<font color="red">*</font>
				</c:if>
			</td>
			<c:if test="${from ne 'list' }">
				<td align="left" style="border:none;">
					<input type="button" name="saveQz" value="保存签注" class="ext_btn ext_btn_submit" onclick="addQz('${from}')"/>
				</td>
			</c:if>
		</tr>
		<c:if test="${from eq 'list' }">
			<tr>
				<td colspan="4" align="center">
					<input type="button" name="saveQz" value="确定" class="ext_btn ext_btn_submit" onclick="addQz('${from}')"/>
					<input type="button" name="cancleQz" value="关闭" class="ext_btn ext_btn_success" onclick="closeSelf()"/>
				</td>
			</tr>
		</c:if>
	</table>

</div>