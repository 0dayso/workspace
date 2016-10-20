function changeDate() {
	var rs = $("#rs");
	var rz = $("#rz");
	ch = document.searchForm.dateType.value;
	if (ch == "1") {
		rs.html("预订日始");
		rz.html("预订日止");
	} else if (ch == "2") {
		rs.html("起飞日始");
		rz.html("起飞日止");
	}
}

function refresh() {
	$("#searchFormButton").click();
}

function ifCheckAll(onechkx) {
	var chkxArr = $('input[type="checkbox"][name="onechkx"]');
	var total = chkxArr.length;
	var checkedLen = 0;
	if (onechkx.checked) {
		$(chkxArr).each(function() {
			if ($(this).prop("checked")) {
				checkedLen++;
			}
		});
		$("#checkallbox").attr("checked", total == checkedLen);
	} else {
		$("#checkallbox").attr("checked", false);
	}
}

//订单详
function detail(id) {
	var url = $ctx + "/vedsb/jpddgl/jpkhdd/detail_" + id;
	window.open(url);
}

//查看异动日志
function enterLogPage(ddbh) {
	var url = $ctx + "/vedsb/jpddgl/jpkhddczrz/enterLogPage_" + ddbh + "?ywlx=01";
	$.layer({
		type : 2,
		title : [ '<b>订单异动日志</b>' ],
		area : [ '650px', '360px' ],
		iframe : {src : url}
	});
}

//点击查询按钮
function toSearch() {
	var wdpt = $("#wdid option:selected").attr("wdpt");
	if (wdpt == '' || wdpt == '10100050') {
		$("#jjd_sm").show();
	} else {
		$("#jjd_sm").hide();
	}
	hqcolVal();
}

//自动出票日志
function showLog(id, pnr) {
	var url = $ctx + "/vedsb/cpkzt/jpzdcpjk/getSm";
	$.ajax({
		type : "post",
		url : url,
		data : {id : id},
		success : function(result) {
			var pageii = $.layer({
				type : 1,
				title : ["<b><font color='white'>PNR: " + pnr + " 自动出票日志</font></b>", "background:#3e75d0;text-decoration:none;" ],
				area : [ 'auto', 'auto' ],
				border : [ 0 ], //去掉默认边框
				shade : [ 0 ], //去掉遮罩
				closeBtn : [ 0, true ], //去掉默认关闭按钮
				shift : 'top', //从左动画弹出
				page : {html : "<div style='width:600px; height:450px;overflow:auto;padding:20px; border:1px solid #ccc; background-color:#FFF;'>"+ result + "</div>" }
			});
		}
	});
}

//根据日期与当前日期比较来给出相应的颜色提示
function timeColorRemind(sjlx,sjval){
	var sj_color="";
	//获取当前日期,精确到天
	var now1=$("#now").val();
	//获取两天后的日期,精确到天
	var now2=$("#now2").val();
	//日期比较
	if(now1 == sjval){//当天
      sj_color = $("#"+sjlx+"_dt").val();
	}else if( sjval > now1 && sjval <= now2 ){//两天内
      sj_color = $("#"+sjlx+"_ltn").val();
	}else if( sjval > now2 ){//两天后
      sj_color = $("#"+sjlx+"_lth").val();
	}
	return sj_color;
}

//紧急度颜色提醒
function jjdColorRemind(jjd){
	var jjd_color="";
	if(jjd == '1'){
		jjd_color="background:#8e0707;";
	}else if(jjd == '2'){
		jjd_color="background:#fd0106;";
	}else if(jjd == '3'){
		jjd_color="background:#097efb;";
	}else if(jjd == '4'){
		jjd_color="background:#fcc102;";
	}else if(jjd == '5'){
     	jjd_color="background:#ee7ae9;";
	}
	return jjd_color;
}

//pnrzt颜色提示
function pnrZtColor(pnrZt){
	var pnrztmc="";
	if(pnrZt == 'HK'){
		pnrztmc="<font color='blue'>"+pnrZt+"</font>";
	}else if(pnrZt == 'XX' || pnrZt == 'NO'){
        pnrztmc="<font color='red'>"+pnrZt+"</font>";
	}else if(pnrZt == 'RR'){
        pnrztmc="<font color='green'>"+pnrZt+"</font>";
	}else{
        pnrztmc=pnrZt;
	}
	return pnrztmc;
}

//回填状态
function phhtzt(phhtzt){
	var phhtztsm="";
	if(phhtzt == 0 && phhtzt == '' ){
		phhtztsm="<font color='blue'>未回填</font>";
	}else if(phhtzt == 1){
        phhtztsm="<font color='green'>回填成功</font>";
	} else if(phhtzt == 2){
		phhtztsm="<font color='red'>回填失败</font>";
	}
	return phhtztsm;
}


//根据采购来源 颜色提示
function cglyColorRemind(cgly){
	var pz_color="";
	if(cgly != ''){
		pz_color = $("#pzlx_"+cgly).val();
	}
	return pz_color;
}

//订单状态颜色提示
function ddztColorRemind(ddzt){
	var ztmc; 
	if(ddzt == "0" ){ 
		ztmc="<font color='blue'>待确认</font>";
	}else if(ddzt == "1" ){ 
		ztmc="已订座";
	}else if(ddzt == "2" ){ 
		ztmc="<font color='blue'>出票中</font>";
	}else if(ddzt == "3" ){ 
		ztmc="<font color='green'>已完成</font>";
	}else if(ddzt == "4" ){ 
		ztmc="<font color='red'>客户消</font>";
	} else{
		ztmc="<font color='red'>已取消</font>";
	}
	return ztmc;
}


//出票状态颜色提示
function cpztColorRemind(cpzt){
    var ztms="";
    if(cpzt == 0 && cpzt != '' ){
		ztms="<font color='green'>自动出票成功</font>";
	}else if(cpzt > 0){
       ztms="<font color='blue'>出票中</font>";
    	if(cpzt == 40){
         	ztms="<font color='green'>手动出票成功</font>";
		}
	} else if(cpzt < 0){
 		ztms="<font color='red'>出票失败</font>";
	}
	return ztms;
}

//pnr显示
function pnrnoShow(xspnrno,cgpnrno){
    var pnrno="<span class='copytext' copytext="+xspnrno+">"+xspnrno+"</span>";
    if(xspnrno!=cgpnrno && cgpnrno != ''){
         pnrno=pnrno+"-><span class='copytext' copytext="+cgpnrno+"><font color=green>"+cgpnrno+"</font></span>";
    }
    return pnrno;
}