<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title>自动出票规则设置</title>
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
<script type="text/javascript">
	$(function(){	
	   	//航司控件
	    $("#gn_hkgs_m").autocompleteGnHkgs("hkgs");
	})
     
	function showCity(nameId, valueId){
    	$("#"+nameId).autocompleteGnCity(valueId);
	}

	/*
	*进入添加页面
	*/
	function enterAddPage(){
		var url = "${ctx}/vedsb/jpzdcp/jpzdcpgzsz/cpgzEdit";
		window.open(url); 
	}
	
	/*
	*进入编辑页面
	*/
	//function EditPage(id){
		//var url = "${ctx}/vedsb/jpzdcp/jpzdcpgzsz/viewcpgzEdit?id="+id;
		//window.open(url);
	//}
	function uptZwgz(id,index){
		var url = "${ctx}/vedsb/jpzdcp/jpzdcpgzsz/cpgzEdit?id="+id+"&index="+index;
		window.open(url);
	}
	
	function clearValue(obj,hkgs_id){
		 if(obj.value==""){
		  $("#"+hkgs_id).val("");
		 }
	}
	
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
 
	function refresh(){
	  $("#searchFormButton").click();
	}
	
	function getStr(){
		var str="";
		$('input[type="checkbox"][name="onechkx"]').each(function(){
			if($(this).prop("checked")){
				str += $(this).val()+",";
			}
		});
		if(str){
			str = str.substring(0,str.length-1);
		}
		return str;
	}
	
	//单个删除
	function deteleCpgz(id,zt){
		  if(zt=="1"){
			  layer.msg("当前状态为[启用]无法删除");
			  return;
		  }else{
			 zt='2'; 
		  }
		  var url="${ctx}/vedsb/jpzdcp/jpzdcpgzsz/batchDelCpgz?gzids="+id+"&zt="+zt ;
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
				   type:"post",
			   success:function(data){
				 layer.close(i);
				 if(data==0){
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
	 
	  //启用禁用规则
   function uptZt(id,zt){
	   var cz = '';
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
	  var url = "${ctx}/vedsb/jpzdcp/jpzdcpgzsz/batchUptZt?gzids="+id+"&zt="+zt+"&time="+new Date().toString();
		$.layer({
	    	 area: ['250px', '150px'],
	         dialog: {
	             msg: "确定"+cz+"吗？",
	             btns: 2,                    
	             type: 4,
	             btn: ['确定','取消'],
	             yes: function(){
	             	 var ii = layer.load('系统正在处理您的操作,请稍候!');
	            	 $.ajax({url:url,
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
	    //批量启用/禁用
   function batchUptZt(zt){
	    var cz = '';
	    if(zt=="0"){
	    	cz="启用";
	    	zt='1';
	    }else{
	    	cz="停用";
	    	zt='0';
	    }
	   var str=getStr();
	   var formVal=$("#searchForm").serialize();
	   if(str==""){
		   $.layer({
		        area: ['250px', '150px'],
		        dialog: {
		        msg: '确定将查询到的所有规则<br>&nbsp;&nbsp;&nbsp; 进行'+cz+'?',
		        btns: 2,                   
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	var ii = layer.load('系统正在处理您的操作,请稍候!');
		        	$.post("${ctx}/vedsb/jpzdcp/jpzdcpgzsz/batchAllUptZt?zwzt="+zt,
		        		formVal,
		        		function(result) {
		        		    layer.close(ii);
			  				if("0"==result){
			  					refresh();
								layer.msg('批量'+cz+'成功!',2,1);
							}else{
	  							layer.msg('批量'+cz+'失败!',2,3);
	  						}
	  					}
		        	);
		        }, no: function(){
		             layer.msg('放弃批量'+cz, 2, 3);
		          }
		     }});
	   }else{
		  //检查选中项是否可以进行操作
		  var tobe_zts = 0;
		  $('input[type="checkbox"][name="onechkx"]').each(function(){
			  if($(this).prop("checked") && zt!=$(this).attr("ztval")){
				  tobe_zts++;
			  }
		  });
		  if(tobe_zts<=0){
			  layer.msg('您选择的所有规则都已是 ['+cz+']状态，无需再批量'+cz,2,3);
			  return;1
		  }
	      $.layer({
	        area: ['250px', '150px'],
	        dialog: {
	        msg: '确定批量'+cz+'吗？',
	        btns: 2,                    
	        type: 4,
	        btn: ['确定','取消'],
	        yes: function(){
	        	var ii = layer.load('系统正在处理您的操作,请稍候!');
	  			$.ajax({
	  				type:"post",
	  				url:"${ctx}/vedsb/jpzdcp/jpzdcpgzsz/batchUptZt?gzids="+str+"&zt="+zt,
	  				success:function(result){
	             	 	layer.close(ii);
  						if("0"==result){
  							refresh();
	  						layer.msg('批量'+cz+'成功!',2,1);
						}else{
  							layer.msg('批量'+cz+'失败!',2,3);
  						}
	  				}	
	  			});
	        }, no: function(){
	            layer.msg('放弃批量'+cz, 2, 3);
	        }
	     }});
	   }
   }
	  

	
	//批量删除
	function batchDel(){  
		 var str=getStr();
	     var formVal=$("#searchForm").serialize();
	     if(str==""){
	       //未选择直接点击
		   $.layer({
		        area: ['265px', '150px'],
		        dialog: {
		        msg: '确定将所有规则进行删除吗？',
		        btns: 2,                   
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	var ii = layer.load('系统正在处理您的操作,请稍候!');
		        	$.post("${ctx}/vedsb/jpzdcp/jpzdcpgzsz/batchDelAllCpgz?zwzt=2", 
		        		formVal,
		        		function(result) {
		        		    layer.close(ii);
		        			if("0"==result){
		  						refresh();
		  						layer.msg('批量删除成功',2,1);
		        			}else if("2"==result){
		  						layer.msg('删除规则中存在[启用]的规则，请先禁用!',2,3);
	  						}else{
	  							layer.msg('批量删除失败',2,3);
	  						}
		        		}
		        	);
		        }, no: function(){
		        	 layer.msg('放弃删除', 2, 3);
		          }
		      }
		    });
	   }else{
		   var zt_1s=0;
		   var zt1_xhs = "";
		   $('input[type="checkbox"][name="onechkx"]').each(function(){
			  if($(this).prop("checked") && "1"==$(this).attr("ztval")){
				  zt_1s++;
				  zt1_xhs += $(this).attr("sortnum")+",";
			  }
		   });
		   if(zt_1s>0){
			  zt1_xhs=zt1_xhs.substring(0,zt1_xhs.length-1);
			  layer.msg('您选择的序号为如下的规则为[启用]状态，不能删除，请先停用！序号为：'+zt1_xhs,3,3);
			  return;
		   }	   
		   var url="${ctx}/vedsb/jpzdcp/jpzdcpgzsz/batchDelCpgz?gzids="+str+"&zt=2";
		   $.layer({
			 area:['250px', '150px'],
			 title:['批量删除'],
			 dialog:{
			 msg:'确定删除吗?',
			 btns:2,
			 type:4,
			  yes: function(){
			   var i=layer.load('系统正在处理您的操作,请稍候');
			   $.ajax({
				   url:url,
				   type:"post",
			   success:function(data){
				 layer.close(i);
				 if(data==0){
					refresh();
					layer.msg('删除成功',1,1);
				 }if(data==-1){
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
	}


	//表单提交	 
	function searchFrom(){	
		layer.load('系统正在处理你的操作,请稍后!');
		$("#searchForm").submit();
		
	}
	//优先级别调整
	function sortYXJB(index,gid,gzyxj,bj){
		var dygYxj=$(".tr:first").attr("yxj");
		var zhgYxj=$(".tr:last").attr("yxj");
		var qygId=$("#tr_"+index).prev().attr("cpgzid");
		var hygId=$("#tr_"+index).next().attr("cpgzid");
        var url="${ctx}/vedsb/jpzdcp/jpzdcpgzsz/updateYxj?gid="+gid+"&bj="+bj+"&dygYxj="+dygYxj+"&zhgYxj="+zhgYxj+"&qygId="+qygId+"&hygId="+hygId;
	 	$.ajax({
	 		url:url,
	 		type:"post",
	 		success:function(data){
		 		if(data=="0"){
		 			searchForm.submit();
		 		}else{
		 			layer.msg('调整失败');
		 		}
	 		}
	 	});				
    }
	
	//查看异动日志
	function enterLogPage(gzbh){
		var url="${ctx}/vedsb/jpzdcp/jpzdcpgzczrz/enterLogPage_"+gzbh;
		$.layer({
			type: 2,
			title: ['<b>规则异动日志</b>'],
			area: ['650px', '360px'],
			iframe: {src: url}
	    });
	}	
</script>
</head>
  
<body>
<div class="container">
  	 <div id="search_bar">
       <div class="box">
          <div class="box_border">
           <div class="box_center pt10 pb10">
				<form action="${ctx}/vedsb/jpzdcp/jpzdcpgzsz/getList" id="searchForm" name="searchForm" method="post" >
				  <input type="hidden"  name="orderBy" value="yxj desc" id="orderBy"/>
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
								 <input  id="cfcity" type=hidden name="cfcity" value="${param.cfcity}"> 
							</td>
							<td class="td_name">到达城市</td>
							<td  class="td_val">    
								 <input name="cityname2" id="cityname2" type="text"  style="width:100px" value="${param.cityname2}" class="input-text lh25 srk" onchange="clearValue(this,'ddcity');" onclick="showCity('cityname2','ddcity')" onkeyup="showCity('cityname2','ddcity')"/>
								 <input id="ddcity" type="hidden" name="ddcity" value="${param.ddcity}"> 
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
							 	<input type="button" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit" onclick="searchFrom()">
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
  
	<c:if test="${ empty list}">
	<div id="div11" style="display:none">
	</c:if>
	<table width="2000px" border="0" cellpadding="0" cellspacing="0" class="list_table"  >
	<tr>
		<th width="5px"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
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
	    <c:forEach items="${list}" var="Jpgz" varStatus="status" begin="0" step="1">   
			<tr class="tr" yxj="${Jpgz.yxj}" cpgzid="${Jpgz.id}"  id="tr_${status.count}" zt="${Jpgz.zt}">
				<td  align="center"><input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" sortnum="${status.count}" ztval="${Jpgz.zt}" value="${Jpgz.id}"></th>
       			<td class="td_center">${status.count}</td>
				<td class="td_center">
			    <a href="###" onclick="uptZwgz('${Jpgz.id}','1');">修改</a>
			    <a href="###" onclick="deteleCpgz('${Jpgz.id}','${Jpgz.zt}');">删除</a>
			    <a href="###" onclick="uptZwgz('${Jpgz.id}','2');">复制</a>
				 <c:if test="${Jpgz.zt=='1'}">
				   <a href="###" onclick="uptZt('${Jpgz.id}','${Jpgz.zt}');">
				   <font color='red'>停用</font>
				   </a>
			     </c:if>
               	 <c:if test="${Jpgz.zt=='0'}">
               	   <a href="###" onclick="uptZt('${Jpgz.id}','${Jpgz.zt}');">
				   <font color='green'>启用</font>
				   </a>
			     </c:if>
			    
		        </td>
				<td class="td_center" ><div class="short1"><a href="###" onclick="enterLogPage('${Jpgz.id}')" style="text-decoration:none">${Jpgz.gzmc}</a></div></td>
				<td class="td_center">
			       <c:if test="${Jpgz.zt=='1'}">
					<font color='green'>启用</font>
			       </c:if>
					<c:if test="${Jpgz.zt=='0'}">
					<font color='red'>停用</font>
			       </c:if>		
	    	    </td>
				<td class="td_center" width="10"><div class="short1">${Jpgz.zcdm }</div></td>
				<td class="td_center" ><div class="short1">${vfn:cut(Jpgz.hkgs,18)}</div></td>
				<td class=td_center ><div class="short1">${vfn:cut(Jpgz.cw,12)}</div></td>
				<td class="td_center" ><div class="short1">${vfn:cut(Jpgz.cfcity,16)}</div></td>
				<td class="td_center" ><div class="short1">${vfn:cut(Jpgz.ddcity,16)}</div></td>
				<td class="td_center" ><div class="short1">${Jpgz.cjrqs}至${Jpgz.cjrqz}</div></td>
				<td class="td_center" ><div class="short1">${Jpgz.zxlr}</div></td>
			    <td class="td_center"><div class="short1">${Jpgz.bpyxj}</div></td>
				<td class="td_center"><div class="short1">${Jpgz.sysjs}-${Jpgz.sysjz}</div></td>
				<td class="td_center" id="yxjtd"><div class="short1">
					<c:if test="${status.count ne 1}">
				 	    <a href="#" onclick="sortYXJB('${status.count}','${Jpgz.id}','${Jpgz.yxj}','1')"><IMG title=调整顺序至上一层 border=0 src="${ctx}/static/images/arrow_up_blue.gif"></a>
			   			<a href="#" onclick="sortYXJB('${status.count}','${Jpgz.id}','${Jpgz.yxj}','2')"><img title=调整顺序至顶层 border=0 src="${ctx}/static/images/arrow_first.gif" /></a>
			   		</c:if>
			   		<c:if test="${!status.last}">
			   			<a href="#" onclick="sortYXJB('${status.count}','${Jpgz.id}','${Jpgz.yxj}','3')"><IMG title=调整顺序至下一层 border=0 src="${ctx}/static/images/arrow_down_blue.gif"></a> 
						<a href="#" onclick="sortYXJB('${status.count}','${Jpgz.id}','${Jpgz.yxj}','4')"><IMG title=调整顺序至最底层 border=0 src="${ctx}/static/images/arrow_last.gif"></a> 
			   		</c:if>
				
				</div></td>
    		</tr>
    		</c:forEach>
	</table>
 	</div>
</body>
</html>

