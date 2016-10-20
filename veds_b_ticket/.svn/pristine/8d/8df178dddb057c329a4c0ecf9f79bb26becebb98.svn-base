<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>销存报表</title>
		<script type="text/javascript">
		$(function(){
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
							var sel='${param.bmbh}'==list[i].id ? 'selected':''
							var $opt="<option value="+list[i].id+" "+sel+">"+list[i].mc+"</option>";
							$("#bmbh").append($opt);
						}
					}
				}
			});
			
		});
		//点击查询
		 function queryPage(){
		 		var ii = layer.load('系统正在处理您的操作,请稍候!');
			   	$("#searchForm").submit();
		 }
		function changeTj(type){
		  	if(type == "1"){
		  		$("#bm").html("入库部门:");
		  	}else{
		  		$("#bm").html("领用部门:");
		  	}
		 }
		 
		  //vefun:票证状态，0：在库，1未使用，出票。。。
 		function detail(pzzt,startno,endno,BMBH){
 			var ksrq=$("input[name='ksrq']").val();
 			var jsrq=$("input[name='jsrq']").val();
 			var url='${ctx}/vedsb/pzzx/xcbb/viewbbmx?pzzt='+pzzt+'&startno='+startno+'&endno='+endno+'&BMBH='+BMBH+'&ksrq='+ksrq+'&jsrq='+jsrq;
 		    vopen(url);
 		}
 		function vopen(url, name, w, h) {//	if (! OWinID || OWinID.closed)
		    if(w==null||w==""){w=800;}
		    if(h==null||h==""){h=500;}
		    var top,left;
		    var pws=getPageSize();
		    top=(pws[5]-h)/2 -20;
		    left=(pws[4]-w)/2;
		   // getPageSizenew Array(pageWidth,pageHeight,windowWidth,windowHeight,screen_width,screen_height)
			OWinID = window.open(url, name, "height=" + h + ",width=" + w + ",top="+top+",left="+left+",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes");
			OWinID.focus();
		}  

			/*屏幕尺寸*/
		 function getPageSize(){
			var xScroll, yScroll; 
			if (window.innerHeight && window.scrollMaxY) { 
				xScroll = document.body.scrollWidth; 
				yScroll = window.innerHeight + window.scrollMaxY; 
			} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac 
				xScroll = document.body.scrollWidth; 
				yScroll = document.body.scrollHeight; 
			} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari 
				xScroll = document.body.offsetWidth; 
				yScroll = document.body.offsetHeight; 
			} 
			var windowWidth, windowHeight; 
			if (self.innerHeight) { // all except Explorer 
				windowWidth = self.innerWidth; 
				windowHeight = self.innerHeight; 
			} else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode 
				windowWidth = document.documentElement.clientWidth; 
				windowHeight = document.documentElement.clientHeight; 
			} else if (document.body) { // other Explorers 
				windowWidth = document.body.clientWidth; 
				windowHeight = document.body.clientHeight; 
			} 
		// for small pages with total height less then height of the viewport 
			if(yScroll < windowHeight){ 
				pageHeight = windowHeight; 
			} else { 
				pageHeight = yScroll; 
			} 
		// for small pages with total width less then width of the viewport 
			if(xScroll < windowWidth){ 
				pageWidth = windowWidth; 
			} else { 
				pageWidth = xScroll; 
			}
			var screen_height = window.screen.availHeight; 
			var screen_width = window.screen.availWidth; 
			arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight,screen_width,screen_height);
			return arrayPageSize; 
		}
	</script>
	</head>
<body>
	<div class="container_clear">
		<div id="search_bar" class="mt10">
			<div class="box">
				<div class="box_border">
					<div class="box_center pt10 pb10">
						<form action="${ctx}/vedsb/pzzx/xcbb/queryXcbb" id="searchForm" name="searchForm" method="post">
							<input type="hidden" name="order by" value="CZRQ desc"/>
							<table class="table01" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>开始日期：</td>
									<td>
										<input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}"
											class="input-text Wdate" size="11" onClick="WdatePicker()" />
									</td>
									<td>结束日期：</td>
									<td>
										<input type="text" name="jsrq" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}"
										 class="input-text Wdate" size="11" onClick="WdatePicker()" />
									</td>
									<td>统计方式：</td>
									<td>
										<select name="tjfs" style="width: 155px;height:24px;" id="tjfs" onchange="changeTj(this.value);">
											<option value="1" ${(empty param.tjfs or param.tjfs eq '1') ? 'selected' : ''  }>按入库部门、号段统计</option>
											<option value="2" ${param.tjfs eq '2' ? 'selected' : '' }>按领用部门、号段统计</option>
										</select>
									</td>
									<td>
										<input type="button" value=" 查 询 "class="ext_btn ext_btn_submit" id="searchFormButton" onclick="queryPage();">
									</td>
								</tr>
								<tr>
									<td>票证类型：</td>
									<td>
										 <select name="pztype" class="select srk" datatype="*" style="width: 110px;height:24px;"> 
										   <c:forEach items="${vfc:getVeclassLb('7202')}" var="onecgly">
										   		<c:if test="${onecgly.parid ne 'none'}">
							                       <option value="${onecgly.id}" ${param.pztype eq onecgly.mc ? 'selected':'' }>${onecgly.mc}</option>
							                    </c:if>
						                    </c:forEach>
					                     </select>
									</td>
									<td id="bm">
										<c:if test="${empty param.tjfs or param.tjfs eq '1' }">
											入库部门:
										</c:if>
										<c:if test="${param.tjfs eq '2' }">
											领用部门:
										</c:if>
									</td>
									<td>
										<select id="bmbh" name="bmbh" style="width: 108px;height: 24px;">
					 						<option value="">请选择</option>
										 </select>
									</td>
									<td>OFFICE号</td>
									<td>
										<select id="officeid" name="officeid" datatype="*" class="select" style="width:155px;height:24px;">
									 		<option value="">请选择</option> 
									   		<c:forEach items="${jpfn:officeIdList(BUSER.shbh)}" var="officeid">
									   			<option value="${officeid}" >${officeid}</option>
						                   	</c:forEach>
						                 </select>
									</td>
									<td>
										<input type="button" value=" 导 出 "class="ext_btn ext_btn_submit">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div>
		<c:if test="${empty list || fn:length(list) <= 0 }">
			没有找到相关数据！
		</c:if>
		<c:if test="${fn:length(list) > 0 }">
			<c:choose>
				<%-- 1.按入库部门号段统计 --%>
				<c:when test="${empty param.tjfs or param.tjfs eq 1}">
					<%@include file="tjfs/rkbmhd.jsp" %>
				</c:when>
				<%-- 2.按领用部门号段统计 --%>
				<c:when test="${param.tjfs eq 2 }">
					<%@include file="tjfs/lybmhd.jsp" %>
				</c:when>
			  </c:choose>
			</c:if>
		</div>
	</div>
</body>
</html>