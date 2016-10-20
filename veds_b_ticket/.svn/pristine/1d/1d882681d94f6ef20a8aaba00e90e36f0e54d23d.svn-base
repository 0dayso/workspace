<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:choose>
  <c:when test="${param.cgly eq 'BSP' }"><%@include file="cgly/bsp.jsp" %></c:when>
  <c:when test="${param.cgly eq 'BOP' }"><%@include file="cgly/bop.jsp" %></c:when>
  <c:when test="${param.cgly eq 'OP' }"><%@include file="cgly/op.jsp" %></c:when>
  <c:otherwise><%@include file="cgly/other.jsp" %></c:otherwise>
</c:choose>