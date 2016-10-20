<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:choose>
  <c:when test="${param.cgly eq 'BSP' or empty param.cgly }"><%@include file="cgly/bsp.jsp" %></c:when>
  <c:when test="${param.cgly eq 'OP' or param.cgly eq 'TB'}"><%@include file="cgly/opAndtb.jsp" %></c:when>
  <c:when test="${param.cgly eq 'B2C' }"><%@include file="cgly/b2c.jsp" %></c:when>
  <c:when test="${param.cgly eq 'BOP' }"><%@include file="cgly/bop.jsp" %></c:when>
  <c:when test="${param.cgly eq 'B2B' }"><%@include file="cgly/b2b.jsp" %></c:when>
</c:choose>
