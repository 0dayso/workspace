<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!-- B2B -->

<%--支付信息 --%>
<%-- 0=手动支付 1=支付宝 2=财付通 3=汇付天下 4=易宝 5=快钱--%>
<input type="hidden" name="mc" value="${empty t_b2b_hkgsxx.mc ? t_b2b_hkgs.mc : t_b2b_hkgsxx.mc}"/>
<input type="hidden" id="hkgs" name="hkgs" value="${t_b2b_hkgsxx.hkgs eq null ? t_b2b_dlzh.hkgs : t_b2b_hkgsxx.hkgs}"/>
<input type="hidden" name="bca" value="${empty t_b2b_hkgsxx.bca ? '720102' : t_b2b_hkgsxx.bca}"/>
<table  border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
    	<td colspan="9" class="title">航空公司：${t_b2b_hkgsxx.hkgs eq null ? t_b2b_dlzh.hkgs : t_b2b_hkgsxx.hkgs}</td>
    </tr>
     <tr>
    	<td colspan="9" class="title">自动支付</td>
    </tr>
    <tr>
    	<c:forEach  items="${zffsList}"  var="vc" varStatus="i">
    		<c:if test="${vc.id ne 'fs0' && (fn:indexOf(t_b2b_hkgsxx.zdzfzc,i.count) >-1 || fn:indexOf(t_b2b_hkgs.zdzffs,i.count) >-1)}">
    			<td>
    				<input type="hidden" name="zdzffs" id="zdzffs${i.count}" value="${fn:indexOf(t_b2b_hkgs.zdzffs,i.count) >-1 ? i.count : ''}">
					<input type="checkbox" id="zdzf${i.count}" ${fn:indexOf(t_b2b_hkgs.zdzffs,i.count) >-1 or empty t_b2b_hkgs.zdzffs ? 'checked' : ''}   onclick="checkZffs(this,'zdzffs','${i.count}')">${vc.mc}
    			</td>
    		</c:if>
    	</c:forEach>
    </tr>
    <tr>
    	<td colspan="9" class="title">手动支付</td>
    </tr>
    <tr>
    <c:forEach  items="${zffsList}"  var="vc">
    	<c:if test="${vc.id ne 'fs0'}">
    		<td>
				<input type="hidden" name="${vc.id}" id="${vc.id}" value="${vc.val ne '0' ? '1' : '0'}">
				<input type="checkbox" name="${vc.id}${vc.id}"  ${vc.val ne '0' ? 'checked' : ''} onclick="checkFs(this,'${vc.id}');">${vc.mc}
			</td>
    	</c:if>
    </c:forEach>
    </tr>
</table>

<%@include file="qtxx.jsp" %>

</form>
<div class="title" style="margin-left:-14px">航司账号密码设置：</div>
<c:if test="${dlzhPage.list!=null}">
	<multipage:pone actionFormName="dlzhPage" page="${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx" var="fyurl" ></multipage:pone>
	<!-- 内容列表显示区 -->
	  <display:table id="vc" name="dlzhPage.list" class="list_table" requestURI="${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx?dtableid=vc" sort="external"  excludedParams="dtableid"  style="width:100%;" >
		<display:column title="序 号" style="text-align:center">
			<span class="sxh">${vc_rowNum}</span>
			<input type="hidden" id="id${vc_rowNum-1}" name="id" value="${vc.id}"/>
		</display:column>
		<c:choose>
			<c:when test="${empty vc.id}">
				<display:column title="操 作<img class='images' src='${ctx}/static/images/wdimages/drop-add.gif' title='点击添加账号' onclick='addTr(\"vc\",\"tpl1\", -1);'/>" style="text-align:center">
		    		<a href="###" style="cursor: pointer;" onclick="delTr('vc',this,'${vc.id}','${vc.bca}')" >删除</a>
		    		<a href="###" style="cursor: pointer;" onclick="saveB2cZh('${vc_rowNum-1}')" >保存</a>
			    </display:column>
			    <display:column title="登录账号<font color='red'>*</font>" style="text-align:center"><input type="text" id="zh${vc_rowNum-1}" name="zh"  /></display:column>             
			    <display:column title="登录密码<font color='red'>*</font>" style="text-align:center"><input type="password"  id="mm${vc_rowNum-1}" name="mm" /></display:column>
			    <display:column title="登录方式" style="text-align:center">
			    	<select name="dlfs" id="dlfs${vc_rowNum-1}">
						<option value="">默认</option>
						<c:forEach items="${vfn:dictList('HKGSSZDLFS')}" var="oneZt">
							<option value="${oneZt.value}">${oneZt.mc}</option>
	          			</c:forEach>
					</select>
			    </display:column>
			    <display:column title="使用状态" style="text-align:center">
		   		</display:column>
			</c:when>
			<c:when test="${not empty vc.id}">
				<display:column title="操 作<img class='images' src='${ctx}/static/images/wdimages/drop-add.gif' title='点击添加账号' onclick='addTr(\"vc\",\"tpl1\", -1);'/>" style="text-align:center">
		    		<a href="###" style="cursor: pointer;" onclick="delTr('vc',this,'${vc.id}','${vc.bca}')" >删除</a>
		    		<a href="###" style="cursor: pointer;" onclick="updZhZt('${vc.id}','${vc.bca}','${vc.sfsy eq '1' ? '0' : '1' }')" > ${vc.sfsy eq '1'?'<font color="green">启用</font>':'<font color="red">禁用</font>' }</a>
			    </display:column>
			    <display:column title="登录账号<font color='red'>*</font>" style="text-align:center">${vc.zh}</display:column>             
			    <display:column title="登录密码<font color='red'>*</font>" style="text-align:center">${fn:substring(vc.mm,0,3)}...</display:column>
			    <display:column title="登录方式" style="text-align:center">
					<c:forEach items="${vfn:dictList('HKGSSZDLFS')}" var="oneZt">
				  		<c:if test="${vc.dlfs eq oneZt.value }">
				  			<c:set var="mc" value="${oneZt.mc}"></c:set>
				  		</c:if>
	          		</c:forEach>
	          		${empty mc ? '默认' : mc}
			    </display:column>
			    <display:column title="使用状态" style="text-align:center">
			    	${ vc.sfsy eq '1' ? '<font color="red">已禁用</font>' : '<font color="green">已启用</font>' }
		   		</display:column>
			</c:when>
		</c:choose>
	</display:table>    
	${fyurl}
	<script type="text/javascript">          	
		highlightTableRows("vc");
	</script>
	<script id="tpl1" type="text/html">
		<tr>
			<td style="text-align:center"><span class="sxh"></span><input type="hidden" id="sid" value="" /></td>
			<td style="text-align:center">
				<a href="###" style="cursor: pointer;" onclick="delTr('vc',this,'${vc.id}','${vc.bca}')" >删除</a>
	    		<a href="###" style="cursor: pointer;" onclick="saveB2cZh()" > 保存</a>
			</td>
			<td style="text-align:center"><input type="text" id="szh" name="zh" value="" /></td>
			<td style="text-align:center"><input type="password"  id="smm" name="mm" value="" /></td>
			<td style="text-align:center">
				<select name="dlfs" id="sdlfs" > 
              		<option value="" selected>默认</option> 
              		<c:forEach items="${vfn:dictList('HKGSSZDLFS')}" var="oneZt">
					    <option value="${oneZt.value}">${oneZt.mc}</option>
					</c:forEach>
				</select>
			</td>
			<td style="text-align:center"></td>
		</tr>
	</script>
</c:if>

