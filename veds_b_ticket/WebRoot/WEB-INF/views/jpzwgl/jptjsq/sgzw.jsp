<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>手工追位</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<style>
		
.td_right{
	width:60px;
}
 </style>
<script type="text/javascript">
var validator;
$(function(){
	validator = $("#sgzw").Validform({
		tiptype:3,
		beforeSubmit:function(curform){
			layer.load('系统正在处理您的操作,请稍候!');
		},
		ajaxpost:{
			success:function(data){
				$.Hidemsg();
				if(data.result.status=='0'){
					$(parent.document.getElementById('searchFormButton')).click();
					parent.layer.msg('申请追位成功',2,1);
					closeZwgz();
				}else{
					layer.msg(data.result.errMsg,3,3);
				}
			}
		}
	});
	if("-1"=="${status}"){
		$(parent.document.getElementById('searchFormButton')).click();
		parent.layer.msg('${error}',3,3);
		closeZwgz();
	}
});
//关闭页面
function closeZwgz(){
	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
}
function saveZw(){
	validator.ajaxPost(false,false,'${ctx}/vedsb/jpzwgl/jptjsq/saveSgTjsq');
}
function changeZwfs(lb){
	if(lb=='0'){
		showOrHideElememt('1','qwcwtr0,qwcwtr1,qwcwtr2','');
		showOrHideElememt('0','qwjgtr','qwjg');
		$('#qwcw').attr('ignore','ignore');
		$('#qwjg').removeAttr('ignore','ignore');
	}else{
		showOrHideElememt('1','qwjgtr','');
		showOrHideElememt('0','qwcwtr0,qwcwtr1,qwcwtr2','');
		$('#qwjg').attr('ignore','ignore');
		$('#qwcw').removeAttr('ignore','ignore');
	}
}
</script>
</head>
<body>
	<div class="container" style="background:#EBF2FC;border:0;height:280px">
	<div id="forms">
        <div class="">
          <div class="">
            <div class="">
              <form action="${ctx}/vedsb/jpzwgl/jptjsq/saveSgTjsq" class="jqtransform" id="sgzw" method="POST">
              <input type="hidden" name="closeDiv" value="true"/>
              <input type="hidden" name="ddbh" value="${param.ddbh}"/>
              <input type="hidden" name="callback"  value="parent.refresh()" />
              <table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0">
              	<tr>
	                <td class="td_right">原PNR:</td>
	                <td>
	                	${oldpnr}
	                </td>
	                <td class="td_right">原舱位:</td>
	                <td>
	                	${oldcabin}
	                </td>
	          	</tr>
	          	<c:if test="${gngj eq '1'}">
	          		<tr>
		                <td class="td_right">追位类型:</td>
		                <td colspan="3">
		                	<input type="radio" name="zwlx" value="0" id="zwlx0" onclick="changeZwfs('0')"><label for="zwlx0">票价</label>
		                	<input type="radio" name="zwlx" value="1" id="zwlx0" onclick="changeZwfs('1')" checked><label for="zwlx1">舱位</label>
		                </td>
	          		</tr>
	          		<tr id="qwjgtr" style="display:none;">
	          			<td class="td_right">期望价格:</td>
		                <td>
		                	<input type="text" name="qwjg" id="qwjg" ignore="ignore" datatype="*,number,minvalue,maxvalue,dotformat" minvalue="0" maxvalue="${price}" dotformat="######.##"  class="input-text lh25"  size="5"
		                 		value="${price}"/>
		                </td>
		            </tr>
	          		<tr id="qwcwtr0">
	          			<td class="td_right">期望舱位:</td>
		                <td>
		                	<input type="text" name="qwcw" id="qwcw" datatype="*" class="input-text lh25"  size="5"
		                 		value="${cw_return}" onkeyup="this.value=this.value.toUpperCase();"/>
		                </td>
		                <td colspan="2">
		                	<input type="radio" name="zwfs" value="0" id="zwfs0" checked><label for="zwfs0">该舱位及以下</label>
			                <input type="radio" name="zwfs" value="1" id="zwfs0"><label for="zwfs1">只追该舱位</label>
		                </td>
		            </tr>
		            <tr id="qwcwtr1">
		                <td colspan="4" style="padding-left:10px;color:#1D79C7;">可以追的舱位有:</td>
	          		</tr>
	          		<tr id="qwcwtr2">
		                <td colspan="4" style="padding-left:10px;color:#1D79C7;">${kzcw}</td>
	          		</tr>
	          	</c:if>
	          	<c:if test="${gngj eq '0'}">
	          		<td class="td_right">目标舱位:</td>
	                <td>
	                	<input type="text" name="mbcw" id="mbcw" datatype="*" class="input-text lh25"  size="5"
	                 		value="" onkeyup="this.value=this.value.toUpperCase();"/>
	                </td>
	          	</c:if>
	          		<tr>
	          			<td colspan="4" style="text-align:center;">
	          				<input type="button" name="button" onclick="saveZw();" class="ext_btn ext_btn_submit" value="确认"/>
	          				<input type="button" name="button" onclick="closeZwgz()" class="ext_btn" value="关闭">
	          			</td>
	          		</tr>
                </table>
               </form>
               <div style="padding-left: 10px;">
    		  	 <div style="color:red;">温馨提示:</div>
               	 <div style="color:rgb(142, 142, 142);">
               	 	<c:if test="${gngj eq '1'}">
	                	1.航程为单程且乘机人均为成人或儿童的订单才能申请追位;<br>
	                	2.申请中订单默认追原舱位;<br>
	                	3.特价舱位订单不能追原舱位,且只能追特价舱位;<br>
	                	4.公布运价舱位且位置已订妥的订单，默认追原舱位的下一舱位。
	                </c:if>
	                <c:if test="${gngj eq '0'}">
	                	1.航程为单程且乘机人均为成人或儿童的订单才能申请追位;<br>
	                	2.多个舱位用逗号(,)分隔,追位舱位匹配顺序与输入的舱位顺序一致。<br>
	                </c:if>
				  </div>
	    		</div>
            </div>
          </div>
        </div>
     </div>
 </div>
</body>
</html>
