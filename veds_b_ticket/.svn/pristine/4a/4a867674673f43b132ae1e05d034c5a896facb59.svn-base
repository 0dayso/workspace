<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<style>
	.input: {
		width: 50px;
	}
	
	.td_right {
		text-align: right;
	}
	
	.td_left {
		text-align: left;
	}
	a:link{
		text-decoration:none;
	}
</style>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
<script type="text/javascript">
	$(function(){	
	   	//航司控件
	    $("#gn_hkgs_m").autocompleteGnHkgs("gn_hkgs");
	    $("#searchFormButton").click();
	})
 	//改变选择时间类型
	function setRqtj(){
		var rqtj = document.searchForm.rqtj.value;
  		if(rqtj == "2"){
  			document.getElementById("rq1").innerHTML="起飞日始";
  			document.getElementById("rq2").innerHTML="起飞日止";
  		}else{
  			document.getElementById("rq1").innerHTML="发布日始";
  			document.getElementById("rq2").innerHTML="发布日止";
  		}
	}
   function refresh(){
	  $("#searchFormButton").click();
	}
	function release(){
	   $.layer({
		   type: 2,
		   title: ['航班异动新增'],
		   area: ['550px', '440px'],
		   iframe: {src: "${ctx}/vedsb/jphbyd/jphbyd/viewedit"}
	   });
    }
   
	function update(id){
		$.layer({
			type: 2,
			title: ['航班异动修改'],
			area: ['550px', '440px'],
			iframe: {src: "${ctx}/vedsb/jphbyd/jphbyd/edit_"+id}
	    });
    }
    /*删除异动*/
	function deleteById(id,zt){
		//弹出删除框
		if(zt=="1"){
			  layer.msg("当前状态为[已审核]无法删除");
			  return;
		  }
		  var url="${ctx}/vedsb/jphbyd/jphbyd/delete?id="+id ;
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
    /*更新异动状态*/
	 function uptZt(id,zt){
	     if(zt=="0"){
	    	cz="审核";
	    	zt='1';
	      }	
	  var url = "${ctx}/vedsb/jphbyd/jphbyd/upZt?id="+id+"&zt="+zt+"&time="+new Date().toString();
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
	function set(){
		$.layer({
			type: 2,
			title: false,
			area: ['800px', '440px'],
			iframe: {src: "${ctx}/vedsb/jphbyd/bqinfohbydgz/queryList"}
	    });
	}
	
	function hbydCl(hbh,hc,cfrq,zt,yjcfsj,id,lx,ydyy){
		$.layer({
			type: 2,
			title: ['航班异动处理'],
			area: ['1000px', '600px'],
			iframe: {src: "${ctx}/vedsb/jphbyd/jphbyd/viewhbydcllist?hbh="+hbh+"&hc="+hc+"&cfrq="+cfrq+"&zt="+zt+"&yjcfsj="+yjcfsj+"&id="+id+"&lx="+lx+"&ydyy="+ydyy}
	    });
	}
</script>
<script id="tpl_list_table" type="text/html">
	<table width="2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th rowspan="2" width="40px">序号</th>
			<th rowspan="2" width="120px">操作</th>
            <th rowspan="2" width="80px">状态</th>
			<th rowspan="2" width="60px">航程</th>
			<th rowspan="2" width="60px">原航班</th>
			<th rowspan="2" width="120px">原航班起飞时间</th>
			<th rowspan="2" width="120px">原航班到达时间</th>
			<th rowspan="2" width="60px">保护<br>航班号</th>
            <th rowspan="2" width="60px">类型</th>
            <th rowspan="2" width="50px">时长(分)</th>
			<th rowspan="2" width="180px">航变原因</th>
			<th rowspan="2" width="120px">预计起飞时间</th>
			<th rowspan="2" width="120px">预计到达时间</th>
			<th colspan="3" width="120px">受影响订单</th>
			<th rowspan="2" width="80px">操作来源</th>
			<th rowspan="2" width="80px">发布人</th>
			<th rowspan="2" width="120px">发布时间</th>
		</tr>
		<tr>
			<th width="40px">全部</th>
			<th width="40px">已处理</th>
            <th width="40px">未处理</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
				<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
					<a href="###"  onclick="update('{{d.list[i].ID}}')">编辑</a>&nbsp;
					<a href="###"  onclick="deleteById('{{d.list[i].ID}}','{{d.list[i].ZT}}')">删除</a>&nbsp;
                    {{#	if($.nullToEmpty(d.list[i].ZT) == "0"){  }}
					<a href="###"  onclick="uptZt('{{d.list[i].ID}}','{{d.list[i].ZT}}')">审核</a>&nbsp;
			        {{# } }}
				</td>
				<td class="td_center">
                 {{#	if($.nullToEmpty(d.list[i].ZT) == "1"){  }}
					<font color='green'>已审核</font>
			    {{# }else{ }}
					<font color='red'>未审核</font>
			    {{# } }}
                </td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HC) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HBH) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CFRQ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].BY2) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].BY1) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HBLX ) == 2 ? '延误' : (d.list[i].HBLX == 1 ? '提前' : '取消' ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].YWSC ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].YDYY) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].YJ_CFSJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].YJ_DDSJ) }}</td>
				<td class="td_center"><a href="###"  onclick="hbydCl('{{d.list[i].HBH}}','{{d.list[i].HC}}','{{d.list[i].CFRQ}}','{{d.list[i].ZT}}','{{d.list[i].YJ_CFSJ}}','{{d.list[i].ID}}','1','{{d.list[i].YDYY}}')">{{  $.nullToEmpty(d.list[i].ALLDD) }}</a></td>
				<td class="td_center"><a href="###"  onclick="hbydCl('{{d.list[i].HBH}}','{{d.list[i].HC}}','{{d.list[i].CFRQ}}','{{d.list[i].ZT}}','{{d.list[i].YJ_CFSJ}}','{{d.list[i].ID}}','2','{{d.list[i].YDYY}}')">{{  $.nullToEmpty(d.list[i].YCLDD) }}</a></td>
				<td class="td_center"><a href="###"  onclick="hbydCl('{{d.list[i].HBH}}','{{d.list[i].HC}}','{{d.list[i].CFRQ}}','{{d.list[i].ZT}}','{{d.list[i].YJ_CFSJ}}','{{d.list[i].ID}}','3','{{d.list[i].YDYY}}')">{{  $.nullToEmpty(d.list[i].ALLDD - d.list[i].YCLDD ) }}</a></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CZLY)  }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].YHBH) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CJTIME) }}</td>
    		</tr>
		{{# } }}
	</table>
</script>
</head>
<body>
<div class="container_clear">
	<div class="box_border">
    	<form action="${ctx}/vedsb/jphbyd/jphbyd/queryPage" id="searchForm" name="searchForm" method="POST">
		<input type="hidden"  name="VIEW" value="692A3B3046E69162F490FF0C1E16BCF1" />
		<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
		<input type="hidden"  name="sortType" value="cjtime desc" id="sortType"/>
		<input type="hidden"  name="pageSize" value="20" id="pageSize"/>
		<table class="table01" border="0" width="80%" cellpadding="0" cellspacing="0">
			<tr>
				<td class="td_right">
					日期条件
				</td>
				<td class="td_left">
					<select class="input-text lh25 srk" id="rqtj" name="rqtj" onchange="setRqtj()" style="width:102px; height:24px;">
					  <option value="1" ${param.rqtj eq '1' ? 'selected' : '' }>发布时间</option>
					  <option value="2" ${param.rqtj eq '2' ? 'selected' : '' }>起飞时间</option>
					</select>
				</td>
				<td class="td_right" id="rq1">
					发布日始
				</td>
				<td class="td_left">
					<input type="text" name="kssj"  class="input-text Wdate" value="${empty param.kssj ? vfn:dateShort(): param.kssj}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 80%;" />
				</td>
				<td class="td_right" id="rq2">
					发布日止
				</td>
				<td class="td_left">
					<input type="text" name="jssj"  class="input-text Wdate" value="${empty param.jssj ? vfn:dateShort(): param.jssj}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 80%;" />
				</td>
				<td class="td_right">
					航空公司
				</td>
				<td class="td_left">
					<input type="text" id="gn_hkgs_m" value="" class="input-text lh25 srk" onchange="clearValue(this,'gn_hkgs');" style="width:80%;"/>
					<input type="hidden" id="gn_hkgs" name="hkgs" value="${param.hkgs }">
				</td>
			  	<td class="td_right">
					航&nbsp;班&nbsp;号
				</td>
				<td class="td_left">
					<input type="text" name="hbh" value="${param.hbh }" class="input-text lh25 srk" onkeyup="value =value.toUpperCase();value=strTrim(this.value)" size="10" style="width:80%;" />
				</td>
			</tr>
			<tr>
				<td class="td_right">
					航&nbsp;&nbsp;程
				</td>
				<td class="td_left">
					<input type="text"  name="hc" value="${param.hc}" style="width:100px;height:24px;" class="input-text lh25 srk" onkeyup="value =value.toUpperCase();value=strTrim(this.value);"/>
				</td>
				<td class="td_right">
					操作来源
				</td>
        		<td class="td_left">
        			<select class="input-text lh25 srk" id="czly" name="czly" style="width: 83%;">
        				<option value=""  ${empty param.czly  ? 'selected' : '' }>==请选择==</option>
		  				<option value="手动发布" ${param.czly eq '手动发布' ? 'selected' : '' }>手动发布</option>
		  				<option value="自动生成" ${param.czly eq '自动生成' ? 'selected' : '' }>自动生成</option>
		  				<option value="Q信息自动生成" ${param.czly eq 'Q信息自动生成' ? 'selected' : '' }>Q信息自动生成</option>
        			</select>
        		</td>
        		<td></td>
				<td style="text-align: center;" colspan="3">
					<input type="button" id="releaseButton" name="button" value="发布" onclick="release();" class="ext_btn ext_btn_success" />
					<input type="button" id="searchFormButton"  class="ext_btn ext_btn_success" value="查询">
					<input type="button" id="setButton" name="button" value="设置" onclick="set();" class="ext_btn ext_btn_success" />
				</td>
			</tr>
		</table>
      </form>
    </div>
	<div  class="mt10" style="display: table;">
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