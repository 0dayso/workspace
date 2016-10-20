<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>行程单列表</title>
<script type="text/javascript">
//关闭页面
function closePage(){
	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
}

//进入打印形成单页面
function enterPrintPage(tkno){
	 var url = "${ctx}/vedsb/xcdgl/printxcd/enterPrintPage?tkno="+tkno;
	 var iWidth=900; //弹出窗口的宽度;
	 var iHeight=600; //弹出窗口的高度;
	 var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	 var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	 window.open(url,"形成单打印","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft); 
}

/*
 *  打印行程单
 *  lx 1代表时代表票号提取 2PNR提取
 *  pnrno 对应lx 传入票号或PNR
 *  注意：票号必须为13位
 */
function printXcd(lx,pnrno){
	var url = "/eterm/cpzx/printxcd_main.shtml?radio_dd=1&asmsAndagent=asms&tqtype=" + lx;
	if(lx == 1){
		var tkno = pnrno.replace("-","");
		if(tkno.length != 13){
			alert("票号必须为13位！");
			return false;
		}
		url += "&tk_or_pnr=" + tkno;
	}else{
		url += "&tk_or_pnr=" + pnrno;
	}
	window.open(url,"xcdprint",1000,450);
	try{parent.hideQuickDiv();}catch(e){}
}
</script>
</head>
<body>
	<c:if test="${empty cjrList}">
		<div style="width:99%;margin-top: 4px;text-align: center;color: red;font-weight: bolder;font-size: 24px;" align="center">
			订单未出票或未进系统，请检查！
		</div>
	</c:if>
	<c:if test="${not empty cjrList}">
		<form name="listForm" action="${ctx}/vedsb/xcdgl/printxcd/enterXcdListPage" method="post">
			<input type="hidden" name="ddbh" value="${param.ddbh}" />
		</form>
		<table cellpadding="0" cellspacing="0" style="width:99%;margin-top: 4px;" align="center" class="tab_jf_in" >
			<tr>
				<td>按票号提取：
					<input type="text" name="tkno" value="${param.tkno}"/>
				</td>
			</tr>
			<tr>
				<th>乘机人</th>
				<th>票号</th>
				<th>行程单号</th>
				<th style="width:90px;">操作</th>
			</tr>
			<c:set var="tmp" value="" />
			<c:forEach items="${cjrList}" var="cjr" varStatus="vs">
				<tr class="${vs.index%2 eq 0 ? 'even' : 'odd' }">
					<td align="center">
						<c:if test="${tmp ne cjr.ID}">${cjr.CJR}</c:if>
					</td>
					<td  align="center">${cjr.TKNO}</td>
					<td align="center">
						<c:choose>
							<c:when test="${empty cjr.XCDH or cjr.XCDH eq '0'}">--未创建--</c:when>
							<c:otherwise>${cjr.XCDH}</c:otherwise>
						</c:choose>
					</td>
					<td  align="center">
						<c:choose>
							<c:when test="${empty cjr.XCDH or cjr.XCDH eq '0'}">
								<a href="javascript:void(0)" onclick="enterPrintPage('${cjr.TKNO}');" class="darkblue"><u>创建</u></a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)" onclick="printXcd(1,'${cjr.TKNO}');" class="darkblue"><u>重打</u></a>
								<a href="javascript:void(0)" onclick="checkZf('${cjr.TKNO}');" class="darkblue"><u>空作废</u></a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<c:set var="tmp" value="${cjr.ID}" />
			</c:forEach>
		</table>
	</c:if>
	<div align="center" style="margin-top: 8px;">
      	<input type="button"  value="关 闭" name="" class="ext_btn" onClick="closePage();">
 	</div>
</body>
<script type="text/javascript">
/*
 * 验证
 * lx KZF-空作废 TFZF-退废作废
 */
function checkZf(tkno,lx){
	if(!lx){
		lx = "KZF";
	}
    if(tkno == null || tkno == ""){
      return false;
    }
    submitAjax("/asms/xcd_zf.shtml",
    	{p:'search',tkno:tkno},
    	function(transport){
    		var msg = transport.responseText.split(",");
    		if(msg[0] == "" || msg[1] == ""){
    			alert("系统中没有此票号对应的行程单号！\n请认真核对！");
    		}else{
    			zf(tkno,msg[0],msg[1],lx);
    		}
    	}
    );
}
/*
 * 作废行程单
 * lx KZF-空作废 TFZF-退废作废
 */
function zf(tkno,xcdh,cjrid,lx){
	if(tkno=="" || tkno==null){
		alert("票号不能为空！");
		return false;
	}
	if(tkno.length != 13){
		alert("票号必须为13位!");
		return;
	}
	if(xcdh=="" || xcdh==null|| xcdh=="0"){
		alert("行程单号不能为空！");
		return;
	}
	if(xcdh.length!=10){
		alert("行程单号必须为10位!");
		return;
	}
	submitAjax("/asms/xcd_zf.shtml",
    	{p:'zf',tkno:tkno,xcdh:xcdh,cjrid:cjrid,zffs:lx,asmsAndagent:"asms"},
    	function(transport){
    		var msg = transport.responseText;
    		alert(msg);
    		if(msg.indexOf("失败") == -1){
	    		try{
	    			document.listForm.submit();
	    		}catch(e){
		    		try{parent.document.listForm.submit();}catch(e){}
	    		}
    		}
    	}
    );
}
</script>
</html>

