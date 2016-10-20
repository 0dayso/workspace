<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title>自动退废规则设置</title>
    <script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
    <script type="text/javascript" src="${ctx}/static/core/data/gn_city.js?v=${VERSION}"></script>
	<script type="text/javascript" src="${ctx}/static/core/data/gj_city.js?v=${VERSION}"></script>
    <style>
	.td_val{
		text-align:left; 
	}
	.td_name{
		text-align:right; 
	}
</style>

<script>
$(function(){	
   	//航司控件
    $("#gn_hkgs_m").autocompleteGnHkgs("qhkgs");
});
	function flush(){
		$("#searchFormButton").click();
	}//刷新表单
	
function showCity(nameId, valueId){
    $("#"+nameId).autocompleteGnCity(valueId);
}

function clearValue(obj,hkgs_id){
	 if(obj.value == ""){
	  $("#"+hkgs_id).val("");
	 }
}
//切换页面
function toSearch(type){
	document.getElementById("tpfptype").value=type;
	flush();
  	$("ul li").removeClass("currentBtn");
  	$("#lx"+type).addClass("currentBtn");
}
//进入编辑页面
function enterEditPage(flag){
	var type=document.getElementById("tpfptype").value;
	var url = "${ctx}/vedsb/cpsz/jpzdtfpgzsz/enterEditPage?tpfp="+type;
	$.layer({
		type: 2,
		title: [type ==1 ? "<b>增加自动退票规则页面</b>" : "<b>增加自动废票规则页面</b>"],
		area: ['730px', '600px'],
		iframe: {src: url}
    });
}

//编辑和复制
function editTfp(id,flag){
	var type=document.getElementById("tpfptype").value;
	var url = "${ctx}/vedsb/cpsz/jpzdtfpgzsz/enterEditPage?tpfp="+type+"&id="+id+"&flag="+flag;
	var title="<b>查看</b>";
	if(flag == 1){
		title="<b>编辑</b>";
	}else if(flag == 2){
		title="<b>复制</b>";
	}
	$.layer({
		type: 2,
		id:'editTfp',
		title: [type == 1 ? title+"<b>自动退票规则页面</b>": title+"<b>自动废票规则页面</b>"],
		area: ['710px', flag == 3 ? '520px' : '710px'],
		iframe: {src: url}
    });
}
//查询删除的记录
function queryDelete(op){
	if(op.checked){
		document.getElementById("isdelid").value=1;
	}else{
		document.getElementById("isdelid").value=0;
	}
	$("#searchFormButton").click();
}
//点击全选
function checkAll(checkallbox){
    var ischeck=checkallbox.checked;
	$('input[type="checkbox"][name="onechkx"]').each(function(){
		   $(this).attr("checked",ischeck);
	  });
}
//点击复选框后,检查是否是全选,取选中的复选框的值
function ifCheckAll(onechkx){
   var chkxArr = $('input[type="checkbox"][name="onechkx"]');
   var total = chkxArr.length;
   var checkedLen = 0;
   var ids="";
   var zts="";
    $(chkxArr).each(function(){
	   if($(this).prop("checked")){
		   checkedLen++;
		   ids+=$(this).val()+",";
		   zts+=$(this).attr("zt")+",";
	   }
   });
   if(total==checkedLen){
	   $("#checkallbox").attr("checked",true);
   }else{
	   $("#checkallbox").attr("checked",false);
   }
   document.getElementById("choosecount").value=checkedLen;
   document.getElementById("chooseids").value=ids.substring(0,ids.length-1);
   document.getElementById("choosezts").value=zts.substring(0,zts.length-1);
 }
 	/**删除退废票*/
	function delTfp(ids,zt){
		var zts=document.getElementById("choosezts").value;
		if(!zts&&!ids){
			layer.msg("请先选择要删除的退废票规则！",2,0);
			return;
		}
		var isbreak=false;
		 if(!ids){//批量删除
		 	ids=document.getElementById("chooseids").value;
		 	var ztarr=zts.split(",");
			 for(var i=0;i<ztarr.length;i++){
			 	if(ztarr[i]==1){
			 		isbreak=true;
			 		layer.msg("只能删除禁用状态的退废票规则！",2,0);
			 		break;
			 	}
			 }
		 }else{
		 	if(zt!=0){
		 		layer.msg("只能删除禁用状态的退废票规则！",2,0);
		 		isbreak=true;
		 		return ;
		 	}
		 }
		 
		  if(isbreak){
			 	return;
			 }
		 var url="${ctx}/vedsb/cpsz/jpzdtfpgzsz/deleteTfp";
         $.layer({
        area: ['260px', '150px'],
        dialog: {
        msg: '确定删除退废票规则，是否继续？',
        btns: 2,                    
        type: 4,
        btn: ['确定','取消'],
        yes: function(){
        	var ii = layer.load('系统正在处理您的操作,请稍候!');
  			$.ajax({
        	 		type:"post",
  					url:url,
  					data:{"ids":ids},
  					success:function(result){
  						layer.close(ii);
  						if("1" == result){
  							layer.msg("删除退废票规则成功！",2,1);
  							flush();
  						}else{
  							layer.msg("删除退废票规则失败！",2,0);
  						}
  					}
        	 	});
        }, no: function(){
            layer.msg('放弃删除退废票规则！', 2, 3);
        }
     }});
	}

/*  	//批量取得复选框的值
   function getStr(name){
		var str="";
		$('input[type="checkbox"][name="'+name+'"]').each(function(){
			if($(this).prop("checked")){
				str += $(this).val()+"/";
			}
		});
		if(str){
			str = str.substring(0,str.length-1);
		}
		return str;<img src="${ctx}/static/images/tffa/czlog.png">
  } */
  
  function changeZt(ids,flag){
  	var zts=document.getElementById("choosezts").value;
  	var show="";
	if(flag==1){
		if(ids){
			show="启用退废票规则";
		}else{
			show="批量启用退废票规则";
		}
	}else{
		if(ids){
			show="禁用退废票规则";
		}else{
			show="批量禁用退废票规则";
		}
	}
		if(!zts&&!ids){
			if(flag==1){
				layer.msg("请先选择要启用的退废票规则！",2,0);
			}
			if(flag==0){
				layer.msg("请先选择要禁用的退废票规则！",2,0);
			}
			return;
		}else{
			var isbreak=false;
			if(!ids){
				var ztarr=zts.split(",");
				ids=document.getElementById("chooseids").value;
				if(flag==1){
					for(var i=0;i<ztarr.length;i++){
						if(ztarr[i]!=0){
							isbreak=true;
							layer.msg("只能启用已禁用状态的退废票规则！",2,0);
							break;
						}
					}
				}else if(flag==0){
					for(var i=0;i<ztarr.length;i++){
						if(ztarr[i]!=1){
							isbreak=true;
							layer.msg("只能禁用已启用的退废票规则！",2,0);
							break;
						}
					}
				}
				
			}
			if(isbreak){
				return;
			}
			$.layer({
		        area: ['250px', '150px'],
		        dialog: {
		        msg: '确定将'+show+'，是否继续？',
		        btns: 2,                    
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	var ii = layer.load('系统正在处理您的操作,请稍候!');
					$.ajax({
		        	 		type:"post",
		  					url:"${ctx}/vedsb/cpsz/jpzdtfpgzsz/updateZt",
		  					data:{"ids":ids,"zt":flag},
		  					success:function(result){
		  						layer.close(ii);
		  						if("1" == result){
		  							if(flag==1){
		  								layer.msg("启用退废票规则成功！",2,1);
		  							}else{
		  								layer.msg("禁用退废票规则成功！",2,1);
		  							}
		  							flush();
		  						}else{
		  							if(flag==1){
		  								layer.msg("启用退废票规则失败！",2,0);
		  							}else{
		  								layer.msg("禁用退废票规则失败！",2,0);
		  							}
		  						}
		  					}
		        	 	});
		        }, no: function(){
		            layer.msg('放弃'+show+"!", 2, 3);
		        }
		     }});
			
			
		}
		
  }
  
   //查看异动日志
	function enterLogPage(gzid){
		var url="${ctx}/vedsb/cpsz/jpzdtfpgzszczrz/enterLogPage_"+gzid;
		$.layer({
			type: 2,
			title: ['<b>自动退废票规则设置异动日志</b>'],
			area: ['650px', '360px'],
			iframe: {src: url}
	    });
	}
</script>

<script id="tpl_list_table" type="text/html">
<div>
	<table border="0" cellpadding="0" cellspacing="0" class="list_table" style="width:100%;">
	<tr>
		<th width="2%"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
		<th width="3%">序号</th>
		<th width="14%">操作</th>
		<th width="12%">规则名称</th>
		<th width="17%">航空公司</th>
		<th width="11%">舱位</th>
		<th width="17%">出发城市</th>
		<th width="17%">到达城市</th>
		<th width="100px">乘机日起止</th>
		<th width="16%">适用对象</th>
		<th width="8%">提前天数起止</th>
		<th width="7%">起飞时间<br>限制(小时)</th>
		<th width="6%">操作人</th>
		<th width="5%">状态</th>
	</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
				<td align="center"><input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" zt="{{ $.nullToEmpty(d.list[i].ZT)}}" value="{{ $.nullToEmpty(d.list[i].ID)}}"></th>
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
				{{# if(d.list[i].ISDEL!=1){ }}
					<a href="javascript:void(0);" onclick="editTfp('{{ d.list[i].ID}}',1);">编辑</a>
					<a href="javascript:void(0);" onclick="editTfp('{{ d.list[i].ID}}',2);">复制</a>
					<a href="javascript:void(0);" onclick="delTfp('{{ d.list[i].ID}}','{{ d.list[i].ZT}}');">删除</a>
					{{# if(d.list[i].ZT==0){ }}
						<a href="javascript:void(0);" onclick="changeZt('{{ d.list[i].ID}}',1);">启用</a>
					{{# } }}
					{{# if(d.list[i].ZT==1){ }}
						<a href="javascript:void(0);" onclick="changeZt('{{ d.list[i].ID}}',0);">禁用</a>
					{{# } }}
				{{# } }}
				{{# if(d.list[i].ISDEL==1){ }}
					<a href="javascript:void(0);" onclick="editTfp('{{ d.list[i].ID}}',3);">查看</a>
				{{# } }}
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].GZMC)}}</td>
				<td class="td_center">{{$.cut($.nullToEmpty(d.list[i].HKGS),18)}}</td>
				<td class="td_center">{{$.cut($.nullToEmpty(d.list[i].CW),12)}}</td>
				<td class="td_center">{{$.cut($.nullToEmpty(d.list[i].CFCITY),18)}}</td>
				<td class="td_center">{{$.cut($.nullToEmpty(d.list[i].DDCITY),18)}}</td>
				<td class="td_center">
					{{#
						if(!d.list[i].CFRQS){  }}
						{{	$.nullToEmpty(d.list[i].CFRQS)}}
					{{#	}else{   }}
						{{d.list[i].CFRQS.substring(5,10)}}&nbsp;至
					{{# }  }}
					
					{{#
						if(!d.list[i].CFRQZ){  }}
						{{	$.nullToEmpty(d.list[i].CFRQZ)}}
					{{#	}else{   }}
						{{d.list[i].CFRQZ.substring(5,10)}}
					{{# }  }}
				</td>
				<td class="td_center">{{$.cut($.nullToEmpty(d.list[i].WDMC),16)}}</td>
				<td class="td_center">
					{{# 
					var rqqz="";
					if(!d.list[i].TQTSS&&!d.list[i].TQTSZ){
						rqqz="没有设置";
					}
					if(d.list[i].TQTSS||d.list[i].TQTSZ){
						if(!d.list[i].TQTSS){
							rqqz="0天-"+d.list[i].TQTSZ;
						}else if(!d.list[i].TQTSZ){
							rqqz=d.list[i].TQTSS+"至--";
						}else{
							rqqz=d.list[i].TQTSS+"天-"+d.list[i].TQTSZ+"天";
						}
					} }} 
					{{rqqz}}
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].QFSJXZ)}}</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].CZYHBH)}}</td>
				<td class="td_center"><a href="###" onclick="enterLogPage('{{d.list[i].ID}}');">
					{{# 
						var td="";
						if(d.list[i].ZT==0){
							td="<font color='red'>已禁用</font>";
						}
						if(d.list[i].ZT==1){
							td="<font color='green'>已启用</font>";
						}					
					}}
					{{td}}
					</a>
				</td>
    		</tr>
		{{# } }}
	</table>
</div>
</script>
  </head>
<body>
<c:set var="gngj" value="${empty param.gngj ? '1' : param.gngj }"></c:set>
<div class="tabContainer">
	<ul class="tabHead">
		<li lx='1' onclick="toSearch('1');" id="lx1" class="${empty param.tpfp or param.tpfp eq '1' ? 'currentBtn' : ''}">
		<a style="text-decoration:none;" id="tpgz">自动退票规则</a>
		</li>
		<li lx='2' onclick="toSearch('2');" id="lx2" class="${param.tpfp eq '2' ? 'currentBtn' : ''}">
		<a style="text-decoration:none;" id="fpgz">自动废票规则</a>
		</li>
	</ul>
	<div class="clear"></div>
</div>
<div class="container">
  	  	<div id="search_bar">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/cpsz/jpzdtfpgzsz/queryPage?gngj=${gngj }" id="searchForm" name="searchForm" method="post" target="_blank">
            				<input type="hidden"  name="VIEW" id="choosezts" />
            				<input type="hidden"  name="start" value="${param.pageNum }" id="pageNum"/>
							<input type="hidden"  name="orderBy" value="cz_datetime desc" id="orderBy"/>
							<input type="hidden"  name="count" value="10" id="pageSize"/>
							<input type="hidden"  value="0" id="choosecount"/>
							<input type="hidden"  value="0" id="chooseids"/>
							<input type="hidden"  name="tpfp" value="${empty param.tpfp ?1:param.tpfp }" id="tpfptype"/>
              				<table class="table01" border="0" cellpadding="0" cellspacing="0">
							   <tr>
								  <td  class="td_name">航空公司</td>
							      <td  class="td_val">
							        <input type="text" id="gn_hkgs_m" value="" style="width:96px;height: 20px;" onchange="clearValue(this,'qhkgs');"/>
   					 	  			<input type="hidden" id="qhkgs" name="hkgs" value="${param.hkgs }">
								  </td>
							      <td class="td_name"> 舱位 </td>
								   <td class="td_val">
									  <input type="text"  name="cw"  style="width:96px;height: 20px;" value="${param.cw}" />
								   </td>
								   <td class="td_name">出发城市  </td>
							      <td  class="td_val">
							      	 <input name="cityname1" id="cityname1" type="text"  style="width:96px;height: 20px;"  onclick="showCity('cityname1','cfcity')" onkeyup="showCity('cityname1','cfcity')"/>
							      <INPUT  id="cfcity" type=hidden name="cfcity" value="${param.cfcity }"> 
							      </td>
								    <td class="td_name">到达城市  </td>
							       <td  class="td_val">    
							      	 <input name="cityname2" id="cityname2" type="text"   style="width:96px;height: 20px;" onclick="showCity('cityname2','ddcity')" onkeyup="showCity('cityname2','ddcity')"/>
							         <INPUT id="ddcity" type="hidden" name="ddcity" value="${param.ddcity }"> 
							      </td>
									<td class="td_name">规则状态</td>
									<td class="td_val">
										<select id="gzzt" name="zt"  style="width:100px;height: 24px;" >
											<option value="">==请选择==</option>				
											<option value="0" ${'0' eq param.gzzt ? 'selected' : ''}>禁用</option>
											<option value="1" ${'1' eq param.gzzt ? 'selected' : ''}>启用</option>	
										</select>
									</td>
							    </tr>
							     <tr> 
									<td class="td_name">规则名称</td>
								   <td class="td_val">
									  <input type="text"  name="gzmc"  style="width:96px;height: 20px;" value="${param.gzmc }"/>
								   </td>
								   <td id = "rs" class="td_name">乘机日始</td>
                  					<td class="td_val"><input type="text" name="cfrqs"  style="width:98px;height: 20px;" value="${cfrqs}" class="input-text Wdate" size="10" id="mindate" 
                  					onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxdate\')}'})"></td> 
                  					<td id = "rz" class="td_name">乘机日止</td>
                 					<td class="td_val"><input type="text" name="cfrqz"  style="width:98px;height: 20px;" value="${cfrqz}" class="input-text Wdate"  
                 					id="maxdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'mindate\')}'})"></td>
                 					<td class="td_name">适用网店：</td>
									  <td>
									  	 <select name="syptbs" id="wdid" class="select" style="width:100px;height: 24px;">
								       	 	<option value="">所有</option> 
								       	 	<c:forEach items="${wdzlszList}" var="onewd">
								         		<option value="${onewd.id }">${onewd.wdmc }</option> 
								       	 	</c:forEach>
								         </select>
									  </td>
                 					<td><input type="checkbox" value="0" name="isdelvalue" onclick="queryDelete(this)">查询已删除
                 						<input type="hidden" id="isdelid" value="0" name="isdel">
                 					</td>
								    <td>
										<input type="button" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit" >
									</td>
							     </tr>
							</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
		<table width="" cellpadding="1" style="margin-top: 3px;">
			<tr>
			    <td>
			       <input type="button"  value="新 增" name="New" class="ext_btn ext_btn_success"  onClick="enterEditPage(0);">
			       <input type="button"  value="批量删除" name="Del" class="ext_btn" onClick="delTfp('','');">
			       <input type="button"  value="批量禁用" name="stop" class="ext_btn ext_btn_submit" onClick="changeZt('',0);">
			       <input type="button"  value="批量启用" name="start" class="ext_btn ext_btn_submit" onClick="changeZt('',1);">
			  </td>
		  </tr>
		</table>
      	<div  class="mt10" style="display:table;">
        	<div id="list_table_page1">
       		 <!-- 分页  ID不能修改-->
        </div>
        <div class="box span10 oh" id="list_table">
             <!-- 显示列表 ID不能修改 -->   
        </div>
        <div id="list_table_page">
        <!-- 分页  ID不能修改-->
     	</div>
	</div>
</body>
</html>

