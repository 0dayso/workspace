<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<c:if test="${param.zdlx eq '1'}">
			<c:set var="imgurl" value="${ctx}/static/images/wdgzt/tb"></c:set>
			<c:set var="count" value="2"></c:set>
		</c:if>
		<c:if test="${param.zdlx eq '2'}">
			<c:set var="imgurl" value="${ctx}/static/images/wdgzt/qnr"></c:set>
			<c:set var="count" value="3"></c:set>
		</c:if>
		<c:if test="${param.zdlx eq '3'}">
			<c:set var="imgurl" value="${ctx}/static/images/wdgzt/xc"></c:set>
			<c:set var="count" value="3"></c:set>
		</c:if>
		<script type="text/javascript">
			var step=1;
			function next(){
				step++;
				$('#bzimg').attr("src",'${imgurl}'+step+'.png');
				if(step==${count}){
					$('#next').css("display","none");
				}
				$('#last').css("display","");
			}
			function last(){
				step--;
				$('#bzimg').attr("src",'${imgurl}'+step+'.png');
				if(step==1){
					$('#last').css("display","none");
				}
				$('#next').css("display","");
			}
		</script>
	</head>
	<body>
		<div style="text-align: center;padding:30px 0;">
			<span style="position:absolute;margin-left:-35px;margin-top:98px;cursor:pointer;display:none;" id="last">
				<img src="${ctx}/static/images/wdgzt/left.png" align="middle" onclick="last()" title="上一步"/>
			</span>
			<span style="width:580;height:300;overflow: hidden;position:relation;">
				<img src="${imgurl}1.png" id="bzimg"/>
			</span>
			<span style="position:absolute;margin-left:10px;margin-top:98px;cursor:pointer;" id="next">
				<img src="${ctx}/static/images/wdgzt/right.png" align="middle" onclick="next()" title="下一步"/>
			</span>
		</div>
	</body>
</html>