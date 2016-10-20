<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title></title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<style>
		
.td_right{
	width:90px;
}
 </style>
<script type="text/javascript">
var validator;
$(function(){
	var correct ="${correct}";
	if(correct=="1"){
		$.layer.msg('保存成功',2,1);
	}
	validator = $('#zwsz').Validform({
		tiptype:3,
	});
	initZclx('${entity.wdZclx}');
	if($('#wdpt').val()=='---'){
		var wdptall=document.getElementById('khgsallgn');
		wdptall.checked=true;
		selectAllBox(wdptall,'wdptbox','wdpt',changeWdpt);
	}
	changeMblb('${empty entity.mblx ? '1' : entity.mblx}');
	changeCpzt('${empty entity.cpzt ? '0' : entity.cpzt}');
});
function saveZwgz(nums){
	if(document.getElementById('mblx2').checked==true){
		$('#mbcwcj').val('0');
		var cw=$.trim($('#cw').val());
		var mbcw=$.trim($('#mbcw').val());
		if(!cw || cw=='---'){
			layer.msg('您选择的是指定舱位，必须设置舱位，不能设置为所有',2,3);
			return false;
		}
		if(!mbcw || mbcw=='---'){
			layer.msg('您选择的是指定舱位，必须设置目标舱位，舱位和目标舱位必须一一对应',2,3);
			return false;
		}
		var lmbcw=mbcw.split('/').length;
		if(mbcw.lastIndexOf('/')==(mbcw.length-1)){
			lmbcw=lmbcw-1;
		}
		var lcw=cw.split('/').length;
		if(cw.lastIndexOf('/')==(cw.length-1)){
			lcw=lcw-1;
		}
		if(lmbcw !=lcw ){
			layer.msg('您选择的是指定舱位，舱位和目标舱位必须一一对应',2,3);
			return false;
		}
	}
	validator.submitForm(false);
}
//关闭页面
function closeZwgz(){
	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
}
//目标类别修改
function changeMblb(mblx){
	if(mblx=='2'){//指定舱位
		showOrHideElememt('0','mbcwtr','mbcw');
		showOrHideElememt('1','mbcwcjtr','mbcwcj');
		mbcwWxts();
	}else{
		showOrHideElememt('1','mbcwtr','mbcw');
		showOrHideElememt('0','mbcwcjtr','mbcwcj');
	}
}
//已出票，选择指定舱位时温馨提示
function mbcwWxts(){
	if($('#mblx2').attr('checked')){
		var cpzt=$('#cpzt').val();
		if(cpzt=='1'){
			layer.tips('已出票的订单追位会产生退票费，指定舱位时请考虑退票费成本！',$('#mbcw'),
			{
				guide: 1,
				maxWidth:200,
				time:8,
				style: ['background-color:#78BA32; color:#fff', '#78BA32'],
				closeBtn:[0, true]});
		}
	}
}
//复选框全选，
function selectAllBox(obj,boxname,hiddenId,callback){
	$(':checkbox[name="'+boxname+'"]').each(function(){
		this.checked=obj.checked;
	});
	if(obj.checked){
		$('#'+hiddenId).val('---');
	}else{
		$('#'+hiddenId).val('---');
	}
	if(!!callback){
		callback();
	}
}
//点击一个复选框
function selectOneBox(qxboxId,boxname,hiddenId,callback){
	var wds='';
	var allFlag=true;
	var allNot=true;
	$(':checkbox[name="'+boxname+'"]').each(function(){
		if(this.checked){
			wds+="/"+$(this).val();
			allNot=false;
		}else{
			allFlag=false;
		}
	});
	if(allFlag || allNot){
		$('#'+hiddenId).val('---');
	}else{
		$('#'+qxboxId).attr('checked',false);
		if(!!wds){
			wds=wds.substr(1);
		}
		$('#'+hiddenId).val(wds);
	}
	if(!!callback){
		callback();
	}
}
function changeWdpt(){
	var wdpt=$('#wdpt').val();
	if(!wdpt || wdpt == '---' || wdpt.split('/').length>1){
		$('#wdzclxtr').hide();
		$('#wdZclx').val('---');
	}else{
		$('[name="wdzclxdiv"]').each(function(){
			$('#wdzclxtr').hide();
			$(this).hide();
		});
		var zclxall=document.getElementById('zclxall'+wdpt);
		if(!!zclxall){
			$('#wdzclxtr').show();
			$('#zclx'+wdpt).show();
			zclxall.checked=true;
			selectAllBox(zclxall,'wdzclxbox'+wdpt,'wdZclx');
		}else{
			$('#wdZclx').val('---');
		}
	}
}
function initZclx(zclx){
	if(!zclx){
		return false;
	}
	var wdpt=$('#wdpt').val();
	if(!wdpt || wdpt == '---' || wdpt.split('/').length>1){
		$('#wdzclxtr').hide();
		$('#wdZclx').val('---');
	}else{
		$('[name="wdzclxdiv"]').each(function(){
			$(this).hide();
		});
		if(zclx=='---'){
			var zclxall=document.getElementById('zclxall'+wdpt);
			if(!!zclxall){
				$('#zclx'+wdpt).show();
				$('#wdzclxtr').show();
				zclxall.checked=true;
				selectAllBox(zclxall,'wdzclxbox'+wdpt,'wdZclx');
			}else{
				$('#wdZclx').val('---');
			}
		}else{
			$('#zclx'+wdpt).show();
			$('#wdzclxtr').show();
			$(':checkbox[name="wdzclxbox"'+wdpt+']').each(function(){
				this.checked =("/"+zclx+"/").indexOf("/"+$(this).val()+"/") > -1;
			});
		}
	}
}
function changeCpzt(zt){
	if(zt=='1'){
		$('#ycpsm').show();
	}else{
		$('#ycpsm').hide();
	}
	mbcwWxts();
}
</script>
</head>
<body>
 <div class="container">
 <div id="forms">
        <div class="box">
          <div class="box_border">
            <div class="box_center">
              <form action="${ctx}/vedsb/jpzwgl/jptjsqzwsz/saveZwgz" class="jqtransform" id="zwsz" method="POST">
              <input type="hidden" name="closeDiv" value="true"/>
              <input type="hidden" name="callback"  value="parent.refresh()" />
              <input  type="hidden" name="id" value="${entity.id }">
              <table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0">
              	<tr>
	                <td class="td_right">网店平台</td>
	                <td colspan="3">
	                	<input type="checkbox" id="khgsallgn" name="hkgsallgn" 
				  	   			onclick="selectAllBox(this,'wdptbox','wdpt',changeWdpt);" ${entity.wdpt eq '---' ? 'checked' : '' }/>
				  	   	<label for="khgsallgn">全选</label>
                  	 	<c:forEach items="${shwhkzList}" var="onewdpt" varStatus="wdptStatus">
	                  	 		<input type="checkbox" name="wdptbox" id="${onewdpt.wdlx}" value="${onewdpt.wdlx}" 
								  onclick="selectOneBox('khgsallgn','wdptbox','wdpt',changeWdpt)"
								${entity.wdpt eq '---' or fn:indexOf(entity.wdpt,onewdpt.wdlx)>-1 ? 'checked' :'' }/>
								<label for="${onewdpt.wdlx}">${onewdpt.wdmc}</label>
                  	 	</c:forEach>
                  	 	<input type="hidden" name="wdpt" id="wdpt" value="${not empty entity.wdpt ? entity.wdpt : '---'}">
	                </td>
	          	</tr>
	            <tr style="display:none;" id="wdzclxtr">
                	<td class="td_right">网店政策类型</td>
	                <td colspan="3">
	                 	 <input type="hidden" id="wdZclx" name="wdZclx" value="${not empty entity.wdZclx ? entity.wdZclx : '---'}">
	                 	 <c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
	                 	 	<c:if test="${onewdpt.parid ne 'none'}">
	                 	 		<div id="zclx${onewdpt.id}" style="display:none;" name="wdzclxdiv">
	                 	 			<c:set var="ind" value="0"/>
	                 	 			<c:forEach items="${vfc:getVeclassLb(onewdpt.id)}" var="onezclx" varStatus="index">
	                 	 				<c:if test="${onezclx.parid ne 'none'}">
	                 	 					<c:if test="${ind eq '0'}">
		                 	 					<input type="checkbox" id="zclxall${onewdpt.id}" name="zclxall" 
										  	   			onclick="selectAllBox(this,'wdzclxbox${onewdpt.id}','wdZclx');"/>
										  	   	<label for="khgsallgn">全选</label>
		                 	 				</c:if>
		                 	 				<c:set var="ind" value="1"/>
				                  	 		<input type="checkbox" name="wdzclxbox${onewdpt.id}" id="${onezclx.id}" value="${onezclx.mc}" 
											  onclick="selectOneBox('zclxall${onewdpt.id}','wdzclxbox${onewdpt.id}','wdZclx')"
											${entity.wdZclx eq '---' or fn:indexOf(entity.wdZclx,onezclx.mc)>-1 ? 'checked' :'' }/>
											<label for="${onezclx.id}">${onezclx.mc}</label>
			                  	 		</c:if>
	                 	 			</c:forEach>
	                 	 		</div>
	                 	 	</c:if>
	                 	 </c:forEach>       	  
	                </td>
                </tr>
	            <tr>
	                <td class="td_right">航空公司</td>
	                <td colspan="3">
	                 	<input type="text" name="hkgs" id="hkgs" datatype="*,multihs" class="input-text lh25" size="28" 
	                 		value="${not empty entity.hkgs ? entity.hkgs : '---'}" onkeyup="this.value=this.value.toUpperCase()" />
		            	<font color="red"> *</font><font color="gray">(支持多个，用 "/"分割。例如：CZ/3U，---表示全部)</font>
	                </td>
                </tr>
                <tr>
	                <td class="td_right">提前天数</td>
	                <td colspan="3">
                      	<input type="text" name="tqts" id="tqts"  datatype="number,minvalue,maxvalue,dotformat" dotformat="###." minvalue="0" maxvalue="999" 
                      		value="${not empty entity.tqts ? entity.tqts : 0}"  class="input-text"  style="width:70px;"/>
				     	<label><font color="red">*</font><span style="color: gray;">(起飞日期限制，0表示不限制，设为几则表示只追几天后起飞的航班)</span></label>
			      	</td>
                </tr>
                <tr>
	                <td class="td_right">PNR状态</td>
	                <td colspan="3">
						<input type="text"  id="pnrzt" datatype="*" size="28" name="pnrzt" value="${not empty entity.pnrzt ? entity.pnrzt : '---'}" maxlength="120"  onblur="value=$.trim(this.value).toUpperCase();" class="input-text lh25"/>
						<font color="red">*</font><font color="gray">(支持多个，用 "/"分割。例如： HK/KK，---表示全部)</font>
					</td>
                </tr>
                <tr>
	               	<td class="td_right">出票状态</td>
	                <td colspan="3">
	                   <select name="cpzt" id="cpzt" class="select"  style="width:70px;" onchange="changeCpzt(this.value)">
	            	 		<option value="0" ${empty entity.cpzt or entity.cpzt eq '0' ? 'selected' : '' }>未出票</option>
	            	 		<option value="1" ${entity.cpzt eq '1' ? 'selected' : '' }>已出票</option>
	                	</select>
			      	</td>
                </tr>
                <tr>
                	<td class="td_right">目标类型</td>
                	<td colspan="3">
                		<input type="radio" name="mblx" value="1" id="mblx1"  ${entity.mblx eq '1' or empty entity.mblx ? ' checked ' : '' }  onclick="changeMblb(this.value)"/>指定差价
                  	  	<input type="radio" name="mblx" value="2" id="mblx2"  ${entity.mblx eq '2' ? ' checked ' : '' } onclick="changeMblb(this.value)"/>指定目标舱位 
                	</td>
                </tr>
                <tr id = "rwhtr">
	                <td class="td_right">舱位</td>
	                <td colspan="3">
	                     <input type="text" name="cw" id="cw" size="28"  datatype="*,multicw" value="${not empty entity.cw ? entity.cw : '---'}"  class="input-text lh25"  onkeyup="this.value=this.value.toUpperCase()" nullmsg="请填写信息！"/>
					     <label><font color="red">*</font><span style="color: gray;">(支持多个,用 "/"分割。例如： F/B/Y,---表示全部)</span></label>
				    </td>
                </tr>
                <tr id="mbcwtr">
                	<td class="td_right">目标舱位</td>
                	<td colspan="3">
                		<div style="float:left;">
                     		<input type="text" name="mbcw" id="mbcw" size="28"  datatype="*,multicw" value="${not empty entity.mbcw ? entity.mbcw : '---'}"  class="input-text lh25"  onkeyup="this.value=this.value.toUpperCase()" nullmsg="请填写信息！"/>
                     		<font color="red">*</font>
                     	</div>
                     	<div>
			    			<label><span style="color: gray;">(与舱位要一一对应。例如：舱位 F/B,目标舱位 F/E,表示F舱只<br>追F舱及以下同舱位等级舱位,B舱只追B舱及以下同舱位等级舱位)</span></label>
		      			</div>
		      		</td>
                </tr>
                <tr id="mbcwcjtr">
                	<td class="td_right">目标舱位差价</td>
                	<td>
                  		<input type="text" name="mbcwcj" id="mbcwcj" datatype="number,minvalue,maxvalue,dotformat" minvalue="0" maxvalue="99999" dotformat="#######." 
                  			value="${entity.mbcwcj}"  class="input-text lh25" style="text-align: right;width:70px;" />元&nbsp;
                		<label><font color="red">*</font><span style="color: gray;">(目标舱位差价 = 原舱位价格<span style="display:none;" id="ycpsm">-退票费</span>-目标舱位价格)</span></label>
                	</td>
                </tr>
                <tr>
                  <td class="td_right">优先等级</td>
                  <td>
                  	  <select name="yxdj" id="yxdj" class="select" datatype="n1-1" errormsg="请输入1位数字！" style="width:70px">  
                  	  		<option value="0" ${entity.yxdj eq '0' or empty entity.yxdj ? 'selected' : ''}>0</option>
                  	  		<option value="1" ${entity.yxdj eq '1' ? 'selected' : ''}>1</option>
                  	  		<option value="2" ${entity.yxdj eq '2' ? 'selected' : ''}>2</option>
                  	  		<option value="3" ${entity.yxdj eq '3' ? 'selected' : ''}>3</option>
                  	  		<option value="4" ${entity.yxdj eq '4' ? 'selected' : ''}>4</option>
                  	  		<option value="5" ${entity.yxdj eq '5' ? 'selected' : ''}>5</option>
                  	  		<option value="6" ${entity.yxdj eq '6' ? 'selected' : ''}>6</option>
                  	  		<option value="7" ${entity.yxdj eq '7' ? 'selected' : ''}>7</option>
                  	  		<option value="8" ${entity.yxdj eq '8' ? 'selected' : ''}>8</option>
                  	  		<option value="9" ${entity.yxdj eq '9' ? 'selected' : ''}>9</option>
					  </select>
					  <font color="gray">(请选择一个数字，数字越大表示优先级越高)</font> 
                  </td>
                </tr>
                <tr>
                    <td class="td_center" colspan="4">
                     <input type="button" name="button" onclick="saveZwgz()" class="ext_btn ext_btn_submit" value="保存">  &nbsp;
                     <input type="button" name="button" onclick="closeZwgz()" class="ext_btn" value="关闭">
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