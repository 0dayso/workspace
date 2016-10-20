<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
  <head>
    <title>销售毛利报表</title>
    <link rel="stylesheet" href="${ctx}/static/css/displayTag.css">
    <script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
    <script type="text/javascript">
    	$(function(){
    		var rqtj = '${param.rqtj}';
    		if(rqtj=='4'){
    			$("#rqtj_span1").text("订票日始");
		    	$("#rqtj_span2").text("订票日止");
    		}else{
    			$("#rqtj_span1").text("出票日始");
		    	$("#rqtj_span2").text("出票日止");
    		}
    		$("#gn_hkgs_m").autocompleteGnHkgs("hkgs");
    	});
    	function toSearch(){
    		var name="";
    		var ids="";
    		$(':checkbox[name="cglys"]:checked').each(function(){
	    	    if(name==''){
	    	    	name = $(this).val();
	    	    	name="\'"+name+"\'";
	    	    	ids=$(this).attr("cgids");
	    	    }else{
	    			name=name+","+"\'"+$(this).val()+"\'";
	    			ids=ids+","+$(this).attr("cgids");
	    		}
	    	   
    		});
    		$("#cgly").val(name);
    		$("#cglyids").val(ids);
    		var tj=$("#tjtype").val();
    		$("#searchForm").attr("action","${ctx}/vedsb/jpcwgl/jpxsmlfx/xsmlfx?tjtype="+tj);
            layer.load("系统正在处理您的操作,请稍候!");
            $("#searchForm").submit();
    	}	
    	//日期条件切换
		function change(){
		  if($("#rqtj").val()=='1'){
		    $("#rqtj_span1").text("出票日始");
		    $("#rqtj_span2").text("出票日止");
		  }
		  if($("#rqtj").val()=='4'){
		    $("#rqtj_span1").text("订票日始");
		    $("#rqtj_span2").text("订票日止");
		  }  
		}

        function detail(mxlx,hc,wdpt,tjtype,pzzt,kssj,jssj,rqtj,faid,pnrno,tkno,cjrlx,cjr,hkgs,shclx,hb,tfbz,cgly){
        	 cgly=$("#cgly").val();
    		window.open("${ctx}/vedsb/jpcwgl/jpxsmlfx/mxlist?mxlx="+mxlx+"&hc="+hc+"&wdpt="+wdpt+"&tjtype="+tjtype+"&pzzt="+pzzt+"&kssj="+
    			kssj+"&jssj="+jssj+"&rqtj="+rqtj+"&faid="+faid+"&pnrno="+pnrno+"&tkno="+tkno+"&cjrlx="+cjrlx+"&cjr="+cjr+"&hkgs="+hkgs+
    			"&shclx="+shclx+"&hb="+hb+"&tfbz="+tfbz+"&cgly="+cgly,"",200,400);
        }
        
        jQuery.download = function(url, data, method){    // 获得url和data
		    if( url && data ){ 
		        // data 是 string 或者 array/object
		        data = typeof data == 'string' ? data : jQuery.param(data);        // 把参数组装成 form的  input
		        var inputs = '';
		        jQuery.each(data.split('&'), function(){ 
		            var pair = this.split('=');
		            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
		        });        // request发送请求
		        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>').appendTo('body').submit().remove();
		    };
		};
	
	 function toExport(){
       var url="${ctx}/vedsb/jpcwgl/jpxsmlfx/xsmlfx";
       $.download(url,"export="+$("#vcexpfield").val()+"&"+$("#searchForm").serialize(),"post");
    }
    
    function checkTkno(tkno){
	  var length=tkno.length;
	  if(length<13){
	     layer.alert("请输入正确的票号");
	     return false;
	  }
	  tkno=tkno.replace("-","");
	  tkno=tkno.substring(0,3)+"-"+tkno.substring(3,13);
	  $("#tkno").val(tkno);
 	}
    </script>
  </head>
  <body>
  <c:if test="${empty param.mxlx}">
	<div class="container">
	  	 <div id="search_bar">
	       <div class="box">
			<div class="box_border">
				<div class="box_center pt10 pb10">
					<form action="${ctx}/vedsb/jpcwgl/jpxsmlfx/xsmlfx" id="searchForm" name="searchForm" method="post">
					<table class="table01" border="0" cellpadding="0" cellspacing="0">
					  <tr>
						<td class="xsys" style="text-align: right;"><span id="rqtj_span1">出票日期始</span></td>
						<td>
					       <input type="text" name="kssj" size=10 class="input-text Wdate" value="${empty param.kssj ? vfn:dateShort() : param.kssj }"
					        	onclick="WdatePicker()" onblur="value = replaceAll(value,'%','')"/>
						</td>
						<td class="xsys" style="text-align: right;"><span id="rqtj_span2">出票日期止</span></td>
						<td>
							<input type="text" name="jssj" size=10 class="input-text Wdate" value="${empty param.jssj ? vfn:dateShort() : param.jssj }"
					       		onclick="WdatePicker()" onblur="value = replaceAll(value,'%','')"/>
						</td>
					    <td class="xsys" style="text-align: right;">日期条件</td>
					    <td>
					      <select style="width:90px" name="rqtj" onchange="change();" id="rqtj">
					        <option value="1" ${param.rqtj eq '1' ? 'selected' : ''}>出票日期</option>
					        <option value="4" ${param.rqtj eq '4' ? 'selected' : ''}>订票日期</option>
					      </select>
					    </td>	
					    <td class="xsys" style="text-align: right;">统计方式</td>
					    <td>
							<select name="tjtype" style="width:90px" class="input1" value="${param.tjtype}" id="tjtype">
								<option value="0" ${empty param.tjtype or param.tjtype eq '0' ? 'selected' : ''}>所有明细</option>
								<option value="1" ${ param.tjtype eq '1' ? 'selected' : ''}>按网店平台,网店</option>
								<option value="2" ${ param.tjtype eq '2' ? 'selected' : ''}>按投放方案</option>
								<option value="3" ${ param.tjtype eq '3' ? 'selected' : ''}>按航程</option>
							</select>
						</td>
					  </tr> 
					  <tr>
					  	 <td class="xsys" style="text-align: right;">PNR</td>
					     <td>
					       <input type="text" class="cshnull" name="pnrno" id="pnr" class="input1" value="${param.pnrno}" maxlength="10" style="width:90px;" onblur="value=$.trim(this.value).toUpperCase();" />
					     </td>
					     <td class="xsys" style="text-align: right;">票号</td>
					     <td>
					       <input type="text" class="cshnull" name="tkno" class="input1" value="${param.tkno}" style="width:90px;" maxlength="20" onblur="checkTkno($.trim(this.value).toUpperCase());" id="tkno"/>
					     </td>
					  	 <td class="xsys" style="text-align: right;">乘机人类型</td>
					     <td>
					       <select name="cjrlx" class="cshnull" style="width:94px;">
					         <option value=""  ${ empty param.cjrlx ? "selected" : "" }>==所有==</option>
					         <option value="1" ${ param.cjrlx eq '1' ? "selected" : "" }>成人</option>
					         <option value="2" ${ param.cjrlx eq '2' ? "selected" : "" }>儿童</option>
					         <option value="3" ${ param.cjrlx eq '3' ? "selected" : "" }>婴儿</option>
					       </select>
					     </td>
					     <td class="xsys" style="text-align: right;">乘机人</td>
					     <td>
					      <input type="text" class="input1" name="cjr" size=10 value="${param.cjr}" onblur="value = replaceAll(value,'%','').toUpperCase()" >
					     </td>
					   </tr>
					   <tr>
					    <td class="xsys" style="text-align: right;">航空公司</td>
						<td class="td_val">
						 	 <input type="text" id="gn_hkgs_m" name="gn_hkgs_m" size=10 value="${param.gn_hkgs_m}" onchange="clearValue(this,'hkgs');"/>
   					 	 	 <input type="hidden" id="hkgs" name="hkgs" value="${param.hkgs}">
						</td>
					   <td class="xsys" style="text-align: right;">航程类型</td>
					   <td>
					      	<select name="shclx" id="shclx" style="width:94px;" class="cshnull">
					          <option value="" ${param.shclx eq '' ? 'selected' : ''}>==所有==</option>
					          <option value="1" ${param.shclx eq '1' ? 'selected' : ''}>单程</option>
					          <option value="2" ${param.shclx eq '2' ? 'selected' : '' }>往返程</option>
					          <option value="3" ${param.shclx eq '3' ? 'selected' : '' }>联程</option>
					          <option value="4" ${param.shclx eq '4' ? 'selected' : '' }>缺口程</option>
					        </select>
					    </td>
					  	<td class="xsys" style="text-align: right;">航程</td>
					    <td>
					      <input type="text" class="input1" name="hc" style="width:90px" value="${param.hc}" maxlength="20" onblur="value=$.trim(this.value).toUpperCase();" >
					    </td>
					     <td class="xsys" style="text-align: right;">航班</td>
					    <td>
					      <input type="text" class="input1" name="hb" style="width:90px" value="${param.hb}" maxlength="10" onblur="value=$.trim(this.value).toUpperCase();" >
					    </td>
					  </tr>
					  <tr>
					    <td class="xsys" style="text-align: right;"><span id="s1">票证状态</td>
					  	<td>
							<select name="pzzt" style="width: 94px">
								<option value="">==全部==</option>
								<option value="1" ${param.pzzt eq '1' ? 'selected' : ''}>正常票</option>
								<option value="2" ${param.pzzt eq '2' ? 'selected' : ''}>退票</option>
								<option value="3" ${param.pzzt eq '3' ? 'selected' : ''}>废票</option>
								<option value="4" ${param.pzzt eq '4' ? 'selected' : ''}>改签</option>
							</select>
					  	</td>
					  	<%--<td class="xsys" style="text-align: right;"><span id='tf_flag1'>退废状态</td>
						<td><span id='tf_flag2'>
							<select name="tp_flag" class="input1" style="width:94px" class="cshnull">
					   			<option value="" ${empty param.tp_flag ? 'selected' : ''}>==全部==</option>
					   			<option value="0" ${param.tp_flag eq '0' ? 'selected' : ''}>正常</option>
					   			<option value="1" ${param.tp_flag eq '1' ? 'selected' : ''}>退票已申请</option>
					   			<option value="2" ${param.tp_flag eq '2' ? 'selected' : ''}>废票已申请</option>
					   			<option value="3" ${param.tp_flag eq '3' ? 'selected' : ''}>退票已审核</option>
					   			<option value="4" ${param.tp_flag eq '4' ? 'selected' : ''}>废票已审核</option>
					   			<option value="9" ${param.tp_flag eq '9' ? 'selected' : ''}>退票已完成</option>
					   			<option value="A" ${param.tp_flag eq 'A' ? 'selected' : ''}>废票已完成</option>
					 		</select></span>
						</td>--%>
					  <td class="xsys" style="text-align: right;"><span id="tfbz1">退废标准</td>
					  	<td>
							<select name="tfbz" style="width: 94px">
								<option value="">==全部==</option>
								<option value="1" ${param.tfbz eq '1' ? 'selected' : ''} id="tfbz1">复核</option>
								<option value="2" ${param.tfbz eq '2' ? 'selected' : ''} id="tfbz2">客户办理</option>
								<option value="3" ${param.tfbz eq '3' ? 'selected' : ''} id="tfbz3">采购办理</option>
								<option value="4" ${param.tfbz eq '4' ? 'selected' : ''} id="tfbz4">起飞时间</option>
							</select>
					  	</td>
					  	 <td class="xsys" style="text-align: right;"><span id="ywlx">业务类型</td>
					  	<td>
							<select name="ywlx" style="width: 94px">
								<option value="">==全部==</option>
								<option value="1" ${param.ywlx eq '1' ? 'selected' : ''} id="tfbz1">机票</option>
								<option value="2" ${param.ywlx eq '2' ? 'selected' : ''} id="tfbz2">补差单</option>
							</select>
					  	</td>
					  	<c:if test="${param.tjtype eq '0' or empty param.tjtype}">
					  		<td class="xsys" style="text-align: right;"><span id="tfbz1">对账状态</td>
						  	<td>
						  		<select name="xsdzzt" style="width: 94px">
						  			<option value="">==所有==</option>
						  			<option value="0" ${param.xsdzzt eq '0' ? 'selected' : ''}>未对账</option>
						  			<option value="1" ${param.xsdzzt eq '1' ? 'selected' : ''}>已对账</option>
						  		</select>
						  	</td>
					  	</c:if>
					  </tr>
					  <tr>
					  	 <td colspan="7"><span id='cglys'>采购来源</span>
								<c:forEach items="${vfc:getVeclassLb('10014')}" var="s">
									<c:if test="${s.id ne 10014}">
										<input type="checkbox" name="cglys" cgids="${s.id}"  value="${s.ywmc}" ${fn:contains(param.cglyids,s.id) ? 'checked': ''} id="chk_zt_${s.id}" >
									    ${s.mc}
									 </c:if>
					        	</c:forEach>
					        		<input type="hidden" value="${param.cgly}" name="cgly" id="cgly">
					        		<input type="hidden" value="${param.cglyids}" name="cglyids" id="cglyids">
						    </td>
					    <td>
					  		<input type="button" value="查询" id="search" class="ext_btn ext_btn_submit" onClick="toSearch();"/>
					  		<input type="button" class="ext_btn ext_btn_success" value="导出" onclick="toExport()">
					  	</td>
					</tr>
				</table>
				</form>
				</div>
	        </div>
	       </div>
	    <div  class="mt10"> 
	    </div>
	   </div>
	</div>
	</c:if>
	<c:if test="${empty page.list}">
	没有找到相关数据
	</c:if>
	<c:if test="${not empty page.list}">
	<c:if test="${param.tjtype eq '0'}">
		<%@ include file="mxlist.jsp"%>
	</c:if> 
	<c:if test="${param.tjtype ne '0'}">
		<%@ include file="notMxlist.jsp"%>
	</c:if>
	<input type="hidden" id="vcexpfield" value="${vfn:urid(vcexpfield)}">
	</c:if>
  </body>
</html>
