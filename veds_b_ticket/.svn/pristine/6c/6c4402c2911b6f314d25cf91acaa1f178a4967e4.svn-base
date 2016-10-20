<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	$(function(){
		if("${param.id }" && $("#czykz").val()){
			$("#sfsyzzh").attr("checked","checked");
			$("#czykz").removeAttr("disabled");
		}
		
		var url="${ctx}/vedsb/b2bsz/jpb2bzfzh/queryZfkm";
		$.ajax({
     		type : "post",
     		url : url,
     		success:function(result){
     			if(result.CODE == '0' ){
     				var list=result.DATA;
     				for(var i=0;i<list.length;i++){
     					var zfxx7='${param.zfxx7}';
     					var sel=list[i].kmbh == zfxx7 ? "selected": "";
	           	 		var $opt="<option value="+list[i].kmbh+" "+sel+">"+list[i].kmmc+"</option>";
	           	 		$("#zfkm").append($opt);
	           	 	}
     			}
     		}
		});
		
		
	})
	
	//新增,修改保存
	function save(){
		var zfzh = $("#zfzh").val();
		var zfzzh = $("#zfzzh").val();
		var sfsyzzh = $("#sfsyzzh").val();
		var zfxx7=$("#zfkm").val();
		if(!zfzh){
			$("#error").html("支付账号不能为空!");
			return null;
		}
		//判断子账号是否勾选
		if($("#sfsyzzh").is(":checked")){
			if(!zfzzh){
				layer.msg("子账号账号不能为空!",2,0);
				return null;
			}
		}
		var url="${ctx}/vedsb/b2bsz/jpb2bzfzh/zfzhSave?zfzh="+zfzh+"&zfzzh="+zfzzh+"&zfxx7="+zfxx7+"&sfsyzzh="+sfsyzzh+"&zflx=${param.zflx }&id=${param.id }";
		$.ajax({
			type:"post",
			url:url,
			success:function(result){
				if("-1" == result){
					layer.msg("账号保存失败！",2,0);
				}else if("-2" == result){
					layer.msg("账号已存在！",2,0);
				}else{
					layer.msg("账号保存成功！",2,1);
				}
				setTimeout(function(){
					window.location.href="${ctx}/vedsb/b2bsz/jpb2bzfzh/viewlist?zflx=${param.zflx }"
				},800)
			}
		})
	}
	
	//删除
	function toDel(id){
		$.layer({
		   	 area: ['400px', '150px'],
		        dialog: {
		            msg: '确定删除该行？',
		            btns: 2,
		            type: 4,
		            btn: ['确定','取消'],
		            yes: function(){
		            	layer.msg('删除成功', 1, 2);
		            	location.href="${ctx}/vedsb/b2bsz/jpb2bzfzh/delete_"+id+"?turningUrl=${ctx}/vedsb/b2bsz/jpb2bzfzh/viewlist?zflx=${param.zflx }";
		            }, no: function(){
		                layer.msg('放弃删除', 1, 3);
		            }
		     }
	    });
	}
	
	function setCzykz(ts){
		if(ts.checked){
			$("#sfsyzzh").val("1");
	 		$("#zfzzh").removeAttr("disabled");
	 	}else{
	 		$("#sfsyzzh").val("0");
	 	    $("#zfzzh").attr("disabled","disabled");
	 	}
	}
	
	function chs(ts){
		if(ts.value == ""){
			$("#error").html("支付账号不能为空!");
		}else{
			$("#error").html("");
		}
	}
	
	//签约
	function changeQY(id,zflx){
		if(zflx != '7'){
			var ii = layer.load('系统正在处理您的操作,请稍候!');
			var url="${ctx}/vedsb/b2bsz/jpb2bzfzh/qy";  // !=7
			$.ajax({
				type:"post",
				data:{"id":id},
				dataType:"json",
				url:url,
				success:function(result){
					layer.close(ii);
					if( result.CODE == '0' ){
						var dialog=$.layer({
					        area: ['250px', '150px'],
					        dialog: {
						        msg: result.MSG,
						        btns: 2,                    
						        type: 4,
						        btn: ['确定','取消'],
						        yes: function(){
						        	layer.close(dialog);
						        	parent.hkgsmainIframe.window.location.reload();
						        	window.open(result.URL);
								}
							}
						});
					}
				}
			});
		}else{
			$.layer({
				type: 2,
			   	title: ['消息'],
			   	area: ['460px', '180px'],
			   	iframe: {src: "${ctx}/vedsb/b2bsz/jpb2bzfzh/viewetrip?id="+id}
			});
		}
	}
	
	//修改账号状态
	function toUpdate(id,sfkq){
		if(sfkq == '1'){
			var msg = "开始使用";
		}else{
			var msg = "停止使用";
		}
		$.layer({
		   	 area: ['400px', '150px'],
		        dialog: {
		            msg: "您确定["+msg+"]这个账户？",
		            btns: 2,
		            type: 4,
		            btn: ['确定','取消'],
		            yes: function(){
		            	location.href="${ctx}/vedsb/b2bsz/jpb2bzfzh/updateToSfqy?id="+id+"&sfkq="+sfkq+"&zflx=${param.zflx }"
		            },no: function(){
		                
		            }
		     }
	    });
	}
</script>
</head>
<body>
<div class="container_clear">
	<input type="hidden"  name="id" value="${param.id }"/>
	<input type="hidden"  name="zflx" value="${param.zflx }"/>
	<input type="hidden" name="orderBy" value="zfzh"/>
    <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<thead>
			<th colspan="2" align="left">
				<span style="font-size:16px;color: #0858c7">添加或修改支付账号</span>
			</th>
		</thead>
		<tbody>
			 <tr>
           		<td class="td_right">本账号对应支付科目：</td>
           		<td class="td_left">
           			<select id="zfkm" name="zfxx7" style="width:120px;">
           				<option value="">=请选择=</option>
           			</select>
           		</td>
           	</tr>
		    <tr>
				<td class="td_right" style="width: 10%;">支付账号：</td>
             	<td class="td_left" style="width: 55%;">
             		<c:if test="${not empty param.zfzh }"><b>${param.zfzh }</b>
						<input type="hidden" id="zfzh" name="zfzh" value="${param.zfzh }" style="width:30%;" />
					</c:if>
					<c:if test="${empty param.zfzh }">
						<input id="zfzh" name="zfzh" value="${param.zfzh }" onchange="chs(this);" style="width:30%;" /> <font color="red">* <span id="error"></span></font>
					</c:if>
             	</td>
             </tr>
             <c:if test="${not empty map }" >
	             <tr>
	              	<td class="td_right">支付类型：</td>
	              	<td class="td_left">
	              		<select name="zfxx1" id="zfxx1">
	              			<c:forEach var="item" items="${map}" >
								<option value="${item.key }" ${item.key eq param.zfxx1 ? 'selected' : '' }>${item.value.mc}</option>
							</c:forEach>
						</select>
	              	</td>
	              </tr>
              </c:if>
           
            <c:if test="${param.zflx eq '3' || param.zflx eq '4'}">
     			<tr>
					<td class="td_right">支付密码：</td>
					<td class="td_left">
						<input type="password" id="zfxx2" name="zfxx2" value="${param.zfxx2}" />
						<br>交易密码加密保存，如果要修改请输入原始密码
					</td>
				</tr>
     		</c:if>
            <c:if test="${param.zflx eq '1' || param.zflx eq '2' || param.zflx eq '7'}">
            	<tr>
              		<td class="td_right">操作员子账号：</td>
              		<td class="td_left">
              			<input id="zfzzh" name="zfzzh" value="${param.zfzzh }" style="width:30%;" ${param.sfsyzzh eq '1' ? '' : 'disabled' }>
			 		    <label for="sfzzh"><input type="checkbox" id="sfsyzzh" name="sfsyzzh" value="${empty param.sfsyzzh ? '0' : param.sfsyzzh }" onclick="setCzykz(this)" ${param.sfsyzzh eq '1' ? 'checked' : '' }> 使用操作员子账号</label>
			 		  	<br>同一个总支付账号可以授权个多个操作员用， 必须在支付公司后台设置相应的操作员名.
              		</td>
              	</tr>
           	</c:if>
			<tr>
				<td colspan="2" align="center">
					<input type="button" onclick="save();" class="ext_btn ext_btn_submit" value="保存">
				</td>
			</tr>
		</tbody>
	</table>
	<!-- 支付信息列表 -->
	<%@include file="list_table.jsp" %>
</div>
</body>
</html>