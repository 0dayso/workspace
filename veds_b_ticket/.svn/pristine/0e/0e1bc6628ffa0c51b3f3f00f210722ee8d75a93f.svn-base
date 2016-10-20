<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<ul>
	<li>
		<span class="current" id="ptzcbs_all_span" onclick="changeAnyPlat(this)" ptzcbs="all" style="width:50px">全部</span>
	</li>
	<c:forEach items="${ptzhList}" var="one">
		<c:if test="${one.open1 eq '1'}">
			<li>
				<span id="ptzcbs_${one.ptzcbs}_span" title="点击获取单个平台政策" onclick="changeAnyPlat(this);"  ptzcbs="${one.ptzcbs}" platname="${one.ex.PLATNAME}">${one.ex.PLATNAME}</span>
				<c:if test="${one.ptzcbs ne '10100000' and  one.ptzcbs ne '10100011'}">
					<span>
						<input type="checkbox"   name="ptzcbs_chk" value="${one.ptzcbs }" id="ptzcbs_${one.ptzcbs }_chk" onClick="setLoadPlat(this);" platname="${one.ex.PLATNAME}"/>
					</span>
				</c:if>
			</li>
		</c:if>
	</c:forEach>
	<li style="background:none;border:none">
		<label><input type="checkbox" id="filter_change_pnr_chk"  onclick="setPnrTjCookie(this,'glhbm')"/>过滤换编码</label>
	</li>
	<li style="background:none;border:none">
		<label><input type="checkbox" id="filter_thzc_chk"  onclick="setPnrTjCookie(this,'gltjzc')"/>过滤特惠政策</label>
	</li>
</ul>
