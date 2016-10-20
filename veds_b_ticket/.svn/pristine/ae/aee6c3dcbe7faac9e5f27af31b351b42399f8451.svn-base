<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>订单详情</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>

<style type="text/css">
.passenger td{text-align:center;}
.wb_td01{ 
	background:#d0f2ff;
}
.wb_td02{ 
	font-size:12px; 
	color:#999;
	white-space:nowrap;
	over-flow:hidden;
}
.wb_td03{ 
	white-space:nowrap;
	over-flow:hidden;
}
.wb_tr01{ border-bottom:1px solid #ccc;}
.btn{ font-size:12px; color:#fff; text-align:center; border:none; background:#1195db; height:25px; width:80px;}
.noncewb{ height:35px; width:73px; background:url(${ctx}/static/images/wdimages/non_bg.png) no-repate;}
.web_button{height:25px;background:#f1f1f1;border:1px solid #ccc;border-radius:3px;cursor:pointer;}
</style>

<script type="text/javascript">
var validator;
$(function(){
	validator = $("#saveDetailForm").Validform({
		tiptype:4,
		ajaxPost:true,
		callback:function(transport){
			if(transport.CODE=="0"){
				$("#skxx_nxdh_error").hide();
			   layer.msg("订单详保存成功！",2,1);
			}else{
			   layer.msg(transport.MSG,2,1);
			//   alert(transport.MSG);
			}
		}
	});
})

	//检查电话号码和联系方式是否都为空
	function checkNxdh(){
		var nxdh=$("#ct_nxrdh").val();
   		var nxsj=$("#nxsj").val();
   		if(nxdh=="" && nxsj ==""){
   			$("#skxx_nxdh_error").show();
   			return false;
   		}
   		return true;
	}
   function saveDetail(){
   		var flag=checkNxdh();
   		if(flag){
   			validator.submitForm(false);
   		}
   }
   
  //发送短信
  function enterSendSmsPage(ddbh){
	var url="${ctx}/vedsb/common/sms/enterSendSmsPage?ddbh="+ddbh;
	$.layer({
			type: 2,
			title: ['<b>发送短信</b>'],
			area: ['650px', '360px'],
			iframe: {src: url}
	   });
   }
   
   //订婴儿票
   function enterYepPage(id){
	   var url ="${ctx}/vedsb/jpddgl/jpkhdd/enterYepPage_"+id;
	   $.layer({
			type: 2,
			title: ['<b>填写婴儿信息</b>'],
			area: ['750px', '400px'],
			iframe: {src: url}
	   });
   }
   
   //出票前降舱
   function enterJcPage(id){
	   var url ="${ctx}/vedsb/jpddgl/jpkhdd/enterJcPage_"+id;
	   $.layer({
			type: 2,
			title: ['<b>舱位信息</b>'],
			area: ['600px', '380px'],
			iframe: {src: url}
	   });
   }
   
   //换PNR出票
   function enterChangePnrPage(id){
	   var url ="${ctx}/vedsb/jpddgl/jpkhdd/enterChangePnrPage_"+id;
	   $.layer({
			type: 2,
			title: ['<b>换PNR出票</b>'],
			area: ['500px', '200px'],
			iframe: {src: url}
	   });
   }
   
   //新增补差单
   function sqJpkhddBcd(ddbh){
   	   var url ="${ctx}/vedsb/jpbcd/jpbcd/toedit?ddbh="+ddbh+"&ywlxs=jpdd";
	   $.layer({
			type: 2,
			title: ['<b>新增补差单</b>'],
			area: ['400px', '235px'],
			iframe: {src: url}
	   });
   }
   
  	//签注
  	function getQzxx(ddbh){
  		var url ="${ctx}/vedsb/common/jpcommon/qzlist?ddbh="+ddbh;
		   $.layer({
				type: 2,
				title: ['签注'],
				area: ['745px', '600px'],
				iframe: {src: url}
		   });
  	}
</script>
</head>
<body>

<c:set var = "ywdh" value = "${jpkhdd.DDBH }"></c:set>
<div style="background: #fff; height:650px;width:100%">
 <div class="container">
 <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_center" style=" width:100%; background:#fff ;float:left">
              <form action="${ctx}/vedsb/jpddgl/jpkhdd/saveDetail" id="saveDetailForm" method="POST">
	              <input type="hidden" name="callback" value="flush()"/>
	              <input type="hidden" name="ddbh" value="${jpkhdd.DDBH}">
	              <input type="hidden" name="shbh" value="${entity.shbh}">
	              <input type="hidden" name="ywlx" value="国内机票">
	              
	               <!--基本信息 -->
	               <%@include file="jpkhdd/jpkhdd_jbxx.jsp"%>
	               <!--航程信息 -->
	               <%@include file="jpkhdd/jpkhdd_hcxx.jsp"%>
	               
	               <!--乘机人信息 -->
	               <%@include file="jpkhdd/jpkhdd_cjrxx.jsp"%>
	               
	               <!--收款信息-->
	               <%@include file="jpkhdd/jpkhdd_skxx.jsp"%>
	               
	               	<%-- 补差信息 --%>
					<c:if test="${not empty bcdList}">
						<%@include file="jpkhdd/jpkhdd_bcdxx.jsp"%>
					</c:if>
	               
	               <!--邮寄信息-->
	               <%@include file="jpkhdd/jpkhdd_yjxx.jsp"%>
	               
	               <!--备注信息-->
	               <%@include file="jpkhdd/jpkhdd_bzxx.jsp"%>
	               
	               <!--签注信息-->
	               <div class="box_top"><b class="pl15">签注信息</b></div>
	               <%@include file="/WEB-INF/views/common/jpddqz.jsp"%>
	               
	               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
						 <td></td>
						</tr>
	               </table>
               
	                <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="td_center">
								<input type="button" id="searchFormButton" name="button" value="保存" class="ext_btn ext_btn_submit" onclick="saveDetail()"/>
								<input type="button" id="searchFormButton" name="button" value="发送短信" class="ext_btn ext_btn_submit" onclick="enterSendSmsPage('${jpkhdd.DDBH}');"/>
								<input type="button" id="searchFormButton" name="button" value="订婴儿票" class="ext_btn ext_btn_submit" onclick="enterYepPage('${jpkhdd.DDBH}')"/>
								<c:if test="${jpkhdd.DDZT eq '0' or jpkhdd.DDZT eq '1'}">
									<input type="button" id="searchFormButton" name="button" value="出票前降舱" class="ext_btn ext_btn_submit" onclick="enterJcPage('${jpkhdd.DDBH}')"/>
									<input type="button" id="searchFormButton" name="button" value="换PNR出票" class="ext_btn ext_btn_submit"  onclick="enterChangePnrPage('${jpkhdd.DDBH}')"/>
								</c:if>
								<c:if test="${jpkhdd.DDZT eq '3'}">
									<input type="button" id="searchFormButton" name="button" value="申请补差单" class="ext_btn ext_btn_submit" onclick="sqJpkhddBcd('${jpkhdd.DDBH}')"/>
								</c:if>
								<input type="button" id="searchFormButton" name="button" value="关闭" class="ext_btn ext_btn_submit" onclick="javascript:window.close()"/>
							</td>
						</tr>
	               </table>
               </form>
            </div>
        </div>
     </div>
 </div>
 </div>
</body>
</html>

<%--
内置基本的datatype类型有： * | *6-16 | n | n6-16 | s | s6-18 | p | m | e | url
*：检测是否有输入，可以输入任何字符，不留空即可通过验证；
*6-16：检测是否为6到16位任意字符；
n：数字类型；
n6-16：6到16位数字；
s：字符串类型；
s6-18：6到18位字符串；
p：验证是否为邮政编码；
m：手机号码格式
e：email格式；
url：验证字符串是否为网址。
自定义datatype的名称，可以由字母、数字、下划线、中划线和*号组成。
形如"*6-16"的datatype，Validform会自动扩展，可以指定任意的数值范围。如内置基本类型有"*6-16"，那么你绑定datatype="*4-12"就表示4到12位任意字符。如果你自定义了一个datatype="zh2-4"，表示2到4位中文字符，那么datatype="zh2-6"就表示2到6位中文字符。

5.2版本之后，datatype支持规则累加或单选。用","分隔表示规则累加；用"|"分隔表示规则多选一，即只要符合其中一个规则就可以通过验证，绑定的规则会依次验证，只要验证通过，后面的规则就会忽略不再比较。如绑定datatype="m|e"，表示既可以填写手机号码，也能填写邮箱地址，如果知道填入的是手机号码，那么就不会再检测他是不是邮箱地址；datatype="zh,s2-4"，表示要符合自定义类型"zh"，也要符合规则"s2-4"。

注：
5.2.1版本之后，datatype支持：
直接绑定正则：如可用这样写datatype="/\w{3,6}/i"，要求是3到6位的字母，不区分大小写；
支持简单的逻辑运算：如datatype="m | e, *4-18 | /\w{3,6}/i | /^validform\.rjboy\.cn$/"，
这个表达式的意思是：可以是手机号码；或者是邮箱地址，但字符长度必须在4到18位；或者是3到6位的字母，不区分大小写；或者输入validform.rjboy.cn，区分大小写。这里","分隔相当于逻辑运算里的"&&"； "|"分隔相当于逻辑运算里的"||"；不支持括号运算。
nullmsg
当表单元素值为空时的提示信息，不绑定，默认提示"请填入信息！"。
如：nullmsg="请填写用户名！"

5.3版开始，对于没有绑定nullmsg的对象，会自动查找class为Validform_label下的文字作为提示文字:

如这样的html结构：
<span class="Validform_label">*用户名：</span><inputtype="text" val="" datatype="s" />

当这个文本框里没有输入时的出错信息就会是："请输入用户名！"
这里Validform_label跟input之间的位置关系，不一定是要同级关系，同级里没有找到的话，它还会在同级的子级、父级的同级、父级的同级的子级里查找。

sucmsg 5.3+
当表单元素通过验证时的提示信息，不绑定，默认提示"通过信息验证！"。
如：sucmsg="用户名还未被使用，可以注册！"

5.3版开始，也可以在实时验证返回的json数据里返回成功的提示文字，请查看附加属性ajaxurl的介绍。

errormsg
输入内容不能通过验证时的提示信息，默认提示"请输入正确信息！"。
如：errormsg="用户名必须是2到4位中文字符！"

5.3版开始，Validform可以根据你绑定的datatype智能的输出相应出错信息，具体介绍请查看tipmsg

ignore
绑定了ignore="ignore"的表单元素，在有输入时，会验证所填数据是否符合datatype所指定数据类型，
没有填写内容时则会忽略对它的验证；

recheck
表单里面经常需要检查两次密码输入是否一致，recheck就是用来指定需要比较的另外一个表单元素。
如：recheck="password1"，那么它就会拿当前元素的值跟该表单下，name为"password1"的元素比较。

tip
表单里经常有些文本框需要默认就显示一个灰色的提示文字，当获得焦点时提示文字消失，失去焦点时提示文字显示。tip属性就是用来实现这个效果。它通常和altercss搭配使用。
如<input type="text"value="默认提示文字" class="gray intxt"tip="默认提示文字" altercss="gray" />

altercss
它需要和tip属性配合使用，altercss指定的样式名，会在文本框获得焦点时被删除，没有输入内容而失去焦点时重新加上。

ajaxurl
指定ajax实时验证的后台文件的地址。
后台页面如valid.php文件中可以用 $_POST["param"] 接收到值，Ajax中会POST过来变量param和name。param是文本框的值，name是文本框的name属性。

5.2版本开始，可以在ajaxurl指定的url后绑定参数，如：
ajaxurl="valid.php?myparam1=value1&myparam2=value2"；

5.3之前的版本中，该文件输出的字符会作为错误信息显示在页面上，如果验证通过需输出小写字母"y"。
在5.3版中，实时验证的返回数据做了调整，须是含有status值的json数据！跟callback里的ajax返回数据格式统一，建议不再返回字符串"y"或"n"。目前这两种格式的数据都兼容。

注： 
如果ajax校验通过，会在该元素上绑定validform_valid值为true。可以通过设置该值来控制验证能不能通过，如验证码的验证，第一次验证通过后，不小心右点击了下验证码图片，验证码换了，但是仍然指示这个对象已经通过了验证，这时可以手动调整该值：$("#name")[0].validform_valid="false"。
怎样设置ajax的参数，具体可以查看Validform对象的config方法。
 参考资料：

http://blog.csdn.net/zhangdaiscott/article/details/26375043
正则表达式参考资料：http://deerchao.net/tutorials/regex/regex.htm
--%>