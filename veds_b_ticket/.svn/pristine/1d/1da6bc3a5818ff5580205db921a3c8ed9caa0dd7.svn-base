<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	$(function(){
		var url="${ctx}/vedsb/bbzx/tpbb/queryWdList";
		$.ajax({
			type:"post",
			url:url,
			success:function(result){
				var zfkm=result.SHZFKMDATA;
				for(var i=0;i<zfkm.length;i++){
					var sel='${jpkhdd.skkm }'==zfkm[i].kmbh ? 'selected' : '';
					var $opt="<option value="+zfkm[i].kmbh+" "+sel+">"+zfkm[i].kmmc+"</option>";
					$("#tkkmid").append($opt);
				}
				 
			}
		});
	});
</script>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" id="hcInfo">
	<tr> 
	   <td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/blxx.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">退票信息</span>
		</td>
		<td>
			<table class="table01" cellpadding="0" cellspacing="0" align="left" style="height:90px;margin-left: 0px;">
			  <tr>
			     <td align="right">退款科目：</td>
			     <td align="left"> 
			         <select name="tkkm" class="select" id="tkkmid">
			   		 	<option value="">==请选择==</option>
			   		 </select>
			     </td>
			     
			     <td align="right">是否自愿退票:</td>
			     <td align="left">
			     	<input type="radio" id="xsSfzytp" name="xsSfzytp" value="1" checked onclick="$('#zytply').show();$('#fzytply').hide();$('#zytply').removeAttr('disabled');$('#fzytply').attr('disabled','disabled');" class="validate-one-required">
					<label for="sfzytf1">是</label>
					<input type="radio" id="xsSfzytp" name="xsSfzytp" value="0" onclick="$('#zytply').hide();$('#fzytply').show();$('#zytply').attr('disabled','disabled');$('#fzytply').removeAttr('disabled'); " class="validate-one-required">
					<label for="sfzytf0">否</label>
			     </td>
			     <td>退票理由： </td>
			     <td>
			         <!-- 自愿退票理由 -->
			         <select id="zytply" name="xsTply" style="width:200px;">
							<c:forEach items="${vfc:getVeclassLb('10015')}"  var="zfyybh">
								<c:if test="${zfyybh.id eq '1001501'}">
									<option value="${zfyybh.id}" title="${zfyybh.mc}">${zfyybh.mc}</option>
								</c:if>
							</c:forEach>
					</select> 
					<!-- 非自愿退票理由 -->
					<select id="fzytply" name="xsTply" style="width:200px;display:none" disabled>
						    <option value="">===请选择理由===</option>
							<c:forEach items="${vfc:getVeclassLb('10015')}"  var="zfyybh">
								<c:if test="${zfyybh.id ne '10015' and zfyybh.id ne '1001501' }">
									<option value="${zfyybh.id}" title="${zfyybh.mc}">${zfyybh.mc}</option>
								</c:if>
							</c:forEach>
					</select> 
			     </td>
			  </tr>
			</table>
		</td>
	</tr>
</table>