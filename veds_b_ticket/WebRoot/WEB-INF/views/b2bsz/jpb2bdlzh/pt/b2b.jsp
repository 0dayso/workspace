<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!-- B2B -->

<%--支付信息 --%>
<%-- 0=手动支付 1=支付宝 2=财付通 3=汇付天下 4=易宝 5=快钱--%>
<input type="hidden" name="mc" value="${empty t_b2b_hkgsxx.mc ? t_b2b_hkgs.mc : t_b2b_hkgsxx.mc}"/>
<input type="hidden" id="hkgs" name="hkgs" value="${t_b2b_hkgsxx.hkgs eq null ? t_b2b_dlzh.hkgs : t_b2b_hkgsxx.hkgs}"/>
<input type="hidden" name="bca" value="${bca}"/>
<table  border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
    	<td colspan="2" class="title">航空公司：${t_b2b_hkgsxx.hkgs eq null ? t_b2b_dlzh.hkgs : t_b2b_hkgsxx.hkgs}</td>
    </tr>
    <tr>
    	<td colspan="2" class="title">支付方式</td>
    </tr>
    <c:forEach  items="${zffsList}"  var="vc">
	    <c:if test="${vc.id ne 'fs0'}">
	      	<tr>
				<td width="60%">${vc.mc}</td>
				<td width="40%">
					<input type="hidden" name="${vc.id}" id="${vc.id}" value="${vc.val ne '0' ? '1' : '0'}">
					<input type="checkbox" name="${vc.id}${vc.id}"  ${vc.val ne '0' ? 'checked' : ''} onclick="checkFs(this,'${vc.id}');">
				</td>
			</tr>
		</c:if>
    </c:forEach>
</table>

 
 <table border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
    	<td colspan="4" class="title">登录账号信息：</td>
    </tr>
	<tr>
		<td>是否在出票页面开启【${t_b2b_hkgsxx.hkgs eq null ? t_b2b_dlzh.hkgs : t_b2b_hkgsxx.hkgs}】 BPET出票： 
			<input type="radio" name="sfzc" value="1" ${t_b2b_hkgs.sfzc eq null || t_b2b_hkgs.sfzc eq '1' ? 'checked' : ''}/>是
			<input type="radio" name="sfzc" value="0" ${t_b2b_hkgs.sfzc eq '0' ? 'checked' : '' }/>否
		</td>
		<td>替代的航空公司：
			<select name="bzbz">
			 <option value="">不替代</option>
			 <c:forEach items="${t_b2b_hkgsxxList}" var="one">
			 	<option ${t_b2b_hkgs.bzbz eq one.hkgs ? 'selected' : ''} value="${one.hkgs }">${one.hkgs }${one.mc }</option>
			 </c:forEach>
			</select>&nbsp;
			用于航空公司合并后可以到合并航空公司出票。如GS和CN可以用HU替代
         </td>
      </tr>
   </table>
 
<%@include file="qtxx.jsp" %>

<div class="title" style="margin-left:-14px">航司账号密码设置：</div>
<c:if test="${dlzhPage.list!=null}">
	<multipage:pone actionFormName="dlzhPage" page="${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx" var="fyurl" ></multipage:pone>
	<!-- 内容列表显示区 -->
	  <display:table id="vc" name="dlzhPage.list" class="list_table" requestURI="${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx?dtableid=vc" sort="external"  excludedParams="dtableid"  style="width:100%;" >
		<display:column title="序 号" style="text-align:center">
			<span class="sxh">${vc_rowNum}</span>
			<input type="hidden" id="id${vc_rowNum-1}" name="id" value="${vc.id}"/>
			<c:set var="num" value="0"></c:set>
		</display:column>
		<display:column title="操 作<img class='images' src='${ctx}/static/images/wdimages/drop-add.gif' title='点击添加账号' onclick='addTr(\"vc\",\"tpl1\", -1);'/>" style="text-align:center">
	    	<a href="###" style="cursor: pointer;" onclick="delTr('vc', this, '${vc.id}', '${vc.bca}');" >删除</a>
	    	<a href="###" style="cursor: pointer;" onclick="saveB2bZh('${vc_rowNum-1}')" >保存</a>
	    </display:column>
	    <display:column title="登录账号<font color='red'>*</font>" style="text-align:center"><input type="text" id="zh${vc_rowNum-1}" name="zh" value="${vc.zh}" datatype="*" nullmsg="请填写值" /></display:column>             
	    <display:column title="登录密码<font color='red'>*</font>" style="text-align:center"><input type="password" id="mm${vc_rowNum-1}" name="mm" value="${vc.mm}" datatype="*" nullmsg="请填写值" /></display:column>
	    <display:column title="office号" style="text-align:center">
	    	<select id="office${vc_rowNum-1}" name="office">
	    		<option value="">==请选择==</option>
	    		<c:forEach items="${pzList}" var="pz">
	    			<option value="${pz.officeid}" ${pz.officeid eq vc.office ? 'selected' : ''}>${pz.officeid}</option>
	    		</c:forEach>
	    	</select>
	    </display:column>
	    <display:column title="使用用户<font color='red'>*</font>" style="text-align:center">
		    <input type="text" id="yhbhs${vc_rowNum-1}" name="yhbhs" value="${vc.ex.YHBH.xm}" datatype="*" nullmsg="请选择值" style="width:100px"/>
		    <input type="hidden" name="yhbh"  id="yhbh${vc_rowNum-1}" value="${vc.yhbh}" class="yh"/>
	    </display:column>
	</display:table>    
	${fyurl}
	<script type="text/javascript">          	
		highlightTableRows("vc");
	</script>
	<script id="tpl1" type="text/html">
		<tr>
			<td style="text-align:center"><span class="sxh"></span><input type="hidden" id="sid" name="id" value=""/></td>
			<td style="text-align:center">
				<a href="###" style="cursor: pointer;" onclick="delTr('vc', this, '${vc.id}', '${vc.bca}');" >删除</a>
		    	<a href="###" style="cursor: pointer;" onclick="saveB2bZh()" >保存</a>
			</td>
			<td style="text-align:center"><input type="text" id="szh" name="zh" value="" datatype="*" nullmsg="请填写值" /></td>
			<td style="text-align:center"><input type="password" id="smm" name="mm" value="" datatype="*" nullmsg="请填写值" /></td>
			<td style="text-align:center">
				<select id="soffice" name="office">
	    			<option value="">==请选择==</option>
	    			<c:forEach items="${pzList}" var="pz">
	    				<option value="${pz.officeid}">${pz.officeid}</option>
	    			</c:forEach>
	    		</select>
			</td>
			<td style="text-align:center">
				<input type="text" id="yhbhss" name="yhbhs" value="" datatype="*" nullmsg="请选择值" style="width:100px"/>
				<input type="hidden" name="yhbh"  id="syhbh" value="" class="yh"/>
			</td>
		</tr>
	</script>
</c:if>
