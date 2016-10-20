var ii;
function iframOnloadEven(){
	if(ii){
		layer.close(ii);
	}
	setCompareBtShow();
}
function setCompareBtShow(){
	if(!document.getElementById("asmsiframe")){
		return false;
	}
	var asms = document.getElementById("asmsiframe").contentWindow;
	var asmsdata = asms.document.getElementById("vc_asms");
	if(!document.getElementById("hiddeniframe")){
		return false;
	}
	var bank = document.getElementById("hiddeniframe").contentWindow;
	var bankdata=bank.document.getElementById("banklistid");
	if(asmsdata && bankdata){
		$("#compare_ti").show();
	}else{
		$("#compare_ti").hide();
	}
}
function upfile(){
	var fileName=$("#file").val();
	if(!fileName){
		alert("请选择一个文件！");
		return false;
	}
   var hz=fileName.substr(fileName.lastIndexOf(".")+1).toUpperCase();
	//验证文件格式
	if(hz!="XLS" && hz!="XLSX"){
		alert("请上传xls或xlsx格式文件！");
		return false;
	}
	ii = layer.load('系统正在处理您的操作,请稍候!');
	$("#upForm").submit();
}
function searchAsms(){
	ii = layer.load('系统正在处理您的操作,请稍候!');
	$("#asmsForm").submit();
}
//对比
function compareData(){
	var url=$ctx+'/vedsb/jpcwgl/jpysdz/dbdata';
	var parameters = $('#asmsForm').serialize()+"&date="+Math.random();
	url+="?"+parameters;
	ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON(url,function(json){
		layer.close(ii);
		if(json.status=='1'){
			ii = layer.load('已完成对比，正跳转到对比结果页面，请稍等。。。');
			try{
				window.opener.document.dzform.submit();
			}catch(e){
				
			}
			$("#dbjgForm").submit();
		}else{
			layer.msg(json.error,3,3);
		}
	});
}
function showXzbz(zdlx){
	var url=$ctx+'/vedsb/jpcwgl/jpysdz/viewzdxzbz?zdlx='+zdlx;
	$.layer({
			type: 2,
			title: ['下载帮助'],
			area: ['700px', '400px'],
			iframe: {src: url}
	   });
}
