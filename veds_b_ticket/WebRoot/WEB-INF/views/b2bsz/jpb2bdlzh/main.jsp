<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>航空公司支付设置</title>
</head>
  <frameset cols="170,*" frameborder="NO">  
  <frame id="contents_frame" style="width: 200px;margin-left: 5px;" name="contents_frame" target="main" src="${ctx}/vedsb/b2bsz/jpb2bdlzh/tree?pt=${param.pt}&bca=${param.bca}" frameborder="0" >
  <frame id="info_frame"    name="info_frame" src="${ctx}/vedsb/b2bsz/jpb2bdlzh/viewb2bzhsz?pt=${param.pt}&bca=${param.bca}" frameborder="0" >
  <noframes>
  </noframes>
</frameset>
</html>