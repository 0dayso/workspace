<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
	<head>
		<title>行程单列表</title>
		<%@include file="/static/core/print_lodop/lodop.jsp" %>
		<script type="text/javascript" src="/static/js/xcdprint/xcdprint.js"></script>
		<script type="text/javascript" src="/static/js/xcdprint/xcdprintinfo.js"></script>
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
			
			//判断字符串是否为空
			function isBlank(obj){
				return(!obj || $.trim(obj) === "");
			}

			//按票号提取行程单信息
			function doTkno(tkno){
				if(event.keyCode == 13){
					var ddbh = $("#ddbh").val();
					if(tkno.length<13){
				 		alert("请检查PNR/票号输入是否有效");
				 		return;
				 	}
					if(isBlank(tkno)){
						alert("请检查PNR/票号输入是否有效");
				 		return;
					}
					//alert(ddbh+"aa"+tkno);
			    	window.location.href="${ctx}/vedsb/xcdgl/printxcd/enterXcdListPage?ddbh="+ddbh+"&tkno="+tkno;
				}else{
					return;
				}
			}
			
			
			/************创建行程单 START**********/
			var createTknos=new Array();
			var createTknosIndex=0;
			var createSuccessFlag=true;//全部创建成功
			var createFailureTknoArr=new Array();//创建失败的票号
			var createFailureXcdhArr=new Array();//创建失败的行程单
			var createFailureTipArr=new Array();//创建失败的信息
			//创建行程单
			function createXcd(){ 
				var tkno ="";
				var chk_value =[];    
				//jquery获取复选框值    
				$('input[name="selOne"]:checked').each(function(){    
			 		chk_value.push($(this).val());    
			 	});
				//alert(chk_value);
				//这里还未考虑有多个票号的情况
				if(chk_value.length==0){
					alert("请选择需要创建行程单的票号！");	
				}
				tkno=chk_value.join(','); // arry 转为 字符串
				//alert(tkno);
				//初始化创建失败信息
				createSuccessFlag=true;
				createFailureTknoArr=new Array();//创建失败的票号
				createFailureXcdhArr=new Array();//创建失败的行程单
				createFailureTipArr=new Array();//创建失败的信息
				//disabledButton(0);
				createTknos=tkno.split(",");
				reqCreateXcd2(createTknos[createTknosIndex]);
			}    
			
			function reqCreateXcd2(tknotemp){
				if(isBlank(tknotemp)){
					return;
				}
				var params={};
				var xcdhtemp = $("#xcdh"+tknotemp).val();
				var gngj = $("#gngj"+tknotemp).val();
				var pj_zdj = "0.00"
				params['tkno'] = tknotemp;
				params['xcdh'] = xcdhtemp;
				params['gngj'] = gngj;
				params['pj_zdj'] = pj_zdj;
				var url = "${ctx}/vedsb/xcdgl/printxcd/createXcd2"
				$.ajax({
					type:"post",
					url:url,
					data:params,
					success:function(result){
						var info=transport.responseText;
						if(info != "OK"){
							alert("创建行程单失败，提示错误:"+info+"  创建失败的票号是:"+tknotemp);
							createSuccessFlag = false;
							createFailureTknoArr.push(tknotemp);//创建失败的票号
							createFailureXcdhArr.push(xcdhtemp);//创建失败的行程单
							createFailureTipArr.push(info);//创建失败的信息
						}else{//创建成功后改变显示状态
							alert("创建行程单成功！"+"  创建成功的票号是:"+tknotemp);
							$("#xcdzt"+tknotemp).attr("value","3"); 
						}
						//openXcdDetail(tknoTemp);
						createTknosIndex++;
						if(createTknosIndex<createTknos.length){
							reqCreateXcd2(createTknos[createTknosIndex]);
						}else{
							//disabledButton(1);
							createTknos=new Array();
							createTknosIndex=0;
							//showCreateResult();
						}
					}
				});
			}
			/************创建行程单 END**********/
			
			
			
			/************打开行程单 START**********/
			//查看详细 xcdzt为3表示已创建 
			var onclickCnt=0;//防止连续点击
			function openXcdDetail(tkno){
				if(onclickCnt>0){
					return;
				}
				onclickCnt++;
				$("xcdhTemp").disabled="";
				//setTkNoColor(tkno);
				var xcdh = $("#xcdh"+tkno).val();
				if(isBlank(xcdh)){
					$("xcdhTemp").value="";
				}
				var xcdzt = $("#xcdzt"+tkno).val();
				$("#xcdhTemp").attr("value",xcdh);
				//隐藏所有   这一段不知道做什么作用，故隐藏
				/**var allDetailDivObj=$("allDetailDiv");
				var divObjs=allDetailDivObj.getElementsByTagName("div");
				for(var i=0;i<divObjs.length;i++){
					if(divObjs[i].alreadyload=="true"||divObjs[i].alreadyload=="false"){
						divObjs[i].style.display="none";
					}
				}*/
				//var alreadyload=$("#xcdprintinfoshowDIV"+tknoTemp).alreadyload;
				var divId="xcdprintinfoshowDIV"+tkno; 
				if($(divId)&&$(divId).alreadyload=="true"){//已经加载过了直接显示
					$(divId).style.display="";
					onclickCnt=0;
				}else{
					$("#emptyXcdPage").style.display="block";
					var url = "${ctx}/vedsb/xcdgl/printxcd/openXcdDetail";
					$("#xcdprintinfoshowDIV"+tknoTemp).load(url,{xcdh:xcdh,tkno:tkno,xcdzt:xcdzt},function(data){
						$(divId).alreadyload=="true";
						$(divId).style.display = "";
						$("#emptyXcdPage").style.display="none";
						//disabledButton(1);
						onclickCnt = 0;
						//setYzm();
						//var ifgq = $("ifgq").value;
						//calPjhj(ifgq);
					});
				}
			}
			/************打开行程单 END**********/


			/************打印行程单 START**********/
			var printTknos=new Array();
			var printTknosIndex=0;
			function printXcd(){
				var bool=true;
				var tkno="";
				var chk_value =[];
				$('input[name="selOne"]:checked').each(function(){    
			 		chk_value.push($(this).val());    
			 	});
				if(chk_value.length==0){
					alert("请选择需要创建行程单的票号！");	
				}
				tkno=chk_value.join(','); // arry 转为 字符串
				if(!bool){
					return;
				}
				if(isBlank(tkno)){
					alert("请选择有效记录打印");
					return ;
				}
				var tknoPrints=tkno.split(",");
				for(var i=0;i<tknoPrints.length;i++){
					var tknoTemp=tknoPrints[i];
					//var alreadyload=$("#xcdprintinfoshowDIV"+tknoTemp).alreadyload;
					
					//if(alreadyload=="true"){
						var formid="xcdform"+tknoTemp;
						if($(formid)){
							printTknos.push(tknoTemp);
							//setXcdztSpanInnerHTML("等待打印","",tknoTemp);
						}else{
							//setXcdztSpanInnerHTML("打印出错","票号"+tknoTemp+"无打印数据,请核实",tknoTemp);
						}
					//}
				}
				//printAjax(printTknos[printTknosIndex],getprintAction());
				printAjax(printTknos[printTknosIndex]);
			}
			
			//调用打印
			function printAjax(tkno){
				if(isBlank(tkno)){
					return;
				}
				var formid="xcdform"+tkno;
				var xcdh=$("xcdh"+tkno).value
				var formObj= $(formid);
				var dataArray =  $("#formid").serialize();
				//$("spanxcdzt"+tkno).innerHTML="<img src='/static/images/print_kdd/1001700mr.gif'>";
				if(formObj){
					var url = "${ctx}/vedsb/xcdgl/printxcd/printXcd";
					var params= {};
					params['tkno'] = tkno;
					params['xcdh'] = xcdh;
					params['dataArray'] = dataArray;
					$.ajax({
						type:"post",
						url:url,
						data:params,
						success:function(result){
							var text = result.responseText;	
							if(text.indexOf("操作失败,原因")==0){
								setXcdztSpanInnerHTML("打印出错",text,tkno);
							}else{
								var json = eval("("+text+")"); 
								var gngj = $("#gngj"+tkno).val();
							    //var rtn =  xcdPrint_lodop(json,action,gngj,"");
						    	var rtn =  xcdPrint_lodop(json,gngj,"");
							    if(rtn>0){//打印成功
							   		setXcdztSpanInnerHTML("打印完成","",tkno);
							   	}else{
							   		setXcdztSpanInnerHTML("打印出错","系统打印驱动运行异常",tkno);
							   	}
						    	printTknosIndex++;
								if(printTknosIndex<printTknos.length){
									//printAjax(printTknos[printTknosIndex],action);
									printAjax(printTknos[printTknosIndex]);
								}else{
									//disabledButton(1);
									printTknos=new Array();
									printTknosIndex=0;
									//打印完成后行程单代打收款弹出界面
									//if($("zdtcddsk").checked){
										//xcdddsk();
									//}
								}
							}
						}
					});
				}
			}
			
		</script>
	</head>
  	<body style="background: white;">
  		 <!-- 票号列表或者乘机人列表 1票号 2乘机人 -->
  		<div id=wrapper title="" style="height: 99%;width: 100%;" >
  		   <div style="width: 22%;float: left">
  		       <%@include file="printxcd_left.jsp" %>
  		   </div>
  		   <div style="width: 77%;float: right">
  		       <%@include file="printxcd_right.jsp" %>
  		   </div>
  		</div>
  	</body>
</html>
