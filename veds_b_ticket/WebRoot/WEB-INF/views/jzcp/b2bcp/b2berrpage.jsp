<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>b2b错误信息页面</title>
  </head>
  
  <body>
  <table width="100%" class="loginboxzdcp" align="center">
  <input type="hidden" id="dkhbm" name="dkhbm" value="${jpB2bHkgs.dkhbm}" />	
  <tr>
  	<td class="red">
  		<b>[B2B代购]${jpKhdd.hkgs}航空公司PNR[${jpKhdd.cgPnrNo}/${jpKhdd.cgHkgsPnr}]</b><br>${errmsg}
  	</td>
  </tr>
  <tr>
  	<td>
   		<input type="button" value=" 返 回 " onclick="loadB2BPolicy()"  class="ext_btn ext_btn_submit" />
   		<input type="button" value=" 退出登陆 " onclick="b2bLogout('${jpKhdd.ddbh}','${jpKhdd.hkgs}','')"  class="ext_btn ext_btn_submit" />
    </td>
  </tr>
  </table>
  </body>
</html>
