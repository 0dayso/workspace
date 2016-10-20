<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
	<title>票证发放添加</title>
	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
	 <script type="text/javascript">
 	PZKCZT = '${vfn:toJSON(vfn:dictList('PZKCZT'))}';
 	PZZT = '${vfn:toJSON(vfn:dictList('PZZT'))}';
 	</script>
	<script type="text/javascript">
	    var validator;
		$(function(){
			//默认加载列表
			$("#searchFormButton").click();
			//使用jQuery异步提交表单
			validator = $("#b_pc_th_form").Validform({
				tiptype:3,
				ajaxPost:true,
				callback : function(date) {
					if(date.code=='1'){
						alert(date.msg);
						window.location.href='${ctx}/vedsb/pzzx/pzff/viewpzffcx?title=2';
					}else{
						alert(date.msg);
						return false;
					}
				},
			});
			
			//获取部门下拉选的值
			var url="${ctx}/vedsb/shbm/shbm/query";
			$.ajax({
				type:"post",
				dataType:"json",
				url:url,
				success:function(result){
					if(result.CODE == '0'){
						var list=result.DATA;
						for(var i=0;i<list.length;i++){
							var $opt="<option value="+list[i].id+">"+list[i].mc+"</option>";
							$("#bmbh").append($opt);
						}
					}
				}
			});
			
		});
	</script>

<script type="text/javascript">
		//总数量
		var zsl=0;
		function click_check(startno,endno,ffsl,pztype,officeid,id,xh,isChecked,obj){	
			
			//判断表中未选中长度不为0时，取消全选
			if($("input[name='xcd_cb']").not("input:checked").length>0){
				 $("#checkAll").attr("checked",false);
			}
			
			//判断表中未选中长度，当为0时，给表头中的文本框加上选中事件
			if($("input[name='xcd_cb']").not("input:checked").length==0){
				 $("#checkAll").attr("checked",true);
			}
			//该input:checkbox的中使用的是onchange事件，因此利用事件自动离开和获取焦点而达到onclick事件
			this.blur();   
   			this.focus();
			var save_startno = $("#startno_" + xh);
			var save_endno = $("#endno_" + xh);
			var save_ffsl = $("#ffsl_" + xh);
			var save_pztype = $("#pztype_" + xh);
			var save_officeid = $("#officeid_" + xh);
			var save_id = $("#id_" + xh);
			if (isChecked) {
				if ($("input[name='xcd_cb']:checked").length == 1) {
					$("#tr_1").attr('id', "tr_" + xh);
					//设置起始码参数
					$("#startno_1").attr('id', "startno_" + xh);
					$("#startno_"+xh).attr("minvalue", startno);
					$("#startno_"+xh).attr("maxvalue", endno);
					//设置终止码参数
					$("#endno_1").attr('id', 'endno_' + xh);
					$("#endno_"+xh).attr("minvalue", startno);
					$("#endno_"+xh).attr("maxvalue", endno);
					//设置input的id值
					$("#ffsl_1").attr('id', 'ffsl_' + xh);
					$("#pztype_1").attr('id', 'pztype_' + xh);
					$("#officeid_1").attr('id', 'officeid_' + xh);
					$("#id_1").attr('id', 'id_' + xh);
					//获取文本框
					save_startno = $("#startno_" + xh);
			        save_endno = $("#endno_" + xh);
			        save_ffsl = $("#ffsl_" + xh);
			        save_pztype = $("#pztype_" + xh);
			        save_officeid = $("#officeid_" + xh);
			        save_id = $("#id_" + xh);
					//设置文本框的值
					save_startno.val(startno);
					save_endno.val(endno);
					save_ffsl.val(ffsl);
					save_pztype.val(pztype);
					save_officeid.val(officeid);
					save_id.val(id);
					zsl=ffsl;
				} else {
					zsl=$("#zsl").text()*1+ffsl*1;
					$.ajax({
						url : "viewff_xcd_tr?startno=" + startno + "&endno=" + endno + "&ffsl=" + ffsl + "&pztype=" + pztype+"&officeid=" + officeid +"&id=" + id + "&xh="+ xh,
						cache : false,
						success : function(html) {
						      var index=html.indexOf("<tr");
						      var end=html.indexOf("</tr>");
						      var tr_html=html.substring(index,end)+"</tr>";
						      var len = $("#tab tr").length;//获取tr的个数
							  $("#tab tr:eq(" + (len - 3) + ")").after(tr_html);
						}
					});
				}
			} else {
				if ($("input[name='xcd_cb']:checked").length >= 1) {//判断是否为该表单的第一行，如果为第一行不移除，只清空，其他清空
					$("#tr_" + xh).remove();
					zsl=$("#zsl").text()*1-ffsl*1;
				} else {
					$("#tr_" + xh).attr("id", "tr_1");
					$("#startno_" + xh).attr('id', "startno_1");
					$("#endno_" + xh).attr('id', "endno_1");
					$("#ffsl_" + xh).attr('id', "ffsl_1");
					$("#pztype_" + xh).attr('id', "pztype_1");
					$("#officeid_" + xh).attr('id', "officeid_1");
                    $("#id_" + xh).attr('id', "id_1");
                    //第一个input清空
					save_startno.val("");
					save_endno.val("");
					save_ffsl.val("");
					save_pztype.val("");
					save_officeid.val("");
					save_id.val("");
					zsl=0;
					$("#checkAll").attr("checked",false);
					
					//获取文本框对象
					save_startno = $("#startno_" + xh);
					save_endno = $("#endno_" + xh);
					save_ffsl = $("#ffsl_" + xh);
					save_pztype = $("#pztype_" + xh);
					save_officeid = $("#officeid_" + xh);
					save_id = $("#id_" + xh);
				}
			}
          $("#zsl").html(zsl);
		}
		//保存
		function toSave() {
			//获取checkbox选中的个数
			var flag = $("input[name='xcd_cb']:checked").length;
			//为0表示表单中没有数据
			if (flag == 0) {
				alert('注意：您还没有选定需要退回的票号！');
				return false;
			}
			//备注信息过长
			if ($("#b_pc_th_form").val().length > 100) {
				alert("注意:备注限定中文100字!");
				return false;
			}
			//验证框架
			validator.submitForm(false);
		}
		//文本框的改变事件
		function calcnum(obj) {
			$tr = $(obj).parents("tr");
			$startno = $tr.find("input[name='startno']");
			$endno = $tr.find("input[name='endno']");
			$ffsl = $tr.find("input[name='ffsl']");
			$ffsl.val($endno.val() - $startno.val()+1);
			var zsl=0;
			$("input[name='ffsl']").each(function(){  
				zsl+=$(this).val()*1;
			});
			$("#zsl").html(zsl);
		}
		
		//全选操作
		
	function checkAll(checkallbox) {
		if (checkallbox.checked) {
			$("input[name='xcd_cb']").not("input:checked").each(function() {
				$(this).attr("checked", true);
				$(this).change();
			});
		} else {
			$('input[type="checkbox"][name="xcd_cb"]').each(function() {
				$(this).attr("checked", false);
				$(this).change();
			});
		}
	}
</script>
<!-- 模板 -->
<script id="tpl_list_table" type="text/html">
	 <table  class="list_table" style="width:1024px;" border="0" cellpadding="0" cellspacing="0" >
       <tr>
		<th width="5">
			<input type="checkbox" name="checkallbox" id="checkAll" onclick="checkAll(this);">
		</th>
        <th width="5">序号</th>
        <th width="17">起始号码</th>
        <th width="20">终止号码</th>
		<th width="10">数量</th>
		<th width="10">剩余数量</th>
		<th width="10">状态</th>
		<th width="25">OFFICEID</th>
		<th width="30">所属部门</th>
		<th width="30">操作日期</th>
       </tr>   
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
		<td class="td_center">
			<input type="checkbox" name="xcd_cb" onchange="click_check({{ d.list[i].startno }},{{ d.list[i].endno }},{{ d.list[i].sl }},{{ d.list[i].pztype }},'{{ d.list[i].officeid }}',{{ d.list[i].id }},{{ i+1 }},this.checked,this);" />
		</td>
        <td class="td_center">{{ i+1 }}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].startno) }}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].endno) }}</td>
		<td class="td_right">{{ $.nullToEmpty(d.list[i].sl) }}</td>
		<td class="td_right">{{ $.nullToEmpty(d.list[i].sysl) }}</td>
		<td class="td_center">{{$.findJson(PZKCZT,d.list[i].pzzt).mc}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].officeid) }}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.CZYHBH.bmmc )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].czDatetime,'yyyy-MM-dd HH:mm')}}</td>
       </tr>
	   {{# } }}
	 </table>
   </script>
</head>
<body>
   <!--页签 -->
<%@include file="pzff_title.jsp"%>

<div class="container_clear">
 	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/pzzx/pzff/queryFromKC" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
              				<table  class="table01" border="0" cellpadding="0" cellspacing="0"> 
              				<!-- 第1行 -->
              				<tr>
              						<td>票证类型</td>
              						<td>
						            	<select name="pztype"  class="select" style="width:100px">
							               				<c:forEach items="${vfc:getVeclassLb('7202')}" var="oneLx">
														    <c:if test="${oneLx.id ne '7202'}">
																<option value="${oneLx.id }">${oneLx.mc }</option>
															</c:if>
														</c:forEach>
							            </select>
							            &nbsp;<font color="red">*</font>
						            <td>
              					  <td class="text_right">
              							起始号码：
              						</td>
              						<td class="text_left">
              							<input type="text"  class="input-text lh25" id="startno" name="startno" style="width:90px" value="" size="10">
              						</td>    
              						<td class="text_right">
              							终止号码：
              						</td>
              						<td class="text_left">
              							<input type="text"  class="input-text lh25" id="endno" name="endno" style="width:90px" value="" size="10">
              						</td>    
              						<td class="text_right">
              							状态：
              						</td>
              						<td>
					                   <select id="pzzt" name="pzzt" class="select">         
					                       <option value="" ${empty param.pzzt ? 'seletced' : '' }>==请选择==</option>
					                       <option value="0" ${param.pzzt eq '0' ? 'selected' : ''  }>入库</option>
					                       <option value="1" ${param.pzzt eq '1' ? 'selected' : ''  }>未使用</option>
					                  </select>
					                </td>
					                <td class="text_right">
              							OFFICE号：
              						</td>
              						<td class="text_left">
              							<select id="officeid" name="officeid" datatype="*" class="select" style="width:100px">
									 		<option value="">请选择</option> 
									   		<c:forEach items="${jpfn:officeIdList(BUSER.shbh)}" var="officeid">
									   			<option value="${officeid}" >${officeid}</option>
						                   	</c:forEach>
						                 </select>
              						</td>
              						<td colspan="2" class="text_right">
										<input type="button" id="searchFormButton" name="button" value=" 查 询  "  class="ext_btn ext_btn_submit" />
									</td>
              					</tr>
              				</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
       <div  class="mt10">
      	<div id="list_table_page1">
        	<!-- 分页  ID不能修改-->
        </div>
        <div class="box span10 oh" id="list_table">
             <!-- 显示列表 ID不能修改 -->
        </div>
        <br>
        <div id="list_table_page">
        <!-- 分页  ID不能修改-->
        </div>
     </div>
    	<!--  保存区 -->
		<div id="save_div">
			<form action="${ctx}/vedsb/pzzx/pzff/saveToJpPzOut" method="post"
				id="b_pc_th_form" name="b_pc_th_form">
				<div style="background-color:#fff;">
					<table id="tab" style="width:800px;border-collapse:   separate;   border-spacing:   10px;">
					<col style="width: 80px;" />
					<col style="width: 240px;" />
					<col style="width: 80px;" />
					<col style="width: 240px;" />
					<col style="width: 80px;" />
					<col style="width: 80px;" />
					<tr>
						<td colspan="6" style="color:#FF0000">
							&nbsp;选定页面上边 
							<input type="checkbox" name="123" style="vertical-align:middle;" checked disabled="disabled">复选框，选取发放单号段。可以选择多个号段一起发放。
						</td>
					</tr>
					<tr id="tr">
						<td>发放部门：</td>
						<td>
							 <select id="bmbh" name="bmbh" datatype="*" style="width: 100px;height: 24px;">
							 	<option value="">请选择</option>
							 </select>
							<font color="red">*</font>
						</td>
						
					</tr>
					<tr id="tr_1">
						<td>起始单号：</td>
						<td>
							<input type="text" id="startno_1"   class="input-text lh25" datatype="integer,maxvalue,minvalue" name="startno" onblur="calcnum(this)"> 
							<font color="red">*</font>
						</td>
						<td>终止单号：</td>
						<td>
							<input type="text" id="endno_1"   class="input-text lh25" datatype="integer,maxvalue,minvalue" name="endno"onblur="calcnum(this)"> 
							<font color="red">*</font>
						</td>
						<td>数 &nbsp;量：</td>
						<td><input type="text"   class="input-text lh25" id="ffsl_1" name="ffsl"value="0" size="2" readOnly="true"> 
							<input type="hidden" name="pztype" value="" id="pztype_1" />
							<input type="hidden" name="officeid" value="" id="officeid_1" />
							<input type="hidden" name="id" value="" id="id_1" />
						</td>
					</tr>
					<tr>
						<td>备 &nbsp;注：</td>
						<td colspan="3">
							<input   class="input-text lh25" type="text" name="bzbz" value="" size="66">
						</td>
						<td>
							总数量：
						</td>
						<td>
							<span id="zsl">0</span>
						</td>
					</tr>
					<tr>
						<td>&nbsp;操作员：</td>
						<td>${BUSER.bh}
						</td>
						<td>&nbsp;所在部门：</td>
						<td>${BUSER.bmmc}</td>
					</tr>
				</table>
				</div>
				<br>
				<table width="98%" border="0" cellspacing="2" cellpadding="0"align="center">
					<tr>
						<td><div align="center">
								<input type="button" name="" class="ext_btn ext_btn_submit"value="发放" onclick="toSave();"> 
								<input type="button"name="" class="ext_btn ext_btn_submit" value="取 消" onClick="window.history.back();">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>