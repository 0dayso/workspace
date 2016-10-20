<%@ page language="java" pageEncoding="UTF-8"%>

<script>
$(function(){
	if("${error}" != null && "${error}" != ''){
		layer.alert("${error}");
	}
});
//切换提取类型
function changePnrtq(lx){
	if(lx == "1"){
	    $("#pnrTab1").show();
		$("#pnrTab2").hide();
		$("#bmtq").show();
	}else{
		$("#pnrTab1").hide();
		$("#pnrTab2").show();
		$("#bmtq").hide();
	}
	document.location.href= "${ctx}/vedsb/jpddgl/jpkhdd/enterSgdPage?lx="+lx;
}
//根据PNR提取信息
function tqByPnr(){
	var pnrno=$("input[id='pnrno']").val();
 	if(!pnrno){
	  	alert("PNR不能为空！");
	  	return false;
 	}
 	document.location.href= "${ctx}/vedsb/jpddgl/jpkhdd/tqByPnr?pnrno="+pnrno+"&type="+$("input[name='type']:checked").val();
}

//根据PNR内容提取信息
function tqByPnrContent(){
	var pnrContent =$("textarea[id='pnr_nr']").val();
 	if(!pnrContent){
	  	alert("PNR内容不能为空！");
	  	return false;
 	}
 	document.location.href= "${ctx}/vedsb/jpddgl/jpkhdd/tqByPnrContent?pnrContent="+pnrContent;
}

</script>
<div class="tabContainer">
	<ul class="tabHead">
   	 	<li class="${(empty lx or lx eq '1') ? 'currentBtn' : ''}" id="pnrtq1">
			<span onclick="changePnrtq('1')"><a href="#">按PNR提取</a></span>
		</li>
		<li class="${lx eq '2' ? 'currentBtn' : ''}" id="pnrtq2">
			<span onclick="changePnrtq('2')"><a href="#" >按PNR内容提取</a></span>
		</li>
	</ul>
</div>
<div id="pnrTab1"  style="display: ${(empty param.lx or param.lx eq '1') ? '' : 'none'}; border: #EAB97E;width: 99.5%;margin-left: 5px;background:#FFF8ED;padding: 3 0 3 5;">
	<span id="bmtq">
		<label>
			<input type="radio" name="type"  value="1"  ${empty param.type ? 'checked' : '' }>小编码
		</label>
		<label>
			<input type="radio" name="type"  value="2"  ${ param.type eq '2' ? 'checked' : '' }>大编码
		</label>
		<input type="text" name="pnrno" id="pnrno" value="${pnrObject.pnr_no}"  onblur=""  onKeyDown="if(event.keyCode == 13){tqByPnr()}" onkeyup="value =value.toUpperCase();" size="6">
		<input type="button" name="tq" value="提取信息" class="ext_btn ext_btn_submit" onclick="tqByPnr();">
	</span>
</div>
<div id="pnrTab2" style="display: ${param.lx eq '2' ? '' : 'none'}; background:none;margin-left: 5px;background:#FFF8ED;padding: 3 0 3 5;">
	<textarea name="pnr_nr" id="pnr_nr" cols="80" rows="10" style="background-color: black;color:#0BBF1D;">${param.pnr_nr }</textarea>
	<input type="button" name="tq" value="提取信息" class="ext_btn ext_btn_submit" onclick="tqByPnrContent();">
</div>
<div id="pnrnr_info" style="background:none;margin-left: 5px;background:#FFF8ED;">
	PNR 
	<input type="text" name="xs_pnr_no" value="${pnrObject.pnr_no}" onblur="value =value.toUpperCase();"  
		onkeyup="value =value.toUpperCase();" onblur="value = value.toUpperCase()" size="6" datatype="s6-6" errormsg="请输入6位字母或数字或两者组合">
		&nbsp;<font color='red'>*</font>
	大编码 
	<input type="text" name="xs_hkgs_pnr" value="${pnrObject.bigPnrno }" onblur="value =value.toUpperCase();"  
		onkeyup="value =value.toUpperCase();" onblur="value = value.toUpperCase()" size="6">
	 <input type="hidden" value="${param.gngj eq  '0' ? '0': '1' }" name="gngj" >
	
</div>
