<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>平台账号</title>
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
		width:100px;
	}
	.ptzh_left{
		text-align: left;
		width:200px;
	}
</style>
<script type="text/javascript">
	$(function() {
		var zddklxpm = $("#10100001zddklx").val();
		var zddklx = $("#10100016zddklx").val();
		hideZfzh(zddklx,"10100016");
		hideZfzh(zddklxpm,"10100001");
	})
	
	function hideZfzh(obj,ptzcbs){
		if("1" == obj){
			$('#'+ptzcbs+'zfbzh').show();
			$('#'+ptzcbs+'cftzh').hide();
			$('#'+ptzcbs+'380yezh').hide();
		}else if("2" == obj){
			$('#'+ptzcbs+'zfbzh').hide();
			$('#'+ptzcbs+'cftzh').show();
			$('#'+ptzcbs+'380yezh').hide();
		}else if("3" == obj){
			$('#'+ptzcbs+'zfbzh').hide();
			$('#'+ptzcbs+'cftzh').hide();
			$('#'+ptzcbs+'380yezh').show();
		}
	}
	
	function openUrl(obj){
	   	if(obj == "1"){
	   	   	window.open("https://airprod.alipay.com/exterfaceAssign.htm?sign=532b5dfb048a93d1f05df74e5edafa43&alipay_exterface_invoke_assign_client_ip=123.121.17.7&_input_charset=GBK&alipay_exterface_invoke_assign_target=airDutUserSign.htm&alipay_exterface_invoke_assign_model=deduct&sign_type=MD5&service=sign_protocol_with_partner&partner=2088911908041843&alipay_exterface_invoke_assign_sign=_l_c_i_x_y_d_vl_d%2Bcn43_fmhbw_k80pcyq55k_d_c_i1u_p4n5%2B%2B_uof5i_qc7_e_k_g_vqw%3D%3D");
	   	}else if(obj == "2"){
	   		window.open("https://www.tenpay.com/cgi-bin/trust/showtrust.cgi?spid=1205716201");
	   	}
	}
	
	var deptCfCount = 0;
	function toSave(){
		var ptzcsb = "10100000";
		var cps_user1 = $("#user1"+ptzcsb);
		var cps_user2 = $("#user2"+ptzcsb);
		var cps_pwd1 = $("#pwd1"+ptzcsb);
		var cps_by3input = $("#by3input"+ptzcsb);
		var cps_deptname = $("#deptName"+ptzcsb);
		var tp_ipaddress=$("#tp_ipaddress");//退票证明ip
		var tp_port=$("#tp_port");//退票端口
		//采购CPS判断
		
		var cpsOpen1 = $("#cpsOpen1").val();
		
		if(cpsOpen1 == '1'){
			if(cps_user1.value == ""){
				alert("商户号不能为空");
				cps_user1.focus();
				return false;
			}
			if(cps_user2.value == ""){
				alert("用户号不能空");
				cps_user2.focus();
				return false;
			}
			if(cps_pwd1.value == ""){
				alert("密匙不能为空");
				cps_pwd1.focus();	
				return false;		
			}
			if(cps_by3input.value == ""){
				alert("默认采购部门不能为空");
				cps_by3input.focus();
				return false;
			}
			if(cps_deptname.value == ""){
				alert("接收部门不能为空");
				cps_deptname.focus();
				return false;
			}
			if(tp_ipaddress.value == ""){
				tp_ipaddress.focus();
				alert("退票证明ip不能为空！");
				return false;
			}
			if(tp_port.value == ""){
				tp_port.focus();
				alert("退票证明端口不能为空！");
				return false;
			}
		}
		
	    var url="${ctx}/vedsb/cgptsz/jpptzczh/saveZh";
	    var ii = layer.load('系统正在处理您的操作,请稍候!');
	    $.ajax({
	    	url:url,
	    	data:$("#ptzczhForm").serialize(),
	    	type:"post",
	    	success:function(result) {
	    		if ("1" == result) {
	    			layer.msg("保存成功！",2,1);
	    			location.reload()
	    		} else {
	    			layer.msg(result,2,1);
	    			layer.close(ii);
	    		}
	    	}
	    })
		return true;
	}
	
	//八千翼支付宝账号签约
	function getpaySignOnUrl(_url,_name,_pwd,_emAccount){
		var _url=$("#yi8kurl1").val();
		var _name=$("#yi8kuser1").val();
		var _pwd=$("#yi8kpwd1").val();
		var _emAccount=$("#yi8kby1").val();
		
		if(_url == ""){
			alert("地址不能为空");
			return;
		}
		if(_name == ""){
			alert("用户名不能为空");
			return;
		}
		if(_pwd == ""){
			alert("密码不能为空");
			return;
		}
		if(_emAccount == ""){
			alert("支付宝账号不能为空");
			$("yi8kby1").focus();
			return;
		}
		var url = "${ctx}/vedsb/cgptsz/jpptzczh/getpaySignOnUrl?url="+_url+"&name="+_name+"&pwd="+_pwd+"&emAccount="+_emAccount;
		var ii = layer.load("系统正在处理您的操作,请稍候!");
		$.ajax({
			url:url,
			type:"post",
			success:function(signUrl) {
				if(signUrl){
					if(signUrl.status == "1"){
						window.open(signUrl.result);
					}else{
						alert(signUrl.result);
					}
				}else{
					alert("请核对用户名,密码,地址,支付宝账号的准确性");
				}
			}
		})
		layer.close(ii);
	}
	
	function taobaokey(con){
		var appkey = con.value;
		if(appkey == ""){
			alert("请先输入账号(appkey)");
			con.focus();
			return ;
		}
		window.open("http://container.open.taobao.com/container?appkey="+appkey);
	}
	
	function getyeexingUserSign(){
		var url = "${ctx}/vedsb/ptzh/jpptzczh/getyeexingUserSign";
		var ii = layer.load("系统正在处理您的操作,请稍候!");
		$.ajax({
			url:url,
			type:"post",
			success:function(signUrl) {
				if(signUrl){
					if(signUrl.status == "1"){
						window.open(signUrl.result);
					}else{
						alert(signUrl.result);
					}
				}else{
					alert("请核对是否保存。");					
				}
			}
		})
		layer.close(ii);
	}
</script>
</head>
<body>
	<form name="ptzczhForm" id="ptzczhForm" action="${ctx}/vedsb/cgptsz/jpptzczh/saveZh" method="post">
			<input type="hidden" name="type" value="7" >
			<input name="by2" value="0" type="hidden">
			<input name="id" type="hidden" value="${ptzh.id }">
			<input name="ptzcbs" type="hidden" value="${ptzh.ptzcbs }">
			<c:choose>
					<%-- CPS --%>
					<c:when test="${ptzh.ptzcbs eq '10100000'}">
						<%@include file="pt/10100000_cps.jsp"%>
					</c:when>
					<%-- 今日天下通 --%>
					<c:when test="${ptzh.ptzcbs eq '10100002'}">
						<%@include file="pt/10100002_jrtxt.jsp"%>
					</c:when>
					<%-- 51book --%>
					<c:when test="${ptzh.ptzcbs eq '10100003'}">
						<%@include file="pt/10100003_51book.jsp"%>
					</c:when>
					<%-- 517NA--%>
					<c:when test="${ptzh.ptzcbs eq '10100004'}">
						<%@include file="pt/10100004_517na.jsp"%>
					</c:when>
					<%-- 百拓--%>
					<c:when test="${ptzh.ptzcbs eq '10100005'}">
						<%@include file="pt/10100005_bt.jsp"%>
					</c:when>
					<%-- 8000翼--%>
					<c:when test="${ptzh.ptzcbs eq '10100007'}">
						<%@include file="pt/10100007_8000y.jsp"%>
					</c:when>
					<%-- 淘宝--%>
					<c:when test="${ptzh.ptzcbs eq '10100011'}">
						<%@include file="pt/10100011_tb.jsp"%>
					</c:when>
					<c:when test="${ptzh.ptzcbs eq '10100015'}">
						<%@include file="pt/10100015_pp.jsp"%>
					</c:when>
					<%-- 一九易--%>
					<c:when test="${ptzh.ptzcbs eq '10100018'}">
						<%@include file="pt/10100018_yjy.jsp"%>
					</c:when>
		</c:choose>
	</form>
	<p style="margin-left: 30px">
		<b>注意事项：</b>
		<c:if test="${ptzh.ptzcbs eq '10100000'}">
			<br>取退废票证明地址请设置为外网访问地址，如果没有设置，会引起提交退废票申请时，上传退废证明失败！
		</c:if>
		<c:if test="${ptzh.ptzcbs eq '10100002'}">
			<br>传给平台的联系人和手机，如果不设置，则使用订单上的！
		</c:if>
		<br>当系统不支持某个平台时，会提示系统不支持该平台。
		<br>当某平台不支持代扣时，会提示该平台不支持代扣。
	</p>
</body>
</html>