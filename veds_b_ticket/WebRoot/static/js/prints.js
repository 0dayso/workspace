function Lodop_Print(action){
	//如果打印定时启动，则清除，用在自动打印的时候，停止自动打印，好操作打印调整功能.
    var rtn = 0; 
    if(action=="P"){
		var rtn_ptn =LODOP.PRINT();
		if(rtn_ptn){
			rtn=1;
		}
    }else if(action=="S"){
    	rtn = LODOP.PRINT_SETUP();
    }else if(action=="V"){ 
    	rtn = LODOP.PREVIEW();
    }else if(action =="D"){
    	LODOP.PRINT_DESIGN();
    }
	return  rtn
}

//收件人长度验证
function checksjrlength() {
	var LEN = 16;
    var ct_sjrbytes = isBlank($("ct_sjr").value) ? 0 : getStrBytes(trim($("ct_sjr").value));
	if(ct_sjrbytes > LEN)  
	{
		alert("收件人长度不能超过"+LEN+"字符！现在长度:"+ct_sjrbytes+"/"+LEN);
		$("ct_sjr").value = "请输入收件人！";
	}
	if(ct_sjrbytes == 0)  
	{
		alert("收件人不能为空,请输入姓名!");
		$("ct_sjr").value = "请输入收件人！";
	}
}

//收件地址长度验证
function checksjdzlength() {
	var LEN = 200;
	var ct_sjdzbytes = isBlank($("ct_sjdz").value) ? 0 : getStrBytes(trim($("ct_sjdz").value));
	if(ct_sjdzbytes > LEN)
	{
		alert("收件地址长度不能超过"+LEN+"字符！现在长度:"+ct_sjdzbytes+"/"+LEN);
		$("ct_sjdz").value = "请输入收件地址！";
	}
}

//收件单位长度验证
function checksjdwlength() {	
	var LEN = 30;
	var ct_sjdwbytes = isBlank($("ct_sjdw").value) ? 0 : getStrBytes(trim($("ct_sjdw").value));
	if(ct_sjdwbytes > LEN)  
	{
		alert("长度不能超过"+LEN+"字符！现在长度:"+ct_sjdwbytes+"/"+LEN);
		$("ct_sjdw").value = "请输入收件单位！";
	}
}

//收件人手机长度验证
function checksj() {
	//1是 0不是
	var ct_smsmobilno = $("ct_smsmobilno");//联系手机
	var ct_smsmobilnobytes = isBlank(ct_smsmobilno.value) ? 0 : getStrBytes(trim(ct_smsmobilno.value));
	if(ct_smsmobilnobytes > 22)  
	{
		alert("手机格式不对，请重新输入!");
		ct_smsmobilno.value = "请输入手机！";
	}
}

//收件人电话验证
function checkdhh() {
	var ct_nxrdh = $("ct_nxrdh");//联系电话	
	var ct_nxrdhbytes = isBlank(ct_nxrdh.value) ? 0 : getStrBytes(trim(ct_nxrdh.value));
	if(ct_nxrdhbytes > 20)
	{
		alert("电话格式不对，请重新输入!");
		ct_nxrdh.value = "请输入电话！";
	}
}

//寄件人字段长度验证
function lengchek(obj,length) {
    var value=obj.value;
    var valuebytes = getStrBytes(trim(value));
	if(valuebytes == 0)  
	{
		alert("寄件人信息不完整！");
		obj.value = "请重新输入！";
	}
	if(valuebytes > length)  
	{
		alert("长度不能超过"+length+"字符！现在长度:"+valuebytes+"/"+length);
		obj.value = "请重新输入！";
	}
}

//跳转到被打开页面的父页面
function refOpener(){
	try{
		document.listForm.submit();
	}catch(e){
		try{
			//创建当前窗口的那个窗口的引用,也就是得到父级窗口的引用
			opener.document.listForm.submit();
			//关闭窗口
			self.close();
		}catch(e){}
	}
}

//挂号信表单验证
function checkghform() {
	//收件人信息验证
	var ct_sjrbytes = isBlank($("ct_sjr1").innerText) ? 0 : getStrBytes(trim($("ct_sjr1").innerText));
	if(ct_sjrbytes == 0)  
	{
		alert("收件人不能为空!");
		$("ct_sjr1").innerText = "请输入收件人！";
		return false;
	}
	if(ct_sjrbytes > 16)  
	{
		alert("收件人长度不能超过"+16+"字符！现在长度:"+ct_sjrbytes+"/16");
		$("ct_sjr1").innerText = "请输入收件人！";
		return false;
	}
	var ct_sjdzbytes = isBlank($("ct_sjdz1").innerText) ? 0 : getStrBytes(trim($("ct_sjdz1").innerText));
	if(ct_sjdzbytes == 0)  
	{
		alert("收件地址不能为空,请输入收件地址!");
		$("ct_sjdz1").innerText = "请输入收件地址！";
		return false;
	}
	if(ct_sjdzbytes > 200)  
	{
		alert("收件地址长度不能超过"+200+"字符！现在长度:"+ct_sjdzbytes+"/200");
		$("ct_sjdz1").innerText = "请输入收件地址！";
		return false;
	}
	return true;
}

//邮寄表单验证
function checkyjform() {
	//收件人信息验证
	var ct_sjrbytes = document.getElementById("ct_sjr1").innerText;
	var lenth= !ct_sjrbytes?0:ct_sjrbytes.length;
	if(lenth == 0)  
	{
		alert("收件人不能为空!");
		document.getElementById("ct_sjr1").innerText = "请输入收件人！";
		return false;
	}
	if(lenth > 16)  
	{
		alert("收件人长度不能超过"+16+"字符！现在长度:"+lenth+"/16");
		document.getElementById("ct_sjr1").innerText = "请输入收件人！";
		return false;
	}
	var ct_sjdzbytes = document.getElementById("ct_sjdz1").innerText;
	lenth= !ct_sjdzbytes?0:ct_sjdzbytes.length;
	if(lenth == 0)  
	{
		alert("收件地址不能为空,请输入收件地址!");
		document.getElementById("ct_sjdz1").innerText = "请输入收件地址！";
		return false;
	}
	if(lenth > 200)  
	{
		alert("收件地址长度不能超过"+200+"字符！现在长度:"+lenth+"/200");
		document.getElementById("ct_sjdz1").innerText = "请输入收件地址！";
		return false;
	}
	return true;
}


//快递表单打印前验证
function checkform() {
	//收件人信息验证
	var ct_sjrbytes = document.getElementById("ct_sjr").value;
	var length=ct_sjrbytes?ct_sjrbytes.length:0;
	if(length == 0)  
	{
		alert("收件人不能为空!");
		document.getElementById("ct_sjr").value = "请输入收件人！";
		return false;
	}
	if(length > 16)  
	{
		alert("收件人长度不能超过"+16+"字符！现在长度:"+length+"/16");
		document.getElementById("ct_sjr").value = "请输入收件人！";
		return false;
	}
	var ct_sjdzbytes = document.getElementById("ct_sjdz").value;
	length=ct_sjdzbytes?ct_sjdzbytes.length:0;
	if(length == 0)  
	{
		alert("收件地址不能为空,请输入收件地址!");
		document.getElementById("ct_sjdz").value = "请输入收件地址！";
		return false;
	}
	if(length > 200)  
	{
		alert("收件地址长度不能超过"+200+"字符！现在长度:"+length+"/200");
		document.getElementById("ct_sjdz").value = "请输入收件地址！";
		return false;
	}
	var ct_smsmobilnobytes = document.getElementById("ct_smsmobilno").value;
	length=ct_smsmobilnobytes?ct_sjdzbytes.length:0;
	if(length == 0)
	{
		alert("手机号码不能为空");
		document.getElementById("ct_smsmobilno").value = "请输入手机号！";
		return false;
	}
	
	if(length > 60)
	{
		alert("手机号码长度不能超过"+60+"字符！现在长度:"+length+"/60");
		document.getElementById("ct_smsmobilno").value="请输入手机号！";
		return false;
	}
	return true;
}	
