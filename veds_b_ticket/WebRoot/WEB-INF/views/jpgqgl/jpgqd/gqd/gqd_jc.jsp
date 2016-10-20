<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">	
	//关闭页面
	function closePage(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
	//改签页面降舱生成新订单
	function generateNewOrder(){
		var params={};
		params['ddbh']=$("#ddbh").value;
		params['gqdh']=$("#gqdh").value;
		params['fjjx']=$("#fjjx").value;
		params['cfsj']=$("#cfsj").value;
		params['hzl']=$("#hzl").value;
		params['hbh']=$("#hbh").value;
		params['cw']=$("#cw").value;
		params['price']=$("#price").value;
		params['pnr_osi']=$("#pnr_osi").value;
		params['dz_officeid']=$("#dz_officeid").value;
		params['pnr_tldatetime']=$("#pnr_tldatetime").value;
		var url="${ctx}/vedsb/jpddgl/jpkhdd/generateNewOrder";
		$.ajax({
			type:"post",
			url:url,
			data:params,
			success:function(result){
				if("1" == result){
 					layer.msg('新订单生成成功！',2,1);
				}else{
					layer.msg(result,2,1);
				}
				window.close();
			}
		});
	}
	
   //换PNR出票
   function enterChangePnrPage(id){
	   var url ="${ctx}/vedsb/jpddgl/jpkhdd/enterChangePnrPage_"+id+"?frompage=gqxq";
	   $.layer({
			type: 2,
			title: ['<b>换PNR出票</b>'],
			area: ['500px', '200px'],
			iframe: {src: url}
	   });
   }
  
 //降舱出票或降舱生成新订单
  function xgPnr(ddbh,frompage,gqdh,cw,price,fjjx,cfsj,ddsj,hzl,hbh) {
	  var url = "${ctx}/vedsb/jpddgl/jpkhdd/checkXgpnr_"+ddbh+"?jcbs=1&frompage="+frompage+"&gqdh="+gqdh+"&cw="+cw+"&price="+price+"&fjjx="+fjjx+"&cfsj="+cfsj+"&ddsj="+ddsj+"&hzl="+hzl+"&hbh="+hbh;
	  $.layer({
			type: 2,
			title: ['<b>降舱</b>'],
			area: ['500px', '200px'],
			iframe: {src: url}
	   });
  }
   
   //取消
	function changePnrAndCw(id){
  		var url = "${ctx}/vedsb/jpddgl/jpkhdd/changePnrAndCw?id="+id;
  		$.layer({
  			area: ['250px', '150px'],
  			dialog : {
  				 msg : "您确定要降舱吗？",
  				 btns: 2,
	        	 type: 4,
	        	 btn : ['确定','取消'],
	        	 yes : function(){
	        	 	$.ajax({
	        	 		type:"post",
	  					url:url,
	  					success:function(result){
	  						if("1" == result){
	  							layer.msg("降舱成功！",2,1);
	  						}else{
	  							layer.msg("降舱失败！",2,1);
	  						}
	  					}
	        	 	});
	        	 },no: function(){
	             	layer.msg("降舱操作", 2, 3);
	          	 }
  			}
  		});
  	}
</script>	
<style type="text/css">
body{font-size:12px;}
.cjr_info {padding:0 10px;line-height:22px;}
.current_cw{padding:0 10px;line-height:30px;font-weight:bold;background:#dedede;border-bottom:1px solid #fff;}
.current_cw .cw{display:inline-block;width:30px; font-family:Arial, Helvetica, sans-serif;font-size:14px;}
.current_cw .price{color:#f94304;font-family:Arial, Helvetica, sans-serif;font-size:14px;display:inline-block;width:60px;}
.low_cw{padding:0 10px;line-height:24px;background:#eee;}
.low_cw span{width:30px;display:inline-block;}
.low_cw .cw{width:30px; font-family:Arial, Helvetica, sans-serif;font-size:14px;}
.low_cw .price{color:#f94304;font-family:Arial, Helvetica, sans-serif;font-size:14px;width:60px;}
.btn_fresh{ background:#246fd4;color:#fff;padding:0 8px;margin:5px 0;font-family:Arial;text-align:center;height:26px;line-height:26px;cursor:pointer;display:inline-block;border:none;}
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
.noncewb{ height:35px; width:73px; background:url(/static/images/wdimages/non_bg.png) no-repate;}
.web_button{height:25px;background:#f1f1f1;border:1px solid #ccc;border-radius:3px;cursor:pointer;}
</style>
<div class="cjr_info">
  <table width="100%" border="0">
    <tr>
      <td>
      		<span class="headtitle">原PNR</span>
		  	<span class="wb_td02">${jpkhdd.XS_PNR_NO }</span><br>
		  	<span class="headtitle">航程</span>
		  	<span class="wb_td02">${jpkhddHd.cfcity }${jpkhddHd.ddcity }</span>
		  	<span class="headtitle">航班号</span>
		  	<span class="wb_td02">${jpkhddHd.xsHbh }</span>
		  	<span class="headtitle">起飞时间</span>
		  	<span class="wb_td02">${jpkhddHd.cfsj } </span>
      		<span class="headtitle">乘机人</span>
		  	<span class="wb_td02">${jpkhdd.CJR } </span>
      </td>
    </tr>
  </table>
</div>
<div class="current_cw">
  <table width="100%" border="0">
    <tr>
      <td width="70">当前舱位：</td>
      <td width="100">
      	  <span class="cw">${currentAvCabin.cw }${currentAvCabin.cwNum }</span> 
      	  <span class="price" title="当前订单舱位票价"><fmt:formatNumber pattern="#####">${currentAvCabin.price }</fmt:formatNumber></span>
      </td>
      <td>
      	  <input type="button" value="换PNR出票"  class="ext_btn ext_btn_submit" onclick="enterChangePnrPage('${jpkhdd.DDBH}')"/>
      </td>
    </tr>
  </table>
</div>
<div class="low_cw">
  <table width="100%" border="0">
  	<c:forEach items="${cabinList}" var="oneCw" varStatus="vs">
  		<c:if test="${oneCw.price gt 0}" var="havaPrice"></c:if> 
	    <tr>
	      <c:if test="${vs.index eq 0}">
	       	<td width="70">更低舱位：</td>
	      </c:if>
	      <c:if test="${vs.index gt 0}">
	     	<td>&nbsp;</td>
	      </c:if>
	      <td>
	      	<span class="cw">${oneCw.cw }${oneCw.cwNum }</span>
	      	<span id="price_span_${vs.index }" class="price" style="display:${havaPrice  ? '' : 'none'}"><fmt:formatNumber pattern="#####">${oneCw.price }</fmt:formatNumber></span>
	      	<c:if test="${!havaPrice}">
	      		<label><a href="###" onclick="pat(this,'${oneCw.cw }',${vs.index })">点击PAT</a></label>
	      	</c:if>
	      </td>
	      <td id="subprice_td_${vs.index }" style="display:${havaPrice ? '' : 'none'}">
	      	<span title="差价=订单上舱位票价-当前舱位票价，差价大于0，方可进行换编码操作">差价</span>
	      	<span id="subprice_span_${vs.index }" class="price"><fmt:formatNumber pattern="#####">${oneCw.subPrice }</fmt:formatNumber></span>
	      </td>
	      <td id="operate_span_${vs.index }" style="display:${(havaPrice and oneCw.subPrice gt 0)  ? '' : 'none'}">
	        <c:if test="${param.frompage eq 'gqxq'}">
	      		<a href="###" title="生成新订单" id="new_cw_price_a_${vs.index }" new_cw_price="${oneCw.price }"  onclick="xgPnr('${jpkhdd.ddbh}', '${param.frompage}','${param.gqdh}','${oneCw.cw}','${oneCw.price}','${oneCw.fjjx}','${oneCw.cfsj}','${oneCw.ddsj}','${oneCw.hzl}','${oneCw.hbh}')">降舱</a>
	      	</c:if>
	      	<c:if test="${param.frompage ne 'gqxq'}">
	      		<a href="###" title="原编码座位保留，需手工XE" id="new_cw_price_a_${vs.index }" new_cw_price="${oneCw.price }" onclick="changePnrAndCw('${jpkhdd.DDBH}');">降舱</a>
	      	</c:if>
	      </td>
	    </tr>
    </c:forEach>
  </table>
</div>

<p style="text-align:center;padding:5px 5px" id="autoCloseTip_p">
	<input type="button" name="button" onclick="closePage()" class="ext_btn ext_btn_submit" value="关闭">
</p>
<script type="text/javascript">	
	
</script>	