<%@page language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
    <title>网店平台账号设置</title>
</head>
<body onload="init()">
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
	var validator;
	$(function(){
		changPtSet();
		validator = $("#jpDdszForm").Validform({
			tiptype:3,
		});

	})
	function changPtSet(){
		var wdpt = $("#wdpt").val();
		var ddGngj = $("#ddGngj").val();
		var ptbs = "";
		if(wdpt=='10100010'){
			ptbs = "isQN";
		}else if(wdpt=='10100011'){
			ptbs = "isTB";
			if(ddGngj=='1'){
				ptbs = "isTBGJ";
			}
		}else if(wdpt=='10100012'){
			ptbs = "isKX";
		}else if(wdpt=='10100024'){
			ptbs = "isTC";
		}else if(wdpt=='10100050'){
			ptbs = "isXC";
		}
		var oldptbs = $("#oldptbs").val();
		if(oldptbs!=''){
			$("."+oldptbs).hide();
			$("."+oldptbs+"_no").show();
		}
		$("."+ptbs).show();
		$("."+ptbs+"_no").hide();
		$("#oldptbs").val(ptbs);
	}
	function changDdBmid(obj){
		var ddBmid = $(obj).find("option:selected").attr("_ddBmid");
		$('#ddBmid').val(ddBmid);
	}
	function toSave(){
		validator.submitForm(false);
	}
	function closeLayer(){
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	}
	function taobaokey(appkey){
		if(appkey==""){
			alert("先输入APPKEY");
			$("#ddJkzh").focus();
			return ;
		}
		window.open("https://oauth.taobao.com/authorize?response_type=token&client_id="+appkey);
	}
</script>
<div class="container" >
<input type="hidden" id="oldptbs" value="" />
<form action="${ctx}/vedsb/jpddsz/jpddsz/saveJpDdsz" id="jpDdszForm" method="post" name="jpDdszForm">
    <input type="hidden" name="closeDiv" value="true"/>
    <input type="hidden" name="callback"  value="parent.refresh()" />
    <input type="hidden" id="wdid" name="wdid" value="${wdzlsz.id}" />
    <input type="hidden" id="wdpt" name="wdpt" value="${wdzlsz.wdpt}" />
    <input type="hidden" id="type" name="type" value="${not empty jpDdsz ? 'edit': 'add'}" />
    <input type="hidden" name="submitForm" value="ListForm">
    <input type="hidden" id="wdmc" name="wdmc" value="${wdzlsz.wdmc}" />
	<table class="list_table" width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td class="td_right" style="width: 20%">网店名称：</td>
            <td class="td_left" style="width: 30%">${wdzlsz.wdmc}</td>
            <td class="td_right" style="width: 20%">网店平台：</td>
            <td class="td_left" style="width: 30%">${wdzlsz.wdptmc}</td>
            <td colspan="2" style="display:none"></td>
        </tr>
        <tr>
            <td class="td_right">是否开启正常导单：</td>
            <td >
            	<input type="checkbox" ${jpDdsz.ddKqzcdd eq '1' ? 'checked' : ''} onClick="if(this.checked){$('#ddKqzcdd').val('1')}else{$('#ddKqzcdd').val('0')}"/>
            	<input type="hidden" name="ddKqzcdd" id="ddKqzcdd"  value="${empty jpDdsz.ddKqzcdd ? '0' : jpDdsz.ddKqzcdd}" />
            </td>
            <td class="td_right" style="" >业务类型：</td>
	        <td style="" class="td_left">
	        	${wdzlsz.ywlx ne '1001901' ? '国际机票':'国内机票'}
	        	<input type="hidden" name="ddGngj" id="ddGngj"  value="${'1001901' ne wdzlsz.ywlx ? '1' : '0'}" />
	        </td>
        </tr>
        <tr class="isXC isTB" style="display:none">
           <td class="td_right isTB isXC" style="display:none" >是否开启退票导单：</td>
           <td class="td_left isTB isXC" style="display:none" >
	           <input type="checkbox" ${jpDdsz.ddKqtpdd eq '1'  ? 'checked' : '' } onClick="if(this.checked){$('#ddKqtpdd').val('1')}else{$('#ddKqtpdd').val('0')}" />
	           <input type="hidden" name="ddKqtpdd" id="ddKqtpdd"  value="${empty jpDdsz.ddKqtpdd ? '0' : jpDdsz.ddKqtpdd}" />
           </td>
           <td class="td_right isTB isXC" style="display:none" >是否开启改签导单：</td>
           <td class="td_left isTB isXC" style="display:none" >
	           <input type="checkbox" ${jpDdsz.ddKqgqdd eq '1'  ? 'checked' : '' } onClick="if(this.checked){$('#ddKqgqdd').val('1')}else{$('#ddKqgqdd').val('0')}" />
	           <input type="hidden" name="ddKqgqdd" id="ddKqgqdd"  value="${empty jpDdsz.ddKqgqdd ? '0' : jpDdsz.ddKqgqdd}" />
           </td>
        </tr>
        <tr style="display:none" class="isTB">
            <td class="td_right">开启扫描派单业务：</td>
            <td>
               <input type="checkbox" ${jpDdsz.sfsmpdyw eq '1'  ? 'checked' : '' } onClick="if(this.checked){$('#sfsmpdyw').val('1')}else{$('#sfsmpdyw').val('0')}"/>
	           <input type="hidden" name="sfsmpdyw" id="sfsmpdyw"  value="${empty jpDdsz.sfsmpdyw ? '0' : jpDdsz.sfsmpdyw}" />
            </td>			  
            <td class="td_right">出票方式：</td>
            <td>
               <input type="checkbox" ${jpDdsz.ddautocp eq '1'  ? 'checked' : '' } onClick="if(this.checked){$('#ddautocp').val('1')}else{$('#ddautocp').val('0')}"/>
               勾选表示扫描方式出票,不勾选表示导弹后立即出票
	           <input type="hidden" name="ddautocp" id="ddautocp"  value="${empty jpDdsz.ddautocp ? '0' : jpDdsz.ddautocp}" />
            </td>
        </tr>
        <tr style="display:none" class="isTB">
            <td class="td_right">设置正常单扫描时间范围（单位分钟）：</td>
            <td>
	           <input type="text" name="tbsmsjjg" id="tbsmsjjg" datatype="number,minvalue,maxvalue" minvalue="120" maxvalue="99999"  nullmsg="请输入值"  value="${empty jpDdsz.tbsmsjjg ? '120' : jpDdsz.tbsmsjjg}" />
            </td>			  
 			<td colspan="2" style=""></td>
        </tr>
        <tr class="isQN" style="display:none">
           <td style="display:none" class="td_right isQN">导单联系人手机：</td>
           <td style="display:none" class="td_left isQN">
               <input type="text" name="lxrsj" id="lxrsj" size="25"  value="${jpDdsz.lxrsj}"  size="10" onblur="value=strTrim(this.value);"/>
           </td>
           <td colspan="2" class="td_left"></td>
        </tr>  
        <tr>
            <td class="td_right">接口账号：</td>
            <td>
                <input name="ddJkzh" id="ddJkzh" datatype="*" class="" size="25"
                value="${jpDdsz.ddJkzh}" />
                <font color="red">*</font>
            </td>
            <td class="td_right">接口密码：</td>
            <td>
                <input name="ddJkmm" type="password" datatype="*" class="" size="25"
                value="${jpDdsz.ddJkmm}" />
                <font color="red">*</font>
            </td>
       	</tr>
        <tr>
            <td class="td_right">接口地址：</td>
            <td>
                <input name="ddJkdz" datatype="*" class="" size="25"
                value="${jpDdsz.ddJkdz}" />
                <font color="red">*</font>
            </td>
         	<td class="td_right">安全码1：</td>
         	<td>
              	<input name="ddAqm1" class="" id="dh" size="25" value="${jpDdsz.ddAqm1}" />
        	</td>
        </tr>
        <tr>
           	<td class="td_right">安全码2：</td>
           	<td>
               	<input name="ddAqm2" class="" size="25" value="${jpDdsz.ddAqm2}"/>
           	</td>
           	<td class="td_right"> </td>
           	<td>
              
           	</td>
        </tr>
        <tr>
        	<td class="td_right">订单OFFICE：</td>
           	<td>
               	<input name="ddoffice" class="" id="ddoffice" size="25"
               	value="${jpDdsz.ddoffice}" />
           	</td>
           	<td class="td_right isXC" style="display:none">授权OFFICE：</td>
           	<td class="isXC" style="display:none">
           		<input name="sqoffice" class="" id="sqoffice" size="25"
               	value="${jpDdsz.sqoffice}" />
           	</td>
           	<td colspan="2" class="isXC_no"></td>
       	</tr>
       	<!-- 
       	<tr class="isTB" style="display:none">
        	<td class="td_right">淘宝改签请求地址：</td>
           	<td>
               	<input name="gqddz" class="" id="gqddz" size="25"
               	value="${jpDdsz.gqddz}" />
           	</td>
           	<td class="td_right">淘宝改签登录账号：</td>
           	<td class="" style="">
           		<input name="gqdlzh" class="" id="gqdlzh" size="25"
               	value="${jpDdsz.gqdlzh}" />
           	</td>
       	</tr>
       	 -->
       	 <tr class="isTB isTBGJ" style="display:none">
           	<td class="td_right">淘宝登录账号：</td>
           	<td class="" style="" colspan="3">
           		<input name="gqdlzh" class="" id="gqdlzh" size="25"
               	value="${jpDdsz.gqdlzh}" />
               	<span style="color:gray">用于对应淘宝发送Sessionkey通知信息</span>
           	</td>
       	</tr>
       	  <tr style="display:none" class="isTB isTBGJ">
			<td class="td_left" colspan="4">
				<a href="javascript:void(0)" onclick="taobaokey($('#ddJkzh').val())" >获取授权Sessionkey</a>
				失效时间： ${jpDdsz.ddAqm3}	 
				<br>
				请在淘宝注册这个回调地址：http://服务器地址:端口/veds_ticket/webcontent/taobaosessionkey/wdsession
		    </td>
         </tr>
		<tr style="display:none" class="isTB isTBGJ isXC">
            <td class="td_right">自动确认订单：</td>
            <td>
                <input type="radio" name="ddSfqr" ${empty jpDdsz.ddSfqr or jpDdsz.ddSfqr ne '1'  ? 'checked' : '' } value="0" />不自动认领<!-- 不自动锁单 -->
                <input type="radio" name="ddSfqr" ${jpDdsz.ddSfqr eq '1'  ? 'checked' : '' } value="1" />自动认领<!-- 不自动锁单 -->
            </td>			  
            <td colspan="2"></td>
        </tr>
        <tr>
            <td class="td_right">默认订票员：</td>
	        <td>
	         	<select name="ddUserid" id="" datatype="*" class="" request="true" onchange="changDdBmid(this)">
	         		<option value="">==请选择默认订票员==</option>
	         		<c:forEach var="shyhb" items="${shyhbList}">
	         			<option value="${shyhb.bh}" ${jpDdsz.ddUserid eq shyhb.bh ? 'selected':'' } _ddBmid="${shyhb.shbmid}"  >${shyhb.xm}</option>
	         		</c:forEach>
	            </select>
	            <font color="red">*</font>
	            <input type="hidden" name="ddBmid" id="ddBmid"  value="${jpDdsz.ddBmid}" />           
	        </td>
           	<td class="td_right">默认收款科目：</td>
         	<td class="">
         		<select name="ddMrkm" id="" datatype="*" class="" request="true">
         			<option value="">==请选择默认收款科目==</option>
         			<c:forEach var="shzfkm" items="${shzfkmList}">
         				<option value="${shzfkm.kmbh}" ${jpDdsz.ddMrkm eq shzfkm.kmbh ? 'selected':'' }>${shzfkm.kmmc}</option>
         			</c:forEach>
				</select>
				<font color="red">*</font>
         	</td>
        </tr>
        <tr style="display:none" class="isQN">	
	  		<td class="td_right">是否导入配送信息：</td>
	     	<td>
		   		<select name="sfdrpsxx" style="width:100px">
		   			<option value="0" ${'0' eq jpDdsz.sfdrpsxx ? 'selected':'' }>不导入配送信息和快递费用</option>
		   			<option value="1" ${'1' eq jpDdsz.sfdrpsxx ? 'selected':'' }>导入配送信息和快递费用</option>
		   			<option value="2" ${'2' eq jpDdsz.sfdrpsxx ? 'selected':'' }>只导入配送信息不导入快递费用</option>
		  		</select>
          	</td>
          	<td class="td_right">导单无支付方式：</td>
         	<td>
        		<select name="ddwzffs" id="" class="" request="true">
            		<option value="0" ${empty jpDdsz.ddwzffs or jpDdsz.ddwzffs eq '0' ? 'selected' : '' }>取默认收款科目</option>
            	 	<option value="1" ${jpDdsz.ddwzffs eq '1' ? 'selected' : '' }>订单不自动支付</option>
            	</select>
         	</td>
        </tr>
      
         <!-- 控制退票入库是否自动转废票(10826参数) -->
         <!-- 
         <tr style="display:none" class="isTB isTBGJ isXC">
        	<td class="td_right">网店宝当天出票的退<br>票单导入是否自动转<br>废票(10826参数)：</td>
        	<td colspan='3'>
        		<input type="radio" name="YDHM" ${empty cs10826_1 or cs10826_1 eq '0' ? 'checked' : ''} value="0" />不转换
        		<input type="radio" name="YDHM" ${cs10826_1 eq '1' ? 'checked' : ''} value="1" />全部转换
        		<input type="radio" name="YDHM" ${cs10826_1 eq '2' ? 'checked' : ''} value="2" />以下航司转换
        		<input type="text" id="cs10826_yw" name="YW" style="width:150px; border-top: none;border-left: none;border-right: none"
            		class="input1 UpperCase" value="${cs10826_2}" />（多个用/分隔）
            	<input type="button" class="asms_button" value="修改参数" onclick="setSort()"/>
        	</td>
         </tr>
          -->
          <!-- 订单取消通知 -->
          <!-- 
         <tr style="display:none" class="isQN isXC">
           	<td class="td_right">网店通知订单<br>取消时是否提醒：</td>
           	<td colspan="3">
           		<input type="radio" name="ddqxtx" onclick="" value="0" ${empty jpDdsz.ddqxtx or '0' eq jpDdsz.ddqxtx ? 'checked' : ''} />不提醒
           		<input type="radio" name="ddqxtx" onclick="" value="1" ${'1' eq jpDdsz.ddqxtx ? 'checked' : ''} />提醒
           		<font color="gray">（去哪国内、携程必须开启通知业务；同时下面的提醒方式用","号分隔）</font>
           	</td>
         </tr>
         <tr style="display:none" class="isQN isXC">
            <td class="td_right" colspan="1">RTX：</td>
         	<td colspan="3">
	    		<input type="text" name="rtxtx" style="width:400px; border-top: none;border-left: none;border-right: none"
         			class="input1" value="${jpDdsz.rtxtx}" />
         	</td>
         </tr>
         <tr style="display:none" class="isQN isXC">
         	<td class="td_right" colspan="1">短信：</td>
         	<td colspan="3">
         		<input type="text" name="dxtx" style="width:400px; border-top: none;border-left: none;border-right: none"
         		class="input1" value="${jpDdsz.dxtx}" />
         	</td>
         </tr>
         <tr style="display:none" class="isQN isXC">
        	<td class="td_right" colspan="1">邮件：</td>
         	<td colspan="3">
         		<input type="text" name="yjtx" style="width:400px; border-top: none;border-left: none;border-right: none"
         		class="input1" value="${jpDdsz.yjtx}" />
         	</td>
         </tr>
         -->
         <tr>
          	<td class="td_right" colspan="1">检查NO位时限：</td>
           	<td colspan="3">
            	<input type="radio" name="ddNwsx" ${'0' eq jpDdsz.ddNwsx or empty jpDdsz.ddNwsx ? 'checked' : '' } value="0" />否
            	<input type="radio" name="ddNwsx" ${'1' eq jpDdsz.ddNwsx ? 'checked' : '' } value="1" />是
            </td>
         </tr>
         <tr name="ddsznodes" id="ifAutoPAT">
         	<td class="td_right" colspan="1">是否自动PAT：</td>
          	<td colspan="3">
           		<input type="radio" id="ddZdpat" onclick="" name="ddZdpat" ${jpDdsz.ddZdpat eq '0' or empty jpDdsz.ddZdpat  ? 'checked' : '' } value="0" />不自动PAT
           	  	<input type="radio" id="ddZdpat" onclick="" name="ddZdpat" ${jpDdsz.ddZdpat eq '1'  ? 'checked' : '' } value="1" />票面价为0的订单，自动PAT<br>
           	  	<input type="radio" id="ddZdpat" onclick="" name="ddZdpat" ${jpDdsz.ddZdpat eq '2'  ? 'checked' : '' } value="2" />所有订单都自动PAT,且修改订单账单价 
           	  	<input type="radio" id="ddZdpat" onclick="" name="ddZdpat" ${jpDdsz.ddZdpat eq '3'  ? 'checked' : '' } value="3" />政策代码中含
<input type="text" id="ddZcdmpat" name="ddZcdmpat" value="${jpDdsz.ddZcdmpat}" class="${jpDdsz.ddZdpat eq '3' ? 'required' : ''}"
 style="width:150px; border-top: none;border-left: none;border-right: none"  />的订单，才进行PAT
           	</td>
          </tr>
        </table>
        <br/>
        <div align="center">
            <input type="button" class="ext_btn ext_btn_submit" value="保 存" onclick="toSave()"/>
            <input type="button" class="ext_btn" value="关 闭" onclick="closeLayer();"/>
        </div>
    </form>
    <table cellspacing="0" cellpadding="0" width="100%" align="center" border="0"
    class="sugges">
        <tr>
            <td class="sugtitle">温馨提示： 带
                <font color="red">*</font>为必填项，请认真填写
                <br/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

