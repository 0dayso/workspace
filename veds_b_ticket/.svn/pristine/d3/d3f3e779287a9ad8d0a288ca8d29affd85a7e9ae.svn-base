
//取消
function cancelTpd(tpdh) {
	var url = $ctx + "/vedsb/jptpgl/jptpd/getJpTpd_" + tpdh + "?forward=cancel";
	$.layer({
		area : [ '250px', '150px' ],
		dialog : {
			msg : "确定取消退票单[" + tpdh + "]吗？",
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var ii = layer.load("正在取消，请稍等……");
				$.layer({
					type : 2,
					title : [ '退票单取消' ],
					area : [ '600px', '400px' ],
					iframe : {
						src : url
					}
				});
			},
			no : function() {
				layer.msg("放弃取消退票单!", 2, 3);
			}
		}
	});
}

//销售审核
function xsShTpd(tpdh) {
	var url = $ctx + "/vedsb/jptpgl/jptpd/tpdDetail_" + tpdh + "?forward=xssh";
	window.open(url);
}

//采购提交
function cgTjTpd(tpdh) {
	var url = $ctx + "/vedsb/jptpgl/jptpd/tpdDetail_" + tpdh + "?forward=cgtj";
	window.open(url);
}

//销售完成
function xsWcTpd(tpdh) {
	var url = $ctx + "/vedsb/jptpgl/jptpd/tpdDetail_" + tpdh + "?forward=xswc";
	window.open(url);
}

//采购提交
function cgTjTpd(tpdh) {
	var url = $ctx + "/vedsb/jptpgl/jptpd/tpdDetail_" + tpdh + "?forward=cgtj";
	window.open(url);
}

//采购完成
function cgWcTpd(tpdh) {
	var url = $ctx + "/vedsb/jptpgl/jptpd/tpdDetail_" + tpdh + "?forward=cgwc";
	window.open(url);
}

//退票单详
function detailTpd(tpdh) {
	var url = $ctx + "/vedsb/jptpgl/jptpd/tpdDetail_" + tpdh;
	window.open(url);
}

//签注
function getQzxx(tpbh) {
	var url = $ctx + "/vedsb/common/jpcommon/qzlist?ywdh=" + tpbh
			+ "&ywlx=02&from=list";
	$.layer({
		type : 2,
		title : [ '签注' ],
		area : [ '745px', '400px' ],
		iframe : {
			src : url
		}
	});
}

function checkAll(checkallbox) {
	$('input[type="checkbox"][name="onechkx"]').each(function() {
		$(this).attr("checked", checkallbox.checked);
	});
}

//批量销售完成
function batchXsWc() {
	var length = $('input[type="checkbox"][name="onechkx"]:checked').length;
	if (length == 0) {
		layer.alert("请勾选需要批量销售完成的订单");
		return;
	}

	var tpdhs = "";
	$("input[type='checkbox'][name='onechkx']:checked").each(function() {
		tpdhs += $(this).val() + ",";
	});

	var ii = layer.load('系统正在处理您的操作,请稍候!');
	var url = $ctx + "/vedsb/jptpgl/jptpd/batchXsWc";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			tpdhs : tpdhs
		},
		success : function(data) {
			layer.close(ii);
			if (data == '') {
				layer.alert("销售批量完成成功", 1, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			}else {
				layer.alert(data, 5, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			}
		}
	});

}

// 批量采购完成
function batchCgWc() {
	var length = $('input[type="checkbox"][name="onechkx"]:checked').length;
	if (length == 0) {
		layer.alert("请勾选需要批量采购完成的订单");
		return;
	}
	var tpdhs = "";
	$("input[type='checkbox'][name='onechkx']:checked").each(function() {
		tpdhs += $(this).val() + ",";
	});
	
	
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	var url = $ctx + "/vedsb/jptpgl/jptpdcg/batchCgWc";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			tpdhs : tpdhs
		},
		success : function(data) {
			layer.close(ii);
			if (data == '') {
				layer.alert("采购批量完成成功", 1, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			} else {
				layer.alert(data, 5, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			}
		}
	});
}

//批量手工退票
function batchCgTp() {
	var length = $('input[type="checkbox"][name="onechkx"]:checked').length;
	if (length == 0) {
		layer.alert("请勾选需要批量销售完成的订单");
		return;
	}
	
	var tpdhs = "";
	$("input[type='checkbox'][name='onechkx']:checked").each(function() {
		var tpzt=$(this).attr("tpzt");// 0 初始状态  1 自动退票成功,  2 自动退票失败,  3  退票中 ,4  手动出票成功
		if(tpzt == '0' || tpzt == '2'){
			tpdhs += $(this).val() + ",";
		}
	});
	if(isBlank(tpdhs)){
		layer.alert("请勾选'初始状态'或'退票失败'的订单"); 
		return;
	}
	var ii = layer.load('系统正在批量手工退票,请稍候!');
	var url = $ctx + "/vedsb/jptpgl/jptpdcg/batchCgTp";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			tpdhs : tpdhs
		},
		success : function(data) {
			layer.close(ii);
			if (data == '') {
				layer.alert("批量手工退票成功", 1, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			} else {
				layer.alert(data, 5, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			}
		}
	});

}


function showZdtpLog(id,pnr){
	var url = $ctx + "/vedsb/jptpgl/jpzdtpjk/getSm";
	$.ajax({
   	 		type:"post",
		url:url,
		data:{id:id},
		success:function(result){
		     var pageii = $.layer({
						    type: 1,
						    title: ["<b><font color='white'>PNR: "+pnr+" 自动退票日志</font></b>", "background:#3e75d0;text-decoration:none;"],
						    area: ['auto', 'auto'],
						    border: [0], //去掉默认边框
						    shade: [0], //去掉遮罩
						    closeBtn: [0, true], //去掉默认关闭按钮
						    shift: 'top', //从左动画弹出
						    page: {
						        html: "<div style='width:600px; height:450px;overflow:auto;padding:20px; border:1px solid #ccc; background-color:#FFF;'>"+result+"</div>"
						    }
						   });
		}});
}


/**
 * 批量票证提交
 */
function  batchTppzTj(){
	var length = $('input[type="checkbox"][name="onechkx"]:checked').length;
	if (length == 0) {
		layer.alert("请勾选需要提交凭证的订单");
		return;
	}
	
	var tpdhs = "";
	$("input[type='checkbox'][name='onechkx']:checked").each(function() {
		var cgly=$(this).attr("cgly");
		var cg_sfzytp=$(this).attr("cg_sfzytp");
		var cg_tpzt=$(this).attr("cg_tpzt");
		if((cgly == 'BSP' || cgly == 'BSPET' || cgly == 'BOP') && cg_sfzytp == "0" && cg_tpzt == "3"){
			tpdhs += $(this).val() + ",";
		}
	});
	if(isBlank(tpdhs)){
		layer.alert("请勾选采购来源是'BSP或BOP'的非自愿退票订单"); 
		return;
	}
	var ii = layer.load('系统正在批量提交凭证,请稍候!');
	var url = $ctx + "/vedsb/jptpgl/jptpdcg/batchTppzTj";
	$.ajax({
		type : "POST",
		url : url,
		data : {tpdhs : tpdhs},
		success : function(data) {
			layer.close(ii);
			if (data == '') {
				layer.alert("批量提交凭证完成", 1, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			} else {
				layer.alert(data, 5, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			}
		}
	});
}

/**
 * 获取航班延误信息
 * @param tpdh
 */
	function getHbyd(tpdh){
		var url= $ctx +"/vedsb/jptpgl/jptpd/getHbyd_"+tpdh;
		var hbydImage="";
		$.ajax({
    	 		type:"post",
    	 		async:true, 
				url:url,
				success:function(data){
				     var hblx=data.hblx;
				     var hbsj=data.hbsj;
				     if(hblx == "1"){
				     	hbydImage="<br><font color='red'><b>提前 ["+hbsj+"m]</b></font>";
				     }else if(hblx == "2"){
					 	hbydImage="<br><font color='red'><b>延误 ["+hbsj+"m]</b></font>";
					 }else if(hblx == "3"){
					 	hbydImage="<br><font color='red'><b>取消 ["+hbsj+"m]</b></font>";
					 }
					 $("#hbyd"+tpdh).html(hbydImage);
				}
    	 	});
	   }