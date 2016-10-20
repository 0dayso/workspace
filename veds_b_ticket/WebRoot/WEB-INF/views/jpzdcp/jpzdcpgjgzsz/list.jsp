<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title>自动出票国际规则设置</title>
    <script type="text/javascript" src="${ctx}/static/core/data/hkgs.js?v=${VERSION}"></script>
    <script type="text/javascript" src="${ctx}/static/core/data/gn_city.js?v=${VERSION}"></script>
	<script type="text/javascript" src="${ctx}/static/core/data/gj_city.js?v=${VERSION}"></script>
    <style>
	.td_val{
		text-align:left; 
	}
	.td_name{
		text-align:right; 
	}
	a{
		text-decoration:none;
	}
</style>
<script type="text/javascript">
	$(function(){	
	   	//航司控件
	    $("#gn_hkgs_m").autocompleteHkgs("hkgs");
	})
     
	function showCity(nameId, valueId){
    	$("#"+nameId).autocompleteGnCity(valueId);
	}

	/*
	*进入添加页面
	*/
	function enterAddPage(){
		var url = "${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/cpgjgzedit";
		window.open(url); 
	}
	//进入新增或编辑页面
	function uptZwgz(id,index){
		var url = "${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/cpgjgzedit?id="+id+"&index="+index;
		window.open(url);
	}
	
	function clearValue(obj,hkgs_id){
		 if(obj.value==""){
		  $("#"+hkgs_id).val("");
		 }
	}
	//全选
	function checkAll(checkallbox){
	   if(checkallbox.checked){
		   $('input[type="checkbox"][name="onechkx"]').each(function(){
			   $(this).attr("checked",true);
		   });
	   }else{
		   $('input[type="checkbox"][name="onechkx"]').each(function(){
			   $(this).attr("checked",false);
		   });
	   }
	}
	//取消全选
	function ifCheckAll(onechkx){
	   var chkxArr = $('input[type="checkbox"][name="onechkx"]');
	   var total = chkxArr.length;
	   var checkedLen = 0;
	   if(onechkx.checked){
		   $(chkxArr).each(function(){
			   if($(this).prop("checked")){
				   checkedLen++;
			   }
		   });
		   if(total==checkedLen){
			   $("#checkallbox").attr("checked",true);
		   }else{
			   $("#checkallbox").attr("checked",false);
		   }
	   }else{
		   $("#checkallbox").attr("checked",false);
	   }
	}
 	//刷新
	function refresh(){
	  $("#searchFormButton").click();
	}
	
	//单条规则删除
	function deteleCpgz(id,zt){
		  if(zt=="1"){
			  layer.msg("当前状态为[启用]无法删除");
			  return;
		  }else{
			 zt='2'; 
		  }
		  var url="${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/updatezt" ;
		    $.layer({
			 area:['250px', '150px'],
			 title:['删除'],
			 dialog:{
			 msg:'确定删除吗?',
			 btns:2,
			 type:4,
			  yes: function(){
			   var i=layer.load('系统正在处理您的操作,请稍候');
			   $.ajax({
				   url:url,
				   data:{gzid:id,zt:zt},
				   type:"post",
				   success:function(result){
					 layer.close(i);
					 if(result == 0){
						refresh();
						layer.msg('删除成功',1,1);
					 }else{
						layer.msg('删除失败',1,3); 
					      }
				     } 
				   });
				},
			   no: function(){
			    layer.load('放弃删除',1,4)	  
			   }
			 }
			   
		   });
	}
	 
	  //单条规则启用/停用
   function uptZt(id,zt){
	   var cz = "";
	    if(zt==""){
	    	layer.msg('当前状态无法进行启用停用操作');
	    	return;
	    }else if(zt=="0"){
	    	cz="启用";
	    	zt='1';
	    }else if(zt=='1'){
	    	cz="停用";
	    	zt='0';
	    }
	  var url = "${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/updatezt";
		$.layer({
	    	 area: ['250px', '150px'],
	         dialog: {
	             msg: "确定"+cz+"吗？",
	             btns: 2,                    
	             type: 4,
	             btn: ['确定','取消'],
	             yes: function(){
	             	 var ii = layer.load('系统正在处理您的操作,请稍候!');
	            	 $.ajax({
	            	 		data:{gzid:id,zt:zt},
	            	 		url:url,
	           	 			success:function(data){
	           	 				layer.close(ii);
	           	 				if(data=="0"){
		           	 				refresh();
		           	 				layer.msg(cz+'成功',2,1);
	           	 				}else{
		           	 				layer.msg(cz+'失败',2,3);
	           	 				}
	           	 				
	           	 			}
	           	 		});
	             }, no: function(){
	                 layer.msg('放弃'+cz, 2, 3);
	             }
	         }
	     });
   }
   
   //获取选中的记录的ID
   function getIdArr(zt){
   		//保存ID，已逗号分隔
   		var ids="";
   		$("input[name=onechkx]").each(function(){
   			if($(this).is(":checked")){
   				var ztval = $(this).attr("ztval");
   				if(zt == ztval){
   					ids += $(this).attr("gzid") + ",";
   				}
   			}
   		});
   		if(ids){
   			ids = ids.substring(0,ids.length-1);
   		}
   		return ids;
   }
   
   //判断是否是全部启用/禁用/删除
   function ifqbqyjy(){
   		var index = 0;
   		$("input[name=onechkx]").each(function(){
   			if($(this).is(":checked")){
   				index++ ;
   			}
   		});
   		//为0表示未勾选，即全部启用/禁用/删除
   		if(index == 0){
   			return true;
   		}
   }
   
	//批量启用/禁用
   function batchUptZt(zt){
	    var msg = "";
	    var ids = getIdArr(zt);
	    var cz = '';
	    if(zt=="0"){
	    	cz="启用";
	    	zt='1';
	    }else if(zt=='1'){
	    	cz="停用";
	    	zt='0';
	    }
	    //判断是不是全部启用/禁用
	   if(ifqbqyjy()){
	   		msg = "确定将查询到的所有规则<br>&nbsp;&nbsp;&nbsp; 进行"+cz+"?"
	   }else{
		  if(ids == ""){
		  	layer.msg('您选择的所有规则都已是 ['+cz+']状态，无需再批量'+cz,2,3);
		  	return;
		  }
		  msg = "确定批量"+cz+"吗？";
	   }
	    $.layer({
		        area: ['250px', '150px'],
			    dialog: {
			        msg: msg,
			        btns: 2,                   
			        type: 4,
			        btn: ['确定','取消'],
			        yes: function(){
			        	var ii = layer.load('系统正在处理您的操作,请稍候!');
				        $.ajax({
			  				type:"post",
			  				url:"${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/batchUptZt",
			  				data:{ids:ids,zt:zt},
			  				success:function(result){
			        		    layer.close(ii);
				  				if(result == "0"){
				  					refresh();
									layer.msg('批量'+cz+'成功!',2,1);
								}else{
		  							layer.msg('批量'+cz+'失败!',2,3);
		  						}
			  				}	
			  			});
			        }, 
			        no: function(){
			            layer.msg('放弃批量'+cz, 2, 3);
			        }
		      }
		 });
   }
	  //判断列表中是否存在已启用  1启用
	  function ifqbsc(){
	  	var flag = false;
	  	$.ajax({
 			url:"${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/ifczyqybyshbh",
 			async:false,
 			success:function(result){
  				if(result == "0"){
  					flag = true;
				}
 			}	
	 	});
	 	return flag;
	  }
	//批量删除
	function batchDel(){
		var msg = "";
		//判断是否勾选
		if(ifqbqyjy()){
			//判断所有记录中是否存在已启用的规则
			if(ifqbsc()){
				layer.msg('删除规则中存在[启用]的规则，请先停用!',2,3);
				return;
			}
			msg = "确定将所有规则进行删除吗？";
		}else{
			var ids = getIdArr("0");
			if(ids == ""){
				layer.msg('删除规则中存在[启用]的规则，请先停用!',2,3);
				return;
			}else{
				msg = "确定删除吗?";
			}
		}
		$.layer({
	        area: ['265px', '150px'],
	        title:['批量删除'],
		    dialog: {
		        msg: msg,
		        btns: 2,                   
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	var ii = layer.load('系统正在处理您的操作,请稍候!');
			        $.ajax({
		  				type:"post",
		  				url:"${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/batchUptZt",
		  				data:{ids:ids,zt:"2"},
		  				success:function(result){
		        		    layer.close(ii);
			  				if(result == "0"){
			  					refresh();
								layer.msg('批量删除成功!',2,1);
							}else{
	  							layer.msg('批量删除失败!',2,3);
	  						}
		  				}	
		  			});
		        }, 
		        no: function(){
		            layer.msg('放弃批量删除', 2, 3);
		        }
		      }
		 });
	}

	//优先级别调整
	function sortYXJB(index,gid,gzyxj,bj){
		var dygYxj=$(".tr:first").attr("yxj");
		var zhgYxj=$(".tr:last").attr("yxj");
		var qygId=$("#tr_"+index).prev().attr("cpgzid");
		var hygId=$("#tr_"+index).next().attr("cpgzid");
        var url="${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/updateYxj?gid="+gid+"&bj="+bj+"&dygYxj="+dygYxj+"&zhgYxj="+zhgYxj+"&qygId="+qygId+"&hygId="+hygId;
	 	$.ajax({
	 		url:url,
	 		type:"post",
	 		success:function(result){
		 		if(result == "0"){
		 			$("#searchFormButton").click();
		 		}else{
		 			layer.msg('调整失败');
		 		}
	 		}
	 	});				
    }
	
	//查看异动日志
	function enterLogPage(gzbh){
		var url="${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/enterLogPage_"+gzbh;
		$.layer({
			type: 2,
			title: ['<b>规则异动日志</b>'],
			area: ['650px', '360px'],
			iframe: {src: url}
	    });
	}	
	
	//DOCA设置
	function docaSz(){
		$.layer({
			type: 2,
			title: ['<b>DOCA设置</b>'],
			area: ['1300px', '600px'],
			iframe: {src: "${ctx}/vedsb/jpzdcp/jpgjdocasz/query"}
	    });
	}
</script>
<script id="tpl_list_table" type="text/html">
<div>
	<table width="2000px" border="0" cellpadding="0" name="cpkzt_table" cellspacing="0" class="list_table">
	<tr>
		<th width="1%"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
		<th width="2%">序号</th>
		<th width="10%">操作</th>
		<th width="5%">规则名称</th>
		<th width="5%">状态</th>
        <th width="7%">适用</br>政策代码</th>
		<th width="10%">航空公司</th>
		<th width="5%">舱位</th>
		<th width="8%">出发城市</th>
		<th width="8%">到达城市</th>
		<th width="9%">乘机</br>日期起止</th>
		<th width="8%">最小</br>出票利润</th>
		<th width="8%">出票渠道</th>
		<th width="8%">有效时间</th>
        <th width="8%">优先级</th>
	</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}  
			<tr class="tr" yxj="{{$.nullToEmpty(d.list[i].yxj)}}" cpgzid="{{$.nullToEmpty(d.list[i].id)}}"  id="tr_{{ i+1 }}" zt="{{$.nullToEmpty(d.list[i].zt)}}">
				<td  align="center">
					<input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" sortnum="{{ i+1 }}" ztval="{{$.nullToEmpty(d.list[i].zt)}}" gzid="{{$.nullToEmpty(d.list[i].id)}}">
				</td>
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
			    <a href="###" onclick="uptZwgz('{{$.nullToEmpty(d.list[i].id)}}','1');">修改</a>
			    <a href="###" onclick="deteleCpgz('{{$.nullToEmpty(d.list[i].id)}}','{{$.nullToEmpty(d.list[i].zt)}}');">删除</a>
			    <a href="###" onclick="uptZwgz('{{$.nullToEmpty(d.list[i].id)}}','2');">复制</a>
				{{#
					var zt = $.nullToEmpty(d.list[i].zt);
					if(zt == '1'){
				}}
				   <a href="###" onclick="uptZt('{{$.nullToEmpty(d.list[i].id)}}','{{$.nullToEmpty(d.list[i].zt)}}');">
				   		<font color='red'>停用</font>
					</a>
				{{#
					}
					if(zt == '0'){
				}}
				  <a href="###" onclick="uptZt('{{$.nullToEmpty(d.list[i].id)}}','{{$.nullToEmpty(d.list[i].zt)}}');">
				  	 <font color='green'>启用</font>
				   </a>
				{{#
					}
				}}
		        </td>
				<td class="td_center" >
					<div class="short1">
						<a href="###" onclick="enterLogPage('{{$.nullToEmpty(d.list[i].id)}}')" style="text-decoration:none">{{$.nullToEmpty(d.list[i].gzmc)}}</a>
					</div>
				</td>
				<td class="td_center">
					{{d.list[i].zt == 1 ? '<font color=green>启用</font>' : ''}}
					{{d.list[i].zt == 0 ? '<font color=red>停用</font>' : ''}}
	    	    </td>
				<td class="td_center" width="10"><div class="short1">{{$.nullToEmpty(d.list[i].zcdm)}}</div></td>
				<td class="td_center" ><div class="short1">{{$.cut(d.list[i].hkgs, 18)}}</div></td>
				<td class=td_center ><div class="short1">{{$.cut(d.list[i].cw, 18)}}</div></td>
				<td class="td_center" ><div class="short1">{{$.cut(d.list[i].cfcs, 16)}}</div></td>
				<td class="td_center" ><div class="short1">{{$.cut(d.list[i].ddcs, 16)}}</div></td>
				<td class="td_center" ><div class="short1">{{$.nullToEmpty(d.list[i].cjrqs)}}至{{$.nullToEmpty(d.list[i].cjrqz)}}</div></td>
				<td class="td_center" ><div class="short1">{{$.nullToEmpty(d.list[i].drzxlr)}}</div></td>
			    <td class="td_center"><div class="short1">
					{{$.nullToEmpty(d.list[i].kqbop) == '1' ? "BOP" : ""}}
					{{$.nullToEmpty(d.list[i].kqbop) == '1' && $.nullToEmpty(d.list[i].kqbspet) == '1' ? "/" : ""}}
					{{$.nullToEmpty(d.list[i].kqbspet) == '1' ? "BSPET" : ""}}
				</div></td>
				<td class="td_center"><div class="short1">{{$.nullToEmpty(d.list[i].sysjs)}}-{{$.nullToEmpty(d.list[i].sysjz)}}</div></td>
				<td class="td_center" id="yxjtd">
				<div class="short1">
				{{#
					if(i != 0){
				}}
				 	    <a href="#" onclick="sortYXJB('{{ i+1 }}','{{$.nullToEmpty(d.list[i].id)}}','{{$.nullToEmpty(d.list[i].yxj)}}','1')"><IMG title=调整顺序至上一层 border=0 src="${ctx}/static/images/arrow_up_blue.gif"></a>
			   			<a href="#" onclick="sortYXJB('{{ i+1 }}','{{$.nullToEmpty(d.list[i].id)}}','{{$.nullToEmpty(d.list[i].yxj)}}','2')"><img title=调整顺序至顶层 border=0 src="${ctx}/static/images/arrow_first.gif" /></a>
				{{#
					}
					if(i != (d.list.length-1)){
				}}
			   			<a href="#" onclick="sortYXJB('{{ i+1 }}','{{$.nullToEmpty(d.list[i].id)}}','{{$.nullToEmpty(d.list[i].yxj)}}','3')"><IMG title=调整顺序至下一层 border=0 src="${ctx}/static/images/arrow_down_blue.gif"></a> 
						<a href="#" onclick="sortYXJB('{{ i+1 }}','{{$.nullToEmpty(d.list[i].id)}}','{{$.nullToEmpty(d.list[i].yxj)}}','4')"><IMG title=调整顺序至最底层 border=0 src="${ctx}/static/images/arrow_last.gif"></a> 
				{{#
					}
				}}
				</div>
				</td>
    		</tr>
		{{# } }}
	</table>
</div>
</script>
</head>
  
<body>
<div class="container">
  	 <div id="search_bar">
       <div class="box">
          <div class="box_border">
           <div class="box_center pt10 pb10">
				<form action="${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/query" id="searchForm" name="searchForm" method="post" >
				  <input type="hidden"  name="orderBy" value="yxj desc" id="orderBy"/>
				  <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				  <input type="hidden"  name="pageSize" value="10" id="pageSize"/>
              		<table class="table01" border="0" cellpadding="0" cellspacing="0" >
						<tr>
						  	<td class="td_name">规则ID</td>
							<td class="td_val">
							 	<input type="text"  name="id" value="${param.id}" style="width:100px" class="input-text lh25 srk"/>
							</td>
							<td class="td_name">自动出票</br>规则名称</td>
	                  	    <td class="td_val">
		                  	 	<input type="text" name="gzmc" value="${param.gzmc}" id="gzmc" style="width:100px" class="input-text lh25 srk"/>
	                 		</td>
	                 	    <td class="td_name">适用政</br>策代码</td>
	                  		<td class="td_val">
		                  	 	<input type="text" name="zcdm" value="${param.zcdm}" id="zcdm" style="width:100px" class="input-text lh25 srk"/>
	                 		</td>
	                  		<td class="td_name">航空公司</td>
	                  		<td class="td_val">
		                  	 	 <input type="text" id="gn_hkgs_m" name="gn_hkgs_m" class="input-text lh25 srk" style="width:100px" value="${param.gn_hkgs_m}" onchange="clearValue(this,'hkgs');"/>
	   					 	 	 <input type="hidden" id="hkgs" name="hkgs" value="${param.hkgs}">
	                 		</td>
							<td class="td_name">舱位</td>
							<td class="td_val">
							 	<input type="text"  name="cw" style="width:100px" value="${param.cw}" onkeyup="this.value=this.value.toUpperCase()" class="input-text lh25 srk"/>
							</td>
						</tr>
						<tr>
							<td class="td_name">出发城市</td>
							<td  class="td_val">
								 <input name="cityname1" id="cityname1" type="text"  style="width:100px" class="input-text lh25 srk" value="${param.cityname1}" onchange="clearValue(this,'cfcity');" onclick="showCity('cityname1','cfcity')" onkeyup="showCity('cityname1','cfcity')"/>
								 <input  id="cfcs" type=hidden name="cfcity" value="${param.cfcs}"> 
							</td>
							<td class="td_name">到达城市</td>
							<td  class="td_val">    
								 <input name="cityname2" id="cityname2" type="text"  style="width:100px" value="${param.cityname2}" class="input-text lh25 srk" onchange="clearValue(this,'ddcity');" onclick="showCity('cityname2','ddcity')" onkeyup="showCity('cityname2','ddcity')"/>
								 <input id="ddcity" type="hidden" name="ddcs" value="${param.ddcs}"> 
							</td>
							<td id = "rs" class="td_name">乘机日始</td>
                  			<td class="td_val">
                  				<input type="text" name="cjrqs" value="${param.cjrqs}" style="width:100px" class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxdate\')}'})">
                  			</td> 
                  			<td id = "rz" class="td_name">乘机日止</td>
                 			<td class="td_val">
                 				<input type="text" name="cjrqz" style="width: 100px;" value="${param.cjrqz}" style="width:100px" class="input-text Wdate"  id="maxdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'mindate\')}'})">
                 			</td>
							<td class="td_name">规则状态</td>
							<td class="td_val">
								<select id="zt" name="zt" style="width:102px" class="input-text lh25 srk">
									<option value="">==请选择==</option>				
									<option value="1" ${'1' eq param.zt ? 'selected' : ''}>启用</option>	
									<option value="0" ${'0' eq param.zt ? 'selected' : ''}>禁用</option>
								</select>
							</td>	
							<td class="td_val">
							 	<input type="button" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit" />
							 	<input type="button" id="doca" name="button" value="DOCA设置" class="ext_btn ext_btn_success" onclick="docaSz()"/>
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
			       <input type="button"  value="新 增" name="New" class="ext_btn ext_btn_submit"  onClick="enterAddPage();">
			       <input type="button"  value="批量删除" name="Del" class="ext_btn ext_btn_submit" onClick="batchDel();">       
			       <input type="button"  value="批量停用" name="start" class="ext_btn ext_btn_submit" onClick="batchUptZt('1');">
			       <input type="button"  value="批量启用" name="start" class="ext_btn ext_btn_submit" onClick="batchUptZt('0');">				   
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

