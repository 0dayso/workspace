<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<%--集中出票页面的js都放在此页面--%>
<!--航司B2B Start -->
<body>
<c:if test="${not empty errmsg}">
<script type="text/javascript">
	alert("${errmsg}");
</script>
</c:if>
<table width="100%" border="0" >
	<input type="hidden" id="dkhbm" name="dkhbm" value="${jpB2bHkgs.dkhbm}" />	
	<tr>
		<td>
			<input type="button" name="button" id="b2blogin" onclick="b2bLoginPage('${jpKhdd.ddbh}','${jpKhdd.hkgs }');" value="点击登录" class="ext_btn ext_btn_submit" />
		</td>
	 	<td><label>
        	B2B[${jpKhdd.hkgs}]</label>
        </td>
        <td >BPET</td>
		<td>
			账号:
			<c:if var="bl1" test="${fn:length(dlzhList)>=2}">
			<span style="position:relative;">
			<span>
				<select id="sel_users" class="input01" style="width: 110px; background-color: #e1ecfe;" onChange="sethkgszh(this);">
					<c:forEach items="${dlzhList}" var="dlzh">
			            <option value="${dlzh.mm}" officeno="${dlzh.office}">${dlzh.zh}</option>
			        </c:forEach>
				</select>				
			</span>
			<span style="width:90px;height: 21px;position:absolute;right:19px;"><input id="hszh" name="hszh" class="" style="width:88px; height: 17px" value="${dlzhList[0].zh}" class="" /></span>
			</span>
			</c:if>
			<c:if test="${!bl1}">
			<input id="hszh" name="hszh" class="input01" size="8" value="${empty hszh ? dlzhList[0].zh : hszh}" />
			</c:if>
			</td>
		<td>密码: <input type="password" id="hsmm" name="hsmm" value="${empty hszh ? dlzhList[0].mm : hsmm}" size="8" class="input01" /></td>
		<td>office: <input type="text" id="office" name="office" value="${empty hszh ? dlzhList[0].office : office}" size="8" class="input01" /></td>
		<td>验证码:<input id="yzm" name="yzm" size="8" class="input01" value="" style="" /></td>
		<td>
			<input id="validatenoid" name="validatenoid" type="hidden" value=""/>         
		          <img id="validatenoImg" src="${ctx }${yzmpath}" alt="" title="" style="" width="80" height="30" 
		          onclick="yzmImgPage()" />
		    
		</td>
		<td><c:if test="${not empty errmsg}"><span style="color: red">${errmsg}</span></c:if></td><td>
	</tr>
</table>
<script type="text/javascript">
	yzmImgPage = function(){
	  if($('hszh').val() == ""){
		  return ;
	  }
	  loadValidatenoImg($('#validatenoImg'),'${jpKhdd.ddbh}','${jpKhdd.hkgs }',$('#hszh').val());
	}
	//自动加载验证码
	$(function(){
		yzmImgPage();
	});
</script>
</body>