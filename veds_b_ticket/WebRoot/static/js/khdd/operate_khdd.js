//全选
function checkAll(checkallbox) {
	$('input[type="checkbox"][name="onechkx"]').each(function() {
		$(this).attr("checked", checkallbox.checked);
	});

}

// 取消
function cancel(id) {
	$.layer({
		area : [ '250px', '150px' ],
		dialog : {
			msg : '确定取消订单吗？',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var ii = layer.load('系统正在处理您的操作,请稍候!');
				var url = $ctx + "/vedsb/jpddgl/jpkhdd/cancel?id=" + id;
				$.getJSON(url, function(result) {
					if (result == '1') {
						layer.msg('订单取消成功!', 2, 1);
						refresh()
					} else {
						layer.msg('订单取消失败：' + data.MSG, 2, 5);
					}
				});
			},
			no : function() {
				layer.msg('放弃批量完成！', 2, 3);
			}
		}
	});
}

// 一键完成页面
function enterWcPage(id) {
	var url = $ctx + "/vedsb/jpddgl/jpkhdd/enterWcPage_" + id;
	window.open(url);
}

// 出票页面
function enterCpPage(id,xgly) {
	var url = $ctx + "/vedsb/jpddgl/jpkhdd/enterCpPage_" + id;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		success : function(data) {
			if (data.result == "1") {// 该单没锁，直接进入出票页面
				refresh();
				window.open($ctx + "/vedsb/jzcp/cpmain/main?ddbh="+ id);
			} else if (data.result == "-1") {// 该单没锁，进入出票失败
				layer.msg(data.msg, 2, 3);
			} else if (data.result == "0") {// 该单已锁
				var a = layer.confirm("工号" + data.msg + "正在操作是否继续？",
					function(index) {
						var ii = layer.load('系统正在处理您的操作,请稍候!');
						$.ajax({
							type : "post",
							async : false,
							url : $ctx + "/vedsb/jpddgl/jpkhdd/xgCpSdr",
							data : {ddbh : id,xgly : xgly},
							success : function(data) {
								layer.close(ii);
								if (data == '') {// 锁单继续成功
									refresh();
									window.open($ctx+ "/vedsb/jzcp/cpmain/main?ddbh="+ id);
								} else {// 锁单继续失败
									layer.msg(result,2,3);
								}
							}
						});
						layer.close(index);
					});
				}
			}
	});
}
// 解锁
function jiesuo(ddbh,xgly) {
	var a = layer.confirm("你确定要解锁码？", function(index) {
		var ii = layer.load('系统正在处理您的操作,请稍候!');
		$.ajax({
			type : "post",
			url : $ctx + "/vedsb/jpddgl/jpkhdd/jpJieSuo",
			data : {ddbh : ddbh,xgly : xgly},
			success : function(result) {
				layer.close(ii);
				if ("" == result) {
					refresh();
					layer.msg('解锁成功', 2, 1);
				} else {
					layer.msg(result, 2, 3);
				}
			}
		});
		layer.close(index);
		window.open($ctx + "/vedsb/jzcp/cpmain/main?ddbh=" + id);
	});
}

//手工补位
function sgBw(ddbh,pnrzt){
	
	if(pnrzt != 'NO'){
		$.layer({
			area : [ '250px', '150px' ],
			dialog : {
				msg : 'PNR状态为NO时订单才需要补位,是否继续补位?',
				btns : 2,
				type : 4,
				btn : [ '确定', '取消' ],
				yes : function() {
					var ii = layer.load('系统正在处理您的操作,请稍候!');
					sgBwAjax(ddbh);
				},
				no : function() {
					layer.msg('放弃补位！', 2, 3);
				}
			}
		});
	}else{
		sgBwAjax(ddbh);
	}
	
}

function sgBwAjax(ddbh){
	$.ajax({
		type : "post",
		url : $ctx + "/vedsb/jpddgl/jpkhdd/sgBw_"+ddbh,
		success : function(result) {
			if ("" == result) {
				layer.alert("补位成功!", 1, '提示信息', function index() {
					$("#searchFormButton").click();
				});
			} else {
				layer.alert(result, 5, '提示信息');
			}
		}
	});
}


// 转机票页面
function enterZjpPage(ddbh) {
	var url = $ctx + "/vedsb/cpkzt/cpkzt/enterZjpPage_" + ddbh;
	$.layer({
		type : 2,
		title : [ '<b>一键完成</b>' ],
		area : [ '860px', '500px' ],
		iframe : {src : url}
	});
}

// 交票
function jpAction(ddbh, ddzt) {
	$.layer({
		area : [ '250px', '150px' ],
		dialog : {
			msg : '确定交票吗？',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var ii = layer.load('系统正在处理您的操作,请稍候!');
				$.ajax({
					type : "post",
					url : $ctx + "/vedsb/jpddgl/jpkhdd/jpAction?ddbh=" + ddbh + "&ddzt=" + ddzt,
					success : function(result) {
						layer.close(ii);
						if ("1" == result) {
							refresh();
							layer.msg('订单交票成功!', 2, 1);
						} else {
							layer.msg('订单交票失败!', 2, 3);
						}
					}
				});
			},
			no : function() {
				layer.msg('放弃交票！', 2, 3);
			}
		}
	});
}

// 手工追位
function toSgZw(ddbh, obj) {
	var url = $ctx + "/vedsb/jpzwgl/jptjsq/toTjsq?ddbh=" + ddbh;
	var offset = $(obj).offset();
	var top = offset.top + "px";
	var left = offset.left + 10 + "px";
	$.layer({
		type : 2,
		title : false,
		bgcolor : '#7B68EE',
		shade : [ 0 ], // 去掉遮罩
		border : [ 0 ],
		offset : [ top, left ],
		area : [ '430px', '290px' ],
		fix : false,
		iframe : {src : url},
		scrollbar : true
	});
}


//签注
function getQzxx(ddbh) {
	var url = $ctx + "/vedsb/common/jpcommon/qzlist?ywdh=" + ddbh
			+ "&ywlx=01&from=list";
	$.layer({
		type : 2,
		title : [ '签注' ],
		area : [ '745px', '400px' ],
		iframe : {src : url}
	});
}

// 定时刷新
function autoRefesh() {
	if ($("input[name='refresh_status']:checked").length == 1) {
		$("#auto_ref_stop").attr("id", "auto_ref");
		$.autoRefresh({
			showResponse : function() {
				$("#searchFormButton").click();
			}
		});
	} else {
		$("#auto_ref").attr("id", "auto_ref_stop");
		$.autoRefreshStop();
	}
}

// 锁单人输入框离开添加判断
function xgCpsdr() {
	var sdr = $("#sdr").val();
	var $cpsdr = $("#cp_sdr");
	if (sdr == '') {
		$cpsdr.val("");
	} else {
		$cpsdr.click();
	}
}

// 点击表头排序
function sort(obj) {
	var order = $("#orderBy");
	if (order.val().indexOf(obj) == '-1') {
		order.val(obj + " desc");
	} else {
		if (order.val().indexOf("desc") == '-1') {
			order.val(obj + " desc");
		} else {
			order.val(obj + " asc");
		}
	}
	$("#searchFormButton").click();
}



//手工订单
function enterSgdPage(gngj) {
	var url = $ctx + "/vedsb/jpddgl/jpkhdd/enterSgdPage?gngj=" + gngj;
	window.open(url);
}

//手工导单
function openDdrk(wdid,gngj) {
	var url = $ctx + "/vedsb/jpddsz/jpddsz/opensgDdrk?gngj=" + gngj+"&wdid=" + wdid;
	var w = 400;
	var h = 150;
	$.layer({
		type : 2,
		title : "手工导单",
		area : [ w, h ],
		iframe : {src : url}
	});
}

// 批量完成
function batchWc() {
	var ddbhs="";var zdcpjk_ids="";
	$("input[type='checkbox'][name='onechkx']:checked").each(function() {
		var o = $(this);
		var ddzt = o.attr("ddzt");
		var phhtzt=o.attr("phhtzt");
		var zdcpjk_id= o.attr("zdcpjk_id");
		if ((ddzt == '1' || ddzt == '2' ) && phhtzt != "1") {
			zdcpjk_ids+=zdcpjk_id+",";
			ddbhs += o.val() + ",";
		}
	});
	
	if (ddbhs == "") {
		layer.alert('请选择出票失败的订单!',5);
		return;
	}
	
	$.layer({
		area : [ '250px', '150px' ],
		dialog : {
			msg : '确定批量完成选中订单吗？',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var ii = layer.load('系统正在处理您的操作,请稍候!');
				$.ajax({
					type : "post",
					url : $ctx + "/vedsb/cpkzt/cpkzt/batchWc?ddbhs=" + ddbhs+"&zdcpjk_ids="+zdcpjk_ids,
					success : function(result) {
						layer.close(ii);
						layer.alert(result.MSG, 2, 1,function index(){
							refresh();
						});
					}
				});
			},
			no : function() {
				layer.msg('放弃批量完成！', 2, 3);
			}
		}
	});
}

//批量回填票号
function batchPhht() {
	var length = $("input[type='checkbox'][name='onechkx']:checked").length;
	if (length == 0) {
		layer.msg('请勾选需要票号回填的订单!', 2, 3);
		return;
	}
	var wbdhs = "";
	var wdids = "";
	var ddbhs = "";
	$("input[type='checkbox'][name='onechkx']:checked").each(function() {
		var o = $(this);
		var phhtzt = o.attr("phhtzt");
		if (phhtzt != '1') {
			ddbhs += o.val() + ",";
			wbdhs += o.attr("wbdh") + ",";
			wdids += o.attr("wdid") + ",";
		}
	});
	if (wbdhs == "") {
		layer.alert('请选择未回填或者回填失败的订单!',5);
		return;
	}

	$.layer({
		area : [ '250px', '150px'],
		dialog: {
			msg : "您确定批量回填选中的订单吗?",
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var ii = layer.load('系统正在处理您的操作,请稍候!');
				$.ajax({
					type : "post",
					url : $ctx + "/vedsb/cpkzt/cpkzt/batchPhht",
					data : {wbdhs : wbdhs,ddbhs : ddbhs,wdids : wdids},
					success : function(result) {
						layer.close(ii);
						if (result == "") {
							layer.alert("批量回填票号成功!", 1, '提示信息',
									function index() {
										refresh();
									});
						} else {
							layer.alert(result, 5, '提示信息', function index() {
								refresh();
							});
						}
					}
				});
			},
			no : function() {
				layer.msg('放弃批量回填！', 2, 3);
			}
		}
	});
}

/**
 * 批量手动出票 即手动触发全自动出票
 */
function batchSgcp() {
	var length = $("input[type='checkbox'][name='onechkx']:checked").length;
	if (length == 0) {
		layer.msg('请勾选需要手工出票订单!', 2, 3);
		return;
	}
	var ddbhs = "";
	$("input[type='checkbox'][name='onechkx']:checked").each(function() {
		var o = $(this);
		var ddzt = o.attr("ddzt");
		var cpzt = parseInt(o.attr("cpzt"));
		if (ddzt == '1' && (cpzt < 0 || cpzt =="" || isNaN(cpzt))) {
			ddbhs += o.val() + ",";
		}
	});
	if (ddbhs == "") {
		layer.alert('请选择已订座且出票失败的订单!', 5);
		return;
	}

	$.layer({
		area : [ '250px', '150px'],
		dialog: {
			msg : "您确定批量手工出选中的订单吗?",
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var ii = layer.load('系统正在处理您的操作,请稍候!');
				$.ajax({
					type : "post",
					url : $ctx + "/vedsb/cpkzt/cpkzt/batchSgcp",
					data : {ddbhs : ddbhs},
					success : function(result) {
						layer.close(ii);
						if (result == "") {
							layer.alert("批量手工出票完成!", 1, '提示信息',function index() {
										refresh();
									});
						} else {
							layer.alert(result, 5, '提示信息', function index() {
								refresh();
							});
						}
					}
				});
			},
			no : function() {
				layer.msg('放弃批量手工出票！', 2, 3);
			}
		}
	});
}
