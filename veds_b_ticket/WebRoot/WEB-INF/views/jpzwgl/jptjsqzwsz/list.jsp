<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
 <html>
 <head>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
	<style>
		.srk{
			width:90px;
		}
   </style>
   <script type="text/javascript">
   $(function(){	
	   	//航司控件
	    $("#gn_hkgs_m").autocompleteGnHkgs("gn_hkgs");
		//refresh();
	})
   //新增
   function insertZwgz(){
	   var url ="${ctx}/vedsb/jpzwgl/jptjsqzwsz/initedit";
	   $.layer({
			type: 2,
			title: ['新增追位规则'],
			area: ['710px', '455px'],
			iframe: {src: url}
	   });
   }
   
   function uptZwgz(id,czlx){
	   var str="";
	   if(czlx =="2"){
		   var str="<font color='red'>【本规则是通过复制过来，请修改之后生成一条新规则！】</font>";
	   }
	   var url ="${ctx}/vedsb/jpzwgl/jptjsqzwsz/initedit?id="+id+"&timer="+new Date().toString()+"&czlx="+czlx;
	   $.layer({
			type: 2,
			title: ['修追位规则' + str],
			area: ['710px', '455px'],
			iframe: {src: url},
			scrollbar: true
	   }); 
   }
   
   //启用禁用规则
   function uptZt(id,zt){
	   var cz = '';
	    if(zt=="0"){
	    	cz="启用";
	    	zt='1';
	    }else{
	    	cz="禁用";
	    	zt='0';
	    }
	   	var url = "${ctx}/vedsb/jpzwgl/jptjsqzwsz/batchUptZt?zwgzids="+id+"&zt="+zt+"&time="+new Date().toString();
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
   
   //删除规则
   function deteleZwgz(id,zt){
	var url = "${ctx}/vedsb/jpzwgl/jptjsqzwsz/batchDelZwgz?zwgzids="+id+"&time="+new Date().toLocaleString();
	$.layer({
    	 area: ['250px', '150px'],
         dialog: {
             msg: '确定删除吗？',
             btns: 2,                    
             type: 4,
             btn: ['确定','取消'],
             yes: function(){
             	if( zt=="1"){
             		layer.msg('请先禁用该规则后再删除!',2,3);
             	}else{
             		var ii = layer.load('系统正在处理您的操作,请稍候!');
            	 	$.ajax({url:url,
           	 			success:function(data){
	             			layer.close(ii);
           	 				if(data=="0"){
	           	 				refresh();
	           	 				layer.msg('删除成功',2,1);
           	 				}else{
	           	 				layer.msg('删除失败',2,3);
           	 				}
           	 			}
           	 		});
            	}
             }, no: function(){
                 layer.msg('放弃删除', 2, 3);
             }
         }
     });
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
		        msg: '确定将所有追位规则进行删除吗？',
		        btns: 2,                   
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	var ii = layer.load('系统正在处理您的操作,请稍候!');
		        	$.post("${ctx}/vedsb/jpzwgl/jptjsqzwsz/batchDelAllZwgz", 
		        		formVal,
		        		function(result) {
		        		    layer.close(ii);
		        			if("0"==result){
		  						refresh();
		  						layer.msg('批量删除成功',2,1);
		        			}else if("2"==result){
		  						layer.msg('删除规则中存在未禁用的规则，请先禁用!',2,3);
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
		  //验证是否有启用的规则，不能删除
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
			  layer.msg('您选择的序号为如下的规则为[启用]状态，不能删除，请先禁用！序号为：'+zt1_xhs,3,3);
			  return;
		  }
		  
	      $.layer({
	        area: ['250px', '150px'],
	        dialog: {
	        msg: '确定删除吗？',
	        btns: 2,                    
	        type: 4,
	        btn: ['确定','取消'],
	        yes: function(){
	        	var ii = layer.load('系统正在处理您的操作,请稍候!');
	  			$.ajax({
	  				type:"post",
	  				url:"${ctx}/vedsb/jpzwgl/jptjsqzwsz/batchDelZwgz?zwgzids="+str+"&time="+new Date().toString(),
	  				success:function(result){
	             	 	layer.close(ii);
	  					if("0"==result){
	  						refresh();
		  					layer.msg('批量删除成功',2,1);
	  					}else if("2"==result){
		  					layer.msg('删除规则中存在未禁用的规则，请先禁用!',2,3);
	  					}else{
	  						layer.msg('批量删除失败',2,3);
	  					}
	  				}	
	  			});
	        }, no: function(){
	             layer.msg('放弃删除', 2, 3);
	          }
	      }
	     });
	  	}
   }
   
   //批量启用/禁用
   function batchUptZt(zt){
	   var cz = "1"==zt ? "启用" : "禁用";
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
		        	$.post("${ctx}/vedsb/jpzwgl/jptjsqzwsz/batchAllUptZt?zwzt="+zt, 
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
			  return;
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
	  				url:"${ctx}/vedsb/jpzwgl/jptjsqzwsz/batchUptZt?zwgzids="+str+"&zt="+zt,
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
   
   function getStr2(){
		var str="";
		$('input[type="checkbox"][name="onechkx"]').each(function(){
			if($(this).prop("checked")){
				str += $(this).val()+"/";
			}
		});
		if(str){
			str = str.substring(0,str.length-1);
		}
		return str;
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
   function strTrim(str){
		 return str.replace(/(^\s*)|(\s*$)/g, "");
   }
   function getRz(id){
	   var url = "${ctx}/vedsb/ydrz/bydrz/viewlist?bm=wd_qdgz_czrz&ywid="+id+"&lx=0";
	   $.layer({
			type: 2,
			title: ['抢单规则操作日志'],
			area: ['720px', '450px'],
			iframe: {src: url}
	   }); 
   }
   
   function clearValue(obj,hkgs_id){
	   if(obj.value==""){
		   $("#"+hkgs_id).val("");
	   }
   }
   </script>
 	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<th width="2%"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
        <th width="3%">序号</th>
        <th width="12%">操作</th>
        <th width="6%">网店平台</th>
        <th width="10%">网店政策类型</th>
		<th width="9%">航空公司</th>
		<th width="10%">舱位</th>
		<th width="9%">目标舱位</th>
		<th width="7%">PNR状态</th>
		<th width="4%">出票<br>状态</th>
		<th width="3%">提前<br>天数</th>
		<th width="3%">优先<br>等级</th>
		<th width="7%">最&nbsp;&nbsp;后</br>操作时间</th>
		<th width="7%">最&nbsp;后</br>操作人</th>
	    <th width="4%">状态</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
        <td width="5" align="center"><input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" sortnum="{{i+1}}" ztval="{{ $.nullToEmpty(d.list[i].zt)}}" value="{{ $.nullToEmpty(d.list[i].id)}}"></th>
        <td class="td_center">{{ i+1 }}</td>
		<td class="td_center">
			<a href="###" onclick="uptZwgz('{{d.list[i].id}}','1');">修改</a>
			<a href="###" onclick="deteleZwgz('{{d.list[i].id}}','{{d.list[i].zt}}');">删除</a>
			<a href="###" onclick="uptZwgz('{{d.list[i].id}}','2');">复制</a>
			<a href="###" onclick="uptZt('{{d.list[i].id}}','{{d.list[i].zt}}');">
				{{#	var zt;
					if(d.list[i].zt =="1"){
						zt="<font color='red'>禁用</font>";
              		}else{
						zt="<font color='green'>启用</font>";
					}
				}}
				{{zt}}
			</a>
		</td>
		<td class="td_center" title="{{ $.nullToEmpty(d.list[i].ex.WDPTMC) }}"><div class="short1">{{ $.nullToEmpty(d.list[i].ex.WDPTMC) }}</div></td>		
        <td class="td_center" title="{{ $.nullToEmpty(d.list[i].wdZclx)}}"><div class="short1">{{ $.nullToEmpty(d.list[i].wdZclx)}}</div></td>
		<td class="td_center" title="{{$.nullToEmpty(d.list[i].hkgs) }}"><div class="short1">{{ $.nullToEmpty(d.list[i].hkgs) }}</div></td>		
		<td class="td_center" title="{{ $.nullToEmpty(d.list[i].cw) }}"><div class="short1">{{ $.nullToEmpty(d.list[i].cw) }}</div></td>		
        <td class="td_center" title="{{ $.nullToEmpty(d.list[i].mbcw)}}">
			{{#	if($.nullToEmpty(d.list[i].mblx) == "2"){  }}
				<div class="short1">{{ $.nullToEmpty(d.list[i].mbcw)}}</div>
			{{# }else{ }}
				{{ $.nullToEmpty(d.list[i].mbcwcj) }}
			{{# } }}
		</td>
		<td class="td_center" title="{{$.nullToEmpty(d.list[i].pnrzt) }}"><div class="short1">{{ $.nullToEmpty(d.list[i].pnrzt) }}</div></td>		
		<td class="td_center">
			{{#	if($.nullToEmpty(d.list[i].cpzt) == "1"){  }}
				已出票
			{{# }else{ }}
				未出票
			{{# } }}
		</td>
		<td class="td_right">{{ $.nullToEmpty(d.list[i].tqts) }}</td>
		<td class="td_right">{{ $.nullToEmpty(d.list[i].yxdj) }}</td>
		<td class="td_center">{{ $.dateF(d.list[i].xgsj,'MM-dd HH:mm') }}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.XGYH.xm) }}</td>
		<td class="td_center">
			{{# var ztmc;}}
			{{#		if(  $.nullToEmpty(d.list[i].zt) =="1"){ 
						ztmc="<font color='green'>已启用</font>"
					}else{
						ztmc="<font color='red'>已禁用</font>"
					}
			}}
			{{ztmc}}
		</td>
       </tr>
	   {{# } }}
	 </table>
   </script>
   <title>追位设置管理</title>
  </head>
  
  <body>
  	<%@include file="/WEB-INF/views/jpzwgl/gzlable.jsp"%>
    <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/jpzwgl/jptjsqzwsz/zwgzlist" id="searchForm" name="searchForm" method="post">
	            <input type="hidden"  name="pageNum" value="${SEARCH_PARAM.pageNum}" id="pageNum"/>
				<input type="hidden"  name="orderBy" value="xgsj desc" id="orderBy"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td class="td_right">航空公司</td>
	                  <td>
	                  	  <input type="text" id="gn_hkgs_m" value="" class="input-text lh25 srk" onchange="clearValue(this,'gn_hkgs');"/>
   					 	  <input type="hidden" id="gn_hkgs" name="hkgs" value="">
	                  </td>
	                  <td class="td_right">舱位</td>
	                  <td>
		                  <input type="text" name="cw" id="cw" value="" class="input-text lh25 srk" onkeyup="value =value.toUpperCase();value=strTrim(this.value);"  title="支持模糊查询">
	                  </td>
	                  <td class="td_right">目标舱位</td>
	                  <td>
		                  <input type="text" name="mbcw" id="mbcw" value="" class="input-text lh25 srk" onkeyup="value =value.toUpperCase();value=strTrim(this.value);"  title="支持模糊查询">
	                  </td>
	                  <td class="td_right">网店平台</td>
	                  <td>
		                  <select name="wdpt" id="wdpt" class="select srk"> 
		                  	 	<option value="">全部</option> 
		                  	 	<c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
		                  	 		<c:if test="${onewdpt.parid ne 'none'}">
		                  	 			<option value="${onewdpt.id }">${onewdpt.mc }</option>
		                  	 		</c:if>
		                  	 	</c:forEach>
		                  </select>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="td_right">PNR状态</td>
	                  <td>
		                  <input type="text" name="pnrzt" id="pnrzt" value="" class="input-text lh25 srk" onblur="value=$.trim(this.value).toUpperCase();"  title="支持模糊查询">
	                  </td>
	                  <td class="td_right">出票状态</td>
	                  <td>
	                  	 <select name="cpzt" id="cpzt" class="select srk"> 
	                  	 	<option value="">全部</option> 
	                        <option value="0">未出票</option> 
	                        <option value="1">已出票</option> 
	                     </select> 
	                  </td>
	                  <td class="td_right">状态</td>
	                  <td>
	                  	 <select name="zt" id="zt" class="select srk"> 
	                  	 	<option value="">全部</option> 
	                        <option value="1">启用</option> 
	                        <option value="0" >禁用</option> 
	                     </select> 
	                  </td>
	                  <td colspan="2" align="right">
	                      <input type="button"  id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询"> 
	                  </td>
	                </tr>
	              </table>
	              </form>
            </div>
          </div>
        </div>
        <input type="button" class="ext_btn ext_btn_success" value="新增" onclick="insertZwgz()"> 
      	<input type="button" class="ext_btn" onclick="batchDel()" value="批量删除">
		<input type="button" class="ext_btn ext_btn_submit" onclick="batchUptZt('1')" value="批量启用">
		<input type="button" class="ext_btn ext_btn_submit" onclick="batchUptZt('0')" value="批量禁用">
		<input type="hidden" class="ext_btn ext_btn_submit" onclick="toBatchUptKrgz()" value="批量修改">
      <div  class="mt10">
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
    </div>
  </div>
  </body>
</html>
