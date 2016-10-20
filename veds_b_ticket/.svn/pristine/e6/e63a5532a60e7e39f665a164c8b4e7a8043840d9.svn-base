<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	var hqcolVal = function hqColorValue(){
		//获取颜色提示值
			var url="${ctx}/vedsb/colorremind/colorremind/query";
			$.ajax({
				url:url,
				type:"post",
				data:{"bh":"2024"},
				dataType:"json",
				success:function(result){
					if(result.CODE == '0'){
						var data=result.DATA;
						for(var i=0;i<data.length;i++){
							var cz=data[i].csz2.split("|");
							var bg1=cz[0];
							var bg2=cz[1];
							var bg3=cz[2];
							var name=data[i].csz1;
							$("#"+name+"_dt").val(bg1);
							$("#"+name+"_ltn").val(bg2);
							$("#"+name+"_lth").val(bg3);
							<c:forEach var="vc" items="${vfc:getVeclassLb('10014')}" varStatus="vs">
								$("#"+name+"_${vc.ywmc}").val(cz[${vs.index}]);
 							</c:forEach>
						}
					}else{
						layer.msg(result.MSG,2,5);
					}
				}
			});
			}
	var hqMrPxValue=function hqMrpxValue(){
		//获取默认排序的值
		var url="${ctx}/vedsb/colorremind/colorremind/query";
		$.ajax({
			url:url,
			type:"post",
			data:{"bh":"2025"},
			dataType:"json",
			success:function(result){
				if(result.CODE == '0'){
					var list=result.DATA;
					//为空则用户没设置，即按默认方式排序，正常单 预定时间 降   出票控制台 出发日期  升
					if(list == null || list == '' ){
						$("#pxzd").val($("#order").val());
						$("input[name='px'][value="+$("#by").val()+"]").attr("checked",true);
					}else{
						$("#orderBy").val(list[0].csz1+" "+list[0].csz2);
						$("input[name=px][value="+list[0].csz2+"]").attr("checked",true);
						$("#pxzd").val(list[0].csz1);
					}
				}else{
					layer.msg(result.MSG,2,5);
				}
			}
		});
	}
	$(function(){
		hqcolVal();
		hqMrPxValue();
	});
		
</script>
<input type="hidden" id="now" value="${vfn:dateShort() }"/>
<input type="hidden" id="now2" value="${vfn:getPreDay(vfn:dateShort(),2) }" />

<input type="hidden" id="cfrq_dt" value="">
<input type="hidden" id="cfrq_ltn" value="">
<input type="hidden" id="cfrq_lth" value="">

<input type="hidden" id="ydsj_dt" value="">
<input type="hidden" id="ydsj_ltn" value="">
<input type="hidden" id="ydsj_lth" value="">

<input type="hidden" id="nosj_dt" value="">
<input type="hidden" id="nosj_ltn" value="">
<input type="hidden" id="nosj_lth" value="">

<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
	<c:if test="${oneLx.id ne '10014'}">
		<input type="hidden" name="pzlx" id="pzlx_${oneLx.ywmc}" value=""/>
	</c:if>
</c:forEach>

