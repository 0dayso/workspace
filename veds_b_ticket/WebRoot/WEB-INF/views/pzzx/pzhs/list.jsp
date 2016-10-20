<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
	<head>
		<title>行程单回收</title>
		<!-- 日期选择控件  -->
		<script type="text/javascript"src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			PZZT = '${vfn:toJSON(vfn:dictList('PZZT'))}';
			function selChange(obj){
				//zf作废  hs回收
				if($(obj).val()=='hs'){
					$("#td_ksrq").html("回收日始：");
					$("#td_jsrq").html("回收日止：");
				}else{
					$("#td_ksrq").html("作废日始：");
					$("#td_jsrq").html("作废日止：");
				}
			}
			//  回收/取消回收
			function toHs(lx){
				var $check_xcd=$("input[name='check_xcd']:checked");
				if($check_xcd.length==0){
					alert("请先选择一条票证记录票证！");
					return;
				}
				var flag=true;
				var pzzt_arr="";
				var xcdNo_arr="";
				var pztype_arr="";
				//遍历选中name为check_xcd的checkbox  
				$('input[type="checkbox"][name="check_xcd"]:checked').each(function() {
					var xcdNo=$(this).val().split(",")[0];
					var pzzt=$(this).val().split(",")[1];
					var pztype=$(this).val().split(",")[2];
					// 1为回收  2为取消回收
					if(lx=="1"){
		       	    	if(pzzt!="3" && pzzt!="5" && pzzt!="7"){
		       	    		alert("您所选择的票证中有已经回收的，不能再次回收！");
		       	    		flag=false;
		       	   			return false; //跳出循环
		       	   		}
	       	    	}else{
		       	     	if(pzzt!="4" && pzzt!="6" && pzzt!="8"){
		       	     		alert("您所选择的票证中有未回收的，不能取消回收！");
		       	     		flag=false;
		       	   			return false; //跳出循环
		       	   		}
		       	   	}
		       	   	 if(pzzt_arr==""){//pzzt_arr未赋值
			       	       pzzt_arr=pzzt;
			       	   	   xcdNo_arr=xcdNo;
			       	   	   pztype_arr=pztype;
			       	  }else{
				       	   pzzt_arr+=","+pzzt;
				       	   xcdNo_arr+=","+xcdNo;
				       	   pztype_arr+=","+pztype;
			       	  }
				});
				if(flag){
					var str="";
					var ts="";//操作后的成功提示
					if(lx=="1"){
		  				str="您确定回收票证吗?";
		  				ts="回收成功";
		  			}else{
		  				str="您确定取消回收票证吗?";
		  				ts="取消成功";
		  			}
					if(window.confirm(str)){
						var url="${ctx}/vedsb/pzzx/pzhs/hs";
						$.ajax({ 
					       type: "post", 
					       url: url, 
					       data: {"pzzt_arr":pzzt_arr,"xcdNo_arr":xcdNo_arr,"pztype_arr":pztype_arr}, 
					        success: function(result){
					         	 if(result.CODE=='0'){
					         	 	alert(ts);
					         	 	window.location.reload();//刷新
					         	 }
					         	 if(result.CODE=='-1'){
					         	 	alert(result.MSG);
					         	 }
					        } 
						});
					}
				}
			}
			//选中所有
			function checkAll(checkallbox) {
				if (checkallbox.checked) {
					$('input[type="checkbox"][name="check_xcd"]').each(function() {
						$(this).attr("checked", true);
					});
				} else {
					$('input[type="checkbox"][name="check_xcd"]').each(function() {
						$(this).attr("checked", false);
					});
				}
			}
		</script>
		<!-- 模板 -->
<script id="tpl_list_table" type="text/html">
	 <table class="list_table" border="0" cellpadding="0" cellspacing="0">
       <tr>
		<th width="5"><input type="checkbox" id="checkAll" name="checkallbox" onclick="checkAll(this);"></th>
        <th width="10">序号</th>
		<th width="20">票证类型</th>
		<th width="30">票号</th>
        <th width="30">行程单号</th>
        <th width="20">OFFICEID</th>
		<th width="35">发放部门</th>
		<th width="25">发放人</th>
		<th width="40">发放时间</th>
		<th width="50">状态</th>
		<th width="40">经办部门</th>
		<th width="30">经办人</th>
		<th width="45">经办时间</th>
		<th width="45">作废部门</th>
		<th width="30">作废人</th>
		<th width="50">作废时间</th>
       </tr> 
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
		<td class="td_center">
			<input type="checkbox" name="check_xcd" value="{{ $.nullToEmpty(d.list[i].xcdNo) }},{{ $.nullToEmpty(d.list[i].pzzt) }},{{ $.nullToEmpty(d.list[i].pztype) }}"/>
		</td>
        <td class="td_center">{{ i+1 }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ex.PZTYPE.mc) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].tkno )}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].xcdNo )}}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].officeid )}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.OUTYHBH.bmmc )}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.OUTYHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].outDatetime,'yyyy-MM-dd HH:mm')}}</td>
		<td class="td_left">
			{{#  var zt=$.nullToEmpty(d.list[i].pzzt);
				if(zt=='0'){
			}}
				在库
			{{#  }  if(zt=='1'){  }}
				未使用
			{{# } if(zt=='2'){ }}
				出票
			{{# } if(zt=='3'){ }}
				未创建已作废未回收
			{{# } if(zt=='4'){ }}
				未创建已作废已回收 
			{{# } if(zt=='5'){ }}
				已创建已作废未回收 
			{{# } if(zt=='6'){ }}
				已创建已作废已回收
			{{# } if(zt=='7'){ }}
				退废票已作废未回收 
			{{# } if(zt=='8'){ }}
				退废票已作废已回收 
			{{# } if(zt=='9'){ }}
				报废
			{{# } }}
		</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.JBYHBH.bmmc)}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.JBYHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].jbDatetime,'yyyy-MM-dd HH:mm')}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.TFYHBH.bmmc )}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.TFYHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].tfDatetime,'yyyy-MM-dd HH:mm')}}</td>
       </tr>
	   {{# } }}
	 </table>
   </script>
	</head>
	<body>
		<div class="container_clear">
			<div id="search_bar" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_center pt10 pb10">
							<form id="searchForm" action="${ctx}/vedsb/pzzx/pzhs/pzhs_list" name="searchForm"  method="post">
								<input type="hidden" name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" /> 
								<input type="hidden" name="pageNum" value="${param.pageNum }" id="pageNum" /> 
								<input type="hidden" name="pageSize" value="10" id="pageSize" />
								<table class="table01">
									<tr>
										<td>
											&nbsp;日期条件：
										</td>
										<td>
											<select name="rqtj" style="width:95px;height:24px;" onchange="selChange(this);">
												<option value="zf" selected="selected">作废时间</option>
												<option value="hs">回收时间</option>
											</select>
										</td>
										<td id="td_ksrq">
											作废日始：
										</td>
										<td>
											<input type="text" name="ksrq" style="width:95px;height:24px;" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}"
											class="input-text Wdate" size="11" onClick="WdatePicker()" />
										</td>
										<td id="td_jsrq">
											作废日止：
										</td>
										<td>
											<input type="text" name="jsrq" style="width:95px;"  value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}"
											class="input-text Wdate"  onClick="WdatePicker()" />
										</td>
										<td>票证类型：</td>
										<td>
											 <select name="pztype" class="select srk" datatype="*" style="width: 95px;heigth:24px;"> 
											   <c:forEach items="${vfc:getVeclassLb('7202')}" var="onecgly">
											   		<c:if test="${onecgly.parid ne 'none'}">
								                       <option value="${onecgly.id}" ${param.pztype eq onecgly.mc ? 'selected':'' }>${onecgly.mc}</option>
								                    </c:if>
							                    </c:forEach>
						                     </select>
										</td>
									</tr>
									<tr>
										<td>&nbsp;类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
									      <td>
									      	<select name="pzzt" style="width:95px;height:24px;" class="input1" >
									      		<option value="whs" ${(empty param.pzzt or param.pzzt eq 'zf') ? 'selected' : '' }>未回收</option>
									      		<option value="hs" ${param.pzzt eq 'hs' ? 'selected' : '' }>已回收</option>
									      		<option value="qb" ${param.pzzt eq 'qb' ? 'selected' : '' }>全部</option>
									      	</select>
									      </td>
									      <td>
									      	票&nbsp;&nbsp;&nbsp;&nbsp;号：
									      </td>
									      <td>
									      	<input type="text" name="tkNo" style="width:95px;" class="input-text lh25"/>
									      </td>
									      <td>
									      	行程单号：
									      </td>
									      <td>
									      	<input type="text" name="xcdNo" style="width:95px;" class="input-text lh25"/>
									      </td>
									      <td>
									      	发放部门：
									      </td>
									      <td>
									      	<input type="text" name="bmbh" style="width:95px;" class="input-text lh25" />
									      </td>
									      <td></td>
									      <td align="right">
											<input type="button" value=" 查 询 "class="ext_btn ext_btn_submit" id="searchFormButton">
										  </td>
										  
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div style="position:absolute;top: 105px;">
				<input type="button" value="回        收" class="ext_btn ext_btn_submit" onclick="toHs('1');">
				<input type="button" value="取消回收" class="ext_btn ext_btn_submit" onclick="toHs('2');">
			</div>
			<div><br></div>
			<div class="mt10">
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
		</div>
	</body>
</html>